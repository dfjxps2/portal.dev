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
import com.quick.core.base.model.PageBounds;
import com.quick.portal.userDepartment.UserDepartmentDO;
import com.quick.portal.userJob.UserJobDO;
import com.quick.portal.userRoleRela.UserRoleRelaDO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * sys_user数据访问接口
 */
public interface ISysUserDao<SysUserDO> extends ISysBaseDao<SysUserDO> {
    int updatePassword(SysUserDO password);
    String selectUserId();
    UserRoleRelaDO getUserRoleRe(String urrid,String roleId);
    List<Map<String,Object>> getAllUser(Map<String, Object> m, PageBounds page);
    List<Map<String,Object>> selectByName(String username);
    int deleteUserDepRela(String relaid);
    int updateUserDepRela(SysUserDO rela);
    void insertUserDepRela(SysUserDO userDO);
    //根据部门查询计算总条数
    int recount(String depid);
    int modifyPwdByApp(SysUserDO sysDO);
    
    List<Map<String,Object>> selectRole(Map<String,Object> p);
    List<Map<String,Object>> selectDep(Map<String,Object> p);
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


    Map<String,Object> getUserEdit(Map<String,Object> p);
    void  updateUserRole(Map<String,Object> p);
    void deleteUserRole(String id);
    void addRoleUsers(ArrayList<Object> list);

    List<SysUserDO> getUserInfo(Map<String, Object> m);
}