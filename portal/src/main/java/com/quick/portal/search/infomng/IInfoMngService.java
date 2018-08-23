/**
 * <h3>标题 : potal统一门户-sys_user </h3>
 * <h3>描述 : sys_user服务接口</h3>
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

import java.util.List;
import java.util.Map;

import com.quick.core.base.ISysBaseService;
import com.quick.core.base.model.PageBounds;

/**
 * IInfoMngService服务接口
 */
public interface IInfoMngService extends ISysBaseService<InfoMngDO> {
	
	 public List getSolrInfo(Map<String, Object> m, PageBounds page,String userID,String type,String termCd);
	 
	 public void saveVisitInfo(String id,int type,int userID);
	 
	 //记录搜索信息
	 public void saveSearchTermsInfo(Map<String, Object> param,String userID);
	 
	//按热点搜索信息查询
	public String getPersonalHabitsInfo(String userID);
	
	
	public String getMsgIDByID (String uid);
	
	public List<Map<String, Object>> getHotSearchInfo();
	
	public List<Map<String, Object>> getPerHabitsInfo(String userID);

}