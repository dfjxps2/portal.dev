package com.quick.portal.security.synchrodata.internal;


public interface IDataSynchronizedService {
	//同步单个用户数据
	public String getUsersDataByUserID(String userName);
	//同步批量用户数据
	public String getAllUserData();
		
	//同步菜单权限数据
	public String getMenuPrivilegeByUserID(String userID);
		
   
}
