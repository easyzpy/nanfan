package com.randing.system.domain.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author Leen
 * @since 2023-03-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("unit")
public class Unit implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty("id")
    private Long id;
    /**
     * 单位id
     */
    @ApiModelProperty("单位id")
    private String unitId;

    /**
     * 单位名称
     */
    @ApiModelProperty("单位名称")
    @NotBlank(message = "单位名称不能为空")
    private String unitName;

    /**
     * 法人名称
     */
    @ApiModelProperty("法人名称")
    @NotBlank(message = "法人名称不能为空")
    private String legalPerson;

    /**
     * 联系人
     */
    @ApiModelProperty("联系人")
    @NotBlank(message = "联系人不能为空")
    private String unitContacts;

    /**
     * 联系电话
     */
    @ApiModelProperty("联系电话")
    @NotBlank(message = "联系电话不能为空")
    private String contactsPhone;

    /**
     * 单位地址
     */
    @ApiModelProperty("单位地址")
    @NotBlank(message = "单位地址不能为空")
    private String unitAddress;

    /**
     * 统一信用代码
     */
    @ApiModelProperty("统一信用代码")
    @NotBlank(message = "统一信用代码不能为空")
    private String creditCode;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private LocalDateTime createDate;

    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    private String createUser;

    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateDate;

    /**
     * 修改人
     */
    @ApiModelProperty("修改人")
    private String updateUser;

    /**
     * 单位简称
     */
    @ApiModelProperty("单位简称")
    @NotBlank(message = "单位简称不能为空")
    private String unitSimpleName;

    @ApiModelProperty("营业执照 fileUrl")
    @TableField(exist = false)
    private String fileUrl;
    @ApiModelProperty("营业执照 fileName 文件原名")
    @TableField(exist = false)
    private String fileName;

    @TableField(exist = false)
    @ApiModelProperty("用户联系手机号")
    private String userContractPhone;

}
