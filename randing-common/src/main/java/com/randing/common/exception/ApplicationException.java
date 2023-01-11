package com.randing.common.exception;

/**
 * 框架系统异常->即非业务逻辑异常
 * 该异常返回给前端的提示默认是“系统繁忙，请稍后再试”
 *
 */
public class ApplicationException extends RuntimeException {
    private ApplicationException(String msg) {
        super(msg);
    }

    public static ApplicationException ae(String msg) {
        return new ApplicationException(msg);
    }

    public static ApplicationException ae(String msg, String... agrs) {
        return new ApplicationException(String.format(msg, agrs));
    }

}
