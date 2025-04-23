package com.media.haiou.domain.enums;

public enum UploadStatus {
    INIT,       // 初始化
    UPLOADING,  // 上传中
    COMPLETED,  // 已完成
    MERGING,    // 合并中
    FAILED      // 失败
}