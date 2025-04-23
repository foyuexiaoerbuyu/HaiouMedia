package com.media.haiou.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.media.haiou.domain.MediaResource;
import com.media.haiou.domain.VideoMetadata;
import com.media.haiou.domain.vo.VideoMetadataVO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class VideoMetadataConverter {
    public static VideoMetadataVO toVO(VideoMetadata metadata, MediaResource media) {
        if (metadata == null || media == null) {
            return null;
        }

        VideoMetadataVO vo = new VideoMetadataVO();
        // 复制元数据基础字段
        BeanUtils.copyProperties(metadata, vo);
        
        // 设置从MediaResource获取的字段
        vo.setTitle(media.getTitle());
        vo.setDuration(media.getDuration());
        vo.setCoverImage(media.getCoverImage());
        vo.setFilePath(media.getFilePath());
        
        // 计算衍生字段
        vo.setHasSubtitle(StringUtils.isNotBlank(metadata.getSubtitlePath()));
        
        return vo;
    }

    public static List<VideoMetadataVO> toVOList(List<VideoMetadata> metadataList,
                                                 Map<Long, MediaResource> mediaMap) {
        return metadataList.stream()
                .map(metadata -> {
                    MediaResource media = mediaMap.get(metadata.getMediaId());
                    return toVO(metadata, media);
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}