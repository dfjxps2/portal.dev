/**
 * <h3>标题 : potal统一门户-app_page </h3>
 * <h3>描述 : app_page数据对象</h3>
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
package com.quick.portal.appPage;


/**
 * app_page数据对象
 */
public class AppPageDO implements java.io.Serializable {
	
    private static final long serialVersionUID = 1L;
	
    // <editor-fold defaultstate="collapsed" desc="私有成员">
    /**
     *
     */
    private Integer  app_page_id; 	
    /**
     *
     */
    private Integer  page_id;     	
    /**
     *
     */
    private Integer  app_id;      	
	// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="成员Get/Set">
    /**
     *   Get方法
     * @return 
     */
    public Integer getApp_page_id() {
        return this.app_page_id;
    }
    /**
     *   Set方法
     * @return 
     */
    public void setApp_page_id(Integer app_page_id) {
        this.app_page_id = app_page_id;
    }
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
    public Integer getApp_id() {
        return this.app_id;
    }
    /**
     *   Set方法
     * @return 
     */
    public void setApp_id(Integer app_id) {
        this.app_id = app_id;
    }
    // </editor-fold>
}