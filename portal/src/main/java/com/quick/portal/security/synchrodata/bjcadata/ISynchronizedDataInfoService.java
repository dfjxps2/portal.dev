package com.quick.portal.security.synchrodata.bjcadata;

/*
 * 接收公服系统数据接口
 * 
 */
public interface ISynchronizedDataInfoService  {
	
	public boolean SynchronizedUserInfo(int OperateID, String OperateCode,
			String OperateType) throws Exception;
	

/*	public boolean synchronizedDataInfo2(int operateID, String operateCode,
			String operateType) throws Exception;*/
}
