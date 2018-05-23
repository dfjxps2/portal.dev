/**
 * <h3>标题 : potal统一门户-app_class </h3>
 * <h3>描述 : app_class数据对象</h3>
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
package com.quick.portal.appClass;

import java.util.Date;


/**
 * app_class数据对象
 */
public class AppClassDO implements java.io.Serializable {
	
    private static final long serialVersionUID = 1L;
	
    // <editor-fold defaultstate="collapsed" desc="私有成员">
    /**
     *应用系统类型ID
     */
    private Integer  app_class_id;   	
    /**
     *应用系统类型名称
     */
    private String  app_class_name; 	
    /**
     *创建时间
     */
    private Date  cre_time;       	
    /**
     *修改时间
     */
    private Date  upd_time;       	
	// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="成员Get/Set">
    /**
     * 应用系统类型ID  Get方法
     * @return 
     */
    public Integer getApp_class_id() {
        return this.app_class_id;
    }
    /**
     * 应用系统类型ID  Set方法
     * @return 
     */
    public void setApp_class_id(Integer app_class_id) {
        this.app_class_id = app_class_id;
    }
    /**
     * 应用系统类型名称  Get方法
     * @return 
     */
    public String getApp_class_name() {
        return this.app_class_name;
    }
    /**
     * 应用系统类型名称  Set方法
     * @return 
     */
    public void setApp_class_name(String app_class_name) {
        this.app_class_name = app_class_name;
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
     * 修改时间  Get方法
     * @return 
     */
    public Date getUpd_time() {
        return this.upd_time;
    }
    /**
     * 修改时间  Set方法
     * @return 
     */
    public void setUpd_time(Date upd_time) {
        this.upd_time = upd_time;
    }
    // </editor-fold>
}