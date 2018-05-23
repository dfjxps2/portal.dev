/**
 * <h3>标题 : potal统一门户-page </h3>
 * <h3>描述 : page数据对象</h3>
 * <h3>日期 : 2018-05-03</h3>
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
package com.quick.portal.page;

import java.util.Date;


/**
 * page数据对象
 */
public class PageDO implements java.io.Serializable {
	
    private static final long serialVersionUID = 1L;
	
    // <editor-fold defaultstate="collapsed" desc="私有成员">
    /**
     *
     */
    private Integer  page_id;     	
    /**
     *
     */
    private Integer  template_id; 	
    /**
     *
     */
    private String  page_name;   	
    /**
     *
     */
    private Integer  user_id;     	
    /**
     *
     */
    private Date  cre_time;    	
    /**
     *
     */
    private Date  upd_time;    	
	// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="成员Get/Set">
    /**
     *   Get方法
     * @return 
     */
    public Integer getPage_id() {
        return this.page_id;
    }
    /**
     *   Set方法
     * @return 
     */
    public void setPage_id(Integer page_id) {
        this.page_id = page_id;
    }
    /**
     *   Get方法
     * @return 
     */
    public Integer getTemplate_id() {
        return this.template_id;
    }
    /**
     *   Set方法
     * @return 
     */
    public void setTemplate_id(Integer template_id) {
        this.template_id = template_id;
    }
    /**
     *   Get方法
     * @return 
     */
    public String getPage_name() {
        return this.page_name;
    }
    /**
     *   Set方法
     * @return 
     */
    public void setPage_name(String page_name) {
        this.page_name = page_name;
    }
    /**
     *   Get方法
     * @return 
     */
    public Integer getUser_id() {
        return this.user_id;
    }
    /**
     *   Set方法
     * @return 
     */
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
    /**
     *   Get方法
     * @return 
     */
    public Date getCre_time() {
        return this.cre_time;
    }
    /**
     *   Set方法
     * @return 
     */
    public void setCre_time(Date cre_time) {
        this.cre_time = cre_time;
    }
    /**
     *   Get方法
     * @return 
     */
    public Date getUpd_time() {
        return this.upd_time;
    }
    /**
     *   Set方法
     * @return 
     */
    public void setUpd_time(Date upd_time) {
        this.upd_time = upd_time;
    }
    // </editor-fold>
}