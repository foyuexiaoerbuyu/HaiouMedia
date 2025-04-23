package com.media.haiou.domain.dto;

import com.media.haiou.domain.enums.MediaType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class CreateMediaDTO {
    @NotBlank
    private String title;
    
    private String description;
    
    @NotNull
    private MediaType mediaType;
    
    private String format;
    
    private Integer duration;
    
    private Long fileSize;
    
    private String coverImage;
    
    // 视频特有属性
    private Integer width;
    private Integer height;
    private Integer bitrate;
    
    // 音频特有属性
    private String artist;
    private String album;
    private Integer trackNumber;
    private String genre;
    private String lyrics;
    
    // 视频元数据
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
    
    // 分类和标签
    private List<Long> categoryIds = new ArrayList<>();
    private List<Long> tagIds = new ArrayList<>();
}
