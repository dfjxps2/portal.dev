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
import com.quick.core.util.common.QCookie;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
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

//    @RequestMapping
//    public String chose(ModelMap model) {
//        return view();
//    }
//

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
    public void validateAccount(HttpServletResponse res, String username) throws Exception {
        Map<String, Object> param_map = new HashMap<>();

        param_map.put("user_name", username);

        List<Map<String, Object>> map = sysUserService.select(param_map);

        if (CollectionUtils.isEmpty(map)) {
            res.getWriter().write("false");
        } else {
            res.getWriter().write("true");
        }
        res.getWriter().flush();
    }

    @RequestMapping(value = "/userEndecrypt")
    public String userEndecrypt(ModelMap model) {
        return view();
    }


}