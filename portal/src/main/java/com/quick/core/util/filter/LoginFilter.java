/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.quick.core.util.filter;

import com.quick.core.base.AppResource;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Administrator
 */
public class LoginFilter extends HttpServlet implements Filter {

    private FilterConfig filterConfig;

    public void doFilter(ServletRequest arg0, ServletResponse arg1,
                    FilterChain arg2) throws IOException, ServletException {
        HttpServletRequest request = ((HttpServletRequest) arg0);
        Boolean b = false;
        String url = request.getRequestURI();
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            Cookie c = cookies[i];
            if(c.getName().equals(AppResource.COOKIE_LOGINER)){
                arg1.setCharacterEncoding("UTF-8");
                PrintWriter out = arg1.getWriter();
                String outString = "<script language=javascript>alert('您还没登录 , 请登录!!');javascript:location='"+((HttpServletRequest) arg0).getContextPath()+"/login.jsp';</script>";
                out.print(outString);
                out.flush();
                out.close();
            } else {
                arg2.doFilter(arg0, arg1);
            }
        }
    }

    public void init(FilterConfig arg0) throws ServletException {
        this.filterConfig = arg0;
    }

}