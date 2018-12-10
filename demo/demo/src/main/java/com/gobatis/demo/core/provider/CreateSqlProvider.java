package com.gobatis.demo.core.provider;

import org.apache.ibatis.builder.annotation.ProviderContext;
import org.apache.ibatis.jdbc.SQL;

import java.lang.reflect.Field;
import java.util.stream.Stream;

/**
 * @program: demo
 * @description:
 * @author: WT1124
 * @create: 2018-12-11 11:22
 **/
public class CreateSqlProvider extends SqlProviderSupport{

    public String sql(ProviderContext context) {
        Class<?> entityType = entityType(context);
        Field[] fields = entityType.getDeclaredFields();
        return new SQL()
                .INSERT_INTO(tableName(entityType))
                .INTO_COLUMNS(columns(fields))
                .INTO_VALUES(Stream.of(fields).map(this::bindParameter).toArray(String[]::new))
                .toString();
    }
}
