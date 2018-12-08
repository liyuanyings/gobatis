package com.gobatis.demo.service.impl;

import com.gobatis.demo.dao.ChannelDao;
import com.gobatis.demo.entity.channel.Channel;
import com.gobatis.demo.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author LiYuanying
 */
@Service
public class ChannelServiceImpl implements ChannelService {

    private final ChannelDao channelDao;


    @Autowired
    public ChannelServiceImpl(ChannelDao channelDao) {
        this.channelDao = channelDao;
    }


    @Override
    public Channel get(Long id) {
        return this.channelDao.get(id);
    }

    @Override
    public Long insert(Channel channel) {
        return this.channelDao.insert(channel);
    }

    @Override
    public void remove(Long id) {
        this.channelDao.remove(id);
    }

    @Override
    public void list() {
        channelDao.selectList(new Channel());
    }

}
