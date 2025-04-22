package com.media.haiou.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 用户观看记录表
 * @TableName user_watch_history
 */
@TableName(value ="user_watch_history")
@Data
public class UserWatchHistory {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Long userId;

    /**
     * 
     */
    private Long mediaId;

    /**
     * 关联剧集ID
     */
    private Long seriesId;

    /**
     * 观看进度(秒)
     */
    private Integer progress;

    /**
     * 视频总时长(秒)
     */
    private Integer duration;

    /**
     * 
     */
    private Date lastWatchTime;
}