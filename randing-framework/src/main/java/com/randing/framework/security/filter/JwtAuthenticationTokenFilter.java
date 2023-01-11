package com.randing.framework.security.filter;

import com.randing.common.TokenService;
import com.randing.common.core.domain.model.LoginUser;
import com.randing.common.exception.BaseException;
import com.randing.common.utils.SecurityUtils;
import com.randing.common.utils.StringUtils;
import com.randing.common.utils.jwt.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * token过滤器 验证token有效性
 * 
 * @author randing
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter
{
    @Autowired
    private TokenService tokenService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException
    {
//        LoginUser loginUser = tokenService.getLoginUser(request);
//        if (StringUtils.isNotNull(loginUser) && StringUtils.isNull(SecurityUtils.getAuthentication()))
//        {
//            tokenService.verifyToken(loginUser);
//            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
//            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//        }
//        else
//        {
//            String url = request.getRequestURI();
//            if(url.startsWith("/api") && !url.contains("/captchaImage") && !url.contains("api/login") &&
//               !url.contains("/userNameRetrieval") &&!url.contains("/pwdRetrieval") &&
//               !url.contains("/activateAccount") && !url.contains("/dictType/user_type") &&
//               !url.contains("/download") && !url.contains("/prSiteDrug/exportExcel") && !url.contains("/exportSitePDF") &&
//               !url.contains("/uploadFile") && !url.contains("/importTemplate") && !url.contains("/exportPDF") &&
//               !url.contains("/exportSiteExcel") && !url.contains("/export") && !url.contains("/export/prDoubt")
//               && !url.contains("/api/emailCaptcha")
//            )
//            {
//                // 全局异常@ControllerAdvice不能捕获拦截器Filter异常,
//                // @ControllerAdvice+@ExceptionHandler 只能拦截控制器Controller异常，
//                // Filter在Controller之前，Controller层的异常捕获，是无法捕获到还没有请求到Controller时发生的异常的
//                // Filter异常转发到controller,从而触发GlobalExceptionHandler异常处理
//                request.setAttribute("filter.error", new AccountExpiredException("用户登录已过期"));
//                request.getRequestDispatcher("/api/token/expired").forward(request, response);
//                return ;
//            }
//        }
        String url = request.getRequestURI();
        if (url.startsWith("/api/token/getToken")) {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(new JwtUser(), null, null);
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);
        } else {
            String authorization = request.getHeader("authorization");
            if (StringUtils.isEmpty(authorization)) {
                throw new BaseException("token为空");
            }
            JwtUser jwtUser = tokenService.parseToken(authorization);
            if (jwtUser.isExpired()) {
                throw new BaseException("token过期");
            }
            if (jwtUser.isExpiredHalf()) {
                String token = tokenService.createToken(jwtUser);
                response.addHeader("refresh-token", token);
            }

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(jwtUser, null, null);
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);
        }
    }
    static class MyUserDetails implements UserDetails {

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return null;
        }

        @Override
        public String getPassword() {
            return null;
        }

        @Override
        public String getUsername() {
            return "";
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }

}

