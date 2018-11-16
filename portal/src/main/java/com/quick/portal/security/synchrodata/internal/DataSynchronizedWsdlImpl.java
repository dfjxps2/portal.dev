package com.quick.portal.security.synchrodata.internal;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.quick.portal.security.synchrodata.bjcadata.SynchronizedDataConstants;


@WebService(serviceName = "intlDataSynchronizedService")
public class DataSynchronizedWsdlImpl implements IDataSynchronizedWsdl {
	
	@Autowired
	private IDataSynchronizedService userDataSyncService;

	
	/*
	 * 通过应用编号、用户帐号获取单个用户数据接口，返回报文数据
	 */
	@Override
	public String getUsersDataByUserID(String appID, String userID) {
		// TODO Auto-generated method stub
		String str = userDataSyncService.getUsersDataByUserID(appID,userID);
		return str;
	}
	

	/*
	 * 通过应用编号获取该应用系统下所有的用户应用权限数据接口，返回报文数据
	 */

	@Override
	public String getUserBatchDataByAppName(String appID,
			String startdt, String enddt) {
		// TODO Auto-generated method stub
		String str = userDataSyncService.getUserApplicationPrivilegeByAppID(appID,startdt,enddt);
		return str;
	}

	
	/*
	 * 同步单个用户数据
	 */
/*	@Override
	public String getUsersDataByUserID(String userID) {
		String str = null;
		if(null == userID || "".equals(userID)){
			str = Dom4jUtil.creatErrXmlFile(SynchronizedDataConstants.FAIL_STATUS,SynchronizedDataConstants.PARAM_ISNULL_FAIL_MSG);
		}else{
			str = userDataSyncService.getUsersDataByUserID(userID);
		}
		return str;
	}
	
	
	
	 * 同步批量用户数据
	 
	@Override
	public String getAllUserData() {
		String str = userDataSyncService.getAllUserData();
		return str;
	}

	
	
	 * 同步菜单权限数据
	 * (non-Javadoc)
	 * @see com.quick.portal.security.synchrodata.internal.IDataSynchronizedWsdl#getMenuPrivilegeByUserID(java.lang.String)
	 
	@Override
	public String getFunPrivilegeDataByUserID(String userID) {
		String str = null;
		if(null == userID || "".equals(userID)){
			str = Dom4jUtil.creatErrXmlFile(SynchronizedDataConstants.FAIL_STATUS,SynchronizedDataConstants.USERID_PARAM_ISNULL_FAIL_MSG);
		}else{
			str = userDataSyncService.getFunPrivilegeByUserID(userID);
		}
		return str;
	}*/

}
