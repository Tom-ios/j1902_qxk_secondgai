package com.qf.j1902.intercepter;

import com.qf.j1902.controller.BaseController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.invoke.MethodHandle;

import static sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl.ThreadStateMap.Byte1.other;

/**
 * Created by Qxk on 2019/6/6.
 */
public class ZhongCintercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);
        if(handler instanceof HandlerMethod){
            HandlerMethod method = (HandlerMethod) handler;
            Object bean = method.getBean();
            if(bean instanceof BaseController){
                HttpSession session = request.getSession(true);
                Object other = session.getAttribute("other");
                System.out.println(other);
                if(other==null){
                    request.getRequestDispatcher("/dengLu").forward(request,response);
                }

            }else {


            }


        }else {

        }


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
