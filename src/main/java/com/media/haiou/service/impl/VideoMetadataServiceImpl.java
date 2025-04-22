package com.media.haiou.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.media.haiou.domain.VideoMetadata;
import com.media.haiou.service.VideoMetadataService;
import com.media.haiou.mapper.VideoMetadataMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【video_metadata(视频元数据表 (video_metadata) - 针对视频的特殊属性)】的数据库操作Service实现
* @createDate 2025-04-22 11:59:58
*/
@Service
public class VideoMetadataServiceImpl extends ServiceImpl<VideoMetadataMapper, VideoMetadata>
    implements VideoMetadataService{

}




