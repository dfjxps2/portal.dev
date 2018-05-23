/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.quick.core.base.sql;

/**
 *
 * @author cong
 */
public class SybaseDialect extends Dialect implements IDialect {
    
    @Override
    public String sqlSelectPage(String sql, int offset, int limit){
        int startRow = offset;
        int endRow = offset + limit;
        String dt=Long.toString(System.currentTimeMillis())+ ((int)Math.random()*10);
        String execsql=sql.replaceFirst("SELECT", "SELECT sybid=identity(12),");
        execsql=execsql.replaceFirst("FROM", "INTO #temptable"+dt+" FROM");
        execsql=execsql+"    SELECT * FROM #temptable"+dt+" WHERE sybid>="+startRow+" AND sybid<="+endRow;
    
        return execsql;
    }
}
