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
public class Step2SelfExaminationPermanentReqVo implements Serializable {
    private static final long serialVersionUID = -9151756505564447960L;

    private Long id;
    /**
     * 南繁自查信息统计表id
     */
    @ApiModelProperty("南繁自查信息统计表id")
    private String selfExaminationId;

    /**
     * 常驻联系人名称
     */
    @ApiModelProperty("常驻联系人名称")
    @NotBlank(message = "姓名不能为空")
    private String permanentName;

    /**
     * 常驻联系方式
     */
    @NotBlank(message = "联系方式不能为空")
    @ApiModelProperty("常驻联系方式")
    private String permanentPhone;

    /**
     * 联系人专业
     */
    @NotBlank(message = "专业不能为空")
    @ApiModelProperty("联系人专业")
    private String permanentMajor;

    /**
     * 联系人学历
     */
    @NotBlank(message = "学历不能为空")
    @ApiModelProperty("联系人学历")
    private String permanentEducation;

    /**
     * 技术职称
     */
    @NotBlank(message = "技术职称不能为空")
    @ApiModelProperty("技术职称")
    private String permanentTitle;

    /**
     * 年龄
     */
    @ApiModelProperty("年龄")
    @NotNull(message = "年龄不能为空")
    private Integer permanentAge;

    /**
     * 角色
     */
    @NotBlank(message = "角色不能为空")
    @ApiModelProperty("角色")
    private String permanentRole;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空")
    @ApiModelProperty("备注")
    private String permanentRemarks;

}

