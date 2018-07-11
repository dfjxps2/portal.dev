package com.quick.portal.web.login;

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

}
