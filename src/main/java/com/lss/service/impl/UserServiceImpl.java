package com.lss.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lss.utils.JWTUtil;
import com.lss.domain.entity.User;
import com.lss.mapper.UserMapper;
import com.lss.service.IUserService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Override
    public User getUserByReq(HttpServletRequest req) {
        //获取用户id
        String userId = JWTUtil.getUserId(req);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //通过userId字段查询
        wrapper.eq("user_id", userId);
        return this.getOne(wrapper);
    }
}
