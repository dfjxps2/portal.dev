/**
 * <h3>标题 : potal统一门户-user_job </h3>
 * <h3>描述 : user_job数据对象</h3>
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
package com.quick.portal.userJob;

import java.util.Date;


/**
 * user_job数据对象
 */
public class UserJobDO implements java.io.Serializable {
	
    private static final long serialVersionUID = 1L;
	
    // <editor-fold defaultstate="collapsed" desc="私有成员">
    /**
     *用户岗位ID
     */
    private Integer  job_id;    	
    /**
     *用户岗位名称
     */
    private String  job_name;  	
    /**
     *用户岗位级别
     */
    private Integer  job_level; 	
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
     * 用户岗位名称  Get方法
     * @return 
     */
    public String getJob_name() {
        return this.job_name;
    }
    /**
     * 用户岗位名称  Set方法
     * @return 
     */
    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }
    /**
     * 用户岗位级别  Get方法
     * @return 
     */
    public Integer getJob_level() {
        return this.job_level;
    }
    /**
     * 用户岗位级别  Set方法
     * @return 
     */
    public void setJob_level(Integer job_level) {
        this.job_level = job_level;
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