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

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.quick.core.base.SysApiController;
import com.quick.core.util.common.JsonUtil;
import com.quick.core.util.common.QCommon;
import com.quick.core.util.type.TypeUtil;
import com.quick.portal.application.ApplicationDO;
import com.quick.portal.application.IApplicationService;
import com.quick.portal.page.IPageService;
import com.quick.portal.section.ISectionService;
import com.quick.portal.security.authority.metric.MetricPrivilegeConstants;
import com.quick.portal.security.authority.metric.PropertiesUtil;
import com.quick.portal.web.model.DataResult;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 门户请求类
 *
 * @author Administrator
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/mobile/monitor")
public class MonitorApiController extends SysApiController {

    @Resource(name = "applicationService")
    private IApplicationService applicationService;

    @Resource(name = "pageService")
    private IPageService pageService;

    @Resource(name = "sectionService")
    private ISectionService sectionService;
    
    /*@RequestMapping(value = "/settingUser")
  	@ResponseBody
      public Object settingUser(Integer app_id,Integer page_id) {
    	String user_id = rstr("u", loginer.getUser_id().toString());
    	String time = rstr("time","").toString();
        List<Map<String, Object>> plist = queryPage(app_id);
        if(page_id == 0 && plist != null && plist.size() > 0){
            page_id = (Integer)TypeUtil.parse(Integer.class, plist.get(0).get("page_id"));
        }
        ApplicationDO app = applicationService.selectObj(app_id.toString());

        Object layoutJson = getPageJson(page_id);
        Object metricJson = getMetricJson(page_id,user_id,time);
        String url = PropertiesUtil.getPropery("index.service.url");
    	String port = PropertiesUtil.getPropery("index.service.port");
    	String urlShow = url.concat(MetricPrivilegeConstants.SERVICE_PORT).concat(port).concat(MetricPrivilegeConstants.GET_MEASURES_SERVICE_NAME);
        Map<String, Object> map = new HashMap<String, Object>();
        
        map.put("urlShow", urlShow);
        map.put("page_id", page_id);
        map.put("page", JsonUtil.serialize(plist));
        map.put("metric",  metricJson);
        map.put("layout", layoutJson);
        map.put("app", JsonUtil.serialize(app));
        return  new DataResult(map);
    }*/
    
    
    @RequestMapping(value = "/settingUser")
  	@ResponseBody
      public DataResult settingUser(String app_id,String page_id) {
    	int aid = Integer.valueOf(app_id);
    	int pid = Integer.valueOf(page_id);
    	String user_id = rstr("u", loginer.getUser_id().toString());
    	String time = rstr("time","").toString();
    	/*
        List<Map<String, Object>> plist = queryPage(aid);
        if((page_id == null||"".equals(page_id)) && plist != null && plist.size() > 0){
        	pid = (Integer)TypeUtil.parse(Integer.class, plist.get(0).get("page_id"));
        }
        ApplicationDO app = applicationService.selectObj(app_id);
        */
        List<Map<String, Object>>  restList = sectionService.selectLayoutJsonByApp(pid,Integer.parseInt(user_id),time);
        /*  
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("urlShow", getmectricUrl());
        map.put("page_id", page_id);
        map.put("app", app);
        map.put("page", plist);
        
        map.put("app", JsonUtil.serialize(app));
        map.put("page", JsonUtil.serialize(plist));
        restList.add(map);
        */
        return  new DataResult(restList);
    }
    
    /**
     * 查询页面配置信息
     * @return
     */
    @RequestMapping(value = "/getLayout")
    @ResponseBody
    public DataResult getLayout(Integer p,String u){
    	//获取当前用户id
    	String time = null;
    	String user_id = rstr("u", loginer.getUser_id().toString());
        String layout = "[{id:0, no:1,x: 0, y: 0, width: 12, height: 6, metric:[]}]";
        List<Map<String, Object>>  restList = null ;
        Map <String, Object> mp = new HashMap();
        if(p != null && p > 0){
        	restList = sectionService.selectLayoutJsonByApp(p,Integer.parseInt(user_id),time);
        	if(null != restList && restList.size()>0){
        		Map <String, Object> metricMp = new HashMap();
        		String mectricUrl  = getmectricUrl();
        		metricMp.put("mectricUrl", mectricUrl);
        		restList.add(metricMp);
        		return new DataResult(restList);
        	}else{
        		 return new DataResult(-1,"查询数据为空");
 
        	}
        }
    
        return new DataResult(restList);

    }
  
/*    @RequestMapping(value = "/getLayout",produces="application/json;charset=utf-8")
    @ResponseBody
    public Object getLayout(Integer p,String u){
    	//获取当前用户id
    	String time = null;
    	String user_id = rstr("u", loginer.getUser_id().toString());
        String layout = "[{id:0, no:1,x: 0, y: 0, width: 12, height: 6, metric:[]}]";
        if(p != null && p > 0){
            String res = sectionService.selectLayoutJson(p,Integer.parseInt(user_id),time);
            if(!QCommon.isNullOrEmpty(res))
                layout = res;
        }
        if(layout.indexOf(",}]")>-1){
        	layout = layout.replace(",}]", "}]");
        }
        return new DataResult(1,layout.replace("\"", "'"));
    }*/

