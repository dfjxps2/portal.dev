
package com.quick.portal.web.login;

/**
 * 
 * 修改备注：
 * 
 * @version
 *
 */
public class WebLoginConstants {

    public static final String COMMON_ERROR_CONTROLLER = "/lock/error";
    public static final String COMMON_INFO_CONTROLLER = "/lock/info";
    public static final String PAGE_ERROR_URL = "page/error/error";
    public static final String PAGE_INFO_URL = "page/error/info";
    public static final String USER_STATUS_WARING_PREFIX = "输错密码次数太多，还可以";
    public static final String USER_STATUS_WARING_SUFFIX = "次尝试后账户将被锁定";
    public static final String FORBIDDEN_USER_STATE = "0";   
    public static final String REDIRECT_KEY = "redirect:";
    
    
    public static final String MAINFRAME_URL = "/mainframe";
    public static final String MAIN_URL = "/home/main";
    

    public final static String SYS_MENU_FLAG = "2";
    public final static String APP_MENU_FLAG = "1";
    
    //啟用狀態
    public final static int ENABLED_USER_STATE = 1;
    //禁用狀態
    public final static int DISABLE_USER_STATE = 0;
    //角色類型 ：超级管理员
    public final static String ADMINISTRATOR_ROLE_TYPE = "1";
    //角色類型 ：平台管理员
    public final static String PORTAL_ROLE_TYPE = "2";
    //角色類型 ：业务部门用户
    public final static String BUSINESS_ROLE_TYPE = "3";
    
	public static final String PORTAL_ZORE_VAL = "0";
    

}

