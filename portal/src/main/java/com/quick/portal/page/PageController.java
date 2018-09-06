/**
 * <h3>标题 : potal统一门户-page </h3>
 * <h3>描述 : page请求类</h3>
 * <h3>日期 : 2018-05-03</h3>
 * <h3>版权 : Copyright (C) 北京东方金信科技有限公司</h3>
 * 
 * <p>
 * @author 你自己的姓名 mazong@seaboxdata.com
 * @version <b>v1.0.0</b>
 *          
 * <b>修改历史:</b>
 * -------------------------------------------
 * 修改人 修改日期 修改描述
 * -------------------------------------------
 *          
 *          
 * </p>
 */
package com.quick.portal.page;


import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quick.core.base.ISysBaseService;
import com.quick.core.base.SysBaseController;
import com.quick.core.base.model.DataStore;
import com.quick.core.util.common.QCommon;
import com.quick.core.util.common.QCookie;
import com.quick.portal.section.ISectionService;
import com.quick.portal.security.authority.metric.MetricPrivilegeConstants;
import com.quick.portal.security.authority.metric.PropertiesUtil;

/**
 * page请求类
 * @author 你自己的姓名
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/page")
public class PageController extends SysBaseController<PageDO> {
    
    @Resource(name = "pageService")
    private IPageService pageService;

    @Resource(name = "sectionService")
    private ISectionService sectionService;
    
    @Override
    public ISysBaseService getBaseService(){
        return pageService;
    }
    
    //页面请求
    @RequestMapping
    public String list(ModelMap model) {
        return view();
    }
    @RequestMapping
    public String edit(ModelMap model) {
    	String url = PropertiesUtil.getPropery("index.service.url");
      	String port = PropertiesUtil.getPropery("index.service.port");
    	String urlSet = url.concat(MetricPrivilegeConstants.SERVICE_PORT).concat(port).concat(MetricPrivilegeConstants.GET_METRIC_SERVICE_NAME);
    	String urlShow = url.concat(MetricPrivilegeConstants.SERVICE_PORT).concat(port).concat(MetricPrivilegeConstants.GET_MEASURES_SERVICE_NAME);
    	model.addAttribute("urlSet", urlSet);
    	model.addAttribute("urlShow", urlShow);
        Integer page_id = rint("page_id");
        Object layoutJson = getLayoutJson(page_id);
        Object metricJson = getMetricJson(page_id);
        model.addAttribute("layout", layoutJson);
        model.addAttribute("metric", metricJson);
        return view();
    }
    @RequestMapping
    public String show(ModelMap model) {
        Integer page_id = rint("p");
        //Object layout = getLayoutJson(page_id);
        //model.addAttribute("layout", layout);
      	String url = PropertiesUtil.getPropery("index.service.url");
      	String port = PropertiesUtil.getPropery("index.service.port");
      	String serviceUrl = url.concat(MetricPrivilegeConstants.SERVICE_PORT).concat(port).concat(MetricPrivilegeConstants.GET_MEASURES_SERVICE_NAME);
      	model.addAttribute("MEASURES_URL", serviceUrl);
        return view();
    }
    @RequestMapping
    public String setting(ModelMap model) {
    	String url = PropertiesUtil.getPropery("index.service.url");
      	String port = PropertiesUtil.getPropery("index.service.port");
      	String serviceUrl = url.concat(MetricPrivilegeConstants.SERVICE_PORT).concat(port).concat(MetricPrivilegeConstants.GET_METRIC_SERVICE_NAME);
      	model.addAttribute("METRIC_URL", serviceUrl);
        return view();
    }
    @RequestMapping
    public String chose(ModelMap model) {
        return view();
    }

    @Override
    public DataStore save(PageDO model){
    	//获取当前用户id
    	 String user_id = QCookie.getValue(request, "sbd.user_id");
        Integer app_id = rint("app_id");
        Integer page_id = model.getPage_id();
        if(page_id == null){
            model.setUser_id(loginer.getUser_id());
        }
        String section_json = rstr("section_json");
        
        String metric_json = rstr("metric_json");
        return pageService.save(model, app_id, section_json,metric_json,user_id);
    }

    /**
     * 查询用户所有应用
     * @return
     */
    @PostMapping
    @ResponseBody
    public Object getLayoutJson(Integer page_id){
        String layout = "[{x: 0, y: 0, width: 12, height: 6, id:0, no:1}]";
        if(page_id != null && page_id > 0){
            String res = sectionService.selectSectionJson(page_id);
            if(!QCommon.isNullOrEmpty(res))
                layout = res;
        }
        return layout;
    }
    @PostMapping
    @ResponseBody
    public Object getMetricJson(Integer page_id){
    	String user_id = rstr("u", loginer.getUser_id().toString());
    	String time = rstr("time","").toString();
        String json = "[]";
        if(page_id != null && page_id > 0){
            String res = sectionService.selectMetricJson(page_id,Integer.parseInt(user_id),time);
            if(!QCommon.isNullOrEmpty(res))
                json = res;
        }
        return json;
    }

}
