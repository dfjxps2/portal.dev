/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.seaboxdata.portal.mobile.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.quick.core.base.SysApiController;

/**
 * 通用系统拦截
 * @author Administrator
 *   
 */
public class MobileBaseInterceptor extends HandlerInterceptorAdapter {
	
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Override
    public boolean preHandle(HttpServletRequest request,
                    HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            Object object = ((HandlerMethod) handler).getBean();
            if(object instanceof SysApiController) {

                ((SysApiController) object).init(request, response, handler);
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
            if(object instanceof SysApiController) {
                ((SysApiController) object).actionAfter(request, response, handler, modelAndView);
            }
        }
        super.postHandle(request, response, handler, modelAndView);
    }


}
