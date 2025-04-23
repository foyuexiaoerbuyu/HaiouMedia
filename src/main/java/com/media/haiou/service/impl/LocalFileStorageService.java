package com.media.haiou.service.impl;

import com.media.haiou.service.FileStorageService;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class LocalFileStorageService implements FileStorageService {
    @Override
    public String storeFile(MultipartFile file, String path) {
        // 实现文件存储逻辑
        return null;
    }
    
    @Override
    public Resource loadFileAsResource(String path) {
        // 实现文件加载逻辑
        return null;
    }
    
    @Override
    public void deleteFile(String path) {
        // 实现文件删除逻辑
    }
}