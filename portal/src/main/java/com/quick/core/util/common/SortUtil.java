/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.quick.core.util.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 排序帮助类
 * @author Administrator
 */
public class SortUtil {
    
    /**
     * 
     * @param <T>
     * @param list 原数据
     * @param method "getXXX" 方法名
     * @param sort  升降序类型 "desc" 或者 "asc"
     */
    public static <T> void Sort(List<T> list, final String method, final String sort){
        Collections.sort(list, new Comparator() {            
            public int compare(Object a, Object b) {
                int ret = 0;
                try{
                    Method m1 = ((T)a).getClass().getMethod(method, null);
                    Method m2 = ((T)b).getClass().getMethod(method, null);
                    if(sort != null && "desc".equals(sort))//倒序
                        ret = m2.invoke(((T)b), null).toString().compareTo(m1.invoke(((T)a), null).toString());    
                    else//正序
                        ret = m1.invoke(((T)a), null).toString().compareTo(m2.invoke(((T)b), null).toString());
                }catch(NoSuchMethodException ne){
                    System.out.println(ne);
                }catch(IllegalAccessException ie){
                    System.out.println(ie);
                }catch(InvocationTargetException it){
                    System.out.println(it);
                }
                return ret;
            }
         });
    }

}
