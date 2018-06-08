/**
 * <h3>标题 : potal统一门户-application </h3>
 * <h3>描述 : application请求类</h3>
 * <h3>日期 : 2018-04-13</h3>
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
package com.quick.portal.application;

import com.quick.core.base.ISysBaseService;
import com.quick.core.base.SysBaseController;
import javax.annotation.Resource;
import javax.faces.application.Application;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quick.core.base.model.DataStore;
import com.quick.core.base.model.JsonDataGrid;
import com.quick.core.base.model.PageBounds;
import com.quick.core.util.common.QCommon;
import com.quick.core.util.type.TypeUtil;
import com.quick.portal.appClassRela.AppClassRelaDO;
import com.quick.portal.appClassRela.IAppClassRelaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * application请求类
 * @author 你自己的姓名
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/application")
public class ApplicationController extends SysBaseController<ApplicationDO> {
    
    @Resource(name = "applicationService")
    private IApplicationService applicationService;

    @Resource(name = "appClassRelaService")
    private IAppClassRelaService appClassRelaService;
    
    @Override
    public ISysBaseService getBaseService(){
        return applicationService;
    }
    
    //页面请求
    @RequestMapping
    public String list(ModelMap model) {
        return view();
    }
    @RequestMapping
    public String edit(ModelMap model) {
        return view();
    }
    @RequestMapping
    public String chose(ModelMap model) {
        return view();
    }
    @RequestMapping
    public String bizlist(ModelMap model) {
        return view();
    }
    @RequestMapping
    public String bizedit(ModelMap model) {
        return view();
    }

    @Override
    public DataStore save(ApplicationDO model) {
        //保存应用
        ActionMsg = getBaseService().save(model);
        //保存应用分类
        String acids = rstr("app_class_id");
        String rids = rstr("rel_id");
        if(!QCommon.isNullOrEmpty(acids)){
            appClassRelaService.deleteByAppId(model.getApp_id().toString());
            String[] rel_ids = rids.split(",");
            String[] app_class_ids = acids.split(",");
            for(int i = 0; i < app_class_ids.length; i++){
                AppClassRelaDO m = new AppClassRelaDO();
                if(i < rel_ids.length)
                    m.setRel_id( (Integer)TypeUtil.parse(Integer.class, rel_ids[i]) );
                m.setApp_class_id( (Integer)TypeUtil.parse(Integer.class, app_class_ids[i]) );
                m.setApp_id(model.getApp_id());

                appClassRelaService.save(m);
            }
        }


        return ActionMsg;
    }

    @RequestMapping(value = "/getBizList", method = RequestMethod.POST)
    @ResponseBody
    public Object getBizList() throws Exception {
        return getQuery("is_app_page=1");
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public DataStore update(Integer app_id){
        if(app_id == null || app_id == 0)
            return  ActionMsg.setError("应用不存在");
        ApplicationDO model = applicationService.selectObj(app_id.toString());
        model.setPub_date(new Date());
        ActionMsg = applicationService.save(model);
        return ActionMsg;
    }
}