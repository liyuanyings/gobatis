package com.gobatis.demo.core.provider;


import org.apache.ibatis.jdbc.SQL;

public class SelectMethodProvider {

    public Object selectOne(Object id, Class clazz) {
        return new SQL(){{
            SELECT("*");
            FROM("sys_privilege");
            WHERE("id = #{id}");
        }}.toString();
    }

    public Object selectOne(Object entity) {
        return new SQL(){{
                SELECT("*");
                FROM("sys_privilege");
                WHERE("id = #{id}");
        }}.toString();
    }

}
