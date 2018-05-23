package com.quick.portal.metricConfig;

import java.util.Date;

public class MetricConfigDO implements java.io.Serializable {
	
	 private static final long serialVersionUID = 1L;
	 
	 private Integer  config_id;
	 private Integer  user_id;
	 private Integer  sec_metric_id;
	 private Integer  param_id;
	 private String  param_value;
	 private Date  valid_time;
	 
	public Integer getConfig_id() {
		return config_id;
	}
	public void setConfig_id(Integer config_id) {
		this.config_id = config_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getSec_metric_id() {
		return sec_metric_id;
	}
	public void setSec_metric_id(Integer sec_metric_id) {
		this.sec_metric_id = sec_metric_id;
	}
	public Integer getParam_id() {
		return param_id;
	}
	public void setParam_id(Integer param_id) {
		this.param_id = param_id;
	}
	public String getParam_value() {
		return param_value;
	}
	public void setParam_value(String param_value) {
		this.param_value = param_value;
	}
	public Date getValid_time() {
		return valid_time;
	}
	public void setValid_time(Date valid_time) {
		this.valid_time = valid_time;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	 
	 

}
