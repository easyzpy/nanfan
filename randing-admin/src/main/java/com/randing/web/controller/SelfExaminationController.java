package com.randing.web.controller;


import com.randing.common.core.domain.AjaxResult;
import com.randing.common.exception.BaseException;
import com.randing.system.domain.po.SelfExaminationActivity;
import com.randing.system.domain.po.SelfExaminationBase;
import com.randing.system.domain.po.SelfExaminationGain;
import com.randing.system.domain.po.SelfExaminationPermanent;
import com.randing.system.domain.vo.SelfStep1ReqVo;
import com.randing.system.domain.vo.Step2SelfExaminationPermanentReqVo;
import com.randing.system.domain.vo.Step3SelfExaminationBaseReqVo;
import com.randing.system.domain.vo.Step4SelfExaminationActivityReqVo;
import com.randing.system.domain.vo.Step5SelfExaminationGainReqVo;
import com.randing.system.domain.vo.Step6SelfExamination;
import com.randing.system.service.ISelfExaminationService;
import com.randing.system.service.impl.UnitServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Leen
 * @since 2023-03-17
 */
@RestController
@RequestMapping("/selfExamination")
@Api("南繁单位接口")
public class SelfExaminationController {
    @Autowired
    private ISelfExaminationService selfExaminationService;
    /*-----------------基本信息----------------------*/
    @PostMapping("step1Insert")
    @ApiOperation("基本信息-保存")
    public AjaxResult step1insert(@RequestBody SelfStep1ReqVo reqVo) {

        if (reqVo == null) {
            throw new BaseException("参数错误");
        }

        String unitNature = reqVo.getUnitNature();//联系方式
        int unitNatureInt = Integer.parseInt(unitNature);
        if (unitNatureInt != 1 && unitNatureInt != 2 && unitNatureInt != 3 && unitNatureInt != 4) {
            throw new BaseException("单位性质 必须为高校，科研院所，企业或其他");
        }
        String creditCode = reqVo.getCreditCode();
        if (!UnitServiceImpl.patternCreditCode(creditCode)) {
            throw new BaseException("统一代码格式错误");
        }
        selfExaminationService.step1save(reqVo);
        return AjaxResult.success();
    }
    /*-----------------联系人----------------------*/
    @PostMapping("step2Permanent")
    @ApiOperation("常驻联系人-保存")
    public AjaxResult step2Permanent(@Validated @RequestBody Step2SelfExaminationPermanentReqVo reqVo) {
        if (reqVo == null) {
            throw new BaseException("参数错误");
        }
        selfExaminationService.step2Permanent(reqVo);
        return AjaxResult.success();
    }

    @GetMapping("step2GetPermanentList")
    @ApiOperation("常驻联系人-列表")
    public AjaxResult<List<SelfExaminationPermanent>> step2getPermanentList() {
        return AjaxResult.success(selfExaminationService.step2GetPermanentList());
    }
    /*-----------------基地信息----------------------*/
    @PostMapping("step3Base")
    @ApiOperation("基地信息-保存")
    public AjaxResult step3Base(@RequestBody Step3SelfExaminationBaseReqVo reqVo) {
        if (reqVo == null) {
            throw new BaseException("参数错误");
        }
        selfExaminationService.step3Base(reqVo);
        return AjaxResult.success();
    }
    @GetMapping("step3GetBaseList")
    @ApiOperation("基地信息-列表")
    public AjaxResult<List<SelfExaminationBase>> step3GetBaseList() {
        return AjaxResult.success(selfExaminationService.step3GetBasetList());
    }
    /*-----------------活动信息----------------------*/
    @PostMapping("step4Activity")
    @ApiOperation("活动信息-保存")
    public AjaxResult step4Activity(@RequestBody Step4SelfExaminationActivityReqVo reqVo) {
        if (reqVo == null) {
            throw new BaseException("参数错误");
        }
        selfExaminationService.step4Activity(reqVo);
        return AjaxResult.success();
    }
    @GetMapping("step4GetActivityList")
    @ApiOperation("活动信息-列表")
    public AjaxResult<List<SelfExaminationActivity>> step4GetActivityList() {
        return AjaxResult.success(selfExaminationService.step4GetActivityList());
    }
    /*-----------------育种信息----------------------*/
    @PostMapping("step5Gain")
    @ApiOperation("活动信息-保存")
    public AjaxResult step5Activity(@RequestBody Step5SelfExaminationGainReqVo reqVo) {
        if (reqVo == null) {
            throw new BaseException("参数错误");
        }
        selfExaminationService.step5Gain(reqVo);
        return AjaxResult.success();
    }
    @GetMapping("step5GetGainList")
    @ApiOperation("活动信息-列表")
    public AjaxResult<List<SelfExaminationGain>> step5GetGainList() {
        return AjaxResult.success(selfExaminationService.step5GetGainList());
    }
    /*-----------------用工需求----------------------*/
    @PostMapping("step6Emp")
    @ApiOperation("活动信息-保存")
    public AjaxResult step6Emp(@RequestBody Step6SelfExamination reqVo) {
        if (reqVo == null) {
            throw new BaseException("参数错误");
        }
//        selfExaminationService.step6Emp(reqVo);
        return AjaxResult.success();
    }
}


