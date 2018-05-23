/**
 * <h3>标题 : potal统一门户-comments </h3>
 * <h3>描述 : comments数据对象</h3>
 * <h3>日期 : 2018-05-03</h3>
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
package com.quick.portal.comments;

import java.util.Date;


/**
 * comments数据对象
 */
public class CommentsDO implements java.io.Serializable {
	
    private static final long serialVersionUID = 1L;
	
    // <editor-fold defaultstate="collapsed" desc="私有成员">
    /**
     *
     */
    private Integer  comment_id;
    /**
     *
     */
    private Integer  user_id;      	
    /**
     *
     */
    private String  conent;       	
    /**
     *
     */
    private String  snapshot_url; 	
    /**
     *
     */
    private Date  cmt_time;     	
    /**
     *
     */
    private Integer  cmt_state;    	
	// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="成员Get/Set">
    /**
     *   Get方法
     * @return 
     */
    public Integer getComment_id() {
        return this.comment_id;
    }
    /**
     *   Set方法
     * @return 
     */
    public void setComment_id(Integer comment_id) {
        this.comment_id = comment_id;
    }
    /**
     *   Get方法
     * @return 
     */
    public Integer getUser_id() {
        return this.user_id;
    }
    /**
     *   Set方法
     * @return 
     */
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
    /**
     *   Get方法
     * @return 
     */
    public String getConent() {
        return this.conent;
    }
    /**
     *   Set方法
     * @return 
     */
    public void setConent(String conent) {
        this.conent = conent;
    }
    /**
     *   Get方法
     * @return 
     */
    public String getSnapshot_url() {
        return this.snapshot_url;
    }
    /**
     *   Set方法
     * @return 
     */
    public void setSnapshot_url(String snapshot_url) {
        this.snapshot_url = snapshot_url;
    }
    /**
     *   Get方法
     * @return 
     */
    public Date getCmt_time() {
        return this.cmt_time;
    }
    /**
     *   Set方法
     * @return 
     */
    public void setCmt_time(Date cmt_time) {
        this.cmt_time = cmt_time;
    }
    /**
     *   Get方法
     * @return 
     */
    public Integer getCmt_state() {
        return this.cmt_state;
    }
    /**
     *   Set方法
     * @return 
     */
    public void setCmt_state(Integer cmt_state) {
        this.cmt_state = cmt_state;
    }
    // </editor-fold>
}