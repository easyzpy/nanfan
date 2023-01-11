package com.randing.common.core.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.randing.common.annotation.Excel;
import com.randing.common.annotation.FieldData;
import com.randing.common.constant.LoginConstant;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色表 sys_role
 * 
 * @author ruoyi
 */
@Data
@Accessors(chain = true)
@TableName("sys_role")
public class SysRole implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 角色ID */
    @Excel(name = "角色序号", cellType = Excel.ColumnType.NUMERIC)
    @TableId(value = "id",type = IdType.AUTO)
    @FieldData(name = "id")
    private Long id;

    /** 角色名称 */
    @Excel(name = "角色名称")
    @NotBlank(message = "角色名称不能为空")
    @Size(min = 0, max = 30, message = "角色名称长度不能超过30个字符")
    @FieldData(name = "角色名称")
    private String name;

    /**
     * 角色标识
     */
    @Excel(name = "角色标识")
    @Size(min = 0, max = 30, message = "角色标识长度不能超过30个字符")
    @FieldData(name = "角色标识")
    private String identity;

    /** 盲态状态:0-盲态 1-非盲 2-非盲可揭盲 3-盲态可揭盲 */
    @Excel(name = "盲态状态", readConverterExp = "0=盲态,1=非盲,2=非盲可揭盲,3=盲态可揭盲")
    @FieldData(name = "盲态状态", description = "0=盲态,1=非盲,2=非盲可揭盲,3=盲态可揭盲")
    private Integer blindState;

    /** 角色排序 */
    @Excel(name = "角色排序")
    //@NotNull(message = "显示顺序不能为空")
    private Integer roleSort;

    /** 角色状态（0正常 1停用） */
    @Excel(name = "角色状态", readConverterExp = "0=正常,1=停用")
    @FieldData(name = "角色状态", description = "0=正常,1=停用")
    private Integer status;

    /** 角色类型（0-系统角色 1-项目角色） */
    @Excel(name = "角色类型", readConverterExp = "0=系统角色,1=项目角色")
    @FieldData(name = "角色类型", description = "0=系统角色,1=项目角色")
    private Integer roleType;

    /** 项目编号 */
    @FieldData(name = "项目编号")
    private Long prId;

    /**
     * 单位类型 0-申办方 1-研究中心 2- 仓库 3-实验室
     */
    @ApiModelProperty(value = "单位类型 0-申办方 1-研究中心 2- 仓库 3-实验室", required = false)
    @FieldData(name = "单位类型", description = "0=申办方,1=研究中心,2=仓库,3=实验室")
    private Integer unitType;

    /**
     * 是否系统内置角色：1-是，0-否
     */
    @ApiModelProperty(value = "是否系统内置角色：1-是，0-否", required = false)
    private Integer isDefault;

    /**
     * 逻辑删除标记  0-未删除 1-已删除
     */
    private Integer delFlag;

    /** 创建者 */
    @FieldData(name = "创建者")
    private String createBy;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @FieldData(name = "创建时间", description = "date")
    private LocalDateTime createTime;

    /** 更新者 */
    @FieldData(name = "更新者")
    private String updateBy;

    /** 更新时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @FieldData(name = "更新时间", description = "date")
    private LocalDateTime updateTime;

    /** 描述 */
    @FieldData(name = "描述")
    private String remark;

    /** 菜单组 */
    @TableField(exist = false)
    private Long[] menuIds;

    /** 菜单组id */
    @TableField(exist = false)
    private Long[] checkedKey;

    /** 菜单列表 */
    @TableField(exist = false)
    private List<Integer> checkedKeys;

    @Excel(name = "项目编号")
    @TableField(exist = false)
    private String projectNo;

    /**
     * 请求参数
     */
    @TableField(exist = false)
    private Map<String, Object> params = new HashMap<>();

    public SysRole(Long id) {
        this.id = id;
    }

    public SysRole() {

    }

    public Integer isAdmin() {
        return isAdmin(this.id,this.roleType);
    }

    public static Integer isAdmin(Long id, Integer roleType) {
        if (id != null && 1L == id && roleType != null && 0 == roleType) {
            return LoginConstant.SUPER_ADMINISTRATOR;
        } else if (roleType != null && 0 == roleType) {
            return LoginConstant.ADMINISTRATOR;
        } else {
            return LoginConstant.PROJECT_USERS;
        }
    }

}
