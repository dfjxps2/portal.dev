/**
 * <h3>标题 : potal统一门户-sys_menu </h3>
 * <h3>描述 : sys_menu服务实现类</h3>
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
package com.quick.portal.sysMenu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quick.core.base.ISysBaseDao;
import com.quick.core.base.SysBaseService;
import com.quick.core.base.model.DataStore;
import com.quick.core.util.common.DateTime;
import com.quick.portal.application.IApplicationDao;

/**
 * sys_menu服务实现类
 */
 @Transactional
 @Service("sysMenuService")
public class SysMenuServiceImpl extends SysBaseService<SysMenuDO> implements ISysMenuService {
    
    /**
     * 构造函数
     */
    public SysMenuServiceImpl() {
        BaseTable = "sys_menu";
        BaseComment = "sys_menu";
        PrimaryKey = "menu_id";
        NameKey = "menu_name";
    }
    
    @Autowired
    private ISysMenuDao dao;

    @Autowired
    private IApplicationDao daos;
    
    @Override
    public ISysBaseDao getDao(){
        return dao;
    }
    
    /**
     * 保存业务
     * @return 
     */
    @Override
    public DataStore save(SysMenuDO entity) {
        //如果编号为空,新增实体对象,否则更新实体对象
        Integer val = entity.getMenu_id();
        int c = 0;
        if(val == null || val == 0) {
            entity.setCre_time( DateTime.Now().getTime() );  //新增时间
			entity.setUpd_time( DateTime.Now().getTime() );  //修改时间

            c = dao.insert(entity);
        }else {
            entity.setUpd_time( DateTime.Now().getTime() );  //修改时间

            c = dao.update(entity);
        }
        this.updateMenuLevel(entity);
        if(c == 0)
            return ActionMsg.setError("操作失败");
        ActionMsg.setValue(entity);
        return ActionMsg.setOk("操作成功");
    }
    
    
	public void updateMenuLevel(SysMenuDO menuVO) {
		Map<String,Object> paramMap = new HashMap();
		String sMenuID  = null;
		String menuID = null;
		int menuLevel = 0;
		//公服标识查询部门上级编号数据
		List<Map<String, Object>> menuList = this.searchMenuInfoByID(menuVO.getMenu_id());
		for (Map<String, Object> m : menuList){
		    	//通过上级部门编号查询部门ID
				  sMenuID = m.get("SUPER_MENU_ID").toString();
		    	  menuID = m.get("MENU_ID").toString();
		    	  menuLevel = Integer.parseInt(m.get("MENU_LEVEL").toString());
		    	  menuLevel = menuLevel +1;
		    	  System.out.println("menuID="+menuID+"menuID="+sMenuID+"menuLevel="+menuLevel);
		    	  paramMap.put("super_menu_id", sMenuID);
		    	  paramMap.put("menu_id", menuID);
		    	  paramMap.put("menu_level", menuLevel);
				  dao.updateMenuLevel(paramMap);
		  }
		
	}
	
	
	public List<Map<String, Object>> searchMenuInfoByID(int menuID) {
		List<Map<String, Object>> result = dao.searchMenuInfoByID(menuID);
		return result;
	}
    
    @Override
    public List<Map<String, Object>> listAllMenu() {
        List<Map<String,Object>> result = dao.listAllMenu();
        return result;
    }
    
    
    @Override
    public DataStore delete(String sysid) {
        dao.delete(sysid);
        return ActionMsg.setOk("操作成功");
    }
    
    /**
     * 删除业务
     * @param sysid
     * @return 
     */
    @Override
    public DataStore deletes(List<String> menuid,int menu_state) {
    	SysMenuDO menu = new SysMenuDO();
    	int j = 0;
    	if (menuid.size()>0) {
    		menu.setMenu_state(menu_state);
    		for (int i = 0; i < menuid.size(); i++) {
    			j = i + 1;
    			menu.setMenu_id(Integer.valueOf(menuid.get(i)));
    			menu.setUpd_time( DateTime.Now().getTime() );  //修改时间
    			dao.deletes(menu);
			}
		}
    	if (j>0) {
    		return ActionMsg.setOk("操作成功！");
		}else{
			 return ActionMsg.setOk("操作失败！");
		}
    }

    @Override
    public List<Map<String, Object>> getApp() {
        // TODO Auto-generated method stub
        return daos.select(null);
    }
    
    @Override
	public String getIsAppMenuByID(int menuID) {
		String flag = "0";
		SysMenuDO menu = new SysMenuDO();
		menu.setMenu_id(menuID);
		int count = dao.getIsAppMenuByID(menu);
		if(count >0){
			flag = "1";
		}else{
			flag = "0";
		}
		return flag;
	}
}