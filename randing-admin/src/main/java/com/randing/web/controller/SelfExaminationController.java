package com.randing.web.controller;


import com.randing.common.core.domain.AjaxResult;
import com.randing.common.exception.BaseException;
import com.randing.common.utils.StringUtils;
import com.randing.system.domain.po.ReturnPeople;
import com.randing.system.domain.po.SelfExamination;
import com.randing.system.domain.po.SelfExaminationActivity;
import com.randing.system.domain.po.SelfExaminationBase;
import com.randing.system.domain.po.SelfExaminationExtension;
import com.randing.system.domain.po.SelfExaminationGain;
import com.randing.system.domain.po.SelfExaminationNewCategory;
import com.randing.system.domain.po.SelfExaminationPermanent;
import com.randing.system.domain.vo.SelfStep1ReqVo;
import com.randing.system.domain.vo.Step10ReturnPeople;
import com.randing.system.domain.vo.Step2SelfExaminationPermanentReqVo;
import com.randing.system.domain.vo.Step3SelfExaminationBaseReqVo;
import com.randing.system.domain.vo.Step4SelfExaminationActivityReqVo;
import com.randing.system.domain.vo.Step5SelfExaminationGainReqVo;
import com.randing.system.domain.vo.Step6SelfExamination;
import com.randing.system.domain.vo.Step7SelfExamination;
import com.randing.system.domain.vo.Step8SelfExamination;
import com.randing.system.domain.vo.Step8SelfExaminationExtension;
import com.randing.system.domain.vo.Step8SelfExaminationNewCategory;
import com.randing.system.domain.vo.Step9SelfExamination;
import com.randing.system.service.ISelfExaminationService;
import com.randing.system.service.impl.UnitServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
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
    @GetMapping("findMyExamination")
    @ApiOperation("南繁单位详情查询")
    public AjaxResult<SelfExamination> findMyExamination() {
        return AjaxResult.success(selfExaminationService.findMyExamination());
    }

    /*-----------------基本信息----------------------*/
    @PostMapping("step1Insert")
    @ApiOperation("step1基本信息-保存")
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
    @ApiOperation("step2常驻联系人-保存")
    public AjaxResult step2Permanent(@Validated @RequestBody Step2SelfExaminationPermanentReqVo reqVo) {
        if (reqVo == null) {
            throw new BaseException("参数错误");
        }
        selfExaminationService.step2Permanent(reqVo);
        return AjaxResult.success();
    }

    @GetMapping("step2GetPermanentList")
    @ApiOperation("step2常驻联系人-列表")
    public AjaxResult<List<SelfExaminationPermanent>> step2getPermanentList() {
        return AjaxResult.success(selfExaminationService.step2GetPermanentList());
    }

    @DeleteMapping("step2DeletePermanent")
    @ApiOperation("step2常驻联系人-删除")
    public AjaxResult step2DeletePermanent(@RequestParam("id")Long id) {
        selfExaminationService.step2DeletePermanent(id);
        return AjaxResult.success();
    }
    /*-----------------基地信息----------------------*/
    @PostMapping("step3Base")
    @ApiOperation("step3基地信息-保存")
    public AjaxResult step3Base(@RequestBody Step3SelfExaminationBaseReqVo reqVo) {
        if (reqVo == null) {
            throw new BaseException("参数错误");
        }
        selfExaminationService.step3Base(reqVo);
        return AjaxResult.success();
    }
    @GetMapping("step3GetBaseList")
    @ApiOperation("step3基地信息-列表")
    public AjaxResult<List<SelfExaminationBase>> step3GetBaseList() {
        return AjaxResult.success(selfExaminationService.step3GetBasetList());
    }
    /*-----------------活动信息----------------------*/
    @PostMapping("step4Activity")
    @ApiOperation("step4活动信息-保存")
    public AjaxResult step4Activity(@RequestBody Step4SelfExaminationActivityReqVo reqVo) {
        if (reqVo == null) {
            throw new BaseException("参数错误");
        }
        selfExaminationService.step4Activity(reqVo);
        return AjaxResult.success();
    }
    @GetMapping("step4GetActivityList")
    @ApiOperation("step4活动信息-列表")
    public AjaxResult<List<SelfExaminationActivity>> step4GetActivityList() {
        return AjaxResult.success(selfExaminationService.step4GetActivityList());
    }
    /*-----------------育种信息----------------------*/
    @PostMapping("step5Gain")
    @ApiOperation("step5育种信息-保存")
    public AjaxResult step5Activity(@RequestBody Step5SelfExaminationGainReqVo reqVo) {
        if (reqVo == null) {
            throw new BaseException("参数错误");
        }
        selfExaminationService.step5Gain(reqVo);
        return AjaxResult.success();
    }
    @GetMapping("step5GetGainList")
    @ApiOperation("step5育种信息-列表")
    public AjaxResult<List<SelfExaminationGain>> step5GetGainList() {
        return AjaxResult.success(selfExaminationService.step5GetGainList());
    }
    /*-----------------服务需求----------------------*/
    @PostMapping("step6Emp")
    @ApiOperation("step6服务需求-保存")
    public AjaxResult step6Emp(@RequestBody Step6SelfExamination reqVo) {
        if (reqVo == null) {
            throw new BaseException("参数错误");
        }
        //serviceDemand
        //entrustedBreeding
        //laboratoryRequirements
        //otherRequirements

        if (reqVo.getServiceDemand() != null && reqVo.getServiceDemand().equals("1")) {
            if (StringUtils.isBlank(reqVo.getServiceDemandDescribe())) {
                throw new BaseException("具体农事服务需求不能为空");
            }
        }
        if (reqVo.getEntrustedBreeding() != null && reqVo.getEntrustedBreeding().equals("1")) {
            if (StringUtils.isBlank(reqVo.getEntrustedBreedingDescribe())) {
                throw new BaseException("具体委托育种、制种需求不能为空");
            }
        }

        if (reqVo.getLaboratoryRequirements() != null && reqVo.getLaboratoryRequirements().equals("1")) {
            if (StringUtils.isBlank(reqVo.getLaboratoryRequirementsDescribe())) {
                throw new BaseException("具体实验室需求不能为空");
            }
        }

        if (reqVo.getOtherRequirements() != null && reqVo.getOtherRequirements().equals("1")) {
            if (StringUtils.isBlank(reqVo.getOtherRequirementsDescribe())) {
                throw new BaseException("具体其他需求不能为空");
            }
        }
        selfExaminationService.step6Emp(reqVo);
        return AjaxResult.success();
    }

    /*-----------------育种安全----------------------*/
    @PostMapping("step7Secure")
    @ApiOperation("step7育种安全-保存")
    public AjaxResult step7Secure(@RequestBody Step7SelfExamination reqVo) {
        if (reqVo == null) {
            throw new BaseException("参数错误");
        }

        if (reqVo.getTransgeneSpotcheck() != null && reqVo.getTransgeneSpotcheck().equals("1")) {
            if (reqVo.getTransgeneTime()==null) {
                throw new BaseException("转基因抽查时间不能为空");
            }
            if (StringUtils.isBlank(reqVo.getTransgeneCrop())) {
                throw new BaseException("抽查作物不能为空");
            }
            if (StringUtils.isBlank(reqVo.getTransgeneAddress())) {
                throw new BaseException("抽查的基地位置不能为空");
            }
            if (reqVo.getTransgeneThrough()==null) {
                throw new BaseException("基地位置的经度不能为空");
            }
            if (reqVo.getTransgeneLatitude()==null) {
                throw new BaseException("基地位置的纬度不能为空");
            }
            if (StringUtils.isBlank(reqVo.getTransgeneCompany())) {
                throw new BaseException("抽查的单位不能为空");
            }
        }
        selfExaminationService.step7Secure(reqVo);
        return AjaxResult.success();
    }
    /*-----------------科研成果----------------------*/
    @GetMapping("step8CategoryList")
    @ApiOperation("step8南繁育成新品类及个数-列表")
    public AjaxResult<List<SelfExaminationNewCategory>> step8CategoryList() {
        List<SelfExaminationNewCategory> list = selfExaminationService.step8CategoryList();
        return AjaxResult.success(list);
    }

    @GetMapping("step8ExtensionList")
    @ApiOperation("step8成果转化个数-列表")
    public AjaxResult<List<SelfExaminationExtension>> step8ExtensionList() {
        List<SelfExaminationExtension> list = selfExaminationService.step8ExtensionList();
        return AjaxResult.success(list);
    }

    @DeleteMapping("step8DeleteCategory")
    @ApiOperation("step8南繁育成新品类及个数-删除")
    public AjaxResult step8DeleteCategory(@RequestParam("id")Long id) {
        selfExaminationService.step8DeleteCategory(id);
        return AjaxResult.success();
    }
    @DeleteMapping("step8DeleteExtension")
    @ApiOperation("step8成果转化个数-删除")
    public AjaxResult step8DeleteExtension(@RequestParam("id")Long id) {
        selfExaminationService.step8DeleteExtension(id);
        return AjaxResult.success();
    }

    @PostMapping("step8Category")
    @ApiOperation("step8南繁育成新品类及个数-保存")
    public AjaxResult step8Category(@RequestBody Step8SelfExaminationNewCategory reqVo) {
        if (reqVo == null) {
            throw new BaseException("参数错误");
        }
        selfExaminationService.step8Category(reqVo);
        return AjaxResult.success();
    }
    @PostMapping("step8Extension")
    @ApiOperation("step8成果转化个数-保存")
    public AjaxResult step8Extension(@RequestBody Step8SelfExaminationExtension reqVo) {
        if (reqVo == null) {
            throw new BaseException("参数错误");
        }
        selfExaminationService.step8Extension(reqVo);
        return AjaxResult.success();
    }

    @PostMapping("step8Science")
    @ApiOperation("step8科研成果-保存")
    public AjaxResult step8Science(@RequestBody Step8SelfExamination reqVo) {
        if (reqVo == null) {
            throw new BaseException("参数错误");
        }
        selfExaminationService.step8Science(reqVo);
        return AjaxResult.success();
    }
