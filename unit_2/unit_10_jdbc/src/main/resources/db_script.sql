-- MySQL dump 10.13  Distrib 8.0.24, for Win64 (x86_64)
--
-- Host: localhost    Database: nix-4
-- ------------------------------------------------------
-- Server version	8.0.24

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
-- Table structure for table `locations`
--

DROP TABLE IF EXISTS `locations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `locations` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(25) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locations`
--

LOCK TABLES `locations` WRITE;
/*!40000 ALTER TABLE `locations` DISABLE KEYS */;
INSERT INTO `locations` VALUES (9,'Atlanta'),(7,'Boston'),(6,'Chicago'),(11,'Dallas'),(4,'Denver'),(12,'Houston'),(5,'Kansas City'),(3,'Los Angeles'),(10,'Miami'),(8,'New York'),(2,'San Francisco'),(1,'Seattle');
/*!40000 ALTER TABLE `locations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `problems`
--

DROP TABLE IF EXISTS `problems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `problems` (
  `id` int NOT NULL AUTO_INCREMENT,
  `from_id` int NOT NULL,
  `to_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `problems_ibfk_1` (`from_id`),
  KEY `problems_ibfk_2` (`to_id`),
  CONSTRAINT `problems_ibfk_1` FOREIGN KEY (`from_id`) REFERENCES `locations` (`id`),
  CONSTRAINT `problems_ibfk_2` FOREIGN KEY (`to_id`) REFERENCES `locations` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `problems`
--

LOCK TABLES `problems` WRITE;
/*!40000 ALTER TABLE `problems` DISABLE KEYS */;
INSERT INTO `problems` VALUES (1,1,10),(2,2,7),(3,3,6),(4,1,2),(5,11,12),(6,3,11),(7,2,9),(8,4,9),(9,11,10),(10,4,6),(11,4,7),(12,11,11);
/*!40000 ALTER TABLE `problems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `routes`
--

DROP TABLE IF EXISTS `routes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `routes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `from_id` int NOT NULL,
  `to_id` int NOT NULL,
  `cost` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `routes_ibfk_1` (`from_id`),
  KEY `routes_ibfk_2` (`to_id`),
  CONSTRAINT `routes_ibfk_1` FOREIGN KEY (`from_id`) REFERENCES `locations` (`id`),
  CONSTRAINT `routes_ibfk_2` FOREIGN KEY (`to_id`) REFERENCES `locations` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `routes`
--

LOCK TABLES `routes` WRITE;
/*!40000 ALTER TABLE `routes` DISABLE KEYS */;
INSERT INTO `routes` VALUES (1,1,2,807),(2,1,4,1331),(3,1,6,2097),(4,2,1,807),(5,2,3,381),(6,2,4,1267),(7,3,2,381),(8,3,4,1015),(9,3,5,1663),(10,3,11,1435),(11,4,1,1331),(12,4,2,1267),(13,4,3,1015),(14,4,5,599),(15,4,6,1003),(16,5,3,1663),(17,5,4,599),(18,5,6,533),(19,5,8,1260),(20,5,9,864),(21,5,11,496),(22,6,1,2097),(23,6,4,1003),(24,6,5,533),(25,6,7,983),(26,6,8,787),(27,7,6,983),(28,7,8,214),(29,8,5,1260),(30,8,6,787),(31,8,7,214),(32,8,9,888),(33,9,5,864),(34,9,8,888),(35,9,10,661),(36,9,11,781),(37,9,12,810),(38,10,9,661),(39,10,12,1187),(40,11,3,1435),(41,11,5,496),(42,11,9,781),(43,11,12,239),(44,12,9,810),(45,12,10,1187),(46,12,11,239);
/*!40000 ALTER TABLE `routes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solutions`
--

DROP TABLE IF EXISTS `solutions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `solutions` (
  `problem_id` int NOT NULL,
  `cost` int DEFAULT NULL,
  PRIMARY KEY (`problem_id`),
  CONSTRAINT `solutions_ibfk_1` FOREIGN KEY (`problem_id`) REFERENCES `problems` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solutions`
--

LOCK TABLES `solutions` WRITE;
/*!40000 ALTER TABLE `solutions` DISABLE KEYS */;
INSERT INTO `solutions` VALUES (1,3455),(2,3253),(3,2018),(4,807),(5,239),(6,1435),(7,2597),(8,1463),(9,1426),(10,1003),(11,1986),(12,0);
/*!40000 ALTER TABLE `solutions` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-25 11:41:17
