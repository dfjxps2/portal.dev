/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quick.core.base;

import com.quick.core.base.model.PageBounds;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Administrator
 */
public interface ISysBaseDao<T> {
    
    int insert(T record);
    int update(T record);
    int delete(String sysid);
    List<Map<String, Object>> select(Map<String, Object> m, PageBounds page);
    List<Map<String, Object>> select(Map<String, Object> m);
    List<Map<String, Object>> selectAll();
    T selectObj(String sysid);
    int count(Map<String, Object> m);
}
