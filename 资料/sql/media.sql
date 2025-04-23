/*
 Navicat Premium Data Transfer

 Source Server         : 01本地
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : media

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 23/04/2025 11:03:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for audio_metadata
-- ----------------------------
DROP TABLE IF EXISTS `audio_metadata`;
CREATE TABLE `audio_metadata`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `media_id` bigint(0) NOT NULL,
  `artist` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '艺术家',
  `album` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '专辑',
  `track_number` int(0) NULL DEFAULT NULL COMMENT '音轨号',
  `genre` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '流派',
  `lyrics` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '歌词',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `media_id`(`media_id`) USING BTREE,
  CONSTRAINT `audio_metadata_ibfk_1` FOREIGN KEY (`media_id`) REFERENCES `media_resource` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '音频元数据表 (audio_metadata) - 针对音频的特殊属性' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `type` enum('VIDEO','AUDIO') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分类类型:视频/音频',
  `parent_id` bigint(0) NULL DEFAULT 0 COMMENT '父分类ID,0表示一级分类',
  `level` int(0) NULL DEFAULT 1 COMMENT '分类层级',
  `sort` int(0) NULL DEFAULT 0 COMMENT '排序',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '分类图标',
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for media_category
-- ----------------------------
DROP TABLE IF EXISTS `media_category`;
CREATE TABLE `media_category`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `media_id` bigint(0) NOT NULL,
  `category_id` bigint(0) NOT NULL,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_media_category`(`media_id`, `category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '媒体资源与分类关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for media_resource
-- ----------------------------
DROP TABLE IF EXISTS `media_resource`;
CREATE TABLE `media_resource`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `file_path` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `file_size` bigint(0) NULL DEFAULT NULL,
  `duration` int(0) NULL DEFAULT NULL COMMENT '时长(秒)',
  `media_type` enum('VIDEO','AUDIO') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '媒体类型:视频/音频',
  `format` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件格式(mp4,avi,mp3等)',
  `cover_image` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '封面图片路径',
  `width` int(0) NULL DEFAULT NULL COMMENT '视频宽度',
  `height` int(0) NULL DEFAULT NULL COMMENT '视频高度',
  `bitrate` int(0) NULL DEFAULT NULL COMMENT '比特率',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `status` tinyint(0) NULL DEFAULT 1 COMMENT '状态:0-禁用,1-正常',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '媒体资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for media_tag
-- ----------------------------
DROP TABLE IF EXISTS `media_tag`;
CREATE TABLE `media_tag`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `media_id` bigint(0) NOT NULL,
  `tag_id` bigint(0) NOT NULL,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_media_tag`(`media_id`, `tag_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '媒体资源与标签关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `type` enum('VIDEO','AUDIO','COMMON') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'COMMON',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '标签表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for upload_task
-- ----------------------------
DROP TABLE IF EXISTS `upload_task`;
CREATE TABLE `upload_task`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `file_md5` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `file_size` bigint(0) NOT NULL,
  `chunk_size` int(0) NOT NULL,
  `total_chunks` int(0) NOT NULL,
  `media_type` enum('VIDEO','AUDIO') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `status` enum('INIT','UPLOADING','COMPLETED','MERGING','FAILED') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'INIT',
  `uploaded_chunks` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `file_path` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_md5`(`file_md5`) USING BTREE,
  INDEX `idx_status`(`status`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '上传任务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_watch_history
-- ----------------------------
DROP TABLE IF EXISTS `user_watch_history`;
CREATE TABLE `user_watch_history`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(0) NOT NULL,
  `media_id` bigint(0) NOT NULL,
  `series_id` bigint(0) NULL DEFAULT NULL COMMENT '关联剧集ID',
  `progress` int(0) NULL DEFAULT NULL COMMENT '观看进度(秒)',
  `duration` int(0) NULL DEFAULT NULL COMMENT '视频总时长(秒)',
  `last_watch_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_media`(`user_id`, `media_id`) USING BTREE,
  INDEX `idx_user_series`(`user_id`, `series_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户观看记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for video_metadata
-- ----------------------------
DROP TABLE IF EXISTS `video_metadata`;
CREATE TABLE `video_metadata`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `media_id` bigint(0) NOT NULL,
  `resolution` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '分辨率(720p,1080p等)',
  `frame_rate` decimal(5, 2) NULL DEFAULT NULL COMMENT '帧率',
  `director` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '导演',
  `actors` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '演员',
  `release_year` int(0) NULL DEFAULT NULL COMMENT '发布年份',
  `episode` int(0) NULL DEFAULT NULL COMMENT '当前集数',
  `season` int(0) NULL DEFAULT NULL COMMENT '当前季数',
  `subtitle_path` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字幕文件路径',
  `series_id` bigint(0) NULL DEFAULT NULL COMMENT '剧集ID',
  `episode_order` int(0) NULL DEFAULT NULL COMMENT '集数顺序',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `media_id`(`media_id`) USING BTREE,
  CONSTRAINT `video_metadata_ibfk_1` FOREIGN KEY (`media_id`) REFERENCES `media_resource` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '视频元数据表 (video_metadata) - 针对视频的特殊属性' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for video_series
-- ----------------------------
DROP TABLE IF EXISTS `video_series`;
CREATE TABLE `video_series`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '剧集总标题',
  `original_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '原版标题',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '剧情简介',
  `cover_image` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '封面图路径',
  `total_seasons` int(0) NULL DEFAULT 1 COMMENT '总季数',
  `total_episodes` int(0) NULL DEFAULT NULL COMMENT '总集数',
  `release_year` int(0) NULL DEFAULT NULL COMMENT '首播年份',
  `country` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '国家/地区',
  `language` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '语言',
  `status` tinyint(0) NULL DEFAULT 1 COMMENT '状态:0-完结,1-连载中',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '新增剧集表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
