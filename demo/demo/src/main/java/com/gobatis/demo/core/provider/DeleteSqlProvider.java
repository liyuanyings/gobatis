package com.gobatis.demo.core.provider;

import org.apache.ibatis.builder.annotation.ProviderContext;
import org.apache.ibatis.jdbc.SQL;

/**
 * @program: demo
 * @description:
 * @author: WT1124
 * @create: 2018-12-11 11:23
 **/
public class DeleteSqlProvider extends SqlProviderSupport {

    public String sql(ProviderContext context) {
        Class<?> entityType = entityType(context);
        return new SQL()
                .DELETE_FROM(tableName(entityType))
                .WHERE("id = #{id}")
                .toString();
    }
}
