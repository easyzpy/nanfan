package com.randing.common.exception;

import com.randing.common.utils.MessageUtils;
import com.randing.common.utils.StringUtils;

/**
 * 基础异常
 * 
 * @author randing
 */
public class PrIdException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    /**
     * 所属模块
     */
    private String module;

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误码对应的参数
     */
    private Object[] args;

    /**
     * 错误消息
     */
    private String defaultMessage;

    public PrIdException(String module, String code, Object[] args, String defaultMessage)
    {
        this.module = module;
        this.code = code;
        this.args = args;
        this.defaultMessage = defaultMessage;
    }

    public PrIdException(String module, String code, Object[] args)
    {
        this(module, code, args, null);
    }

    public PrIdException(String defaultMessage, String code)
    {
        this(null, code, null, defaultMessage);
    }

    public PrIdException(String code, Object[] args)
    {
        this(null, code, args, null);
    }

    public PrIdException(String defaultMessage)
    {
        this(null, null, null, defaultMessage);
    }

    @Override
    public String getMessage()
    {
        String message = null;
        if (!StringUtils.isEmpty(code))
        {
            message = MessageUtils.message(code, args);
        }
        if (message == null)
        {
            message = defaultMessage;
        }
        return message;
    }

    public String getModule()
    {
        return module;
    }

    public String getCode()
    {
        return code;
    }

    public Object[] getArgs()
    {
        return args;
    }

    public String getDefaultMessage()
    {
        return defaultMessage;
    }
}
