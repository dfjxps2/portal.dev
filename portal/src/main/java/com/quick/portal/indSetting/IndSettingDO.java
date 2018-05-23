package com.quick.portal.indSetting;

public class IndSettingDO implements java.io.Serializable {
	
	/**
	 * role_metric_privilege数据对象
	 */
	 private static final long serialVersionUID = 1L;
	 
	 /**
	     *权限ID
	     */
	 private Integer privi_id;
	 
	 /**
	     *用户角色ID
	     */
	 private Integer role_id;
	 
	 /**
	     *指标ID
	     */
	 private Integer metric_id;

	public Integer getPrivi_id() {
		return privi_id;
	}

	public void setPrivi_id(Integer privi_id) {
		this.privi_id = privi_id;
	}

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}

	public Integer getMetric_id() {
		return metric_id;
	}

	public void setMetric_id(Integer metric_id) {
		this.metric_id = metric_id;
	}

}
