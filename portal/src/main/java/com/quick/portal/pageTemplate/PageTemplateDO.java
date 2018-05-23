/**
 * <h3>标题 : potal统一门户-page_template </h3>
 * <h3>描述 : page_template数据对象</h3>
 * <h3>日期 : 2018-05-03</h3>
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
package com.quick.portal.pageTemplate;


/**
 * page_template数据对象
 */
public class PageTemplateDO implements java.io.Serializable {
	
    private static final long serialVersionUID = 1L;
	
    // <editor-fold defaultstate="collapsed" desc="私有成员">
    /**
     *
     */
    private Integer  template_id;   	
    /**
     *
     */
    private String  template_name; 	
    /**
     *
     */
    private String  template_url;

    private String icon_url;
	// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="成员Get/Set">
    /**
     *   Get方法
     * @return 
     */
    public Integer getTemplate_id() {
        return this.template_id;
    }
    /**
     *   Set方法
     * @return 
     */
    public void setTemplate_id(Integer template_id) {
        this.template_id = template_id;
    }
    /**
     *   Get方法
     * @return 
     */
    public String getTemplate_name() {
        return this.template_name;
    }
    /**
     *   Set方法
     * @return 
     */
    public void setTemplate_name(String template_name) {
        this.template_name = template_name;
    }
    /**
     *   Get方法
     * @return 
     */
    public String getTemplate_url() {
        return this.template_url;
    }
    /**
     *   Set方法
     * @return 
     */
    public void setTemplate_url(String template_url) {
        this.template_url = template_url;
    }
    /**
     *   Get方法
     * @return
     */
    public String getIcon_url() {
        return this.icon_url;
    }
    /**
     *   Set方法
     * @return
     */
    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }
    // </editor-fold>
}