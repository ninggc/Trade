-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 123.207.244.139    Database: trade
-- ------------------------------------------------------
-- Server version	5.1.73-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `auth_group`
--

DROP TABLE IF EXISTS `auth_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(80) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_group`
--

LOCK TABLES `auth_group` WRITE;
/*!40000 ALTER TABLE `auth_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_group_permissions`
--

DROP TABLE IF EXISTS `auth_group_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_group_permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_group_permissions_group_id_permission_id_0cd325b0_uniq` (`group_id`,`permission_id`),
  KEY `auth_group_permissions_group_id_b120cbf9` (`group_id`),
  KEY `auth_group_permissions_permission_id_84c5c92e` (`permission_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_group_permissions`
--

LOCK TABLES `auth_group_permissions` WRITE;
/*!40000 ALTER TABLE `auth_group_permissions` DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_group_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_permission`
--

DROP TABLE IF EXISTS `auth_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `content_type_id` int(11) NOT NULL,
  `codename` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_permission_content_type_id_codename_01ab375a_uniq` (`content_type_id`,`codename`),
  KEY `auth_permission_content_type_id_2f476e4b` (`content_type_id`)
) ENGINE=MyISAM AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_permission`
--

LOCK TABLES `auth_permission` WRITE;
/*!40000 ALTER TABLE `auth_permission` DISABLE KEYS */;
INSERT INTO `auth_permission` (`id`, `name`, `content_type_id`, `codename`) VALUES (1,'Can add log entry',1,'add_logentry'),(2,'Can change log entry',1,'change_logentry'),(3,'Can delete log entry',1,'delete_logentry'),(4,'Can add group',2,'add_group'),(5,'Can change group',2,'change_group'),(6,'Can delete group',2,'delete_group'),(7,'Can add permission',3,'add_permission'),(8,'Can change permission',3,'change_permission'),(9,'Can delete permission',3,'delete_permission'),(10,'Can add user',4,'add_user'),(11,'Can change user',4,'change_user'),(12,'Can delete user',4,'delete_user'),(13,'Can add content type',5,'add_contenttype'),(14,'Can change content type',5,'change_contenttype'),(15,'Can delete content type',5,'delete_contenttype'),(16,'Can add session',6,'add_session'),(17,'Can change session',6,'change_session'),(18,'Can delete session',6,'delete_session'),(19,'Can add commodity',7,'add_commodity'),(20,'Can change commodity',7,'change_commodity'),(21,'Can delete commodity',7,'delete_commodity'),(22,'Can add campus',8,'add_campus'),(23,'Can change campus',8,'change_campus'),(24,'Can delete campus',8,'delete_campus'),(25,'Can add delegation',9,'add_delegation'),(26,'Can change delegation',9,'change_delegation'),(27,'Can delete delegation',9,'delete_delegation'),(28,'Can add security',10,'add_security'),(29,'Can change security',10,'change_security'),(30,'Can delete security',10,'delete_security'),(31,'Can add collection',11,'add_collection'),(32,'Can change collection',11,'change_collection'),(33,'Can delete collection',11,'delete_collection'),(34,'Can add school',12,'add_school'),(35,'Can change school',12,'change_school'),(36,'Can delete school',12,'delete_school'),(37,'Can add indent',13,'add_indent'),(38,'Can change indent',13,'change_indent'),(39,'Can delete indent',13,'delete_indent'),(40,'Can add comment',14,'add_comment'),(41,'Can change comment',14,'change_comment'),(42,'Can delete comment',14,'delete_comment'),(43,'Can add delegation_order',15,'add_delegation_order'),(44,'Can change delegation_order',15,'change_delegation_order'),(45,'Can delete delegation_order',15,'delete_delegation_order'),(46,'Can add city',16,'add_city'),(47,'Can change city',16,'change_city'),(48,'Can delete city',16,'delete_city'),(49,'Can add sortbook',17,'add_sortbook'),(50,'Can change sortbook',17,'change_sortbook'),(51,'Can delete sortbook',17,'delete_sortbook'),(52,'Can add user',18,'add_user'),(53,'Can change user',18,'change_user'),(54,'Can delete user',18,'delete_user'),(55,'Can add location',19,'add_location'),(56,'Can change location',19,'change_location'),(57,'Can delete location',19,'delete_location');
/*!40000 ALTER TABLE `auth_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_user`
--

DROP TABLE IF EXISTS `auth_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_user`
--

LOCK TABLES `auth_user` WRITE;
/*!40000 ALTER TABLE `auth_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_user_groups`
--

DROP TABLE IF EXISTS `auth_user_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_user_groups` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_user_groups_user_id_group_id_94350c0c_uniq` (`user_id`,`group_id`),
  KEY `auth_user_groups_user_id_6a12ed8b` (`user_id`),
  KEY `auth_user_groups_group_id_97559544` (`group_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_user_groups`
--

LOCK TABLES `auth_user_groups` WRITE;
/*!40000 ALTER TABLE `auth_user_groups` DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_user_groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_user_user_permissions`
--

DROP TABLE IF EXISTS `auth_user_user_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_user_user_permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_user_user_permissions_user_id_permission_id_14a6b632_uniq` (`user_id`,`permission_id`),
  KEY `auth_user_user_permissions_user_id_a95ead1b` (`user_id`),
  KEY `auth_user_user_permissions_permission_id_1fbb5f2c` (`permission_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_user_user_permissions`
--

LOCK TABLES `auth_user_user_permissions` WRITE;
/*!40000 ALTER TABLE `auth_user_user_permissions` DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_user_user_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `django_admin_log`
--

DROP TABLE IF EXISTS `django_admin_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `django_admin_log`
--

LOCK TABLES `django_admin_log` WRITE;
/*!40000 ALTER TABLE `django_admin_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `django_admin_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `django_content_type`
--

DROP TABLE IF EXISTS `django_content_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `django_content_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_label` varchar(100) NOT NULL,
  `model` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `django_content_type_app_label_model_76bd3d3b_uniq` (`app_label`,`model`)
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `django_content_type`
--

LOCK TABLES `django_content_type` WRITE;
/*!40000 ALTER TABLE `django_content_type` DISABLE KEYS */;
INSERT INTO `django_content_type` (`id`, `app_label`, `model`) VALUES (1,'admin','logentry'),(2,'auth','group'),(3,'auth','permission'),(4,'auth','user'),(5,'contenttypes','contenttype'),(6,'sessions','session'),(7,'dms','commodity'),(8,'dms','campus'),(9,'dms','delegation'),(10,'dms','security'),(11,'dms','collection'),(12,'dms','school'),(13,'dms','indent'),(14,'dms','comment'),(15,'dms','delegation_order'),(16,'dms','city'),(17,'dms','sortbook'),(18,'dms','user'),(19,'dms','location');
/*!40000 ALTER TABLE `django_content_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `django_migrations`
--

DROP TABLE IF EXISTS `django_migrations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `django_migrations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `applied` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `django_migrations`
--

LOCK TABLES `django_migrations` WRITE;
/*!40000 ALTER TABLE `django_migrations` DISABLE KEYS */;
INSERT INTO `django_migrations` (`id`, `app`, `name`, `applied`) VALUES (1,'contenttypes','0001_initial','2017-12-19 15:06:56'),(2,'auth','0001_initial','2017-12-19 15:07:01'),(3,'admin','0001_initial','2017-12-19 15:07:02'),(4,'admin','0002_logentry_remove_auto_add','2017-12-19 15:07:02'),(5,'contenttypes','0002_remove_content_type_name','2017-12-19 15:07:03'),(6,'auth','0002_alter_permission_name_max_length','2017-12-19 15:07:03'),(7,'auth','0003_alter_user_email_max_length','2017-12-19 15:07:04'),(8,'auth','0004_alter_user_username_opts','2017-12-19 15:07:04'),(9,'auth','0005_alter_user_last_login_null','2017-12-19 15:07:04'),(10,'auth','0006_require_contenttypes_0002','2017-12-19 15:07:05'),(11,'auth','0007_alter_validators_add_error_messages','2017-12-19 15:07:05'),(12,'auth','0008_alter_user_username_max_length','2017-12-19 15:07:06'),(13,'dms','0001_initial','2017-12-19 15:07:17'),(14,'sessions','0001_initial','2017-12-19 15:07:18');
/*!40000 ALTER TABLE `django_migrations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `django_session`
--

DROP TABLE IF EXISTS `django_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `django_session` (
  `session_key` varchar(40) NOT NULL,
  `session_data` longtext NOT NULL,
  `expire_date` datetime NOT NULL,
  PRIMARY KEY (`session_key`),
  KEY `django_session_expire_date_a5c62663` (`expire_date`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `django_session`
--

LOCK TABLES `django_session` WRITE;
/*!40000 ALTER TABLE `django_session` DISABLE KEYS */;
INSERT INTO `django_session` (`session_key`, `session_data`, `expire_date`) VALUES ('q6i8at5tihfwp9wt1s21fqwt2wa9ilok','NDMyMTJlYTNhMDMxNmNkNGI5OTYwZmMwYTcwNzkzMmQ1ZDgzN2RkNzp7InVzZXJuYW1lIjoibmluZyJ9','2018-01-03 09:47:44'),('lkfb8hahlsl8ovvlfym650zip560unvo','NDMyMTJlYTNhMDMxNmNkNGI5OTYwZmMwYTcwNzkzMmQ1ZDgzN2RkNzp7InVzZXJuYW1lIjoibmluZyJ9','2018-01-03 09:48:03'),('wwnk78kpbai1mdpvk1kzp3ry7tqle3kt','NDMyMTJlYTNhMDMxNmNkNGI5OTYwZmMwYTcwNzkzMmQ1ZDgzN2RkNzp7InVzZXJuYW1lIjoibmluZyJ9','2018-01-03 09:49:26'),('0enbod6qzsjsxeoaw82w1t6jtt5h7xnb','NDMyMTJlYTNhMDMxNmNkNGI5OTYwZmMwYTcwNzkzMmQ1ZDgzN2RkNzp7InVzZXJuYW1lIjoibmluZyJ9','2018-01-03 10:16:02'),('j7t84gyelfsayn9a1quxrulkyupw560r','NDMyMTJlYTNhMDMxNmNkNGI5OTYwZmMwYTcwNzkzMmQ1ZDgzN2RkNzp7InVzZXJuYW1lIjoibmluZyJ9','2018-01-07 01:35:21'),('3zscmvocclq5uxn3ckhzf5zs9fbetj70','NDMyMTJlYTNhMDMxNmNkNGI5OTYwZmMwYTcwNzkzMmQ1ZDgzN2RkNzp7InVzZXJuYW1lIjoibmluZyJ9','2018-01-07 01:35:54');
/*!40000 ALTER TABLE `django_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dms_campus`
--

DROP TABLE IF EXISTS `dms_campus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dms_campus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dms_campus`
--

LOCK TABLES `dms_campus` WRITE;
/*!40000 ALTER TABLE `dms_campus` DISABLE KEYS */;
INSERT INTO `dms_campus` (`id`, `name`) VALUES (1,'default');
/*!40000 ALTER TABLE `dms_campus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dms_city`
--

DROP TABLE IF EXISTS `dms_city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dms_city` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mail` varchar(20) NOT NULL,
  `province` varchar(20) NOT NULL,
  `country` varchar(20) NOT NULL,
  `cityname` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=393 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dms_city`
--

LOCK TABLES `dms_city` WRITE;
/*!40000 ALTER TABLE `dms_city` DISABLE KEYS */;
INSERT INTO `dms_city` (`id`, `mail`, `province`, `country`, `cityname`) VALUES (1,'110100','北京','','北京市'),(2,'120100','天津','','天津市'),(3,'130100','河北省','','石家庄市'),(4,'130200','河北省','','唐山市'),(5,'130300','河北省','','秦皇岛市'),(6,'130400','河北省','','邯郸市'),(7,'130500','河北省','','邢台市'),(8,'130600','河北省','','保定市'),(9,'130700','河北省','','张家口市'),(10,'130800','河北省','','承德市'),(11,'130900','河北省','','沧州市'),(12,'131000','河北省','','廊坊市'),(13,'131100','河北省','','衡水市'),(14,'140100','山西省','','太原市'),(15,'140200','山西省','','大同市'),(16,'140300','山西省','','阳泉市'),(17,'140400','山西省','','长治市'),(18,'140500','山西省','','晋城市'),(19,'140600','山西省','','朔州市'),(20,'140700','山西省','','晋中市'),(21,'140800','山西省','','运城市'),(22,'140900','山西省','','忻州市'),(23,'141000','山西省','','临汾市'),(24,'141100','山西省','','吕梁市'),(25,'150100','内蒙古自治区','','呼和浩特市'),(26,'150200','内蒙古自治区','','包头市'),(27,'150300','内蒙古自治区','','乌海市'),(28,'150400','内蒙古自治区','','赤峰市'),(29,'150500','内蒙古自治区','','通辽市'),(30,'150600','内蒙古自治区','','鄂尔多斯市'),(31,'150700','内蒙古自治区','','呼伦贝尔市'),(32,'150800','内蒙古自治区','','巴彦淖尔市'),(33,'150900','内蒙古自治区','','乌兰察布市'),(34,'152200','内蒙古自治区','','兴安盟'),(35,'152500','内蒙古自治区','','锡林郭勒盟'),(36,'152900','内蒙古自治区','','阿拉善盟'),(37,'210100','辽宁省','','沈阳市'),(38,'210200','辽宁省','','大连市'),(39,'210300','辽宁省','','鞍山市'),(40,'210400','辽宁省','','抚顺市'),(41,'210500','辽宁省','','本溪市'),(42,'210600','辽宁省','','丹东市'),(43,'210700','辽宁省','','锦州市'),(44,'210800','辽宁省','','营口市'),(45,'210900','辽宁省','','阜新市'),(46,'211000','辽宁省','','辽阳市'),(47,'211100','辽宁省','','盘锦市'),(48,'211200','辽宁省','','铁岭市'),(49,'211300','辽宁省','','朝阳市'),(50,'211400','辽宁省','','葫芦岛市'),(51,'220100','吉林省','','长春市'),(52,'220200','吉林省','','吉林市'),(53,'220300','吉林省','','四平市'),(54,'220400','吉林省','','辽源市'),(55,'220500','吉林省','','通化市'),(56,'220600','吉林省','','白山市'),(57,'220700','吉林省','','松原市'),(58,'220800','吉林省','','白城市'),(59,'222400','吉林省','','延边朝鲜族自治州'),(60,'230100','黑龙江省','','哈尔滨市'),(61,'230200','黑龙江省','','齐齐哈尔市'),(62,'230300','黑龙江省','','鸡西市'),(63,'230400','黑龙江省','','鹤岗市'),(64,'230500','黑龙江省','','双鸭山市'),(65,'230600','黑龙江省','','大庆市'),(66,'230700','黑龙江省','','伊春市'),(67,'230800','黑龙江省','','佳木斯市'),(68,'230900','黑龙江省','','七台河市'),(69,'231000','黑龙江省','','牡丹江市'),(70,'231100','黑龙江省','','黑河市'),(71,'231200','黑龙江省','','绥化市'),(72,'232700','黑龙江省','','大兴安岭地区'),(73,'310100','上海','','上海市'),(74,'320100','江苏省','','南京市'),(75,'320200','江苏省','','无锡市'),(76,'320300','江苏省','','徐州市'),(77,'320400','江苏省','','常州市'),(78,'320500','江苏省','','苏州市'),(79,'320600','江苏省','','南通市'),(80,'320700','江苏省','','连云港市'),(81,'320800','江苏省','','淮安市'),(82,'320900','江苏省','','盐城市'),(83,'321000','江苏省','','扬州市'),(84,'321100','江苏省','','镇江市'),(85,'321200','江苏省','','泰州市'),(86,'321300','江苏省','','宿迁市'),(87,'330100','浙江省','','杭州市'),(88,'330200','浙江省','','宁波市'),(89,'330300','浙江省','','温州市'),(90,'330400','浙江省','','嘉兴市'),(91,'330500','浙江省','','湖州市'),(92,'330600','浙江省','','绍兴市'),(93,'330700','浙江省','','金华市'),(94,'330800','浙江省','','衢州市'),(95,'330900','浙江省','','舟山市'),(96,'331000','浙江省','','台州市'),(97,'331100','浙江省','','丽水市'),(98,'340100','安徽省','','合肥市'),(99,'340200','安徽省','','芜湖市'),(100,'340300','安徽省','','蚌埠市'),(101,'340400','安徽省','','淮南市'),(102,'340500','安徽省','','马鞍山市'),(103,'340600','安徽省','','淮北市'),(104,'340700','安徽省','','铜陵市'),(105,'340800','安徽省','','安庆市'),(106,'341000','安徽省','','黄山市'),(107,'341100','安徽省','','滁州市'),(108,'341200','安徽省','','阜阳市'),(109,'341300','安徽省','','宿州市'),(110,'341500','安徽省','','六安市'),(111,'341600','安徽省','','亳州市'),(112,'341700','安徽省','','池州市'),(113,'341800','安徽省','','宣城市'),(114,'350100','福建省','','福州市'),(115,'350200','福建省','','厦门市'),(116,'350300','福建省','','莆田市'),(117,'350400','福建省','','三明市'),(118,'350500','福建省','','泉州市'),(119,'350600','福建省','','漳州市'),(120,'350700','福建省','','南平市'),(121,'350800','福建省','','龙岩市'),(122,'350900','福建省','','宁德市'),(123,'360100','江西省','','南昌市'),(124,'360200','江西省','','景德镇市'),(125,'360300','江西省','','萍乡市'),(126,'360400','江西省','','九江市'),(127,'360500','江西省','','新余市'),(128,'360600','江西省','','鹰潭市'),(129,'360700','江西省','','赣州市'),(130,'360800','江西省','','吉安市'),(131,'360900','江西省','','宜春市'),(132,'361000','江西省','','抚州市'),(133,'361100','江西省','','上饶市'),(134,'370100','山东省','','济南市'),(135,'370200','山东省','','青岛市'),(136,'370300','山东省','','淄博市'),(137,'370400','山东省','','枣庄市'),(138,'370500','山东省','','东营市'),(139,'370600','山东省','','烟台市'),(140,'370700','山东省','','潍坊市'),(141,'370800','山东省','','济宁市'),(142,'370900','山东省','','泰安市'),(143,'371000','山东省','','威海市'),(144,'371100','山东省','','日照市'),(145,'371200','山东省','','莱芜市'),(146,'371300','山东省','','临沂市'),(147,'371400','山东省','','德州市'),(148,'371500','山东省','','聊城市'),(149,'371600','山东省','','滨州市'),(150,'371700','山东省','','菏泽市'),(151,'410100','河南省','','郑州市'),(152,'410200','河南省','','开封市'),(153,'410300','河南省','','洛阳市'),(154,'410400','河南省','','平顶山市'),(155,'410500','河南省','','安阳市'),(156,'410600','河南省','','鹤壁市'),(157,'410700','河南省','','新乡市'),(158,'410800','河南省','','焦作市'),(159,'410881','河南省','','济源市'),(160,'410900','河南省','','濮阳市'),(161,'411000','河南省','','许昌市'),(162,'411100','河南省','','漯河市'),(163,'411200','河南省','','三门峡市'),(164,'411300','河南省','','南阳市'),(165,'411400','河南省','','商丘市'),(166,'411500','河南省','','信阳市'),(167,'411600','河南省','','周口市'),(168,'411700','河南省','','驻马店市'),(169,'420100','湖北省','','武汉市'),(170,'420200','湖北省','','黄石市'),(171,'420300','湖北省','','十堰市'),(172,'420500','湖北省','','宜昌市'),(173,'420600','湖北省','','襄阳市'),(174,'420700','湖北省','','鄂州市'),(175,'420800','湖北省','','荆门市'),(176,'420900','湖北省','','孝感市'),(177,'421000','湖北省','','荆州市'),(178,'421100','湖北省','','黄冈市'),(179,'421200','湖北省','','咸宁市'),(180,'421300','湖北省','','随州市'),(181,'422800','湖北省','','恩施土家族苗族自治州'),(182,'429004','湖北省','','仙桃市'),(183,'429005','湖北省','','潜江市'),(184,'429006','湖北省','','天门市'),(185,'429021','湖北省','','神农架林区'),(186,'430100','湖南省','','长沙市'),(187,'430200','湖南省','','株洲市'),(188,'430300','湖南省','','湘潭市'),(189,'430400','湖南省','','衡阳市'),(190,'430500','湖南省','','邵阳市'),(191,'430600','湖南省','','岳阳市'),(192,'430700','湖南省','','常德市'),(193,'430800','湖南省','','张家界市'),(194,'430900','湖南省','','益阳市'),(195,'431000','湖南省','','郴州市'),(196,'431100','湖南省','','永州市'),(197,'431200','湖南省','','怀化市'),(198,'431300','湖南省','','娄底市'),(199,'433100','湖南省','','湘西土家族苗族自治州'),(200,'440100','广东省','','广州市'),(201,'440200','广东省','','韶关市'),(202,'440300','广东省','','深圳市'),(203,'440400','广东省','','珠海市'),(204,'440500','广东省','','汕头市'),(205,'440600','广东省','','佛山市'),(206,'440700','广东省','','江门市'),(207,'440800','广东省','','湛江市'),(208,'440900','广东省','','茂名市'),(209,'441200','广东省','','肇庆市'),(210,'441300','广东省','','惠州市'),(211,'441400','广东省','','梅州市'),(212,'441500','广东省','','汕尾市'),(213,'441600','广东省','','河源市'),(214,'441700','广东省','','阳江市'),(215,'441800','广东省','','清远市'),(216,'441900','广东省','','东莞市'),(217,'442000','广东省','','中山市'),(218,'445100','广东省','','潮州市'),(219,'445200','广东省','','揭阳市'),(220,'445300','广东省','','云浮市'),(221,'450100','广西壮族自治区','','南宁市'),(222,'450200','广西壮族自治区','','柳州市'),(223,'450300','广西壮族自治区','','桂林市'),(224,'450400','广西壮族自治区','','梧州市'),(225,'450500','广西壮族自治区','','北海市'),(226,'450600','广西壮族自治区','','防城港市'),(227,'450700','广西壮族自治区','','钦州市'),(228,'450800','广西壮族自治区','','贵港市'),(229,'450900','广西壮族自治区','','玉林市'),(230,'451000','广西壮族自治区','','百色市'),(231,'451100','广西壮族自治区','','贺州市'),(232,'451200','广西壮族自治区','','河池市'),(233,'451300','广西壮族自治区','','来宾市'),(234,'451400','广西壮族自治区','','崇左市'),(235,'460100','海南省','','海口市'),(236,'460200','海南省','','三亚市'),(237,'469001','海南省','','五指山市'),(238,'469002','海南省','','琼海市'),(239,'469003','海南省','','儋州市'),(240,'469005','海南省','','文昌市'),(241,'469006','海南省','','万宁市'),(242,'469007','海南省','','东方市'),(243,'469025','海南省','','定安县'),(244,'469026','海南省','','屯昌县'),(245,'469027','海南省','','澄迈县'),(246,'469028','海南省','','临高县'),(247,'469030','海南省','','白沙黎族自治县'),(248,'469031','海南省','','昌江黎族自治县'),(249,'469033','海南省','','乐东黎族自治县'),(250,'469034','海南省','','陵水黎族自治县'),(251,'469035','海南省','','保亭黎族苗族自治县'),(252,'469036','海南省','','琼中黎族苗族自治县'),(253,'469037','海南省','','西沙群岛'),(254,'469038','海南省','','南沙群岛'),(255,'469039','海南省','','中沙群岛的岛礁及其海域'),(256,'500100','重庆','','重庆市'),(257,'510100','四川省','','成都市'),(258,'510300','四川省','','自贡市'),(259,'510400','四川省','','攀枝花市'),(260,'510500','四川省','','泸州市'),(261,'510600','四川省','','德阳市'),(262,'510700','四川省','','绵阳市'),(263,'510800','四川省','','广元市'),(264,'510900','四川省','','遂宁市'),(265,'511000','四川省','','内江市'),(266,'511100','四川省','','乐山市'),(267,'511300','四川省','','南充市'),(268,'511400','四川省','','眉山市'),(269,'511500','四川省','','宜宾市'),(270,'511600','四川省','','广安市'),(271,'511700','四川省','','达州市'),(272,'511800','四川省','','雅安市'),(273,'511900','四川省','','巴中市'),(274,'512000','四川省','','资阳市'),(275,'513200','四川省','','阿坝藏族羌族自治州'),(276,'513300','四川省','','甘孜藏族自治州'),(277,'513400','四川省','','凉山彝族自治州'),(278,'520100','贵州省','','贵阳市'),(279,'520200','贵州省','','六盘水市'),(280,'520300','贵州省','','遵义市'),(281,'520400','贵州省','','安顺市'),(282,'522200','贵州省','','铜仁地区'),(283,'522300','贵州省','','黔西南布依族苗族自治州'),(284,'522400','贵州省','','毕节地区'),(285,'522600','贵州省','','黔东南苗族侗族自治州'),(286,'522700','贵州省','','黔南布依族苗族自治州'),(287,'530100','云南省','','昆明市'),(288,'530300','云南省','','曲靖市'),(289,'530400','云南省','','玉溪市'),(290,'530500','云南省','','保山市'),(291,'530600','云南省','','昭通市'),(292,'530700','云南省','','丽江市'),(293,'530800','云南省','','普洱市'),(294,'530900','云南省','','临沧市'),(295,'532300','云南省','','楚雄彝族自治州'),(296,'532500','云南省','','红河哈尼族彝族自治州'),(297,'532600','云南省','','文山壮族苗族自治州'),(298,'532800','云南省','','西双版纳傣族自治州'),(299,'532900','云南省','','大理白族自治州'),(300,'533100','云南省','','德宏傣族景颇族自治州'),(301,'533300','云南省','','怒江傈僳族自治州'),(302,'533400','云南省','','迪庆藏族自治州'),(303,'540100','西藏自治区','','拉萨市'),(304,'542100','西藏自治区','','昌都地区'),(305,'542200','西藏自治区','','山南地区'),(306,'542300','西藏自治区','','日喀则地区'),(307,'542400','西藏自治区','','那曲地区'),(308,'542500','西藏自治区','','阿里地区'),(309,'542600','西藏自治区','','林芝地区'),(310,'610100','陕西省','','西安市'),(311,'610200','陕西省','','铜川市'),(312,'610300','陕西省','','宝鸡市'),(313,'610400','陕西省','','咸阳市'),(314,'610500','陕西省','','渭南市'),(315,'610600','陕西省','','延安市'),(316,'610700','陕西省','','汉中市'),(317,'610800','陕西省','','榆林市'),(318,'610900','陕西省','','安康市'),(319,'611000','陕西省','','商洛市'),(320,'620100','甘肃省','','兰州市'),(321,'620200','甘肃省','','嘉峪关市'),(322,'620300','甘肃省','','金昌市'),(323,'620400','甘肃省','','白银市'),(324,'620500','甘肃省','','天水市'),(325,'620600','甘肃省','','武威市'),(326,'620700','甘肃省','','张掖市'),(327,'620800','甘肃省','','平凉市'),(328,'620900','甘肃省','','酒泉市'),(329,'621000','甘肃省','','庆阳市'),(330,'621100','甘肃省','','定西市'),(331,'621200','甘肃省','','陇南市'),(332,'622900','甘肃省','','临夏回族自治州'),(333,'623000','甘肃省','','甘南藏族自治州'),(334,'630100','青海省','','西宁市'),(335,'632100','青海省','','海东地区'),(336,'632200','青海省','','海北藏族自治州'),(337,'632300','青海省','','黄南藏族自治州'),(338,'632500','青海省','','海南藏族自治州'),(339,'632600','青海省','','果洛藏族自治州'),(340,'632700','青海省','','玉树藏族自治州'),(341,'632800','青海省','','海西蒙古族藏族自治州'),(342,'640100','宁夏回族自治区','','银川市'),(343,'640200','宁夏回族自治区','','石嘴山市'),(344,'640300','宁夏回族自治区','','吴忠市'),(345,'640400','宁夏回族自治区','','固原市'),(346,'640500','宁夏回族自治区','','中卫市'),(347,'650100','新疆维吾尔自治区','','乌鲁木齐市'),(348,'650200','新疆维吾尔自治区','','克拉玛依市'),(349,'652100','新疆维吾尔自治区','','吐鲁番地区'),(350,'652200','新疆维吾尔自治区','','哈密地区'),(351,'652300','新疆维吾尔自治区','','昌吉回族自治州'),(352,'652700','新疆维吾尔自治区','','博尔塔拉蒙古自治州'),(353,'652800','新疆维吾尔自治区','','巴音郭楞蒙古自治州'),(354,'652900','新疆维吾尔自治区','','阿克苏地区'),(355,'653000','新疆维吾尔自治区','','克孜勒苏柯尔克孜自治州'),(356,'653100','新疆维吾尔自治区','','喀什地区'),(357,'653200','新疆维吾尔自治区','','和田地区'),(358,'654000','新疆维吾尔自治区','','伊犁哈萨克自治州'),(359,'654200','新疆维吾尔自治区','','塔城地区'),(360,'654300','新疆维吾尔自治区','','阿勒泰地区'),(361,'659001','新疆维吾尔自治区','','石河子市'),(362,'659002','新疆维吾尔自治区','','阿拉尔市'),(363,'659003','新疆维吾尔自治区','','图木舒克市'),(364,'659004','新疆维吾尔自治区','','五家渠市'),(365,'710100','台湾省','','台北市'),(366,'710200','台湾省','','高雄市'),(367,'710300','台湾省','','台南市'),(368,'710400','台湾省','','台中市'),(369,'710500','台湾省','','金门县'),(370,'710600','台湾省','','南投县'),(371,'710700','台湾省','','基隆市'),(372,'710800','台湾省','','新竹市'),(373,'710900','台湾省','','嘉义市'),(374,'711100','台湾省','','新北市'),(375,'711200','台湾省','','宜兰县'),(376,'711300','台湾省','','新竹县'),(377,'711400','台湾省','','桃园县'),(378,'711500','台湾省','','苗栗县'),(379,'711700','台湾省','','彰化县'),(380,'711900','台湾省','','嘉义县'),(381,'712100','台湾省','','云林县'),(382,'712400','台湾省','','屏东县'),(383,'712500','台湾省','','台东县'),(384,'712600','台湾省','','花莲县'),(385,'712700','台湾省','','澎湖县'),(386,'810100','香港特别行政区','','香港岛'),(387,'810200','香港特别行政区','','九龙'),(388,'810300','香港特别行政区','','新界'),(389,'820100','澳门特别行政区','','澳门半岛'),(390,'820200','澳门特别行政区','','离岛'),(391,'990100','海外','','海外'),(392,'','default','default','default');
/*!40000 ALTER TABLE `dms_city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dms_collection`
--

DROP TABLE IF EXISTS `dms_collection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dms_collection` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `commodity_id_id` int(11) NOT NULL,
  `user_id_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `dms_collection_commodity_id_id_31248b19` (`commodity_id_id`),
  KEY `dms_collection_user_id_id_47a68e05` (`user_id_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dms_collection`
--

LOCK TABLES `dms_collection` WRITE;
/*!40000 ALTER TABLE `dms_collection` DISABLE KEYS */;
/*!40000 ALTER TABLE `dms_collection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dms_comment`
--

DROP TABLE IF EXISTS `dms_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dms_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(45) NOT NULL,
  `commituser_id` int(11) NOT NULL,
  `commodityid_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `dms_comment_commituser_id_03dad467` (`commituser_id`),
  KEY `dms_comment_commodityid_id_4cf4e0e1` (`commodityid_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dms_comment`
--

LOCK TABLES `dms_comment` WRITE;
/*!40000 ALTER TABLE `dms_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `dms_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dms_commodity`
--

DROP TABLE IF EXISTS `dms_commodity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dms_commodity`
--

LOCK TABLES `dms_commodity` WRITE;
/*!40000 ALTER TABLE `dms_commodity` DISABLE KEYS */;
INSERT INTO `dms_commodity` (`id`, `name`, `sort`, `price`, `note`, `img1`, `img2`, `img3`, `location_id_id`, `schoolid_id`, `user_id_id`) VALUES (1,'test',33,45,'test','','','',1,1,1);
/*!40000 ALTER TABLE `dms_commodity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dms_delegation`
--

DROP TABLE IF EXISTS `dms_delegation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dms_delegation`
--

LOCK TABLES `dms_delegation` WRITE;
/*!40000 ALTER TABLE `dms_delegation` DISABLE KEYS */;
/*!40000 ALTER TABLE `dms_delegation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dms_delegation_order`
--

DROP TABLE IF EXISTS `dms_delegation_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dms_delegation_order`
--

LOCK TABLES `dms_delegation_order` WRITE;
/*!40000 ALTER TABLE `dms_delegation_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `dms_delegation_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dms_indent`
--

DROP TABLE IF EXISTS `dms_indent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dms_indent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datetime` date NOT NULL,
  `remask` varchar(45) NOT NULL,
  `commodity_id_id` int(11) NOT NULL,
  `location_id_id` int(11) NOT NULL,
  `purchase_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `dms_indent_commodity_id_id_60a93898` (`commodity_id_id`),
  KEY `dms_indent_location_id_id_ca1b80f2` (`location_id_id`),
  KEY `dms_indent_purchase_id_2169c4f1` (`purchase_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dms_indent`
--

LOCK TABLES `dms_indent` WRITE;
/*!40000 ALTER TABLE `dms_indent` DISABLE KEYS */;
/*!40000 ALTER TABLE `dms_indent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dms_location`
--

DROP TABLE IF EXISTS `dms_location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dms_location` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `extra` varchar(20) NOT NULL,
  `cityid_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `dms_location_cityid_id_604e74ea` (`cityid_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dms_location`
--

LOCK TABLES `dms_location` WRITE;
/*!40000 ALTER TABLE `dms_location` DISABLE KEYS */;
INSERT INTO `dms_location` (`id`, `extra`, `cityid_id`) VALUES (1,'default',392);
/*!40000 ALTER TABLE `dms_location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dms_school`
--

DROP TABLE IF EXISTS `dms_school`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dms_school` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `usernum` int(11) NOT NULL,
  `setuptime` varchar(20) NOT NULL,
  `indentnum` int(11) NOT NULL,
  `city_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `dms_school_city_id_aaed5792` (`city_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dms_school`
--

LOCK TABLES `dms_school` WRITE;
/*!40000 ALTER TABLE `dms_school` DISABLE KEYS */;
INSERT INTO `dms_school` (`id`, `name`, `usernum`, `setuptime`, `indentnum`, `city_id`) VALUES (1,'default',0,'',0,392);
/*!40000 ALTER TABLE `dms_school` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dms_security`
--

DROP TABLE IF EXISTS `dms_security`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dms_security` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(15) NOT NULL,
  `email` varchar(45) NOT NULL,
  `tel` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dms_security`
--

LOCK TABLES `dms_security` WRITE;
/*!40000 ALTER TABLE `dms_security` DISABLE KEYS */;
INSERT INTO `dms_security` (`id`, `password`, `email`, `tel`) VALUES (1,'123','123456797@qq.com','12345678902');
/*!40000 ALTER TABLE `dms_security` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dms_sortbook`
--

DROP TABLE IF EXISTS `dms_sortbook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dms_sortbook` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `grade` int(11) NOT NULL,
  `shcool` varchar(45) NOT NULL,
  `major` varchar(20) NOT NULL,
  `commodity_id_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `dms_sortbook_commodity_id_id_6d811a07` (`commodity_id_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dms_sortbook`
--

LOCK TABLES `dms_sortbook` WRITE;
/*!40000 ALTER TABLE `dms_sortbook` DISABLE KEYS */;
/*!40000 ALTER TABLE `dms_sortbook` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dms_user`
--

DROP TABLE IF EXISTS `dms_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dms_user`
--

LOCK TABLES `dms_user` WRITE;
/*!40000 ALTER TABLE `dms_user` DISABLE KEYS */;
INSERT INTO `dms_user` (`id`, `username`, `gender`, `age`, `introduce`, `unique`, `portrait`, `addressid_id`, `campus_id_id`, `locationid_id`, `schoolid_id`, `security_id_id`) VALUES (1,'ning','',0,'','null','',1,1,1,1,1);
/*!40000 ALTER TABLE `dms_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-24  9:44:22
