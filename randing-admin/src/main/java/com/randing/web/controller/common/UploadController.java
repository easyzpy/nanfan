package com.randing.web.controller.common;

import com.randing.common.core.domain.AjaxResult;
import com.randing.common.exception.BaseException;
import com.randing.common.exception.file.InvalidExtensionException;
import com.randing.common.utils.DateUtils;
import com.randing.common.utils.file.FileUploadUtils;
import com.randing.common.utils.uuid.IdUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpRequest;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@Slf4j
public class UploadController {
    @Value("${tomcatBasedir}")
    public String tomcatBasedir;


    @PostMapping("uploadFile")
    public AjaxResult<String> uploadFile(MultipartFile file, HttpServletRequest request) {
        log.info("base:{}", tomcatBasedir);
        try {
            String contextPath = request.getServletContext().getRealPath("/");
            File file1 = new File(contextPath);
            String parentFile = file1.getParentFile().getAbsolutePath();
            contextPath = parentFile + "testimage";
            log.info("contextPath:{}", contextPath);
            String extension = FileUploadUtils.getExtension(file);
            String fileName = contextPath  +DateUtils.datePath() + "/" + IdUtils.fastUUID() + "." + extension;
            File dest = new File(fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            log.info("filePath:{}", dest.getAbsolutePath());
            file.transferTo(dest);
        } catch (IOException e) {
//            throw new RuntimeException(e);
            e.printStackTrace();
            throw new BaseException("上传失败");
        }
        return AjaxResult.success("success");
    }
    @GetMapping("/common/preview")
    public void filePreviewOrDownload(HttpServletRequest request, HttpServletResponse response) {
        String fileName = request.getParameter("fileName");
        //type. p预览，d下载
        response.reset();
        response.setCharacterEncoding("UTF-8");

        File file = new File(tomcatBasedir +File.separator+ fileName);
        FileInputStream fis = null;
        OutputStream os = null;
        try {
            fis = new FileInputStream(file);

            //下载
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename*=utf-8''" + System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf(".")));

            os = response.getOutputStream();
            byte[] bytes = new byte[1024];
            int length = 0;
            while ((length = fis.read(bytes)) != -1) {
                os.write(bytes, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @GetMapping("/common/preview2/{fileName}")
    public void filePreviewOrDownload(@PathVariable("fileName")String fileName
                                      ,@RequestParam("url") String url
            , HttpServletRequest request, HttpServletResponse response) {
//        String fileName = request.getParameter("fileName");

        response.setCharacterEncoding("UTF-8");
        File file = new File(tomcatBasedir + url);
        FileInputStream fis = null;
        OutputStream os = null;
        try {
            fis = new FileInputStream(file);

            //下载
            response.setContentType(ContentType.IMAGE_PNG.getMimeType());
//            response.setHeader("Content-Disposition", "attachment;filename*=utf-8''" + System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf(".")));

            os = response.getOutputStream();
            byte[] bytes = new byte[1024];
            int length = 0;
            while ((length = fis.read(bytes)) != -1) {
                os.write(bytes, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
