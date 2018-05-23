/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.quick.core.base.model;

import com.quick.core.util.common.JsonUtil;
import com.quick.core.util.common.QCommon;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public class DataStore {
    private Integer code;
    private String msg;
    private Object value;
    private String url;
    private String title;
    private Integer pageSize;
    private Integer pageNo;
    private Integer pageTotal;
	private Object data;
    

    public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private Map<String,Object> form = new HashMap<String,Object>();
    private Map<String, List> dataSet = new HashMap<String, List>();

    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Map<String, Object> getForm() {
        return form;
    }
    public void setForm(HashMap<String, Object> form) {
        this.form = form;
    }
    public Map<String, List> getDataSet() {
        return dataSet;
    }
    public void setDataSet(HashMap<String, List> dataSet) {
        this.dataSet = dataSet;
    }
    public Integer getPageSize() {
        return pageSize;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    public Integer getPageNo() {
        return pageNo;
    }
    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }
    public Integer getPageTotal() {
        return pageTotal;
    }
    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }
    public DataStore(){
    }
    public DataStore(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    
    /**
     * 设置操作成功消息
     * @param msg
     * @return 
     */
    public DataStore setError(String msg) {
        this.code = -1;
        this.msg = msg;
        return this;
    }
    /**
     * 设置操作失败消息
     * @param msg
     * @return 
     */
    public DataStore setOk(String msg) {
        this.code = 1;
        this.msg = msg;
        return this;
    }
    /**
     * 设置操作失败消息
     * @param msg
     * @return 
     */
    public DataStore setSuceess(String msg) {
        this.code = 1;
        this.msg = msg;
        return this;
    }
    /**
     * 设置未登录消息
     * @return 
     */
    public DataStore setNoLogin() {
        this.code = -99;
        this.msg = "您的登录已过期！请重新登录。";
        return this;
    }
    public void add(String key, Object value) {
        this.form.put(key, value);
    }
    public Object get(String key) {
        if(this.form.containsKey(key))
                return this.form.get(key);
        return null;
    }
    public void addTable(String key, List value) {
        this.dataSet.put(key, value);
    }
    public List getTable(String key) {
        if(this.dataSet.containsKey(key))
            return this.dataSet.get(key);
        return null;
    }
    
    /**
     * 将JSON转成DataStore
     * @param jsonStr 
     */
    public void fromJson(String jsonStr){
        //JsonSerializer serializer = new JsonSerializer();
        //JavaScriptSerializer js = new JavaScriptSerializer();
        //Hashtable obj = js.Deserialize<Hashtable>(jsonstring);
        //String headerString = js.Serialize(obj["header"]);
        //String bodyString = js.Serialize(obj["body"]);
        //header.fromJson(headerString);
        //body.fromJson(bodyString);
    }

    public String toJson() {
        StringBuilder json = new StringBuilder(100);
        json.append("{");
        json.append("\"code\":").append(code).append(",\"msg\":\"").append(QCommon.urlEncode(msg)).append("\"");
        if (value != null){
            json.append(",\"value\":").append(JsonUtil.serialize(value));
        }
        /*
        if (form != null && form.Count > 0)
        {
            json.append(",\"form\":");
            json.Append(JsonConvert.SerializeObject(form));
        }
        if (tables != null && tables.size() > 0)
        {
            json.append(",\"tables\":{");
            int i = 0;
            foreach (KeyValuePair<string, IList> kvp in tables)
            {
                if (i > 0)
                    json.Append(",");
                json.Append("\"").Append(kvp.Key).Append("\":");
                json.Append(JsonConvert.SerializeObject(kvp.Value));
                i++;
            }
            json.append("}");
        }
        */
        json.append("}");
        return json.toString();
    }

    public boolean isSuccess() {
        return code.equals(1) ? true : false;
    }
    public boolean isError() {
        return code < 1 ? true : false;
    }
    public boolean isNoLogin() {
        return code.equals(-99) ? true : false;
    }
}
