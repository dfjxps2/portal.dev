/**
 * <h3>标题 : potal统一门户-InfoMngServiceImpl </h3>
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
package com.quick.portal.search.infomng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quick.core.base.ISysBaseDao;
import com.quick.core.base.SysBaseService;
import com.quick.core.base.model.PageBounds;

/**
 * InfoMngServiceImpl服务实现类
 */
 @Transactional
 @Service("infoMngService")
public class InfoMngServiceImpl extends SysBaseService<InfoMngDO> implements IInfoMngService {
    
    /**
     * 构造函数
     */
    
    @Autowired
    private IInfoMngDao dao;
    
    @Override
    public ISysBaseDao getDao(){
        return dao;
    }
    
   
    
    
    @Override
    public List<Map<String, Object>> select(Map<String, Object> m, PageBounds page) {
    	return null;
    }

	@Override
	public List<Map<String, Object>> getSolrInfo(Map<String, Object> m,
			PageBounds page,String userID,String type) {
		List<Map<String, Object>> dataList = new ArrayList<>();
    	String keyword = m.get(SolrInfoConstants.INDEX_KEYWORD).toString();
        if(null == keyword || "".equals(keyword)){
        	return dataList;
        }else{
        	Integer uid = Integer.valueOf(userID);
        	List<Map<String, Object>> retList = getSolrInfoByCurrUserID(uid);
        	dataList = SolrUtils.searchInfoDataByCondition(m, page,retList,type);
        }
    	return dataList;
		
	}

	/*
	 * 保存文件与SOLR关系记录到表中
	 * (non-Javadoc)
	 * @see com.quick.portal.search.infomng.IInfoMngService#saveVisitInfo(java.lang.String, int, int)
	 */
	@Override
	public void saveVisitInfo(String url,int type,int userID) {
		// TODO Auto-generated method stub
		InfoMngDO info = new InfoMngDO();
		String msgID = searchMsgByUrl(url);
		int ojbID = 0;
		if(null == msgID || "".equals(msgID)){
			ojbID = ojbID;
		}else{
			ojbID =Integer.valueOf(msgID);
		}
		info.setUser_id(userID);
		info.setVisit_obj_id(ojbID);
		boolean bool = isExitsVisitInfo(info);
		if(bool){
			updExitsVisitInfo(info);
		}else{
			saveVisitInfo(info);
		}
		
	}
	
	/*
	 * 判断重复
	 * 通过消息编号、用户编号查询数据是否重复
	 */
	public boolean isExitsVisitInfo(InfoMngDO info) {
		boolean bool = false;
		int count = dao.isExitsVisitInfo(info);
		if(count >0){
			bool = true;
		}else{
			bool = false;
		}
		return bool ;
	}
	
	/*
	 * 修改表数据
	 */
	public void updExitsVisitInfo(InfoMngDO info) {
		 dao.updExitsVisitInfo(info);
	}
	
	
	/*
	 * 新增表数据
	 */
	public void saveVisitInfo(InfoMngDO info) {
		 dao.saveVisitInfo(info);
	}
	
	
	
	/*
	 * 通过url查询信息ID
	 */
	public String searchMsgByUrl(String url) {
		String msgID = "";
		List<Map<String, Object>> retList = dao.searchMsgByUrl(url);
		if(null != retList && retList.size() >0){
			Map<String, Object> mp = retList.get(0);
			msgID = mp.get("MSG_ID").toString();
		}
		return msgID;
	}
	
	

	public List<Map<String, Object>> getSolrInfoByCurrUserID(int userID) {
		List<Map<String, Object>> dataList = dao.getSolrInfoByCurrUserID(userID);
    	return dataList;
	}



	/*
	 * 记录搜索信息
	 * (non-Javadoc)
	 * @see com.quick.portal.search.infomng.IInfoMngService#saveSearchTermsInfo(java.util.Map)
	 */
	@Override
	public void saveSearchTermsInfo(Map<String, Object> param,String userID) {
		// TODO Auto-generated method stub
		InfoMngDO info = new InfoMngDO();
		int uid = 0 ;
		if(null == userID ||"".equals(userID)){
			uid = uid;
		}else{
			info.setUser_id(Integer.valueOf(userID));
		}
		String keyword = param.get(SolrInfoConstants.INDEX_KEYWORD).toString();
		info.setKeyword(keyword);
		boolean bool = isExitsSearchTermsInfo(info);
		if(bool){
			updSearchTermsInfo(info);
		}else{
			saveSearchTermsInfo(info);
		}
		
	}
	
	
	/*
	 * 判断重复
	 * 通过消息编号、用户编号查询数据是否重复
	 */
	public boolean isExitsSearchTermsInfo(InfoMngDO info) {
		boolean bool = false;
		int count = dao.isExitsSearchTermsInfo(info);
		if(count >0){
			bool = true;
		}else{
			bool = false;
		}
		return bool ;
	}
	
	/*
	 * 修改表数据
	 */
	public void updSearchTermsInfo(InfoMngDO info) {
		 dao.updSearchTermsInfo(info);
	}
	
	
	/*
	 * 新增表数据
	 */
	public void saveSearchTermsInfo(InfoMngDO info) {
		 dao.saveSearchTermsInfo(info);
	}


	/*
	 * 按热点搜索信息查询
	 * (non-Javadoc)
	 * @see com.quick.portal.search.infomng.IInfoMngService#getHotSearchInfo()
	 */
	
	public List<Map<String, Object>> getHotSearchInfo() {
		List<Map<String, Object>> dataList = dao.getHotSearchInfo();
		return dataList;
	}


	
	/*
	 * 按热点搜索信息查询
	 * (non-Javadoc)
	 * @see com.quick.portal.search.infomng.IInfoMngService#getPersonalHabitsInfo(java.lang.String)
	 */
	@Override
	public String getPersonalHabitsInfo(String userID) {
		int uid = 0;
		if(null != userID && !"".equals(userID)){
			uid = Integer.valueOf(userID);
		}
		List<Map<String, Object>> personalDataList = dao.getPersonalHabitsInfo(uid);
		//按热点搜索信息查询
		List<Map<String, Object>> hotDataList = getHotSearchInfo();
		String retStr = InfoMngUtils.formatPersonalHabitsInfo2String(personalDataList,hotDataList);
		return retStr;
	}
	
	
	
	
}