package com.media.haiou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.media.haiou.domain.*;
import com.media.haiou.domain.dto.*;
import com.media.haiou.domain.enums.MediaType;
import com.media.haiou.domain.enums.UploadStatus;
import com.media.haiou.domain.vo.CategoryVO;
import com.media.haiou.domain.vo.TagVO;
import com.media.haiou.domain.vo.VideoMetadataVO;
import com.media.haiou.domain.vo.VideoSeriesVO;
import com.media.haiou.mapper.*;
import com.media.haiou.service.FileStorageService;
import com.media.haiou.service.MediaQueryService;
import com.media.haiou.service.UploadService;
import com.media.haiou.service.VideoMetadataService;
import com.media.haiou.utils.BusinessException;
import com.media.haiou.service.MediaService;
import com.media.haiou.utils.PageResult;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;

/**
* @author Administrator
* @description 针对表【video_metadata(视频元数据表 (video_metadata) - 针对视频的特殊属性)】的数据库操作Service实现
* @createDate 2025-04-22 11:59:58
*/
@Service
public class VideoMetadataServiceImpl extends ServiceImpl<VideoMetadataMapper, VideoMetadata>
    implements VideoMetadataService{






}




