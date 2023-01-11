package com.randing.framework.web.exception;

import com.randing.common.constant.HttpStatus;
import com.randing.common.core.domain.AjaxResult;
import com.randing.common.exception.BaseException;
import com.randing.common.exception.PrIdException;
import com.randing.common.exception.RandomSubjectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import javax.security.auth.login.AccountExpiredException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * 全局异常处理器
 * 
 * @author randing
 */
@RestControllerAdvice
public class GlobalExceptionHandler
{
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public static final String SYSTEM_ERROR_MSG = "系统异常，请联系平台管理员";

    /**
     * 业务异常
     */
    @ExceptionHandler(BaseException.class)
    public AjaxResult baseException(BaseException e)
    {
        String msg = e.getMessage();
        log.error(msg, e);
        if(msg.contains("Data too long"))
        {
            msg ="输入的数据过长，请修改";
        }
        return AjaxResult.error(msg);
    }

    /**
     * 随机受试者异常
     */
    @ExceptionHandler(RandomSubjectException.class)
    public AjaxResult randomSubjectException(RandomSubjectException e)
    {
        log.error(e.getMessage(), e);
        return AjaxResult.error(HttpStatus.RANDOM_SUBJECT_ERROR,e.getMessage());
    }

    /**
     * 项目id异常
     */
    @ExceptionHandler(PrIdException.class)
    public AjaxResult prIdException(PrIdException e)
    {
        log.error(e.getMessage(), e);
        return AjaxResult.error(HttpStatus.PR_ID_ERROR,e.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public AjaxResult handlerNoFoundException(Exception e)
    {
        log.error(e.getMessage(), e);
        return AjaxResult.error(HttpStatus.NOT_FOUND, "路径不存在，请检查路径是否正确");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public AjaxResult handleAuthorizationException(AccessDeniedException e)
    {
        log.error(e.getMessage());
        return AjaxResult.error(HttpStatus.FORBIDDEN, "没有权限，请联系管理员授权");
    }

    @ExceptionHandler(AccountExpiredException.class)
    public AjaxResult handleAccountExpiredException(AccountExpiredException e)
    {
        log.error(e.getMessage(), e);
        return AjaxResult.error(HttpStatus.TOKEN_TIMEOUT,"登录超时，请重新登录");
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public AjaxResult handleUsernameNotFoundException(UsernameNotFoundException e)
    {
        log.error(e.getMessage(), e);
        return AjaxResult.error(e.getMessage());
    }

    /**
     * 未处理的，不应该出现的异常。统一返回系统繁忙，请稍后再试
     *
     * @param e 异常对象
     * @return
     */
    @ExceptionHandler({Exception.class})
    public AjaxResult exceptionHandler(Exception e) {
        log.error("[系统繁忙]", e);
        return AjaxResult.error(SYSTEM_ERROR_MSG, e.getMessage());
    }

    /**
     * 校验框架异常处理
     * 针对RequestParameter
     *
     * @param e
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public AjaxResult constraintViolationException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        ConstraintViolation<?> next = constraintViolations.iterator().next();
        log.error("[参数校验不通过]{}", e.getMessage());
        return AjaxResult.error(next.getMessage(), null);
    }


    /**
     * 校验框架异常处理
     * 针对RequestBody
     *
     * @param e
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public AjaxResult constraintViolationException(MethodArgumentNotValidException e) {
        log.error("[参数校验不通过]{}", e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return AjaxResult.error(e.getBindingResult().getAllErrors().get(0).getDefaultMessage(), null);
    }

    /**
     * 定时任务异常处理     *
     *
     * @param e
     */
//    @ExceptionHandler(SchedulerException.class)
//    public AjaxResult schedulerException(SchedulerException e) {
//        log.error("[定时任务异常]{}", e.getMessage());
//        return AjaxResult.error(e.getMessage(), null);
//    }
}
