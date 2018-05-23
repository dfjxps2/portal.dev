/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quick.core.base;

import com.quick.core.base.model.DataStore;
import com.quick.core.base.model.PageBounds;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public interface ISysBaseService<T> {
    

    /**
     * 保存实体类
     * @param entity 实体类
     */
    DataStore save(T entity);
    
    
    /**
     * 删除实体类
     * @param sysid
     * @return 
     */
    DataStore delete(String sysid);
    
    /**
     * 获取一个实体
     * @param sysid
     * @return 
     */
    T selectObj(String sysid);
    
   /**
    * 通过查询条件获取一个实体
    * @param map
    * @return
    */
    T selectObj(Map<String,Object> map);
    
    
    /**
     * 获取一个Map
     * @param sysid
     * @return 
     */
    Map<String, Object> selectMap(String sysid);
    
    /**
     * 根据URL参数获取一个Map
     * @param map
     * @return
     */
    Map<String, Object> selectMap(Map<String, Object> map);

    /**
     * 获取分页列表
     * @param m
     * @param page
     * @return 
     */
    List<Map<String, Object>> select(Map<String, Object> m, PageBounds page);
    /**
     * 获取查询列表
     * @param m
     * @return 
     */
    List<Map<String, Object>> select(Map<String, Object> m);
    /**
     * 获取所有列表
     * @return 
     */
    List<Map<String, Object>> selectAll();
    /**
     * 获取SQL查询列表
     * @param mapper
     * @param m
     * @return
     */
    List<Map<String, Object>> selectMapper(String mapper, Map<String, Object> m);
    
    String getBaseTable();
    String getBaseView();
    String getBaseComment();
    String getPrimaryKey();
    String getNextNo();
    
    ISysBaseDao getDao();
    
    int count(Map<String, Object> m);

    /**
     * 判断某列内容是否已存在(重复)
     * @param key
     * @param value
     * @param id
     * @return
     */
    boolean exist(String key, Object value, Object id);
}
