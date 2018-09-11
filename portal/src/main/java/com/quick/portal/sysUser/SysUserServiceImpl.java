/**
 * <h3>标题 : potal统一门户-sys_user </h3>
 * <h3>描述 : sys_user服务实现类</h3>
 * <h3>日期 : 2018-04-13</h3>
 * <h3>版权 : Copyright (C) 北京东方金信科技有限公司</h3>
 *
 * <p>
 *
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

import com.quick.core.base.SysBaseService;
import com.quick.core.base.model.DataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * sys_user服务实现类
 */
@Transactional
@Service("sysUserService")
public class SysUserServiceImpl extends SysBaseService<SysUserDO> implements ISysUserService {

    /**
     * 构造函数
     */
    @Autowired
    public SysUserServiceImpl(ISysUserDao<SysUserDO> dao) {
        BaseTable = "sys_user";
        BaseComment = "sys_user";
        PrimaryKey = "user_id";
        NameKey = "user_name";

        setDao(dao);
        this.dao = dao;
    }

    private ISysUserDao<SysUserDO> dao;

    @Override
    public DataStore save(SysUserDO sysUserDO) {
        if (sysUserDO.getUser_password() == null || sysUserDO.getUser_password().equals(""))
            sysUserDO.setUser_password("21232f297a57a5a743894a0e4a801fc3");

        sysUserDO.setRoles(String.join(",", sysUserDO.getRole_ids()));

        return super.save(sysUserDO);
    }

    /**
     * 删除业务
     * @param user_id
     * @return
     */
    @Override
    public DataStore delete(String user_id) {
        SysUserDO sysUserDO = new SysUserDO();

        sysUserDO.setUser_id(Integer.valueOf(user_id));

        dao.delete(sysUserDO);
        if (sysUserDO.getError_no() == 1)
            return ActionMsg.setOk("操作成功");
        else
            return ActionMsg.setError("操作失败");
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
        List<Map<String, Object>> retList = dao.getLockCount(ip);
        if (null != retList && retList.size() > 0) {
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
        List<Map<String, Object>> retList = dao.isExitUserInfoByUserId(userId);
        if (null != retList && retList.size() > 0) {
            return retList.get(0);
        }
        return null;
    }

    public List<SysUserDO> getUserInfo(Map<String, Object> m) {
        List<SysUserDO> retList = dao.getUserInfo(m);
        if (null != retList && retList.size() > 0) {
            return retList;
        }
        return null;
    }

    public DataStore updatePassword(SysUserDO sysUserDO){
        int c = dao.updatePassword(sysUserDO);

        if (c == 1)
            return ActionMsg.setOk("密码修改成功");
        else
            return ActionMsg.setError("密码修改失败");
    }
}