    /**
     * 查询应用所有页面
     * @return
     */
    @RequestMapping(value = "/getPage")
    @ResponseBody
    public DataResult getPage(Integer t){
        List<Map<String, Object>> ls = queryPage(t);
        return new DataResult(ls);
    }
    private List<Map<String, Object>> queryPage(Integer t){
        List<Map<String, Object>> ls = new ArrayList<>();
        if(t != null && t > 0){
            Map<String, Object> p = new HashMap<>();
            p.put("app_id", t.toString());
            ls = pageService.select(p);
        }
        return ls;
    }


    public Object getPageJson(Integer page_id){
        String layout = "[{x: 0, y: 0, width: 12, height: 6, id:0, no:1}]";
        if(page_id != null && page_id > 0){
            String res = sectionService.selectSectionJson(page_id);
            if(!QCommon.isNullOrEmpty(res))
                layout = res;
        }
        return layout;
    }

    public Object getMetricJson(Integer page_id,String user_id,String time){
        String json = "[]";
        if(page_id != null && page_id > 0){
            String res = sectionService.selectMetricJson(page_id,Integer.parseInt(user_id),time);
            if(!QCommon.isNullOrEmpty(res))
                json = res;
        }
        return json;
    }
    
    @RequestMapping(value = "/getEditionMetric")
	@ResponseBody
    public Object  getEditionMetric(Integer app_id) {
    	String user_id = rstr("u", loginer.getUser_id().toString());
    	String json = "";
         if(app_id!= null && app_id > 0){
        	 String res = sectionService.getEditionMetricJson(app_id,Integer.parseInt(user_id));
             if(!QCommon.isNullOrEmpty(res))
            	 json = res;
         }
         return json;
	}
    
    
    /**
     * 添加用户指标配置
     * @param metric_json
     */
    @RequestMapping(value ="/saveSetting")
    @ResponseBody
    public Object saveSetting( String metric_json) {
    	String user_id = rstr("u", loginer.getUser_id().toString());
    	return  pageService.addUserConfig(metric_json,user_id);
    }
    
    public static void main(String [] args){
    	 Map <String, Object> mpa = new HashMap();
    	 mpa.put("id", "aa");
    	 mpa.put("name", "name");
    	 for (Map.Entry<String, Object> m : mpa.entrySet()) {
             System.out.print(m.getKey() + "    ");
             System.out.println(m.getValue());
         }
    	 
    	List<String> gameids = java.util.Arrays.asList(str.split(",")); 
    	List<Map <String, Object> > retLit = new ArrayList();
    	Map <String, Object> mp = null;
    	String [] flds = null;
    	for(String str :gameids ){
    		mp = new HashMap();
    		if(str.startsWith("metric:")){
    			if(str != null && !"".equals("")){
    				
    			}
    			flds = str.split(":");
    			mp.put(flds[0].replace("{", ""), flds[1]+flds[2]);
    		}else{
    			flds = str.split(":");
    			mp.put(flds[0].replace("{", ""), flds[1]);
    		}
    	    
    		
    		retLit.add(mp);
    		
    	}
    	
    	 for (Map<String, Object> map : retLit) {
             for (Map.Entry<String, Object> m : map.entrySet()) {
                 System.out.print(m.getKey() + "    ");
                 System.out.println(m.getValue());
             }
         }
    }
    
    
    private static String getmectricUrl(){
    	String url = PropertiesUtil.getPropery("index.service.url");
      	String port = PropertiesUtil.getPropery("index.service.port");
      	String urlShow = url.concat(MetricPrivilegeConstants.SERVICE_PORT).concat(port).concat(MetricPrivilegeConstants.GET_MEASURES_SERVICE_NAME);
      	return urlShow;
    }
    
    public static final String str = "{'id':151,'x':0,'y':0,'no':'1','width':12,'height':4,metric:[{'metric_id':'C20007','category_id':'C20000','dimension':'obj','charts':'bar','numb':'1','measure_name':'街道办事处占比','time_dim':'year','unit':'%'}]},{'id':152,'x':0,'y':4,'no':'2','width':4,'height':2,metric:[{'metric_id':'C20008','category_id':'C20000','dimension':'obj','charts':'line','numb':'2','measure_name':'建制镇占比','time_dim':'year','unit':'%'}]},{'id':153,'x':4,'y':4,'no':'3','width':4,'height':2,metric:[]},{'id':154,'x':8,'y':4,'no':'4','width':4,'height':2,metric:[{'metric_id':'C20010','category_id':'C20000','dimension':'obj','charts':'ringPie','numb':'4','measure_name':'社区居委会占比','time_dim':'year','unit':'%'}]}";

}