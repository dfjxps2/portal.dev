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
import java.util.List;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

/**
 *
 * @author cong
 */
public class MybatisUtil {
    /**
    * 替换MappedStatement原有的SQL。<br>
    * @param statement
    * @param originalBound
    * @param newSql
    * @return
    */
   public static MappedStatement createBy(MappedStatement statement, BoundSql originalBound, String newSql) {
        final BoundSql newBoundSql = new BoundSql(
            statement.getConfiguration(), newSql,
            originalBound.getParameterMappings(),
            originalBound.getParameterObject());

        MappedStatement.Builder builder = new MappedStatement.Builder(
            statement.getConfiguration(), statement.getId(), new SqlSource() {
                @Override
                public BoundSql getBoundSql(Object parameterObject) {
                        return newBoundSql;
                }
            },
        statement.getSqlCommandType());

        // MyBatis这代码根本不遵循规范！不然可以用反射！
        builder.cache(statement.getCache());
        builder.fetchSize(statement.getFetchSize());
        builder.flushCacheRequired(statement.isFlushCacheRequired());
        builder.keyGenerator(statement.getKeyGenerator());
        if (statement.getKeyProperties() != null) {
            for (String keyProperty : statement.getKeyProperties()) {
                    builder.keyProperty(keyProperty);
            }
        }
        builder.resource(statement.getResource());
        builder.resultMaps(statement.getResultMaps());
        builder.resultSetType(statement.getResultSetType());
        builder.statementType(statement.getStatementType());
        builder.timeout(statement.getTimeout());
        builder.useCache(statement.isUseCache());
        builder.parameterMap(statement.getParameterMap());
        return builder.build();
    }

	/**
	 * 查询总纪录数。
	 * @param statement
	 * @param originalBound
	 * @param countSql
	 * @return
	 * @throws SQLException
	 */
	public static int getCount(MappedStatement statement, BoundSql originalBound, String countSql)
            throws SQLException {

            Object param = originalBound.getParameterObject();
            BoundSql countBound = new BoundSql(
                            statement.getConfiguration(), countSql, originalBound.getParameterMappings(), param);

            Connection conn = statement.getConfiguration().getEnvironment().getDataSource().getConnection();

            try (PreparedStatement ps = conn.prepareStatement(countSql)) {
                setParameters(ps, statement, countBound, param);
                ResultSet rs = ps.executeQuery();
                int count = 0;
                if (rs.next()) {
                    count = rs.getInt(1);
                }
                rs.close();
                return count;
            }
        }

    /**
     * @see org.apache.ibatis.scripting.defaults.DefaultParameterHandler
     * @param ps
     * @param mappedStatement
     * @param boundSql
     * @param parameterObject
     * @throws SQLException
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void setParameters(
        PreparedStatement ps, MappedStatement mappedStatement,
        BoundSql boundSql, Object parameterObject) throws SQLException {

        ErrorContext.instance().activity("setting parameters")
                .object(mappedStatement.getParameterMap().getId());
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        if (parameterMappings == null) {
            return;
        }

        Configuration configuration = mappedStatement.getConfiguration();
        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
        MetaObject metaObject = parameterObject == null ? null : configuration.newMetaObject(parameterObject);
        for (int i = 0; i < parameterMappings.size(); i++) {
            ParameterMapping parameterMapping = parameterMappings.get(i);
            if (parameterMapping.getMode() == ParameterMode.OUT) {
                continue;
            }

            Object value;
            String propertyName = parameterMapping.getProperty();
            if (boundSql.hasAdditionalParameter(propertyName)) {
                // issue #448 ask first for additional params
                value = boundSql.getAdditionalParameter(propertyName);
            } else if (parameterObject == null) {
                value = null;
            } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                value = parameterObject;
            } else {
                value = metaObject == null ? null : metaObject.getValue(propertyName);
            }
            TypeHandler typeHandler = parameterMapping.getTypeHandler();
            JdbcType jdbcType = parameterMapping.getJdbcType();
            if (value == null && jdbcType == null)
                jdbcType = configuration.getJdbcTypeForNull();
            typeHandler.setParameter(ps, i + 1, value, jdbcType);
        }
    }
    
    public static MappedStatement getStatement(Invocation invocation) {
        return (MappedStatement) invocation.getArgs()[0];
    }
}
