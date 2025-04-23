package com.media.haiou.domain.dto;

import com.media.haiou.domain.enums.MediaType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class MediaResourceVO {
    private Long id;
    private String title;
    private String description;
    private String filePath;
    private Long fileSize;
    private Integer duration;
    private MediaType mediaType; // 枚举：VIDEO/AUDIO
    private String format;
    private String coverImage;
    
    // 视频特有字段
    private Integer width;
    private Integer height;
    private Integer bitrate;
    
    // 音频特有字段
    private String artist;
    private String album;
    
    // 分类和标签
    private List<String> categories;
    private List<String> tags;
    
    // 时间信息
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}