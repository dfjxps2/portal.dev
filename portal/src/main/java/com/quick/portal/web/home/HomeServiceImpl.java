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
import com.quick.portal.web.model.DataResult;

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


	
	/*
	 * app端：查询用户所有应用
	 * (non-Javadoc)
	 * @see com.quick.portal.web.home.IHomeService#queryUserAllByApp(java.util.Map)
	 */
	@Override
	public List<Map<String, Object>> queryUserAllByApp(Map<String, Object> m) {
		 return dao.queryUserAllByApp(m);
	}
	
	/*
	 * 查询当前用户未订阅的应用列表
	 * (non-Javadoc)
	 * @see com.quick.portal.web.home.IHomeService#queryUnSubscribeByApp(java.util.Map)
	 */
	@Override
	public List<Map<String, Object>> queryUnSubscribeByApp(Map<String, Object> m) {
		return dao.queryUnSubscribeByApp(m);
	}
	
	/*
	 *
     * app端 查询当前用户已订阅的应用列表
     * @param m
     * @return
     */
	@Override
	public List<Map<String, Object>> querySubscribedByApp(Map<String, Object> m) {
		return dao.querySubscribedByApp(m);
	}
	
	/*
	 * 保存应用
	 * (non-Javadoc)
	 * @see com.quick.portal.web.home.IHomeService#dosave(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public int dosave(String userID, String aid, String did) {
		if(null != did && !"".equals(did)){
			//删除应用
			this.dodel(userID,did);
		}
		
		if(null != aid && !"".equals(aid)){
			//增加应用
			this.doadd(userID,aid);
		}
		return 1;
		
	}
	
	/*
	 * 增加应用
	 */
	public void doadd(String userID, String id) {
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("user_id", userID);
		Map<String, Object> mp = this.queryAppConfig(p);
		 	String dsid = (String) mp.get("dashboard_id").toString();;
		 	Integer did = Integer.valueOf(dsid);
	        String sno = mp.get("param_value").toString();
	        Integer param_value = sno.length() == 0 ? 1 : Integer.valueOf(sno);
	        String[] ids = id.split(",");
	        for(String str : ids){
	            Map<String, Object> m = new HashMap<>();
	            m.put("dashboard_id", did);
	            m.put("param_value", param_value);
	            m.put("app_id", str);
	            m.put("param_id", 1);
	            this.addApp(m);
	            param_value++;
	        }
	}
	
	/*
	 * 删除应用
	 */
	public void dodel(String userID, String id) {
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("user_id", userID);
    	String bid = this.queryDashboard(p);
            String[] ids = id.split(",");
            for (String str : ids) {
                this.deleteDashboardAppByID(bid,str);
            }
	}
	
	/*
     * 判断重复数据（应用编号、仪表表编号）
     */
	@Override
	public boolean isExitsAppInfo(Map<String, Object> m) {
		boolean bool = false;
		int count = dao.isExitsAppInfo(m);
		if(count >0){
			bool = true;
		}
		return bool;
	}
	
}