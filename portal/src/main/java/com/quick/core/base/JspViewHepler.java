package com.quick.core.base;


import javax.servlet.http.HttpServletRequest;

import com.quick.core.util.common.DateTime;
import org.springframework.web.util.UrlPathHelper;

public class JspViewHepler {
    
    public JspViewHepler() {
        
    }

    /**
     * 获取当前时间
     */
    public DateTime Now() {
        return DateTime.Now();
    }


    
    public String getActionUrl(HttpServletRequest request) {
        UrlPathHelper url = new UrlPathHelper();
    	String dir = url.getOriginatingContextPath(request);
    	String path = url.getOriginatingRequestUri(request);
    	path = path.substring(dir.length());
    	return path;
    }
    public String getRequestUrl(HttpServletRequest request) {
    	String dir = request.getContextPath();
    	String path = request.getRequestURI();
    	path = path.substring(dir.length());
    	return path;
    }
    
    
    public String toJson(Object object) {
    	try {
            return "";
            //return JSONUtil.serialize(object);
    	}catch(Exception ex) {
            ex.printStackTrace();
            return "null";
    	}
    }


}
