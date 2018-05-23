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
public interface IDialect {
    String sqlSelectPage(String sql, int offset, int limit);
    
}
