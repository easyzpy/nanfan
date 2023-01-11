package com.randing.common.constant;

/**
 * 通用常量信息
 *
 * @author randing
 */
public class Constants {
    /**
     * 邮箱正则表达式
     */
//    public static final String regex = "\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}";
    public static final String regex = "\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * GBK 字符集
     */
    public static final String GBK = "GBK";

    /**
     * http请求
     */
    public static final String HTTP = "http://";

    /**
     * https请求
     */
    public static final String HTTPS = "https://";

    /**
     * 通用成功标识
     */
    public static final String SUCCESS = "0";

    /**
     * 通用失败标识
     */
    public static final String FAIL = "1";

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 登录用户 redis key
     */
    public static final String LOGIN_TOKEN_KEY = "login_tokens:";

    /**
     * 登录用户 pr key
     */
    public static final String LOGIN_PR_KEY = "pr_tokens:";

    /**
     * 账号激活 redis key
     */
    public static final String ACCOUNT_ACTIVATION_KEY = "activation_tokens:";

    /**
     * 质疑编码 redis key
     */
    public static final String DOUBT_CODE = "doubt_code:";

    /**
     * 防重提交 redis key
     */
    public static final String REPEAT_SUBMIT_KEY = "repeat_submit:";

    /**
     * 验证码有效期（分钟）
     */
    public static final Integer CAPTCHA_EXPIRATION = 60;

    /**
     * 激活码有效期（分钟）
     */
    public static final Integer ACTIVATION_CODE = 1440;

    /**
     * 令牌
     */
    public static final String TOKEN = "token";

    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 令牌前缀
     */
    public static final String LOGIN_USER_KEY = "login_user_key";

    /**
     * 用户ID
     */
    public static final String JWT_USERID = "userid";

    /**
     * 用户名称
     */
    public static final String JWT_USERNAME = "sub";

    /**
     * 用户头像
     */
    public static final String JWT_AVATAR = "avatar";

    /**
     * 创建时间
     */
    public static final String JWT_CREATED = "created";

    /**
     * 用户权限
     */
    public static final String JWT_AUTHORITIES = "authorities";

    /**
     * 参数管理 cache key
     */
    public static final String SYS_CONFIG_KEY = "sys_config:";

    /**
     * 字典管理 cache key
     */
    public static final String SYS_DICT_KEY = "sys_dict:";

    /**
     * 资源映射路径 前缀
     */
    public static final String RESOURCE_PREFIX = "/profile";

    /**
     * 分隔符
     */
    public final static String DELIMITER = "/";
    /**
     * 冒号
     */
    public final static String COLON = ":";
    /**
     * 连接符
     */
    public final static String JOINER = "-";

    /**
     * 逗号
     */
    public final static String COMMA = ",";

    /**
     * 括号
     */
    public final static String BRACKETS = "(";

    /**
     * 反括号
     */
    public final static String ANTI_BRACKET = ")";

    /**
     * 0
     */
    public final static int ZERO = 0;

    /**
     * 1
     */
    public final static int ONE = 1;

    /**
     * 2
     */
    public final static int TWO = 2;

    /**
     * 3
     */
    public final static int THREE = 3;

    /**
     * 4
     */
    public final static int FOUR = 4;

    /**
     * 5
     */
    public final static int FIVE = 5;

    /**
     * 6
     */
    public final static int SIX = 6;

    /**
     * 7
     */
    public final static int SEVEN = 7;

    /**
     * 8
     */
    public final static int EIGHT = 8;

    /**
     * 9
     */
    public final static int NINE = 9;
    /**
     * 10
     */
    public final static int TEN = 10;
    /**
     * 11
     */
    public final static int ELEVEM = 11;
    /**
     * 12
     */
    public final static int TWELVE = 12;
    /**
     * 13
     */
    public final static int THIRTEEN = 13;
    /**
     * 14
     */
    public final static int FOURTEEN = 14;
    /**
     * 15
     */
    public final static int FIFTEN = 15;

    /**
     * 20
     */
    public final static int TWENTY = 20;

    /**
     * win 系统
     */
    public static final String WIN = "win";

    /**
     * mac 系统
     */
    public static final String MAC = "mac";

    /**
     * N/A
     */
    public static final String NAN = "N/A";

    /**
     * 字典国家/地区
     */
    public static final String COUNTRY = "state_region";

    /**
     * 药品信息
     */
    public static final String DRUG_MESSAGE="药品信息";

    /**
     * 性别
     */
    public static final String SEX="性别";

    /**
     * 规格单位
     */
    public static final String SPECS_UNIT="片(支)/";

    /**
     * 中心实验室检测结果用途:0-无检测 1-预筛选  2-筛选 3-分层因素
     */
    public static final int CHECK_RESULT_PURPOSE_NO=0;
    public static final int CHECK_RESULT_PURPOSE_PRESCREEN=1;
    public static final int CHECK_RESULT_PURPOSE_SCREEN=2;
    public static final int CHECK_RESULT_PURPOSE_FACTOR=3;


}
