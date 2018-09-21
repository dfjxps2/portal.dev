
package com.quick.portal.userRole;

import com.quick.core.base.ISysBaseDao;
import com.quick.core.base.SysBaseService;
import com.quick.core.base.model.DataStore;
import com.quick.core.util.common.QCommon;
import com.quick.core.util.common.ReflectUtil;
import com.quick.portal.sysUser.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务类
 * @author Administrator
 */
 @Service("roleService")
public class RoleServiceImpl extends SysBaseService<UserRoleDO> implements RoleService {

    //初始化
    @Autowired
    public RoleServiceImpl(RoleDao dao) {
        BaseTable = "user_role";
        BaseComment = "";
        PrimaryKey = "role_id";
        NameKey = "role_name";
        this.dao = dao;
    }

    @Resource(name = "sysUserService")
    private ISysUserService sysUserService;

    private RoleDao dao;

    //重写dao
    @Override
    public ISysBaseDao<UserRoleDO> getDao(){
        return dao;
    }

    //新增角色
    @Transactional
    public DataStore insert(UserRoleDO roleDO, String user_role_predicate)
    {
        Map<String, Object> params = ReflectUtil.toMap(roleDO);
        params.put("error_no", new Integer(0));
        params.put("user_role_predicate", user_role_predicate);

        dao.insert(params);

        if (params.get("error_no").toString().equals("0"))
            return ActionMsg.setError("操作失败");
        else
            return ActionMsg.setOk("操作成功");
    }

    //更新角色
    @Override
    public DataStore update(UserRoleDO roleDO, String user_role_predicate)
    {
        Map<String, Object> params = ReflectUtil.toMap(roleDO);
        params.put("error_no", new Integer(0));
        params.put("user_role_predicate", user_role_predicate);

        dao.update(params);

        if (params.get("error_no").toString().equals("0"))
            return ActionMsg.setError("操作失败");
        else
            return ActionMsg.setOk("操作成功");

    }

    @Transactional
    @Override
    public DataStore delete(String role_id) {
        Map<String, Object> params = new HashMap<>();
        params.put("role_id", role_id);

        List<Map<String, Object>> userList = sysUserService.select(params);

        if (userList.size() > 0){
            return ActionMsg.setError("该角色有用户正在使用，无法删除");
        }else
            return super.delete(role_id);
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