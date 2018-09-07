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

import java.io.IOException;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.quick.core.base.model.DataStore;
import com.quick.core.base.model.JsonDataGrid;
import com.quick.core.base.model.PageBounds;
import com.quick.core.util.common.QCookie;


import com.quick.core.util.common.QRequest;
import com.quick.portal.userRoleRela.IUserRoleRelaDao;
import com.quick.portal.userRoleRela.UserRoleRelaDO;


import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quick.core.base.ISysBaseService;
import com.quick.core.base.SysBaseController;

/**
 * sys_user请求类
 * @author 你自己的姓名
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/sysUser")
public class SysUserController extends SysBaseController<SysUserDO> {

    @Resource(name = "sysUserService")
    private ISysUserService sysUserService;

    @Autowired
    private ISysUserDao iSysUserDao;

    @Autowired
    private IUserRoleRelaDao iUserRoleRelaDao;

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
    public String chose(ModelMap model) {
        return view();
    }

    @RequestMapping
    public String changepw(ModelMap model) {
        return view();
    }

    @RequestMapping(value = "/resetPass")
    public void resetPass(HttpServletResponse res, String account, String state) {
        SysUserDO sysUserDO = new SysUserDO();
        String password = "21232f297a57a5a743894a0e4a801fc3";
        sysUserDO.setUser_password(password);
        sysUserDO.setUser_id(Integer.parseInt(account));
        iSysUserDao.updatePassword(sysUserDO);
        try {
            res.getWriter().write("1");
            res.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //获取编辑对象数据
    @RequestMapping(value = "/getEditData")
    @ResponseBody
    public Map<String, Object> getObj() throws Exception {

        String sysid = QRequest.getString(request, "user_id");
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", sysid);
        Map<String, Object> obj = iSysUserDao.getUserEdit(map);
        return obj;
    }

    //添加用户
    @RequestMapping(value = "/addUser")
    @ResponseBody
    public DataStore save(@RequestBody SysUserDO sysUserDO) {
        String password = "21232f297a57a5a743894a0e4a801fc3";
        sysUserDO.setUser_password(password);

        return super.save(sysUserDO);
    }

    //修改密码
    @RequestMapping(value = "/changPw")
    @ResponseBody
    public void changePassword(Model model, String new_password, HttpServletResponse res) {
        SysUserDO sysUserDO = new SysUserDO();
        try {
            res.setContentType("text/html");
            res.setCharacterEncoding("utf-8");
            Integer user_id = Integer.parseInt(QCookie.getValue(request, "sbd.user_id"));
//             PortalPasswordEncoder passwordEncoder = new PortalPasswordEncoder("MD5","");
//             String password = passwordEncoder.encode(newPw);
            sysUserDO.setUser_password(new_password);
            sysUserDO.setUser_id(user_id);
            iSysUserDao.updatePassword(sysUserDO);
            res.getWriter().write("1");
            res.getWriter().flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //密码更改--旧密码校验
    @RequestMapping(value = "checkOldPw")
    public void checkParamName(HttpServletResponse res, String user_old_pw) throws IOException {
        Map<String, Object> map = new HashMap<>();
//        PortalPasswordEncoder passwordEncoder = new PortalPasswordEncoder("MD5","");
//        String oldps = passwordEncoder.encode(oldPw);
        map.put("user_password", user_old_pw);
        Integer user_id = Integer.parseInt(QCookie.getValue(request, "sbd.user_id"));
        map.put("user_id", user_id);
        List<Map<String, Object>> result = sysUserService.select(map);
        if (result.size() != 0) {
            res.getWriter().write("true");
        } else {
            res.getWriter().write("false");
        }
        res.getWriter().flush();
    }

    //用户编辑
    @RequestMapping(value = "/editUser")
    public void editUsrDetail(Model model, SysUserDO usrDetail, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        res.setCharacterEncoding("utf-8");
        JSONArray json = new JSONArray();
        Date date = Calendar.getInstance().getTime();//当前添加时间
        SysUserDO sysUserDO = new SysUserDO();
        if (usrDetail.getUser_state() != null && !usrDetail.getUser_state().equals("undefined") && !"null".equals(usrDetail.getUser_state())) {
            sysUserDO.setUser_state(usrDetail.getUser_state());//角色状态
        }
        if (usrDetail.getUser_real_name() != null && !usrDetail.getUser_real_name().equals("undefined") && !"null".equals(usrDetail.getUser_real_name())) {
            sysUserDO.setUser_real_name(usrDetail.getUser_real_name());//真实名称
        }
        if (usrDetail.getDep_id() != null && !usrDetail.getDep_id().equals("undefined") && !"null".equals(usrDetail.getDep_id())) {
            sysUserDO.setDep_id(usrDetail.getDep_id());//部门名称
        }
        if (usrDetail.getJob_id() != null && !usrDetail.getJob_id().equals("undefined") && !"null".equals(usrDetail.getJob_id())) {
            sysUserDO.setJob_id(usrDetail.getJob_id());//岗位名称
        }
        if (!usrDetail.getUser_addr().equals("") && !usrDetail.getUser_addr().equals("undefined") && !"null".equals(usrDetail.getUser_addr())) {
            sysUserDO.setUser_addr(usrDetail.getUser_addr());
            ;//地址
        }
        if (!usrDetail.getUser_tel().equals("") && !usrDetail.getUser_tel().equals("undefined") && !"null".equals(usrDetail.getUser_tel())) {
            sysUserDO.setUser_tel(usrDetail.getUser_tel());
            ;//电话
        }

        if (!usrDetail.getUser_name().equals("") && !usrDetail.getUser_name().equals("undefined") && !"null".equals(usrDetail.getUser_name())) {
            sysUserDO.setUser_name(usrDetail.getUser_name());//用户名称
        }

        if (!usrDetail.getUser_id().equals("") && !usrDetail.getUser_id().equals("undefined") && !"null".equals(usrDetail.getUser_id())) {
            sysUserDO.setUser_id(usrDetail.getUser_id());//用户ID
        }
        sysUserDO.setUpd_time(date);
        if (usrDetail.getRela_id() != null && !usrDetail.getRela_id().equals("undefined") && !"null".equals(usrDetail.getRela_id())) {
            sysUserDO.setRela_id(usrDetail.getRela_id());
            iSysUserDao.updateUserDepRela(sysUserDO);
        } else {
            iSysUserDao.insertUserDepRela(sysUserDO);
        }
        iSysUserDao.update(sysUserDO);
        //将角色和用户关系放入user_role_rela中
        UserRoleRelaDO userRoleRelaDO = new UserRoleRelaDO();
        ArrayList<Object> list = new ArrayList<>();
        Integer userId = null;
        if (usrDetail.getUser_id() != null && !usrDetail.getUser_id().equals("undefined") && !"null".equals(usrDetail.getUser_id())) {
            userId = usrDetail.getUser_id();
            iSysUserDao.deleteUserRole(usrDetail.getUser_id().toString());
        } else {
            json.put("0");
            res.getWriter().write(json.toString());
            return;
        }
        userRoleRelaDO.setUpd_time(date);
        userRoleRelaDO.setCre_time(date);
        if (usrDetail.getRoles() != null && !usrDetail.getRoles().equals("undefined")) {
            String[] role = usrDetail.getRoles().split(",");
            for (String value : role) {
                userRoleRelaDO = new UserRoleRelaDO();
                userRoleRelaDO.setRole_id(Integer.parseInt(value));
                userRoleRelaDO.setUser_id(userId);
                userRoleRelaDO.setUpd_time(date);
                userRoleRelaDO.setCre_time(date);
                list.add(userRoleRelaDO);
            }
        } else {
            userRoleRelaDO.setRole_id(null);
        }

        iSysUserDao.addRoleUsers(list);

        json.put("1");
        res.getWriter().write(json.toString());

    }

    @RequestMapping(value = "/checkUser")
    public void validateAccount(HttpServletResponse res, String username, String markname) {
        res.setCharacterEncoding("utf-8");
        //去数据库，根据ids_account查询单个用户的信息
        Map<String, Object> editmap = new HashMap<>();
        editmap.put("user_name", username);
        editmap.put("user_id", markname);
        List<Map<String, Object>> map = iSysUserDao.selectByName(username);
        List<Map<String, Object>> mapedit = iSysUserDao.select(editmap);
        String nametile = "0";
        for (Map<String, Object> data : mapedit) {
            String uname = data.get("user_name").toString();
            if (uname.equals(username) && markname != null) {
                nametile = "1";
            }
        }
        try {
            if (CollectionUtils.isEmpty(map) || nametile.equals("1")) {
                res.getWriter().write("false");
            } else {
                res.getWriter().write("true");
            }
            res.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *删除功能
     * @throws IOException
     */
    @RequestMapping(value = "/deleteUser")
    @ResponseBody
    public DataStore deleteUser(String user_id) throws IOException {

        if (user_id != null && !user_id.equals("undefined") && !"null".equals(user_id)) {
            return sysUserService.delete(user_id);
        }

        return ActionMsg.setError("用户ID不存在！");
    }


    @RequestMapping(value = "/userEndecrypt")
    public String userEndecrypt(ModelMap model) {
        return view();
    }


}