package com.randing.common.utils;

import com.randing.common.utils.jwt.JwtUser;
import com.randing.common.utils.jwt.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.stream.Collectors;

public class LoginUser {
    public static Long getLoginUserId() {
        JwtUser user = getUser();
        List<Role> collect = user.getNanUser().getRoles().stream().filter(f -> f.getRoleType() == 5).collect(Collectors.toList());
        if (!collect.isEmpty()) {
            return null;
        }

        return user.getNanUser().getId();
//        Long nanId = jwtUser.getNanUser().getId();
//        return nanId;
    }
    public static JwtUser getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
        return jwtUser;
    }

    public static Long getId() {
        return getUser().getNanUser().getId();
    }

}
