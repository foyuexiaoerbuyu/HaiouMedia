package com.media.haiou.mapper;

import com.media.haiou.domain.MediaResource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
* @author Administrator
* @description 针对表【media_resource(媒体资源表)】的数据库操作Mapper
* @createDate 2025-04-22 11:59:58
* @Entity com.media.haiou.domain.MediaResource
*/
public interface MediaResourceMapper extends BaseMapper<MediaResource> {
    @Select("SELECT * FROM media_resource WHERE file_md5 = #{fileMd5}")
    MediaResource selectByFileMd5(String fileMd5);
}




