/*
Navicat MySQL Data Transfer

Source Server         : 
Source Server Version : 50626
Source Host           : 
Source Database       : 

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2020-05-14 21:30:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `quartz_job`
-- ----------------------------
DROP TABLE IF EXISTS `quartz_job`;
CREATE TABLE `quartz_job` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `bean_name` varchar(255) DEFAULT NULL COMMENT 'Spring Bean名称',
  `cron_expression` varchar(255) DEFAULT NULL COMMENT 'cron 表达式',
  `is_pause` bit(1) DEFAULT NULL COMMENT '状态：1暂停、0启用',
  `job_name` varchar(255) DEFAULT NULL COMMENT '任务名称',
  `method_name` varchar(255) DEFAULT NULL COMMENT '方法名称',
  `params` varchar(255) DEFAULT NULL COMMENT '参数',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `status` int(1) DEFAULT NULL COMMENT '状态1正常0已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='定时任务';

-- ----------------------------
-- Records of quartz_job
-- ----------------------------
INSERT INTO `quartz_job` VALUES ('1', 'taskService', '0/10 * * * * ?', '', '推广订单周结算', 'developerPayment', '2', '每日0点创建新的访客记录', '2019-01-08 14:53:31', '1');
INSERT INTO `quartz_job` VALUES ('2', 'testTask', '0 0/20 * * * ?', '', '测试1', 'run1', 'test', '带参测试，多参使用json', '2019-08-22 14:08:29', '1');
INSERT INTO `quartz_job` VALUES ('3', 'testTask', '0 0/20 * * * ?', '', '测试', 'run', '', '不带参测试', '2019-09-26 16:44:39', '1');
INSERT INTO `quartz_job` VALUES ('4', 'taskService', '0/10 * * * * ?', '', 'taskService12', 'developerPayment', '', 'developerPayment2', '2020-05-14 13:18:17', '0');
INSERT INTO `quartz_job` VALUES ('5', 'taskService', '0/10 * * * * ?', '', 'developerPayment', 'developerPayment', '', '', '2020-05-14 16:40:31', '1');
INSERT INTO `quartz_job` VALUES ('6', '22', '0/10 * * * * ?', '', '22', '22', '', '', '2020-05-14 16:42:51', '0');

-- ----------------------------
-- Table structure for `quartz_log`
-- ----------------------------
DROP TABLE IF EXISTS `quartz_log`;
CREATE TABLE `quartz_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bean_name` varchar(255) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `cron_expression` varchar(255) DEFAULT NULL,
  `exception_detail` text,
  `is_success` bit(1) DEFAULT NULL,
  `job_name` varchar(255) DEFAULT NULL,
  `method_name` varchar(255) DEFAULT NULL,
  `params` varchar(255) DEFAULT NULL,
  `time` bigint(20) DEFAULT NULL COMMENT '执行时长毫秒',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='定时任务日志';

