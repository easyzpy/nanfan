package com.randing.system.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.randing.system.domain.vo.base.BasePage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 崖州湾实验室用地田间，育种服务申请表
 * </p>
 *
 * @author Leen
 * @since 2022-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FieldBreedServiceApplyFormVo extends BasePage implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 用地申请单位
     */
    @ApiModelProperty("用地申请单位")
    private String landApplyUnit;

    /**
     * 填报人
     */
    @ApiModelProperty("填报人")
    private String intpuPerson;

    /**
     * 申请用地地址
     */
    @ApiModelProperty("申请用地地址")
    private String landApplyAddress;

    /**
     * 项目名称
     */
    @ApiModelProperty("项目名称")
    private String projectName;

    /**
     * 需申请的服务模式类型
     */
    @ApiModelProperty("需申请的服务模式类型")
    private String needApplyServiceModeType;

    /**
     * 有田间服务需求填报
     */
    @ApiModelProperty("有田间服务需求填报")
    private String fieldServiceNeedReport;


    /**
     * 育种实验室资源配套服务
     */
    @ApiModelProperty("育种实验室资源配套服务")
    private String breedService;

    /**
     * 申请单位意见，单位领导意见
     */
    @ApiModelProperty("申请单位意见，单位领导意见")
    private String applyUnitOpinionLeaderOpinion;

    /**
     * 申请单位意见，单位公章
     */
    @ApiModelProperty("申请单位意见，单位公章")
    private String applyUnitOpinionSeal;

    /**
     * 申请单位意见，日期
     */
    @ApiModelProperty("申请单位意见，日期")
    private LocalDateTime applyUnitOpinionDate;

    /**
     * 投资控股公司业务审核意见
     */
    @ApiModelProperty("投资控股公司业务审核意见")
    private String holdingUnitBussinessVerifyOpinion;

    /**
     * 投资控股公司业务审核意见，领导签字
     */
    @ApiModelProperty("投资控股公司业务审核意见，领导签字")
    private String holdingUnitBussinessVerifyOpinionLeaderSign;

    /**
     * 申请人联系方式
     */
    @ApiModelProperty("申请人联系方式")
    private String applyPersonCaontact;

    /**
     * 是否删除 0 使用 1 删除
     */
    @ApiModelProperty("是否删除 0 使用 1 删除")
    private Integer delFlag;

    private Integer createUser;

    private LocalDateTime createTime;

    private Integer updateUser;

    private LocalDateTime updateTime;

    private String backup1;

    private String backup2;

    private String backup3;

    private String backup4;

    private String backup5;


}
