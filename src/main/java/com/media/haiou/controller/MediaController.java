package com.media.haiou.controller;

import com.media.haiou.domain.MediaResource;
import com.media.haiou.domain.UploadTask;
import com.media.haiou.domain.dto.CompleteUploadDTO;
import com.media.haiou.domain.dto.CreateMediaDTO;
import com.media.haiou.domain.dto.InitUploadDTO;
import com.media.haiou.domain.dto.UpdateMediaDTO;
import com.media.haiou.service.MediaService;
import com.media.haiou.service.UploadService;
import com.media.haiou.utils.Result;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/media")
@RequiredArgsConstructor
public class MediaController {

    @Autowired
    private   MediaService mediaService;
    @Autowired
    private   UploadService uploadService;

    /**
     * 初始化上传任务
     */
    @PostMapping("/upload/init")
    public Result<UploadTask> initUploadTask(@RequestBody @Valid InitUploadDTO dto) {
        return Result.success(uploadService.initUploadTask(dto));
    }

    /**
     * 上传分片
     */
    @PostMapping("/upload/chunk")
    public Result<Boolean> uploadChunk(
            @RequestParam String taskId,
            @RequestParam Integer chunkNumber,
            @RequestParam MultipartFile chunk) {
        return Result.success(uploadService.uploadChunk(taskId, chunkNumber, chunk));
    }

    /**
     * 完成上传并合并文件
     */
    @PostMapping("/upload/complete")
    public Result<MediaResource> completeUpload(@RequestBody @Valid CompleteUploadDTO dto) {
        return Result.success(uploadService.completeUpload(dto));
    }

    /**
     * 创建媒体资源信息
     */
    @PostMapping
    public Result<MediaResource> createMedia(@RequestBody @Valid CreateMediaDTO dto) {
        return Result.success(mediaService.createMedia(dto));
    }

    /**
     * 更新媒体资源信息
     */
    @PutMapping("/{id}")
    public Result<MediaResource> updateMedia(
            @PathVariable Long id,
            @RequestBody @Valid UpdateMediaDTO dto) {
        return Result.success(mediaService.updateMedia(id, dto));
    }
}