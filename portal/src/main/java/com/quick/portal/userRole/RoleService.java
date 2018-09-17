/**
 * <h3>标题 : Quick通用系统框架 </h3>
 * <h3>描述 : 服务接口</h3>
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

import com.quick.core.base.ISysBaseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

/**
 * 服务接口
 * @author Administrator
 */
public interface RoleService extends ISysBaseService<UserRoleDO> {
    //新增角色
    int insert(UserRoleDO roleDO);
    //更新角色
    int update(UserRoleDO roleDO);

    UserRoleDO selectObjByName(Map<String,Object> map);

    List<Map<String,Object>> listAllMenu(Map<String, Object> m);

    void saveMenuPri(String role_id, List<String> menuList);

    List<Map<String,Object>> listMenuPri(String role_id);

    List<Map<String,Object>> listAllApp(Map<String, Object> m);
    
    List<Map<String,Object>> getRoleType();

    void  delRoleUser(HttpServletResponse res, HttpServletRequest req);

    void addRoleUser(HttpServletResponse res, HttpServletRequest req);
    //该角色下是否有用户
    String getRoleHasUser(String rid);

}