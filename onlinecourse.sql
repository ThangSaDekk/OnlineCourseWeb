-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: onlinecourse
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Kiến thức cơ sở','Các kiến thức nền tảng cần thiết','2024-08-22 14:48:14','2024-08-22 14:48:14'),(2,'Lập trình cơ sở','Những khái niệm và kỹ năng lập trình cơ bản','2024-08-22 14:48:14','2024-08-22 14:48:14'),(3,'Lập trình nâng cao','Những kỹ năng và kiến thức lập trình chuyên sâu','2024-08-22 14:48:14','2024-08-22 14:48:14'),(4,'Giải quyết vấn đề','Kỹ năng phân tích và giải quyết các vấn đề phức tạp','2024-08-22 14:48:14','2024-08-22 14:48:14'),(5,'Kỹ năng nâng cao','Các kỹ năng nâng cao để phát triển bản thân và sự nghiệp','2024-08-22 14:48:14','2024-08-22 14:48:14');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `description` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci,
  `time_experted` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `price` bigint NOT NULL,
  `category_id` int NOT NULL,
  `instructor_id` int NOT NULL,
  `status` enum('ACTIVE','INACTIVE') COLLATE utf8mb3_unicode_ci NOT NULL,
  `course_type` enum('ONLINE','ON_OFF') COLLATE utf8mb3_unicode_ci NOT NULL,
  `img` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_title` (`title`),
  KEY `category_id` (`category_id`),
  KEY `instructor_id` (`instructor_id`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `course_ibfk_2` FOREIGN KEY (`instructor_id`) REFERENCES `instructor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=276 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (271,'Nhập môn Data Analysis','Khóa học cơ bản về phân tích dữ liệu sử dụng các công cụ phổ biến.','30 giờ','2024-08-22 15:20:02','2024-08-22 15:20:02',1200000,1,9,'ACTIVE','ONLINE',NULL),(272,'Triển khai Dịch vụ Đám mây','Hướng dẫn cách triển khai các dịch vụ đám mây với AWS.','40 giờ','2024-08-22 15:20:02','2024-08-22 15:20:02',1500000,2,10,'ACTIVE','ON_OFF',NULL),(273,'Xử lý Dữ liệu lớn','Kỹ thuật xử lý và phân tích dữ liệu lớn cho các dự án dữ liệu phức tạp.','50 giờ','2024-08-22 15:20:02','2024-08-22 15:20:02',1800000,3,9,'INACTIVE','ONLINE',NULL),(274,'Kỹ năng SQL nâng cao','Khóa học chuyên sâu về SQL cho quản lý và phân tích cơ sở dữ liệu.','35 giờ','2024-08-22 15:20:02','2024-08-22 15:20:02',1300000,4,10,'ACTIVE','ONLINE',NULL),(275,'Quản lý Dự án IT','Học cách quản lý các dự án IT từ khâu lập kế hoạch đến thực hiện.','45 giờ','2024-08-22 15:20:02','2024-08-22 15:20:02',1600000,5,10,'ACTIVE','ON_OFF',NULL);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enrollment`
--

DROP TABLE IF EXISTS `enrollment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `enrollment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `course_id` int NOT NULL,
  `invoice_id` int DEFAULT NULL,
  `status` enum('IN_PROGRESS','COMPLETED','CANCELLED') COLLATE utf8mb3_unicode_ci DEFAULT 'IN_PROGRESS',
  `progress` int DEFAULT '0',
  `grade` varchar(10) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `certificate_url` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_user_course` (`user_id`,`course_id`),
  KEY `course_id` (`course_id`),
  KEY `invoice_id` (`invoice_id`),
  CONSTRAINT `enrollment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `enrollment_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE,
  CONSTRAINT `enrollment_ibfk_3` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enrollment`
--

LOCK TABLES `enrollment` WRITE;
/*!40000 ALTER TABLE `enrollment` DISABLE KEYS */;
INSERT INTO `enrollment` VALUES (26,4,271,1,'IN_PROGRESS',50,NULL,NULL,'2024-08-24 08:11:00','2024-08-24 08:11:00'),(27,4,272,1,'IN_PROGRESS',100,NULL,NULL,'2024-08-24 11:28:16','2024-08-24 11:28:16'),(28,4,273,1,'IN_PROGRESS',0,NULL,NULL,'2024-08-24 11:28:45','2024-08-24 11:28:45'),(29,3,274,2,'IN_PROGRESS',0,NULL,NULL,'2024-08-26 20:10:19','2024-08-26 20:10:19'),(30,3,275,2,'IN_PROGRESS',75,NULL,NULL,'2024-08-26 20:11:10','2024-08-26 20:11:10'),(31,2,271,3,'IN_PROGRESS',80,NULL,NULL,'2024-08-26 20:12:54','2024-08-26 20:12:54'),(32,2,272,3,'IN_PROGRESS',90,NULL,NULL,'2024-08-26 20:12:54','2024-08-26 20:12:54');
/*!40000 ALTER TABLE `enrollment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `forgotpassword`
--

