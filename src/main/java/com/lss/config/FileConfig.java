package com.lss.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FileConfig implements WebMvcConfigurer {
    //如果请求的地址是/upload/开头那么就清求后面这个地址
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        //对图片地址做一个映射
        registry.addResourceHandler("/upload/**").addResourceLocations("file:D:\\file\\upload\\images");
    }
}