
package com.quick.portal.userRole;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.quick.core.base.ISysBaseService;
import com.quick.core.base.SysBaseController;
import com.quick.core.base.model.DataStore;
import com.quick.portal.security.authority.metric.MetricPrivilegeConstants;
import com.quick.portal.security.authority.metric.PropertiesUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 请求类
 *
 * @author Administrator
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/role")
public class RoleController extends SysBaseController<UserRoleDO> {
    //角色服务
    @Resource(name = "roleService")
    private RoleService roleService;

    @Override
    public ISysBaseService<UserRoleDO> getBaseService() {
        return roleService;
    }

    //页面请求---开始
    @RequestMapping
    public String list(ModelMap model) {
        return view();
    }

    @RequestMapping
    public String edit(ModelMap model) {
        return view();
    }

    @RequestMapping
    public String roleAuthorize(ModelMap model) {
        return view();
    }

    /**
     * 保存角色
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/saveRole")
    @ResponseBody
    public DataStore save(UserRoleDO model) {
        if (model.getRole_id() != null && model.getRole_id() >= 0)
            return roleService.update(model, rstr("user_role_predicate"));
        else
            return roleService.insert(model, rstr("user_role_predicate"));
    }


    @RequestMapping("listAllMenu")
    @ResponseBody
    public List<Map<String, Object>> listAllMenu(String role_id) {
        Map<String, Object> parm = new HashMap<>();
        parm.put("role_id", role_id);
        return roleService.listAllMenu(parm);
    }

    @RequestMapping("listMenuPri")
    @ResponseBody
    public List<Map<String, Object>> listMenuPri(String role_id) {
        return roleService.listMenuPri(role_id);
    }

    ;

    @RequestMapping("saveMenuPri")
    public String saveMenuPri(String role_id, String menus) {
        JSONArray jsonObject = (JSONArray) JSONObject.parse(menus);
        List<String> menuList = jsonObject.toJavaList(String.class);
        roleService.saveMenuPri(role_id, menuList);
        return "page/role/list";
    }

    @RequestMapping
    public String metricAuth(ModelMap model) {
        String url = PropertiesUtil.getPropery("index.service.url");
        String port = PropertiesUtil.getPropery("index.service.port");
        String serviceUrl = url.concat(MetricPrivilegeConstants.SERVICE_PORT).concat(port).concat(MetricPrivilegeConstants.GET_METRIC_SERVICE_NAME);
        model.addAttribute("METRIC_URL", serviceUrl);
        return "page/role/metricAuth";
    }

    @RequestMapping("listAllApp")
    @ResponseBody
    public List<Map<String, Object>> listAllApp(String role_id) {
        Map<String, Object> parm = new HashMap<>();
        parm.put("role_id", role_id);
        return roleService.listAllApp(parm);
    }

    //角色类型下拉框数据
    @RequestMapping(value = "/getRoleType")
    public void getRoleType(HttpServletResponse res) {
        List<Map<String, Object>> result = roleService.getRoleType();
        String json = getRoleTypeJsonString(result);
        try {
            res.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getRoleTypeJsonString(List<Map<String, Object>> map) {
        org.json.JSONArray json = new org.json.JSONArray();
        org.json.JSONObject jo = null;
        for (Map sysMap : map) {
            jo = new org.json.JSONObject();
            jo.put("roleTypeId", sysMap.get("role_type_id"));
            jo.put("roleTypeName", sysMap.get("role_type_name"));
            json.put(jo);
        }
        return json.toString();
    }

}