package com.gobatis.demo.core.provider;

import org.apache.ibatis.builder.annotation.ProviderContext;
import org.apache.ibatis.jdbc.SQL;

import java.util.stream.Stream;

/**
 * @program: demo
 * @description:
 * @author: WT1124
 * @create: 2018-12-11 11:22
 **/
public class UpdateSqlProvider extends SqlProviderSupport{

    public String updateSql(ProviderContext context) {
        Class<?> entityType = entityType(context);
        return new SQL()
                .UPDATE(tableName(entityType))
                .SET(Stream.of(entityType.getDeclaredFields()).map(field -> bindParameter(field)).toArray(String[]::new)
                ).WHERE("id = #{id}")
                .toString();
    }

    public String updateIgnoreNullSql(ProviderContext context) {
        Class<?> entityType = entityType(context);
        return new SQL()
                .UPDATE(tableName(entityType))
                .SET(Stream.of(entityType.getDeclaredFields()).map(field -> bindParameter(field)).toArray(String[]::new))
                .WHERE("id = #{id}")
                .toString();
    }

}
