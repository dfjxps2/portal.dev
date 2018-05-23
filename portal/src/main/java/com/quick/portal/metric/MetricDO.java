/**
 * <h3>标题 : potal统一门户-metric </h3>
 * <h3>描述 : metric数据对象</h3>
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
package com.quick.portal.metric;

import java.util.Date;


/**
 * metric数据对象
 */
public class MetricDO implements java.io.Serializable {
	
    private static final long serialVersionUID = 1L;
	
    // <editor-fold defaultstate="collapsed" desc="私有成员">
    /**
     *
     */
    private Integer  metric_id;     	
    /**
     *
     */
    private String  metric_name;   	
    /**
     *
     */
    private Integer  metric_src_id; 	
    /**
     *
     */
    private String  src_metric_id; 	
    /**
     *
     */
    private String  metric_url;    	
    /**
     *
     */
    private Integer  appr_state;    	
    /**
     *
     */
    private Date  pub_time;      	
    /**
     *
     */
    private Date  appr_time;     	
	// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="成员Get/Set">
    /**
     *   Get方法
     * @return 
     */
    public Integer getMetric_id() {
        return this.metric_id;
    }
    /**
     *   Set方法
     * @return 
     */
    public void setMetric_id(Integer metric_id) {
        this.metric_id = metric_id;
    }
    /**
     *   Get方法
     * @return 
     */
    public String getMetric_name() {
        return this.metric_name;
    }
    /**
     *   Set方法
     * @return 
     */
    public void setMetric_name(String metric_name) {
        this.metric_name = metric_name;
    }
    /**
     *   Get方法
     * @return 
     */
    public Integer getMetric_src_id() {
        return this.metric_src_id;
    }
    /**
     *   Set方法
     * @return 
     */
    public void setMetric_src_id(Integer metric_src_id) {
        this.metric_src_id = metric_src_id;
    }
    /**
     *   Get方法
     * @return 
     */
    public String getSrc_metric_id() {
        return this.src_metric_id;
    }
    /**
     *   Set方法
     * @return 
     */
    public void setSrc_metric_id(String src_metric_id) {
        this.src_metric_id = src_metric_id;
    }
    /**
     *   Get方法
     * @return 
     */
    public String getMetric_url() {
        return this.metric_url;
    }
    /**
     *   Set方法
     * @return 
     */
    public void setMetric_url(String metric_url) {
        this.metric_url = metric_url;
    }
    /**
     *   Get方法
     * @return 
     */
    public Integer getAppr_state() {
        return this.appr_state;
    }
    /**
     *   Set方法
     * @return 
     */
    public void setAppr_state(Integer appr_state) {
        this.appr_state = appr_state;
    }
    /**
     *   Get方法
     * @return 
     */
    public Date getPub_time() {
        return this.pub_time;
    }
    /**
     *   Set方法
     * @return 
     */
    public void setPub_time(Date pub_time) {
        this.pub_time = pub_time;
    }
    /**
     *   Get方法
     * @return 
     */
    public Date getAppr_time() {
        return this.appr_time;
    }
    /**
     *   Set方法
     * @return 
     */
    public void setAppr_time(Date appr_time) {
        this.appr_time = appr_time;
    }
    // </editor-fold>
}