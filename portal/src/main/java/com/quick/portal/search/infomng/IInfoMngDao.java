/**
 * <h3>标题 : potal统一门户-sys_user </h3>
 * <h3>描述 : sys_user数据访问接口</h3>
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

import com.quick.core.base.ISysBaseDao;
import com.quick.portal.sysUser.SysUserDO;

/**
 * IInfoMngDao数据访问接口
 */
public interface IInfoMngDao<InfoMngDO> extends ISysBaseDao<SysUserDO> {
	public int isExitsVisitInfo(InfoMngDO info);
	public void updExitsVisitInfo(InfoMngDO info);
	public void saveVisitInfo(InfoMngDO info);
	
	public List<Map<String, Object>> searchMsgByUrl(String url);
	
	
	public List<Map<String, Object>> getSolrInfoByCurrUserID(int userID);
	//记录搜索信息
	public void saveSearchTermsInfo(Map<String, Object> param);
	//按热点搜索信息查询
	public List<Map<String, Object>> getHotSearchInfo();
	//按热点搜索信息查询
	public List<Map<String, Object>> getPersonalHabitsInfo(String userID);
	//通过用户编号、关键字查询数据是否重复
	public int isExitsSearchTermsInfo(InfoMngDO info);
	//修改搜索次数
	public void updSearchTermsInfo(InfoMngDO info);
	
	public void saveSearchTermsInfo(InfoMngDO info);
}