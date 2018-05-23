/**
 * <h3>标题 : potal统一门户-user_department </h3>
 * <h3>描述 : user_department数据对象</h3>
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
package com.quick.portal.userDepartment;

import java.util.Date;


/**
 * user_department数据对象
 */
public class UserDepartmentDO implements java.io.Serializable {
	
    private static final long serialVersionUID = 1L;
	
    // <editor-fold defaultstate="collapsed" desc="私有成员">
    /**
     *部门ID
     */
    private Integer  dep_id;       	
    /**
     *上级部门ID
     */
    private Integer  super_dep_id; 	
    /**
     *部门名称
     */
    private String  dep_name;     	
    /**
     *部门级别
     */
    private Integer  dep_level;    	
    /**
     *部门状态
     */
    private Integer  dep_state;    	
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
     * 上级部门ID  Get方法
     * @return 
     */
    public Integer getSuper_dep_id() {
        return this.super_dep_id;
    }
    /**
     * 上级部门ID  Set方法
     * @return 
     */
    public void setSuper_dep_id(Integer super_dep_id) {
        this.super_dep_id = super_dep_id;
    }
    /**
     * 部门名称  Get方法
     * @return 
     */
    public String getDep_name() {
        return this.dep_name;
    }
    /**
     * 部门名称  Set方法
     * @return 
     */
    public void setDep_name(String dep_name) {
        this.dep_name = dep_name;
    }
    /**
     * 部门级别  Get方法
     * @return 
     */
    public Integer getDep_level() {
        return this.dep_level;
    }
    /**
     * 部门级别  Set方法
     * @return 
     */
    public void setDep_level(Integer dep_level) {
        this.dep_level = dep_level;
    }
    /**
     * 部门状态  Get方法
     * @return 
     */
    public Integer getDep_state() {
        return this.dep_state;
    }
    /**
     * 部门状态  Set方法
     * @return 
     */
    public void setDep_state(Integer dep_state) {
        this.dep_state = dep_state;
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