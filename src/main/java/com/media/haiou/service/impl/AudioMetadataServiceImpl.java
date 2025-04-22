package com.media.haiou.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.media.haiou.domain.AudioMetadata;
import com.media.haiou.service.AudioMetadataService;
import com.media.haiou.mapper.AudioMetadataMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【audio_metadata(音频元数据表 (audio_metadata) - 针对音频的特殊属性)】的数据库操作Service实现
* @createDate 2025-04-22 11:59:58
*/
@Service
public class AudioMetadataServiceImpl extends ServiceImpl<AudioMetadataMapper, AudioMetadata>
    implements AudioMetadataService{

}




