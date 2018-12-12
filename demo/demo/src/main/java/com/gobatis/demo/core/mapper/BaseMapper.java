package com.gobatis.demo.core.mapper;

import com.gobatis.demo.core.provider.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


/**
 * @author LiYuanying
 */
public interface BaseMapper<T extends Serializable, PK extends Serializable> {

    @InsertProvider(type = CreateSqlProvider.class, method = "sql")
    void insert(T entity);

    @UpdateProvider(type = UpdateSqlProvider.class, method = "updateSql")
    boolean update(T entity);

    @UpdateProvider(type = UpdateSqlProvider.class, method = "updateIgnoreNullSql")
    boolean updateIgnoreNull(T entity);

    @DeleteProvider(type = DeleteSqlProvider.class, method = "sql")
    boolean delete(PK id);

    boolean delete(PK... ids);

    boolean delete(Collection<PK> ids);

    @SelectProvider(type = FindOneSqlProvider.class, method = "sql")
    T selectOne(PK id);

    List<T> selectList(PK... ids);

    List<T> selectList(Collection<PK> ids);

    List<T> selectList(T entity);

    List<PK> selectIds(T entity);




    default Page<T> findPageByCriteria(T criteria, Pageable pageable) {
        long total = __countByCriteria(criteria);
        if (total == 0) {
            return new PageImpl<>(Collections.emptyList(), pageable, total);
        }
        List<T> content = __selectByCriteria(criteria, new RowBounds((int) pageable.getOffset(), pageable.getPageSize()));
        return new PageImpl<>(content, pageable, total);
    }

    @SelectProvider(type = CountByCriteriaSqlProvider.class, method = "sql")
    long __countByCriteria(T criteria);

    @SelectProvider(type = SelectByCriteriaSqlProvider.class, method = "sql")
    List<T> __selectByCriteria(T criteria, RowBounds rowBounds);


    /**
     * 基本接口
     */

    //    int insert(T entity);

//    int insert(Collection<T> entities);

    //    int update(T entity);

//    int update(Collection<T> entities);

//    T selectOne(PK id);

//    List<T> selectList(T entity);

//    Page<T> selectPage(T entity);

//    Set<Serializable> selectIds(T entity);

//    int delete(Serializable id);

//    int delete(T entity);

//    int delete(Collection<Serializable> ids);

    /**
     * 增强功能 withLinks();
     */
}
