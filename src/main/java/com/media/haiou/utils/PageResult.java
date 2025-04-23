package com.media.haiou.utils;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageResult<T> {
  private List<T> list;
  private Long total;
  private Integer pageSize;
  private Integer pageNum;

    public PageResult(List<T> list, Long total, Integer pageSize, Integer pageNum) {
        this.list = list;
        this.total = total;
        this.pageSize = pageSize;
        this.pageNum = pageNum;
    }

    public static <T> PageResult<T> empty() {
    return new PageResult<>(new ArrayList<>(), 0L, 0, 0);
  }
}