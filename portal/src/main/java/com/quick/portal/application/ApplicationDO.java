/**
 * <h3>标题 : potal统一门户-application </h3>
 * <h3>描述 : application数据对象</h3>
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
package com.quick.portal.application;

import java.util.Date;


/**
 * application数据对象
 */
public class ApplicationDO implements java.io.Serializable {
	
    private static final long serialVersionUID = 1L;
	
    // <editor-fold defaultstate="collapsed" desc="私有成员">
    /**
     *应用系统ID
     */
    private Integer  app_id;          	
    /**
     *上级应用系统ID
     */
    private Integer  super_app_id;    	
    /**
     *应用系统名称
     */
    private String  app_name;        	
    /**
     *部门ID
     */
    private Integer  dep_id;          	
    /**
     *应用系统级别
     */
    private Integer  app_level;       	
    /**
     *应用系统状态
     */
    private Integer  app_state;       	
    /**
     *应用系统URL
     */
    private String  app_url;         	
    /**
     *应用系统预览URL
     */
    private String  app_preview_url; 	
    /**
     *注册日期
     */
    private Date  reg_date;        	
    /**
     *发布日期
     */
    private Date  pub_date;        	
    /**
     *创建时间
     */
    private Date  cre_time;        	
    /**
     *更新时间
     */
    private Date  upd_time;

    private String dep_global_id;

	// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="成员Get/Set">
    /**
     * 应用系统ID  Get方法
     * @return 
     */
    public Integer getApp_id() {
        return this.app_id;
    }
    /**
     * 应用系统ID  Set方法
     * @return 
     */
    public void setApp_id(Integer app_id) {
        this.app_id = app_id;
    }
    /**
     * 上级应用系统ID  Get方法
     * @return 
     */
    public Integer getSuper_app_id() {
        return this.super_app_id;
    }
    /**
     * 上级应用系统ID  Set方法
     * @return 
     */
    public void setSuper_app_id(Integer super_app_id) {
        this.super_app_id = super_app_id;
    }
    /**
     * 应用系统名称  Get方法
     * @return 
     */
    public String getApp_name() {
        return this.app_name;
    }
    /**
     * 应用系统名称  Set方法
     * @return 
     */
    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }
    /**
     * 部门ID  Get方法
     * @return 
     */
    public Integer getDep_id() {
        return this.dep_id;
    }
    /**
     * 部门ID  Set方法
     * @return 
     */
    public void setDep_id(Integer dep_id) {
        this.dep_id = dep_id;
    }
    /**
     * 应用系统级别  Get方法
     * @return 
     */
    public Integer getApp_level() {
        return this.app_level;
    }
    /**
     * 应用系统级别  Set方法
     * @return 
     */
    public void setApp_level(Integer app_level) {
        this.app_level = app_level;
    }
    /**
     * 应用系统状态  Get方法
     * @return 
     */
    public Integer getApp_state() {
        return this.app_state;
    }
    /**
     * 应用系统状态  Set方法
     * @return 
     */
    public void setApp_state(Integer app_state) {
        this.app_state = app_state;
    }
    /**
     * 应用系统URL  Get方法
     * @return 
     */
    public String getApp_url() {
        return this.app_url;
    }
    /**
     * 应用系统URL  Set方法
     * @return 
     */
    public void setApp_url(String app_url) {
        this.app_url = app_url;
    }
    /**
     * 应用系统预览URL  Get方法
     * @return 
     */
    public String getApp_preview_url() {
        return this.app_preview_url;
    }
    /**
     * 应用系统预览URL  Set方法
     * @return 
     */
    public void setApp_preview_url(String app_preview_url) {
        this.app_preview_url = app_preview_url;
    }
    /**
     * 注册日期  Get方法
     * @return 
     */
    public Date getReg_date() {
        return this.reg_date;
    }
    /**
     * 注册日期  Set方法
     * @return 
     */
    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }
    /**
     * 发布日期  Get方法
     * @return 
     */
    public Date getPub_date() {
        return this.pub_date;
    }
    /**
     * 发布日期  Set方法
     * @return 
     */
    public void setPub_date(Date pub_date) {
        this.pub_date = pub_date;
    }
    /**
     * 创建时间  Get方法
     * @return 
     */
    public Date getCre_time() {
        return this.cre_time;
    }
    /**
     * 创建时间  Set方法
     * @return 
     */
    public void setCre_time(Date cre_time) {
        this.cre_time = cre_time;
    }
    /**
     * 更新时间  Get方法
     * @return 
     */
    public Date getUpd_time() {
        return this.upd_time;
    }
    /**
     * 更新时间  Set方法
     * @return 
     */
    public void setUpd_time(Date upd_time) {
        this.upd_time = upd_time;
    }


    public void setDep_global_id(String dep_global_id) {
        this.dep_global_id = dep_global_id;
    }
    /**
     * 应用系统预览URL  Get方法
     * @return
     */
    public String GetDep_global_id() {
        return this.dep_global_id;
    }


    // </editor-fold>
}