package com.media.haiou.service;

import com.media.haiou.domain.dto.MediaDetailVO;
import com.media.haiou.domain.dto.MediaResourceVO;
import com.media.haiou.domain.enums.MediaType;
import com.media.haiou.domain.vo.VideoMetadataVO;
import com.media.haiou.utils.PageResult;

import java.util.List;

public interface MediaQueryService {
    PageResult<MediaResourceVO> queryMedia(
            String title, MediaType mediaType, Long categoryId, List<Long> tagIds,
            Integer page, Integer size);

    MediaDetailVO getMediaDetail(Long id);

    List<MediaResourceVO> getHotMedia(MediaType mediaType, Integer limit);

    PageResult<MediaResourceVO> getMediaByCategory(Long categoryId, Integer page, Integer size);

    List<VideoMetadataVO> getSeriesEpisodes(Long seriesId);
}
