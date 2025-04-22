package com.media.haiou.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 上传任务表
 * @TableName upload_task
 */
@TableName(value ="upload_task")
@Data
public class UploadTask {
    /**
     * 
     */
    @TableId
    private String id;

    /**
     * 
     */
    private String fileName;

    /**
     * 
     */
    private String fileMd5;

    /**
     * 
     */
    private Long fileSize;

    /**
     * 
     */
    private Integer chunkSize;

    /**
     * 
     */
    private Integer totalChunks;

    /**
     * 
     */
    private Object mediaType;

    /**
     * 
     */
    private Object status;

    /**
     * 
     */
    private String uploadedChunks;

    /**
     * 
     */
    private String filePath;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;
}