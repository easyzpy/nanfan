package com.randing.system.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Step4SelfExaminationActivityReqVo implements Serializable {
    private static final long serialVersionUID = -9151756505564447960L;
    /**
     * 作物名称—育种信息
     */
    @ApiModelProperty("作物名称—育种信息")
    @NotBlank(message = "作物名称—育种信息不能为空")
    private String activityBreedingName;

    /**
     * 品种/组合名称—育种信息
     */
    @ApiModelProperty("组合名称—育种信息")
    @NotBlank(message = "组合名称—育种信息不能为空")
    private String activityBreedingVarieties;

    /**
     * 育种组合数—育种信息
     */
    @ApiModelProperty("育种组合数")
    @NotBlank(message = "育种组合数—育种信息不能为空")
    private Integer activityBreedingSum;

    /**
     * 育种项目—育种信息
     */
    @ApiModelProperty("育种项目—育种信息")
    @NotBlank(message = "育种项目—育种信息不能为空")
    private String activityBreedingProject;

    /**
     * 种植面积—育种信息
     */
    @ApiModelProperty("种植面积—育种信息")
    @NotBlank(message = "种植面积—育种信息不能为空")
    private BigDecimal activityBreedingArea;

    /**
     * 活动的开始时间—育种信息
     */
    @ApiModelProperty("")
    @NotBlank(message = "活动的开始时间—育种信息不能为空")
    private LocalDateTime activityBreedingStart;

    /**
     * 活动的结束时间—育种信息
     */
    @ApiModelProperty("活动的结束时间—育种信息")
    @NotBlank(message = "活动的结束时间—育种信息不能为空")
    private LocalDateTime activityBreedingEnd;

    /**
     * 是否为转基因科研（0：否 1：是）育种信息
     */
    @ApiModelProperty("是否为转基因科研（0：否 1：是）育种信息")
    @NotBlank(message = "是否为转基因科研—育种信息不能为空")
    private String activityBreedingGene;

    /**
     * 作物名称—制种信息
     */
    @ApiModelProperty("作物名称—制种信息")
    @NotBlank(message = "作物名称—制种信息不能为空")
    private String activityMakeName;

    /**
     * 品种名称—制种信息
     */
    @ApiModelProperty("品种名称—制种信息")
    @NotBlank(message = "品种名称—制种信息不能为空")
    private String activityMakeVarieties;

    /**
     * 制种面积（亩）—制种信息
     */
    @ApiModelProperty("制种面积（亩）—制种信息")
    @NotBlank(message = "制种面积（亩）—制种信息不能为空")
    private BigDecimal activityMakeArea;

    /**
     * 产量（公斤）—制种信息
     */
    @ApiModelProperty("产量（公斤）—制种信息")
    @NotBlank(message = "产量（公斤）—制种信息不能为空")
    private BigDecimal activityMakeAmount;

    /**
     * 总人数
     */
    @ApiModelProperty("总人数")
    @NotBlank(message = "总人数不能为空")
    private Integer activityPeople;

    /**
     * 管理人员总数
     */
    @ApiModelProperty("管理人员总数")
    @NotBlank(message = "管理人员总数不能为空")
    private Integer activityAdmin;

    /**
     * 科研人数总和
     */
    @ApiModelProperty("科研人数总和")
    @NotBlank(message = "科研人数总和不能为空")
    private Integer activitySkill;

    /**
     * 工人数
     */
    @ApiModelProperty("工人数")
    @NotBlank(message = "工人数不能为空")
    private Integer activityWork;

    /**
     * 其他人数
     */
    @NotBlank(message = "其他人数不能为空")
    @ApiModelProperty("其他人数")
    private Integer activityOther;

}

