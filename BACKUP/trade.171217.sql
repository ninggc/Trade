/*
Navicat MySQL Data Transfer

Source Server         : ning
Source Server Version : 50173
Source Host           : 123.207.244.139:3306
Source Database       : trade

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-12-17 16:19:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for auth_group
-- ----------------------------
DROP TABLE IF EXISTS `auth_group`;
CREATE TABLE `auth_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(80) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_group
-- ----------------------------

-- ----------------------------
-- Table structure for auth_group_permissions
-- ----------------------------
DROP TABLE IF EXISTS `auth_group_permissions`;
CREATE TABLE `auth_group_permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_group_permissions_group_id_permission_id_0cd325b0_uniq` (`group_id`,`permission_id`),
  KEY `auth_group_permissions_group_id_b120cbf9` (`group_id`),
  KEY `auth_group_permissions_permission_id_84c5c92e` (`permission_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_group_permissions
-- ----------------------------

-- ----------------------------
-- Table structure for auth_permission
-- ----------------------------
DROP TABLE IF EXISTS `auth_permission`;
CREATE TABLE `auth_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `content_type_id` int(11) NOT NULL,
  `codename` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_permission_content_type_id_codename_01ab375a_uniq` (`content_type_id`,`codename`),
  KEY `auth_permission_content_type_id_2f476e4b` (`content_type_id`)
) ENGINE=MyISAM AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_permission
-- ----------------------------
INSERT INTO `auth_permission` VALUES ('1', 'Can add log entry', '1', 'add_logentry');
INSERT INTO `auth_permission` VALUES ('2', 'Can change log entry', '1', 'change_logentry');
INSERT INTO `auth_permission` VALUES ('3', 'Can delete log entry', '1', 'delete_logentry');
INSERT INTO `auth_permission` VALUES ('4', 'Can add group', '2', 'add_group');
INSERT INTO `auth_permission` VALUES ('5', 'Can change group', '2', 'change_group');
INSERT INTO `auth_permission` VALUES ('6', 'Can delete group', '2', 'delete_group');
INSERT INTO `auth_permission` VALUES ('7', 'Can add permission', '3', 'add_permission');
INSERT INTO `auth_permission` VALUES ('8', 'Can change permission', '3', 'change_permission');
INSERT INTO `auth_permission` VALUES ('9', 'Can delete permission', '3', 'delete_permission');
INSERT INTO `auth_permission` VALUES ('10', 'Can add user', '4', 'add_user');
INSERT INTO `auth_permission` VALUES ('11', 'Can change user', '4', 'change_user');
INSERT INTO `auth_permission` VALUES ('12', 'Can delete user', '4', 'delete_user');
INSERT INTO `auth_permission` VALUES ('13', 'Can add content type', '5', 'add_contenttype');
INSERT INTO `auth_permission` VALUES ('14', 'Can change content type', '5', 'change_contenttype');
INSERT INTO `auth_permission` VALUES ('15', 'Can delete content type', '5', 'delete_contenttype');
INSERT INTO `auth_permission` VALUES ('16', 'Can add session', '6', 'add_session');
INSERT INTO `auth_permission` VALUES ('17', 'Can change session', '6', 'change_session');
INSERT INTO `auth_permission` VALUES ('18', 'Can delete session', '6', 'delete_session');
INSERT INTO `auth_permission` VALUES ('19', 'Can add commodity', '7', 'add_commodity');
INSERT INTO `auth_permission` VALUES ('20', 'Can change commodity', '7', 'change_commodity');
INSERT INTO `auth_permission` VALUES ('21', 'Can delete commodity', '7', 'delete_commodity');
INSERT INTO `auth_permission` VALUES ('22', 'Can add campus', '8', 'add_campus');
INSERT INTO `auth_permission` VALUES ('23', 'Can change campus', '8', 'change_campus');
INSERT INTO `auth_permission` VALUES ('24', 'Can delete campus', '8', 'delete_campus');
INSERT INTO `auth_permission` VALUES ('25', 'Can add delegation', '9', 'add_delegation');
INSERT INTO `auth_permission` VALUES ('26', 'Can change delegation', '9', 'change_delegation');
INSERT INTO `auth_permission` VALUES ('27', 'Can delete delegation', '9', 'delete_delegation');
INSERT INTO `auth_permission` VALUES ('28', 'Can add security', '10', 'add_security');
INSERT INTO `auth_permission` VALUES ('29', 'Can change security', '10', 'change_security');
INSERT INTO `auth_permission` VALUES ('30', 'Can delete security', '10', 'delete_security');
INSERT INTO `auth_permission` VALUES ('31', 'Can add collection', '11', 'add_collection');
INSERT INTO `auth_permission` VALUES ('32', 'Can change collection', '11', 'change_collection');
INSERT INTO `auth_permission` VALUES ('33', 'Can delete collection', '11', 'delete_collection');
INSERT INTO `auth_permission` VALUES ('34', 'Can add indent', '12', 'add_indent');
INSERT INTO `auth_permission` VALUES ('35', 'Can change indent', '12', 'change_indent');
INSERT INTO `auth_permission` VALUES ('36', 'Can delete indent', '12', 'delete_indent');
INSERT INTO `auth_permission` VALUES ('37', 'Can add delegation_order', '13', 'add_delegation_order');
INSERT INTO `auth_permission` VALUES ('38', 'Can change delegation_order', '13', 'change_delegation_order');
INSERT INTO `auth_permission` VALUES ('39', 'Can delete delegation_order', '13', 'delete_delegation_order');
INSERT INTO `auth_permission` VALUES ('40', 'Can add city', '14', 'add_city');
INSERT INTO `auth_permission` VALUES ('41', 'Can change city', '14', 'change_city');
INSERT INTO `auth_permission` VALUES ('42', 'Can delete city', '14', 'delete_city');
INSERT INTO `auth_permission` VALUES ('43', 'Can add user', '15', 'add_user');
INSERT INTO `auth_permission` VALUES ('44', 'Can change user', '15', 'change_user');
INSERT INTO `auth_permission` VALUES ('45', 'Can delete user', '15', 'delete_user');
INSERT INTO `auth_permission` VALUES ('46', 'Can add location', '16', 'add_location');
INSERT INTO `auth_permission` VALUES ('47', 'Can change location', '16', 'change_location');
INSERT INTO `auth_permission` VALUES ('48', 'Can delete location', '16', 'delete_location');
INSERT INTO `auth_permission` VALUES ('49', 'Can add comment', '17', 'add_comment');
INSERT INTO `auth_permission` VALUES ('50', 'Can change comment', '17', 'change_comment');
INSERT INTO `auth_permission` VALUES ('51', 'Can delete comment', '17', 'delete_comment');
INSERT INTO `auth_permission` VALUES ('52', 'Can add sortbook', '18', 'add_sortbook');
INSERT INTO `auth_permission` VALUES ('53', 'Can change sortbook', '18', 'change_sortbook');
INSERT INTO `auth_permission` VALUES ('54', 'Can delete sortbook', '18', 'delete_sortbook');
INSERT INTO `auth_permission` VALUES ('55', 'Can add school', '19', 'add_school');
INSERT INTO `auth_permission` VALUES ('56', 'Can change school', '19', 'change_school');
INSERT INTO `auth_permission` VALUES ('57', 'Can delete school', '19', 'delete_school');

-- ----------------------------
-- Table structure for auth_user
-- ----------------------------
DROP TABLE IF EXISTS `auth_user`;
CREATE TABLE `auth_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(128) NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `is_superuser` tinyint(1) NOT NULL,
  `username` varchar(150) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `email` varchar(254) NOT NULL,
  `is_staff` tinyint(1) NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `date_joined` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_user
-- ----------------------------

-- ----------------------------
-- Table structure for auth_user_groups
-- ----------------------------
DROP TABLE IF EXISTS `auth_user_groups`;
CREATE TABLE `auth_user_groups` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_user_groups_user_id_group_id_94350c0c_uniq` (`user_id`,`group_id`),
  KEY `auth_user_groups_user_id_6a12ed8b` (`user_id`),
  KEY `auth_user_groups_group_id_97559544` (`group_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_user_groups
-- ----------------------------

-- ----------------------------
-- Table structure for auth_user_user_permissions
-- ----------------------------
DROP TABLE IF EXISTS `auth_user_user_permissions`;
CREATE TABLE `auth_user_user_permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_user_user_permissions_user_id_permission_id_14a6b632_uniq` (`user_id`,`permission_id`),
  KEY `auth_user_user_permissions_user_id_a95ead1b` (`user_id`),
  KEY `auth_user_user_permissions_permission_id_1fbb5f2c` (`permission_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_user_user_permissions
-- ----------------------------

-- ----------------------------
-- Table structure for django_admin_log
-- ----------------------------
DROP TABLE IF EXISTS `django_admin_log`;
CREATE TABLE `django_admin_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `action_time` datetime NOT NULL,
  `object_id` longtext,
  `object_repr` varchar(200) NOT NULL,
  `action_flag` smallint(5) unsigned NOT NULL,
  `change_message` longtext NOT NULL,
  `content_type_id` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `django_admin_log_content_type_id_c4bce8eb` (`content_type_id`),
  KEY `django_admin_log_user_id_c564eba6` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of django_admin_log
-- ----------------------------

-- ----------------------------
-- Table structure for django_content_type
-- ----------------------------
DROP TABLE IF EXISTS `django_content_type`;
CREATE TABLE `django_content_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_label` varchar(100) NOT NULL,
  `model` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `django_content_type_app_label_model_76bd3d3b_uniq` (`app_label`,`model`)
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of django_content_type
-- ----------------------------
INSERT INTO `django_content_type` VALUES ('1', 'admin', 'logentry');
INSERT INTO `django_content_type` VALUES ('2', 'auth', 'group');
INSERT INTO `django_content_type` VALUES ('3', 'auth', 'permission');
INSERT INTO `django_content_type` VALUES ('4', 'auth', 'user');
INSERT INTO `django_content_type` VALUES ('5', 'contenttypes', 'contenttype');
INSERT INTO `django_content_type` VALUES ('6', 'sessions', 'session');
INSERT INTO `django_content_type` VALUES ('7', 'dms', 'commodity');
INSERT INTO `django_content_type` VALUES ('8', 'dms', 'campus');
INSERT INTO `django_content_type` VALUES ('9', 'dms', 'delegation');
INSERT INTO `django_content_type` VALUES ('10', 'dms', 'security');
INSERT INTO `django_content_type` VALUES ('11', 'dms', 'collection');
INSERT INTO `django_content_type` VALUES ('12', 'dms', 'indent');
INSERT INTO `django_content_type` VALUES ('13', 'dms', 'delegation_order');
INSERT INTO `django_content_type` VALUES ('14', 'dms', 'city');
INSERT INTO `django_content_type` VALUES ('15', 'dms', 'user');
INSERT INTO `django_content_type` VALUES ('16', 'dms', 'location');
INSERT INTO `django_content_type` VALUES ('17', 'dms', 'comment');
INSERT INTO `django_content_type` VALUES ('18', 'dms', 'sortbook');
INSERT INTO `django_content_type` VALUES ('19', 'dms', 'school');

-- ----------------------------
-- Table structure for django_migrations
-- ----------------------------
DROP TABLE IF EXISTS `django_migrations`;
CREATE TABLE `django_migrations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `applied` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of django_migrations
-- ----------------------------
INSERT INTO `django_migrations` VALUES ('1', 'contenttypes', '0001_initial', '2017-10-15 10:38:45');
INSERT INTO `django_migrations` VALUES ('2', 'auth', '0001_initial', '2017-10-15 10:38:49');
INSERT INTO `django_migrations` VALUES ('3', 'admin', '0001_initial', '2017-10-15 10:38:50');
INSERT INTO `django_migrations` VALUES ('4', 'admin', '0002_logentry_remove_auto_add', '2017-10-15 10:38:50');
INSERT INTO `django_migrations` VALUES ('5', 'contenttypes', '0002_remove_content_type_name', '2017-10-15 10:38:51');
INSERT INTO `django_migrations` VALUES ('6', 'auth', '0002_alter_permission_name_max_length', '2017-10-15 10:38:52');
INSERT INTO `django_migrations` VALUES ('7', 'auth', '0003_alter_user_email_max_length', '2017-10-15 10:38:52');
INSERT INTO `django_migrations` VALUES ('8', 'auth', '0004_alter_user_username_opts', '2017-10-15 10:38:52');
INSERT INTO `django_migrations` VALUES ('9', 'auth', '0005_alter_user_last_login_null', '2017-10-15 10:38:53');
INSERT INTO `django_migrations` VALUES ('10', 'auth', '0006_require_contenttypes_0002', '2017-10-15 10:38:53');
INSERT INTO `django_migrations` VALUES ('11', 'auth', '0007_alter_validators_add_error_messages', '2017-10-15 10:38:53');
INSERT INTO `django_migrations` VALUES ('12', 'auth', '0008_alter_user_username_max_length', '2017-10-15 10:38:54');
INSERT INTO `django_migrations` VALUES ('20', 'dms', '0001_initial', '2017-12-17 08:19:49');
INSERT INTO `django_migrations` VALUES ('18', 'sessions', '0001_initial', '2017-12-10 01:59:04');

-- ----------------------------
-- Table structure for django_session
-- ----------------------------
DROP TABLE IF EXISTS `django_session`;
CREATE TABLE `django_session` (
  `session_key` varchar(40) NOT NULL,
  `session_data` longtext NOT NULL,
  `expire_date` datetime NOT NULL,
  PRIMARY KEY (`session_key`),
  KEY `django_session_expire_date_a5c62663` (`expire_date`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of django_session
-- ----------------------------
INSERT INTO `django_session` VALUES ('howdpv37vfvyd37yndmc2zirn3jzba5o', 'OTAwODZkODczMjQyZGEwNWUzYWYzNDU4YzFhZDM2MjBiMzQ0ODI1YTp7InVzZXJuYW1lIjoidG9vdCJ9', '2017-12-24 02:52:01');
INSERT INTO `django_session` VALUES ('dg2trr1gaki5jj1cp5bdjkqxlntrkjw1', 'OTAwODZkODczMjQyZGEwNWUzYWYzNDU4YzFhZDM2MjBiMzQ0ODI1YTp7InVzZXJuYW1lIjoidG9vdCJ9', '2017-12-24 03:33:12');
INSERT INTO `django_session` VALUES ('hvlaqoo2pbkvku9kjregr0qfgnkq6dj8', 'OTAwODZkODczMjQyZGEwNWUzYWYzNDU4YzFhZDM2MjBiMzQ0ODI1YTp7InVzZXJuYW1lIjoidG9vdCJ9', '2017-12-24 03:33:30');
INSERT INTO `django_session` VALUES ('0e39lr94h6g0rt74rlxyy7da2puxds2b', 'NDMyMTJlYTNhMDMxNmNkNGI5OTYwZmMwYTcwNzkzMmQ1ZDgzN2RkNzp7InVzZXJuYW1lIjoibmluZyJ9', '2017-12-24 04:08:42');
INSERT INTO `django_session` VALUES ('tus7uc09epktdogwktbnwc6u3zr0odep', 'NDMyMTJlYTNhMDMxNmNkNGI5OTYwZmMwYTcwNzkzMmQ1ZDgzN2RkNzp7InVzZXJuYW1lIjoibmluZyJ9', '2017-12-31 02:33:57');

-- ----------------------------
-- Table structure for dms_campus
-- ----------------------------
DROP TABLE IF EXISTS `dms_campus`;
CREATE TABLE `dms_campus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dms_campus
-- ----------------------------
INSERT INTO `dms_campus` VALUES ('1', 'default');

-- ----------------------------
-- Table structure for dms_city
-- ----------------------------
DROP TABLE IF EXISTS `dms_city`;
CREATE TABLE `dms_city` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mail` varchar(20) NOT NULL,
  `province` varchar(20) NOT NULL,
  `country` varchar(20) NOT NULL,
  `cityname` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dms_city
-- ----------------------------
INSERT INTO `dms_city` VALUES ('1', '', 'default', 'default', 'default');

-- ----------------------------
-- Table structure for dms_collection
-- ----------------------------
DROP TABLE IF EXISTS `dms_collection`;
CREATE TABLE `dms_collection` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `commodity_id_id` int(11) NOT NULL,
  `user_id_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `dms_collection_commodity_id_id_31248b19` (`commodity_id_id`),
  KEY `dms_collection_user_id_id_47a68e05` (`user_id_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dms_collection
-- ----------------------------

-- ----------------------------
-- Table structure for dms_comment
-- ----------------------------
DROP TABLE IF EXISTS `dms_comment`;
CREATE TABLE `dms_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(45) NOT NULL,
  `commituser_id` int(11) NOT NULL,
  `commodityid_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `dms_comment_commituser_id_03dad467` (`commituser_id`),
  KEY `dms_comment_commodityid_id_4cf4e0e1` (`commodityid_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dms_comment
-- ----------------------------

-- ----------------------------
-- Table structure for dms_commodity
-- ----------------------------
DROP TABLE IF EXISTS `dms_commodity`;
CREATE TABLE `dms_commodity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `sort` int(11) NOT NULL,
  `price` int(10) unsigned NOT NULL,
  `note` varchar(45) NOT NULL,
  `img1` varchar(100) NOT NULL,
  `img2` varchar(100) NOT NULL,
  `img3` varchar(100) NOT NULL,
  `location_id_id` int(11) NOT NULL,
  `schoolid_id` int(11) NOT NULL,
  `user_id_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `dms_commodity_location_id_id_92a8de7f` (`location_id_id`),
  KEY `dms_commodity_schoolid_id_9c979290` (`schoolid_id`),
  KEY `dms_commodity_user_id_id_440e8819` (`user_id_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dms_commodity
-- ----------------------------

-- ----------------------------
-- Table structure for dms_delegation
-- ----------------------------
DROP TABLE IF EXISTS `dms_delegation`;
CREATE TABLE `dms_delegation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `description` varchar(45) NOT NULL,
  `datetime` time NOT NULL,
  `endtime` time NOT NULL,
  `img1` varchar(100) NOT NULL,
  `img2` varchar(100) NOT NULL,
  `img3` varchar(100) NOT NULL,
  `publisher_id` int(11) NOT NULL,
  `schoolid_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `dms_delegation_publisher_id_9939e4bf` (`publisher_id`),
  KEY `dms_delegation_schoolid_id_54853500` (`schoolid_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dms_delegation
-- ----------------------------

-- ----------------------------
-- Table structure for dms_delegation_order
-- ----------------------------
DROP TABLE IF EXISTS `dms_delegation_order`;
CREATE TABLE `dms_delegation_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datetime` time NOT NULL,
  `endtime` time NOT NULL,
  `remask` varchar(45) NOT NULL,
  `delegation_id_id` int(11) NOT NULL,
  `purchaser_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `dms_delegation_order_delegation_id_id_71a680da` (`delegation_id_id`),
  KEY `dms_delegation_order_purchaser_id_8123d7b4` (`purchaser_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dms_delegation_order
-- ----------------------------

-- ----------------------------
-- Table structure for dms_indent
-- ----------------------------
DROP TABLE IF EXISTS `dms_indent`;
CREATE TABLE `dms_indent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datetime` date NOT NULL,
  `remask` varchar(45) NOT NULL,
  `commodity_id_id` int(11) NOT NULL,
  `location_id_id` int(11) NOT NULL,
  `purchase_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `purchase_id` (`purchase_id`),
  KEY `dms_indent_commodity_id_id_60a93898` (`commodity_id_id`),
  KEY `dms_indent_location_id_id_ca1b80f2` (`location_id_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dms_indent
-- ----------------------------

-- ----------------------------
-- Table structure for dms_location
-- ----------------------------
DROP TABLE IF EXISTS `dms_location`;
CREATE TABLE `dms_location` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `extra` varchar(20) NOT NULL,
  `cityid_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `dms_location_cityid_id_604e74ea` (`cityid_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dms_location
-- ----------------------------
INSERT INTO `dms_location` VALUES ('1', 'default', '1');

-- ----------------------------
-- Table structure for dms_school
-- ----------------------------
DROP TABLE IF EXISTS `dms_school`;
CREATE TABLE `dms_school` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `usernum` int(11) NOT NULL,
  `setuptime` time NOT NULL,
  `indentnum` int(11) NOT NULL,
  `city_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `dms_school_city_id_aaed5792` (`city_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dms_school
-- ----------------------------

-- ----------------------------
-- Table structure for dms_security
-- ----------------------------
DROP TABLE IF EXISTS `dms_security`;
CREATE TABLE `dms_security` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(15) NOT NULL,
  `email` varchar(45) NOT NULL,
  `tel` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dms_security
-- ----------------------------

-- ----------------------------
-- Table structure for dms_sortbook
-- ----------------------------
DROP TABLE IF EXISTS `dms_sortbook`;
CREATE TABLE `dms_sortbook` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `grade` int(11) NOT NULL,
  `shcool` varchar(45) NOT NULL,
  `major` varchar(20) NOT NULL,
  `commodity_id_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `dms_sortbook_commodity_id_id_6d811a07` (`commodity_id_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dms_sortbook
-- ----------------------------

-- ----------------------------
-- Table structure for dms_user
-- ----------------------------
DROP TABLE IF EXISTS `dms_user`;
CREATE TABLE `dms_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `gender` varchar(1) NOT NULL,
  `age` int(11) NOT NULL,
  `introduce` varchar(45) NOT NULL,
  `unique` varchar(45) NOT NULL,
  `portrait` varchar(100) NOT NULL,
  `addressid_id` int(11) NOT NULL,
  `campus_id_id` int(11) NOT NULL,
  `locationid_id` int(11) NOT NULL,
  `schoolid_id` int(11) NOT NULL,
  `security_id_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `dms_user_addressid_id_213fe19f` (`addressid_id`),
  KEY `dms_user_campus_id_id_635adbdb` (`campus_id_id`),
  KEY `dms_user_locationid_id_40e519b6` (`locationid_id`),
  KEY `dms_user_schoolid_id_0ffcb900` (`schoolid_id`),
  KEY `dms_user_security_id_id_83e6eeae` (`security_id_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dms_user
-- ----------------------------
