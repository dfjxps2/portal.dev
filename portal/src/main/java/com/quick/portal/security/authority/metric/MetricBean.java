package com.quick.portal.security.authority.metric;

import java.util.ArrayList;
import java.util.List;

import com.quick.portal.web.mainframe.MainFrameBean;

public class MetricBean implements java.io.Serializable{
	private static final long serialVersionUID = 2L;
	
	private String category_name;
	
	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}

	public List<MetricBeanVO> getMeasures() {
		return measures;
	}

	public void setMeasures(List<MetricBeanVO> measures) {
		this.measures = measures;
	}

	private String  category_id;
	
	private List<MetricBeanVO> measures ;
	
	public MetricBean() {
		super();
		this.measures = new ArrayList<MetricBeanVO>();
	}
	

}
