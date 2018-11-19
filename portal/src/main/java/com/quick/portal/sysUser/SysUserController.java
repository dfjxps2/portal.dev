/**
 * <h3>标题 : potal统一门户-sys_user </h3>
 * <h3>描述 : sys_user请求类</h3>
 * <h3>日期 : 2018-04-13</h3>
 * <h3>版权 : Copyright (C) 北京东方金信科技有限公司</h3>
 *
 * <p>
 *
 * @author 你自己的姓名 mazong@seaboxdata.com
 * @version <b>v1.0.0</b>
 *
 * <b>修改历史:</b>
 * -------------------------------------------
 * 修改人 修改日期 修改描述
 * -------------------------------------------
 *
 *
 * </p>
 */
package com.quick.portal.sysUser;

import com.quick.core.base.ISysBaseService;
import com.quick.core.base.SysBaseController;
import com.quick.core.base.model.DataStore;
import com.quick.core.util.common.CommonUtils;
import com.quick.core.util.common.QCookie;
import com.quick.portal.userAccessLog.UserAccessLogServiceUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * sys_user请求类
 *
 * @author 你自己的姓名
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/sysUser")
public class SysUserController extends SysBaseController<SysUserDO> {


    @Resource(name = "sysUserService")
    private ISysUserService sysUserService;

    @Override
    public ISysBaseService<SysUserDO> getBaseService() {
        return sysUserService;
    }

    //页面请求(/sysUser/list =》page/sysUser/list
    @RequestMapping
    public String list(ModelMap model) {
        return view();
    }

    @RequestMapping
    public String edit(ModelMap model) {
        return view();
    }


    @RequestMapping
    public String changepw(ModelMap model) {
        return view();
    }

    @RequestMapping(value = "/resetPass")
    @ResponseBody
    public DataStore resetPass(String account) {
        SysUserDO sysUserDO = new SysUserDO();
        String password = "21232f297a57a5a743894a0e4a801fc3";

        sysUserDO.setUser_password(password);
        sysUserDO.setUser_id(Integer.parseInt(account));

        return sysUserService.updatePassword(sysUserDO);
    }


    //修改密码
    @RequestMapping(value = "/changPassword")
    @ResponseBody
    public DataStore changePassword(String new_password) {
        SysUserDO sysUserDO = new SysUserDO();
        String user_id = QCookie.getValue(request, "sbd.user_id");

        assert user_id != null;

        sysUserDO.setUser_password(new_password);
        sysUserDO.setUser_id(Integer.parseInt(user_id));

        return sysUserService.updatePassword(sysUserDO);
    }

    //密码更改--旧密码校验
    @RequestMapping(value = "checkOldPw")
    public void checkParamName(HttpServletResponse res, String user_old_pw) throws IOException {
        Map<String, Object> map = new HashMap<>();
        String user_id = QCookie.getValue(request, "sbd.user_id");

        assert user_id != null;

        map.put("user_password", user_old_pw);
        map.put("user_id", Integer.parseInt(user_id));
        List<Map<String, Object>> result = sysUserService.select(map);

        if (result.size() != 0) {
            res.getWriter().write("true");
        } else {
            res.getWriter().write("false");
        }

        res.getWriter().flush();
    }

    @RequestMapping(value = "/checkUser")
    public void validateAccount(HttpServletResponse res, String username, String markname) throws Exception {
        Map<String, Object> param_map = new HashMap<>();

        param_map.put("user_name", username);
        List<Map<String, Object>> users = sysUserService.select(param_map);
        if (users.size() == 0) {
            res.getWriter().write("false");
        } else {
            param_map.put("user_id", markname);
            users = sysUserService.select(param_map);
            if (users.size() > 0)
                res.getWriter().write("false");
            else
                res.getWriter().write("true");
        }
        res.getWriter().flush();
    }

    @RequestMapping(value = "/deleteUser")
    @ResponseBody
    public DataStore deleteUser(String user_id) {
        loggerInfoDeleteUserInfo(user_id);
        return super.deleteAction();
    }


    @RequestMapping(value = "/saveUser")
    @ResponseBody
    public DataStore saveAction(SysUserDO model) {
        if (model.getUser_id() != null && !"".equals(model.getUser_id())) {
            loggerInfoUpdateUserInfo(model);
        }else{
            loggerInfoInsertUserInfo(model);
        }
        return super.save(model);
    }


    public void loggerInfoDeleteUserInfo(String id){
        String userName = QCookie.getValue(request, "sbd.user_name");
        String ip = CommonUtils.getIpAddrAdvanced(request);
        String requestResult = "系统操作员:"+userName+"删除用户信息->用户编号:"+id;
        String operateType = "统一门户->用户管理服务->用户删除";;
        String operatedUser = "被操作用户编号:"+id;
        String operLog = "用户管理服务日志->删除用户";
        String serviceName = "服务名称:用户管理服务;服务方法名:";
        UserAccessLogServiceUtils.loggerLogInfo(userName,operatedUser,operateType,requestResult,operLog,serviceName,ip);
    }

    public void loggerInfoInsertUserInfo(SysUserDO model){
        String userName = QCookie.getValue(request, "sbd.user_name");
        String ip = CommonUtils.getIpAddrAdvanced(request);
        String requestResult = "系统操作员:"+userName+"新增用户信息->新增用户名称:"+model.getUser_name();
        String operateType = "统一门户->用户管理服务->用户新增";
        String operatedUser = "被操作用户名称:"+model.getUser_name();
        String operLog = "用户管理服务日志->新增角用户";
        String serviceName = "服务名称:用户管理服务;服务方法名:";
        UserAccessLogServiceUtils.loggerLogInfo(userName,operatedUser,operateType,requestResult,operLog,serviceName,ip);

    }

    public void loggerInfoUpdateUserInfo(SysUserDO model){
        String userName = QCookie.getValue(request, "sbd.user_name");
        String ip = CommonUtils.getIpAddrAdvanced(request);
        String requestResult = "系统操作员:"+userName+"修改用户信息->把用户编号:"+model.getUser_id()+"的用户名称修改为"+model.getUser_name();
        String operateType = "统一门户->用户管理服务->用户修改";
        String operatedUser = "被操作用户编号:"+model.getUser_id()+":用户名称:"+model.getUser_name();
        String operLog = "用户管理服务日志->修改用户";
        String serviceName = "服务名称:用户管理服务;服务方法名:";
        UserAccessLogServiceUtils.loggerLogInfo(userName,operatedUser,operateType,requestResult,operLog,serviceName,ip);
    }

}