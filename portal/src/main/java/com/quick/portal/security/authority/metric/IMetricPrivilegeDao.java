/**
 * <h3>标题 : potal统一门户-metric_privilege </h3>
 * <h3>描述 : metric_privilege数据访问接口</h3>
 * <h3>日期 : 2018-04-13</h3>
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

import java.util.List;
import java.util.Map;

import com.quick.core.base.ISysBaseDao;

/**
 * metric_privilege数据访问接口
 */
public interface IMetricPrivilegeDao<MetricPrivilegeDO> extends ISysBaseDao<MetricPrivilegeDO> {
	
	/*
     * 指标信息查询
     */
    List<Map<String,Object>> listAllMetric();
    /*
     *设置指标权限 
     */
    void saveMetricPrivilege(Map<String, Object> paramMap);
    
    /*
     * 通过角色查询指标权限
     */
    List<Map<String,Object>> listMetricPrivilege(String roleId);
    /*
     * 通过角色查询删除指标权限
     */
    void removeMetricPriByRole(Map<String, Object> paramMap);
    /*
	 * 保存指标数据
	 */
    void saveMetricData(Map<String, Object>  paramMap);
    /*
	 * 判断源指标ID是否存在
	 */
    int isExistMetricByID(String id);
    /*
	 * 修改指标数据
	 */
    void updateMetricData(Map<String, Object>  paramMap);
    
    List<Map<String, Object>> searchMetricBySrcMetricId(String srcMetricId);
}