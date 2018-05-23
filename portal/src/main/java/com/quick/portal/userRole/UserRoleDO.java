/**
 * <h3>标题 : potal统一门户-user_role </h3>
 * <h3>描述 : user_role数据对象</h3>
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
package com.quick.portal.userRole;

import java.util.Date;


/**
 * user_role数据对象
 */
public class UserRoleDO implements java.io.Serializable {
	
    private static final long serialVersionUID = 1L;
	
    // <editor-fold defaultstate="collapsed" desc="私有成员">
    /**
     *用户角色ID
     */
    private Integer  role_id;    	
    /**
     *用户角色名称
     */
    private String  role_name;  	
    /**
     *角色状态
     */
    private Integer  role_state; 	
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
     * 用户角色ID  Get方法
     * @return 
     */
    public Integer getRole_id() {
        return this.role_id;
    }
    /**
     * 用户角色ID  Set方法
     * @return 
     */
    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }
    /**
     * 用户角色名称  Get方法
     * @return 
     */
    public String getRole_name() {
        return this.role_name;
    }
    /**
     * 用户角色名称  Set方法
     * @return 
     */
    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }
    /**
     * 角色状态  Get方法
     * @return 
     */
    public Integer getRole_state() {
        return this.role_state;
    }
    /**
     * 角色状态  Set方法
     * @return 
     */
    public void setRole_state(Integer role_state) {
        this.role_state = role_state;
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