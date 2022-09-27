package com.lss.handler;

import com.lss.domain.data.AjaxResult;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 做validation的异常处理
     * 引入BindException是Validated
     */
    //通过注解的形式捕获BindException的异常
    @ExceptionHandler(BindException.class)
    public AjaxResult handlerBindException(BindException e) {
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return new AjaxResult(400, message, null);
    }
}
