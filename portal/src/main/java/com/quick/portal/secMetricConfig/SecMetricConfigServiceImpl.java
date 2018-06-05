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
package com.quick.portal.secMetricConfig;

import com.quick.core.base.ISysBaseDao;
import com.quick.core.base.SysBaseService;
import com.quick.core.base.model.DataStore;
import com.quick.core.util.common.CommonUtils;
import com.quick.core.util.common.DateTime;
import com.quick.core.util.common.QCookie;
import com.quick.portal.userAccessLog.IUserAccessLogDao;
import com.quick.portal.userAccessLog.IUserAccessLogService;
import com.quick.portal.userAccessLog.UserAccessLogDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * sec_metric_config服务实现类
 */
 @Transactional
 @Service("secMetricConfigService")
public class SecMetricConfigServiceImpl extends SysBaseService<SecMetricConfigDo> implements ISecMetricConfigService {

    /**
     * 构造函数
     */
    public SecMetricConfigServiceImpl() {
        BaseTable = "sec_metric_config";
        BaseComment = "sec_metric_config";
        PrimaryKey = "config_id";
    }
    
    @Autowired
    private ISecMetricConfigDao dao;
    
    @Override
    public ISysBaseDao getDao(){
        return dao;
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
    public int selectUAC( Map<String, Object> queryMap ){
        return dao.selectUAC(queryMap );
    }
    public int insertUAC( Map<String, Object> queryMap ){
        return dao.insertUAC(queryMap );
    }
    public int updateUAC_Active( Map<String, Object> queryMap ){
        return dao.updateUAC_Active(queryMap );
    }
    public int updateUAC_Version( Map<String, Object> queryMap ){
        return dao.updateUAC_Version(queryMap );
    }

}