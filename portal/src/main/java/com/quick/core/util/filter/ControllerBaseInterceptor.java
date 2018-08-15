/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.quick.core.util.filter;

import com.quick.core.base.SysBaseController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quick.portal.web.login.WebLoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 通用系统拦截
 * @author Administrator
 */
public class ControllerBaseInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Override
    public boolean preHandle(HttpServletRequest request,
                    HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            Object object = ((HandlerMethod) handler).getBean();
            if(object instanceof SysBaseController) {
                if (!checkValidity(request, response))
                    return false;
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

    private boolean checkValidity(HttpServletRequest request, HttpServletResponse response) throws Exception {
        WebLoginUser webLoginUser = new WebLoginUser().loadSession(request, response);

        if (webLoginUser.getRequestSerial() == 0){
            logger.warn("Request for {} with request serial = 0, possibly system internal error.", request.getRequestURI());
            response.sendRedirect(request.getContextPath());
            return false;
        }
        return true;
    }
}
