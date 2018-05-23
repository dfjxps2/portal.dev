/**
 * <h3>标题 : potal统一门户-msg_source </h3>
 * <h3>描述 : msg_source服务实现类</h3>
 * <h3>日期 : 2018-05-03</h3>
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
package com.quick.portal.msgSource;

import com.quick.core.base.SysBaseService;
import com.quick.core.base.ISysBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.quick.core.base.model.DataStore;
import com.quick.core.util.common.DateTime;

/**
 * msg_source服务实现类
 */
 @Transactional
 @Service("msgSourceService")
public class MsgSourceServiceImpl extends SysBaseService<MsgSourceDO> implements IMsgSourceService {
    
    /**
     * 构造函数
     */
    public MsgSourceServiceImpl() {
        BaseTable = "msg_source";
        BaseComment = "msg_source";
        PrimaryKey = "msg_src_id";
        NameKey = "msg_src_name";
    }
    
    @Autowired
    private IMsgSourceDao dao;
    
    @Override
    public ISysBaseDao getDao(){
        return dao;
    }
    
    /**
     * 保存业务
     * @return 
     */
    @Override
    public DataStore save(MsgSourceDO entity) {
        //如果编号为空,新增实体对象,否则更新实体对象
        Integer val = entity.getMsg_src_id();
        int c = 0;
        if(val == null || val == 0) {
            
            c = dao.insert(entity);
        }else {
            
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