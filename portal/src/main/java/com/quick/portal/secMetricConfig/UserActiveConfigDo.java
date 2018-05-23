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
public class UserActiveConfigDo implements java.io.Serializable {
	
    private static final long serialVersionUID = 1L;

    private int config_id;            //设置id
    private int user_id;          //用户id
    private int is_active;          //用户id
    private String version_num;
    private int term_type_id;          //设备终端id
    private Date cre_time;      //生效时间

    public int getTerm_type_id() {
        return term_type_id;
    }

    public void setTerm_type_id(int term_type_id) {
        this.term_type_id = term_type_id;
    }

    public String getVersion_num() {
        return version_num;
    }

    public void setVersion_num(String version_num) {
        this.version_num = version_num;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }



    public void setConfig_id(int config_id) {
        this.config_id = config_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public Date getCre_time() {
        return cre_time;
    }
}