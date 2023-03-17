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
public class Step3SelfExaminationBaseReqVo implements Serializable {
    private static final long serialVersionUID = -9151756505564447960L;
    /**
     * 位置
     */
    @ApiModelProperty("位置")
    @NotBlank(message = "位置不能为空")
    private String baseAddress;

    /**
     * 面积（亩）
     */
    @NotNull(message = "面积不能为空")
    @ApiModelProperty("面积（亩）")
    private BigDecimal baseArea;

    /**
     * 土地取得方式
     */
    @ApiModelProperty("土地取得方式")
    @NotBlank(message = "土地取得方式不能为空")
    private String baseObtain;

    /**
     * 合同开始日期
     */
    @ApiModelProperty("合同开始日期")
    @NotNull(message = "合同开始时间不能为空")
    private LocalDateTime baseStart;

    /**
     * 合同结束日期
     */
    @NotNull(message = "合同结束时间不能为空")
    @ApiModelProperty("合同结束日期")
    private LocalDateTime baseEnd;

    /**
     * 占用面积（亩）基地内建设的配套设施
     */
    @NotNull(message = "占地面积（亩）不能为空")
    @ApiModelProperty("占用面积（亩）基地内建设的配套设施")

    private BigDecimal baseMatchingOccupy;

    /**
     * 建筑面积（m²）基地内建设的配套设施
     */
    @NotNull(message = "建筑面积（m²）不能为空")
    @ApiModelProperty("建筑面积（m²）基地内建设的配套设施")
    private BigDecimal baseMatchingErect;

    /**
     * 建筑类型—基地内建设的配套设施
     */
    @ApiModelProperty("建筑类型—基地内建设的配套设施")
    @NotBlank(message = "建筑类型不能为空")
    private String baseMatchingErectType;

    /**
     * 楼层—基地内建设的配套设施
     */
    @NotNull(message = "层数不能为空")
    @ApiModelProperty("楼层—基地内建设的配套设施")
    private Integer baseMatchingFloor;

    /**
     * 报建时间—基地内建设的配套设施
     */
    @ApiModelProperty("报建时间—基地内建设的配套设施")
    @NotNull(message = "报建时间不能为空")
    private LocalDateTime baseMatchingCreate;

    /**
     * 报建手续—基地内建设的配套设施
     */
    @NotNull(message = "报建手续不能为空")
    @ApiModelProperty("报建手续—基地内建设的配套设施")
    private String baseMatchingProcedures;
    /*---------------综合办公楼----------------*/
    /**
     * 综合办公楼-面积
     */
    @NotNull(message = "综合办公楼-面积不能为空")
    @ApiModelProperty("综合办公楼-面积")
    private BigDecimal baseZhbglArea;
    /**
     * 综合办公楼-完工时间
     */
    @NotNull(message = "综合办公楼-完工时间不能为空")
    @ApiModelProperty("综合办公楼-完工时间")
    private LocalDateTime baseZhbglTime;
    /**
     * 综合办公楼-报建手续
     */
    @NotBlank(message = "综合办公楼-报建手续不能为空")
    @ApiModelProperty("综合办公楼-报建手续")
    private String baseZhbglProcedures;

    /*---------------仓库----------------*/

