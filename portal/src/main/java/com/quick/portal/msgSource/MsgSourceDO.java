/**
 * <h3>标题 : potal统一门户-msg_source </h3>
 * <h3>描述 : msg_source数据对象</h3>
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
package com.quick.portal.msgSource;


/**
 * msg_source数据对象
 */
public class MsgSourceDO implements java.io.Serializable {
	
    private static final long serialVersionUID = 1L;
	
    // <editor-fold defaultstate="collapsed" desc="私有成员">
    /**
     *
     */
    private Integer  msg_src_id;      	
    /**
     *
     */
    private String  msg_src_name;    	
    /**
     *
     */
    private String  msg_src_url;     	
    /**
     *
     */
    private Integer  msg_src_type_id; 	
	// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="成员Get/Set">
    /**
     *   Get方法
     * @return 
     */
    public Integer getMsg_src_id() {
        return this.msg_src_id;
    }
    /**
     *   Set方法
     * @return 
     */
    public void setMsg_src_id(Integer msg_src_id) {
        this.msg_src_id = msg_src_id;
    }
    /**
     *   Get方法
     * @return 
     */
    public String getMsg_src_name() {
        return this.msg_src_name;
    }
    /**
     *   Set方法
     * @return 
     */
    public void setMsg_src_name(String msg_src_name) {
        this.msg_src_name = msg_src_name;
    }
    /**
     *   Get方法
     * @return 
     */
    public String getMsg_src_url() {
        return this.msg_src_url;
    }
    /**
     *   Set方法
     * @return 
     */
    public void setMsg_src_url(String msg_src_url) {
        this.msg_src_url = msg_src_url;
    }
    /**
     *   Get方法
     * @return 
     */
    public Integer getMsg_src_type_id() {
        return this.msg_src_type_id;
    }
    /**
     *   Set方法
     * @return 
     */
    public void setMsg_src_type_id(Integer msg_src_type_id) {
        this.msg_src_type_id = msg_src_type_id;
    }
    // </editor-fold>
}