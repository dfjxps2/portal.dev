/**
 * <h3>标题 : Quick通用系统框架 </h3>
 * <h3>描述 : 请求类</h3>
 * <h3>日期 : 2017-04-10</h3>
 * <h3>版权 : Copyright (C) 海口鑫网计算机网络有限公司</h3>
 * 
 * <p>
 * @author wtj wtj@xinwing.com.cn
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
package com.quick.portal.userRole;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quick.core.base.model.JsonDataGrid;
import com.quick.core.base.model.PageBounds;
import com.quick.core.util.common.QRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.quick.core.base.ISysBaseService;
import com.quick.core.base.SysBaseController;
import com.quick.core.base.model.DataStore;
import com.quick.core.util.common.DateTime;
import com.quick.core.util.common.ReflectUtil;
import com.quick.portal.security.authority.metric.MetricPrivilegeConstants;
import com.quick.portal.security.authority.metric.PropertiesUtil;

/**
 * 请求类
 * @author Administrator
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/role")
public class RoleController extends SysBaseController<Role> {
    //角色服务
    @Resource(name = "roleService")
    private RoleService roleService;

    @Autowired
    private  RoleDao roleDao;
    
    @Override
    public ISysBaseService getBaseService(){
        return roleService;
    }
    
    //页面请求---开始
    @RequestMapping
    public String list(ModelMap model) {
        return "page/role/list";
    }
    @RequestMapping
    public String edit(ModelMap model) {
        return "page/role/edit";
    }

    @RequestMapping
    public String addRole(ModelMap model) {
        return "page/role/addRole";
    }

    @RequestMapping
    public String roleUsers(ModelMap model) {
        return "page/role/roleUsers";
    }

    @RequestMapping
    public String addRoleUser(ModelMap model) {
        return "page/role/addRoleUser";
    }


    //页面请求---结束
    /**
     * 保存角色
     * @param model
     * @return
     */
    @Override
    public DataStore save(Role model) {
        Date now = DateTime.Now().getTime();
        //名称不能重复
        if(roleService.exist("job_name", model.getRole_name(), model.getRole_id()))
            return ActionMsg.setError("名称已存在，请换一个");
        roleService.save(model);
        return ActionMsg.setOk("ok");
    }
    /**
     *根据角色编码获取该角色对象
     * @return 角色对象
     */
    @Override
    public Map<String, Object> getObj(){
        Map<String,Object> m= getQueryMap(request);
        Role obj= roleService.selectObj(m);
       if(obj==null){
           return null;
       }
        Map<String, Object> p=  ReflectUtil.toMap(obj);
        return p;
    }
    /**
     *根据角色名字精确获取该角色对象
     * @return 角色对象
     */
    @RequestMapping(value = "/getObjByName")
    @ResponseBody
    public Map<String, Object> getObjByName(){
        Map<String,Object> m= getQueryMap(request);
        Role obj= roleService.selectObjByName(m);
        if(obj==null){
            return null;
        }
        Map<String, Object> p=  ReflectUtil.toMap(obj);
        return p;
    }

    /**
     * 角色对应用户数据
     */
    @RequestMapping(value = "/getRoleUsers")
    @ResponseBody
    public String roleUsers(HttpServletResponse res){
        String str="[]";
        //分页参数
        int pageSize= QRequest.getInteger(request,"pageSize",10);
        int pageNo=QRequest.getInteger(request,"page",1);

        //获取表名
        String tableName = getTableName();
        // 排序处理
        String fieldOrder = getFieldOrder();
        String whereStr = "";
        whereStr += getFilterCondition(); // 数据必须受限
        String fieldShow = getFieldShow();

        // 日期格式
        String dateTimeFormat = QRequest.getString(request, "dateTimeFormat",
                "yyyy-MM-dd HH:mm:ss");
        response.setCharacterEncoding("utf-8");
        response.setContentType(QRequest.getResponseType("json")); // 输出JS文件
        // 默认O条数据
        int recordCount = 0;
        Map<String, Object> queryMap = getQueryMap(request, fieldShow,
                tableName, whereStr, fieldOrder);
        PageBounds pager = new PageBounds(pageNo, pageSize);
        List<Map<String, Object>> dt = roleDao.listRoleUser(queryMap,pager);
        recordCount = roleDao.roleUserCount(queryMap);
        str = new JsonDataGrid(recordCount, dt).toJson(dateTimeFormat);
        try {
            response.getWriter().print(str);
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 角色可增加的用户数据
     */

    @RequestMapping(value = "/getUserData")
    @ResponseBody
    public String userData(HttpServletResponse res){
        String str="[]";
        //分页参数
        int pageSize= QRequest.getInteger(request,"pageSize",10);
        int pageNo=QRequest.getInteger(request,"page",1);

        //获取表名
        String tableName = getTableName();
        // 排序处理
        String fieldOrder = getFieldOrder();
        String whereStr = "";
        whereStr += getFilterCondition(); // 数据必须受限
        String fieldShow = getFieldShow();

        // 日期格式
        String dateTimeFormat = QRequest.getString(request, "dateTimeFormat",
                "yyyy-MM-dd HH:mm:ss");
        response.setCharacterEncoding("utf-8");
        response.setContentType(QRequest.getResponseType("json")); // 输出JS文件
        // 默认O条数据
        int recordCount = 0;
        Map<String, Object> queryMap = getQueryMap(request, fieldShow,
                tableName, whereStr, fieldOrder);
        PageBounds pager = new PageBounds(pageNo, pageSize);
        List<Map<String, Object>> dt = roleDao.listUser(queryMap,pager);
        for(Map<String,Object> data : dt){
            if(data.get("user_state").toString().equals("1")){

                data.put("user_state","激活");
            }else {
                data.put("user_state","禁用");
            }
        }
        recordCount = roleDao.recountUsers(queryMap);
        str = new JsonDataGrid(recordCount, dt).toJson(dateTimeFormat);
        try {
            response.getWriter().print(str);
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 提交添加用户
     */
    @RequestMapping(value="/addRoleUsers")
    @ResponseBody
    public  void addUsers(HttpServletResponse res, HttpServletRequest req){
        roleService.addRoleUser(res,req);
    }


    /**
     * 删除角色对应的 用户
     */
    @RequestMapping(value="/deleteRoleUser")
    @ResponseBody
    public  void deleteRoleUser(HttpServletResponse res, HttpServletRequest req){
        roleService.delRoleUser(res,req);
    }

    /**
     * 新增角色
     * @param model
     * @return
     */
    @RequestMapping(value = "/insertRole")
    @ResponseBody
    public String Role(Role model){
        try{
        roleService.insert(model);}
        catch (Exception e)
        {
             e.printStackTrace();
        }
        //return ActionMsg.setOk("ok");
        return "page/role/list";

    }

    /**
     * 修改角色属性内容
     * @param model
     * @return
     */
    @RequestMapping
//    @ResponseBody
    public String updataRole(Role model){
        try {
            if(!"".equals(model.getRole_name()))
                model.setRole_name(URLDecoder.decode(model.getRole_name(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        roleService.update(model);
        return "page/role/list";
    }

    @RequestMapping("listAllMenu")
    @ResponseBody
    public List<Map<String,Object>> listAllMenu(String role_id){
        Map<String, Object> parm = new HashMap<>();
        parm.put("role_id", role_id);
        return roleService.listAllMenu(parm);
    }

    @RequestMapping("listMenuPri")
    @ResponseBody
    public List<Map<String,Object>> listMenuPri(String role_id){
        return roleService.listMenuPri(role_id);
    };

    @RequestMapping("saveMenuPri")
    public String saveMenuPri(String role_id,String menus){
        JSONArray jsonObject= (JSONArray) JSONObject.parse(menus);
        List<String> menuList = jsonObject.toJavaList(String.class);
        roleService.saveMenuPri(role_id,menuList);
        return "page/role/list";
    }

    @RequestMapping
    public String roleAuth(ModelMap model) {
        return "page/role/roleAuth";
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
    public List<Map<String,Object>> listAllApp(String role_id){
        Map<String, Object> parm = new HashMap<>();
        parm.put("role_id", role_id);
        return roleService.listAllApp(parm);
    }
    

    //角色类型下拉框数据
    @RequestMapping(value = "/getRoleType")
    public void getRoleType(HttpServletResponse res) {
        List<Map<String,Object>> result = roleService.getRoleType();
        String json = getRoleTypeJsonString(result);
        try {
            res.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getRoleTypeJsonString(List<Map<String,Object>> map) {
        org.json.JSONArray json = new org.json.JSONArray();
        org.json.JSONObject jo = null;
        for (Map sysMap:map) {
            jo = new org.json.JSONObject();
            jo.put("roleTypeId",sysMap.get("role_type_id"));
            jo.put("roleTypeName",sysMap.get("role_type_name"));
            json.put(jo);
        }
        return json.toString();
    }
    
    
    @RequestMapping("/getRoleHasUser")
    @ResponseBody
    public String getRoleHasUser(String rid){
        return roleService.getRoleHasUser(rid);
    };


}