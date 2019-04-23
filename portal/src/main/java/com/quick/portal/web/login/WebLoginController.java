package com.quick.portal.web.login;

import cn.org.bjca.security.SecurityEngineDeal;
import com.quick.core.base.exception.ExceptionEnumServiceImpl;
import com.quick.core.util.common.CommonUtils;
import com.quick.core.util.common.QCommon;
import com.quick.core.util.common.QCookie;
import com.quick.portal.security.authority.metric.PropertiesUtil;
import com.quick.portal.sysUser.ISysUserService;
import com.quick.portal.sysUser.SysUserDO;
import com.quick.portal.userAccessLog.IUserAccessLogService;
import com.quick.portal.userAccessLog.UserAccessLogConstants;
import com.quick.portal.userAccessLog.UserAccessLogServiceUtils;
import com.seaboxdata.portal.auth.cert.CertUserProfile;
import org.json.JSONArray;
import org.json.JSONObject;
import org.pac4j.cas.client.rest.CasRestFormClient;
import org.pac4j.cas.config.CasConfiguration;
import org.pac4j.cas.profile.CasProfile;
import org.pac4j.cas.profile.CasRestProfile;
import org.pac4j.core.context.J2EContext;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.credentials.TokenCredentials;
import org.pac4j.core.profile.CommonProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private CasConfiguration casConfiguration;

    //登录页
    @RequestMapping(value = "/login")
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
            /**
             *  在系统中记录门户系统用户登录的情况包括如下字段：
             *  （1）用户名称；（2）用户IP；（3）服务名称；（4）开始处理时间；（5）处理结果；（6）请求服务的URL。这些字段有（4）开始处理时间；（5）处理结果；（6）请求服务的URL不支持
             */

            loggerInfoLoginSystemLogInfo(request, loginer);
            //平台用户:1:app;2:sys;公服用户:1:app
            String flag = WebLoginUitls.getUserHomePage(loginer);
            if (WebLoginConstants.SYS_MENU_FLAG.equals(flag)) {
                return WebLoginConstants.REDIRECT_KEY.concat(WebLoginConstants.MAINFRAME_URL);
            } else {
                return WebLoginConstants.REDIRECT_KEY.concat(WebLoginConstants.MAIN_URL);
            }
        }
    }

    @RequestMapping(value = "/gotoService")
    public String gotoService(HttpServletRequest request, HttpServletResponse response) {
        final WebContext context = new J2EContext(request, response);
        List<CommonProfile> profiles = WebLoginUitls.getProfiles(request, response);
        if (profiles.size() == 0) {
            logger.error("Can't get user profile.");
            return WebLoginConstants.REDIRECT_KEY.concat(WebLoginConstants.COMMON_ERROR_CONTROLLER);
        }

        String serviceURL = request.getParameter("serviceURL");

        if (profiles.get(0) instanceof CasRestProfile) {
            CasRestProfile profile = (CasRestProfile) profiles.get(0);

            CasRestFormClient casRestFormClient = new CasRestFormClient(casConfiguration, "", "");
            TokenCredentials tokenCredentials = casRestFormClient.requestServiceTicket(serviceURL, profile, context);

            return WebLoginConstants.REDIRECT_KEY.concat(serviceURL.concat("?ticket=" + tokenCredentials.getToken()));
        } else if (profiles.get(0) instanceof CasProfile) {
            return WebLoginConstants.REDIRECT_KEY.concat(serviceURL);
        } else {
            logger.error("Unexpected profile type of {}.", profiles.get(0).getClass().getName());
            return WebLoginConstants.REDIRECT_KEY.concat(WebLoginConstants.COMMON_ERROR_CONTROLLER);
        }
    }

    @RequestMapping(value = "/home/login")
    public String login(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        String url = request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort() + request.getContextPath();
        model.addAttribute("host", url);
        return "page/home/login";
    }

    @RequestMapping(value = "/")
    public String logon(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        String url = request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort() + request.getContextPath();
        model.addAttribute("host", url);
        return "page/home/logon";
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

        if (profiles.size() == 0)
            return null;

        CommonProfile profile = profiles.get(0);
        account = profile.getId();

        if (account == null || account.length() == 0) {
            logger.error("Can't get user id from profile.");
            return null;
        }

        if (profile instanceof CertUserProfile) {
            CertUserProfile certUserProfile = (CertUserProfile) profile;

            Map<String, Object> userAttrs = certUserProfile.getAttributes();

            if (!certUserProfile.getUniqueIdCode().equals(userAttrs.get("uniqueIdCode"))) {
                logger.error("User {} login failed, unique id code doesn't match.", account);
                return null;
            }
        }

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


    /**
     * 在系统中记录门户系统用户登录的情况包括如下字段：
     * （1）用户名称；（2）用户IP；（3）服务名称；（4）开始处理时间；（5）处理结果；
     * （6）请求服务的URL。这些字段有（4）开始处理时间；（5）处理结果；（6）请求服务的URL不支持
     */
    public void loggerInfoLoginSystemLogInfo(HttpServletRequest request, WebLoginUser loginer) {
        String ip = CommonUtils.getIpAddrAdvanced(request);
        String userName = loginer.getUser_name();
        String requestResult = "系统操作员:" + userName + "登录统一门户服务日志";
        String operateType = "统一门户->登录日志";
        String operatedUser = "系统操作员编号:" + loginer.getUser_id() + "系统操作员名称:" + loginer.getUser_name();
        String operLog = "用户登录统一门户服务日志->登录日志";
        String serviceName = "服务名称:登录日志;服务方法名:";
        UserAccessLogServiceUtils.loggerLogInfo(logger,
                userName, operatedUser, operateType, requestResult, operLog, serviceName, ip);
    }

    @RequestMapping(value = "/getCert")
    @ResponseBody
    public void getCert(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        SecurityEngineDeal sed = null;
        String msgC = null;
        HttpSession session = request.getSession();
        try {
            Map map = new HashMap();
            sed = SecurityEngineDeal.getInstance("SVSDefault");
            String strServerCert = sed.getServerCertificate();
            String strRandom = sed.genRandom(24);
            session.setAttribute("Random", strRandom);
            String strSignedData = sed.signData(strRandom.getBytes());
            map.put("strServerCert", strServerCert);
            map.put("strRandom", strRandom);
            map.put("strSignedData", strSignedData);
            msgC = getMsgCsJson(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            response.getWriter().write(msgC);
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getMsgCsJson(Map<String, Object> maps) {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = null;
        jsonObject = new JSONObject();
        jsonObject.put("strServerCert", maps.get("strServerCert"));
        jsonObject.put("strRandom", maps.get("strRandom"));
        jsonObject.put("strSignedData", maps.get("strSignedData"));
        jsonArray.put(jsonObject);
        String jo = jsonArray.toString();
        return jo;
    }

}
