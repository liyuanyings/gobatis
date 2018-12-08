package com.gobatis.demo.service;


import com.gobatis.demo.entity.channel.Channel;

public interface ChannelService {

    Channel get(Long id);

    Long insert(Channel channel);

    void remove(Long id);

    void list();
}
