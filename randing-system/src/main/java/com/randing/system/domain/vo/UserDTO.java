package com.randing.system.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {


    private Integer id;
    private String loginName;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 单位
     */
    private String unit;

    /**
     * 联系 电话
     */
    private String contactPhone;

    /**
     * 性别 0 女 1 男
     */
    private String sex;

    private Integer delFlag;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer updateUser;

    /**
     * 系统类别：0、土地系统，1、单位信息系统
     */
    private String systemType;

//    private List<Menu>
}
