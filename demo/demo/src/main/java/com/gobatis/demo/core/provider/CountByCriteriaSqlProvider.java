package com.gobatis.demo.core.provider;

import org.apache.ibatis.builder.annotation.ProviderContext;
import org.apache.ibatis.jdbc.SQL;

import java.lang.reflect.Field;
import java.util.stream.Stream;

/**
 * @program: demo
 * @description:
 * @author: WT1124
 * @create: 2018-12-11 11:25
 **/
public class CountByCriteriaSqlProvider extends SqlProviderSupport {
    private static final Field[] EMPTY_FIELDS = new Field[0];

    public String sql(Object criteria, ProviderContext context) {
        Class<?> entityType = entityType(context);
        return new SQL()
                .SELECT("count(*)")
                .FROM(tableName(entityType))
                .WHERE(Stream.of(criteria == null ? EMPTY_FIELDS : criteria.getClass().getDeclaredFields())
                        .filter(field -> value(criteria, field) != null)
                        .map(field -> columnName(field) + " = " + bindParameter(field)).toArray(String[]::new))
                .toString();
    }

}
