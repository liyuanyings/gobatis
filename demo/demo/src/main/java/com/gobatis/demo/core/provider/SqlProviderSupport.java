package com.gobatis.demo.core.provider;

import com.gobatis.demo.core.mapper.BaseMapper;
import com.google.common.base.CaseFormat;
import org.apache.ibatis.builder.annotation.ProviderContext;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.stream.Stream;

/**
 * @program: demo
 * @description:
 * @author: WT1124
 * @create: 2018-12-11 11:15
 **/
public class SqlProviderSupport {

    protected Class<?> entityType(ProviderContext context) {
        return Stream.of(context.getMapperType().getGenericInterfaces())
                .filter(ParameterizedType.class::isInstance).map(ParameterizedType.class::cast)
                .filter(type -> type.getRawType() == BaseMapper.class)
                .findFirst()
                .map(type -> type.getActualTypeArguments()[0])
                .filter(Class.class::isInstance).map(Class.class::cast)
                .orElseThrow(() -> new IllegalStateException("The SimpleCrudMapper does not found in " + context.getMapperType().getName() + "."));
    }

    protected String tableName(Class<?> entityType) {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, entityType.getSimpleName());
    }

    protected String[] columns(Field[] fields) {
        return Stream.of(fields).map(this::columnName).toArray(String[]::new);
    }

    protected String columnName(Field field) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field.getName());
    }

    protected String bindParameter(Field field) {
        return "#{" + field.getName() + "}";
    }

    protected Object value(Object bean, Field field) {
        try {
            field.setAccessible(true);
            return field.get(bean);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        } finally {
            field.setAccessible(false);
        }
    }
}
