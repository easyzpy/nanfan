//package com.randing.common.utils;
//
//import com.aliyun.oss.OSSClient;
//import com.aliyun.oss.model.*;
//import lombok.Data;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//import java.io.*;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.UUID;
//
///**
// * AliyunOss
// *
// * @author fly xia
// * @date 2020-12-23
// */
//@Slf4j
//@Data
//@Component
//public class AliyunOssUtil {
//    /**
//     * 斜杠
//     */
//    private final String FLAG_SLANTING_ROD = "/";
//    /**
//     * http://
//     */
//    private final String FLAG_HTTP = "http://";
//    /**
//     * https://
//     */
//    private final String FLAG_HTTPS = "https://";
//    /**
//     * 空字符串
//     */
//    private final String FLAG_EMPTY_STRING = "";
//    /**
//     * 点号
//     */
//    private final String FLAG_DOT = ".";
//    /**
//     * 横杠
//     */
//    private final String FLAG_CROSSBAR = "-";
//
//    /**
//     * 缺省的最大上传文件大小：20M
//     */
//    private final int DEFAULT_MAXIMUM_FILE_SIZE = 20;
//
//
//    // 文件保存路径
//    public final static String FILE_PATH_OSS = "";
//
//    /**
//     * endpoint
//     */
//    @Value("${aliyun.oss.endpoint}")
//    private String endpoint;
//
//    /**
//     * access key id
//     */
//    @Value("${aliyun.oss.keyid}")
//    private String accessKeyId;
//
//    /**
//     * access key secret
//     */
//    @Value("${aliyun.oss.keysecret}")
//    private String accessKeySecret;
//
//    /**
//     * bucket name (namespace)
//     */
//    @Value("${aliyun.oss.bucketname}")
//    private String bucketName;
//
//    /**
//     * maxFileSize 文件大小 /M
//     */
//    @Value("${aliyun.oss.maxFileSize}")
//    private String maxFileSize;
//
//    /**
//     * 以文件流的方式上传文件
//     * @Author: Captain&D
//     * @cnblogs: https://www.cnblogs.com/captainad
//     * @param fileName 文件名称
//     * @param filePath 文件路径
//     * @param inputStream 文件输入流
//     * @return
//     */
//    public String uploadFile(String fileName, String filePath, InputStream inputStream) {
//        return coreUpload(fileName, filePath, inputStream);
//    }
//
//    /**
//     * 核心上传功能
//     * @Author: Captain&D
//     * @cnblogs: https://www.cnblogs.com/captainad
//     * @param fileName 文件名
//     * @param filePath 文件路径
//     * @param inputStream 文件输入流
//     * @return
//     */
//    private String coreUpload(String fileName, String filePath, InputStream inputStream) {
//        //log.info("Start to upload file....");
//        if(StringUtils.isEmpty(fileName) || inputStream == null) {
//            log.error("Filename Or inputStream is lack when upload file.");
//            return null;
//        }
//        if(StringUtils.isEmpty(filePath)) {
//            log.warn("File path is lack when upload file but we automatically generated");
//            String dateCategory = DateUtil.dateTimeFormat(new Date(), "yyyyMMdd");
//            filePath = "".concat(dateCategory).concat(FLAG_SLANTING_ROD);
//        }
//        String fileUrl;
//        OSSClient ossClient = null;
//        try{
//
//            // If the upload file size exceeds the limit
//            long maxSizeAllowed = getMaximumFileSizeAllowed();
//            if(Long.valueOf(inputStream.available()) > maxSizeAllowed) {
//                log.error("Uploaded file is too big.");
//                return null;
//            }
//
//            ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
//
//            // Create bucket if not exists
//            if (!ossClient.doesBucketExist(bucketName)) {
//                log.info("Bucket '{}' is not exists and create it now.", bucketName);
//                ossClient.createBucket(bucketName);
//                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
//                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
//                ossClient.createBucket(createBucketRequest);
//            }
//
//            /*********************************/
//            // List the bucket in my account
//            //listBuckets(ossClient);
//            /*********************************/
//
//            // File path format
////            if(!filePath.startsWith(FLAG_SLANTING_ROD)) {
////                filePath = FLAG_SLANTING_ROD.concat(filePath);
////            }
//            if(!filePath.endsWith(FLAG_SLANTING_ROD)) {
//                filePath = filePath.concat(FLAG_SLANTING_ROD);
//            }
//            // File url
//            StringBuilder buffer = new StringBuilder();
//            buffer.append(filePath).append(fileName);
//            fileUrl = buffer.toString();
//            //log.info("After format the file url is {}", fileUrl);
//
//            //2021-01-14 13:20分 wsm  添加执行类型
//            //param fileUrl 表示上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
//            // 创建PutObjectRequest对象。
//            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileUrl, inputStream);
//            // 设置 ContentType类型,防止图片等资源无法使用url直接访问
//            ObjectMetadata metadata = new ObjectMetadata();
//            metadata.setContentType(getContentType(fileUrl.substring(fileUrl.lastIndexOf("."))));
//            putObjectRequest.setMetadata(metadata);
//            // 开始上传
//            ossClient.putObject(putObjectRequest);
//
//            // Upload file and set ACL
//            //ossClient.putObject(bucketName, fileUrl, inputStream);
//
//            //PutObjectResult result = ossClient.putObject(new PutObjectRequest(bucketName, fileUrl, inputStream));
//            //ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
//
//            long time = new Date().getTime();
//            //url过期时间为100年
//            Date expiration = new Date(time + 1000L * 3600 * 24 * 365 * 100);
//            fileUrl = ossClient.generatePresignedUrl(bucketName, fileUrl, expiration).toString();
//
//            //log.info("upload path is {}", fileUrl);
//        }catch (Exception e){
//            log.error("Upload file failed.", e);
//            fileUrl = null;
//        }finally {
//            if(ossClient != null) {
//                ossClient.shutdown();
//            }
//        }
//        return fileUrl;
//    }
//
//    /**
//     * 列出buckets下的所有文件
//     * @Author: Captain&D
//     * @cnblogs: https://www.cnblogs.com/captainad
//     * @param ossClient
//     */
//    private void listObjects(OSSClient ossClient) {
//        System.out.println("Listing objects");
//        ObjectListing objectListing = ossClient.listObjects(bucketName);
//        for (OSSObjectSummary objectSummary : objectListing.getObjectSummaries()) {
//            System.out.println(" - " + objectSummary.getKey() + "  " +
//                    "(size = " + objectSummary.getSize() + ")");
//        }
//        System.out.println();
//    }
//
//    /**
//     * 列出当前用户下的所有bucket
//     * @Author: Captain&D
//     * @cnblogs: https://www.cnblogs.com/captainad
//     * @param ossClient
//     */
//    private void listBuckets(OSSClient ossClient) {
//        System.out.println("Listing buckets");
//        ListBucketsRequest listBucketsRequest = new ListBucketsRequest();
//        listBucketsRequest.setMaxKeys(500);
//        for (Bucket bucket : ossClient.listBuckets()) {
//            System.out.println(" - " + bucket.getName());
//        }
//        System.out.println();
//    }
//
//    /**
//     * 以文件的形式上传文件
//     * @Author: Captain&D
//     * @cnblogs: https://www.cnblogs.com/captainad
//     * @param fileName
//     * @param filePath
//     * @param file
//     * @return
//     */
//    public String uploadFile(String fileName,String filePath,File file) {
//        if(file == null) {
//            log.warn("File is lack when upload.");
//            return null;
//        }
//        if(StringUtils.isEmpty(fileName)) {
//            log.warn("File name is lack when upload file but we automatically generated");
//            String uuidFileName = UUID.randomUUID().toString().replace(FLAG_CROSSBAR, FLAG_EMPTY_STRING);
//            String fname = file.getName();
//            String suffix = fname.substring(fname.lastIndexOf(FLAG_DOT), fname.length());
//            fileName = uuidFileName.concat(suffix);
//        }
//        InputStream inputStream = null;
//        String fileUrl = null;
//        try{
//            inputStream = new FileInputStream(file);
//            fileUrl = uploadFile(fileName, filePath,inputStream);
//        }catch (Exception e){
//            log.error("Upload file error.", e);
//        }finally {
//            if (inputStream != null) {
//                try {
//                    // 关闭流
//                    inputStream.close();
//                } catch (IOException e) {
//                    log.error(e.getMessage());
//                    e.printStackTrace();
//                    System.out.println("未关闭流");
//                }
//            }
//
//        }
//        return fileUrl;
//    }
//
//    /**
//     * 获取访问的base地址
//     * @Author: Captain&D
//     * @cnblogs: https://www.cnblogs.com/captainad
//     * @return
//     */
//    private String getHostUrl() {
//        String hostUrl = null;
//        if(this.endpoint.startsWith(FLAG_HTTP)) {
//            hostUrl = FLAG_HTTP.concat(this.bucketName).concat(FLAG_DOT)
//                    .concat(this.endpoint.replace(FLAG_HTTP, FLAG_EMPTY_STRING)).concat(FLAG_SLANTING_ROD);
//        } else if (this.endpoint.startsWith(FLAG_HTTPS)) {
//            return FLAG_HTTPS.concat(this.bucketName).concat(FLAG_DOT)
//                    .concat(this.endpoint.replace(FLAG_HTTPS, FLAG_EMPTY_STRING)).concat(FLAG_SLANTING_ROD);
//        }
//        return hostUrl;
//    }
//
//    /**
//     * 获取最大允许上传文件的大小
//     * @Author: Captain&D
//     * @cnblogs: https://www.cnblogs.com/captainad
//     * @return
//     */
//    private long getMaximumFileSizeAllowed() {
//        // 缓存单位是M
//        String maxConfigVal = maxFileSize;
//        if(StringUtils.isEmpty(maxConfigVal)) {
//            return DEFAULT_MAXIMUM_FILE_SIZE * 1024L * 1024L;
//        }else {
//            return Long.valueOf(maxConfigVal.trim()) * 1024L * 1024L;
//        }
//    }
//
//    /**
//     * 删除文件
//     * @Author: Captain&D
//     * @cnblogs: https://www.cnblogs.com/captainad
//     * @param fileUrl 文件访问的全路径
//     */
//    public void deleteFile(String fileUrl) {
//        log.info("Start to delete file from OSS.{}", fileUrl);
//        if(StringUtils.isEmpty(fileUrl)
//                || (!fileUrl.startsWith(FLAG_HTTP)
//                && !fileUrl.startsWith(FLAG_HTTPS))) {
//            log.error("Delete file failed because the invalid file address. -> {}", fileUrl);
//            return;
//        }
//        OSSClient ossClient = null;
//        try{
//            /**
//             * http:// bucketname                                dev/test/pic/abc.jpg = key
//             * http:// captainad.oss-ap-southeast-1.aliyuncs.com/dev/test/pic/abc.jpg
//             */
//            String key = fileUrl.replace(getHostUrl(), FLAG_EMPTY_STRING);
//            if(log.isDebugEnabled()) {
//                log.debug("Delete file key is {}", key);
//            }
//            ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
//            ossClient.deleteObject(bucketName, key);
//        }catch (Exception e){
//            log.error("Delete file error.", e);
//        } finally {
//            if(ossClient != null) {
//                ossClient.shutdown();
//            }
//        }
//    }
//
//
//    /**
//     * 删除某个Object
//     * @param bucketUrl
//     * @return
//     */
//    public boolean deleteObject(String bucketUrl) {
//        OSSClient client = new OSSClient(this.endpoint, this.accessKeyId, this.accessKeySecret);
//        try {
//            // 删除Object.
//            client.deleteObject(bucketName, bucketUrl);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        } finally {
//            client.shutdown();
//        }
//        return true;
//    }
//
//    /**
//     * 删除多个Object
//     * @param bucketUrls
//     * @return
//     */
//    public boolean deleteObjects(List<String> bucketUrls) {
//        OSSClient client = new OSSClient(this.endpoint, this.accessKeyId, this.accessKeySecret);
//        try {
//            // 删除Object.
//            DeleteObjectsResult deleteObjectsResult = client.deleteObjects(new DeleteObjectsRequest(this.bucketName).withKeys(bucketUrls));
//            List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        } finally {
//            client.shutdown();
//        }
//        return true;
//    }
//
//    /**
//     * 删除指定目录下文件
//     * @param path path不包含bucketName
//     * @return
//     */
//    public boolean deleteFilesInPath(String path) {
//        OSSClient client = new OSSClient(this.endpoint, this.accessKeyId, this.accessKeySecret);
//        try {
//            List<OSSObjectSummary> objs = client.listObjects(this.bucketName,path).getObjectSummaries();
//            List<String> bucketUrls = new ArrayList<>();
//            for (OSSObjectSummary obj:objs) {
//                bucketUrls.add(obj.getKey());
//            }
//            if(bucketUrls.size()>0)
//            {
//                // 删除Object.
//                client.deleteObjects(new DeleteObjectsRequest(bucketName).withKeys(bucketUrls));
//                //DeleteObjectsResult deleteObjectsResult = client.deleteObjects(new DeleteObjectsRequest(bucketName).withKeys(bucketUrls));
//                //List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        } finally {
//            client.shutdown();
//        }
//        return true;
//    }
//
//
//    /**
//     * TODO 判断获取文件保存内存
//     *
//     * @param FilenameExtension
//     * @return
//     */
//    public static String getContentType(String FilenameExtension) {
//        if (FilenameExtension.equalsIgnoreCase(".bmp")) {
//            return "image/bmp";
//        }
//        if (FilenameExtension.equalsIgnoreCase(".gif")) {
//            return "image/gif";
//        }
//        if (FilenameExtension.equalsIgnoreCase(".jpeg") ||
//                FilenameExtension.equalsIgnoreCase(".jpg") ||
//                FilenameExtension.equalsIgnoreCase(".png")) {
//            return "image/jpg";
//        }
//        if (FilenameExtension.equalsIgnoreCase(".pdf")) {
//            return "application/pdf";
//        }
//        if (FilenameExtension.equalsIgnoreCase(".html")) {
//            return "text/html";
//        }
//        if (FilenameExtension.equalsIgnoreCase(".txt")) {
//            return "text/plain";
//        }
//        if (FilenameExtension.equalsIgnoreCase(".csv")||
//                FilenameExtension.equalsIgnoreCase(".xls")||
//                FilenameExtension.equalsIgnoreCase(".xlsx")) {
//            return "application/vnd.ms-excel";
//        }
//        if (FilenameExtension.equalsIgnoreCase(".vsd")) {
//            return "application/vnd.visio";
//        }
//        if (FilenameExtension.equalsIgnoreCase(".pptx") ||
//                FilenameExtension.equalsIgnoreCase(".ppt")) {
//            return "application/vnd.ms-powerpoint";
//        }
//        if (FilenameExtension.equalsIgnoreCase(".docx") ||
//                FilenameExtension.equalsIgnoreCase(".doc")) {
//            return "application/msword";
//        }
//        if (FilenameExtension.equalsIgnoreCase(".xml")) {
//            return "text/xml";
//        }
//        if (FilenameExtension.equalsIgnoreCase(".mp4")) {
//            return "video/mp4";
//        }
//        if (FilenameExtension.equalsIgnoreCase(".mp3")) {
//            return "audio/mp3";
//        }
//        return "";
//    }
//}
