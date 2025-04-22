package com.media.haiou.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.media.haiou.domain.Category;
import com.media.haiou.service.CategoryService;
import com.media.haiou.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【category(分类表)】的数据库操作Service实现
* @createDate 2025-04-22 11:59:58
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

}




