package com.lss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lss.domain.entity.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public interface IUserService extends IService<User> {
    User getUserByReq(HttpServletRequest req);

}
