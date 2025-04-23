package com.media.haiou.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    String storeFile(MultipartFile file, String path);
    Resource loadFileAsResource(String path);
    void deleteFile(String path);
}