/**
 * <h3>标题 : Quick通用系统框架 </h3>
 * <h3>描述 : 服务类</h3>
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

import com.quick.core.base.SysBaseService;
import com.quick.core.base.ISysBaseDao;
import com.quick.core.util.common.QCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务类
 * @author Administrator
 */
 @Service("roleService")
public class RoleServiceImpl extends SysBaseService<Role> implements RoleService {

    //初始化
    public RoleServiceImpl() {
        BaseTable = "user_role";
        BaseComment = "";
        PrimaryKey = "role_id";
        NameKey = "role_name";
    }

    @Autowired
    private RoleDao dao;

    //重写dao
    @Override
    public ISysBaseDao getDao(){
        return dao;
    }


    //新增角色
    @Override
    public int insert(Role role) {
        return dao.insert(role);
    }
    //更新角色
    @Override
    public int update(Role role) {
        return dao.update(role);
    }

    //获取全部的集成系统
    @Override
    public List<Map<String, Object>> getAllInteSystem(Map<String, Object> m) {
        return dao.getAllInteSystem(m);
    }
    //获取集成系统的所有角色
    @Override
    public List<Map<String, Object>> getAllInteSystemRole(Map<String, Object> m) {
        return dao.getAllInteSystemRole(m);
    }
    //新增 ids角色授权
    @Override
    public int setIdsRoleAuthorize(Map<String, Object> m) {
        return dao.setIdsRoleAuthorize(m);
    }
    //获取 ids角色授权列表信息
    @Override
    public List<Map<String, Object>> getIdsRoleAuthorizeList(Map<String, Object> m) {
        return dao.getIdsRoleAuthorizeList(m);
    }

    //删除 ids角色授权
    @Override
    public int delIdsRoleAuthorize(Map<String, Object> m) {
        return dao.delIdsRoleAuthorize(m);
    }



    //获取 ids用户信息
    @Override
    public List<Map<String, Object>> getIdsUserList(Map<String, Object> m) {
        return dao.getIdsUserList(m);
    }

    @Override
    public List<Map<String, Object>> getIdsUserListOne(Map<String, Object> m) {
        return dao.getIdsUserListOne(m);
    }

    //获取 ids角色与ids用户关系
    @Override
    public List<Map<String, Object>> getIdsRoleAndUserRelationList(Map<String, Object> m) {
        return dao.getIdsRoleAndUserRelationList(m);
    }
    //新增  ids用户授权 ids角色
    @Override
    public int insertIdsUsrRoleRelation(Map<String, Object> m) {
        return dao.insertIdsUsrRoleRelation(m);
    }
    //删除  ids用户授权 ids角色
    @Override
    public int delIdsUsrRoleRelation(Map<String, Object> m) {
        return dao.delIdsUsrRoleRelation(m);
    }

    // 获取第三方集成系统的用户列表
    @Override
    public List<Map<String, Object>> getSysUserList(Map<String, Object> m) {
        return dao.getSysUserList(m);
    }

    //新增 ids用户与第三方用户
    @Override
    public int insertIdsAcctRelation(Map<String, Object> m) {
        return dao.insertIdsAcctRelation(m);
    }
    //删除  ids用户与第三方用户授权
    @Override
    public int delIdsAcctRelation(Map<String, Object> m) {
        return dao.delIdsAcctRelation(m);
    }
    // 获取ids用户与第三方用户列表
    @Override
    public List<Map<String, Object>> getIdsAcctrelationList(Map<String, Object> m) {
        return dao.getIdsAcctrelationList(m);
    }

    //角色授权---获取集成列表
    @Override
    public List<Map<String, Object>> getIdsRoleauthsysList(Map<String, Object> m) {
        return dao.getIdsRoleauthsysList(m);
    }

    //新增 ids角色授权 的集成系统表 ids_roleauthsys
    @Override
    public int setIdsRoleauthsys(Map<String, Object> m) {
        return dao.setIdsRoleauthsys(m);
    }
    //删除 ids角色授权 的集成系统关系列表
    @Override
    public int delIdsRoleauthsys(Map<String, Object> m) {
        return dao.delIdsRoleauthsys(m);
    }

    //获取ids账号总数
    @Override
    public int idsCount(Map<String, Object> m) {
        return dao.idsCount(m);
    }
   //获取集成系统账号总数
    @Override
    public int sysUsrCount(Map<String, Object> m) {
        return dao.sysUsrCount(m);
    }

    @Override
    public List<Map<String, Object>> listAllMenu(Map<String, Object> m) {
        List<Map<String,Object>> result = dao.listAllMenu(m);
        return result;
    }
    
    /*  step 0: delete
     *  step 1: insert
     * (non-Javadoc)
     * @see com.quick.portal.userRole.RoleService#saveMenuPri(java.lang.String, java.util.List)
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveMenuPri(String role_id, List<String> menuList) {
        List<Map<String,Object>> oldlst = dao.listMenuPri(role_id);
        List<Map<String,Object>> newlst = new ArrayList<>();
        //dao.removeMenuPriByRole(paramMap);
        if(null != menuList && menuList.size()>0){
            for(String str : menuList){
                Map<String,Object> paramMap = new HashMap();
                paramMap.put("role_id",role_id);
                //menu_id,show_on_init,show_order
                String[] arr = QCommon.split(str, "|");
                if(arr.length > 0)
                    paramMap.put("menu_id",arr[0]);
                if(arr.length == 3){
                    paramMap.put("show_on_init",arr[1]);
                    paramMap.put("show_order",arr[2]);
                }
                newlst.add(paramMap);
                if(findMenuId(oldlst, arr[0]))
                    dao.updateMenuPri(paramMap);
                else
                    dao.saveMenuPri(paramMap);
            }
            //删除不存在权限
            for(Map<String, Object> o :oldlst){
                String v = o.get("menu_id").toString();
                if(!findMenuId(newlst, v))
                    dao.deleteMenuPri(o);
            }
        }
    }
    private Boolean findMenuId(List<Map<String, Object>> lst, String menu_id){
        for(Map<String, Object> m : lst){
            Object s = m.get("menu_id");
            if(s != null && s.toString().equals(menu_id))
                return true;
        }
        return false;
    }


    @Override
    public List<Map<String, Object>> listMenuPri(String role_id) {
        List<Map<String,Object>> result = dao.listMenuPri(role_id);
        return result;
    }
    /*
       * 根据姓名精确查找*/
    public Role selectObjByName(Map<String,Object> map){
        return dao.selectObjByName(map);
    }
}