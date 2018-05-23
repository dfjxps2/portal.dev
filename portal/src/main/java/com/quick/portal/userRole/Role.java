/**
 * <h3>标题 : Quick通用系统框架 </h3>
 * <h3>描述 : 实体类</h3>
 * <h3>日期 : 2017-04-10</h3>
 * <h3>版权 : Copyright (C) 海口鑫网计算机网络有限公司</h3>
 * 
 * <p>
 * @author wtj wtj@xinwing.com.cn
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


public class Role  implements java.io.Serializable {
	
    private static final long serialVersionUID = 1L;
	
    private String  IDS_ROLEID;   	//主键
    private String  IDS_ROLENAME; 	//角色名称
    private String  IDS_ROLECODE; //角色编码
    private String  IDS_ROLEREMARK;//描述
    private Date  IDS_ROLEDATE;      	//创建时间

    //add by SongChaoqun 20180414 为了避免当前系统旧认证出现问题，保留上面旧的变量。将新增角色变量追加到当前BEAN
    private String role_id;
    private String role_name;
    private String role_state;
    private String cre_time;
    private String upd_time;

    /**
     * 主键  get方法
     * @return 
     */
    public String getIDS_ROLEID() {
        return this.IDS_ROLEID;
    }

    /**
     * 主键  set方法
     * @return
     */
    public void setIDS_ROLEID(String IDS_ROLEID) {
        this.IDS_ROLEID = IDS_ROLEID;
    }
    /**
     * 角色名称 get方法
     * @return 
     */
    public String getIDS_ROLENAME() {
        return this.IDS_ROLENAME;
    }
    /**
     * 角色名称 set方法
     * @return
     */
    public void setIDS_ROLENAME(String IDS_ROLENAME) {
        this.IDS_ROLENAME = IDS_ROLENAME;
    }

    /**
     * 角色编码  get方法
     * @return
     */
    public String getIDS_ROLECODE() {        return IDS_ROLECODE;    }
    /**
     * 角色编码  set方法
     * @return
     */
    public void setIDS_ROLECODE(String IDS_ROLECODE) {        this.IDS_ROLECODE = IDS_ROLECODE;    }
    /**
     * 描述 get方法
     * @return
     */
    public String getIDS_ROLEREMARK() {
        return IDS_ROLEREMARK;
    }
    /**
     * 描述 set方法
     * @return
     */
    public void setIDS_ROLEREMARK(String IDS_ROLEREMARK) {
        this.IDS_ROLEREMARK = IDS_ROLEREMARK;
    }
    /**
     * 创建时间 get方法
     * @return
     */
    public Date getIDS_ROLEDATE() {
        return IDS_ROLEDATE;
    }
    /**
     * 创建时间 set方法
     * @return
     */
    public void setIDS_ROLEDATE(Date IDS_ROLEDATE) {
        this.IDS_ROLEDATE = IDS_ROLEDATE;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getRole_state() {
        return role_state;
    }

    public void setRole_state(String role_state) {
        this.role_state = role_state;
    }

    public String getCre_time() {
        return cre_time;
    }

    public void setCre_time(String cre_time) {
        this.cre_time = cre_time;
    }

    public String getUpd_time() {
        return upd_time;
    }

    public void setUpd_time(String upd_time) {
        this.upd_time = upd_time;
    }
}