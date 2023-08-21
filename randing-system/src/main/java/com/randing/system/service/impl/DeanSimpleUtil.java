package com.randing.system.service.impl;

import com.randing.system.domain.po.UserRole;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 国密加解密、mac、签名工具类
 * <p>
 * sm4加解密|sm4mac和验证
 * sm3Hmac|验证
 * sm2签名|验签
 * 时间戳
 * 随机数
 * </p>
 * @author tnb
 * @date   2023-08-08
 */
@Slf4j
@Component
public class DeanSimpleUtil {
	/*
	 * 接口调用地址（内网）
	 * 备注：
	 * 生产用内网地址
	 * 测试用外网地址
	 */
//	public  final String url = "http://10.100.1.192:8010/";
    @Value("${secret.url}")
	public String url ="http://10.100.1.192:8010/";
	/*
	 * 应用ID
	 * 南繁共享用地服务平台
	 * 10000012
	 */
    public  final String appId ="10000012";
    /*
     * sm3Hmac密钥索引[4-5]
     * 密钥索引
     */
    public  final String sm3HmacKeyNum = "5";
    /*
     * sm4加密密钥索引[6-10]
     * 密钥索引
     */
    public  final String sm4KeyNum = "6";
    /*
     * sm4-CBC-加解密
     * 偏移量
     * 16位字符串
     */
    public  final String iv = "89be473a054db679";

    public  final int RES_SUCCESS = 200;

