/**
 * <h3>标题 : potal统一门户-metric </h3>
 * <h3>描述 : metric请求类</h3>
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
package com.quick.portal.metric;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quick.core.base.ISysBaseService;
import com.quick.core.base.SysBaseController;

/**
 * metric请求类
 * @author 你自己的姓名
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/metric")
public class  MetricController extends SysBaseController<MetricDO> {
    
    @Resource(name = "metricService")
    private IMetricService metricService;
    
    @Override
    public ISysBaseService getBaseService(){
        return metricService;
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
}