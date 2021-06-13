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
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES (1,'7435856909710137',1),(2,'3404653536797584',1),(3,'9898760327223833',2),(4,'6628184771510103',3),(5,'2041664109493761',3),(6,'2895819220853132',3);
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `expenses`
--

LOCK TABLES `expenses` WRITE;
/*!40000 ALTER TABLE `expenses` DISABLE KEYS */;
INSERT INTO `expenses` VALUES ('Clothes'),('Entertainment'),('Food'),('Shoes'),('Travel');
/*!40000 ALTER TABLE `expenses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `incomes`
--

LOCK TABLES `incomes` WRITE;
/*!40000 ALTER TABLE `incomes` DISABLE KEYS */;
INSERT INTO `incomes` VALUES ('Gift'),('Interest'),('Payment'),('Salary'),('Wage');
/*!40000 ALTER TABLE `incomes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
INSERT INTO `transactions` VALUES (1,250,0,'2021-01-11 16:48:47.231939',1),(2,1000.5,1,'2021-02-01 16:48:47.231939',1),(3,11.56,1,'2021-05-08 16:48:47.231939',2),(4,655,0,'2021-05-11 16:48:47.231939',2),(5,20,0,'2021-06-18 16:48:47.231939',3),(12,500,0,'2021-06-12 15:34:26.655631',1),(13,77,0,'2021-06-12 15:50:30.661923',1),(14,5000,0,'2021-06-12 16:09:36.065792',1),(15,20,0,'2021-06-12 17:06:53.837920',1),(16,20000,0,'2021-06-13 16:34:59.664670',3);
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `transactions_expenses`
--

LOCK TABLES `transactions_expenses` WRITE;
/*!40000 ALTER TABLE `transactions_expenses` DISABLE KEYS */;
INSERT INTO `transactions_expenses` VALUES (2,'Clothes'),(3,'Entertainment'),(3,'Food'),(2,'Shoes'),(3,'Travel');
/*!40000 ALTER TABLE `transactions_expenses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `transactions_incomes`
--

LOCK TABLES `transactions_incomes` WRITE;
/*!40000 ALTER TABLE `transactions_incomes` DISABLE KEYS */;
INSERT INTO `transactions_incomes` VALUES (1,'Gift'),(12,'Gift'),(16,'Gift'),(1,'Interest'),(4,'Payment'),(15,'Payment'),(4,'Salary'),(5,'Salary'),(14,'Salary'),(5,'Wage'),(13,'Wage');
/*!40000 ALTER TABLE `transactions_incomes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'liam@gmail.com','Liam'),(2,'noah@gmail.com','Noah'),(3,'oliver@gmail.com','Oliver');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-13 16:39:45
