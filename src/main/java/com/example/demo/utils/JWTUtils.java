package com.example.demo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.javaBean.User_login;
import org.apache.commons.lang.StringUtils;

import java.util.Calendar;

/**
 * @author admin
 */
public class JWTUtils {

    /**
     * 获取token
     * @param u user
     * @return token
     */
    public static String getToken(User_login u) {
        Calendar instance = Calendar.getInstance();
        //默认令牌过期时间7天
        instance.add(Calendar.DATE, 7);

        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("userId", u.getId())
                .withClaim("username", u.getUsername());

        return builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(u.getPassword()));
    }

    /**
     * 验证token合法性 成功返回token
     */
    public static DecodedJWT verify(String token) throws Exception {
        if(StringUtils.isEmpty(token)){
            System.out.println(token);
            throw new Exception("token不能为空1");
        }

        //获取登录用户真正的密码假如数据库查出来的是123456
        String password = "admin";
        JWTVerifier build = JWT.require(Algorithm.HMAC256(password)).build();
        return build.verify(token);
    }
//    public static Claims parseToken(String token, String secret){
//        if(secret==null){
//            return null;
//        }
//        return Jwts.parser().setSigningKey(secret)
//                .parseClaimsJws(token).getBody();
//    }

   /* public static void main(String[] args) {
        DecodedJWT verify = verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MTcxMDg1MDAsInVzZXJuYW1lIjoiYWRtaW4ifQ.geBEtpluViRUg66_P7ZisN3I_d4e32Wms8mFoBYM5f0");
        System.out.println(verify.getClaim("password").asString());
    }*/
}