/*--------------------工作建议------------------*/
    @PostMapping("step9Remark")
    @ApiOperation("step9工作建议-保存")
    public AjaxResult step9Remark(@RequestBody Step9SelfExamination reqVo) {
        if (reqVo == null) {
            throw new BaseException("参数错误");
        }
        selfExaminationService.step9Remark(reqVo);
        return AjaxResult.success();
    }

//    基地往返人员信息处理模块：单位名称，基地位置，人员姓名，学历，联系电话，到达时间，离岛时间、主要科研工作内容
    @PostMapping("step10ReturnPeople")
    @ApiOperation("step10returnPeople-保存")
    public AjaxResult step10Remark(@RequestBody Step10ReturnPeople reqVo) {
        if (reqVo == null) {
            throw new BaseException("参数错误");
        }
        selfExaminationService.step10ReturnPeople(reqVo);
        return AjaxResult.success();
    }

    @GetMapping("step10ReturnPeopleList")
    @ApiOperation("step10returnPeople-列表")
    public AjaxResult<List<ReturnPeople>> step10RemarkList() {
        List<ReturnPeople> list = selfExaminationService.step10RemarkList();
        return AjaxResult.success(list);
    }
    @DeleteMapping("step10ReturnPeople")
    @ApiOperation("step10returnPeople-删除")
    public AjaxResult step10DeleteReturnPeople(@RequestParam("id")Long id) {
        selfExaminationService.step10DeleteReturnPeople(id);
        return AjaxResult.success();
    }
}


