package com.gobatis.demo.controller;

import com.gobatis.demo.entity.channel.Channel;
import com.gobatis.demo.service.ChannelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author LiChunqing
 */
@Controller
@RequestMapping("/channel")
public class ChannelController {

    private  final Logger logger = LoggerFactory.getLogger(ChannelController.class);

    private final ChannelService channelService;

    @Autowired
    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }


    @ResponseBody
    @PostMapping(value = "/insert", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Channel insert(String name, String description) {

        Channel channel = new Channel();
        channel.setChannelName(name);
        channel.setDescription(description);
        long id = channelService.insert(channel);
        return channel;
    }

    @ResponseBody
    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Channel get(@PathVariable("id") Long id) {
        Channel channel = channelService.get(id);
        return channel;
    }

    @ResponseBody
    @DeleteMapping(value = "/remove/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void remove(@PathVariable("id") Long id) {
        channelService.remove(id);
    }

    @ResponseBody
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void list() {
        channelService.list();
    }
}
