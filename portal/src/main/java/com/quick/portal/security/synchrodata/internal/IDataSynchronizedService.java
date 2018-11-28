package com.quick.portal.security.synchrodata.internal;


public interface IDataSynchronizedService {

	/*
	 * 通过应用名称、用户帐号获取单个用户数据接口，返回报文数据
	 */
	public String  getUsersDataByUserID(String appName,String userID);
	
	/*
	 * 通过应用名称获取该应用系统下所有的用户应用权限数据接口，返回报文数据
	 */
	public String getUserBatchDataByAppName(String appName,
			String startdt, String enddt);



	/*
	 * 通过用户帐号获取机构数据接口，返回报文数据
	 */
	public String  getOrgDataByUserID(String userID);

	/*
	 * 批量获取机构数据接口，返回报文数据
	 */
	public String getOrgBatchData();


		
   
}
