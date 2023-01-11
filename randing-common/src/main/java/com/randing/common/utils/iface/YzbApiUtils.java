package com.randing.common.utils.iface;

import com.alibaba.fastjson.JSONObject;
import com.randing.common.TokenService;
import com.randing.common.exception.BaseException;
import com.randing.common.utils.iface.dto.GetTokenResDTO;
import com.randing.common.utils.iface.dto.YzbResDTO;
import com.randing.common.utils.iface.dto.YzbUserInfo;
import com.randing.common.utils.jwt.JwtUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class YzbApiUtils {

    @Value("${yzapi.hostname}")
    private String hostName = "https://api.test.yzbays.com";

    @Value("${yzapi.clientId}")
    private String clientId = "yzb-nfyd";

    @Value("${yzapi.clientSecret}")
    private String clientSecret = "20c7de12-3225-485b-b572-0d6421b48e62";

//    private String token;
//
//    private String refreshKey;

    public YzbResDTO<GetTokenResDTO> getToken(String code) {
        if (code == null) {
            throw new IllegalArgumentException("code is required");
        }
        LinkedMultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add("clientId", clientId);
        param.add("clientSecret", clientSecret);
        param.set("code", code);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<LinkedMultiValueMap<String, Object>> httpEntity = new HttpEntity<>(param, httpHeaders);
        /*多层嵌套json*/
        ParameterizedTypeReference<YzbResDTO<GetTokenResDTO>> typeRef = new ParameterizedTypeReference<YzbResDTO<GetTokenResDTO>>() {};
        ResponseEntity<YzbResDTO<GetTokenResDTO>> exchange = new RestTemplate().exchange(hostName + "/ext/api/getToken", HttpMethod.POST, httpEntity, typeRef);
        if (exchange.getStatusCode() != HttpStatus.OK) {
            log.info("getToken.getTokenResDTOResponseEntity:{}", JSONObject.toJSON(exchange));
        }

        YzbResDTO<GetTokenResDTO> body = exchange.getBody();
        return body;
    }

    public static void main(String[] args) {

        YzbApiUtils yzbApiUtils = new YzbApiUtils();
        YzbResDTO<GetTokenResDTO> resDTO = yzbApiUtils.getToken("291409");
        if (!resDTO.getCode().equals("200")) {
            throw new BaseException("token获取失败");
        }
        GetTokenResDTO resData = resDTO.getData();
        String accessToken = resData.getAccessToken();
        String refreshKey = resData.getRefreshKey();
        YzbResDTO<YzbUserInfo> userInfo = yzbApiUtils.getUserInfo("63aa9a0c179ecc4a908cb8a0", accessToken);
        //生成token
        TokenService tokenService = new TokenService();
        JwtUser jwtUser = new JwtUser();
        jwtUser.setYzbToken(accessToken);
        jwtUser.setRefreshKey(refreshKey);
        jwtUser.setUserInfo(userInfo.getData());
        String token = tokenService.createToken(jwtUser);
        System.out.println(token);
//        TokenService tokenService = new TokenService();
//        String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjp7Inl6YlRva2VuIjoiZXlKaGJHY2lPaUpJVXpJMU5pSXNJblI1Y0NJNklrcFhWQ0o5LmV5SndZWE56ZDI5eVpDSTZJakl3WXpka1pURXlMVE15TWpVdE5EZzFZaTFpTlRjeUxUQmtOalF5TVdJME9HVTJNaUlzSW1WNGNDSTZNVFkzTXpNME56a3hOQ3dpZFhObGNtNWhiV1VpT2lKNWVtSXRibVo1WkNKOS5vaVV0emktd25ERTNRVndBZEpfSXF3OENLNk5VNlVCenVmcEF0MllFS1FRIiwicmVmcmVzaEtleSI6ImUzYWJlN2VkNTY3YTRjZTNhNTZkNWQ2ODdhZTZjNDgzIiwidXNlckluZm8iOnsiaWQiOiI2M2FhOWEwYzE3OWVjYzRhOTA4Y2I4YTAiLCJuYW1lIjpudWxsLCJ1c2VybmFtZSI6InlpZHVuX3A0YTFqbiIsIm5pY2tuYW1lIjpudWxsLCJwaG90byI6Ii8vc3RhdGljLnl6YmF5cy5jbi9zdGF0aWNzL2F1dGhpbmctY29uc29sZS9kZWZhdWx0LXVzZXItYXZhdGFyLnBuZyIsInBob25lIjoiMTg1MTYyOTM1MTciLCJvcmdJZCI6bnVsbCwib3JnTmFtZSI6bnVsbCwiaWRlbnRpdHlUeXBlIjo0LCJpc0lkZW50aXR5IjoxLCJpc1ZlcmlmaWVkIjowfSwiaWF0Ijp7ImRheU9mV2VlayI6IlRVRVNEQVkiLCJkYXlPZlllYXIiOjEwLCJtb250aCI6IkpBTlVBUlkiLCJ5ZWFyIjoyMDIzLCJkYXlPZk1vbnRoIjoxMCwiaG91ciI6MTgsIm1pbnV0ZSI6NDcsIm1vbnRoVmFsdWUiOjEsIm5hbm8iOjIyNDAwMDAwMCwic2Vjb25kIjo1LCJjaHJvbm9sb2d5Ijp7ImlkIjoiSVNPIiwiY2FsZW5kYXJUeXBlIjoiaXNvODYwMSJ9fSwiZXhwIjp7ImRheU9mV2VlayI6IldFRE5FU0RBWSIsImRheU9mWWVhciI6MTEsIm1vbnRoIjoiSkFOVUFSWSIsInllYXIiOjIwMjMsImRheU9mTW9udGgiOjExLCJob3VyIjoxOCwibWludXRlIjo0NywibW9udGhWYWx1ZSI6MSwibmFubyI6MjI0MDAwMDAwLCJzZWNvbmQiOjUsImNocm9ub2xvZ3kiOnsiaWQiOiJJU08iLCJjYWxlbmRhclR5cGUiOiJpc284NjAxIn19LCJleHBpcmVkIjpmYWxzZSwiZXhwaXJlZEhhbGYiOmZhbHNlfX0.FfC2Bj2bYO4e1Rxv0K1_iKo9su4BuA0YBRFTvgHMDD0";
        JwtUser jwtUser1 = tokenService.parseToken(token);
        System.out.println(jwtUser1);

        System.out.println();
    }

    public GetTokenResDTO refreshToken(String token) {

//        if (this.refreshKey == null) {
//            throw new IllegalArgumentException("refreshKey is null");
//        }
        LinkedMultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add("clientId", clientId);
        param.add("clientSecret", clientSecret);
//        param.set("refreshKey", this.refreshKey);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<LinkedMultiValueMap<String, Object>> httpEntity = new HttpEntity<>(param, httpHeaders);
        ResponseEntity<GetTokenResDTO> getTokenResDTOResponseEntity = new RestTemplate().postForEntity(hostName + "/ext/api/refreshToken", httpEntity, GetTokenResDTO.class);
        if (getTokenResDTOResponseEntity.getStatusCode() != HttpStatus.OK) {
            log.info("refreshToken.getTokenResDTOResponseEntity:{}", JSONObject.toJSON(getTokenResDTOResponseEntity));
            return null;
        }
        GetTokenResDTO body = getTokenResDTOResponseEntity.getBody();

        return body;
    }
        /*特殊说明：所有请求方法，header中需要携带开放平台获取的accessToken（获取accessToken方法参考授权接口）；
        accessToken值放在请求Header里，请求头名称是 x-yzb-token
        *//*
    *//**
     * 获取用户信息
     * @param userId
     * @return
     */
    public YzbResDTO<YzbUserInfo> getUserInfo(String userId, String token) {
        if (StringUtils.isBlank(userId)) {
            throw new IllegalArgumentException("userId is required");
        }
        if (token == null) {
            throw new IllegalStateException("token is null, please getToken");
        }

        LinkedMultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add("clientId", clientId);
        param.add("clientSecret", clientSecret);
        param.set("userId", userId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("x-yzb-token", token);
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<LinkedMultiValueMap<String, Object>> httpEntity = new HttpEntity<>(param, httpHeaders);
        ParameterizedTypeReference<YzbResDTO<YzbUserInfo>> parameterizedTypeReference = new ParameterizedTypeReference<YzbResDTO<YzbUserInfo>>() {
        };
        ResponseEntity<YzbResDTO<YzbUserInfo>> exchange = new RestTemplate().exchange(hostName + "/ext/enterprise/user/info/get", HttpMethod.POST, httpEntity, parameterizedTypeReference);
        if (exchange.getStatusCode() != HttpStatus.OK || exchange.getBody() == null || !"200".equals(exchange.getBody().getCode())) {
            log.info("refreshToken.getTokenResDTOResponseEntity:{}", JSONObject.toJSON(exchange));
            return null;
        }
        YzbResDTO<YzbUserInfo> body = exchange.getBody();
        return body;
    }




}
