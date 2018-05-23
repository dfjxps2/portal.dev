/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.quick.core.base;

import com.quick.core.util.common.QCommon;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * Sql封装类
 * @author cong
 */
public class SqlText extends SqlSessionDaoSupport {
    private String tableName;
    private String whereStr = "";
    private String selectStr = "*";
    private String orderStr;
    
    public SqlText(){
    }
    
    public SqlText(String tableName){
        this.tableName = tableName;
    }
    
    //<editor-fold defaultstate="collapsed" desc="From">
    public SqlText from(String tableName){
        this.tableName = tableName;
        return this;
    }
    
    public SqlText from(Class cls){
        this.tableName = cls.getName();
        return this;
    }
    //</editor-fold>
    
    //<editor-fold desc="Select">
    /**
     * Select 子句
     * @param select 字段或者其他合法的Select表达式
     * @return 
     */
    public SqlText select(String select){
        if (!QCommon.isNullOrEmpty(select))
            selectStr = select;
        return this;
    }

    /**
     * Select 子句
     * @param columns 需要选择的列名
     * @return 
     */
    public SqlText select(String... columns) {
        selectStr = buildSelect(columns);
        return this;
    }
    
    /**
     * 根据列名数组构建需要选择的列名
     * @param columns 列名数组
     * @return 
     */
    private String buildSelect(String... columns){
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (String s : columns){
            if (!isFirst)
                sb.append(", ");
            sb.append(s);
            if(isFirst)
                isFirst = false;
        }
        return sb.toString();
    }
    // </editor-fold>
    
    //<editor-fold desc="Where">
    /**
     * Where子句
     * @param where
     * @return 
     */
    public SqlText Where(String where){
        whereStr += where;
        return this;
    }
    // </editor-fold>
    
    //<editor-fold desc="OrderBy">
    
    public SqlText OrderBy(String orderBy) {
        orderStr = orderBy;
        return this;
    }
    // </editor-fold>
    
    public List toList(){
        String sql = sqlSelect();
        return getSqlSession().selectList(sql, logger);
    }
    
    //<editor-fold desc="SqlFunction">
    public String sqlSelect(){
        String sql = "select "+selectStr+" from " + tableName;
        if (!QCommon.isNullOrEmpty(whereStr))
            sql += " where 1=1 " + whereStr;
        if (!QCommon.isNullOrEmpty(orderStr))
            sql += " order by " + orderStr;
        return sql;
    }
    public String sqlSelectCount() {
        String sql = "select count(*) from " + tableName;
        if (!QCommon.isNullOrEmpty(whereStr))
            sql += " where 1=1 " + whereStr;
        return sql;
    }
    // </editor-fold>
}
