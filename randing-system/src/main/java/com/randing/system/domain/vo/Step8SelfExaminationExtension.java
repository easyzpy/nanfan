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
public class Step8SelfExaminationExtension implements Serializable {
    private static final long serialVersionUID = 3524341282884562276L;


    /**
     * 品种名称
     */
    @ApiModelProperty("品种名称")
    private String extensionName;

    /**
     * 占地面积
     */
    @ApiModelProperty("占地面积")
    private Double extensionArea;


    /**
     * 推广类别（0，崖州区，1、全国）
     */
    @ApiModelProperty("推广类别（0，崖州区，1、全国）")
    private String extensionType;


}
