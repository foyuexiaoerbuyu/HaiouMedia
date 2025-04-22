package com.media.haiou.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 媒体资源与标签关联表
 * @TableName media_tag
 */
@TableName(value ="media_tag")
@Data
public class MediaTag {
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
    private Long tagId;

    /**
     * 
     */
    private Date createTime;
}