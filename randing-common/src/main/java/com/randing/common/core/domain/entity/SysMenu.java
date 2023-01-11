package com.randing.common.core.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜单权限表 sys_menu
 * 
 * @author randing
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_menu")
public class SysMenu implements Comparable<SysMenu>, Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="菜单ID",required = false,hidden = false)
    private Long id;

    /**
     * 菜单名称
     */
    @NotBlank(message = "菜单名称不能为空")
    @ApiModelProperty(value="菜单名称",required = true,hidden = false)
    private String menuName;

    /**
     * 父菜单编号
     */
    @ApiModelProperty(value="父菜单编号",required = false,hidden = false)
    private Long parentId;

    /**
     * 显示顺序
     */
    @ApiModelProperty(value="显示顺序",required = true,hidden = false)
    private Integer orderNum;

    /**
     * 路由地址
     */
    @ApiModelProperty(value="路由地址",required = false,hidden = false)
    private String path;

    /**
     * 组件路径
     */
    @ApiModelProperty(value="组件路径",required = false,hidden = false)
    private String component;

    /**
     * 是否为外链: 0-是 1-否
     */
    @NotNull(message = "是否为外链: 0-是 1-否不能为空")
    @ApiModelProperty(value="是否为外链: 0-是 1-否",required = true,hidden = false)
    private Integer isFrame;

    /**
     * 菜单类型: M-目录 C-菜单 F-按钮
     */
    @NotBlank(message = "菜单类型: M-目录 C-菜单 F-按钮不能为空")
    @ApiModelProperty(value="菜单类型: M-目录 C-菜单 F-按钮",required = true,hidden = false)
    private String menuType;

    /**
     * 菜单状态: 0-显示 1-隐藏
     */
    @NotBlank(message = "菜单状态: 0-显示 1-隐藏 不能为空")
    @ApiModelProperty(value="菜单状态: 0-显示 1-隐藏",required = true,hidden = false)
    private String visible;

    /**
     * 菜单状态: 0-正常 1-停用
     */
    @NotBlank(message = "菜单状态: 0-正常 1-停用 不能为空")
    @ApiModelProperty(value="菜单状态: 0-正常 1-停用",required = true,hidden = false)
    private String status;

    /**
     * 权限标识
     */
    @ApiModelProperty(value="权限标识",required = false,hidden = false)
    private String identity;

    /**
     * 菜单图标
     */
    @ApiModelProperty(value="菜单图标",required = false,hidden = false)
    private String icon;

    /**
     * 创建者
     */
    @ApiModelProperty(value="创建者",required = true,hidden = true)
    private String createBy;

    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间",required = true,hidden = true)
    private LocalDateTime createTime;

    /**
     * 更新者
     */
    @ApiModelProperty(value="更新者",required = true,hidden = true)
    private String updateBy;

    /**
     * 更新时间
     */
    @ApiModelProperty(value="更新时间",required = true,hidden = true)
    private LocalDateTime updateTime;

    /**
     * 备注
     */
    @ApiModelProperty(value="备注",required = true,hidden = false)
    private String remark;

    /**
     * 是否项目菜单: 0-否 1-是
     */
    @ApiModelProperty(value="是否项目菜单: 0-否 1-是",required = true,hidden = false)
    private Integer isProject;

    /**
     * 逻辑删除标记  0-未删除 1-已删除
     */
    @ApiModelProperty(value="逻辑删除标记  0-未删除 1-已删除",required = true, hidden = true)
    private Integer delFlag;

    /**
     * 所属项目模块:1-药品管理，2-模拟随机，3-基础项目，4-自适应项目，5-多队列，6-中心实验室
     */
    @ApiModelProperty(value="所属项目模块:1-药品管理，2-模拟随机，3-基础项目，4-自适应项目，5-多队列，6-中心实验室",
            required = false,hidden = false)
    private Integer projectModel;

    /**
     * 登录用户id
     */
    @TableField(exist = false)
    @ApiModelProperty(value="登录用户id",required = true,hidden = false)
    private Long userId;

    /** 子菜单 */
    @TableField(exist = false)
    @ApiModelProperty(value="子菜单",required = true,hidden = true)
    private List<SysMenu> children = new ArrayList<SysMenu>();

    /** 请求参数 */
    @TableField(exist = false)
    @ApiModelProperty(value="请求参数",required = false,hidden = false)
    private Map<String, Object> params = new HashMap<>();
    

    @Override
    public int compareTo(SysMenu o) {
        if(this.orderNum != o.orderNum){
            return this.orderNum - o.orderNum;
        }
        return 0;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public Integer getIsFrame() {
        return isFrame;
    }

    public void setIsFrame(Integer isFrame) {
        this.isFrame = isFrame;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIsProject() {
        return isProject;
    }

    public void setIsProject(Integer isProject) {
        this.isProject = isProject;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getProjectModel() {
        return projectModel;
    }

    public void setProjectModel(Integer projectModel) {
        this.projectModel = projectModel;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<SysMenu> getChildren() {
        return children;
    }

    public void setChildren(List<SysMenu> children) {
        this.children = children;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public SysMenu() {
    }

    public SysMenu(Long id, String menuName, Long parentId, Integer orderNum, String path, String component, Integer isFrame, String menuType, String visible, String status, String identity, String icon, String createBy, LocalDateTime createTime, String updateBy, LocalDateTime updateTime, String remark, Integer isProject, Integer delFlag, Integer projectModel, Long userId, List<SysMenu> children, Map<String, Object> params) {
        this.id = id;
        this.menuName = menuName;
        this.parentId = parentId;
        this.orderNum = orderNum;
        this.path = path;
        this.component = component;
        this.isFrame = isFrame;
        this.menuType = menuType;
        this.visible = visible;
        this.status = status;
        this.identity = identity;
        this.icon = icon;
        this.createBy = createBy;
        this.createTime = createTime;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
        this.remark = remark;
        this.isProject = isProject;
        this.delFlag = delFlag;
        this.projectModel = projectModel;
        this.userId = userId;
        this.children = children;
        this.params = params;
    }
}
