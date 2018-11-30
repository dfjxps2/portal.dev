package com.quick.portal.security.synchrodata.internal;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.quick.portal.security.synchrodata.bjcadata.SynchronizedDataConstants;


@WebService(serviceName = "intlDataSynchronizedService")
public class DataSynchronizedWsdlImpl implements IDataSynchronizedWsdl {
	
	@Autowired
	private IDataSynchronizedService userDataSyncService;

	
	/*
	 * 通过应用名称、用户帐号获取单个用户数据接口，返回报文数据
	 */
	@Override
	public String getUsersDataByUserID(String appName, String userID) {
		// TODO Auto-generated method stub
		String str = userDataSyncService.getUsersDataByUserID(appName,userID);
		return str;
	}
	

	/*
	 * 通过应用名称获取该应用系统下所有的用户应用权限数据接口，返回报文数据
	 */

	@Override
	public String getUserBatchDataByAppName(String appName,
			String startdt, String enddt) {
		// TODO Auto-generated method stub
		String str = userDataSyncService.getUserBatchDataByAppName(appName,startdt,enddt);
		return str;
	}

	
	/*
	 * 通过用户帐号获取机构数据接口，返回报文数据
	 */
	@Override
	public String getOrgDataByUserID(String userID) {
		String str = null;
		if(null == userID || "".equals(userID)){
			str = Dom4jUtil.creatErrXmlFile(SynchronizedDataConstants.FAIL_STATUS,SynchronizedDataConstants.PARAM_ISNULL_FAIL_MSG);
		}else{
			str = userDataSyncService.getOrgDataByUserID(userID);
		}
		return str;
	}
	

	
	/*
	 * 批量获取机构数据接口，返回报文数据
	 * (non-Javadoc)
	 * @see com.quick.portal.security.synchrodata.internal.IDataSynchronizedWsdl#getMenuPrivilegeByUserID(java.lang.String)
	 */
	@Override
	public String getOrgBatchData() {
		String str = userDataSyncService.getOrgBatchData();
		return str;
	}

}
