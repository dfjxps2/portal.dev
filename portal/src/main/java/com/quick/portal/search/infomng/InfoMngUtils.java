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
			sb.append("<div class=\"wrap\"> ");
			sb.append(" <table>");
			sb.append("    <thead>");
			sb.append("     	<tr>");
			sb.append("     		<th>");
			sb.append("     			<input type=\"checkbox\" id=\"j_cbAll\" />");
			sb.append("     		</th>");
			sb.append("     		<th>关键字</th>");
			sb.append("     	</tr>");
			sb.append("     </thead>");
			sb.append("     	<tbody id=\"j_tb\">");
			for(Map<String,Object> mp:dataList){
				sb.append("     <tr>");
				sb.append("     	<td> <input type=\"checkbox\" /></td>");
				sb.append("     	<td> "+mp.get("NAME")+"</td>");
				sb.append("     </tr>");
			}
			sb.append("    </tbody>");
			sb.append(" </table>");
			sb.append("</div>");
			str = sb.toString();
		}

		return str;
	}
	

	
	
	public static String formatPersonalHabitsInfo2String(List<Map<String, Object>> dataList){
		String str = null;
		if(null == dataList || dataList.size()==0){
			return str;
		}else{
			StringBuffer sb = new StringBuffer();
			sb.append("<div class=\"wrap\"> ");
			sb.append(" <table>");
			sb.append("    <thead>");
			sb.append("     	<tr>");
			sb.append("     		<th>");
			sb.append("     			<input type=\"checkbox\" id=\"j_cbAll\" />");
			sb.append("     		</th>");
			sb.append("     		<th>关键字</th>");
			sb.append("     	</tr>");
			sb.append("     </thead>");
			sb.append("     	<tbody id=\"j_tb\">");
			for(Map<String,Object> mp:dataList){
				sb.append("     <tr>");
				sb.append("     	<td> <input type=\"checkbox\" /></td>");
				sb.append("     	<td> "+mp.get("NAME")+"</td>");
				sb.append("     </tr>");
			}
			sb.append("    </tbody>");
			sb.append(" </table>");
			sb.append("</div>");
			str = sb.toString();
		}
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