package com.media.haiou.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.media.haiou.domain.Tag;
import com.media.haiou.service.TagService;
import com.media.haiou.mapper.TagMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【tag(标签表)】的数据库操作Service实现
* @createDate 2025-04-22 11:59:58
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService{

}




