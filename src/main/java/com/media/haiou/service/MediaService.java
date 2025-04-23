package com.media.haiou.service;

import com.media.haiou.domain.MediaResource;
import com.media.haiou.domain.dto.CreateMediaDTO;
import com.media.haiou.domain.dto.UpdateMediaDTO;
import org.springframework.transaction.annotation.Transactional;

public interface MediaService {
    @Transactional
    MediaResource createMedia(CreateMediaDTO dto);

    @Transactional
    MediaResource updateMedia(Long id, UpdateMediaDTO dto);
}
