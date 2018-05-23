/**
 * <h3>标题 : potal统一门户-MetricDO </h3>
 * <h3>描述 : MetricDO数据对象</h3>
 * <h3>日期 : 2018-04-13</h3>
 * <h3>版权 : Copyright (C) 北京东方金信科技有限公司</h3>
 * 
 * <p>
 * @author 你自己的姓名 cxh@seaboxdata.com
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
package com.quick.portal.security.authority.metric;

import java.util.Date;

/**
 * MetricDO数据对象
 */
public class MetricPrivilegeDO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	/*
	 * 权限ID
	 */
	private Integer priviId;
	
	/*
	 * 用户角色ID
	 */
	private Integer roleId;
	
	/**
	 * 指标ID
	 */
	private Integer metricId;
	public Integer getMetricId() {
		return metricId;
	}
	public void setMetricId(Integer metricId) {
		this.metricId = metricId;
	}
	public Integer getMetricName() {
		return metricName;
	}
	public void setMetricName(Integer metricName) {
		this.metricName = metricName;
	}
	public Integer getMetricSrcId() {
		return metricSrcId;
	}
	public void setMetricSrcId(Integer metricSrcId) {
		this.metricSrcId = metricSrcId;
	}
	public String getSrcMetricId() {
		return srcMetricId;
	}
	public void setSrcMetricId(String srcMetricId) {
		this.srcMetricId = srcMetricId;
	}
	public String getMetricUrl() {
		return metricUrl;
	}
	public void setMetricUrl(String metricUrl) {
		this.metricUrl = metricUrl;
	}
	public Integer getApprState() {
		return apprState;
	}
	public void setApprState(Integer apprState) {
		this.apprState = apprState;
	}
	public Date getPubTime() {
		return pubTime;
	}
	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}
	public Date getApprTime() {
		return apprTime;
	}
	public void setApprTime(Date apprTime) {
		this.apprTime = apprTime;
	}
	public Integer getPriviId() {
		return priviId;
	}
	public void setPriviId(Integer priviId) {
		this.priviId = priviId;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	/**
	 * 指标名称
	 */
	private Integer metricName;

	/**
	 * 指标源ID
	 */
	private Integer metricSrcId;

	/*
	 * 指标源ID
	 */

	private String srcMetricId;

	/*
	 * 指标链接
	 */
	private String metricUrl;

	/**
	 * 指标链接
	 */
	private Integer apprState;
	/**
	 * 发布时间
	 */
	private Date pubTime;
	/**
	 * 审核时间
	 */
	private Date apprTime;
}