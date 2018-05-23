package com.quick.core.util.common;

import javax.servlet.http.HttpServletRequest;

import com.quick.core.util.type.TypeUtil;
import com.quick.core.util.common.QCommon;

public class QRequest {

    public static String getRemoteIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


    public static String getString(HttpServletRequest request, String name) {
        String value = request.getParameter(name);
        return value == null ? "" : QCommon.urlDecode(value);
    }
    public static String getString(HttpServletRequest request, String name, String defValue) {
        String value = request.getParameter(name);
        return QCommon.isNullOrEmpty(value) ? defValue : QCommon.urlDecode(value);
    }
    public static Integer getInteger(HttpServletRequest request, String name) {
        String str = request.getParameter(name);
        return QCommon.isNullOrEmpty(str) ? 0 : TypeUtil.parseObject(Integer.class, str); 
    }
    public static Integer getInteger(HttpServletRequest request, String name, Integer defValue) {
        String str = request.getParameter(name);
        return TypeUtil.parseObject(Integer.class, str, defValue);
    }
    public static Long getLong(HttpServletRequest request, String name) {
        String str = request.getParameter(name);
        return QCommon.isNullOrEmpty(str) ? 0L : TypeUtil.parseObject(Long.class, str);
    }
    public static Long getLong(HttpServletRequest request, String name, Long defValue) {
        String str = request.getParameter(name);
        return TypeUtil.parseObject(Long.class, str, defValue); 
    }
    
    public static String getRequestUrl(HttpServletRequest request) {
        String dir = request.getContextPath();
        String path = request.getRequestURI();
        path = path.substring(dir.length());
        if(request.getQueryString()!=null&&request.getQueryString().length()>0)
        path+= "?" + (request.getQueryString()); 
        return path;
    }

    public static String getResponseType(String type){
        String t = "";
        switch (type){
            case "json": t = "text/plain";
                break;
            case "xml": t = "text/xml";
                break;
            case "js": t = "application/x-javascript";
                break;
            default:
                t = "text/html";
                break;
        }
        return t;
    }
}
