package com.media.haiou.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.media.haiou.domain.*;
import com.media.haiou.domain.dto.CreateMediaDTO;
import com.media.haiou.domain.dto.UpdateMediaDTO;
import com.media.haiou.domain.enums.MediaType;
import com.media.haiou.mapper.*;
import com.media.haiou.service.MediaService;
import com.media.haiou.utils.BusinessException;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {
    @Resource
    private final MediaResourceMapper mediaResourceMapper;
    @Resource
    private final AudioMetadataMapper audioMetadataMapper;
    @Resource
    private final VideoMetadataMapper videoMetadataMapper;
    @Resource
    private final MediaCategoryMapper mediaCategoryMapper;
    @Resource
    private final MediaTagMapper mediaTagMapper;
    @Resource
    private final VideoSeriesMapper videoSeriesMapper;

    @Override
    @Transactional
    public MediaResource createMedia(CreateMediaDTO dto) {
        // 1. 保存基础媒体信息
        MediaResource media = new MediaResource();
        BeanUtils.copyProperties(dto, media);
        mediaResourceMapper.insert(media);

        // 2. 根据类型保存元数据
        if (dto.getMediaType() == MediaType.AUDIO) {
            saveAudioMetadata(media.getId(), dto);
        } else if (dto.getMediaType() == MediaType.VIDEO) {
            saveVideoMetadata(media.getId(), dto);
        }

        // 3. 保存分类关联
        saveMediaCategories(media.getId(), dto.getCategoryIds());

        // 4. 保存标签关联
        saveMediaTags(media.getId(), dto.getTagIds());

        return media;
    }

    private void saveAudioMetadata(Long mediaId, CreateMediaDTO dto) {
        AudioMetadata metadata = new AudioMetadata();
        metadata.setMediaId(mediaId);
        metadata.setArtist(dto.getArtist());
        metadata.setAlbum(dto.getAlbum());
        metadata.setTrackNumber(dto.getTrackNumber());
        metadata.setGenre(dto.getGenre());
        metadata.setLyrics(dto.getLyrics());
        audioMetadataMapper.insert(metadata);
    }

    private void saveVideoMetadata(Long mediaId, CreateMediaDTO dto) {
        VideoMetadata metadata = new VideoMetadata();
        metadata.setMediaId(mediaId);
        metadata.setResolution(dto.getResolution());
        metadata.setFrameRate(dto.getFrameRate());
        metadata.setDirector(dto.getDirector());
        metadata.setActors(dto.getActors());
        metadata.setReleaseYear(dto.getReleaseYear());
        metadata.setEpisode(dto.getEpisode());
        metadata.setSeason(dto.getSeason());
        metadata.setSubtitlePath(dto.getSubtitlePath());
        metadata.setSeriesId(String.valueOf(dto.getSeriesId()));
        metadata.setEpisodeOrder(String.valueOf(dto.getEpisodeOrder()));
        videoMetadataMapper.insert(metadata);

        // 如果是剧集，更新剧集总集数
        if (dto.getSeriesId() != null) {
            updateSeriesEpisodeCount(dto.getSeriesId());
        }
    }

    private void updateSeriesEpisodeCount(Long seriesId) {
        VideoSeries series = videoSeriesMapper.selectById(seriesId);
        if (series != null) {
            Integer totalEpisodes = videoMetadataMapper.countBySeriesId(seriesId);
            series.setTotalEpisodes(totalEpisodes);
            videoSeriesMapper.updateById(series);
        }
    }

    private void saveMediaCategories(Long mediaId, List<Long> categoryIds) {
        if (CollectionUtils.isEmpty(categoryIds)) {
            return;
        }

        List<MediaCategory> relations = categoryIds.stream()
                .map(categoryId -> {
                    MediaCategory relation = new MediaCategory();
                    relation.setMediaId(mediaId);
                    relation.setCategoryId(categoryId);
                    return relation;
                })
                .collect(Collectors.toList());

        mediaCategoryMapper.batchInsert(relations);
    }

    private void saveMediaTags(Long mediaId, List<Long> tagIds) {
        if (CollectionUtils.isEmpty(tagIds)) {
            return;
        }

        List<MediaTag> relations = tagIds.stream()
                .map(tagId -> {
                    MediaTag relation = new MediaTag();
                    relation.setMediaId(mediaId);
                    relation.setTagId(tagId);
                    return relation;
                })
                .collect(Collectors.toList());

        mediaTagMapper.batchInsert(relations);
    }

    @Override
    @Transactional
    public MediaResource updateMedia(Long id, UpdateMediaDTO dto) {
        // 1. 更新基础信息
        MediaResource media = mediaResourceMapper.selectById(id);
        if (media == null) {
            throw new BusinessException("媒体资源不存在");
        }

        media.setTitle(dto.getTitle());
        media.setDescription(dto.getDescription());
        media.setCoverImage(dto.getCoverImage());
        mediaResourceMapper.updateById(media);

        // 2. 更新分类关联
        mediaCategoryMapper.deleteByMediaId(id);
        saveMediaCategories(id, dto.getCategoryIds());

        // 3. 更新标签关联
        mediaTagMapper.deleteByMediaId(id);
        saveMediaTags(id, dto.getTagIds());

        return media;
    }
}