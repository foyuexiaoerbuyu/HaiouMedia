package com.media.haiou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.media.haiou.domain.*;
import com.media.haiou.domain.dto.MediaDetailVO;
import com.media.haiou.domain.dto.MediaResourceVO;
import com.media.haiou.domain.enums.MediaType;
import com.media.haiou.domain.vo.CategoryVO;
import com.media.haiou.domain.vo.TagVO;
import com.media.haiou.domain.vo.VideoMetadataVO;
import com.media.haiou.domain.vo.VideoSeriesVO;
import com.media.haiou.mapper.*;
import com.media.haiou.service.MediaQueryService;
import com.media.haiou.utils.BusinessException;
import com.media.haiou.utils.PageResult;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MediaQueryServiceImpl implements MediaQueryService {

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
    public PageResult<MediaResourceVO> queryMedia(String title, MediaType mediaType, Long categoryId, List<Long> tagIds, Integer page, Integer size) {
        Page<MediaResource> pageInfo = new Page<>(page, size);
        LambdaQueryWrapper<MediaResource> queryWrapper = new LambdaQueryWrapper<>();

        if (StringUtils.isNotBlank(title)) {
            queryWrapper.like(MediaResource::getTitle, title);
        }

        if (mediaType != null) {
            queryWrapper.eq(MediaResource::getMediaType, mediaType);
        }

        if (categoryId != null) {
            List<Long> mediaIds = mediaCategoryMapper.selectMediaIdsByCategoryId(categoryId);
            if (CollectionUtils.isEmpty(mediaIds)) {
                return PageResult.empty();
            }
            queryWrapper.in(MediaResource::getId, mediaIds);
        }

        if (!CollectionUtils.isEmpty(tagIds)) {
            List<Long> mediaIds = mediaTagMapper.selectMediaIdsByTagIds(tagIds);
            if (CollectionUtils.isEmpty(mediaIds)) {
                return PageResult.empty();
            }
            queryWrapper.in(MediaResource::getId, mediaIds);
        }

        queryWrapper.eq(MediaResource::getStatus, 1) // 只查询正常状态的
                .orderByDesc(MediaResource::getCreateTime);

        mediaResourceMapper.selectPage(pageInfo, queryWrapper);

        List<MediaResourceVO> vos = pageInfo.getRecords().stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());

        return new PageResult<>(vos, pageInfo.getTotal(), Math.toIntExact(pageInfo.getSize()), Math.toIntExact(pageInfo.getCurrent()));
    }

    @Override
    public MediaDetailVO getMediaDetail(Long id) {
        MediaResource media = mediaResourceMapper.selectById(id);
        if (media == null) {
            throw new BusinessException("媒体资源不存在");
        }

        MediaDetailVO vo = new MediaDetailVO();
        BeanUtils.copyProperties(media, vo);

        // 查询分类
        List<Category> categories = mediaCategoryMapper.selectCategoriesByMediaId(id);
        List<CategoryVO> categoryVOS = new ArrayList<>();
        for (Category category : categories) {
            CategoryVO categoryVO = new CategoryVO();
            BeanUtils.copyProperties(category, categoryVO);
            categoryVOS.add(categoryVO);
        }
        vo.setCategories(categoryVOS);

        // 查询标签
        List<Tag> tags = mediaTagMapper.selectTagsByMediaId(id);
        List<TagVO> tagss = new ArrayList<>();
        for (Tag tag : tags) {
            TagVO tagVO = new TagVO();
            BeanUtils.copyProperties(tag, tagVO);
            tagss.add(tagVO);

        }
        vo.setTags(tagss);

        // 查询元数据
        if (media.getMediaType() == MediaType.AUDIO) {
            AudioMetadata metadata = audioMetadataMapper.selectByMediaId(id);
            vo.setAudioMetadata(metadata);
        } else if (media.getMediaType() == MediaType.VIDEO) {
            VideoMetadata metadata = videoMetadataMapper.selectByMediaId(id);
            vo.setVideoMetadata(metadata);

            if (metadata.getSeriesId() != null) {
                VideoSeries series = videoSeriesMapper.selectById(metadata.getSeriesId());
                VideoSeriesVO videoSeries = new VideoSeriesVO();
                BeanUtils.copyProperties(series, videoSeries);
                vo.setSeries(videoSeries);
            }
        }

        return vo;
    }

    @Override
    public List<MediaResourceVO> getHotMedia(MediaType mediaType, Integer limit) {
        LambdaQueryWrapper<MediaResource> queryWrapper = new LambdaQueryWrapper<>();

        if (mediaType != null) {
            queryWrapper.eq(MediaResource::getMediaType, mediaType);
        }

        queryWrapper.eq(MediaResource::getStatus, 1)
                .orderByDesc(MediaResource::getCreateTime)
                .last("LIMIT " + limit);

        return mediaResourceMapper.selectList(queryWrapper).stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
    }

    @Override
    public PageResult<MediaResourceVO> getMediaByCategory(Long categoryId, Integer page, Integer size) {
        List<Long> mediaIds = mediaCategoryMapper.selectMediaIdsByCategoryId(categoryId);
        if (CollectionUtils.isEmpty(mediaIds)) {
            return PageResult.empty();
        }

        Page<MediaResource> pageInfo = new Page<>(page, size);
        LambdaQueryWrapper<MediaResource> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(MediaResource::getId, mediaIds)
                .eq(MediaResource::getStatus, 1)
                .orderByDesc(MediaResource::getCreateTime);

        mediaResourceMapper.selectPage(pageInfo, queryWrapper);

        List<MediaResourceVO> vos = pageInfo.getRecords().stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());

        return new PageResult<>(vos, pageInfo.getTotal(), Math.toIntExact(pageInfo.getSize()), Math.toIntExact(pageInfo.getCurrent()));
    }

    @Override
    public List<VideoMetadataVO> getSeriesEpisodes(Long seriesId) {
        LambdaQueryWrapper<VideoMetadata> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(VideoMetadata::getSeriesId, seriesId)
                .orderByAsc(VideoMetadata::getSeason)
                .orderByAsc(VideoMetadata::getEpisodeOrder);

        List<VideoMetadata> episodes = videoMetadataMapper.selectList(queryWrapper);

        return episodes.stream()
                .map(episode -> {
                    VideoMetadataVO vo = new VideoMetadataVO();
                    BeanUtils.copyProperties(episode, vo);

                    MediaResource media = mediaResourceMapper.selectById(episode.getMediaId());
                    if (media != null) {
                        vo.setTitle(media.getTitle());
                        vo.setDuration(media.getDuration());
                        vo.setCoverImage(media.getCoverImage());
                    }

                    return vo;
                })
                .collect(Collectors.toList());
    }

    private MediaResourceVO convertToVO(MediaResource media) {
        MediaResourceVO vo = new MediaResourceVO();
        BeanUtils.copyProperties(media, vo);
        return vo;
    }
}