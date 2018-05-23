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
public class MySqlDialect extends Dialect implements IDialect {
    
    @Override
    public String sqlSelectPage(String sql, int offset, int limit){
        int startRow = offset;
        int endRow = offset + limit;
        StringBuilder sb = new StringBuilder(sql.length() + 100);
        //sb.append("SELECT * FROM (");
        sb.append(sql);
        //mysql limit的定义为 limit startrow  pagenum
        //sb.append(" ) X  LIMIT "+startRow+","+limit);
        sb.append("  LIMIT "+startRow+","+limit);
        return sb.toString();
    }
}
