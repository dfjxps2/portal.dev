/**
 * <h3>标题 : potal统一门户-user_access_log </h3>
 * <h3>描述 : user_access_log数据对象</h3>
 * <h3>日期 : 2018-04-13</h3>
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
package com.quick.portal.secMetricConfig;

import java.util.Date;


/**
 * sec_metric_config数据对象
 */
public class SecMetricConfigDo implements java.io.Serializable {
	
    private static final long serialVersionUID = 1L;

    private int config_id;            //设置id
    private int user_id;          //用户id
    private int sec_metric_id;          //栏目指标id
    private int param_id;       //参数id
    private String param_value;       //参数值
    private Date cre_time;      //生效时间



    public void setConfig_id(int config_id) {
        this.config_id = config_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setSec_metric_id(int sec_metric_id) {
        this.sec_metric_id = sec_metric_id;
    }

    public void setParam_id(int param_id) {
        this.param_id = param_id;
    }

    public void setParam_value(String param_value) {
        this.param_value = param_value;
    }

    public void setCre_time(Date valid_time) {
        this.cre_time = valid_time;
    }

    public int getConfig_id() {

        return config_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getSec_metric_id() {
        return sec_metric_id;
    }

    public int getParam_id() {
        return param_id;
    }

    public String getParam_value() {
        return param_value;
    }

    public Date getCre_time() {
        return cre_time;
    }
}