package com.gobatis.demo.dao;


import com.gobatis.demo.entity.channel.Channel;
import com.gobatis.demo.mapper.ChannelMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LiYuanying
 */
@Repository
public class ChannelDao {

    private final ChannelMapper channelMapper;

    @Autowired
    public ChannelDao(SqlSession sqlSession) {
        this.channelMapper = sqlSession.getMapper(ChannelMapper.class);
    }

    public Channel get(Long id) {
        return this.channelMapper.get(id);
    }

    public Object selectList(Channel channel){
        return channelMapper.select(channel);
    }

    public Long insert(Channel entity) {
        return this.channelMapper.insert(entity);
    }

    public boolean update(Channel entity) {
        return this.channelMapper.update(entity) > 0;
    }

    public boolean updateIgnoreNull(Channel entity) {
        return this.channelMapper.updateIgnoreNull(entity) > 0;
    }

    public void remove(Long id) {
        this.channelMapper.remove(id);
    }

    public void remove(List<Long> ids) {
        this.channelMapper.removeList(ids);
    }

    public Integer countByName(String name){
        return this.channelMapper.countByName(name);
    }
}
