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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.quick.core.base.ISysBaseService;
import com.quick.core.base.SysBaseController;
import com.quick.core.base.model.DataStore;
import com.quick.core.base.model.JsonDataGrid;
import com.quick.core.base.model.PageBounds;
import com.quick.core.util.common.DateTime;
import com.quick.core.util.common.JsonUtil;
import com.quick.core.util.common.QRequest;
import com.quick.core.util.common.ReflectUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

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
        Date nowDate=new Date();
        model.setIDS_ROLEDATE(nowDate);
        roleService.save(model);
        //roleService.selectObj(sysid)
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
    //获取角色列表 以及分页
    @Override
    public Object getList() throws Exception {
       try {
           String str = "[]";
           Map<String, Object> p = getQueryMap(request);
           int pageSize = QRequest.getInteger(request, "pageSize", 99999); // 获取datagrid传来的行数
           // //每页显示条数
           int pageNo = QRequest.getInteger(request, "page", 1); // 获取datagrid传来的页码
           // //当前页

           // 表名
           String tableName = getTableName();
           // 主键
           String primaryKey = getPrimaryKey();
           // 排序处理

           String fieldOrder ="role_id asc";//getFieldOrder();
           String sort=QRequest.getString(request,"IDSROLESORTBYIDSUSR");//ids角色列表根据ids用户点击事件，来判断排序
           if(sort!=""){
               fieldOrder="sort";
           }
           String whereStr = getCondition();
           whereStr += getFilterCondition(); // 数据必须受限
           String fieldShow = getFieldShow();

           // 日期格式
           String dateTimeFormat = QRequest.getString(request, "dateTimeFormat", "yyyy-MM-dd HH:mm:ss"); // 例：dateTimeFormat=yyyy-MM-dd HH:mm

           // 默认输出html(?)
           // String contentType = QRequest.getString(request, "responseType",
           // "json"); //输出json 为： responseType=json

           response.setCharacterEncoding("utf-8");
           response.setHeader("If-Modified-Since", "Thu, 01 Jun 1970 00:00:00 GMT");
           response.setContentType(QRequest.getResponseType("json")); // 输出JS文件

           // 默认O条数据
           int recordCount = 0;
           Map<String, Object> queryMap = getQueryMap(request, fieldShow, tableName, whereStr, fieldOrder);
           PageBounds pager = new PageBounds(pageNo, pageSize);

           List<Map<String, Object>> dt = roleService.select(queryMap, pager);
           //getBaseService().select(queryMap, pager);

           recordCount = pager.getTotal();
           str = new JsonDataGrid(recordCount, dt).toJson(dateTimeFormat);

            response.getWriter().print(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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



    //获取ids的集成系统 列表
    @RequestMapping
    @ResponseBody
    public Object getAllInteSystem(){
      String str = "[]";
      try{
        Map<String,Object> p=new HashMap<String,Object>();
        p.put("IDS_ROLEID",QRequest.getString(request,"IDS_ROLEID"));
        p.put("ALL_IDS_ROLEID",QRequest.getString(request,"ALL_IDS_ROLEID"));//用于获取两个表（ids_roleauthsys，ids_rolesysrelation）的集成系统
        p.put("IDS_USERID",QRequest.getString(request,"IDS_USERID"));
        String roleByUsrid = QRequest.getString(request,"ROELBYUSRID");
        p.put("ROELBYUSRID",roleByUsrid);//用于点击ids账号查询出集成系统
        p.put("IDS_SYSAUTHTYPE",QRequest.getString(request,"IDS_SYSAUTHTYPE")); //过滤授权类型
        p.put("SORT",QRequest.getString(request,"SORT")); //排序
        p.put("SORT2",QRequest.getString(request,"SORT2"));
        p.put("SORTBYIDSUSR",QRequest.getString(request,"SORTBYIDSUSR"));
      List<Map<String,Object>> dt= roleService.getAllInteSystem(p);
        str = new JsonDataGrid(dt.size(), dt).toJson();

          response.getWriter().print(str);
      }catch (Exception e){
          e.printStackTrace();
      }
        return null;
    }
    //获取ids集成系统的所有角色 列表
    @RequestMapping
    @ResponseBody
    public Object getAllInteSystemRole(){
        String strRole = "[]";
        try{
            Map<String,Object> p=new HashMap<String,Object>();
            p.put("IDS_ROLEID",QRequest.getString(request,"IDS_ROLEID"));
            p.put("IDS_SYSID",QRequest.getString(request,"IDS_SYSID"));
            //排序
            p.put("SYSORLESORT",QRequest.getString(request,"SYSORLESORT"));//角色授权页面的集成系统列表根据打钩排序
        List<Map<String,Object>> dt= roleService.getAllInteSystemRole(p);
        strRole = new JsonDataGrid(dt.size(), dt).toJson();

            response.getWriter().print(strRole);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    //获取 ids角色授权列表信息
    @RequestMapping
    @ResponseBody
    public Object getIdsRoleAuthorizeList(){
        String str="[]";
        Map<String,Object> p=new HashMap<String,Object>();
        p.put("IDS_ROLEID",QRequest.getString(request,"IDS_ROLEID"));
        p.put("IDS_SYSID",QRequest.getString(request,"IDS_SYSID"));
        List<Map<String,Object>> dt= roleService.getIdsRoleAuthorizeList(p);
        str = new JsonDataGrid(dt.size(), dt).toJson();
        try{
            response.getWriter().print(str);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //获取 ids用户信息
    @RequestMapping
    @ResponseBody
    public Object getIdsUserList(){
        String str="[]";
        //int pageSize = QRequest.getInteger(request, "pageSize", 99999); // 获取datagrid传来的行数
        // //每页显示条数
        int pageNum = QRequest.getInteger(request, "page",1); // 获取datagrid传来的页码

        int pageS=QRequest.getInteger(request, "pageSize", 10);//一页显示多少条

        try{
        Map<String,Object> p=new HashMap<String,Object>();
        String IDS_ROLEID =QRequest.getString(request,"IDS_ROLEID");
        p.put("IDS_ROLEID",IDS_ROLEID);
        //p.put("IDS_USER",QRequest.getString(request,"IDS_USER"));
        //p.put("IDS_USERNAME",QRequest.getString(request,"IDS_USERNAME"));
        String IDS_ACOUNT_USERNAME=QRequest.getString(request,"IDS_ACOUNT_USERNAME");
        p.put("IDS_ACOUNT_USERNAME",IDS_ACOUNT_USERNAME);
        p.put("pageB",(pageNum-1)*pageS);//从第几条开始
        p.put("pageS",pageS);
        List<Map<String,Object>> dt= roleService.getIdsUserListOne(p);
        int count= 10000;
         //控制总数
            if(!"".equals(IDS_ACOUNT_USERNAME) || !"".equals(IDS_ROLEID)) {
                dt=roleService.getIdsUserList(p);
            }

        str = new JsonDataGrid(count, dt).toJson();
        response.getWriter().print(str);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    //角色授权---获取角色授权集成系统列表 ids_roleauthsys
    @RequestMapping
    @ResponseBody
    public Object getIdsRoleauthsysList(){
        String str="[]";
        try{
            Map<String,Object> p=new HashMap<String,Object>();
            p.put("IDS_ROLEID",QRequest.getString(request,"IDS_ROLEID"));

            List<Map<String,Object>> dt= roleService.getIdsRoleauthsysList(p);
            str = new JsonDataGrid(dt.size(), dt).toJson();
            response.getWriter().print(str);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //角色管理--》集成系统角色授权
    @RequestMapping
    @ResponseBody
    public Object setIdsRoleAuthorize(){
        try {
            String IDS_ROLEID = QRequest.getString(request, "IDS_ROLEID");
            String IDS_SYSROLE_LIST = QRequest.getString(request, "IDS_SYSROLEID_LIST");//系统角色主键：id
            String IDS_SYSID = QRequest.getString(request, "IDS_SYSID");
            String IDS_SYSID_LIST = QRequest.getString(request, "IDS_SYSID_LIST");//集成系统id 列表
            Map<String, Object> p = new HashMap<String, Object>();
/*
            // 只根据 删除
            p.put("IDS_ROLEID", IDS_ROLEID);
            roleService.delIdsRoleauthsys(p);
            p.clear();
            String[] SYSID=IDS_SYSID_LIST.split(",");
            if(IDS_SYSID_LIST==""||IDS_SYSID_LIST==null){
                p.put("IDS_ROLEID", IDS_ROLEID);
                roleService.delIdsRoleAuthorize(p);
                return ActionMsg.setOk("ok");
            }
            for(String s:SYSID){
                UUID uuid = UUID.randomUUID();//随机生成唯一id
                String str = uuid.toString().replaceAll("-", "");
                p.put("ID", str);
                p.put("IDS_ROLEID", IDS_ROLEID);
                p.put("IDS_SYSID", s);
                roleService.setIdsRoleauthsys(p);
                p.clear();
            }
            */
            // 只根据ids角色删除
            p.put("IDS_ROLEID", IDS_ROLEID);
            roleService.delIdsRoleAuthorize(p);
            p.clear();

            if(IDS_SYSROLE_LIST=="" || IDS_SYSROLE_LIST==null){
                 return ActionMsg.setOk("ok");
            }
            List<Map<String, Object>> list =  JsonUtil.fromJson(IDS_SYSROLE_LIST, ArrayList.class, Map.class);
            for (Map<String, Object> map : list) {
                UUID uuid = UUID.randomUUID();//随机生成唯一id
                String str = uuid.toString().replaceAll("-", "");
                p.put("ID", str);
                p.put("IDS_ROLEID", IDS_ROLEID);
                p.put("IDS_SYSROLEID", map.get("IDS_SYSROLEID"));
                p.put("IDS_SYSID", map.get("IDS_SYSID"));
                roleService.setIdsRoleAuthorize(p);//根据系统角色主键id 授权
                p.clear();
            }
            return ActionMsg.setOk("ok");
        }catch (Exception e){
            e.printStackTrace();
            return null;

        }
    }
    //角色管理--》角色授权集成系统
    @RequestMapping
    @ResponseBody
    public Object setIdsRoleAuthorizeToSystem(){
      try{
          String IDS_ROLEID = QRequest.getString(request, "IDS_ROLEID");
          String IDS_SYSID_LIST = QRequest.getString(request, "IDS_SYSID_LIST");//集成系统id 列表
          Map<String, Object> p = new HashMap<String, Object>();
          // 只根据角色id 删除
          p.put("IDS_ROLEID", IDS_ROLEID);
          roleService.delIdsRoleauthsys(p);
          p.clear();
          String[] SYSID=IDS_SYSID_LIST.split(",");
          if(IDS_SYSID_LIST==""||IDS_SYSID_LIST==null){
              p.put("IDS_ROLEID", IDS_ROLEID);
              roleService.delIdsRoleAuthorize(p);
              return ActionMsg.setOk("ok");
          }
          for(String s:SYSID){
              UUID uuid = UUID.randomUUID();//随机生成唯一id
              String str = uuid.toString().replaceAll("-", "");
              p.put("ID", str);
              p.put("IDS_ROLEID", IDS_ROLEID);
              p.put("IDS_SYSID", s);
              roleService.setIdsRoleauthsys(p);
              p.clear();
          }
          return ActionMsg.setOk("ok");
      }catch (Exception e){
          e.printStackTrace();
          return null;
      }
    }
    //ids用户授权
    @RequestMapping
    @ResponseBody
    public Object insertIdsUsrRoleRelation(){
        try {
            String IDS_ROLEID_LIST = QRequest.getString(request, "IDS_ROLEID_LIST");
            String IDS_USERID = QRequest.getString(request, "IDS_USERID");
            Map<String, Object> p = new HashMap<String,Object>();
            p.put("ACCOUNTID", IDS_USERID);
            //删除
            roleService.delIdsUsrRoleRelation(p);
            p.clear();
            String[] IDS_ROLEID = IDS_ROLEID_LIST.split(",");
            if (IDS_ROLEID[0] == "") {
                return ActionMsg.setOk("ok");
            }
            for (int i = 0; i < IDS_ROLEID.length; i++) {
                UUID uuid = UUID.randomUUID();//随机生成唯一id
                String str = uuid.toString().replaceAll("-", "");
                p.put("ROLEID", IDS_ROLEID[i]);
                p.put("ACCOUNTID", IDS_USERID);
                p.put("ID",str);
                roleService.insertIdsUsrRoleRelation(p);
                p.clear();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ActionMsg.setOk("ok");
    }

    //ids用户授权 list
    @RequestMapping
    @ResponseBody
    public Object getIdsUsrRoleRelationList(){
        String str="[]";
        try{
            Map<String,Object> p=new HashMap<String,Object>();
            p.put("IDS_USERID",QRequest.getString(request,"IDS_USERID"));
            List<Map<String,Object>> dt= roleService.getIdsRoleAndUserRelationList(p);
            str = new JsonDataGrid(dt.size(), dt).toJson();
            response.getWriter().print(str);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // 获取第三方集成系统的用户列表
    @RequestMapping
    @ResponseBody
    public Object getSysUserList(){
        try {
            int pageNum = QRequest.getInteger(request, "page",1); // 获取datagrid传来的页码
            int pageS=QRequest.getInteger(request, "pageSize", 99999);//一页显示多少条
            String str = "[]";
            Map<String,Object> p=new HashMap<String,Object>();
            p.put("IDS_SYSID",QRequest.getString(request,"IDS_SYSID"));
            p.put("JCXTUSERSORTBYIDSUSER",QRequest.getString(request,"JCXTUSERSORTBYIDSUSER"));//排序
            p.put("SYS_ACOUNT_USERNAME",QRequest.getString(request,"SYS_ACOUNT_USERNAME"));//账号、用户名
            p.put("pageB",(pageNum-1)*pageS);//从第几条开始
            p.put("pageS",pageS);
            List<Map<String,Object>> dt= roleService.getSysUserList(p);
            int count=roleService.sysUsrCount(p);
            str = new JsonDataGrid(count, dt).toJson();
            response.getWriter().print(str);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return null;
    }
    //ids用户与第三方用户 授权
    @RequestMapping
    @ResponseBody
    public Object  insertIdsAcctRelation(){
        try {
            String IDS_USERID = QRequest.getString(request, "IDS_USERID");
            String IDS_SYSUSRID_LIST = QRequest.getString(request, "IDS_SYSUSRID_LIST");
            String IDS_SYSID= QRequest.getString(request, "IDS_SYSID");
            Map<String, Object> p = new HashMap<String,Object>();
           // p.put("IDS_USERID", IDS_USERID);
            p.put("IDS_ACCOUNTID", IDS_USERID);//只根据集成系统主键 id删除
            //删除
            roleService.delIdsAcctRelation(p);
            p.clear();
            String[] IDS_SYSUSRID = IDS_SYSUSRID_LIST.split(",");
            if (IDS_SYSUSRID[0] == "") {
                return ActionMsg.setOk("ok");
            }
            for (int i = 0; i < IDS_SYSUSRID.length; i++) {
                p.put("IDS_ACCOUNTID", IDS_USERID);
                p.put("ID", IDS_SYSUSRID[i]);
                roleService.insertIdsAcctRelation(p);
                p.clear();
            }

        }catch (Exception e){
            e.printStackTrace();
            ActionMsg.setError("授权错误");
        }
        return ActionMsg.setOk("ok");
    }
    //获取ids用户与第三方用户列表
    @RequestMapping
    @ResponseBody
    public Object getIdsAcctrelationList() {
        try {
            String str = "[]";
            String IDS_USERID = QRequest.getString(request, "IDS_USERID");
            String IDS_SYSID = QRequest.getString(request, "IDS_SYSID");

            Map<String, Object> p = new HashMap<String,Object>();
            p.put("IDS_USERID", IDS_USERID);
            p.put("IDS_SYSID", IDS_SYSID);
            List<Map<String, Object>> dt = roleService.getIdsAcctrelationList(p);
            str = new JsonDataGrid(dt.size(), dt).toJson();
            response.getWriter().print(str);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @RequestMapping
    public String roleAuth(ModelMap model) {
        return "page/role/roleAuth";
    }
    
    @RequestMapping
    public String metricAuth(ModelMap model) {
        return "page/role/metricAuth";
    }
}