    /**
     * 仓库-面积
     */
    @ApiModelProperty("仓库-面积")
    @NotNull(message = "仓库-面积不能为空")
    private BigDecimal baseCkArea;
    /**
     * 仓库-完工时间
     */
    @NotNull(message = "仓库-完工时间不能为空")
    @ApiModelProperty("仓库-完工时间")
    private LocalDateTime baseCkTime;
    /**
     * 仓库-报建手续
     */
    @NotBlank(message = "仓库-报建手续不能为空")
    @ApiModelProperty("仓库-报建手续")
    private String baseCkProcedures;
    /*---------------宿舍楼----------------*/
    /**
     * 宿舍楼-面积
     */
    @ApiModelProperty("宿舍楼-面积")
    @NotNull(message = "宿舍楼-面积不能为空")
    private BigDecimal baseSslArea;
    /**
     * 宿舍楼-完工时间
     */
    @ApiModelProperty("宿舍楼-完工时间")
    @NotNull(message = "宿舍楼-完工时间不能为空")
    private LocalDateTime baseSslTime;
    /**
     * 宿舍楼-报建手续
     */
    @ApiModelProperty("宿舍楼-报建手续")
    @NotBlank(message = "宿舍楼-报建手续不能为空")
    private String baseSslProcedures;
    /*---------------食堂----------------*/
    /**
     * 食堂-面积
     */
    @ApiModelProperty("食堂-面积")
    @NotNull(message = "食堂-面积不能为空")
    private BigDecimal baseStArea;
    /**
     * 食堂-完工时间
     */
    @ApiModelProperty("食堂-完工时间")
    @NotNull(message = "食堂-完工时间不能为空")
    private LocalDateTime baseStTime;
    /**
     * 食堂-报建手续
     */
    @ApiModelProperty("食堂-报建手续")
    @NotBlank(message = "食堂-报建手续不能为空")
    private String baseStProcedures;
    /*---------------晒场----------------*/
    /**
     * 晒场的占地面积（m²）基地内建设的附属设施
     */
    @ApiModelProperty("晒场的占地面积（m²）基地内建设的附属设施")
    @NotNull(message = "晒场-面积不能为空")
    private BigDecimal baseSiteArea;

    /**
     * 晒场停车场的完工时间—基地内建设的附属设施
     */
    @ApiModelProperty("晒场停车场的完工时间—基地内建设的附属设施")
    @NotNull(message = "晒场-完工时间不能为空")
    private LocalDateTime baseSiteTime;

    /**
     * 晒场停车场的保建手续—基地内建设的附属设施
     */
    @ApiModelProperty("晒场停车场的保建手续—基地内建设的附属设施")
    @NotBlank(message = "晒场-报建手续不能为空")
    private String baseSiteProcedures;
    /*围墙*/

    /**
     * 基地围墙的占地面积（m²）基地内建设的附属设施
     */
    @ApiModelProperty("基地围墙的占地面积（m²）基地内建设的附属设施")
    private BigDecimal baseWallArea;

    /**
     * 基地围墙的完工时间—基地内建设的附属设施
     */
    @ApiModelProperty("基地围墙的完工时间—基地内建设的附属设施")
    private LocalDateTime baseWallTime;

    /**
     * 基地围墙的保建手续—基地内建设的附属设施
     */
    @ApiModelProperty("基地围墙的保建手续—基地内建设的附属设施")
    private String baseWallProcedures;

    /**
     * 硬化道路的占地面积（m²）基地内建设的附属设施
     */
    @ApiModelProperty("硬化道路的占地面积（m²）基地内建设的附属设施")
    private BigDecimal baseRoadArea;

    /**
     * 硬化道路的完工时间—基地内建设的附属设施
     */
    @ApiModelProperty("硬化道路的完工时间—基地内建设的附属设施")
    private LocalDateTime baseRoadTime;

    /**
     * 硬化道路的保建手续—基地内建设的附属设施
     */
    @ApiModelProperty("硬化道路的保建手续—基地内建设的附属设施")
    private String baseRoadProcedures;

    /**
     * 硬化停车场的占地面积（m²）基地内建设的附属设施
     */
    @ApiModelProperty("硬化停车场的占地面积（m²）基地内建设的附属设施")
    private BigDecimal baseVehicleArea;

    /**
     * 硬化停车场的完工时间—基地内建设的附属设施
     */
    @ApiModelProperty("硬化停车场的完工时间—基地内建设的附属设施")
    private LocalDateTime baseVehicleTime;

    /**
     * 硬化停车场的保建手续—基地内建设的附属设施
     */
    @ApiModelProperty("硬化停车场的保建手续—基地内建设的附属设施")
    private String baseVehicleProcedures;


}

