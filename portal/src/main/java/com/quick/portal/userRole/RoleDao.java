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
import com.quick.core.base.model.PageBounds;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 数据访问接口
 * @author Administrator
 */
public interface RoleDao extends ISysBaseDao<UserRoleDO> {
    //新增角色
    int insert(Map<String, Object> map);
    //更新角色
    int update(Map<String, Object> map);

    List<Map<String,Object>> listAllMenu(Map<String, Object> m);

    void saveMenuPri(Map<String, Object> m);

    void updateMenuPri(Map<String, Object> m);

    void deleteMenuPri(Map<String, Object> m);

    void removeMenuPriByRole(Map<String, Object> paramMap);

    List<Map<String,Object>> listMenuPri(String role_id);

    List<Map<String,Object>> listAllApp(Map<String, Object> m);

    List<Map<String,Object>> listAppTree(Map<String, Object> m);

    int saveInitApp(@Param("role_id") Integer role_id, @Param("menuList") List<Map<String, Object>> menuList);

    List<Map<String,Object>> getRoleType();

}
