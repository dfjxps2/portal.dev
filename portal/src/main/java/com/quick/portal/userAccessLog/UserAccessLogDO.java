/**
 * <h3>标题 : potal统一门户-user_access_log </h3>
 * <h3>描述 : user_access_log数据对象</h3>
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
package com.quick.portal.userAccessLog;

import java.util.Date;


/**
 * user_access_log数据对象
 */
public class UserAccessLogDO implements java.io.Serializable {
	
    private static final long serialVersionUID = 1L;

    private int log_id;            //日志id
    private Date log_time;        //登录时间
    private int log_type_id;     //日志类型
    private int user_id;          //用户id
    private String user_ip;       //用户ip
    private int user_op_type;    //用户操作类型
    private int menu_id;          //菜单id
    private String log_detail;   //日志详情

  // <editor-fold defaultstate="collapsed" desc="成员Get/Set">
    /**
     * 日志记录ID  Get方法
     * @return
     */
    public Integer getLog_id() {
        return this.log_id;
    }
    /**
     * 日志记录ID  Set方法
     * @return
     */
    public void setLog_id(Integer log_id) {
        this.log_id = log_id;
    }
    /**
     * 日志记录时间  Get方法
     * @return
     */
    public Date getLog_time() {
        return this.log_time;
    }
    /**
     * 日志记录时间  Set方法
     * @return
     */
    public void setLog_time(Date log_time) {
        this.log_time = log_time;
    }
    /**
     * 日志类型代码  Get方法
     * @return
     */
    public Integer getLog_type_id() {
        return this.log_type_id;
    }
    /**
     * 日志类型代码  Set方法
     * @return
     */
    public void setLog_type_id(Integer log_type_id) {
        this.log_type_id = log_type_id;
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
     * 用户IP地址  Get方法
     * @return
     */
    public String getUser_ip() {
        return this.user_ip;
    }
    /**
     * 用户IP地址  Set方法
     * @return
     */
    public void setUser_ip(String user_ip) {
        this.user_ip = user_ip;
    }
    /**
     * 操作类型  Get方法
     * @return
     */
    public Integer getUser_op_type() {
        return this.user_op_type;
    }
    /**
     * 操作类型  Set方法
     * @return
     */
    public void setUser_op_type(Integer user_op_type) {
        this.user_op_type = user_op_type;
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
     * 日志详情  Get方法
     * @return
     */
    public String getLog_detail() {
        return this.log_detail;
    }
    /**
     * 日志详情  Set方法
     * @return
     */
    public void setLog_detail(String log_detail) {
        this.log_detail = log_detail;
    }
    // </editor-fold>

}