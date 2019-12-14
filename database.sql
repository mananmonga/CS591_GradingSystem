-- MySQL dump 10.13  Distrib 5.7.28, for macos10.14 (x86_64)
--
-- Host: localhost    Database: gradingsystem
-- ------------------------------------------------------
-- Server version	5.7.28-log

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
-- Table structure for table `Course`
--

DROP TABLE IF EXISTS `Course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Course` (
  `CourseID` varchar(45) NOT NULL,
  `CourseName` varchar(45) NOT NULL,
  `CourseCode` varchar(45) NOT NULL,
  `Description` varchar(45) DEFAULT NULL,
  `CreateDate` varchar(45) DEFAULT NULL,
  `Curve` varchar(45) DEFAULT NULL,
  `CurveValue` double DEFAULT NULL,
  PRIMARY KEY (`CourseID`),
  UNIQUE KEY `CourseID_UNIQUE` (`CourseID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Course`
--

LOCK TABLES `Course` WRITE;
/*!40000 ALTER TABLE `Course` DISABLE KEYS */;
INSERT INTO `Course` VALUES ('4f74842cee744bc','OOd','CS591','test1','Wed Dec 11 15:48:14 EST 2019','Percentage',3),('f0a2d279056b4a8','algorithm','CS530','homer','Fri Dec 13 20:10:27 EST 2019','Flat',4);
/*!40000 ALTER TABLE `Course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Grade`
--

DROP TABLE IF EXISTS `Grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Grade` (
  `CourseID` varchar(45) NOT NULL,
  `TaskID` varchar(45) NOT NULL,
  `StudentID` varchar(45) NOT NULL,
  `Score` double DEFAULT NULL,
  `Comment` varchar(45) DEFAULT NULL,
  `GradeID` varchar(45) NOT NULL,
  PRIMARY KEY (`GradeID`),
  UNIQUE KEY `GradeID_UNIQUE` (`GradeID`),
  KEY `StudentID_3_idx` (`StudentID`),
  KEY `TaskName_CourseID_idx` (`TaskID`,`StudentID`),
  KEY `CourseID_5_idx` (`CourseID`),
  CONSTRAINT `CourseID_5` FOREIGN KEY (`CourseID`) REFERENCES `Course` (`CourseID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `StudentID_3` FOREIGN KEY (`StudentID`) REFERENCES `Student` (`StudentID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `TaskID_1` FOREIGN KEY (`TaskID`) REFERENCES `task` (`TaskID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Grade`
--

LOCK TABLES `Grade` WRITE;
/*!40000 ALTER TABLE `Grade` DISABLE KEYS */;
INSERT INTO `Grade` VALUES ('f0a2d279056b4a8','549905146365490','U29',43,'rerr','0f9e4864a0de4c0'),('f0a2d279056b4a8','9327389d68a348e','U27',43,'dwq','11da1a27b3f7440'),('f0a2d279056b4a8','b8badd6adb9f4d3','U29',4,'deda','55fea214a240483'),('f0a2d279056b4a8','b8badd6adb9f4d3','U30',4,'rf','7c5df14baf4947b'),('f0a2d279056b4a8','9327389d68a348e','U29',87,'dew','83adfe2e5e3f4f8'),('f0a2d279056b4a8','549905146365490','U27',6,'ewq','a4ac502f1130497'),('f0a2d279056b4a8','9327389d68a348e','U30',23,'dede','af8f08727a4e465'),('f0a2d279056b4a8','b8badd6adb9f4d3','U27',4,'w','ced59689e03c4a5'),('f0a2d279056b4a8','549905146365490','U30',8,'frfr','edb284616875492'),('4f74842cee744bc','e4555034a81843e','cc',20,'gt','hyhyh'),('4f74842cee744bc','62929f71b4fd4b1','cc',76,'d','rfrfr');
/*!40000 ALTER TABLE `Grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Section`
--

DROP TABLE IF EXISTS `Section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Section` (
  `SectionID` int(11) NOT NULL,
  `CourseID` varchar(45) NOT NULL,
  PRIMARY KEY (`SectionID`,`CourseID`),
  KEY `CourseID_1_idx` (`CourseID`),
  CONSTRAINT `CourseID_1` FOREIGN KEY (`CourseID`) REFERENCES `Course` (`CourseID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Section`
--

LOCK TABLES `Section` WRITE;
/*!40000 ALTER TABLE `Section` DISABLE KEYS */;
/*!40000 ALTER TABLE `Section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Student`
--

DROP TABLE IF EXISTS `Student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Student` (
  `StudentID` varchar(45) NOT NULL,
  `Name` varchar(45) NOT NULL,
  PRIMARY KEY (`StudentID`),
  UNIQUE KEY `StudentID_UNIQUE` (`StudentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Student`
--

LOCK TABLES `Student` WRITE;
/*!40000 ALTER TABLE `Student` DISABLE KEYS */;
INSERT INTO `Student` VALUES ('aa','Tian'),('bb','Zhelin2'),('cc','cc'),('U22','Hou'),('U23','You'),('U24','Ding'),('U25','Tan'),('U27','Tian1'),('U29','Zhilin1'),('U30','Dennis1'),('U31','Tian'),('U32','TT'),('U34','TT2'),('U35','TTT');
/*!40000 ALTER TABLE `Student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `StudentCourse`
--

DROP TABLE IF EXISTS `StudentCourse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `StudentCourse` (
  `StudentID` varchar(45) NOT NULL,
  `CourseID` varchar(45) NOT NULL,
  `section` int(11) DEFAULT NULL,
  `Comment` varchar(45) DEFAULT NULL,
  `Bonus` double DEFAULT NULL,
  PRIMARY KEY (`StudentID`,`CourseID`),
  KEY `CourseID_2_idx` (`CourseID`),
  CONSTRAINT `CourseID_2` FOREIGN KEY (`CourseID`) REFERENCES `Course` (`CourseID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `StudentID_4` FOREIGN KEY (`StudentID`) REFERENCES `Student` (`StudentID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `StudentCourse`
--

LOCK TABLES `StudentCourse` WRITE;
/*!40000 ALTER TABLE `StudentCourse` DISABLE KEYS */;
INSERT INTO `StudentCourse` VALUES ('cc','4f74842cee744bc',1,'hard-worker',10),('U27','f0a2d279056b4a8',1,'wdsqw',0),('U29','f0a2d279056b4a8',1,'ed',0),('U30','f0a2d279056b4a8',1,'rfr',0);
/*!40000 ALTER TABLE `StudentCourse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task` (
  `CourseID` varchar(45) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `FullCredit` int(11) DEFAULT NULL,
  `Weight` double DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `Description` varchar(45) DEFAULT NULL,
  `TaskID` varchar(45) NOT NULL,
  `Curve` varchar(45) DEFAULT NULL,
  `CurveValue` double DEFAULT NULL,
  PRIMARY KEY (`CourseID`,`TaskID`),
  UNIQUE KEY `TaskID_UNIQUE` (`TaskID`),
  CONSTRAINT `CourseID_3` FOREIGN KEY (`CourseID`) REFERENCES `Course` (`CourseID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES ('4f74842cee744bc','HW1',100,0.3,'Absolute Grading','test1','62929f71b4fd4b1','Percentage',1),('4f74842cee744bc','Quiz1',90,0.7,'Deduction Grading','test2','e4555034a81843e','Flat',9),('f0a2d279056b4a8','Quiz1',100,0.4,'Deduction Grading','bb','549905146365490','Flat',12),('f0a2d279056b4a8','Quiz2',150,0.3,'Deduction Grading','cc','9327389d68a348e','Percentage',3),('f0a2d279056b4a8','HW1',100,0.3,'Absolute Grading','aa','b8badd6adb9f4d3','Percentage',11);
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-13 20:21:01
