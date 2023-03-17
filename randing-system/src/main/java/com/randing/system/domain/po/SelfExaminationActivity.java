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
 * （南繁单位活动信息表
 * </p>
 *
 * @author Leen
 * @since 2023-03-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SelfExaminationActivity implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId("id")
    private Long id;
    /**
     * 活动信息id
     */
    @ApiModelProperty("")
    private String activityId;

    /**
     * 单位自查信息id
     */
    @ApiModelProperty("")
    private String selfExaminationId;

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

    /**
     * 创建时间
     */
    @ApiModelProperty("")
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
