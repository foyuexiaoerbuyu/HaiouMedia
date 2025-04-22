package com.media.haiou.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.media.haiou.domain.MediaCategory;
import com.media.haiou.service.MediaCategoryService;
import com.media.haiou.mapper.MediaCategoryMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【media_category(媒体资源与分类关联表)】的数据库操作Service实现
* @createDate 2025-04-22 11:59:58
*/
@Service
public class MediaCategoryServiceImpl extends ServiceImpl<MediaCategoryMapper, MediaCategory>
    implements MediaCategoryService{

}




