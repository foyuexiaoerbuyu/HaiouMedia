package com.media.haiou.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.media.haiou.domain.Category;
import com.media.haiou.domain.MediaCategory;
import com.media.haiou.domain.MediaResource;
import com.media.haiou.domain.Tag;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【media_category(媒体资源与分类关联表)】的数据库操作Mapper
 * @createDate 2025-04-22 11:59:58
 * @Entity com.media.haiou.domain.MediaCategory
 */
@Mapper
public interface MediaCategoryMapper extends BaseMapper<MediaCategory> {
    @Select("SELECT * FROM media_resource WHERE file_md5 = #{fileMd5}")
    MediaResource selectByFileMd5(String fileMd5);

    @Select("SELECT c.* FROM category c " +
            "JOIN media_category mc ON c.id = mc.category_id " +
            "WHERE mc.media_id = #{mediaId}")
    List<Category> selectCategoriesByMediaId(Long mediaId);

    @Select("SELECT t.* FROM tag t " +
            "JOIN media_tag mt ON t.id = mt.tag_id " +
            "WHERE mt.media_id = #{mediaId}")
    List<Tag> selectTagsByMediaId(Long mediaId);

    @Select("SELECT media_id FROM media_category WHERE category_id = #{categoryId}")
    List<Long> selectMediaIdsByCategoryId(Long categoryId);

    @Select("<script>" +
            "SELECT DISTINCT media_id FROM media_tag WHERE tag_id IN " +
            "<foreach collection='tagIds' item='tagId' open='(' separator=',' close=')'>" +
            "#{tagId}" +
            "</foreach>" +
            "</script>")
    List<Long> selectMediaIdsByTagIds(@Param("tagIds") List<Long> tagIds);

    // 根据媒体ID删除分类关联
    @Delete("DELETE FROM media_category WHERE media_id = #{mediaId}")
    int deleteByMediaId(@Param("mediaId") Long mediaId);


    // 批量插入媒体-分类关系
    int batchInsert(@Param("list") List<MediaCategory> relations);
}




