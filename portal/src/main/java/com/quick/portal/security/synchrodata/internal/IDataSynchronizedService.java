package com.quick.portal.security.synchrodata.internal;


public interface IDataSynchronizedService {
/*	//同步单个用户数据
	public String getUsersDataByUserID(String userName);
	//同步批量用户数据
	public String getAllUserData();
		
	//同步菜单权限数据
	public String getFunPrivilegeByUserID(String userID);*/
	
	
	/*
	 * 通过应用编号、用户帐号获取单个用户数据接口，返回报文数据
	 */
	public String  getUsersDataByUserID(String appID,String userID);
	
	/*
	 * 通过应用编号获取该应用系统下所有的用户应用权限数据接口，返回报文数据
	 */
	public String getUserApplicationPrivilegeByAppID(String appID,
			String startdt, String enddt);

		
   
}
