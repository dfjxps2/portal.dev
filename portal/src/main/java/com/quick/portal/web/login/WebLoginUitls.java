package com.quick.portal.web.login;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pac4j.core.context.J2EContext;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.profile.CommonProfile;
import org.pac4j.core.profile.ProfileManager;

public class WebLoginUitls {
	
	public static String getVal(Object obj){
		if(obj == null)
			return "";
		return obj.toString();
	}
	
	public static String getVal(Map<String, Object> m, String key){
		Object obj = m.get(key);
		if(obj == null)
			return "";
		return obj.toString();
	}
	
	public static List<CommonProfile> getProfiles(HttpServletRequest request, HttpServletResponse response) {
    	final WebContext context = new J2EContext(request, response);  
        final ProfileManager manager = new ProfileManager(context);
        return manager.getAll(true);
    }
	
	
	public static void writeFatal(HttpServletRequest request,HttpServletResponse response, String msg){
		String url = request.getContextPath();
		String logoutUrl = url+"/home/logout";
		response.setHeader("content-type", "text/html;charset=UTF-8");
		String outString =" <script src=\""+url+"/res/plugin/jQuery/jquery-1.11.3.min.js\" type=\"text/javascript\"></script>";
		outString+="<link href=\""+url+"/res/layer/skin/default/layer.css\" rel=\"stylesheet\">";
		outString+="<link href=\""+url+"/res/layer/skin/moon/style.css\" rel=\"stylesheet\">";
		outString+="<script src=\""+url+"/res/layer/layer.js\"></script>";
		outString+= "<script language=javascript>layer.msg('"+msg+"',{icon: 1, time: 5000, skin: 'layer-ext-moon'},function(){(window.parent||window).location='"
				+ logoutUrl + "/';});</script>";
		try {
			response.getWriter().print(outString);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
