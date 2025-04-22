package com.media.haiou.domain.dto;

import com.media.haiou.domain.VideoSeries;

import java.util.List;

public record SeriesDetailDTO(VideoSeries series, List<SeasonInfo> seasons) {
}