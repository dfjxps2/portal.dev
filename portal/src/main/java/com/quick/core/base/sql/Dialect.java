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
public abstract class Dialect implements IDialect {
    @Override
    public String sqlSelectPage(String sql, int offset, int limit){
        return sql;
    }
}
