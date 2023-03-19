package com.randing.system.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Step5SelfExaminationGainCheckReqVo implements Serializable {
    private static final long serialVersionUID = -9151756505564447960L;


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

}

