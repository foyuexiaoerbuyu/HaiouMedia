package com.media.haiou.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 新增剧集表
 * @TableName video_series
 */
@TableName(value ="video_series")
@Data
public class VideoSeries {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 剧集总标题
     */
    private String title;

    /**
     * 原版标题
     */
    private String originalTitle;

    /**
     * 剧情简介
     */
    private String description;

    /**
     * 封面图路径
     */
    private String coverImage;

    /**
     * 总季数
     */
    private Integer totalSeasons;

    /**
     * 总集数
     */
    private Integer totalEpisodes;

    /**
     * 首播年份
     */
    private Integer releaseYear;

    /**
     * 国家/地区
     */
    private String country;

    /**
     * 语言
     */
    private String language;

    /**
     * 状态:0-完结,1-连载中
     */
    private Integer status;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;


}