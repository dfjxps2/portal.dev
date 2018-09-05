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
	
	
	public static String getPath( HttpServletRequest request) {
        String path = request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort() + request.getContextPath();
        return path;
    }
	
	
	/*
     * rType --1: 超级管理员类型;2:平台管理员类型;3:业务部门用户类型
     * return 
     *  ture --1: 超级管理员类型;2:平台管理员类型;
     *  false --3:业务部门用户类型
     */
    public static boolean isAdminRoleType(String rType){
		boolean bool = false;
		if (null != rType && !"".equals(rType)) {
			String[] rTypes = rType.split(",");
			for (String type : rTypes) {
				if (WebLoginConstants.ADMINISTRATOR_ROLE_TYPE.equals(type)
						|| WebLoginConstants.PORTAL_ROLE_TYPE.equals(type)) {
					bool = true;
					return bool;
				}
			}
		}
		return bool;
    }
    
    
    
	public static WebLoginUser getLoginUser(Map<String, Object> u,List<Map<String, Object>> roles){
		WebLoginUser user = new WebLoginUser();
		user.setUser_real_name(val(u, "user_real_name"));
		user.setUser_id(Integer.valueOf(val(u, "user_id")));
		user.setUser_global_id(val(u, "user_global_id"));
		user.setUser_name(getVal(u, "user_name"));
		user.setUser_state(Integer.valueOf(getVal(u, "user_state")));
		user = getLoginUserRole(roles, user);
		return user;
	}
	
	public static WebLoginUser getLoginUserRole(List<Map<String, Object>> roles,WebLoginUser user){
		String rids = "";
        String rstate  = "";
        //一个用户多角色
        for (Map<String, Object> m : roles){
        	rids += WebLoginConstants.OCTOTHORPE_SPECIAL_CHARACTER + m.get("role_id").toString();
        	rstate += WebLoginConstants.OCTOTHORPE_SPECIAL_CHARACTER + m.get("role_type_id").toString();
        } 
        rids = rids.substring(1);
        user.setRole_ids(rids);
        rstate = rstate.substring(1);
        user.setRole_type_ids(rstate);
        return user;
	}
	

	public static String val(Map<String, Object> m, String key){
		Object obj = m.get(key);
		if(obj == null)
			return "";
		return obj.toString();
	}
    

}
