package com.randing.web.controller.common;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.randing.common.TokenService;
import com.randing.common.core.domain.AjaxResult;
import com.randing.common.exception.BaseException;
import com.randing.common.utils.StringUtils;
import com.randing.common.utils.iface.YzbApiUtils;
import com.randing.common.utils.iface.dto.GetTokenResDTO;
import com.randing.common.utils.iface.dto.YzbResDTO;
import com.randing.common.utils.iface.dto.YzbUserInfo;
import com.randing.common.utils.jwt.JwtResBean;
import com.randing.common.utils.jwt.JwtUser;
import com.randing.system.domain.po.Role;
import com.randing.system.domain.po.User;
import com.randing.system.domain.po.UserRole;
import com.randing.system.mapper.RoleMapper;
import com.randing.system.mapper.UserMapper;
import com.randing.system.mapper.UserRoleMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Api("通用接口")
@RequestMapping("/token")
@Slf4j
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
    @ApiOperation(value = "获取token")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户id", name = "userId", required = true, paramType = "query"),
            @ApiImplicitParam(value = "code 通过跳转链接获取", name = "code", required = true, paramType = "query")
    })
    public AjaxResult<JwtResBean> getToken(@RequestParam("userId")String userId, @RequestParam("code")String code) {
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
        log.info("user origin phone :{}", userInfo.getData().getPhone());
//        userInfo.getData().setPhone("13635452958");
        User userByPhone = getUserByPhone(userInfo.getData().getPhone());
        com.randing.common.utils.jwt.User user = new com.randing.common.utils.jwt.User();
        BeanUtils.copyProperties(userByPhone, user);
        //生成token
        JwtUser jwtUser = new JwtUser();
        jwtUser.setYzbToken(accessToken);
        jwtUser.setRefreshKey(refreshKey);
        jwtUser.setUserInfo(userInfo.getData());
        jwtUser.setNanUser(user);
        String token = tokenService.createToken(jwtUser);
        HashMap<String, Object> resMap = new HashMap<>();
        JwtResBean build = JwtResBean.builder().nanUser(user).token(token).build();
        return AjaxResult.success(build);

    }

    public User getUserByPhone(String phone) {
        User user = userMapper.selectOne(Wrappers.lambdaQuery(User.class).eq(User::getContactPhone, phone));
        if (user == null) {
            log.info("user is null");
            return null;
        }
        List<UserRole> userRoles = userRoleMapper.selectList(Wrappers.lambdaQuery(UserRole.class)
                .eq(UserRole::getUserId, user.getId())
        );
        if (CollectionUtils.isEmpty(userRoles)) {
            throw new BaseException("此用户竟然没有角色");
        }
        List<Role> roles = roleMapper.selectList(Wrappers.lambdaQuery(Role.class).in(Role::getRoleId, userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList())));

        user.setRoles(roles);
        return user;
    }
}
