package com.randing.system.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Step6SelfExamination implements Serializable {

    private static final long serialVersionUID = -9192482559459605751L;

    /*用工需求--------------------------*/
    /**
     * 普通工人数（南翻服务需求—用工需求）
     */
    @ApiModelProperty("普通工人数（南翻服务需求—用工需求）")
    private Integer ordinaryWorker;

    /**
     * 技术工人数（南翻服务需求—用工需求）
     */
    @ApiModelProperty("技术工人数（南翻服务需求—用工需求）")
    private Integer technicalWorker;

    /**
     * 其他人数（南翻服务需求—用工需求）
     */
    @ApiModelProperty("其他人数（南翻服务需求—用工需求）")
    private Integer otherWorker;
    /*用地需求---------------------*/
    /**
     * 试验用地面积（南繁服务需求—用地需求）
     */
    @ApiModelProperty("试验用地面积（南繁服务需求—用地需求）")
    private Double testLand;

    /**
     * 实施用地面积（南繁服务需求—用地需求）
     */
    @ApiModelProperty("实施用地面积（南繁服务需求—用地需求）")
    private Double implementationLand;
    /*服务需求------------------------*/
    /**
     * 农事服务需求（0、无，1、有）（南繁服务需求）
     */
    @ApiModelProperty("农事服务需求（0、无，1、有）（南繁服务需求）")
    private String serviceDemand;

    /**
     * 具体农事服务需求（南繁服务需求）
     */
    @ApiModelProperty("具体农事服务需求（南繁服务需求）")
    private String serviceDemandDescribe;

    /**
     * 是否有委托育种、制种需求（0、没有，1、有）（南繁服务需求）
     */
    @ApiModelProperty("是否有委托育种、制种需求（0、没有，1、有）（南繁服务需求）")
    private String entrustedBreeding;

    /**
     * 具体委托育种、制种需求（南繁服务需求）
     */
    @ApiModelProperty("具体委托育种、制种需求（南繁服务需求）")
    private String entrustedBreedingDescribe;

    /**
     * 是否有实验室需求（0、没有，1、有）（南繁服务需求）
     */
    @ApiModelProperty("是否有实验室需求（0、没有，1、有）（南繁服务需求）")
    private String laboratoryRequirements;

    /**
     * 具体实验室需求（南繁服务需求）
     */
    @ApiModelProperty("具体实验室需求（南繁服务需求）")
    private String laboratoryRequirementsDescribe;

    /**
     * 是否有其他需求（0、没有，1、有）（南繁服务需求）
     */
    @ApiModelProperty("是否有其他需求（0、没有，1、有）（南繁服务需求）")
    private String otherRequirements;

    /**
     * 具体其他需求（南繁服务需求）
     */
    @ApiModelProperty("具体其他需求（南繁服务需求）")
    private String otherRequirementsDescribe;
}
