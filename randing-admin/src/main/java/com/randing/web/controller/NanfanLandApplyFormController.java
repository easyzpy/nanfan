package com.randing.web.controller;


import com.randing.common.core.domain.AjaxResult;
import com.randing.system.domain.vo.NanfanLandApplyFormVo;
import com.randing.system.service.INanfanLandApplyFormService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 崖州湾实验室南繁配套用地申请表 前端控制器
 * </p>
 *
 * @author Leen
 * @since 2022-12-22
 */
@RestController
@Api("崖州湾实验室南繁配套用地申请表 前端控制器")
@RequestMapping("/nanfanLandApplyForm")
public class NanfanLandApplyFormController {

    @Autowired
    private INanfanLandApplyFormService nanfanLandApplyFormService;

    @PostMapping("listPage")
    @ApiOperation("崖州湾实验室南繁配套用地申请表 列表")
    public AjaxResult listPage(@RequestBody NanfanLandApplyFormVo nanfanLandApplyFormVo) {
        return AjaxResult.success(nanfanLandApplyFormService.listPage(nanfanLandApplyFormVo));
    }

    @GetMapping("findById")
    @ApiOperation("崖州湾实验室南繁配套用地申请表 详情")
    public AjaxResult findById(@RequestParam("id") Long id) {
        return AjaxResult.success(nanfanLandApplyFormService.findById(id));
    }
}

