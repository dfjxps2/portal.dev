package com.quick.portal.security.synchrodata.internal;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.quick.portal.security.synchrodata.bjcadata.SynchronizedDataConstants;


@WebService(serviceName = "intlDataSynchronizedService")
public class DataSynchronizedWsdlImpl implements IDataSynchronizedWsdl {
	
	@Autowired
	private IDataSynchronizedService userDataSyncService;

	
	/*
	 * 同步单个用户数据
	 */
	@Override
	public String getUsersDataByUserID(@WebParam(name = "userID") String userID) {
		String str = null;
		if(null == userID || "".equals(userID)){
			str = Dom4jUtil.creatErrXmlFile(SynchronizedDataConstants.FAIL_STATUS,SynchronizedDataConstants.PARAM_ISNULL_FAIL_MSG);
		}else{
			str = userDataSyncService.getUsersDataByUserID(userID);
		}
		return str;
	}
	
	
	/*
	 * 同步批量用户数据
	 */
	@Override
	public String getAllUserData() {
		String str = userDataSyncService.getAllUserData();
		return str;
	}

	
	/*
	 * 同步菜单权限数据
	 * (non-Javadoc)
	 * @see com.quick.portal.security.synchrodata.internal.IDataSynchronizedWsdl#getMenuPrivilegeByUserID(java.lang.String)
	 */
	@Override
	public String getFunPrivilegeByUserID(String userID) {
		String str = null;
		if(null == userID || "".equals(userID)){
			str = Dom4jUtil.creatErrXmlFile(SynchronizedDataConstants.FAIL_STATUS,SynchronizedDataConstants.USERID_PARAM_ISNULL_FAIL_MSG);
		}else{
			str = userDataSyncService.getFunPrivilegeByUserID(userID);
		}
		return str;
	}

}
