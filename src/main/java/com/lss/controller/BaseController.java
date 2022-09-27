package com.lss.controller;

import com.lss.domain.data.AjaxResult;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {
    public  static AjaxResult success(Object data){
        return new AjaxResult(200,"操作成功",data);
    }
    public  static AjaxResult success(String msg,Object data){
        return new AjaxResult(200,msg,data);
    }
    public  static AjaxResult success(){
        return new AjaxResult(200,"操作成功",null);
    }
    public  static AjaxResult error(Object data){
        return new AjaxResult(500,"操作失败",data);
    }
    public  static AjaxResult error(String msg,Object data){
        return new AjaxResult(500,msg,data);
    }
    public  static AjaxResult error(){
        return new AjaxResult(500,"操作失败",null);
    }
}
