/**
 * <h3>标题 : potal统一门户-metric_privilege </h3>
 * <h3>描述 : menu_privilege服务实现类</h3>
 * <h3>日期 : 2018-04-13</h3>
 * <h3>版权 : Copyright (C) 北京东方金信科技有限公司</h3>
 * 
 * <p>
 * @author 你自己的姓名 cxh@seaboxdata.com
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
package com.quick.portal.security.authority.metric;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quick.core.base.ISysBaseDao;
import com.quick.core.base.SysBaseService;
import com.quick.core.base.model.DataStore;
import com.quick.portal.search.infomng.SolrInfoConstants;
import com.quick.portal.search.infomng.SolrUtils;

/**
 * metric_privilege服务实现类
 */
 @Transactional
 @Service("metricPrivilegeService")
public class MetricPrivilegeServiceImpl extends SysBaseService<MetricPrivilegeDO> implements IMetricPrivilegeService {
    
    /**
     * 构造函数
     */
    public MetricPrivilegeServiceImpl() {
        BaseTable = "role_metric_privilege";
        BaseComment = "role_metric_privilege";
        PrimaryKey = "pri_id";
    }
    
    @Autowired
    private IMetricPrivilegeDao dao;
    
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

    /*
     * 指标信息查询
     */
	@Override
	public List<Map<String, Object>> listAllMetric() {
		  List<Map<String,Object>> result = dao.listAllMetric();
	      return result;
	}
	/*  
	 * 
	 * 设置指标权限
	 *  step 0: delete
     *  step 1: insert
     * (non-Javadoc)
     * @see com.quick.portal.userRole.RoleService#saveMenuPri(java.lang.String, java.util.List)
     */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveMetricPrivilege(String roleId, List<String> metricList) {
		Map<String,Object> paramMap = new HashMap();
		String metricID = null;
        paramMap.put(MetricPrivilegeConstants.ROLE_ID_CONTS,roleId);
        dao.removeMetricPriByRole(paramMap);
        if(null != metricList && metricList.size()>0){
            for(String srcMetricId : metricList){
            	metricID = searchMetricBySrcMetricId(srcMetricId);
                paramMap.put(MetricPrivilegeConstants.METRIC_ID_CONTS,metricID);
                dao.saveMetricPrivilege(paramMap);
            }
        }  
	}
	
	/*
	 * 通过部门编号查询部门ID
	 */
	public String searchMetricBySrcMetricId(String srcMetricId) {
		String metricID = "";
		List<Map<String, Object>> retList = dao.searchMetricBySrcMetricId(srcMetricId);
		if(null != retList && retList.size() >0){
			Map<String, Object> mp = retList.get(0);
			metricID = mp.get(MetricPrivilegeConstants.METRIC_ID_FIELD).toString();
		}
		return metricID;
	}

	/*
     * 通过角色查询指标权限
     */
	@Override
	public List<Map<String, Object>> listMetricPrivilege(String roleId) {
		List<Map<String,Object>> result = dao.listMetricPrivilege(roleId);
        return result;
	}

	/*
	 * 保存指标权限 
	 * (non-Javadoc)
	 * @see com.quick.portal.security.authority.metric.IMetricPrivilegeService#saveMetricData(java.util.List)
	 */
/*	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveMetricData(List<MetricBean> retList) {
		// TODO Auto-generated method stub
		String pid = null;
		String pName = null;
		String id = null;
		String name = null;
		boolean bool = false;
		boolean flag = false;
		for(MetricBean mtcBean:retList){
			pid = mtcBean.getCategory_id();
			pName = mtcBean.getCategory_name();
			bool = isExistMetricByID(pid);
			if(bool){
				updateMetricData(MetricPrivilegeConstants.ROOT_NODE,pid,pName);
			}else{
				saveMetricData(MetricPrivilegeConstants.ROOT_NODE,pid,pName);
			}
			
			List<MetricBeanVO> mLst = mtcBean.getMeasures();
			for(MetricBeanVO mb :mLst){
				 id = mb.getMeasure_id();
				 name = mb.getMeasure_name();
				 flag = isExistMetricByID(id);
				 if(flag){
					 updateMetricData(pid,id,name);	
				 }else{
					 saveMetricData(pid,id,name);
				 }
			}
			
		}
	}*/
	
	/*
	 * 保存指标权限 
	 * (non-Javadoc)
	 * @see com.quick.portal.security.authority.metric.IMetricPrivilegeService#saveMetricData(java.util.List)
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveMetricData(List<MetricBean> retList) {
		String pid = null;
		String pName = null;
		boolean bool = false;
		for(MetricBean mtcBean:retList){
			pid = mtcBean.getCategory_id();
			pName = mtcBean.getCategory_name();
			//父节点指标
			bool = isExistMetricByID(pid);
			if(bool){
				updateMetricData(MetricPrivilegeConstants.ROOT_NODE,pid,pName);
			}else{
				saveMetricData(MetricPrivilegeConstants.ROOT_NODE,pid,pName);
			}
			//solr增加指标
			SolrUtils.addSolrInfo(pid, pName, SolrInfoConstants.INDEX_OBJ_TYPE, pName);
			List<MetricBeanVO> mList = mtcBean.getMeasures();
			if(null != mList && mList.size()>0){
				//保存子节点指标数据
				saveChildMetricData(pid,mList);
			}
		}
	}
	
	/*
	 * step1:保存子节点指标数据
	 * step2:solr增加指标
	 */
	@Transactional(rollbackFor = Exception.class)
	public void saveChildMetricData(String pid, List<MetricBeanVO> childList) {
		String id = null;
		String name = null;
		boolean flag = false;;
		for(MetricBeanVO mb :childList){
			 id = mb.getMeasure_id();
			 name = mb.getMeasure_name();
			 flag = isExistMetricByID(id);
			 if(flag){
				 updateMetricData(pid,id,name);	
			 }else{
				 saveMetricData(pid,id,name);
			 }
			SolrUtils.addSolrInfo(id, name, SolrInfoConstants.INDEX_OBJ_TYPE, name);
		}
	}
	/*
	 * 保存指标数据
	 */
	public void saveMetricData(String pid,String id,String name){
		Map<String,Object> paramMap = new HashMap();
		paramMap.put(MetricPrivilegeConstants.PID_CONTS, pid);
		paramMap.put(MetricPrivilegeConstants.ID_CONTS, id);
		paramMap.put(MetricPrivilegeConstants.NAME_CONTS,name);
		dao.saveMetricData(paramMap);
	};
	/*
	 * 判断源指标ID是否存在
	 */
	public boolean isExistMetricByID(String id){
		boolean bool = false;
		int count = dao.isExistMetricByID(id);
		if(count >0){
			bool = true;
		}else{
			bool = false;
		}
		return bool;
	}
	/*
	 * 修改指标数据
	 */
	public void updateMetricData(String pid,String id,String name){
		Map<String,Object> paramMap = new HashMap();
		paramMap.put(MetricPrivilegeConstants.PID_CONTS, pid);
		paramMap.put(MetricPrivilegeConstants.ID_CONTS, id);
		paramMap.put(MetricPrivilegeConstants.NAME_CONTS,name);
		dao.updateMetricData(paramMap);
	};
}