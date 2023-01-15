package com.randing.system.domain.po;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 崖州湾实验室南繁配套用地申请表
 * </p>
 *
 * @author Leen
 * @since 2022-12-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("nanfan_land_apply_form")
public class NanfanLandApplyForm implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用地申请单位
     */
    private String landApplyUnit;

    /**
     * 填报人
     */
    private String inputPerson;

    /**
     * 申请用地面积（亩）
     */
    private Double landApplyArea;

    /**
     * 申请用地类型
     */
    private String landApplyType;

    /**
     * 申请用地地址
     */
    private String landApplyAddress;

    /**
     * 依托项目来源
     */
    private String relyProjectSource;

    /**
     * 依托项目名称
     */
    private String relyProjectName;

    /**
     * 申请用地理由（重点说明用地面积的依据）
     */
    private String landApplyReson;

    /**
     * 费用预算
     */
    private Double budget;

    /**
     * 申请单位意见，单位领导意见
     */
    private String applyUnitOpinionLeaderOpinion;

    /**
     * 申请单位意见，单位公章
     */
    private String applyUnitOpinionSeal;

    /**
     * 申请单位意见，日期
     */
    private LocalDateTime applyUnitOpinonDate;

    /**
     * 投资控股公司业务审核意见
     */
    private String holdingUnitBusinessOpinion;

    /**
     * 投资控股公司业务审核意见，领导签字
     */
    private String holdingUnitBusinessLeaderSign;

    /**
     * 投资控股公司主管领导意见
     */
    private String holdingUnitLeaderOpinion;

    /**
     * 投资控股公司主管领导意见，领导签字
     */
    private String holdingUnitLeaderSign;

    /**
     * 投资控股公司主管领导意见，日期
     */
    private LocalDateTime holdingUnitLeaderDate;

    /**
     * 申请人联系方式
     */
    private String landApplyPersonCantact;

    /**
     * 0 使用 <p>
     * 1 删除 <p>
     * 2 保存不提交<p>
     * 3 提交（待审批） <p>
     * 4 已评分（已评分未选地块）<p>
     * 5 已退回<p>
     * 6异议中（发起异议）<p>
     * 7已选地（异议通过）<p>
     * 8异议中（异议退回）<p>
     * 9已选地块（已评分并选择地块）<p>
     * 10确定地块（确定后只能发起合同签订，不能发起异议）<p>
     * 11合同已签订<p>
     */
    private Integer delFlag;

    /**
     * 投资控股公司业务审核意见，日期
     */
    private String holdingUnitBusinessLeaderDate;

    private LocalDateTime createTime;

    private Integer createUser;

    private LocalDateTime updateTime;

    private Integer updateUser;

    private String backup1;

    private String backup2;

    private String backup3;

    private String backup4;

    private String backup5;

    /**
     * 退回原因
     */
    private String backMsg;

    /**
     * 申请用地用途
     */
    private String landApplyPurpose;

    /**
     * 科研用地计划pdf路径
     */
    private String landApplyResonAttach;

    /**
     * 已分配的土地信息id
     */
    private Integer inforId;

    /**
     * 申请年限
     */
    private Integer landApplyYear;

    /**
     * 土地保证金附件
     */
    private String landSecurityFund;

    /**
     * 是否同意调剂
     */
    private String isAdjust;

    /**
     * 系统评分
     */
    private Double sumScore;

    /**
     * 所属批次id
     */
    private String batchId;

    /**
     * 所属批次名称
     */
    private String batchName;

    /**
     * 审批标签
     */
    private String applyLabel;

    /**
     * 预计转化新品种面积
     */
    private Double conversion;

    /**
     * 预计培训农民人次
     */
    private Integer train;


}
