package com.randing.system.domain.po;

import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author Leen
 * @since 2023-01-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 角色id
     */
    private String roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色类别：0：公共角色，1：申请单位，2：审批单位，3：应用管理员，4：系统管理员，5：系统所有者（本系统中最顶级角色，不可被修改，不可被删除，拥有所有权限）
     */
    @ApiModelProperty("角色类别：0：公共角色，1：申请单位，2：审批单位，3：应用管理员，4：系统管理员，5：系统所有者（本系统中最顶级角色，不可被修改，不可被删除，拥有所有权限）")
    private Integer roleType;

    /**
     * 创建时间
     */
    private LocalDateTime addTime;

    /**
     * 创建人
     */
    private String addUser;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 修改人
     */
    private String updateUser;

    /**
     * 角色描述
     */
    private String roleMark;

    /**
     * 该角色是否可以删除（0：不可以，1：可以）
     */
    private String roleStatus;


}
