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
package com.quick.portal.web.home;

import com.quick.core.base.ISysBaseDao;
import com.quick.core.base.SysBaseService;
import com.quick.core.base.model.DataStore;
import com.quick.core.util.common.DateTime;
import com.quick.portal.application.ApplicationDO;
import com.quick.portal.application.IApplicationDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * application服务实现类
 */
 @Transactional
 @Service("homeService")
public class HomeServiceImpl extends SysBaseService<ApplicationDO> implements IHomeService {
    
    @Autowired
    private IHomeDao dao;

    /**
     * 查询用户所有应用
     *
     * @param m
     * @return
     */
    @Override
    public List<Map<String, Object>> queryUserApp(Map<String, Object> m) {
        return dao.queryUserApp(m);
    }

    /**
     * 查询用户所有应用2
     *
     * @param m
     * @return
     */
    @Override
    public List<Map<String, Object>> queryUserAllApp(Map<String, Object> m) {
        return dao.queryUserAllApp(m);
    }

    /**
     * 查询所有应用
     *
     * @param m
     * @return
     */
    @Override
    public List<Map<String, Object>> queryApp(Map<String, Object> m) {
        return dao.queryApp(m);
    }

    /**
     * 添加用户桌面
     *
     * @param m
     * @return
     */
    @Override
    public int addDashboard(Map<String, Object> m) {
        int c = dao.countDashboard(m);
        if(c == 0){
            c = dao.addDashboard(m);
            return dao.addDashboard_Apps(m) + c;
        }
        return c;
    }

    /**
     * 更新应用排序
     *
     * @param list
     * @return
     */
    @Override
    public int updateAppSort(List<Map<String, Object>> list) {
        for(Map<String, Object> m : list)
            dao.updateAppSort(m);
        return list.size();
    }

    /**
     * 删除用户应用
     *
     * @param config_id
     * @return
     */
    @Override
    public int deleteApp(String config_id) {
        return dao.deleteApp(config_id);
    }

    /**
     * 添加用户应用
     *
     * @param m
     * @return
     */
    @Override
    public int addApp(Map<String, Object> m) {
        return dao.addApp(m);
    }

    @Override
    public Map<String, Object> queryAppConfig(Map<String, Object> m) {
        Map<String, Object> md = dao.queryAppConfig(m);

        //读取参数
        Boolean noadd = false;
        if(md == null){
            md = new HashMap<>();
            md.put("param_value", 1);
            noadd = true;
        }else{
            String val = md.get("dashboard_id").toString();
            if("0".equals(val))
                noadd = true;
        }
        if(noadd){
            List<Map<String, Object>> dlist = dao.queryDashboard(m);
            if(dlist == null || dlist.size()==0)
                return null;
            md.put("dashboard_id", dlist.get(0).get("dashboard_id"));
        }
        return md;
    }

	@Override
	public String queryDashboard(Map<String, Object> m) {
		String bid = "";
		String ids = "";
		List<Map<String, Object>> retList = dao.queryDashboard(m);
		if(null != retList && retList.size()>0){
			for(Map<String, Object> mp :retList){
				bid += Integer.parseInt(mp.get("dashboard_id").toString())+",";
			}
			ids = bid.endsWith(",")==true?bid.substring(0,bid.length()-1):bid;
		}
		return ids;
	}
    
	
    /**
     * 删除用户应用
     *
     * @param bid :应用配置编号
     * @param aid :应用编号
     * @return
     */
    @Override
    public int deleteDashboardAppByID(String bid,String aid) {
    	Map<String, Object> p = new HashMap<>();
    	p.put("bid", bid);
    	p.put("aid", aid);
        return dao.deleteDashboardAppByID(p);
    }

	@Override
	public List<Map<String, Object>> getUserApp(Map<String, Object> m) {
		  return dao.getUserApp(m);
	}
	
	/*
	 * app端：查询用户所有应用
	 * (non-Javadoc)
	 * @see com.quick.portal.web.home.IHomeService#queryUserAllByApp(java.util.Map)
	 */
	@Override
	public List<Map<String, Object>> queryUserAllByApp(Map<String, Object> m) {
		 return dao.queryUserAllByApp(m);
	}

}