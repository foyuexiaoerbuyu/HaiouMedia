package com.media.haiou.domain.vo;

import lombok.Data;

// 相关子VO类
@Data
public class VideoSeriesVO {
    private Long id;
    private String title;
    private String originalTitle;
    private String description;
    private String coverImage;
    private Integer totalSeasons;
    private Integer totalEpisodes;
    private Integer releaseYear;
    private String country;
    private String language;
    private Integer status;
}