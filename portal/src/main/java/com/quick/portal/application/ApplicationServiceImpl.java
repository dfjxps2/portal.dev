/**
 * <h3>标题 : potal统一门户-application </h3>
 * <h3>描述 : application服务实现类</h3>
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
package com.quick.portal.application;

import com.quick.core.base.SysBaseService;
import com.quick.core.base.ISysBaseDao;
import com.quick.core.util.type.TypeUtil;
import com.quick.portal.sysMenu.ISysMenuDao;
import com.quick.portal.sysMenu.SysMenuDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.quick.core.base.model.DataStore;
import com.quick.core.util.common.DateTime;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * application服务实现类
 */
 @Transactional
 @Service("applicationService")
public class ApplicationServiceImpl extends SysBaseService<ApplicationDO> implements IApplicationService {
    
    /**
     * 构造函数
     */
    public ApplicationServiceImpl() {
        BaseTable = "application";
        BaseComment = "应用";
        PrimaryKey = "app_id";
        NameKey = "app_name";
    }
    
    @Autowired
    private IApplicationDao dao;

    @Autowired
    private ISysMenuDao menuDao;
    
    @Override
    public ISysBaseDao getDao(){
        return dao;
    }
    
    /**
     * 保存业务
     * mdy date:20180611
     * mdy desc:保存应用时，不要新增菜单数据
     * @return 
     */
    @Override
    public DataStore save(ApplicationDO entity) {
        //如果编号为空,新增实体对象,否则更新实体对象
        Integer val = entity.getApp_id();
        Date now = DateTime.Now().getTime();
        int c = 0;
        //名称不能重复
        if(exist("app_name", entity.getApp_name(), val))
            return ActionMsg.setError("名称已存在，请换一个");
        if( val == null || val == 0) {
            entity.setCre_time( now );  //新增时间
			entity.setUpd_time( now );  //修改时间

            c = dao.insert(entity);
            //新增应用时加入菜单 （自增多isnert未解决，采用代码插入)
            /*if(c>0) {
                //获取上级菜单Id  应用集成
                Integer super_menu_id = 0;
                Map<String, Object> superParm = new HashMap<>();
                superParm.put("menu_name", "app_menu");
                List<Map<String, Object>> superMap = menuDao.select(superParm);
                if(superMap != null && superParm.size() > 0)
                    super_menu_id = (Integer)TypeUtil.parse(Integer.class, superMap.get(0).get("menu_id"));

                SysMenuDO m = new SysMenuDO();
                m.setMenu_cn_name( entity.getApp_name() );
                m.setMenu_name( entity.getApp_name() );
                m.setMenu_level( entity.getApp_level() );
                if(super_menu_id != null && super_menu_id > 0)
                    m.setSuper_menu_id(super_menu_id);
                m.setMenu_state( entity.getApp_state() );
                m.setMenu_url( entity.getApp_url() );
                m.setApp_id( entity.getApp_id() );
                m.setUpd_time( entity.getUpd_time() );
                m.setCre_time( entity.getCre_time() );

                menuDao.insert(m);
            }*/
        }else {
            entity.setUpd_time( now );  //修改时间
            c = dao.update(entity);
        }
        if(c == 0)
            return ActionMsg.setError("操作失败");
        ActionMsg.setValue(entity);
        return ActionMsg.setOk("操作成功");
    }
    
    /**
     * 删除业务
     * @param sysid
     * @return 
     */
    @Override
    public DataStore delete(String sysid) {
        dao.delete(sysid);
        return ActionMsg.setOk("操作成功");
    }
}