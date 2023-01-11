package com.randing.common.constant;

/**
 * 用户常量信息
 * 
 * @author randing
 */
public class UserConstants
{
    /**
     * 平台内系统用户的唯一标志
     */
    public static final String SYS_USER = "SYS_USER";

    /** 正常状态 */
    public static final String NORMAL = "0";

    /** 异常状态 */
    public static final String EXCEPTION = "1";

    /** 用户封禁状态 */
    public static final Integer USER_DISABLE = 1;

    /** 角色封禁状态 */
    public static final String ROLE_DISABLE = "1";

    /** 系统用户 */
    public static final Integer SYSTEM_USER = 0;

    /** 项目用户 */
    public static final Integer PROJECT_USER = 1;

    /** 字典正常状态 */
    public static final String DICT_NORMAL = "0";

    /** 是否为系统默认（是） */
    public static final String YES = "Y";

    /** 是否菜单外链（是） */
    public static final String YES_FRAME = "0";

    /** 是否菜单外链（否） */
    public static final String NO_FRAME = "1";

    /** 菜单类型（目录） */
    public static final String TYPE_DIR = "M";

    /** 菜单类型（菜单） */
    public static final String TYPE_MENU = "C";

    /** 菜单类型（按钮） */
    public static final String TYPE_BUTTON = "F";

    /** Layout组件标识 */
    public final static String LAYOUT = "Layout";

    /** ParentView组件标识 */
    public final static String PARENT_VIEW = "ParentView";

    /** 校验返回结果码 */
    public final static String UNIQUE = "0";
    public final static String NOT_UNIQUE = "1";


    /** 申办方 */
    public static final Integer SPONSOR = 0;

    /** 研究中心 */
    public static final Integer RESEARCH_INSTITUTE =2;

    /** 中心实验室 */
    public static final Integer CENTRAL_LABORATORY = 1;

    /** 仓库 */
    public static final Integer DEPOT = 3;

    /**
     * 职位字典常量中心实验室
     */
    public static final String POSITION="user_post_SITE";

}
