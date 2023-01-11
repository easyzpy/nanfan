package com.randing.web.controller;



import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Leen
 * @since 2021-06-07
 */
@RestController
@RequestMapping("/system/user")
@Api(value="用户controller",tags={"用户操作接口"})
public class SysUserController {

    @RequestMapping("hello")
    public String hello() {
        return "hello";
    }
}

