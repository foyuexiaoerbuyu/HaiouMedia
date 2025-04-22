package com.media.haiou.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 媒体资源与分类关联表
 * @TableName media_category
 */
@TableName(value ="media_category")
@Data
public class MediaCategory {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Long mediaId;

    /**
     * 
     */
    private Long categoryId;

    /**
     * 
     */
    private Date createTime;
}