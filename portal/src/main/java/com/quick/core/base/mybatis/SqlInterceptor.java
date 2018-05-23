/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quick.core.base.mybatis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import com.quick.core.base.model.PageBounds;
import com.quick.core.base.sql.IDialect;
import com.quick.core.base.sql.MySqlDialect;

/**
 * 通用分页拦截
 * @author Administrator
 */
@Intercepts( {@Signature(type=Executor.class,method="query",args={ MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class })})
public class SqlInterceptor implements Interceptor {
    private final static Log log = LogFactory.getLog(SqlInterceptor.class);

    
    
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        try{
            MappedStatement mappedStatement=(MappedStatement)invocation.getArgs()[0];
    	    Object parameter = invocation.getArgs()[1];   
    	    BoundSql boundSql = mappedStatement.getBoundSql(parameter);   
    	    String originalSql = boundSql.getSql().trim();
            RowBounds rowBounds=(RowBounds)invocation.getArgs()[2];
     
        //分页或自动语句
        if (rowBounds != null && rowBounds != RowBounds.DEFAULT) {
            PageBounds pageBounds = (PageBounds)rowBounds;
            return pageIntercept(invocation, mappedStatement, boundSql, pageBounds,originalSql);
        }
        /*
        RowBounds rowBounds = (RowBounds) metaStatementHandler.getValue("delegate.rowBounds");
        if (rowBounds != null && rowBounds != RowBounds.DEFAULT) {
            return pageIntercept(invocation, metaStatementHandler, rowBounds);
        }
        */


        return invocation.proceed();
        }
        catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
    
  
    
   

    private Object pageIntercept(Invocation invocation, MappedStatement mappedStatement, BoundSql boundSql, PageBounds pager,String originalSql)  throws Throwable {
        IDialect dialect = new MySqlDialect();

        if(pager.isAutoCount()){
            String countSql = "select count(1) from (" + originalSql + ") ct";

            if(log.isDebugEnabled())
                log.debug(countSql);
            int count = getCount(mappedStatement, boundSql, countSql);
            //int count = 100;
            if (count == 0) {
                // 结果为空时不再继续查询。
                return Collections.EMPTY_LIST;
            }
            if(log.isDebugEnabled())
                log.debug("count :" + count);
            pager.setTotal(count);
        }

        String pageSql=dialect.sqlSelectPage(originalSql, pager.getOffset(), pager.getLimit() );
        invocation.getArgs()[2] = new RowBounds(RowBounds.NO_ROW_OFFSET, RowBounds.NO_ROW_LIMIT);    
        BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(), pageSql,boundSql.getParameterMappings(),boundSql.getParameterObject());     
         MappedStatement newMs = copyFromMappedStatement(mappedStatement,new BoundSqlSqlSource(newBoundSql));   
         invocation.getArgs()[0]= newMs;   
         return invocation.proceed();
    }
    /** 
     * 复制MappedStatement对象 
     */  
    private MappedStatement copyFromMappedStatement(MappedStatement ms,SqlSource newSqlSource) {  
      Builder builder = new Builder(ms.getConfiguration(),ms.getId(),newSqlSource,ms.getSqlCommandType());  
        
      builder.resource(ms.getResource());  
      builder.fetchSize(ms.getFetchSize());  
      builder.statementType(ms.getStatementType());  
      builder.timeout(ms.getTimeout());  
      builder.parameterMap(ms.getParameterMap());  
      builder.resultMaps(ms.getResultMaps());  
      builder.resultSetType(ms.getResultSetType());  
      builder.cache(ms.getCache());  
      builder.flushCacheRequired(ms.isFlushCacheRequired());  
      builder.useCache(ms.isUseCache());  
        
      return builder.build();  
    }  
    
   
    public class BoundSqlSqlSource implements SqlSource {    
        BoundSql boundSql;    
        public BoundSqlSqlSource(BoundSql boundSql) {    
          this.boundSql = boundSql;    
        }    
        public BoundSql getBoundSql(Object parameterObject) {    
          return boundSql;    
        }    
      }    
    
    /**
    * 查询总纪录数。
    * @param statement
    * @param boundSql
    * @param countSql
    * @return
    * @throws SQLException
    */
   private static int getCount(MappedStatement statement, BoundSql boundSql, String countSql)
                   throws Exception {

        Object param = boundSql.getParameterObject();
        BoundSql countBound = new BoundSql(statement.getConfiguration(), countSql, boundSql.getParameterMappings(), param);
        
      
        ParameterHandler parameterHandler = new DefaultParameterHandler(statement, param, countBound);
        
        Connection conn =
            statement.getConfiguration().getEnvironment().getDataSource().getConnection();

        try (PreparedStatement ps = conn.prepareStatement(countSql)) {
            parameterHandler.setParameters(ps);
            ResultSet rs = ps.executeQuery();
            int count = 0;
            if (rs.next()) {
                count = rs.getInt(1);
            }
            rs.close();
            return count;
        }finally{
        	conn.close();
        }
      
    }
   
   
    
    @Override
    public Object plugin(Object target) {
        // 当目标类是StatementHandler类型时，才包装目标类，否者直接返回目标本身,减少目标被代理的次数  
        //if (target instanceof StatementHandler) {  
            return Plugin.wrap(target, this);  
        //} else {  
        //    return target;  
        //}
    }
    @Override
    public void setProperties(Properties properties) {
    }
}


