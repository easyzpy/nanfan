package com.randing.system.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Step10ReturnPeople implements Serializable {
    private static final long serialVersionUID = 3524341282884562276L;



    /**
     * 单位信息
     */
    @ApiModelProperty("单位信息")
    @NotBlank(message = "单位信息不能为空")
    private String company;

    /**
     * 基地位置
     */
    @ApiModelProperty("基地位置")
    @NotBlank(message = "基地位置不能为空")
    private String address;

    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    @NotBlank(message = "姓名不能为空")
    private String peopleName;
    /**
     * 基地联系人电话
     */
    @ApiModelProperty("基地联系人电话")
    @NotBlank(message = "基地联系人电话不能为空")
    private String baseContactPhone;

    /**
     * 到达日期
     */
    @ApiModelProperty("到达日期")
    @NotBlank(message = "到达日期不能为空")
    private LocalDateTime arrivalDate;

    /**
     * 学历
     */
    @ApiModelProperty("学历")
    @NotBlank(message = "学历不能为空")
    private String education;

    /**
     * 主要科研工作内容
     */
    @ApiModelProperty("主要科研工作内容")
    @NotBlank(message = "主要科研工作内容不能为空")
    private String researchContent;

}
