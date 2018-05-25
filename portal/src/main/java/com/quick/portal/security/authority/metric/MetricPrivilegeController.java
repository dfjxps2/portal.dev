/**
 * <h3>标题 : potal统一门户-metric_privilege </h3>
 * <h3>描述 : metric_privilege请求类</h3>
 * <h3>日期 : 2018-05-3</h3>
 * <h3>版权 : Copyright (C) 北京东方金信科技有限公司</h3>
 * 
 * <p>
 * @author 你自己的姓名 cxh@seaboxdata.com
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
package com.quick.portal.security.authority.metric;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.quick.core.base.ISysBaseService;
import com.quick.core.base.SysBaseController;
import com.quick.core.util.common.QCookie;

/**
 * metricPrivilege请求类
 * @author 你自己的姓名
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/metricPrivilege")
public class MetricPrivilegeController extends SysBaseController<MetricPrivilegeDO> {
    
    @Resource(name = "metricPrivilegeService")
    private IMetricPrivilegeService metricPrivilegeService;
    
    @Override
    public ISysBaseService getBaseService(){
        return metricPrivilegeService;
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
    
    /*
     *保存指标权限 
     */
    
    @RequestMapping(value = "/saveMetricData",method = RequestMethod.POST)
    @ResponseBody
    public String saveMetricData(String metricData) throws Exception {
    	String flag = "1";
    	//当前用户编号
    	String userID = QCookie.getValue(request, "ids");
    	boolean isSolr = MetricPrivilegeUtils.isExpriationTime(userID);
    	if(! isSolr){
    		 return flag;
    	}
    	try {
    		metricData = URLDecoder.decode(metricData, MetricPrivilegeConstants.LANGUAGE_CODE_UTF);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new Exception("指标转义异常："+e.getLocalizedMessage());
		}
        JSONArray jsonObject = (JSONArray) JSONObject.parse(metricData);
        List<MetricBean> retList = jsonObject.toJavaList(MetricBean.class);
        metricPrivilegeService.saveMetricData(retList);
        return flag;
    }
    
    
    /*
     * 指标信息查询
     */
    
    @RequestMapping(value = "/listAllMetric")
    @ResponseBody
    public List<Map<String,Object>> listAllMetric(){
        return metricPrivilegeService.listAllMetric();
    }
    
    /*
     * 通过角色查询指标权限
     */
    
    @RequestMapping(value = "/listMetricPrivilege")
    @ResponseBody
    public List<Map<String,Object>> listMetricPrivilege(String roleId){
        return metricPrivilegeService.listMetricPrivilege(roleId);
    };
    
    /*
     *设置指标权限 
     */
    
    @RequestMapping(value = "/saveMetricPrivilege")
    public String saveMetricPrivilege(String roleId,String ids){
        JSONArray jsonObject= (JSONArray) JSONObject.parse(ids);
        List<String> metricList = jsonObject.toJavaList(String.class);
        metricPrivilegeService.saveMetricPrivilege(roleId,metricList);
        return "page/role/list";
    }
}