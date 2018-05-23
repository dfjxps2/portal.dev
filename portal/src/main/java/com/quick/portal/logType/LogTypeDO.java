/**
 * <h3>标题 : potal统一门户-log_type </h3>
 * <h3>描述 : log_type数据对象</h3>
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
package com.quick.portal.logType;

import java.util.Date;


/**
 * log_type数据对象
 */
public class LogTypeDO implements java.io.Serializable {
	
    private static final long serialVersionUID = 1L;
	
    // <editor-fold defaultstate="collapsed" desc="私有成员">
    /**
     *日志类型代码
     */
    private Integer  log_type_id;   	
    /**
     *日志类型名称
     */
    private String  log_type_name; 	
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
     * 日志类型名称  Get方法
     * @return 
     */
    public String getLog_type_name() {
        return this.log_type_name;
    }
    /**
     * 日志类型名称  Set方法
     * @return 
     */
    public void setLog_type_name(String log_type_name) {
        this.log_type_name = log_type_name;
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