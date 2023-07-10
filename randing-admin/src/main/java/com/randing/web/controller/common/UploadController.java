package com.randing.web.controller.common;

import com.randing.common.core.domain.AjaxResult;
import com.randing.system.domain.po.SelfExamFile;
import com.randing.system.service.ISelfExamFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@Api("土地申请")
public class UploadController {


    @Autowired
    private ISelfExamFileService selfExamFileService;

    @Value("${tomcatBasedir}")
    public String tomcatBasedir;


    //common/image/unit/jpg/5dfcabfc-05cb-4ba2-96f8-e65fa97786db
    @PostMapping("uploadFile")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "文件", name = "file", required = true),
            @ApiImplicitParam(value = "自查表id", name = "selfExaminationId", required = true)
    })
    public AjaxResult<SelfExamFile> uploadFile(MultipartFile file, HttpServletRequest request) {
//        log.info("base:{}", tomcatBasedir);
//        String selfExaminationId = request.getParameter("selfExaminationId");
//        if (selfExaminationId == null) {
//            return AjaxResult.error("参数错误");
//        }
        //ssh -R 80:127.0.0.1:8095 sh@sh3.neiwangyun.net
        SelfExamFile selfExamFile = selfExamFileService.uploadFile(file);

        return AjaxResult.success(selfExamFile);
    }
    @PostMapping("uploadApplyLandFile")
    @ApiOperation("土地申请上传文件 20230709")
    public AjaxResult<SelfExamFile> uploadApplyLandFile(MultipartFile file, HttpServletRequest request) {
        SelfExamFile selfExamFile = selfExamFileService.uploadApplyLandFile(file);

        return AjaxResult.success(selfExamFile);
    }

    @GetMapping("/common/preview")
    public void filePreviewOrDownload(HttpServletRequest request, HttpServletResponse response) {
        String fileName = request.getParameter("fileName");
        //type. p预览，d下载
        response.reset();
        response.setCharacterEncoding("UTF-8");

        File file = new File(tomcatBasedir + File.separator + fileName);
        FileInputStream fis = null;
        OutputStream os = null;
        try {
            fis = new FileInputStream(file);

            if (fileName.contains("pdf")) {
                response.setContentType("application/pdf");
//                response.setHeader("Content-Disposition", "attachment;filename*=utf-8''" + System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf(".")));
            } else {
//                response.setContentType(ContentType.APPLICATION_OCTET_STREAM.getMimeType());
                String extension = getExtension(fileName);
                response.setContentType("image/"+extension);
//                response.setHeader("Content-Disposition", "attachment;filename*=utf-8''" + fileName.substring(fileName.lastIndexOf("/")+1)+getExtension(fileName));

            }

            os = response.getOutputStream();
            byte[] bytes = new byte[1024];
            int length = 0;
            while ((length = fis.read(bytes)) != -1) {
                os.write(bytes, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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
    public void filePreviewOrDownload(@PathVariable("fileName") String fileName
            , @RequestParam("url") String url
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
        } finally {
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

    private String getExtension(String path) {
//        "pdf".equals(extension) &&!"jpg".equals(extension) && !"png".equals(extension) && !"jpeg".equals(extension)&&!"gif".equals(extension)
        if (path.contains("pdf")) {
            return "pdf";
        } else if (path.contains("jpg")) {
            return "jpg";
        } else if (path.contains("png")) {
            return "png";
        } else if (path.contains("jpeg")) {
            return "jpeg";
        } else if (path.contains("gif")) {
            return "gif";
        }
        return null;
    }

}
