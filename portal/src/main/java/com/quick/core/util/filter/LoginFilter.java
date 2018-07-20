/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.quick.core.util.filter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

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

/**
 *
 * @author Administrator
 * loginCasFilter
 */
public class LoginFilter extends HttpServlet implements Filter {

    private FilterConfig filterConfig;
    private final static String filterParm_prefix_portal = "/portal/";
    private final static String filterParm_prefix_callback = "/portal/callback";
    private final static String CAS_PREFIXURL = "sso.cas.server.prefixUrl";
    private final static String COOKIE_ROLE_ID = "sbd.role";
    private final static String SESSION_PAC4JUSERPROFILES = "pac4jUserProfiles";
	private final static String[] white_eq_pages = {"/", "/callback" };
	private final static String[] white_lk_pages = {"/mobile/", "/res/" };
    
    public void doFilter(ServletRequest request, ServletResponse response,
                    FilterChain chain) throws IOException, ServletException {
    	HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) response);
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String rid = QCookie.getValue(req, COOKIE_ROLE_ID);
        HttpSession session = req.getSession();
        Object obj = session.getAttribute(SESSION_PAC4JUSERPROFILES);
        String url = req.getRequestURI();

		//白名单不认证
		boolean is_pass = isWhiteList(url, req);
		if(is_pass) {
			chain.doFilter(request, response);
			return;
		}

        if(rid == null || "0".equals(rid)){
        	if(url.startsWith(filterParm_prefix_portal) && filterParm_prefix_portal.equals(url)){
        		chain.doFilter(req, res);
        	}else if(url.startsWith(filterParm_prefix_callback) && filterParm_prefix_callback.equals(url)){
        		chain.doFilter(req, res);
        	}else{
        		String casLogoutUrl = getCasLogoutUrl(req);
				sendRedirectToRoot(casLogoutUrl, res, req);//wrapper.sendRedirect(casLogoutUrl);
        	}
        }else{
        	if(null == obj){
            	String casLogoutUrl = getCasLogoutUrl(req);
				sendRedirectToRoot(casLogoutUrl, res, req);
            }else{
            	chain.doFilter(req, res);
            }

        }
    }
    
    private String getCasLogoutUrl(HttpServletRequest req){
    	 req.getSession().invalidate();
         String casUrl = PropertiesUtil.getPropery(CAS_PREFIXURL);
         String cUrl = req.getScheme() + "://" + req.getServerName()
                 + ":" + req.getServerPort() + req.getContextPath()
                 + "/"; 
         String casLogoutUrl =casUrl.concat("/logout?service=").concat(QCommon.urlEncode(cUrl));
         return casLogoutUrl;
    }
    

    public void init(FilterConfig arg0) throws ServletException {
        this.filterConfig = arg0;
    }

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public boolean isWhiteList(String url, HttpServletRequest request){

		String contextPath = request.getContextPath();
		if(url.equalsIgnoreCase(contextPath))
			return true;
		//静态文件不处理
		for(String u:white_eq_pages){
			if(url.equalsIgnoreCase(contextPath + u))
				return true;
		}
		for(String s:white_lk_pages){
			if(url.startsWith(contextPath + s))
				return true;
		}
		return false;
	}

	public void sendRedirectToRoot(String retUrl, HttpServletResponse response,HttpServletRequest request){
		String host = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + request.getContextPath()
				+ "/";
		if(isAjax(request)){
			writeJs("{\"code\":-99,\"msg\":\"会话已超时，请重新登录！\", \"url\":\""+retUrl+"\"}",response);
		}else{
			writeMsg("会话已超时，请重新登录！", host, retUrl, request, response);
		}
	}

	//前台弹出alert框
	public void toAlert( HttpServletResponse response,HttpServletRequest request){
		String casLogoutUrl = getCasLogoutUrl(request);   
	    try {
	         response.setContentType("text/html;charset=UTF-8");
	         response.setCharacterEncoding("UTF-8");
	            
	         OutputStreamWriter out = new OutputStreamWriter(response.getOutputStream());   
	         
	         String msg="由于您长时间没有操作，session已过期，请重新登录！";
	         msg=new String(msg.getBytes("UTF-8"));
	         
	         out.write("<meta http-equiv='Content-Type' content='text/html';charset='UTF-8'>");
	         out.write("<script>");
	         out.write("alert('"+msg+"');");
	         out.write("top.location.href = '"+casLogoutUrl+"'; ");
	         out.write("</script>");
	         out.flush();
	         out.close();

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	private boolean isAjax(HttpServletRequest request){
		//如果是ajax请求响应头会有x-requested-with
		if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){
			return true;
		}
		return false;
	}
	public void writeJs(String msg,HttpServletResponse response) {
		response.setContentType("application/json; charset=utf-8"); // 输出JS文件
		try {
			OutputStream out = response.getOutputStream();
			out.write(msg.getBytes("UTF-8"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public void writeMsg(String msg,String host, String url, HttpServletRequest request, HttpServletResponse response){
		response.setHeader("content-type", "text/html;charset=UTF-8");
		String outString ="<html><body><script src=\""+host+"res/plugin/jQuery/jquery-1.11.3.min.js\" type=\"text/javascript\"></script>";
		outString+="<link href=\""+host+"res/layer/skin/default/layer.css\" rel=\"stylesheet\">";
		outString+="<script src=\""+host+"res/layer/layer.js\"></script>";
		outString+= "<script language=javascript>layer.alert('"+msg+"',function(){(window.parent||window).location='"
				+ url + "';});</script>";
		outString+= "</body></html>";
		try {
			response.getWriter().print(outString);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}