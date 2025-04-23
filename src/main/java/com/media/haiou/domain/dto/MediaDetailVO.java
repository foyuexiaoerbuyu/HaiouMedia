package com.media.haiou.domain.dto;

import com.media.haiou.domain.AudioMetadata;
import com.media.haiou.domain.VideoMetadata;
import com.media.haiou.domain.enums.MediaType;
import com.media.haiou.domain.vo.CategoryVO;
import com.media.haiou.domain.vo.TagVO;
import com.media.haiou.domain.vo.VideoSeriesVO;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class MediaDetailVO {
    private Long id;
    private String title;
    private String description;
    private String filePath;
    private Long fileSize;
    private Integer duration;
    private MediaType mediaType; // 枚举：VIDEO/AUDIO
    private String format;
    private String coverImage;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    // 视频特有属性
    private String resolution;
    private BigDecimal frameRate;
    private String director;
    private String actors;
    private Integer releaseYear;
    private Integer episode;
    private Integer season;
    private String subtitlePath;
    private Long seriesId;
    private Integer episodeOrder;

    // 音频特有属性
    private String artist;
    private String album;
    private Integer trackNumber;
    private String genre;
    private String lyrics;

    // 关联信息
    private VideoSeriesVO series; // 剧集信息
    private List<CategoryVO> categories; // 分类列表
    private List<TagVO> tags; // 标签列表

    // 其他统计信息
    private Integer viewCount;
    private Integer likeCount;

    private AudioMetadata audioMetadata;
    private VideoMetadata videoMetadata;

}
