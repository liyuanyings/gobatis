package com.gobatis.demo.mapper;

import com.gobatis.demo.core.mapper.BaseMapper;
import com.gobatis.demo.entity.channel.Channel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChannelMapper extends BaseMapper<Channel, Long> {

    Channel get(Long id);

    long insert(Channel entity);

    int update(Channel entity);

    int updateIgnoreNull(Channel entity);

    int remove(Long id);

    int removeList(List<Long> ids);

    int count();

    int countByName(String name);

    List<Channel> list();
}
