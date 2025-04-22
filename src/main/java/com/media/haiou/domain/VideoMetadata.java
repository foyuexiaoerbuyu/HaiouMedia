package com.media.haiou.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 视频元数据表 (video_metadata) - 针对视频的特殊属性
 *
 * @TableName video_metadata
 */
@TableName(value = "video_metadata")
@Data
public class VideoMetadata {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     *
     */
    private Long mediaId;

    /**
     * 分辨率(720p,1080p等)
     */
    private String resolution;

    /**
     * 帧率
     */
    private BigDecimal frameRate;

    /**
     * 导演
     */
    private String director;

    /**
     * 演员
     */
    private String actors;

    /**
     * 发布年份
     */
    private Integer releaseYear;

    /**
     * 集数(电视剧用)
     */
    private Integer episode;

    /**
     * 季数(电视剧用)
     */
    private Integer season;

    /**
     * 字幕文件路径
     */
    private String subtitlePath;

    /**
     * '剧集ID'
     */
    private String seriesId;

    /**
     * '集数顺序'
     */
    private String episodeOrder;

}