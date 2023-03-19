package com.randing.system.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Step9SelfExamination implements Serializable {
    private static final long serialVersionUID = 3524341282884562276L;



    /**
     * 南繁服务建议
     */
    @ApiModelProperty("南繁服务建议")
    @NotBlank(message = "南繁服务建议不能为空")
    private String serviceSuggestions;

    /**
     * 南繁基层管理服务员联系方式
     */
    @ApiModelProperty("南繁基层管理服务员联系方式")
    @NotBlank(message = "南繁基层管理服务员联系方式不能为空")
    private String adminPhone;


}
