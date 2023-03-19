package com.randing.system.domain.po;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author Leen
 * @since 2023-03-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SelfExaminationExtension implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId
    private Long id;
    /**
     * 科研成功转化个数表id
     */
    @ApiModelProperty("科研成功转化个数表id")
    private String extensionId;

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
     * 单位自查信息表id
     */
    @ApiModelProperty("单位自查信息表id")
    private String selfExaminationId;

    /**
     * 推广类别（0，崖州区，1、全国）
     */
    @ApiModelProperty("推广类别（0，崖州区，1、全国）")
    private String extensionType;


}
