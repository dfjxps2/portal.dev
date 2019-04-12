package com.quick.portal.web.login;

import org.pac4j.core.context.J2EContext;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.profile.CommonProfile;
import org.pac4j.core.profile.ProfileManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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
        final ProfileManager<CommonProfile> manager = new ProfileManager<>(context);
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
    public static boolean isAdminRoleType(WebLoginUser loginer){
		return loginer.getRoleList().stream().map((r) -> {
		    String roleType = r.getRole_type_id().toString();
		    return roleType.equals(WebLoginConstants.ADMINISTRATOR_ROLE_TYPE) ||
					roleType.equals(WebLoginConstants.PORTAL_ROLE_TYPE);
        }).reduce(false, (r1, r2) -> (r1 || r2));

    }


	/*
	 *rType --1: 超级管理员类型;2:平台管理员类型;3:业务部门用户类型
	 *
	 * 平台用户:1:app;2:sys
	 * 公服用户:1:app
	 * 1:app;2:sys
	 */
	public static String getUserHomePage(WebLoginUser loginer) {
		String flag = null;
		//平台用户
		if (null == loginer.getUser_global_id() || "".equals(loginer.getUser_global_id())) {
			boolean bool = WebLoginUitls.isAdminRoleType(loginer);
			if(bool){
				flag = WebLoginConstants.SYS_MENU_FLAG;
			}else{
				flag = WebLoginConstants.APP_MENU_FLAG;
			}
			//公服用户
		} else {
			flag = WebLoginConstants.APP_MENU_FLAG;
		}
		return flag;

	}

	public static String val(Map<String, Object> m, String key){
		Object obj = m.get(key);
		if(obj == null)
			return "";
		return obj.toString();
	}
    

}
