/**
 * <h3>标题 : Quick通用系统框架 </h3>
 * <h3>描述 : 应用中的相关配置信息都放在此</h3>
 * <h3>日期 : 2014-03-23</h3>
 * <h3>版权 : Copyright (C) 海口鑫网计算机网络有限公司</h3>
 *
 * <p>
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

import com.quick.core.base.AppResource;
import com.quick.core.util.common.QCommon;
import com.quick.core.util.common.QCookie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quick.portal.sysUser.ISysUserService;
import com.quick.portal.sysUser.SysUserDO;
import com.quick.portal.userAccessLog.IUserAccessLogService;
import com.quick.portal.userAccessLog.UserAccessLogConstants;
import com.quick.portal.userRoleRela.IUserRoleRelaService;

import org.springframework.context.annotation.Scope;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 身份验证
 *
 * @author Administrator
 */
@Controller
@Scope("prototype")
public class WebLoginController {

    protected static final String LOGIN = "/home/login";
    private Logger logger = Logger.getLogger(getClass());
    
    @Resource(name = "sysUserService")
    private ISysUserService sysUserService;
    @Resource(name = "userRoleRelaService")
    private IUserRoleRelaService userRoleRelaService;
    @Resource(name = "userAccessLogService")
    private IUserAccessLogService userAccessLogService;
    

    //登录页
    @RequestMapping(value = "/" )
    public String index() {
        return "redirect:/home/main";
    }

    @RequestMapping(value = "/home/login")
    public String login(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        String url = request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort() + request.getContextPath();
        model.addAttribute("host",url);
        return "page/home/login";
    }
    //服务请求
    @RequestMapping(value = "/home/dologin", method = RequestMethod.POST)
    @ResponseBody
    public String dologin(String username, String password, String code,HttpServletRequest request, HttpServletResponse response) {
       String flag = "1";
    	try {
    	   if (username.equals("null") || password.equals("null")) {
    		   return "用户名或密码为空";
           }
	       username=QCommon.urlDecode(username);
           Map<String, Object> parm = new HashMap<>();
           parm.put("user_name", username);
	       SysUserDO loginUser = sysUserService.selectObj(parm);
	       if (loginUser == null) {
	            return "用户名或密码错误";
	       }
           //密码比较
           if(!loginUser.getUser_password().equals(password)){
               return "用户名或密码错误";
           }
	       //if(!"1".equals(loginUser.getUser_state())){
	       //	   return "帐号被锁，请联系管理员";
	       //}
           parm.clear();
           parm.put("user_id", loginUser.getUser_id());
           List<Map<String,Object>> roles = userRoleRelaService.select(parm);
           saveSession(loginUser,roles, request, response);
           userAccessLogService.saveLog(request, UserAccessLogConstants.SYS_LOG_TYPE_ID, UserAccessLogConstants.LOGIN_USER_OP_TYPE,1,loginUser.getUser_real_name() + "登录成功");
           //平台用户
           if(null == loginUser.getUser_global_id() || "".equals(loginUser.getUser_global_id())){
        	   flag = "2";
        	 //公服用户
           }else{
        	   flag = "1";
           }
       } catch (Exception e) {
            e.printStackTrace();
            return  e.getMessage();
        }
	    return flag;
    }
  
    @RequestMapping(value = "/home/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        //记录日志
        String ids = QCookie.getValue(request, "ids");
        if(ids != null && ids.length() > 0)
            userAccessLogService.saveLog(request, UserAccessLogConstants.SYS_LOG_TYPE_ID, UserAccessLogConstants.LOGOUT_USER_OP_TYPE,UserAccessLogConstants.FUN_MENU_ID, UserAccessLogConstants.LOGOUT_USER_OP_DESC);

        QCookie.remove(response, request, AppResource.COOKIE_LOGINER );
        QCookie.remove(response, request, "ids" );
        QCookie.remove(response, request, "sbd.user" );
        QCookie.remove(response, request, "sbd.role" );

        String url = request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort() + request.getContextPath()
                + "/";
        return "redirect:https://cas4.example.org:8443/cas/logout?service=" + QCommon.urlEncode(url);
    }

    public void saveSession(SysUserDO loginUser,List<Map<String,Object>> roles, HttpServletRequest request, HttpServletResponse response){
        String ids = "";
        for(Map<String,Object> m : roles)
            ids += "," + m.get("role_id").toString();
        ids = ids.substring(1);
        QCookie.set(response, "ids", loginUser.getUser_id().toString());
        QCookie.set(response, "sbd.user", loginUser.getUser_name(), 4*3600);
        QCookie.set(response, "sbd.role", ids, 4*3600);
    }
}
