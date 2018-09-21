/**
 * <h3>标题 : potal统一门户-menu_privilege </h3>
 * <h3>描述 : menu_privilege数据对象</h3>
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
package com.quick.portal.sysPrivilege;

import java.util.Date;


/**
 * menu_privilege数据对象
 */
public class SysPrivilegeDO implements java.io.Serializable {
	
    private static final long serialVersionUID = 1L;
	
    // <editor-fold defaultstate="collapsed" desc="私有成员">
    /**
     *权限ID
     */
    private Integer  pri_id;   	
    /**
     *菜单ID
     */
    private Integer  menu_id;  	
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
     * 权限ID  Get方法
     * @return 
     */
    public Integer getPri_id() {
        return this.pri_id;
    }
    /**
     * 权限ID  Set方法
     * @return 
     */
    public void setPri_id(Integer pri_id) {
        this.pri_id = pri_id;
    }
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