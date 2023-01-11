package com.randing.common.utils.iface.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class YzbUserInfo implements Serializable {
    private static final long serialVersionUID = -4400571648251155808L;

    /**
     * 用户ID
     */
    private String id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 用户头像地址
     */
    private String photo;
    /**
     *联系方式
     */
    private String phone;
    /**
     * 企业ID
     */
    private String orgId;
    /**
     * 企业名称
     */
    private String orgName;
    /**
     * 身份类型 0.未选择 1.游客、2.居民 3.学校师生 4.科技城管理局员工 5.入园企业员工
     */
    private Integer identityType;
    /**
     *是否已选择身份 1.是 0.否
     */
    private Integer isIdentity;
    /**是否已实名认证 1.是 0.否
     *
     */
    private Integer isVerified;



}
