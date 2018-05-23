/**
 * <h3>标题 : potal统一门户-sys_menu </h3>
 * <h3>描述 : sys_menu数据对象</h3>
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
package com.quick.portal.sysMenu;

import java.util.Date;


/**
 * sys_menu数据对象
 */
public class SysMenuDO implements java.io.Serializable {
	
    private static final long serialVersionUID = 1L;
	
    // <editor-fold defaultstate="collapsed" desc="私有成员">
    /**
     *菜单ID
     */
    private Integer  menu_id;       	
    /**
     *上级菜单ID
     */
    private Integer  super_menu_id; 	
    /**
     *菜单中文名称
     */
    private String  menu_cn_name;  	
    /**
     *菜单英文名称
     */
    private String  menu_name;     	
    /**
     *菜单图标URL
     */
    private String  menu_icon_url; 	
    /**
     *菜单功能URL
     */
    private String  menu_url;      	
    /**
     *菜单级别
     */
    private Integer  menu_level;    	
    /**
     *菜单激活标志
     */
    private Integer  menu_state;    	
    /**
     *应用系统ID
     */
    private Integer  app_id;   
    /**
     *菜单显示顺序
     */
    private Integer  disp_order;  
	/**
     *创建时间
     */
    private Date  cre_time;      	
    /**
     *更新时间
     */
    private Date  upd_time;      	
	// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="成员Get/Set">
    /**
     * 菜单ID  Get方法
     * @return 
     */
    public Integer getMenu_id() {
        return this.menu_id;
    }
    /**
     * 菜单ID  Set方法
     * @return 
     */
    public void setMenu_id(Integer menu_id) {
        this.menu_id = menu_id;
    }
    /**
     *菜单显示顺序  Get方法
     */
    public Integer getDisp_order() {
		return this.disp_order;
	}
    /**
     *菜单显示顺序  Set方法
     */
    public void setDisp_order(Integer disp_order) {
		this.disp_order = disp_order;
	}
    /**
     * 上级菜单ID  Get方法
     * @return 
     */
    public Integer getSuper_menu_id() {
        return this.super_menu_id;
    }
    /**
     * 上级菜单ID  Set方法
     * @return 
     */
    public void setSuper_menu_id(Integer super_menu_id) {
        this.super_menu_id = super_menu_id;
    }
    /**
     * 菜单中文名称  Get方法
     * @return 
     */
    public String getMenu_cn_name() {
        return this.menu_cn_name;
    }
    /**
     * 菜单中文名称  Set方法
     * @return 
     */
    public void setMenu_cn_name(String menu_cn_name) {
        this.menu_cn_name = menu_cn_name;
    }
    /**
     * 菜单英文名称  Get方法
     * @return 
     */
    public String getMenu_name() {
        return this.menu_name;
    }
    /**
     * 菜单英文名称  Set方法
     * @return 
     */
    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }
    /**
     * 菜单图标URL  Get方法
     * @return 
     */
    public String getMenu_icon_url() {
        return this.menu_icon_url;
    }
    /**
     * 菜单图标URL  Set方法
     * @return 
     */
    public void setMenu_icon_url(String menu_icon_url) {
        this.menu_icon_url = menu_icon_url;
    }
    /**
     * 菜单功能URL  Get方法
     * @return 
     */
    public String getMenu_url() {
        return this.menu_url;
    }
    /**
     * 菜单功能URL  Set方法
     * @return 
     */
    public void setMenu_url(String menu_url) {
        this.menu_url = menu_url;
    }
    /**
     * 菜单级别  Get方法
     * @return 
     */
    public Integer getMenu_level() {
        return this.menu_level;
    }
    /**
     * 菜单级别  Set方法
     * @return 
     */
    public void setMenu_level(Integer menu_level) {
        this.menu_level = menu_level;
    }
    /**
     * 菜单激活标志  Get方法
     * @return 
     */
    public Integer getMenu_state() {
        return this.menu_state;
    }
    /**
     * 菜单激活标志  Set方法
     * @return 
     */
    public void setMenu_state(Integer menu_state) {
        this.menu_state = menu_state;
    }
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
    // </editor-fold>
}