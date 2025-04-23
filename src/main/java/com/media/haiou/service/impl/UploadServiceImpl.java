package com.media.haiou.service.impl;

import com.media.haiou.domain.MediaResource;
import com.media.haiou.domain.UploadTask;
import com.media.haiou.domain.dto.CompleteUploadDTO;
import com.media.haiou.domain.dto.InitUploadDTO;
import com.media.haiou.domain.enums.UploadStatus;
import com.media.haiou.mapper.MediaResourceMapper;
import com.media.haiou.mapper.UploadTaskMapper;
import com.media.haiou.service.FileStorageService;
import com.media.haiou.service.UploadService;
import com.media.haiou.utils.BusinessException;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UploadServiceImpl implements UploadService {
    @Resource
    private final UploadTaskMapper uploadTaskMapper;
    @Resource
    private final MediaResourceMapper mediaResourceMapper;
    private final FileStorageService fileStorageService;

    @Value("${upload.chunk-dir}")
    private String chunkDir;

    @Value("${upload.media-dir}")
    private String mediaDir;

    @Override
    public UploadTask initUploadTask(InitUploadDTO dto) {
        // 检查是否已存在相同MD5的文件
        MediaResource existMedia = mediaResourceMapper.selectByFileMd5(dto.getFileMd5());
        if (existMedia != null) {
            throw new BusinessException("相同文件已存在");
        }

        // 创建上传任务
        UploadTask task = new UploadTask();
        task.setId(UUID.randomUUID().toString());
        task.setFileName(dto.getFileName());
        task.setFileMd5(dto.getFileMd5());
        task.setFileSize(dto.getFileSize());
        task.setChunkSize(dto.getChunkSize());
        task.setTotalChunks((int) Math.ceil((double) dto.getFileSize() / dto.getChunkSize()));
        task.setMediaType(dto.getMediaType());
        task.setStatus(UploadStatus.INIT);

        uploadTaskMapper.insert(task);
        return task;
    }

    @Override
    public boolean uploadChunk(String taskId, Integer chunkNumber, MultipartFile chunk) {
        UploadTask task = uploadTaskMapper.selectById(taskId);
        if (task == null) {
            throw new BusinessException("上传任务不存在");
        }

        if (task.getStatus() != UploadStatus.INIT && task.getStatus() != UploadStatus.UPLOADING) {
            throw new BusinessException("上传任务状态异常");
        }

        // 保存分片文件
        String chunkFilename = String.format("%s_%d", task.getFileMd5(), chunkNumber);
        Path chunkPath = Paths.get(chunkDir, chunkFilename);

        try {
            Files.createDirectories(chunkPath.getParent());
            chunk.transferTo(chunkPath);

            // 更新任务状态
            task.setStatus(UploadStatus.UPLOADING);

            // 记录已上传分片
            String uploadedChunks = task.getUploadedChunks();
            Set<Integer> chunks = uploadedChunks == null ? new HashSet<>()
                    : new HashSet<>(Arrays.stream(uploadedChunks.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toSet()));

            chunks.add(chunkNumber);
            task.setUploadedChunks(chunks.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(",")));

            uploadTaskMapper.updateById(task);
            return true;
        } catch (IOException e) {
            throw new BusinessException("分片上传失败", e);
        }
    }

    @Override
    @Transactional
    public MediaResource completeUpload(CompleteUploadDTO dto) {
        UploadTask task = uploadTaskMapper.selectById(dto.getTaskId());
        if (task == null) {
            throw new BusinessException("上传任务不存在");
        }

        if (!task.getFileMd5().equals(dto.getFileMd5())) {
            throw new BusinessException("文件MD5不匹配");
        }

        // 检查所有分片是否已上传
        Set<Integer> uploadedChunks = dto.getUploadedChunks().stream()
                .collect(Collectors.toSet());

        for (int i = 0; i < task.getTotalChunks(); i++) {
            if (!uploadedChunks.contains(i)) {
                throw new BusinessException("存在未上传的分片");
            }
        }

        // 合并文件
        task.setStatus(UploadStatus.MERGING);
        uploadTaskMapper.updateById(task);

        try {
            String fileExtension = FilenameUtils.getExtension(task.getFileName());
            String filename = task.getFileMd5() + "." + fileExtension;
            Path outputPath = Paths.get(mediaDir, filename);

            Files.createDirectories(outputPath.getParent());
            try (OutputStream out = Files.newOutputStream(outputPath, StandardOpenOption.CREATE)) {
                for (int i = 0; i < task.getTotalChunks(); i++) {
                    String chunkFilename = String.format("%s_%d", task.getFileMd5(), i);
                    Path chunkPath = Paths.get(chunkDir, chunkFilename);

                    Files.copy(chunkPath, out);
                    Files.delete(chunkPath); // 删除分片文件
                }
            }

            // 更新任务状态
            task.setStatus(UploadStatus.COMPLETED);
            task.setFilePath(outputPath.toString());
            uploadTaskMapper.updateById(task);

            // 创建媒体资源记录
            MediaResource media = new MediaResource();
            media.setTitle(FilenameUtils.removeExtension(task.getFileName()));
            media.setFileSize(task.getFileSize());
            media.setMediaType(task.getMediaType());
            media.setFormat(fileExtension);
            media.setFilePath(outputPath.toString());
            mediaResourceMapper.insert(media);

            return media;
        } catch (IOException e) {
            task.setStatus(UploadStatus.FAILED);
            uploadTaskMapper.updateById(task);
            throw new BusinessException("文件合并失败", e);
        }
    }
}