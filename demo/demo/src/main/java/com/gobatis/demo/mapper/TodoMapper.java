package com.gobatis.demo.mapper;

import com.gobatis.demo.core.mapper.BaseMapper;
import com.gobatis.demo.entity.Todo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TodoMapper extends BaseMapper<Todo, Todo> {
}
