/**
 * <h3>标题 : potal统一门户-user_role_rela </h3>
 * <h3>描述 : user_role_rela数据对象</h3>
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
package com.quick.portal.userRoleRela;

import java.util.Date;


/**
 * user_role_rela数据对象
 */
public class UserRoleRelaDO implements java.io.Serializable {
	
    private static final long serialVersionUID = 1L;
	
    // <editor-fold defaultstate="collapsed" desc="私有成员">
    /**
     *关系ID
     */
    private Integer  rel_id;   	
    /**
     *用户ID
     */
    private Integer  user_id;  	
    /**
     *用户角色ID
     */
    private Integer  role_id;  	
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
     * 关系ID  Get方法
     * @return 
     */
    public Integer getRel_id() {
        return this.rel_id;
    }
    /**
     * 关系ID  Set方法
     * @return 
     */
    public void setRel_id(Integer rel_id) {
        this.rel_id = rel_id;
    }
    /**
     * 用户ID  Get方法
     * @return 
     */
    public Integer getUser_id() {
        return this.user_id;
    }
    /**
     * 用户ID  Set方法
     * @return 
     */
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
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