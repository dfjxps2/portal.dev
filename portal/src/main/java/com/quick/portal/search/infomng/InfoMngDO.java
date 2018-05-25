/**
 * <h3>标题 : potal统一门户-InfoMngDO </h3>
 * <h3>描述 : sys_user数据对象</h3>
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



/**
 * InfoMngDO数据对象
 */
public class InfoMngDO  implements java.io.Serializable {
	
    private static final long serialVersionUID = 1L;
	
    
    private Integer stats_id;
    /**
     *用户ID
     */
    private Integer  user_id;   
    
    
    private Integer visit_obj_id;
    
    private Integer visit_obj_type;
    
    
    private String msg_content;
    
    
    private String obj_type;
    
    
    private String keyword;
    
    
    public Integer getStats_id() {
		return stats_id;
	}


	public void setStats_id(Integer stats_id) {
		this.stats_id = stats_id;
	}


	public Integer getUser_id() {
		return user_id;
	}


	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}


	public Integer getVisit_obj_id() {
		return visit_obj_id;
	}


	public void setVisit_obj_id(Integer visit_obj_id) {
		this.visit_obj_id = visit_obj_id;
	}


	public Integer getVisit_obj_type() {
		return visit_obj_type;
	}


	public void setVisit_obj_type(Integer visit_obj_type) {
		this.visit_obj_type = visit_obj_type;
	}


	public Integer getVisit_times() {
		return visit_times;
	}


	public void setVisit_times(Integer visit_times) {
		this.visit_times = visit_times;
	}


	public String getMsg_content() {
		return msg_content;
	}


	public void setMsg_content(String msg_content) {
		this.msg_content = msg_content;
	}


	public String getObj_type() {
		return obj_type;
	}


	public void setObj_type(String obj_type) {
		this.obj_type = obj_type;
	}


	public String getKeyword() {
		return keyword;
	}


	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}


	private Integer visit_times;
	
	
	

}