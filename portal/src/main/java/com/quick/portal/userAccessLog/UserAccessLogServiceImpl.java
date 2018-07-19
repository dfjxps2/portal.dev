/**
 * <h3>标题 : potal统一门户-user_access_log </h3>
 * <h3>描述 : user_access_log服务实现类</h3>
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
package com.quick.portal.userAccessLog;

import com.quick.core.base.SysBaseService;
import com.quick.core.base.ISysBaseDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quick.core.base.model.DataStore;
import com.quick.core.util.common.CommonUtils;
import com.quick.core.util.common.DateTime;
import com.quick.core.util.common.QCookie;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * user_access_log服务实现类
 */
 @Transactional
 @Service("userAccessLogService")
public class UserAccessLogServiceImpl extends SysBaseService<UserAccessLogDO> implements IUserAccessLogService {
    
    /**
     * 构造函数
     */
    public UserAccessLogServiceImpl() {
        BaseTable = "user_access_log";
        BaseComment = "user_access_log";
        PrimaryKey = "log_id";
    }
    
    @Autowired
    private IUserAccessLogDao dao;
    
    @Override
    public ISysBaseDao getDao(){
        return dao;
    }
    
    /**
     * 保存业务
     * @return 
     */
    @Override
    public DataStore save(UserAccessLogDO entity) {
        //如果编号为空,新增实体对象,否则更新实体对象
        Integer val = entity.getLog_id();
        int c = 0;
        if(val == 0 || val == null) {
            
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
    public List<Map<String, Object>> selectTopUser(Map<String, Object> map){
        return dao.selectTopUser(map);
    }
    public List<Map<String, Object>> selectTopTag(Map<String, Object> map){
        return dao.selectTopTag(map);
    }
    
    
    /*
     * 日志保存
     * (non-Javadoc)
     * @see com.quick.portal.userAccessLog.IUserAccessLogService#saveLog(int, int, int, java.lang.String)
     */
	@Override
	public void saveLog(HttpServletRequest request,int logTypeId, int userOpType, int menuId,
			String message,String userID, String userNM) {
		 //用户ID
		 String userId = QCookie.getValue(request, "ids");
		 int uid = 0 ;
		 if(null != userId && !"".equals(userId)){
			 uid = Integer.parseInt(userId);
		 }else{
			 uid = Integer.parseInt(userID);
		 }
		 //用户名
		 String uname = QCookie.getValue(request, "sbd.user");
		 if(null == uname  || "".equals(uname)){
			 uname = userNM;

			 if(null == uname || "".equals(uname)){
				 uname = "";
			 }
		 }else{
			 uname = uname ;
		 }
		 UserAccessLogDO entity = new UserAccessLogDO();
		 entity.setLog_time(DateTime.Now().getTime());
		 entity.setLog_type_id(logTypeId);
		 entity.setUser_id(uid);
		 String ip = CommonUtils.getIpAddress(request);
		 entity.setUser_ip(ip);
		 entity.setMenu_id(menuId);
		 entity.setUser_op_type(userOpType);
		 entity.setLog_detail("用户名:".concat(uname).concat(",").concat(message));
		 dao.insert(entity);
	}


}