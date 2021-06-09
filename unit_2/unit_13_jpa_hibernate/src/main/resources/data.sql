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
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES (1,'Java course','Java'),(2,'C# course','C#');
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `grades`
--

LOCK TABLES `grades` WRITE;
/*!40000 ALTER TABLE `grades` DISABLE KEYS */;
INSERT INTO `grades` VALUES (1,9),(2,10),(3,9),(4,8),(5,9),(6,7),(7,8),(8,7),(9,9),(10,8),(11,8),(12,7),(13,8),(14,7),(15,9),(16,8),(17,8),(18,7),(19,4),(20,7),(21,5),(22,6),(23,7),(24,7),(25,5),(26,4),(27,6),(28,7),(29,7),(30,4),(31,4),(32,4),(33,4),(34,6),(35,6),(36,6);
/*!40000 ALTER TABLE `grades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `groups_`
--

LOCK TABLES `groups_` WRITE;
/*!40000 ALTER TABLE `groups_` DISABLE KEYS */;
INSERT INTO `groups_` VALUES (1,'nix-4',1,1),(2,'nix-7',2,2),(3,'nix-5',1,1);
/*!40000 ALTER TABLE `groups_` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `lessons`
--

LOCK TABLES `lessons` WRITE;
/*!40000 ALTER TABLE `lessons` DISABLE KEYS */;
INSERT INTO `lessons` VALUES (1,'2021-05-09 12:42:08.581178',1,1),(2,'2021-05-12 12:42:08.581178',1,2),(3,'2021-05-23 12:42:08.581178',1,3),(4,'2021-06-09 12:42:08.581178',1,4),(5,'2021-06-17 12:42:08.581178',1,5),(6,'2021-06-18 12:42:08.581178',1,6),(7,'2021-05-08 12:42:08.581178',2,7),(8,'2021-05-11 12:42:08.581178',2,8),(9,'2021-05-24 12:42:08.581178',2,9),(10,'2021-06-08 12:42:08.581178',2,10),(11,'2021-06-20 12:42:08.581178',2,11),(12,'2021-05-24 12:42:08.581178',2,12);
/*!40000 ALTER TABLE `lessons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (1,'Liam',1),(2,'Noah',1),(3,'Oliver',2),(4,'Emma',1),(5,'Mia',3),(6,'Henry',2),(7,'Alexander',2),(8,'Benjamin',3),(9,'Isabella',3);
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `students_grades`
--

LOCK TABLES `students_grades` WRITE;
/*!40000 ALTER TABLE `students_grades` DISABLE KEYS */;
INSERT INTO `students_grades` VALUES (1,1),(1,2),(1,3),(1,4),(2,5),(2,6),(2,7),(2,8),(3,9),(3,10),(3,11),(3,12),(4,13),(4,14),(4,15),(4,16),(5,17),(5,18),(5,19),(5,20),(6,21),(6,22),(6,23),(6,24),(7,25),(7,26),(7,27),(7,28),(8,29),(8,30),(8,31),(8,32),(9,33),(9,34),(9,35),(9,36);
/*!40000 ALTER TABLE `students_grades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `subjects`
--

LOCK TABLES `subjects` WRITE;
/*!40000 ALTER TABLE `subjects` DISABLE KEYS */;
INSERT INTO `subjects` VALUES (1,'SQL'),(2,'Hibernate'),(3,'Multithreading'),(4,'Exceptions'),(5,'IO'),(6,'OOP'),(7,'Date API'),(8,'HTTP'),(9,'Generix'),(10,'Logging'),(11,'Clean code'),(12,'Recursion');
/*!40000 ALTER TABLE `subjects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `teachers`
--

LOCK TABLES `teachers` WRITE;
/*!40000 ALTER TABLE `teachers` DISABLE KEYS */;
INSERT INTO `teachers` VALUES (1,'Albert Einstein'),(2,'Aristotle ');
/*!40000 ALTER TABLE `teachers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-09 15:35:22
