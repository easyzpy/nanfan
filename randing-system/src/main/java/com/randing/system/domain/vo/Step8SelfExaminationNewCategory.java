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
public class Step8SelfExaminationNewCategory implements Serializable {
    private static final long serialVersionUID = 3524341282884562276L;


    /**
     * 新品类
     */
    @ApiModelProperty("作物名称")
    @NotBlank(message = "作物名称不能为空")
    private String newCategory;

    /**
     * 个数
     */
    @ApiModelProperty("个数")
    @NotNull(message = "个数不能为空")
    private Integer newCategoryNumber;


}
