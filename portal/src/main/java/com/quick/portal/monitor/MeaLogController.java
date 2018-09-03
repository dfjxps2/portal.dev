package com.quick.portal.monitor;

import com.quick.core.base.SysBaseController;
import com.quick.core.util.common.CommonUtils;
import com.quick.core.util.common.QCookie;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sc on 2018/8/27.
 */
@Controller
@Scope("prototype")
@RequestMapping("mealog")
public class MeaLogController {

    @Resource(name = "MeaLog")
    private MeaLogServiceImpl meaLogService;

    @ResponseBody
    @RequestMapping("sendlog")
    public String sendLog(String meaid, HttpServletRequest request){
        Map map = new HashMap();
        if (meaid == null || meaid.equals("null")){
            meaid="0";
        }
        try{
   /*         Cookie[] cookies = request.getCookies();
            for(Cookie cs : cookies){
                System.out.println("cookieName"+cs.getName()+"---------"+"cookieValue"+cs.getValue());
            }*/
            String ids  = QCookie.getValue(request,"ids");
            System.out.println("ids"+ids);
            System.out.println("meaid"+meaid);
            String usname = QCookie.getValue(request,"sbd.uid");

/*            String ip = request.getHeader( "x-forwarded-for" );
            if ( ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase( ip ) )
            {
                ip = request.getHeader( "Proxy-Client-IP" );
            }
            if ( ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase( ip ) )
            {
                ip = request.getHeader( "WL-Proxy-Client-IP" );
            }
            if ( ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase( ip ) )
            {
                ip = request.getRemoteAddr();
            }*/
            String ip = CommonUtils.getIpAddress(request);
            map.put("user_id",ids);
            map.put("user_ip",ip);
            map.put("user_op_type",2);
            map.put("log_detail","用户名:"+usname+"调取了指标接口,查看了"+meaid+"指标");
            map.put("log_type_id",1);
            map.put("menu_id",2);
            meaLogService.sendLog(map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "sendLog is success!";
    }

    public static String getLocalIp() throws Exception {
        String ipString = "";
        Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
        InetAddress ip = null;
        while (allNetInterfaces.hasMoreElements()) {
            NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
            Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                ip = (InetAddress) addresses.nextElement();
                if (ip != null && ip instanceof Inet4Address && !ip.getHostAddress().equals("127.0.0.1")) {
                    return ip.getHostAddress();
                }
            }
        }
        return ipString;
    }

    public String sendGet(String url,String param){
        String result = "";
        BufferedReader in = null;
        try{
            String urlNameString = url + "?"+param;
            URL realUrl = new URL(urlNameString);
            //打开和URL之间的链接
            URLConnection connection = realUrl.openConnection();
            //设置通用的请求属性
            connection.setRequestProperty("accept","*/*");
            connection.setRequestProperty("connection","Keep-Alive");
            connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //建立实际链接
            connection.connect();
            Map<String,List<String>> map = connection.getHeaderFields();
            for(String key:map.keySet()){
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(in != null){
                    in.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return result;
    }
}
