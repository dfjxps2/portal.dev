/**
 * <h3>标题 : potal统一门户-app_class_rela </h3>
 * <h3>描述 : app_class_rela数据对象</h3>
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
package com.quick.portal.appClassRela;


/**
 * app_class_rela数据对象
 */
public class AppClassRelaDO implements java.io.Serializable {
	
    private static final long serialVersionUID = 1L;
	
    // <editor-fold defaultstate="collapsed" desc="私有成员">
    /**
     *分类ID
     */
    private Integer  rel_id;       	
    /**
     *应用系统ID
     */
    private Integer  app_class_id; 	
    /**
     *应用系统类型ID
     */
    private Integer  app_id;       	
	// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="成员Get/Set">
    /**
     * 分类ID  Get方法
     * @return 
     */
    public Integer getRel_id() {
        return this.rel_id;
    }
    /**
     * 分类ID  Set方法
     * @return 
     */
    public void setRel_id(Integer rel_id) {
        this.rel_id = rel_id;
    }
    /**
     * 应用系统ID  Get方法
     * @return 
     */
    public Integer getApp_class_id() {
        return this.app_class_id;
    }
    /**
     * 应用系统ID  Set方法
     * @return 
     */
    public void setApp_class_id(Integer app_class_id) {
        this.app_class_id = app_class_id;
    }
    /**
     * 应用系统类型ID  Get方法
     * @return 
     */
    public Integer getApp_id() {
        return this.app_id;
    }
    /**
     * 应用系统类型ID  Set方法
     * @return 
     */
    public void setApp_id(Integer app_id) {
        this.app_id = app_id;
    }
    // </editor-fold>
}