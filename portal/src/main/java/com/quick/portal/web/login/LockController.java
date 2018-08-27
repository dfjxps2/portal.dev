/**
 * <h3>标题 : portal统一门户-管理驾驶仓 </h3>
 * <h3>描述 : 应用中的相关配置信息都放在此</h3>
 * <h3>日期 : 2018-04-13</h3>
 * <h3>版权 : Copyright (C) 北京东方金信科技有限公司</h3>
 *
 * <p>
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quick.portal.sysUser.ISysUserService;
import com.quick.portal.sysUser.SysUserDO;

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

    @RequestMapping(value = "/lockAccount")
    public String lockAccount(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        String url = request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort() + request.getContextPath();
        String userId = request.getParameter("u");
        if(null == userId || "".equals(userId)){
        	userId = "用户信息异常：用户帐号为空";
        }else{
        	 sysUserService.updateUserStatueByUersId(userId);
        }
       
        model.addAttribute("host", url);
        model.addAttribute("uid", userId);
        return "page/home/lock";
    }
}