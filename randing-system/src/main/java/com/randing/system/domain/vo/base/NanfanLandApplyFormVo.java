package com.randing.system.domain.vo.base;

import com.baomidou.mybatisplus.annotation.TableField;
import com.randing.system.domain.common.OrderByEnum;
import com.randing.system.domain.po.LandContract;
import com.randing.system.domain.po.LandInfor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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
public class NanfanLandApplyFormVo extends BasePage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用地申请单位
     */
    @ApiModelProperty("用地申请单位")
    private String landApplyUnit;

    /**
     * 填报人
     */
    @ApiModelProperty("填报人")
    private String inputPerson;

    /**
     * 申请用地面积（亩）
     */
    @ApiModelProperty("申请用地面积（亩）")
    private Double landApplyArea;

    /**
     * 申请用地类型
     */
    @ApiModelProperty("申请用地类型;new:作物类型")
    private String landApplyType;

    /**
     * 申请用地地址
     */
    @ApiModelProperty("申请用地地址")
    private String landApplyAddress;

    /**
     * 依托项目来源
     */
    @ApiModelProperty("依托项目来源")
    private String relyProjectSource;

    /**
     * 依托项目名称
     */
    @ApiModelProperty("依托项目名称; new:用地用途")
    private String relyProjectName;

    /**
     * 申请用地理由（重点说明用地面积的依据）
     */
    @ApiModelProperty("申请用地理由（重点说明用地面积的依据）")
    private String landApplyReson;

    /**
     * 费用预算
     */
    @ApiModelProperty("费用预算")
    private Double budget;

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
    private LocalDateTime applyUnitOpinonDate;

    /**
     * 投资控股公司业务审核意见
     */
    @ApiModelProperty("投资控股公司业务审核意见")
    private String holdingUnitBusinessOpinion;

    /**
     * 投资控股公司业务审核意见，领导签字
     */
    @ApiModelProperty("投资控股公司业务审核意见，领导签字")
    private String holdingUnitBusinessLeaderSign;

    /**
     * 投资控股公司主管领导意见
     */
    @ApiModelProperty("投资控股公司主管领导意见")
    private String holdingUnitLeaderOpinion;

    /**
     * 投资控股公司主管领导意见，领导签字
     */
    @ApiModelProperty("投资控股公司主管领导意见，领导签字")
    private String holdingUnitLeaderSign;

    /**
     * 投资控股公司主管领导意见，日期
     */
    @ApiModelProperty("投资控股公司主管领导意见，日期")
    private LocalDateTime holdingUnitLeaderDate;

    /**
     * 申请人联系方式
     */
    @ApiModelProperty("申请人联系方式")
    private String landApplyPersonCantact;

    /**
     * 是否删除 0 使用 1 删除
     */
    @ApiModelProperty("0 使用 1 删除 2 保存不提交 3 提交（待审批） 4 已评分（已评分未选地块）5 已退回  6异议中（发起异议） 7已选地（异议通过） 8异议中（异议退回） 9已选地块（已评分并选择地块）10确定地块（确定后只能发起合同签订，不能发起异议） 11合同已签订")
    private Integer delFlag;

    /**
     * 投资控股公司业务审核意见，日期
     */
    @ApiModelProperty("投资控股公司业务审核意见，日期")
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
    @ApiModelProperty("退回原因")
    private String backMsg;

    /**
     * 申请用地用途
     */
    @ApiModelProperty("申请用地用途")
    private String landApplyPurpose;

    /**
     * 科研用地计划pdf路径
     */
    @ApiModelProperty("科研用地计划pdf路径")
    private String landApplyResonAttach;

    /**
     * 已分配的土地信息id
     */
    @ApiModelProperty("已分配的土地信息id")
    private Integer inforId;

    /**
     * 申请年限
     */
    @ApiModelProperty("申请年限")
    private Integer landApplyYear;

    /**
     * 土地保证金附件
     */
    @ApiModelProperty("土地保证金附件")
    private String landSecurityFund;

    /**
     * 是否同意调剂
     */
    @ApiModelProperty("是否同意调剂")
    private String isAdjust;

    /**
     * 系统评分
     */
    @ApiModelProperty("系统评分")
    private Double sumScore;

    /**
     * 所属批次id
     */
    @ApiModelProperty("所属批次id")
    private String batchId;

    /**
     * 所属批次名称
     */
    @ApiModelProperty("所属批次名称")
    private String batchName;

    /**
     * 审批标签
     */
    @ApiModelProperty("审批标签")
    private String applyLabel;

    /**
     * 预计转化新品种面积
     */
    @ApiModelProperty("预计转化新品种面积")
    private Double conversion;

    /**
     * 预计培训农民人次
     */
    @ApiModelProperty("预计培训农民人次")
    private Integer train;

    @ApiModelProperty("根据本年科研计划需求，租地时间是否能足够（0：否，1：是）")
    private Integer timeEnough;
    @ApiModelProperty("满足本年科研计划需要延长租地(天)")
    private Integer timeEnoughTime;
    @ApiModelProperty("是否继续续租")
    private Integer renewal;
    @ApiModelProperty("是否续租下一个南繁季")
    private Integer renewalQuarter;
    @ApiModelProperty("地块租赁方式(短租、长租)")
    private String leaseMethod;
    @ApiModelProperty("长租时间（由用户输入）")
    private String longLeaseTime;
    @ApiModelProperty("提交时间")
    private LocalDateTime pushTime;


    @ApiModelProperty("费用预算-排序")
    private OrderByEnum budgetOrder;
    /**
     * 面积
     */
    @ApiModelProperty("申请用地面积-排序")
    private OrderByEnum landApplyAreaOrder;
    /**
     * 面积
     */
    @ApiModelProperty("申请日期-排序")
    private OrderByEnum createTimeOrder;
    /**
     * 申请单位/作物类型 / 用地用途
     */
    @ApiModelProperty("申请单位/作物类型 / 用地用途")
    private String text;

    private LandContract landContract;

    @ApiModelProperty("土地信息")
    @TableField(exist = false)
    private List<LandInfor> applyInfors;

