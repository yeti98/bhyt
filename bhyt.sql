-- MySQL dump 10.13  Distrib 5.7.28, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: bhyt
-- ------------------------------------------------------
-- Server version	5.7.28-0ubuntu0.19.04.2

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
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL,
  `base_salary` double NOT NULL,
  `code` varchar(255) NOT NULL,
  `date_of_birth` date DEFAULT NULL,
  `expiry_date` date NOT NULL,
  `first_registration_place` varchar(255) NOT NULL,
  `gender` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `personalid` varchar(255) NOT NULL,
  `start_date` date NOT NULL,
  `time_stamp` datetime(6) NOT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_8q38o07vmn6x2opedgsq9sewo` (`code`),
  UNIQUE KEY `UK_gofarvuj5plj2j8gwuncw7hu` (`personalid`),
  KEY `FK3vxtaa41wbc8d2o28ryh2mmg9` (`category_id`),
  KEY `FKqhq5aolak9ku5x5mx11cpjad9` (`user_id`),
  CONSTRAINT `FK3vxtaa41wbc8d2o28ryh2mmg9` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FKqhq5aolak9ku5x5mx11cpjad9` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill` (`id`, `amount`, `base_salary`, `code`, `date_of_birth`, `expiry_date`, `first_registration_place`, `gender`, `name`, `personalid`, `start_date`, `time_stamp`, `category_id`, `user_id`) VALUES (1,540000,12000000,'CN_BH0001','2013-05-08','2021-05-17','BV Bạch Mai','Nam','Nguyễn Văn An','017432803','2020-05-17','2020-05-17 20:10:49.000000',1,NULL),(5,1080000,24000000,'CN_BH0002','2013-05-08','2021-05-17','BV Bạch Mai','Nam','Nguyễn Văn An','017432804','2020-05-17','2020-05-17 20:10:49.000000',4,NULL);
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `default_base_salary` double DEFAULT NULL,
  `min_supported_percent` double DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `value` double NOT NULL,
  `value_description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_acatplu22q5d1andql2jbvjy7` (`code`),
  UNIQUE KEY `UK_46ccwnsi9409t36lurvtyljak` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` (`id`, `code`, `default_base_salary`, `min_supported_percent`, `name`, `type`, `value`, `value_description`) VALUES (1,'N01',0,0,'Nhóm do người lao động và người sử dụng lao động đóng','BY_PERCENT',4.5,'Phần trăm tiền lương cơ sở'),(4,'N02',0,0,'Nhóm do Quỹ bảo hiểm xã hội đóng:','BY_PERCENT',4.5,'Phần trăm tiền lương hưu/trợ cấp thất nghiệp'),(8,'N03',0,0,'Nhóm do ngân sách Nhà nước đóng','BY_PERCENT',4.5,' 4,5% mức lương cơ sở,tiền lương hàng tháng'),(9,'N04.1',1440000,70,'Nhóm do ngân sách Nhà nước hỗ trợ - Hộ gia đình cận nghèo','SUPPORTED',75.9,' Mức hỗ trợ tối thiểu là 70% TLCS'),(10,'N04.2',1440000,30,'Nhóm do ngân sách Nhà nước hỗ trợ - HSSV','SUPPORTED',35,'Mức hỗ trợ tối thiểu là 30% TLCS.'),(11,'N04.3',2000000,50,'Nhóm do ngân sách Nhà nước hỗ trợ - Hộ gia đình làm nông,... có thu nhập trung bình.','SUPPORTED',55,'Mức hỗ trợ tối thiểu là 50% TLCS.'),(12,'N05.1',0,NULL,'Nhóm thứ năm đối tượng tham gia theo hộ gia đình - Người thứ nhất','FAMILY',4.5,'4.5 % TLCS'),(13,'N05.2',0,NULL,' Nhóm thứ năm đối tượng tham gia theo hộ gia đình - Người thứ hai','BY_PERCENT',70,'70% mức tiền đóng của người thứ nhất'),(14,'N05.3',0,0,' Nhóm thứ năm đối tượng tham gia theo hộ gia đình - Người thứ ba','FAMILY',60,' 60% mức tiền đóng của người thứ nhất ');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `family`
--

DROP TABLE IF EXISTS `family`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `family` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_bn4rdydl6psta9iab5qnxhi7g` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `family`
--

LOCK TABLES `family` WRITE;
/*!40000 ALTER TABLE `family` DISABLE KEYS */;
/*!40000 ALTER TABLE `family` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `family_bills`
--

DROP TABLE IF EXISTS `family_bills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `family_bills` (
  `family_id` bigint(20) NOT NULL,
  `bills_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_ionv72hpc2mvgqfqw5yrywkvp` (`bills_id`),
  KEY `FKfd8fnq575saog1lca5yob76jx` (`family_id`),
  CONSTRAINT `FK8jv22g1uo7ocbxlnc220e3s1g` FOREIGN KEY (`bills_id`) REFERENCES `bill` (`id`),
  CONSTRAINT `FKfd8fnq575saog1lca5yob76jx` FOREIGN KEY (`family_id`) REFERENCES `family` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `family_bills`
--

LOCK TABLES `family_bills` WRITE;
/*!40000 ALTER TABLE `family_bills` DISABLE KEYS */;
/*!40000 ALTER TABLE `family_bills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `email`, `password`, `role`) VALUES (1,'admin@gmail.com','$2y$12$Fz9/zF4iyZG.G347JVXIFe9vDpjxQZLyVZmA3BmMxeP09yyUyT0qG','ADMIN');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-21 22:48:19
