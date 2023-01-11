package com.randing.common.core.domain.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.randing.common.annotation.Excel;
import com.randing.common.annotation.Excel.ColumnType;
import com.randing.common.core.domain.BaseEntity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 字典类型表 sys_dict_type
 * 
 * @author randing
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_dict_type")
public class SysDictType implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 字典主键 */
    @Excel(name = "字典主键", cellType = ColumnType.NUMERIC)
    @ApiModelProperty(value = "字典主键", required = false,hidden = false,dataType = "Long")
    private Long dictId;

    /** 字典名称 */
    @Excel(name = "字典名称")
    @ApiModelProperty(value = "字典名称", required = true,hidden = false,dataType = "String")
    private String dictName;

    /** 字典类型 */
    @Excel(name = "字典类型")
    @ApiModelProperty(value = "字典类型", required = true,hidden = false,dataType = "String")
    private String dictType;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    @ApiModelProperty(value = "状态（0正常 1停用）", required = true,hidden = false,dataType = "String")
    private String status;

    /**
     * 逻辑删除标记  0-未删除 1-已删除
     */
    @ApiModelProperty(value = "逻辑删除标记  0-未删除 1-已删除", required = false,hidden = true,dataType = "Integer")
    private Integer delFlag;

    /** 备注 */
    @ApiModelProperty(value = "备注", required = false,hidden = false,dataType = "String")
    private String remark;

    /** 创建者 */
    @ApiModelProperty(value = "创建者", required = false,hidden = true,dataType = "String")
    private String createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间", required = false,hidden = true,dataType = "LocalDateTime")
    private LocalDateTime createTime;

    /** 更新者 */
    @ApiModelProperty(value = "更新者", required = false,hidden = true,dataType = "String")
    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间", required = false,hidden = true,dataType = "LocalDateTime")
    private LocalDateTime updateTime;

    /**
     * 请求参数
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "请求参数", required = false,hidden = false,dataType = "Map<String, Object>")
    private Map<String, Object> params = new HashMap<>();
}
