package com.media.haiou.controller;

import com.media.haiou.domain.dto.MediaDetailVO;
import com.media.haiou.domain.dto.MediaResourceVO;
import com.media.haiou.domain.enums.MediaType;
import com.media.haiou.domain.vo.VideoMetadataVO;
import com.media.haiou.service.MediaQueryService;
import com.media.haiou.utils.PageResult;
import com.media.haiou.utils.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/media")
@RequiredArgsConstructor
public class MediaQueryController {

    @Autowired
    private MediaQueryService mediaQueryService;

    /**
     * 分页查询媒体资源
     */
    @GetMapping
    public Result<PageResult<MediaResourceVO>> queryMedia(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) MediaType mediaType,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) List<Long> tagIds,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        return Result.success(mediaQueryService.queryMedia(title, mediaType, categoryId, tagIds, page, size));
    }

    /**
     * 获取媒体详情
     */
    @GetMapping("/{id}")
    public Result<MediaDetailVO> getMediaDetail(@PathVariable Long id) {
        return Result.success(mediaQueryService.getMediaDetail(id));
    }

    /**
     * 查询热门媒体
     */
    @GetMapping("/hot")
    public Result<List<MediaResourceVO>> getHotMedia(
            @RequestParam(required = false) MediaType mediaType,
            @RequestParam(defaultValue = "10") Integer limit) {
        return Result.success(mediaQueryService.getHotMedia(mediaType, limit));
    }

    /**
     * 根据分类查询媒体
     */
    @GetMapping("/category/{categoryId}")
    public Result<PageResult<MediaResourceVO>> getMediaByCategory(
            @PathVariable Long categoryId,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        return Result.success(mediaQueryService.getMediaByCategory(categoryId, page, size));
    }

    /**
     * 查询剧集列表
     */
    @GetMapping("/series/{seriesId}")
    public Result<List<VideoMetadataVO>> getSeriesEpisodes(@PathVariable Long seriesId) {
        return Result.success(mediaQueryService.getSeriesEpisodes(seriesId));
    }
}