package com.randing.web.controller.common;

import com.randing.common.core.domain.AjaxResult;
import com.randing.common.exception.BaseException;
import com.randing.common.exception.file.InvalidExtensionException;
import com.randing.common.utils.DateUtils;
import com.randing.common.utils.file.FileUploadUtils;
import com.randing.common.utils.uuid.IdUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
@Slf4j
public class UploadController {


    @PostMapping("uploadFile")
    public AjaxResult<String> uploadFile(MultipartFile file, HttpServletRequest request) {
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

}
