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
package com.quick.portal.indSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.quick.core.base.SysBaseService;
import com.quick.core.base.ISysBaseDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quick.core.base.model.DataStore;
import com.quick.core.util.common.DateTime;
import com.quick.portal.application.ApplicationDO;
import com.quick.portal.sysMenu.SysMenuDO;

/**
 * role_metric_privilege服务实现类
 */
 @Transactional
 @Service("IndSettingService")
public class IndSettingServiceImpl extends SysBaseService<IndSettingDO> implements IndSettingService {
    
    /**
     * 构造函数
     */
    public IndSettingServiceImpl() {
        BaseTable = "role_metric_privilege";
        BaseComment = "role_metric_privilege";
        PrimaryKey = "metric_id";
        NameKey = "metric_id";
    }
    
    @Autowired
    private IndSettingDao dao;
    
    @Override
    public ISysBaseDao getDao(){
        return dao;
    }
    
    /**
     * 保存业务
     * @return 
     */
    @Override
    public DataStore save(IndSettingDO entity) {
    			IndSettingDO m = new IndSettingDO();
                m.setMetric_id(entity.getMetric_id());
                m.setPrivi_id(entity.getPrivi_id());
                m.setRole_id(entity.getRole_id());
                int c = dao.insert(m);
        if(c == 0)
            return ActionMsg.setError("操作失败");
        ActionMsg.setValue(entity);
        return ActionMsg.setOk("操作成功");
    }
    
}