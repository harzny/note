package com.lss.interceptor;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.lss.utils.JWTUtil;
import com.lss.domain.data.AjaxResult;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setCharacterEncoding("utf8");
        response.setContentType("text/plain;charset=utf8");
//        从请求头获取token
        String token = request.getHeader("Authorization");
        if(StringUtils.isEmpty(token)){
            // 优化返回的结果
            // 使用了fastjson依赖
            response.getWriter().write(JSON.toJSONString(new AjaxResult(401,"token不存在",null)));
            return false;
        }
        DecodedJWT verityToken= JWTUtil.verityToken(token);
        if (verityToken==null){
            response.getWriter().write("token验证失败");
        }
        return true;
    }
}
