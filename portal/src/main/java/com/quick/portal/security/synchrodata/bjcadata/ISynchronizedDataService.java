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
package com.quick.portal.security.synchrodata.bjcadata;

import java.util.Collection;

import com.bjca.uums.client.bean.DepartmentInformation;
import com.bjca.uums.client.bean.LoginInformation;
import com.bjca.uums.client.bean.PersonInformation;
import com.bjca.uums.client.bean.RoleInformation;

/**
 * SynchronizedDataService服务接口
 */
public interface ISynchronizedDataService {
	/*
     * 机构数据同步
     */
	public boolean synchronizedDeptData(DepartmentInformation department,int operateID)throws Exception;
    /*
     *角色数据同步
     */
	public boolean synchronizedRoleData(RoleInformation roleInformation) throws Exception;
    
    /*
     * 用户数据同步
     */
	public boolean synchronizedPersonData(PersonInformation person,LoginInformation loginInformation,int operateID)throws Exception;
	/*
     * 机构数据批量同步
     */
	public boolean synchronizedBatchDeptData(Collection collection)throws Exception;
	

	
}