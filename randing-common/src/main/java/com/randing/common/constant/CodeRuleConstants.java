package com.randing.common.constant;

/**
 * 用户常量信息
 * 
 * @author randing
 */
public class CodeRuleConstants
{
    //编号项：1-受试者预筛选号，2-受试者编号，3-受试者随机号
    /**编号类型: 受试者预筛选号 */
    public static final Integer ITEM_PRESUBJECT_NO = 1;
    /**编号类型:  受试者编号 */
    public static final Integer ITEM_SUBJECT_NO =2;
    /**编号类型:  受试者随机号 */
    public static final Integer ITEM_RANDOM_NO = 3;


    //前缀类型: 1-自定义，2-系统值,3-自定义+研究中心编号
    /**前缀类型: 自定义 */
    public static final Integer PREFIX_TYPE_CUSTOM = 1;
    /** 前缀类型: 系统值 */
    public static final Integer PREFIX_TYPE_SYSTEM =2;
    /** 前缀类型: 系统值 */
    public static final Integer PREFIX_TYPE_CUSTOM_SITECODE =3;


    //后缀类型: 1- 随机号,2- 流水号(项目),3-流水号(研究中心)
    /**后缀类型: 随机号 */
    public static final Integer SUFFIX_TYPE_RANDOM = 1;
    /** 后缀类型: 流水号(项目) */
    public static final Integer SUFFIX_TYPE_FLOW_PR =2;
    /** 后缀类型: 流水号(研究中心) */
    public static final Integer SUFFIX_TYPE_FLOW_SITE =3;



}