DROP TABLE IF EXISTS `forgotpassword`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `forgotpassword` (
  `id` int NOT NULL AUTO_INCREMENT,
  `otp` int NOT NULL,
  `expirationTime` timestamp NOT NULL,
  `userId` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  CONSTRAINT `forgotpassword_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forgotpassword`
--

LOCK TABLES `forgotpassword` WRITE;
/*!40000 ALTER TABLE `forgotpassword` DISABLE KEYS */;
INSERT INTO `forgotpassword` VALUES (1,225906,'2024-08-29 07:25:49',2);
/*!40000 ALTER TABLE `forgotpassword` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instructor`
--

DROP TABLE IF EXISTS `instructor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `instructor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `expertise` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `description` text CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`),
  CONSTRAINT `instructor_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instructor`
--

LOCK TABLES `instructor` WRITE;
/*!40000 ALTER TABLE `instructor` DISABLE KEYS */;
INSERT INTO `instructor` VALUES (9,'Data Analysis','Chuyên gia phân tích dữ liệu với khả năng sử dụng các công cụ phân tích mạnh mẽ.','2024-08-22 15:09:52','2024-08-22 15:09:52',4),(10,'Cloud Services','Chuyên gia về các dịch vụ đám mây, có kinh nghiệm triển khai ứng dụng trên AWS và Azure.','2024-08-22 15:09:52','2024-08-22 15:09:52',5),(17,'Data SQL','Phan tich thiet ke he thong ke','2024-08-22 16:30:00','2024-08-22 16:30:00',15),(18,'Data SQL','Phan tich thiet ke he thong dza','2024-08-22 16:30:48','2024-08-22 16:30:48',16),(19,'Data SQLLL','Phan tich thiet ke he thong dzac','2024-08-22 17:27:29','2024-08-22 17:27:29',17),(20,'Data SQLdâz','Phan tich thiet ke he thong d','2024-08-22 17:41:56','2024-08-22 17:41:56',18),(21,'Data SQLd','Phan tich thiet ke he thong d','2024-08-24 02:30:25','2024-08-24 02:30:25',19),(22,'Data SQLdâz','Phan tich thiet ke he thong ke','2024-08-24 02:31:24','2024-08-24 02:31:24',20),(23,'Data SQLdâz','tro ly ao va nhung cong nghe lap trinh hien dai','2024-08-24 02:32:24','2024-08-24 02:32:24',21);
/*!40000 ALTER TABLE `instructor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice` (
  `id` int NOT NULL AUTO_INCREMENT,
  `invoice_number` varchar(100) COLLATE utf8mb3_unicode_ci NOT NULL,
  `payer_name` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `payer_phone` varchar(11) COLLATE utf8mb3_unicode_ci NOT NULL,
  `payer_email` varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  `total_amount` bigint NOT NULL,
  `status` bit(1) DEFAULT b'0',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `invoice_number` (`invoice_number`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES (1,'INV0001','John Doe','0912345678','johndoe@example.com',5000000,_binary '\0','2024-08-22 15:08:08','2024-08-22 15:08:08'),(2,'INV0002','Jane Smith','0923456789','janesmith@example.com',7500000,_binary '','2024-08-22 15:08:08','2024-08-22 15:08:08'),(3,'INV0003','Alice Johnson','0934567890','alicejohnson@example.com',1200000,_binary '\0','2024-08-22 15:08:08','2024-08-28 14:45:30'),(7,'INV004','Van A','1234567890','vana@example.com',5000000,_binary '','2024-01-15 03:00:00','2024-08-28 14:12:31'),(8,'INV005','Van B','1234567891','b@example.com',6000000,_binary '','2024-02-14 03:00:00','2024-08-28 14:12:31'),(9,'INV006','Van C','1234567892','c@example.com',5500000,_binary '','2024-03-13 03:00:00','2024-08-28 14:12:31'),(10,'INV007','Van D','1234567893','bob@example.com',7000000,_binary '','2024-04-12 03:00:00','2024-08-28 14:12:31'),(11,'INV008','Charlie Black','1234567894','charlie@example.com',6500000,_binary '','2024-05-11 03:00:00','2024-08-28 14:12:31'),(12,'INV009','Diana Green','1234567895','diana@example.com',7200000,_binary '','2024-06-10 03:00:00','2024-08-28 14:12:31'),(13,'INV0010','Eve Grey','1234567896','eve@example.com',8000000,_binary '','2024-07-09 03:00:00','2024-08-28 14:12:31'),(14,'INV0011','Frank Blue','1234567897','frank@example.com',9000000,_binary '','2024-08-08 03:00:00','2024-08-28 14:12:31'),(15,'INV0012','Grace Red','1234567898','grace@example.com',8500000,_binary '','2024-09-07 03:00:00','2024-08-28 14:12:31'),(16,'INV013','Hank Yellow','1234567899','hank@example.com',9500000,_binary '','2024-10-06 03:00:00','2024-08-28 14:12:31'),(17,'INV014','Ivy Purple','1234567890','ivy@example.com',8700000,_binary '','2024-11-05 03:00:00','2024-08-28 14:12:31'),(18,'INV015','Jack Orange','1234567891','jack@example.com',8800000,_binary '','2024-12-04 03:00:00','2024-08-28 14:12:31');
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `last_name` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `email` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `phone` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `username` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `active` bit(1) DEFAULT b'1',
  `user_role` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `avatar` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_email` (`email`),
  UNIQUE KEY `unique_phone` (`phone`),
  UNIQUE KEY `unique_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Admin','User','dattanlee@gmail.com','1234567890','admin','$2a$12$f9vi0qmXsjx7VPALCp7iwOlv68OtPZ90jq31j7LjcxnS2hsU1SD7C',_binary '','ROLE_ADMIN','https://res.cloudinary.com/djrv1hnrc/image/upload/v1724348061/Online_Course/fsduoigkaa3hxubgsdmr.jpg','2024-08-22 15:01:04','2024-08-29 06:26:53'),(2,'John','Doe','anhsaucop@gmail.com','2345678901','johndoe','$2a$10$QL.AtwI.uIfVXVMMJPNnNO4gdQZWI167JY5LGZm1I1HslYc2WHBgO',_binary '','ROLE_USER',NULL,'2024-08-22 15:01:04','2024-08-29 05:21:55'),(3,'Jane','Smith','jane.smith@example.com','3456789012','janesmith','$2a$12$f9vi0qmXsjx7VPALCp7iwOlv68OtPZ90jq31j7LjcxnS2hsU1SD7C',_binary '','ROLE_USER',NULL,'2024-08-22 15:01:04','2024-08-22 15:28:39'),(4,'Alice','Johnson','alice.johnson@example.com','4567890123','alicejohnson','$2a$12$f9vi0qmXsjx7VPALCp7iwOlv68OtPZ90jq31j7LjcxnS2hsU1SD7C',_binary '','ROLE_USER',NULL,'2024-08-22 15:01:04','2024-08-22 15:28:39'),(5,'Michael','Brown','michael.brown@example.com','5678901234','michaelbrown','$2a$12$f9vi0qmXsjx7VPALCp7iwOlv68OtPZ90jq31j7LjcxnS2hsU1SD7C',_binary '','ROLE_INSTRUCTOR',NULL,'2024-08-22 15:01:04','2024-08-22 15:28:39'),(6,'Emily','Davis','emily.davis@example.com','6789012345','emilydavis','$2a$12$f9vi0qmXsjx7VPALCp7iwOlv68OtPZ90jq31j7LjcxnS2hsU1SD7C',_binary '','ROLE_INSTRUCTOR',NULL,'2024-08-22 15:01:04','2024-08-22 15:28:39'),(7,'David','Wilson','david.wilson@example.com','7890123456','davidwilson','$2a$12$f9vi0qmXsjx7VPALCp7iwOlv68OtPZ90jq31j7LjcxnS2hsU1SD7C',_binary '','ROLE_INSTRUCTOR',NULL,'2024-08-22 15:01:04','2024-08-22 15:28:39'),(15,'Hiep','Trinh','2151050087dat@ou.edu.vn','0941265207','hieptrinh','$2a$10$T9wXD0YrEGWmcEUzrIm4duVMSZBCq4ALaExWLjFw/lkaKXEE7cXJK',_binary '','ROLE_INSTRUCTOR','https://res.cloudinary.com/dh1irfap0/image/upload/v1724344199/fpewtefpnufkzab7k72f.bmp','2024-08-22 16:30:00','2024-08-22 16:30:00'),(16,'thang','le','thangle@gmail.com','0941265206','vietthang','$2a$10$e1cZYsK5NSmZxFxs8ufEm.FWclbVlTf2bgPYNe9c0Uyf1.05mzVNi',_binary '','ROLE_INSTRUCTOR',NULL,'2024-08-22 16:30:48','2024-08-22 16:30:48'),(17,'tran','dan','2151050087dan@ou.edu.vn','0941265209','tandat','$2a$10$t5tb.2un1/XpW0Wxq83ureP8V6kDGm65Kv9EEj9jYmQEIQvo2MmDK',_binary '','ROLE_INSTRUCTOR','https://res.cloudinary.com/dh1irfap0/image/upload/v1724347649/is7u1qqfszz1w3xdsyes.jpg','2024-08-22 17:27:29','2024-08-22 17:27:29'),(18,'thang','nguyen','2151050089dat@ou.edu.vn','0941265298','hhhh','$2a$10$7WbS.af/5vr2SCgpOT5qY.0w1KYlDl3EweW9aRg13g7bQyUCe/YXe',_binary '','ROLE_INSTRUCTOR','https://res.cloudinary.com/djrv1hnrc/image/upload/v1724348516/o0axfjdzs2tlyizkghfc.jpg','2024-08-22 17:41:56','2024-08-22 17:41:56'),(19,'zxcv','zxcv','hsa@gmail.com','0941265264','hhhhhh','$2a$10$Acg3So2wQXTwljK0bM8tRuRzracE76yUIsAE/5cfGFFg6gWSxd4Ru',_binary '','ROLE_INSTRUCTOR','https://res.cloudinary.com/djrv1hnrc/image/upload/v1724466625/c2tcr63k1xsp0bttjphb.bmp','2024-08-24 02:30:25','2024-08-24 02:30:25'),(20,'zxcbvn','asad','2151050087datt@ou.edu.vn','09412653243','tandat23','$2a$10$LNzHKt4Mr0AKzysAwoRO3eWa2eDcXikVP8Akilo0AjmuHBF6T1oRu',_binary '','ROLE_INSTRUCTOR','https://res.cloudinary.com/djrv1hnrc/image/upload/v1724466684/hztfp0clyhdhchffnjn4.bmp','2024-08-24 02:31:24','2024-08-24 02:31:24'),(21,'vanw','aw','abcew@gmail.com','94126522714','ertyui','$2a$10$KilDuBA6ZfTLc18buIW1mOk2H0ye3EQy9Q1rzjAUMtGCmnImJHxVG',_binary '','ROLE_INSTRUCTOR','https://res.cloudinary.com/djrv1hnrc/image/upload/v1724466744/mclsv0ybs6xsoryhjo5l.bmp','2024-08-24 02:32:24','2024-08-24 02:32:24');
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

-- Dump completed on 2024-08-29 18:20:24
