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

import java.io.IOException;
import java.io.OutputStream;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quick.core.base.AppResource;
import com.quick.core.util.common.QCommon;
import com.quick.core.util.common.QCookie;
import com.quick.portal.security.authority.metric.PropertiesUtil;
import com.quick.portal.sysUser.ISysUserService;
import com.quick.portal.sysUser.SysUserDO;
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
        String userGlobalID = null, rid = null;
        if (loginer.getRole_id() == 0) {
            loginer = loadCASUserInfo(request, response);
            if (null == loginer) {
                logger.error("Can't get user information from user profile and user database.");
                return "redirect:" + LOGOUT_URL;
            } else {
                rid = String.valueOf(loginer.getRole_id());
                userGlobalID = loginer.getUser_global_id();
            }
        }

        loginer.setRequestSerial(1);
        loginer.saveSession(request, response);
        userAccessLogService.saveLog(request, UserAccessLogConstants.SYS_LOG_TYPE_ID, UserAccessLogConstants.LOGIN_USER_OP_TYPE, 1, loginer.getUser_real_name() + "登录成功", loginer.getUser_id().toString(), loginer.getUser_name());

        //平台用户:1:app;2:sys;公服用户:1:app
        String flag = getSysUrlByUserGlobalID(userGlobalID, rid);
        if (SYS_MENU_FLAG.equals(flag)) {
            return "redirect:/mainframe";
        } else {
            return "redirect:/home/main";
        }
    }


    @RequestMapping(value = "/home/login")
    public String login(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        String url = request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort() + request.getContextPath();
        model.addAttribute("host", url);
        return "page/home/login";
    }

    //服务请求
    @RequestMapping(value = "/home/dologin", method = RequestMethod.POST)
    @ResponseBody
    public String dologin(String username, String password, String code, HttpServletRequest request, HttpServletResponse response) {
        String flag = "1";
        try {
            if (username.equals("null") || password.equals("null")) {
                return "用户名或密码为空";
            }
            username = QCommon.urlDecode(username);
            Map<String, Object> parm = new HashMap<>();
            parm.put("user_name", username);
            SysUserDO loginUser = sysUserService.selectObj(parm);
            if (loginUser == null) {
                return "用户名或密码错误";
            }
            //密码比较
            if (!loginUser.getUser_password().equals(password)) {
                return "用户名或密码错误";
            }
            //if(!"1".equals(loginUser.getUser_state())){
            //	   return "帐号被锁，请联系管理员";
            //}
            parm.clear();
            parm.put("user_id", loginUser.getUser_id());

            List<Map<String, Object>> roles = userRoleRelaService.select(parm);
            saveSession(loginUser, roles, request, response);
            userAccessLogService.saveLog(request, UserAccessLogConstants.SYS_LOG_TYPE_ID, UserAccessLogConstants.LOGIN_USER_OP_TYPE, 1, loginUser.getUser_real_name() + "登录成功", loginUser.getUser_id().toString(), loginUser.getUser_name());
            String rid = null;
            if (null != loginUser.getRole_id() && !"".equals(loginUser.getRole_id())) {
                rid = String.valueOf(loginUser.getRole_id());
            } else {
                return "用户名角色为空";
            }
            // 平台用户:1:app;2:sys;公服用户:1:app
            flag = getSysUrlByUserGlobalID(loginUser.getUser_global_id(), rid);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return flag;
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
        request.getSession().invalidate();
        String casUrl = PropertiesUtil.getPropery("sso.cas.server.prefixUrl");
        String url = request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort() + request.getContextPath()
                + "/";
        String retUrl = casUrl.concat("/logout?service=").concat(QCommon.urlEncode(url));//"redirect:".concat
        if(isAjax(request)){
            writeJs("{\"code\":-99,\"msg\":\"会话已超时，请重新登录！\", \"url\":\""+retUrl+"\"}",response);
        }else{
            writeMsg("会话已超时，请重新登录！", url, retUrl, request, response);
        }
        return null;
    }

    public void saveSession(SysUserDO loginUser, List<Map<String, Object>> roles, HttpServletRequest request, HttpServletResponse response) {
        String ids = "";
        for (Map<String, Object> m : roles)
            ids += "," + m.get("role_id").toString();
        ids = ids.substring(1);
        QCookie.set(response, "ids", loginUser.getUser_id().toString());
        QCookie.set(response, "sbd.user", loginUser.getUser_name(), 4 * 3600);
        QCookie.set(response, "sbd.role", ids, 4 * 3600);
        QCookie.set(response, "sbd.gid", loginUser.getUser_global_id(), 4 * 3600);
        QCookie.set(response, "sbd.uid", loginUser.getUser_name(), 4 * 3600);
    }

    public WebLoginUser loadCASUserInfo(HttpServletRequest request, HttpServletResponse response) {
    	 String account = null;
    	 List<CommonProfile> profiles = WebLoginUitls.getProfiles(request, response);
    	 for(CommonProfile profile : profiles){
    		 account =  profile.getId();
    	 }
        if (null !=account && !"".equals(account)) {
            Map<String, Object> parm = new HashMap<>();
            parm.put("user_name", account);
            Map<String, Object> u = sysUserService.selectMap(parm);
            WebLoginUser user = new WebLoginUser();
            user.setRole_id(Integer.valueOf(WebLoginUitls.getVal(u, "role_id")));
            user.setUser_real_name(WebLoginUitls.getVal(u, "user_real_name"));
            user.setUser_id(Integer.valueOf(WebLoginUitls.getVal(u, "user_id")));
            user.setUser_global_id(WebLoginUitls.getVal(u, "user_global_id"));
            user.setUser_name(WebLoginUitls.getVal(u, "user_name"));
            user.saveSession(request, response);//保存至本地
            return user;
        }
        return null;
    }


    /*
     * 平台用户:1:app;2:sys
     * 公服用户:1:app
     * 1:app;2:sys
     */

    public String getSysUrlByUserGlobalID(String userGlobalID, String rid) {
        String flag = null;
        //平台用户
        if (null == userGlobalID || "".equals(userGlobalID)) {
            if (ADMINISTRATOR_ROLE.equals(rid) || PORTAL_ADMINISTRATOR_ROLE.equals(rid)) {
                flag = SYS_MENU_FLAG;
            } else {
                flag = APP_MENU_FLAG;
            }
            //公服用户
        } else {
            flag = APP_MENU_FLAG;
        }
        return flag;
    }

    private boolean isAjax(HttpServletRequest request){
        //如果是ajax请求响应头会有x-requested-with
        if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){
            return true;
        }
        return false;
    }
    public void writeJs(String msg,HttpServletResponse response) {
        response.setContentType("application/json; charset=utf-8"); // 输出JS文件
        try {
            OutputStream out = response.getOutputStream();
            out.write(msg.getBytes("UTF-8"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void writeMsg(String msg,String host, String url, HttpServletRequest request, HttpServletResponse response){
        response.setHeader("content-type", "text/html;charset=UTF-8");
        String outString =" <script src=\""+host+"/res/plugin/jQuery/jquery-1.11.3.min.js\" type=\"text/javascript\"></script>";
        outString+="<link href=\""+host+"/res/layer/skin/default/layer.css\" rel=\"stylesheet\">";
        outString+="<script src=\""+host+"/res/layer/layer.js\"></script>";
        outString+= "<script language=javascript>layer.alert('"+msg+"',function(){(window.parent||window).location='"
                + url + "';});</script>";
        try {
            response.getWriter().print(outString);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public final static String ADMINISTRATOR_USER = "admin";
    public final static String ADMINISTRATOR_ROLE = "1";
    public final static String PORTAL_ADMINISTRATOR_ROLE = "100";

    public final static String SYS_MENU_FLAG = "2";
    public final static String APP_MENU_FLAG = "1";
}
