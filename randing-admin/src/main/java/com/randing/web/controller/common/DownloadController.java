package com.randing.web.controller.common;

import com.randing.common.utils.DownloadUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "模板下载", tags = "模板相关接口")
public class DownloadController {

    //科研计划模版
    private static final String applyWorldFilePath = "awt.docx";

    @GetMapping("/applyWordTemplate")
    //@PreAuthorize("@ss.hasPermi('project:member:download')")
    @ApiOperation(value="科研计划模板.docx",notes="applyWordTemplate")
    public void importTemplate(HttpServletRequest request, HttpServletResponse response) {
        DownloadUtils.downloadFile(request, response, "attachment/awt.docx","科研计划模板.docx");
    }
}
