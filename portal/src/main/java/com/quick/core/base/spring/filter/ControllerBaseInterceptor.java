/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.quick.core.base.spring.filter;

import com.quick.core.base.SysBaseController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 通用系统拦截
 * @author Administrator
 */
public class ControllerBaseInterceptor extends HandlerInterceptorAdapter {
    
    @Override
    public boolean preHandle(HttpServletRequest request,
                    HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            Object object = ((HandlerMethod) handler).getBean();
            if(object instanceof SysBaseController) {
                ((SysBaseController) object).init(request, response, handler);
            }
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request,
                    HttpServletResponse response, Object handler,
                    ModelAndView modelAndView) throws Exception {
        if (handler instanceof HandlerMethod) {
            Object object = ((HandlerMethod) handler).getBean();
            if(object instanceof SysBaseController) {
                ((SysBaseController) object).actionAfter(request, response, handler, modelAndView);
            }
        }
        super.postHandle(request, response, handler, modelAndView);
    }    
}
