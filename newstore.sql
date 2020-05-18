/*
 Navicat Premium Data Transfer

 Source Server         : 本地连接
 Source Server Type    : MySQL
 Source Server Version : 50639
 Source Host           : localhost:3306
 Source Schema         : newstore

 Target Server Type    : MySQL
 Target Server Version : 50639
 File Encoding         : 65001

 Date: 06/05/2020 17:12:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_newbee_mall_admin_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_newbee_mall_admin_user`;
CREATE TABLE `tb_newbee_mall_admin_user`  (
  `admin_user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `login_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员登陆名称',
  `login_password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员登陆密码',
  `nick_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员显示昵称',
  `locked` tinyint(4) NULL DEFAULT 0 COMMENT '是否锁定 0未锁定 1已锁定无法登陆',
  PRIMARY KEY (`admin_user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_newbee_mall_admin_user
-- ----------------------------
INSERT INTO `tb_newbee_mall_admin_user` VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', '晓杰', 0);

-- ----------------------------
-- Table structure for tb_newbee_mall_carousel
-- ----------------------------
DROP TABLE IF EXISTS `tb_newbee_mall_carousel`;
CREATE TABLE `tb_newbee_mall_carousel`  (
  `carousel_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '首页轮播图主键id',
  `carousel_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '轮播图',
  `redirect_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '\'##\'' COMMENT '点击后的跳转地址(默认不跳转)',
  `carousel_rank` int(11) NOT NULL DEFAULT 0 COMMENT '排序值(字段越大越靠前)',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '删除标识字段(0-未删除 1-已删除)',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_user` int(11) NOT NULL DEFAULT 0 COMMENT '创建者id',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `update_user` int(11) NOT NULL DEFAULT 0 COMMENT '修改者id',
  PRIMARY KEY (`carousel_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_newbee_mall_carousel
-- ----------------------------
INSERT INTO `tb_newbee_mall_carousel` VALUES (2, 'http://localhost:8089/upload/20200506_12271924.jpg', '#', 1, 1, '2019-11-29 00:00:00', 0, '2020-05-06 17:09:53', 0);
INSERT INTO `tb_newbee_mall_carousel` VALUES (5, 'https://newbee-mall.oss-cn-beijing.aliyuncs.com/images/banner2.png', '', 0, 0, '2019-11-29 00:00:00', 0, '2019-11-29 00:00:00', 0);
INSERT INTO `tb_newbee_mall_carousel` VALUES (8, 'http://localhost:8089/upload/20200506_12274811.jpg', '##', 4, 1, '2020-05-06 12:27:53', 0, '2020-05-06 17:09:53', 0);
INSERT INTO `tb_newbee_mall_carousel` VALUES (9, 'http://localhost:8089/upload/20200506_17100433.jpg', '##', 3, 0, '2020-05-06 17:10:09', 0, '2020-05-06 17:10:09', 0);
INSERT INTO `tb_newbee_mall_carousel` VALUES (10, 'http://localhost:8089/upload/20200506_17102647.jpg', '##', 0, 0, '2020-05-06 17:10:30', 0, '2020-05-06 17:10:30', 0);

-- ----------------------------
-- Table structure for tb_newbee_mall_goods_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_newbee_mall_goods_category`;
CREATE TABLE `tb_newbee_mall_goods_category`  (
  `category_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '分类id',
  `category_level` tinyint(4) NOT NULL DEFAULT 0 COMMENT '分类级别(1-一级分类 2-二级分类 3-三级分类)',
  `parent_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '父分类id',
  `category_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '分类名称',
  `category_rank` int(11) NOT NULL DEFAULT 0 COMMENT '排序值(字段越大越靠前)',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '删除标识字段(0-未删除 1-已删除)',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_user` int(11) NOT NULL DEFAULT 0 COMMENT '创建者id',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `update_user` int(11) NULL DEFAULT 0 COMMENT '修改者id',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 161 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_newbee_mall_goods_category
-- ----------------------------
INSERT INTO `tb_newbee_mall_goods_category` VALUES (1, 1, 0, '学历考试', 100, 0, '2019-09-11 18:45:40', 0, '2020-05-06 14:37:56', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (2, 1, 0, '鞋靴 箱包 配件', 91, 1, '2019-09-12 00:11:30', 0, '2019-09-12 00:11:30', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (3, 1, 0, '玩具 孕产 用品', 0, 1, '2019-09-12 00:11:17', 0, '2019-09-12 00:11:17', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (4, 1, 0, '工具 装修 建材', 93, 1, '2019-09-12 00:10:07', 0, '2019-09-12 00:10:07', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (5, 1, 0, '美妆 清洁 宠物', 94, 1, '2019-09-12 00:09:51', 0, '2019-09-17 18:22:34', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (113, 2, 1, '考博', 10, 0, '2020-05-06 15:01:17', 0, '2020-05-06 15:01:17', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (114, 2, 1, '考硕士', 9, 0, '2020-05-06 15:01:38', 0, '2020-05-06 16:11:49', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (115, 2, 1, '在职研究生', 8, 1, '2020-05-06 15:01:52', 0, '2020-05-06 15:01:52', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (116, 3, 113, '小博士', 10, 1, '2020-05-06 15:02:36', 0, '2020-05-06 15:02:36', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (117, 3, 113, '大博士', 9, 1, '2020-05-06 15:02:46', 0, '2020-05-06 15:02:46', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (118, 3, 113, '真博士', 8, 1, '2020-05-06 15:02:56', 0, '2020-05-06 15:02:56', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (119, 3, 113, '贾博士', 7, 1, '2020-05-06 15:03:04', 0, '2020-05-06 15:03:04', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (120, 3, 114, '同等学力申硕', 10, 0, '2020-05-06 15:03:18', 0, '2020-05-06 16:13:20', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (121, 3, 114, '在职研究生', 9, 0, '2020-05-06 15:03:26', 0, '2020-05-06 16:13:37', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (122, 3, 114, '学术研究生', 7, 0, '2020-05-06 15:03:38', 0, '2020-05-06 16:13:47', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (123, 3, 115, '白说是', 5, 1, '2020-05-06 15:03:53', 0, '2020-05-06 15:03:53', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (124, 3, 115, '加布事', 4, 1, '2020-05-06 15:04:03', 0, '2020-05-06 15:04:03', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (125, 3, 115, '嘿嘿嘿', 3, 1, '2020-05-06 15:04:14', 0, '2020-05-06 15:04:14', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (126, 2, 1, '大专本科', 8, 0, '2020-05-06 16:12:05', 0, '2020-05-06 16:30:42', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (127, 3, 113, '考博用书', 5, 0, '2020-05-06 16:13:00', 0, '2020-05-06 16:13:00', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (128, 3, 126, '成人高考', 0, 0, '2020-05-06 16:30:19', 0, '2020-05-06 16:30:19', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (129, 3, 126, '自考本科', 0, 0, '2020-05-06 16:30:26', 0, '2020-05-06 16:30:26', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (130, 3, 126, '自考专科', 4, 0, '2020-05-06 16:31:12', 0, '2020-05-06 16:31:12', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (131, 3, 126, '专插本', 0, 0, '2020-05-06 16:31:35', 0, '2020-05-06 16:31:35', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (132, 1, 0, '外语考试', 10, 0, '2020-05-06 16:33:38', 0, '2020-05-06 16:37:13', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (133, 2, 132, '语言能力', 0, 0, '2020-05-06 16:34:15', 0, '2020-05-06 16:39:00', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (134, 2, 132, '翻译资格', 0, 0, '2020-05-06 16:36:23', 0, '2020-05-06 16:36:23', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (135, 2, 132, '口译资格', 0, 0, '2020-05-06 16:36:31', 0, '2020-05-06 16:36:31', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (136, 2, 132, '英语专业', 0, 0, '2020-05-06 16:36:48', 0, '2020-05-06 16:41:59', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (137, 3, 133, '韩语TOPIK', 0, 0, '2020-05-06 16:37:36', 0, '2020-05-06 16:37:36', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (138, 3, 133, '日语等级', 0, 0, '2020-05-06 16:37:48', 0, '2020-05-06 16:37:48', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (139, 3, 133, 'BJT商务日语', 0, 0, '2020-05-06 16:38:04', 0, '2020-05-06 16:38:04', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (140, 3, 134, '翻译', 0, 0, '2020-05-06 16:40:54', 0, '2020-05-06 16:40:54', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (141, 3, 135, '口译', 0, 0, '2020-05-06 16:41:07', 0, '2020-05-06 16:41:07', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (142, 3, 136, '专四', 0, 0, '2020-05-06 16:41:26', 0, '2020-05-06 16:41:26', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (143, 3, 136, '专八', 0, 0, '2020-05-06 16:41:31', 0, '2020-05-06 16:41:31', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (144, 1, 0, '职业资格', 0, 0, '2020-05-06 16:53:31', 0, '2020-05-06 16:53:31', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (145, 1, 0, '工程证书', 0, 0, '2020-05-06 16:53:59', 0, '2020-05-06 16:53:59', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (146, 2, 144, '计算机', 0, 0, '2020-05-06 16:54:19', 0, '2020-05-06 16:54:19', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (147, 2, 144, '制造业', 0, 0, '2020-05-06 16:54:26', 0, '2020-05-06 16:54:26', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (148, 2, 144, '服务业', 0, 0, '2020-05-06 16:54:35', 0, '2020-05-06 16:54:35', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (149, 2, 144, '其他', 0, 0, '2020-05-06 16:54:41', 0, '2020-05-06 16:54:41', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (150, 3, 146, '华为认证', 0, 0, '2020-05-06 16:55:00', 0, '2020-05-06 16:55:00', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (151, 3, 146, '腾讯认证', 0, 0, '2020-05-06 16:55:10', 0, '2020-05-06 16:55:10', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (152, 3, 147, '注册验船师', 0, 0, '2020-05-06 16:55:26', 0, '2020-05-06 16:55:26', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (153, 3, 147, '注册计量师', 0, 0, '2020-05-06 16:55:42', 0, '2020-05-06 16:55:42', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (154, 3, 148, '育婴师', 0, 0, '2020-05-06 16:56:11', 0, '2020-05-06 16:56:11', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (155, 3, 148, '其他', 0, 0, '2020-05-06 16:56:23', 0, '2020-05-06 16:56:23', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (156, 3, 149, '其他类', 0, 0, '2020-05-06 16:56:50', 0, '2020-05-06 16:56:50', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (157, 2, 145, '国家职业资格', 0, 0, '2020-05-06 16:57:24', 0, '2020-05-06 16:57:24', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (158, 2, 145, '行业资格', 0, 0, '2020-05-06 16:57:42', 0, '2020-05-06 16:57:42', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (159, 3, 157, '健康管理师', 0, 0, '2020-05-06 16:58:03', 0, '2020-05-06 16:58:03', 0);
INSERT INTO `tb_newbee_mall_goods_category` VALUES (160, 2, 145, 'other', 0, 0, '2020-05-06 16:58:50', 0, '2020-05-06 16:58:50', 0);

-- ----------------------------
-- Table structure for tb_newbee_mall_goods_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_newbee_mall_goods_info`;
CREATE TABLE `tb_newbee_mall_goods_info`  (
  `goods_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '商品表主键id',
  `goods_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品名',
  `goods_intro` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品简介',
  `goods_category_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '关联分类id',
  `goods_cover_img` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '/admin/dist/img/no-img.png' COMMENT '商品主图',
  `goods_carousel` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '/admin/dist/img/no-img.png' COMMENT '商品轮播图',
  `goods_detail_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品详情',
  `original_price` int(11) NOT NULL DEFAULT 1 COMMENT '商品价格',
  `selling_price` int(11) NOT NULL DEFAULT 1 COMMENT '商品实际售价',
  `stock_num` int(11) NOT NULL DEFAULT 0 COMMENT '商品库存数量',
  `tag` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品标签',
  `goods_sell_status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '商品上架状态 0-下架 1-上架',
  `create_user` int(11) NOT NULL DEFAULT 0 COMMENT '添加者主键id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '商品添加时间',
  `update_user` int(11) NOT NULL DEFAULT 0 COMMENT '修改者主键id',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '商品修改时间',
  PRIMARY KEY (`goods_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10899 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_newbee_mall_goods_info
-- ----------------------------
INSERT INTO `tb_newbee_mall_goods_info` VALUES (10898, '建厚', '猪猪', 127, 'http://localhost:8089/upload/20200506_17054261.jpg', 'http://localhost:8089/upload/20200506_17054261.jpg', '<p>\n	朴建厚，猪猪\n</p>\n<p>\n	<img src=\"http://localhost:8089/upload/20200506_17042584.jpg\" alt=\"\" />\n</p>', 4, 6, 0, '宝宝', 0, 0, '2020-05-06 17:05:43', 0, '2020-05-06 17:05:43');

-- ----------------------------
-- Table structure for tb_newbee_mall_index_config
-- ----------------------------
DROP TABLE IF EXISTS `tb_newbee_mall_index_config`;
CREATE TABLE `tb_newbee_mall_index_config`  (
  `config_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '首页配置项主键id',
  `config_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '显示字符(配置搜索时不可为空，其他可为空)',
  `config_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '1-搜索框热搜 2-搜索下拉框热搜 3-(首页)热销商品 4-(首页)新品上线 5-(首页)为你推荐',
  `goods_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '商品id 默认为0',
  `redirect_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '##' COMMENT '点击后的跳转地址(默认不跳转)',
  `config_rank` int(11) NOT NULL DEFAULT 0 COMMENT '排序值(字段越大越靠前)',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '删除标识字段(0-未删除 1-已删除)',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_user` int(11) NOT NULL DEFAULT 0 COMMENT '创建者id',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '最新修改时间',
  `update_user` int(11) NULL DEFAULT 0 COMMENT '修改者id',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_newbee_mall_index_config
-- ----------------------------
INSERT INTO `tb_newbee_mall_index_config` VALUES (25, '猪猪宝宝', 3, 10898, '##', 0, 1, '2020-05-06 17:06:18', 0, '2020-05-06 17:07:43', 0);
INSERT INTO `tb_newbee_mall_index_config` VALUES (26, '宝宝猪猪', 4, 10898, '##', 0, 1, '2020-05-06 17:06:29', 0, '2020-05-06 17:07:49', 0);
INSERT INTO `tb_newbee_mall_index_config` VALUES (27, '建厚宝宝', 5, 10898, '##', 0, 1, '2020-05-06 17:06:39', 0, '2020-05-06 17:07:54', 0);

-- ----------------------------
-- Table structure for tb_newbee_mall_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_newbee_mall_order`;
CREATE TABLE `tb_newbee_mall_order`  (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单表主键id',
  `order_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '订单号',
  `user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '用户主键id',
  `total_price` int(11) NOT NULL DEFAULT 1 COMMENT '订单总价',
  `pay_status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '支付状态:0.未支付,1.支付成功,-1:支付失败',
  `pay_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '0.无 1.支付宝支付 2.微信支付',
  `pay_time` datetime(0) NULL DEFAULT NULL COMMENT '支付时间',
  `order_status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '订单状态:0.待支付 1.已支付 2.配货完成 3:出库成功 4.交易成功 -1.手动关闭 -2.超时关闭 -3.商家关闭',
  `extra_info` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '订单body',
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '收货人姓名',
  `user_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '收货人手机号',
  `user_address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '收货人收货地址',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '删除标识字段(0-未删除 1-已删除)',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '最新修改时间',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_newbee_mall_order_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_newbee_mall_order_item`;
CREATE TABLE `tb_newbee_mall_order_item`  (
  `order_item_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单关联购物项主键id',
  `order_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '订单主键id',
  `goods_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '关联商品id',
  `goods_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '下单时商品的名称(订单快照)',
  `goods_cover_img` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '下单时商品的主图(订单快照)',
  `selling_price` int(11) NOT NULL DEFAULT 1 COMMENT '下单时商品的价格(订单快照)',
  `goods_count` int(11) NOT NULL DEFAULT 1 COMMENT '数量(订单快照)',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`order_item_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_newbee_mall_shopping_cart_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_newbee_mall_shopping_cart_item`;
CREATE TABLE `tb_newbee_mall_shopping_cart_item`  (
  `cart_item_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '购物项主键id',
  `user_id` bigint(20) NOT NULL COMMENT '用户主键id',
  `goods_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '关联商品id',
  `goods_count` int(11) NOT NULL DEFAULT 1 COMMENT '数量(最大为5)',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '删除标识字段(0-未删除 1-已删除)',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '最新修改时间',
  PRIMARY KEY (`cart_item_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 73 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_newbee_mall_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_newbee_mall_user`;
CREATE TABLE `tb_newbee_mall_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户主键id',
  `nick_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户昵称',
  `login_name` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '登陆名称(默认为手机号)',
  `password_md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT 'MD5加密后的密码',
  `introduce_sign` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '个性签名',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '收货地址',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '注销标识字段(0-正常 1-已注销)',
  `locked_flag` tinyint(4) NOT NULL DEFAULT 0 COMMENT '锁定标识字段(0-未锁定 1-已锁定)',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '注册时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_newbee_mall_user
-- ----------------------------
INSERT INTO `tb_newbee_mall_user` VALUES (10, '盐包', '18814482748', 'e10adc3949ba59abbe56e057f20f883e', '我超厉害', '广东省深圳市腾讯大厦', 0, 0, '2020-05-06 16:43:20');

SET FOREIGN_KEY_CHECKS = 1;
