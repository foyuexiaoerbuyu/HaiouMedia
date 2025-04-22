package com.media.haiou.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 媒体资源表
 * @TableName media_resource
 */
@TableName(value ="media_resource")
@Data
public class MediaResource {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private String title;

    /**
     * 
     */
    private String description;

    /**
     * 
     */
    private String filePath;

    /**
     * 
     */
    private Long fileSize;

    /**
     * 时长(秒)
     */
    private Integer duration;

    /**
     * 媒体类型:视频/音频
     */
    private Object mediaType;

    /**
     * 文件格式(mp4,avi,mp3等)
     */
    private String format;

    /**
     * 封面图片路径
     */
    private String coverImage;

    /**
     * 视频宽度
     */
    private Integer width;

    /**
     * 视频高度
     */
    private Integer height;

    /**
     * 比特率
     */
    private Integer bitrate;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 状态:0-禁用,1-正常
     */
    private Integer status;
}