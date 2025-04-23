package com.media.haiou.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoMetadataVO {
    private Long id;
    private Long mediaId;
    private String title;          // 视频标题（从关联的MediaResource获取）
    private String resolution;     // 分辨率（如：1080p）
    private BigDecimal frameRate;  // 帧率（如：24.00）
    private String director;       // 导演
    private String actors;         // 演员列表（逗号分隔）
    private Integer releaseYear;   // 发布年份
    private Integer episode;       // 当前集数
    private Integer season;        // 当前季数
    private String subtitlePath;   // 字幕文件路径
    private Long seriesId;         // 所属剧集ID
    private String seriesTitle;    // 所属剧集标题
    private Integer episodeOrder;  // 集数排序
    private Integer duration;      // 时长（秒，从MediaResource获取）
    private String coverImage;     // 封面图（从MediaResource获取）
    private String filePath;       // 文件路径（从MediaResource获取）
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    // 可扩展字段
    private Boolean hasSubtitle;   // 是否有字幕
    private String videoCodec;     // 视频编码格式
    private String audioCodec;     // 音频编码格式
}