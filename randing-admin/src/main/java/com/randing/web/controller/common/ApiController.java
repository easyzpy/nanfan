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
import com.randing.system.service.impl.DeanSimpleUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Wrapper;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api("通用接口")
//@RequestMapping("/")
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
    @Autowired
    private DeanSimpleUtil deanSimpleUtil;

    @GetMapping("ping")
    public String ping() {
        return "ping";
    }

    @GetMapping("token/getToken")
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

        User userByPhone = getUserByPhone(userInfo.getData());
        com.randing.common.utils.jwt.User user = new com.randing.common.utils.jwt.User();
        BeanUtils.copyProperties(userByPhone, user);
        user.setContactPhoneMac(user.getContactPhone());
        user.setContactPhone(userInfo.getData().getPhone());
        //生成token
        JwtUser jwtUser = new JwtUser();
//        jwtUser.setYzbToken(accessToken);
//        jwtUser.setRefreshKey(refreshKey);
//        jwtUser.setUserInfo(userInfo.getData());
        jwtUser.setNanUser(user);
        String token = tokenService.createToken(jwtUser);
        JwtResBean build = JwtResBean.builder().nanUser(user).token(token).build();
        return AjaxResult.success(build);

    }

    public User getUserByPhone(YzbUserInfo yzbUserInfo) {
        String encodePhone = deanSimpleUtil.sm4Enc(yzbUserInfo.getPhone());
        String phone = yzbUserInfo.getPhone();
        User user = userMapper.selectOne(Wrappers.lambdaQuery(User.class).eq(User::getContactPhone, encodePhone));
        if (user == null) {
            /*log.info("user is null");
            return null;*/
            //用户第一次登录南繁出现没有查到的情况
            user = initUserAndUserRole(yzbUserInfo);
        }
        List<UserRole> userRoles = userRoleMapper.selectList(Wrappers.lambdaQuery(UserRole.class)
                .eq(UserRole::getUserId, user.getId())
        );
        if (CollectionUtils.isEmpty(userRoles)) {
            throw new BaseException("此用户没有角色");
        }
        List<Role> roles = roleMapper.selectList(Wrappers.lambdaQuery(Role.class).select(Role::getId,Role::getRoleId,Role::getRoleType)
                .in(Role::getRoleId, userRoles.stream()
                        .map(UserRole::getRoleId).collect(Collectors.toList())));

        user.setRoles(roles);
        return user;
    }
    /*{
  "id":"6089819bb6b2ffd7786a1c81",
  "identityType":4,
  "isIdentity":1,
  "isVerified":0,
  "name":"万琪慧",
  "nickname":"",
//  "phone":"13635452958",
//  "photo":"//static.yzbays.cn/statics/authing-console/default-user-avatar.png",
//  "username":"13635452958"
}*/
    @Transactional
    public User initUserAndUserRole(YzbUserInfo yzbUserInfo) {
        String phone = deanSimpleUtil.sm4Enc(yzbUserInfo.getPhone());
        //目前loginName 和 contractPhone都使用phone
        User user = new User();
        user.setSubId(yzbUserInfo.getId());
        user.setSystemType("0");
        user.setContactPhone(phone);
//        user.setLoginName(yzbUserInfo.getUsername());
        user.setName(yzbUserInfo.getName());
        user.setUnit(null);
        user.setLoginName(phone);
        user.setCreateTime(LocalDateTime.now());


        userMapper.insert(user);

        //初始化角色
        String roleName = "申报用户";

        Role role = roleMapper.selectOne(Wrappers.lambdaQuery(Role.class).eq(Role::getRoleName, roleName));
        if (role == null) {
            throw new BaseException("没有可用角色赋予用户");
        }
        List<UserRole> userRoles = userRoleMapper.selectList(Wrappers.lambdaQuery(UserRole.class).eq(UserRole::getUserId, user.getId()).eq(UserRole::getRoleId, role.getRoleId()));
        UserRole userRole;
        if (userRoles.isEmpty()) {
            userRole = new UserRole();
            userRole.setUserId(user.getId().intValue());
            userRole.setRoleId(role.getRoleId());
            int insert = userRoleMapper.insert(userRole);
            if (insert < 0) {
                throw new BaseException("用户角色新增失败");
            }
        } else {
            userRole = userRoles.get(0);
        }
        user.setUserMac(deanSimpleUtil.getUserMac(String.valueOf(user.getId()), user.getName(), user.getLoginName(), user.getContactPhone(), user.getUnit()));
        user.setRoleMac(deanSimpleUtil.getRoleMac(String.valueOf(user.getId()), Collections.singletonList(userRole)));
        userMapper.updateById(user);
        return user;
    }

}
