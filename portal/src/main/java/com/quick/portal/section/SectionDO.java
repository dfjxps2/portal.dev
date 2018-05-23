/**
 * <h3>标题 : potal统一门户-section </h3>
 * <h3>描述 : section数据对象</h3>
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
package com.quick.portal.section;

import java.util.Date;


/**
 * section数据对象
 */
public class SectionDO implements java.io.Serializable {
	
    private static final long serialVersionUID = 1L;
	
    // <editor-fold defaultstate="collapsed" desc="私有成员">
    /**
     *
     */
    private Integer  section_id;   	
    /**
     *
     */
    private String  section_name; 	
    /**
     *
     */
    private Integer  section_type; 	
    /**
     *
     */
    private String  section_url;  	
    /**
     *
     */
    private Date  upd_time;     	
	// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="成员Get/Set">
    /**
     *   Get方法
     * @return 
     */
    public Integer getSection_id() {
        return this.section_id;
    }
    /**
     *   Set方法
     * @return 
     */
    public void setSection_id(Integer section_id) {
        this.section_id = section_id;
    }
    /**
     *   Get方法
     * @return 
     */
    public String getSection_name() {
        return this.section_name;
    }
    /**
     *   Set方法
     * @return 
     */
    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }
    /**
     *   Get方法
     * @return 
     */
    public Integer getSection_type() {
        return this.section_type;
    }
    /**
     *   Set方法
     * @return 
     */
    public void setSection_type(Integer section_type) {
        this.section_type = section_type;
    }
    /**
     *   Get方法
     * @return 
     */
    public String getSection_url() {
        return this.section_url;
    }
    /**
     *   Set方法
     * @return 
     */
    public void setSection_url(String section_url) {
        this.section_url = section_url;
    }
    /**
     *   Get方法
     * @return 
     */
    public Date getUpd_time() {
        return this.upd_time;
    }
    /**
     *   Set方法
     * @return 
     */
    public void setUpd_time(Date upd_time) {
        this.upd_time = upd_time;
    }
    // </editor-fold>
}