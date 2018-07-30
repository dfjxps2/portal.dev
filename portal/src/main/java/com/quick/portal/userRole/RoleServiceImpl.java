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
    /* 根据姓名精确查找*/
    @Override
    public Role selectObjByName(Map<String,Object> map){
        return dao.selectObjByName(map);
    }

    @Override
    public List<Map<String, Object>> listAllApp(Map<String, Object> m) {
        List<Map<String,Object>> result = dao.listAllApp(m);
        return result;
    }

	@Override
	public List<Map<String, Object>> getRoleType() {
		return dao.getRoleType();
	}
}