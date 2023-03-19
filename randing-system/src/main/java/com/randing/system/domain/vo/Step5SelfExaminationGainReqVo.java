package com.randing.system.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Step5SelfExaminationGainReqVo implements Serializable {
    private static final long serialVersionUID = -9151756505564447960L;


    /**
     * 专利数
     */
    @ApiModelProperty("专利数")
    @NotNull(message = "专利数不能为空")
    private Integer gainPatentSum;

    /**
     * 所获奖项
     */
    @ApiModelProperty("所获奖项")
    @NotBlank(message = "所获奖项不能为空")
    private String gainPrize;

    /**
     * 上年度总投入（万元）
     */
    @ApiModelProperty("上年度总投入（万元）")
    @NotNull(message = "上年度总投入（万元）不能为空")
    private BigDecimal gainOldInvestment;

    /**
     * 科学生产投入（万元）
     */
    @ApiModelProperty("科学生产投入（万元）")
    @NotNull(message = "科学生产投入（万元）不能为空")
    private BigDecimal gainScienceInvestment;

    /**
     * 基础设施投入（万元）
     */
    @ApiModelProperty("基础设施投入（万元）")
    @NotNull(message = "基础设施投入（万元）不能为空")
    private BigDecimal gainBasicInvestment;

    /**
     * 财政投入（万元）
     */
    @ApiModelProperty("财政投入（万元）")
    @NotNull(message = "财政投入（万元）不能为空")
    private BigDecimal gainMoneyInvestment;

    /**
     * 年份
     */
    @ApiModelProperty("年份")
    @NotNull(message = "年份不能为空")
    private Integer gainYear;

    /**
     * 劳务用工投入
     */
    @ApiModelProperty("劳务用工投入")
    @NotNull(message = "劳务用工投入不能为空")
    private BigDecimal gainLwygInvestment;
/*审定品种信息*/
    @ApiModelProperty("审定品种信息")
    private List<Step5SelfExaminationGainCheckReqVo> gainCheck;

//    /**
//     * 审定时间
//     */
//    @NotNull(message = "审定时间不能为空")
//    @ApiModelProperty("审定时间-审定品种信息")
//    private LocalDateTime checkTime;
//
//    /**
//     * 审定机构
//     */
//    @NotBlank(message = "审定机构不能为空")
//    @ApiModelProperty("审定机构-审定品种信息")
//    private String checkMechanism;
//
//    /**
//     * 品种名称
//     */
//    @ApiModelProperty("品种名称-审定品种信息")
//    @NotBlank(message = "品种名称不能为空")
//    private String checkVarieties;
//
//    /**
//     * 作物类型
//     */
//    @ApiModelProperty("作物类型-审定品种信息")
//    @NotBlank(message = "作物类型不能为空")
//    private String checkCropType;
//
//    /**
//     * 上一年度销售额（万元）
//     */
//    @ApiModelProperty("上一年度销售额（万元）-审定品种信息")
//    @NotNull(message = "上一年度销售额审定机构不能为空")
//    private BigDecimal checkOldMoney;
//
//    /**
//     * 累计全国推广面积（亩）
//     */
//    @ApiModelProperty("累计全国推广面积（亩）-审定品种信息")
//    @NotNull(message = "累计全国推广面积（亩）审定时间不能为空")
//    private BigDecimal checkWholeCountry;
//
//    /**
//     * 累计海南推广面积（亩）
//     */
//    @ApiModelProperty("累计海南推广面积（亩）-审定品种信息")
//    @NotNull(message = "累计海南推广面积（亩）不能为空")
//    private BigDecimal checkHainan;

}

