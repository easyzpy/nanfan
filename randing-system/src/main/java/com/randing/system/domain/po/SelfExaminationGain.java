package com.randing.system.domain.po;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 *
 * </p>
 *
 * @author Leen
 * @since 2023-03-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SelfExaminationGain implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;
    /**
     * 成果信息id
     */
    @ApiModelProperty("成果信息id")
    private String gainId;

    /**
     * 单位自查信息id
     */
    @ApiModelProperty("单位自查信息id")
    private String selfExaminationId;

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
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @ApiModelProperty("")
    private String createBy;

    /**
     * 修改时间
     */
    @ApiModelProperty("")
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /**
     * 修改人
     */
    @ApiModelProperty("")
    private String updateBy;

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

    @TableField(exist = false)
    private SelfExaminationGainCheck selfExaminationGainCheck;


}
