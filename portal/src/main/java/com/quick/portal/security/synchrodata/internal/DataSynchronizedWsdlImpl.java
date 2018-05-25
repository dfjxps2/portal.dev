package com.quick.portal.security.synchrodata.internal;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.quick.portal.security.synchrodata.bjcadata.SynchronizedDataConstants;


@WebService(serviceName = "UserDataSync")
public class DataSynchronizedWsdlImpl implements IDataSynchronizedWsdl {
	
	@Autowired
	private IDataSynchronizedService userDataSyncService;

	
	/*
	 * 同步单个用户数据
	 */
	@Override
	public String getUsersDataByIDOrName(@WebParam(name = "userName") String userName) {
		String str = null;
		if(null == userName || "".equals(userName)){
			str = Dom4jUtil.creatErrXmlFile(SynchronizedDataConstants.FAIL_STATUS,SynchronizedDataConstants.PARAM_ISNULL_FAIL_MSG);
		}else{
			str = userDataSyncService.getUsersDataByIDOrName(userName);
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

}
