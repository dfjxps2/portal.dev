
package com.quick.portal.userRole;

import com.quick.core.base.ISysBaseService;
import com.quick.core.base.SysBaseController;
import com.quick.core.base.model.DataStore;
import com.quick.core.util.common.CommonUtils;
import com.quick.core.util.common.QCookie;
import com.quick.portal.security.authority.metric.MetricPrivilegeConstants;
import com.quick.portal.security.authority.metric.PropertiesUtil;
import com.quick.portal.userAccessLog.UserAccessLogServiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 请求类
 *
 * @author Administrator
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/role")
public class RoleController extends SysBaseController<UserRoleDO> {

    private Logger logger = LoggerFactory.getLogger(getClass());

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
    public String addRoleUser(ModelMap model) {
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
    public DataStore save(UserRoleDO model,HttpServletRequest request) {
        if (model.getRole_id() != null && model.getRole_id() >= 0) {
            loggerInfoUpdateRoleInfo(model);
            return roleService.update(model, rstr("user_role_predicate"));
        } else {
            loggerInfoInsertRoleInfo(model);
            return roleService.insert(model, rstr("user_role_predicate"));
        }
    }

    @RequestMapping
    @ResponseBody
    public List<Map<String, Object>> listAppTree(String role_id) {
        Map<String, Object> param = new HashMap<>();
        param.put("role_id", role_id);

        List<Map<String, Object>> appListForRole = roleService.listAppTree(param);

        return appListForRole;
    }

    @RequestMapping
    @ResponseBody
    public DataStore saveInitApp(String role_id, String[] appList) {

        List<Map<String, Object>> dataList = Arrays.stream(appList).map(item -> {
            Map<String, Object> map = new HashMap<>();
            String[] cols = item.split(",");
            map.put("menu_id", cols[0]);
            map.put("show_order", Integer.valueOf(cols[1]));
            return map;
        }).collect(Collectors.toList());

        DataStore result = roleService.saveInitApp(Integer.valueOf(role_id), dataList);

        return result;
    }

    @RequestMapping
    public String metricAuth(ModelMap model) {
        String url = PropertiesUtil.getPropery("index.service.url");
        String port = PropertiesUtil.getPropery("index.service.port");
        String serviceUrl = url.concat(MetricPrivilegeConstants.SERVICE_PORT).concat(port).concat(MetricPrivilegeConstants.GET_METRIC_SERVICE_NAME);
        model.addAttribute("METRIC_URL", serviceUrl);
        return "page/role/metricAuth";
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


    @RequestMapping(value = "/deleteRole")
    @ResponseBody
    public DataStore deleteRole(String role_id) {
        loggerInfoDeleteRoleInfo(role_id);
        return super.deleteAction();
    }


    private void loggerInfoDeleteRoleInfo(String id){
        String userName = QCookie.getValue(request, "sbd.user_name");
        String ip = CommonUtils.getIpAddrAdvanced(request);
        String requestResult = "系统操作员:"+userName+"删除角色信息->角色编号:"+id;
        String operateType = "统一门户->角色管理服务->删除角色";
        String operatedUser = "被操作角色编号:"+id;
        String operLog = "角色管理服务日志->删除角色";
        String serviceName = "服务名称:角色管理服务;服务方法名:";
        UserAccessLogServiceUtils.loggerLogInfo(logger,
                userName,operatedUser,operateType,requestResult,operLog,serviceName,ip);
    }

    private void loggerInfoUpdateRoleInfo(UserRoleDO model){
        String userName = QCookie.getValue(request, "sbd.user_name");
        String ip = CommonUtils.getIpAddrAdvanced(request);
        String requestResult = "系统操作员:"+userName+"修改角色信息->把角色编号:"+model.getRole_id()+"的角色名称修改为"+model.getRole_name();
        String operateType = "统一门户->角色管理服务->修改角色";
        String id = model.getRole_id().toString();
        String operatedUser = "被操作角色编号:"+id+":角色名称:"+model.getRole_name();
        String operLog = "角色管理服务日志->修改角色";
        String serviceName = "服务名称:角色管理服务;服务方法名:";
        UserAccessLogServiceUtils.loggerLogInfo(logger,
                userName,operatedUser,operateType,requestResult,operLog,serviceName,ip);
    }


    public void loggerInfoInsertRoleInfo(UserRoleDO model){
        String userName = QCookie.getValue(request, "sbd.user_name");
        String ip = CommonUtils.getIpAddrAdvanced(request);
        String requestResult = "系统操作员:"+userName+"新增角色信息->新增角色名称:"+model.getRole_name();
        String operateType = "统一门户->角色管理服务->新增角色";
        String operatedUser = "被操作角色名称:"+model.getRole_name();
        String operLog = "角色管理服务日志->新增角色";
        String serviceName = "服务名称:角色管理服务;服务方法名:";
        UserAccessLogServiceUtils.loggerLogInfo(logger,
                userName,operatedUser,operateType,requestResult,operLog,serviceName,ip);
    }

}