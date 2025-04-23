package com.media.haiou.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CompleteUploadDTO {
    @NotBlank
    private String taskId;
    
    @NotBlank
    private String fileMd5;
    
    @NotNull
    private List<Integer> uploadedChunks;
}