package com.gobatis.demo.core.mapper;

import com.gobatis.demo.core.provider.SelectMethodProvider;
import org.apache.ibatis.annotations.SelectProvider;

public interface BaseMapper<T, PK> {

    @SelectProvider(type = SelectMethodProvider.class, method = "selectOneById")
    T selectOne(PK id, Class<T> clazz);

    @SelectProvider(type = SelectMethodProvider.class, method = "selectOne")
    T selectOne(T entity);
}
