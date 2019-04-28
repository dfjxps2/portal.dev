/**
 * <h3>标题 : potal统一门户-sys_user </h3>
 * <h3>描述 : sys_user服务接口</h3>
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

import java.util.List;
import java.util.Map;

import com.quick.core.base.ISysBaseService;
import com.quick.core.base.model.DataStore;
import com.quick.core.base.model.PageBounds;


/**
 * sys_user服务接口
 */
public interface ISysUserService extends ISysBaseService<SysUserDO> {
	
	/*
	 * 锁定用户帐号
	 */
	void updateUserStatueByUersId (String userId);
	
	/*
	 * 查询指定IP,密码错误次数
	 */
	Map<String,Object> getLockCount(String ip);
	
	/*
		通过用户名称查询用户信息
	 * 
	 */
	Map<String,Object> isExitUserInfoByUserId(String userId);


	List<SysUserDO> getUserInfo(Map<String, Object> m);

    DataStore updatePassword(SysUserDO sysUserDO);

    /*
     * 记录用户登录日志
     */
    void userAuthLog(String ipAddr, String userId, String authResult);
}