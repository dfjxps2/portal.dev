/**
 * <h3>标题 : Quick通用系统框架 </h3>
 * <h3>描述 : 应用中的相关配置信息都放在此</h3>
 * <h3>日期 : 2014-03-23</h3>
 * <h3>版权 : Copyright (C) 海口鑫网计算机网络有限公司</h3>
 *
 * <p>
 *
 * @author admin admin@xinwing.com.cn
 * @version <b>v1.0.0</b>
 *
 * <b>修改历史:</b>
 * ------------------------------------------- 修改人 修改日期 修改描述
 * -------------------------------------------
 *
 *
 * </p>
 */
package com.quick.portal.web.login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pac4j.core.profile.CommonProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quick.core.base.AppResource;
import com.quick.core.base.exception.ExceptionEnumServiceImpl;
import com.quick.core.util.common.QCommon;
import com.quick.core.util.common.QCookie;
import com.quick.portal.security.authority.metric.PropertiesUtil;
import com.quick.portal.sysUser.ISysUserService;
import com.quick.portal.userAccessLog.IUserAccessLogService;
import com.quick.portal.userAccessLog.UserAccessLogConstants;
import com.quick.portal.userRoleRela.IUserRoleRelaService;

/**
 * 身份验证
 *
 * @author Administrator
 */
@Controller
@Scope("prototype")
public class WebLoginController {

    protected static final String LOGOUT_URL = "/home/logout";
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource(name = "sysUserService")
    private ISysUserService sysUserService;
    @Resource(name = "userRoleRelaService")
    private IUserRoleRelaService userRoleRelaService;
    @Resource(name = "userAccessLogService")
    private IUserAccessLogService userAccessLogService;


    //登录页
    @RequestMapping(value = "/")
    public String index(HttpServletRequest request, HttpServletResponse response) {
        WebLoginUser loginer = new WebLoginUser().loadSession(request, response);
        String userGlobalID = null;
        if (null ==loginer.getRole_type_ids() || "0".equals(loginer.getRole_type_ids())) {
            loginer = loadCASUserInfo(request, response);
            if (null == loginer) {
            	request.getSession().setAttribute("code", ExceptionEnumServiceImpl.USER_ROLE_ISVALID.getCode());
            	request.getSession().setAttribute("message", ExceptionEnumServiceImpl.USER_ROLE_ISVALID.getMessage());
                logger.error("Can't get user information from user profile and user database.");
                return WebLoginConstants.REDIRECT_KEY.concat(WebLoginConstants.COMMON_ERROR_CONTROLLER);
            } else {
            	userGlobalID = loginer.getUser_global_id();
            }
        }
        String rType = String.valueOf(loginer.getRole_type_ids());
        if(null !=loginer.getUser_state() && loginer.getUser_state() == WebLoginConstants.DISABLE_USER_STATE){
        	request.setAttribute("code", ExceptionEnumServiceImpl.USER_STATUS_LOCKING.getCode());
        	request.setAttribute("message", loginer.getUser_name()+":"+ExceptionEnumServiceImpl.USER_STATUS_LOCKING.getMessage());
        	 return WebLoginConstants.REDIRECT_KEY.concat(WebLoginConstants.COMMON_ERROR_CONTROLLER);
    	}else{
	        loginer.setRequestSerial(1);
	        loginer.saveSession(request, response);
	        userAccessLogService.saveLog(request, UserAccessLogConstants.SYS_LOG_TYPE_ID, UserAccessLogConstants.LOGIN_USER_OP_TYPE, 1, loginer.getUser_real_name() + "登录成功", loginer.getUser_id().toString(), loginer.getUser_name());
	        //平台用户:1:app;2:sys;公服用户:1:app
	        String flag = getSysUrlByUserGlobalID(userGlobalID, rType);
	        if (WebLoginConstants.SYS_MENU_FLAG.equals(flag)) {
	        	return WebLoginConstants.REDIRECT_KEY.concat(WebLoginConstants.MAINFRAME_URL);
	        } else {
	        	return WebLoginConstants.REDIRECT_KEY.concat(WebLoginConstants.MAIN_URL);
	        }
    	}
    }


    @RequestMapping(value = "/home/login")
    public String login(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        String url = request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort() + request.getContextPath();
        model.addAttribute("host", url);
        return "page/home/login";
    }



    @RequestMapping(value = "/home/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        logger.info("user logout.");
        //记录日志
        String ids = QCookie.getValue(request, "ids");
        if (ids != null && ids.length() > 0)
            userAccessLogService.saveLog(request, UserAccessLogConstants.SYS_LOG_TYPE_ID, UserAccessLogConstants.LOGOUT_USER_OP_TYPE, UserAccessLogConstants.FUN_MENU_ID, UserAccessLogConstants.LOGOUT_USER_OP_DESC, ids, "");
        QCookie.remove(response, request, AppResource.COOKIE_LOGINER);
        QCookie.remove(response, request, "ids");
        QCookie.remove(response, request, "sbd.user");
        QCookie.remove(response, request, "sbd.role");
        QCookie.remove(response, request, "sbd.gid");
        QCookie.remove(response, request, "sbd.uid");
        QCookie.remove(response, request, "sbd.tk");
        QCookie.remove(response, request, "request.serial");
        QCookie.remove(response, request, "sbd.ustate");
        QCookie.remove(response, request,"sbd.rtype");
        request.getSession().invalidate();
        String casUrl = PropertiesUtil.getPropery("sso.cas.server.prefixUrl");
        String url = request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort() + request.getContextPath()
                + "/";
        String retUrl = "redirect:".concat(casUrl).concat("/logout?service=").concat(QCommon.urlEncode(url));
        return retUrl;
    }


    public WebLoginUser loadCASUserInfo(HttpServletRequest request, HttpServletResponse response) {
		String account = null;
		List<CommonProfile> profiles = WebLoginUitls.getProfiles(request,
				response);
		for (CommonProfile profile : profiles) {
			account = profile.getId();
		}
		if (null != account && !"".equals(account)) {
			Map<String, Object> parm = new HashMap<>();
			parm.put("user_name", account);
			Map<String, Object> u = sysUserService.selectMap(parm);
			if (null == u || u.isEmpty()) {
				return null;
			}
			parm.put("user_id", u.get("user_id"));
			List<Map<String, Object>> roles = userRoleRelaService.select(parm);
			WebLoginUser user = WebLoginUitls.getLoginUser(u, roles);
			user.saveSession(request, response);// 保存至本地
			return user;
		}
		return null;
    }


    /*
     *rType --1: 超级管理员类型;2:平台管理员类型;3:业务部门用户类型
     * 
     * 平台用户:1:app;2:sys
     * 公服用户:1:app
     * 1:app;2:sys
     */
    public String getSysUrlByUserGlobalID(String userGlobalID, String rType) {
        String flag = null;
        //平台用户
        if (null == userGlobalID || "".equals(userGlobalID)) {
            boolean bool = WebLoginUitls.isAdminRoleType(rType);
            if(bool){
            	  flag = WebLoginConstants.SYS_MENU_FLAG;
            }else{
            	 flag = WebLoginConstants.APP_MENU_FLAG;
            }   
            //公服用户
        } else {
            flag = WebLoginConstants.APP_MENU_FLAG;
        }
        return flag;
    }
    
}
