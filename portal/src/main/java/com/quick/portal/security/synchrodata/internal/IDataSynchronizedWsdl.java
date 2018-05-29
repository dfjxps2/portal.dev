package com.quick.portal.security.synchrodata.internal;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface IDataSynchronizedWsdl {
	
	//同步单个用户数据
	public String getUsersDataByUserID(@WebParam(name = "userID") String userID);
	
	//同步批量用户数据
	public String getAllUserData(); 
	
	//同步菜单权限数据
	public String getMenuPrivilegeByUserID(String userID);
	

}
