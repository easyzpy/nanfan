package com.randing.web.controller.common;

import com.randing.common.core.domain.AjaxResult;
import com.randing.common.exception.BaseException;
import com.randing.common.utils.LoginUser;
import com.randing.system.domain.po.User;
import com.randing.system.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api(value = "用户信息")
public class UserController {

    @Autowired
    private IUserService userService;

//    @Autowired
//    private

    /**
     *  获取用户信息
     *  使用: unitid为空 无法填写申请
     * @return
     */
    @GetMapping("/user/getUserInfo")
    @ApiOperation("获取用户信息 若unitid为空 无法填写申请")
    public AjaxResult<User> checkUnit() {
        Long loginUserId = LoginUser.getLoginUserId();
        User byId = userService.getById(loginUserId);
        return AjaxResult.success(byId);
    }

    @PostMapping("/user/bindUnit")
    @ApiOperation("用户绑定企业信息")
    public AjaxResult bindUnit(@RequestBody User user) {
        if (user == null || StringUtils.isEmpty(user.getUnit())) {
            throw new BaseException("参数错误");
        }
    }
}
