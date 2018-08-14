/**
 * <h3>标题 : portal统一门户-管理驾驶仓 </h3>
 * <h3>描述 : 应用中的相关配置信息都放在此</h3>
 * <h3>日期 : 2018-04-13</h3>
 * <h3>版权 : Copyright (C) 北京东方金信科技有限公司</h3>
 *
 * <p>
 * @author admin mazong@seaboxdata.com
 * @version <b>v1.0.0</b>
 *
 * <b>修改历史:</b>
 * -------------------------------------------
 * 修改人  修改日期   修改描述
 * -------------------------------------------
 *
 *
 * </p>
 */
package com.seaboxdata.portal.mobile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quick.core.base.SysApiController;
import com.quick.core.base.model.DataStore;
import com.quick.core.util.common.JsonUtil;
import com.quick.core.util.common.QCommon;
import com.quick.portal.web.home.IHomeService;
import com.quick.portal.web.model.DataResult;

/**
 * 门户API接口类
 *
 * @author Administrator
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/mobile/home")
public class HomeApiController extends SysApiController {

    @Resource(name = "homeService")
    private IHomeService homeService;
    
    

    
    
    /**
     * 删除应用
     * id: 应用编号
     * @return
     */
    @RequestMapping(value = "/dodel")
    @ResponseBody
    public Object dodel(String u,String id){
    	Map<String, Object> p = new HashMap<>();
        String uid = rstr("u", loginer.getUser_id().toString());
        p.put("user_id", uid);
    	String bid = homeService.queryDashboard(p);
        if(id != null && !id.equals("")) {
            String[] ids = id.split(",");
            for (String str : ids) {
                homeService.deleteDashboardAppByID(bid,str);
            }
        }
        return new DataResult(1,CANCEL_MSG);
//        return getUserApp();
    }

    @RequestMapping(value = "/doadd")
    @ResponseBody
    public Object doadd(String id){
        if(id == null || id.equals(""))
        	return new DataResult(0,"请选择要添加的应用");
        String[] ids = id.split(",");
        Map<String, Object> p = new HashMap<>();
        String uid = rstr("u", loginer.getUser_id().toString());
        p.put("user_id", uid);

        Map<String, Object> u = homeService.queryAppConfig(p);
        if(u == null)
        	return new DataResult(0,"读取用户桌面出错，请刷新页面");
        String dashboard_id = val(u, "dashboard_id");
        String sno = val(u, "param_value");
        Integer param_value = sno.length() == 0 ? 1 : Integer.valueOf(sno);
        for(String str : ids){
            Map<String, Object> m = new HashMap<>();
            m.put("dashboard_id", dashboard_id);
            m.put("param_value", param_value);
            m.put("app_id", str);
            m.put("param_id", 1);
            homeService.addApp(m);
            param_value++;
        }
//        return getUserApp();
        return new DataResult(1,SUBSCRIPTION_MSG);
    }

    /**
     * 查询用户所有应用
     * @return
     */
    @RequestMapping(value = "/getUserApp")
    @ResponseBody
    public DataResult getUserApp(){
        return new DataResult(queryUserApp());
    }
    private List<Map<String, Object>> queryUserApp(){
        String app_name = rstr("t");
        if(app_name.length() > 0)
            urlMap.put("app_name", app_name);
        String uid = rstr("u", loginer.getUser_id().toString());
        urlMap.put("user_id", uid);

//        List<Map<String, Object>> list =  homeService.queryUserApp(urlMap);
        List<Map<String, Object>> list =  homeService.getUserApp(urlMap);
        fixUrl(list);
        return list;
    }

    @RequestMapping(value = "/saveSort")
    @ResponseBody
    public DataStore saveSort(String s){
        if(QCommon.isNullOrEmpty(s) || !s.startsWith("["))
            return ActionMsg.setError("无效的格式");
        try{
            List<Map<String, Object>> lst = JsonUtil.fromJson(s, List.class, Map.class);
            homeService.updateAppSort(lst);
            return ActionMsg.setOk("OK");
        }catch(Exception e){
            return ActionMsg.setError("无效的格式,"+e.getMessage());
        }
    }


    /**
     * 查询所有应用
     * @return
     */
    
    @RequestMapping(value = "/getApp")
    @ResponseBody
    public DataResult getApp(HttpServletRequest request,HttpServletResponse response){
        String uid = rstr("u", loginer.getUser_id().toString());
        String role_id = rstr("r", loginer.getRole_id().toString());
        urlMap.put("user_id", uid);
        urlMap.put("role_id", role_id);
        List<Map<String, Object>> list = homeService.queryApp(urlMap);
        fixUrl(list);
        return new DataResult(list);
    }
    /**
     * APP端：查询所有应用
     * @return
     */
    
    @RequestMapping(value = "/getAllApp")
    @ResponseBody
    public DataResult getAllApp(){
        String uid = rstr("u", loginer.getUser_id().toString());
        String role_id = rstr("r", loginer.getRole_id().toString());
        urlMap.put("user_id", uid);
        urlMap.put("role_id", role_id);

        List<Map<String, Object>> list = homeService.queryUserAllByApp(urlMap);
        fixUrl(list);
        return new DataResult(list);
    }
    private void fixUrl(List<Map<String, Object>> list){
        //补充host
        for(Map<String, Object> m : list){
            String app_url = val(m, "app_url");
            String app_preview_url = val(m,"app_preview_url");
            String menu_icon_url = val(m, "menu_icon_url");
            if(app_url.length()>0 && !app_url.startsWith("http:"))
                m.put("app_url", getUrl() + "/" + app_url);
            if(app_preview_url.length()>0 && !app_preview_url.startsWith("http:"))
                m.put("app_preview_url", getUrl() + "/" + app_preview_url);
            if(menu_icon_url.length()>0 && !menu_icon_url.startsWith("http:"))
                m.put("menu_icon_url", getUrl() + "/" + menu_icon_url);
        }
    }
    
    
    private static final String  CANCEL_MSG = "取消成功";
    
    private static final String  SUBSCRIPTION_MSG = "订阅成功";
	

}