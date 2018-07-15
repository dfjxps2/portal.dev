/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.quick.core.util.filter;

import com.quick.core.base.AppResource;
import com.quick.core.util.common.QCommon;
import com.quick.core.util.common.QCookie;
import com.quick.portal.security.authority.metric.PropertiesUtil;

import java.io.IOException;
import java.io.OutputStreamWriter;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 *
 * @author Administrator
 * loginCasFilter
 */
//public class LoginFilter implements Filter {
public class LoginFilter extends HttpServlet implements Filter {

    private FilterConfig filterConfig;
    private final static String filterParm = ";";
    
    public void doFilter(ServletRequest request, ServletResponse response,
                    FilterChain chain) throws IOException, ServletException {
    	 HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) response);
  
       HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uid = QCookie.getValue(req, "ids");
        String url = req.getRequestURI();
        if(uid == null || "".equals(uid)){
        	int index= url.lastIndexOf("/");//获取url字符串中“.”的最后一个索引
        	String method = url.substring(index+1);//返回a
        	if(method.startsWith(filterParm)||"".equals(method)){
        		chain.doFilter(req, res);
        	}else{
        		String casLogoutUrl = getCasLogoutUrl(req);
//           	  	wrapper.sendRedirect(casLogoutUrl);
//        		req.getRequestDispatcher(casLogoutUrl).forward(request, response);
        		toAlert(res,req);    
           	  	return ;
        	}
        }else{
        	chain.doFilter(req, res); 
        }
    }
    
    private String getCasLogoutUrl(HttpServletRequest req){
    	 req.getSession().invalidate();
         String casUrl = PropertiesUtil.getPropery("cas.serverUrl");
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

}