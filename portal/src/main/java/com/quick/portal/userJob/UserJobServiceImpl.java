/**
 * <h3>标题 : potal统一门户-user_job </h3>
 * <h3>描述 : user_job服务实现类</h3>
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
package com.quick.portal.userJob;

import com.quick.core.base.SysBaseService;
import com.quick.core.base.ISysBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.quick.core.base.model.DataStore;
import com.quick.core.util.common.DateTime;

import java.util.Date;

/**
 * user_job服务实现类
 */
 @Transactional
 @Service("userJobService")
public class UserJobServiceImpl extends SysBaseService<UserJobDO> implements IUserJobService {
    
    /**
     * 构造函数
     */
    public UserJobServiceImpl() {
        BaseTable = "user_job";
        BaseComment = "user_job";
        PrimaryKey = "job_id";
        NameKey = "job_name";
    }
    
    @Autowired
    private IUserJobDao dao;
    
    @Override
    public ISysBaseDao getDao(){
        return dao;
    }
    
    /**
     * 保存业务
     * @return 
     */
    @Override
    public DataStore save(UserJobDO entity) {
        //如果编号为空,新增实体对象,否则更新实体对象
        Integer val = entity.getJob_id();
        int c = 0;
        Date now = DateTime.Now().getTime();
        //名称不能重复
        if(exist("job_name", entity.getJob_name(), val))
            return ActionMsg.setError("名称已存在，请换一个");
        if (val != null && val != 0) {
            entity.setUpd_time( now );  //修改时间

            c = dao.update(entity);
        } else {
            entity.setCre_time( now );  //新增时间
			entity.setUpd_time( now );  //修改时间

            c = dao.insert(entity);
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