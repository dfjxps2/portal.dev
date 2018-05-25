package com.quick.portal.security.authority.metric;

import java.util.ArrayList;
import java.util.List;

import com.quick.portal.web.mainframe.MainFrameBean;

public class MetricBeanVO implements java.io.Serializable{
	
	private String measure_name;
	
	public String getMeasure_name() {
		return measure_name;
	}

	public void setMeasure_name(String measure_name) {
		this.measure_name = measure_name;
	}

	public String getMeasure_id() {
		return measure_id;
	}

	public void setMeasure_id(String measure_id) {
		this.measure_id = measure_id;
	}

	private String  measure_id;
	
	

}
