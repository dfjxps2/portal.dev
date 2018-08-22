/*
Navicat MySQL Data Transfer

Source Server         : PortalServer
Source Server Version : 50639
Source Host           : 10.1.3.193:3306
Source Database       : portaldb

Target Server Type    : MYSQL
Target Server Version : 50639
File Encoding         : 65001

Date: 2018-08-22 10:21:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for alert_mode
-- ----------------------------
DROP TABLE IF EXISTS `alert_mode`;
CREATE TABLE `alert_mode` (
  `alert_mode_id` int(11) NOT NULL,
  `alert_mode_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`alert_mode_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of alert_mode
-- ----------------------------

-- ----------------------------
-- Table structure for application
-- ----------------------------
DROP TABLE IF EXISTS `application`;
CREATE TABLE `application` (
  `app_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '应用系统ID',
  `super_app_id` int(11) DEFAULT NULL COMMENT '上级应用系统ID',
  `app_name` varchar(40) DEFAULT NULL COMMENT '应用系统名称',
  `dep_id` int(11) NOT NULL COMMENT '部门ID',
  `app_level` int(11) NOT NULL COMMENT '应用系统级别',
  `app_state` int(11) NOT NULL COMMENT '应用系统状态',
  `app_url` varchar(200) DEFAULT NULL COMMENT '应用系统URL',
  `app_preview_url` varchar(200) DEFAULT NULL COMMENT '应用系统预览URL',
  `reg_date` date DEFAULT NULL COMMENT '注册日期',
  `pub_date` date DEFAULT NULL COMMENT '发布日期',
  `cre_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `upd_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`app_id`)
) ENGINE=InnoDB AUTO_INCREMENT=132 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of application
-- ----------------------------
INSERT INTO `application` VALUES ('37', null, '信息资源管理系统', '3100', '1', '1', 'http://10.1.2.141:8080/loginA?username=admin&password=123456', '', '2018-06-08', '2018-06-08', '2018-06-08 15:09:40', '2018-06-28 17:17:05');
INSERT INTO `application` VALUES ('38', null, '统一监控平台', '3', '1', '1', 'http://10.1.3.77/zabbix', '', '2018-06-08', '2018-06-08', '2018-06-08 15:41:21', '2018-06-15 09:33:02');
INSERT INTO `application` VALUES ('39', null, '数据治理平台', '3300', '1', '1', 'http://10.1.2.143:8080/', '', null, null, '2018-06-08 16:50:19', '2018-06-15 16:42:34');
INSERT INTO `application` VALUES ('43', null, '实时外汇行情', '3', '1', '1', 'http://10.1.3.193:8080/SpiderShower/', 'res/script/home/images/u13.png', null, null, null, '2018-06-15 16:24:03');
INSERT INTO `application` VALUES ('53', null, '共享交换系统', '3300', '1', '1', 'http://10.1.2.142:8080/cloud/', '', null, null, '2018-06-15 16:11:22', '2018-07-26 13:48:49');
INSERT INTO `application` VALUES ('54', null, '网络爬虫系统', '3300', '1', '1', 'http://10.1.3.193:8080/SpiderManage', 'res/script/home/images/u13.png', null, null, '2018-06-15 16:13:48', '2018-06-15 18:00:21');
INSERT INTO `application` VALUES ('55', null, '大数据管理平台', '3300', '1', '1', 'http://10.1.3.208:9999/#/main/dashboard/metrics', '', null, null, '2018-06-15 16:16:08', '2018-06-15 16:16:08');
INSERT INTO `application` VALUES ('56', null, '大数据安全管理', '3300', '1', '1', 'http://10.1.3.53:6080/index.html', '', null, null, '2018-06-15 16:17:07', '2018-06-15 16:17:07');
INSERT INTO `application` VALUES ('57', null, '大数据使用平台', '3300', '1', '1', 'http://10.1.3.206:8888', '', null, null, '2018-06-15 16:18:02', '2018-06-15 16:18:02');
INSERT INTO `application` VALUES ('58', null, '大数据元数据管理', '3300', '1', '1', 'http://10.1.3.208:21000/index.html', '', null, null, '2018-06-15 16:23:22', '2018-06-15 16:23:22');
INSERT INTO `application` VALUES ('59', null, '资源目录系统', '3100', '1', '1', 'http://10.1.2.141:8080/loginA?username=admin&password=123456', '', null, null, '2018-06-15 16:25:00', '2018-06-15 16:25:00');
INSERT INTO `application` VALUES ('60', null, '数据治理系统', '3300', '1', '1', 'http://10.1.2.143:8080/', '', null, null, '2018-06-15 16:26:01', '2018-06-15 16:26:01');
INSERT INTO `application` VALUES ('61', null, '指标管理平台', '3300', '1', '1', 'http://10.1.3.193:8080/bsbProd/', '', null, null, '2018-06-15 16:27:11', '2018-06-15 16:27:11');
INSERT INTO `application` VALUES ('62', null, '分布式挖掘系统', '3400', '1', '1', 'http://10.1.3.207:54321', '', null, null, '2018-06-15 16:27:57', '2018-06-15 16:27:57');
INSERT INTO `application` VALUES ('63', null, '高维模型分析', '3400', '1', '1', 'http://10.1.3.204:7070/kylin', '', null, null, '2018-06-15 16:28:30', '2018-06-15 16:28:30');
INSERT INTO `application` VALUES ('64', null, '大数据检索引擎', '3300', '1', '1', 'http://10.1.3.208:8983/solr/#/', '', null, null, '2018-06-15 16:29:30', '2018-06-15 16:43:22');
INSERT INTO `application` VALUES ('65', null, '批量日志分析', '3400', '1', '1', 'http://10.1.3.208:61888', '', null, null, '2018-06-15 16:30:08', '2018-06-15 16:40:52');
INSERT INTO `application` VALUES ('66', null, '监控运维', '3400', '1', '1', 'http://10.1.3.77/zabbix', '', null, null, '2018-06-15 16:30:44', '2018-06-15 16:36:46');
INSERT INTO `application` VALUES ('102', null, '城市交通', '4', '1', '1', 'http://www.baidu.com', 'res/script/home/images/u30.png', '2018-04-12', '2018-04-12', '2018-04-12 00:00:00', '2018-08-16 08:18:28');
INSERT INTO `application` VALUES ('103', null, '经济运行', '4200', '1', '1', 'http://www.baidu.com', 'res/script/home/images/u14.png', '2018-04-12', '2018-04-12', '2018-04-12 00:00:00', '2018-05-24 14:35:42');
INSERT INTO `application` VALUES ('104', null, '城市生命线', '2', '1', '1', 'http://www.baidu.com', 'res/script/home/images/u31.png', '2018-04-13', '2018-04-13', null, '2018-08-16 08:18:41');
INSERT INTO `application` VALUES ('106', null, '环境气象', '5', '1', '1', 'http://www.baidu.com', 'res/script/home/images/u11.png', null, null, '2018-04-13 02:10:56', '2018-08-15 09:50:20');
INSERT INTO `application` VALUES ('107', null, '政务服务', '2', '1', '1', 'http://www.baidu.com', 'upload/home/e0e86cf1dcd844859d869581b13d85e7.png', null, null, '2018-04-13 02:13:02', '2018-08-15 12:45:21');
INSERT INTO `application` VALUES ('108', null, '一带一路', '2', '1', '1', 'http://www.baidu.com', 'res/script/home/images/u31.png', null, null, '2018-04-13 09:30:30', '2018-08-15 12:35:31');
INSERT INTO `application` VALUES ('109', null, '态势感知', '5', '1', '1', 'http://10.1.2.143:8080/WebReport/ReportServer?formlet=RLT.frm', 'res/script/home/images/u21.png', null, null, null, '2018-06-19 18:57:30');
INSERT INTO `application` VALUES ('110', null, '民生幸福指数', '3', '1', '1', 'http://www.baidu.com', 'upload/home/82216059f8f14d4c9b62583bdcc41ea3.png', null, null, null, '2018-08-15 11:30:02');
INSERT INTO `application` VALUES ('111', null, '外贸分析地图', '4200', '1', '1', 'http://10.1.3.193:8088/hainan/trade.html', 'res/script/home/images/u15.png', '2018-04-19', null, '2018-04-19 21:56:46', '2018-06-08 11:01:38');
INSERT INTO `application` VALUES ('112', null, '数据可视化', '3300', '1', '1', 'http://10.1.2.143:8080/WebReport/ReportServer?formlet=RLT.frm', '', null, null, '2018-06-15 18:41:41', '2018-06-15 18:41:41');
INSERT INTO `application` VALUES ('113', null, '人群实时分布', '5', '1', '1', 'http://10.1.2.143:8080/WebReport/ReportServer?formlet=RLT.frm', '', null, null, '2018-06-15 19:28:41', '2018-06-15 19:28:41');
INSERT INTO `application` VALUES ('114', null, '待办事项统计', '5', '1', '1', 'http://10.1.2.143:8080/WebReport/ReportServer?reportlet=RYGJ.cpt&op=view', '', null, null, '2018-06-15 19:29:13', '2018-06-15 19:29:13');
INSERT INTO `application` VALUES ('116', null, '数据可视化工具', '3300', '2', '1', 'http:10.1.2.143:37799/WebReport/ReportServer?op=fs', '', null, null, '2018-06-19 12:05:37', '2018-06-19 12:05:37');
INSERT INTO `application` VALUES ('124', null, '交易及会员数据分析服务系统', '5', '1', '1', 'http://localhost:8080/cqpgx-data/shiro-cas', 'res/script/home/images/u14.png', null, null, '2018-06-29 17:21:22', '2018-07-03 15:22:01');
INSERT INTO `application` VALUES ('128', null, '业务监测应用', '2', '1', '1', '', '', null, '2018-08-13', '2018-07-05 09:44:18', '2018-08-13 11:11:21');
INSERT INTO `application` VALUES ('129', null, '数据监测', '2', '1', '1', '', '', null, '2018-07-05', '2018-07-05 10:26:59', '2018-07-05 10:31:01');
INSERT INTO `application` VALUES ('131', null, '智能应用', '2', '1', '1', '', '', null, null, '2018-08-14 17:07:52', '2018-08-14 17:07:52');

-- ----------------------------
-- Table structure for app_class
-- ----------------------------
DROP TABLE IF EXISTS `app_class`;
CREATE TABLE `app_class` (
  `app_class_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '应用系统类型ID',
  `app_class_name` varchar(100) DEFAULT NULL COMMENT '应用系统类型名称',
  `cre_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `upd_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`app_class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_class
-- ----------------------------
INSERT INTO `app_class` VALUES ('1', '主题应用', null, null);
INSERT INTO `app_class` VALUES ('2', '部门应用', null, null);
INSERT INTO `app_class` VALUES ('3', '应急大数据', null, null);

-- ----------------------------
-- Table structure for app_class_rela
-- ----------------------------
DROP TABLE IF EXISTS `app_class_rela`;
CREATE TABLE `app_class_rela` (
  `rel_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `app_class_id` int(11) NOT NULL COMMENT '应用系统ID',
  `app_id` int(11) NOT NULL COMMENT '应用系统类型ID',
  PRIMARY KEY (`rel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_class_rela
-- ----------------------------
INSERT INTO `app_class_rela` VALUES ('1', '1', '3');
INSERT INTO `app_class_rela` VALUES ('3', '1', '4');
INSERT INTO `app_class_rela` VALUES ('4', '1', '6');
INSERT INTO `app_class_rela` VALUES ('5', '1', '7');
INSERT INTO `app_class_rela` VALUES ('6', '1', '8');
INSERT INTO `app_class_rela` VALUES ('7', '2', '9');
INSERT INTO `app_class_rela` VALUES ('8', '2', '10');
INSERT INTO `app_class_rela` VALUES ('9', '2', '13');
INSERT INTO `app_class_rela` VALUES ('10', '2', '14');
INSERT INTO `app_class_rela` VALUES ('11', '2', '15');
INSERT INTO `app_class_rela` VALUES ('12', '3', '11');
INSERT INTO `app_class_rela` VALUES ('14', '1', '22');
INSERT INTO `app_class_rela` VALUES ('15', '1', '23');
INSERT INTO `app_class_rela` VALUES ('22', '1', '40');
INSERT INTO `app_class_rela` VALUES ('23', '2', '41');
INSERT INTO `app_class_rela` VALUES ('24', '1', '42');
INSERT INTO `app_class_rela` VALUES ('25', '1', '44');
INSERT INTO `app_class_rela` VALUES ('26', '1', '45');
INSERT INTO `app_class_rela` VALUES ('27', '1', '46');
INSERT INTO `app_class_rela` VALUES ('28', '1', '47');
INSERT INTO `app_class_rela` VALUES ('33', '3', '55');
INSERT INTO `app_class_rela` VALUES ('34', '3', '56');
INSERT INTO `app_class_rela` VALUES ('35', '3', '57');
INSERT INTO `app_class_rela` VALUES ('36', '3', '58');
INSERT INTO `app_class_rela` VALUES ('37', '2', '59');
INSERT INTO `app_class_rela` VALUES ('38', '3', '60');
INSERT INTO `app_class_rela` VALUES ('39', '2', '61');
INSERT INTO `app_class_rela` VALUES ('40', '2', '62');
INSERT INTO `app_class_rela` VALUES ('41', '2', '63');
INSERT INTO `app_class_rela` VALUES ('47', '3', '65');
INSERT INTO `app_class_rela` VALUES ('48', '3', '39');
INSERT INTO `app_class_rela` VALUES ('49', '3', '64');
INSERT INTO `app_class_rela` VALUES ('51', '1', '103');
INSERT INTO `app_class_rela` VALUES ('61', '3', '111');
INSERT INTO `app_class_rela` VALUES ('63', '1', '112');
INSERT INTO `app_class_rela` VALUES ('64', '1', '113');
INSERT INTO `app_class_rela` VALUES ('65', '1', '114');
INSERT INTO `app_class_rela` VALUES ('67', '1', '116');
INSERT INTO `app_class_rela` VALUES ('70', '1', '121');
INSERT INTO `app_class_rela` VALUES ('71', '1', '131');

-- ----------------------------
-- Table structure for app_page
-- ----------------------------
DROP TABLE IF EXISTS `app_page`;
CREATE TABLE `app_page` (
  `app_page_id` int(11) NOT NULL AUTO_INCREMENT,
  `page_id` int(11) NOT NULL,
  `app_id` int(11) NOT NULL,
  PRIMARY KEY (`app_page_id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_page
-- ----------------------------
INSERT INTO `app_page` VALUES ('5', '5', '17');
INSERT INTO `app_page` VALUES ('6', '6', '17');
INSERT INTO `app_page` VALUES ('7', '7', '24');
INSERT INTO `app_page` VALUES ('8', '8', '17');
INSERT INTO `app_page` VALUES ('9', '9', '17');
INSERT INTO `app_page` VALUES ('10', '10', '29');
INSERT INTO `app_page` VALUES ('11', '11', '29');
INSERT INTO `app_page` VALUES ('12', '12', '17');
INSERT INTO `app_page` VALUES ('13', '13', '31');
INSERT INTO `app_page` VALUES ('14', '14', '32');
INSERT INTO `app_page` VALUES ('15', '15', '32');
INSERT INTO `app_page` VALUES ('16', '16', '32');
INSERT INTO `app_page` VALUES ('17', '17', '23');
INSERT INTO `app_page` VALUES ('18', '18', '32');
INSERT INTO `app_page` VALUES ('19', '19', '33');
INSERT INTO `app_page` VALUES ('20', '20', '22');
INSERT INTO `app_page` VALUES ('21', '21', '36');
INSERT INTO `app_page` VALUES ('22', '22', '48');
INSERT INTO `app_page` VALUES ('23', '23', '36');
INSERT INTO `app_page` VALUES ('24', '24', '49');
INSERT INTO `app_page` VALUES ('25', '25', '49');
INSERT INTO `app_page` VALUES ('26', '25', '52');
INSERT INTO `app_page` VALUES ('27', '26', '115');
INSERT INTO `app_page` VALUES ('28', '27', '117');
INSERT INTO `app_page` VALUES ('29', '28', '117');
INSERT INTO `app_page` VALUES ('30', '29', '118');
INSERT INTO `app_page` VALUES ('31', '30', '49');
INSERT INTO `app_page` VALUES ('32', '31', '119');
INSERT INTO `app_page` VALUES ('33', '32', '120');
INSERT INTO `app_page` VALUES ('34', '33', '119');
INSERT INTO `app_page` VALUES ('35', '34', '119');
INSERT INTO `app_page` VALUES ('36', '35', '119');
INSERT INTO `app_page` VALUES ('37', '36', '119');
INSERT INTO `app_page` VALUES ('38', '37', '121');
INSERT INTO `app_page` VALUES ('39', '38', '122');
INSERT INTO `app_page` VALUES ('40', '39', '123');
INSERT INTO `app_page` VALUES ('41', '40', '125');
INSERT INTO `app_page` VALUES ('42', '41', '125');
INSERT INTO `app_page` VALUES ('43', '42', '126');
INSERT INTO `app_page` VALUES ('44', '43', '127');
INSERT INTO `app_page` VALUES ('45', '44', '49');
INSERT INTO `app_page` VALUES ('46', '45', '128');
INSERT INTO `app_page` VALUES ('47', '46', '129');
INSERT INTO `app_page` VALUES ('48', '47', '128');
INSERT INTO `app_page` VALUES ('49', '48', '130');
INSERT INTO `app_page` VALUES ('50', '49', '131');

-- ----------------------------
-- Table structure for business_type
-- ----------------------------
DROP TABLE IF EXISTS `business_type`;
CREATE TABLE `business_type` (
  `busi_type_id` int(11) NOT NULL,
  `busi_type_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`busi_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of business_type
-- ----------------------------

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `conent` varchar(500) DEFAULT NULL,
  `snapshot_url` varchar(200) DEFAULT NULL,
  `cmt_time` timestamp NULL DEFAULT NULL,
  `cmt_state` int(11) DEFAULT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comments
-- ----------------------------
INSERT INTO `comments` VALUES ('1', '1', '经济指标未达到季度目标', 'upload/comment/5f88b835d9264881827857dec2c209e2.png', '2018-06-07 11:28:58', '0');
INSERT INTO `comments` VALUES ('2', '10', '画布的标记不能修改', 'upload/comment/62aa62422e624f818e24ae69d8b17e28.png', '2018-06-07 15:24:39', '0');
INSERT INTO `comments` VALUES ('3', '10', '画布标记便偏移', 'upload/comment/0bd0867d1a3c495a9f3de0a4e38e811f.png', '2018-06-07 15:25:21', '0');
INSERT INTO `comments` VALUES ('4', '1', '批示发送', 'upload/comment/7570159f77384b279294a2bd79c3e3d7.png', '2018-06-21 13:44:38', '0');
INSERT INTO `comments` VALUES ('5', '1', '批示', 'upload/comment/6ee06d9cf526437ab257936c934406b7.png', '2018-06-21 13:45:05', '0');
INSERT INTO `comments` VALUES ('6', '1', '2', 'upload/comment/bb3401b65189469491c7d33bd674c652.png', '2018-06-21 13:53:51', '0');

-- ----------------------------
-- Table structure for content_rule
-- ----------------------------
DROP TABLE IF EXISTS `content_rule`;
CREATE TABLE `content_rule` (
  `rule_id` int(11) NOT NULL,
  `rule_type_id` int(11) DEFAULT NULL,
  `cre_time` timestamp NULL DEFAULT NULL,
  `upd_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`rule_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of content_rule
-- ----------------------------
INSERT INTO `content_rule` VALUES ('1', '1', '2018-05-13 17:15:25', '2018-05-13 17:15:30');

-- ----------------------------
-- Table structure for content_rule_param
-- ----------------------------
DROP TABLE IF EXISTS `content_rule_param`;
CREATE TABLE `content_rule_param` (
  `param_id` int(11) NOT NULL,
  `rule_id` int(11) DEFAULT NULL,
  `param_name` varchar(20) DEFAULT NULL,
  `param_value` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`param_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of content_rule_param
-- ----------------------------
INSERT INTO `content_rule_param` VALUES ('1', '1', '敏感词汇', '暴力');
INSERT INTO `content_rule_param` VALUES ('2', '1', '敏感词汇', '枪支');
INSERT INTO `content_rule_param` VALUES ('3', '1', '敏感词汇', '毒品');
INSERT INTO `content_rule_param` VALUES ('4', '1', '敏感词汇', '恐怖袭击');
INSERT INTO `content_rule_param` VALUES ('5', '1', '敏感词汇', '生气');
INSERT INTO `content_rule_param` VALUES ('6', '1', '敏感词汇', '害怕');

-- ----------------------------
-- Table structure for content_rule_type
-- ----------------------------
DROP TABLE IF EXISTS `content_rule_type`;
CREATE TABLE `content_rule_type` (
  `rule_type_id` int(11) NOT NULL,
  `rule_type_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`rule_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of content_rule_type
-- ----------------------------
INSERT INTO `content_rule_type` VALUES ('1', '黑名单');

-- ----------------------------
-- Table structure for dashboard_app_config
-- ----------------------------
DROP TABLE IF EXISTS `dashboard_app_config`;
CREATE TABLE `dashboard_app_config` (
  `config_id` int(11) NOT NULL AUTO_INCREMENT,
  `dashboard_id` int(11) DEFAULT NULL,
  `app_id` int(11) NOT NULL,
  `param_id` int(11) DEFAULT NULL,
  `param_value` varchar(40) DEFAULT NULL,
  `cre_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=746 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dashboard_app_config
-- ----------------------------
INSERT INTO `dashboard_app_config` VALUES ('135', '12', '124', '1', '6', '2018-07-03 15:16:59');
INSERT INTO `dashboard_app_config` VALUES ('158', '10', '129', '1', '4', '2018-07-27 15:29:19');
INSERT INTO `dashboard_app_config` VALUES ('159', '10', '128', '1', '5', '2018-07-27 15:29:19');
INSERT INTO `dashboard_app_config` VALUES ('160', '10', '130', '1', '6', '2018-07-27 15:29:19');
INSERT INTO `dashboard_app_config` VALUES ('163', '3', '130', '1', '3', '2018-07-27 15:30:07');
INSERT INTO `dashboard_app_config` VALUES ('164', '1', '130', '1', '17', '2018-08-03 09:12:02');
INSERT INTO `dashboard_app_config` VALUES ('216', '3', '3', '1', '66', '2018-08-13 09:18:15');
INSERT INTO `dashboard_app_config` VALUES ('411', '3', '103', '1', '138', '2018-08-15 17:54:26');
INSERT INTO `dashboard_app_config` VALUES ('413', '3', '1', '1', '141', '2018-08-16 09:21:58');
INSERT INTO `dashboard_app_config` VALUES ('414', '3', '130', '1', '142', '2018-08-20 11:00:44');
INSERT INTO `dashboard_app_config` VALUES ('416', '3', '130', '1', '144', '2018-08-20 11:01:07');
INSERT INTO `dashboard_app_config` VALUES ('418', '3', '130', '1', '146', '2018-08-20 11:36:27');
INSERT INTO `dashboard_app_config` VALUES ('420', '3', '103', '1', '148', '2018-08-20 11:36:37');
INSERT INTO `dashboard_app_config` VALUES ('421', '3', '103', '1', '149', '2018-08-20 11:40:25');
INSERT INTO `dashboard_app_config` VALUES ('427', '3', '12', '1', '155', '2018-08-20 11:40:25');
INSERT INTO `dashboard_app_config` VALUES ('428', '3', '103', '1', '156', '2018-08-20 13:25:24');
INSERT INTO `dashboard_app_config` VALUES ('432', '3', '10', '1', '160', '2018-08-20 13:25:24');
INSERT INTO `dashboard_app_config` VALUES ('433', '3', '103', '1', '161', '2018-08-20 13:58:20');
INSERT INTO `dashboard_app_config` VALUES ('438', '3', '10', '1', '166', '2018-08-20 13:58:20');
INSERT INTO `dashboard_app_config` VALUES ('439', '3', '103', '1', '167', '2018-08-20 14:12:40');
INSERT INTO `dashboard_app_config` VALUES ('443', '3', '10', '1', '171', '2018-08-20 14:12:40');
INSERT INTO `dashboard_app_config` VALUES ('444', '3', '103', '1', '172', '2018-08-20 14:24:19');
INSERT INTO `dashboard_app_config` VALUES ('450', '3', '103', '1', '178', '2018-08-20 14:27:01');
INSERT INTO `dashboard_app_config` VALUES ('457', '3', '103', '1', '185', '2018-08-20 14:28:26');
INSERT INTO `dashboard_app_config` VALUES ('463', '3', '103', '1', '191', '2018-08-20 14:28:47');
INSERT INTO `dashboard_app_config` VALUES ('473', '3', '103', '1', '201', '2018-08-20 14:34:43');
INSERT INTO `dashboard_app_config` VALUES ('477', '3', '103', '1', '205', '2018-08-20 14:55:10');
INSERT INTO `dashboard_app_config` VALUES ('482', '3', '103', '1', '210', '2018-08-20 14:57:32');
INSERT INTO `dashboard_app_config` VALUES ('488', '3', '103', '1', '216', '2018-08-20 15:05:12');
INSERT INTO `dashboard_app_config` VALUES ('495', '3', '103', '1', '223', '2018-08-20 15:07:48');
INSERT INTO `dashboard_app_config` VALUES ('500', '3', '103', '1', '228', '2018-08-20 15:09:11');
INSERT INTO `dashboard_app_config` VALUES ('504', '3', '103', '1', '232', '2018-08-20 15:13:22');
INSERT INTO `dashboard_app_config` VALUES ('509', '3', '103', '1', '237', '2018-08-20 15:29:17');
INSERT INTO `dashboard_app_config` VALUES ('515', '3', '103', '1', '243', '2018-08-20 15:29:36');
INSERT INTO `dashboard_app_config` VALUES ('522', '3', '103', '1', '250', '2018-08-20 15:30:17');
INSERT INTO `dashboard_app_config` VALUES ('528', '3', '103', '1', '256', '2018-08-20 15:30:22');
INSERT INTO `dashboard_app_config` VALUES ('534', '3', '103', '1', '258', '2018-08-20 15:31:01');
INSERT INTO `dashboard_app_config` VALUES ('536', '3', '103', '1', '260', '2018-08-20 15:33:15');
INSERT INTO `dashboard_app_config` VALUES ('539', '3', '103', '1', '263', '2018-08-20 15:39:30');
INSERT INTO `dashboard_app_config` VALUES ('543', '3', '103', '1', '267', '2018-08-20 15:46:11');
INSERT INTO `dashboard_app_config` VALUES ('548', '3', '103', '1', '269', '2018-08-20 15:47:32');
INSERT INTO `dashboard_app_config` VALUES ('551', '3', '103', '1', '271', '2018-08-20 15:48:45');
INSERT INTO `dashboard_app_config` VALUES ('553', '3', '103', '1', '272', '2018-08-20 15:48:56');
INSERT INTO `dashboard_app_config` VALUES ('554', '3', '103', '1', '273', '2018-08-20 16:35:34');
INSERT INTO `dashboard_app_config` VALUES ('556', '3', '103', '1', '275', '2018-08-20 16:40:06');
INSERT INTO `dashboard_app_config` VALUES ('559', '3', '103', '1', '277', '2018-08-20 16:43:35');
INSERT INTO `dashboard_app_config` VALUES ('561', '3', '103', '1', '279', '2018-08-20 16:52:11');
INSERT INTO `dashboard_app_config` VALUES ('564', '3', '103', '1', '281', '2018-08-20 17:07:53');
INSERT INTO `dashboard_app_config` VALUES ('566', '3', '103', '1', '283', '2018-08-20 17:18:14');
INSERT INTO `dashboard_app_config` VALUES ('569', '3', '103', '1', '285', '2018-08-20 17:24:40');
INSERT INTO `dashboard_app_config` VALUES ('571', '3', '103', '1', '286', '2018-08-20 17:26:17');
INSERT INTO `dashboard_app_config` VALUES ('572', '3', '103', '1', '287', '2018-08-21 09:05:25');
INSERT INTO `dashboard_app_config` VALUES ('573', '3', '131', '1', '288', '2018-08-21 09:05:25');
INSERT INTO `dashboard_app_config` VALUES ('574', '3', '103', '1', '289', '2018-08-21 09:07:29');
INSERT INTO `dashboard_app_config` VALUES ('575', '3', '131', '1', '290', '2018-08-21 09:07:29');
INSERT INTO `dashboard_app_config` VALUES ('577', '3', '103', '1', '292', '2018-08-21 09:10:00');
INSERT INTO `dashboard_app_config` VALUES ('578', '3', '131', '1', '293', '2018-08-21 09:10:00');
INSERT INTO `dashboard_app_config` VALUES ('584', '3', '103', '1', '299', '2018-08-21 09:10:20');
INSERT INTO `dashboard_app_config` VALUES ('585', '3', '131', '1', '300', '2018-08-21 09:10:20');
INSERT INTO `dashboard_app_config` VALUES ('592', '3', '103', '1', '306', '2018-08-21 09:12:16');
INSERT INTO `dashboard_app_config` VALUES ('593', '3', '131', '1', '307', '2018-08-21 09:12:16');
INSERT INTO `dashboard_app_config` VALUES ('599', '3', '103', '1', '313', '2018-08-21 09:12:30');
INSERT INTO `dashboard_app_config` VALUES ('600', '3', '131', '1', '314', '2018-08-21 09:12:30');
INSERT INTO `dashboard_app_config` VALUES ('605', '3', '103', '1', '319', '2018-08-21 09:18:21');
INSERT INTO `dashboard_app_config` VALUES ('606', '3', '131', '1', '320', '2018-08-21 09:18:21');
INSERT INTO `dashboard_app_config` VALUES ('609', '3', '103', '1', '323', '2018-08-21 09:21:13');
INSERT INTO `dashboard_app_config` VALUES ('610', '3', '131', '1', '324', '2018-08-21 09:21:13');
INSERT INTO `dashboard_app_config` VALUES ('614', '3', '103', '1', '328', '2018-08-21 09:21:41');
INSERT INTO `dashboard_app_config` VALUES ('615', '3', '131', '1', '329', '2018-08-21 09:21:41');
INSERT INTO `dashboard_app_config` VALUES ('619', '3', '103', '1', '331', '2018-08-21 09:23:13');
INSERT INTO `dashboard_app_config` VALUES ('620', '3', '131', '1', '332', '2018-08-21 09:23:13');
INSERT INTO `dashboard_app_config` VALUES ('623', '3', '103', '1', '335', '2018-08-21 09:28:09');
INSERT INTO `dashboard_app_config` VALUES ('624', '3', '131', '1', '336', '2018-08-21 09:28:09');
INSERT INTO `dashboard_app_config` VALUES ('628', '3', '103', '1', '339', '2018-08-21 09:30:36');
INSERT INTO `dashboard_app_config` VALUES ('629', '3', '131', '1', '340', '2018-08-21 09:30:36');
INSERT INTO `dashboard_app_config` VALUES ('636', '3', '103', '1', '341', '2018-08-21 09:40:54');
INSERT INTO `dashboard_app_config` VALUES ('637', '3', '131', '1', '342', '2018-08-21 09:40:54');
INSERT INTO `dashboard_app_config` VALUES ('638', '3', '103', '1', '343', '2018-08-21 09:45:30');
INSERT INTO `dashboard_app_config` VALUES ('639', '3', '131', '1', '344', '2018-08-21 09:45:30');
INSERT INTO `dashboard_app_config` VALUES ('641', '3', '103', '1', '346', '2018-08-21 09:58:20');
INSERT INTO `dashboard_app_config` VALUES ('642', '3', '131', '1', '347', '2018-08-21 09:58:20');
INSERT INTO `dashboard_app_config` VALUES ('645', '3', '103', '1', '350', '2018-08-21 10:02:36');
INSERT INTO `dashboard_app_config` VALUES ('646', '3', '131', '1', '351', '2018-08-21 10:02:36');
INSERT INTO `dashboard_app_config` VALUES ('651', '3', '103', '1', '353', '2018-08-21 10:19:28');
INSERT INTO `dashboard_app_config` VALUES ('652', '3', '131', '1', '354', '2018-08-21 10:19:28');
INSERT INTO `dashboard_app_config` VALUES ('654', '3', '103', '1', '356', '2018-08-21 10:21:43');
INSERT INTO `dashboard_app_config` VALUES ('655', '3', '131', '1', '357', '2018-08-21 10:21:43');
INSERT INTO `dashboard_app_config` VALUES ('662', '3', '103', '1', '364', '2018-08-21 10:22:37');
INSERT INTO `dashboard_app_config` VALUES ('663', '3', '131', '1', '365', '2018-08-21 10:22:37');
INSERT INTO `dashboard_app_config` VALUES ('672', '3', '103', '1', '370', '2018-08-21 10:34:30');
INSERT INTO `dashboard_app_config` VALUES ('673', '3', '131', '1', '371', '2018-08-21 10:34:30');
INSERT INTO `dashboard_app_config` VALUES ('677', '3', '103', '1', '375', '2018-08-21 10:35:55');
INSERT INTO `dashboard_app_config` VALUES ('678', '3', '131', '1', '376', '2018-08-21 10:35:55');
INSERT INTO `dashboard_app_config` VALUES ('684', '3', '103', '1', '381', '2018-08-21 10:48:38');
INSERT INTO `dashboard_app_config` VALUES ('685', '3', '131', '1', '382', '2018-08-21 10:48:38');
INSERT INTO `dashboard_app_config` VALUES ('689', '3', '103', '1', '386', '2018-08-21 10:50:34');
INSERT INTO `dashboard_app_config` VALUES ('690', '3', '131', '1', '387', '2018-08-21 10:50:34');
INSERT INTO `dashboard_app_config` VALUES ('695', '3', '103', '1', '392', '2018-08-21 10:50:53');
INSERT INTO `dashboard_app_config` VALUES ('696', '3', '131', '1', '393', '2018-08-21 10:50:53');
INSERT INTO `dashboard_app_config` VALUES ('702', '3', '103', '1', '395', '2018-08-21 10:58:16');
INSERT INTO `dashboard_app_config` VALUES ('703', '3', '131', '1', '396', '2018-08-21 10:58:16');
INSERT INTO `dashboard_app_config` VALUES ('705', '3', '103', '1', '397', '2018-08-21 10:58:43');
INSERT INTO `dashboard_app_config` VALUES ('706', '3', '131', '1', '398', '2018-08-21 10:58:43');
INSERT INTO `dashboard_app_config` VALUES ('709', '3', '103', '1', '399', '2018-08-21 11:01:18');
INSERT INTO `dashboard_app_config` VALUES ('710', '3', '131', '1', '400', '2018-08-21 11:01:18');
INSERT INTO `dashboard_app_config` VALUES ('711', '3', '102', '1', '401', '2018-08-21 11:01:18');
INSERT INTO `dashboard_app_config` VALUES ('714', '3', '103', '1', '404', '2018-08-21 13:43:10');
INSERT INTO `dashboard_app_config` VALUES ('715', '3', '131', '1', '405', '2018-08-21 13:43:10');
INSERT INTO `dashboard_app_config` VALUES ('716', '3', '43', '1', '406', '2018-08-21 13:43:10');
INSERT INTO `dashboard_app_config` VALUES ('717', '3', '102', '1', '407', '2018-08-21 13:43:10');
INSERT INTO `dashboard_app_config` VALUES ('720', '3', '103', '1', '410', '2018-08-21 14:26:54');
INSERT INTO `dashboard_app_config` VALUES ('721', '3', '131', '1', '411', '2018-08-21 14:26:54');
INSERT INTO `dashboard_app_config` VALUES ('722', '3', '43', '1', '412', '2018-08-21 14:26:54');
INSERT INTO `dashboard_app_config` VALUES ('723', '3', '102', '1', '413', '2018-08-21 14:26:54');
INSERT INTO `dashboard_app_config` VALUES ('727', '3', '103', '1', '417', '2018-08-21 14:31:16');
INSERT INTO `dashboard_app_config` VALUES ('728', '3', '103', '1', '418', '2018-08-21 16:56:47');
INSERT INTO `dashboard_app_config` VALUES ('729', '3', '131', '1', '419', '2018-08-21 16:56:47');
INSERT INTO `dashboard_app_config` VALUES ('730', '3', '43', '1', '420', '2018-08-21 16:56:47');
INSERT INTO `dashboard_app_config` VALUES ('731', '3', '102', '1', '421', '2018-08-21 16:56:47');
INSERT INTO `dashboard_app_config` VALUES ('736', '3', '103', '1', '424', '2018-08-21 16:56:58');
INSERT INTO `dashboard_app_config` VALUES ('737', '3', '131', '1', '425', '2018-08-21 16:56:58');
INSERT INTO `dashboard_app_config` VALUES ('738', '3', '43', '1', '426', '2018-08-21 16:56:58');
INSERT INTO `dashboard_app_config` VALUES ('739', '3', '102', '1', '427', '2018-08-21 16:56:58');
INSERT INTO `dashboard_app_config` VALUES ('742', '3', '103', '1', '428', '2018-08-22 10:02:55');
INSERT INTO `dashboard_app_config` VALUES ('743', '3', '131', '1', '429', '2018-08-22 10:02:55');
INSERT INTO `dashboard_app_config` VALUES ('744', '3', '43', '1', '430', '2018-08-22 10:02:55');
INSERT INTO `dashboard_app_config` VALUES ('745', '3', '102', '1', '431', '2018-08-22 10:02:55');

-- ----------------------------
-- Table structure for dashboard_config
-- ----------------------------
DROP TABLE IF EXISTS `dashboard_config`;
CREATE TABLE `dashboard_config` (
  `config_id` int(11) NOT NULL AUTO_INCREMENT,
  `dashboard_id` int(11) DEFAULT NULL,
  `param_id` int(11) DEFAULT NULL,
  `param_value` varchar(40) DEFAULT NULL,
  `cre_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dashboard_config
-- ----------------------------

-- ----------------------------
-- Table structure for log_type
-- ----------------------------
DROP TABLE IF EXISTS `log_type`;
CREATE TABLE `log_type` (
  `log_type_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '日志类型代码',
  `log_type_name` varchar(40) DEFAULT NULL COMMENT '日志类型名称',
  `cre_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `upd_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`log_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log_type
-- ----------------------------
INSERT INTO `log_type` VALUES ('1', '系统访问日志', null, null);

-- ----------------------------
-- Table structure for menu_privilege
-- ----------------------------
DROP TABLE IF EXISTS `menu_privilege`;
CREATE TABLE `menu_privilege` (
  `pri_id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_id` int(11) NOT NULL,
  `role_id` int(11) DEFAULT NULL,
  `show_on_init` int(11) DEFAULT NULL,
  `show_order` int(11) DEFAULT NULL,
  `cre_time` timestamp NULL DEFAULT NULL,
  `upd_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`pri_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1051 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu_privilege
-- ----------------------------
INSERT INTO `menu_privilege` VALUES ('396', '17', '2', null, null, '2018-04-23 11:53:09', null);
INSERT INTO `menu_privilege` VALUES ('563', '1', '12', '1', '0', '2018-04-23 15:32:06', null);
INSERT INTO `menu_privilege` VALUES ('564', '2', '12', '1', '0', '2018-04-23 15:32:06', null);
INSERT INTO `menu_privilege` VALUES ('565', '8', '12', '1', '0', '2018-04-23 15:32:06', null);
INSERT INTO `menu_privilege` VALUES ('566', '10', '12', '1', '0', '2018-04-23 15:32:06', null);
INSERT INTO `menu_privilege` VALUES ('567', '11', '12', '1', '0', '2018-04-23 15:32:06', null);
INSERT INTO `menu_privilege` VALUES ('568', '12', '12', '1', '0', '2018-04-23 15:32:06', null);
INSERT INTO `menu_privilege` VALUES ('569', '13', '12', '1', '0', '2018-04-23 15:32:06', null);
INSERT INTO `menu_privilege` VALUES ('570', '14', '12', '1', '0', '2018-04-23 15:32:06', null);
INSERT INTO `menu_privilege` VALUES ('571', '16', '12', '1', '0', '2018-04-23 15:32:06', null);
INSERT INTO `menu_privilege` VALUES ('572', '17', '12', '1', '0', '2018-04-23 15:32:06', null);
INSERT INTO `menu_privilege` VALUES ('573', '18', '12', '1', '0', '2018-04-23 15:32:06', null);
INSERT INTO `menu_privilege` VALUES ('574', '3', '12', '1', '0', '2018-04-23 15:32:06', null);
INSERT INTO `menu_privilege` VALUES ('575', '4', '12', '1', '0', '2018-04-23 15:32:06', null);
INSERT INTO `menu_privilege` VALUES ('576', '5', '12', '1', '0', '2018-04-23 15:32:06', null);
INSERT INTO `menu_privilege` VALUES ('577', '6', '12', '1', '0', '2018-04-23 15:32:06', null);
INSERT INTO `menu_privilege` VALUES ('578', '7', '12', '1', '0', '2018-04-23 15:32:06', null);
INSERT INTO `menu_privilege` VALUES ('579', '15', '12', '1', '0', '2018-04-23 15:32:06', null);
INSERT INTO `menu_privilege` VALUES ('580', '20', '12', '1', '0', '2018-04-23 15:32:06', null);
INSERT INTO `menu_privilege` VALUES ('581', '21', '12', '1', '0', '2018-04-23 15:32:06', null);
INSERT INTO `menu_privilege` VALUES ('582', '22', '12', '1', '0', '2018-04-23 15:32:06', null);
INSERT INTO `menu_privilege` VALUES ('583', '23', '12', '1', '0', '2018-04-23 15:32:06', null);
INSERT INTO `menu_privilege` VALUES ('584', '24', '12', '1', '0', '2018-04-23 15:32:06', null);
INSERT INTO `menu_privilege` VALUES ('585', '25', '12', '1', '0', '2018-04-23 15:32:06', null);
INSERT INTO `menu_privilege` VALUES ('586', '26', '12', '1', '0', '2018-04-23 15:32:06', null);
INSERT INTO `menu_privilege` VALUES ('587', '27', '12', '1', '0', '2018-04-23 15:32:06', null);
INSERT INTO `menu_privilege` VALUES ('588', '28', '12', '1', '0', '2018-04-23 15:32:06', null);
INSERT INTO `menu_privilege` VALUES ('589', '29', '12', '1', '0', '2018-04-23 15:32:06', null);
INSERT INTO `menu_privilege` VALUES ('590', '30', '12', '1', '0', '2018-04-23 15:32:06', null);
INSERT INTO `menu_privilege` VALUES ('591', '31', '12', '1', '0', '2018-04-23 15:32:07', null);
INSERT INTO `menu_privilege` VALUES ('592', '32', '12', '1', '0', '2018-04-23 15:32:07', null);
INSERT INTO `menu_privilege` VALUES ('593', '39', '12', '1', '0', '2018-04-23 15:32:07', null);
INSERT INTO `menu_privilege` VALUES ('594', '40', '12', '1', '0', '2018-04-23 15:32:07', null);
INSERT INTO `menu_privilege` VALUES ('595', '1', '10', null, null, '2018-04-23 15:33:23', null);
INSERT INTO `menu_privilege` VALUES ('596', '2', '10', null, null, '2018-04-23 15:33:23', null);
INSERT INTO `menu_privilege` VALUES ('603', '16', '10', null, null, '2018-04-23 15:33:23', null);
INSERT INTO `menu_privilege` VALUES ('604', '17', '10', null, null, '2018-04-23 15:33:23', null);
INSERT INTO `menu_privilege` VALUES ('605', '18', '10', null, null, '2018-04-23 15:33:23', null);
INSERT INTO `menu_privilege` VALUES ('627', '1', '5', null, null, '2018-04-23 15:33:29', null);
INSERT INTO `menu_privilege` VALUES ('628', '2', '5', null, null, '2018-04-23 15:33:29', null);
INSERT INTO `menu_privilege` VALUES ('629', '8', '5', null, null, '2018-04-23 15:33:29', null);
INSERT INTO `menu_privilege` VALUES ('630', '10', '5', null, null, '2018-04-23 15:33:29', null);
INSERT INTO `menu_privilege` VALUES ('631', '11', '5', null, null, '2018-04-23 15:33:29', null);
INSERT INTO `menu_privilege` VALUES ('632', '12', '5', null, null, '2018-04-23 15:33:29', null);
INSERT INTO `menu_privilege` VALUES ('633', '13', '5', null, null, '2018-04-23 15:33:29', null);
INSERT INTO `menu_privilege` VALUES ('634', '14', '5', null, null, '2018-04-23 15:33:29', null);
INSERT INTO `menu_privilege` VALUES ('635', '16', '5', null, null, '2018-04-23 15:33:29', null);
INSERT INTO `menu_privilege` VALUES ('636', '17', '5', null, null, '2018-04-23 15:33:29', null);
INSERT INTO `menu_privilege` VALUES ('637', '18', '5', null, null, '2018-04-23 15:33:29', null);
INSERT INTO `menu_privilege` VALUES ('662', '1', '16', null, null, '2018-04-23 17:37:03', null);
INSERT INTO `menu_privilege` VALUES ('694', '1', '1', null, null, '2018-04-23 17:41:13', null);
INSERT INTO `menu_privilege` VALUES ('695', '2', '1', null, null, '2018-04-23 17:41:13', null);
INSERT INTO `menu_privilege` VALUES ('696', '8', '1', null, null, '2018-04-23 17:41:13', null);
INSERT INTO `menu_privilege` VALUES ('697', '10', '1', null, null, '2018-04-23 17:41:13', null);
INSERT INTO `menu_privilege` VALUES ('698', '11', '1', null, null, '2018-04-23 17:41:13', null);
INSERT INTO `menu_privilege` VALUES ('699', '12', '1', null, null, '2018-04-23 17:41:13', null);
INSERT INTO `menu_privilege` VALUES ('701', '14', '1', null, null, '2018-04-23 17:41:13', null);
INSERT INTO `menu_privilege` VALUES ('702', '16', '1', null, null, '2018-04-23 17:41:13', null);
INSERT INTO `menu_privilege` VALUES ('703', '17', '1', null, null, '2018-04-23 17:41:13', null);
INSERT INTO `menu_privilege` VALUES ('704', '18', '1', null, null, '2018-04-23 17:41:13', null);
INSERT INTO `menu_privilege` VALUES ('726', '50', '1', null, null, '2018-05-17 23:48:59', null);
INSERT INTO `menu_privilege` VALUES ('727', '52', '10', null, null, '2018-05-22 09:57:40', null);
INSERT INTO `menu_privilege` VALUES ('728', '52', '1', null, null, '2018-05-22 14:54:17', null);
INSERT INTO `menu_privilege` VALUES ('729', '45', '1', null, null, '2018-05-25 11:05:27', null);
INSERT INTO `menu_privilege` VALUES ('730', '46', '1', null, null, '2018-05-25 11:05:27', null);
INSERT INTO `menu_privilege` VALUES ('731', '58', '1', null, null, '2018-05-28 17:45:58', null);
INSERT INTO `menu_privilege` VALUES ('733', '59', '1', null, null, '2018-05-28 17:46:11', null);
INSERT INTO `menu_privilege` VALUES ('734', '60', '1', null, null, '2018-05-28 17:46:22', null);
INSERT INTO `menu_privilege` VALUES ('736', '57', '1', null, null, '2018-05-29 08:41:25', null);
INSERT INTO `menu_privilege` VALUES ('737', '13', '1', null, null, '2018-05-29 08:46:44', null);
INSERT INTO `menu_privilege` VALUES ('738', '1', '2', null, null, '2018-05-29 11:53:28', null);
INSERT INTO `menu_privilege` VALUES ('739', '2', '2', null, null, '2018-05-29 11:53:28', null);
INSERT INTO `menu_privilege` VALUES ('740', '8', '2', null, null, '2018-05-29 11:53:28', null);
INSERT INTO `menu_privilege` VALUES ('741', '10', '2', null, null, '2018-05-29 11:53:28', null);
INSERT INTO `menu_privilege` VALUES ('742', '11', '2', null, null, '2018-05-29 11:53:28', null);
INSERT INTO `menu_privilege` VALUES ('743', '12', '2', null, null, '2018-05-29 11:53:28', null);
INSERT INTO `menu_privilege` VALUES ('744', '13', '2', null, null, '2018-05-29 11:53:28', null);
INSERT INTO `menu_privilege` VALUES ('745', '14', '2', null, null, '2018-05-29 11:53:28', null);
INSERT INTO `menu_privilege` VALUES ('746', '16', '2', null, null, '2018-05-29 11:53:28', null);
INSERT INTO `menu_privilege` VALUES ('747', '18', '2', null, null, '2018-05-29 11:53:28', null);
INSERT INTO `menu_privilege` VALUES ('748', '45', '2', null, null, '2018-05-29 11:53:28', null);
INSERT INTO `menu_privilege` VALUES ('749', '46', '2', null, null, '2018-05-29 11:53:28', null);
INSERT INTO `menu_privilege` VALUES ('750', '50', '2', null, null, '2018-05-29 11:53:28', null);
INSERT INTO `menu_privilege` VALUES ('751', '52', '2', null, null, '2018-05-29 11:53:28', null);
INSERT INTO `menu_privilege` VALUES ('752', '58', '2', null, null, '2018-05-29 11:53:28', null);
INSERT INTO `menu_privilege` VALUES ('753', '59', '2', null, null, '2018-05-29 11:53:28', null);
INSERT INTO `menu_privilege` VALUES ('754', '60', '2', null, null, '2018-05-29 11:53:28', null);
INSERT INTO `menu_privilege` VALUES ('755', '57', '2', null, null, '2018-05-29 11:53:28', null);
INSERT INTO `menu_privilege` VALUES ('809', '69', '1', null, null, '2018-06-04 18:23:49', null);
INSERT INTO `menu_privilege` VALUES ('810', '70', '1', null, null, '2018-06-04 18:23:49', null);
INSERT INTO `menu_privilege` VALUES ('849', '45', '10', null, null, '2018-06-08 13:27:43', null);
INSERT INTO `menu_privilege` VALUES ('850', '46', '10', null, null, '2018-06-08 13:27:43', null);
INSERT INTO `menu_privilege` VALUES ('851', '58', '10', null, null, '2018-06-08 13:27:43', null);
INSERT INTO `menu_privilege` VALUES ('852', '59', '10', null, null, '2018-06-08 13:27:43', null);
INSERT INTO `menu_privilege` VALUES ('853', '60', '10', null, null, '2018-06-08 13:27:43', null);
INSERT INTO `menu_privilege` VALUES ('854', '69', '10', null, null, '2018-06-08 13:27:43', null);
INSERT INTO `menu_privilege` VALUES ('855', '70', '10', null, null, '2018-06-08 13:27:43', null);
INSERT INTO `menu_privilege` VALUES ('856', '57', '10', null, null, '2018-06-08 13:27:43', null);
INSERT INTO `menu_privilege` VALUES ('898', '138', '1', null, null, '2018-06-14 16:15:46', null);
INSERT INTO `menu_privilege` VALUES ('911', '155', '1', null, null, '2018-06-14 16:41:51', null);
INSERT INTO `menu_privilege` VALUES ('925', '167', '1', null, null, '2018-06-15 18:18:50', null);
INSERT INTO `menu_privilege` VALUES ('926', '166', '1', null, null, '2018-06-15 18:18:50', null);
INSERT INTO `menu_privilege` VALUES ('927', '165', '1', null, null, '2018-06-15 18:18:50', null);
INSERT INTO `menu_privilege` VALUES ('928', '164', '1', null, null, '2018-06-15 18:18:50', null);
INSERT INTO `menu_privilege` VALUES ('929', '170', '1', null, null, '2018-06-15 18:18:50', null);
INSERT INTO `menu_privilege` VALUES ('930', '163', '1', null, null, '2018-06-15 18:18:50', null);
INSERT INTO `menu_privilege` VALUES ('931', '169', '1', null, null, '2018-06-15 18:18:50', null);
INSERT INTO `menu_privilege` VALUES ('932', '162', '1', null, null, '2018-06-15 18:18:50', null);
INSERT INTO `menu_privilege` VALUES ('933', '168', '1', null, null, '2018-06-15 18:18:50', null);
INSERT INTO `menu_privilege` VALUES ('934', '5', '1', null, null, '2018-06-15 18:22:25', null);
INSERT INTO `menu_privilege` VALUES ('935', '103', '1', null, null, '2018-06-15 18:22:25', null);
INSERT INTO `menu_privilege` VALUES ('936', '104', '1', null, null, '2018-06-15 18:22:25', null);
INSERT INTO `menu_privilege` VALUES ('937', '156', '1', null, null, '2018-06-15 18:22:25', null);
INSERT INTO `menu_privilege` VALUES ('938', '145', '1', null, null, '2018-06-15 18:22:25', null);
INSERT INTO `menu_privilege` VALUES ('939', '146', '1', null, null, '2018-06-15 18:22:25', null);
INSERT INTO `menu_privilege` VALUES ('940', '147', '1', null, null, '2018-06-15 18:22:25', null);
INSERT INTO `menu_privilege` VALUES ('941', '152', '1', null, null, '2018-06-15 18:22:25', null);
INSERT INTO `menu_privilege` VALUES ('942', '154', '1', null, null, '2018-06-15 18:22:25', null);
INSERT INTO `menu_privilege` VALUES ('943', '159', '1', null, null, '2018-06-15 18:22:25', null);
INSERT INTO `menu_privilege` VALUES ('944', '149', '1', null, null, '2018-06-15 18:22:25', null);
INSERT INTO `menu_privilege` VALUES ('945', '150', '1', null, null, '2018-06-15 18:22:26', null);
INSERT INTO `menu_privilege` VALUES ('946', '151', '1', null, null, '2018-06-15 18:22:26', null);
INSERT INTO `menu_privilege` VALUES ('947', '160', '1', null, null, '2018-06-15 18:22:26', null);
INSERT INTO `menu_privilege` VALUES ('948', '139', '1', null, null, '2018-06-15 18:24:29', null);
INSERT INTO `menu_privilege` VALUES ('949', '141', '1', null, null, '2018-06-15 18:24:29', null);
INSERT INTO `menu_privilege` VALUES ('950', '144', '1', null, null, '2018-06-15 18:24:29', null);
INSERT INTO `menu_privilege` VALUES ('951', '161', '1', null, null, '2018-06-15 18:24:29', null);
INSERT INTO `menu_privilege` VALUES ('952', '177', '1', null, null, '2018-06-15 19:31:13', null);
INSERT INTO `menu_privilege` VALUES ('953', '178', '1', null, null, '2018-06-15 19:31:13', null);
INSERT INTO `menu_privilege` VALUES ('954', '1', '4', null, null, '2018-06-19 08:23:12', null);
INSERT INTO `menu_privilege` VALUES ('955', '167', '4', null, null, '2018-06-19 08:23:12', null);
INSERT INTO `menu_privilege` VALUES ('956', '152', '4', null, null, '2018-06-19 08:23:12', null);
INSERT INTO `menu_privilege` VALUES ('957', '166', '4', null, null, '2018-06-19 08:23:12', null);
INSERT INTO `menu_privilege` VALUES ('958', '165', '4', null, null, '2018-06-19 08:23:12', null);
INSERT INTO `menu_privilege` VALUES ('960', '164', '4', null, null, '2018-06-19 08:23:12', null);
INSERT INTO `menu_privilege` VALUES ('961', '170', '4', null, null, '2018-06-19 08:23:12', null);
INSERT INTO `menu_privilege` VALUES ('962', '163', '4', null, null, '2018-06-19 08:23:12', null);
INSERT INTO `menu_privilege` VALUES ('963', '169', '4', null, null, '2018-06-19 08:23:12', null);
INSERT INTO `menu_privilege` VALUES ('964', '155', '4', null, null, '2018-06-19 08:23:12', null);
INSERT INTO `menu_privilege` VALUES ('965', '162', '4', null, null, '2018-06-19 08:23:12', null);
INSERT INTO `menu_privilege` VALUES ('966', '168', '4', null, null, '2018-06-19 08:23:12', null);
INSERT INTO `menu_privilege` VALUES ('967', '154', '4', null, null, '2018-06-19 08:23:12', null);
INSERT INTO `menu_privilege` VALUES ('968', '178', '4', null, null, '2018-06-19 08:23:12', null);
INSERT INTO `menu_privilege` VALUES ('969', '177', '4', null, null, '2018-06-19 08:23:12', null);
INSERT INTO `menu_privilege` VALUES ('971', '168', '5', null, null, '2018-06-19 08:24:04', null);
INSERT INTO `menu_privilege` VALUES ('972', '167', '5', null, null, '2018-06-19 08:24:04', null);
INSERT INTO `menu_privilege` VALUES ('973', '152', '5', null, null, '2018-06-19 08:24:04', null);
INSERT INTO `menu_privilege` VALUES ('974', '166', '5', null, null, '2018-06-19 08:24:04', null);
INSERT INTO `menu_privilege` VALUES ('975', '165', '5', null, null, '2018-06-19 08:24:04', null);
INSERT INTO `menu_privilege` VALUES ('977', '164', '5', null, null, '2018-06-19 08:24:04', null);
INSERT INTO `menu_privilege` VALUES ('978', '170', '5', null, null, '2018-06-19 08:24:04', null);
INSERT INTO `menu_privilege` VALUES ('979', '163', '5', null, null, '2018-06-19 08:24:04', null);
INSERT INTO `menu_privilege` VALUES ('980', '169', '5', null, null, '2018-06-19 08:24:04', null);
INSERT INTO `menu_privilege` VALUES ('981', '155', '5', null, null, '2018-06-19 08:24:04', null);
INSERT INTO `menu_privilege` VALUES ('982', '162', '5', null, null, '2018-06-19 08:24:04', null);
INSERT INTO `menu_privilege` VALUES ('997', '180', '1', null, null, '2018-06-19 12:15:40', null);
INSERT INTO `menu_privilege` VALUES ('1004', '184', '4', '1', '2', '2018-06-27 11:06:39', null);
INSERT INTO `menu_privilege` VALUES ('1005', '183', '4', '1', '3', '2018-06-27 11:06:39', null);
INSERT INTO `menu_privilege` VALUES ('1016', '187', '1', null, null, '2018-06-29 17:24:11', null);
INSERT INTO `menu_privilege` VALUES ('1022', '192', '1', '1', '2', '2018-07-05 10:34:07', null);
INSERT INTO `menu_privilege` VALUES ('1023', '191', '1', '1', '1', '2018-07-05 10:34:07', null);
INSERT INTO `menu_privilege` VALUES ('1024', '192', '3', '1', '2', '2018-07-05 10:48:02', null);
INSERT INTO `menu_privilege` VALUES ('1025', '191', '3', '1', '1', '2018-07-05 10:48:02', null);
INSERT INTO `menu_privilege` VALUES ('1026', '191', '16', '1', '1', '2018-07-19 09:56:31', null);
INSERT INTO `menu_privilege` VALUES ('1027', '192', '2', '1', '1', '2018-07-27 15:12:56', null);
INSERT INTO `menu_privilege` VALUES ('1028', '191', '2', '1', '2', '2018-07-27 15:12:56', null);
INSERT INTO `menu_privilege` VALUES ('1031', '192', '5', '1', '3', '2018-07-27 15:28:17', null);
INSERT INTO `menu_privilege` VALUES ('1032', '191', '5', '1', '2', '2018-07-27 15:28:17', null);
INSERT INTO `menu_privilege` VALUES ('1034', '1', '108', null, null, '2018-07-31 09:39:18', null);
INSERT INTO `menu_privilege` VALUES ('1035', '159', '108', null, null, '2018-07-31 09:39:18', null);
INSERT INTO `menu_privilege` VALUES ('1036', '149', '108', null, null, '2018-07-31 09:39:18', null);
INSERT INTO `menu_privilege` VALUES ('1037', '150', '108', null, null, '2018-07-31 09:39:18', null);
INSERT INTO `menu_privilege` VALUES ('1038', '151', '108', null, null, '2018-07-31 09:39:18', null);
INSERT INTO `menu_privilege` VALUES ('1039', '160', '108', null, null, '2018-07-31 09:39:18', null);
INSERT INTO `menu_privilege` VALUES ('1041', '192', '10', '1', '1', '2018-07-31 10:37:44', null);
INSERT INTO `menu_privilege` VALUES ('1042', '191', '10', '1', '2', '2018-07-31 10:37:44', null);
INSERT INTO `menu_privilege` VALUES ('1043', '1', '109', null, null, '2018-07-31 17:08:38', null);
INSERT INTO `menu_privilege` VALUES ('1044', '138', '109', null, null, '2018-07-31 17:08:38', null);
INSERT INTO `menu_privilege` VALUES ('1045', '139', '109', null, null, '2018-07-31 17:08:38', null);
INSERT INTO `menu_privilege` VALUES ('1046', '141', '109', null, null, '2018-07-31 17:08:38', null);
INSERT INTO `menu_privilege` VALUES ('1047', '144', '109', null, null, '2018-07-31 17:08:38', null);
INSERT INTO `menu_privilege` VALUES ('1048', '161', '109', null, null, '2018-07-31 17:08:38', null);
INSERT INTO `menu_privilege` VALUES ('1049', '194', '1', null, null, '2018-08-14 17:15:20', null);
INSERT INTO `menu_privilege` VALUES ('1050', '195', '1', '1', '3', '2018-08-14 17:16:34', null);

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `msg_id` int(11) NOT NULL AUTO_INCREMENT,
  `msg_title` varchar(60) DEFAULT NULL,
  `msg_src_id` int(11) DEFAULT NULL,
  `src_msg_id` varchar(20) DEFAULT NULL,
  `msg_type_id` int(11) DEFAULT NULL,
  `msg_imprt_id` int(11) DEFAULT NULL,
  `msg_class_id` int(11) DEFAULT NULL,
  `pub_user_id` int(11) DEFAULT NULL,
  `appr_user_id` int(11) DEFAULT NULL,
  `msg_digest` varchar(800) DEFAULT NULL,
  `msg_content` text,
  `msg_attachment` varchar(200) DEFAULT NULL,
  `appr_state` int(11) DEFAULT NULL,
  `pub_time` timestamp NULL DEFAULT NULL,
  `appr_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`msg_id`)
) ENGINE=InnoDB AUTO_INCREMENT=220 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('190', '北京城市副中心控制性详细规划揭开首都发展新篇章 ', '2', null, null, null, '2', '1', '1', '北京城市副中心控制性详细规划揭开首都发展新篇章 ', '/home/portal/files/mesPublish/20180622/content/北京城市副中心控制性详细规划揭开首都发展新篇章 .txt', '/home/portal/files/mesPublish/20180622/attachment/北京城市大数据平台及领导驾驶舱项目_单元测试记录及缺陷跟踪表_领导驾驶舱统一管理系统_v0.2_20180420.xlsx', '3', '2018-07-12 13:56:28', '2018-07-12 13:56:28');
INSERT INTO `message` VALUES ('192', '市委常委会召开会议 委书记蔡奇主持会议', '2', null, null, null, '2', '1', '1', '市委常委会召开会议 研究北京市推进京津冀协同发展二〇一八—二〇二〇年行动计划等事项 市委书记蔡奇主持会议', '/home/portal/files/mesPublish/20180622/content/市委常委会召开会议 委书记蔡奇主持会议.txt', '/home/portal/files/mesPublish/20180622/attachment/top.jsp', '3', '2018-06-26 15:08:47', '2018-06-26 15:08:47');
INSERT INTO `message` VALUES ('194', '个税起征点拟调至每月5000元', '2', null, null, null, '1', '1', '1', '个人所得税法修正案草案提请十三届全国人大常委会第三次会议审议。', '/home/portal/files/mesPublish/20180622/content/个税起征点拟调至每月5000元.txt', '/home/portal/files/mesPublish/20180625/attachment/excel.txt', '3', '2018-06-26 16:10:51', '2018-06-26 16:10:51');
INSERT INTO `message` VALUES ('206', '北京市2018年1月-6月财政收支情况', '2', null, null, null, '2', '1', null, '海外网7月12日电 当地时间11日，美国参议院以88：11的压倒性票数通过了一项非约束性的决议，该决议能够确保在特朗普采取关税行动时，美国国会能够在其中发挥一定作用。', '/home/portal/files/mesPublish/20180712/content/北京市2018年1月-6月财政收支情况.txt', '/home/portal/files/mesPublish/20180712/attachment/earth.json', '3', '2018-07-12 16:43:34', '2018-07-12 16:43:34');
INSERT INTO `message` VALUES ('219', '北京回龙观天通苑地区三年行动计划通过 200亿修补更新', '0', null, '5', null, null, null, null, null, '/home/portal/files/material/20180726/北京回龙观天通苑地区三年行动计划通过 200亿修补更新.txt', null, null, '2018-07-26 10:59:36', '2018-07-26 10:59:36');

-- ----------------------------
-- Table structure for metric
-- ----------------------------
DROP TABLE IF EXISTS `metric`;
CREATE TABLE `metric` (
  `metric_id` int(11) NOT NULL AUTO_INCREMENT,
  `metric_name` varchar(80) DEFAULT NULL,
  `metric_src_id` int(11) DEFAULT NULL,
  `src_metric_id` varchar(20) DEFAULT NULL,
  `src_metric_sup_id` varchar(20) DEFAULT NULL,
  `metric_url` varchar(200) DEFAULT NULL,
  `appr_state` int(11) DEFAULT NULL,
  `pub_time` timestamp NULL DEFAULT NULL,
  `appr_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`metric_id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of metric
-- ----------------------------
INSERT INTO `metric` VALUES ('1', '行政区划', null, 'C20000', 'root', null, '1', '2018-08-01 09:41:54', '2018-08-01 09:41:54');
INSERT INTO `metric` VALUES ('2', '街道办事处', null, 'C20001', 'C20000', null, '1', '2018-08-01 09:41:57', '2018-08-01 09:41:57');
INSERT INTO `metric` VALUES ('3', '建制镇', null, 'C20002', 'C20000', null, '1', '2018-08-01 09:41:58', '2018-08-01 09:41:58');
INSERT INTO `metric` VALUES ('4', '建制乡', null, 'C20003', 'C20000', null, '1', '2018-08-01 09:41:58', '2018-08-01 09:41:58');
INSERT INTO `metric` VALUES ('5', '社区居委会', null, 'C20004', 'C20000', null, '1', '2018-08-01 09:41:59', '2018-08-01 09:41:59');
INSERT INTO `metric` VALUES ('6', '村民委员会', null, 'C20005', 'C20000', null, '1', '2018-08-01 09:41:59', '2018-08-01 09:41:59');
INSERT INTO `metric` VALUES ('7', '主要年份国民经济和社会发展总量与速度指标', null, 'C60000', 'root', null, '1', '2018-08-01 09:42:01', '2018-08-01 09:42:01');
INSERT INTO `metric` VALUES ('8', '总量指标', null, 'C60001', 'C60000', null, '1', '2018-08-01 09:42:01', '2018-08-01 09:42:01');
INSERT INTO `metric` VALUES ('9', '速度指标', null, 'C60002', 'C60000', null, '1', '2018-08-01 09:42:01', '2018-08-01 09:42:01');
INSERT INTO `metric` VALUES ('10', '国民经济各行业规模（限额）以上法人单位情况', null, 'C40000', 'root', null, '1', '2018-08-01 09:42:02', '2018-08-01 09:42:02');
INSERT INTO `metric` VALUES ('11', '单产业法人', null, 'C40002', 'C40000', null, '1', '2018-08-01 09:42:03', '2018-08-01 09:42:03');
INSERT INTO `metric` VALUES ('12', '多产业法人', null, 'C40003', 'C40000', null, '1', '2018-08-01 09:42:03', '2018-08-01 09:42:03');
INSERT INTO `metric` VALUES ('13', '法人单位数', null, 'C40001', 'C40000', null, '1', '2018-08-01 09:42:03', '2018-08-01 09:42:03');
INSERT INTO `metric` VALUES ('14', '全市私营个体经济基本情况', null, 'C50000', 'root', null, '1', '2018-08-01 09:42:05', '2018-08-01 09:42:05');
INSERT INTO `metric` VALUES ('15', '私营', null, 'C50001', 'C50000', null, '1', '2018-08-01 09:42:06', '2018-08-01 09:42:06');
INSERT INTO `metric` VALUES ('16', '个体', null, 'C50003', 'C50000', null, '1', '2018-08-01 09:42:07', '2018-08-01 09:42:07');
INSERT INTO `metric` VALUES ('17', '私营增长率', null, 'C50002', 'C50000', null, '1', '2018-08-01 09:42:07', '2018-08-01 09:42:07');
INSERT INTO `metric` VALUES ('18', '个体增长率', null, 'C50004', 'C50000', null, '1', '2018-08-01 09:42:07', '2018-08-01 09:42:07');
INSERT INTO `metric` VALUES ('19', '规模（限额）以上法人单位基本情况', null, 'C30000', 'root', null, '1', '2018-08-01 09:42:14', '2018-08-01 09:42:14');
INSERT INTO `metric` VALUES ('20', '多产业法人数', null, 'C30003', 'C30000', null, '1', '2018-08-01 09:42:15', '2018-08-01 09:42:15');
INSERT INTO `metric` VALUES ('21', '法人单位数', null, 'C30001', 'C30000', null, '1', '2018-08-01 09:42:16', '2018-08-01 09:42:16');
INSERT INTO `metric` VALUES ('22', '单产业法人数', null, 'C30002', 'C30000', null, '1', '2018-08-01 09:42:17', '2018-08-01 09:42:17');
INSERT INTO `metric` VALUES ('23', '市区经济各行业规模（限额）以上法人单位情况', null, 'C70000', 'root', null, '1', '2018-06-14 13:48:49', '2018-06-14 13:48:49');
INSERT INTO `metric` VALUES ('24', '街道办事处占比', null, 'C20007', 'C20000', null, '1', '2018-08-01 09:41:55', '2018-08-01 09:41:55');
INSERT INTO `metric` VALUES ('25', '建制镇占比', null, 'C20008', 'C20000', null, '1', '2018-08-01 09:41:56', '2018-08-01 09:41:56');
INSERT INTO `metric` VALUES ('26', '建制乡占比', null, 'C20009', 'C20000', null, '1', '2018-08-01 09:41:56', '2018-08-01 09:41:56');
INSERT INTO `metric` VALUES ('27', '社区居委会占比', null, 'C20010', 'C20000', null, '1', '2018-08-01 09:41:56', '2018-08-01 09:41:56');
INSERT INTO `metric` VALUES ('28', '法人单位总数', null, 'C20006', 'C20000', null, '1', '2018-08-01 09:42:00', '2018-08-01 09:42:00');
INSERT INTO `metric` VALUES ('29', '村民委员会占比', null, 'C20011', 'C20000', null, '1', '2018-08-01 09:42:00', '2018-08-01 09:42:00');
INSERT INTO `metric` VALUES ('30', '单产业法人单位数占比', null, 'C40004', 'C40000', null, '1', '2018-08-01 09:42:04', '2018-08-01 09:42:04');
INSERT INTO `metric` VALUES ('31', '多产业法人单位数占比', null, 'C40005', 'C40000', null, '1', '2018-08-01 09:42:04', '2018-08-01 09:42:04');
INSERT INTO `metric` VALUES ('32', '私营增长量', null, 'C50005', 'C50000', null, '1', '2018-08-01 09:42:05', '2018-08-01 09:42:05');
INSERT INTO `metric` VALUES ('33', '个体增长量', null, 'C50006', 'C50000', null, '1', '2018-08-01 09:42:06', '2018-08-01 09:42:06');
INSERT INTO `metric` VALUES ('34', '单产业法人数占比', null, 'C30004', 'C30000', null, '1', '2018-08-01 09:42:15', '2018-08-01 09:42:15');
INSERT INTO `metric` VALUES ('35', '多产业法人数占比', null, 'C30005', 'C30000', null, '1', '2018-08-01 09:42:16', '2018-08-01 09:42:16');
INSERT INTO `metric` VALUES ('36', '法人单位数', null, 'C70001', 'C70000', null, '1', '2018-06-14 13:48:50', '2018-06-14 13:48:50');
INSERT INTO `metric` VALUES ('37', '单产业法人', null, 'C70002', 'C70000', null, '1', '2018-06-14 13:48:51', '2018-06-14 13:48:51');
INSERT INTO `metric` VALUES ('38', '测试指标', null, 'C30006', 'C30000', null, '1', '2018-06-14 13:48:46', '2018-06-14 13:48:46');
INSERT INTO `metric` VALUES ('39', '主要经济指标增长速度', null, 'C80000', 'root', null, '1', '2018-08-01 09:42:08', '2018-08-01 09:42:08');
INSERT INTO `metric` VALUES ('40', '进出口总值比上年增长', null, 'C80005', 'C80000', null, '1', '2018-08-01 09:42:08', '2018-08-01 09:42:08');
INSERT INTO `metric` VALUES ('41', '房地产开发投资比上年增长', null, 'C80003', 'C80000', null, '1', '2018-08-01 09:42:09', '2018-08-01 09:42:09');
INSERT INTO `metric` VALUES ('42', '农林牧渔业总产值比上年增长', null, 'C80006', 'C80000', null, '1', '2018-08-01 09:42:10', '2018-08-01 09:42:10');
INSERT INTO `metric` VALUES ('43', '规模以上工业总产值比上年增长', null, 'C80007', 'C80000', null, '1', '2018-08-01 09:42:10', '2018-08-01 09:42:10');
INSERT INTO `metric` VALUES ('44', '居民消费价格指数', null, 'C80008', 'C80000', null, '1', '2018-08-01 09:42:11', '2018-08-01 09:42:11');
INSERT INTO `metric` VALUES ('45', '城镇居民人均可支配收入比上年名义增长', null, 'C80009', 'C80000', null, '1', '2018-08-01 09:42:11', '2018-08-01 09:42:11');
INSERT INTO `metric` VALUES ('46', '城镇居民人均可支配收入比上年实际增长', null, 'C80010', 'C80000', null, '1', '2018-08-01 09:42:12', '2018-08-01 09:42:12');
INSERT INTO `metric` VALUES ('47', '农村居民人均可支配收入比上年名义增长', null, 'C80011', 'C80000', null, '1', '2018-08-01 09:42:12', '2018-08-01 09:42:12');
INSERT INTO `metric` VALUES ('48', '农村居民人均可支配收入比上年实际名义增长', null, 'C80012', 'C80000', null, '1', '2018-08-01 09:42:12', '2018-08-01 09:42:12');
INSERT INTO `metric` VALUES ('49', '地区生产总值比上年增长', null, 'C80001', 'C80000', null, '1', '2018-08-01 09:42:13', '2018-08-01 09:42:13');
INSERT INTO `metric` VALUES ('50', '全社会固定资产投资比上年增长', null, 'C80002', 'C80000', null, '1', '2018-08-01 09:42:13', '2018-08-01 09:42:13');
INSERT INTO `metric` VALUES ('51', '社会消费品零售总额比上年增长', null, 'C80004', 'C80000', null, '1', '2018-08-01 09:42:13', '2018-08-01 09:42:13');
INSERT INTO `metric` VALUES ('52', 'TTDT', null, 'C80013', 'C80000', null, '1', '2018-08-01 09:42:08', '2018-08-01 09:42:08');
INSERT INTO `metric` VALUES ('53', '京滨工业园', null, 'J10000', 'root', null, '1', '2018-08-01 09:42:17', '2018-08-01 09:42:17');
INSERT INTO `metric` VALUES ('54', '园区基本情况', null, 'J10001', 'J10000', null, '1', '2018-08-01 09:42:18', '2018-08-01 09:42:18');
INSERT INTO `metric` VALUES ('55', '招商引资分析', null, 'J10002', 'J10000', null, '1', '2018-08-01 09:42:18', '2018-08-01 09:42:18');
INSERT INTO `metric` VALUES ('56', '园区贸易分析', null, 'J10003', 'J10000', null, '1', '2018-08-01 09:42:19', '2018-08-01 09:42:19');

-- ----------------------------
-- Table structure for metric_source
-- ----------------------------
DROP TABLE IF EXISTS `metric_source`;
CREATE TABLE `metric_source` (
  `metric_src_id` int(11) NOT NULL,
  `metric_src_url` varchar(200) DEFAULT NULL,
  `metric_src_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`metric_src_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of metric_source
-- ----------------------------

-- ----------------------------
-- Table structure for msg_classify
-- ----------------------------
DROP TABLE IF EXISTS `msg_classify`;
CREATE TABLE `msg_classify` (
  `msg_class_id` int(11) NOT NULL,
  `msg_class_name` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`msg_class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of msg_classify
-- ----------------------------
INSERT INTO `msg_classify` VALUES ('1', '机密');
INSERT INTO `msg_classify` VALUES ('2', '公开');

-- ----------------------------
-- Table structure for msg_importance
-- ----------------------------
DROP TABLE IF EXISTS `msg_importance`;
CREATE TABLE `msg_importance` (
  `msg_imprt_id` int(11) NOT NULL,
  `msg_imprt_name` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`msg_imprt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of msg_importance
-- ----------------------------

-- ----------------------------
-- Table structure for msg_source
-- ----------------------------
DROP TABLE IF EXISTS `msg_source`;
CREATE TABLE `msg_source` (
  `msg_src_id` int(11) NOT NULL,
  `msg_src_name` varchar(20) DEFAULT NULL,
  `msg_src_url` varchar(200) DEFAULT NULL,
  `msg_src_type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`msg_src_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of msg_source
-- ----------------------------
INSERT INTO `msg_source` VALUES ('0', '内部资料库', null, '0');
INSERT INTO `msg_source` VALUES ('1', '新华社', 'http://cpc.people.com.cn/n1/2018/0505/c64094-29966415.html', '1');
INSERT INTO `msg_source` VALUES ('2', '平台内部发布', null, '0');

-- ----------------------------
-- Table structure for msg_src_privilege
-- ----------------------------
DROP TABLE IF EXISTS `msg_src_privilege`;
CREATE TABLE `msg_src_privilege` (
  `privi_id` int(11) NOT NULL,
  `role_id` int(11) DEFAULT NULL,
  `msg_src_id` int(11) DEFAULT NULL,
  `msg_type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`privi_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of msg_src_privilege
-- ----------------------------

-- ----------------------------
-- Table structure for msg_src_type
-- ----------------------------
DROP TABLE IF EXISTS `msg_src_type`;
CREATE TABLE `msg_src_type` (
  `msg_src_type_id` int(11) NOT NULL,
  `msg_src_type_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`msg_src_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of msg_src_type
-- ----------------------------
INSERT INTO `msg_src_type` VALUES ('0', '内部信息源');
INSERT INTO `msg_src_type` VALUES ('1', '外部信息源');

-- ----------------------------
-- Table structure for msg_tag
-- ----------------------------
DROP TABLE IF EXISTS `msg_tag`;
CREATE TABLE `msg_tag` (
  `msg_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of msg_tag
-- ----------------------------
INSERT INTO `msg_tag` VALUES ('190', '1');
INSERT INTO `msg_tag` VALUES ('192', '1');
INSERT INTO `msg_tag` VALUES ('194', '8');
INSERT INTO `msg_tag` VALUES ('206', '1');
INSERT INTO `msg_tag` VALUES ('219', '27');

-- ----------------------------
-- Table structure for msg_type
-- ----------------------------
DROP TABLE IF EXISTS `msg_type`;
CREATE TABLE `msg_type` (
  `msg_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `sup_msg_type_id` int(11) DEFAULT NULL,
  `msg_type_name` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`msg_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of msg_type
-- ----------------------------
INSERT INTO `msg_type` VALUES ('1', '0', '政策文件');
INSERT INTO `msg_type` VALUES ('2', '0', '政府公文');
INSERT INTO `msg_type` VALUES ('3', '0', '大数据分析报告');
INSERT INTO `msg_type` VALUES ('4', '2', '时政');
INSERT INTO `msg_type` VALUES ('5', '1', '内参');
INSERT INTO `msg_type` VALUES ('7', '3', '热点舆情分析');

-- ----------------------------
-- Table structure for object_type
-- ----------------------------
DROP TABLE IF EXISTS `object_type`;
CREATE TABLE `object_type` (
  `object_type_id` int(11) NOT NULL COMMENT '对象类型ID：0-应用系统，1-应用系统页面',
  `object_type_name` varchar(40) DEFAULT NULL COMMENT '对象类型名称',
  PRIMARY KEY (`object_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='对象类型表';

-- ----------------------------
-- Records of object_type
-- ----------------------------
INSERT INTO `object_type` VALUES ('0', 'APP应用系统');
INSERT INTO `object_type` VALUES ('1', 'PC应用系统');

-- ----------------------------
-- Table structure for page
-- ----------------------------
DROP TABLE IF EXISTS `page`;
CREATE TABLE `page` (
  `page_id` int(11) NOT NULL AUTO_INCREMENT,
  `template_id` int(11) DEFAULT NULL,
  `page_name` varchar(60) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `cre_time` timestamp NULL DEFAULT NULL,
  `upd_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`page_id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of page
-- ----------------------------
INSERT INTO `page` VALUES ('5', '1', 'abc', '1', '2018-05-29 09:18:25', '2018-06-05 17:09:18');
INSERT INTO `page` VALUES ('6', '2', '123', '1', '2018-05-30 10:12:59', '2018-05-30 10:13:13');
INSERT INTO `page` VALUES ('7', '4', '城市交通模板1', '1', '2018-06-04 13:54:09', '2018-06-04 13:54:09');
INSERT INTO `page` VALUES ('10', '1', '第二个页面', '1', '2018-06-04 17:55:04', '2018-06-04 17:55:04');
INSERT INTO `page` VALUES ('11', '4', '第三个页面', '10', '2018-06-04 18:38:50', '2018-06-04 18:38:50');
INSERT INTO `page` VALUES ('13', '2', 'V2.0', '31', '2018-06-05 10:30:02', '2018-06-05 10:30:02');
INSERT INTO `page` VALUES ('14', '2', '栏目指标测试图表', '1', '2018-06-06 16:25:42', '2018-06-06 16:27:03');
INSERT INTO `page` VALUES ('15', null, '栏目页面测试图表2', '1', '2018-06-06 16:30:10', '2018-06-07 14:39:14');
INSERT INTO `page` VALUES ('17', null, 'test栏目指标测试页面', '1', '2018-06-07 13:42:06', '2018-06-07 14:07:27');
INSERT INTO `page` VALUES ('18', '2', '栏目指标测试页面3', '10', '2018-06-07 14:09:43', '2018-06-07 14:09:43');
INSERT INTO `page` VALUES ('19', '2', '政务页面', '1', '2018-06-07 14:11:53', '2018-06-07 14:11:53');
INSERT INTO `page` VALUES ('20', '2', '指标应用模板', '1', '2018-06-08 15:08:17', '2018-06-08 15:08:17');
INSERT INTO `page` VALUES ('21', '2', '指标测试', '1', '2018-06-11 11:30:56', '2018-06-13 13:53:55');
INSERT INTO `page` VALUES ('22', null, 'page1', '1', '2018-06-13 13:00:06', '2018-06-13 13:00:06');
INSERT INTO `page` VALUES ('26', '4', '北京城市测试页面', '1', '2018-06-19 08:31:23', '2018-06-19 08:31:23');
INSERT INTO `page` VALUES ('27', '4', '页面1', '1', '2018-06-20 15:50:29', '2018-06-25 09:48:17');
INSERT INTO `page` VALUES ('28', '2', '页面2', '1', '2018-06-20 15:53:20', '2018-06-25 10:02:08');
INSERT INTO `page` VALUES ('29', null, '页面1', '1', '2018-06-25 10:09:38', '2018-06-25 10:12:00');
INSERT INTO `page` VALUES ('31', '2', '页面', '1', '2018-06-27 11:09:49', '2018-07-03 18:15:11');
INSERT INTO `page` VALUES ('32', '4', '页面', '1', '2018-06-27 11:10:33', '2018-06-29 11:12:00');
INSERT INTO `page` VALUES ('33', null, '页面2', '1', '2018-06-27 11:29:23', '2018-07-03 16:43:12');
INSERT INTO `page` VALUES ('34', '1', '布局1', '1', '2018-06-27 11:33:29', '2018-06-27 11:33:29');
INSERT INTO `page` VALUES ('35', '3', '布局3', '1', '2018-06-27 11:34:18', '2018-06-27 11:34:18');
INSERT INTO `page` VALUES ('36', null, '表格', '1', '2018-06-27 11:39:27', '2018-06-27 11:40:17');
INSERT INTO `page` VALUES ('37', '4', '指标配置管理测试应用-页面1', '1', '2018-06-27 13:40:12', '2018-06-27 13:40:12');
INSERT INTO `page` VALUES ('38', null, '配置管理1', '1', '2018-06-29 11:26:45', '2018-06-29 11:29:29');
INSERT INTO `page` VALUES ('39', '2', '配置管理2', '1', '2018-06-29 11:28:08', '2018-06-29 11:28:08');
INSERT INTO `page` VALUES ('40', '5', '版本测试', '1', '2018-07-04 11:32:27', '2018-07-04 11:37:42');
INSERT INTO `page` VALUES ('41', null, '11', '1', '2018-07-04 11:33:16', '2018-07-04 11:33:16');
INSERT INTO `page` VALUES ('42', null, '22', '1', '2018-07-04 11:34:55', '2018-07-04 11:34:55');
INSERT INTO `page` VALUES ('43', '4', '测试0704', '1', '2018-07-04 14:46:59', '2018-07-04 14:46:59');
INSERT INTO `page` VALUES ('44', '4', '业务数据监测', '1', '2018-07-05 09:33:19', '2018-07-05 09:33:19');
INSERT INTO `page` VALUES ('45', '4', '业务监测', '1', '2018-07-05 10:24:53', '2018-07-05 11:31:06');
INSERT INTO `page` VALUES ('46', '1', '数据监测', '1', '2018-07-05 10:28:46', '2018-08-14 14:46:24');
INSERT INTO `page` VALUES ('47', null, 'test', '1', '2018-07-05 10:45:49', '2018-08-08 17:31:30');
INSERT INTO `page` VALUES ('48', '4', '页面1', '1', '2018-07-27 15:21:27', '2018-07-27 15:21:27');
INSERT INTO `page` VALUES ('49', null, '智能应用页面', '1', '2018-08-14 17:10:18', '2018-08-14 17:20:27');

-- ----------------------------
-- Table structure for page_config
-- ----------------------------
DROP TABLE IF EXISTS `page_config`;
CREATE TABLE `page_config` (
  `config_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `page_id` int(11) DEFAULT NULL,
  `param_id` int(11) DEFAULT NULL,
  `param_value` varchar(20) DEFAULT NULL,
  `cre_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of page_config
-- ----------------------------

-- ----------------------------
-- Table structure for page_section
-- ----------------------------
DROP TABLE IF EXISTS `page_section`;
CREATE TABLE `page_section` (
  `page_section_id` int(11) NOT NULL AUTO_INCREMENT,
  `section_id` int(11) NOT NULL,
  `page_id` int(11) NOT NULL,
  `section_idx` int(11) DEFAULT NULL,
  PRIMARY KEY (`page_section_id`)
) ENGINE=InnoDB AUTO_INCREMENT=168 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of page_section
-- ----------------------------
INSERT INTO `page_section` VALUES ('23', '23', '5', '1');
INSERT INTO `page_section` VALUES ('24', '24', '5', '2');
INSERT INTO `page_section` VALUES ('25', '25', '6', '1');
INSERT INTO `page_section` VALUES ('26', '26', '7', '1');
INSERT INTO `page_section` VALUES ('27', '27', '7', '2');
INSERT INTO `page_section` VALUES ('28', '28', '7', '3');
INSERT INTO `page_section` VALUES ('29', '29', '7', '4');
INSERT INTO `page_section` VALUES ('37', '37', '10', '1');
INSERT INTO `page_section` VALUES ('38', '38', '10', '5');
INSERT INTO `page_section` VALUES ('39', '39', '10', '3');
INSERT INTO `page_section` VALUES ('40', '40', '11', '1');
INSERT INTO `page_section` VALUES ('41', '41', '11', '2');
INSERT INTO `page_section` VALUES ('42', '42', '11', '4');
INSERT INTO `page_section` VALUES ('46', '46', '13', '1');
INSERT INTO `page_section` VALUES ('47', '47', '13', '2');
INSERT INTO `page_section` VALUES ('48', '48', '13', '3');
INSERT INTO `page_section` VALUES ('49', '49', '14', '1');
INSERT INTO `page_section` VALUES ('50', '50', '14', '2');
INSERT INTO `page_section` VALUES ('51', '51', '14', '3');
INSERT INTO `page_section` VALUES ('52', '52', '15', '1');
INSERT INTO `page_section` VALUES ('53', '53', '15', '2');
INSERT INTO `page_section` VALUES ('54', '54', '15', '3');
INSERT INTO `page_section` VALUES ('56', '56', '17', '1');
INSERT INTO `page_section` VALUES ('57', '57', '17', '2');
INSERT INTO `page_section` VALUES ('58', '58', '17', '3');
INSERT INTO `page_section` VALUES ('59', '59', '18', '1');
INSERT INTO `page_section` VALUES ('60', '60', '18', '2');
INSERT INTO `page_section` VALUES ('61', '61', '18', '4');
INSERT INTO `page_section` VALUES ('62', '62', '19', '1');
INSERT INTO `page_section` VALUES ('63', '63', '19', '3');
INSERT INTO `page_section` VALUES ('64', '64', '20', '1');
INSERT INTO `page_section` VALUES ('65', '65', '20', '2');
INSERT INTO `page_section` VALUES ('66', '66', '20', '3');
INSERT INTO `page_section` VALUES ('67', '67', '20', '4');
INSERT INTO `page_section` VALUES ('68', '68', '21', '1');
INSERT INTO `page_section` VALUES ('69', '69', '21', '2');
INSERT INTO `page_section` VALUES ('70', '70', '22', '1');
INSERT INTO `page_section` VALUES ('85', '85', '26', '1');
INSERT INTO `page_section` VALUES ('86', '86', '26', '2');
INSERT INTO `page_section` VALUES ('87', '87', '26', '3');
INSERT INTO `page_section` VALUES ('88', '88', '27', '1');
INSERT INTO `page_section` VALUES ('89', '89', '27', '2');
INSERT INTO `page_section` VALUES ('90', '90', '27', '3');
INSERT INTO `page_section` VALUES ('91', '91', '27', '4');
INSERT INTO `page_section` VALUES ('92', '92', '28', '1');
INSERT INTO `page_section` VALUES ('93', '93', '28', '2');
INSERT INTO `page_section` VALUES ('94', '94', '28', '3');
INSERT INTO `page_section` VALUES ('95', '95', '28', '4');
INSERT INTO `page_section` VALUES ('96', '96', '29', '1');
INSERT INTO `page_section` VALUES ('98', '98', '31', '1');
INSERT INTO `page_section` VALUES ('99', '99', '31', '2');
INSERT INTO `page_section` VALUES ('100', '100', '31', '3');
INSERT INTO `page_section` VALUES ('101', '101', '31', '4');
INSERT INTO `page_section` VALUES ('102', '102', '32', '1');
INSERT INTO `page_section` VALUES ('103', '103', '32', '2');
INSERT INTO `page_section` VALUES ('104', '104', '32', '3');
INSERT INTO `page_section` VALUES ('105', '105', '32', '4');
INSERT INTO `page_section` VALUES ('106', '106', '33', '1');
INSERT INTO `page_section` VALUES ('107', '107', '34', '1');
INSERT INTO `page_section` VALUES ('108', '108', '34', '2');
INSERT INTO `page_section` VALUES ('109', '109', '34', '3');
INSERT INTO `page_section` VALUES ('110', '110', '34', '4');
INSERT INTO `page_section` VALUES ('111', '111', '34', '5');
INSERT INTO `page_section` VALUES ('112', '112', '34', '6');
INSERT INTO `page_section` VALUES ('113', '113', '35', '1');
INSERT INTO `page_section` VALUES ('114', '114', '35', '2');
INSERT INTO `page_section` VALUES ('115', '115', '35', '3');
INSERT INTO `page_section` VALUES ('116', '116', '35', '4');
INSERT INTO `page_section` VALUES ('117', '117', '35', '5');
INSERT INTO `page_section` VALUES ('118', '118', '36', '1');
INSERT INTO `page_section` VALUES ('119', '119', '37', '1');
INSERT INTO `page_section` VALUES ('120', '120', '37', '2');
INSERT INTO `page_section` VALUES ('121', '121', '37', '3');
INSERT INTO `page_section` VALUES ('122', '122', '37', '4');
INSERT INTO `page_section` VALUES ('123', '123', '38', '1');
INSERT INTO `page_section` VALUES ('124', '124', '39', '1');
INSERT INTO `page_section` VALUES ('125', '125', '39', '2');
INSERT INTO `page_section` VALUES ('126', '126', '39', '3');
INSERT INTO `page_section` VALUES ('127', '127', '39', '4');
INSERT INTO `page_section` VALUES ('128', '128', '38', '2');
INSERT INTO `page_section` VALUES ('129', '129', '38', '3');
INSERT INTO `page_section` VALUES ('130', '130', '38', '4');
INSERT INTO `page_section` VALUES ('131', '131', '40', '1');
INSERT INTO `page_section` VALUES ('132', '132', '40', '2');
INSERT INTO `page_section` VALUES ('133', '133', '40', '3');
INSERT INTO `page_section` VALUES ('134', '134', '40', '4');
INSERT INTO `page_section` VALUES ('135', '135', '40', '5');
INSERT INTO `page_section` VALUES ('136', '136', '40', '6');
INSERT INTO `page_section` VALUES ('137', '137', '40', '7');
INSERT INTO `page_section` VALUES ('138', '138', '41', '1');
INSERT INTO `page_section` VALUES ('139', '139', '42', '1');
INSERT INTO `page_section` VALUES ('140', '140', '42', '2');
INSERT INTO `page_section` VALUES ('141', '141', '42', '3');
INSERT INTO `page_section` VALUES ('142', '142', '42', '4');
INSERT INTO `page_section` VALUES ('143', '143', '43', '1');
INSERT INTO `page_section` VALUES ('144', '144', '43', '2');
INSERT INTO `page_section` VALUES ('145', '145', '43', '3');
INSERT INTO `page_section` VALUES ('146', '146', '43', '4');
INSERT INTO `page_section` VALUES ('147', '147', '44', '1');
INSERT INTO `page_section` VALUES ('148', '148', '44', '2');
INSERT INTO `page_section` VALUES ('149', '149', '44', '3');
INSERT INTO `page_section` VALUES ('150', '150', '44', '4');
INSERT INTO `page_section` VALUES ('151', '151', '45', '1');
INSERT INTO `page_section` VALUES ('152', '152', '45', '2');
INSERT INTO `page_section` VALUES ('153', '153', '45', '3');
INSERT INTO `page_section` VALUES ('154', '154', '45', '4');
INSERT INTO `page_section` VALUES ('160', '160', '46', '6');
INSERT INTO `page_section` VALUES ('161', '161', '47', '1');
INSERT INTO `page_section` VALUES ('162', '162', '48', '1');
INSERT INTO `page_section` VALUES ('163', '163', '48', '2');
INSERT INTO `page_section` VALUES ('164', '164', '48', '3');
INSERT INTO `page_section` VALUES ('165', '165', '48', '4');
INSERT INTO `page_section` VALUES ('166', '166', '49', '1');
INSERT INTO `page_section` VALUES ('167', '167', '49', '2');

-- ----------------------------
-- Table structure for page_sec_config
-- ----------------------------
DROP TABLE IF EXISTS `page_sec_config`;
CREATE TABLE `page_sec_config` (
  `config_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `page_section_id` int(11) DEFAULT NULL,
  `param_id` int(11) DEFAULT NULL,
  `param_value` varchar(40) DEFAULT NULL,
  `cre_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of page_sec_config
-- ----------------------------

-- ----------------------------
-- Table structure for page_template
-- ----------------------------
DROP TABLE IF EXISTS `page_template`;
CREATE TABLE `page_template` (
  `template_id` int(11) NOT NULL,
  `template_name` varchar(20) DEFAULT NULL,
  `template_url` varchar(200) DEFAULT NULL,
  `icon_url` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`template_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of page_template
-- ----------------------------
INSERT INTO `page_template` VALUES ('1', '3X3', 'upload/pageTemplate/tmpl/tmp1.html', 'upload/pageTemplate/layout1.png');
INSERT INTO `page_template` VALUES ('2', '1X3', 'upload/pageTemplate/tmpl/tmp2.html', 'upload/pageTemplate/layout2.png');
INSERT INTO `page_template` VALUES ('3', '1X4', 'upload/pageTemplate/tmpl/tmp3.html', 'upload/pageTemplate/layout3.png');
INSERT INTO `page_template` VALUES ('4', '1-3', 'upload/pageTemplate/tmpl/tmp4.html', 'upload/pageTemplate/layout4.png');
INSERT INTO `page_template` VALUES ('5', '1X3X4', 'upload/pageTemplate/tmpl/tmp5.html', 'upload/pageTemplate/layout5.png');
INSERT INTO `page_template` VALUES ('6', '3X1X3', 'upload/pageTemplate/tmpl/tmp6.html', 'upload/pageTemplate/layout6.png');

-- ----------------------------
-- Table structure for page_term_rel
-- ----------------------------
DROP TABLE IF EXISTS `page_term_rel`;
CREATE TABLE `page_term_rel` (
  `rel_id` int(11) NOT NULL AUTO_INCREMENT,
  `page_id` int(11) DEFAULT NULL,
  `term_type_id` int(11) DEFAULT NULL,
  `disp_order` int(11) DEFAULT NULL,
  PRIMARY KEY (`rel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of page_term_rel
-- ----------------------------

-- ----------------------------
-- Table structure for pending_business
-- ----------------------------
DROP TABLE IF EXISTS `pending_business`;
CREATE TABLE `pending_business` (
  `busi_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `busi_type_id` int(11) DEFAULT NULL,
  `close_time` timestamp NULL DEFAULT NULL,
  `details` varchar(200) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `importance` int(11) DEFAULT NULL,
  `src_busi_id` varchar(20) DEFAULT NULL,
  `recv_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`busi_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pending_business
-- ----------------------------

-- ----------------------------
-- Table structure for resource_type
-- ----------------------------
DROP TABLE IF EXISTS `resource_type`;
CREATE TABLE `resource_type` (
  `res_type_id` int(11) NOT NULL COMMENT '资源类型ID：0-小图标，1-大图标，2-预览',
  `res_type_name` varchar(40) DEFAULT NULL COMMENT '资源类型名称',
  PRIMARY KEY (`res_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源类型表';

-- ----------------------------
-- Records of resource_type
-- ----------------------------
INSERT INTO `resource_type` VALUES ('0', 'APP资源');
INSERT INTO `resource_type` VALUES ('1', 'PC资源');
INSERT INTO `resource_type` VALUES ('2', 'PC预览资源');

-- ----------------------------
-- Table structure for role_metric_privilege
-- ----------------------------
DROP TABLE IF EXISTS `role_metric_privilege`;
CREATE TABLE `role_metric_privilege` (
  `privi_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `metric_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`privi_id`)
) ENGINE=InnoDB AUTO_INCREMENT=894 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_metric_privilege
-- ----------------------------
INSERT INTO `role_metric_privilege` VALUES ('175', '10', '2');
INSERT INTO `role_metric_privilege` VALUES ('176', '10', '3');
INSERT INTO `role_metric_privilege` VALUES ('177', '10', '4');
INSERT INTO `role_metric_privilege` VALUES ('178', '10', '5');
INSERT INTO `role_metric_privilege` VALUES ('179', '10', '6');
INSERT INTO `role_metric_privilege` VALUES ('180', '10', '24');
INSERT INTO `role_metric_privilege` VALUES ('181', '10', '25');
INSERT INTO `role_metric_privilege` VALUES ('182', '10', '26');
INSERT INTO `role_metric_privilege` VALUES ('183', '10', '27');
INSERT INTO `role_metric_privilege` VALUES ('184', '10', '28');
INSERT INTO `role_metric_privilege` VALUES ('185', '10', '29');
INSERT INTO `role_metric_privilege` VALUES ('187', '10', '8');
INSERT INTO `role_metric_privilege` VALUES ('188', '10', '9');
INSERT INTO `role_metric_privilege` VALUES ('190', '10', '11');
INSERT INTO `role_metric_privilege` VALUES ('191', '10', '12');
INSERT INTO `role_metric_privilege` VALUES ('192', '10', '13');
INSERT INTO `role_metric_privilege` VALUES ('193', '10', '30');
INSERT INTO `role_metric_privilege` VALUES ('194', '10', '31');
INSERT INTO `role_metric_privilege` VALUES ('496', '1', '2');
INSERT INTO `role_metric_privilege` VALUES ('497', '1', '3');
INSERT INTO `role_metric_privilege` VALUES ('498', '1', '4');
INSERT INTO `role_metric_privilege` VALUES ('499', '1', '5');
INSERT INTO `role_metric_privilege` VALUES ('500', '1', '6');
INSERT INTO `role_metric_privilege` VALUES ('501', '1', '24');
INSERT INTO `role_metric_privilege` VALUES ('502', '1', '25');
INSERT INTO `role_metric_privilege` VALUES ('503', '1', '26');
INSERT INTO `role_metric_privilege` VALUES ('504', '1', '27');
INSERT INTO `role_metric_privilege` VALUES ('505', '1', '28');
INSERT INTO `role_metric_privilege` VALUES ('506', '1', '29');
INSERT INTO `role_metric_privilege` VALUES ('508', '1', '8');
INSERT INTO `role_metric_privilege` VALUES ('509', '1', '9');
INSERT INTO `role_metric_privilege` VALUES ('511', '1', '11');
INSERT INTO `role_metric_privilege` VALUES ('512', '1', '12');
INSERT INTO `role_metric_privilege` VALUES ('513', '1', '13');
INSERT INTO `role_metric_privilege` VALUES ('514', '1', '30');
INSERT INTO `role_metric_privilege` VALUES ('515', '1', '31');
INSERT INTO `role_metric_privilege` VALUES ('517', '1', '15');
INSERT INTO `role_metric_privilege` VALUES ('518', '1', '16');
INSERT INTO `role_metric_privilege` VALUES ('519', '1', '17');
INSERT INTO `role_metric_privilege` VALUES ('520', '1', '18');
INSERT INTO `role_metric_privilege` VALUES ('521', '1', '32');
INSERT INTO `role_metric_privilege` VALUES ('522', '1', '33');
INSERT INTO `role_metric_privilege` VALUES ('524', '1', '20');
INSERT INTO `role_metric_privilege` VALUES ('525', '1', '21');
INSERT INTO `role_metric_privilege` VALUES ('526', '1', '22');
INSERT INTO `role_metric_privilege` VALUES ('527', '1', '34');
INSERT INTO `role_metric_privilege` VALUES ('528', '1', '35');
INSERT INTO `role_metric_privilege` VALUES ('529', '1', '38');
INSERT INTO `role_metric_privilege` VALUES ('531', '1', '36');
INSERT INTO `role_metric_privilege` VALUES ('532', '1', '37');
INSERT INTO `role_metric_privilege` VALUES ('534', '1', '40');
INSERT INTO `role_metric_privilege` VALUES ('535', '1', '41');
INSERT INTO `role_metric_privilege` VALUES ('536', '1', '42');
INSERT INTO `role_metric_privilege` VALUES ('537', '1', '43');
INSERT INTO `role_metric_privilege` VALUES ('538', '1', '44');
INSERT INTO `role_metric_privilege` VALUES ('539', '1', '45');
INSERT INTO `role_metric_privilege` VALUES ('540', '1', '46');
INSERT INTO `role_metric_privilege` VALUES ('541', '1', '47');
INSERT INTO `role_metric_privilege` VALUES ('542', '1', '48');
INSERT INTO `role_metric_privilege` VALUES ('543', '1', '49');
INSERT INTO `role_metric_privilege` VALUES ('544', '1', '50');
INSERT INTO `role_metric_privilege` VALUES ('545', '1', '51');
INSERT INTO `role_metric_privilege` VALUES ('547', '3', '2');
INSERT INTO `role_metric_privilege` VALUES ('548', '3', '3');
INSERT INTO `role_metric_privilege` VALUES ('549', '3', '4');
INSERT INTO `role_metric_privilege` VALUES ('550', '3', '5');
INSERT INTO `role_metric_privilege` VALUES ('551', '3', '6');
INSERT INTO `role_metric_privilege` VALUES ('552', '3', '24');
INSERT INTO `role_metric_privilege` VALUES ('553', '3', '25');
INSERT INTO `role_metric_privilege` VALUES ('554', '3', '26');
INSERT INTO `role_metric_privilege` VALUES ('555', '3', '27');
INSERT INTO `role_metric_privilege` VALUES ('556', '3', '28');
INSERT INTO `role_metric_privilege` VALUES ('557', '3', '29');
INSERT INTO `role_metric_privilege` VALUES ('559', '3', '8');
INSERT INTO `role_metric_privilege` VALUES ('560', '3', '9');
INSERT INTO `role_metric_privilege` VALUES ('562', '3', '11');
INSERT INTO `role_metric_privilege` VALUES ('563', '3', '12');
INSERT INTO `role_metric_privilege` VALUES ('564', '3', '13');
INSERT INTO `role_metric_privilege` VALUES ('565', '3', '30');
INSERT INTO `role_metric_privilege` VALUES ('566', '3', '31');
INSERT INTO `role_metric_privilege` VALUES ('568', '3', '15');
INSERT INTO `role_metric_privilege` VALUES ('569', '3', '16');
INSERT INTO `role_metric_privilege` VALUES ('570', '3', '17');
INSERT INTO `role_metric_privilege` VALUES ('571', '3', '18');
INSERT INTO `role_metric_privilege` VALUES ('572', '3', '32');
INSERT INTO `role_metric_privilege` VALUES ('573', '3', '33');
INSERT INTO `role_metric_privilege` VALUES ('575', '3', '20');
INSERT INTO `role_metric_privilege` VALUES ('576', '3', '21');
INSERT INTO `role_metric_privilege` VALUES ('577', '3', '22');
INSERT INTO `role_metric_privilege` VALUES ('578', '3', '34');
INSERT INTO `role_metric_privilege` VALUES ('579', '3', '35');
INSERT INTO `role_metric_privilege` VALUES ('580', '3', '38');
INSERT INTO `role_metric_privilege` VALUES ('582', '3', '36');
INSERT INTO `role_metric_privilege` VALUES ('583', '3', '37');
INSERT INTO `role_metric_privilege` VALUES ('585', '3', '40');
INSERT INTO `role_metric_privilege` VALUES ('586', '3', '41');
INSERT INTO `role_metric_privilege` VALUES ('587', '3', '42');
INSERT INTO `role_metric_privilege` VALUES ('588', '3', '43');
INSERT INTO `role_metric_privilege` VALUES ('589', '3', '44');
INSERT INTO `role_metric_privilege` VALUES ('590', '3', '45');
INSERT INTO `role_metric_privilege` VALUES ('591', '3', '46');
INSERT INTO `role_metric_privilege` VALUES ('592', '3', '47');
INSERT INTO `role_metric_privilege` VALUES ('593', '3', '48');
INSERT INTO `role_metric_privilege` VALUES ('594', '3', '49');
INSERT INTO `role_metric_privilege` VALUES ('595', '3', '50');
INSERT INTO `role_metric_privilege` VALUES ('596', '3', '51');
INSERT INTO `role_metric_privilege` VALUES ('598', '4', '2');
INSERT INTO `role_metric_privilege` VALUES ('599', '4', '3');
INSERT INTO `role_metric_privilege` VALUES ('600', '4', '4');
INSERT INTO `role_metric_privilege` VALUES ('601', '4', '5');
INSERT INTO `role_metric_privilege` VALUES ('602', '4', '6');
INSERT INTO `role_metric_privilege` VALUES ('603', '4', '24');
INSERT INTO `role_metric_privilege` VALUES ('604', '4', '25');
INSERT INTO `role_metric_privilege` VALUES ('605', '4', '26');
INSERT INTO `role_metric_privilege` VALUES ('606', '4', '27');
INSERT INTO `role_metric_privilege` VALUES ('607', '4', '28');
INSERT INTO `role_metric_privilege` VALUES ('608', '4', '29');
INSERT INTO `role_metric_privilege` VALUES ('610', '4', '8');
INSERT INTO `role_metric_privilege` VALUES ('611', '4', '9');
INSERT INTO `role_metric_privilege` VALUES ('613', '4', '11');
INSERT INTO `role_metric_privilege` VALUES ('614', '4', '12');
INSERT INTO `role_metric_privilege` VALUES ('615', '4', '13');
INSERT INTO `role_metric_privilege` VALUES ('616', '4', '30');
INSERT INTO `role_metric_privilege` VALUES ('617', '4', '31');
INSERT INTO `role_metric_privilege` VALUES ('619', '4', '15');
INSERT INTO `role_metric_privilege` VALUES ('620', '4', '16');
INSERT INTO `role_metric_privilege` VALUES ('621', '4', '17');
INSERT INTO `role_metric_privilege` VALUES ('622', '4', '18');
INSERT INTO `role_metric_privilege` VALUES ('623', '4', '32');
INSERT INTO `role_metric_privilege` VALUES ('624', '4', '33');
INSERT INTO `role_metric_privilege` VALUES ('626', '4', '20');
INSERT INTO `role_metric_privilege` VALUES ('627', '4', '21');
INSERT INTO `role_metric_privilege` VALUES ('628', '4', '22');
INSERT INTO `role_metric_privilege` VALUES ('629', '4', '34');
INSERT INTO `role_metric_privilege` VALUES ('630', '4', '35');
INSERT INTO `role_metric_privilege` VALUES ('631', '4', '38');
INSERT INTO `role_metric_privilege` VALUES ('633', '4', '36');
INSERT INTO `role_metric_privilege` VALUES ('634', '4', '37');
INSERT INTO `role_metric_privilege` VALUES ('636', '4', '40');
INSERT INTO `role_metric_privilege` VALUES ('637', '4', '41');
INSERT INTO `role_metric_privilege` VALUES ('638', '4', '42');
INSERT INTO `role_metric_privilege` VALUES ('639', '4', '43');
INSERT INTO `role_metric_privilege` VALUES ('640', '4', '44');
INSERT INTO `role_metric_privilege` VALUES ('641', '4', '45');
INSERT INTO `role_metric_privilege` VALUES ('642', '4', '46');
INSERT INTO `role_metric_privilege` VALUES ('643', '4', '47');
INSERT INTO `role_metric_privilege` VALUES ('644', '4', '48');
INSERT INTO `role_metric_privilege` VALUES ('645', '4', '49');
INSERT INTO `role_metric_privilege` VALUES ('646', '4', '50');
INSERT INTO `role_metric_privilege` VALUES ('647', '4', '51');
INSERT INTO `role_metric_privilege` VALUES ('700', '16', '2');
INSERT INTO `role_metric_privilege` VALUES ('701', '16', '3');
INSERT INTO `role_metric_privilege` VALUES ('702', '16', '4');
INSERT INTO `role_metric_privilege` VALUES ('703', '16', '5');
INSERT INTO `role_metric_privilege` VALUES ('704', '16', '6');
INSERT INTO `role_metric_privilege` VALUES ('705', '16', '24');
INSERT INTO `role_metric_privilege` VALUES ('706', '16', '25');
INSERT INTO `role_metric_privilege` VALUES ('707', '16', '26');
INSERT INTO `role_metric_privilege` VALUES ('708', '16', '27');
INSERT INTO `role_metric_privilege` VALUES ('709', '16', '28');
INSERT INTO `role_metric_privilege` VALUES ('710', '16', '29');
INSERT INTO `role_metric_privilege` VALUES ('712', '16', '8');
INSERT INTO `role_metric_privilege` VALUES ('713', '16', '9');
INSERT INTO `role_metric_privilege` VALUES ('715', '16', '11');
INSERT INTO `role_metric_privilege` VALUES ('716', '16', '12');
INSERT INTO `role_metric_privilege` VALUES ('717', '16', '13');
INSERT INTO `role_metric_privilege` VALUES ('718', '16', '30');
INSERT INTO `role_metric_privilege` VALUES ('719', '16', '31');
INSERT INTO `role_metric_privilege` VALUES ('721', '16', '15');
INSERT INTO `role_metric_privilege` VALUES ('722', '16', '16');
INSERT INTO `role_metric_privilege` VALUES ('723', '16', '17');
INSERT INTO `role_metric_privilege` VALUES ('724', '16', '18');
INSERT INTO `role_metric_privilege` VALUES ('725', '16', '32');
INSERT INTO `role_metric_privilege` VALUES ('726', '16', '33');
INSERT INTO `role_metric_privilege` VALUES ('728', '16', '20');
INSERT INTO `role_metric_privilege` VALUES ('729', '16', '21');
INSERT INTO `role_metric_privilege` VALUES ('730', '16', '22');
INSERT INTO `role_metric_privilege` VALUES ('731', '16', '34');
INSERT INTO `role_metric_privilege` VALUES ('732', '16', '35');
INSERT INTO `role_metric_privilege` VALUES ('734', '16', '36');
INSERT INTO `role_metric_privilege` VALUES ('735', '16', '37');
INSERT INTO `role_metric_privilege` VALUES ('737', '16', '40');
INSERT INTO `role_metric_privilege` VALUES ('738', '16', '41');
INSERT INTO `role_metric_privilege` VALUES ('739', '16', '42');
INSERT INTO `role_metric_privilege` VALUES ('740', '16', '43');
INSERT INTO `role_metric_privilege` VALUES ('741', '16', '44');
INSERT INTO `role_metric_privilege` VALUES ('742', '16', '45');
INSERT INTO `role_metric_privilege` VALUES ('743', '16', '46');
INSERT INTO `role_metric_privilege` VALUES ('744', '16', '47');
INSERT INTO `role_metric_privilege` VALUES ('745', '16', '48');
INSERT INTO `role_metric_privilege` VALUES ('746', '16', '49');
INSERT INTO `role_metric_privilege` VALUES ('747', '16', '50');
INSERT INTO `role_metric_privilege` VALUES ('748', '16', '51');
INSERT INTO `role_metric_privilege` VALUES ('749', '16', '52');
INSERT INTO `role_metric_privilege` VALUES ('792', '5', '2');
INSERT INTO `role_metric_privilege` VALUES ('793', '5', '3');
INSERT INTO `role_metric_privilege` VALUES ('794', '5', '4');
INSERT INTO `role_metric_privilege` VALUES ('795', '5', '5');
INSERT INTO `role_metric_privilege` VALUES ('796', '5', '6');
INSERT INTO `role_metric_privilege` VALUES ('797', '5', '24');
INSERT INTO `role_metric_privilege` VALUES ('798', '5', '25');
INSERT INTO `role_metric_privilege` VALUES ('799', '5', '26');
INSERT INTO `role_metric_privilege` VALUES ('800', '5', '27');
INSERT INTO `role_metric_privilege` VALUES ('801', '5', '28');
INSERT INTO `role_metric_privilege` VALUES ('802', '5', '29');
INSERT INTO `role_metric_privilege` VALUES ('804', '5', '8');
INSERT INTO `role_metric_privilege` VALUES ('805', '5', '9');
INSERT INTO `role_metric_privilege` VALUES ('807', '5', '11');
INSERT INTO `role_metric_privilege` VALUES ('808', '5', '12');
INSERT INTO `role_metric_privilege` VALUES ('809', '5', '13');
INSERT INTO `role_metric_privilege` VALUES ('810', '5', '30');
INSERT INTO `role_metric_privilege` VALUES ('811', '5', '31');
INSERT INTO `role_metric_privilege` VALUES ('813', '5', '15');
INSERT INTO `role_metric_privilege` VALUES ('814', '5', '16');
INSERT INTO `role_metric_privilege` VALUES ('815', '5', '17');
INSERT INTO `role_metric_privilege` VALUES ('816', '5', '18');
INSERT INTO `role_metric_privilege` VALUES ('817', '5', '32');
INSERT INTO `role_metric_privilege` VALUES ('818', '5', '33');
INSERT INTO `role_metric_privilege` VALUES ('820', '5', '20');
INSERT INTO `role_metric_privilege` VALUES ('821', '5', '21');
INSERT INTO `role_metric_privilege` VALUES ('822', '5', '22');
INSERT INTO `role_metric_privilege` VALUES ('823', '5', '34');
INSERT INTO `role_metric_privilege` VALUES ('824', '5', '35');
INSERT INTO `role_metric_privilege` VALUES ('825', '5', '38');
INSERT INTO `role_metric_privilege` VALUES ('827', '5', '36');
INSERT INTO `role_metric_privilege` VALUES ('828', '5', '37');
INSERT INTO `role_metric_privilege` VALUES ('830', '5', '40');
INSERT INTO `role_metric_privilege` VALUES ('831', '5', '41');
INSERT INTO `role_metric_privilege` VALUES ('832', '5', '42');
INSERT INTO `role_metric_privilege` VALUES ('833', '5', '43');
INSERT INTO `role_metric_privilege` VALUES ('834', '5', '44');
INSERT INTO `role_metric_privilege` VALUES ('835', '5', '45');
INSERT INTO `role_metric_privilege` VALUES ('836', '5', '46');
INSERT INTO `role_metric_privilege` VALUES ('837', '5', '47');
INSERT INTO `role_metric_privilege` VALUES ('838', '5', '48');
INSERT INTO `role_metric_privilege` VALUES ('839', '5', '49');
INSERT INTO `role_metric_privilege` VALUES ('840', '5', '50');
INSERT INTO `role_metric_privilege` VALUES ('841', '5', '51');
INSERT INTO `role_metric_privilege` VALUES ('843', '2', '2');
INSERT INTO `role_metric_privilege` VALUES ('844', '2', '3');
INSERT INTO `role_metric_privilege` VALUES ('845', '2', '4');
INSERT INTO `role_metric_privilege` VALUES ('846', '2', '5');
INSERT INTO `role_metric_privilege` VALUES ('847', '2', '6');
INSERT INTO `role_metric_privilege` VALUES ('848', '2', '24');
INSERT INTO `role_metric_privilege` VALUES ('849', '2', '25');
INSERT INTO `role_metric_privilege` VALUES ('850', '2', '26');
INSERT INTO `role_metric_privilege` VALUES ('851', '2', '27');
INSERT INTO `role_metric_privilege` VALUES ('852', '2', '28');
INSERT INTO `role_metric_privilege` VALUES ('853', '2', '29');
INSERT INTO `role_metric_privilege` VALUES ('855', '2', '8');
INSERT INTO `role_metric_privilege` VALUES ('856', '2', '9');
INSERT INTO `role_metric_privilege` VALUES ('858', '2', '11');
INSERT INTO `role_metric_privilege` VALUES ('859', '2', '12');
INSERT INTO `role_metric_privilege` VALUES ('860', '2', '13');
INSERT INTO `role_metric_privilege` VALUES ('861', '2', '30');
INSERT INTO `role_metric_privilege` VALUES ('862', '2', '31');
INSERT INTO `role_metric_privilege` VALUES ('864', '2', '15');
INSERT INTO `role_metric_privilege` VALUES ('865', '2', '16');
INSERT INTO `role_metric_privilege` VALUES ('866', '2', '17');
INSERT INTO `role_metric_privilege` VALUES ('867', '2', '18');
INSERT INTO `role_metric_privilege` VALUES ('868', '2', '32');
INSERT INTO `role_metric_privilege` VALUES ('869', '2', '33');
INSERT INTO `role_metric_privilege` VALUES ('871', '2', '20');
INSERT INTO `role_metric_privilege` VALUES ('872', '2', '21');
INSERT INTO `role_metric_privilege` VALUES ('873', '2', '22');
INSERT INTO `role_metric_privilege` VALUES ('874', '2', '34');
INSERT INTO `role_metric_privilege` VALUES ('875', '2', '35');
INSERT INTO `role_metric_privilege` VALUES ('876', '2', '38');
INSERT INTO `role_metric_privilege` VALUES ('878', '2', '36');
INSERT INTO `role_metric_privilege` VALUES ('879', '2', '37');
INSERT INTO `role_metric_privilege` VALUES ('881', '2', '40');
INSERT INTO `role_metric_privilege` VALUES ('882', '2', '41');
INSERT INTO `role_metric_privilege` VALUES ('883', '2', '42');
INSERT INTO `role_metric_privilege` VALUES ('884', '2', '43');
INSERT INTO `role_metric_privilege` VALUES ('885', '2', '44');
INSERT INTO `role_metric_privilege` VALUES ('886', '2', '45');
INSERT INTO `role_metric_privilege` VALUES ('887', '2', '46');
INSERT INTO `role_metric_privilege` VALUES ('888', '2', '47');
INSERT INTO `role_metric_privilege` VALUES ('889', '2', '48');
INSERT INTO `role_metric_privilege` VALUES ('890', '2', '49');
INSERT INTO `role_metric_privilege` VALUES ('891', '2', '50');
INSERT INTO `role_metric_privilege` VALUES ('892', '2', '51');
INSERT INTO `role_metric_privilege` VALUES ('893', '2', '52');

-- ----------------------------
-- Table structure for role_privilege
-- ----------------------------
DROP TABLE IF EXISTS `role_privilege`;
CREATE TABLE `role_privilege` (
  `rel_id` int(11) NOT NULL AUTO_INCREMENT,
  `sys_priv_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`rel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_privilege
-- ----------------------------
INSERT INTO `role_privilege` VALUES ('1', '1', '1');
INSERT INTO `role_privilege` VALUES ('2', '2', '1');
INSERT INTO `role_privilege` VALUES ('3', '3', '1');
INSERT INTO `role_privilege` VALUES ('4', '4', '1');

-- ----------------------------
-- Table structure for role_type
-- ----------------------------
DROP TABLE IF EXISTS `role_type`;
CREATE TABLE `role_type` (
  `role_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_type_name` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`role_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_type
-- ----------------------------
INSERT INTO `role_type` VALUES ('1', '超级管理员');
INSERT INTO `role_type` VALUES ('2', '平台管理员');
INSERT INTO `role_type` VALUES ('3', '业务部门用户');

-- ----------------------------
-- Table structure for search_keywords
-- ----------------------------
DROP TABLE IF EXISTS `search_keywords`;
CREATE TABLE `search_keywords` (
  `keyword_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `keyword` varchar(20) DEFAULT NULL,
  `search_times` int(11) DEFAULT NULL,
  `last_search_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`keyword_id`)
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of search_keywords
-- ----------------------------
INSERT INTO `search_keywords` VALUES ('2', '1', '街道办事处', '19', '2018-06-25 13:01:00');
INSERT INTO `search_keywords` VALUES ('32', '1', 'test', '32', '2018-07-23 10:16:33');
INSERT INTO `search_keywords` VALUES ('33', '1', 'test', '32', '2018-07-23 10:16:33');
INSERT INTO `search_keywords` VALUES ('34', '1', '12345', '8', '2018-06-21 13:58:56');
INSERT INTO `search_keywords` VALUES ('35', '1', '5月份考勤', '86', '2018-07-23 10:16:22');
INSERT INTO `search_keywords` VALUES ('36', '1', '1', '1', '2018-06-07 09:09:15');
INSERT INTO `search_keywords` VALUES ('37', '1', '2345', '1', '2018-06-07 09:09:22');
INSERT INTO `search_keywords` VALUES ('38', '1', '5月', '3', '2018-06-07 09:10:22');
INSERT INTO `search_keywords` VALUES ('39', '1', 'content', '2', '2018-06-07 09:10:55');
INSERT INTO `search_keywords` VALUES ('40', '1', '20180607', '18', '2018-06-21 19:10:20');
INSERT INTO `search_keywords` VALUES ('41', '1', ' 20180607', '2', '2018-06-07 09:16:25');
INSERT INTO `search_keywords` VALUES ('42', '1', '高考加油', '2', '2018-06-07 09:16:29');
INSERT INTO `search_keywords` VALUES ('43', '1', '20180607高考第一天', '1', '2018-06-07 09:30:47');
INSERT INTO `search_keywords` VALUES ('44', '1', '2018年6月7日', '1', '2018-06-07 09:31:49');
INSERT INTO `search_keywords` VALUES ('45', '1', '2018', '2', '2018-06-07 09:47:07');
INSERT INTO `search_keywords` VALUES ('46', '31', 'test', '3', '2018-06-12 17:37:30');
INSERT INTO `search_keywords` VALUES ('47', '31', '高考加油', '4', '2018-06-07 09:50:12');
INSERT INTO `search_keywords` VALUES ('48', '1', '行政区划', '1', '2018-06-07 09:52:53');
INSERT INTO `search_keywords` VALUES ('49', '1', '建制乡', '1', '2018-06-07 09:56:56');
INSERT INTO `search_keywords` VALUES ('50', '1', '内容发布', '2', '2018-06-25 14:47:21');
INSERT INTO `search_keywords` VALUES ('51', '1', '测试', '20', '2018-07-23 09:57:00');
INSERT INTO `search_keywords` VALUES ('52', '1', '夏天', '1', '2018-06-12 13:33:44');
INSERT INTO `search_keywords` VALUES ('53', '1', 'ddd', '1', '2018-06-14 15:40:46');
INSERT INTO `search_keywords` VALUES ('54', '1', '看快递', '2', '2018-06-14 15:42:46');
INSERT INTO `search_keywords` VALUES ('55', '1', '电视', '7', '2018-06-21 16:21:57');
INSERT INTO `search_keywords` VALUES ('56', '1', '点点', '3', '2018-06-14 15:47:21');
INSERT INTO `search_keywords` VALUES ('57', '1', 'de', '2', '2018-06-14 16:06:30');
INSERT INTO `search_keywords` VALUES ('58', '1', '北京市', '7', '2018-06-25 15:37:45');
INSERT INTO `search_keywords` VALUES ('59', '1', '北京', '108', '2018-08-16 16:56:01');
INSERT INTO `search_keywords` VALUES ('60', '1', 'jdk', '1', '2018-06-19 10:41:16');
INSERT INTO `search_keywords` VALUES ('61', '1', '公积金', '2', '2018-06-21 16:07:44');
INSERT INTO `search_keywords` VALUES ('62', '1', 'jdk配置方法', '6', '2018-06-22 13:24:35');
INSERT INTO `search_keywords` VALUES ('63', '1', '2018062119规则数据', '1', '2018-06-21 15:26:32');
INSERT INTO `search_keywords` VALUES ('64', '1', '中国', '11', '2018-07-23 10:16:39');
INSERT INTO `search_keywords` VALUES ('65', '1', '人民', '8', '2018-06-22 14:57:58');
INSERT INTO `search_keywords` VALUES ('66', '1', '北京城市副中心', '2', '2018-06-22 13:35:23');
INSERT INTO `search_keywords` VALUES ('67', '1', '政务', '5', '2018-06-27 09:29:19');
INSERT INTO `search_keywords` VALUES ('68', '1', '战略定位——建设三个示范区', '1', '2018-06-22 13:52:42');
INSERT INTO `search_keywords` VALUES ('69', '1', '行政', '1', '2018-06-22 14:00:31');
INSERT INTO `search_keywords` VALUES ('70', '1', '指标', '1', '2018-06-22 14:00:46');
INSERT INTO `search_keywords` VALUES ('71', '1', '常委', '6', '2018-06-22 17:59:14');
INSERT INTO `search_keywords` VALUES ('72', '1', '蔡奇', '7', '2018-06-26 15:09:44');
INSERT INTO `search_keywords` VALUES ('73', '1', '常委会', '5', '2018-06-22 17:59:21');
INSERT INTO `search_keywords` VALUES ('74', '1', '个税', '20', '2018-06-27 14:33:43');
INSERT INTO `search_keywords` VALUES ('75', '1', '市委', '1', '2018-06-22 17:50:02');
INSERT INTO `search_keywords` VALUES ('76', '1', '长维护', '1', '2018-06-22 17:53:02');
INSERT INTO `search_keywords` VALUES ('77', '1', '个人所得税法', '6', '2018-06-22 18:01:04');
INSERT INTO `search_keywords` VALUES ('78', '2', '北京', '9', '2018-06-27 14:07:02');
INSERT INTO `search_keywords` VALUES ('79', '1', '数据分析', '1', '2018-06-25 11:48:05');
INSERT INTO `search_keywords` VALUES ('80', '1', '统一监控平台', '1', '2018-06-25 11:48:36');
INSERT INTO `search_keywords` VALUES ('81', '1', '实时外汇行情', '1', '2018-06-25 11:50:36');
INSERT INTO `search_keywords` VALUES ('82', '1', '门户', '1', '2018-06-25 14:31:13');
INSERT INTO `search_keywords` VALUES ('83', '1', '李克强', '2', '2018-06-26 15:08:25');
INSERT INTO `search_keywords` VALUES ('84', '1', '内容 ', '4', '2018-06-25 16:01:38');
INSERT INTO `search_keywords` VALUES ('85', '1', '内容发布1', '1', '2018-06-25 15:53:50');
INSERT INTO `search_keywords` VALUES ('86', '1', '发布', '1', '2018-06-25 15:56:52');
INSERT INTO `search_keywords` VALUES ('87', '1', '测试项目', '1', '2018-06-25 15:59:33');
INSERT INTO `search_keywords` VALUES ('88', '1', '申请', '1', '2018-06-25 16:20:50');
INSERT INTO `search_keywords` VALUES ('89', '1', '个税起征点拟调至每月5000元', '5', '2018-06-26 17:00:04');
INSERT INTO `search_keywords` VALUES ('90', '1', '避税行为', '1', '2018-06-26 15:00:20');
INSERT INTO `search_keywords` VALUES ('91', '1', '市常委', '1', '2018-06-26 15:09:13');
INSERT INTO `search_keywords` VALUES ('92', '1', '市委常委会召开会议 委书记蔡奇主持会议', '1', '2018-06-26 15:09:29');
INSERT INTO `search_keywords` VALUES ('93', '1', '蔡奇主持会议', '1', '2018-06-26 15:09:41');
INSERT INTO `search_keywords` VALUES ('94', '1', '个税起征点拟调至每月10000元', '6', '2018-06-26 16:05:28');
INSERT INTO `search_keywords` VALUES ('95', '1', '北大', '6', '2018-07-26 10:13:41');
INSERT INTO `search_keywords` VALUES ('96', '1', '北大清华部分自招生降至一本线录取', '2', '2018-06-26 16:56:48');
INSERT INTO `search_keywords` VALUES ('97', '1', '热点', '1', '2018-06-27 09:29:26');
INSERT INTO `search_keywords` VALUES ('98', '2', '资料', '1', '2018-06-27 14:03:38');
INSERT INTO `search_keywords` VALUES ('99', '2', '个税', '4', '2018-06-27 14:06:12');
INSERT INTO `search_keywords` VALUES ('100', '2', '北', '1', '2018-06-27 14:06:35');
INSERT INTO `search_keywords` VALUES ('101', '1', '字字珠玑', '3', '2018-07-26 09:51:05');
INSERT INTO `search_keywords` VALUES ('102', '1', '城市1', '1', '2018-06-27 15:22:10');
INSERT INTO `search_keywords` VALUES ('103', '1', 'åäº¬', '1', '2018-07-06 08:20:59');
INSERT INTO `search_keywords` VALUES ('104', '1', 'å', '1', '2018-07-06 08:22:00');
INSERT INTO `search_keywords` VALUES ('105', '1', 'ç¹ç¹äº', '5', '2018-07-06 08:56:03');
INSERT INTO `search_keywords` VALUES ('106', '1', '点点事', '8', '2018-07-06 08:53:42');
INSERT INTO `search_keywords` VALUES ('107', '1', '向服务器', '2', '2018-07-18 17:20:37');
INSERT INTO `search_keywords` VALUES ('108', '1', '向服务器发送请求', '2', '2018-07-18 17:20:42');
INSERT INTO `search_keywords` VALUES ('109', '6', '北京', '1', '2018-07-19 10:49:32');
INSERT INTO `search_keywords` VALUES ('110', '1', 'GDP', '1', '2018-07-19 15:00:11');
INSERT INTO `search_keywords` VALUES ('111', '1', '北大清华', '1', '2018-07-26 10:13:29');
INSERT INTO `search_keywords` VALUES ('112', '1', '北京商务', '1', '2018-07-26 10:14:49');
INSERT INTO `search_keywords` VALUES ('113', '1', '个人所得税', '2', '2018-07-26 10:15:42');
INSERT INTO `search_keywords` VALUES ('114', '1', '城市建设', '4', '2018-07-26 11:36:02');
INSERT INTO `search_keywords` VALUES ('115', '1', '李克强同尼泊尔总理奥利举行会谈 ', '2', '2018-07-27 08:10:31');
INSERT INTO `search_keywords` VALUES ('116', '7', '北京', '1', '2018-07-31 14:13:08');
INSERT INTO `search_keywords` VALUES ('117', '1', 'aaa', '1', '2018-08-06 16:17:49');
INSERT INTO `search_keywords` VALUES ('118', '1', '北京11', '1', '2018-08-10 15:40:02');

-- ----------------------------
-- Table structure for section
-- ----------------------------
DROP TABLE IF EXISTS `section`;
CREATE TABLE `section` (
  `section_id` int(11) NOT NULL AUTO_INCREMENT,
  `section_name` varchar(60) DEFAULT NULL,
  `section_type` int(11) DEFAULT NULL,
  `section_url` varchar(200) DEFAULT NULL,
  `upd_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`section_id`)
) ENGINE=InnoDB AUTO_INCREMENT=168 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of section
-- ----------------------------
INSERT INTO `section` VALUES ('23', '栏目1', '1', '{\"x\":0,\"y\":0,\"no\":\"1\",\"width\":12,\"height\":6}', '2018-06-05 17:09:18');
INSERT INTO `section` VALUES ('24', '栏目2', '1', '{\"x\":0,\"y\":6,\"no\":\"2\",\"width\":12,\"height\":5}', '2018-06-05 17:09:18');
INSERT INTO `section` VALUES ('25', '栏目1', '1', '{\"x\":0,\"y\":0,\"no\":\"1\",\"width\":8,\"height\":6}', '2018-05-30 10:13:13');
INSERT INTO `section` VALUES ('26', '栏目1', '1', '{\"x\":0,\"y\":0,\"no\":\"1\",\"width\":12,\"height\":4}', '2018-06-04 13:54:09');
INSERT INTO `section` VALUES ('27', '栏目2', '1', '{\"x\":0,\"y\":4,\"no\":\"2\",\"width\":4,\"height\":2}', '2018-06-04 13:54:09');
INSERT INTO `section` VALUES ('28', '栏目3', '1', '{\"x\":4,\"y\":4,\"no\":\"3\",\"width\":4,\"height\":2}', '2018-06-04 13:54:09');
INSERT INTO `section` VALUES ('29', '栏目4', '1', '{\"x\":8,\"y\":4,\"no\":\"4\",\"width\":4,\"height\":2}', '2018-06-04 13:54:09');
INSERT INTO `section` VALUES ('37', '栏目1', '1', '{\"x\":0,\"y\":0,\"no\":\"1\",\"width\":12,\"height\":2}', '2018-06-04 17:55:04');
INSERT INTO `section` VALUES ('38', '栏目2', '1', '{\"x\":0,\"y\":4,\"no\":\"5\",\"width\":6,\"height\":2}', '2018-06-04 17:55:04');
INSERT INTO `section` VALUES ('39', '栏目3', '1', '{\"x\":0,\"y\":2,\"no\":\"3\",\"width\":6,\"height\":2}', '2018-06-04 17:55:04');
INSERT INTO `section` VALUES ('40', '栏目1', '1', '{\"x\":0,\"y\":0,\"no\":\"1\",\"width\":12,\"height\":4}', '2018-06-04 18:38:50');
INSERT INTO `section` VALUES ('41', '栏目2', '1', '{\"x\":0,\"y\":4,\"no\":\"2\",\"width\":4,\"height\":2}', '2018-06-04 18:38:50');
INSERT INTO `section` VALUES ('42', '栏目3', '1', '{\"x\":8,\"y\":4,\"no\":\"4\",\"width\":4,\"height\":2}', '2018-06-04 18:38:50');
INSERT INTO `section` VALUES ('46', '栏目1', '1', '{\"x\":0,\"y\":0,\"no\":\"1\",\"width\":8,\"height\":6}', '2018-06-05 10:30:02');
INSERT INTO `section` VALUES ('47', '栏目2', '1', '{\"x\":8,\"y\":0,\"no\":\"2\",\"width\":4,\"height\":2}', '2018-06-05 10:30:02');
INSERT INTO `section` VALUES ('48', '栏目3', '1', '{\"x\":8,\"y\":2,\"no\":\"3\",\"width\":4,\"height\":2}', '2018-06-05 10:30:02');
INSERT INTO `section` VALUES ('49', '栏目1', '1', '{\"x\":0,\"y\":0,\"no\":\"1\",\"width\":8,\"height\":6}', '2018-06-06 16:27:03');
INSERT INTO `section` VALUES ('50', '栏目2', '1', '{\"x\":8,\"y\":0,\"no\":\"2\",\"width\":4,\"height\":2}', '2018-06-06 16:27:03');
INSERT INTO `section` VALUES ('51', '栏目3', '1', '{\"x\":8,\"y\":2,\"no\":\"3\",\"width\":4,\"height\":4}', '2018-06-06 16:27:03');
INSERT INTO `section` VALUES ('52', '栏目1', '1', '{\"x\":0,\"y\":0,\"no\":\"1\",\"width\":12,\"height\":6}', '2018-06-07 14:39:14');
INSERT INTO `section` VALUES ('53', '栏目2', '1', '{\"x\":0,\"y\":6,\"no\":\"2\",\"width\":4,\"height\":2}', '2018-06-07 14:39:14');
INSERT INTO `section` VALUES ('54', '栏目3', '1', '{\"x\":4,\"y\":6,\"no\":\"3\",\"width\":8,\"height\":2}', '2018-06-07 14:39:14');
INSERT INTO `section` VALUES ('56', '栏目1', '1', '{\"x\":0,\"y\":0,\"no\":\"1\",\"width\":12,\"height\":4}', '2018-06-07 14:07:27');
INSERT INTO `section` VALUES ('57', '栏目2', '1', '{\"x\":0,\"y\":4,\"no\":\"2\",\"width\":4,\"height\":4}', '2018-06-07 14:07:27');
INSERT INTO `section` VALUES ('58', '栏目3', '1', '{\"x\":4,\"y\":4,\"no\":\"3\",\"width\":8,\"height\":4}', '2018-06-07 14:07:27');
INSERT INTO `section` VALUES ('59', '栏目1', '1', '{\"x\":0,\"y\":0,\"no\":\"1\",\"width\":8,\"height\":6}', '2018-06-07 14:09:43');
INSERT INTO `section` VALUES ('60', '栏目2', '1', '{\"x\":8,\"y\":0,\"no\":\"2\",\"width\":4,\"height\":2}', '2018-06-07 14:09:43');
INSERT INTO `section` VALUES ('61', '栏目3', '1', '{\"x\":8,\"y\":2,\"no\":\"4\",\"width\":4,\"height\":4}', '2018-06-07 14:09:43');
INSERT INTO `section` VALUES ('62', '栏目1', '1', '{\"x\":0,\"y\":0,\"no\":\"1\",\"width\":8,\"height\":6}', '2018-06-07 14:11:53');
INSERT INTO `section` VALUES ('63', '栏目2', '1', '{\"x\":8,\"y\":0,\"no\":\"3\",\"width\":4,\"height\":2}', '2018-06-07 14:11:53');
INSERT INTO `section` VALUES ('64', '栏目1', '1', '{\"x\":0,\"y\":0,\"no\":\"1\",\"width\":8,\"height\":6}', '2018-06-08 15:08:17');
INSERT INTO `section` VALUES ('65', '栏目2', '1', '{\"x\":8,\"y\":0,\"no\":\"2\",\"width\":4,\"height\":2}', '2018-06-08 15:08:17');
INSERT INTO `section` VALUES ('66', '栏目3', '1', '{\"x\":8,\"y\":2,\"no\":\"3\",\"width\":4,\"height\":2}', '2018-06-08 15:08:17');
INSERT INTO `section` VALUES ('67', '栏目4', '1', '{\"x\":8,\"y\":4,\"no\":\"4\",\"width\":4,\"height\":2}', '2018-06-08 15:08:17');
INSERT INTO `section` VALUES ('68', '栏目1', '1', '{\"x\":4,\"y\":0,\"no\":\"1\",\"width\":8,\"height\":6}', '2018-06-13 13:53:55');
INSERT INTO `section` VALUES ('69', '栏目2', '1', '{\"x\":0,\"y\":0,\"no\":\"2\",\"width\":4,\"height\":6}', '2018-06-13 13:53:55');
INSERT INTO `section` VALUES ('70', '栏目1', '1', '{\"x\":0,\"y\":0,\"no\":\"1\",\"width\":12,\"height\":6}', '2018-06-13 13:00:06');
INSERT INTO `section` VALUES ('85', '栏目1', '1', '{\"x\":0,\"y\":0,\"no\":\"1\",\"width\":12,\"height\":4}', '2018-06-19 08:31:23');
INSERT INTO `section` VALUES ('86', '栏目2', '1', '{\"x\":0,\"y\":4,\"no\":\"2\",\"width\":6,\"height\":2}', '2018-06-19 08:31:23');
INSERT INTO `section` VALUES ('87', '栏目3', '1', '{\"x\":7,\"y\":4,\"no\":\"3\",\"width\":5,\"height\":2}', '2018-06-19 08:31:23');
INSERT INTO `section` VALUES ('88', '栏目1', '1', '{\"x\":0,\"y\":0,\"no\":\"1\",\"width\":12,\"height\":4}', '2018-06-25 09:48:17');
INSERT INTO `section` VALUES ('89', '栏目2', '1', '{\"x\":0,\"y\":4,\"no\":\"2\",\"width\":4,\"height\":2}', '2018-06-25 09:48:17');
INSERT INTO `section` VALUES ('90', '栏目3', '1', '{\"x\":4,\"y\":4,\"no\":\"3\",\"width\":4,\"height\":2}', '2018-06-25 09:48:17');
INSERT INTO `section` VALUES ('91', '栏目4', '1', '{\"x\":8,\"y\":4,\"no\":\"4\",\"width\":4,\"height\":2}', '2018-06-25 09:48:17');
INSERT INTO `section` VALUES ('92', '栏目1', '1', '{\"x\":0,\"y\":0,\"no\":\"1\",\"width\":8,\"height\":6}', '2018-06-25 10:02:08');
INSERT INTO `section` VALUES ('93', '栏目2', '1', '{\"x\":8,\"y\":0,\"no\":\"2\",\"width\":4,\"height\":2}', '2018-06-25 10:02:08');
INSERT INTO `section` VALUES ('94', '栏目3', '1', '{\"x\":8,\"y\":2,\"no\":\"3\",\"width\":4,\"height\":2}', '2018-06-25 10:02:08');
INSERT INTO `section` VALUES ('95', '栏目4', '1', '{\"x\":8,\"y\":4,\"no\":\"4\",\"width\":4,\"height\":2}', '2018-06-25 10:02:08');
INSERT INTO `section` VALUES ('96', '栏目1', '1', '{\"x\":0,\"y\":0,\"no\":\"1\",\"width\":12,\"height\":6}', '2018-06-25 10:12:00');
INSERT INTO `section` VALUES ('98', '栏目1', '1', '{\"x\":0,\"y\":0,\"no\":\"1\",\"width\":8,\"height\":6}', '2018-07-03 18:15:11');
INSERT INTO `section` VALUES ('99', '栏目2', '1', '{\"x\":8,\"y\":0,\"no\":\"2\",\"width\":4,\"height\":2}', '2018-07-03 18:15:11');
INSERT INTO `section` VALUES ('100', '栏目3', '1', '{\"x\":8,\"y\":2,\"no\":\"3\",\"width\":4,\"height\":2}', '2018-07-03 18:15:11');
INSERT INTO `section` VALUES ('101', '栏目4', '1', '{\"x\":8,\"y\":4,\"no\":\"4\",\"width\":4,\"height\":2}', '2018-07-03 18:15:11');
INSERT INTO `section` VALUES ('102', '栏目1', '1', '{\"x\":0,\"y\":0,\"no\":\"1\",\"width\":12,\"height\":4}', '2018-06-29 11:12:00');
INSERT INTO `section` VALUES ('103', '栏目2', '1', '{\"x\":0,\"y\":4,\"no\":\"2\",\"width\":4,\"height\":2}', '2018-06-29 11:12:00');
INSERT INTO `section` VALUES ('104', '栏目3', '1', '{\"x\":4,\"y\":4,\"no\":\"3\",\"width\":4,\"height\":2}', '2018-06-29 11:12:00');
INSERT INTO `section` VALUES ('105', '栏目4', '1', '{\"x\":8,\"y\":4,\"no\":\"4\",\"width\":4,\"height\":2}', '2018-06-29 11:12:00');
INSERT INTO `section` VALUES ('106', '栏目1', '1', '{\"x\":0,\"y\":0,\"no\":\"1\",\"width\":12,\"height\":6}', '2018-07-03 16:43:12');
INSERT INTO `section` VALUES ('107', '栏目1', '1', '{\"x\":0,\"y\":0,\"no\":\"1\",\"width\":6,\"height\":2}', '2018-06-27 11:33:29');
INSERT INTO `section` VALUES ('108', '栏目2', '1', '{\"x\":6,\"y\":0,\"no\":\"2\",\"width\":6,\"height\":2}', '2018-06-27 11:33:29');
INSERT INTO `section` VALUES ('109', '栏目3', '1', '{\"x\":0,\"y\":2,\"no\":\"3\",\"width\":6,\"height\":2}', '2018-06-27 11:33:29');
INSERT INTO `section` VALUES ('110', '栏目4', '1', '{\"x\":6,\"y\":2,\"no\":\"4\",\"width\":6,\"height\":2}', '2018-06-27 11:33:29');
INSERT INTO `section` VALUES ('111', '栏目5', '1', '{\"x\":0,\"y\":4,\"no\":\"5\",\"width\":6,\"height\":2}', '2018-06-27 11:33:29');
INSERT INTO `section` VALUES ('112', '栏目6', '1', '{\"x\":6,\"y\":4,\"no\":\"6\",\"width\":6,\"height\":2}', '2018-06-27 11:33:29');
INSERT INTO `section` VALUES ('113', '栏目1', '1', '{\"x\":0,\"y\":0,\"no\":\"1\",\"width\":8,\"height\":4}', '2018-06-27 11:34:18');
INSERT INTO `section` VALUES ('114', '栏目2', '1', '{\"x\":8,\"y\":0,\"no\":\"2\",\"width\":4,\"height\":1}', '2018-06-27 11:34:18');
INSERT INTO `section` VALUES ('115', '栏目3', '1', '{\"x\":8,\"y\":1,\"no\":\"3\",\"width\":4,\"height\":1}', '2018-06-27 11:34:18');
INSERT INTO `section` VALUES ('116', '栏目4', '1', '{\"x\":8,\"y\":2,\"no\":\"4\",\"width\":4,\"height\":1}', '2018-06-27 11:34:18');
INSERT INTO `section` VALUES ('117', '栏目5', '1', '{\"x\":8,\"y\":3,\"no\":\"5\",\"width\":4,\"height\":1}', '2018-06-27 11:34:18');
INSERT INTO `section` VALUES ('118', '栏目1', '1', '{\"x\":0,\"y\":0,\"no\":\"1\",\"width\":12,\"height\":6}', '2018-06-27 11:40:17');
INSERT INTO `section` VALUES ('119', '栏目1', '1', '{\"x\":0,\"y\":0,\"no\":\"1\",\"width\":12,\"height\":4}', '2018-06-27 13:40:12');
INSERT INTO `section` VALUES ('120', '栏目2', '1', '{\"x\":0,\"y\":4,\"no\":\"2\",\"width\":4,\"height\":2}', '2018-06-27 13:40:12');
INSERT INTO `section` VALUES ('121', '栏目3', '1', '{\"x\":4,\"y\":4,\"no\":\"3\",\"width\":4,\"height\":2}', '2018-06-27 13:40:12');
INSERT INTO `section` VALUES ('122', '栏目4', '1', '{\"x\":8,\"y\":4,\"no\":\"4\",\"width\":4,\"height\":2}', '2018-06-27 13:40:12');
INSERT INTO `section` VALUES ('123', '栏目1', '1', '{\"x\":0,\"y\":0,\"no\":\"1\",\"width\":12,\"height\":6}', '2018-06-29 11:29:29');
INSERT INTO `section` VALUES ('124', '栏目1', '1', '{\"x\":0,\"y\":0,\"no\":\"1\",\"width\":8,\"height\":6}', '2018-06-29 11:28:08');
INSERT INTO `section` VALUES ('125', '栏目2', '1', '{\"x\":8,\"y\":0,\"no\":\"2\",\"width\":4,\"height\":2}', '2018-06-29 11:28:08');
INSERT INTO `section` VALUES ('126', '栏目3', '1', '{\"x\":8,\"y\":2,\"no\":\"3\",\"width\":4,\"height\":2}', '2018-06-29 11:28:08');
INSERT INTO `section` VALUES ('127', '栏目4', '1', '{\"x\":8,\"y\":4,\"no\":\"4\",\"width\":4,\"height\":2}', '2018-06-29 11:28:08');
INSERT INTO `section` VALUES ('128', '栏目2', '1', '{\"x\":0,\"y\":6,\"no\":\"2\",\"width\":4,\"height\":3}', '2018-06-29 11:29:29');
INSERT INTO `section` VALUES ('129', '栏目3', '1', '{\"x\":4,\"y\":6,\"no\":\"3\",\"width\":4,\"height\":3}', '2018-06-29 11:29:29');
INSERT INTO `section` VALUES ('130', '栏目4', '1', '{\"x\":8,\"y\":6,\"no\":\"4\",\"width\":4,\"height\":3}', '2018-06-29 11:29:29');
INSERT INTO `section` VALUES ('131', '栏目1', '1', '{\"x\":0,\"y\":0,\"no\":\"1\",\"width\":8,\"height\":6}', '2018-07-04 11:37:42');
INSERT INTO `section` VALUES ('132', '栏目2', '1', '{\"x\":8,\"y\":0,\"no\":\"2\",\"width\":4,\"height\":2}', '2018-07-04 11:37:42');
INSERT INTO `section` VALUES ('133', '栏目3', '1', '{\"x\":8,\"y\":2,\"no\":\"3\",\"width\":4,\"height\":2}', '2018-07-04 11:37:42');
INSERT INTO `section` VALUES ('134', '栏目4', '1', '{\"x\":8,\"y\":4,\"no\":\"4\",\"width\":4,\"height\":2}', '2018-07-04 11:37:42');
INSERT INTO `section` VALUES ('135', '栏目5', '1', '{\"x\":0,\"y\":6,\"no\":\"5\",\"width\":4,\"height\":2}', '2018-07-04 11:37:42');
INSERT INTO `section` VALUES ('136', '栏目6', '1', '{\"x\":4,\"y\":6,\"no\":\"6\",\"width\":4,\"height\":2}', '2018-07-04 11:37:42');
INSERT INTO `section` VALUES ('137', '栏目7', '1', '{\"x\":8,\"y\":6,\"no\":\"7\",\"width\":4,\"height\":2}', '2018-07-04 11:37:42');
INSERT INTO `section` VALUES ('138', '栏目1', '1', '{\"x\":0,\"y\":0,\"no\":\"1\",\"width\":12,\"height\":6}', '2018-07-04 11:33:16');
INSERT INTO `section` VALUES ('139', '栏目1', '1', '{\"x\":0,\"y\":3,\"no\":\"1\",\"width\":12,\"height\":6}', '2018-07-04 11:34:55');
INSERT INTO `section` VALUES ('140', '栏目2', '1', '{\"x\":0,\"y\":0,\"no\":\"2\",\"width\":4,\"height\":3}', '2018-07-04 11:34:55');
INSERT INTO `section` VALUES ('141', '栏目3', '1', '{\"x\":4,\"y\":0,\"no\":\"3\",\"width\":4,\"height\":3}', '2018-07-04 11:34:55');
INSERT INTO `section` VALUES ('142', '栏目4', '1', '{\"x\":8,\"y\":0,\"no\":\"4\",\"width\":4,\"height\":3}', '2018-07-04 11:34:55');
INSERT INTO `section` VALUES ('143', '栏目1', '1', '{\"x\":0,\"y\":0,\"no\":\"1\",\"width\":12,\"height\":4}', '2018-07-04 14:46:59');
INSERT INTO `section` VALUES ('144', '栏目2', '1', '{\"x\":0,\"y\":4,\"no\":\"2\",\"width\":4,\"height\":2}', '2018-07-04 14:46:59');
INSERT INTO `section` VALUES ('145', '栏目3', '1', '{\"x\":4,\"y\":4,\"no\":\"3\",\"width\":4,\"height\":2}', '2018-07-04 14:46:59');
INSERT INTO `section` VALUES ('146', '栏目4', '1', '{\"x\":8,\"y\":4,\"no\":\"4\",\"width\":4,\"height\":2}', '2018-07-04 14:46:59');
INSERT INTO `section` VALUES ('147', '栏目1', '1', '{\"x\":0,\"y\":0,\"no\":\"1\",\"width\":12,\"height\":4}', '2018-07-05 09:33:19');
INSERT INTO `section` VALUES ('148', '栏目2', '1', '{\"x\":0,\"y\":4,\"no\":\"2\",\"width\":4,\"height\":2}', '2018-07-05 09:33:19');
INSERT INTO `section` VALUES ('149', '栏目3', '1', '{\"x\":4,\"y\":4,\"no\":\"3\",\"width\":4,\"height\":2}', '2018-07-05 09:33:19');
INSERT INTO `section` VALUES ('150', '栏目4', '1', '{\"x\":8,\"y\":4,\"no\":\"4\",\"width\":4,\"height\":2}', '2018-07-05 09:33:19');
INSERT INTO `section` VALUES ('151', '栏目1', '1', '{\"x\":0,\"y\":0,\"no\":\"1\",\"width\":12,\"height\":4}', '2018-07-05 11:31:06');
INSERT INTO `section` VALUES ('152', '栏目2', '1', '{\"x\":0,\"y\":4,\"no\":\"2\",\"width\":4,\"height\":2}', '2018-07-05 11:31:06');
INSERT INTO `section` VALUES ('153', '栏目3', '1', '{\"x\":4,\"y\":4,\"no\":\"3\",\"width\":4,\"height\":2}', '2018-07-05 11:31:06');
INSERT INTO `section` VALUES ('154', '栏目4', '1', '{\"x\":8,\"y\":4,\"no\":\"4\",\"width\":4,\"height\":2}', '2018-07-05 11:31:06');
INSERT INTO `section` VALUES ('160', '栏目1', '1', '{\"x\":3,\"y\":0,\"no\":\"6\",\"width\":8,\"height\":6}', '2018-08-14 14:46:24');
INSERT INTO `section` VALUES ('161', '栏目1', '1', '{\"x\":0,\"y\":0,\"no\":\"1\",\"width\":12,\"height\":6}', '2018-08-08 17:31:30');
INSERT INTO `section` VALUES ('162', '栏目1', '1', '{\"x\":0,\"y\":0,\"no\":\"1\",\"width\":12,\"height\":4}', '2018-07-27 15:21:27');
INSERT INTO `section` VALUES ('163', '栏目2', '1', '{\"x\":0,\"y\":4,\"no\":\"2\",\"width\":4,\"height\":2}', '2018-07-27 15:21:27');
INSERT INTO `section` VALUES ('164', '栏目3', '1', '{\"x\":4,\"y\":4,\"no\":\"3\",\"width\":4,\"height\":2}', '2018-07-27 15:21:27');
INSERT INTO `section` VALUES ('165', '栏目4', '1', '{\"x\":8,\"y\":4,\"no\":\"4\",\"width\":4,\"height\":2}', '2018-07-27 15:21:27');
INSERT INTO `section` VALUES ('166', '栏目1', '1', '{\"x\":0,\"y\":0,\"no\":\"1\",\"width\":12,\"height\":6}', '2018-08-14 17:20:27');
INSERT INTO `section` VALUES ('167', '栏目2', '1', '{\"x\":0,\"y\":6,\"no\":\"2\",\"width\":12,\"height\":3}', '2018-08-14 17:20:27');

-- ----------------------------
-- Table structure for section_metric
-- ----------------------------
DROP TABLE IF EXISTS `section_metric`;
CREATE TABLE `section_metric` (
  `sec_metric_id` int(11) NOT NULL AUTO_INCREMENT,
  `section_id` int(11) DEFAULT NULL,
  `metric_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`sec_metric_id`)
) ENGINE=InnoDB AUTO_INCREMENT=268 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of section_metric
-- ----------------------------
INSERT INTO `section_metric` VALUES ('16', '25', '11');
INSERT INTO `section_metric` VALUES ('20', '26', '24');
INSERT INTO `section_metric` VALUES ('21', '27', '26');
INSERT INTO `section_metric` VALUES ('22', '28', '4');
INSERT INTO `section_metric` VALUES ('23', '29', '27');
INSERT INTO `section_metric` VALUES ('34', '37', '24');
INSERT INTO `section_metric` VALUES ('35', '37', '25');
INSERT INTO `section_metric` VALUES ('36', '37', '26');
INSERT INTO `section_metric` VALUES ('37', '38', '3');
INSERT INTO `section_metric` VALUES ('38', '39', '2');
INSERT INTO `section_metric` VALUES ('39', '39', '31');
INSERT INTO `section_metric` VALUES ('40', '40', '8');
INSERT INTO `section_metric` VALUES ('41', '40', '9');
INSERT INTO `section_metric` VALUES ('42', '40', '17');
INSERT INTO `section_metric` VALUES ('43', '41', '25');
INSERT INTO `section_metric` VALUES ('44', '42', '36');
INSERT INTO `section_metric` VALUES ('50', '46', '24');
INSERT INTO `section_metric` VALUES ('51', '47', '8');
INSERT INTO `section_metric` VALUES ('52', '47', '9');
INSERT INTO `section_metric` VALUES ('53', '48', '33');
INSERT INTO `section_metric` VALUES ('54', '48', '22');
INSERT INTO `section_metric` VALUES ('55', '48', '37');
INSERT INTO `section_metric` VALUES ('56', '23', '2');
INSERT INTO `section_metric` VALUES ('57', '23', '3');
INSERT INTO `section_metric` VALUES ('58', '24', '20');
INSERT INTO `section_metric` VALUES ('65', '49', '24');
INSERT INTO `section_metric` VALUES ('66', '49', '25');
INSERT INTO `section_metric` VALUES ('67', '49', '26');
INSERT INTO `section_metric` VALUES ('68', '50', '8');
INSERT INTO `section_metric` VALUES ('69', '51', '22');
INSERT INTO `section_metric` VALUES ('70', '51', '37');
INSERT INTO `section_metric` VALUES ('86', '56', '24');
INSERT INTO `section_metric` VALUES ('87', '57', '8');
INSERT INTO `section_metric` VALUES ('88', '57', '9');
INSERT INTO `section_metric` VALUES ('89', '58', '11');
INSERT INTO `section_metric` VALUES ('90', '58', '34');
INSERT INTO `section_metric` VALUES ('91', '59', '24');
INSERT INTO `section_metric` VALUES ('92', '59', '25');
INSERT INTO `section_metric` VALUES ('93', '59', '26');
INSERT INTO `section_metric` VALUES ('94', '60', '30');
INSERT INTO `section_metric` VALUES ('95', '60', '18');
INSERT INTO `section_metric` VALUES ('96', '61', '37');
INSERT INTO `section_metric` VALUES ('97', '62', '27');
INSERT INTO `section_metric` VALUES ('98', '62', '31');
INSERT INTO `section_metric` VALUES ('99', '63', '2');
INSERT INTO `section_metric` VALUES ('100', '52', '24');
INSERT INTO `section_metric` VALUES ('101', '53', '8');
INSERT INTO `section_metric` VALUES ('102', '53', '9');
INSERT INTO `section_metric` VALUES ('103', '54', '34');
INSERT INTO `section_metric` VALUES ('104', '54', '37');
INSERT INTO `section_metric` VALUES ('105', '64', '2');
INSERT INTO `section_metric` VALUES ('106', '65', '3');
INSERT INTO `section_metric` VALUES ('107', '66', '6');
INSERT INTO `section_metric` VALUES ('108', '67', '29');
INSERT INTO `section_metric` VALUES ('112', '70', '24');
INSERT INTO `section_metric` VALUES ('113', '70', '25');
INSERT INTO `section_metric` VALUES ('114', '68', '3');
INSERT INTO `section_metric` VALUES ('115', '69', '24');
INSERT INTO `section_metric` VALUES ('116', '69', '25');
INSERT INTO `section_metric` VALUES ('136', '85', '40');
INSERT INTO `section_metric` VALUES ('137', '86', '41');
INSERT INTO `section_metric` VALUES ('138', '87', '11');
INSERT INTO `section_metric` VALUES ('148', '88', '24');
INSERT INTO `section_metric` VALUES ('149', '88', '27');
INSERT INTO `section_metric` VALUES ('150', '89', '8');
INSERT INTO `section_metric` VALUES ('151', '89', '9');
INSERT INTO `section_metric` VALUES ('152', '90', '45');
INSERT INTO `section_metric` VALUES ('153', '91', '22');
INSERT INTO `section_metric` VALUES ('154', '92', '24');
INSERT INTO `section_metric` VALUES ('155', '93', '31');
INSERT INTO `section_metric` VALUES ('156', '94', '16');
INSERT INTO `section_metric` VALUES ('157', '95', '47');
INSERT INTO `section_metric` VALUES ('160', '88', '24');
INSERT INTO `section_metric` VALUES ('161', '88', '26');
INSERT INTO `section_metric` VALUES ('162', '90', '40');
INSERT INTO `section_metric` VALUES ('163', '91', '21');
INSERT INTO `section_metric` VALUES ('164', '93', '8');
INSERT INTO `section_metric` VALUES ('165', '94', '41');
INSERT INTO `section_metric` VALUES ('166', '95', '26');
INSERT INTO `section_metric` VALUES ('167', '96', '24');
INSERT INTO `section_metric` VALUES ('169', '98', '24');
INSERT INTO `section_metric` VALUES ('170', '99', '25');
INSERT INTO `section_metric` VALUES ('171', '100', '26');
INSERT INTO `section_metric` VALUES ('172', '101', '27');
INSERT INTO `section_metric` VALUES ('173', '102', '24');
INSERT INTO `section_metric` VALUES ('174', '103', '25');
INSERT INTO `section_metric` VALUES ('175', '104', '26');
INSERT INTO `section_metric` VALUES ('176', '105', '27');
INSERT INTO `section_metric` VALUES ('177', '106', '24');
INSERT INTO `section_metric` VALUES ('180', '107', '24');
INSERT INTO `section_metric` VALUES ('181', '108', '25');
INSERT INTO `section_metric` VALUES ('182', '109', '26');
INSERT INTO `section_metric` VALUES ('183', '110', '27');
INSERT INTO `section_metric` VALUES ('184', '111', '2');
INSERT INTO `section_metric` VALUES ('185', '112', '3');
INSERT INTO `section_metric` VALUES ('186', '113', '8');
INSERT INTO `section_metric` VALUES ('187', '113', '9');
INSERT INTO `section_metric` VALUES ('188', '114', '11');
INSERT INTO `section_metric` VALUES ('189', '118', '8');
INSERT INTO `section_metric` VALUES ('190', '118', '9');
INSERT INTO `section_metric` VALUES ('191', '119', '24');
INSERT INTO `section_metric` VALUES ('192', '99', '25');
INSERT INTO `section_metric` VALUES ('193', '98', '4');
INSERT INTO `section_metric` VALUES ('194', '102', '24');
INSERT INTO `section_metric` VALUES ('195', '104', '26');
INSERT INTO `section_metric` VALUES ('196', '98', '4');
INSERT INTO `section_metric` VALUES ('197', '101', '46');
INSERT INTO `section_metric` VALUES ('198', '104', '26');
INSERT INTO `section_metric` VALUES ('199', '105', '47');
INSERT INTO `section_metric` VALUES ('200', '123', '21');
INSERT INTO `section_metric` VALUES ('201', '124', '24');
INSERT INTO `section_metric` VALUES ('202', '124', '22');
INSERT INTO `section_metric` VALUES ('203', '125', '13');
INSERT INTO `section_metric` VALUES ('204', '126', '18');
INSERT INTO `section_metric` VALUES ('205', '127', '20');
INSERT INTO `section_metric` VALUES ('206', '128', '24');
INSERT INTO `section_metric` VALUES ('207', '129', '26');
INSERT INTO `section_metric` VALUES ('208', '130', '2');
INSERT INTO `section_metric` VALUES ('209', '128', '24');
INSERT INTO `section_metric` VALUES ('210', '129', '26');
INSERT INTO `section_metric` VALUES ('211', '130', '2');
INSERT INTO `section_metric` VALUES ('212', '100', '29');
INSERT INTO `section_metric` VALUES ('213', '101', '43');
INSERT INTO `section_metric` VALUES ('214', '101', '46');
INSERT INTO `section_metric` VALUES ('215', '100', '4');
INSERT INTO `section_metric` VALUES ('216', '131', '24');
INSERT INTO `section_metric` VALUES ('217', '132', '8');
INSERT INTO `section_metric` VALUES ('218', '133', '11');
INSERT INTO `section_metric` VALUES ('219', '134', '29');
INSERT INTO `section_metric` VALUES ('220', '135', '12');
INSERT INTO `section_metric` VALUES ('221', '136', '52');
INSERT INTO `section_metric` VALUES ('222', '137', '20');
INSERT INTO `section_metric` VALUES ('223', '132', '8');
INSERT INTO `section_metric` VALUES ('224', '133', '11');
INSERT INTO `section_metric` VALUES ('225', '134', '29');
INSERT INTO `section_metric` VALUES ('226', '135', '12');
INSERT INTO `section_metric` VALUES ('227', '137', '20');
INSERT INTO `section_metric` VALUES ('228', '138', '24');
INSERT INTO `section_metric` VALUES ('229', '138', '25');
INSERT INTO `section_metric` VALUES ('230', '139', '24');
INSERT INTO `section_metric` VALUES ('231', '140', '25');
INSERT INTO `section_metric` VALUES ('232', '141', '27');
INSERT INTO `section_metric` VALUES ('233', '142', '29');
INSERT INTO `section_metric` VALUES ('234', '133', '11');
INSERT INTO `section_metric` VALUES ('235', '134', '29');
INSERT INTO `section_metric` VALUES ('236', '135', '12');
INSERT INTO `section_metric` VALUES ('237', '136', '43');
INSERT INTO `section_metric` VALUES ('238', '137', '20');
INSERT INTO `section_metric` VALUES ('239', '143', '24');
INSERT INTO `section_metric` VALUES ('240', '144', '25');
INSERT INTO `section_metric` VALUES ('241', '145', '26');
INSERT INTO `section_metric` VALUES ('242', '146', '47');
INSERT INTO `section_metric` VALUES ('243', '147', '24');
INSERT INTO `section_metric` VALUES ('244', '148', '25');
INSERT INTO `section_metric` VALUES ('245', '149', '26');
INSERT INTO `section_metric` VALUES ('246', '150', '27');
INSERT INTO `section_metric` VALUES ('247', '151', '24');
INSERT INTO `section_metric` VALUES ('248', '152', '25');
INSERT INTO `section_metric` VALUES ('249', '153', '26');
INSERT INTO `section_metric` VALUES ('250', '154', '27');
INSERT INTO `section_metric` VALUES ('256', '160', '3');
INSERT INTO `section_metric` VALUES ('257', '161', '24');
INSERT INTO `section_metric` VALUES ('258', '152', '25');
INSERT INTO `section_metric` VALUES ('259', '153', '26');
INSERT INTO `section_metric` VALUES ('260', '154', '27');
INSERT INTO `section_metric` VALUES ('261', '162', '24');
INSERT INTO `section_metric` VALUES ('262', '163', '25');
INSERT INTO `section_metric` VALUES ('263', '164', '26');
INSERT INTO `section_metric` VALUES ('264', '165', '2');
INSERT INTO `section_metric` VALUES ('265', '166', '24');
INSERT INTO `section_metric` VALUES ('266', '166', '27');
INSERT INTO `section_metric` VALUES ('267', '167', '18');

-- ----------------------------
-- Table structure for section_msg_src
-- ----------------------------
DROP TABLE IF EXISTS `section_msg_src`;
CREATE TABLE `section_msg_src` (
  `sec_msg_src_id` int(11) NOT NULL AUTO_INCREMENT,
  `section_id` int(11) DEFAULT NULL,
  `msg_src_id` int(11) DEFAULT NULL,
  `msg_type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`sec_msg_src_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of section_msg_src
-- ----------------------------

-- ----------------------------
-- Table structure for sec_metric_config
-- ----------------------------
DROP TABLE IF EXISTS `sec_metric_config`;
CREATE TABLE `sec_metric_config` (
  `config_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `sec_metric_id` int(11) DEFAULT NULL,
  `param_id` int(11) DEFAULT NULL,
  `param_value` varchar(40) DEFAULT NULL,
  `cre_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3545 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sec_metric_config
-- ----------------------------
INSERT INTO `sec_metric_config` VALUES ('860', '1', '131', '7', 'year', '2018-06-15 10:41:08');
INSERT INTO `sec_metric_config` VALUES ('861', '1', '131', '8', '1', '2018-06-15 10:41:08');
INSERT INTO `sec_metric_config` VALUES ('862', '1', '132', '1', 'C40002', '2018-06-15 10:41:08');
INSERT INTO `sec_metric_config` VALUES ('863', '1', '132', '2', 'C40000', '2018-06-15 10:41:08');
INSERT INTO `sec_metric_config` VALUES ('864', '1', '132', '3', 'obj', '2018-06-15 10:41:08');
INSERT INTO `sec_metric_config` VALUES ('865', '1', '132', '4', 'bar', '2018-06-15 10:41:08');
INSERT INTO `sec_metric_config` VALUES ('866', '1', '132', '5', '1', '2018-06-15 10:41:08');
INSERT INTO `sec_metric_config` VALUES ('867', '1', '132', '6', '单产业法人', '2018-06-15 10:41:08');
INSERT INTO `sec_metric_config` VALUES ('868', '1', '132', '7', 'year', '2018-06-15 10:41:08');
INSERT INTO `sec_metric_config` VALUES ('869', '1', '132', '8', '个', '2018-06-15 10:41:08');
INSERT INTO `sec_metric_config` VALUES ('870', '1', '133', '1', 'C40003', '2018-06-15 10:41:08');
INSERT INTO `sec_metric_config` VALUES ('871', '1', '133', '2', 'C40000', '2018-06-15 10:41:08');
INSERT INTO `sec_metric_config` VALUES ('872', '1', '133', '3', 'obj', '2018-06-15 10:41:08');
INSERT INTO `sec_metric_config` VALUES ('873', '1', '133', '4', 'bar', '2018-06-15 10:41:08');
INSERT INTO `sec_metric_config` VALUES ('874', '1', '133', '5', '2', '2018-06-15 10:41:08');
INSERT INTO `sec_metric_config` VALUES ('875', '1', '133', '6', '多产业法人', '2018-06-15 10:41:08');
INSERT INTO `sec_metric_config` VALUES ('876', '1', '133', '7', 'year', '2018-06-15 10:41:08');
INSERT INTO `sec_metric_config` VALUES ('877', '1', '133', '8', '个', '2018-06-15 10:41:08');
INSERT INTO `sec_metric_config` VALUES ('878', '1', '134', '1', 'C40001', '2018-06-15 10:41:08');
INSERT INTO `sec_metric_config` VALUES ('879', '1', '134', '2', 'C40000', '2018-06-15 10:41:08');
INSERT INTO `sec_metric_config` VALUES ('880', '1', '134', '3', 'obj', '2018-06-15 10:41:08');
INSERT INTO `sec_metric_config` VALUES ('881', '1', '134', '4', 'bar', '2018-06-15 10:41:08');
INSERT INTO `sec_metric_config` VALUES ('882', '1', '134', '5', '3', '2018-06-15 10:41:08');
INSERT INTO `sec_metric_config` VALUES ('883', '1', '134', '6', '法人单位数', '2018-06-15 10:41:08');
INSERT INTO `sec_metric_config` VALUES ('884', '1', '134', '7', 'year', '2018-06-15 10:41:08');
INSERT INTO `sec_metric_config` VALUES ('885', '1', '134', '8', '个', '2018-06-15 10:41:08');
INSERT INTO `sec_metric_config` VALUES ('886', '1', '135', '1', 'C50006', '2018-06-15 10:41:08');
INSERT INTO `sec_metric_config` VALUES ('887', '1', '135', '2', 'C50000', '2018-06-15 10:41:08');
INSERT INTO `sec_metric_config` VALUES ('888', '1', '135', '3', 'obj', '2018-06-15 10:41:08');
INSERT INTO `sec_metric_config` VALUES ('889', '1', '135', '4', 'bar', '2018-06-15 10:41:08');
INSERT INTO `sec_metric_config` VALUES ('890', '1', '135', '5', '2', '2018-06-15 10:41:08');
INSERT INTO `sec_metric_config` VALUES ('891', '1', '135', '6', '个体增长量', '2018-06-15 10:41:08');
INSERT INTO `sec_metric_config` VALUES ('892', '1', '135', '7', 'year', '2018-06-15 10:41:08');
INSERT INTO `sec_metric_config` VALUES ('893', '1', '135', '8', '%', '2018-06-15 10:41:08');
INSERT INTO `sec_metric_config` VALUES ('894', '1', '136', '1', 'C80005', '2018-06-19 08:31:23');
INSERT INTO `sec_metric_config` VALUES ('895', '1', '136', '2', 'C80000', '2018-06-19 08:31:23');
INSERT INTO `sec_metric_config` VALUES ('896', '1', '136', '3', 'obj', '2018-06-19 08:31:23');
INSERT INTO `sec_metric_config` VALUES ('897', '1', '136', '4', 'bar', '2018-06-19 08:31:23');
INSERT INTO `sec_metric_config` VALUES ('898', '1', '136', '5', '1', '2018-06-19 08:31:23');
INSERT INTO `sec_metric_config` VALUES ('899', '1', '136', '6', '进出口总值比上年增长', '2018-06-19 08:31:23');
INSERT INTO `sec_metric_config` VALUES ('900', '1', '136', '7', 'year', '2018-06-19 08:31:23');
INSERT INTO `sec_metric_config` VALUES ('901', '1', '136', '8', '%', '2018-06-19 08:31:23');
INSERT INTO `sec_metric_config` VALUES ('902', '1', '137', '1', 'C80003', '2018-06-19 08:31:23');
INSERT INTO `sec_metric_config` VALUES ('903', '1', '137', '2', 'C80000', '2018-06-19 08:31:23');
INSERT INTO `sec_metric_config` VALUES ('904', '1', '137', '3', 'obj', '2018-06-19 08:31:23');
INSERT INTO `sec_metric_config` VALUES ('905', '1', '137', '4', 'table', '2018-06-19 08:31:23');
INSERT INTO `sec_metric_config` VALUES ('906', '1', '137', '5', '2', '2018-06-19 08:31:23');
INSERT INTO `sec_metric_config` VALUES ('907', '1', '137', '6', '房地产开发投资比上年增长', '2018-06-19 08:31:23');
INSERT INTO `sec_metric_config` VALUES ('908', '1', '137', '7', 'year', '2018-06-19 08:31:23');
INSERT INTO `sec_metric_config` VALUES ('909', '1', '137', '8', '%', '2018-06-19 08:31:23');
INSERT INTO `sec_metric_config` VALUES ('910', '1', '138', '1', 'C40002', '2018-06-19 08:31:23');
INSERT INTO `sec_metric_config` VALUES ('911', '1', '138', '2', 'C40000', '2018-06-19 08:31:23');
INSERT INTO `sec_metric_config` VALUES ('912', '1', '138', '3', 'obj', '2018-06-19 08:31:23');
INSERT INTO `sec_metric_config` VALUES ('913', '1', '138', '4', 'bar', '2018-06-19 08:31:23');
INSERT INTO `sec_metric_config` VALUES ('914', '1', '138', '5', '1', '2018-06-19 08:31:23');
INSERT INTO `sec_metric_config` VALUES ('915', '1', '138', '6', '单产业法人', '2018-06-19 08:31:23');
INSERT INTO `sec_metric_config` VALUES ('916', '1', '138', '7', 'year', '2018-06-19 08:31:23');
INSERT INTO `sec_metric_config` VALUES ('917', '1', '138', '8', '个', '2018-06-19 08:31:23');
INSERT INTO `sec_metric_config` VALUES ('1151', '1', '151', '1', 'C60002', '2018-06-25 10:01:12');
INSERT INTO `sec_metric_config` VALUES ('1152', '1', '151', '2', 'C60000', '2018-06-25 10:01:12');
INSERT INTO `sec_metric_config` VALUES ('1153', '1', '151', '3', 'obj', '2018-06-25 10:01:12');
INSERT INTO `sec_metric_config` VALUES ('1154', '1', '151', '4', 'rose', '2018-06-25 10:01:12');
INSERT INTO `sec_metric_config` VALUES ('1155', '1', '151', '5', '2', '2018-06-25 10:01:12');
INSERT INTO `sec_metric_config` VALUES ('1156', '1', '151', '6', '速度指标', '2018-06-25 10:01:12');
INSERT INTO `sec_metric_config` VALUES ('1157', '1', '151', '7', 'year', '2018-06-25 10:01:12');
INSERT INTO `sec_metric_config` VALUES ('1158', '1', '151', '8', '%', '2018-06-25 10:01:12');
INSERT INTO `sec_metric_config` VALUES ('1159', '1', '151', '9', '0', '2018-06-25 10:01:12');
INSERT INTO `sec_metric_config` VALUES ('1943', '0', '194', '1', 'C20007', '2018-06-29 11:12:00');
INSERT INTO `sec_metric_config` VALUES ('1944', '0', '194', '2', 'C20000', '2018-06-29 11:12:00');
INSERT INTO `sec_metric_config` VALUES ('1945', '0', '194', '3', 'obj', '2018-06-29 11:12:00');
INSERT INTO `sec_metric_config` VALUES ('1946', '0', '194', '4', 'bar', '2018-06-29 11:12:00');
INSERT INTO `sec_metric_config` VALUES ('1947', '0', '194', '5', '1', '2018-06-29 11:12:00');
INSERT INTO `sec_metric_config` VALUES ('1948', '0', '194', '6', '街道办事处占比', '2018-06-29 11:12:00');
INSERT INTO `sec_metric_config` VALUES ('1949', '0', '194', '7', 'year', '2018-06-29 11:12:00');
INSERT INTO `sec_metric_config` VALUES ('1950', '0', '194', '8', '%', '2018-06-29 11:12:00');
INSERT INTO `sec_metric_config` VALUES ('1951', '0', '194', '9', '1', '2018-06-29 11:12:00');
INSERT INTO `sec_metric_config` VALUES ('1952', '0', '174', '1', 'C20008', '2018-06-29 11:12:00');
INSERT INTO `sec_metric_config` VALUES ('1953', '0', '174', '2', 'C20000', '2018-06-29 11:12:00');
INSERT INTO `sec_metric_config` VALUES ('1954', '0', '174', '3', 'obj', '2018-06-29 11:12:00');
INSERT INTO `sec_metric_config` VALUES ('1955', '0', '174', '4', 'line', '2018-06-29 11:12:00');
INSERT INTO `sec_metric_config` VALUES ('1956', '0', '174', '5', '2', '2018-06-29 11:12:00');
INSERT INTO `sec_metric_config` VALUES ('1957', '0', '174', '6', '建制镇占比', '2018-06-29 11:12:00');
INSERT INTO `sec_metric_config` VALUES ('1958', '0', '174', '7', 'year', '2018-06-29 11:12:00');
INSERT INTO `sec_metric_config` VALUES ('1959', '0', '174', '8', '%', '2018-06-29 11:12:00');
INSERT INTO `sec_metric_config` VALUES ('1960', '0', '174', '9', '0', '2018-06-29 11:12:00');
INSERT INTO `sec_metric_config` VALUES ('1961', '0', '198', '1', 'C20009', '2018-06-29 11:12:00');
INSERT INTO `sec_metric_config` VALUES ('1962', '0', '198', '2', 'C20000', '2018-06-29 11:12:00');
INSERT INTO `sec_metric_config` VALUES ('1963', '0', '198', '3', 'obj', '2018-06-29 11:12:00');
INSERT INTO `sec_metric_config` VALUES ('1964', '0', '198', '4', 'pie', '2018-06-29 11:12:00');
INSERT INTO `sec_metric_config` VALUES ('1965', '0', '198', '5', '3', '2018-06-29 11:12:00');
INSERT INTO `sec_metric_config` VALUES ('1966', '0', '198', '6', '建制乡占比', '2018-06-29 11:12:00');
INSERT INTO `sec_metric_config` VALUES ('1967', '0', '198', '7', 'year', '2018-06-29 11:12:00');
INSERT INTO `sec_metric_config` VALUES ('1968', '0', '198', '8', '%', '2018-06-29 11:12:00');
INSERT INTO `sec_metric_config` VALUES ('1969', '0', '198', '9', '0', '2018-06-29 11:12:00');
INSERT INTO `sec_metric_config` VALUES ('1970', '0', '199', '1', 'C80011', '2018-06-29 11:12:00');
INSERT INTO `sec_metric_config` VALUES ('1971', '0', '199', '2', 'C80000', '2018-06-29 11:12:00');
INSERT INTO `sec_metric_config` VALUES ('1972', '0', '199', '3', 'obj', '2018-06-29 11:12:00');
INSERT INTO `sec_metric_config` VALUES ('1973', '0', '199', '4', 'bar', '2018-06-29 11:12:00');
INSERT INTO `sec_metric_config` VALUES ('1974', '0', '199', '5', '9', '2018-06-29 11:12:00');
INSERT INTO `sec_metric_config` VALUES ('1975', '0', '199', '6', '农村居民人均可支配收入比上年名义增长', '2018-06-29 11:12:00');
INSERT INTO `sec_metric_config` VALUES ('1976', '0', '199', '7', 'year', '2018-06-29 11:12:00');
INSERT INTO `sec_metric_config` VALUES ('1977', '0', '199', '8', '%', '2018-06-29 11:12:00');
INSERT INTO `sec_metric_config` VALUES ('1978', '0', '199', '9', '1', '2018-06-29 11:12:00');
INSERT INTO `sec_metric_config` VALUES ('1979', '2', '174', '1', 'C20008', '2018-06-29 11:12:30');
INSERT INTO `sec_metric_config` VALUES ('1980', '2', '174', '2', 'C20000', '2018-06-29 11:12:30');
INSERT INTO `sec_metric_config` VALUES ('1981', '2', '174', '3', 'obj', '2018-06-29 11:12:30');
INSERT INTO `sec_metric_config` VALUES ('1982', '2', '174', '4', 'line', '2018-06-29 11:12:30');
INSERT INTO `sec_metric_config` VALUES ('1983', '2', '174', '5', '2', '2018-06-29 11:12:30');
INSERT INTO `sec_metric_config` VALUES ('1984', '2', '174', '6', '建制镇占比', '2018-06-29 11:12:30');
INSERT INTO `sec_metric_config` VALUES ('1985', '2', '174', '7', 'year', '2018-06-29 11:12:30');
INSERT INTO `sec_metric_config` VALUES ('1986', '2', '174', '8', '%', '2018-06-29 11:12:30');
INSERT INTO `sec_metric_config` VALUES ('1987', '2', '174', '9', '1', '2018-06-29 11:12:30');
INSERT INTO `sec_metric_config` VALUES ('1988', '2', '198', '1', 'C20009', '2018-06-29 11:12:30');
INSERT INTO `sec_metric_config` VALUES ('1989', '2', '198', '2', 'C20000', '2018-06-29 11:12:30');
INSERT INTO `sec_metric_config` VALUES ('1990', '2', '198', '3', 'obj', '2018-06-29 11:12:30');
INSERT INTO `sec_metric_config` VALUES ('1991', '2', '198', '4', 'pie', '2018-06-29 11:12:30');
INSERT INTO `sec_metric_config` VALUES ('1992', '2', '198', '5', '3', '2018-06-29 11:12:30');
INSERT INTO `sec_metric_config` VALUES ('1993', '2', '198', '6', '建制乡占比', '2018-06-29 11:12:30');
INSERT INTO `sec_metric_config` VALUES ('1994', '2', '198', '7', 'year', '2018-06-29 11:12:30');
INSERT INTO `sec_metric_config` VALUES ('1995', '2', '198', '8', '%', '2018-06-29 11:12:30');
INSERT INTO `sec_metric_config` VALUES ('1996', '2', '198', '9', '1', '2018-06-29 11:12:30');
INSERT INTO `sec_metric_config` VALUES ('1997', '2', '199', '1', 'C80011', '2018-06-29 11:12:30');
INSERT INTO `sec_metric_config` VALUES ('1998', '2', '199', '2', 'C80000', '2018-06-29 11:12:30');
INSERT INTO `sec_metric_config` VALUES ('1999', '2', '199', '3', 'obj', '2018-06-29 11:12:30');
INSERT INTO `sec_metric_config` VALUES ('2000', '2', '199', '4', 'bar', '2018-06-29 11:12:30');
INSERT INTO `sec_metric_config` VALUES ('2001', '2', '199', '5', '9', '2018-06-29 11:12:30');
INSERT INTO `sec_metric_config` VALUES ('2002', '2', '199', '6', '农村居民人均可支配收入比上年名义增长', '2018-06-29 11:12:30');
INSERT INTO `sec_metric_config` VALUES ('2003', '2', '199', '7', 'year', '2018-06-29 11:12:30');
INSERT INTO `sec_metric_config` VALUES ('2004', '2', '199', '8', '%', '2018-06-29 11:12:30');
INSERT INTO `sec_metric_config` VALUES ('2005', '2', '199', '9', '0', '2018-06-29 11:12:30');
INSERT INTO `sec_metric_config` VALUES ('2024', '0', '201', '1', 'C20007', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2025', '0', '201', '2', 'C20000', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2026', '0', '201', '3', 'obj', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2027', '0', '201', '4', 'bar', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2028', '0', '201', '5', '1', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2029', '0', '201', '6', '街道办事处占比', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2030', '0', '201', '7', 'year', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2031', '0', '201', '8', '%', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2032', '0', '201', '9', '1', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2033', '0', '202', '1', 'C30002', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2034', '0', '202', '2', 'C30000', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2035', '0', '202', '3', 'obj', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2036', '0', '202', '4', 'bar', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2037', '0', '202', '5', '5', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2038', '0', '202', '6', '单产业法人数', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2039', '0', '202', '7', 'year', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2040', '0', '202', '8', '个', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2041', '0', '202', '9', '1', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2042', '0', '203', '1', 'C40001', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2043', '0', '203', '2', 'C40000', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2044', '0', '203', '3', 'obj', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2045', '0', '203', '4', 'line', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2046', '0', '203', '5', '3', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2047', '0', '203', '6', '法人单位数', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2048', '0', '203', '7', 'year', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2049', '0', '203', '8', '个', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2050', '0', '203', '9', '1', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2051', '0', '204', '1', 'C50004', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2052', '0', '204', '2', 'C50000', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2053', '0', '204', '3', 'obj', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2054', '0', '204', '4', 'line', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2055', '0', '204', '5', '6', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2056', '0', '204', '6', '个体增长率', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2057', '0', '204', '7', 'year', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2058', '0', '204', '8', '%', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2059', '0', '204', '9', '1', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2060', '0', '205', '1', 'C30003', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2061', '0', '205', '2', 'C30000', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2062', '0', '205', '3', 'obj', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2063', '0', '205', '4', 'ringPie', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2064', '0', '205', '5', '1', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2065', '0', '205', '6', '多产业法人数', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2066', '0', '205', '7', 'year', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2067', '0', '205', '8', '个', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2068', '0', '205', '9', '1', '2018-06-29 11:28:08');
INSERT INTO `sec_metric_config` VALUES ('2105', '0', '200', '1', 'C30001', '2018-06-29 11:29:29');
INSERT INTO `sec_metric_config` VALUES ('2106', '0', '200', '2', 'C30000', '2018-06-29 11:29:29');
INSERT INTO `sec_metric_config` VALUES ('2107', '0', '200', '3', 'obj', '2018-06-29 11:29:29');
INSERT INTO `sec_metric_config` VALUES ('2108', '0', '200', '4', 'bar', '2018-06-29 11:29:29');
INSERT INTO `sec_metric_config` VALUES ('2109', '0', '200', '5', '4', '2018-06-29 11:29:29');
INSERT INTO `sec_metric_config` VALUES ('2110', '0', '200', '6', '法人单位数', '2018-06-29 11:29:29');
INSERT INTO `sec_metric_config` VALUES ('2111', '0', '200', '7', 'year', '2018-06-29 11:29:29');
INSERT INTO `sec_metric_config` VALUES ('2112', '0', '200', '8', '个', '2018-06-29 11:29:29');
INSERT INTO `sec_metric_config` VALUES ('2113', '0', '200', '9', '1', '2018-06-29 11:29:29');
INSERT INTO `sec_metric_config` VALUES ('2114', '0', '209', '1', 'C20007', '2018-06-29 11:29:29');
INSERT INTO `sec_metric_config` VALUES ('2115', '0', '209', '2', 'C20000', '2018-06-29 11:29:29');
INSERT INTO `sec_metric_config` VALUES ('2116', '0', '209', '3', 'obj', '2018-06-29 11:29:29');
INSERT INTO `sec_metric_config` VALUES ('2117', '0', '209', '4', 'line', '2018-06-29 11:29:29');
INSERT INTO `sec_metric_config` VALUES ('2118', '0', '209', '5', '1', '2018-06-29 11:29:29');
INSERT INTO `sec_metric_config` VALUES ('2119', '0', '209', '6', '街道办事处占比', '2018-06-29 11:29:29');
INSERT INTO `sec_metric_config` VALUES ('2120', '0', '209', '7', 'year', '2018-06-29 11:29:29');
INSERT INTO `sec_metric_config` VALUES ('2121', '0', '209', '8', '%', '2018-06-29 11:29:29');
INSERT INTO `sec_metric_config` VALUES ('2122', '0', '209', '9', '1', '2018-06-29 11:29:29');
INSERT INTO `sec_metric_config` VALUES ('2123', '0', '210', '1', 'C20009', '2018-06-29 11:29:29');
INSERT INTO `sec_metric_config` VALUES ('2124', '0', '210', '2', 'C20000', '2018-06-29 11:29:29');
INSERT INTO `sec_metric_config` VALUES ('2125', '0', '210', '3', 'obj', '2018-06-29 11:29:29');
INSERT INTO `sec_metric_config` VALUES ('2126', '0', '210', '4', 'gauge', '2018-06-29 11:29:29');
INSERT INTO `sec_metric_config` VALUES ('2127', '0', '210', '5', '3', '2018-06-29 11:29:29');
INSERT INTO `sec_metric_config` VALUES ('2128', '0', '210', '6', '建制乡占比', '2018-06-29 11:29:29');
INSERT INTO `sec_metric_config` VALUES ('2129', '0', '210', '7', 'year', '2018-06-29 11:29:29');
INSERT INTO `sec_metric_config` VALUES ('2130', '0', '210', '8', '%', '2018-06-29 11:29:29');
INSERT INTO `sec_metric_config` VALUES ('2131', '0', '210', '9', '1', '2018-06-29 11:29:29');
INSERT INTO `sec_metric_config` VALUES ('2132', '0', '211', '1', 'C20001', '2018-06-29 11:29:29');
INSERT INTO `sec_metric_config` VALUES ('2133', '0', '211', '2', 'C20000', '2018-06-29 11:29:29');
INSERT INTO `sec_metric_config` VALUES ('2134', '0', '211', '3', 'obj', '2018-06-29 11:29:29');
INSERT INTO `sec_metric_config` VALUES ('2135', '0', '211', '4', 'ringPie', '2018-06-29 11:29:29');
INSERT INTO `sec_metric_config` VALUES ('2136', '0', '211', '5', '5', '2018-06-29 11:29:29');
INSERT INTO `sec_metric_config` VALUES ('2137', '0', '211', '6', '街道办事处', '2018-06-29 11:29:29');
INSERT INTO `sec_metric_config` VALUES ('2138', '0', '211', '7', 'year', '2018-06-29 11:29:29');
INSERT INTO `sec_metric_config` VALUES ('2139', '0', '211', '8', '个', '2018-06-29 11:29:29');
INSERT INTO `sec_metric_config` VALUES ('2140', '0', '211', '9', '1', '2018-06-29 11:29:29');
INSERT INTO `sec_metric_config` VALUES ('2141', '2', '211', '1', 'C20001', '2018-06-29 11:30:42');
INSERT INTO `sec_metric_config` VALUES ('2142', '2', '211', '2', 'C20000', '2018-06-29 11:30:42');
INSERT INTO `sec_metric_config` VALUES ('2143', '2', '211', '3', 'obj', '2018-06-29 11:30:42');
INSERT INTO `sec_metric_config` VALUES ('2144', '2', '211', '4', 'ringPie', '2018-06-29 11:30:42');
INSERT INTO `sec_metric_config` VALUES ('2145', '2', '211', '5', '5', '2018-06-29 11:30:42');
INSERT INTO `sec_metric_config` VALUES ('2146', '2', '211', '6', '街道办事处', '2018-06-29 11:30:42');
INSERT INTO `sec_metric_config` VALUES ('2147', '2', '211', '7', 'year', '2018-06-29 11:30:42');
INSERT INTO `sec_metric_config` VALUES ('2148', '2', '211', '8', '个', '2018-06-29 11:30:42');
INSERT INTO `sec_metric_config` VALUES ('2149', '2', '211', '9', '0', '2018-06-29 11:30:42');
INSERT INTO `sec_metric_config` VALUES ('2150', '2', '205', '1', 'C30003', '2018-06-29 11:30:52');
INSERT INTO `sec_metric_config` VALUES ('2151', '2', '205', '2', 'C30000', '2018-06-29 11:30:52');
INSERT INTO `sec_metric_config` VALUES ('2152', '2', '205', '3', 'obj', '2018-06-29 11:30:52');
INSERT INTO `sec_metric_config` VALUES ('2153', '2', '205', '4', 'ringPie', '2018-06-29 11:30:52');
INSERT INTO `sec_metric_config` VALUES ('2154', '2', '205', '5', '1', '2018-06-29 11:30:52');
INSERT INTO `sec_metric_config` VALUES ('2155', '2', '205', '6', '多产业法人数', '2018-06-29 11:30:52');
INSERT INTO `sec_metric_config` VALUES ('2156', '2', '205', '7', 'year', '2018-06-29 11:30:52');
INSERT INTO `sec_metric_config` VALUES ('2157', '2', '205', '8', '个', '2018-06-29 11:30:52');
INSERT INTO `sec_metric_config` VALUES ('2158', '2', '205', '9', '0', '2018-06-29 11:30:52');
INSERT INTO `sec_metric_config` VALUES ('2159', '2', '210', '1', 'C20009', '2018-06-29 11:32:58');
INSERT INTO `sec_metric_config` VALUES ('2160', '2', '210', '2', 'C20000', '2018-06-29 11:32:58');
INSERT INTO `sec_metric_config` VALUES ('2161', '2', '210', '3', 'obj', '2018-06-29 11:32:58');
INSERT INTO `sec_metric_config` VALUES ('2162', '2', '210', '4', 'gauge', '2018-06-29 11:32:58');
INSERT INTO `sec_metric_config` VALUES ('2163', '2', '210', '5', '3', '2018-06-29 11:32:58');
INSERT INTO `sec_metric_config` VALUES ('2164', '2', '210', '6', '建制乡占比', '2018-06-29 11:32:58');
INSERT INTO `sec_metric_config` VALUES ('2165', '2', '210', '7', 'year', '2018-06-29 11:32:58');
INSERT INTO `sec_metric_config` VALUES ('2166', '2', '210', '8', '%', '2018-06-29 11:32:58');
INSERT INTO `sec_metric_config` VALUES ('2167', '2', '210', '9', '0', '2018-06-29 11:32:58');
INSERT INTO `sec_metric_config` VALUES ('2168', '2', '211', '1', 'C20001', '2018-06-29 11:32:58');
INSERT INTO `sec_metric_config` VALUES ('2169', '2', '211', '2', 'C20000', '2018-06-29 11:32:58');
INSERT INTO `sec_metric_config` VALUES ('2170', '2', '211', '3', 'obj', '2018-06-29 11:32:58');
INSERT INTO `sec_metric_config` VALUES ('2171', '2', '211', '4', 'ringPie', '2018-06-29 11:32:58');
INSERT INTO `sec_metric_config` VALUES ('2172', '2', '211', '5', '5', '2018-06-29 11:32:58');
INSERT INTO `sec_metric_config` VALUES ('2173', '2', '211', '6', '街道办事处', '2018-06-29 11:32:58');
INSERT INTO `sec_metric_config` VALUES ('2174', '2', '211', '7', 'year', '2018-06-29 11:32:58');
INSERT INTO `sec_metric_config` VALUES ('2175', '2', '211', '8', '个', '2018-06-29 11:32:58');
INSERT INTO `sec_metric_config` VALUES ('2176', '2', '211', '9', '0', '2018-06-29 11:32:58');
INSERT INTO `sec_metric_config` VALUES ('2177', '1', '202', '1', 'C30002', '2018-06-29 14:05:39');
INSERT INTO `sec_metric_config` VALUES ('2178', '1', '202', '2', 'C30000', '2018-06-29 14:05:39');
INSERT INTO `sec_metric_config` VALUES ('2179', '1', '202', '3', 'obj', '2018-06-29 14:05:39');
INSERT INTO `sec_metric_config` VALUES ('2180', '1', '202', '4', 'bar', '2018-06-29 14:05:39');
INSERT INTO `sec_metric_config` VALUES ('2181', '1', '202', '5', '5', '2018-06-29 14:05:39');
INSERT INTO `sec_metric_config` VALUES ('2182', '1', '202', '6', '单产业法人数', '2018-06-29 14:05:39');
INSERT INTO `sec_metric_config` VALUES ('2183', '1', '202', '7', 'year', '2018-06-29 14:05:39');
INSERT INTO `sec_metric_config` VALUES ('2184', '1', '202', '8', '个', '2018-06-29 14:05:39');
INSERT INTO `sec_metric_config` VALUES ('2185', '1', '202', '9', '0', '2018-06-29 14:05:39');
INSERT INTO `sec_metric_config` VALUES ('2186', '1', '202', '1', 'C30002', '2018-06-29 14:06:55');
INSERT INTO `sec_metric_config` VALUES ('2187', '1', '202', '2', 'C30000', '2018-06-29 14:06:55');
INSERT INTO `sec_metric_config` VALUES ('2188', '1', '202', '3', 'obj', '2018-06-29 14:06:55');
INSERT INTO `sec_metric_config` VALUES ('2189', '1', '202', '4', 'bar', '2018-06-29 14:06:55');
INSERT INTO `sec_metric_config` VALUES ('2190', '1', '202', '5', '5', '2018-06-29 14:06:55');
INSERT INTO `sec_metric_config` VALUES ('2191', '1', '202', '6', '单产业法人数', '2018-06-29 14:06:55');
INSERT INTO `sec_metric_config` VALUES ('2192', '1', '202', '7', 'year', '2018-06-29 14:06:55');
INSERT INTO `sec_metric_config` VALUES ('2193', '1', '202', '8', '个', '2018-06-29 14:06:55');
INSERT INTO `sec_metric_config` VALUES ('2194', '1', '202', '9', '1', '2018-06-29 14:06:55');
INSERT INTO `sec_metric_config` VALUES ('2195', '1', '203', '1', 'C40001', '2018-06-29 14:22:31');
INSERT INTO `sec_metric_config` VALUES ('2196', '1', '203', '2', 'C40000', '2018-06-29 14:22:31');
INSERT INTO `sec_metric_config` VALUES ('2197', '1', '203', '3', 'obj', '2018-06-29 14:22:31');
INSERT INTO `sec_metric_config` VALUES ('2198', '1', '203', '4', 'line', '2018-06-29 14:22:31');
INSERT INTO `sec_metric_config` VALUES ('2199', '1', '203', '5', '3', '2018-06-29 14:22:31');
INSERT INTO `sec_metric_config` VALUES ('2200', '1', '203', '6', '法人单位数', '2018-06-29 14:22:31');
INSERT INTO `sec_metric_config` VALUES ('2201', '1', '203', '7', 'year', '2018-06-29 14:22:31');
INSERT INTO `sec_metric_config` VALUES ('2202', '1', '203', '8', '个', '2018-06-29 14:22:31');
INSERT INTO `sec_metric_config` VALUES ('2203', '1', '203', '9', '0', '2018-06-29 14:22:31');
INSERT INTO `sec_metric_config` VALUES ('2204', '1', '200', '1', 'C30001', '2018-06-29 14:26:14');
INSERT INTO `sec_metric_config` VALUES ('2205', '1', '200', '2', 'C30000', '2018-06-29 14:26:14');
INSERT INTO `sec_metric_config` VALUES ('2206', '1', '200', '3', 'obj', '2018-06-29 14:26:14');
INSERT INTO `sec_metric_config` VALUES ('2207', '1', '200', '4', 'bar', '2018-06-29 14:26:14');
INSERT INTO `sec_metric_config` VALUES ('2208', '1', '200', '5', '4', '2018-06-29 14:26:14');
INSERT INTO `sec_metric_config` VALUES ('2209', '1', '200', '6', '法人单位数', '2018-06-29 14:26:14');
INSERT INTO `sec_metric_config` VALUES ('2210', '1', '200', '7', 'year', '2018-06-29 14:26:14');
INSERT INTO `sec_metric_config` VALUES ('2211', '1', '200', '8', '个', '2018-06-29 14:26:14');
INSERT INTO `sec_metric_config` VALUES ('2212', '1', '200', '9', '0', '2018-06-29 14:26:14');
INSERT INTO `sec_metric_config` VALUES ('2213', '1', '200', '1', 'C30001', '2018-06-29 14:26:35');
INSERT INTO `sec_metric_config` VALUES ('2214', '1', '200', '2', 'C30000', '2018-06-29 14:26:35');
INSERT INTO `sec_metric_config` VALUES ('2215', '1', '200', '3', 'obj', '2018-06-29 14:26:35');
INSERT INTO `sec_metric_config` VALUES ('2216', '1', '200', '4', 'bar', '2018-06-29 14:26:35');
INSERT INTO `sec_metric_config` VALUES ('2217', '1', '200', '5', '4', '2018-06-29 14:26:35');
INSERT INTO `sec_metric_config` VALUES ('2218', '1', '200', '6', '法人单位数', '2018-06-29 14:26:35');
INSERT INTO `sec_metric_config` VALUES ('2219', '1', '200', '7', 'year', '2018-06-29 14:26:35');
INSERT INTO `sec_metric_config` VALUES ('2220', '1', '200', '8', '个', '2018-06-29 14:26:35');
INSERT INTO `sec_metric_config` VALUES ('2221', '1', '200', '9', '1', '2018-06-29 14:26:35');
INSERT INTO `sec_metric_config` VALUES ('2231', '1', '174', '1', 'C20008', '2018-07-03 16:41:44');
INSERT INTO `sec_metric_config` VALUES ('2232', '1', '174', '2', 'C20000', '2018-07-03 16:41:44');
INSERT INTO `sec_metric_config` VALUES ('2233', '1', '174', '3', 'obj', '2018-07-03 16:41:44');
INSERT INTO `sec_metric_config` VALUES ('2234', '1', '174', '4', 'line', '2018-07-03 16:41:44');
INSERT INTO `sec_metric_config` VALUES ('2235', '1', '174', '5', '2', '2018-07-03 16:41:44');
INSERT INTO `sec_metric_config` VALUES ('2236', '1', '174', '6', '建制镇占比', '2018-07-03 16:41:44');
INSERT INTO `sec_metric_config` VALUES ('2237', '1', '174', '7', 'year', '2018-07-03 16:41:44');
INSERT INTO `sec_metric_config` VALUES ('2238', '1', '174', '8', '%', '2018-07-03 16:41:44');
INSERT INTO `sec_metric_config` VALUES ('2239', '1', '174', '9', '1', '2018-07-03 16:41:44');
INSERT INTO `sec_metric_config` VALUES ('2240', '1', '198', '1', 'C20009', '2018-07-03 16:41:44');
INSERT INTO `sec_metric_config` VALUES ('2241', '1', '198', '2', 'C20000', '2018-07-03 16:41:44');
INSERT INTO `sec_metric_config` VALUES ('2242', '1', '198', '3', 'obj', '2018-07-03 16:41:44');
INSERT INTO `sec_metric_config` VALUES ('2243', '1', '198', '4', 'pie', '2018-07-03 16:41:44');
INSERT INTO `sec_metric_config` VALUES ('2244', '1', '198', '5', '3', '2018-07-03 16:41:44');
INSERT INTO `sec_metric_config` VALUES ('2245', '1', '198', '6', '建制乡占比', '2018-07-03 16:41:44');
INSERT INTO `sec_metric_config` VALUES ('2246', '1', '198', '7', 'year', '2018-07-03 16:41:44');
INSERT INTO `sec_metric_config` VALUES ('2247', '1', '198', '8', '%', '2018-07-03 16:41:44');
INSERT INTO `sec_metric_config` VALUES ('2248', '1', '198', '9', '1', '2018-07-03 16:41:44');
INSERT INTO `sec_metric_config` VALUES ('2249', '0', '177', '1', 'C20007', '2018-07-03 16:43:12');
INSERT INTO `sec_metric_config` VALUES ('2250', '0', '177', '2', 'C20000', '2018-07-03 16:43:12');
INSERT INTO `sec_metric_config` VALUES ('2251', '0', '177', '3', 'obj', '2018-07-03 16:43:12');
INSERT INTO `sec_metric_config` VALUES ('2252', '0', '177', '4', 'line', '2018-07-03 16:43:12');
INSERT INTO `sec_metric_config` VALUES ('2253', '0', '177', '5', '1', '2018-07-03 16:43:12');
INSERT INTO `sec_metric_config` VALUES ('2254', '0', '177', '6', '街道办事处占比', '2018-07-03 16:43:12');
INSERT INTO `sec_metric_config` VALUES ('2255', '0', '177', '7', 'year', '2018-07-03 16:43:12');
INSERT INTO `sec_metric_config` VALUES ('2256', '0', '177', '8', 'undefined', '2018-07-03 16:43:12');
INSERT INTO `sec_metric_config` VALUES ('2257', '0', '177', '9', '1', '2018-07-03 16:43:12');
INSERT INTO `sec_metric_config` VALUES ('2312', '1', '177', '1', 'C20007', '2018-07-03 17:18:47');
INSERT INTO `sec_metric_config` VALUES ('2313', '1', '177', '2', 'C20000', '2018-07-03 17:18:47');
INSERT INTO `sec_metric_config` VALUES ('2314', '1', '177', '3', 'obj', '2018-07-03 17:18:47');
INSERT INTO `sec_metric_config` VALUES ('2315', '1', '177', '4', 'line', '2018-07-03 17:18:47');
INSERT INTO `sec_metric_config` VALUES ('2316', '1', '177', '5', '1', '2018-07-03 17:18:47');
INSERT INTO `sec_metric_config` VALUES ('2317', '1', '177', '6', '街道办事处占比', '2018-07-03 17:18:47');
INSERT INTO `sec_metric_config` VALUES ('2318', '1', '177', '7', 'year', '2018-07-03 17:18:47');
INSERT INTO `sec_metric_config` VALUES ('2319', '1', '177', '8', 'undefined', '2018-07-03 17:18:47');
INSERT INTO `sec_metric_config` VALUES ('2320', '1', '177', '9', '0', '2018-07-03 17:18:47');
INSERT INTO `sec_metric_config` VALUES ('2339', '1', '177', '1', 'C20007', '2018-07-03 17:29:05');
INSERT INTO `sec_metric_config` VALUES ('2340', '1', '177', '2', 'C20000', '2018-07-03 17:29:05');
INSERT INTO `sec_metric_config` VALUES ('2341', '1', '177', '3', 'obj', '2018-07-03 17:29:05');
INSERT INTO `sec_metric_config` VALUES ('2342', '1', '177', '4', 'line', '2018-07-03 17:29:05');
INSERT INTO `sec_metric_config` VALUES ('2343', '1', '177', '5', '1', '2018-07-03 17:29:05');
INSERT INTO `sec_metric_config` VALUES ('2344', '1', '177', '6', '街道办事处占比', '2018-07-03 17:29:05');
INSERT INTO `sec_metric_config` VALUES ('2345', '1', '177', '7', 'year', '2018-07-03 17:29:05');
INSERT INTO `sec_metric_config` VALUES ('2346', '1', '177', '8', 'undefined', '2018-07-03 17:29:05');
INSERT INTO `sec_metric_config` VALUES ('2347', '1', '177', '9', '1', '2018-07-03 17:29:05');
INSERT INTO `sec_metric_config` VALUES ('2348', '1', '199', '1', 'C80011', '2018-07-03 17:29:22');
INSERT INTO `sec_metric_config` VALUES ('2349', '1', '199', '2', 'C80000', '2018-07-03 17:29:22');
INSERT INTO `sec_metric_config` VALUES ('2350', '1', '199', '3', 'obj', '2018-07-03 17:29:22');
INSERT INTO `sec_metric_config` VALUES ('2351', '1', '199', '4', 'bar', '2018-07-03 17:29:22');
INSERT INTO `sec_metric_config` VALUES ('2352', '1', '199', '5', '9', '2018-07-03 17:29:22');
INSERT INTO `sec_metric_config` VALUES ('2353', '1', '199', '6', '农村居民人均可支配收入比上年名义增长', '2018-07-03 17:29:22');
INSERT INTO `sec_metric_config` VALUES ('2354', '1', '199', '7', 'year', '2018-07-03 17:29:22');
INSERT INTO `sec_metric_config` VALUES ('2355', '1', '199', '8', '%', '2018-07-03 17:29:22');
INSERT INTO `sec_metric_config` VALUES ('2356', '1', '199', '9', '0', '2018-07-03 17:29:22');
INSERT INTO `sec_metric_config` VALUES ('2357', '1', '177', '1', 'C20007', '2018-07-03 17:50:12');
INSERT INTO `sec_metric_config` VALUES ('2358', '1', '177', '2', 'C20000', '2018-07-03 17:50:12');
INSERT INTO `sec_metric_config` VALUES ('2359', '1', '177', '3', 'obj', '2018-07-03 17:50:12');
INSERT INTO `sec_metric_config` VALUES ('2360', '1', '177', '4', 'line', '2018-07-03 17:50:12');
INSERT INTO `sec_metric_config` VALUES ('2361', '1', '177', '5', '1', '2018-07-03 17:50:12');
INSERT INTO `sec_metric_config` VALUES ('2362', '1', '177', '6', '街道办事处占比', '2018-07-03 17:50:12');
INSERT INTO `sec_metric_config` VALUES ('2363', '1', '177', '7', 'year', '2018-07-03 17:50:12');
INSERT INTO `sec_metric_config` VALUES ('2364', '1', '177', '8', 'undefined', '2018-07-03 17:50:12');
INSERT INTO `sec_metric_config` VALUES ('2365', '1', '177', '9', '0', '2018-07-03 17:50:12');
INSERT INTO `sec_metric_config` VALUES ('2375', '1', '177', '1', 'C20007', '2018-07-03 17:50:35');
INSERT INTO `sec_metric_config` VALUES ('2376', '1', '177', '2', 'C20000', '2018-07-03 17:50:35');
INSERT INTO `sec_metric_config` VALUES ('2377', '1', '177', '3', 'obj', '2018-07-03 17:50:35');
INSERT INTO `sec_metric_config` VALUES ('2378', '1', '177', '4', 'line', '2018-07-03 17:50:35');
INSERT INTO `sec_metric_config` VALUES ('2379', '1', '177', '5', '1', '2018-07-03 17:50:35');
INSERT INTO `sec_metric_config` VALUES ('2380', '1', '177', '6', '街道办事处占比', '2018-07-03 17:50:35');
INSERT INTO `sec_metric_config` VALUES ('2381', '1', '177', '7', 'year', '2018-07-03 17:50:35');
INSERT INTO `sec_metric_config` VALUES ('2382', '1', '177', '8', 'undefined', '2018-07-03 17:50:35');
INSERT INTO `sec_metric_config` VALUES ('2383', '1', '177', '9', '0', '2018-07-03 17:50:35');
INSERT INTO `sec_metric_config` VALUES ('2402', '1', '177', '1', 'C20007', '2018-07-03 17:54:51');
INSERT INTO `sec_metric_config` VALUES ('2403', '1', '177', '2', 'C20000', '2018-07-03 17:54:51');
INSERT INTO `sec_metric_config` VALUES ('2404', '1', '177', '3', 'obj', '2018-07-03 17:54:51');
INSERT INTO `sec_metric_config` VALUES ('2405', '1', '177', '4', 'line', '2018-07-03 17:54:51');
INSERT INTO `sec_metric_config` VALUES ('2406', '1', '177', '5', '1', '2018-07-03 17:54:51');
INSERT INTO `sec_metric_config` VALUES ('2407', '1', '177', '6', '街道办事处占比', '2018-07-03 17:54:51');
INSERT INTO `sec_metric_config` VALUES ('2408', '1', '177', '7', 'year', '2018-07-03 17:54:51');
INSERT INTO `sec_metric_config` VALUES ('2409', '1', '177', '8', 'undefined', '2018-07-03 17:54:51');
INSERT INTO `sec_metric_config` VALUES ('2410', '1', '177', '9', '1', '2018-07-03 17:54:51');
INSERT INTO `sec_metric_config` VALUES ('2420', '1', '177', '1', 'C20007', '2018-07-03 17:59:43');
INSERT INTO `sec_metric_config` VALUES ('2421', '1', '177', '2', 'C20000', '2018-07-03 17:59:43');
INSERT INTO `sec_metric_config` VALUES ('2422', '1', '177', '3', 'obj', '2018-07-03 17:59:43');
INSERT INTO `sec_metric_config` VALUES ('2423', '1', '177', '4', 'line', '2018-07-03 17:59:43');
INSERT INTO `sec_metric_config` VALUES ('2424', '1', '177', '5', '1', '2018-07-03 17:59:43');
INSERT INTO `sec_metric_config` VALUES ('2425', '1', '177', '6', '街道办事处占比', '2018-07-03 17:59:43');
INSERT INTO `sec_metric_config` VALUES ('2426', '1', '177', '7', 'year', '2018-07-03 17:59:43');
INSERT INTO `sec_metric_config` VALUES ('2427', '1', '177', '8', 'undefined', '2018-07-03 17:59:43');
INSERT INTO `sec_metric_config` VALUES ('2428', '1', '177', '9', '0', '2018-07-03 17:59:43');
INSERT INTO `sec_metric_config` VALUES ('2537', '0', '193', '1', 'C20003', '2018-07-03 18:15:11');
INSERT INTO `sec_metric_config` VALUES ('2538', '0', '193', '2', 'C20000', '2018-07-03 18:15:11');
INSERT INTO `sec_metric_config` VALUES ('2539', '0', '193', '3', 'obj', '2018-07-03 18:15:11');
INSERT INTO `sec_metric_config` VALUES ('2540', '0', '193', '4', 'bar', '2018-07-03 18:15:11');
INSERT INTO `sec_metric_config` VALUES ('2541', '0', '193', '5', '7', '2018-07-03 18:15:11');
INSERT INTO `sec_metric_config` VALUES ('2542', '0', '193', '6', '建制乡', '2018-07-03 18:15:11');
INSERT INTO `sec_metric_config` VALUES ('2543', '0', '193', '7', 'year', '2018-07-03 18:15:11');
INSERT INTO `sec_metric_config` VALUES ('2544', '0', '193', '8', '个', '2018-07-03 18:15:11');
INSERT INTO `sec_metric_config` VALUES ('2545', '0', '193', '9', '1', '2018-07-03 18:15:11');
INSERT INTO `sec_metric_config` VALUES ('2546', '0', '170', '1', 'C20008', '2018-07-03 18:15:11');
INSERT INTO `sec_metric_config` VALUES ('2547', '0', '170', '2', 'C20000', '2018-07-03 18:15:11');
INSERT INTO `sec_metric_config` VALUES ('2548', '0', '170', '3', 'obj', '2018-07-03 18:15:11');
INSERT INTO `sec_metric_config` VALUES ('2549', '0', '170', '4', 'line', '2018-07-03 18:15:11');
INSERT INTO `sec_metric_config` VALUES ('2550', '0', '170', '5', '2', '2018-07-03 18:15:11');
INSERT INTO `sec_metric_config` VALUES ('2551', '0', '170', '6', '建制镇占比', '2018-07-03 18:15:11');
INSERT INTO `sec_metric_config` VALUES ('2552', '0', '170', '7', 'year', '2018-07-03 18:15:11');
INSERT INTO `sec_metric_config` VALUES ('2553', '0', '170', '8', '%', '2018-07-03 18:15:11');
INSERT INTO `sec_metric_config` VALUES ('2554', '0', '170', '9', '1', '2018-07-03 18:15:11');
INSERT INTO `sec_metric_config` VALUES ('2555', '0', '215', '1', 'C20003', '2018-07-03 18:15:11');
INSERT INTO `sec_metric_config` VALUES ('2556', '0', '215', '2', 'C20000', '2018-07-03 18:15:11');
INSERT INTO `sec_metric_config` VALUES ('2557', '0', '215', '3', 'obj', '2018-07-03 18:15:11');
INSERT INTO `sec_metric_config` VALUES ('2558', '0', '215', '4', 'pie', '2018-07-03 18:15:11');
INSERT INTO `sec_metric_config` VALUES ('2559', '0', '215', '5', '7', '2018-07-03 18:15:11');
INSERT INTO `sec_metric_config` VALUES ('2560', '0', '215', '6', '建制乡', '2018-07-03 18:15:11');
INSERT INTO `sec_metric_config` VALUES ('2561', '0', '215', '7', 'year', '2018-07-03 18:15:11');
INSERT INTO `sec_metric_config` VALUES ('2562', '0', '215', '8', '个', '2018-07-03 18:15:11');
INSERT INTO `sec_metric_config` VALUES ('2563', '0', '215', '9', '1', '2018-07-03 18:15:11');
INSERT INTO `sec_metric_config` VALUES ('2564', '0', '214', '1', 'C80010', '2018-07-03 18:15:11');
INSERT INTO `sec_metric_config` VALUES ('2565', '0', '214', '2', 'C80000', '2018-07-03 18:15:11');
INSERT INTO `sec_metric_config` VALUES ('2566', '0', '214', '3', 'obj', '2018-07-03 18:15:11');
INSERT INTO `sec_metric_config` VALUES ('2567', '0', '214', '4', 'gauge', '2018-07-03 18:15:11');
INSERT INTO `sec_metric_config` VALUES ('2568', '0', '214', '5', '8', '2018-07-03 18:15:11');
INSERT INTO `sec_metric_config` VALUES ('2569', '0', '214', '6', '城镇居民人均可支配收入比上年实际增长', '2018-07-03 18:15:11');
INSERT INTO `sec_metric_config` VALUES ('2570', '0', '214', '7', 'year', '2018-07-03 18:15:11');
INSERT INTO `sec_metric_config` VALUES ('2571', '0', '214', '8', '%', '2018-07-03 18:15:11');
INSERT INTO `sec_metric_config` VALUES ('2572', '0', '214', '9', '1', '2018-07-03 18:15:11');
INSERT INTO `sec_metric_config` VALUES ('2573', '1', '214', '1', 'C80010', '2018-07-03 18:15:20');
INSERT INTO `sec_metric_config` VALUES ('2574', '1', '214', '2', 'C80000', '2018-07-03 18:15:20');
INSERT INTO `sec_metric_config` VALUES ('2575', '1', '214', '3', 'obj', '2018-07-03 18:15:20');
INSERT INTO `sec_metric_config` VALUES ('2576', '1', '214', '4', 'gauge', '2018-07-03 18:15:20');
INSERT INTO `sec_metric_config` VALUES ('2577', '1', '214', '5', '8', '2018-07-03 18:15:20');
INSERT INTO `sec_metric_config` VALUES ('2578', '1', '214', '6', '城镇居民人均可支配收入比上年实际增长', '2018-07-03 18:15:20');
INSERT INTO `sec_metric_config` VALUES ('2579', '1', '214', '7', 'year', '2018-07-03 18:15:20');
INSERT INTO `sec_metric_config` VALUES ('2580', '1', '214', '8', '%', '2018-07-03 18:15:20');
INSERT INTO `sec_metric_config` VALUES ('2581', '1', '214', '9', '0', '2018-07-03 18:15:20');
INSERT INTO `sec_metric_config` VALUES ('2582', '1', '214', '1', 'C80010', '2018-07-04 10:50:28');
INSERT INTO `sec_metric_config` VALUES ('2583', '1', '214', '2', 'C80000', '2018-07-04 10:50:28');
INSERT INTO `sec_metric_config` VALUES ('2584', '1', '214', '3', 'obj', '2018-07-04 10:50:28');
INSERT INTO `sec_metric_config` VALUES ('2585', '1', '214', '4', 'gauge', '2018-07-04 10:50:28');
INSERT INTO `sec_metric_config` VALUES ('2586', '1', '214', '5', '8', '2018-07-04 10:50:28');
INSERT INTO `sec_metric_config` VALUES ('2587', '1', '214', '6', '城镇居民人均可支配收入比上年实际增长', '2018-07-04 10:50:28');
INSERT INTO `sec_metric_config` VALUES ('2588', '1', '214', '7', 'year', '2018-07-04 10:50:28');
INSERT INTO `sec_metric_config` VALUES ('2589', '1', '214', '8', '%', '2018-07-04 10:50:28');
INSERT INTO `sec_metric_config` VALUES ('2590', '1', '214', '9', '1', '2018-07-04 10:50:28');
INSERT INTO `sec_metric_config` VALUES ('2591', '1', '177', '1', 'C20007', '2018-07-04 10:52:57');
INSERT INTO `sec_metric_config` VALUES ('2592', '1', '177', '2', 'C20000', '2018-07-04 10:52:57');
INSERT INTO `sec_metric_config` VALUES ('2593', '1', '177', '3', 'obj', '2018-07-04 10:52:57');
INSERT INTO `sec_metric_config` VALUES ('2594', '1', '177', '4', 'line', '2018-07-04 10:52:57');
INSERT INTO `sec_metric_config` VALUES ('2595', '1', '177', '5', '1', '2018-07-04 10:52:57');
INSERT INTO `sec_metric_config` VALUES ('2596', '1', '177', '6', '街道办事处占比', '2018-07-04 10:52:57');
INSERT INTO `sec_metric_config` VALUES ('2597', '1', '177', '7', 'year', '2018-07-04 10:52:57');
INSERT INTO `sec_metric_config` VALUES ('2598', '1', '177', '8', 'undefined', '2018-07-04 10:52:57');
INSERT INTO `sec_metric_config` VALUES ('2599', '1', '177', '9', '1', '2018-07-04 10:52:57');
INSERT INTO `sec_metric_config` VALUES ('2600', '1', '174', '1', 'C20008', '2018-07-04 11:11:54');
INSERT INTO `sec_metric_config` VALUES ('2601', '1', '174', '2', 'C20000', '2018-07-04 11:11:54');
INSERT INTO `sec_metric_config` VALUES ('2602', '1', '174', '3', 'obj', '2018-07-04 11:11:54');
INSERT INTO `sec_metric_config` VALUES ('2603', '1', '174', '4', 'line', '2018-07-04 11:11:54');
INSERT INTO `sec_metric_config` VALUES ('2604', '1', '174', '5', '2', '2018-07-04 11:11:54');
INSERT INTO `sec_metric_config` VALUES ('2605', '1', '174', '6', '建制镇占比', '2018-07-04 11:11:54');
INSERT INTO `sec_metric_config` VALUES ('2606', '1', '174', '7', 'year', '2018-07-04 11:11:54');
INSERT INTO `sec_metric_config` VALUES ('2607', '1', '174', '8', '%', '2018-07-04 11:11:54');
INSERT INTO `sec_metric_config` VALUES ('2608', '1', '174', '9', '1', '2018-07-04 11:11:54');
INSERT INTO `sec_metric_config` VALUES ('2609', '1', '198', '1', 'C20009', '2018-07-04 11:11:54');
INSERT INTO `sec_metric_config` VALUES ('2610', '1', '198', '2', 'C20000', '2018-07-04 11:11:54');
INSERT INTO `sec_metric_config` VALUES ('2611', '1', '198', '3', 'obj', '2018-07-04 11:11:54');
INSERT INTO `sec_metric_config` VALUES ('2612', '1', '198', '4', 'pie', '2018-07-04 11:11:54');
INSERT INTO `sec_metric_config` VALUES ('2613', '1', '198', '5', '3', '2018-07-04 11:11:54');
INSERT INTO `sec_metric_config` VALUES ('2614', '1', '198', '6', '建制乡占比', '2018-07-04 11:11:54');
INSERT INTO `sec_metric_config` VALUES ('2615', '1', '198', '7', 'year', '2018-07-04 11:11:54');
INSERT INTO `sec_metric_config` VALUES ('2616', '1', '198', '8', '%', '2018-07-04 11:11:54');
INSERT INTO `sec_metric_config` VALUES ('2617', '1', '198', '9', '1', '2018-07-04 11:11:54');
INSERT INTO `sec_metric_config` VALUES ('2618', '1', '199', '1', 'C80011', '2018-07-04 11:11:54');
INSERT INTO `sec_metric_config` VALUES ('2619', '1', '199', '2', 'C80000', '2018-07-04 11:11:54');
INSERT INTO `sec_metric_config` VALUES ('2620', '1', '199', '3', 'obj', '2018-07-04 11:11:54');
INSERT INTO `sec_metric_config` VALUES ('2621', '1', '199', '4', 'bar', '2018-07-04 11:11:54');
INSERT INTO `sec_metric_config` VALUES ('2622', '1', '199', '5', '9', '2018-07-04 11:11:54');
INSERT INTO `sec_metric_config` VALUES ('2623', '1', '199', '6', '农村居民人均可支配收入比上年名义增长', '2018-07-04 11:11:54');
INSERT INTO `sec_metric_config` VALUES ('2624', '1', '199', '7', 'year', '2018-07-04 11:11:54');
INSERT INTO `sec_metric_config` VALUES ('2625', '1', '199', '8', '%', '2018-07-04 11:11:54');
INSERT INTO `sec_metric_config` VALUES ('2626', '1', '199', '9', '1', '2018-07-04 11:11:54');
INSERT INTO `sec_metric_config` VALUES ('2744', '0', '228', '1', 'C20007', '2018-07-04 11:33:16');
INSERT INTO `sec_metric_config` VALUES ('2745', '0', '228', '2', 'C20000', '2018-07-04 11:33:16');
INSERT INTO `sec_metric_config` VALUES ('2746', '0', '228', '3', 'obj', '2018-07-04 11:33:16');
INSERT INTO `sec_metric_config` VALUES ('2747', '0', '228', '4', 'line', '2018-07-04 11:33:16');
INSERT INTO `sec_metric_config` VALUES ('2748', '0', '228', '5', '1', '2018-07-04 11:33:16');
INSERT INTO `sec_metric_config` VALUES ('2749', '0', '228', '6', '街道办事处占比', '2018-07-04 11:33:16');
INSERT INTO `sec_metric_config` VALUES ('2750', '0', '228', '7', 'year', '2018-07-04 11:33:16');
INSERT INTO `sec_metric_config` VALUES ('2751', '0', '228', '8', '%', '2018-07-04 11:33:16');
INSERT INTO `sec_metric_config` VALUES ('2752', '0', '228', '9', '1', '2018-07-04 11:33:16');
INSERT INTO `sec_metric_config` VALUES ('2753', '0', '229', '1', 'C20008', '2018-07-04 11:33:16');
INSERT INTO `sec_metric_config` VALUES ('2754', '0', '229', '2', 'C20000', '2018-07-04 11:33:16');
INSERT INTO `sec_metric_config` VALUES ('2755', '0', '229', '3', 'obj', '2018-07-04 11:33:16');
INSERT INTO `sec_metric_config` VALUES ('2756', '0', '229', '4', 'line', '2018-07-04 11:33:16');
INSERT INTO `sec_metric_config` VALUES ('2757', '0', '229', '5', '2', '2018-07-04 11:33:16');
INSERT INTO `sec_metric_config` VALUES ('2758', '0', '229', '6', '建制镇占比', '2018-07-04 11:33:16');
INSERT INTO `sec_metric_config` VALUES ('2759', '0', '229', '7', 'year', '2018-07-04 11:33:16');
INSERT INTO `sec_metric_config` VALUES ('2760', '0', '229', '8', '%', '2018-07-04 11:33:16');
INSERT INTO `sec_metric_config` VALUES ('2761', '0', '229', '9', '1', '2018-07-04 11:33:16');
INSERT INTO `sec_metric_config` VALUES ('2762', '0', '230', '1', 'C20007', '2018-07-04 11:34:55');
INSERT INTO `sec_metric_config` VALUES ('2763', '0', '230', '2', 'C20000', '2018-07-04 11:34:55');
INSERT INTO `sec_metric_config` VALUES ('2764', '0', '230', '3', 'obj', '2018-07-04 11:34:55');
INSERT INTO `sec_metric_config` VALUES ('2765', '0', '230', '4', 'line', '2018-07-04 11:34:55');
INSERT INTO `sec_metric_config` VALUES ('2766', '0', '230', '5', '1', '2018-07-04 11:34:55');
INSERT INTO `sec_metric_config` VALUES ('2767', '0', '230', '6', '街道办事处占比', '2018-07-04 11:34:55');
INSERT INTO `sec_metric_config` VALUES ('2768', '0', '230', '7', 'year', '2018-07-04 11:34:55');
INSERT INTO `sec_metric_config` VALUES ('2769', '0', '230', '8', '%', '2018-07-04 11:34:55');
INSERT INTO `sec_metric_config` VALUES ('2770', '0', '230', '9', '1', '2018-07-04 11:34:55');
INSERT INTO `sec_metric_config` VALUES ('2771', '0', '231', '1', 'C20008', '2018-07-04 11:34:55');
INSERT INTO `sec_metric_config` VALUES ('2772', '0', '231', '2', 'C20000', '2018-07-04 11:34:55');
INSERT INTO `sec_metric_config` VALUES ('2773', '0', '231', '3', 'obj', '2018-07-04 11:34:55');
INSERT INTO `sec_metric_config` VALUES ('2774', '0', '231', '4', 'pie', '2018-07-04 11:34:55');
INSERT INTO `sec_metric_config` VALUES ('2775', '0', '231', '5', '2', '2018-07-04 11:34:55');
INSERT INTO `sec_metric_config` VALUES ('2776', '0', '231', '6', '建制镇占比', '2018-07-04 11:34:55');
INSERT INTO `sec_metric_config` VALUES ('2777', '0', '231', '7', 'year', '2018-07-04 11:34:55');
INSERT INTO `sec_metric_config` VALUES ('2778', '0', '231', '8', '%', '2018-07-04 11:34:55');
INSERT INTO `sec_metric_config` VALUES ('2779', '0', '231', '9', '1', '2018-07-04 11:34:55');
INSERT INTO `sec_metric_config` VALUES ('2780', '0', '232', '1', 'C20010', '2018-07-04 11:34:55');
INSERT INTO `sec_metric_config` VALUES ('2781', '0', '232', '2', 'C20000', '2018-07-04 11:34:55');
INSERT INTO `sec_metric_config` VALUES ('2782', '0', '232', '3', 'obj', '2018-07-04 11:34:55');
INSERT INTO `sec_metric_config` VALUES ('2783', '0', '232', '4', 'ringRose', '2018-07-04 11:34:55');
INSERT INTO `sec_metric_config` VALUES ('2784', '0', '232', '5', '4', '2018-07-04 11:34:55');
INSERT INTO `sec_metric_config` VALUES ('2785', '0', '232', '6', '社区居委会占比', '2018-07-04 11:34:55');
INSERT INTO `sec_metric_config` VALUES ('2786', '0', '232', '7', 'year', '2018-07-04 11:34:55');
INSERT INTO `sec_metric_config` VALUES ('2787', '0', '232', '8', '%', '2018-07-04 11:34:55');
INSERT INTO `sec_metric_config` VALUES ('2788', '0', '232', '9', '1', '2018-07-04 11:34:55');
INSERT INTO `sec_metric_config` VALUES ('2789', '0', '233', '1', 'C20011', '2018-07-04 11:34:55');
INSERT INTO `sec_metric_config` VALUES ('2790', '0', '233', '2', 'C20000', '2018-07-04 11:34:55');
INSERT INTO `sec_metric_config` VALUES ('2791', '0', '233', '3', 'obj', '2018-07-04 11:34:55');
INSERT INTO `sec_metric_config` VALUES ('2792', '0', '233', '4', 'bar', '2018-07-04 11:34:55');
INSERT INTO `sec_metric_config` VALUES ('2793', '0', '233', '5', '11', '2018-07-04 11:34:55');
INSERT INTO `sec_metric_config` VALUES ('2794', '0', '233', '6', '村民委员会占比', '2018-07-04 11:34:55');
INSERT INTO `sec_metric_config` VALUES ('2795', '0', '233', '7', 'year', '2018-07-04 11:34:55');
INSERT INTO `sec_metric_config` VALUES ('2796', '0', '233', '8', '%', '2018-07-04 11:34:55');
INSERT INTO `sec_metric_config` VALUES ('2797', '0', '233', '9', '1', '2018-07-04 11:34:55');
INSERT INTO `sec_metric_config` VALUES ('2798', '0', '216', '1', 'C20007', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2799', '0', '216', '2', 'C20000', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2800', '0', '216', '3', 'obj', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2801', '0', '216', '4', 'line', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2802', '0', '216', '5', '1', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2803', '0', '216', '6', '街道办事处占比', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2804', '0', '216', '7', 'year', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2805', '0', '216', '8', '%', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2806', '0', '216', '9', '1', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2807', '0', '223', '1', 'C60001', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2808', '0', '223', '2', 'C60000', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2809', '0', '223', '3', 'obj', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2810', '0', '223', '4', 'pie', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2811', '0', '223', '5', '1', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2812', '0', '223', '6', '总量指标', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2813', '0', '223', '7', 'year', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2814', '0', '223', '8', '1', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2815', '0', '223', '9', '1', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2816', '0', '234', '1', 'C40002', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2817', '0', '234', '2', 'C40000', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2818', '0', '234', '3', 'obj', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2819', '0', '234', '4', 'ringPie', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2820', '0', '234', '5', '1', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2821', '0', '234', '6', '单产业法人', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2822', '0', '234', '7', 'year', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2823', '0', '234', '8', '个', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2824', '0', '234', '9', '1', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2825', '0', '235', '1', 'C20011', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2826', '0', '235', '2', 'C20000', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2827', '0', '235', '3', 'obj', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2828', '0', '235', '4', 'gauge', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2829', '0', '235', '5', '11', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2830', '0', '235', '6', '村民委员会占比', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2831', '0', '235', '7', 'year', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2832', '0', '235', '8', '%', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2833', '0', '235', '9', '1', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2834', '0', '236', '1', 'C40003', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2835', '0', '236', '2', 'C40000', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2836', '0', '236', '3', 'obj', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2837', '0', '236', '4', 'rose', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2838', '0', '236', '5', '2', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2839', '0', '236', '6', '多产业法人', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2840', '0', '236', '7', 'year', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2841', '0', '236', '8', '个', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2842', '0', '236', '9', '1', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2843', '0', '237', '1', 'C80007', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2844', '0', '237', '2', 'C80000', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2845', '0', '237', '3', 'obj', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2846', '0', '237', '4', 'line', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2847', '0', '237', '5', '5', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2848', '0', '237', '6', '规模以上工业总产值比上年增长', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2849', '0', '237', '7', 'year', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2850', '0', '237', '8', '%', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2851', '0', '237', '9', '1', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2852', '0', '238', '1', 'C30003', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2853', '0', '238', '2', 'C30000', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2854', '0', '238', '3', 'obj', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2855', '0', '238', '4', 'bar', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2856', '0', '238', '5', '1', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2857', '0', '238', '6', '多产业法人数', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2858', '0', '238', '7', 'year', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2859', '0', '238', '8', '个', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2860', '0', '238', '9', '1', '2018-07-04 11:37:42');
INSERT INTO `sec_metric_config` VALUES ('2861', '1', '238', '1', 'C30003', '2018-07-04 11:38:22');
INSERT INTO `sec_metric_config` VALUES ('2862', '1', '238', '2', 'C30000', '2018-07-04 11:38:22');
INSERT INTO `sec_metric_config` VALUES ('2863', '1', '238', '3', 'obj', '2018-07-04 11:38:22');
INSERT INTO `sec_metric_config` VALUES ('2864', '1', '238', '4', 'bar', '2018-07-04 11:38:22');
INSERT INTO `sec_metric_config` VALUES ('2865', '1', '238', '5', '1', '2018-07-04 11:38:22');
INSERT INTO `sec_metric_config` VALUES ('2866', '1', '238', '6', '多产业法人数', '2018-07-04 11:38:22');
INSERT INTO `sec_metric_config` VALUES ('2867', '1', '238', '7', 'year', '2018-07-04 11:38:22');
INSERT INTO `sec_metric_config` VALUES ('2868', '1', '238', '8', '个', '2018-07-04 11:38:22');
INSERT INTO `sec_metric_config` VALUES ('2869', '1', '238', '9', '0', '2018-07-04 11:38:22');
INSERT INTO `sec_metric_config` VALUES ('2870', '1', '229', '1', 'C20008', '2018-07-04 11:38:22');
INSERT INTO `sec_metric_config` VALUES ('2871', '1', '229', '2', 'C20000', '2018-07-04 11:38:22');
INSERT INTO `sec_metric_config` VALUES ('2872', '1', '229', '3', 'obj', '2018-07-04 11:38:22');
INSERT INTO `sec_metric_config` VALUES ('2873', '1', '229', '4', 'line', '2018-07-04 11:38:22');
INSERT INTO `sec_metric_config` VALUES ('2874', '1', '229', '5', '2', '2018-07-04 11:38:22');
INSERT INTO `sec_metric_config` VALUES ('2875', '1', '229', '6', '建制镇占比', '2018-07-04 11:38:22');
INSERT INTO `sec_metric_config` VALUES ('2876', '1', '229', '7', 'year', '2018-07-04 11:38:22');
INSERT INTO `sec_metric_config` VALUES ('2877', '1', '229', '8', '%', '2018-07-04 11:38:22');
INSERT INTO `sec_metric_config` VALUES ('2878', '1', '229', '9', '0', '2018-07-04 11:38:22');
INSERT INTO `sec_metric_config` VALUES ('2879', '1', '233', '1', 'C20011', '2018-07-04 11:39:24');
INSERT INTO `sec_metric_config` VALUES ('2880', '1', '233', '2', 'C20000', '2018-07-04 11:39:24');
INSERT INTO `sec_metric_config` VALUES ('2881', '1', '233', '3', 'obj', '2018-07-04 11:39:24');
INSERT INTO `sec_metric_config` VALUES ('2882', '1', '233', '4', 'bar', '2018-07-04 11:39:24');
INSERT INTO `sec_metric_config` VALUES ('2883', '1', '233', '5', '11', '2018-07-04 11:39:24');
INSERT INTO `sec_metric_config` VALUES ('2884', '1', '233', '6', '村民委员会占比', '2018-07-04 11:39:24');
INSERT INTO `sec_metric_config` VALUES ('2885', '1', '233', '7', 'year', '2018-07-04 11:39:24');
INSERT INTO `sec_metric_config` VALUES ('2886', '1', '233', '8', '%', '2018-07-04 11:39:24');
INSERT INTO `sec_metric_config` VALUES ('2887', '1', '233', '9', '0', '2018-07-04 11:39:24');
INSERT INTO `sec_metric_config` VALUES ('2888', '1', '214', '1', 'C80010', '2018-07-04 14:37:22');
INSERT INTO `sec_metric_config` VALUES ('2889', '1', '214', '2', 'C80000', '2018-07-04 14:37:22');
INSERT INTO `sec_metric_config` VALUES ('2890', '1', '214', '3', 'obj', '2018-07-04 14:37:22');
INSERT INTO `sec_metric_config` VALUES ('2891', '1', '214', '4', 'gauge', '2018-07-04 14:37:22');
INSERT INTO `sec_metric_config` VALUES ('2892', '1', '214', '5', '8', '2018-07-04 14:37:22');
INSERT INTO `sec_metric_config` VALUES ('2893', '1', '214', '6', '城镇居民人均可支配收入比上年实际增长', '2018-07-04 14:37:22');
INSERT INTO `sec_metric_config` VALUES ('2894', '1', '214', '7', 'year', '2018-07-04 14:37:22');
INSERT INTO `sec_metric_config` VALUES ('2895', '1', '214', '8', '%', '2018-07-04 14:37:22');
INSERT INTO `sec_metric_config` VALUES ('2896', '1', '214', '9', '0', '2018-07-04 14:37:22');
INSERT INTO `sec_metric_config` VALUES ('2897', '1', '199', '1', 'C80011', '2018-07-04 14:39:31');
INSERT INTO `sec_metric_config` VALUES ('2898', '1', '199', '2', 'C80000', '2018-07-04 14:39:31');
INSERT INTO `sec_metric_config` VALUES ('2899', '1', '199', '3', 'obj', '2018-07-04 14:39:31');
INSERT INTO `sec_metric_config` VALUES ('2900', '1', '199', '4', 'bar', '2018-07-04 14:39:31');
INSERT INTO `sec_metric_config` VALUES ('2901', '1', '199', '5', '9', '2018-07-04 14:39:31');
INSERT INTO `sec_metric_config` VALUES ('2902', '1', '199', '6', '农村居民人均可支配收入比上年名义增长', '2018-07-04 14:39:31');
INSERT INTO `sec_metric_config` VALUES ('2903', '1', '199', '7', 'year', '2018-07-04 14:39:31');
INSERT INTO `sec_metric_config` VALUES ('2904', '1', '199', '8', '%', '2018-07-04 14:39:31');
INSERT INTO `sec_metric_config` VALUES ('2905', '1', '199', '9', '0', '2018-07-04 14:39:31');
INSERT INTO `sec_metric_config` VALUES ('2906', '0', '239', '1', 'C20007', '2018-07-04 14:46:59');
INSERT INTO `sec_metric_config` VALUES ('2907', '0', '239', '2', 'C20000', '2018-07-04 14:46:59');
INSERT INTO `sec_metric_config` VALUES ('2908', '0', '239', '3', 'obj', '2018-07-04 14:46:59');
INSERT INTO `sec_metric_config` VALUES ('2909', '0', '239', '4', 'bar', '2018-07-04 14:46:59');
INSERT INTO `sec_metric_config` VALUES ('2910', '0', '239', '5', '1', '2018-07-04 14:46:59');
INSERT INTO `sec_metric_config` VALUES ('2911', '0', '239', '6', '街道办事处占比', '2018-07-04 14:46:59');
INSERT INTO `sec_metric_config` VALUES ('2912', '0', '239', '7', 'year', '2018-07-04 14:46:59');
INSERT INTO `sec_metric_config` VALUES ('2913', '0', '239', '8', '%', '2018-07-04 14:46:59');
INSERT INTO `sec_metric_config` VALUES ('2914', '0', '239', '9', '1', '2018-07-04 14:46:59');
INSERT INTO `sec_metric_config` VALUES ('2915', '0', '240', '1', 'C20008', '2018-07-04 14:46:59');
INSERT INTO `sec_metric_config` VALUES ('2916', '0', '240', '2', 'C20000', '2018-07-04 14:46:59');
INSERT INTO `sec_metric_config` VALUES ('2917', '0', '240', '3', 'obj', '2018-07-04 14:46:59');
INSERT INTO `sec_metric_config` VALUES ('2918', '0', '240', '4', 'line', '2018-07-04 14:46:59');
INSERT INTO `sec_metric_config` VALUES ('2919', '0', '240', '5', '2', '2018-07-04 14:46:59');
INSERT INTO `sec_metric_config` VALUES ('2920', '0', '240', '6', '建制镇占比', '2018-07-04 14:46:59');
INSERT INTO `sec_metric_config` VALUES ('2921', '0', '240', '7', 'year', '2018-07-04 14:46:59');
INSERT INTO `sec_metric_config` VALUES ('2922', '0', '240', '8', '%', '2018-07-04 14:46:59');
INSERT INTO `sec_metric_config` VALUES ('2923', '0', '240', '9', '1', '2018-07-04 14:46:59');
INSERT INTO `sec_metric_config` VALUES ('2924', '0', '241', '1', 'C20009', '2018-07-04 14:46:59');
INSERT INTO `sec_metric_config` VALUES ('2925', '0', '241', '2', 'C20000', '2018-07-04 14:46:59');
INSERT INTO `sec_metric_config` VALUES ('2926', '0', '241', '3', 'obj', '2018-07-04 14:46:59');
INSERT INTO `sec_metric_config` VALUES ('2927', '0', '241', '4', 'pie', '2018-07-04 14:46:59');
INSERT INTO `sec_metric_config` VALUES ('2928', '0', '241', '5', '3', '2018-07-04 14:46:59');
INSERT INTO `sec_metric_config` VALUES ('2929', '0', '241', '6', '建制乡占比', '2018-07-04 14:46:59');
INSERT INTO `sec_metric_config` VALUES ('2930', '0', '241', '7', 'year', '2018-07-04 14:46:59');
INSERT INTO `sec_metric_config` VALUES ('2931', '0', '241', '8', '%', '2018-07-04 14:46:59');
INSERT INTO `sec_metric_config` VALUES ('2932', '0', '241', '9', '1', '2018-07-04 14:46:59');
INSERT INTO `sec_metric_config` VALUES ('2933', '0', '242', '1', 'C80011', '2018-07-04 14:46:59');
INSERT INTO `sec_metric_config` VALUES ('2934', '0', '242', '2', 'C80000', '2018-07-04 14:46:59');
INSERT INTO `sec_metric_config` VALUES ('2935', '0', '242', '3', 'obj', '2018-07-04 14:46:59');
INSERT INTO `sec_metric_config` VALUES ('2936', '0', '242', '4', 'bar', '2018-07-04 14:46:59');
INSERT INTO `sec_metric_config` VALUES ('2937', '0', '242', '5', '9', '2018-07-04 14:46:59');
INSERT INTO `sec_metric_config` VALUES ('2938', '0', '242', '6', '农村居民人均可支配收入比上年名义增长', '2018-07-04 14:46:59');
INSERT INTO `sec_metric_config` VALUES ('2939', '0', '242', '7', 'year', '2018-07-04 14:46:59');
INSERT INTO `sec_metric_config` VALUES ('2940', '0', '242', '8', '%', '2018-07-04 14:46:59');
INSERT INTO `sec_metric_config` VALUES ('2941', '0', '242', '9', '1', '2018-07-04 14:46:59');
INSERT INTO `sec_metric_config` VALUES ('2942', '1', '242', '1', 'C80011', '2018-07-04 14:47:51');
INSERT INTO `sec_metric_config` VALUES ('2943', '1', '242', '2', 'C80000', '2018-07-04 14:47:51');
INSERT INTO `sec_metric_config` VALUES ('2944', '1', '242', '3', 'obj', '2018-07-04 14:47:51');
INSERT INTO `sec_metric_config` VALUES ('2945', '1', '242', '4', 'bar', '2018-07-04 14:47:51');
INSERT INTO `sec_metric_config` VALUES ('2946', '1', '242', '5', '9', '2018-07-04 14:47:51');
INSERT INTO `sec_metric_config` VALUES ('2947', '1', '242', '6', '农村居民人均可支配收入比上年名义增长', '2018-07-04 14:47:51');
INSERT INTO `sec_metric_config` VALUES ('2948', '1', '242', '7', 'year', '2018-07-04 14:47:51');
INSERT INTO `sec_metric_config` VALUES ('2949', '1', '242', '8', '%', '2018-07-04 14:47:51');
INSERT INTO `sec_metric_config` VALUES ('2950', '1', '242', '9', '0', '2018-07-04 14:47:51');
INSERT INTO `sec_metric_config` VALUES ('2951', '1', '240', '1', 'C20008', '2018-07-04 14:48:12');
INSERT INTO `sec_metric_config` VALUES ('2952', '1', '240', '2', 'C20000', '2018-07-04 14:48:12');
INSERT INTO `sec_metric_config` VALUES ('2953', '1', '240', '3', 'obj', '2018-07-04 14:48:12');
INSERT INTO `sec_metric_config` VALUES ('2954', '1', '240', '4', 'line', '2018-07-04 14:48:12');
INSERT INTO `sec_metric_config` VALUES ('2955', '1', '240', '5', '2', '2018-07-04 14:48:12');
INSERT INTO `sec_metric_config` VALUES ('2956', '1', '240', '6', '建制镇占比', '2018-07-04 14:48:12');
INSERT INTO `sec_metric_config` VALUES ('2957', '1', '240', '7', 'year', '2018-07-04 14:48:12');
INSERT INTO `sec_metric_config` VALUES ('2958', '1', '240', '8', '%', '2018-07-04 14:48:12');
INSERT INTO `sec_metric_config` VALUES ('2959', '1', '240', '9', '0', '2018-07-04 14:48:12');
INSERT INTO `sec_metric_config` VALUES ('2960', '1', '241', '1', 'C20009', '2018-07-04 14:48:12');
INSERT INTO `sec_metric_config` VALUES ('2961', '1', '241', '2', 'C20000', '2018-07-04 14:48:12');
INSERT INTO `sec_metric_config` VALUES ('2962', '1', '241', '3', 'obj', '2018-07-04 14:48:12');
INSERT INTO `sec_metric_config` VALUES ('2963', '1', '241', '4', 'pie', '2018-07-04 14:48:12');
INSERT INTO `sec_metric_config` VALUES ('2964', '1', '241', '5', '3', '2018-07-04 14:48:12');
INSERT INTO `sec_metric_config` VALUES ('2965', '1', '241', '6', '建制乡占比', '2018-07-04 14:48:12');
INSERT INTO `sec_metric_config` VALUES ('2966', '1', '241', '7', 'year', '2018-07-04 14:48:12');
INSERT INTO `sec_metric_config` VALUES ('2967', '1', '241', '8', '%', '2018-07-04 14:48:12');
INSERT INTO `sec_metric_config` VALUES ('2968', '1', '241', '9', '0', '2018-07-04 14:48:12');
INSERT INTO `sec_metric_config` VALUES ('2969', '1', '242', '1', 'C80011', '2018-07-04 14:48:12');
INSERT INTO `sec_metric_config` VALUES ('2970', '1', '242', '2', 'C80000', '2018-07-04 14:48:12');
INSERT INTO `sec_metric_config` VALUES ('2971', '1', '242', '3', 'obj', '2018-07-04 14:48:12');
INSERT INTO `sec_metric_config` VALUES ('2972', '1', '242', '4', 'bar', '2018-07-04 14:48:12');
INSERT INTO `sec_metric_config` VALUES ('2973', '1', '242', '5', '9', '2018-07-04 14:48:12');
INSERT INTO `sec_metric_config` VALUES ('2974', '1', '242', '6', '农村居民人均可支配收入比上年名义增长', '2018-07-04 14:48:12');
INSERT INTO `sec_metric_config` VALUES ('2975', '1', '242', '7', 'year', '2018-07-04 14:48:12');
INSERT INTO `sec_metric_config` VALUES ('2976', '1', '242', '8', '%', '2018-07-04 14:48:12');
INSERT INTO `sec_metric_config` VALUES ('2977', '1', '242', '9', '0', '2018-07-04 14:48:12');
INSERT INTO `sec_metric_config` VALUES ('2978', '1', '240', '1', 'C20008', '2018-07-04 14:52:28');
INSERT INTO `sec_metric_config` VALUES ('2979', '1', '240', '2', 'C20000', '2018-07-04 14:52:28');
INSERT INTO `sec_metric_config` VALUES ('2980', '1', '240', '3', 'obj', '2018-07-04 14:52:28');
INSERT INTO `sec_metric_config` VALUES ('2981', '1', '240', '4', 'line', '2018-07-04 14:52:28');
INSERT INTO `sec_metric_config` VALUES ('2982', '1', '240', '5', '2', '2018-07-04 14:52:28');
INSERT INTO `sec_metric_config` VALUES ('2983', '1', '240', '6', '建制镇占比', '2018-07-04 14:52:28');
INSERT INTO `sec_metric_config` VALUES ('2984', '1', '240', '7', 'year', '2018-07-04 14:52:28');
INSERT INTO `sec_metric_config` VALUES ('2985', '1', '240', '8', '%', '2018-07-04 14:52:28');
INSERT INTO `sec_metric_config` VALUES ('2986', '1', '240', '9', '0', '2018-07-04 14:52:28');
INSERT INTO `sec_metric_config` VALUES ('2987', '1', '241', '1', 'C20009', '2018-07-04 14:52:28');
INSERT INTO `sec_metric_config` VALUES ('2988', '1', '241', '2', 'C20000', '2018-07-04 14:52:28');
INSERT INTO `sec_metric_config` VALUES ('2989', '1', '241', '3', 'obj', '2018-07-04 14:52:28');
INSERT INTO `sec_metric_config` VALUES ('2990', '1', '241', '4', 'pie', '2018-07-04 14:52:28');
INSERT INTO `sec_metric_config` VALUES ('2991', '1', '241', '5', '3', '2018-07-04 14:52:28');
INSERT INTO `sec_metric_config` VALUES ('2992', '1', '241', '6', '建制乡占比', '2018-07-04 14:52:28');
INSERT INTO `sec_metric_config` VALUES ('2993', '1', '241', '7', 'year', '2018-07-04 14:52:28');
INSERT INTO `sec_metric_config` VALUES ('2994', '1', '241', '8', '%', '2018-07-04 14:52:28');
INSERT INTO `sec_metric_config` VALUES ('2995', '1', '241', '9', '0', '2018-07-04 14:52:28');
INSERT INTO `sec_metric_config` VALUES ('2996', '1', '242', '1', 'C80011', '2018-07-04 14:52:28');
INSERT INTO `sec_metric_config` VALUES ('2997', '1', '242', '2', 'C80000', '2018-07-04 14:52:28');
INSERT INTO `sec_metric_config` VALUES ('2998', '1', '242', '3', 'obj', '2018-07-04 14:52:28');
INSERT INTO `sec_metric_config` VALUES ('2999', '1', '242', '4', 'bar', '2018-07-04 14:52:28');
INSERT INTO `sec_metric_config` VALUES ('3000', '1', '242', '5', '9', '2018-07-04 14:52:28');
INSERT INTO `sec_metric_config` VALUES ('3001', '1', '242', '6', '农村居民人均可支配收入比上年名义增长', '2018-07-04 14:52:28');
INSERT INTO `sec_metric_config` VALUES ('3002', '1', '242', '7', 'year', '2018-07-04 14:52:28');
INSERT INTO `sec_metric_config` VALUES ('3003', '1', '242', '8', '%', '2018-07-04 14:52:28');
INSERT INTO `sec_metric_config` VALUES ('3004', '1', '242', '9', '1', '2018-07-04 14:52:28');
INSERT INTO `sec_metric_config` VALUES ('3005', '1', '174', '1', 'C20008', '2018-07-04 14:55:42');
INSERT INTO `sec_metric_config` VALUES ('3006', '1', '174', '2', 'C20000', '2018-07-04 14:55:42');
INSERT INTO `sec_metric_config` VALUES ('3007', '1', '174', '3', 'obj', '2018-07-04 14:55:42');
INSERT INTO `sec_metric_config` VALUES ('3008', '1', '174', '4', 'line', '2018-07-04 14:55:42');
INSERT INTO `sec_metric_config` VALUES ('3009', '1', '174', '5', '2', '2018-07-04 14:55:42');
INSERT INTO `sec_metric_config` VALUES ('3010', '1', '174', '6', '建制镇占比', '2018-07-04 14:55:42');
INSERT INTO `sec_metric_config` VALUES ('3011', '1', '174', '7', 'year', '2018-07-04 14:55:42');
INSERT INTO `sec_metric_config` VALUES ('3012', '1', '174', '8', '%', '2018-07-04 14:55:42');
INSERT INTO `sec_metric_config` VALUES ('3013', '1', '174', '9', '1', '2018-07-04 14:55:42');
INSERT INTO `sec_metric_config` VALUES ('3014', '1', '198', '1', 'C20009', '2018-07-04 14:55:42');
INSERT INTO `sec_metric_config` VALUES ('3015', '1', '198', '2', 'C20000', '2018-07-04 14:55:42');
INSERT INTO `sec_metric_config` VALUES ('3016', '1', '198', '3', 'obj', '2018-07-04 14:55:42');
INSERT INTO `sec_metric_config` VALUES ('3017', '1', '198', '4', 'pie', '2018-07-04 14:55:42');
INSERT INTO `sec_metric_config` VALUES ('3018', '1', '198', '5', '3', '2018-07-04 14:55:42');
INSERT INTO `sec_metric_config` VALUES ('3019', '1', '198', '6', '建制乡占比', '2018-07-04 14:55:42');
INSERT INTO `sec_metric_config` VALUES ('3020', '1', '198', '7', 'year', '2018-07-04 14:55:42');
INSERT INTO `sec_metric_config` VALUES ('3021', '1', '198', '8', '%', '2018-07-04 14:55:42');
INSERT INTO `sec_metric_config` VALUES ('3022', '1', '198', '9', '1', '2018-07-04 14:55:42');
INSERT INTO `sec_metric_config` VALUES ('3023', '1', '199', '1', 'C80011', '2018-07-04 14:55:42');
INSERT INTO `sec_metric_config` VALUES ('3024', '1', '199', '2', 'C80000', '2018-07-04 14:55:42');
INSERT INTO `sec_metric_config` VALUES ('3025', '1', '199', '3', 'obj', '2018-07-04 14:55:42');
INSERT INTO `sec_metric_config` VALUES ('3026', '1', '199', '4', 'bar', '2018-07-04 14:55:42');
INSERT INTO `sec_metric_config` VALUES ('3027', '1', '199', '5', '9', '2018-07-04 14:55:42');
INSERT INTO `sec_metric_config` VALUES ('3028', '1', '199', '6', '农村居民人均可支配收入比上年名义增长', '2018-07-04 14:55:42');
INSERT INTO `sec_metric_config` VALUES ('3029', '1', '199', '7', 'year', '2018-07-04 14:55:42');
INSERT INTO `sec_metric_config` VALUES ('3030', '1', '199', '8', '%', '2018-07-04 14:55:42');
INSERT INTO `sec_metric_config` VALUES ('3031', '1', '199', '9', '1', '2018-07-04 14:55:42');
INSERT INTO `sec_metric_config` VALUES ('3032', '1', '199', '1', 'C80011', '2018-07-04 14:55:54');
INSERT INTO `sec_metric_config` VALUES ('3033', '1', '199', '2', 'C80000', '2018-07-04 14:55:54');
INSERT INTO `sec_metric_config` VALUES ('3034', '1', '199', '3', 'obj', '2018-07-04 14:55:54');
INSERT INTO `sec_metric_config` VALUES ('3035', '1', '199', '4', 'bar', '2018-07-04 14:55:54');
INSERT INTO `sec_metric_config` VALUES ('3036', '1', '199', '5', '9', '2018-07-04 14:55:54');
INSERT INTO `sec_metric_config` VALUES ('3037', '1', '199', '6', '农村居民人均可支配收入比上年名义增长', '2018-07-04 14:55:54');
INSERT INTO `sec_metric_config` VALUES ('3038', '1', '199', '7', 'year', '2018-07-04 14:55:54');
INSERT INTO `sec_metric_config` VALUES ('3039', '1', '199', '8', '%', '2018-07-04 14:55:54');
INSERT INTO `sec_metric_config` VALUES ('3040', '1', '199', '9', '0', '2018-07-04 14:55:54');
INSERT INTO `sec_metric_config` VALUES ('3041', '1', '240', '1', 'C20008', '2018-07-04 14:56:32');
INSERT INTO `sec_metric_config` VALUES ('3042', '1', '240', '2', 'C20000', '2018-07-04 14:56:32');
INSERT INTO `sec_metric_config` VALUES ('3043', '1', '240', '3', 'obj', '2018-07-04 14:56:32');
INSERT INTO `sec_metric_config` VALUES ('3044', '1', '240', '4', 'line', '2018-07-04 14:56:32');
INSERT INTO `sec_metric_config` VALUES ('3045', '1', '240', '5', '2', '2018-07-04 14:56:32');
INSERT INTO `sec_metric_config` VALUES ('3046', '1', '240', '6', '建制镇占比', '2018-07-04 14:56:32');
INSERT INTO `sec_metric_config` VALUES ('3047', '1', '240', '7', 'year', '2018-07-04 14:56:32');
INSERT INTO `sec_metric_config` VALUES ('3048', '1', '240', '8', '%', '2018-07-04 14:56:32');
INSERT INTO `sec_metric_config` VALUES ('3049', '1', '240', '9', '0', '2018-07-04 14:56:32');
INSERT INTO `sec_metric_config` VALUES ('3050', '1', '241', '1', 'C20009', '2018-07-04 14:56:32');
INSERT INTO `sec_metric_config` VALUES ('3051', '1', '241', '2', 'C20000', '2018-07-04 14:56:32');
INSERT INTO `sec_metric_config` VALUES ('3052', '1', '241', '3', 'obj', '2018-07-04 14:56:32');
INSERT INTO `sec_metric_config` VALUES ('3053', '1', '241', '4', 'pie', '2018-07-04 14:56:32');
INSERT INTO `sec_metric_config` VALUES ('3054', '1', '241', '5', '3', '2018-07-04 14:56:32');
INSERT INTO `sec_metric_config` VALUES ('3055', '1', '241', '6', '建制乡占比', '2018-07-04 14:56:32');
INSERT INTO `sec_metric_config` VALUES ('3056', '1', '241', '7', 'year', '2018-07-04 14:56:32');
INSERT INTO `sec_metric_config` VALUES ('3057', '1', '241', '8', '%', '2018-07-04 14:56:32');
INSERT INTO `sec_metric_config` VALUES ('3058', '1', '241', '9', '0', '2018-07-04 14:56:32');
INSERT INTO `sec_metric_config` VALUES ('3059', '1', '242', '1', 'C80011', '2018-07-04 14:56:32');
INSERT INTO `sec_metric_config` VALUES ('3060', '1', '242', '2', 'C80000', '2018-07-04 14:56:32');
INSERT INTO `sec_metric_config` VALUES ('3061', '1', '242', '3', 'obj', '2018-07-04 14:56:32');
INSERT INTO `sec_metric_config` VALUES ('3062', '1', '242', '4', 'bar', '2018-07-04 14:56:32');
INSERT INTO `sec_metric_config` VALUES ('3063', '1', '242', '5', '9', '2018-07-04 14:56:32');
INSERT INTO `sec_metric_config` VALUES ('3064', '1', '242', '6', '农村居民人均可支配收入比上年名义增长', '2018-07-04 14:56:32');
INSERT INTO `sec_metric_config` VALUES ('3065', '1', '242', '7', 'year', '2018-07-04 14:56:32');
INSERT INTO `sec_metric_config` VALUES ('3066', '1', '242', '8', '%', '2018-07-04 14:56:32');
INSERT INTO `sec_metric_config` VALUES ('3067', '1', '242', '9', '0', '2018-07-04 14:56:32');
INSERT INTO `sec_metric_config` VALUES ('3068', '1', '240', '1', 'C20008', '2018-07-04 14:56:49');
INSERT INTO `sec_metric_config` VALUES ('3069', '1', '240', '2', 'C20000', '2018-07-04 14:56:49');
INSERT INTO `sec_metric_config` VALUES ('3070', '1', '240', '3', 'obj', '2018-07-04 14:56:49');
INSERT INTO `sec_metric_config` VALUES ('3071', '1', '240', '4', 'line', '2018-07-04 14:56:49');
INSERT INTO `sec_metric_config` VALUES ('3072', '1', '240', '5', '2', '2018-07-04 14:56:49');
INSERT INTO `sec_metric_config` VALUES ('3073', '1', '240', '6', '建制镇占比', '2018-07-04 14:56:49');
INSERT INTO `sec_metric_config` VALUES ('3074', '1', '240', '7', 'year', '2018-07-04 14:56:49');
INSERT INTO `sec_metric_config` VALUES ('3075', '1', '240', '8', '%', '2018-07-04 14:56:49');
INSERT INTO `sec_metric_config` VALUES ('3076', '1', '240', '9', '1', '2018-07-04 14:56:49');
INSERT INTO `sec_metric_config` VALUES ('3077', '1', '241', '1', 'C20009', '2018-07-04 14:56:49');
INSERT INTO `sec_metric_config` VALUES ('3078', '1', '241', '2', 'C20000', '2018-07-04 14:56:49');
INSERT INTO `sec_metric_config` VALUES ('3079', '1', '241', '3', 'obj', '2018-07-04 14:56:49');
INSERT INTO `sec_metric_config` VALUES ('3080', '1', '241', '4', 'pie', '2018-07-04 14:56:49');
INSERT INTO `sec_metric_config` VALUES ('3081', '1', '241', '5', '3', '2018-07-04 14:56:49');
INSERT INTO `sec_metric_config` VALUES ('3082', '1', '241', '6', '建制乡占比', '2018-07-04 14:56:49');
INSERT INTO `sec_metric_config` VALUES ('3083', '1', '241', '7', 'year', '2018-07-04 14:56:49');
INSERT INTO `sec_metric_config` VALUES ('3084', '1', '241', '8', '%', '2018-07-04 14:56:49');
INSERT INTO `sec_metric_config` VALUES ('3085', '1', '241', '9', '1', '2018-07-04 14:56:49');
INSERT INTO `sec_metric_config` VALUES ('3086', '1', '242', '1', 'C80011', '2018-07-04 14:56:49');
INSERT INTO `sec_metric_config` VALUES ('3087', '1', '242', '2', 'C80000', '2018-07-04 14:56:49');
INSERT INTO `sec_metric_config` VALUES ('3088', '1', '242', '3', 'obj', '2018-07-04 14:56:49');
INSERT INTO `sec_metric_config` VALUES ('3089', '1', '242', '4', 'bar', '2018-07-04 14:56:49');
INSERT INTO `sec_metric_config` VALUES ('3090', '1', '242', '5', '9', '2018-07-04 14:56:49');
INSERT INTO `sec_metric_config` VALUES ('3091', '1', '242', '6', '农村居民人均可支配收入比上年名义增长', '2018-07-04 14:56:49');
INSERT INTO `sec_metric_config` VALUES ('3092', '1', '242', '7', 'year', '2018-07-04 14:56:49');
INSERT INTO `sec_metric_config` VALUES ('3093', '1', '242', '8', '%', '2018-07-04 14:56:49');
INSERT INTO `sec_metric_config` VALUES ('3094', '1', '242', '9', '0', '2018-07-04 14:56:49');
INSERT INTO `sec_metric_config` VALUES ('3095', '2', '238', '1', 'C30003', '2018-07-04 15:45:55');
INSERT INTO `sec_metric_config` VALUES ('3096', '2', '238', '2', 'C30000', '2018-07-04 15:45:55');
INSERT INTO `sec_metric_config` VALUES ('3097', '2', '238', '3', 'obj', '2018-07-04 15:45:55');
INSERT INTO `sec_metric_config` VALUES ('3098', '2', '238', '4', 'bar', '2018-07-04 15:45:55');
INSERT INTO `sec_metric_config` VALUES ('3099', '2', '238', '5', '1', '2018-07-04 15:45:55');
INSERT INTO `sec_metric_config` VALUES ('3100', '2', '238', '6', '多产业法人数', '2018-07-04 15:45:55');
INSERT INTO `sec_metric_config` VALUES ('3101', '2', '238', '7', 'year', '2018-07-04 15:45:55');
INSERT INTO `sec_metric_config` VALUES ('3102', '2', '238', '8', '个', '2018-07-04 15:45:55');
INSERT INTO `sec_metric_config` VALUES ('3103', '2', '238', '9', '0', '2018-07-04 15:45:55');
INSERT INTO `sec_metric_config` VALUES ('3104', '2', '229', '1', 'C20008', '2018-07-04 15:45:55');
INSERT INTO `sec_metric_config` VALUES ('3105', '2', '229', '2', 'C20000', '2018-07-04 15:45:55');
INSERT INTO `sec_metric_config` VALUES ('3106', '2', '229', '3', 'obj', '2018-07-04 15:45:55');
INSERT INTO `sec_metric_config` VALUES ('3107', '2', '229', '4', 'line', '2018-07-04 15:45:55');
INSERT INTO `sec_metric_config` VALUES ('3108', '2', '229', '5', '2', '2018-07-04 15:45:55');
INSERT INTO `sec_metric_config` VALUES ('3109', '2', '229', '6', '建制镇占比', '2018-07-04 15:45:55');
INSERT INTO `sec_metric_config` VALUES ('3110', '2', '229', '7', 'year', '2018-07-04 15:45:55');
INSERT INTO `sec_metric_config` VALUES ('3111', '2', '229', '8', '%', '2018-07-04 15:45:55');
INSERT INTO `sec_metric_config` VALUES ('3112', '2', '229', '9', '0', '2018-07-04 15:45:55');
INSERT INTO `sec_metric_config` VALUES ('3113', '2', '233', '1', 'C20011', '2018-07-04 15:46:14');
INSERT INTO `sec_metric_config` VALUES ('3114', '2', '233', '2', 'C20000', '2018-07-04 15:46:14');
INSERT INTO `sec_metric_config` VALUES ('3115', '2', '233', '3', 'obj', '2018-07-04 15:46:14');
INSERT INTO `sec_metric_config` VALUES ('3116', '2', '233', '4', 'bar', '2018-07-04 15:46:14');
INSERT INTO `sec_metric_config` VALUES ('3117', '2', '233', '5', '11', '2018-07-04 15:46:14');
INSERT INTO `sec_metric_config` VALUES ('3118', '2', '233', '6', '村民委员会占比', '2018-07-04 15:46:14');
INSERT INTO `sec_metric_config` VALUES ('3119', '2', '233', '7', 'year', '2018-07-04 15:46:14');
INSERT INTO `sec_metric_config` VALUES ('3120', '2', '233', '8', '%', '2018-07-04 15:46:14');
INSERT INTO `sec_metric_config` VALUES ('3121', '2', '233', '9', '0', '2018-07-04 15:46:14');
INSERT INTO `sec_metric_config` VALUES ('3122', '2', '238', '1', 'C30003', '2018-07-04 16:10:23');
INSERT INTO `sec_metric_config` VALUES ('3123', '2', '238', '2', 'C30000', '2018-07-04 16:10:23');
INSERT INTO `sec_metric_config` VALUES ('3124', '2', '238', '3', 'obj', '2018-07-04 16:10:23');
INSERT INTO `sec_metric_config` VALUES ('3125', '2', '238', '4', 'bar', '2018-07-04 16:10:23');
INSERT INTO `sec_metric_config` VALUES ('3126', '2', '238', '5', '1', '2018-07-04 16:10:23');
INSERT INTO `sec_metric_config` VALUES ('3127', '2', '238', '6', '多产业法人数', '2018-07-04 16:10:23');
INSERT INTO `sec_metric_config` VALUES ('3128', '2', '238', '7', 'year', '2018-07-04 16:10:23');
INSERT INTO `sec_metric_config` VALUES ('3129', '2', '238', '8', '个', '2018-07-04 16:10:23');
INSERT INTO `sec_metric_config` VALUES ('3130', '2', '238', '9', '1', '2018-07-04 16:10:23');
INSERT INTO `sec_metric_config` VALUES ('3131', '2', '229', '1', 'C20008', '2018-07-04 16:10:23');
INSERT INTO `sec_metric_config` VALUES ('3132', '2', '229', '2', 'C20000', '2018-07-04 16:10:23');
INSERT INTO `sec_metric_config` VALUES ('3133', '2', '229', '3', 'obj', '2018-07-04 16:10:23');
INSERT INTO `sec_metric_config` VALUES ('3134', '2', '229', '4', 'line', '2018-07-04 16:10:23');
INSERT INTO `sec_metric_config` VALUES ('3135', '2', '229', '5', '2', '2018-07-04 16:10:23');
INSERT INTO `sec_metric_config` VALUES ('3136', '2', '229', '6', '建制镇占比', '2018-07-04 16:10:23');
INSERT INTO `sec_metric_config` VALUES ('3137', '2', '229', '7', 'year', '2018-07-04 16:10:23');
INSERT INTO `sec_metric_config` VALUES ('3138', '2', '229', '8', '%', '2018-07-04 16:10:23');
INSERT INTO `sec_metric_config` VALUES ('3139', '2', '229', '9', '1', '2018-07-04 16:10:23');
INSERT INTO `sec_metric_config` VALUES ('3140', '2', '233', '1', 'C20011', '2018-07-04 16:41:38');
INSERT INTO `sec_metric_config` VALUES ('3141', '2', '233', '2', 'C20000', '2018-07-04 16:41:38');
INSERT INTO `sec_metric_config` VALUES ('3142', '2', '233', '3', 'obj', '2018-07-04 16:41:38');
INSERT INTO `sec_metric_config` VALUES ('3143', '2', '233', '4', 'bar', '2018-07-04 16:41:38');
INSERT INTO `sec_metric_config` VALUES ('3144', '2', '233', '5', '11', '2018-07-04 16:41:38');
INSERT INTO `sec_metric_config` VALUES ('3145', '2', '233', '6', '村民委员会占比', '2018-07-04 16:41:38');
INSERT INTO `sec_metric_config` VALUES ('3146', '2', '233', '7', 'year', '2018-07-04 16:41:38');
INSERT INTO `sec_metric_config` VALUES ('3147', '2', '233', '8', '%', '2018-07-04 16:41:38');
INSERT INTO `sec_metric_config` VALUES ('3148', '2', '233', '9', '1', '2018-07-04 16:41:38');
INSERT INTO `sec_metric_config` VALUES ('3149', '0', '243', '1', 'C20007', '2018-07-05 09:33:19');
INSERT INTO `sec_metric_config` VALUES ('3150', '0', '243', '2', 'C20000', '2018-07-05 09:33:19');
INSERT INTO `sec_metric_config` VALUES ('3151', '0', '243', '3', 'obj', '2018-07-05 09:33:19');
INSERT INTO `sec_metric_config` VALUES ('3152', '0', '243', '4', 'bar', '2018-07-05 09:33:19');
INSERT INTO `sec_metric_config` VALUES ('3153', '0', '243', '5', '1', '2018-07-05 09:33:19');
INSERT INTO `sec_metric_config` VALUES ('3154', '0', '243', '6', '街道办事处占比', '2018-07-05 09:33:19');
INSERT INTO `sec_metric_config` VALUES ('3155', '0', '243', '7', 'year', '2018-07-05 09:33:19');
INSERT INTO `sec_metric_config` VALUES ('3156', '0', '243', '8', '%', '2018-07-05 09:33:19');
INSERT INTO `sec_metric_config` VALUES ('3157', '0', '243', '9', '1', '2018-07-05 09:33:19');
INSERT INTO `sec_metric_config` VALUES ('3158', '0', '244', '1', 'C20008', '2018-07-05 09:33:19');
INSERT INTO `sec_metric_config` VALUES ('3159', '0', '244', '2', 'C20000', '2018-07-05 09:33:19');
INSERT INTO `sec_metric_config` VALUES ('3160', '0', '244', '3', 'obj', '2018-07-05 09:33:19');
INSERT INTO `sec_metric_config` VALUES ('3161', '0', '244', '4', 'line', '2018-07-05 09:33:19');
INSERT INTO `sec_metric_config` VALUES ('3162', '0', '244', '5', '2', '2018-07-05 09:33:19');
INSERT INTO `sec_metric_config` VALUES ('3163', '0', '244', '6', '建制镇占比', '2018-07-05 09:33:19');
INSERT INTO `sec_metric_config` VALUES ('3164', '0', '244', '7', 'year', '2018-07-05 09:33:19');
INSERT INTO `sec_metric_config` VALUES ('3165', '0', '244', '8', '%', '2018-07-05 09:33:19');
INSERT INTO `sec_metric_config` VALUES ('3166', '0', '244', '9', '1', '2018-07-05 09:33:19');
INSERT INTO `sec_metric_config` VALUES ('3167', '0', '245', '1', 'C20009', '2018-07-05 09:33:19');
INSERT INTO `sec_metric_config` VALUES ('3168', '0', '245', '2', 'C20000', '2018-07-05 09:33:19');
INSERT INTO `sec_metric_config` VALUES ('3169', '0', '245', '3', 'obj', '2018-07-05 09:33:19');
INSERT INTO `sec_metric_config` VALUES ('3170', '0', '245', '4', 'pie', '2018-07-05 09:33:19');
INSERT INTO `sec_metric_config` VALUES ('3171', '0', '245', '5', '3', '2018-07-05 09:33:19');
INSERT INTO `sec_metric_config` VALUES ('3172', '0', '245', '6', '建制乡占比', '2018-07-05 09:33:19');
INSERT INTO `sec_metric_config` VALUES ('3173', '0', '245', '7', 'year', '2018-07-05 09:33:19');
INSERT INTO `sec_metric_config` VALUES ('3174', '0', '245', '8', '%', '2018-07-05 09:33:19');
INSERT INTO `sec_metric_config` VALUES ('3175', '0', '245', '9', '1', '2018-07-05 09:33:19');
INSERT INTO `sec_metric_config` VALUES ('3176', '0', '246', '1', 'C20010', '2018-07-05 09:33:19');
INSERT INTO `sec_metric_config` VALUES ('3177', '0', '246', '2', 'C20000', '2018-07-05 09:33:19');
INSERT INTO `sec_metric_config` VALUES ('3178', '0', '246', '3', 'obj', '2018-07-05 09:33:19');
INSERT INTO `sec_metric_config` VALUES ('3179', '0', '246', '4', 'ringPie', '2018-07-05 09:33:19');
INSERT INTO `sec_metric_config` VALUES ('3180', '0', '246', '5', '4', '2018-07-05 09:33:19');
INSERT INTO `sec_metric_config` VALUES ('3181', '0', '246', '6', '社区居委会占比', '2018-07-05 09:33:19');
INSERT INTO `sec_metric_config` VALUES ('3182', '0', '246', '7', 'year', '2018-07-05 09:33:19');
INSERT INTO `sec_metric_config` VALUES ('3183', '0', '246', '8', '%', '2018-07-05 09:33:19');
INSERT INTO `sec_metric_config` VALUES ('3184', '0', '246', '9', '1', '2018-07-05 09:33:19');
INSERT INTO `sec_metric_config` VALUES ('3284', '0', '247', '1', 'C20007', '2018-07-05 11:31:06');
INSERT INTO `sec_metric_config` VALUES ('3285', '0', '247', '2', 'C20000', '2018-07-05 11:31:06');
INSERT INTO `sec_metric_config` VALUES ('3286', '0', '247', '3', 'obj', '2018-07-05 11:31:06');
INSERT INTO `sec_metric_config` VALUES ('3287', '0', '247', '4', 'bar', '2018-07-05 11:31:06');
INSERT INTO `sec_metric_config` VALUES ('3288', '0', '247', '5', '1', '2018-07-05 11:31:06');
INSERT INTO `sec_metric_config` VALUES ('3289', '0', '247', '6', '街道办事处占比', '2018-07-05 11:31:06');
INSERT INTO `sec_metric_config` VALUES ('3290', '0', '247', '7', 'year', '2018-07-05 11:31:06');
INSERT INTO `sec_metric_config` VALUES ('3291', '0', '247', '8', '%', '2018-07-05 11:31:06');
INSERT INTO `sec_metric_config` VALUES ('3292', '0', '247', '9', '1', '2018-07-05 11:31:06');
INSERT INTO `sec_metric_config` VALUES ('3293', '0', '258', '1', 'C20008', '2018-07-05 11:31:06');
INSERT INTO `sec_metric_config` VALUES ('3294', '0', '258', '2', 'C20000', '2018-07-05 11:31:06');
INSERT INTO `sec_metric_config` VALUES ('3295', '0', '258', '3', 'obj', '2018-07-05 11:31:06');
INSERT INTO `sec_metric_config` VALUES ('3296', '0', '258', '4', 'line', '2018-07-05 11:31:06');
INSERT INTO `sec_metric_config` VALUES ('3297', '0', '258', '5', '2', '2018-07-05 11:31:06');
INSERT INTO `sec_metric_config` VALUES ('3298', '0', '258', '6', '建制镇占比', '2018-07-05 11:31:06');
INSERT INTO `sec_metric_config` VALUES ('3299', '0', '258', '7', 'year', '2018-07-05 11:31:06');
INSERT INTO `sec_metric_config` VALUES ('3300', '0', '258', '8', '%', '2018-07-05 11:31:06');
INSERT INTO `sec_metric_config` VALUES ('3301', '0', '258', '9', '1', '2018-07-05 11:31:06');
INSERT INTO `sec_metric_config` VALUES ('3302', '0', '259', '1', 'C20009', '2018-07-05 11:31:06');
INSERT INTO `sec_metric_config` VALUES ('3303', '0', '259', '2', 'C20000', '2018-07-05 11:31:06');
INSERT INTO `sec_metric_config` VALUES ('3304', '0', '259', '3', 'obj', '2018-07-05 11:31:06');
INSERT INTO `sec_metric_config` VALUES ('3305', '0', '259', '4', 'pie', '2018-07-05 11:31:06');
INSERT INTO `sec_metric_config` VALUES ('3306', '0', '259', '5', '3', '2018-07-05 11:31:06');
INSERT INTO `sec_metric_config` VALUES ('3307', '0', '259', '6', '建制乡占比', '2018-07-05 11:31:06');
INSERT INTO `sec_metric_config` VALUES ('3308', '0', '259', '7', 'year', '2018-07-05 11:31:06');
INSERT INTO `sec_metric_config` VALUES ('3309', '0', '259', '8', '%', '2018-07-05 11:31:06');
INSERT INTO `sec_metric_config` VALUES ('3310', '0', '259', '9', '1', '2018-07-05 11:31:06');
INSERT INTO `sec_metric_config` VALUES ('3311', '0', '260', '1', 'C20010', '2018-07-05 11:31:06');
INSERT INTO `sec_metric_config` VALUES ('3312', '0', '260', '2', 'C20000', '2018-07-05 11:31:06');
INSERT INTO `sec_metric_config` VALUES ('3313', '0', '260', '3', 'obj', '2018-07-05 11:31:06');
INSERT INTO `sec_metric_config` VALUES ('3314', '0', '260', '4', 'ringPie', '2018-07-05 11:31:06');
INSERT INTO `sec_metric_config` VALUES ('3315', '0', '260', '5', '4', '2018-07-05 11:31:06');
INSERT INTO `sec_metric_config` VALUES ('3316', '0', '260', '6', '社区居委会占比', '2018-07-05 11:31:06');
INSERT INTO `sec_metric_config` VALUES ('3317', '0', '260', '7', 'year', '2018-07-05 11:31:06');
INSERT INTO `sec_metric_config` VALUES ('3318', '0', '260', '8', '%', '2018-07-05 11:31:06');
INSERT INTO `sec_metric_config` VALUES ('3319', '0', '260', '9', '1', '2018-07-05 11:31:06');
INSERT INTO `sec_metric_config` VALUES ('3320', '1', '260', '1', 'C20010', '2018-07-05 11:31:32');
INSERT INTO `sec_metric_config` VALUES ('3321', '1', '260', '2', 'C20000', '2018-07-05 11:31:32');
INSERT INTO `sec_metric_config` VALUES ('3322', '1', '260', '3', 'obj', '2018-07-05 11:31:32');
INSERT INTO `sec_metric_config` VALUES ('3323', '1', '260', '4', 'ringPie', '2018-07-05 11:31:32');
INSERT INTO `sec_metric_config` VALUES ('3324', '1', '260', '5', '4', '2018-07-05 11:31:32');
INSERT INTO `sec_metric_config` VALUES ('3325', '1', '260', '6', '社区居委会占比', '2018-07-05 11:31:32');
INSERT INTO `sec_metric_config` VALUES ('3326', '1', '260', '7', 'year', '2018-07-05 11:31:32');
INSERT INTO `sec_metric_config` VALUES ('3327', '1', '260', '8', '%', '2018-07-05 11:31:32');
INSERT INTO `sec_metric_config` VALUES ('3328', '1', '260', '9', '0', '2018-07-05 11:31:32');
INSERT INTO `sec_metric_config` VALUES ('3338', '1', '259', '1', 'C20009', '2018-07-05 11:34:12');
INSERT INTO `sec_metric_config` VALUES ('3339', '1', '259', '2', 'C20000', '2018-07-05 11:34:12');
INSERT INTO `sec_metric_config` VALUES ('3340', '1', '259', '3', 'obj', '2018-07-05 11:34:12');
INSERT INTO `sec_metric_config` VALUES ('3341', '1', '259', '4', 'pie', '2018-07-05 11:34:12');
INSERT INTO `sec_metric_config` VALUES ('3342', '1', '259', '5', '3', '2018-07-05 11:34:12');
INSERT INTO `sec_metric_config` VALUES ('3343', '1', '259', '6', '建制乡占比', '2018-07-05 11:34:12');
INSERT INTO `sec_metric_config` VALUES ('3344', '1', '259', '7', 'year', '2018-07-05 11:34:12');
INSERT INTO `sec_metric_config` VALUES ('3345', '1', '259', '8', '%', '2018-07-05 11:34:12');
INSERT INTO `sec_metric_config` VALUES ('3346', '1', '259', '9', '0', '2018-07-05 11:34:12');
INSERT INTO `sec_metric_config` VALUES ('3347', '1', '260', '1', 'C20010', '2018-07-05 11:34:12');
INSERT INTO `sec_metric_config` VALUES ('3348', '1', '260', '2', 'C20000', '2018-07-05 11:34:12');
INSERT INTO `sec_metric_config` VALUES ('3349', '1', '260', '3', 'obj', '2018-07-05 11:34:12');
INSERT INTO `sec_metric_config` VALUES ('3350', '1', '260', '4', 'ringPie', '2018-07-05 11:34:12');
INSERT INTO `sec_metric_config` VALUES ('3351', '1', '260', '5', '4', '2018-07-05 11:34:12');
INSERT INTO `sec_metric_config` VALUES ('3352', '1', '260', '6', '社区居委会占比', '2018-07-05 11:34:12');
INSERT INTO `sec_metric_config` VALUES ('3353', '1', '260', '7', 'year', '2018-07-05 11:34:12');
INSERT INTO `sec_metric_config` VALUES ('3354', '1', '260', '8', '%', '2018-07-05 11:34:12');
INSERT INTO `sec_metric_config` VALUES ('3355', '1', '260', '9', '0', '2018-07-05 11:34:12');
INSERT INTO `sec_metric_config` VALUES ('3374', '1', '259', '1', 'C20009', '2018-07-05 11:37:18');
INSERT INTO `sec_metric_config` VALUES ('3375', '1', '259', '2', 'C20000', '2018-07-05 11:37:18');
INSERT INTO `sec_metric_config` VALUES ('3376', '1', '259', '3', 'obj', '2018-07-05 11:37:18');
INSERT INTO `sec_metric_config` VALUES ('3377', '1', '259', '4', 'pie', '2018-07-05 11:37:18');
INSERT INTO `sec_metric_config` VALUES ('3378', '1', '259', '5', '3', '2018-07-05 11:37:18');
INSERT INTO `sec_metric_config` VALUES ('3379', '1', '259', '6', '建制乡占比', '2018-07-05 11:37:18');
INSERT INTO `sec_metric_config` VALUES ('3380', '1', '259', '7', 'year', '2018-07-05 11:37:18');
INSERT INTO `sec_metric_config` VALUES ('3381', '1', '259', '8', '%', '2018-07-05 11:37:18');
INSERT INTO `sec_metric_config` VALUES ('3382', '1', '259', '9', '0', '2018-07-05 11:37:18');
INSERT INTO `sec_metric_config` VALUES ('3383', '1', '260', '1', 'C20010', '2018-07-05 11:37:18');
INSERT INTO `sec_metric_config` VALUES ('3384', '1', '260', '2', 'C20000', '2018-07-05 11:37:18');
INSERT INTO `sec_metric_config` VALUES ('3385', '1', '260', '3', 'obj', '2018-07-05 11:37:18');
INSERT INTO `sec_metric_config` VALUES ('3386', '1', '260', '4', 'ringPie', '2018-07-05 11:37:18');
INSERT INTO `sec_metric_config` VALUES ('3387', '1', '260', '5', '4', '2018-07-05 11:37:18');
INSERT INTO `sec_metric_config` VALUES ('3388', '1', '260', '6', '社区居委会占比', '2018-07-05 11:37:18');
INSERT INTO `sec_metric_config` VALUES ('3389', '1', '260', '7', 'year', '2018-07-05 11:37:18');
INSERT INTO `sec_metric_config` VALUES ('3390', '1', '260', '8', '%', '2018-07-05 11:37:18');
INSERT INTO `sec_metric_config` VALUES ('3391', '1', '260', '9', '0', '2018-07-05 11:37:18');
INSERT INTO `sec_metric_config` VALUES ('3401', '0', '261', '1', 'C20007', '2018-07-27 15:21:27');
INSERT INTO `sec_metric_config` VALUES ('3402', '0', '261', '2', 'C20000', '2018-07-27 15:21:27');
INSERT INTO `sec_metric_config` VALUES ('3403', '0', '261', '3', 'obj', '2018-07-27 15:21:27');
INSERT INTO `sec_metric_config` VALUES ('3404', '0', '261', '4', 'bar', '2018-07-27 15:21:27');
INSERT INTO `sec_metric_config` VALUES ('3405', '0', '261', '5', '1', '2018-07-27 15:21:27');
INSERT INTO `sec_metric_config` VALUES ('3406', '0', '261', '6', '街道办事处占比', '2018-07-27 15:21:27');
INSERT INTO `sec_metric_config` VALUES ('3407', '0', '261', '7', 'year', '2018-07-27 15:21:27');
INSERT INTO `sec_metric_config` VALUES ('3408', '0', '261', '8', '%', '2018-07-27 15:21:27');
INSERT INTO `sec_metric_config` VALUES ('3409', '0', '261', '9', '1', '2018-07-27 15:21:27');
INSERT INTO `sec_metric_config` VALUES ('3410', '0', '262', '1', 'C20008', '2018-07-27 15:21:27');
INSERT INTO `sec_metric_config` VALUES ('3411', '0', '262', '2', 'C20000', '2018-07-27 15:21:27');
INSERT INTO `sec_metric_config` VALUES ('3412', '0', '262', '3', 'obj', '2018-07-27 15:21:27');
INSERT INTO `sec_metric_config` VALUES ('3413', '0', '262', '4', 'line', '2018-07-27 15:21:27');
INSERT INTO `sec_metric_config` VALUES ('3414', '0', '262', '5', '2', '2018-07-27 15:21:27');
INSERT INTO `sec_metric_config` VALUES ('3415', '0', '262', '6', '建制镇占比', '2018-07-27 15:21:27');
INSERT INTO `sec_metric_config` VALUES ('3416', '0', '262', '7', 'year', '2018-07-27 15:21:27');
INSERT INTO `sec_metric_config` VALUES ('3417', '0', '262', '8', '%', '2018-07-27 15:21:27');
INSERT INTO `sec_metric_config` VALUES ('3418', '0', '262', '9', '1', '2018-07-27 15:21:27');
INSERT INTO `sec_metric_config` VALUES ('3419', '0', '263', '1', 'C20009', '2018-07-27 15:21:27');
INSERT INTO `sec_metric_config` VALUES ('3420', '0', '263', '2', 'C20000', '2018-07-27 15:21:27');
INSERT INTO `sec_metric_config` VALUES ('3421', '0', '263', '3', 'obj', '2018-07-27 15:21:27');
INSERT INTO `sec_metric_config` VALUES ('3422', '0', '263', '4', 'pie', '2018-07-27 15:21:27');
INSERT INTO `sec_metric_config` VALUES ('3423', '0', '263', '5', '3', '2018-07-27 15:21:27');
INSERT INTO `sec_metric_config` VALUES ('3424', '0', '263', '6', '建制乡占比', '2018-07-27 15:21:27');
INSERT INTO `sec_metric_config` VALUES ('3425', '0', '263', '7', 'year', '2018-07-27 15:21:27');
INSERT INTO `sec_metric_config` VALUES ('3426', '0', '263', '8', '%', '2018-07-27 15:21:27');
INSERT INTO `sec_metric_config` VALUES ('3427', '0', '263', '9', '1', '2018-07-27 15:21:27');
INSERT INTO `sec_metric_config` VALUES ('3428', '0', '264', '1', 'C20001', '2018-07-27 15:21:27');
INSERT INTO `sec_metric_config` VALUES ('3429', '0', '264', '2', 'C20000', '2018-07-27 15:21:27');
INSERT INTO `sec_metric_config` VALUES ('3430', '0', '264', '3', 'obj', '2018-07-27 15:21:27');
INSERT INTO `sec_metric_config` VALUES ('3431', '0', '264', '4', 'gauge', '2018-07-27 15:21:27');
INSERT INTO `sec_metric_config` VALUES ('3432', '0', '264', '5', '5', '2018-07-27 15:21:27');
INSERT INTO `sec_metric_config` VALUES ('3433', '0', '264', '6', '街道办事处', '2018-07-27 15:21:27');
INSERT INTO `sec_metric_config` VALUES ('3434', '0', '264', '7', 'year', '2018-07-27 15:21:27');
INSERT INTO `sec_metric_config` VALUES ('3435', '0', '264', '8', '个', '2018-07-27 15:21:27');
INSERT INTO `sec_metric_config` VALUES ('3436', '0', '264', '9', '1', '2018-07-27 15:21:27');
INSERT INTO `sec_metric_config` VALUES ('3437', '1', '259', '1', 'C20009', '2018-08-08 17:30:27');
INSERT INTO `sec_metric_config` VALUES ('3438', '1', '259', '2', 'C20000', '2018-08-08 17:30:27');
INSERT INTO `sec_metric_config` VALUES ('3439', '1', '259', '3', 'obj', '2018-08-08 17:30:27');
INSERT INTO `sec_metric_config` VALUES ('3440', '1', '259', '4', 'pie', '2018-08-08 17:30:27');
INSERT INTO `sec_metric_config` VALUES ('3441', '1', '259', '5', '3', '2018-08-08 17:30:27');
INSERT INTO `sec_metric_config` VALUES ('3442', '1', '259', '6', '建制乡占比', '2018-08-08 17:30:27');
INSERT INTO `sec_metric_config` VALUES ('3443', '1', '259', '7', 'year', '2018-08-08 17:30:27');
INSERT INTO `sec_metric_config` VALUES ('3444', '1', '259', '8', '%', '2018-08-08 17:30:27');
INSERT INTO `sec_metric_config` VALUES ('3445', '1', '259', '9', '1', '2018-08-08 17:30:27');
INSERT INTO `sec_metric_config` VALUES ('3446', '1', '260', '1', 'C20010', '2018-08-08 17:30:27');
INSERT INTO `sec_metric_config` VALUES ('3447', '1', '260', '2', 'C20000', '2018-08-08 17:30:27');
INSERT INTO `sec_metric_config` VALUES ('3448', '1', '260', '3', 'obj', '2018-08-08 17:30:27');
INSERT INTO `sec_metric_config` VALUES ('3449', '1', '260', '4', 'ringPie', '2018-08-08 17:30:27');
INSERT INTO `sec_metric_config` VALUES ('3450', '1', '260', '5', '4', '2018-08-08 17:30:27');
INSERT INTO `sec_metric_config` VALUES ('3451', '1', '260', '6', '社区居委会占比', '2018-08-08 17:30:27');
INSERT INTO `sec_metric_config` VALUES ('3452', '1', '260', '7', 'year', '2018-08-08 17:30:27');
INSERT INTO `sec_metric_config` VALUES ('3453', '1', '260', '8', '%', '2018-08-08 17:30:27');
INSERT INTO `sec_metric_config` VALUES ('3454', '1', '260', '9', '1', '2018-08-08 17:30:27');
INSERT INTO `sec_metric_config` VALUES ('3455', '1', '259', '1', 'C20009', '2018-08-08 17:30:28');
INSERT INTO `sec_metric_config` VALUES ('3456', '1', '259', '2', 'C20000', '2018-08-08 17:30:28');
INSERT INTO `sec_metric_config` VALUES ('3457', '1', '259', '3', 'obj', '2018-08-08 17:30:28');
INSERT INTO `sec_metric_config` VALUES ('3458', '1', '259', '4', 'pie', '2018-08-08 17:30:28');
INSERT INTO `sec_metric_config` VALUES ('3459', '1', '259', '5', '3', '2018-08-08 17:30:28');
INSERT INTO `sec_metric_config` VALUES ('3460', '1', '259', '6', '建制乡占比', '2018-08-08 17:30:28');
INSERT INTO `sec_metric_config` VALUES ('3461', '1', '259', '7', 'year', '2018-08-08 17:30:28');
INSERT INTO `sec_metric_config` VALUES ('3462', '1', '259', '8', '%', '2018-08-08 17:30:28');
INSERT INTO `sec_metric_config` VALUES ('3463', '1', '259', '9', '1', '2018-08-08 17:30:28');
INSERT INTO `sec_metric_config` VALUES ('3464', '1', '260', '1', 'C20010', '2018-08-08 17:30:28');
INSERT INTO `sec_metric_config` VALUES ('3465', '1', '260', '2', 'C20000', '2018-08-08 17:30:28');
INSERT INTO `sec_metric_config` VALUES ('3466', '1', '260', '3', 'obj', '2018-08-08 17:30:28');
INSERT INTO `sec_metric_config` VALUES ('3467', '1', '260', '4', 'ringPie', '2018-08-08 17:30:28');
INSERT INTO `sec_metric_config` VALUES ('3468', '1', '260', '5', '4', '2018-08-08 17:30:28');
INSERT INTO `sec_metric_config` VALUES ('3469', '1', '260', '6', '社区居委会占比', '2018-08-08 17:30:28');
INSERT INTO `sec_metric_config` VALUES ('3470', '1', '260', '7', 'year', '2018-08-08 17:30:28');
INSERT INTO `sec_metric_config` VALUES ('3471', '1', '260', '8', '%', '2018-08-08 17:30:28');
INSERT INTO `sec_metric_config` VALUES ('3472', '1', '260', '9', '1', '2018-08-08 17:30:28');
INSERT INTO `sec_metric_config` VALUES ('3473', '0', '257', '1', 'C20007', '2018-08-08 17:31:30');
INSERT INTO `sec_metric_config` VALUES ('3474', '0', '257', '2', 'C20000', '2018-08-08 17:31:30');
INSERT INTO `sec_metric_config` VALUES ('3475', '0', '257', '3', 'obj', '2018-08-08 17:31:30');
INSERT INTO `sec_metric_config` VALUES ('3476', '0', '257', '4', 'bar', '2018-08-08 17:31:30');
INSERT INTO `sec_metric_config` VALUES ('3477', '0', '257', '5', '1', '2018-08-08 17:31:30');
INSERT INTO `sec_metric_config` VALUES ('3478', '0', '257', '6', '街道办事处占比', '2018-08-08 17:31:30');
INSERT INTO `sec_metric_config` VALUES ('3479', '0', '257', '7', 'year', '2018-08-08 17:31:30');
INSERT INTO `sec_metric_config` VALUES ('3480', '0', '257', '8', '%', '2018-08-08 17:31:30');
INSERT INTO `sec_metric_config` VALUES ('3481', '0', '257', '9', '1', '2018-08-08 17:31:30');
INSERT INTO `sec_metric_config` VALUES ('3482', '1', '259', '1', 'C20009', '2018-08-13 13:32:27');
INSERT INTO `sec_metric_config` VALUES ('3483', '1', '259', '2', 'C20000', '2018-08-13 13:32:27');
INSERT INTO `sec_metric_config` VALUES ('3484', '1', '259', '3', 'obj', '2018-08-13 13:32:27');
INSERT INTO `sec_metric_config` VALUES ('3485', '1', '259', '4', 'pie', '2018-08-13 13:32:27');
INSERT INTO `sec_metric_config` VALUES ('3486', '1', '259', '5', '3', '2018-08-13 13:32:27');
INSERT INTO `sec_metric_config` VALUES ('3487', '1', '259', '6', '建制乡占比', '2018-08-13 13:32:27');
INSERT INTO `sec_metric_config` VALUES ('3488', '1', '259', '7', 'year', '2018-08-13 13:32:27');
INSERT INTO `sec_metric_config` VALUES ('3489', '1', '259', '8', '%', '2018-08-13 13:32:27');
INSERT INTO `sec_metric_config` VALUES ('3490', '1', '259', '9', '0', '2018-08-13 13:32:27');
INSERT INTO `sec_metric_config` VALUES ('3500', '0', '256', '1', 'C20002', '2018-08-14 14:46:24');
INSERT INTO `sec_metric_config` VALUES ('3501', '0', '256', '2', 'C20000', '2018-08-14 14:46:24');
INSERT INTO `sec_metric_config` VALUES ('3502', '0', '256', '3', 'obj', '2018-08-14 14:46:24');
INSERT INTO `sec_metric_config` VALUES ('3503', '0', '256', '4', 'line', '2018-08-14 14:46:24');
INSERT INTO `sec_metric_config` VALUES ('3504', '0', '256', '5', '6', '2018-08-14 14:46:24');
INSERT INTO `sec_metric_config` VALUES ('3505', '0', '256', '6', '建制镇', '2018-08-14 14:46:24');
INSERT INTO `sec_metric_config` VALUES ('3506', '0', '256', '7', 'year', '2018-08-14 14:46:24');
INSERT INTO `sec_metric_config` VALUES ('3507', '0', '256', '8', '个', '2018-08-14 14:46:24');
INSERT INTO `sec_metric_config` VALUES ('3508', '0', '256', '9', '1', '2018-08-14 14:46:24');
INSERT INTO `sec_metric_config` VALUES ('3527', '0', '265', '1', 'C20007', '2018-08-14 17:20:27');
INSERT INTO `sec_metric_config` VALUES ('3528', '0', '265', '2', 'C20000', '2018-08-14 17:20:27');
INSERT INTO `sec_metric_config` VALUES ('3529', '0', '265', '3', 'obj', '2018-08-14 17:20:27');
INSERT INTO `sec_metric_config` VALUES ('3530', '0', '265', '4', 'bar', '2018-08-14 17:20:27');
INSERT INTO `sec_metric_config` VALUES ('3531', '0', '265', '5', '1', '2018-08-14 17:20:27');
INSERT INTO `sec_metric_config` VALUES ('3532', '0', '265', '6', '街道办事处占比', '2018-08-14 17:20:27');
INSERT INTO `sec_metric_config` VALUES ('3533', '0', '265', '7', 'year', '2018-08-14 17:20:27');
INSERT INTO `sec_metric_config` VALUES ('3534', '0', '265', '8', '%', '2018-08-14 17:20:27');
INSERT INTO `sec_metric_config` VALUES ('3535', '0', '265', '9', '1', '2018-08-14 17:20:27');
INSERT INTO `sec_metric_config` VALUES ('3536', '0', '267', '1', 'C50004', '2018-08-14 17:20:27');
INSERT INTO `sec_metric_config` VALUES ('3537', '0', '267', '2', 'C50000', '2018-08-14 17:20:27');
INSERT INTO `sec_metric_config` VALUES ('3538', '0', '267', '3', 'obj', '2018-08-14 17:20:27');
INSERT INTO `sec_metric_config` VALUES ('3539', '0', '267', '4', 'bar', '2018-08-14 17:20:27');
INSERT INTO `sec_metric_config` VALUES ('3540', '0', '267', '5', '6', '2018-08-14 17:20:27');
INSERT INTO `sec_metric_config` VALUES ('3541', '0', '267', '6', '个体增长率', '2018-08-14 17:20:27');
INSERT INTO `sec_metric_config` VALUES ('3542', '0', '267', '7', 'year', '2018-08-14 17:20:27');
INSERT INTO `sec_metric_config` VALUES ('3543', '0', '267', '8', '%', '2018-08-14 17:20:27');
INSERT INTO `sec_metric_config` VALUES ('3544', '0', '267', '9', '1', '2018-08-14 17:20:27');

-- ----------------------------
-- Table structure for sec_msg_src_config
-- ----------------------------
DROP TABLE IF EXISTS `sec_msg_src_config`;
CREATE TABLE `sec_msg_src_config` (
  `config_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `sec_msg_src_id` int(11) DEFAULT NULL,
  `param_id` int(11) DEFAULT NULL,
  `param_value` varchar(40) DEFAULT NULL,
  `cre_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sec_msg_src_config
-- ----------------------------

-- ----------------------------
-- Table structure for system_resource
-- ----------------------------
DROP TABLE IF EXISTS `system_resource`;
CREATE TABLE `system_resource` (
  `term_type_id` int(11) NOT NULL COMMENT '终端类型ID：0-桌面电脑，1-移动电话，2-平板电脑',
  `object_type_id` int(11) NOT NULL COMMENT '对象类型ID',
  `object_id` int(11) NOT NULL COMMENT '对象ID（资源所属的对象的ID，即应用ID或应用页面ID',
  `res_type_id` int(11) NOT NULL COMMENT '资源类型ID',
  `res_url` varchar(256) DEFAULT NULL COMMENT '资源路径',
  PRIMARY KEY (`term_type_id`,`object_type_id`,`object_id`,`res_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统资源表';

-- ----------------------------
-- Records of system_resource
-- ----------------------------
INSERT INTO `system_resource` VALUES ('1', '0', '43', '0', 'http://10.1.3.193:8080/portal/upload/files/app/a1.png');
INSERT INTO `system_resource` VALUES ('1', '0', '102', '0', 'http://10.1.3.193:8080/portal/upload/files/app/a2.png');
INSERT INTO `system_resource` VALUES ('1', '0', '103', '0', 'http://10.1.3.193:8080/portal/upload/files/app/a3.png');
INSERT INTO `system_resource` VALUES ('1', '0', '104', '0', 'http://10.1.3.193:8080/portal/upload/files/app/a4.png');
INSERT INTO `system_resource` VALUES ('1', '0', '106', '0', 'http://10.1.3.193:8080/portal/upload/files/app/a5.png');
INSERT INTO `system_resource` VALUES ('1', '0', '107', '0', 'http://10.1.3.193:8080/portal/upload/files/app/a6.png');
INSERT INTO `system_resource` VALUES ('1', '0', '108', '0', 'http://10.1.3.193:8080/portal/upload/files/app/a7.png');
INSERT INTO `system_resource` VALUES ('1', '0', '109', '0', 'http://10.1.3.193:8080/portal/upload/files/app/a1.png');
INSERT INTO `system_resource` VALUES ('1', '0', '110', '0', 'http://10.1.3.193:8080/portal/upload/files/app/a2.png');
INSERT INTO `system_resource` VALUES ('1', '0', '111', '0', 'http://10.1.3.193:8080/portal/upload/files/app/a3.png');
INSERT INTO `system_resource` VALUES ('1', '0', '124', '0', 'http://10.1.3.193:8080/portal/upload/files/app/a4.png');
INSERT INTO `system_resource` VALUES ('1', '0', '128', '0', 'http://10.1.3.193:8080/portal/upload/files/app/a5.png');
INSERT INTO `system_resource` VALUES ('1', '0', '129', '0', 'http://10.1.3.193:8080/portal/upload/files/app/a6.png');
INSERT INTO `system_resource` VALUES ('1', '0', '131', '0', 'http://10.1.3.193:8080/portal/upload/files/app/a7.png');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `super_menu_id` int(11) DEFAULT NULL COMMENT '上级菜单ID',
  `menu_cn_name` varchar(40) DEFAULT NULL COMMENT '菜单中文名',
  `menu_name` varchar(40) DEFAULT NULL COMMENT '菜单名称',
  `menu_icon_url` varchar(200) DEFAULT NULL COMMENT '菜单图标URL',
  `menu_url` varchar(200) DEFAULT NULL COMMENT '菜单功能URL',
  `menu_level` int(11) NOT NULL COMMENT '菜单级别',
  `menu_state` int(11) NOT NULL COMMENT '菜单状态',
  `app_id` int(11) DEFAULT NULL COMMENT '对应的应用ID',
  `disp_order` int(11) DEFAULT NULL COMMENT '显示顺序',
  `cre_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `upd_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=196 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', null, '系统主菜单', 'main_frame', null, '555555', '1', '1', null, '8', null, '2018-06-11 14:40:01');
INSERT INTO `sys_menu` VALUES ('2', '1', '系统管理', 'sys_adminaaa', 'fa fa-home', 'qq', '5', '1', null, '39', null, '2018-06-14 16:39:01');
INSERT INTO `sys_menu` VALUES ('5', '1', '数据管理', 'data_admin', 'fa fa-magic', null, '2', '1', null, '79', null, null);
INSERT INTO `sys_menu` VALUES ('8', '2', '用户管理', 'user_admin', null, 'sysUser/list', '3', '1', null, '37', null, '2018-06-11 14:51:19');
INSERT INTO `sys_menu` VALUES ('10', '2', '菜单管理', 'menu_admin', null, 'sysMenu/list', '3', '1', null, '36', null, null);
INSERT INTO `sys_menu` VALUES ('11', '2', '应用集成管理', 'app_admin', null, 'application/list', '3', '1', null, '35', null, null);
INSERT INTO `sys_menu` VALUES ('12', '2', '角色管理', 'role_admin', null, 'role/list', '3', '1', null, '34', null, null);
INSERT INTO `sys_menu` VALUES ('13', '2', '部门管理', 'dep_admin', null, 'userDepartment/list', '3', '1', null, '33', null, null);
INSERT INTO `sys_menu` VALUES ('14', '2', '岗位管理', 'job_admin', null, 'userJob/list', '3', '1', null, '32', null, '2018-07-25 14:42:35');
INSERT INTO `sys_menu` VALUES ('16', '2', '日志管理', 'wel_main', '', null, '3', '1', null, '31', null, null);
INSERT INTO `sys_menu` VALUES ('17', '16', '日志查询', 'main1', '', 'userAccessLog/list', '4', '1', null, '30', '2018-04-14 14:53:58', '2018-04-14 14:54:01');
INSERT INTO `sys_menu` VALUES ('18', '16', '日志报表', 'main2', '', 'userAccessLog/logTable', '4', '1', null, '29', '2018-04-14 14:53:58', '2018-04-14 14:54:01');
INSERT INTO `sys_menu` VALUES ('45', '2', '信息管理', 'info', '', '', '2', '1', null, '28', null, null);
INSERT INTO `sys_menu` VALUES ('46', '45', '信息搜索', 'search_info', '', 'info/list', '3', '1', null, '27', null, null);
INSERT INTO `sys_menu` VALUES ('50', '2', '业务监测应用管理', 'app_page', null, 'application/bizlist', '0', '1', null, '35', '2018-05-03 11:37:14', '2018-05-04 11:00:36');
INSERT INTO `sys_menu` VALUES ('52', '2', '内容管理', 'message_admin', null, '', '3', '1', null, '25', '2018-05-22 09:53:43', '2018-05-24 13:58:11');
INSERT INTO `sys_menu` VALUES ('57', '2', '配置版本管理', '指标配置管理', null, 'secMetricConfig/list', '3', '1', null, '19', '2018-05-07 16:08:51', '2018-05-07 16:08:55');
INSERT INTO `sys_menu` VALUES ('58', '52', '内容浏览', 'message_browse', null, 'mesManage/list', '4', '1', null, '24', '2018-05-28 17:39:47', '2018-05-28 17:39:50');
INSERT INTO `sys_menu` VALUES ('59', '52', '内容审核管理', 'mes_appr_manage', null, 'mesManage/mesappr', '4', '1', null, '23', '2018-05-28 17:40:45', '2018-05-28 17:40:49');
INSERT INTO `sys_menu` VALUES ('60', '52', '内容标签管理', 'mes_tag_manage', null, 'mesManage/taglist', '4', '1', null, '22', '2018-05-28 17:42:20', '2018-05-28 17:42:24');
INSERT INTO `sys_menu` VALUES ('69', '52', '资料库管理', 'database_manag', null, 'mesManage/databaselist', '4', '1', null, '21', '2018-06-04 18:15:18', '2018-06-04 18:15:21');
INSERT INTO `sys_menu` VALUES ('70', '52', '资料类别管理', 'datatype_manag', null, 'mesManage/datatype', '4', '1', null, '20', '2018-06-04 18:16:23', '2018-06-04 18:16:27');
INSERT INTO `sys_menu` VALUES ('103', '5', '数据治理平台', 'data_mng', null, 'http://10.1.2.143:8080/', '4', '1', '39', '77', '2018-06-12 19:19:50', '2018-06-15 16:34:24');
INSERT INTO `sys_menu` VALUES ('104', '5', '指标管理平台', 'index_mng', null, 'http://10.1.3.193:8080/bsbProd/main.jsp', '6', '1', '61', '76', '2018-06-12 19:27:17', '2018-06-27 13:12:53');
INSERT INTO `sys_menu` VALUES ('138', '1', '数据分析', 'data_analysis', 'fa fa-edit', '无', '0', '1', null, '59', '2018-06-14 16:14:41', '2018-06-14 16:15:26');
INSERT INTO `sys_menu` VALUES ('139', '138', '分布式挖掘系统', 'distributed_mining_sys', null, 'http://10.1.3.207:54321', '7', '1', '62', '58', '2018-06-14 16:19:20', '2018-06-15 16:34:54');
INSERT INTO `sys_menu` VALUES ('141', '138', '高维模型分析', 'high_dimensional_model_analysis', null, 'http://10.1.3.204:7070/kylin', '5', '1', '63', '56', '2018-06-14 16:23:10', '2018-06-15 16:35:08');
INSERT INTO `sys_menu` VALUES ('144', '138', '批量日志分析', 'log_analysis', null, 'http://10.1.3.208:61888/', '7', '1', '65', '53', '2018-06-14 16:24:45', '2018-06-15 16:35:37');
INSERT INTO `sys_menu` VALUES ('145', '1', '数据接入', '数据接入', 'fa fa-columns', '无', '2', '1', null, '99', '2018-06-14 16:26:12', '2018-06-14 17:52:14');
INSERT INTO `sys_menu` VALUES ('146', '145', '共享交换系统', 'Shared_switching_sys', null, 'http://10.1.2.142:8080/cloud/', '7', '1', '53', '98', '2018-06-14 16:26:47', '2018-07-12 13:33:07');
INSERT INTO `sys_menu` VALUES ('147', '145', '网络爬虫系统', 'Network_crawler_sys', null, 'http://10.1.3.193:8080/SpiderManage', '6', '1', '54', '97', '2018-06-14 16:27:12', '2018-06-15 16:32:26');
INSERT INTO `sys_menu` VALUES ('149', '159', '大数据安全管理', 'data_security_mng', null, 'http://10.1.3.208:6080/index.html', '4', '1', '56', '87', '2018-06-14 16:31:16', '2018-06-15 16:32:55');
INSERT INTO `sys_menu` VALUES ('150', '159', '大数据使用平台', 'data_use_platform', null, 'http://10.1.3.206:8888', '3', '1', '57', '86', '2018-06-14 16:31:50', '2018-06-15 16:33:12');
INSERT INTO `sys_menu` VALUES ('151', '159', '大数据分级管理', 'data_metadata _mng', null, 'http://10.1.3.208:21000/index.html', '2', '1', '58', '85', '2018-06-14 16:32:45', '2018-06-15 16:33:48');
INSERT INTO `sys_menu` VALUES ('152', '1', '监控运维', '监控运维', 'fa fa-desktop', 'http://10.1.3.77/zabbix', '8', '1', '66', '50', '2018-06-14 16:37:19', '2018-06-15 16:35:50');
INSERT INTO `sys_menu` VALUES ('154', '1', '数据可视化', '数据可视化', 'fa fa fa-bar-chart-o', '无', '9', '1', '112', '69', '2018-06-14 16:38:23', '2018-06-15 19:23:44');
INSERT INTO `sys_menu` VALUES ('155', '1', '实时外汇行情', 'foreign _currency', null, 'http://10.1.3.193:8080/SpiderShower/', '1', '0', '43', '48', '2018-06-14 16:41:11', '2018-06-14 17:34:58');
INSERT INTO `sys_menu` VALUES ('156', '5', '资源目录系统', 'dic_mng', null, 'http://10.1.2.141:8080/loginA?username=admin&password=123456', '6', '1', '59', '78', '2018-06-14 16:45:16', '2018-06-15 16:34:07');
INSERT INTO `sys_menu` VALUES ('159', '1', '大数据平台', 'data_platform', 'fa fa-picture-o', '无', '1', '1', '35', '89', '2018-06-15 11:49:39', '2018-06-15 11:56:15');
INSERT INTO `sys_menu` VALUES ('160', '159', '大数据管理平台', 'dat_mng_pl', null, 'http://10.1.3.208:9999/#/main/dashboard/metrics', '3', '1', '55', '88', '2018-06-15 11:55:05', '2018-06-15 16:32:41');
INSERT INTO `sys_menu` VALUES ('161', '138', '大数据检索引擎', 'data_search_engine', null, 'http://10.1.3.208:8983/solr/#/', '4', '1', '64', '55', '2018-06-15 14:02:15', '2018-06-15 16:35:22');
INSERT INTO `sys_menu` VALUES ('162', '1', '城市交通', 'anonymous-menu', null, 'http://www.baidu.com', '1', '0', '102', '0', null, null);
INSERT INTO `sys_menu` VALUES ('163', '1', '经济运行', 'anonymous-menu', null, 'http://www.baidu.com', '1', '0', '103', '0', null, null);
INSERT INTO `sys_menu` VALUES ('164', '1', '城市生命线', 'anonymous-menu', null, 'http://www.baidu.com', '1', '0', '104', '0', null, null);
INSERT INTO `sys_menu` VALUES ('165', '1', '环境气象', 'anonymous-menu', null, 'http://www.baidu.com', '1', '0', '106', '0', null, null);
INSERT INTO `sys_menu` VALUES ('166', '1', '政务服务', 'anonymous-menu', null, 'http://www.baidu.com', '1', '0', '107', '0', null, null);
INSERT INTO `sys_menu` VALUES ('167', '1', '一带一路', 'anonymous-menu', null, 'http://www.baidu.com', '1', '0', '108', '0', null, null);
INSERT INTO `sys_menu` VALUES ('168', '1', '态势感知', 'anonymous-menu', null, 'http://10.1.2.143:8080/WebReport/ReportServer?formlet=RLT.frm', '1', '0', '109', '1', null, '2018-06-19 18:54:45');
INSERT INTO `sys_menu` VALUES ('169', '1', '民生幸福指数', 'anonymous-menu', null, 'http://www.baidu.com', '1', '0', '110', '0', null, null);
INSERT INTO `sys_menu` VALUES ('170', '1', '外贸分析地图', 'anonymous-menu', null, 'http://10.1.3.193:8088/hainan/trade.html', '1', '0', '111', '0', null, null);
INSERT INTO `sys_menu` VALUES ('177', '154', '人群实时分布', '人群实时分布', null, 'http://10.1.2.143:8080/WebReport/ReportServer?formlet=RLT.frm', '2', '1', '113', '1', '2018-06-15 19:30:06', '2018-06-15 19:30:05');
INSERT INTO `sys_menu` VALUES ('178', '154', '待办事项统计', '待办事项统计', null, 'http://10.1.2.143:8080/WebReport/ReportServer?reportlet=RYGJ.cpt&op=view', '3', '1', '114', '1', '2018-06-15 19:30:44', '2018-06-15 19:31:40');
INSERT INTO `sys_menu` VALUES ('180', '154', '数据可视化工具', '数据可视化工具', null, 'http://10.1.2.143:37799/WebReport/ReportServer?op=fs', '4', '1', '116', '3', '2018-06-19 12:06:38', '2018-06-19 12:42:03');
INSERT INTO `sys_menu` VALUES ('187', '2', '交易及会员数据分析服务系统', 'trade_data_analyse;', null, 'http://localhost:8080/cqpgx-data/shiro-cas', '1', '0', '124', '1', '2018-06-29 17:24:33', '2018-07-03 15:23:15');
INSERT INTO `sys_menu` VALUES ('191', null, '业务监测应用', '业务监测', null, '--', '0', '0', '128', '1', '2018-07-05 10:29:11', '2018-07-05 10:33:48');
INSERT INTO `sys_menu` VALUES ('192', null, '数据监测', '数据监测', null, '--', '0', '0', '129', '1', '2018-07-05 10:29:25', '2018-07-05 10:33:57');
INSERT INTO `sys_menu` VALUES ('194', '2', '智能应用APP', 'ST_APP', null, '/goStapp', '1', '1', null, '1', '2018-08-14 17:15:10', '2018-08-14 17:17:26');
INSERT INTO `sys_menu` VALUES ('195', '194', '智能应用APP仪表盘', 'dash_app', null, 'goDashApp', '2', '0', '131', '1', '2018-08-14 17:18:39', '2018-08-14 17:18:39');

-- ----------------------------
-- Table structure for sys_privilege
-- ----------------------------
DROP TABLE IF EXISTS `sys_privilege`;
CREATE TABLE `sys_privilege` (
  `sys_priv_id` int(11) NOT NULL AUTO_INCREMENT,
  `sys_priv_name` varchar(40) NOT NULL,
  `cre_time` date DEFAULT NULL,
  `upd_time` date DEFAULT NULL,
  PRIMARY KEY (`sys_priv_id`),
  UNIQUE KEY `sys_priv_name_idx` (`sys_priv_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_privilege
-- ----------------------------
INSERT INTO `sys_privilege` VALUES ('1', 'user:insert', '2018-07-09', '2018-07-09');
INSERT INTO `sys_privilege` VALUES ('2', 'user:delete', '2018-07-09', '2018-07-09');
INSERT INTO `sys_privilege` VALUES ('3', 'user:update', '2018-07-09', '2018-07-09');
INSERT INTO `sys_privilege` VALUES ('4', 'user:select', '2018-07-09', '2018-07-09');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(128) DEFAULT NULL,
  `user_password` varchar(128) DEFAULT NULL,
  `user_real_name` varchar(128) DEFAULT NULL,
  `user_state` int(11) NOT NULL,
  `job_id` int(11) DEFAULT NULL,
  `user_addr` varchar(200) DEFAULT NULL,
  `user_tel` varchar(20) DEFAULT NULL,
  `user_global_id` varchar(60) DEFAULT NULL,
  `cre_time` timestamp NULL DEFAULT NULL,
  `upd_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3', '管理员', '1', '1', null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('2', 'user1', '21232f297a57a5a743894a0e4a801fc3', '用户1', '1', '2', null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('3', 'user2', '21232f297a57a5a743894a0e4a801fc3', '用户2', '1', '4', null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('6', 'user3', '21232f297a57a5a743894a0e4a801fc3', 'user3', '1', '16', null, null, null, '2018-06-28 15:12:05', '2018-06-28 15:12:05');
INSERT INTO `sys_user` VALUES ('7', 'user4', '21232f297a57a5a743894a0e4a801fc3', 'user4', '0', '6', null, null, '', '2018-07-19 09:33:02', '2018-07-19 09:33:02');

-- ----------------------------
-- Table structure for tags
-- ----------------------------
DROP TABLE IF EXISTS `tags`;
CREATE TABLE `tags` (
  `tag_id` int(11) NOT NULL,
  `tag_text` varchar(20) DEFAULT NULL,
  `tag_type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tags
-- ----------------------------
INSERT INTO `tags` VALUES ('1', '政务', '1');
INSERT INTO `tags` VALUES ('2', '金融', '2');
INSERT INTO `tags` VALUES ('3', '基金', '2');
INSERT INTO `tags` VALUES ('4', '股票', '2');
INSERT INTO `tags` VALUES ('5', 'GDP', '2');
INSERT INTO `tags` VALUES ('7', '舆情', '3');
INSERT INTO `tags` VALUES ('8', '热点', '3');
INSERT INTO `tags` VALUES ('19', '新增', null);
INSERT INTO `tags` VALUES ('20', '我的', null);
INSERT INTO `tags` VALUES ('21', '无', null);
INSERT INTO `tags` VALUES ('22', '第一次成功', null);
INSERT INTO `tags` VALUES ('23', '你是谁', null);
INSERT INTO `tags` VALUES ('25', '参数', null);
INSERT INTO `tags` VALUES ('26', '参', null);
INSERT INTO `tags` VALUES ('27', '城市建设', null);

-- ----------------------------
-- Table structure for tag_type
-- ----------------------------
DROP TABLE IF EXISTS `tag_type`;
CREATE TABLE `tag_type` (
  `tag_type_id` int(11) NOT NULL,
  `super_type_id` int(11) DEFAULT NULL,
  `tag_type_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`tag_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tag_type
-- ----------------------------
INSERT INTO `tag_type` VALUES ('1', '0', '政治');
INSERT INTO `tag_type` VALUES ('2', '0', '经济');
INSERT INTO `tag_type` VALUES ('3', null, '社会');
INSERT INTO `tag_type` VALUES ('4', null, '国际');

-- ----------------------------
-- Table structure for terminal_type
-- ----------------------------
DROP TABLE IF EXISTS `terminal_type`;
CREATE TABLE `terminal_type` (
  `term_type_id` int(11) NOT NULL,
  `term_type_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`term_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of terminal_type
-- ----------------------------

-- ----------------------------
-- Table structure for user_access_log
-- ----------------------------
DROP TABLE IF EXISTS `user_access_log`;
CREATE TABLE `user_access_log` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '日志记录ID',
  `log_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '日志记录时间',
  `log_type_id` int(11) NOT NULL COMMENT '日志类型代码',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `user_ip` varchar(20) DEFAULT NULL COMMENT '用户IP地址',
  `user_op_type` int(11) NOT NULL COMMENT '操作类型',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单ID',
  `log_detail` varchar(200) DEFAULT NULL COMMENT '日志详情',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5406 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_access_log
-- ----------------------------
INSERT INTO `user_access_log` VALUES ('4587', '2018-07-19 09:47:39', '1', '1', '10.1.0.221', '2', '8', '用户名:%E7%AE%A1%E7%90%86%E5%91%98,查询用户管理');
INSERT INTO `user_access_log` VALUES ('4588', '2018-07-19 09:56:16', '1', '1', '10.1.1.2', '2', '8', '用户名:%E7%AE%A1%E7%90%86%E5%91%98,查询用户管理');
INSERT INTO `user_access_log` VALUES ('4589', '2018-07-19 09:56:23', '1', '1', '10.1.1.2', '2', '12', '用户名:%E7%AE%A1%E7%90%86%E5%91%98,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4590', '2018-07-19 10:23:01', '1', '1', '10.1.1.2', '2', '11', '用户名:%E7%AE%A1%E7%90%86%E5%91%98,查询应用集成管理');
INSERT INTO `user_access_log` VALUES ('4591', '2018-07-19 10:23:16', '1', '1', '10.1.1.2', '2', '50', '用户名:%E7%AE%A1%E7%90%86%E5%91%98,查询业务监测应用管理');
INSERT INTO `user_access_log` VALUES ('4592', '2018-07-19 11:08:59', '1', '1', '10.1.0.221', '2', '69', '用户名:%E7%AE%A1%E7%90%86%E5%91%98,查询资料库管理');
INSERT INTO `user_access_log` VALUES ('4593', '2018-07-19 14:45:33', '1', '1', '10.1.1.2', '2', '69', '用户名:%E7%AE%A1%E7%90%86%E5%91%98,查询资料库管理');
INSERT INTO `user_access_log` VALUES ('4594', '2018-07-19 14:46:46', '1', '1', '10.1.1.2', '2', '69', '用户名:%E7%AE%A1%E7%90%86%E5%91%98,查询资料库管理');
INSERT INTO `user_access_log` VALUES ('4595', '2018-07-19 14:47:43', '1', '1', '10.1.1.2', '2', '69', '用户名:%E7%AE%A1%E7%90%86%E5%91%98,查询资料库管理');
INSERT INTO `user_access_log` VALUES ('4596', '2018-07-19 14:48:10', '1', '1', '10.1.1.2', '2', '69', '用户名:%E7%AE%A1%E7%90%86%E5%91%98,查询资料库管理');
INSERT INTO `user_access_log` VALUES ('4597', '2018-07-19 15:09:23', '1', '1', '10.1.0.221', '2', '69', '用户名:%E7%AE%A1%E7%90%86%E5%91%98,查询资料库管理');
INSERT INTO `user_access_log` VALUES ('4598', '2018-07-19 15:13:40', '1', '1', '10.1.0.221', '2', '69', '用户名:%E7%AE%A1%E7%90%86%E5%91%98,查询资料库管理');
INSERT INTO `user_access_log` VALUES ('4599', '2018-07-19 15:16:41', '1', '1', '10.1.0.221', '2', '69', '用户名:%E7%AE%A1%E7%90%86%E5%91%98,查询资料库管理');
INSERT INTO `user_access_log` VALUES ('4600', '2018-07-19 15:33:14', '1', '1', '10.1.0.221', '2', '10', '用户名:%E7%AE%A1%E7%90%86%E5%91%98,查询菜单管理');
INSERT INTO `user_access_log` VALUES ('4601', '2018-07-19 15:33:29', '1', '1', '10.1.0.221', '2', '69', '用户名:%E7%AE%A1%E7%90%86%E5%91%98,查询资料库管理');
INSERT INTO `user_access_log` VALUES ('4602', '2018-07-19 16:09:13', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4603', '2018-07-19 16:09:49', '1', '1', '10.1.0.221', '2', '69', '用户名:admin,查询资料库管理');
INSERT INTO `user_access_log` VALUES ('4604', '2018-07-19 16:13:49', '1', '1', '10.1.1.2', '0', '1', '用户名:,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4605', '2018-07-19 16:19:18', '1', '1', '10.1.0.221', '2', '59', '用户名:admin,查询内容审核管理');
INSERT INTO `user_access_log` VALUES ('4606', '2018-07-19 16:19:22', '1', '1', '10.1.0.221', '2', '69', '用户名:admin,查询资料库管理');
INSERT INTO `user_access_log` VALUES ('4607', '2018-07-19 16:25:04', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4608', '2018-07-19 16:25:21', '1', '1', '10.1.0.221', '2', '69', '用户名:admin,查询资料库管理');
INSERT INTO `user_access_log` VALUES ('4609', '2018-07-19 17:18:10', '1', '1', '10.1.0.221', '0', '1', '用户名:,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4610', '2018-07-20 09:23:50', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4611', '2018-07-20 09:23:55', '1', '1', '10.1.0.221', '2', '46', '用户名:admin,查询信息搜索');
INSERT INTO `user_access_log` VALUES ('4612', '2018-07-20 09:24:05', '1', '1', '10.1.0.221', '2', '46', '用户名:admin,查询信息搜索');
INSERT INTO `user_access_log` VALUES ('4613', '2018-07-20 09:29:16', '1', '1', '10.1.0.221', '2', '69', '用户名:admin,查询资料库管理');
INSERT INTO `user_access_log` VALUES ('4614', '2018-07-20 13:16:51', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4615', '2018-07-20 13:19:00', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('4616', '2018-07-23 09:51:37', '1', '1', '10.1.1.255', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4617', '2018-07-23 09:51:52', '1', '1', '10.1.1.255', '2', '46', '用户名:admin,查询信息搜索');
INSERT INTO `user_access_log` VALUES ('4618', '2018-07-23 10:11:43', '1', '1', '10.1.1.255', '2', '69', '用户名:admin,查询资料库管理');
INSERT INTO `user_access_log` VALUES ('4619', '2018-07-23 10:14:39', '1', '1', '10.1.1.255', '2', '58', '用户名:admin,查询内容浏览');
INSERT INTO `user_access_log` VALUES ('4620', '2018-07-24 09:43:26', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4621', '2018-07-24 09:43:30', '1', '1', '10.1.1.2', '1', '0', '用户名:admin,退出操作');
INSERT INTO `user_access_log` VALUES ('4622', '2018-07-24 10:04:19', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4623', '2018-07-25 09:53:10', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4624', '2018-07-25 10:51:24', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4625', '2018-07-25 10:51:29', '1', '1', '10.1.0.221', '2', '13', '用户名:admin,查询部门管理');
INSERT INTO `user_access_log` VALUES ('4626', '2018-07-25 11:09:29', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4627', '2018-07-25 11:09:33', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('4628', '2018-07-25 11:09:37', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4629', '2018-07-25 11:09:53', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4630', '2018-07-25 11:10:11', '1', '1', '10.1.1.2', '1', '0', '用户名:admin,退出操作');
INSERT INTO `user_access_log` VALUES ('4631', '2018-07-25 11:12:45', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4632', '2018-07-25 11:12:49', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4633', '2018-07-25 11:15:54', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4634', '2018-07-25 11:15:59', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4635', '2018-07-25 11:24:46', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('4636', '2018-07-25 11:26:09', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('4637', '2018-07-25 13:30:44', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4638', '2018-07-25 13:31:01', '1', '1', '10.1.0.221', '2', '50', '用户名:admin,查询业务监测应用管理');
INSERT INTO `user_access_log` VALUES ('4639', '2018-07-25 13:31:07', '1', '1', '10.1.0.221', '2', '17', '用户名:admin,查询日志查询');
INSERT INTO `user_access_log` VALUES ('4640', '2018-07-25 13:31:41', '1', '1', '10.1.0.221', '2', '14', '用户名:admin,查询岗位管理');
INSERT INTO `user_access_log` VALUES ('4641', '2018-07-25 13:31:46', '1', '1', '10.1.0.221', '2', '18', '用户名:admin,查询日志报表');
INSERT INTO `user_access_log` VALUES ('4642', '2018-07-25 14:11:17', '1', '1', '10.1.1.2', '2', '10', '用户名:admin,查询菜单管理');
INSERT INTO `user_access_log` VALUES ('4643', '2018-07-25 14:14:45', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4644', '2018-07-25 14:15:42', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4645', '2018-07-25 14:15:46', '1', '1', '10.1.1.2', '2', '10', '用户名:admin,查询菜单管理');
INSERT INTO `user_access_log` VALUES ('4646', '2018-07-25 14:19:14', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('4647', '2018-07-25 14:19:48', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4648', '2018-07-25 14:19:52', '1', '1', '10.1.1.2', '1', '0', '用户名:admin,退出操作');
INSERT INTO `user_access_log` VALUES ('4649', '2018-07-25 14:19:57', '1', '2', '10.1.1.2', '0', '1', '用户名:user1,用户1登录成功');
INSERT INTO `user_access_log` VALUES ('4650', '2018-07-25 14:22:20', '1', '2', '10.1.1.2', '1', '0', '用户名:user1,退出操作');
INSERT INTO `user_access_log` VALUES ('4651', '2018-07-25 14:22:23', '1', '2', '10.1.1.2', '0', '1', '用户名:user1,用户1登录成功');
INSERT INTO `user_access_log` VALUES ('4652', '2018-07-25 14:22:57', '1', '2', '10.1.1.2', '0', '1', '用户名:user1,用户1登录成功');
INSERT INTO `user_access_log` VALUES ('4653', '2018-07-25 14:28:34', '1', '2', '10.1.1.2', '2', '8', '用户名:user1,查询用户管理');
INSERT INTO `user_access_log` VALUES ('4654', '2018-07-25 14:30:33', '1', '2', '10.1.1.2', '2', '10', '用户名:user1,查询菜单管理');
INSERT INTO `user_access_log` VALUES ('4655', '2018-07-25 14:31:35', '1', '2', '10.1.1.2', '2', '10', '用户名:user1,查询菜单管理');
INSERT INTO `user_access_log` VALUES ('4656', '2018-07-25 14:33:32', '1', '2', '10.1.1.2', '1', '0', '用户名:user1,退出操作');
INSERT INTO `user_access_log` VALUES ('4657', '2018-07-25 14:33:36', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4658', '2018-07-25 14:33:39', '1', '1', '10.1.1.2', '2', '10', '用户名:admin,查询菜单管理');
INSERT INTO `user_access_log` VALUES ('4659', '2018-07-25 14:33:53', '1', '1', '10.1.1.2', '2', '10', '用户名:admin,查询菜单管理');
INSERT INTO `user_access_log` VALUES ('4660', '2018-07-25 14:36:00', '1', '1', '10.1.1.2', '2', '10', '用户名:admin,查询菜单管理');
INSERT INTO `user_access_log` VALUES ('4661', '2018-07-25 14:40:47', '1', '1', '10.1.1.2', '1', '0', '用户名:admin,退出操作');
INSERT INTO `user_access_log` VALUES ('4662', '2018-07-25 14:40:54', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4663', '2018-07-25 14:41:01', '1', '1', '10.1.1.2', '1', '0', '用户名:admin,退出操作');
INSERT INTO `user_access_log` VALUES ('4664', '2018-07-25 14:41:09', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4665', '2018-07-25 14:41:25', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4666', '2018-07-25 14:42:04', '1', '1', '10.1.1.2', '2', '10', '用户名:admin,查询菜单管理');
INSERT INTO `user_access_log` VALUES ('4667', '2018-07-25 14:42:31', '1', '1', '10.1.1.2', '2', '10', '用户名:admin,查询菜单管理');
INSERT INTO `user_access_log` VALUES ('4668', '2018-07-25 14:44:08', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('4669', '2018-07-25 14:44:14', '1', '1', '10.1.1.2', '2', '10', '用户名:admin,查询菜单管理');
INSERT INTO `user_access_log` VALUES ('4670', '2018-07-25 14:44:19', '1', '1', '10.1.1.2', '2', '11', '用户名:admin,查询应用集成管理');
INSERT INTO `user_access_log` VALUES ('4671', '2018-07-25 14:44:22', '1', '1', '10.1.1.2', '2', '50', '用户名:admin,查询业务监测应用管理');
INSERT INTO `user_access_log` VALUES ('4672', '2018-07-25 14:44:37', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4673', '2018-07-25 16:04:49', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4674', '2018-07-25 16:04:54', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('4675', '2018-07-25 16:05:16', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4676', '2018-07-25 16:05:23', '1', '1', '10.1.1.2', '2', '11', '用户名:admin,查询应用集成管理');
INSERT INTO `user_access_log` VALUES ('4677', '2018-07-25 16:05:27', '1', '1', '10.1.1.2', '2', '10', '用户名:admin,查询菜单管理');
INSERT INTO `user_access_log` VALUES ('4678', '2018-07-25 16:24:26', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4679', '2018-07-25 16:24:34', '1', '1', '10.1.0.221', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4680', '2018-07-25 16:32:40', '1', '1', '10.1.0.221', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('4681', '2018-07-25 17:29:35', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4682', '2018-07-25 17:29:42', '1', '1', '10.1.0.221', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4683', '2018-07-26 09:31:17', '1', '2', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4684', '2018-07-26 09:43:40', '1', '1', '192.168.1.142', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4685', '2018-07-26 09:43:57', '1', '1', '192.168.1.142', '2', '58', '用户名:admin,查询内容浏览');
INSERT INTO `user_access_log` VALUES ('4686', '2018-07-26 09:49:41', '1', '1', '192.168.1.142', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4687', '2018-07-26 09:53:04', '1', '1', '192.168.1.142', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4688', '2018-07-26 09:53:04', '1', '1', '192.168.1.142', '2', '46', '用户名:admin,查询信息搜索');
INSERT INTO `user_access_log` VALUES ('4689', '2018-07-26 09:53:38', '1', '1', '192.168.1.142', '2', '69', '用户名:admin,查询资料库管理');
INSERT INTO `user_access_log` VALUES ('4690', '2018-07-26 09:55:34', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('4691', '2018-07-26 09:55:55', '1', '1', '10.1.1.2', '2', '13', '用户名:admin,查询部门管理');
INSERT INTO `user_access_log` VALUES ('4692', '2018-07-26 10:04:40', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4693', '2018-07-26 10:04:49', '1', '1', '10.1.0.221', '2', '152', '用户名:admin,查询监控运维');
INSERT INTO `user_access_log` VALUES ('4694', '2018-07-26 10:04:55', '1', '1', '10.1.0.221', '2', '156', '用户名:admin,查询资源目录系统');
INSERT INTO `user_access_log` VALUES ('4695', '2018-07-26 10:05:37', '1', '1', '10.1.0.221', '2', '146', '用户名:admin,查询数据接入系统');
INSERT INTO `user_access_log` VALUES ('4696', '2018-07-26 10:12:44', '1', '1', '192.168.1.142', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4697', '2018-07-26 10:12:54', '1', '1', '192.168.1.142', '2', '69', '用户名:admin,查询资料库管理');
INSERT INTO `user_access_log` VALUES ('4698', '2018-07-26 10:11:52', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4699', '2018-07-26 10:11:59', '1', '1', '10.1.1.2', '2', '58', '用户名:admin,查询内容浏览');
INSERT INTO `user_access_log` VALUES ('4700', '2018-07-26 10:13:39', '1', '1', '10.1.1.2', '2', '46', '用户名:admin,查询信息搜索');
INSERT INTO `user_access_log` VALUES ('4701', '2018-07-26 10:13:52', '1', '1', '10.1.1.2', '2', '69', '用户名:admin,查询资料库管理');
INSERT INTO `user_access_log` VALUES ('4702', '2018-07-26 10:14:43', '1', '1', '10.1.1.2', '2', '46', '用户名:admin,查询信息搜索');
INSERT INTO `user_access_log` VALUES ('4703', '2018-07-26 10:15:01', '1', '1', '10.1.1.2', '2', '69', '用户名:admin,查询资料库管理');
INSERT INTO `user_access_log` VALUES ('4704', '2018-07-26 10:34:53', '1', '1', '10.1.0.221', '2', '147', '用户名:admin,查询网络爬虫系统');
INSERT INTO `user_access_log` VALUES ('4705', '2018-07-26 10:34:57', '1', '1', '10.1.0.221', '2', '150', '用户名:admin,查询大数据使用平台');
INSERT INTO `user_access_log` VALUES ('4706', '2018-07-26 10:35:43', '1', '1', '10.1.0.221', '2', '146', '用户名:admin,查询数据接入系统');
INSERT INTO `user_access_log` VALUES ('4707', '2018-07-26 10:35:49', '1', '1', '10.1.0.221', '2', '147', '用户名:admin,查询网络爬虫系统');
INSERT INTO `user_access_log` VALUES ('4708', '2018-07-26 11:35:29', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4709', '2018-07-26 11:35:37', '1', '1', '10.1.1.2', '2', '46', '用户名:admin,查询信息搜索');
INSERT INTO `user_access_log` VALUES ('4710', '2018-07-26 12:02:02', '1', '1', '192.168.1.142', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4711', '2018-07-26 13:10:45', '1', '1', '192.168.1.142', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4712', '2018-07-26 13:35:03', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4713', '2018-07-26 13:37:32', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4714', '2018-07-26 13:37:39', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('4715', '2018-07-26 13:37:41', '1', '1', '10.1.1.2', '2', '10', '用户名:admin,查询菜单管理');
INSERT INTO `user_access_log` VALUES ('4716', '2018-07-26 13:43:01', '1', '1', '10.1.1.2', '2', '11', '用户名:admin,查询应用集成管理');
INSERT INTO `user_access_log` VALUES ('4717', '2018-07-26 13:48:10', '1', '1', '10.1.1.2', '2', '13', '用户名:admin,查询部门管理');
INSERT INTO `user_access_log` VALUES ('4718', '2018-07-26 13:48:14', '1', '1', '10.1.1.2', '2', '14', '用户名:admin,查询岗位管理');
INSERT INTO `user_access_log` VALUES ('4719', '2018-07-26 14:06:19', '1', '1', '10.1.1.2', '2', '58', '用户名:admin,查询内容浏览');
INSERT INTO `user_access_log` VALUES ('4720', '2018-07-26 14:12:00', '1', '1', '10.1.1.2', '2', '59', '用户名:admin,查询内容审核管理');
INSERT INTO `user_access_log` VALUES ('4721', '2018-07-26 14:14:45', '1', '1', '10.1.1.2', '2', '60', '用户名:admin,查询内容标签管理');
INSERT INTO `user_access_log` VALUES ('4722', '2018-07-26 14:14:51', '1', '1', '10.1.1.2', '2', '59', '用户名:admin,查询内容审核管理');
INSERT INTO `user_access_log` VALUES ('4723', '2018-07-26 14:24:09', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4724', '2018-07-26 14:24:29', '1', '1', '10.1.0.221', '2', '59', '用户名:admin,查询内容审核管理');
INSERT INTO `user_access_log` VALUES ('4725', '2018-07-26 14:25:42', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4726', '2018-07-26 14:25:45', '1', '1', '10.1.1.2', '2', '58', '用户名:admin,查询内容浏览');
INSERT INTO `user_access_log` VALUES ('4727', '2018-07-26 14:27:18', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4728', '2018-07-26 14:28:08', '1', '1', '10.1.0.221', '2', '60', '用户名:admin,查询内容标签管理');
INSERT INTO `user_access_log` VALUES ('4729', '2018-07-26 14:28:14', '1', '1', '10.1.0.221', '2', '59', '用户名:admin,查询内容审核管理');
INSERT INTO `user_access_log` VALUES ('4730', '2018-07-26 14:28:36', '1', '1', '10.1.0.221', '2', '13', '用户名:admin,查询部门管理');
INSERT INTO `user_access_log` VALUES ('4731', '2018-07-26 14:28:50', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4732', '2018-07-26 14:28:55', '1', '1', '10.1.1.2', '2', '58', '用户名:admin,查询内容浏览');
INSERT INTO `user_access_log` VALUES ('4733', '2018-07-26 14:35:38', '1', '1', '10.1.1.2', '2', '58', '用户名:admin,查询内容浏览');
INSERT INTO `user_access_log` VALUES ('4734', '2018-07-26 14:41:56', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4735', '2018-07-26 14:42:03', '1', '1', '10.1.0.221', '2', '13', '用户名:admin,查询部门管理');
INSERT INTO `user_access_log` VALUES ('4736', '2018-07-26 14:42:24', '1', '1', '10.1.1.2', '2', '13', '用户名:admin,查询部门管理');
INSERT INTO `user_access_log` VALUES ('4737', '2018-07-26 14:43:03', '1', '1', '10.1.1.2', '2', '59', '用户名:admin,查询内容审核管理');
INSERT INTO `user_access_log` VALUES ('4738', '2018-07-26 14:54:20', '1', '1', '10.1.0.221', '2', '14', '用户名:admin,查询岗位管理');
INSERT INTO `user_access_log` VALUES ('4739', '2018-07-26 14:54:48', '1', '1', '10.1.0.221', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('4740', '2018-07-26 15:12:10', '1', '1', '10.1.0.221', '2', '13', '用户名:admin,查询部门管理');
INSERT INTO `user_access_log` VALUES ('4741', '2018-07-26 15:12:19', '1', '1', '10.1.0.221', '2', '13', '用户名:admin,查询部门管理');
INSERT INTO `user_access_log` VALUES ('4742', '2018-07-26 15:52:44', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4743', '2018-07-26 15:53:12', '1', '1', '10.1.0.221', '2', '59', '用户名:admin,查询内容审核管理');
INSERT INTO `user_access_log` VALUES ('4744', '2018-07-26 15:53:38', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4745', '2018-07-26 15:53:43', '1', '1', '10.1.1.2', '2', '13', '用户名:admin,查询部门管理');
INSERT INTO `user_access_log` VALUES ('4746', '2018-07-26 15:53:45', '1', '1', '10.1.1.2', '2', '14', '用户名:admin,查询岗位管理');
INSERT INTO `user_access_log` VALUES ('4747', '2018-07-26 15:53:56', '1', '1', '10.1.1.2', '2', '50', '用户名:admin,查询业务监测应用管理');
INSERT INTO `user_access_log` VALUES ('4748', '2018-07-26 15:53:59', '1', '1', '10.1.1.2', '2', '11', '用户名:admin,查询应用集成管理');
INSERT INTO `user_access_log` VALUES ('4749', '2018-07-26 15:54:05', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4750', '2018-07-26 15:54:21', '1', '1', '10.1.1.2', '2', '58', '用户名:admin,查询内容浏览');
INSERT INTO `user_access_log` VALUES ('4751', '2018-07-26 15:54:24', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4752', '2018-07-26 15:54:24', '1', '1', '10.1.1.2', '2', '60', '用户名:admin,查询内容标签管理');
INSERT INTO `user_access_log` VALUES ('4753', '2018-07-26 15:54:25', '1', '1', '10.1.1.2', '2', '59', '用户名:admin,查询内容审核管理');
INSERT INTO `user_access_log` VALUES ('4754', '2018-07-26 15:54:42', '1', '1', '10.1.0.221', '2', '59', '用户名:admin,查询内容审核管理');
INSERT INTO `user_access_log` VALUES ('4755', '2018-07-26 15:55:01', '1', '1', '10.1.0.221', '2', '58', '用户名:admin,查询内容浏览');
INSERT INTO `user_access_log` VALUES ('4756', '2018-07-26 15:55:06', '1', '1', '10.1.0.221', '2', '60', '用户名:admin,查询内容标签管理');
INSERT INTO `user_access_log` VALUES ('4757', '2018-07-26 15:55:16', '1', '1', '10.1.0.221', '2', '11', '用户名:admin,查询应用集成管理');
INSERT INTO `user_access_log` VALUES ('4758', '2018-07-26 15:55:21', '1', '1', '10.1.0.221', '2', '10', '用户名:admin,查询菜单管理');
INSERT INTO `user_access_log` VALUES ('4759', '2018-07-26 15:55:27', '1', '1', '10.1.0.221', '2', '17', '用户名:admin,查询日志查询');
INSERT INTO `user_access_log` VALUES ('4760', '2018-07-26 15:55:38', '1', '1', '10.1.0.221', '2', '46', '用户名:admin,查询信息搜索');
INSERT INTO `user_access_log` VALUES ('4761', '2018-07-26 16:04:03', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4762', '2018-07-26 16:04:08', '1', '1', '10.1.1.2', '2', '58', '用户名:admin,查询内容浏览');
INSERT INTO `user_access_log` VALUES ('4763', '2018-07-26 16:07:48', '1', '1', '10.1.0.221', '2', '59', '用户名:admin,查询内容审核管理');
INSERT INTO `user_access_log` VALUES ('4764', '2018-07-26 16:47:21', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4765', '2018-07-26 16:47:30', '1', '1', '10.1.1.2', '2', '58', '用户名:admin,查询内容浏览');
INSERT INTO `user_access_log` VALUES ('4766', '2018-07-26 16:47:35', '1', '1', '10.1.1.2', '2', '59', '用户名:admin,查询内容审核管理');
INSERT INTO `user_access_log` VALUES ('4767', '2018-07-27 08:08:22', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4768', '2018-07-27 08:08:45', '1', '1', '10.1.0.221', '2', '58', '用户名:admin,查询内容浏览');
INSERT INTO `user_access_log` VALUES ('4769', '2018-07-27 08:09:03', '1', '1', '10.1.0.221', '2', '46', '用户名:admin,查询信息搜索');
INSERT INTO `user_access_log` VALUES ('4770', '2018-07-27 08:11:55', '1', '1', '10.1.0.221', '1', '0', '用户名:admin,退出操作');
INSERT INTO `user_access_log` VALUES ('4771', '2018-07-27 09:46:18', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4772', '2018-07-27 09:46:23', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('4773', '2018-07-27 09:46:28', '1', '1', '10.1.1.2', '2', '14', '用户名:admin,查询岗位管理');
INSERT INTO `user_access_log` VALUES ('4774', '2018-07-27 09:46:33', '1', '1', '10.1.1.2', '2', '13', '用户名:admin,查询部门管理');
INSERT INTO `user_access_log` VALUES ('4775', '2018-07-27 09:50:57', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4776', '2018-07-27 09:51:12', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('4777', '2018-07-27 09:52:02', '1', '1', '10.1.1.2', '2', '10', '用户名:admin,查询菜单管理');
INSERT INTO `user_access_log` VALUES ('4778', '2018-07-27 09:58:13', '1', '1', '10.1.1.2', '2', '50', '用户名:admin,查询业务监测应用管理');
INSERT INTO `user_access_log` VALUES ('4779', '2018-07-27 10:01:06', '1', '1', '10.1.1.2', '2', '13', '用户名:admin,查询部门管理');
INSERT INTO `user_access_log` VALUES ('4780', '2018-07-27 10:01:11', '1', '1', '10.1.1.2', '2', '14', '用户名:admin,查询岗位管理');
INSERT INTO `user_access_log` VALUES ('4781', '2018-07-27 10:09:37', '1', '1', '10.1.1.2', '2', '58', '用户名:admin,查询内容浏览');
INSERT INTO `user_access_log` VALUES ('4782', '2018-07-27 10:09:38', '1', '1', '10.1.1.2', '2', '59', '用户名:admin,查询内容审核管理');
INSERT INTO `user_access_log` VALUES ('4783', '2018-07-27 10:09:41', '1', '1', '10.1.1.2', '2', '60', '用户名:admin,查询内容标签管理');
INSERT INTO `user_access_log` VALUES ('4784', '2018-07-27 10:15:23', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4785', '2018-07-27 10:32:20', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('4786', '2018-07-27 10:32:28', '1', '1', '10.1.1.2', '2', '10', '用户名:admin,查询菜单管理');
INSERT INTO `user_access_log` VALUES ('4787', '2018-07-27 10:32:45', '1', '1', '10.1.1.2', '2', '11', '用户名:admin,查询应用集成管理');
INSERT INTO `user_access_log` VALUES ('4788', '2018-07-27 10:36:33', '1', '1', '10.1.1.2', '2', '59', '用户名:admin,查询内容审核管理');
INSERT INTO `user_access_log` VALUES ('4789', '2018-07-27 10:53:50', '1', '1', '10.1.1.2', '2', '70', '用户名:admin,查询资料类别管理');
INSERT INTO `user_access_log` VALUES ('4790', '2018-07-27 10:57:02', '1', '1', '10.1.1.2', '1', '0', '用户名:admin,退出操作');
INSERT INTO `user_access_log` VALUES ('4791', '2018-07-27 10:57:16', '1', '2', '10.1.1.2', '0', '1', '用户名:user1,用户1登录成功');
INSERT INTO `user_access_log` VALUES ('4792', '2018-07-27 10:57:21', '1', '2', '10.1.1.2', '1', '0', '用户名:user1,退出操作');
INSERT INTO `user_access_log` VALUES ('4793', '2018-07-27 10:57:28', '1', '6', '10.1.1.2', '0', '1', '用户名:user3,user3登录成功');
INSERT INTO `user_access_log` VALUES ('4794', '2018-07-27 10:57:31', '1', '6', '10.1.1.2', '1', '0', '用户名:user3,退出操作');
INSERT INTO `user_access_log` VALUES ('4795', '2018-07-27 10:57:40', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4796', '2018-07-27 10:59:05', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('4797', '2018-07-27 10:59:06', '1', '1', '10.1.1.2', '2', '10', '用户名:admin,查询菜单管理');
INSERT INTO `user_access_log` VALUES ('4798', '2018-07-27 10:59:08', '1', '1', '10.1.1.2', '2', '11', '用户名:admin,查询应用集成管理');
INSERT INTO `user_access_log` VALUES ('4799', '2018-07-27 10:59:10', '1', '1', '10.1.1.2', '2', '50', '用户名:admin,查询业务监测应用管理');
INSERT INTO `user_access_log` VALUES ('4800', '2018-07-27 11:28:12', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4801', '2018-07-27 13:47:27', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4802', '2018-07-27 13:47:37', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('4803', '2018-07-27 13:52:20', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4804', '2018-07-27 13:53:02', '1', '1', '10.1.1.2', '2', '10', '用户名:admin,查询菜单管理');
INSERT INTO `user_access_log` VALUES ('4805', '2018-07-27 13:53:07', '1', '1', '10.1.1.2', '2', '13', '用户名:admin,查询部门管理');
INSERT INTO `user_access_log` VALUES ('4806', '2018-07-27 13:53:26', '1', '1', '10.1.1.2', '2', '14', '用户名:admin,查询岗位管理');
INSERT INTO `user_access_log` VALUES ('4807', '2018-07-27 13:53:40', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4808', '2018-07-27 13:53:41', '1', '1', '10.1.1.2', '2', '11', '用户名:admin,查询应用集成管理');
INSERT INTO `user_access_log` VALUES ('4809', '2018-07-27 13:55:33', '1', '1', '10.1.1.2', '2', '50', '用户名:admin,查询业务监测应用管理');
INSERT INTO `user_access_log` VALUES ('4810', '2018-07-27 13:59:47', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4811', '2018-07-27 13:59:59', '1', '1', '10.1.0.221', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4812', '2018-07-27 14:00:08', '1', '1', '10.1.0.221', '2', '11', '用户名:admin,查询应用集成管理');
INSERT INTO `user_access_log` VALUES ('4813', '2018-07-27 14:00:09', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('4814', '2018-07-27 14:00:11', '1', '1', '10.1.0.221', '2', '10', '用户名:admin,查询菜单管理');
INSERT INTO `user_access_log` VALUES ('4815', '2018-07-27 14:00:31', '1', '1', '10.1.1.2', '2', '57', '用户名:admin,查询配置版本管理');
INSERT INTO `user_access_log` VALUES ('4816', '2018-07-27 14:00:36', '1', '1', '10.1.0.221', '2', '13', '用户名:admin,查询部门管理');
INSERT INTO `user_access_log` VALUES ('4817', '2018-07-27 14:00:42', '1', '1', '10.1.0.221', '2', '14', '用户名:admin,查询岗位管理');
INSERT INTO `user_access_log` VALUES ('4818', '2018-07-27 14:00:47', '1', '1', '10.1.0.221', '2', '50', '用户名:admin,查询业务监测应用管理');
INSERT INTO `user_access_log` VALUES ('4819', '2018-07-27 14:06:11', '1', '1', '10.1.0.221', '1', '0', '用户名:admin,退出操作');
INSERT INTO `user_access_log` VALUES ('4820', '2018-07-27 14:06:13', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4821', '2018-07-27 14:14:48', '1', '1', '10.1.0.221', '2', '10', '用户名:admin,查询菜单管理');
INSERT INTO `user_access_log` VALUES ('4822', '2018-07-27 14:18:52', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4823', '2018-07-27 14:19:08', '1', '1', '10.1.0.221', '2', '152', '用户名:admin,查询监控运维');
INSERT INTO `user_access_log` VALUES ('4824', '2018-07-27 14:19:19', '1', '1', '10.1.0.221', '2', '13', '用户名:admin,查询部门管理');
INSERT INTO `user_access_log` VALUES ('4825', '2018-07-27 14:32:48', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4826', '2018-07-27 14:32:52', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4827', '2018-07-27 14:33:07', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('4828', '2018-07-27 14:33:13', '1', '1', '10.1.1.2', '1', '0', '用户名:admin,退出操作');
INSERT INTO `user_access_log` VALUES ('4829', '2018-07-27 14:33:16', '1', '2', '10.1.1.2', '0', '1', '用户名:user1,用户1登录成功');
INSERT INTO `user_access_log` VALUES ('4830', '2018-07-27 14:51:05', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4831', '2018-07-27 15:07:46', '1', '2', '10.1.1.2', '0', '1', '用户名:user1,用户1登录成功');
INSERT INTO `user_access_log` VALUES ('4832', '2018-07-27 15:09:17', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4833', '2018-07-27 15:10:59', '1', '1', '10.1.1.2', '2', '50', '用户名:admin,查询业务监测应用管理');
INSERT INTO `user_access_log` VALUES ('4834', '2018-07-27 15:12:37', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4835', '2018-07-27 15:12:39', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('4836', '2018-07-27 15:16:35', '1', '1', '10.1.1.2', '2', '10', '用户名:admin,查询菜单管理');
INSERT INTO `user_access_log` VALUES ('4837', '2018-07-27 15:28:01', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('4838', '2018-07-27 15:28:07', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4839', '2018-07-27 15:28:52', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4840', '2018-07-27 15:29:13', '1', '1', '10.1.1.2', '0', '1', '用户名:user2,用户2登录成功');
INSERT INTO `user_access_log` VALUES ('4841', '2018-07-27 15:29:48', '1', '3', '10.1.1.2', '1', '0', '用户名:user2,退出操作');
INSERT INTO `user_access_log` VALUES ('4842', '2018-07-27 15:29:54', '1', '2', '10.1.1.2', '0', '1', '用户名:user1,用户1登录成功');
INSERT INTO `user_access_log` VALUES ('4843', '2018-07-27 16:56:42', '1', '2', '10.1.1.2', '0', '1', '用户名:user1,用户1登录成功');
INSERT INTO `user_access_log` VALUES ('4844', '2018-07-27 16:56:46', '1', '2', '10.1.1.2', '1', '0', '用户名:user1,退出操作');
INSERT INTO `user_access_log` VALUES ('4845', '2018-07-27 16:56:49', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4846', '2018-07-27 16:56:55', '1', '1', '10.1.1.2', '2', '60', '用户名:admin,查询内容标签管理');
INSERT INTO `user_access_log` VALUES ('4847', '2018-07-27 16:58:15', '1', '1', '10.1.1.2', '2', '69', '用户名:admin,查询资料库管理');
INSERT INTO `user_access_log` VALUES ('4848', '2018-07-27 16:59:43', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4849', '2018-07-30 08:10:26', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4850', '2018-07-30 08:10:30', '1', '1', '10.1.0.221', '2', '160', '用户名:admin,查询大数据管理平台');
INSERT INTO `user_access_log` VALUES ('4851', '2018-07-30 08:10:38', '1', '1', '10.1.0.221', '2', '149', '用户名:admin,查询大数据安全管理');
INSERT INTO `user_access_log` VALUES ('4852', '2018-07-30 08:10:46', '1', '1', '10.1.0.221', '2', '150', '用户名:admin,查询大数据使用平台');
INSERT INTO `user_access_log` VALUES ('4853', '2018-07-30 10:11:12', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4854', '2018-07-30 10:11:44', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4855', '2018-07-30 15:37:23', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4856', '2018-07-30 15:37:29', '1', '1', '10.1.0.221', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('4857', '2018-07-30 15:42:55', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4858', '2018-07-30 15:43:05', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('4859', '2018-07-30 15:43:15', '1', '1', '10.1.1.2', '1', '0', '用户名:admin,退出操作');
INSERT INTO `user_access_log` VALUES ('4860', '2018-07-30 15:44:31', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4861', '2018-07-30 15:44:33', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('4862', '2018-07-30 15:44:40', '1', '1', '10.1.1.2', '1', '0', '用户名:admin,退出操作');
INSERT INTO `user_access_log` VALUES ('4863', '2018-07-30 15:44:43', '1', '2', '10.1.1.2', '0', '1', '用户名:user1,用户1登录成功');
INSERT INTO `user_access_log` VALUES ('4864', '2018-07-30 15:54:32', '1', '1', '10.1.0.221', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4865', '2018-07-30 16:30:14', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4866', '2018-07-30 16:30:17', '1', '1', '10.1.0.221', '2', '11', '用户名:admin,查询应用集成管理');
INSERT INTO `user_access_log` VALUES ('4867', '2018-07-30 16:30:40', '1', '1', '10.1.0.221', '2', '14', '用户名:admin,查询岗位管理');
INSERT INTO `user_access_log` VALUES ('4868', '2018-07-30 16:30:57', '1', '1', '10.1.0.221', '2', '13', '用户名:admin,查询部门管理');
INSERT INTO `user_access_log` VALUES ('4869', '2018-07-30 16:31:12', '1', '1', '10.1.0.221', '2', '10', '用户名:admin,查询菜单管理');
INSERT INTO `user_access_log` VALUES ('4870', '2018-07-30 16:40:13', '1', '1', '10.1.0.221', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4871', '2018-07-30 16:42:32', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4872', '2018-07-30 16:42:55', '1', '1', '10.1.0.221', '1', '0', '用户名:admin,退出操作');
INSERT INTO `user_access_log` VALUES ('4873', '2018-07-30 16:47:36', '1', '1', '192.168.1.142', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4874', '2018-07-30 16:54:34', '1', '1', '192.168.1.142', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4875', '2018-07-30 16:55:37', '1', '1', '192.168.1.142', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4876', '2018-07-30 16:58:32', '1', '1', '192.168.1.142', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4877', '2018-07-30 16:59:09', '1', '1', '192.168.1.142', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4878', '2018-07-30 17:01:51', '1', '1', '192.168.1.142', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4879', '2018-07-30 17:03:34', '1', '1', '192.168.1.142', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4880', '2018-07-30 17:13:20', '1', '1', '192.168.1.142', '1', '0', '用户名:admin,退出操作');
INSERT INTO `user_access_log` VALUES ('4881', '2018-07-30 17:19:09', '1', '6', '192.168.1.142', '0', '1', '用户名:user3,user3登录成功');
INSERT INTO `user_access_log` VALUES ('4882', '2018-07-30 17:19:41', '1', '1', '192.168.1.142', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4883', '2018-07-30 17:19:48', '1', '1', '192.168.1.142', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4884', '2018-07-30 17:24:46', '1', '1', '192.168.1.142', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4885', '2018-07-30 17:36:04', '1', '2', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4886', '2018-07-30 17:36:10', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('4887', '2018-07-30 17:36:18', '1', '1', '10.1.1.2', '1', '0', '用户名:admin,退出操作');
INSERT INTO `user_access_log` VALUES ('4888', '2018-07-30 17:39:39', '1', '1', '192.168.1.142', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4889', '2018-07-30 17:51:50', '1', '1', '192.168.1.142', '1', '0', '用户名:admin,退出操作');
INSERT INTO `user_access_log` VALUES ('4890', '2018-07-30 17:58:39', '1', '1', '192.168.1.142', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4891', '2018-07-30 17:58:45', '1', '1', '192.168.1.142', '1', '0', '用户名:admin,退出操作');
INSERT INTO `user_access_log` VALUES ('4892', '2018-07-30 17:59:02', '1', '1', '192.168.1.142', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4893', '2018-07-30 17:59:46', '1', '1', '192.168.1.142', '1', '0', '用户名:admin,退出操作');
INSERT INTO `user_access_log` VALUES ('4894', '2018-07-30 17:59:58', '1', '1', '192.168.1.142', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4895', '2018-07-30 18:00:04', '1', '1', '192.168.1.142', '1', '0', '用户名:admin,退出操作');
INSERT INTO `user_access_log` VALUES ('4896', '2018-07-31 08:35:01', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4897', '2018-07-31 08:35:11', '1', '1', '10.1.0.221', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4898', '2018-07-31 08:36:32', '1', '1', '10.1.0.221', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('4899', '2018-07-31 08:36:38', '1', '1', '10.1.0.221', '1', '0', '用户名:admin,退出操作');
INSERT INTO `user_access_log` VALUES ('4900', '2018-07-31 08:36:53', '1', '2', '10.1.0.221', '1', '0', '用户名:user1,退出操作');
INSERT INTO `user_access_log` VALUES ('4901', '2018-07-31 08:37:02', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4902', '2018-07-31 08:37:08', '1', '1', '10.1.0.221', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('4903', '2018-07-31 08:37:22', '1', '1', '10.1.0.221', '1', '0', '用户名:admin,退出操作');
INSERT INTO `user_access_log` VALUES ('4904', '2018-07-31 08:37:29', '1', '3', '10.1.0.221', '0', '1', '用户名:user2,用户2登录成功');
INSERT INTO `user_access_log` VALUES ('4905', '2018-07-31 09:02:39', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4906', '2018-07-31 09:02:46', '1', '1', '10.1.0.221', '2', '104', '用户名:admin,查询指标管理平台');
INSERT INTO `user_access_log` VALUES ('4907', '2018-07-31 09:08:17', '1', '1', '10.1.0.221', '2', '50', '用户名:admin,查询业务监测应用管理');
INSERT INTO `user_access_log` VALUES ('4908', '2018-07-31 09:14:05', '1', '1', '10.1.0.221', '2', '50', '用户名:admin,查询业务监测应用管理');
INSERT INTO `user_access_log` VALUES ('4909', '2018-07-31 09:14:59', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4910', '2018-07-31 09:15:08', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4911', '2018-07-31 09:15:24', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4912', '2018-07-31 09:15:29', '1', '1', '10.1.0.221', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('4913', '2018-07-31 09:15:32', '1', '1', '10.1.0.221', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4914', '2018-07-31 09:17:55', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('4915', '2018-07-31 09:18:51', '1', '1', '10.1.0.221', '2', '58', '用户名:admin,查询内容浏览');
INSERT INTO `user_access_log` VALUES ('4916', '2018-07-31 09:19:09', '1', '1', '10.1.0.221', '2', '59', '用户名:admin,查询内容审核管理');
INSERT INTO `user_access_log` VALUES ('4917', '2018-07-31 09:27:27', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4918', '2018-07-31 09:27:35', '1', '1', '10.1.0.221', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4919', '2018-07-31 09:29:42', '1', '1', '10.1.0.221', '1', '0', '用户名:admin,退出操作');
INSERT INTO `user_access_log` VALUES ('4920', '2018-07-31 09:30:18', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4921', '2018-07-31 09:30:44', '1', '1', '10.1.0.221', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4922', '2018-07-31 09:32:10', '1', '1', '10.1.1.2', '1', '0', '用户名:admin,退出操作');
INSERT INTO `user_access_log` VALUES ('4923', '2018-07-31 09:32:13', '1', '2', '10.1.1.2', '0', '1', '用户名:user1,用户1登录成功');
INSERT INTO `user_access_log` VALUES ('4924', '2018-07-31 09:32:24', '1', '2', '10.1.1.2', '1', '0', '用户名:user1,退出操作');
INSERT INTO `user_access_log` VALUES ('4925', '2018-07-31 09:32:27', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4926', '2018-07-31 09:32:40', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('4927', '2018-07-31 09:35:31', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4928', '2018-07-31 09:38:47', '1', '7', '10.1.1.2', '0', '1', '用户名:user4,user4登录成功');
INSERT INTO `user_access_log` VALUES ('4929', '2018-07-31 09:39:26', '1', '7', '10.1.1.2', '2', '160', '用户名:user4,查询大数据管理平台');
INSERT INTO `user_access_log` VALUES ('4930', '2018-07-31 09:42:21', '1', '7', '10.1.1.2', '1', '0', '用户名:user4,退出操作');
INSERT INTO `user_access_log` VALUES ('4931', '2018-07-31 09:42:31', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4932', '2018-07-31 09:43:18', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4933', '2018-07-31 09:43:26', '1', '1', '10.1.0.221', '2', '58', '用户名:admin,查询内容浏览');
INSERT INTO `user_access_log` VALUES ('4934', '2018-07-31 09:47:48', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4935', '2018-07-31 09:48:11', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('4936', '2018-07-31 09:48:24', '1', '1', '10.1.1.2', '1', '0', '用户名:admin,退出操作');
INSERT INTO `user_access_log` VALUES ('4937', '2018-07-31 09:49:17', '1', '1', '10.1.0.221', '2', '60', '用户名:admin,查询内容标签管理');
INSERT INTO `user_access_log` VALUES ('4938', '2018-07-31 09:49:21', '1', '1', '10.1.0.221', '2', '69', '用户名:admin,查询资料库管理');
INSERT INTO `user_access_log` VALUES ('4939', '2018-07-31 09:49:22', '1', '2', '10.1.1.2', '0', '1', '用户名:user1,用户1登录成功');
INSERT INTO `user_access_log` VALUES ('4940', '2018-07-31 09:49:23', '1', '1', '10.1.0.221', '2', '70', '用户名:admin,查询资料类别管理');
INSERT INTO `user_access_log` VALUES ('4941', '2018-07-31 09:49:37', '1', '2', '10.1.1.2', '1', '0', '用户名:user1,退出操作');
INSERT INTO `user_access_log` VALUES ('4942', '2018-07-31 09:49:44', '1', '3', '10.1.1.2', '0', '1', '用户名:user2,用户2登录成功');
INSERT INTO `user_access_log` VALUES ('4943', '2018-07-31 09:49:48', '1', '3', '10.1.1.2', '1', '0', '用户名:user2,退出操作');
INSERT INTO `user_access_log` VALUES ('4944', '2018-07-31 09:54:17', '1', '7', '10.1.1.2', '0', '1', '用户名:user4,user4登录成功');
INSERT INTO `user_access_log` VALUES ('4945', '2018-07-31 09:54:40', '1', '7', '10.1.1.2', '1', '0', '用户名:user4,退出操作');
INSERT INTO `user_access_log` VALUES ('4946', '2018-07-31 09:54:46', '1', '2', '10.1.1.2', '0', '1', '用户名:user1,用户1登录成功');
INSERT INTO `user_access_log` VALUES ('4947', '2018-07-31 09:55:00', '1', '2', '10.1.1.2', '1', '0', '用户名:user1,退出操作');
INSERT INTO `user_access_log` VALUES ('4948', '2018-07-31 09:55:06', '1', '2', '10.1.1.2', '0', '1', '用户名:user1,用户1登录成功');
INSERT INTO `user_access_log` VALUES ('4949', '2018-07-31 10:00:36', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4950', '2018-07-31 10:00:46', '1', '1', '10.1.0.221', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4951', '2018-07-31 10:03:06', '1', '2', '10.1.1.2', '1', '0', '用户名:user1,退出操作');
INSERT INTO `user_access_log` VALUES ('4952', '2018-07-31 10:03:15', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4953', '2018-07-31 10:08:20', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4954', '2018-07-31 10:10:16', '1', '1', '10.1.0.221', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4955', '2018-07-31 10:10:49', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('4956', '2018-07-31 10:11:01', '1', '1', '10.1.1.2', '1', '0', '用户名:admin,退出操作');
INSERT INTO `user_access_log` VALUES ('4957', '2018-07-31 10:11:17', '1', '7', '10.1.1.2', '1', '0', '用户名:user4,退出操作');
INSERT INTO `user_access_log` VALUES ('4958', '2018-07-31 10:11:40', '1', '7', '10.1.1.2', '1', '0', '用户名:user4,退出操作');
INSERT INTO `user_access_log` VALUES ('4959', '2018-07-31 10:18:28', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4960', '2018-07-31 10:19:21', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('4961', '2018-07-31 10:20:28', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4962', '2018-07-31 10:21:27', '1', '1', '10.1.1.2', '1', '0', '用户名:admin,退出操作');
INSERT INTO `user_access_log` VALUES ('4963', '2018-07-31 10:21:36', '1', '6', '10.1.1.2', '0', '1', '用户名:user3,user3登录成功');
INSERT INTO `user_access_log` VALUES ('4964', '2018-07-31 10:22:31', '1', '6', '10.1.1.2', '1', '0', '用户名:user3,退出操作');
INSERT INTO `user_access_log` VALUES ('4965', '2018-07-31 10:26:12', '1', '1', '10.1.1.2', '1', '0', '用户名:admin,退出操作');
INSERT INTO `user_access_log` VALUES ('4966', '2018-07-31 10:26:17', '1', '7', '10.1.1.2', '0', '1', '用户名:user4,user4登录成功');
INSERT INTO `user_access_log` VALUES ('4967', '2018-07-31 10:26:34', '1', '7', '10.1.1.2', '1', '0', '用户名:user4,退出操作');
INSERT INTO `user_access_log` VALUES ('4968', '2018-07-31 10:26:42', '1', '7', '10.1.1.2', '0', '1', '用户名:user4,user4登录成功');
INSERT INTO `user_access_log` VALUES ('4969', '2018-07-31 10:26:53', '1', '7', '10.1.1.2', '1', '0', '用户名:user4,退出操作');
INSERT INTO `user_access_log` VALUES ('4970', '2018-07-31 10:30:57', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4971', '2018-07-31 10:31:22', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('4972', '2018-07-31 10:31:30', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4973', '2018-07-31 10:31:54', '1', '1', '10.1.1.2', '1', '0', '用户名:admin,退出操作');
INSERT INTO `user_access_log` VALUES ('4974', '2018-07-31 10:32:01', '1', '6', '10.1.1.2', '0', '1', '用户名:user3,user3登录成功');
INSERT INTO `user_access_log` VALUES ('4975', '2018-07-31 10:32:17', '1', '6', '10.1.1.2', '1', '0', '用户名:user3,退出操作');
INSERT INTO `user_access_log` VALUES ('4976', '2018-07-31 10:32:28', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4977', '2018-07-31 10:32:31', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4978', '2018-07-31 10:34:52', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('4979', '2018-07-31 10:34:55', '1', '1', '10.1.1.2', '1', '0', '用户名:admin,退出操作');
INSERT INTO `user_access_log` VALUES ('4980', '2018-07-31 10:35:00', '1', '6', '10.1.1.2', '0', '1', '用户名:user3,user3登录成功');
INSERT INTO `user_access_log` VALUES ('4981', '2018-07-31 10:35:27', '1', '7', '10.1.1.2', '1', '0', '用户名:user4,退出操作');
INSERT INTO `user_access_log` VALUES ('4982', '2018-07-31 10:35:29', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4983', '2018-07-31 10:35:33', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4984', '2018-07-31 10:35:37', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4985', '2018-07-31 10:35:43', '1', '1', '10.1.0.221', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4986', '2018-07-31 10:36:34', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('4987', '2018-07-31 10:37:52', '1', '1', '10.1.1.2', '1', '0', '用户名:admin,退出操作');
INSERT INTO `user_access_log` VALUES ('4988', '2018-07-31 10:37:55', '1', '6', '10.1.1.2', '0', '1', '用户名:user3,user3登录成功');
INSERT INTO `user_access_log` VALUES ('4989', '2018-07-31 10:38:46', '1', '6', '10.1.1.2', '1', '0', '用户名:user3,退出操作');
INSERT INTO `user_access_log` VALUES ('4990', '2018-07-31 10:38:49', '1', '2', '10.1.1.2', '0', '1', '用户名:user1,用户1登录成功');
INSERT INTO `user_access_log` VALUES ('4991', '2018-07-31 10:38:53', '1', '2', '10.1.1.2', '1', '0', '用户名:user1,退出操作');
INSERT INTO `user_access_log` VALUES ('4992', '2018-07-31 10:38:57', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('4993', '2018-07-31 10:38:59', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('4994', '2018-07-31 10:39:02', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('4995', '2018-07-31 10:39:09', '1', '1', '10.1.1.2', '1', '0', '用户名:admin,退出操作');
INSERT INTO `user_access_log` VALUES ('4996', '2018-07-31 10:39:16', '1', '7', '10.1.1.2', '0', '1', '用户名:user4,user4登录成功');
INSERT INTO `user_access_log` VALUES ('4997', '2018-07-31 10:40:31', '1', '7', '10.1.1.2', '1', '0', '用户名:user4,退出操作');
INSERT INTO `user_access_log` VALUES ('4998', '2018-07-31 10:40:34', '1', '7', '10.1.1.2', '0', '1', '用户名:user4,user4登录成功');
INSERT INTO `user_access_log` VALUES ('4999', '2018-07-31 10:41:03', '1', '6', '10.1.1.2', '1', '0', '用户名:user3,退出操作');
INSERT INTO `user_access_log` VALUES ('5000', '2018-07-31 10:41:09', '1', '7', '10.1.1.2', '0', '1', '用户名:user4,user4登录成功');
INSERT INTO `user_access_log` VALUES ('5001', '2018-07-31 10:41:20', '1', '7', '10.1.1.2', '1', '0', '用户名:user4,退出操作');
INSERT INTO `user_access_log` VALUES ('5002', '2018-07-31 10:41:27', '1', '7', '10.1.1.2', '0', '1', '用户名:user4,user4登录成功');
INSERT INTO `user_access_log` VALUES ('5003', '2018-07-31 10:41:55', '1', '7', '10.1.1.2', '1', '0', '用户名:user4,退出操作');
INSERT INTO `user_access_log` VALUES ('5004', '2018-07-31 10:41:59', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5005', '2018-07-31 10:42:03', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5006', '2018-07-31 10:42:15', '1', '7', '10.1.1.2', '1', '0', '用户名:user4,退出操作');
INSERT INTO `user_access_log` VALUES ('5007', '2018-07-31 10:42:32', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5008', '2018-07-31 10:42:36', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5009', '2018-07-31 10:43:00', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('5010', '2018-07-31 10:43:06', '1', '1', '10.1.1.2', '1', '0', '用户名:admin,退出操作');
INSERT INTO `user_access_log` VALUES ('5011', '2018-07-31 10:48:50', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5012', '2018-07-31 10:54:05', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5013', '2018-07-31 10:54:17', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('5014', '2018-07-31 10:54:39', '1', '1', '10.1.1.2', '1', '0', '用户名:admin,退出操作');
INSERT INTO `user_access_log` VALUES ('5015', '2018-07-31 10:55:35', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5016', '2018-07-31 10:55:41', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5017', '2018-07-31 10:55:52', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5018', '2018-07-31 10:56:06', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5019', '2018-07-31 10:56:10', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5020', '2018-07-31 10:56:20', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5021', '2018-07-31 10:56:38', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5022', '2018-07-31 10:58:22', '1', '1', '10.1.1.2', '2', '13', '用户名:admin,查询部门管理');
INSERT INTO `user_access_log` VALUES ('5023', '2018-07-31 10:58:27', '1', '1', '10.1.1.2', '2', '11', '用户名:admin,查询应用集成管理');
INSERT INTO `user_access_log` VALUES ('5024', '2018-07-31 10:58:33', '1', '1', '10.1.1.2', '2', '10', '用户名:admin,查询菜单管理');
INSERT INTO `user_access_log` VALUES ('5025', '2018-07-31 10:58:41', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5026', '2018-07-31 10:58:55', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5027', '2018-07-31 10:59:40', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5028', '2018-07-31 11:07:02', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5029', '2018-07-31 11:07:06', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5030', '2018-07-31 11:14:20', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5031', '2018-07-31 11:14:28', '1', '1', '10.1.0.221', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5032', '2018-07-31 11:15:13', '1', '1', '10.1.0.221', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5033', '2018-07-31 11:35:54', '1', '1', '192.168.1.142', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5034', '2018-07-31 11:36:01', '1', '1', '192.168.1.142', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5035', '2018-07-31 11:37:11', '1', '1', '192.168.1.142', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5036', '2018-07-31 11:38:11', '1', '1', '192.168.1.142', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5037', '2018-07-31 11:41:01', '1', '1', '192.168.1.142', '2', '10', '用户名:admin,查询菜单管理');
INSERT INTO `user_access_log` VALUES ('5038', '2018-07-31 11:41:02', '1', '1', '192.168.1.142', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5039', '2018-07-31 11:41:37', '1', '1', '192.168.1.142', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5040', '2018-07-31 11:42:16', '1', '1', '192.168.1.142', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5041', '2018-07-31 11:42:31', '1', '1', '192.168.1.142', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5042', '2018-07-31 11:45:50', '1', '1', '192.168.1.142', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5043', '2018-07-31 11:45:56', '1', '1', '192.168.1.142', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5044', '2018-07-31 11:48:33', '1', '1', '192.168.1.142', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5045', '2018-07-31 11:50:51', '1', '1', '192.168.1.142', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5046', '2018-07-31 11:52:04', '1', '1', '192.168.1.142', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5047', '2018-07-31 11:53:09', '1', '1', '192.168.1.142', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5048', '2018-07-31 11:53:37', '1', '1', '192.168.1.142', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5049', '2018-07-31 11:56:10', '1', '1', '192.168.1.142', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5050', '2018-07-31 13:14:30', '1', '1', '192.168.1.142', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5051', '2018-07-31 13:15:04', '1', '1', '192.168.1.142', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5052', '2018-07-31 13:17:01', '1', '1', '192.168.1.142', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5053', '2018-07-31 13:17:38', '1', '1', '192.168.1.142', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5054', '2018-07-31 13:17:45', '1', '1', '192.168.1.142', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5055', '2018-07-31 13:18:23', '1', '1', '192.168.1.142', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5056', '2018-07-31 13:19:40', '1', '1', '192.168.1.142', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5057', '2018-07-31 13:20:30', '1', '1', '192.168.1.142', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5058', '2018-07-31 13:36:43', '1', '0', '192.168.1.142', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5059', '2018-07-31 13:37:06', '1', '1', '192.168.1.142', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('5060', '2018-07-31 13:40:02', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5061', '2018-07-31 13:42:43', '1', '1', '192.168.1.142', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5062', '2018-07-31 13:47:04', '1', '1', '192.168.1.142', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5063', '2018-07-31 13:47:25', '1', '1', '192.168.1.142', '2', '10', '用户名:admin,查询菜单管理');
INSERT INTO `user_access_log` VALUES ('5064', '2018-07-31 13:47:37', '1', '1', '192.168.1.142', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5065', '2018-07-31 13:47:47', '1', '1', '192.168.1.142', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5066', '2018-07-31 13:51:27', '1', '1', '192.168.1.142', '1', '0', '用户名:admin,退出操作');
INSERT INTO `user_access_log` VALUES ('5067', '2018-07-31 13:51:45', '1', '1', '192.168.1.142', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5068', '2018-07-31 13:51:50', '1', '1', '192.168.1.142', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5069', '2018-07-31 13:52:10', '1', '1', '192.168.1.142', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5070', '2018-07-31 13:55:02', '1', '1', '192.168.1.142', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5071', '2018-07-31 13:55:54', '1', '1', '192.168.1.142', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5072', '2018-07-31 13:55:01', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5073', '2018-07-31 13:55:06', '1', '1', '10.1.0.221', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5074', '2018-07-31 14:12:46', '1', '1', '10.1.1.2', '0', '1', '用户名:user4,user4登录成功');
INSERT INTO `user_access_log` VALUES ('5075', '2018-07-31 14:13:14', '1', '7', '10.1.1.2', '1', '0', '用户名:user4,退出操作');
INSERT INTO `user_access_log` VALUES ('5076', '2018-07-31 14:13:16', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5077', '2018-07-31 14:24:00', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('5078', '2018-07-31 14:29:09', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5079', '2018-07-31 14:53:57', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5080', '2018-07-31 14:54:03', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('5081', '2018-07-31 15:13:43', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5082', '2018-07-31 15:13:47', '1', '1', '10.1.0.221', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5083', '2018-07-31 15:17:37', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5084', '2018-07-31 15:17:44', '1', '1', '10.1.0.221', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5085', '2018-07-31 15:19:46', '1', '1', '10.1.0.221', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5086', '2018-07-31 15:21:26', '1', '1', '10.1.0.221', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5087', '2018-07-31 15:22:13', '1', '1', '10.1.0.221', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5088', '2018-07-31 15:23:14', '1', '1', '10.1.0.221', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5089', '2018-07-31 15:23:50', '1', '1', '10.1.0.221', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5090', '2018-07-31 15:24:52', '1', '1', '10.1.0.221', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5091', '2018-07-31 15:26:37', '1', '1', '10.1.0.221', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5092', '2018-07-31 15:27:46', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5093', '2018-07-31 15:27:55', '1', '1', '10.1.0.221', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5094', '2018-07-31 15:28:06', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5095', '2018-07-31 15:30:00', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5096', '2018-07-31 16:39:37', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5097', '2018-07-31 16:39:44', '1', '1', '10.1.0.221', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5098', '2018-07-31 16:40:04', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5099', '2018-07-31 16:40:10', '1', '1', '10.1.0.221', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5100', '2018-07-31 16:41:30', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5101', '2018-07-31 16:41:33', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5102', '2018-07-31 16:41:54', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5103', '2018-07-31 16:42:00', '1', '1', '10.1.0.221', '1', '0', '用户名:admin,退出操作');
INSERT INTO `user_access_log` VALUES ('5104', '2018-07-31 16:42:04', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5105', '2018-07-31 16:42:09', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5106', '2018-07-31 16:42:09', '1', '3', '10.1.0.221', '0', '1', '用户名:user2,用户2登录成功');
INSERT INTO `user_access_log` VALUES ('5107', '2018-07-31 16:42:16', '1', '3', '10.1.0.221', '1', '0', '用户名:user2,退出操作');
INSERT INTO `user_access_log` VALUES ('5108', '2018-07-31 16:42:23', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5109', '2018-07-31 16:44:43', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5110', '2018-07-31 16:44:57', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5111', '2018-07-31 16:46:23', '1', '1', '10.1.0.221', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('5112', '2018-07-31 16:48:19', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('5113', '2018-07-31 16:57:32', '1', '1', '10.1.0.221', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5114', '2018-07-31 17:08:48', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('5115', '2018-07-31 17:09:07', '1', '1', '10.1.1.2', '1', '0', '用户名:admin,退出操作');
INSERT INTO `user_access_log` VALUES ('5116', '2018-07-31 17:09:12', '1', '6', '10.1.1.2', '0', '1', '用户名:user3,user3登录成功');
INSERT INTO `user_access_log` VALUES ('5117', '2018-07-31 17:10:28', '1', '6', '10.1.1.2', '1', '0', '用户名:user3,退出操作');
INSERT INTO `user_access_log` VALUES ('5118', '2018-07-31 17:10:34', '1', '3', '10.1.1.2', '0', '1', '用户名:user2,用户2登录成功');
INSERT INTO `user_access_log` VALUES ('5119', '2018-07-31 17:12:06', '1', '3', '10.1.1.2', '1', '0', '用户名:user2,退出操作');
INSERT INTO `user_access_log` VALUES ('5120', '2018-07-31 17:12:11', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5121', '2018-07-31 17:12:52', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5122', '2018-07-31 17:33:24', '1', '1', '10.1.1.2', '2', '13', '用户名:admin,查询部门管理');
INSERT INTO `user_access_log` VALUES ('5123', '2018-08-01 09:17:21', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5124', '2018-08-01 09:28:36', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5125', '2018-08-01 09:41:40', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5126', '2018-08-01 09:45:14', '1', '1', '10.1.1.2', '2', '13', '用户名:admin,查询部门管理');
INSERT INTO `user_access_log` VALUES ('5127', '2018-08-01 10:19:17', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5128', '2018-08-01 10:21:52', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5129', '2018-08-01 10:37:11', '1', '1', '10.1.1.2', '2', '13', '用户名:admin,查询部门管理');
INSERT INTO `user_access_log` VALUES ('5130', '2018-08-01 13:35:35', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5131', '2018-08-01 14:20:13', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5132', '2018-08-01 17:23:18', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5133', '2018-08-01 17:23:21', '1', '1', '10.1.0.221', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('5134', '2018-08-02 09:23:50', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5135', '2018-08-02 09:23:56', '1', '1', '10.1.0.221', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5136', '2018-08-02 09:26:59', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5137', '2018-08-02 09:27:04', '1', '1', '10.1.0.221', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5138', '2018-08-02 09:27:55', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5139', '2018-08-02 09:28:12', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5140', '2018-08-02 09:31:12', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5141', '2018-08-02 09:31:21', '1', '1', '10.1.0.221', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5142', '2018-08-03 08:35:02', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5143', '2018-08-03 10:12:18', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5144', '2018-08-03 10:12:44', '1', '1', '10.1.0.221', '2', '50', '用户名:admin,查询业务监测应用管理');
INSERT INTO `user_access_log` VALUES ('5145', '2018-08-03 10:13:19', '1', '1', '10.1.0.221', '2', '50', '用户名:admin,查询业务监测应用管理');
INSERT INTO `user_access_log` VALUES ('5146', '2018-08-03 10:15:05', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5147', '2018-08-03 10:15:28', '1', '1', '10.1.0.221', '2', '50', '用户名:admin,查询业务监测应用管理');
INSERT INTO `user_access_log` VALUES ('5148', '2018-08-03 10:17:26', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5149', '2018-08-03 10:28:39', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5150', '2018-08-03 11:38:02', '1', '1', '192.168.1.142', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5151', '2018-08-03 11:38:14', '1', '1', '192.168.1.142', '2', '50', '用户名:admin,查询业务监测应用管理');
INSERT INTO `user_access_log` VALUES ('5152', '2018-08-03 11:38:51', '1', '1', '192.168.1.142', '2', '50', '用户名:admin,查询业务监测应用管理');
INSERT INTO `user_access_log` VALUES ('5153', '2018-08-03 11:41:54', '1', '1', '192.168.1.142', '2', '50', '用户名:admin,查询业务监测应用管理');
INSERT INTO `user_access_log` VALUES ('5154', '2018-08-03 11:44:20', '1', '1', '192.168.1.142', '2', '50', '用户名:admin,查询业务监测应用管理');
INSERT INTO `user_access_log` VALUES ('5155', '2018-08-03 13:53:59', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5156', '2018-08-03 15:54:43', '1', '1', '192.168.109.1', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5157', '2018-08-03 15:55:00', '1', '1', '192.168.109.1', '1', '0', '用户名:admin,退出操作');
INSERT INTO `user_access_log` VALUES ('5158', '2018-08-03 15:55:00', '1', '1', '192.168.109.1', '2', '149', '用户名:admin,查询大数据安全管理');
INSERT INTO `user_access_log` VALUES ('5159', '2018-08-03 15:55:00', '1', '1', '192.168.109.1', '1', '0', '用户名:admin,退出操作');
INSERT INTO `user_access_log` VALUES ('5160', '2018-08-03 15:55:06', '1', '1', '192.168.109.1', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5161', '2018-08-03 15:55:08', '1', '1', '192.168.109.1', '2', '152', '用户名:admin,查询监控运维');
INSERT INTO `user_access_log` VALUES ('5162', '2018-08-03 15:55:23', '1', '1', '192.168.109.1', '1', '0', '用户名:admin,退出操作');
INSERT INTO `user_access_log` VALUES ('5163', '2018-08-06 09:38:31', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5164', '2018-08-06 09:42:06', '1', '1', '192.168.109.1', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5165', '2018-08-06 10:35:00', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5166', '2018-08-06 10:35:07', '1', '1', '10.1.1.2', '2', '152', '用户名:admin,查询监控运维');
INSERT INTO `user_access_log` VALUES ('5167', '2018-08-06 10:35:55', '1', '1', '10.1.1.2', '2', '57', '用户名:admin,查询配置版本管理');
INSERT INTO `user_access_log` VALUES ('5168', '2018-08-06 10:36:03', '1', '1', '10.1.1.2', '2', '58', '用户名:admin,查询内容浏览');
INSERT INTO `user_access_log` VALUES ('5169', '2018-08-06 10:36:05', '1', '1', '10.1.1.2', '2', '59', '用户名:admin,查询内容审核管理');
INSERT INTO `user_access_log` VALUES ('5170', '2018-08-06 10:36:07', '1', '1', '10.1.1.2', '2', '60', '用户名:admin,查询内容标签管理');
INSERT INTO `user_access_log` VALUES ('5171', '2018-08-06 10:36:08', '1', '1', '10.1.1.2', '2', '69', '用户名:admin,查询资料库管理');
INSERT INTO `user_access_log` VALUES ('5172', '2018-08-06 10:36:12', '1', '1', '10.1.1.2', '2', '70', '用户名:admin,查询资料类别管理');
INSERT INTO `user_access_log` VALUES ('5173', '2018-08-06 16:17:37', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5174', '2018-08-07 11:31:47', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5175', '2018-08-07 11:35:34', '1', '1', '10.1.1.2', '2', '50', '用户名:admin,查询业务监测应用管理');
INSERT INTO `user_access_log` VALUES ('5176', '2018-08-07 13:58:01', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5177', '2018-08-07 14:39:27', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5178', '2018-08-08 13:49:43', '1', '1', '192.168.109.1', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5179', '2018-08-08 13:50:13', '1', '1', '192.168.109.1', '2', '146', '用户名:admin,查询共享交换系统');
INSERT INTO `user_access_log` VALUES ('5180', '2018-08-08 13:50:17', '1', '1', '192.168.109.1', '2', '160', '用户名:admin,查询大数据管理平台');
INSERT INTO `user_access_log` VALUES ('5181', '2018-08-08 13:50:26', '1', '1', '192.168.109.1', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('5182', '2018-08-08 15:46:37', '1', '1', '192.168.1.142', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5183', '2018-08-08 15:56:13', '1', '1', '192.168.1.142', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5184', '2018-08-08 16:02:16', '1', '1', '192.168.1.142', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5185', '2018-08-08 16:23:38', '1', '1', '192.168.1.142', '2', '50', '用户名:admin,查询业务监测应用管理');
INSERT INTO `user_access_log` VALUES ('5186', '2018-08-08 16:24:09', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5187', '2018-08-08 16:24:17', '1', '1', '10.1.0.221', '2', '46', '用户名:admin,查询信息搜索');
INSERT INTO `user_access_log` VALUES ('5188', '2018-08-08 16:50:24', '1', '1', '192.168.1.142', '2', '57', '用户名:admin,查询配置版本管理');
INSERT INTO `user_access_log` VALUES ('5189', '2018-08-08 17:00:13', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5190', '2018-08-08 17:28:32', '1', '1', '192.168.1.142', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5191', '2018-08-08 17:28:50', '1', '1', '192.168.1.142', '2', '50', '用户名:admin,查询业务监测应用管理');
INSERT INTO `user_access_log` VALUES ('5192', '2018-08-08 17:30:52', '1', '1', '192.168.1.142', '2', '50', '用户名:admin,查询业务监测应用管理');
INSERT INTO `user_access_log` VALUES ('5193', '2018-08-09 10:12:15', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5194', '2018-08-09 10:12:47', '1', '1', '10.1.1.2', '2', '50', '用户名:admin,查询业务监测应用管理');
INSERT INTO `user_access_log` VALUES ('5195', '2018-08-09 11:02:49', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5196', '2018-08-09 11:02:53', '1', '1', '10.1.1.2', '2', '50', '用户名:admin,查询业务监测应用管理');
INSERT INTO `user_access_log` VALUES ('5197', '2018-08-09 14:14:47', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5198', '2018-08-09 14:15:20', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5199', '2018-08-09 14:16:50', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5200', '2018-08-09 15:18:27', '1', '1', '192.168.1.142', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5201', '2018-08-09 15:19:22', '1', '1', '192.168.1.142', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5202', '2018-08-09 15:19:48', '1', '1', '192.168.1.142', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5203', '2018-08-09 15:20:08', '1', '1', '192.168.1.142', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5204', '2018-08-09 15:23:35', '1', '1', '192.168.1.142', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5205', '2018-08-10 09:15:42', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5206', '2018-08-10 09:15:46', '1', '1', '10.1.1.2', '2', '50', '用户名:admin,查询业务监测应用管理');
INSERT INTO `user_access_log` VALUES ('5207', '2018-08-10 11:21:12', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5208', '2018-08-10 11:21:17', '1', '1', '10.1.0.221', '2', '104', '用户名:admin,查询指标管理平台');
INSERT INTO `user_access_log` VALUES ('5209', '2018-08-10 11:23:38', '1', '1', '10.1.0.221', '2', '104', '用户名:admin,查询指标管理平台');
INSERT INTO `user_access_log` VALUES ('5210', '2018-08-10 11:24:54', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5211', '2018-08-10 11:27:33', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5212', '2018-08-10 11:27:50', '1', '1', '10.1.0.221', '2', '104', '用户名:admin,查询指标管理平台');
INSERT INTO `user_access_log` VALUES ('5213', '2018-08-10 11:47:19', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5214', '2018-08-10 11:47:23', '1', '1', '10.1.0.221', '2', '104', '用户名:admin,查询指标管理平台');
INSERT INTO `user_access_log` VALUES ('5215', '2018-08-10 13:17:05', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5216', '2018-08-10 13:17:11', '1', '1', '10.1.0.221', '1', '0', '用户名:admin,退出操作');
INSERT INTO `user_access_log` VALUES ('5217', '2018-08-10 13:17:14', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5218', '2018-08-10 13:17:22', '1', '1', '10.1.0.221', '2', '104', '用户名:admin,查询指标管理平台');
INSERT INTO `user_access_log` VALUES ('5219', '2018-08-10 15:39:24', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5220', '2018-08-13 09:46:27', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5221', '2018-08-13 09:48:46', '1', '1', '10.1.1.2', '2', '50', '用户名:admin,查询业务监测应用管理');
INSERT INTO `user_access_log` VALUES ('5222', '2018-08-13 10:40:46', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5223', '2018-08-13 10:48:51', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5224', '2018-08-13 13:30:02', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5225', '2018-08-13 13:30:19', '1', '1', '10.1.0.221', '2', '50', '用户名:admin,查询业务监测应用管理');
INSERT INTO `user_access_log` VALUES ('5226', '2018-08-13 14:48:59', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5227', '2018-08-13 14:51:00', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5228', '2018-08-13 16:15:07', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5229', '2018-08-13 16:17:11', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5230', '2018-08-13 16:18:43', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5231', '2018-08-13 16:19:26', '1', '1', '10.1.1.2', '2', '50', '用户名:admin,查询业务监测应用管理');
INSERT INTO `user_access_log` VALUES ('5232', '2018-08-13 16:34:31', '1', '1', '10.1.1.2', '2', '46', '用户名:admin,查询信息搜索');
INSERT INTO `user_access_log` VALUES ('5233', '2018-08-13 16:58:01', '1', '1', '10.1.1.2', '2', '58', '用户名:admin,查询内容浏览');
INSERT INTO `user_access_log` VALUES ('5234', '2018-08-14 09:14:06', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5235', '2018-08-14 09:37:28', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('5236', '2018-08-14 09:50:44', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5237', '2018-08-14 09:51:00', '1', '1', '10.1.1.2', '2', '58', '用户名:admin,查询内容浏览');
INSERT INTO `user_access_log` VALUES ('5238', '2018-08-14 10:22:21', '1', '1', '10.1.0.111', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5239', '2018-08-14 10:22:48', '1', '1', '10.1.0.111', '2', '160', '用户名:admin,查询大数据管理平台');
INSERT INTO `user_access_log` VALUES ('5240', '2018-08-14 10:22:53', '1', '1', '10.1.0.111', '2', '149', '用户名:admin,查询大数据安全管理');
INSERT INTO `user_access_log` VALUES ('5241', '2018-08-14 10:22:59', '1', '1', '10.1.0.111', '2', '156', '用户名:admin,查询资源目录系统');
INSERT INTO `user_access_log` VALUES ('5242', '2018-08-14 10:25:32', '1', '1', '10.1.0.111', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('5243', '2018-08-14 10:26:51', '1', '1', '10.1.0.111', '2', '152', '用户名:admin,查询监控运维');
INSERT INTO `user_access_log` VALUES ('5244', '2018-08-14 10:34:31', '1', '1', '10.1.0.111', '2', '180', '用户名:admin,查询数据可视化工具');
INSERT INTO `user_access_log` VALUES ('5245', '2018-08-14 10:34:41', '1', '1', '10.1.0.111', '2', '146', '用户名:admin,查询共享交换系统');
INSERT INTO `user_access_log` VALUES ('5246', '2018-08-14 10:34:48', '1', '1', '10.1.0.111', '2', '160', '用户名:admin,查询大数据管理平台');
INSERT INTO `user_access_log` VALUES ('5247', '2018-08-14 14:00:48', '1', '1', '192.168.1.142', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5248', '2018-08-14 14:37:14', '1', '1', '192.168.1.142', '2', '50', '用户名:admin,查询业务监测应用管理');
INSERT INTO `user_access_log` VALUES ('5249', '2018-08-14 14:44:48', '1', '1', '192.168.1.142', '2', '50', '用户名:admin,查询业务监测应用管理');
INSERT INTO `user_access_log` VALUES ('5250', '2018-08-14 17:06:23', '1', '1', '192.168.1.142', '2', '50', '用户名:admin,查询业务监测应用管理');
INSERT INTO `user_access_log` VALUES ('5251', '2018-08-14 17:08:05', '1', '1', '192.168.1.142', '2', '50', '用户名:admin,查询业务监测应用管理');
INSERT INTO `user_access_log` VALUES ('5252', '2018-08-14 17:08:17', '1', '1', '192.168.1.142', '2', '50', '用户名:admin,查询业务监测应用管理');
INSERT INTO `user_access_log` VALUES ('5253', '2018-08-14 17:09:11', '1', '1', '192.168.1.142', '2', '50', '用户名:admin,查询业务监测应用管理');
INSERT INTO `user_access_log` VALUES ('5254', '2018-08-14 17:11:42', '1', '1', '192.168.1.142', '2', '10', '用户名:admin,查询菜单管理');
INSERT INTO `user_access_log` VALUES ('5255', '2018-08-14 17:11:55', '1', '1', '192.168.1.142', '2', '50', '用户名:admin,查询业务监测应用管理');
INSERT INTO `user_access_log` VALUES ('5256', '2018-08-14 17:11:57', '1', '1', '192.168.1.142', '2', '10', '用户名:admin,查询菜单管理');
INSERT INTO `user_access_log` VALUES ('5257', '2018-08-14 17:17:27', '1', '1', '192.168.1.142', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5258', '2018-08-14 17:19:53', '1', '1', '192.168.1.142', '2', '50', '用户名:admin,查询业务监测应用管理');
INSERT INTO `user_access_log` VALUES ('5259', '2018-08-15 09:25:45', '1', '1', '192.168.1.142', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5260', '2018-08-15 09:26:15', '1', '1', '192.168.1.142', '2', '50', '用户名:admin,查询业务监测应用管理');
INSERT INTO `user_access_log` VALUES ('5261', '2018-08-15 09:27:43', '1', '1', '192.168.1.142', '2', '10', '用户名:admin,查询菜单管理');
INSERT INTO `user_access_log` VALUES ('5262', '2018-08-15 09:30:59', '1', '1', '192.168.1.142', '2', '10', '用户名:admin,查询菜单管理');
INSERT INTO `user_access_log` VALUES ('5263', '2018-08-15 09:31:34', '1', '1', '192.168.1.142', '2', '11', '用户名:admin,查询应用集成管理');
INSERT INTO `user_access_log` VALUES ('5264', '2018-08-15 09:49:58', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5265', '2018-08-15 09:50:04', '1', '1', '10.1.0.221', '2', '11', '用户名:admin,查询应用集成管理');
INSERT INTO `user_access_log` VALUES ('5266', '2018-08-15 10:22:26', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5267', '2018-08-15 10:22:36', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('5268', '2018-08-15 10:22:49', '1', '1', '10.1.1.2', '2', '17', '用户名:admin,查询日志查询');
INSERT INTO `user_access_log` VALUES ('5269', '2018-08-15 10:23:02', '1', '1', '10.1.1.2', '2', '18', '用户名:admin,查询日志报表');
INSERT INTO `user_access_log` VALUES ('5270', '2018-08-15 10:27:43', '1', '1', '10.1.1.2', '2', '10', '用户名:admin,查询菜单管理');
INSERT INTO `user_access_log` VALUES ('5271', '2018-08-15 10:29:05', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5272', '2018-08-15 10:29:08', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('5273', '2018-08-15 10:29:53', '1', '1', '10.1.1.2', '2', '194', '用户名:admin,查询智能应用APP');
INSERT INTO `user_access_log` VALUES ('5274', '2018-08-15 10:29:55', '1', '1', '10.1.1.2', '2', '57', '用户名:admin,查询配置版本管理');
INSERT INTO `user_access_log` VALUES ('5275', '2018-08-15 10:30:20', '1', '1', '10.1.1.2', '2', '58', '用户名:admin,查询内容浏览');
INSERT INTO `user_access_log` VALUES ('5276', '2018-08-15 10:30:24', '1', '1', '10.1.1.2', '2', '59', '用户名:admin,查询内容审核管理');
INSERT INTO `user_access_log` VALUES ('5277', '2018-08-15 10:30:28', '1', '1', '10.1.1.2', '2', '46', '用户名:admin,查询信息搜索');
INSERT INTO `user_access_log` VALUES ('5278', '2018-08-15 10:30:40', '1', '1', '10.1.1.2', '2', '14', '用户名:admin,查询岗位管理');
INSERT INTO `user_access_log` VALUES ('5279', '2018-08-15 10:30:45', '1', '1', '10.1.1.2', '2', '13', '用户名:admin,查询部门管理');
INSERT INTO `user_access_log` VALUES ('5280', '2018-08-15 10:31:00', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5281', '2018-08-15 10:33:57', '1', '1', '10.1.1.2', '2', '50', '用户名:admin,查询业务监测应用管理');
INSERT INTO `user_access_log` VALUES ('5282', '2018-08-15 10:33:58', '1', '1', '10.1.1.2', '2', '11', '用户名:admin,查询应用集成管理');
INSERT INTO `user_access_log` VALUES ('5283', '2018-08-15 10:46:01', '1', '1', '10.1.1.2', '2', '14', '用户名:admin,查询岗位管理');
INSERT INTO `user_access_log` VALUES ('5284', '2018-08-15 10:46:24', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5285', '2018-08-15 10:46:46', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5286', '2018-08-15 10:47:35', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5287', '2018-08-15 10:48:01', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5288', '2018-08-15 10:49:47', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5289', '2018-08-15 10:49:52', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('5290', '2018-08-15 10:50:42', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5291', '2018-08-15 10:50:46', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('5292', '2018-08-15 10:50:49', '1', '1', '10.1.1.2', '2', '10', '用户名:admin,查询菜单管理');
INSERT INTO `user_access_log` VALUES ('5293', '2018-08-15 10:51:37', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('5294', '2018-08-15 10:51:40', '1', '1', '10.1.1.2', '2', '10', '用户名:admin,查询菜单管理');
INSERT INTO `user_access_log` VALUES ('5295', '2018-08-15 10:51:50', '1', '1', '10.1.1.2', '2', '11', '用户名:admin,查询应用集成管理');
INSERT INTO `user_access_log` VALUES ('5296', '2018-08-15 10:51:57', '1', '1', '10.1.1.2', '2', '14', '用户名:admin,查询岗位管理');
INSERT INTO `user_access_log` VALUES ('5297', '2018-08-15 10:54:18', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5298', '2018-08-15 10:54:28', '1', '1', '10.1.0.221', '2', '147', '用户名:admin,查询网络爬虫系统');
INSERT INTO `user_access_log` VALUES ('5299', '2018-08-15 10:54:36', '1', '1', '10.1.0.221', '2', '160', '用户名:admin,查询大数据管理平台');
INSERT INTO `user_access_log` VALUES ('5300', '2018-08-15 10:54:52', '1', '1', '10.1.0.221', '2', '150', '用户名:admin,查询大数据使用平台');
INSERT INTO `user_access_log` VALUES ('5301', '2018-08-15 10:54:56', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('5302', '2018-08-15 10:55:04', '1', '1', '10.1.0.221', '2', '151', '用户名:admin,查询大数据分级管理');
INSERT INTO `user_access_log` VALUES ('5303', '2018-08-15 10:55:44', '1', '1', '10.1.0.221', '2', '103', '用户名:admin,查询数据治理平台');
INSERT INTO `user_access_log` VALUES ('5304', '2018-08-15 10:56:03', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5305', '2018-08-15 10:56:06', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('5306', '2018-08-15 10:56:32', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5307', '2018-08-15 10:56:35', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('5308', '2018-08-15 10:58:28', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5309', '2018-08-15 10:58:30', '1', '1', '10.1.1.2', '2', '10', '用户名:admin,查询菜单管理');
INSERT INTO `user_access_log` VALUES ('5310', '2018-08-15 10:58:54', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5311', '2018-08-15 10:58:57', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('5312', '2018-08-15 11:01:45', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5313', '2018-08-15 11:01:56', '1', '1', '10.1.1.2', '2', '11', '用户名:admin,查询应用集成管理');
INSERT INTO `user_access_log` VALUES ('5314', '2018-08-15 11:02:39', '1', '1', '10.1.1.2', '2', '11', '用户名:admin,查询应用集成管理');
INSERT INTO `user_access_log` VALUES ('5315', '2018-08-15 11:04:23', '1', '1', '10.1.1.2', '2', '50', '用户名:admin,查询业务监测应用管理');
INSERT INTO `user_access_log` VALUES ('5316', '2018-08-15 11:04:25', '1', '1', '10.1.1.2', '2', '11', '用户名:admin,查询应用集成管理');
INSERT INTO `user_access_log` VALUES ('5317', '2018-08-15 11:08:54', '1', '1', '10.1.1.2', '2', '11', '用户名:admin,查询应用集成管理');
INSERT INTO `user_access_log` VALUES ('5318', '2018-08-15 11:24:23', '1', '1', '10.1.1.2', '2', '11', '用户名:admin,查询应用集成管理');
INSERT INTO `user_access_log` VALUES ('5319', '2018-08-15 11:37:07', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5320', '2018-08-15 11:37:12', '1', '1', '10.1.0.221', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('5321', '2018-08-15 11:37:44', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5322', '2018-08-15 11:44:44', '1', '1', '10.1.0.223', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5323', '2018-08-15 11:44:49', '1', '1', '10.1.0.223', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('5324', '2018-08-15 11:45:46', '1', '1', '10.1.0.223', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5325', '2018-08-15 11:45:57', '1', '1', '10.1.0.223', '1', '0', '用户名:admin,退出操作');
INSERT INTO `user_access_log` VALUES ('5326', '2018-08-15 12:33:45', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5327', '2018-08-15 12:33:55', '1', '1', '10.1.0.221', '2', '11', '用户名:admin,查询应用集成管理');
INSERT INTO `user_access_log` VALUES ('5328', '2018-08-15 13:23:50', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5329', '2018-08-15 13:25:27', '1', '1', '10.1.0.221', '2', '11', '用户名:admin,查询应用集成管理');
INSERT INTO `user_access_log` VALUES ('5330', '2018-08-15 13:27:35', '1', '1', '10.1.0.221', '2', '11', '用户名:admin,查询应用集成管理');
INSERT INTO `user_access_log` VALUES ('5331', '2018-08-15 13:38:34', '1', '1', '192.168.1.142', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5332', '2018-08-15 13:38:48', '1', '1', '192.168.1.142', '2', '11', '用户名:admin,查询应用集成管理');
INSERT INTO `user_access_log` VALUES ('5333', '2018-08-15 14:03:55', '1', '1', '192.168.1.142', '2', '11', '用户名:admin,查询应用集成管理');
INSERT INTO `user_access_log` VALUES ('5334', '2018-08-15 14:57:47', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5335', '2018-08-15 14:57:52', '1', '1', '10.1.0.221', '2', '11', '用户名:admin,查询应用集成管理');
INSERT INTO `user_access_log` VALUES ('5336', '2018-08-15 15:48:16', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5337', '2018-08-15 15:48:21', '1', '1', '10.1.0.221', '2', '11', '用户名:admin,查询应用集成管理');
INSERT INTO `user_access_log` VALUES ('5338', '2018-08-15 15:51:56', '1', '1', '10.1.0.221', '2', '11', '用户名:admin,查询应用集成管理');
INSERT INTO `user_access_log` VALUES ('5339', '2018-08-15 15:56:39', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5340', '2018-08-15 16:19:25', '1', '1', '10.1.0.223', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5341', '2018-08-15 16:29:18', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5342', '2018-08-16 08:17:04', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5343', '2018-08-16 08:17:34', '1', '1', '10.1.0.221', '2', '11', '用户名:admin,查询应用集成管理');
INSERT INTO `user_access_log` VALUES ('5344', '2018-08-16 10:47:44', '1', '1', '10.1.1.2', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5345', '2018-08-16 10:48:02', '1', '1', '10.1.1.2', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('5346', '2018-08-16 10:48:03', '1', '1', '10.1.1.2', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5347', '2018-08-16 10:48:35', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5348', '2018-08-16 10:48:45', '1', '1', '10.1.0.221', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5349', '2018-08-16 10:49:58', '1', '1', '10.1.0.221', '2', '12', '用户名:admin,查询角色管理');
INSERT INTO `user_access_log` VALUES ('5350', '2018-08-16 10:56:32', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5351', '2018-08-16 11:12:43', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5352', '2018-08-16 11:12:48', '1', '1', '10.1.0.221', '2', '57', '用户名:admin,查询配置版本管理');
INSERT INTO `user_access_log` VALUES ('5353', '2018-08-16 11:17:29', '1', '1', '10.1.1.2', '2', '11', '用户名:admin,查询应用集成管理');
INSERT INTO `user_access_log` VALUES ('5354', '2018-08-16 11:17:42', '1', '1', '10.1.1.2', '2', '10', '用户名:admin,查询菜单管理');
INSERT INTO `user_access_log` VALUES ('5355', '2018-08-16 15:15:04', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5356', '2018-08-16 15:18:55', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5357', '2018-08-16 16:55:13', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5358', '2018-08-16 16:55:19', '1', '1', '10.1.0.221', '2', '46', '用户名:admin,查询信息搜索');
INSERT INTO `user_access_log` VALUES ('5359', '2018-08-17 09:50:50', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5360', '2018-08-17 09:52:31', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5361', '2018-08-17 10:08:14', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5362', '2018-08-17 10:08:18', '1', '1', '10.1.0.221', '2', '103', '用户名:admin,查询数据治理平台');
INSERT INTO `user_access_log` VALUES ('5363', '2018-08-20 10:48:10', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5364', '2018-08-21 09:43:07', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5365', '2018-08-21 09:43:08', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5366', '2018-08-21 09:43:08', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5367', '2018-08-21 09:43:08', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5368', '2018-08-21 09:43:08', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5369', '2018-08-21 09:43:08', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5370', '2018-08-21 09:43:08', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5371', '2018-08-21 09:43:13', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5372', '2018-08-21 09:43:13', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5373', '2018-08-21 09:43:13', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5374', '2018-08-21 09:43:14', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5375', '2018-08-21 09:43:14', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5376', '2018-08-21 09:43:14', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5377', '2018-08-21 09:43:14', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5378', '2018-08-21 09:47:19', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5379', '2018-08-21 09:47:20', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5380', '2018-08-21 09:47:20', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5381', '2018-08-21 09:47:20', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5382', '2018-08-21 09:47:20', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5383', '2018-08-21 09:47:22', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5384', '2018-08-21 09:47:22', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5385', '2018-08-21 09:49:16', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5386', '2018-08-21 09:49:16', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5387', '2018-08-21 09:49:16', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5388', '2018-08-21 09:49:16', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5389', '2018-08-21 09:49:17', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5390', '2018-08-21 09:49:17', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5391', '2018-08-21 10:23:09', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5392', '2018-08-21 10:23:10', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5393', '2018-08-21 10:23:10', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5394', '2018-08-21 10:23:12', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5395', '2018-08-21 10:23:12', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5396', '2018-08-21 10:23:13', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5397', '2018-08-21 13:51:17', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5398', '2018-08-21 13:51:23', '1', '1', '10.1.0.221', '2', '8', '用户名:admin,查询用户管理');
INSERT INTO `user_access_log` VALUES ('5399', '2018-08-21 14:40:23', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5400', '2018-08-21 14:50:12', '1', '1', '10.1.0.186', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5401', '2018-08-21 14:50:21', '1', '1', '10.1.0.186', '2', '139', '用户名:admin,查询分布式挖掘系统');
INSERT INTO `user_access_log` VALUES ('5402', '2018-08-21 15:17:43', '1', '1', '10.1.0.186', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5403', '2018-08-21 15:17:45', '1', '1', '10.1.0.186', '2', '152', '用户名:admin,查询监控运维');
INSERT INTO `user_access_log` VALUES ('5404', '2018-08-22 09:08:07', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');
INSERT INTO `user_access_log` VALUES ('5405', '2018-08-22 09:36:06', '1', '1', '10.1.0.221', '0', '1', '用户名:admin,管理员登录成功');

-- ----------------------------
-- Table structure for user_alert_config
-- ----------------------------
DROP TABLE IF EXISTS `user_alert_config`;
CREATE TABLE `user_alert_config` (
  `config_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `busi_type_id` int(11) DEFAULT NULL,
  `alert_mode_id` int(11) DEFAULT NULL,
  `param_id` int(11) DEFAULT NULL,
  `param_value` varchar(40) DEFAULT NULL,
  `cre_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_alert_config
-- ----------------------------

-- ----------------------------
-- Table structure for user_config_version
-- ----------------------------
DROP TABLE IF EXISTS `user_config_version`;
CREATE TABLE `user_config_version` (
  `config_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `term_type_id` int(11) DEFAULT NULL,
  `cre_time` timestamp NULL DEFAULT NULL,
  `version_num` varchar(20) DEFAULT NULL,
  `is_active` int(11) DEFAULT NULL,
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=200 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_config_version
-- ----------------------------
INSERT INTO `user_config_version` VALUES ('122', '2', '0', '2018-06-27 11:14:56', null, '0');
INSERT INTO `user_config_version` VALUES ('123', '3', '0', '2018-06-27 11:17:43', null, '0');
INSERT INTO `user_config_version` VALUES ('124', '2', '0', '2018-06-27 11:20:07', null, '0');
INSERT INTO `user_config_version` VALUES ('125', '3', '0', '2018-06-27 11:20:23', null, '0');
INSERT INTO `user_config_version` VALUES ('126', '2', '0', '2018-06-27 11:26:27', null, '0');
INSERT INTO `user_config_version` VALUES ('127', '3', '0', '2018-06-27 11:30:02', null, '1');
INSERT INTO `user_config_version` VALUES ('128', '2', '0', '2018-06-27 11:31:27', null, '0');
INSERT INTO `user_config_version` VALUES ('129', '2', '0', '2018-06-27 13:23:27', null, '0');
INSERT INTO `user_config_version` VALUES ('130', '2', '0', '2018-06-27 13:29:10', null, '0');
INSERT INTO `user_config_version` VALUES ('131', '1', '0', '2018-06-27 10:13:52', '11', '0');
INSERT INTO `user_config_version` VALUES ('132', '1', '0', '2018-06-28 10:45:00', '', '0');
INSERT INTO `user_config_version` VALUES ('133', '1', '0', '2018-06-28 10:46:49', '13', '0');
INSERT INTO `user_config_version` VALUES ('134', '1', '0', '2018-06-28 16:33:06', '01', '0');
INSERT INTO `user_config_version` VALUES ('135', '1', '0', '2018-06-28 17:15:22', null, '0');
INSERT INTO `user_config_version` VALUES ('136', '1', '0', '2018-06-28 17:15:27', null, '0');
INSERT INTO `user_config_version` VALUES ('137', '1', '0', '2018-06-28 17:21:38', null, '0');
INSERT INTO `user_config_version` VALUES ('138', '2', '0', '2018-06-28 17:23:18', null, '0');
INSERT INTO `user_config_version` VALUES ('139', '3', '0', '2018-06-28 17:36:44', null, '0');
INSERT INTO `user_config_version` VALUES ('140', '2', '0', '2018-06-29 11:05:04', null, '0');
INSERT INTO `user_config_version` VALUES ('141', '2', '0', '2018-06-29 11:05:15', null, '0');
INSERT INTO `user_config_version` VALUES ('142', '2', '0', '2018-06-29 11:10:44', null, '0');
INSERT INTO `user_config_version` VALUES ('143', '2', '0', '2018-06-29 11:12:30', null, '0');
INSERT INTO `user_config_version` VALUES ('144', '2', '0', '2018-06-29 11:14:06', null, '0');
INSERT INTO `user_config_version` VALUES ('145', '2', '0', '2018-06-27 10:13:52', '1.0', '0');
INSERT INTO `user_config_version` VALUES ('146', '2', '0', '2018-06-29 11:30:42', null, '0');
INSERT INTO `user_config_version` VALUES ('147', '2', '0', '2018-06-29 11:30:52', '', '0');
INSERT INTO `user_config_version` VALUES ('148', '2', '0', '2018-06-29 11:32:58', '1', '0');
INSERT INTO `user_config_version` VALUES ('149', '1', '0', '2018-06-29 14:05:39', '1.0', '0');
INSERT INTO `user_config_version` VALUES ('150', '1', '0', '2018-06-29 14:06:55', '2.0', '0');
INSERT INTO `user_config_version` VALUES ('151', '1', '0', '2018-06-29 14:22:31', '3.0', '0');
INSERT INTO `user_config_version` VALUES ('152', '1', '0', '2018-06-29 14:26:14', '1.0', '0');
INSERT INTO `user_config_version` VALUES ('153', '1', '0', '2018-06-29 14:26:35', '', '0');
INSERT INTO `user_config_version` VALUES ('154', '1', '0', '2018-07-02 09:39:53', '', '0');
INSERT INTO `user_config_version` VALUES ('155', '1', '0', '2018-07-03 16:41:44', null, '0');
INSERT INTO `user_config_version` VALUES ('156', '1', '0', '2018-07-03 17:12:50', null, '0');
INSERT INTO `user_config_version` VALUES ('157', '1', '0', '2018-07-03 17:15:08', null, '0');
INSERT INTO `user_config_version` VALUES ('158', '1', '0', '2018-07-03 17:18:22', null, '0');
INSERT INTO `user_config_version` VALUES ('159', '1', '0', '2018-07-03 17:18:47', null, '0');
INSERT INTO `user_config_version` VALUES ('160', '1', '0', '2018-07-03 17:29:05', null, '0');
INSERT INTO `user_config_version` VALUES ('161', '1', '0', '2018-07-03 17:29:22', null, '0');
INSERT INTO `user_config_version` VALUES ('162', '1', '0', '2018-07-03 17:50:12', null, '0');
INSERT INTO `user_config_version` VALUES ('163', '1', '0', '2018-07-03 17:50:35', null, '0');
INSERT INTO `user_config_version` VALUES ('164', '1', '0', '2018-07-03 17:54:51', null, '0');
INSERT INTO `user_config_version` VALUES ('165', '1', '0', '2018-07-03 17:59:43', null, '0');
INSERT INTO `user_config_version` VALUES ('166', '1', '0', '2018-07-03 18:10:30', null, '0');
INSERT INTO `user_config_version` VALUES ('167', '1', '0', '2018-07-03 18:11:57', null, '0');
INSERT INTO `user_config_version` VALUES ('168', '1', '0', '2018-07-03 18:15:20', '7.03v1.0', '0');
INSERT INTO `user_config_version` VALUES ('169', '1', '0', '2018-07-04 10:50:28', '7.04v1.0', '0');
INSERT INTO `user_config_version` VALUES ('170', '1', '0', '2018-07-04 10:52:57', '7.04v2.0', '0');
INSERT INTO `user_config_version` VALUES ('171', '1', '0', '2018-07-04 11:11:54', null, '0');
INSERT INTO `user_config_version` VALUES ('172', '1', '0', '2018-07-04 11:38:22', 'id125_v1.0', '0');
INSERT INTO `user_config_version` VALUES ('173', '1', '0', '2018-07-04 11:39:24', 'id126_v1.0', '0');
INSERT INTO `user_config_version` VALUES ('174', '1', '0', '2018-07-04 14:37:22', null, '0');
INSERT INTO `user_config_version` VALUES ('175', '1', '0', '2018-07-04 14:39:31', null, '0');
INSERT INTO `user_config_version` VALUES ('179', '1', '0', '2018-07-04 14:55:42', null, '0');
INSERT INTO `user_config_version` VALUES ('180', '1', '0', '2018-07-04 14:55:54', null, '0');
INSERT INTO `user_config_version` VALUES ('188', '2', '0', '2018-07-04 15:45:55', '1.0', '0');
INSERT INTO `user_config_version` VALUES ('189', '2', '0', '2018-07-04 15:46:14', '10.0', '1');
INSERT INTO `user_config_version` VALUES ('190', '2', '0', '2018-07-04 16:10:23', '3.0', '0');
INSERT INTO `user_config_version` VALUES ('191', '2', '0', '2018-07-04 16:41:38', '4.0', '0');
INSERT INTO `user_config_version` VALUES ('192', '1', '0', '2018-07-05 11:31:32', null, '0');
INSERT INTO `user_config_version` VALUES ('193', '1', '0', '2018-07-05 11:31:50', null, '0');
INSERT INTO `user_config_version` VALUES ('194', '1', '0', '2018-07-05 11:34:12', '', '0');
INSERT INTO `user_config_version` VALUES ('195', '1', '0', '2018-07-05 11:34:36', null, '0');
INSERT INTO `user_config_version` VALUES ('196', '1', '0', '2018-07-05 11:37:18', null, '0');
INSERT INTO `user_config_version` VALUES ('197', '1', '0', '2018-08-08 17:30:27', null, '0');
INSERT INTO `user_config_version` VALUES ('198', '1', '0', '2018-08-08 17:30:28', null, '0');
INSERT INTO `user_config_version` VALUES ('199', '1', '0', '2018-08-13 13:32:27', null, '1');

-- ----------------------------
-- Table structure for user_dashboard
-- ----------------------------
DROP TABLE IF EXISTS `user_dashboard`;
CREATE TABLE `user_dashboard` (
  `dashboard_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `dashboard_title` varchar(20) DEFAULT NULL,
  `dashboard_idx` int(11) DEFAULT NULL,
  PRIMARY KEY (`dashboard_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_dashboard
-- ----------------------------
INSERT INTO `user_dashboard` VALUES ('1', '1', '首页', '1');
INSERT INTO `user_dashboard` VALUES ('2', '26', '首页', '1');
INSERT INTO `user_dashboard` VALUES ('3', '1', '首页', '1');
INSERT INTO `user_dashboard` VALUES ('4', '31', '首页', '1');
INSERT INTO `user_dashboard` VALUES ('5', '10', '首页', '1');
INSERT INTO `user_dashboard` VALUES ('6', '34', '首页', '1');
INSERT INTO `user_dashboard` VALUES ('7', '36', '首页', '1');
INSERT INTO `user_dashboard` VALUES ('8', '37', '首页', '1');
INSERT INTO `user_dashboard` VALUES ('9', '40', '首页', '1');
INSERT INTO `user_dashboard` VALUES ('10', '3', '首页', '1');
INSERT INTO `user_dashboard` VALUES ('11', '6', '首页', '1');
INSERT INTO `user_dashboard` VALUES ('12', '4', '首页', '1');
INSERT INTO `user_dashboard` VALUES ('13', '7', '首页', '1');

-- ----------------------------
-- Table structure for user_department
-- ----------------------------
DROP TABLE IF EXISTS `user_department`;
CREATE TABLE `user_department` (
  `dep_id` int(11) NOT NULL AUTO_INCREMENT,
  `super_dep_id` int(11) DEFAULT NULL,
  `dep_name` varchar(80) DEFAULT NULL,
  `dep_level` int(11) NOT NULL,
  `dep_state` int(11) NOT NULL,
  `dep_global_id` varchar(60) DEFAULT NULL,
  `sup_dep_global_id` varchar(60) DEFAULT NULL,
  `cre_time` timestamp NULL DEFAULT NULL,
  `upd_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`dep_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5302 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_department
-- ----------------------------
INSERT INTO `user_department` VALUES ('2', null, '市教委', '1', '0', null, null, null, '2018-07-26 14:42:33');
INSERT INTO `user_department` VALUES ('3', null, '市经济信息化委', '1', '0', null, null, null, null);
INSERT INTO `user_department` VALUES ('4', null, '市交通委', '1', '0', null, null, null, null);
INSERT INTO `user_department` VALUES ('5', null, '市统计局', '1', '1', null, null, null, '2018-05-23 10:28:22');
INSERT INTO `user_department` VALUES ('1100', '1', '市发改委研究室', '2', '1', null, null, null, null);
INSERT INTO `user_department` VALUES ('1200', '1', '市发改委发展规划处', '2', '1', null, null, null, null);
INSERT INTO `user_department` VALUES ('1300', '1', '市发改委国民经济综合处', '2', '1', null, null, null, null);
INSERT INTO `user_department` VALUES ('3100', '3', '北京市信息资源管理中心', '2', '1', null, null, null, null);
INSERT INTO `user_department` VALUES ('3300', '3', '智慧城市建设处（大数据应用处）', '2', '1', null, null, null, null);
INSERT INTO `user_department` VALUES ('3400', '3', '软件与信息服务业处（工业化信息化融合推进处）', '2', '1', null, null, null, null);
INSERT INTO `user_department` VALUES ('4100', '4', '北京市交通信息中心', '2', '1', null, null, null, null);
INSERT INTO `user_department` VALUES ('4200', '4', '北京市交通运行监测调度中心', '2', '1', null, null, null, null);
INSERT INTO `user_department` VALUES ('4300', '4', '北京市轨道交通指挥中心', '2', '1', null, null, null, null);
INSERT INTO `user_department` VALUES ('4400', '4', '北京市交通执法总队', '2', '1', null, null, null, null);
INSERT INTO `user_department` VALUES ('5100', '5', '北京市统计局普查中心', '2', '1', null, null, null, null);
INSERT INTO `user_department` VALUES ('5200', '5', '北京市统计应用研究所', '2', '1', null, null, null, '2018-04-19 16:15:18');
INSERT INTO `user_department` VALUES ('5300', '5', '北京市统计数据管理中心', '2', '1', null, null, null, null);
INSERT INTO `user_department` VALUES ('5301', null, 'dept_name', '1', '1', 'dept_1', 'dept_upcode', '2018-05-09 02:00:05', '2018-05-09 02:00:05');

-- ----------------------------
-- Table structure for user_dep_rela
-- ----------------------------
DROP TABLE IF EXISTS `user_dep_rela`;
CREATE TABLE `user_dep_rela` (
  `rela_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `dep_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`rela_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_dep_rela
-- ----------------------------
INSERT INTO `user_dep_rela` VALUES ('1', '1', '3300');
INSERT INTO `user_dep_rela` VALUES ('2', '2', '3100');
INSERT INTO `user_dep_rela` VALUES ('3', '3', '5300');
INSERT INTO `user_dep_rela` VALUES ('5', '6', '5');
INSERT INTO `user_dep_rela` VALUES ('7', '7', '1100');

-- ----------------------------
-- Table structure for user_job
-- ----------------------------
DROP TABLE IF EXISTS `user_job`;
CREATE TABLE `user_job` (
  `job_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户岗位ID',
  `job_name` varchar(40) DEFAULT NULL COMMENT '用户岗位名称',
  `job_level` int(11) NOT NULL COMMENT '用户岗位级别',
  `cre_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `upd_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_job
-- ----------------------------
INSERT INTO `user_job` VALUES ('1', '市委书记', '1', null, '2018-04-18 14:53:45');
INSERT INTO `user_job` VALUES ('2', '市长', '1', null, null);
INSERT INTO `user_job` VALUES ('3', '副市长', '1', null, null);
INSERT INTO `user_job` VALUES ('4', '局长', '2', null, null);
INSERT INTO `user_job` VALUES ('5', '处长', '3', null, null);
INSERT INTO `user_job` VALUES ('6', '科长', '4', null, null);
INSERT INTO `user_job` VALUES ('16', '科员', '5', '2018-04-19 15:43:01', '2018-04-23 14:15:45');

-- ----------------------------
-- Table structure for user_param
-- ----------------------------
DROP TABLE IF EXISTS `user_param`;
CREATE TABLE `user_param` (
  `param_id` int(11) NOT NULL,
  `param_name` varchar(20) DEFAULT NULL,
  `param_type_id` int(11) DEFAULT NULL,
  `term_type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`param_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_param
-- ----------------------------

-- ----------------------------
-- Table structure for user_param_cd
-- ----------------------------
DROP TABLE IF EXISTS `user_param_cd`;
CREATE TABLE `user_param_cd` (
  `param_id` int(11) NOT NULL,
  `param_cd` int(11) NOT NULL,
  `param_value` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`param_id`,`param_cd`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_param_cd
-- ----------------------------

-- ----------------------------
-- Table structure for user_param_type
-- ----------------------------
DROP TABLE IF EXISTS `user_param_type`;
CREATE TABLE `user_param_type` (
  `param_type_id` int(11) NOT NULL,
  `param_type_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`param_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_param_type
-- ----------------------------

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL,
  `role_type_id` int(11) DEFAULT NULL,
  `role_state` int(11) DEFAULT NULL,
  `cre_time` timestamp NULL DEFAULT NULL,
  `upd_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '系统管理员', '1', '1', null, '2018-07-30 16:40:20');
INSERT INTO `user_role` VALUES ('2', '数据审查员', '2', '1', null, '2018-04-19 16:40:05');
INSERT INTO `user_role` VALUES ('3', '市领导', '3', '1', null, '2018-07-25 16:44:49');
INSERT INTO `user_role` VALUES ('4', '区领导', '3', '1', null, '2018-04-20 11:47:41');
INSERT INTO `user_role` VALUES ('5', '局领导', '3', '1', null, '2018-07-25 16:45:03');
INSERT INTO `user_role` VALUES ('10', '科长', '3', '0', '2018-04-23 10:15:49', '2018-07-31 10:21:05');
INSERT INTO `user_role` VALUES ('16', '普通员工', '3', '1', '2018-04-23 15:35:07', '2018-07-31 16:46:24');
INSERT INTO `user_role` VALUES ('90', '审核系统管理员', '2', '1', '2018-06-08 10:24:07', '2018-07-25 17:04:40');
INSERT INTO `user_role` VALUES ('101', 'aada', null, '1', '2018-07-30 17:01:36', '2018-07-30 17:01:36');
INSERT INTO `user_role` VALUES ('102', 'dd', null, '1', '2018-07-30 17:05:18', '2018-07-30 17:05:18');
INSERT INTO `user_role` VALUES ('104', 'ee', null, '1', '2018-07-30 17:29:47', '2018-07-30 17:30:29');
INSERT INTO `user_role` VALUES ('108', '大数据平台管理员', '2', '1', '2018-07-31 09:37:37', '2018-07-31 16:46:03');
INSERT INTO `user_role` VALUES ('109', '数据分析平台管理员', '2', '1', '2018-07-31 17:08:24', '2018-07-31 17:08:24');

-- ----------------------------
-- Table structure for user_role_rela
-- ----------------------------
DROP TABLE IF EXISTS `user_role_rela`;
CREATE TABLE `user_role_rela` (
  `rel_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '关系ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `role_id` int(11) DEFAULT NULL COMMENT '用户角色ID',
  `cre_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `upd_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`rel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role_rela
-- ----------------------------
INSERT INTO `user_role_rela` VALUES ('1', '1', '1', '2018-04-12 00:00:00', '2018-06-28 11:00:31');
INSERT INTO `user_role_rela` VALUES ('2', '2', '2', '2018-04-12 00:00:00', '2018-07-31 09:32:06');
INSERT INTO `user_role_rela` VALUES ('3', '17', '5', '2018-04-19 15:04:48', null);
INSERT INTO `user_role_rela` VALUES ('4', '18', '1', '2018-04-19 16:42:06', '2018-04-20 11:38:26');
INSERT INTO `user_role_rela` VALUES ('5', '19', '3', '2018-04-19 16:42:44', null);
INSERT INTO `user_role_rela` VALUES ('6', '20', '1', '2018-04-19 18:55:48', null);
INSERT INTO `user_role_rela` VALUES ('7', '21', '1', '2018-04-19 22:05:44', '2018-04-20 13:04:19');
INSERT INTO `user_role_rela` VALUES ('8', '22', '2', '2018-04-20 11:25:53', '2018-04-20 12:50:58');
INSERT INTO `user_role_rela` VALUES ('9', '23', '2', '2018-04-20 12:46:08', null);
INSERT INTO `user_role_rela` VALUES ('11', '3', '5', '2018-04-20 13:05:58', '2018-06-13 16:08:42');
INSERT INTO `user_role_rela` VALUES ('12', '24', '3', '2018-04-20 14:07:32', null);
INSERT INTO `user_role_rela` VALUES ('13', '25', '4', '2018-04-20 14:08:17', null);
INSERT INTO `user_role_rela` VALUES ('15', '30', '3', '2018-04-20 16:29:22', '2018-04-20 16:30:09');
INSERT INTO `user_role_rela` VALUES ('17', '6', '109', '2018-06-28 15:12:05', '2018-07-31 17:09:01');
INSERT INTO `user_role_rela` VALUES ('19', '7', '108', '2018-07-31 09:38:02', '2018-07-31 10:23:23');

-- ----------------------------
-- Table structure for visit_stats
-- ----------------------------
DROP TABLE IF EXISTS `visit_stats`;
CREATE TABLE `visit_stats` (
  `stats_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `visit_obj_id` int(11) DEFAULT NULL,
  `visit_obj_type` int(11) DEFAULT NULL,
  `last_visit_time` timestamp NULL DEFAULT NULL,
  `visit_times` int(11) DEFAULT NULL,
  PRIMARY KEY (`stats_id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of visit_stats
-- ----------------------------
INSERT INTO `visit_stats` VALUES ('22', '1', '190', null, '2018-07-06 16:21:22', '36');
INSERT INTO `visit_stats` VALUES ('23', '1', '189', null, '2018-06-22 13:46:28', '1');
INSERT INTO `visit_stats` VALUES ('24', '1', '191', null, '2018-06-22 13:48:40', '1');
INSERT INTO `visit_stats` VALUES ('25', '1', '192', null, '2018-06-27 14:44:35', '8');
INSERT INTO `visit_stats` VALUES ('26', '1', '194', null, '2018-06-26 13:53:04', '2');
INSERT INTO `visit_stats` VALUES ('27', '1', '195', null, '2018-06-25 16:17:38', '2');
INSERT INTO `visit_stats` VALUES ('28', '1', '196', null, '2018-06-25 14:34:22', '1');
INSERT INTO `visit_stats` VALUES ('29', '1', '197', null, '2018-06-25 15:48:28', '6');
INSERT INTO `visit_stats` VALUES ('30', '1', '198', null, '2018-06-25 14:52:48', '2');
INSERT INTO `visit_stats` VALUES ('31', '1', '0', null, '2018-07-18 14:54:02', '7');
INSERT INTO `visit_stats` VALUES ('32', '1', '199', null, '2018-06-25 15:50:56', '1');
INSERT INTO `visit_stats` VALUES ('33', '1', '200', null, '2018-06-25 15:56:54', '2');
INSERT INTO `visit_stats` VALUES ('34', '1', '201', null, '2018-06-25 16:17:31', '12');
INSERT INTO `visit_stats` VALUES ('35', '1', '202', null, '2018-06-25 16:22:26', '2');
INSERT INTO `visit_stats` VALUES ('36', '1', '203', null, '2018-06-26 16:48:47', '2');
INSERT INTO `visit_stats` VALUES ('37', '2', '0', null, '2018-06-27 14:03:19', '2');
INSERT INTO `visit_stats` VALUES ('38', '2', '192', null, '2018-06-27 14:02:45', '1');
INSERT INTO `visit_stats` VALUES ('39', '2', '204', null, '2018-06-27 14:05:47', '2');
INSERT INTO `visit_stats` VALUES ('40', '2', '190', null, '2018-06-27 14:06:02', '1');
INSERT INTO `visit_stats` VALUES ('41', '2', '194', null, '2018-06-27 14:06:08', '1');
INSERT INTO `visit_stats` VALUES ('42', '1', '205', null, '2018-06-27 14:41:07', '1');
INSERT INTO `visit_stats` VALUES ('43', '1', '206', null, '2018-07-12 16:42:12', '2');
INSERT INTO `visit_stats` VALUES ('44', '1', '219', null, '2018-07-26 11:00:16', '1');
