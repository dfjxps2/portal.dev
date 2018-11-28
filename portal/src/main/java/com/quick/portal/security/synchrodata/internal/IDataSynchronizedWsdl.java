package com.quick.portal.security.synchrodata.internal;

import javax.jws.WebService;

@WebService
public interface IDataSynchronizedWsdl {

	/*
	 * 通过应用编号、用户帐号获取单个用户数据接口，返回报文数据
	 */
	public String  getUsersDataByUserID(String appID,String userID);
	
	/*
	 * 通过应用编号获取该应用系统下所有的用户应用权限数据接口，返回报文数据
	 */
	public String getUserBatchDataByAppName(String appID,
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
