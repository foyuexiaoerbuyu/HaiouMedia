package com.media.haiou.controller;

import com.media.haiou.base.ResourceNotFoundException;
import com.media.haiou.domain.MediaResource;
import com.media.haiou.domain.VideoMetadata;
import com.media.haiou.domain.VideoSeries;
import com.media.haiou.domain.dto.EpisodeDTO;
import com.media.haiou.domain.dto.SeasonInfo;
import com.media.haiou.domain.dto.SeriesDetailDTO;
import com.media.haiou.service.impl.VideoMetadataServiceImpl;
import com.media.haiou.service.impl.VideoSeriesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/series")
public class SeriesController {

    @Autowired
    private VideoSeriesServiceImpl seriesRepository;
    @Autowired
    private VideoMetadataServiceImpl metadataRepository;

    // 获取剧集详情
    @GetMapping("/{seriesId}")
    public ResponseEntity<SeriesDetailDTO> getSeriesDetail(@PathVariable Long seriesId) {
        VideoSeries series = seriesRepository.findById(seriesId)
                .orElseThrow(() -> new ResourceNotFoundException("剧集不存在"));

        // 获取各季信息
        List<SeasonInfo> seasons = metadataRepository.findSeasonsBySeriesId(seriesId);

        return ResponseEntity.ok(new SeriesDetailDTO(series, seasons));
    }

    // 获取指定季的剧集列表
    @GetMapping("/{seriesId}/season/{season}")
    public ResponseEntity<List<EpisodeDTO>> getEpisodesBySeason(
            @PathVariable Long seriesId,
            @PathVariable Integer season) {

        List<VideoMetadata> episodes = metadataRepository.findBySeriesIdAndSeasonOrderByEpisodeOrder(
                seriesId, season);

        List<EpisodeDTO> dtos = episodes.stream()
                .map(ep -> new EpisodeDTO(ep.getMediaResource(), ep))
                .toList();

        return ResponseEntity.ok(dtos);
    }

    // DTO类




    public Optional<EpisodeDTO> getNextEpisode(Long seriesId, int currentSeason, int currentEpisode) {
        return metadataRepository
                .findBySeriesIdAndSeasonAndEpisodeOrderGreaterThanOrderByEpisodeOrderAsc(
                        seriesId, currentSeason, currentEpisode)
                .stream()
                .findFirst()
                .map(ep -> new EpisodeDTO(ep.getMediaResource(), ep));
    }

    public Optional<EpisodeDTO> getPrevEpisode(Long seriesId, int currentSeason, int currentEpisode) {
        return metadataRepository
                .findBySeriesIdAndSeasonAndEpisodeOrderLessThanOrderByEpisodeOrderDesc(
                        seriesId, currentSeason, currentEpisode)
                .stream()
                .findFirst()
                .map(ep -> new EpisodeDTO(ep.getMediaResource(), ep));
    }

    @PostMapping("/batch")
    public ResponseEntity<?> batchUploadEpisodes(
            @RequestParam Long seriesId,
            @RequestParam Integer season,
            @RequestParam MultipartFile[] files,
            @RequestParam String namingPattern) {  // 如"S01E01.mp4"

        VideoSeries series = seriesRepository.findById(seriesId)
                .orElseThrow(() -> new ResourceNotFoundException("剧集不存在"));

        List<MediaResource> results = new ArrayList<>();

        for (MultipartFile file : files) {
            // 解析文件名获取集数信息
            EpisodeInfo info = parseEpisodeInfo(file.getOriginalFilename(), namingPattern);

            // 上传文件
            MediaResource media = uploadService.upload(file, MediaType.VIDEO);

            // 创建集数记录
            VideoMetadata metadata = new VideoMetadata();
            metadata.setSeries(series);
            metadata.setSeason(season);
            metadata.setEpisode(info.episode());
            metadata.setEpisodeOrder(info.episode());
            metadata.setMediaResource(media);

            videoMetadataRepository.save(metadata);
            results.add(media);
        }

        return ResponseEntity.ok(results);
    }

    private record EpisodeInfo(int episode, String title) {}
}