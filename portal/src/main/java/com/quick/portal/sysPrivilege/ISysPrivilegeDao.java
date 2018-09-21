/**
 * <h3>标题 : potal统一门户-menu_privilege </h3>
 * <h3>描述 : menu_privilege数据访问接口</h3>
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
package com.quick.portal.sysPrivilege;

import com.quick.core.base.ISysBaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * menu_privilege数据访问接口
 */
public interface ISysPrivilegeDao<SysPrivilegeDO> extends ISysBaseDao<SysPrivilegeDO> {
    List<Map<String,Object>> getPrivilegeForRole(Map<String, Object> params);
    int savePrivilegeForRole(@Param("role_id") Integer role_id, @Param("menuList") Integer[] menuList);
}