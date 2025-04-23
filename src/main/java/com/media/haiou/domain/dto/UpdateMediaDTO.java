package com.media.haiou.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UpdateMediaDTO {
    @NotBlank
    private String title;
    
    private String description;
    
    private String coverImage;
    
    // 其他可更新字段...
    private List<Long> categoryIds = new ArrayList<>();
    private List<Long> tagIds = new ArrayList<>();
}