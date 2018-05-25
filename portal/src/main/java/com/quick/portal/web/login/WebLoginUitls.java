package com.quick.portal.web.login;

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

}
