package com.media.haiou.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 音频元数据表 (audio_metadata) - 针对音频的特殊属性
 * @TableName audio_metadata
 */
@TableName(value ="audio_metadata")
@Data
public class AudioMetadata {
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
     * 艺术家
     */
    private String artist;

    /**
     * 专辑
     */
    private String album;

    /**
     * 音轨号
     */
    private Integer trackNumber;

    /**
     * 流派
     */
    private String genre;

    /**
     * 歌词
     */
    private String lyrics;
}