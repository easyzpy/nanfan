package com.randing.common.utils;

import com.randing.common.core.domain.model.LoginUser;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 安全服务工具类
 * 
 * @author randing
 */
public class SecurityUtils
{
    /**
     * 获取用户账户
     **/
//    public static String getUsername()
//    {
//        try
//        {
//            return getLoginUser().getUsername();
//        }
//        catch (Exception e)
//        {
//            throw new AccountExpiredException("获取用户账户异常");
//        }
//    }

    /**
     * 获取用户
     **/
    public static LoginUser getLoginUser()
    {
        try
        {
            return (LoginUser) getAuthentication().getPrincipal();
        }
        catch (Exception e)
        {
            throw new AccountExpiredException("获取用户信息异常");
        }
    }

    /**
     * 获取用户,不抛出异常
     **/
    public static LoginUser getLoginUser_NE()
    {
        try
        {
            return (LoginUser) getAuthentication().getPrincipal();
        }
        catch (Exception e)
        {
            return null;
        }
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication()
    {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword 真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    /**
     * 是否为管理员
     * 
     * @param userType 用户 类型
     * @return 结果
     */
    public static Integer isAdmin(Long id, Integer userType) {
        if (id != null && 1L == id && userType != null && 0 == userType) {
            return 0;
        } else if (userType != null && 0 == userType) {
            return 1;
        } else {
            return 2;
        }
    }

}
