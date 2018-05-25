/**
 * <h3>标题 : potal统一门户-menu_privilege </h3>
 * <h3>描述 : menu_privilege服务接口</h3>
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
package com.quick.portal.security.authority.metric;

import java.util.List;
import java.util.Map;

import com.quick.core.base.ISysBaseService;

/**
 * metric_privilege服务接口
 */
public interface IMetricPrivilegeService extends ISysBaseService<MetricPrivilegeDO> {
	
	/*
     * 指标信息查询
     */
    List<Map<String,Object>> listAllMetric();
    /*
     *设置指标权限 
     */
    void saveMetricPrivilege(String roleId, List<String> metricList);
    
    /*
     * 通过角色查询指标权限
     */
    List<Map<String,Object>> listMetricPrivilege(String roleId);
    
    void saveMetricData(List<MetricBean> retList);
}