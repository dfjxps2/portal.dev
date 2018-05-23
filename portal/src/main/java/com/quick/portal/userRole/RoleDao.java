/**
 * <h3>标题 : Quick通用系统框架 </h3>
 * <h3>描述 : 数据访问接口</h3>
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

import com.quick.core.base.ISysBaseDao;

import java.util.List;
import java.util.Map;

/**
 * 数据访问接口
 * @author Administrator
 */
public interface RoleDao extends ISysBaseDao<Role> {
    //新增角色
    int insert(Map role);
    //更新角色
    int update(Role role);
    //通过姓名精确查找
    Role selectObjByName(Map<String,Object> map);

    //获取全部的集成系统
    List<Map<String, Object>> getAllInteSystem(Map<String, Object> m);

    //获取集成系统的所有角色
    List<Map<String, Object>> getAllInteSystemRole(Map<String, Object> m);


    //新增 ids角色授权
    int setIdsRoleAuthorize(Map<String, Object> m);

    //获取 ids角色授权列表信息
    List<Map<String, Object>> getIdsRoleAuthorizeList(Map<String, Object> m);

    //删除 ids角色授权
    int delIdsRoleAuthorize(Map<String, Object> m);


    //获取 ids用户信息
    List<Map<String, Object>> getIdsUserList(Map<String, Object> m);

    //获取 ids用户信息
    List<Map<String, Object>> getIdsUserListOne(Map<String, Object> m);

    //获取 ids角色与ids用户关系
    List<Map<String, Object>> getIdsRoleAndUserRelationList(Map<String, Object> m);

    //新增  ids用户授权 ids角色
    int insertIdsUsrRoleRelation(Map<String, Object> m);

    //删除  ids用户授权 ids角色
    int delIdsUsrRoleRelation(Map<String, Object> m);


    // 获取第三方集成系统的用户列表
    List<Map<String, Object>>  getSysUserList(Map<String, Object> m);


    //新增 ids用户与第三方用户
    int insertIdsAcctRelation(Map<String, Object> m);

    //删除  ids用户与第三方用户授权
    int delIdsAcctRelation(Map<String, Object> m);


    // 获取ids用户与第三方用户列表
    List<Map<String, Object>>  getIdsAcctrelationList(Map<String, Object> m);

    //角色授权---获取集成列表
    List<Map<String, Object>>  getIdsRoleauthsysList(Map<String, Object> m);

    //新增 ids角色授权 的集成系统表 ids_roleauthsys
    int setIdsRoleauthsys(Map<String, Object> m);
    //删除 ids角色授权 的集成系统关系列表
    int delIdsRoleauthsys(Map<String, Object> m);

    //获取ids用户总数
    int idsCount(Map<String, Object> m);

    //获取集成系统用户总数
    int sysUsrCount(Map<String, Object> m);

    List<Map<String,Object>> listAllMenu(Map<String, Object> m);

    void saveMenuPri(Map<String, Object> m);

    void updateMenuPri(Map<String, Object> m);

    void deleteMenuPri(Map<String, Object> m);

    void removeMenuPriByRole(Map<String, Object> paramMap);

    List<Map<String,Object>> listMenuPri(String role_id);
}