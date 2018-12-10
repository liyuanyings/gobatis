package com.gobatis.demo.core.mapper;

import com.gobatis.demo.core.provider.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public interface BaseMapper<T extends Serializable, Criteria> {

    @InsertProvider(type = CreateSqlProvider.class, method = "sql")
    @Options(useGeneratedKeys = true)
    void create(T entity);

    @UpdateProvider(type = UpdateSqlProvider.class, method = "sql")
    boolean update(T entity);

    @DeleteProvider(type = DeleteSqlProvider.class, method = "sql")
    boolean delete(Integer id);

    @SelectProvider(type = FindOneSqlProvider.class, method = "sql")
    T selectOne(Integer id);

    default Page<T> findPageByCriteria(Criteria criteria, Pageable pageable) {
        long total = __countByCriteria(criteria);
        if (total == 0) {
            return new PageImpl<>(Collections.emptyList(), pageable, total);
        }
        List<T> content = __selectByCriteria(criteria, new RowBounds((int) pageable.getOffset(), pageable.getPageSize()));
        return new PageImpl<>(content, pageable, total);
    }

    @SelectProvider(type = CountByCriteriaSqlProvider.class, method = "sql")
    long __countByCriteria(Criteria criteria);

    @SelectProvider(type = SelectByCriteriaSqlProvider.class, method = "sql")
    List<T> __selectByCriteria(Criteria criteria, RowBounds rowBounds);
}
