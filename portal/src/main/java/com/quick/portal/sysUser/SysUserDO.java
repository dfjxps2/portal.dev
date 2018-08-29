/**
 * <h3>标题 : potal统一门户-sys_user </h3>
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
package com.quick.portal.sysUser;

import com.quick.core.base.model.PageBounds;

import java.util.Date;


/**
 * sys_user数据对象
 */
public class SysUserDO  implements java.io.Serializable {
	
    private static final long serialVersionUID = 1L;
	
    // <editor-fold defaultstate="collapsed" desc="私有成员">
    /**
     *用户ID
     */
    private Integer  user_id;        	
    /**
     *用户名
     */
    private String  user_name;      	
    /**
     *用户口令
     */
    private String  user_password;  	
    /**
     *真实姓名
     */
    private String  user_real_name; 	
    /**
     *激活状态
     */
    private Integer  user_state;     	
    /**
     *用户岗位ID
     */
    private Integer  job_id;         	
    /**
     *部门ID
     */
    private Integer  dep_id;         	
    /**
     *用户地址
     */
    private String  user_addr;      	
    /**
     *用户电话
     */
    private String  user_tel;       	
    /**
     *创建时间
     */
    private Date  cre_time;       	
    /**
     *upd_time
     */
    private Date  upd_time;

    /**
     * 角色名
     */
    private  String role_name;
    /**
     * 部门名称
     */
    private  String dep_name;

    /**
     * 用户-部门关系id
     * @return
     */
    private Integer rela_id;

    /**
     * 用户编辑原始用户角色值
     */
    private  Integer role_oid;

    private  Integer rel_id;


    private String user_global_id;
  
  	private Integer role_type_id;

    public Integer getRela_id() {
        return rela_id;
    }

    public void setRela_id(Integer rela_id) {
        this.rela_id = rela_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getDep_name() {
        return dep_name;
    }

    public void setDep_name(String dep_name) {
        this.dep_name = dep_name;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    private String job_name;

    private Integer role_id;

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public Integer getRole_oid() {
        return role_oid;
    }

    public Integer getRel_id() {
        return rel_id;
    }

    public void setRel_id(Integer rel_id) {
        this.rel_id = rel_id;
    }

    public void setRole_oid(Integer role_oid) {
        this.role_oid = role_oid;
    }

    private  String roles;

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    /**
     * 角色创建时间
     */
    private Date ur_cre_time;

    public Date getUr_cre_time() {
        return ur_cre_time;
    }

    public void setUr_cre_time(Date ur_cre_time) {
        this.ur_cre_time = ur_cre_time;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="成员Get/Set">
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
     * 用户名  Get方法
     * @return 
     */
    public String getUser_name() {
        return this.user_name;
    }
    /**
     * 用户名  Set方法
     * @return 
     */
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    /**
     * 用户口令  Get方法
     * @return 
     */
    public String getUser_password() {
        return this.user_password;
    }
    /**
     * 用户口令  Set方法
     * @return 
     */
    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }
    /**
     * 真实姓名  Get方法
     * @return 
     */
    public String getUser_real_name() {
        return this.user_real_name;
    }
    /**
     * 真实姓名  Set方法
     * @return 
     */
    public void setUser_real_name(String user_real_name) {
        this.user_real_name = user_real_name;
    }
    /**
     * 激活状态  Get方法
     * @return 
     */
    public Integer getUser_state() {
        return this.user_state;
    }
    /**
     * 激活状态  Set方法
     * @return 
     */
    public void setUser_state(Integer user_state) {
        this.user_state = user_state;
    }
    /**
     * 用户岗位ID  Get方法
     * @return 
     */
    public Integer getJob_id() {
        return this.job_id;
    }
    /**
     * 用户岗位ID  Set方法
     * @return 
     */
    public void setJob_id(Integer job_id) {
        this.job_id = job_id;
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
     * 用户地址  Get方法
     * @return 
     */
    public String getUser_addr() {
        return this.user_addr;
    }
    /**
     * 用户地址  Set方法
     * @return 
     */
    public void setUser_addr(String user_addr) {
        this.user_addr = user_addr;
    }
    /**
     * 用户电话  Get方法
     * @return 
     */
    public String getUser_tel() {
        return this.user_tel;
    }
    /**
     * 用户电话  Set方法
     * @return 
     */
    public void setUser_tel(String user_tel) {
        this.user_tel = user_tel;
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
     * upd_time  Get方法
     * @return 
     */
    public Date getUpd_time() {
        return this.upd_time;
    }
    /**
     * upd_time  Set方法
     * @return 
     */
    public void setUpd_time(Date upd_time) {
        this.upd_time = upd_time;
    }
    // </editor-fold>

	/**
	 * @return the user_global_id
	 */
	public String getUser_global_id() {
		return user_global_id;
	}

	/**
	 * @param user_global_id the user_global_id to set
	 */
	public void setUser_global_id(String user_global_id) {
		this.user_global_id = user_global_id;
	}

	public Integer getRole_type_id() {
		return role_type_id;
	}

	public void setRole_type_id(Integer role_type_id) {
		this.role_type_id = role_type_id;
	}

}