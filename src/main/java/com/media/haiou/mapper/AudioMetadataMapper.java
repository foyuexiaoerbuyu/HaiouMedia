package com.media.haiou.mapper;

import com.media.haiou.domain.AudioMetadata;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
* @author Administrator
* @description 针对表【audio_metadata(音频元数据表 (audio_metadata) - 针对音频的特殊属性)】的数据库操作Mapper
* @createDate 2025-04-22 11:59:58
* @Entity com.media.haiou.domain.AudioMetadata
*/
public interface AudioMetadataMapper extends BaseMapper<AudioMetadata> {
    @Select("SELECT * FROM audio_metadata WHERE media_id = #{mediaId}")
    AudioMetadata selectByMediaId(Long mediaId);

}




