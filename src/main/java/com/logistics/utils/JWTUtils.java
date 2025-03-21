package com.logistics.utils;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {
   public static String createJWT(String userId,String password) {
       Map<String, Object> claims = new HashMap<String, Object>();
       claims.put("userId", userId);
       claims.put("expire_time", System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 15);
       return JWTUtil.createToken(claims,password.getBytes());
   }
   public static String parseJWT(String token) {
       JWT jwt = JWTUtil.parseToken(token);
       return(String) jwt.getPayload("userId");
   }
}