/*----------评分start---------*/
    @ApiModelProperty("中标地块")
    private String landInforName;
    @ApiModelProperty("中标地块id")
    private String landInforId;


    private String landApplyUnitScoreAttach;

    /**
     * 用地申报单位评分标注
     */
    @ApiModelProperty("用地申报单位评分标注")
    private String landApplyUnitScore;

    /**
     * 用地申报单位评分标注附件
     */
    @ApiModelProperty("用地申报单位评分标注附件")
    private String researchDirectionsScoreAttach;

    /**
     * 科研方向评分标准
     */
    @ApiModelProperty("科研方向评分标准")
    private String researchDirectionsScore;

    /**
     * 科研方向评分标准附件
     */
    @ApiModelProperty("科研方向评分标准附件")
    private String startResearchActivityScoreAttach;

    /**
     * 开展科研活动评分标准
     */
    @ApiModelProperty("开展科研活动评分标准")
    private String startResearchActivityScore;

    /**
     * 开展科研活动评分标准附件
     */
    @ApiModelProperty("开展科研活动评分标准附件")
    private String landQuitLandRestoreScoreAttach;

    /**
     * 用地退出土地评分标准
     */
    @ApiModelProperty("用地退出土地评分标准")
    private String landQuitLandRestoreScore;

    /**
     * 用地退出土地评分标准附件
     */
    @ApiModelProperty("用地退出土地评分标准附件")
    private String driveLandGrowScoreAttach;

    /**
     * 带动当地发展评分标准
     */
    @ApiModelProperty("带动当地发展评分标准")
    private String driveLandGrowScore;

    /**
     * 带动当地发展评分标准附件
     */
    @ApiModelProperty("带动当地发展评分标准附件")
    private String onLandTransformScoreAttach;

    /**
     * 就地转化评分标准
     */
    @ApiModelProperty("就地转化评分标准")
    private String onLandTransformScore;

    /**
     * 就地转化评分标准附件
     */
    @ApiModelProperty("就地转化评分标准附件")
    private String landFulfillPromiseScoreAttach;

    /**
     * 用地承诺兑现程度评分标准
     */
    @ApiModelProperty("用地承诺兑现程度评分标准")
    private String landFulfillPromiseScore;


    /**
     * 申请表id
     */
    @ApiModelProperty("申请表id")
    private Integer applyFormId;

    /**
     * 申请表类型
     */
    @ApiModelProperty("申请表类型")
    private String applyFormType;

    private String recordEachScoreByOrder;

    /**
     * 调整分数的理由
     */
    @ApiModelProperty("调整分数的理由")
    private String scoreReason;

    /**
     * 调整的分数
     */
    @ApiModelProperty("调整的分数")
    private Double adjustmentScore;

    /**
     * 申报单位科研机构选项（科研机构、高校、涉农企业）
     */
    @ApiModelProperty("申报单位科研机构选项（科研机构、高校、涉农企业）")
    private String researchSbdw;

    /**
     * 新品种就地转化评分
     */
    @ApiModelProperty("新品种就地转化评分")
    private String newVarietiesScore;

    /**
     * 新技术就地转化评分
     */
    @ApiModelProperty("新技术就地转化评分")
    private String newTechniqueScore;
    /*----------评分end---------*/
}
