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

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 南繁单位基地信息表
 * </p>
 *
 * @author Leen
 * @since 2023-03-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SelfExaminationBase implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId("id")
    private Long id;
    /**
     * 基地信息id
     */
    @ApiModelProperty("基地信息id")
    private String baseId;

    /**
     * 单位自查信息id
     */
    @ApiModelProperty("单位自查信息id")
    private String selfExaminationId;

    /**
     * 位置
     */
    @ApiModelProperty("位置")
    private String baseAddress;

    /**
     * 面积（亩）
     */
    @ApiModelProperty("面积（亩）")
    private BigDecimal baseArea;

    /**
     * 土地取得方式
     */
    @ApiModelProperty("土地取得方式")
    private String baseObtain;

    /**
     * 合同开始日期
     */
    @ApiModelProperty("合同开始日期")
    private LocalDateTime baseStart;

    /**
     * 合同结束日期
     */
    @ApiModelProperty("合同结束日期")
    private LocalDateTime baseEnd;

    /**
     * 占用面积（亩）基地内建设的配套设施
     */
    @ApiModelProperty("占用面积（亩）基地内建设的配套设施")
    private BigDecimal baseMatchingOccupy;

    /**
     * 建筑面积（m²）基地内建设的配套设施
     */
    @ApiModelProperty("建筑面积（m²）基地内建设的配套设施")
    private BigDecimal baseMatchingErect;

    /**
     * 建筑类型—基地内建设的配套设施
     */
    @ApiModelProperty("建筑类型—基地内建设的配套设施")
    private String baseMatchingErectType;

    /**
     * 楼层—基地内建设的配套设施
     */
    @ApiModelProperty("楼层—基地内建设的配套设施")
    private Integer baseMatchingFloor;

    /**
     * 报建时间—基地内建设的配套设施
     */
    @ApiModelProperty("报建时间—基地内建设的配套设施")
    private LocalDateTime baseMatchingCreate;

    /**
     * 报建手续—基地内建设的配套设施
     */
    @ApiModelProperty("报建手续—基地内建设的配套设施")
    private String baseMatchingProcedures;

    /*---------------围墙-------------*/
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

    /*---------------硬化道路-------------*/
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
    /*---------------硬化停车场-------------*/
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
    /*---------------晒场-------------*/
    /**
     * 晒场的占地面积（m²）基地内建设的附属设施
     */
    @ApiModelProperty("晒场的占地面积（m²）基地内建设的附属设施")

    private BigDecimal baseSiteArea;

    /**
     * 晒场停车场的完工时间—基地内建设的附属设施
     */
    @ApiModelProperty("晒场停车场的完工时间—基地内建设的附属设施")
    private LocalDateTime baseSiteTime;

    /**
     * 晒场停车场的保建手续—基地内建设的附属设施
     */
    @ApiModelProperty("晒场停车场的保建手续—基地内建设的附属设施")
    private String baseSiteProcedures;
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
    /*---------------综合办公楼----------------*/
    /**
     * 综合办公楼-面积
     */
    @ApiModelProperty("综合办公楼-面积")
    private BigDecimal baseZhbglArea;
    /**
     * 综合办公楼-完工时间
     */
    @ApiModelProperty("综合办公楼-完工时间")
    private LocalDateTime baseZhbglTime;
    /**
     * 综合办公楼-报建手续
     */
    @ApiModelProperty("综合办公楼-报建手续")
    private String baseZhbglProcedures;

/*---------------仓库----------------*/

    /**
     * 仓库-面积
     */
    @ApiModelProperty("仓库-面积")
    private BigDecimal baseCkArea;
    /**
     * 仓库-完工时间
     */
    @ApiModelProperty("仓库-完工时间")
    private LocalDateTime baseCkTime;
    /**
     * 仓库-报建手续
     */
    @ApiModelProperty("仓库-报建手续")
    private String baseCkProcedures;
    /*---------------宿舍楼----------------*/
    /**
     * 宿舍楼-面积
     */
    @ApiModelProperty("宿舍楼-面积")
    private BigDecimal baseSslArea;
    /**
     * 宿舍楼-完工时间
     */
    @ApiModelProperty("宿舍楼-完工时间")
    private LocalDateTime baseSslTime;
    /**
     * 宿舍楼-报建手续
     */
    @ApiModelProperty("宿舍楼-报建手续")
    private String baseSslProcedures;
    /*---------------食堂----------------*/
    /**
     * 食堂-面积
     */
    @ApiModelProperty("食堂-面积")
    private BigDecimal baseStArea;
    /**
     * 食堂-完工时间
     */
    @ApiModelProperty("食堂-完工时间")
    private LocalDateTime baseStTime;
    /**
     * 食堂-报建手续
     */
    @ApiModelProperty("食堂-报建手续")
    private String baseStProcedures;

}
