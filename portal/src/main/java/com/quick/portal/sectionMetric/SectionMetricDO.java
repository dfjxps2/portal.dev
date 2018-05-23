package com.quick.portal.sectionMetric;

import java.util.Date;

public class SectionMetricDO implements java.io.Serializable {
	
	 private static final long serialVersionUID = 1L;
	 
	 private Integer  sec_metric_id;
	 private Integer  section_id;
	 private Integer  metric_id;
	public Integer getSec_metric_id() {
		return sec_metric_id;
	}
	public void setSec_metric_id(Integer sec_metric_id) {
		this.sec_metric_id = sec_metric_id;
	}
	public Integer getSection_id() {
		return section_id;
	}
	public void setSection_id(Integer section_id) {
		this.section_id = section_id;
	}
	public Integer getMetric_id() {
		return metric_id;
	}
	public void setMetric_id(Integer metric_id) {
		this.metric_id = metric_id;
	}
	 
	
}
