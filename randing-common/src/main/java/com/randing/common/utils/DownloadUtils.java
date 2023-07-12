package com.randing.common.utils;

import org.springframework.core.io.ClassPathResource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Files;

public class DownloadUtils {

    public static void downloadFile(HttpServletRequest request, HttpServletResponse response, String filePath, String fileName) {
//        File fileUrl = new File("classpath:attachment"+filePath);
        //浏览器下载后的文件名称showValue,从url中截取到源文件名称以及，以及文件类型，如board.docx;
        ClassPathResource classPathResource = new ClassPathResource(filePath);
        try{
            //根据条件得到文件路径
            //将文件读入文件流
//            InputStream inStream = Files.newInputStream(fileUrl.toPath());
            InputStream inStream = Files.newInputStream(classPathResource.getFile().toPath());
            //获得浏览器代理信息
            final String userAgent = request.getHeader("USER-AGENT");
            //判断浏览器代理并分别设置响应给浏览器的编码格式
            String finalFileName = null;
            //IE浏览器
            if(StringUtils.contains(userAgent, "MSIE")||StringUtils.contains(userAgent,"Trident")){
                finalFileName = URLEncoder.encode(fileName,"UTF8");

            }else if(StringUtils.contains(userAgent, "Mozilla")){
                //google,火狐浏览器
                finalFileName = new String(fileName.getBytes(), "ISO8859-1");
            }else{
                //其他浏览器
                finalFileName = URLEncoder.encode(fileName,"UTF8");
            }
            //设置HTTP响应头
            response.reset();//重置 响应头
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition" ,"attachment;filename=\"" +finalFileName+ "\"");

            // 循环取出流中的数据
            byte[] b = new byte[1024];
            int len;
            while ((len = inStream.read(b)) > 0){
                response.getOutputStream().write(b, 0, len);
            }
            inStream.close();
            response.getOutputStream().close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
