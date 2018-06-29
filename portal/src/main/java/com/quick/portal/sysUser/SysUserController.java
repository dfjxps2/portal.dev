/**
 * <h3>标题 : potal统一门户-sys_user </h3>
 * <h3>描述 : sys_user请求类</h3>
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
package com.quick.portal.sysUser;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.quick.core.base.model.DataStore;
import com.quick.core.base.model.JsonDataGrid;
import com.quick.core.base.model.PageBounds;
import com.quick.core.util.User.UserUtil;
import com.quick.core.util.common.QCookie;


import com.quick.core.util.common.QRequest;
import com.quick.portal.mesManage.MesManageDO;
import com.quick.portal.userDepartment.IUserDepartmentDao;
import com.quick.portal.userJob.IUserJobDao;
import com.quick.portal.userRole.IUserRoleDao;
import com.quick.portal.userRoleRela.IUserRoleRelaDao;
import com.quick.portal.userRoleRela.UserRoleRelaDO;

import com.seaboxdata.portal.PortalPasswordEncoder;


import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quick.core.base.ISysBaseService;
import com.quick.core.base.SysBaseController;
import com.quick.core.base.model.JsonDataGrid;
import com.quick.core.base.model.PageBounds;
import com.quick.core.util.common.QCookie;
import com.quick.core.util.common.QRequest;
import com.quick.portal.userDepartment.IUserDepartmentDao;
import com.quick.portal.userJob.IUserJobDao;
import com.quick.portal.userRole.IUserRoleDao;
import com.quick.portal.userRoleRela.IUserRoleRelaDao;
import com.quick.portal.userRoleRela.UserRoleRelaDO;
import com.seaboxdata.portal.PortalPasswordEncoder;

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
    private  ISysUserDao iSysUserDao;

    @Autowired
    private IUserRoleRelaDao iUserRoleRelaDao;

    @Autowired
    private IUserRoleDao iUserRoleDao;

    @Autowired
    private IUserJobDao iUserJobDao;

    @Autowired
    private IUserDepartmentDao iUserDepartmentDao;

    @Override
    public ISysBaseService getBaseService(){
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
    public void resetPass(HttpServletResponse res, String account,String state) {
        SysUserDO sysUserDO = new SysUserDO();
        String password = "21232f297a57a5a743894a0e4a801fc3";
        sysUserDO.setUser_password(password);
        sysUserDO.setUser_id(Integer.parseInt(account));
        iSysUserDao.updatePassword(sysUserDO);
        try {
            res.getWriter().write("1");
            res.getWriter().flush();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

   //添加用户
    @RequestMapping(value = "/addUser")
    public void addUsrDetail(Model model, SysUserDO usrDetail, String user_name,  HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        res.setCharacterEncoding("utf-8");
        JSONArray json = new JSONArray();
        Date date= Calendar.getInstance().getTime();//当前添加时间
        SysUserDO sysUserDO = null;
        sysUserDO = new SysUserDO();
        if(usrDetail.getUser_state() != null && !usrDetail.getUser_state().equals ("undefined") && !"null".equals(usrDetail.getUser_real_name())){
            sysUserDO.setUser_state(usrDetail.getUser_state());;//角色状态
        }
        if(usrDetail.getUser_real_name() != null && !usrDetail.getUser_real_name().equals ("undefined") && !"null".equals(usrDetail.getDep_id())){
            sysUserDO.setUser_real_name(usrDetail.getUser_real_name());//真实名称
        }

        if(usrDetail.getDep_id() != null && !usrDetail.getDep_id().equals ("undefined") && !"null".equals(usrDetail.getDep_id())){
            sysUserDO.setDep_id(usrDetail.getDep_id());//部门名称
        }
        if(usrDetail.getJob_id() != null && !usrDetail.getJob_id().equals ("undefined") && !"null".equals(usrDetail.getJob_id())){
            sysUserDO.setJob_id(usrDetail.getJob_id());//岗位名称
        }
        if (!usrDetail.getUser_addr().equals("")&& !usrDetail.getUser_addr().equals ("undefined") && !"null".equals(usrDetail.getUser_addr())){
            sysUserDO.setUser_addr(usrDetail.getUser_addr());;//地址
        }
        if (!usrDetail.getUser_tel().equals("")&& !usrDetail.getUser_tel().equals ("undefined") && !"null".equals(usrDetail.getUser_tel())){
            sysUserDO.setUser_tel(usrDetail.getUser_tel());;//电话
        }
        String password = "21232f297a57a5a743894a0e4a801fc3";
        sysUserDO.setUser_password(password);
        sysUserDO.setCre_time(date);
        sysUserDO.setUpd_time(date);
        sysUserDO.setUser_name(usrDetail.getUser_name());
        iSysUserDao.insert(sysUserDO);

        //将角色和用户关系放入user_role_rela中
        UserRoleRelaDO userRoleRelaDO = new UserRoleRelaDO();
        if(usrDetail.getRole_id() != null && !usrDetail.getRole_id().equals ("undefined") && !"null".equals(usrDetail.getRole_id())){
            userRoleRelaDO.setRole_id(usrDetail.getRole_id());//角色ID
        }
        userRoleRelaDO.setCre_time(date);
        userRoleRelaDO.setUpd_time(null);
        String roleid = iSysUserDao.selectUserId();
        userRoleRelaDO.setUser_id(Integer.parseInt(roleid));
        iUserRoleRelaDao.insert(userRoleRelaDO);
         sysUserDO = new SysUserDO();
         sysUserDO.setUser_id(Integer.parseInt(roleid));
         sysUserDO.setDep_id(usrDetail.getDep_id());
         iSysUserDao.insertUserDepRela(sysUserDO);
        json.put("1");
        res.getWriter().write(json.toString());

    }

     //修改密码
     @RequestMapping(value = "/changPw")
     @ResponseBody
     public void changePassword(Model model,String new_password, HttpServletResponse res)  {
         SysUserDO sysUserDO = new SysUserDO();
         try{
             res.setContentType("text/html");
             res.setCharacterEncoding("utf-8");
             Integer user_id = Integer.parseInt(QCookie.getValue(request,"ids")) ;
//             PortalPasswordEncoder passwordEncoder = new PortalPasswordEncoder("MD5","");
//             String password = passwordEncoder.encode(newPw);
             sysUserDO.setUser_password(new_password);
             sysUserDO.setUser_id(user_id);
             iSysUserDao.updatePassword(sysUserDO);
             res.getWriter().write("1");
             res.getWriter().flush();
         }catch (Exception e){
             e.printStackTrace();
         }

     }
     //密码更改--旧密码校验
    @RequestMapping(value="checkOldPw")
    public void checkParamName(HttpServletResponse res,String user_old_pw) throws IOException {
        Map<String,Object> map = new HashMap<>();
//        PortalPasswordEncoder passwordEncoder = new PortalPasswordEncoder("MD5","");
//        String oldps = passwordEncoder.encode(oldPw);
        map.put("user_password",user_old_pw);
        Integer user_id = Integer.parseInt(QCookie.getValue(request,"ids")) ;
        map.put("user_id",user_id);
        List<Map<String,Object>> result = sysUserService.select(map);
        if(result.size()!= 0){
            res.getWriter().write("true");
        }else {
            res.getWriter().write("false");
        }
        res.getWriter().flush();
    }

    //部门下拉框数据
    @RequestMapping(value = "/getDep")
    public void depView(HttpServletResponse res) {
        HashMap<String,Object> map = new HashMap<>();
        List<Map<String,Object>> userDep = iUserDepartmentDao.select(map);
        String json = getSysJsonString(userDep);
        try {
            res.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getSysJsonString(List<Map<String,Object>> map) {
        JSONArray json = new JSONArray();
        JSONObject jo = null;
        for (Map sysMap:map) {
            jo = new JSONObject();
            jo.put("depid",sysMap.get("dep_id"));
            jo.put("depname",sysMap.get("dep_name"));
            json.put(jo);
        }
        return json.toString();
    }

    //角色下拉框数据
    @RequestMapping(value = "/getRole")
    public void roleView(HttpServletResponse res) {
        HashMap<String,Object> map = new HashMap<>();
        List<Map<String,Object>> userRole = iUserRoleDao.select(map);
        String json = getRoleJsonString(userRole);
        try {
            res.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getRoleJsonString(List<Map<String,Object>> map) {
        JSONArray json = new JSONArray();
        JSONObject jo = null;
        for (Map roleMap:map) {
            jo = new JSONObject();
            jo.put("roleid",roleMap.get("role_id"));
            jo.put("rolename",roleMap.get("role_name"));
            json.put(jo);
        }
        return json.toString();
    }


    //岗位下拉框数据
    @RequestMapping(value = "/getJob")
    public void jobView(HttpServletResponse res) {
        HashMap<String,Object> map = new HashMap<>();
        List<Map<String,Object>> userJob = iUserJobDao.select(map);
        String json = getJobJsonString(userJob);
        try {
            res.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getJobJsonString(List<Map<String,Object>> map) {
        JSONArray json = new JSONArray();
        JSONObject jo = null;
        for (Map jobMap:map) {
            jo = new JSONObject();
            jo.put("jobid",jobMap.get("job_id"));
            jo.put("jobname",jobMap.get("job_name"));
            json.put(jo);
        }
        return json.toString();
    }


    //页面初始化及查询

//    @ResponseBody
//    public Object getList(HttpServletResponse res) throws Exception {
//        return userData("page",res);
//    }
    @RequestMapping(value = "/getUserData")
    @ResponseBody
    public String userData(HttpServletResponse res) {
        try{
            String str = "[]";
        int pageSize = QRequest.getInteger(request, "pageSize", 10); // 获取datagrid传来的行数
        // //每页显示条数
        int pageNo = QRequest.getInteger(request, "page", 1); // 获取datagrid传来的页码

        // 表名
        String tableName = getTableName();
        // 主键
        String primaryKey = getPrimaryKey();
        // 排序处理
        String fieldOrder = getFieldOrder();
        String whereStr = getCondition();
        whereStr += getFilterCondition(); // 数据必须受限
        String fieldShow = getFieldShow();

        // 日期格式
        String dateTimeFormat = QRequest.getString(request, "dateTimeFormat",
                "yyyy-MM-dd HH:mm:ss"); // 例：dateTimeFormat=yyyy-MM-dd HH:mm

        // 默认输出html(?)
        // String contentType = QRequest.getString(request, "responseType",
        // "json"); //输出json 为： responseType=json
        response.setCharacterEncoding("utf-8");
        response.setContentType(QRequest.getResponseType("json")); // 输出JS文件
        // 默认O条数据
        int recordCount = 0;
        Map<String, Object> queryMap = getQueryMap(request, fieldShow,
                tableName, whereStr, fieldOrder);
        PageBounds pager = new PageBounds(pageNo, pageSize);
        List<Map<String, Object>> dt = iSysUserDao.getAllUser(queryMap,pager);
         for(Map<String,Object> data:dt){
             if(data.get("user_state").toString().equals("1")){

                 data.put("user_state","激活");
             }else {
                 data.put("user_state","禁用");
             }
         }
         if(queryMap.get("dep_id")!= null && !queryMap.get("dep_id").equals("")&&  !queryMap.get("dep_id").equals ("undefined") && !"null".equals(queryMap.get("dep_id"))){
             recordCount = iSysUserDao.recount(queryMap.get("dep_id").toString());
         }else {
             recordCount = sysUserService.count(queryMap);//查询总条数
         }
            str = new JsonDataGrid(recordCount, dt).toJson(dateTimeFormat);
            response.getWriter().print(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //用户编辑
    @RequestMapping(value = "/editUser")
    public void editUsrDetail(Model model, SysUserDO usrDetail,HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        res.setCharacterEncoding("utf-8");
        JSONArray json = new JSONArray();
        Date date= Calendar.getInstance().getTime();//当前添加时间
        SysUserDO sysUserDO = new SysUserDO();
        if(usrDetail.getUser_state() != null && !usrDetail.getUser_state().equals ("undefined") && !"null".equals(usrDetail.getUser_state())){
            sysUserDO.setUser_state(usrDetail.getUser_state());//角色状态
        }
        if(usrDetail.getUser_real_name() != null && !usrDetail.getUser_real_name().equals ("undefined") && !"null".equals(usrDetail.getUser_real_name())){
            sysUserDO.setUser_real_name(usrDetail.getUser_real_name());//真实名称
        }
        if(usrDetail.getDep_id() != null && !usrDetail.getDep_id().equals ("undefined") && !"null".equals(usrDetail.getDep_id())){
            sysUserDO.setDep_id(usrDetail.getDep_id());//部门名称
        }
        if(usrDetail.getJob_id() != null && !usrDetail.getJob_id().equals ("undefined") && !"null".equals(usrDetail.getJob_id())){
            sysUserDO.setJob_id(usrDetail.getJob_id());//岗位名称
        }
        if (!usrDetail.getUser_addr().equals("")&& !usrDetail.getUser_addr().equals ("undefined") && !"null".equals(usrDetail.getUser_addr())){
            sysUserDO.setUser_addr(usrDetail.getUser_addr());;//地址
        }
        if (!usrDetail.getUser_tel().equals("")&& !usrDetail.getUser_tel().equals ("undefined") && !"null".equals(usrDetail.getUser_tel())){
            sysUserDO.setUser_tel(usrDetail.getUser_tel());;//电话
        }

        if (!usrDetail.getUser_name().equals("")&& !usrDetail.getUser_name().equals ("undefined") && !"null".equals(usrDetail.getUser_name())){
            sysUserDO.setUser_name(usrDetail.getUser_name());//用户名称
        }

        if (!usrDetail.getUser_id().equals("")&& !usrDetail.getUser_id().equals ("undefined") && !"null".equals(usrDetail.getUser_id())){
            sysUserDO.setUser_id(usrDetail.getUser_id());//用户ID
        }
        if(usrDetail.getRela_id() != null && !usrDetail.getRela_id().equals ("undefined") && !"null".equals(usrDetail.getRela_id())){
             sysUserDO.setRela_id(usrDetail.getRela_id());
            iSysUserDao.updateUserDepRela(sysUserDO);
        }else{
           iSysUserDao.insertUserDepRela(sysUserDO);
        }
        iSysUserDao.update(sysUserDO);
        //将角色和用户关系放入user_role_rela中
        UserRoleRelaDO userRoleRelaDO = new UserRoleRelaDO();
        UserRoleRelaDO ui = new UserRoleRelaDO();
        if(usrDetail.getUser_id() != null && !usrDetail.getUser_id().equals ("undefined") && !"null".equals(usrDetail.getUser_id())){
            userRoleRelaDO.setUser_id(usrDetail.getUser_id());
            ui  = iSysUserDao.getUserRoleRe(usrDetail.getUser_id().toString());  //角色ID
        }
        if(ui!=null && ui.getCre_time() != null && !ui.getCre_time().equals ("undefined") && !"null".equals(ui.getCre_time())) {
            userRoleRelaDO.setCre_time(ui.getCre_time());
        }else{
            userRoleRelaDO.setCre_time(date);
        }//创建时间
        userRoleRelaDO.setUpd_time(date);
        if(usrDetail.getRole_id() != null && !usrDetail.getRole_id().equals ("undefined") && !"null".equals(usrDetail.getRole_id())){
            userRoleRelaDO.setRole_id(usrDetail.getRole_id());
        }
        if(ui!=null && ui.getRel_id() != null && !ui.getRel_id().equals ("undefined") && !"null".equals(ui.getRel_id())) {
            userRoleRelaDO.setRel_id(ui.getRel_id());
            iUserRoleRelaDao.update(userRoleRelaDO);
        }else{
            iUserRoleRelaDao.insert(userRoleRelaDO);
        }

        json.put("1");
        res.getWriter().write(json.toString());

    }

    @RequestMapping(value = "/checkUser")
    public void validateAccount(HttpServletResponse res,String username,String markname) {
        res.setCharacterEncoding("utf-8");
        //去数据库，根据ids_account查询单个用户的信息
        Map<String,Object> editmap = new HashMap<>();
        editmap.put("user_name",username);
        editmap.put("user_id",markname);
        List<Map<String,Object>> map = iSysUserDao.selectByName(username) ;
        List<Map<String,Object>> mapedit = iSysUserDao.select(editmap);
        String nametile = "0";
        for(Map<String,Object> data : mapedit){
            String  uname = data.get("user_name").toString();
            if(uname.equals(username)&& markname != null){
                nametile = "1";
            }
        }
        try {
            if(CollectionUtils.isEmpty(map)|| nametile.equals("1")){
                res.getWriter().write("false");
            }else{
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
    public void deleteUser(String sysid,String relaid ,String relid,HttpServletResponse res ) throws IOException {
        if(sysid != null && !sysid.equals ("undefined") && !"null".equals(sysid)){
         sysUserService.delete(sysid);
        }
        if(relaid != null && !relaid.equals ("undefined") && !"null".equals(relaid)){
           iSysUserDao.deleteUserDepRela(relaid);
        }
        if(relid != null && !relid.equals ("undefined") && !"null".equals(relid)){
           iUserRoleRelaDao.delete(relid);
        }
        try {
            res.getWriter().write("1");
            res.getWriter().flush();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    @RequestMapping(value = "/userEndecrypt")
    public String userEndecrypt(ModelMap model) {
        return view();
    }
//    /*
//     * 解密
//     */
//    @RequestMapping(value = "/decPwdCase")
//    @ResponseBody
//    public void decPwdCase(String pwd,HttpServletResponse res) throws IOException {
//    	 String conPwd = MD5Util.convertMD5(pwd);
//         String decPwd =  MD5Util.convertMD5(conPwd);
//        try {
//            res.getWriter().write(decPwd);
//            res.getWriter().flush();
//        }catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /*
//     * 加密
//     */
//    @RequestMapping(value = "/enPwdCase")
//    @ResponseBody
//    public void enPwdCase(String pwd,HttpServletResponse res) throws IOException {
//         String enPwd =  MD5Util.string2MD5(pwd);
//        try {
//            res.getWriter().write(enPwd);
//            res.getWriter().flush();
//        }catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//=======
//    /*
//     * 解密
//     */
//    @RequestMapping(value = "/decPwdCase")
//    @ResponseBody
//    public void decPwdCase(String pwd,HttpServletResponse res) throws IOException {
///*    	 String conPwd = MD5Util.convertMD5(pwd);
//         String decPwd =  MD5Util.convertMD5(conPwd);*/
//         String decPwd = null;
//        try {
//            res.getWriter().write(decPwd);
//            res.getWriter().flush();
//        }catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /*
//     * 加密
//     */
//    @RequestMapping(value = "/enPwdCase")
//    @ResponseBody
//    public void enPwdCase(String pwd,HttpServletResponse res) throws IOException {
////         String enPwd =  MD5Util.string2MD5(pwd);
//    	String enPwd = null;
//        try {
//            res.getWriter().write(enPwd);
//            res.getWriter().flush();
//        }catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//>>>>>>> .r335

}