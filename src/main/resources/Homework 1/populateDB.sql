-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ivan_homework
-- ------------------------------------------------------
-- Server version	5.7.18-log

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
-- Dumping data for table `companies`
--

LOCK TABLES `companies` WRITE;
/*!40000 ALTER TABLE `companies` DISABLE KEYS */;
INSERT INTO `companies` VALUES (1,'google','Mountain View'),(2,'apple','Cupertino'),(3,'goit','Kyiv'),(4,'microsoft','Redmond'),(5,'tesla','Palo Alto'),(6,'oracle','Redwood City'),(7,'luxoft','Kyiv'),(8,'company 1','Kyiv'),(9,'company 2','Palo Alto'),(10,'luxoft','Odesa'),(11,'luxoft','Dnipro'),(12,'company 3','Cupertino'),(13,'company 4','Cupertino'),(14,'company 5','Redmond');
/*!40000 ALTER TABLE `companies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'customer 1','Kyiv'),(2,'customer 2','Kyiv'),(3,'customer 3','Kyiv'),(4,'customer 4','London'),(5,'customer 5','London'),(6,'customer 6','New York'),(7,'customer 7','London'),(8,'customer 1','London'),(9,'customer 1','New York'),(10,'customer 2','New York'),(11,'customer 6','Dubai');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `developer_skills`
--

LOCK TABLES `developer_skills` WRITE;
/*!40000 ALTER TABLE `developer_skills` DISABLE KEYS */;
INSERT INTO `developer_skills` VALUES (7,1),(1,2),(3,2),(4,2),(9,2),(13,2),(16,2),(1,3),(8,3),(18,3),(1,4),(3,4),(4,4),(12,4),(19,4),(21,4),(7,5),(15,5),(2,6),(17,6),(1,7),(7,7),(20,7),(1,8),(3,8),(5,8),(21,8),(1,9),(1,10),(14,11),(3,12),(6,12),(21,14),(8,18),(22,19),(6,20),(5,21),(10,24),(4,26),(13,28),(23,28),(6,30),(11,30);
/*!40000 ALTER TABLE `developer_skills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `developers`
--

LOCK TABLES `developers` WRITE;
/*!40000 ALTER TABLE `developers` DISABLE KEYS */;
INSERT INTO `developers` VALUES (1,'John','Doe',2),(2,'Jane','Roe',1),(3,'Awe','Some',8),(4,'Dev 1','Some Man',8),(5,'Dev 2','Some Man',8),(6,'Dev 3','Some Man',8),(7,'Dev 4','Some Man',8),(8,'Dev 5','Some Man',8),(9,'Dev 6','Some Man',8),(10,'Dev 7','Some Man',8),(11,'Dev 7','Some Man',11),(12,'Dev 3','Some Man',11),(13,'Dev 8','Some Man',11),(14,'Dev 9','Some Man',11),(15,'Dev 10','Some Man',3),(16,'Dev 11','Some Man',3),(17,'Dev 12','Some Man',3),(18,'Dev 13','Some Man',3),(19,'Dev 14','Some Man',5),(20,'Dev 15','Some Man',6),(21,'Dev 15','Some Man',1),(22,'Dev 3','Some Man',1),(23,'Dev 14','Some Man',1);
/*!40000 ALTER TABLE `developers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `project_developers`
--

LOCK TABLES `project_developers` WRITE;
/*!40000 ALTER TABLE `project_developers` DISABLE KEYS */;
INSERT INTO `project_developers` VALUES (1,1),(2,2),(1,3),(1,4),(1,5),(5,5),(1,7),(2,7),(1,8),(2,8),(2,9),(5,10),(5,11),(2,12),(3,13),(1,14),(5,15),(3,17),(2,19),(4,22),(2,23),(4,23);
/*!40000 ALTER TABLE `project_developers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `projectIds`
--

LOCK TABLES `projectIds` WRITE;
/*!40000 ALTER TABLE `projectIds` DISABLE KEYS */;
INSERT INTO `projectIds` VALUES (1,'project','some project',1),(2,'project 1','some project',2),(3,'project 2','some description',3),(4,'project 2','some description',3),(5,'project 3','some description',3),(6,'project 4','some description',6);
/*!40000 ALTER TABLE `projectIds` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `skillIds`
--

LOCK TABLES `skillIds` WRITE;
/*!40000 ALTER TABLE `skillIds` DISABLE KEYS */;
INSERT INTO `skillIds` VALUES (1,'java','junior',100),(2,'java','middle',200),(3,'java','senior',300),(4,'c++','junior',100),(5,'c++','middle',200),(6,'c++','senior',300),(7,'c#','junior',100),(8,'c#','middle',200),(9,'c#','senior',300),(10,'sql','junior',100),(11,'sql','middle',200),(12,'sql','senior',300),(13,'http','junior',100),(14,'http','middle',200),(15,'http','senior',300),(16,'javascript','junior',100),(17,'javascript','middle',200),(18,'javascript','senior',300),(19,'php','junior',100),(20,'php','middle',200),(21,'php','senior',300),(22,'perl','junior',100),(23,'perl','middle',200),(24,'perl','senior',300),(25,'python','junior',100),(26,'python','middle',200),(27,'python','senior',300),(28,'ruby','junior',100),(29,'ruby','middle',200),(30,'ruby','senior',300);
/*!40000 ALTER TABLE `skillIds` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-31 14:22:35
