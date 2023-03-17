package com.randing.system.domain.po;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.Api;
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
public class SelfExaminationGainCheck implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 审定品种的信息id
     */
    @ApiModelProperty("审定品种的信息id")
    private String checkId;

    /**
     * 成果信息id
     */
    @ApiModelProperty("成果信息id")
    private String gainId;

    /**
     * 审定时间
     */
    @NotNull(message = "审定时间不能为空")
    @ApiModelProperty("审定时间")
    private LocalDateTime checkTime;

    /**
     * 审定机构
     */
    @NotBlank(message = "审定机构不能为空")
    @ApiModelProperty("审定机构")
    private String checkMechanism;

    /**
     * 品种名称
     */
    @ApiModelProperty("品种名称")
    @NotBlank(message = "品种名称不能为空")
    private String checkVarieties;

    /**
     * 作物类型
     */
    @ApiModelProperty("作物类型")
    @NotBlank(message = "作物类型不能为空")
    private String checkCropType;

    /**
     * 上一年度销售额（万元）
     */
    @ApiModelProperty("上一年度销售额（万元）")
    @NotNull(message = "上一年度销售额审定机构不能为空")
    private BigDecimal checkOldMoney;

    /**
     * 累计全国推广面积（亩）
     */
    @ApiModelProperty("累计全国推广面积（亩）")
    @NotNull(message = "累计全国推广面积（亩）审定时间不能为空")
    private BigDecimal checkWholeCountry;

    /**
     * 累计海南推广面积（亩）
     */
    @ApiModelProperty("累计海南推广面积（亩）")
    @NotNull(message = "累计海南推广面积（亩）不能为空")
    private BigDecimal checkHainan;

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


}
