package com.media.haiou.domain.dto;

import com.media.haiou.domain.MediaResource;
import com.media.haiou.domain.VideoMetadata;

public record EpisodeDTO(MediaResource media, VideoMetadata metadata) {
}
