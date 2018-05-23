/**
 * <h3>标题 : potal统一门户-sys_menu </h3>
 * <h3>描述 : sys_menu服务接口</h3>
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
package com.quick.portal.sysMenu;

import java.util.List;
import java.util.Map;

import com.quick.core.base.ISysBaseService;
import com.quick.core.base.model.DataStore;

/**
 * sys_menu服务接口
 */
public interface ISysMenuService extends ISysBaseService<SysMenuDO> {

	DataStore deletes(List<String> menu_id, int state);
	
	  List<Map<String,Object>> listAllMenu();

	List<Map<String,Object>> getApp();
}