/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.quick.core.util.filter;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;

import com.quick.core.util.common.QCommon;
import com.quick.core.util.common.QCookie;
import com.quick.portal.security.authority.metric.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Administrator
 * loginCasFilter
 */
public class LoginFilter extends HttpServlet implements Filter {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private final static String COOKIE_USER_ID = "sbd.user_id";

    private final static ArrayList<String> prefixIgnores = new ArrayList<>();

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String user_id = QCookie.getValue(req, COOKIE_USER_ID);
        String url = req.getRequestURI();

        //白名单不认证
        boolean is_pass = isWhiteList(url, req);
        if (is_pass) {
            chain.doFilter(request, response);
            return;
        }

        if (user_id == null || "0".equals(user_id)) {
            logger.warn("Request for {} without valid user id, possibly because of session timeout.",
                    ((HttpServletRequest) request).getRequestURI());

            ((HttpServletRequest)request).getSession().invalidate();

            notifySessionInvalid(res, req);
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String cp = filterConfig.getServletContext().getContextPath();
        String ignoresParam = filterConfig.getInitParameter("prefixIgnore");
        String[] ignoreArray = ignoresParam.split(",");
        for (String s : ignoreArray) {
            prefixIgnores.add(cp + s);
        }

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    public boolean isWhiteList(String url, HttpServletRequest request) {

        String contextPath = request.getContextPath();

        if (url.equalsIgnoreCase(contextPath + "/"))
            return true;

        for (String prefix : prefixIgnores) {
            if (url.startsWith(prefix))
                return true;
        }

        return false;
    }

    private void notifySessionInvalid(HttpServletResponse response, HttpServletRequest request) {
        String host = request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort() + request.getContextPath()
                + "/";
        if (isAjax(request)) {
            writeJs("{\"code\":-99,\"msg\":\"会话已超时，请重新登录！\", \"url\":\"" + host + "\"}", response);
        } else {
            String casUrl = PropertiesUtil.getPropery("sso.cas.server.prefixUrl");
            String url = request.getScheme() + "://" + request.getServerName()
                    + ":" + request.getServerPort() + request.getContextPath()
                    + "/";
            String retUrl = casUrl.concat("/logout?service=").concat(QCommon.urlEncode(url));

            writeMsg("会话已超时，请重新登录！", host, retUrl, request, response);
        }
    }

    private boolean isAjax(HttpServletRequest request) {
        //如果是ajax请求响应头会有x-requested-with
        return request.getHeader("x-requested-with") != null
                && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest");
    }

    private void writeJs(String msg, HttpServletResponse response) {
        response.setContentType("application/json; charset=utf-8"); // 输出JS文件
        response.setCharacterEncoding("UTF-8");
        try {
            OutputStream out = response.getOutputStream();
            out.write(msg.getBytes("UTF-8"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void writeMsg(String msg, String host, String url, HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String outString = "<html><body><script src=\"" + host + "res/plugin/jQuery/jquery-1.11.3.min.js\" type=\"text/javascript\"></script>";
        outString += "<link href=\"" + host + "res/layer/skin/default/layer.css\" rel=\"stylesheet\">";
        outString += "<script src=\"" + host + "res/layer/layer.js\"></script>";
        outString += "<script language=javascript>layer.alert('" + msg + "',function(){(window.parent||window).location='"
                + url + "';});</script>";
        outString += "</body></html>";
        try {
            response.getWriter().print(outString);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}