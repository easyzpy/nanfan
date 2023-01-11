package com.randing.common.core.domain.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.randing.common.annotation.Excel;
import com.randing.common.annotation.FieldData;
import com.randing.common.constant.LoginConstant;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户对象 sys_user
 * 
 * @author randing
 */
@Data
@Accessors(chain = true)
@TableName("sys_user")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="用户id",hidden = false,required = false)
    @FieldData(name = "用户id")
    private Long id;

    /**
     * 用户号:姓名+2位序列号
     */
    @Excel(name = "账号")
    @NotBlank(message = "用户号:姓名+2位序列号不能为空")
    @Size(min = 0, max = 20, message = "用户号:姓名+2位序列号长度不能超过20个字符")
    @ApiModelProperty(value="用户号:姓名+2位序列号",hidden = false,required = true)
    @FieldData(name = "账号")
    private String account;

    /**
     * 用户名称
     */
    @Excel(name = "用户姓名")
    @NotBlank(message = "用户姓名不能为空")
    @Size(min = 0, max =20 , message = "用户姓名长度不能超过20个字符")
    @ApiModelProperty(value="用户姓名",hidden = false,required = true)
    @FieldData(name = "用户姓名")
    private String userName;

    /**
     * 用户密码
     */
    @ApiModelProperty(value="用户密码",hidden = false,required = true)
    private String password;

    /**
     * 项目Id
     */
    @ApiModelProperty(value="项目Id",hidden = false,required = true)
    @FieldData(name = "项目Id")
    private Long projectId;

    /**
     * 项目名称
     */
    @Excel(name = "项目名称")
    @TableField(exist = false)
    @ApiModelProperty(value="项目名称",hidden = false,required = false)
    private String projectName;

    /**
     * 项目编号
     */
    @TableField(exist = false)
    @ApiModelProperty(value="项目编号",hidden = false,required = false)
    private String projectNo;

    /**
     * logo
     */
    @TableField(exist = false)
    @ApiModelProperty(value="logo",hidden = false,required = false)
    private String unitLogo;

    /**
     * 用户邮箱
     */
    @Excel(name = "用户邮箱")
    @NotBlank(message = "用户邮箱不能为空")
    @Size(min = 0, max = 30, message = "用户邮箱长度不能超过30个字符")
    @ApiModelProperty(value="用户邮箱",hidden = false,required = true)
    @FieldData(name = "用户邮箱")
    private String email;

    /**
     * 手机号码
     */
    @Excel(name = "手机号")
    @Size(min = 0, max = 11, message = "手机号长度不能超过11个字符")
    @ApiModelProperty(value="手机号",hidden = false,required = true)
    @FieldData(name = "手机号")
    private String phone;

    /**
     * 激活码
     */
    @ApiModelProperty(value="激活码",hidden = true,required = false)
    @FieldData(name = "激活码")
    private String activeCode;

    /**
     * 用户类型:0-系统用户 1-项目用户
     */
    @NotNull(message = " 用户类型:0-系统用户 1-项目用户 不能为空")
    @ApiModelProperty(value="用户类型:0-系统用户 1-项目用户",hidden = false,required = true)
    @FieldData(name = "用户类型", description = "0=系统用户,1=项目用户")
    private Integer userType;

    /**
     * 角色Id
     */
    @NotNull(message = "角色Id不能为空")
    @ApiModelProperty(value="角色Id",hidden = false,required = false)
    @FieldData(name = "角色Id")
    private Long roleId;

    /**
     * 角色名称
     */
    @TableField(exist = false)
    @Excel(name = "角色名称", type = Excel.Type.IMPORT)
    private String roleName;

    /**
     * 是否盲态用户
     */
    @TableField(exist = false)
    @ApiModelProperty(value="是否盲态用户",hidden = true)
    private Integer isBlind;

    /**
     * 用户状态:0=正常,1=停用
     */
    @Excel(name = "用户状态",readConverterExp = "0=正常/激活,1=停用/未激活=")
    @ApiModelProperty(value="用户状态:0=正常,1=停用=",hidden = false,required = true)
    @FieldData(name = "用户状态", description = "0=正常,1=停用")
    private Integer status;

    /**
     * 单位类型：0-申办方/CRO,1-中心实验室,2-研究中心,3-仓库
     */
    @ApiModelProperty(value="单位类型：0-申办方/CRO,1-中心实验室,2-研究中心,3-仓库",hidden = true,required = false)
    @Excel(name = "单位类型",readConverterExp = "0-申办方/CRO,1-中心实验室,2-研究中心,3-仓库", type = Excel.Type.IMPORT)
    @FieldData(name = "单位类型", description = "0=申办方/CRO,1=中心实验室,2=研究中心,3=仓库")
    private Integer unitType;

    /**
     * 单位名称
     */
    @ApiModelProperty(value="单位名称",hidden = true,required = false)
    @Excel(name = "单位名称", type = Excel.Type.IMPORT)
    @FieldData(name = "单位名称")
    private String unitName;

    /**
     * 单位id
     */
    @ApiModelProperty(value="单位id",hidden = true,required = false)
    @FieldData(name = "单位id")
    private String unitId;

    /**
     * 职位
     */
    @ApiModelProperty(value="职位",hidden = true,required = false)
    @FieldData(name = "职位")
    private Integer positon;

    /**
     * 是否研究项目所有者:1-是，0-否
     */
    @ApiModelProperty(value="是否研究项目所有者:1-是，0-否",hidden = true,required = false)
    @FieldData(name = "是否研究项目所有者", description = "1=是,0=否")
    private Integer isPrOwner;

    /**
     * 是否研究中心所有者:1-是，0-否
     */
    @ApiModelProperty(value="是否研究中心所有者:1-是，0-否",hidden = true,required = false)
    @FieldData(name = "是否研究中心所有者", description = "1=是,0=否")
    private Integer isSiteOwner;

    /**
     * 相关机构，只有申办方项目经理能有
     */
    @ApiModelProperty(value="相关机构，只有申办方项目经理能有",hidden = true,required = false)
    @FieldData(name = "相关机构")
    private String relateSite;

    /**
     * 备注
     */
    @ApiModelProperty(value="备注",hidden = false,required = false)
    @FieldData(name = "备注")
    private String remark;

    /**
     * 逻辑删除标记  0-未删除 1-已删除
     */
    @ApiModelProperty(value=" 逻辑删除标记  0-未删除 1-已删除",hidden = true,required = false)
    private Integer delFlag;

    /**
     * 用户创建人
     */
    @ApiModelProperty(value="用户创建人",hidden = true,required = false)
    @FieldData(name = "用户创建人")
    private String createBy;

    /**
     * 用户创建时间
     */
    @ApiModelProperty(value="用户创建时间",hidden = true,required = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间")
    @FieldData(name = "用户创建时间", description = "date")
    private LocalDateTime createTime;

    /**
     * 用户更新人
     */
    @ApiModelProperty(value="用户更新人",hidden = true,required = false)
    @FieldData(name = "用户更新人")
    private String updateBy;

    /**
     * 用户更新时间
     */
    @ApiModelProperty(value="用户更新时间",hidden = true,required = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @FieldData(name = "用户更新时间", description = "date")
    private LocalDateTime updateTime;

    /**
     * 最后一次修改密码时间
     */
    @ApiModelProperty(value="最后一次修改密码时间",hidden = true,required = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @FieldData(name = "最后一次修改密码时间", description = "date")
    private LocalDateTime pwdUpdateTime;

    /**
     * 用户最后登录时间
     */
    @ApiModelProperty(value="用户最后登录时间",hidden = true,required = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @FieldData(name = "用户最后登录时间", description = "date")
    private LocalDateTime lastloginTime;

    @TableField(exist = false)
    @ApiModelProperty(value="其他单位",hidden = true,required = false)
    private String otherUnitName;

    @TableField(exist = false)
    @ApiModelProperty(value="登录用户菜单",hidden = true,required = false)
    private SysMenu menu;

    @TableField(exist = false)
    @ApiModelProperty(value="登录用户角色",hidden = true,required = false)
    private SysRole roles;

    @TableField(exist = false)
    @ApiModelProperty(value="激活码",hidden = true,required = false)
    private String randomCode;

    @TableField(exist = false)
    @ApiModelProperty(value="职位",hidden = true,required = false)
    @Excel(name = "职位", type = Excel.Type.IMPORT)
    private String positonName;

    @TableField(exist = false)
    @ApiModelProperty(value="项目用户状态 0 启用 1禁用",hidden = true)
    private Integer projUserStatus;


    @ApiModelProperty(value="是否是系统管理员",hidden = true,required = false)
    public Integer isAdmin() {
        return isAdmin(this.id,this.userType);
    }

    @ApiModelProperty(value="是否是系统管理员",hidden = true,required = false)
    public static Integer isAdmin(Long id, Integer userType) {
        if (id != null && 1L == id && userType != null && 0 == userType) {
            return LoginConstant.SUPER_ADMINISTRATOR;
        } else if (userType != null && 0 == userType) {
            return LoginConstant.ADMINISTRATOR;
        } else {
            return LoginConstant.PROJECT_USERS;
        }
    }

    @ApiModelProperty(value="激活验证码",hidden = true,required = false)
    public String getRandomCode() {
        return randomCode;
    }

    @ApiModelProperty(value="激活验证码",hidden = true,required = false)
    public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
    }


}
