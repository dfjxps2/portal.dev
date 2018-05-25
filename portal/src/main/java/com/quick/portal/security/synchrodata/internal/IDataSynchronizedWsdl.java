package com.quick.portal.security.synchrodata.internal;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface IDataSynchronizedWsdl {
	
	//同步单个用户数据
	public String getUsersDataByIDOrName(@WebParam(name = "userName") String userName);
	
	//同步批量用户数据
	public String getAllUserData(); 
}
