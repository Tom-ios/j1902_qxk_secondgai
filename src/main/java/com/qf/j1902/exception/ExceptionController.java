package com.qf.j1902.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jeffrey on 2019/6/11.
 */

@ControllerAdvice//标识为控制层拦截器
public class ExceptionController {
    @ExceptionHandler(value=Exception.class)//标识需要拦截的异常
    public  String  defaultErrorHandler(HttpServletRequest request, Exception e){

      return "yichangshow";
    }
}
