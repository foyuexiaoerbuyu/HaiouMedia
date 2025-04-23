package com.media.haiou.domain.vo;

import com.media.haiou.domain.enums.MediaType;
import lombok.Data;

@Data
public class CategoryVO {
    private Long id;
    private String name;
    private MediaType type;
    private String icon;
}
