package com.media.haiou.domain.dto;

import com.media.haiou.domain.enums.MediaType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InitUploadDTO {
    @NotBlank
    private String fileName;
    
    @NotBlank
    private String fileMd5;
    
    @NotNull
    private Long fileSize;
    
    @NotNull
    private Integer chunkSize;
    
    @NotNull
    private MediaType mediaType;
}