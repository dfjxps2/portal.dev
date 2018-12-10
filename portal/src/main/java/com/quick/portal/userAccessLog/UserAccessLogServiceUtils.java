package com.quick.portal.userAccessLog;

import com.quick.core.util.common.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserAccessLogServiceUtils {

//    static Logger logger = LoggerFactory.getLogger(UserAccessLogServiceUtils.class);



    /**
     *  "功能细项为“记录功能”,功能描述为:在系统中记录对用户、
     *  用户组进行增加、删除、修改、授权等操作情况和服务系统、操作人等信息。记录的信息包括：
     * （1）系统操作员ID；
     * （2）被操作用户ID；
     * （3）被操作ID类型(精细化管理系统/基础库统计分析/资源门户(用户/用户组))；
     * （4）操作时间(YYYY-MM-DD HH-MM-SS)；
     * （5）操作描述(具体细节)。
     * "
     */
    public static void loggerLogInfo(Logger logger, String userName,String operatedUser,String operateType,String requestResult,String operLog,String serviceName,String ip) {
        String uName = UserAccessLogConstants.SUPERADMIN_DESC+userName;
        String clazz = Thread.currentThread().getStackTrace()[1].getClassName();
        String method = Thread.currentThread().getStackTrace()[1].getMethodName();
        String serName =serviceName+clazz+"->"+method;
        DateTime dt = new DateTime();
        String loginTime = dt.toString();

        if (logger.getName().endsWith("SysUserController")
                || logger.getName().endsWith("RoleController")
                || logger.getName().endsWith("SysPrivilegeController"))
            logger.info("[{}]:[{}]:[{}]:[{}]:[{}]",
                    userName, operatedUser, operateType, loginTime, serviceName);
        else
            logger.info("[{}]:[{}]:[{}]:[{}]:[{}]:[{}]:[{}]:[{}]:[{}]:[{}]",
                    userName, ip, serName, "统一门户系统", operateType, loginTime, loginTime, 1, "成功", requestResult);

//        logger.info(""+operLog+": add Backlog ->userName={}, operatedUser={},operateType={}, " +
//                        "loginTime={}, requestResult={}, serviceName={}, ipAdd={}"
//                , new Object[]{
//                        uName, operatedUser, operateType,loginTime,requestResult,serName,ip});
    }
}
