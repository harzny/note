package com.lss.utils;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@MapperScan("com.lss.mapper")
public class EncriptionUtil {
    private static final String SALT = "asd_SAD";
    /**
     * 加密
     * @param password
     * @return
     */
    public static String encode(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // 进行密码加密
        return encoder.encode(password+SALT);
    }
    /**
     * 验证密码，true表示验证成功
     * @param password 用户数据输入的密码
     * @param encodePassword 查询出来的密码
     * @return
     */
    public static boolean validatePwd(String password,String encodePassword){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // 进行密码验证，数据库查出来的密码和用户输入的密码进行匹配
        return encoder.matches(password+SALT,encodePassword);
    }
}