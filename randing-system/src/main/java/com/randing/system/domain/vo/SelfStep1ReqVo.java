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
public class SelfStep1ReqVo implements Serializable {
    private static final long serialVersionUID = -5108369411586502724L;
    /**
     * 单位名称
     */
    @ApiModelProperty("单位名称")
    @NotBlank(message = "单位名称不能为空")
    private String companyName;

    /**
     * 基地位置（具体到村小组）
     */
    @ApiModelProperty("基地位置（具体到村小组）")
    @NotBlank(message = "南繁地址不能为空")
    private String baseAddress;

    /**
     * 联系人手机（联系方式）
     */
    @ApiModelProperty("联系人手机（联系方式）")
    @NotBlank(message = "联系方式不能为空")
    private String contactPhone;

    /**
     * 单位性质（1、高校，2、科研院所，3、企业，4、其他）
     */
    @ApiModelProperty("单位性质（1、高校，2、科研院所，3、企业，4、其他）")
    @NotNull(message = "单位性质不能为空")
    private String unitNature;

    @ApiModelProperty("社会信用代码")
    @NotBlank(message = "统一代码不能为空")
    private String creditCode;

    @ApiModelProperty("注册地址")
    @NotBlank(message = "注册地址不能为空")
    private String registerAddress;
}
