package com.quick.portal.web.login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quick.portal.sysUser.SysUserDO;
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
    @Resource(name = "userAccessLogService")
    private IUserAccessLogService userAccessLogService;


    //登录页
    @RequestMapping(value = "/")
    public String index(HttpServletRequest request, HttpServletResponse response) {
        WebLoginUser loginer = new WebLoginUser().loadSession(request, response);

        if (null == loginer.getUser_id() || "0".equals(loginer.getUser_id().toString())) {
            loginer = loadPortalUserInfo(request, response);
            if (null == loginer) {
                request.getSession().setAttribute("code", ExceptionEnumServiceImpl.USER_ROLE_ISVALID.getCode());
                request.getSession().setAttribute("message", ExceptionEnumServiceImpl.USER_ROLE_ISVALID.getMessage());
                logger.error("Can't get user information from user profile and user database.");
                return WebLoginConstants.REDIRECT_KEY.concat(WebLoginConstants.COMMON_ERROR_CONTROLLER);
            }
        }

        if (null != loginer.getUser_state() && loginer.getUser_state() == WebLoginConstants.DISABLE_USER_STATE) {
            request.getSession().setAttribute("code", ExceptionEnumServiceImpl.USER_STATUS_LOCKING.getCode());
            request.getSession().setAttribute("message", loginer.getUser_name() + ":" + ExceptionEnumServiceImpl.USER_STATUS_LOCKING.getMessage());
            return WebLoginConstants.REDIRECT_KEY.concat(WebLoginConstants.COMMON_ERROR_CONTROLLER);
        } else {
            loginer.setRequestSerial(1);
            loginer.saveSession(request, response);
            userAccessLogService.saveLog(request, UserAccessLogConstants.SYS_LOG_TYPE_ID, UserAccessLogConstants.LOGIN_USER_OP_TYPE, 1, loginer.getUser_real_name() + "登录成功", loginer.getUser_id().toString(), loginer.getUser_name());
            //平台用户:1:app;2:sys;公服用户:1:app
            String flag = WebLoginUitls.getUserHomePage(loginer);
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
        String userId = QCookie.getValue(request, "sbd.user_id");
        if (userId != null && userId.length() > 0)
            userAccessLogService.saveLog(request,
                    UserAccessLogConstants.SYS_LOG_TYPE_ID,
                    UserAccessLogConstants.LOGOUT_USER_OP_TYPE,
                    UserAccessLogConstants.FUN_MENU_ID,
                    UserAccessLogConstants.LOGOUT_USER_OP_DESC,
                    userId, "");

        QCookie.remove(response, request, "sbd.user_id");
        QCookie.remove(response, request, "sbd.user_real_name");
        QCookie.remove(response, request, "sbd.user_role");
        QCookie.remove(response, request, "sbd.gid");
        QCookie.remove(response, request, "sbd.user_name");
        QCookie.remove(response, request, "sbd.tk");
        QCookie.remove(response, request, "request.serial");
        QCookie.remove(response, request, "sbd.ustate");
        request.getSession().invalidate();

        String casUrl = PropertiesUtil.getPropery("sso.cas.server.prefixUrl");
        String url = request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort() + request.getContextPath()
                + "/";

        return "redirect:".concat(casUrl).concat("/logout?service=").concat(QCommon.urlEncode(url));
    }

    private WebLoginUser loadPortalUserInfo(HttpServletRequest request, HttpServletResponse response) {
        String account = null;
        List<CommonProfile> profiles = WebLoginUitls.getProfiles(request, response);
        for (CommonProfile profile : profiles) {
            account = profile.getId();
        }
        if (null != account && !"".equals(account)) {
            Map<String, Object> parm = new HashMap<>();
            parm.put("user_name", account);
            List<SysUserDO> uList = sysUserService.getUserInfo(parm);
            if (null == uList || uList.isEmpty()) {
                return null;
            }

            SysUserDO u = uList.get(0);

            WebLoginUser user = new WebLoginUser(u);
            user.saveSession(request, response);//保存至本地
            return user;
        }
        return null;
    }


}
