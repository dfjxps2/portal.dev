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
package com.quick.portal.web.monitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.quick.portal.web.model.DataResult;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quick.core.base.SysWebController;
import com.quick.core.base.model.DataStore;
import com.quick.core.util.common.JsonUtil;
import com.quick.core.util.common.QCommon;
import com.quick.core.util.type.TypeUtil;
import com.quick.portal.application.ApplicationDO;
import com.quick.portal.application.IApplicationService;
import com.quick.portal.page.IPageService;
import com.quick.portal.section.ISectionService;
import com.quick.portal.security.authority.metric.MetricPrivilegeConstants;
import com.quick.portal.security.authority.metric.PropertiesUtil;

/**
 * 门户请求类
 *
 * @author Administrator
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/monitor")
public class MonitorController extends SysWebController {

    @Resource(name = "applicationService")
    private IApplicationService applicationService;

    @Resource(name = "pageService")
    private IPageService pageService;

    @Resource(name = "sectionService")
    private ISectionService sectionService;

    /**
     * 管理驾驶仓(仪表盘)
     * @param model
     * @return
     */
    @RequestMapping
    public String index(Model model) {
        Integer app_id = rint("t", 0);
        Integer page_id = rint("p", 0);
        List<Map<String, Object>> plist = queryPage(app_id);
        String time = rint("time",0).toString();
        if(page_id == 0 && plist != null && plist.size() > 0){
            page_id = (Integer)TypeUtil.parse(Integer.class, plist.get(0).get("page_id"));
        }
        ApplicationDO app = applicationService.selectObj(app_id.toString());
        DataResult layout = getLayout(page_id);
        
        String url = PropertiesUtil.getPropery("index.service.url");
    	String port = PropertiesUtil.getPropery("index.service.port");
    	String serviceUrl = url.concat(MetricPrivilegeConstants.SERVICE_PORT).concat(port).concat(MetricPrivilegeConstants.GET_MEASURES_SERVICE_NAME);
    	model.addAttribute("MEASURES_URL", serviceUrl);
        model.addAttribute("app", app);
        model.addAttribute("page_id", page_id);
        model.addAttribute("pageJson", JsonUtil.serialize(plist));
        model.addAttribute("layout", layout.getData());
        model.addAttribute("time", time);
        return view();
    }
    
    /**
     * 添加应用
     * @param model
     * @return
     */
    @RequestMapping
    public String setting(Model model) {
        Integer app_id = rint("t", 0);
        Integer page_id = rint("p", 0);
        String user_id = rstr("u", loginer.getUser_id().toString());
        String time = rint("time",0).toString();
        List<Map<String, Object>> plist = queryPage(app_id);
        if(page_id == 0 && plist != null && plist.size() > 0){
            page_id = (Integer)TypeUtil.parse(Integer.class, plist.get(0).get("page_id"));
        }
        ApplicationDO app = applicationService.selectObj(app_id.toString());

        Object layoutJson = getPageJson(page_id);
        Object metricJson = getMetricJson(page_id,user_id,time);
       /* String urlShow = PropertiesUtil.getPropery("index.service.showURL");
    	model.addAttribute("urlShow", urlShow);*/
        String url = PropertiesUtil.getPropery("index.service.url");
    	String port = PropertiesUtil.getPropery("index.service.port");
    	String urlShow = url.concat(MetricPrivilegeConstants.SERVICE_PORT).concat(port).concat(MetricPrivilegeConstants.GET_MEASURES_SERVICE_NAME);
    	model.addAttribute("urlShow", urlShow);
    	model.addAttribute("page_id", page_id);
        model.addAttribute("app", app);
        model.addAttribute("page", JsonUtil.serialize(plist));
        model.addAttribute("metric", metricJson);
        model.addAttribute("layout", layoutJson);
        return view();
    }
    
    @RequestMapping(value = "/settingUser")
  	@ResponseBody
      public Object settingUser(Integer app_id,Integer page_id) {
    	String user_id = rstr("u", loginer.getUser_id().toString());
    	String time = rint("time",0).toString();
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
        return map;
    }
    
    @RequestMapping
    public String show(ModelMap model) {
    	String url = PropertiesUtil.getPropery("index.service.url");
    	String port = PropertiesUtil.getPropery("index.service.port");
    	String urlShow = url.concat(MetricPrivilegeConstants.SERVICE_PORT).concat(port).concat(MetricPrivilegeConstants.GET_MEASURES_SERVICE_NAME);
    	model.addAttribute("urlShow", urlShow);
        return view();
    }
    
    /**
     * 查询页面配置信息
     * @return
     */
    @PostMapping
    @ResponseBody
    public DataResult getLayout(Integer p){
    	//获取当前用户id
    	String user_id = rstr("u", loginer.getUser_id().toString());
        String layout = "[{id:0, no:1,x: 0, y: 0, width: 12, height: 6, metric:[]}]";
        if(p != null && p > 0){
            String res = sectionService.selectLayoutJson(p,Integer.parseInt(user_id));
            if(!QCommon.isNullOrEmpty(res))
                layout = res;
        }
        return new DataResult(layout);
    }

    /**
     * 查询应用所有页面
     * @return
     */
    @PostMapping
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
    public DataStore saveSetting( String metric_json) {
    	String user_id = rstr("u", loginer.getUser_id().toString());
    	return saveAfter(pageService.addUserConfig(metric_json,user_id));
    }

}
