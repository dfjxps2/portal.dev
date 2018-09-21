/**
 * <h3>标题 : potal统一门户-menu_privilege </h3>
 * <h3>描述 : menu_privilege请求类</h3>
 * <h3>日期 : 2018-04-13</h3>
 * <h3>版权 : Copyright (C) 北京东方金信科技有限公司</h3>
 * 
 * <p>
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
package com.quick.portal.sysPrivilege;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quick.core.base.ISysBaseService;
import com.quick.core.base.SysBaseController;
import javax.annotation.Resource;

import com.quick.core.base.model.DataStore;
import com.quick.portal.sysMenu.SysMenuDO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * menu_privilege请求类
 * @author 你自己的姓名
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/sysPrivilege")
public class SysPrivilegeController extends SysBaseController<SysPrivilegeDO> {
    
    @Resource(name = "sysPrivilegeService")
    private ISysPrivilegeService sysPrivilegeService;
    
    @Override
    public ISysBaseService<SysPrivilegeDO> getBaseService(){
        return sysPrivilegeService;
    }
    
    //页面请求
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
    @ResponseBody
    public List<Map<String, Object>> listPrivilege(String role_id) {
        Map<String, Object> param = new HashMap<>();

        param.put("menu_state", Integer.valueOf(1));

        List<Map<String, Object>> privilegeDef = sysPrivilegeService.select(param);

        param.put("role_id", role_id);

        List<Map<String, Object>> privilegeOfRole = sysPrivilegeService.getPrivilegeForRole(param);

        Map<Integer, Map<String, Object>> priDefMap = new HashMap<>();
        privilegeDef.forEach(item -> priDefMap.put((Integer)item.get("menu_id"), item));

        Map<Integer, Map<String, Object>> pri4RoleMap = new HashMap<>();
        privilegeOfRole.forEach(item -> pri4RoleMap.put((Integer)item.get("menu_id"), item));

        List<Map<String, Object>> result = new ArrayList<>();
        priDefMap.forEach((k, v) -> result.add(pri4RoleMap.getOrDefault(k, v)));

        return result;
    }

    @RequestMapping
    @ResponseBody
    public DataStore savePrivilege(String role_id, Integer[] privileges){
//        JSONArray jsonObject = (JSONArray) JSONObject.parse(privileges);
//        List<SysMenuDO> menuList = jsonObject.toJavaList(SysMenuDO.class);

        DataStore result = sysPrivilegeService.savePrivilegeForRole(Integer.valueOf(role_id), privileges);
        return result;
    }
}