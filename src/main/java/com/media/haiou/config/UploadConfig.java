package com.media.haiou.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UploadConfig {

    // 默认5MB
    @Value("${upload.chunk-size:5242880}")
    private int chunkSize;

    @Value("${upload.temp-dir:/tmp/upload}")
    private String tempDir;

    @Value("${upload.final-dir:/data/media}")
    private String finalDir;

    @Bean
    public UploadProperties uploadProperties() {
        return new UploadProperties(chunkSize, tempDir, finalDir);
    }

    public record UploadProperties(int chunkSize, String tempDir, String finalDir) {
    }
}