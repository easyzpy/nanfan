package com.randing.common;

import com.alibaba.fastjson.JSONObject;
import com.randing.common.utils.jwt.JwtUser;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * token验证处理
 *
 * @author randing
 */
@Component
public class TokenService {
    // 令牌自定义标识
//    @Value("${token.header}")
//    private String header;

    private final String Bearer = "Bearer ";
    // 令牌秘钥
    @Value("${token.secret}")
    private String secret ;

    // 令牌有效期（默认30分钟）
    @Value("${token.expireTime}")
    private int expireTime;

    private static final String ClaimsKey = "user";


    public String createToken(JwtUser jwtUser) {
        if (jwtUser == null) {
            throw new IllegalArgumentException("createToken param illegal");
        }
        LocalDateTime now = LocalDateTime.now();
        jwtUser.setIat(now);
        jwtUser.setExp(now.plusSeconds(expireTime));
        Map<String, Object> claims = new HashMap<>();

        claims.put(ClaimsKey, JSONObject.toJSONString(jwtUser));
        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secret).compact();
        return Bearer + token;
    }

    public JwtUser parseToken(String token) {
        String s = token.replaceFirst(Bearer, "");
        Jwt jwt = Jwts.parser().setSigningKey(secret).parse(s);
        Map<String, Object> claims = (Map<String, Object>) jwt.getBody();
        Object o = claims.get(ClaimsKey);
        JwtUser jwtUser = JSONObject.parseObject((String)o, JwtUser.class);
        return jwtUser;

    }

}
