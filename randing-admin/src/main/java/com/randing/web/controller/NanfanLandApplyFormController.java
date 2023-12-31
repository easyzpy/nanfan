package com.randing.web.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.randing.common.core.domain.AjaxResult;
import com.randing.system.domain.vo.KeepApplyReqDTO;
import com.randing.system.domain.vo.base.NanfanLandApplyFormVo;
import com.randing.system.domain.vo.base.NanfanLandApplyPostVo;
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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

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
@RequestMapping("/land-apply")
public class NanfanLandApplyFormController {

    @Autowired
    private INanfanLandApplyFormService nanfanLandApplyFormService;

    @PostMapping("getKeepApplay")
    @ApiOperation("崖州湾实验室南繁配套用地申请表 列表")
    public AjaxResult<Page<NanfanLandApplyFormVo>> listPage(@RequestBody KeepApplyReqDTO keepApplyReqDTO) {
        return AjaxResult.success(nanfanLandApplyFormService.getKeepApplay(keepApplyReqDTO));
    }
    @PostMapping("getPushApply")
    @ApiOperation("崖州湾实验室南繁配套用地申请表 待审核列表")
    public AjaxResult<Page<NanfanLandApplyFormVo>> getPushApply(@RequestBody KeepApplyReqDTO keepApplyReqDTO) {
        return AjaxResult.success(nanfanLandApplyFormService.getPushApply(keepApplyReqDTO));
    }
    @PostMapping("getAdoptApplay")
    @ApiOperation("崖州湾实验室南繁配套用地申请表 已审核列表")
    public AjaxResult<Page<NanfanLandApplyFormVo>> getAdoptApplay(@RequestBody KeepApplyReqDTO keepApplyReqDTO) {
        return AjaxResult.success(nanfanLandApplyFormService.getAdoptApplay(keepApplyReqDTO));
    }
    @PostMapping("getfailApplay")
    @ApiOperation("崖州湾实验室南繁配套用地申请表 已退回列表")
    public AjaxResult<Page<NanfanLandApplyFormVo>> getfailApplay(@RequestBody KeepApplyReqDTO keepApplyReqDTO) {
        return AjaxResult.success(nanfanLandApplyFormService.getfailApplay(keepApplyReqDTO));
    }

    @PostMapping("AllApproApply")
    @ApiOperation("崖州湾实验室南繁配套用地申请表 审批列表全部")
    public AjaxResult<Page<NanfanLandApplyFormVo>> AllApproApply(@RequestBody KeepApplyReqDTO keepApplyReqDTO) {
        return AjaxResult.success(nanfanLandApplyFormService.AllApproApply(keepApplyReqDTO));
    }
    @GetMapping("getApplyDetail")
    @ApiOperation("崖州湾实验室南繁配套用地申请表 审批列表全部")
    public AjaxResult<NanfanLandApplyFormVo> getApplyDetail(@RequestParam("id") Long id) {
        return AjaxResult.success(nanfanLandApplyFormService.getApplyDetail(id));
    }

    @GetMapping("findById")
    @ApiOperation("崖州湾实验室南繁配套用地申请表 详情")
    public AjaxResult<NanfanLandApplyFormVo> findById(@RequestParam("id") Long id) {
//        return AjaxResult.success(nanfanLandApplyFormService.findById(id));
        return AjaxResult.success(nanfanLandApplyFormService.findById(id));
    }
    @PostMapping("applySave")
    @ApiOperation("土地申请new 详情 20230709")
    public AjaxResult applySave(@RequestBody NanfanLandApplyPostVo postVo) {

        nanfanLandApplyFormService.landApply(postVo);
        return AjaxResult.success();
    }
}

