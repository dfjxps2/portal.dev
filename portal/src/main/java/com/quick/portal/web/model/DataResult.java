package com.quick.portal.web.model;

/**
 * Created by cc on 2018/6/27.
 */
public class DataResult implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private static final String versionNo = "1.0";

    // <editor-fold defaultstate="collapsed" desc="属性">
    private Integer code;
    private String msg;
    private String url;
    private String version;
    private Object data;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    // </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="方法">

    public DataResult(){
        this.version = versionNo;
    }
    public DataResult(Object data){
        this();
        this.data = data;
        setOk("OK");
    }
    public DataResult(Integer code, String msg) {
        this();
        this.code = code;
        this.msg = msg;
    }

    /**
     * 设置操作成功消息
     * @param msg
     * @return
     */
    public DataResult setError(String msg) {
        this.code = -1;
        this.msg = msg;
        return this;
    }
    /**
     * 设置操作失败消息
     * @param msg
     * @return
     */
    public DataResult setOk(String msg) {
        this.code = 1;
        this.msg = msg;
        return this;
    }

    /**
     *
     * @return
     */
    public boolean isOk() {
        return code.equals(1) ? true : false;
    }
    public boolean isError() {
        return code < 1 ? true : false;
    }
    // </editor-fold>
}
