package com.randing.system.domain.po;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
    private String unitName;

    /**
     * 法人名称
     */
    @ApiModelProperty("法人名称")
    private String legalPerson;

    /**
     * 联系人
     */
    @ApiModelProperty("联系人")
    private String unitContacts;

    /**
     * 联系电话
     */
    @ApiModelProperty("联系电话")
    private String contactsPhone;

    /**
     * 单位地址
     */
    @ApiModelProperty("单位地址")
    private String unitAddress;

    /**
     * 统一信用代码
     */
    @ApiModelProperty("统一信用代码")
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
    private String unitSimpleName;


}
