package com.quick.portal.mesManage;

import java.util.Date;

/**
 * Created by GaoPh on 2018/5/3.
 */
public class MesManageDO implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 信息实体
     */

    /**
     * 信息ID
     */
    private  Integer msg_id;
    /**
     * 信息标题
     */
    private  String  msg_title;
    /**
     * 信息源ID
     */
    private  Integer msg_src_id;
    /**
     * 源信息ID
     */
    private  String  src_msg_id;
    /**
     * 信息类别ID
     */
    private  Integer msg_type_id;
    /**
     * 信息重要程度ID
     */
    private  Integer msg_imprt_id;
    /**
     * 保密级别ID
     */
    private  Integer msg_class_id;
    /**
     * 信息摘要
     */
    private  String  msg_digest;
    /**
     * 信息链接
     */
    private  String  msg_content;
    /**
     * 信息附件
     */
    private  String  msg_attachment;
    /**
     * 审查状态
     */
    private  Integer appr_state;
    /**
     * 发布时间
     */
    private   Date   pub_time;
    /**
     * 审核时间
     */
    private   Date   appr_time;

    /**
     * 信息源名称
     */
    private  String msg_src_name;

    /**
     * 保密级别名称
     */
    private String msg_class_name;

    /**
     * 标签ID
     */
    private  Integer tag_id;

    /**
     * 标签内容
     */
    private  String tag_text;

    /**
     * 信息重要程度名称
     */
    private  String msg_imprt_name;

    private String msgcontent;

    /**
     * 发布者ID
     * @return
     */
    private  Integer pub_user_id;

    /**
     * 审核者ID
     * @return
     */
    private  Integer appr_user_id;

    public Integer getAppr_user_id() {
        return appr_user_id;
    }

    public void setAppr_user_id(Integer appr_user_id) {
        this.appr_user_id = appr_user_id;
    }

    public Integer getPub_user_id() {
        return pub_user_id;
    }

    public void setPub_user_id(Integer pub_user_id) {
        this.pub_user_id = pub_user_id;
    }

    public String getMsgcontent() {
        return msgcontent;
    }

    public void setMsgcontent(String msgcontent) {
        this.msgcontent = msgcontent;
    }

    public Integer getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(Integer msg_id) {
        this.msg_id = msg_id;
    }

    public String getMsg_title() {
        return msg_title;
    }

    public void setMsg_title(String msg_title) {
        this.msg_title = msg_title;
    }

    public Integer getMsg_src_id() {
        return msg_src_id;
    }

    public void setMsg_src_id(Integer msg_src_id) {
        this.msg_src_id = msg_src_id;
    }

    public String getSrc_msg_id() {
        return src_msg_id;
    }

    public void setSrc_msg_id(String src_msg_id) {
        this.src_msg_id = src_msg_id;
    }

    public Integer getMsg_type_id() {
        return msg_type_id;
    }

    public void setMsg_type_id(Integer msg_type_id) {
        this.msg_type_id = msg_type_id;
    }

    public Integer getMsg_imprt_id() {
        return msg_imprt_id;
    }

    public void setMsg_imprt_id(Integer msg_imprt_id) {
        this.msg_imprt_id = msg_imprt_id;
    }

    public Integer getMsg_class_id() {
        return msg_class_id;
    }

    public void setMsg_class_id(Integer msg_class_id) {
        this.msg_class_id = msg_class_id;
    }

    public String getMsg_digest() {
        return msg_digest;
    }

    public void setMsg_digest(String msg_digest) {
        this.msg_digest = msg_digest;
    }

    public String getMsg_content() {
        return msg_content;
    }

    public void setMsg_content(String msg_content) {
        this.msg_content = msg_content;
    }

    public String getMsg_attachment() {
        return msg_attachment;
    }

    public void setMsg_attachment(String msg_attachment) {
        this.msg_attachment = msg_attachment;
    }

    public Integer getAppr_state() {
        return appr_state;
    }

    public void setAppr_state(Integer appr_state) {
        this.appr_state = appr_state;
    }

    public Date getPub_time() {
        return pub_time;
    }

    public void setPub_time(Date pub_time) {
        this.pub_time = pub_time;
    }

    public Date getAppr_time() {
        return appr_time;
    }

    public void setAppr_time(Date appr_time) {
        this.appr_time = appr_time;
    }

    public String getMsg_src_name() {
        return msg_src_name;
    }

    public void setMsg_src_name(String msg_src_name) {
        this.msg_src_name = msg_src_name;
    }

    public String getMsg_class_name() {
        return msg_class_name;
    }

    public void setMsg_class_name(String msg_class_name) {
        this.msg_class_name = msg_class_name;
    }

    public Integer getTag_id() {
        return tag_id;
    }

    public void setTag_id(Integer tag_id) {
        this.tag_id = tag_id;
    }

    public String getTag_text() {
        return tag_text;
    }

    public void setTag_text(String tag_text) {
        this.tag_text = tag_text;
    }

    public String getMsg_imprt_name() {
        return msg_imprt_name;
    }

    public void setMsg_imprt_name(String msg_imprt_name) {
        this.msg_imprt_name = msg_imprt_name;
    }



    private Integer rule_id;
    private Integer param_id;
    private  String param_name;
    private String param_value;
    private Integer rule_type_id;
    private java.sql.Date cre_time;
    private java.sql.Date upd_time;
    private String rule_type_name;
    private String ruleValue;

    public Integer getRule_id() {
        return rule_id;
    }

    public void setRule_id(Integer rule_id) {
        this.rule_id = rule_id;
    }

    public Integer getParam_id() {
        return param_id;
    }

    public void setParam_id(Integer param_id) {
        this.param_id = param_id;
    }

    public String getParam_name() {
        return param_name;
    }

    public void setParam_name(String param_name) {
        this.param_name = param_name;
    }

    public String getParam_value() {
        return param_value;
    }

    public void setParam_value(String param_value) {
        this.param_value = param_value;
    }

    public Integer getRule_type_id() {
        return rule_type_id;
    }

    public void setRule_type_id(Integer rule_type_id) {
        this.rule_type_id = rule_type_id;
    }

    public java.sql.Date getCre_time() {
        return cre_time;
    }

    public void setCre_time(java.sql.Date cre_time) {
        this.cre_time = cre_time;
    }

    public java.sql.Date getUpd_time() {
        return upd_time;
    }

    public void setUpd_time(java.sql.Date upd_time) {
        this.upd_time = upd_time;
    }

    public String getRule_type_name() {
        return rule_type_name;
    }

    public void setRule_type_name(String rule_type_name) {
        this.rule_type_name = rule_type_name;
    }

    public String getRuleValue() {
        return ruleValue;
    }

    public void setRuleValue(String ruleValue) {
        this.ruleValue = ruleValue;
    }
}
