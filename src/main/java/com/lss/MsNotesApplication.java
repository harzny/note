package com.lss;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//配置扫描包的路径
@EnableScheduling //定时器
@MapperScan("com.lss.mapper")
public class MsNotesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsNotesApplication.class, args);
    }

}
