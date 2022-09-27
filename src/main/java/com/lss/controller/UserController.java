package com.lss.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.lss.domain.vo.UserVo;
import com.lss.utils.EncriptionUtil;
import com.lss.utils.JWTUtil;
import com.lss.domain.data.AjaxResult;
import com.lss.domain.entity.User;
import com.lss.mapping.UserMapping;
import com.lss.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Qualifier("IUserService")
    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    public AjaxResult regist(User user) {
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //通过user_account字段查询
        wrapper.eq(StringUtils.isNotEmpty(user.getUserAccount()), "user_account", user.getUserAccount());
        User nowUser = userService.getOne(wrapper);
        if (nowUser != null) {
            return error("用户已存在", null);
        }
        user.setPassword(EncriptionUtil.encode(user.getPassword()));
        boolean b = userService.save(user);
        return b ? success("注册成功", user) : error("注册失败", null);
    }


    @PostMapping("/login")
    public AjaxResult login(@Validated User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotEmpty(user.getUserAccount()),"user_account", user.getUserAccount());
        User nowUser = userService.getOne(wrapper);
        if(nowUser==null) {
            return error("用户不存在", null);
        }
        // 调用封装的工具类来验证密码
        // 返回值为true表示验证成功
        if (!EncriptionUtil.validatePwd(user.getPassword(),nowUser.getPassword())){
            return error("密码错误",null);
        }
        String token = JWTUtil.createToken(nowUser);
        return success("登录成功",token);
    }

    /**
     * 获取用户信息
     * @param req
     * @return
     */
    @GetMapping
    public AjaxResult getUserInfo(HttpServletRequest req) {
        User nowUser = userService.getUserByReq(req);
        //过滤
        UserVo userVo= UserMapping.INSTANCE.toUserVo(nowUser);
        return  success(userVo);
    }

    /**
     * 加密
     * @param
     * @return
     */
    @PostMapping("/encryption")
    public AjaxResult setEncryption(String encryption,HttpServletRequest req) {
//        //获取用户id
//        String userId = JWTUtil.getUserId(req);
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        //通过userId字段查询
//        wrapper.eq("user_id", userId);
//        User nowUser = userService.getOne(wrapper);
        User nowUser = userService.getUserByReq(req);
        //调用工具类来加密
        nowUser.setEncryption(EncriptionUtil.encode(encryption));
        boolean b = userService.updateById(nowUser);
        return b ? success("加密成功", nowUser) : error("加密失败", null);
    }

    @PostMapping("/verify")
    public AjaxResult verifyEncryption(String encryption,HttpServletRequest req) {
        User nowUser = userService.getUserByReq(req);
        //验证
        boolean b = EncriptionUtil.validatePwd(encryption, nowUser.getEncryption());
        return b ? success("验证成功", nowUser) : error("验证失败", null);
    }
}
