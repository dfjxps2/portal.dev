/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.quick.core.util.filter;

import java.io.IOException;
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
    
    public void doFilter(ServletRequest request, ServletResponse response,
                    FilterChain chain) throws IOException, ServletException {
    	HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) response);
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String rid = QCookie.getValue(req, COOKIE_ROLE_ID);
        HttpSession session = req.getSession();
        Object obj = session.getAttribute(SESSION_PAC4JUSERPROFILES);
        String url = req.getRequestURI();
        if(rid == null || "0".equals(rid)){
        	if(url.startsWith(filterParm_prefix_portal) && filterParm_prefix_portal.equals(url)){
        		chain.doFilter(req, res);
        	}else if(url.startsWith(filterParm_prefix_callback) && filterParm_prefix_callback.equals(url)){
        		chain.doFilter(req, res);
        	}else{
        		String casLogoutUrl = getCasLogoutUrl(req);
           	  	wrapper.sendRedirect(casLogoutUrl);
        	}
        }else{
        	if(null == obj){
            	String casLogoutUrl = getCasLogoutUrl(req);
           	  	wrapper.sendRedirect(casLogoutUrl);
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