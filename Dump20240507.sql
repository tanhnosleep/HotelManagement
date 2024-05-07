-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: qlks
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
-- Table structure for table `app_role`
--

DROP TABLE IF EXISTS `app_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app_role` (
  `role_id` bigint NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_uk` (`role_name`),
  UNIQUE KEY `APP_ROLE_UK` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_role`
--

LOCK TABLES `app_role` WRITE;
/*!40000 ALTER TABLE `app_role` DISABLE KEYS */;
INSERT INTO `app_role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
/*!40000 ALTER TABLE `app_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_user`
--

DROP TABLE IF EXISTS `app_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `encryted_password` varchar(125) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `guest_guest_id` bigint DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_uk` (`user_name`),
  UNIQUE KEY `APP_USER_UK` (`user_name`),
  KEY `FKpu975pv9qbl87h1m26p7heafp` (`guest_guest_id`),
  CONSTRAINT `FKpu975pv9qbl87h1m26p7heafp` FOREIGN KEY (`guest_guest_id`) REFERENCES `guest` (`guest_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_user`
--

LOCK TABLES `app_user` WRITE;
/*!40000 ALTER TABLE `app_user` DISABLE KEYS */;
INSERT INTO `app_user` VALUES (1,'admin','$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu',_binary '',NULL),(2,'user','$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu',_binary '',NULL),(3,'leduongk54a2','$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu',_binary '\0',6),(4,'manh1412','$2a$12$ct89.eUJTD0k0BoYVHCX3u8f7y8WGJxT.YrKCrAR2ls9WRpoM9qTi',_binary '\0',7),(5,'dat','$2a$12$KhQRF0jwVWqqE.P/xGsEfusIW3P.IYTRaeQsBIRocE6Mn5s24FsDy',_binary '\0',8),(6,'tanh','$2a$12$24XWMwqmCTDxw.kmtfvB6e6rgGahpmEFqVzjHwU0GHCRZiV1sh56i',_binary '\0',9),(7,'Liêm','$2a$12$T00YL0v6OQDNy1wANGNIzueEaWiYzLqtGSjGCk26wIc5c8sFOy/eq',_binary '\0',10),(8,'Thomas','$2a$12$6Xvs/y49MYVUQ8zh09uMLO5OO3ofQtd/gazQ8At35HEMWFLnBsRTe',_binary '\0',11),(9,'Huy','$2a$12$oFAINPWFBsrI5SU8K21PouZlkOtBFaTvG0Kpk.n2Tdi/6Tb0rGe0.',_binary '\0',12);
/*!40000 ALTER TABLE `app_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `employee_id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `birth` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `employee_name` varchar(255) NOT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `salary` double DEFAULT NULL,
  PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Hà Nội','23/05/2001','leduongk54a2@gmail.com','Lê Mạnh Dương','Nam','0984851556',30000),(2,'qwewq','08/12/1998','lglggty@gmail.com','Nguyễn Văn A','Nữ','0984851557',50000),(4,'Thái Nguyên','25/01/2001','tanh@gmail.com','Nguyễn Tuấn Anh','Nam','0649841588',600000),(5,'Tân Mai, Hà Nội','22/05/2006','vinh225@gmail.com','Nguyễn Quang Vinh','Nam','0900987999',500000),(6,'Hà Nội','20/01/2001','blacktom201@gmail.com','Nguyễn Tiến Đạt','Nam','0915000000',451512),(8,'Mỗ Lao','10/07/2001','nghia@gmail.com','Nguyễn Nghĩa','Nam','0387415141',200000),(12,'','05/06/1991','','Nguyễn Nữ','Nữ','0915222923',200000);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guest`
--

DROP TABLE IF EXISTS `guest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `guest` (
  `guest_id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `birth` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `guest_name` varchar(255) DEFAULT NULL,
  `id_card` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`guest_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guest`
--

LOCK TABLES `guest` WRITE;
/*!40000 ALTER TABLE `guest` DISABLE KEYS */;
INSERT INTO `guest` VALUES (6,'Sơn Tây','23/05/2001','ewqewq@erere.com','Nguyễn Mạnh Nam','26456465456','097856645878'),(7,'Thai binh','19/08/1999','manh@kewqe.com','nguyen duc manh','654484897897','05598789789'),(8,'Hà Nội','20/01/2001','2001datnt@gmail.com','Nguyễn Tiến Đạt','005618944987','0915009999'),(9,'Thái Nguyên','25/01/2001','tanh@gmail.com','Nguyễn Tuấn Anh','120514521847','0649841588'),(10,'The Liems','08/09/2011','liems123@gmail.com','Khiết Liêm','003212332105','0461105847'),(11,'Hà Nam','28/09/1987','thomas@gmail.com','Thomas Edwards','003212332104','0625652266'),(12,'Hà Đông','26/02/2003','huy@gmail.com','Nguyễn Huy','123467865201','0319497451');
/*!40000 ALTER TABLE `guest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (3);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rental`
--

DROP TABLE IF EXISTS `rental`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rental` (
  `rental_id` bigint NOT NULL AUTO_INCREMENT,
  `check_in_date` datetime(6) DEFAULT NULL,
  `check_out_date` datetime(6) DEFAULT NULL,
  `guest_guest_id` bigint DEFAULT NULL,
  `room_room_id` bigint DEFAULT NULL,
  `payment` double DEFAULT NULL,
  PRIMARY KEY (`rental_id`),
  KEY `FKhlitkf949o9vc5x8lxf37jf5n` (`guest_guest_id`),
  KEY `FKfhgt5cbnijp2xh0bec97vfot6` (`room_room_id`),
  CONSTRAINT `FKfhgt5cbnijp2xh0bec97vfot6` FOREIGN KEY (`room_room_id`) REFERENCES `room` (`room_id`),
  CONSTRAINT `FKhlitkf949o9vc5x8lxf37jf5n` FOREIGN KEY (`guest_guest_id`) REFERENCES `guest` (`guest_id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rental`
--

LOCK TABLES `rental` WRITE;
/*!40000 ALTER TABLE `rental` DISABLE KEYS */;
INSERT INTO `rental` VALUES (1,'2022-08-01 19:00:00.031000','2022-08-01 20:00:00.031000',8,5,1200000),(2,'2022-08-01 19:00:00.031000','2022-08-01 20:00:00.031000',8,10,300000),(3,'2022-08-03 19:00:00.031000','2022-08-03 20:00:00.031000',8,5,1200000),(4,'2023-08-01 19:00:00.031000','2023-08-01 20:00:00.031000',8,1,700000),(5,'2023-08-01 19:00:00.031000','2023-08-01 20:00:00.031000',8,5,1200000),(6,'2023-12-01 19:00:00.031000','2023-12-01 20:00:00.031000',8,5,1200000),(7,'2023-12-01 19:00:00.031000','2023-12-01 20:00:00.031000',8,9,900000),(8,'2023-12-24 19:00:00.031000','2023-12-24 20:00:00.031000',8,6,1000000),(9,'2021-01-01 19:00:00.031000','2021-01-01 20:00:00.031000',8,10,300000),(10,'2021-03-14 19:00:00.031000','2021-03-14 20:00:00.031000',8,6,1000000),(11,'2021-10-05 19:00:00.031000','2021-10-05 20:00:00.031000',8,10,300000),(12,'2022-04-13 19:00:00.031000','2022-04-03 20:00:00.031000',8,2,500000),(13,'2023-07-30 19:00:00.031000','2023-07-30 20:00:00.031000',8,10,300000),(14,'2023-02-02 19:00:00.031000','2023-02-02 20:00:00.031000',8,4,1000000),(15,'2023-03-08 19:00:00.031000','2023-03-08 20:00:00.031000',8,9,1000000),(56,'2024-04-19 14:08:43.582000','2024-04-19 14:08:48.784000',8,10,300000),(57,'2024-04-19 14:09:34.071000','2024-04-19 14:09:45.944000',8,8,600000),(58,'2024-04-19 14:38:42.996000','2024-04-19 14:58:53.927000',8,9,900000),(59,'2024-04-19 15:02:37.551000','2024-04-19 15:02:56.368000',8,8,600000),(60,'2024-04-19 15:04:19.212000','2024-04-19 16:23:29.370000',8,5,1200000),(61,'2024-04-19 15:04:23.977000','2024-04-19 16:26:28.102000',8,10,300000),(62,'2024-04-19 15:04:30.883000','2024-04-19 16:31:02.930000',8,1,700000),(63,'2024-04-19 16:32:29.816000','2024-04-19 16:32:59.423000',8,5,1200000),(64,'2024-04-19 16:32:33.561000','2024-04-19 16:32:41.429000',8,6,1000000),(65,'2024-04-19 16:34:03.948000','2024-04-19 16:34:32.781000',8,8,600000),(66,'2024-04-19 16:34:08.248000','2024-04-19 16:36:15.092000',8,2,500000),(67,'2024-04-19 16:34:14.197000','2024-04-19 17:04:27.511000',8,4,1000000),(68,'2024-04-19 17:05:10.112000','2024-04-19 17:05:22.842000',8,6,1000000),(69,'2024-04-19 17:05:17.437000','2024-04-19 17:05:55.876000',8,7,800000),(70,'2024-04-19 17:05:31.616000','2024-04-19 22:34:28.229000',8,9,900000),(71,'2024-04-19 22:32:17.005000','2024-04-19 22:32:46.021000',12,8,600000),(72,'2024-04-19 22:33:19.257000','2024-04-19 22:34:50.788000',12,1,700000),(73,'2024-04-19 22:33:24.364000','2024-04-19 22:43:12.853000',12,2,500000),(74,'2024-04-19 22:33:29.479000','2024-04-19 22:43:56.739000',12,4,1000000),(75,'2024-04-19 22:44:12.859000','2024-04-19 22:44:23.189000',12,1,120000),(76,'2024-04-19 22:45:03.478000','2024-04-19 22:45:20.708000',12,2,500000),(77,'2024-04-19 22:46:43.941000','2024-04-19 22:46:51.937000',12,1,120000),(78,'2024-04-19 22:47:41.241000','2024-04-19 22:47:49.947000',12,1,120000),(79,'2024-04-19 22:51:38.800000','2024-04-20 00:03:02.344000',12,7,800000),(80,'2024-04-20 11:37:14.463000','2024-04-20 11:37:22.660000',8,13,200000),(81,'2024-04-20 11:37:33.424000','2024-04-20 11:54:23.138000',8,12,300000),(82,'2024-05-06 14:34:48.906000','2024-05-06 14:35:10.971000',8,1,120000),(83,'2024-05-06 14:35:02.031000','2024-05-06 14:35:17.305000',8,5,1200000),(84,'2024-05-07 14:49:00.321000','2024-05-07 15:03:04.401000',8,1,120000),(85,'2024-05-07 14:49:23.120000','2024-05-07 15:03:10.364000',8,13,200000),(86,'2024-05-07 14:49:38.098000','2024-05-07 15:03:15.592000',8,10,300000),(87,'2024-05-07 15:04:33.462000','2024-05-07 15:46:34.865000',8,14,800000),(88,'2024-05-07 15:47:07.134000','2024-05-07 23:05:43.258000',8,11,500000),(89,'2024-05-07 15:47:19.794000','2024-05-07 15:49:17.642000',8,12,300000),(90,'2024-05-07 15:47:41.244000',NULL,8,2,NULL);
/*!40000 ALTER TABLE `rental` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `room_id` bigint NOT NULL AUTO_INCREMENT,
  `is_empty` varchar(255) DEFAULT NULL,
  `price_day` double DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`room_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,'Trống',120000,'Single'),(2,'Đã SD',500000,'Couple'),(4,'Trống',1000000,'Family'),(5,'Trống',1200000,'Family'),(6,'Trống',1000000,'Couple'),(7,'Trống',800000,'Presidential'),(8,'Trống',600000,'Single'),(9,'Trống',900000,'Presidential'),(10,'Trống',300000,'Couple'),(11,'Trống',500000,'Presidential'),(12,'Trống',300000,'Single'),(13,'Trống',200000,'Single'),(14,'Trống',800000,'Couple');
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `role_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `USER_ROLE_UK` (`user_id`,`role_id`),
  KEY `user_role_fk2` (`role_id`),
  CONSTRAINT `user_role_fk1` FOREIGN KEY (`user_id`) REFERENCES `app_user` (`user_id`),
  CONSTRAINT `user_role_fk2` FOREIGN KEY (`role_id`) REFERENCES `app_role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1,1),(3,2,2),(4,3,2),(5,4,2),(6,5,2),(7,6,2),(8,7,2),(9,8,2),(10,9,2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-07 23:29:29
