package com.randing.common.utils.jwt;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

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
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 角色id
     */
    private String roleId;


}
