/**
 * <h3>标题 : potal统一门户-sys_user </h3>
 * <h3>描述 : sys_user数据访问接口</h3>
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
package com.quick.portal.sysUser;

import com.quick.core.base.ISysBaseDao;

import java.util.List;
import java.util.Map;

/**
 * sys_user数据访问接口
 */
public interface ISysUserDao<SysUserDO> extends ISysBaseDao<SysUserDO> {
    int updatePassword(SysUserDO password);
    int modifyPwdByApp(SysUserDO sysDO);
    
    /*
	 * 锁定用户帐号
	 */
	void updateUserStatueByUersId (String userId);

	
	/*
	 * 查询指定IP,密码错误次数
	 */
	 List<Map<String,Object>> getLockCount(String ip);
	 
	 
	 /*
	  * 通过用户名称查询用户信息
	  */
	 List<Map<String,Object>> isExitUserInfoByUserId (String userId);


    List<SysUserDO> getUserInfo(Map<String, Object> m);

    int delete(SysUserDO sysUserDO);
}