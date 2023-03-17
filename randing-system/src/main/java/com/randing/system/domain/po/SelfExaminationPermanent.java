package com.randing.system.domain.po;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 单位的常驻联系人
 * </p>
 *
 * @author Leen
 * @since 2023-03-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class SelfExaminationPermanent implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId("id")
    private Long id;
    /**
     * 常驻联系人id
     */
    @ApiModelProperty("常驻联系人id")
    private String permanentId;

    /**
     * 南繁自查信息统计表id
     */
    @ApiModelProperty("南繁自查信息统计表id")
    private String selfExaminationId;

    /**
     * 常驻联系人名称
     */
    @ApiModelProperty("常驻联系人名称")
    private String permanentName;

    /**
     * 常驻联系方式
     */
    @ApiModelProperty("常驻联系方式")
    private String permanentPhone;

    /**
     * 联系人专业
     */
    @ApiModelProperty("联系人专业")
    private String permanentMajor;

    /**
     * 联系人学历
     */
    @ApiModelProperty("联系人学历")
    private String permanentEducation;

    /**
     * 技术职称
     */
    @ApiModelProperty("技术职称")
    private String permanentTitle;

    /**
     * 年龄
     */
    @ApiModelProperty("年龄")
    private Integer permanentAge;

    /**
     * 角色
     */
    @ApiModelProperty("角色")
    private String permanentRole;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String permanentRemarks;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    private String createBy;

    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /**
     * 修改人
     */
    @ApiModelProperty("修改人")
    private String updateBy;


}
