/**
 * <h3>标题 : potal统一门户- </h3>
 * <h3>描述 : InfoMngUtils服务类</h3>
 * <h3>日期 : 2018-05-21</h3>
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

/**
 * InfoMngUtils服务实现类
 */

public class InfoMngUtils  {
    

	public static String formatHotInfo2String(List<Map<String, Object>> dataList){
		String str = null;
		if(null == dataList || dataList.size()==0){
			return str;
		}else{
			StringBuffer sb = new StringBuffer();
			sb.append("<ul class=\"dropdown clearfix\" id=\"ul_hot\" style=\"width:480px;display:none;\"> ");
			sb.append(" <li id =\"li_hot\" class=\"tit_filter\">确定</li> ");
			sb.append(" <li><input type=\"checkbox\" id=\"all_hot\"  value=\"\" /><label for=\"all\"><strong>所有分类</strong></label></li> ");
			for(Map<String,Object> mp:dataList){
				sb.append("     <li><input type=\"checkbox\"  value=\""+mp.get("KEYWORD")+"\"/><label>"+mp.get("KEYWORD")+"</label></li>");
			}
			str = sb.toString();
		}

		return str;
	}
	

	
	
	public static String formatPersonalHabitsInfo2String(List<Map<String, Object>> dataList,List<Map<String, Object>>  hotDataList){
		StringBuffer sb = new StringBuffer();
		sb.append("<div id =\"div_data\"  style=\"z-index:99999;display:none;\"> ");
		sb.append("<ul class=\"dropdown clearfix\"  style=\"width:580px;\">");
		if(null == hotDataList || hotDataList.size()==0){
			  sb.append("</ul></div>");
		}else{
				  sb.append("<li class=\"tit_filter\" style=\"width:560px;\">热点搜索</li>");
				  for(Map<String,Object> mp:hotDataList){
					  sb.append(" <li style=\"line-height: 20px; margin-right: 15px; float: left;\"> <label class=\"radio-inline\"><input type=\"radio\" name=\"radio\" id=\"r1\" value=\""+mp.get("KEYWORD")+"\" checked=\"checked\" onclick=\"click_event(this)\" /><label for=\"radio\"></label><span>"+mp.get("KEYWORD")+"</span></label></li>");
					}
			  if(null == dataList || dataList.size()==0){
				  sb.append("</ul></div");
			  }else{
				  sb.append("<li class=\"tit_filter\" style=\"width:560px;\">个人喜好搜索</li>"); 
				  for(Map<String,Object> m:dataList){
					  sb.append(" <li style=\"line-height: 20px; margin-right: 15px; float: left;\"> <label class=\"radio-inline\"><input type=\"radio\" name=\"radio\" id=\"r1\" value=\""+m.get("KEYWORD")+"\" checked=\"checked\" onclick=\"click_event(this)\" /><label for=\"radio\"></label><span>"+m.get("KEYWORD")+"</span></label></li>");
					}
				  sb.append("</ul></div>");
			  }
		}
		String str = sb.toString();
		return str;
	}
	
	public static void main(String []args){
		InfoMngUtils iu = new InfoMngUtils();
		List<Map<String,Object>> retList = new ArrayList<Map<String,Object>>();
		Map<String,Object> m = null;
		for(int i=0;i<10;i++){
			m = new HashMap();
			m.put("NAME", "test"+i);
			retList.add(m);
		}
	   String retStr = iu.formatHotInfo2String(retList);
	   System.out.println("----="+retStr);
		
	}
}