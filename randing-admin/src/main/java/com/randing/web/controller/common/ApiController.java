package com.randing.web.controller.common;

import com.randing.common.TokenService;
import com.randing.common.core.domain.AjaxResult;
import com.randing.common.exception.BaseException;
import com.randing.common.utils.StringUtils;
import com.randing.common.utils.iface.YzbApiUtils;
import com.randing.common.utils.iface.dto.GetTokenResDTO;
import com.randing.common.utils.iface.dto.YzbResDTO;
import com.randing.common.utils.iface.dto.YzbUserInfo;
import com.randing.common.utils.jwt.JwtUser;
import com.randing.system.mapper.RoleMapper;
import com.randing.system.mapper.UserMapper;
import com.randing.system.mapper.UserRoleMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Api("apicontroller")
@RequestMapping("/token")
public class ApiController {

    @Autowired
    private YzbApiUtils apiUtils;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;




    @GetMapping("getToken")
    public AjaxResult getToken(@RequestParam("userId")String userId, @RequestParam("code")String code) {
        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(code)) {
            throw new BaseException("参数错误");
        }
        YzbResDTO<GetTokenResDTO> resDTO = null;
        try {
            resDTO = apiUtils.getToken(code);
        } catch (Exception e) {
            throw new BaseException("token获取失败");
        }
        if (!resDTO.getCode().equals("200")) {
            throw new BaseException("token获取失败");
        }
        GetTokenResDTO resData = resDTO.getData();
        String accessToken = resData.getAccessToken();
        String refreshKey = resData.getRefreshKey();
        YzbResDTO<YzbUserInfo> userInfo = null;
        try {
            userInfo = apiUtils.getUserInfo(userId, accessToken);
        } catch (Exception e) {
            throw new BaseException("获取用户信息失败");
        }
        //生成token
        JwtUser jwtUser = new JwtUser();
        jwtUser.setYzbToken(accessToken);
        jwtUser.setRefreshKey(refreshKey);
        jwtUser.setUserInfo(userInfo.getData());
        String token = tokenService.createToken(jwtUser);

        return AjaxResult.success(token);

    }
}
