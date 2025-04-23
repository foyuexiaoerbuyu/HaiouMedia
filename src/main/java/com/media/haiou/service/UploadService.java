package com.media.haiou.service;

import com.media.haiou.domain.MediaResource;
import com.media.haiou.domain.UploadTask;
import com.media.haiou.domain.dto.CompleteUploadDTO;
import com.media.haiou.domain.dto.InitUploadDTO;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    UploadTask initUploadTask(InitUploadDTO dto);

    boolean uploadChunk(String taskId, Integer chunkNumber, MultipartFile chunk);

    @Transactional
    MediaResource completeUpload(CompleteUploadDTO dto);
}
