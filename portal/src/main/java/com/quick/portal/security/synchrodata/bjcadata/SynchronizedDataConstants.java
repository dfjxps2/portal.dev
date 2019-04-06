package com.quick.portal.security.synchrodata.bjcadata;




/**
 * 
 * 修改备注：    
 * @version     
 *
 */
public class SynchronizedDataConstants {
	 
	 //成功
	 public static final String SUCCESS_STATUS = "1";
	 //失败
	 public static final String FAIL_STATUS = "0";
	 
	 public static final String ZERO_TOTAL = "0";
	 
	 public static final String PARAM_ISNULL_FAIL_MSG = "用户帐号或者用户名称参数为空，请核对输入条件";
	 
	 public static final String DATA_ISNULL_FAIL_MSG = "查询无数据，请核对输入条件";
	 
	 public static final String USERID_PARAM_ISNULL_FAIL_MSG = "用户帐号参数为空，请核对输入条件";


	 //默认角色
	 public static final int DEFAULT_ROLE_ID = 2;

	 //默认岗位
	 public static final int DEFAULT_JOB_ID = 9;

	 //默认资源
	 public static final int DEFAULT_SYS_PRIV_ID = 4;

	 //默认用户启用状态
	 public static final int DEFAULT_USER_ENABLE_STAT = 1;

	//证书用户类型
	public static final String CRED_USER_TYPE = "CREDENCE_00001";
	//口令用户类型
	public static final String PWD_USER_TYPE = "CREDENCE_00002";
	//证书用户默认密码
	public static final String CRED_DEFAULT_USER = "123456";
	//证书用户NICKNAME默认前缀
	public static final String CRED_DEFAULT_LOGIN_NICK_NAME = "cert_nick";

}
