package com.lss.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lss.domain.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

public class JWTUtil {
    private  static final String SECRET="MS_Note";
    // token的过期时间
    private final static Integer TIME_OUT_DAY = 30;

    /**
     * 创建token
     * @param user
     * @return
     */
    public static String createToken(User user) {
        // 使用日历类创建一个时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,TIME_OUT_DAY);
        //创建一个token
        String token= JWT.create()
                //保存信息
                .withClaim("userId",user.getUserId())
                .withClaim("userAccount",user.getUserAccount())
                .withClaim("userName",user.getUserName())
                .withExpiresAt(calendar.getTime())
                //设置token的秘钥
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }

    /**
     * 验证token
     * @param token
     * @return
     */
    public static DecodedJWT verityToken(String token) {
        DecodedJWT jwt = null;
        try{
            jwt= JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
        }catch (Exception e){
            System.out.println("token验证失败");
        }
        return jwt;
    }

    public static String getUserId(HttpServletRequest req ) {
        String token = req.getHeader("Authorization");

        DecodedJWT jwt=JWTUtil.verityToken(token);
        //获取到用户的ID
        return jwt.getClaim("userId").asString();
    }

}