     /**
      * SM4加密
      * SM4-CBC模式，使用默认16位IV
      * 使用默认密钥索引、默认应用ID
      * @return
      * {"status":200,"message":"SUCCESS","data":"HZjPvjF5TjargxvNboyJLw=="}
      */
    public  String sm4Enc(String data){
    	if(data==null || data.length()==0){
   		 return "";
    	}
        try {
            JSONObject params = new JSONObject();
            params.put("appId",appId);
            params.put("keyNum",sm4KeyNum);
            params.put("data",data);
            params.put("type",2); // 加密机类型 1-ECB  2-CBC
            params.put("iv",iv);// IV值 type为2时有效,必须为16字节长度
            StringBuffer result = post(url + "sm4Enc", params);
            return parseDataString(result,"sm4Enc");
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    /**
     * SM4解密
     * SM4-CBC模式，使用默认16位IV
     * 使用默认密钥索引、默认应用ID
     * @param data 密文
     * @return
     * {"status":200,"message":"SUCCESS","data":"***"}
     */
    public  String sm4Dec(String data){
    	if(data==null || data.length()==0){
      		 return "";
       	}
        try {
            JSONObject params = new JSONObject();
            params.put("appId",appId);
            params.put("keyNum",sm4KeyNum);
            params.put("data",data);
            params.put("type",2); // 加密机类型 1-ECB  2-CBC
            params.put("iv",iv); // IV值 type为2时有效,必须为16字节长度
            StringBuffer result = post(url + "sm4Dec", params);
            return parseDataString(result,"sm4Dec");
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 生成SM3HMAC
     * 使用默认MAC密钥索引、默认应用ID
     * @param data
     * @return
     * {"status":200,"message":"SUCCESS","data":"L127nhXW9ENf3uePxoeYiTwF1PqWmpkqtVbgnDwqbR8="}
     */
    public  String sm3Hmac(String data){
    	if(data==null || data.length()==0){
    		 return "";
     	}
        try {
            JSONObject params = new JSONObject();
            params.put("appId",appId);
            params.put("keyNum",sm3HmacKeyNum);
            params.put("data",data);
            StringBuffer result = post(url + "sm3Hmac", params);
            return parseDataString(result,"sm3Hmac");
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

   /**
    * 校验SM3HMAC
    * 使用默认MAC密钥索引、默认应用ID
    * @param data 原文
    * @param macData MAC值
    * @return
    * {"status":200,"message":"SUCCESS","data":true}
    */
    public  Boolean sm3HmacVerify(String data, String macData){
    	if(data==null || data.length()==0
    			||macData==null ||macData.length()==0){
    		 return true;
     	}
        try {
            JSONObject params = new JSONObject();
            params.put("appId",appId);
            params.put("keyNum",sm3HmacKeyNum);
            params.put("data",data);
            params.put("macData",macData);
            StringBuffer result = post(url + "sm3HmacVerify", params);
            return parseDataBoolean(result,"sm3HmacVerify");
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 解析-String
     * @param result
     * @param method
     * @return
     */
    private  String parseDataString(StringBuffer result, String method) {
    	log.info(method+":\t{}",result);
    	String js=null;
        if(result!=null && (js=result.toString()).length()>0){
        	JSONObject j = JSONObject.fromObject(js);
        	if(j.getInt("status")==RES_SUCCESS){
        		return j.getString("data");
        	}
        }
		return "";
	}
    /**
     * 解析-Boolean
     * @param result
     * @param method
     * @return
     */
    private  Boolean parseDataBoolean(StringBuffer result, String method) {
    	log.info(method+":\t{}",result);
    	String js=null;
    	if(result!=null && (js=result.toString()).length()>0){
        	JSONObject j = JSONObject.fromObject(js);
        	if(j.getInt("status")==RES_SUCCESS){
        		return j.getBoolean("data");
        	}
        }
		return false;
	}
    
    /**
     * post请求方法
     * @param address 访问地址
     * @param obj 参数
     * @return
     */
     public  StringBuffer post(String address, JSONObject obj){
         //创建连接
         try {
             URL url = new URL(address);
             HttpURLConnection connection = (HttpURLConnection) url.openConnection();
             connection.setDoOutput(true);
             connection.setDoInput(true);
             connection.setRequestMethod("POST");
             connection.setUseCaches(false);
             connection.setInstanceFollowRedirects(true);
             connection.setRequestProperty("Content-Type","application/json");
             connection.connect();
             //POST请求
             DataOutputStream out = new DataOutputStream(connection.getOutputStream());
             out.write(obj.toString().getBytes("utf-8"));
             out.flush();
             out.close();
             //读取响应
             BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
             String lines;
             StringBuffer sb = new StringBuffer("");
             while ((lines = reader.readLine()) != null) {
                 lines = new String(lines.getBytes(), "utf-8");
                 sb.append(lines);
             }
             reader.close();
             // 断开连接
             connection.disconnect();
             return sb;
         } catch (Exception e) {
             e.printStackTrace();
             return null;
         }
     }
     
//    public  void main(String[] args) {
//		
////		//1-sm4加解密测试
////		String data="张三!@#$%123456KKaaa00";
////		System.out.println("原文-data:\t"+data);
////		String encData = sm4Enc(data);
////		System.out.println("加密数值-encData:\t"+encData);
////		String data2 = sm4Dec(encData);
////		System.out.println("解密数值-data2:\t"+data2);
//		
////		//2-sm3Hmac测试
////		String data="张三!@#$%123456KKaaa00";
////		System.out.println("原文-data:\t"+data);
////		String macData = sm3Hmac(data);
////		System.out.println("mac数值-macData:\t"+macData);
////		Boolean verifyResult = sm3HmacVerify(data, macData);
////		System.out.println("验证结果-verifyResult:\t"+verifyResult);
//		
//	}
    //用户id（userId）+角色id（roleId）+角色id（roleId）......转sm3Hmac user_role userid正序排
    public static void main(String[] args) {
         //2	wlG9AfbC8n3mUaAV4RUQ/w==		陶乃兵	95d1fb9365c74ff28976ff125d6f49bb	wlG9AfbC8n3mUaAV4RUQ/w==			2021-07-22 08:00:00	2023-08-05 11:48:47		0	60853911a14a3e850ba77e49	jl8I/nynEi8XG6KxuRWILyZ5Ye3bzPs6rwsd2t9Ebr0=	EG49C2kCrWlfdbVl7Tjl+jBNIf3AV54SLWZnlzs0cxY=
        DeanSimpleUtil deanSimpleUtil = new DeanSimpleUtil();
        String loginName = deanSimpleUtil.sm4Dec("+at1r6Qwy9oZdjid+iS9jw==");
//        String s = deanSimpleUtil.sm4Dec("wlG9AfbC8n3mUaAV4RUQ/w==");
//9828
//10076
        String sm3mac = deanSimpleUtil.sm3Hmac("18964081098");
        Boolean verifyResult = deanSimpleUtil.sm3HmacVerify("425" + "92676310b3ff4c63a17690c881b00eef" + "4b6dbf30ee2249efb40ceb9458db658e" + "c6fd6c4949db4fb0bbd395bf8e630cfb", "5pKyaIwXyZrDTQiq3qAmPViW5ZG5lVD+uIqQEjXmyVs=");
        System.out.println(verifyResult);
//        loginName = "+at1r6Qwy9oZdjid+iS9jw==";
        verifyResult = deanSimpleUtil.sm3HmacVerify("425" + "姚超凡" + loginName+ loginName, "tTCoMUgt0Vq8hVP85SoTqIim+Pgt5K6mD8gDsEaUl2M=");
//        verifyResult = deanSimpleUtil.sm3HmacVerify("2" + "123" + "", "i0u46i7wO7VhXl7XMsEYItpoFz6tZMRnOph/tJuMhe4=");
        String s = deanSimpleUtil.sm3Hmac("425" + "姚超凡" + loginName+ loginName + "95d1fb9365c74ff28976ff125d6f49bb");
        String s1 = deanSimpleUtil.sm3Hmac("425" + "姚超凡" + "+at1r6Qwy9oZdjid+iS9jw=="+ "+at1r6Qwy9oZdjid+iS9jw==" + "95d1fb9365c74ff28976ff125d6f49bb");
        String s2 = deanSimpleUtil.sm3Hmac("425" + "姚超凡" + loginName+ loginName + "");
        String s3 = deanSimpleUtil.sm3Hmac("425" + "姚超凡" + "+at1r6Qwy9oZdjid+iS9jw=="+ "+at1r6Qwy9oZdjid+iS9jw==" + "");
        boolean b1 = deanSimpleUtil.sm3HmacVerify("425" + "姚超凡" + "+at1r6Qwy9oZdjid+iS9jw=="+ "+at1r6Qwy9oZdjid+iS9jw==" + "", "tTCoMUgt0Vq8hVP85SoTqIim+Pgt5K6mD8gDsEaUl2M=");
        System.out.println(s);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(b1);
        System.out.println(verifyResult);
//        deanSimpleUtil.sm3
    }

    /**
     * 根据pc逻辑 unitId不参与签名, 直接加在签名后面. 如果unitId为null 则为  tTCoMUgt0Vq8hVP85SoTqIim+Pgt5K6mD8gDsEaUl2M=null
     * @param userId
     * @param name
     * @param loginName 使用密文
     * @param contractPhone 使用密文
     * @return
     */
    public String getUserMac(String userId, String name, String loginName, String contractPhone, String unitId){
        return this.sm3Hmac(userId + name + loginName + contractPhone) + unitId;
    }

    /**
     * 如果roleid未空则返回空
     * @param userId
     * @param roleId
     * @return
     */
//    public String getRoleMac(String userId, String roleId){
//        if (roleId == null) {
//            return null;
//        }
//        return this.sm3Hmac(userId + roleId);
//    }
    /**
     * 如果roleid未空则返回空
     * @param userId
     * @param userRole
     * @return
     */
    public String getRoleMac(String userId, List<UserRole> userRole){
        if (userRole == null) {
            return null;
        }
        return this.sm3Hmac(userId + userRole.stream().sorted(Comparator.comparingLong(UserRole::getId)).map(UserRole::getRoleId).collect(Collectors.joining("")));
    }




}