package com.quick.core.util.web;

import java.io.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtil {
    public static final int MSG_OK = 0;
    public static final int MSG_FAIL = 1;
    public static final String CHARSET = "UTF-8";
    public static final String CONTENT_TYPE_JS = "text/javascript;charset=UTF-8";
    public static final String CONTENT_TYPE_JSON = "application/x-json;charset=UTF-8";

    public WebUtil()
    {
    }

	/*根据request拿到用户存在cookie中的ids_usrid*/
	public static String getCookieUsrid(HttpServletRequest request){
		//根据cookie拿到当前用户的id
		String usrid ="";
		Cookie[]cookies = request.getCookies();
		if (cookies!=null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("ids")) {
					usrid = cookie.getValue();
				}
			}
		}
		//当前用户的id
		return usrid;
	}

    public static void showError(String failMsg, HttpServletRequest request, HttpServletResponse response)
	    throws Exception
	{
	    	showMsg(failMsg, 1, "", request, response);
	}
	
	private static void showMsg(String msg, int msgCode, String extendObj, HttpServletRequest request, HttpServletResponse response)
	    throws IOException
	{
	    boolean scriptTag = false;
	    String cb = request.getParameter("callback");
	    if(cb != null)
	    {
	        scriptTag = true;
	        response.setContentType("text/javascript;charset=UTF-8");
	    } else
	    {
	        response.setContentType("application/x-json;charset=UTF-8");
	    }
	    if(extendObj != null && !extendObj.equals(""))
	    	extendObj = "," + extendObj;
	    PrintWriter out = response.getWriter();
	    if(scriptTag)
	    {
	        out.write((new StringBuilder(String.valueOf(cb))).append("(").toString());
	    }
	    out.print((new StringBuilder("{code:").append(msgCode).append(",'msg':'")).append(msg).append("'").append(extendObj).append("}").toString());
	    
	    if(scriptTag)
	    {
	        out.write(");");
	    }
	}
	public static void showSuccess(String okMsg, HttpServletRequest request, HttpServletResponse response)
	throws Exception
	{
		showMsg(okMsg, 0, "", request, response);
	}
	public static void showSuccess(String okMsg, String url
			, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		showMsg(okMsg, 0, "url:'" + java.net.URLEncoder.encode(url,"utf-8") + "'", request, response);
	}
    /*
     public static String queryObjectToJsonString(QueryObject qo)
    {
        if(qo == null || qo.getResults() == null)
        {
            throw new IllegalArgumentException("QueryObject or QueryObject.result is null...");
        }
        StringBuffer sb = new StringBuffer(128);
        sb.append("[");
        List list = qo.getResults();
        if(!list.isEmpty())
        {
            JSONObject js;
            for(Iterator i = list.iterator(); i.hasNext(); sb.append(js.toString()).append(","))
            {
                Map map = (Map)i.next();
                js = new JSONObject(map);
            }

            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("]");
        return sb.toString();
    }

    public static String queryObjectToJsonStore(QueryObject qo)
    {
        if(qo == null || qo.getResults() == null)
        {
            throw new IllegalArgumentException("QueryObject or QueryObject.result is null...");
        }
        StringBuffer sb = new StringBuffer(128);
        sb.append("[");
        List list = qo.getResults();
        if(!list.isEmpty())
        {
            Map map;
            for(Iterator i = list.iterator(); i.hasNext(); sb.append("['").append(map.get("id")).append("','").append(map.get("name")).append("'],"))
            {
                map = (Map)i.next();
            }

            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("]");
        return sb.toString();
    }

    public static void jsonResponse(QueryObject qo, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        if(qo == null || qo.getResults() == null)
        {
            throw new IllegalArgumentException("QueryObject and it's results must be not null...");
        }
        JSONObject json = queryObjectToJSON(qo);
        String cb = request.getParameter("callback");
        if(cb != null)
        {
            response.setContentType("text/javascript;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write((new StringBuilder(String.valueOf(cb))).append("(").toString());
            out.print(json);
            out.write(");");
        } else
        {
            response.setContentType("application/x-json;charset=UTF-8");
            response.getWriter().print(json);
        }
    } 
     

    public static void jsonResponse(String result, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        if(result == null)
        {
            throw new IllegalArgumentException("result must be not null...");
        }
        String cb = request.getParameter("callback");
        if(cb != null)
        {
            response.setContentType("text/javascript;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write((new StringBuilder(String.valueOf(cb))).append("(").toString());
            out.print(result);
            out.write(");");
        } else
        {
            response.setContentType("application/x-json;charset=UTF-8");
            response.getWriter().print(result);
        }
    }

    public static void jsonResponse(Collection selectList, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        if(selectList == null)
        {
            throw new IllegalArgumentException("selectList must be not null...");
        }
        JSONObject json = selectListToJSON(selectList);
        String cb = request.getParameter("callback");
        if(cb != null)
        {
            response.setContentType("text/javascript;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write((new StringBuilder(String.valueOf(cb))).append("(").toString());
            out.print(json);
            out.write(");");
        } else
        {
            response.setContentType("application/x-json;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.print(json);
        }
    }

    public static void jsonFormalResponse(Object obj, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        if(obj == null)
        {
            throw new IllegalArgumentException("Object must be not null...");
        }
        boolean scriptTag = false;
        String cb = request.getParameter("callback");
        if(cb != null)
        {
            scriptTag = true;
            response.setContentType("text/javascript;charset=UTF-8");
        } else
        {
            response.setContentType("application/x-json;charset=UTF-8");
        }
        PrintWriter out = response.getWriter();
        JSONObject json = new JSONObject();
        JSONArray jsonItems = new JSONArray();
        jsonItems.put(objectToJSON(obj));
        json.put("results", jsonItems);
        if(scriptTag)
        {
            out.write((new StringBuilder(String.valueOf(cb))).append("(").toString());
        }
        out.print(json);
        if(scriptTag)
        {
            out.write(");");
        }
    }

    public static void jsonErrorsResponse(JSONObject errors, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        boolean scriptTag = false;
        String cb = request.getParameter("callback");
        if(cb != null)
        {
            scriptTag = true;
            response.setContentType("text/javascript;charset=UTF-8");
        } else
        {
            response.setContentType("application/x-json;charset=UTF-8");
        }
        PrintWriter out = response.getWriter();
        if(scriptTag)
        {
            out.write((new StringBuilder(String.valueOf(cb))).append("(").toString());
        }
        out.print((new StringBuilder("{errors:[")).append(errors).append("], 'errorInfo':'\u6570\u636E\u6821\u9A8C\u51FA\u9519\uFF01'}").toString());
        if(scriptTag)
        {
            out.write(");");
        }
    }

    

    

    public static void jsonOkMsgResponse(String okMsg, Map msgMap, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        boolean scriptTag = false;
        String cb = request.getParameter("callback");
        if(cb != null)
        {
            scriptTag = true;
            response.setContentType("text/javascript;charset=UTF-8");
        } else
        {
            response.setContentType("application/x-json;charset=UTF-8");
        }
        PrintWriter out = response.getWriter();
        if(scriptTag)
        {
            out.write((new StringBuilder(String.valueOf(cb))).append("(").toString());
        }
        StringBuffer sb = new StringBuffer(64);
        sb.append("{success:true,'info':'").append(okMsg).append("'");
        Object key;
        for(Iterator iter = msgMap.keySet().iterator(); iter.hasNext(); sb.append(",'").append(key).append("':'").append(msgMap.get(key)).append("'"))
        {
            key = iter.next();
        }

        sb.append("'}");
        out.print(sb.toString());
        if(scriptTag)
        {
            out.write(");");
        }
    }

    public static void jsonHtmlResponse(String html, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        boolean scriptTag = false;
        String cb = request.getParameter("callback");
        if(cb != null)
        {
            scriptTag = true;
            response.setContentType("text/javascript;charset=UTF-8");
        } else
        {
            response.setContentType("application/x-json;charset=UTF-8");
        }
        PrintWriter out = response.getWriter();
        if(scriptTag)
        {
            out.write((new StringBuilder(String.valueOf(cb))).append("(").toString());
        }
        out.print(html);
        if(scriptTag)
        {
            out.write(");");
        }
    }

    public static JSONObject objectToJSON(Object obj)
        throws Exception
    {
        if(obj == null)
        {
            throw new IllegalArgumentException("Object must be not null...");
        }
        JSONObject json = new JSONObject();
        if(obj instanceof Map)
        {
            Map map = (Map)obj;
            json = new JSONObject(map);
        } else
        if(obj instanceof Collection)
        {
            Collection c = (Collection)obj;
            for(Iterator iter = c.iterator(); iter.hasNext(); objectToJson(iter.next(), json)) { }
        } else
        {
            objectToJson(obj, json);
        }
        return json;
    }

    private static void objectToJson(Object obj, JSONObject json)
        throws IllegalAccessException, InvocationTargetException, JSONException
    {
        Method methods[] = obj.getClass().getMethods();
        for(int i = 0; i < methods.length; i++)
        {
            String methodName = methods[i].getName();
            if(methodName.startsWith("get") && !"getClass".equals(methodName))
            {
                Class type = methods[i].getReturnType();
                String typeName = type.getName();
                if(type.isPrimitive() || typeName.startsWith("java.lang") || typeName.startsWith("java.sql") || typeName.startsWith("java.math"))
                {
                    Object value = methods[i].invoke(obj, null);
                    if(value != null && typeName.equals("java.sql.Timestamp"))
                    {
                        String v = value.toString();
                        int index = v.indexOf(".");
                        if(index > -1)
                        {
                            value = v.substring(0, index);
                        }
                    }
                    if(value == null)
                    {
                        value = "";
                    }
                    json.put(parseFieldName(methodName), value);
                }
            }
        }

    }

    private static String parseFieldName(String method)
    {
        String field = method.trim().substring(3);
        if(field.equals(field.toUpperCase()))
        {
            return field;
        } else
        {
            return (new StringBuilder(String.valueOf(field.substring(0, 1).toLowerCase()))).append(field.substring(1)).toString();
        }
    }

    private static JSONObject queryObjectToJSON(QueryObject qo)
        throws Exception
    {
        JSONObject json = new JSONObject();
        JSONArray jsonItems = new JSONArray();
        for(Iterator iter = qo.getResults().iterator(); iter.hasNext(); jsonItems.put(objectToJSON(iter.next()))) { }
        json.put("totalCount", qo.getTotalCount());
        json.put("results", jsonItems);
        return json;
    }

    private static JSONObject selectListToJSON(Collection selectList)
        throws Exception
    {
        JSONObject json = new JSONObject();
        JSONArray jsonItems = new JSONArray();
        for(Iterator i = selectList.iterator(); i.hasNext(); jsonItems.put(objectToJSON(i.next()))) { }
        json.put("totalCount", selectList.size());
        json.put("results", jsonItems);
        return json;
    }
    */
}
