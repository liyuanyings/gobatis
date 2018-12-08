package com.gobatis.demo.entity.channel;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
/**
 * @program: web
 * @description:
 * @author: WT1124
 * @create: 2018-08-23 14:41
 **/

@Table(name = "channel")
public class Channel implements Serializable {

    private static final long serialVersionUID = 720739095151919286L;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "channel_name")
    private String channelName;

    @Column(name = "description")
    private String description;

    @Column(name = "create_user_id")
    private Long createUserId;

    @Column(name = "is_deleted")
    private Integer deleted;

    @Column(name = "created_time")
    private Date createTime;

    @Column(name = "last_updated_time")
    private Date lastUpdatedTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }
}
