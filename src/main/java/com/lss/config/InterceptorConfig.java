package com.lss.config;

import com.lss.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    // 调用这个方法为springboot添加一个拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        registry.addInterceptor(new AuthInterceptor())
                // 添加要拦截的请求，/**指的拦截所有的请求
                .addPathPatterns("/**")
                // 配置不需要拦截的请求
                .excludePathPatterns("/user/login","/user/register","/file/**","/upload/**");
    }
}
