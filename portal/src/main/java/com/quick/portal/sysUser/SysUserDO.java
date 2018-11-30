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
import com.quick.portal.userDepartment.UserDepartmentDO;
import com.quick.portal.userRole.UserRoleDO;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * sys_user数据对象
 */
public class SysUserDO  implements java.io.Serializable {
	
    private static final long serialVersionUID = 1L;

    public SysUserDO(){}

    public SysUserDO(SysUserDO u){
        setUser_id(u.getUser_id());
        setUser_name(u.getUser_name());
        setUser_real_name(u.getUser_real_name());
        setJob_id(u.getJob_id());
        setUser_state(u.getUser_state());
        setUser_addr(u.getUser_addr());
        setUser_tel(u.getUser_tel());
        setUser_global_id(u.getUser_global_id());
        setCre_time(u.getCre_time());
        setUpd_time(u.getUpd_time());
        setRoleList(u.getRoleList());
        setDepList(u.getDepList());
        setDep_global_id(u.getDep_global_id());
        setSup_dep_global_id(u.getSup_dep_global_id());


    }
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

    private String user_global_id;
  
    private String[] role_ids;
    private String[] dep_ids;

    private  String roles;
    private String deps;

    private String job_name;
    private int job_level;

    private List<UserRoleDO> roleList;
    private List<UserDepartmentDO> depList;


    private String dep_global_id ;
    private String sup_dep_global_id;

    public List<UserRoleDO> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<UserRoleDO> roleList) {
        this.roleList = roleList;
    }

    public List<UserDepartmentDO> getDepList() {
        return depList;
    }

    public void setDepList(List<UserDepartmentDO> depList) {
        this.depList = depList;
    }

    private int error_no;

    public int getError_no() {
        return error_no;
    }

    public void setError_no(int error_no) {
        this.error_no = error_no;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }


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

    public String[] getRole_ids() {
        return role_ids;
    }

    public void setRole_ids(String[] role_ids) {
        this.role_ids = role_ids;
    }

    public String[] getDep_ids() {
        return dep_ids;
    }

    public void setDep_ids(String[] dep_ids) {
        this.dep_ids = dep_ids;
    }

    public String getDeps() {
        return deps;
    }

    public void setDeps(String deps) {
        this.deps = deps;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    public int getJob_level() {
        return job_level;
    }

    public void setJob_level(int job_level) {
        this.job_level = job_level;
    }

    public String getDep_global_id() {
        return dep_global_id;
    }

    public void setDep_global_id(String dep_global_id) {
        this.dep_global_id = dep_global_id;
    }

    public String getSup_dep_global_id() {
        return sup_dep_global_id;
    }

    public void setSup_dep_global_id(String sup_dep_global_id) {
        this.sup_dep_global_id = sup_dep_global_id;
    }
}