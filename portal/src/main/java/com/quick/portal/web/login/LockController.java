/**
 * <h3>标题 : portal统一门户-管理驾驶仓 </h3>
 * <h3>描述 : 应用中的相关配置信息都放在此</h3>
 * <h3>日期 : 2018-04-13</h3>
 * <h3>版权 : Copyright (C) 北京东方金信科技有限公司</h3>
 *
 * <p>
 *
 * @author admin mazong@seaboxdata.com
 * @version <b>v1.0.0</b>
 *
 * <b>修改历史:</b>
 * -------------------------------------------
 * 修改人  修改日期   修改描述
 * -------------------------------------------
 *
 *
 * </p>
 */
package com.quick.portal.web.login;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quick.core.base.exception.ExceptionEnumServiceImpl;
import com.quick.core.util.common.CommonUtils;
import com.quick.portal.security.authority.metric.PropertiesUtil;
import com.quick.portal.sysUser.ISysUserService;

/**
 * 门户请求类
 *
 * @author Administrator
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/lock")
public class LockController {

    @Resource(name = "sysUserService")
    private ISysUserService sysUserService;

    /*
     * 异常信息返回到页面
     */
    @RequestMapping(value = "/error")
    public String getError(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        String code = (String) request.getSession().getAttribute("code");
        if (null == code || "".equals(code)) {
            code = "";
        }
        String message = (String) request.getSession().getAttribute("message");
        if (null == message || "".equals(message)) {
            message = "";
        }
        String path = WebLoginUitls.getPath(request).concat("/home/logout");
        model.addAttribute("code", code);
        model.addAttribute("message", message);
        model.addAttribute("host", WebLoginUitls.getPath(request));
        model.addAttribute("path", path);
        return WebLoginConstants.PAGE_ERROR_URL;
    }


    /*
     * cas server return method
     *
     */
    @RequestMapping(value = "/getLockInfo")
    public String getLockInfo(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        String ip = CommonUtils.getIpAddrAdvanced(request);
        Map<String, Object> mp = sysUserService.getLockCount(ip);

        String userId = request.getParameter("username");
        if (userId == null)
            userId = (String)mp.get("AUD_USER");

        if (userId == null || userId.length() == 0)
            userId = "证书用户";

        int lockCnt = Integer.parseInt(mp.get("CNT").toString());
        if (lockCnt == 0) {
            request.getSession().setAttribute("code", ExceptionEnumServiceImpl.USER_STATUS_ERROR.getCode());
            request.getSession().setAttribute("message", ExceptionEnumServiceImpl.USER_STATUS_ERROR.getMessage());
            return WebLoginConstants.REDIRECT_KEY.concat(WebLoginConstants.COMMON_INFO_CONTROLLER);
        }
        String sysLockCnt = PropertiesUtil.getPropery("portal.lock.count");
        int setLockCnt = Integer.parseInt(sysLockCnt);
        int remainLockCnt = setLockCnt - lockCnt;

        if (!userId.equals("证书用户")) {

            //通过用户名称查询用户状态falase->0:禁用；true->1：启用
            Map<String, Object> userMap = sysUserService.isExitUserInfoByUserId(userId);
            int userCnt = Integer.parseInt(userMap.get("CNT").toString());
            //用户不存在
            if (userCnt == 0) {
                request.getSession().setAttribute("code", ExceptionEnumServiceImpl.NO_THIS_USER.getCode());
                request.getSession().setAttribute("message", userId + ExceptionEnumServiceImpl.NO_THIS_USER.getMessage());
                return WebLoginConstants.REDIRECT_KEY.concat(WebLoginConstants.COMMON_INFO_CONTROLLER);
            }

            String userState = userMap.get("USER_STATE").toString();
            //用户禁用
            if (null != userState && WebLoginConstants.FORBIDDEN_USER_STATE.equals(userState)) {
                userId = mp.get("AUD_USER").toString();
                request.getSession().setAttribute("code", ExceptionEnumServiceImpl.USER_STATUS_LOCKING.getCode());
                request.getSession().setAttribute("message", userId + ExceptionEnumServiceImpl.USER_STATUS_LOCKING.getMessage());
                return WebLoginConstants.REDIRECT_KEY.concat(WebLoginConstants.COMMON_INFO_CONTROLLER);
            }
        }

        if (remainLockCnt > 0) {
            request.getSession().setAttribute("code", ExceptionEnumServiceImpl.USER_STATUS_WARING.getCode());
            request.getSession().setAttribute("message", WebLoginConstants.USER_STATUS_WARING_PREFIX + remainLockCnt + WebLoginConstants.USER_STATUS_WARING_SUFFIX);
        } else {
            if (!userId.equals("证书用户"))
                sysUserService.updateUserStatueByUersId(userId);
            request.getSession().setAttribute("code", ExceptionEnumServiceImpl.USER_STATUS_LOCKING.getCode());
            request.getSession().setAttribute("message", userId + ExceptionEnumServiceImpl.USER_STATUS_LOCKING.getMessage());
        }

        return WebLoginConstants.REDIRECT_KEY.concat(WebLoginConstants.COMMON_INFO_CONTROLLER);
    }


    /*
     * 异常信息返回到页面
     */
    @RequestMapping(value = "/info")
    public String getInfo(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        String code = (String) request.getSession().getAttribute("code");
        if (null == code || "".equals(code)) {
            code = "";
        }
        String message = (String) request.getSession().getAttribute("message");
        if (null == message || "".equals(message)) {
            message = "";
        }
        model.addAttribute("code", code);
        model.addAttribute("message", message);
        model.addAttribute("host", WebLoginUitls.getPath(request));
        return WebLoginConstants.PAGE_INFO_URL;
    }

}