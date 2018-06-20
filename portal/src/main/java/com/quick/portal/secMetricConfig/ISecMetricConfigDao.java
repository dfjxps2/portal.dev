/**
 * <h3>标题 : potal统一门户-user_access_log </h3>
 * <h3>描述 : user_access_log数据访问接口</h3>
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
package com.quick.portal.secMetricConfig;

import com.quick.core.base.ISysBaseDao;

import java.util.List;
import java.util.Map;

/**
 * sec_metric_config数据访问接口
 */
public interface ISecMetricConfigDao<SecMetricConfigDO> extends ISysBaseDao<SecMetricConfigDO> {
   int  selectUAC( Map<String, Object> queryMap ); //查找指定配置
    int  insertUAC( Map<String, Object> queryMap );
    int  updateUAC_Active( Map<String, Object> queryMap );
    int  updateUAC_Version( Map<String, Object> queryMap );
    
    int  deleteBySectionId(String sec_metric_id );
    
    int  insertUAC_Version(UserActiveConfigDo usDO);
    
    int  updateUserActive(Map<String, Object> map);
    
    List<Map<String,Object>>  selectTime(Integer user_id);

}
