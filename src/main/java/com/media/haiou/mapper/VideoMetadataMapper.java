package com.media.haiou.mapper;

import com.media.haiou.domain.VideoMetadata;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
* @author Administrator
* @description 针对表【video_metadata(视频元数据表 (video_metadata) - 针对视频的特殊属性)】的数据库操作Mapper
* @createDate 2025-04-22 11:59:58
* @Entity com.media.haiou.domain.VideoMetadata
*/
public interface VideoMetadataMapper extends BaseMapper<VideoMetadata> {
    @Select("SELECT * FROM video_metadata WHERE media_id = #{mediaId}")
    VideoMetadata selectByMediaId(Long mediaId);

    @Select("SELECT COUNT(*) FROM video_metadata WHERE series_id = #{seriesId}")
    Integer countBySeriesId(Long seriesId);

//    // 统计指定剧集的视频数量
//    @Select("SELECT COUNT(*) FROM video_metadata WHERE series_id = #{seriesId}")
//    Integer countBySeriesId(@Param("seriesId") Long seriesId);

}




