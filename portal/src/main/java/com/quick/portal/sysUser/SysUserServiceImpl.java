/**
 * <h3>标题 : potal统一门户-sys_user </h3>
 * <h3>描述 : sys_user服务实现类</h3>
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
package com.quick.portal.sysUser;

import java.util.List;
import java.util.Map;

import com.quick.core.base.SysBaseService;
import com.quick.core.base.ISysBaseDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quick.core.base.model.DataStore;
import com.quick.core.util.common.DateTime;

/**
 * sys_user服务实现类
 */
 @Transactional
 @Service("sysUserService")
public class SysUserServiceImpl extends SysBaseService<SysUserDO> implements ISysUserService {
    
    /**
     * 构造函数
     */
    public SysUserServiceImpl() {
        BaseTable = "sys_user";
        BaseComment = "sys_user";
        PrimaryKey = "user_id";
        NameKey = "user_name";
    }
    
    @Autowired
    private ISysUserDao dao;
    
    @Override
    public ISysBaseDao getDao(){
        return dao;
    }
    
    /**
     * 保存业务
     * @return 
     */
    @Override
    public DataStore save(SysUserDO entity) {
        //如果编号为空,新增实体对象,否则更新实体对象
        Integer val = entity.getUser_id();
        int c = 0;
        if(val == null || val == 0 ) {
            entity.setCre_time( DateTime.Now().getTime() );  //新增时间
			entity.setUpd_time( DateTime.Now().getTime() );  //修改时间

            c = dao.insert(entity);
        }else {
            entity.setUpd_time( DateTime.Now().getTime() );  //修改时间

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
    

	/*
	 * 锁定用户帐号
	 */
	@Override
	public void updateUserStatueByUersId(String userId) {
		 dao.updateUserStatueByUersId(userId);
		
	}
	
	
	/*
	 * 查询指定IP,密码错误次数
	 */
	@Override
	public Map<String, Object> getLockCount(String ip) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> retList = dao.getLockCount(ip);
		if(null  != retList && retList.size()>0){
			return retList.get(0);
		}	
		return null;
	}
	
	/*
	 *  通过用户名称查询用户信息
	 * (non-Javadoc)
	 * @see com.quick.portal.sysUser.ISysUserService#isExitUserInfoByUserId(java.lang.String)
	 */
	@Override
	public Map<String, Object> isExitUserInfoByUserId(String userId) {
		List<Map<String,Object>> retList = dao.isExitUserInfoByUserId(userId);
		if(null  != retList && retList.size()>0){
			return retList.get(0);
		}	
		return null;
	}
}