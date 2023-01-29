package com.randing.system.domain.po;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author Leen
 * @since 2023-01-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 登录名
     */
    @ApiModelProperty("登录名")
    private String loginName;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;

    /**
     * 用户名称
     */
    @ApiModelProperty("用户名称")
    private String name;

    /**
     * 单位
     */
    @ApiModelProperty("单位")
    private String unit;

    /**
     * 联系 电话
     */
    @ApiModelProperty("联系 电话")
    private String contactPhone;

    /**
     * 性别 0 女 1 男
     */
    @ApiModelProperty("性别 0 女 1 男")
    private String sex;

    private Integer delFlag;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer updateUser;

    /**
     * 系统类别：0、土地系统，1、单位信息系统
     */
    @ApiModelProperty("系统类别：0、土地系统，1、单位信息系统")
    private String systemType;

    @TableField(exist = false)
    private List<Role> roles;

}
