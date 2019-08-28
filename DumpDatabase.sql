-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: progettodb
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `autore`
--

DROP TABLE IF EXISTS `autore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `autore` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Nome` varchar(45) NOT NULL,
  `Cognome` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `index2` (`Cognome`),
  KEY `index3` (`Nome`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autore`
--

LOCK TABLES `autore` WRITE;
/*!40000 ALTER TABLE `autore` DISABLE KEYS */;
INSERT INTO `autore` VALUES (1,'Stendhal',NULL),(2,'Zerocalcare',NULL),(3,'Italo','Calvino'),(4,'Stefano','Benni'),(5,'ungaro','Svevo'),(6,'Stephen','King'),(7,'Agatha','Christie'),(8,'Dante','Alighieri'),(9,'Oscar','Wilde'),(10,'Giovanni','Pascoli'),(11,'Giacomo','Leopardi'),(12,'nomenome','cogn'),(13,'nomenome','cogeen'),(16,'nome.3','cognome.3'),(17,'sandro','rinzecchito'),(19,'ciao',NULL),(20,'ci sono',NULL),(21,'Aaanome','Aaacognome'),(22,'Anomenocognome',NULL),(23,'Fulvio','Lapenna'),(24,'Nome.2','Cognome.2'),(25,'Ddd',NULL),(26,'Dddd',NULL),(27,'Sdvsf',NULL),(28,'Sd',NULL),(29,'S',NULL),(30,'Amico di s',NULL),(31,'Sssdfs','Sdfsdf'),(32,'Sdf',NULL),(33,'Piergiorgio','Odifreddi'),(34,'Gianni','Rigamonti'),(35,'Niccolò','Ammaniti'),(36,'R.l.','Stevenson'),(37,'Khaled','Hosseini'),(38,'Mark','Haddon'),(39,'Paolo','Mastrocola'),(40,'Philip','Pullman'),(41,'Ignazio','Silone'),(42,'Fred','Uhlman'),(43,'Carlo','Collodi'),(44,'J.r.r.','Tolkien'),(45,'William','Shakespeare'),(46,'Wilde',NULL),(47,'Edgar allan','Poe');
/*!40000 ALTER TABLE `autore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `datimoderatore`
--

DROP TABLE IF EXISTS `datimoderatore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `datimoderatore` (
  `IDUtente` int(10) unsigned NOT NULL,
  `NumPubb` int(10) unsigned NOT NULL DEFAULT '0',
  `DataPromozione` date NOT NULL,
  `Promotore` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`IDUtente`),
  KEY `fk_Promotore_idx` (`Promotore`),
  CONSTRAINT `fk_DatiModeratori_Utente1` FOREIGN KEY (`IDUtente`) REFERENCES `utente` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Promotore` FOREIGN KEY (`Promotore`) REFERENCES `utente` (`ID`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datimoderatore`
--

LOCK TABLES `datimoderatore` WRITE;
/*!40000 ALTER TABLE `datimoderatore` DISABLE KEYS */;
INSERT INTO `datimoderatore` VALUES (32,13,'2019-08-22',32),(35,3,'2019-08-24',32),(36,3,'2019-08-24',32),(39,0,'2019-08-24',32),(43,0,'2019-08-24',32),(55,0,'2019-08-24',32),(56,0,'2019-08-24',32);
/*!40000 ALTER TABLE `datimoderatore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `datiutente`
--

DROP TABLE IF EXISTS `datiutente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `datiutente` (
  `IDUtente` int(10) unsigned NOT NULL,
  `Nome` varchar(45) NOT NULL,
  `Cognome` varchar(45) NOT NULL,
  `DataNascita` date NOT NULL,
  `LuogoNascita` varchar(100) NOT NULL,
  `Residenza` varchar(100) NOT NULL,
  `Email` varchar(254) NOT NULL,
  PRIMARY KEY (`IDUtente`),
  UNIQUE KEY `DatiUtentecol_UNIQUE` (`Email`),
  CONSTRAINT `fk_DatiUtente_Utente1` FOREIGN KEY (`IDUtente`) REFERENCES `utente` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datiutente`
--

LOCK TABLES `datiutente` WRITE;
/*!40000 ALTER TABLE `datiutente` DISABLE KEYS */;
INSERT INTO `datiutente` VALUES (31,'Francesco','Rossi','1987-08-12','Chieti, Italia','L\'aquila, Italia','rossi@gmail.com'),(32,'Admin','Admin','2004-02-10','Chieti, Italia','Chieti, Italia','admin@gmail.com'),(33,'Riccardo','Verdi','2019-04-09','Milano, Italia','Roma, Italia','verde@gmail.com'),(34,'Giacomo','Gialli','1994-11-10','Torino, Italia','Venezia, Italia','giac@yahoo.it'),(35,'Sandro','Arancio','2002-08-14','Pisa, Italia','Palermo, Italia','arancio@bing.com'),(36,'Carlo','Grigio','1983-08-25','Livorno, Italia','Bologna, Italia','grigio@student.univaq.it'),(37,'Marco','Bianchi','1990-08-22','Roma, Italia','Milano, Italia','bianchi@gmail.com'),(38,'Chiara','Neri','1973-05-23','Bari, Italia','Firenze, Italia','chiara@gmial.com'),(39,'Sara','Blu','1987-02-06','Torino, Italia','Roma, Italia','sara@yahoo.it'),(40,'Giorgia','Cyan','1987-06-11','Firenze, Italia','Venezia, Italia','giorgia@gmail.com'),(41,'Emma','Rosa','2018-06-05','Venezia, Italia','Venezia, Italia','rosa@yahoo.it'),(42,'Gianluca','Marrone','2006-01-12','L\'aquila, Italia','L\'aquila, Italia','marrone@gmail.com'),(43,'Franco','Azzurro','1996-03-05','L\'aquila, Italia','Chieti, Italia','azzurro@gmail.com'),(44,'Claudio','Violaviola','1990-04-10','Milano, Italia','Chieti, Italia','viola@yahoo.it'),(45,'Riccardo','Vero','2001-08-08','L\'aquila, Italia','L\'aquila, Italia','ricky@gmail.com'),(46,'Calimero','Distante','2019-08-07','L\'aquila, Italia','L\'aquila, Italia','dog@gmail.com'),(47,'Francesco','Fartade','1923-08-16','Chieti, Italia','Chieti, Italia','fartade@gmail.com'),(48,'Lorenzo','Miasma','2002-08-20','Palermo, Italia','L\'aquila, Italia','kiasmo@gmail.com'),(49,'Sandro','Deriva','2004-08-17','Milano, Italia','Milano, Italia','sandro@gmail.com'),(50,'Chiara','Zulli','1987-12-16','L\'aquila, Italia','L\'aquila, Italia','specchio@gmail.com'),(51,'Antonio','Nave','1988-11-17','Chieti, Italia','Chieti, Italia','navy@yahoo.it'),(52,'Carlo','Piombo','1999-07-21','Chieti, Italia','Chieti, Italia','piombo@gmail.com'),(53,'Silvia','Oro','1990-02-13','Chieti, Italia','L\'aquila, Italia','silvia@gmail.com'),(54,'Mattia','Rame','1986-08-13','L\'aquila, Italia','L\'aquila, Italia','rame@yahoo.it'),(55,'Flavio','Argento','1967-12-11','Chieti, Italia','L\'aquila, Italia','argento@gmail.com'),(56,'Anna','Bronzo','2019-01-15','L\'aquila, Italia','L\'aquila, Italia','bronzo@gmail.com'),(57,'Monte','Legno','1990-05-22','L\'aquila, Italia','L\'aquila, Italia','legno@bing.com'),(58,'Tiziano','Diamante','1985-02-12','L\'aquila, Italia','L\'aquila, Italia','diamante@gmail.com'),(59,'Sandra','Quarzo','1984-08-09','L\'aquila, Italia','L\'aquila, Italia','quarzo@yahoo.it'),(60,'Domenico','Domenico','1990-08-22','Milano, Italia','Torino, Italia','dominic@gmail.com'),(61,'Antonio','Ferro','1975-08-13','L\'aquila, Italia','L\'aquila, Italia','ferro@gmail.com'),(62,'Carlo','Acciaio','1991-08-22','L\'aquila, Italia','L\'aquila, Italia','acciaio@gmail.com'),(63,'Marco','Rubino','2000-08-25','L\'aquila, Italia','L\'aquila, Italia','rubino@gmail.com'),(64,'Silvia','Zaffiro','1997-08-24','L\'aquila, Italia','L\'aquila, Italia','zaffiro@gmail.com'),(65,'Marta','Smeraldo','2005-01-04','L\'aquila, Italia','L\'aquila, Italia','smeraldo@gmail.com'),(66,'Luigi','Topazio','1999-09-09','L\'aquila, Italia','L\'aquila, Italia','topazio@gmail.com'),(67,'Giorgio','Lunedi','2019-05-02','Teramo, Italia','Pescara, Italia','lunedi@yahoo.it'),(68,'Giovanna','Martedi','1992-12-15','Teramo, Italia','L\'aquila, Italia','martedi@yahoo.it'),(69,'Faro','Mercoledi','1996-08-07','L\'aquila, Italia','Chieti, Italia','mercoledi@bing.it'),(70,'Lorenza','Giovedi','1999-08-24','Pescara, Italia','Pescara, Italia','giovedi@yahoo.it'),(71,'Giorgia','Venerdi','2008-08-21','Pescara, Italia','Pescara, Italia','venerdi@gmail.com'),(72,'Andrea','Sabato','1999-08-10','L\'aquila, Italia','L\'aquila, Italia','sabato@yahoo.it');
/*!40000 ALTER TABLE `datiutente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `like`
--

DROP TABLE IF EXISTS `like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `like` (
  `IDPubblicazione` int(10) unsigned NOT NULL,
  `IDUtente` int(10) unsigned NOT NULL,
  `Timestamp` timestamp NOT NULL,
  PRIMARY KEY (`IDPubblicazione`,`IDUtente`),
  KEY `fk_Pubblicazione_has_Utente1_Utente1_idx` (`IDUtente`),
  KEY `fk_Pubblicazione_has_Utente1_Pubblicazione1_idx` (`IDPubblicazione`),
  CONSTRAINT `fk_Pubblicazione_has_Utente1_Pubblicazione1` FOREIGN KEY (`IDPubblicazione`) REFERENCES `pubblicazione` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Pubblicazione_has_Utente1_Utente1` FOREIGN KEY (`IDUtente`) REFERENCES `utente` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `like`
--

LOCK TABLES `like` WRITE;
/*!40000 ALTER TABLE `like` DISABLE KEYS */;
INSERT INTO `like` VALUES (112,31,'2019-08-26 17:36:12'),(112,32,'2019-08-26 17:36:35'),(112,33,'2019-08-26 17:37:45'),(112,34,'2019-08-26 17:37:46'),(112,35,'2019-08-26 17:37:46'),(112,36,'2019-08-26 17:37:46'),(112,37,'2019-08-26 17:37:46'),(112,38,'2019-08-26 17:37:47'),(112,39,'2019-08-26 17:37:47'),(112,40,'2019-08-26 17:37:47'),(112,41,'2019-08-26 17:37:47'),(112,42,'2019-08-26 17:37:47'),(112,43,'2019-08-26 17:37:45'),(113,32,'2019-08-26 21:42:00'),(113,33,'2019-08-26 17:38:00'),(113,34,'2019-08-26 17:38:00'),(113,35,'2019-08-26 17:38:01'),(113,43,'2019-08-26 17:38:00'),(113,58,'2019-08-26 18:31:52'),(114,36,'2019-08-26 17:38:01'),(114,37,'2019-08-26 17:38:01'),(114,38,'2019-08-26 17:38:02'),(114,39,'2019-08-26 17:38:03'),(115,40,'2019-08-26 17:38:03'),(115,41,'2019-08-26 17:38:03'),(115,42,'2019-08-26 17:38:04'),(116,33,'2019-08-26 17:38:56'),(116,34,'2019-08-26 17:38:57'),(116,35,'2019-08-26 17:38:57'),(116,43,'2019-08-26 17:38:56'),(117,36,'2019-08-26 17:38:57'),(117,37,'2019-08-26 17:38:57'),(117,38,'2019-08-26 17:38:58'),(117,39,'2019-08-26 17:38:58'),(117,40,'2019-08-26 17:38:58'),(118,41,'2019-08-26 17:38:58'),(118,42,'2019-08-26 17:38:59'),(118,44,'2019-08-26 17:38:59'),(118,45,'2019-08-26 17:38:59'),(118,46,'2019-08-26 17:38:59'),(118,47,'2019-08-26 17:39:00'),(118,48,'2019-08-26 17:39:00'),(118,49,'2019-08-26 17:39:00'),(118,50,'2019-08-26 17:39:00'),(119,32,'2019-08-26 21:52:52'),(119,33,'2019-08-26 17:39:29'),(119,34,'2019-08-26 17:39:29'),(119,35,'2019-08-26 17:39:29'),(119,43,'2019-08-26 17:39:29'),(120,36,'2019-08-26 17:39:30'),(120,37,'2019-08-26 17:39:30'),(120,38,'2019-08-26 17:39:30'),(120,39,'2019-08-26 17:39:31'),(120,40,'2019-08-26 17:39:31'),(121,41,'2019-08-26 17:39:31'),(121,42,'2019-08-26 17:39:31'),(121,44,'2019-08-26 17:39:32'),(121,45,'2019-08-26 17:39:32'),(121,46,'2019-08-26 17:39:32'),(121,47,'2019-08-26 17:39:32'),(122,48,'2019-08-26 17:39:33'),(122,49,'2019-08-26 17:39:33'),(122,50,'2019-08-26 17:39:33'),(123,33,'2019-08-26 17:39:55'),(123,43,'2019-08-26 17:39:54'),(124,32,'2019-08-26 21:40:32'),(124,34,'2019-08-26 17:39:55'),(124,35,'2019-08-26 17:39:55'),(124,58,'2019-08-26 18:31:58'),(125,36,'2019-08-26 17:39:55'),(125,37,'2019-08-26 17:39:56'),(125,38,'2019-08-26 17:39:56'),(125,39,'2019-08-26 17:39:56'),(126,32,'2019-08-26 21:47:13'),(126,40,'2019-08-26 17:39:56'),(126,41,'2019-08-26 17:39:57'),(126,42,'2019-08-26 17:39:57'),(126,44,'2019-08-26 17:39:57'),(127,45,'2019-08-26 17:39:57'),(127,46,'2019-08-26 17:39:57'),(127,47,'2019-08-26 17:39:58'),(127,48,'2019-08-26 17:39:58'),(127,51,'2019-08-26 17:40:57'),(127,52,'2019-08-26 17:40:57'),(127,53,'2019-08-26 17:40:58'),(127,54,'2019-08-26 17:40:58'),(128,49,'2019-08-26 17:39:59'),(128,50,'2019-08-26 17:39:59'),(128,55,'2019-08-26 17:40:58'),(128,56,'2019-08-26 17:40:59'),(129,33,'2019-08-26 17:40:54'),(129,34,'2019-08-26 17:40:54'),(129,35,'2019-08-26 17:40:55'),(129,43,'2019-08-26 17:40:54'),(130,36,'2019-08-26 17:40:55'),(130,37,'2019-08-26 17:40:55'),(130,38,'2019-08-26 17:40:56'),(130,39,'2019-08-26 17:40:56'),(130,40,'2019-08-26 17:40:56'),(130,41,'2019-08-26 17:40:56'),(130,42,'2019-08-26 17:40:57'),(130,44,'2019-08-26 17:40:57');
/*!40000 ALTER TABLE `like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `metadati`
--

DROP TABLE IF EXISTS `metadati`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `metadati` (
  `IDPubblicazione` int(10) unsigned NOT NULL,
  `Npag` smallint(5) unsigned NOT NULL,
  `Lingua` varchar(45) NOT NULL,
  `Descrizione` text NOT NULL,
  `Indice` text NOT NULL,
  PRIMARY KEY (`IDPubblicazione`),
  CONSTRAINT `fk_Metadati_Pubblicazione1` FOREIGN KEY (`IDPubblicazione`) REFERENCES `pubblicazione` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `metadati`
--

LOCK TABLES `metadati` WRITE;
/*!40000 ALTER TABLE `metadati` DISABLE KEYS */;
INSERT INTO `metadati` VALUES (112,279,'Italiano','la logica da aristotele a godel','indice'),(113,330,'Italiano','corso di logica rigamonti','indice'),(114,150,'Italiano','io non ho paura di ammaniti','indice'),(115,130,'Inglese','il classico inglese','indice'),(116,341,'Italiano','il cacciatore','indice'),(117,229,'Italiano','giallo','indice'),(118,459,'Italiano','una barca nel bosco','indice'),(119,1074,'Italiano','la trilogia completa','la bussola d\'oro'),(120,169,'Italiano','satira politica','indice'),(121,122,'Italiano','best libro','indice'),(122,111,'Italiano','l\'amico','sabato'),(123,101,'Italiano','classico italiano','indice'),(124,120,'Italiano','descrizione','indice'),(125,1200,'Italiano','il signore degli anelli','indice'),(126,220,'Italiano','il dramma','indicce'),(127,173,'Inglese','è importante l\'honestà','indice'),(128,222,'Italiano','desci','indice'),(129,222,'Italiano','descr','incis'),(130,333,'Inglese','dd','dd');
/*!40000 ALTER TABLE `metadati` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parolachiave`
--

DROP TABLE IF EXISTS `parolachiave`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `parolachiave` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ParolaChiave` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Parola Chiave_UNIQUE` (`ParolaChiave`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parolachiave`
--

LOCK TABLES `parolachiave` WRITE;
/*!40000 ALTER TABLE `parolachiave` DISABLE KEYS */;
INSERT INTO `parolachiave` VALUES (17,'Aaaparola'),(27,'Amore'),(11,'animali'),(46,'Bussola'),(35,'Classici'),(40,'Classico'),(9,'comico'),(51,'Commedia'),(49,'Contadini'),(29,'Creato'),(30,'Crecre'),(33,'Diavolo'),(38,'Drammatico'),(57,'Elfi'),(56,'Fantasia'),(36,'Fantastico'),(32,'Filosofia'),(10,'fummetto'),(23,'Gallina'),(19,'Gatto'),(2,'giallo'),(52,'Guerra'),(37,'Horror'),(60,'Inghilterra'),(22,'Inserisci'),(6,'letteratura'),(31,'Logica'),(34,'Matematica'),(24,'Matteo'),(28,'Milhouse'),(26,'Mistero'),(4,'music'),(58,'Nani'),(54,'Narrazione'),(53,'Nazismo'),(45,'Orso'),(25,'Parola'),(12,'parola.1'),(13,'parola.2'),(14,'parola.3'),(55,'Pinocchio'),(5,'poema'),(48,'Politica'),(42,'Psicologico'),(62,'Raccolta'),(61,'Ragni'),(18,'Ricerca'),(1,'romantico'),(41,'Romanzo'),(39,'Russia'),(47,'Satira'),(3,'sci-fi'),(43,'Scuola'),(8,'storico'),(44,'Sud italia'),(20,'Sushi'),(59,'Teatro'),(21,'Tutto'),(50,'Umorismo');
/*!40000 ALTER TABLE `parolachiave` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pubblicazione`
--

DROP TABLE IF EXISTS `pubblicazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `pubblicazione` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ISBN` char(13) NOT NULL,
  `Titolo` varchar(100) NOT NULL DEFAULT 'Sconosciuto',
  `Editore` varchar(45) NOT NULL DEFAULT 'Sconosciuto',
  `NumLike` int(11) NOT NULL DEFAULT '0',
  `NumRec` int(11) NOT NULL DEFAULT '0',
  `DataPubblicazione` date NOT NULL,
  `DataUltimaModifica` date NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ISBN_UNIQUE` (`ISBN`),
  KEY `bytitle` (`Titolo`) /*!80000 INVISIBLE */
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pubblicazione`
--

LOCK TABLES `pubblicazione` WRITE;
/*!40000 ALTER TABLE `pubblicazione` DISABLE KEYS */;
INSERT INTO `pubblicazione` VALUES (112,'2839472839482','il diavolo in cattedra','et saggi',13,3,'2004-02-10','2019-08-26'),(113,'2399991119283','corso di logica','Bollati Boringhieri',6,0,'2004-04-09','2019-08-26'),(114,'1234567890','io non ho paura','einaudi',4,0,'2001-07-03','2019-08-26'),(115,'0987654321','Dr.Jekyll and Mr.Hyde','wordsworth',3,1,'1979-04-05','2019-08-26'),(116,'2345678987654','il cacciatore di aquiloni','piemme',4,3,'2004-08-02','2019-08-26'),(117,'8374837483','lo strano caso del cane ucciso a mezzanotte','einaudi',5,4,'2004-08-13','2019-08-26'),(118,'3829384938','una barca nel bosco','le fenici',9,5,'2004-08-12','2019-08-26'),(119,'2938473829475','queste oscure materie','salami',5,3,'2004-12-18','2019-08-26'),(120,'2345432345','fontamara','mondadori',5,3,'2001-08-07','2019-08-26'),(121,'3829382938','bar sport','feltrinelli',6,0,'2002-08-09','2019-08-26'),(122,'8392837483','l\'amico ritrovato','feltrinelli',3,0,'2002-12-07','2019-08-26'),(123,'8374637283','le avventure di pinocchio','deagostini',2,1,'1995-08-10','2019-08-26'),(124,'9382039283','il bar sotto il mare','feltrinelli',4,1,'2008-08-16','2019-08-26'),(125,'2837483928','la compagnia dell\'anello','rusconi',4,0,'2005-07-15','2019-08-26'),(126,'9999999999','amleto','feltrinelli',5,0,'2008-08-17','2019-08-26'),(127,'9872349872','l\'importanza di essere onesto','mondadori',8,0,'2007-07-02','2019-08-26'),(128,'2938928374832','il cavaliere inesistente','mondadori',4,0,'2008-08-09','2019-08-26'),(129,'2938293827','il sentiero dei nidi di ragno','mondadori',4,0,'2004-08-13','2019-08-26'),(130,'1384712312','racconti','feltrinelli',8,0,'2019-08-09','2019-08-26');
/*!40000 ALTER TABLE `pubblicazione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recensione`
--

DROP TABLE IF EXISTS `recensione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `recensione` (
  `IDPubblicazione` int(10) unsigned NOT NULL,
  `IDUtente` int(10) unsigned NOT NULL,
  `Descrizione` text NOT NULL,
  `FlagApprovazione` bit(1) NOT NULL DEFAULT b'0',
  `ApprovatoDa` int(10) unsigned DEFAULT NULL,
  `Timestamp` timestamp NOT NULL,
  PRIMARY KEY (`IDPubblicazione`,`IDUtente`),
  KEY `fk_Pubblicazione_has_Utente_Utente1_idx` (`IDUtente`),
  KEY `fk_Pubblicazione_has_Utente_Pubblicazione_idx` (`IDPubblicazione`),
  KEY `fk_approva_idx` (`ApprovatoDa`),
  CONSTRAINT `fk_Pubblicazione_has_Utente_Pubblicazione` FOREIGN KEY (`IDPubblicazione`) REFERENCES `pubblicazione` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Pubblicazione_has_Utente_Utente1` FOREIGN KEY (`IDUtente`) REFERENCES `utente` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_approva` FOREIGN KEY (`ApprovatoDa`) REFERENCES `utente` (`ID`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recensione`
--

LOCK TABLES `recensione` WRITE;
/*!40000 ALTER TABLE `recensione` DISABLE KEYS */;
INSERT INTO `recensione` VALUES (112,32,'molto bello',_binary '',32,'2019-08-26 18:14:20'),(112,33,'molto bello',_binary '',32,'2019-08-26 18:16:57'),(112,34,'molto bello',_binary '',32,'2019-08-26 18:16:57'),(113,32,'molto bello',_binary '\0',NULL,'2019-08-26 18:16:57'),(113,35,'molto bello',_binary '\0',NULL,'2019-08-26 18:16:58'),(113,58,'dfdfgdf',_binary '\0',NULL,'2019-08-26 18:31:50'),(114,36,'molto bello',_binary '\0',NULL,'2019-08-26 18:16:58'),(115,37,'molto bello',_binary '',32,'2019-08-26 18:16:58'),(116,38,'molto bello',_binary '',32,'2019-08-26 18:16:59'),(116,39,'molto bello',_binary '',32,'2019-08-26 18:16:59'),(116,40,'molto bello',_binary '',32,'2019-08-26 18:17:00'),(117,32,'provo',_binary '',32,'2019-08-26 22:13:20'),(117,41,'molto bello',_binary '',32,'2019-08-26 18:17:00'),(117,42,'molto bello',_binary '\0',NULL,'2019-08-26 18:17:00'),(117,43,'molto bello',_binary '',32,'2019-08-26 18:17:01'),(117,44,'molto bello',_binary '',32,'2019-08-26 18:17:01'),(118,32,'barca',_binary '',32,'2019-08-26 22:12:37'),(118,45,'molto bello',_binary '',32,'2019-08-26 18:17:02'),(118,46,'molto bello',_binary '',32,'2019-08-26 18:17:02'),(118,47,'molto bello',_binary '',32,'2019-08-26 18:17:02'),(118,48,'molto bello',_binary '',32,'2019-08-26 18:17:03'),(119,32,'cia',_binary '\0',NULL,'2019-08-26 21:52:51'),(119,49,'molto bello',_binary '\0',NULL,'2019-08-26 18:17:03'),(119,50,'molto bello',_binary '',32,'2019-08-26 18:17:04'),(119,51,'molto bello',_binary '',32,'2019-08-26 18:17:04'),(119,52,'molto bello',_binary '',32,'2019-08-26 18:17:05'),(120,53,'molto bello',_binary '',32,'2019-08-26 18:17:06'),(120,54,'molto bello',_binary '',32,'2019-08-26 18:17:07'),(120,55,'molto bello',_binary '',32,'2019-08-26 18:17:07'),(120,56,'molto bello',_binary '\0',NULL,'2019-08-26 18:17:07'),(121,32,'molto bello',_binary '\0',NULL,'2019-08-26 18:18:05'),(122,33,'molto bello',_binary '\0',NULL,'2019-08-26 18:18:05'),(123,34,'molto bello',_binary '\0',NULL,'2019-08-26 18:18:05'),(123,35,'molto bello',_binary '',32,'2019-08-26 18:18:05'),(124,32,'ho vinto',_binary '',32,'2019-08-26 21:40:42'),(124,36,'molto bello',_binary '\0',NULL,'2019-08-26 18:18:05'),(124,58,'sdfgdfdfg',_binary '\0',NULL,'2019-08-26 18:31:56'),(125,37,'molto bello',_binary '\0',NULL,'2019-08-26 18:18:06'),(126,32,'vinco io',_binary '\0',NULL,'2019-08-26 21:47:11'),(126,38,'molto bello',_binary '\0',NULL,'2019-08-26 18:18:06'),(126,39,'molto bello',_binary '\0',NULL,'2019-08-26 18:18:06'),(126,40,'molto bello',_binary '\0',NULL,'2019-08-26 18:18:06'),(127,41,'molto bello',_binary '\0',NULL,'2019-08-26 18:18:06'),(127,42,'molto bello',_binary '\0',NULL,'2019-08-26 18:18:07'),(127,43,'molto bello',_binary '\0',NULL,'2019-08-26 18:18:07'),(127,44,'molto bello',_binary '\0',NULL,'2019-08-26 18:18:07'),(128,45,'molto bello',_binary '\0',NULL,'2019-08-26 18:18:07'),(128,46,'molto bello',_binary '\0',NULL,'2019-08-26 18:18:07'),(128,47,'molto bello',_binary '\0',NULL,'2019-08-26 18:18:08'),(128,48,'molto bello',_binary '\0',NULL,'2019-08-26 18:18:08'),(129,49,'molto bello',_binary '\0',NULL,'2019-08-26 18:18:08'),(129,50,'molto bello',_binary '\0',NULL,'2019-08-26 18:18:08'),(129,51,'molto bello',_binary '\0',NULL,'2019-08-26 18:18:08'),(129,52,'molto bello',_binary '\0',NULL,'2019-08-26 18:18:09'),(130,32,'ciao',_binary '\0',NULL,'2019-08-26 21:49:27'),(130,53,'molto bello',_binary '\0',NULL,'2019-08-26 18:18:09'),(130,54,'molto bello',_binary '\0',NULL,'2019-08-26 18:18:09'),(130,55,'molto bello',_binary '\0',NULL,'2019-08-26 18:18:10'),(130,56,'molto bello',_binary '\0',NULL,'2019-08-26 18:18:10');
/*!40000 ALTER TABLE `recensione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ristampa`
--

DROP TABLE IF EXISTS `ristampa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ristampa` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `IDPubblicazione` int(10) unsigned NOT NULL,
  `DataRistampa` date NOT NULL,
  `Numero` tinyint(3) unsigned NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_RISTAMPA_Pubblicazione1_idx` (`IDPubblicazione`),
  KEY `Ordine Tempo` (`DataRistampa` DESC) /*!80000 INVISIBLE */,
  CONSTRAINT `fk_RISTAMPA_Pubblicazione1` FOREIGN KEY (`IDPubblicazione`) REFERENCES `pubblicazione` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ristampa`
--

LOCK TABLES `ristampa` WRITE;
/*!40000 ALTER TABLE `ristampa` DISABLE KEYS */;
INSERT INTO `ristampa` VALUES (36,112,'2008-07-02',1),(37,112,'2008-07-18',2),(38,113,'2019-08-07',1),(39,113,'2019-08-08',2),(40,114,'2007-08-21',1),(41,115,'2009-05-17',1),(42,116,'2007-07-12',1),(43,118,'2009-01-09',1);
/*!40000 ALTER TABLE `ristampa` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `validazioneInserimentoRistampeTrigger` BEFORE INSERT ON `ristampa` FOR EACH ROW BEGIN
      DECLARE idPubb INTEGER;
      SET idPubb = NEW.`IdPubblicazione`;
        
        IF NOT EXISTS 	(SELECT * FROM Ristampa R
			WHERE R.IDPubblicazione = idPubb AND R.Numero = NEW.Numero)
		THEN
			IF ( SELECT MAX(R.DataRistampa) as RistampaPrecedente FROM Ristampa R
            			WHERE R.IDPubblicazione = idPubb AND R.Numero < NEW.Numero ) > NEW.DataRistampa
				THEN
					SIGNAL SQLSTATE '45000' SET message_text = "ERRORE: Esiste una ristampa di numero inferiore con data superiore";
				ELSE IF ( SELECT MIN(R.DataRistampa) as RistampaPrecedente FROM Ristampa R
						WHERE R.IDPubblicazione = idPubb AND R.Numero > NEW.Numero ) < NEW.DataRistampa
					THEN
						SIGNAL SQLSTATE '45000' SET message_text = "ERRORE: Esiste una ristampa di numero superiore con data inferiore";
				END IF;
			END IF;
		ELSE 
			SIGNAL SQLSTATE '45000' SET message_text = "La ristampa esiste già";
        END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `validazioneAggiornamentoRistampeTrigger` BEFORE INSERT ON `ristampa` FOR EACH ROW BEGIN
      DECLARE idPubb INTEGER;
      SET idPubb = NEW.`IdPubblicazione`;
        
        
      IF ( SELECT MAX(R.DataRistampa) as RistampaPrecedente FROM Ristampa R
            	WHERE R.IDPubblicazione = idPubb AND R.Numero < NEW.Numero ) > NEW.DataRistampa
          THEN
	SIGNAL SQLSTATE '45000' SET message_text = 
"ERRORE: Esiste una ristampa di numero inferiore con data superiore";
          ELSE IF ( SELECT MIN(R.DataRistampa) as RistampaPrecedente FROM Ristampa R
		WHERE R.IDPubblicazione = idPubb AND R.Numero > NEW.Numero ) < NEW.DataRistampa
	          THEN
		SIGNAL SQLSTATE '45000' SET message_text = 
"ERRORE: Esiste una ristampa di numero superiore con data inferiore";
           END IF;
        END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `ruolo`
--

DROP TABLE IF EXISTS `ruolo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ruolo` (
  `ID` tinyint(3) unsigned NOT NULL,
  `Nome` varchar(45) NOT NULL,
  `Descrizione` text,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Nome_UNIQUE` (`Nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ruolo`
--

LOCK TABLES `ruolo` WRITE;
/*!40000 ALTER TABLE `ruolo` DISABLE KEYS */;
INSERT INTO `ruolo` VALUES (1,'passivo','Utente passivo'),(2,'attivo','Utente attivo'),(3,'moderatore','Utente moderatore'),(4,'amministratore','Utente amministratore'),(5,'superAmministratore','Utente super amministratore');
/*!40000 ALTER TABLE `ruolo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scritto`
--

DROP TABLE IF EXISTS `scritto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `scritto` (
  `IDPubblicazione` int(10) unsigned NOT NULL,
  `IDAutore` int(10) unsigned NOT NULL,
  PRIMARY KEY (`IDPubblicazione`,`IDAutore`),
  KEY `fk_Pubblicazione_has_Autore_Autore1_idx` (`IDAutore`),
  KEY `fk_Pubblicazione_has_Autore_Pubblicazione1_idx` (`IDPubblicazione`),
  CONSTRAINT `fk_Pubblicazione_has_Autore_Autore1` FOREIGN KEY (`IDAutore`) REFERENCES `autore` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Pubblicazione_has_Autore_Pubblicazione1` FOREIGN KEY (`IDPubblicazione`) REFERENCES `pubblicazione` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scritto`
--

LOCK TABLES `scritto` WRITE;
/*!40000 ALTER TABLE `scritto` DISABLE KEYS */;
INSERT INTO `scritto` VALUES (128,3),(129,3),(121,4),(124,4),(112,33),(113,34),(114,35),(115,36),(116,37),(117,38),(118,39),(119,40),(120,41),(122,42),(123,43),(125,44),(126,45),(127,46),(130,47);
/*!40000 ALTER TABLE `scritto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sorgente`
--

DROP TABLE IF EXISTS `sorgente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sorgente` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `IDPubblicazione` int(10) unsigned NOT NULL,
  `URI` varchar(2083) NOT NULL,
  `Tipo` varchar(45) NOT NULL DEFAULT 'Sconosciuto',
  `Formato` varchar(45) NOT NULL DEFAULT 'Sconosciuto',
  `Descrizione` text,
  PRIMARY KEY (`ID`),
  KEY `fk_Sorgente_Pubblicazione1_idx` (`IDPubblicazione`),
  KEY `indiceSuTipo` (`Tipo`),
  CONSTRAINT `fk_Sorgente_Pubblicazione1` FOREIGN KEY (`IDPubblicazione`) REFERENCES `pubblicazione` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sorgente`
--

LOCK TABLES `sorgente` WRITE;
/*!40000 ALTER TABLE `sorgente` DISABLE KEYS */;
INSERT INTO `sorgente` VALUES (34,112,'www.google.com','Download','pdf','dove poter scaricare il libro'),(35,112,'www.copertina.com','Immagine','png','copertina'),(36,113,'wwwww.wwwww','Download','pdf','scarica il libro'),(37,114,'www.copertina.com','Immagine','jpeg','copertina del libro'),(38,115,'www.libro.com','Download','pdf','scarica il libro'),(39,116,'www.download.com','Download','pdf','scarica il libro'),(40,117,'www.com','Video','mp4','intervista all\'autore'),(41,118,'www.it','Download','pdf','download'),(42,120,'www.sito.com','Documento','doc','libro scaricabile'),(43,128,'www','Download','pdf','scarica il libro'),(44,129,'wwwwww','Download','pdf','scrica');
/*!40000 ALTER TABLE `sorgente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `storia`
--

DROP TABLE IF EXISTS `storia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `storia` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `IDPubblicazione` int(10) unsigned DEFAULT NULL,
  `IDUtente` int(10) unsigned NOT NULL,
  `Timestamp` timestamp NOT NULL,
  `Descrizione` varchar(250) NOT NULL,
  `Tipo` tinyint(3) unsigned NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_Pubblicazione_has_Utente_Utente2_idx` (`IDUtente`) /*!80000 INVISIBLE */,
  KEY `fk_Pubblicazione_has_Utente_Pubblicazione1_idx` (`IDPubblicazione`),
  KEY `indextipo` (`Tipo`),
  CONSTRAINT `fk_Pubblicazione_has_Utente_Pubblicazione1` FOREIGN KEY (`IDPubblicazione`) REFERENCES `pubblicazione` (`ID`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_Pubblicazione_has_Utente_Utente2` FOREIGN KEY (`IDUtente`) REFERENCES `utente` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=741 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storia`
--

LOCK TABLES `storia` WRITE;
/*!40000 ALTER TABLE `storia` DISABLE KEYS */;
INSERT INTO `storia` VALUES (440,112,32,'2019-08-26 16:56:24','Admin ha inserito la pubblicazione titolata: il diavolo in cattedra',1),(441,112,32,'2019-08-26 16:56:25','Admin ha aggiunto una ristampa alla pubblicazione : il diavolo in cattedra',3),(442,112,32,'2019-08-26 16:56:25','Admin ha aggiunto una ristampa alla pubblicazione : il diavolo in cattedra',3),(443,112,32,'2019-08-26 16:56:25','Admin ha aggiunto una sorgente alla pubblicazione : il diavolo in cattedra',3),(444,112,32,'2019-08-26 16:56:26','Admin ha aggiunto una sorgente alla pubblicazione : il diavolo in cattedra',3),(445,112,32,'2019-08-26 16:56:26','Admin ha aggiunto l\'autore 33 alla pubblicazione : il diavolo in cattedra',3),(446,112,32,'2019-08-26 16:56:27','Admin ha aggiunto una parola chiave alla pubblicazione : il diavolo in cattedra',3),(447,112,32,'2019-08-26 16:56:27','Admin ha aggiunto una parola chiave alla pubblicazione : il diavolo in cattedra',3),(448,112,32,'2019-08-26 16:56:27','Admin ha aggiunto una parola chiave alla pubblicazione : il diavolo in cattedra',3),(449,112,32,'2019-08-26 16:56:28','Admin ha aggiunto una parola chiave alla pubblicazione : il diavolo in cattedra',3),(450,113,32,'2019-08-26 16:58:47','Admin ha inserito la pubblicazione titolata: corso di logica',1),(451,113,32,'2019-08-26 16:58:47','Admin ha aggiunto una ristampa alla pubblicazione : corso di logica',3),(452,113,32,'2019-08-26 16:58:47','Admin ha aggiunto una ristampa alla pubblicazione : corso di logica',3),(453,113,32,'2019-08-26 16:58:48','Admin ha aggiunto una sorgente alla pubblicazione : corso di logica',3),(454,113,32,'2019-08-26 16:58:48','Admin ha aggiunto l\'autore 34 alla pubblicazione : corso di logica',3),(455,113,32,'2019-08-26 16:58:48','Admin ha aggiunto una parola chiave alla pubblicazione : corso di logica',3),(456,113,32,'2019-08-26 16:58:48','Admin ha aggiunto una parola chiave alla pubblicazione : corso di logica',3),(457,113,32,'2019-08-26 16:58:49','Admin ha aggiunto una parola chiave alla pubblicazione : corso di logica',3),(458,114,32,'2019-08-26 17:01:03','Admin ha inserito la pubblicazione titolata: io non ho paura',1),(459,114,32,'2019-08-26 17:01:03','Admin ha aggiunto una ristampa alla pubblicazione : io non ho paura',3),(460,114,32,'2019-08-26 17:01:03','Admin ha aggiunto una sorgente alla pubblicazione : io non ho paura',3),(461,114,32,'2019-08-26 17:01:04','Admin ha aggiunto l\'autore 35 alla pubblicazione : io non ho paura',3),(462,114,32,'2019-08-26 17:01:04','Admin ha aggiunto una parola chiave alla pubblicazione : io non ho paura',3),(463,114,32,'2019-08-26 17:01:04','Admin ha aggiunto una parola chiave alla pubblicazione : io non ho paura',3),(464,115,32,'2019-08-26 17:03:41','Admin ha inserito la pubblicazione titolata: Dr.Jekyll and Mr.Hyde',1),(465,115,32,'2019-08-26 17:03:42','Admin ha aggiunto una ristampa alla pubblicazione : Dr.Jekyll and Mr.Hyde',3),(466,115,32,'2019-08-26 17:03:42','Admin ha aggiunto una sorgente alla pubblicazione : Dr.Jekyll and Mr.Hyde',3),(467,115,32,'2019-08-26 17:03:42','Admin ha aggiunto l\'autore 36 alla pubblicazione : Dr.Jekyll and Mr.Hyde',3),(468,115,32,'2019-08-26 17:03:42','Admin ha aggiunto una parola chiave alla pubblicazione : Dr.Jekyll and Mr.Hyde',3),(469,115,32,'2019-08-26 17:03:43','Admin ha aggiunto una parola chiave alla pubblicazione : Dr.Jekyll and Mr.Hyde',3),(470,115,32,'2019-08-26 17:03:43','Admin ha aggiunto una parola chiave alla pubblicazione : Dr.Jekyll and Mr.Hyde',3),(471,116,32,'2019-08-26 17:05:50','Admin ha inserito la pubblicazione titolata: il cacciatore di aquiloni',1),(472,116,32,'2019-08-26 17:05:50','Admin ha aggiunto una ristampa alla pubblicazione : il cacciatore di aquiloni',3),(473,116,32,'2019-08-26 17:05:50','Admin ha aggiunto una sorgente alla pubblicazione : il cacciatore di aquiloni',3),(474,116,32,'2019-08-26 17:05:51','Admin ha aggiunto l\'autore 37 alla pubblicazione : il cacciatore di aquiloni',3),(475,116,32,'2019-08-26 17:05:51','Admin ha aggiunto una parola chiave alla pubblicazione : il cacciatore di aquiloni',3),(476,116,32,'2019-08-26 17:05:51','Admin ha aggiunto una parola chiave alla pubblicazione : il cacciatore di aquiloni',3),(477,116,32,'2019-08-26 17:05:52','Admin ha aggiunto una parola chiave alla pubblicazione : il cacciatore di aquiloni',3),(478,116,32,'2019-08-26 17:05:52','Admin ha aggiunto una parola chiave alla pubblicazione : il cacciatore di aquiloni',3),(479,117,32,'2019-08-26 17:07:52','Admin ha inserito la pubblicazione titolata: lo strano caso del cane ucciso a mezzanotte',1),(480,117,32,'2019-08-26 17:07:52','Admin ha aggiunto una sorgente alla pubblicazione : lo strano caso del cane ucciso a mezzanotte',3),(481,117,32,'2019-08-26 17:07:53','Admin ha aggiunto l\'autore 38 alla pubblicazione : lo strano caso del cane ucciso a mezzanotte',3),(482,117,32,'2019-08-26 17:07:53','Admin ha aggiunto una parola chiave alla pubblicazione : lo strano caso del cane ucciso a mezzanotte',3),(483,117,32,'2019-08-26 17:07:53','Admin ha aggiunto una parola chiave alla pubblicazione : lo strano caso del cane ucciso a mezzanotte',3),(484,117,32,'2019-08-26 17:07:53','Admin ha aggiunto una parola chiave alla pubblicazione : lo strano caso del cane ucciso a mezzanotte',3),(485,118,32,'2019-08-26 17:10:04','Admin ha inserito la pubblicazione titolata: una barca nel bosco',1),(486,118,32,'2019-08-26 17:10:05','Admin ha aggiunto una ristampa alla pubblicazione : una barca nel bosco',3),(487,118,32,'2019-08-26 17:10:05','Admin ha aggiunto una sorgente alla pubblicazione : una barca nel bosco',3),(488,118,32,'2019-08-26 17:10:05','Admin ha aggiunto l\'autore 39 alla pubblicazione : una barca nel bosco',3),(489,118,32,'2019-08-26 17:10:06','Admin ha aggiunto una parola chiave alla pubblicazione : una barca nel bosco',3),(490,118,32,'2019-08-26 17:10:06','Admin ha aggiunto una parola chiave alla pubblicazione : una barca nel bosco',3),(491,118,32,'2019-08-26 17:10:06','Admin ha aggiunto una parola chiave alla pubblicazione : una barca nel bosco',3),(492,119,32,'2019-08-26 17:11:48','Admin ha inserito la pubblicazione titolata: queste oscure materie',1),(493,119,32,'2019-08-26 17:11:49','Admin ha aggiunto l\'autore 40 alla pubblicazione : queste oscure materie',3),(494,119,32,'2019-08-26 17:11:49','Admin ha aggiunto una parola chiave alla pubblicazione : queste oscure materie',3),(495,119,32,'2019-08-26 17:11:50','Admin ha aggiunto una parola chiave alla pubblicazione : queste oscure materie',3),(496,119,32,'2019-08-26 17:11:50','Admin ha aggiunto una parola chiave alla pubblicazione : queste oscure materie',3),(497,119,32,'2019-08-26 17:11:50','Admin ha aggiunto una parola chiave alla pubblicazione : queste oscure materie',3),(498,120,32,'2019-08-26 17:14:13','Admin ha inserito la pubblicazione titolata: fontamara',1),(499,120,32,'2019-08-26 17:14:13','Admin ha aggiunto una sorgente alla pubblicazione : fontamara',3),(500,120,32,'2019-08-26 17:14:13','Admin ha aggiunto l\'autore 41 alla pubblicazione : fontamara',3),(501,120,32,'2019-08-26 17:14:14','Admin ha aggiunto una parola chiave alla pubblicazione : fontamara',3),(502,120,32,'2019-08-26 17:14:14','Admin ha aggiunto una parola chiave alla pubblicazione : fontamara',3),(503,120,32,'2019-08-26 17:14:14','Admin ha aggiunto una parola chiave alla pubblicazione : fontamara',3),(504,120,32,'2019-08-26 17:14:14','Admin ha aggiunto una parola chiave alla pubblicazione : fontamara',3),(505,121,32,'2019-08-26 17:16:03','Admin ha inserito la pubblicazione titolata: bar sport',1),(506,121,32,'2019-08-26 17:16:04','Admin ha aggiunto l\'autore 4 alla pubblicazione : bar sport',3),(507,121,32,'2019-08-26 17:16:04','Admin ha aggiunto una parola chiave alla pubblicazione : bar sport',3),(508,121,32,'2019-08-26 17:16:04','Admin ha aggiunto una parola chiave alla pubblicazione : bar sport',3),(509,121,32,'2019-08-26 17:16:04','Admin ha aggiunto una parola chiave alla pubblicazione : bar sport',3),(510,121,32,'2019-08-26 17:16:05','Admin ha aggiunto una parola chiave alla pubblicazione : bar sport',3),(511,122,32,'2019-08-26 17:17:42','Admin ha inserito la pubblicazione titolata: l\'amico ritrovato',1),(512,122,32,'2019-08-26 17:17:43','Admin ha aggiunto l\'autore 42 alla pubblicazione : l\'amico ritrovato',3),(513,122,32,'2019-08-26 17:17:43','Admin ha aggiunto una parola chiave alla pubblicazione : l\'amico ritrovato',3),(514,122,32,'2019-08-26 17:17:43','Admin ha aggiunto una parola chiave alla pubblicazione : l\'amico ritrovato',3),(515,122,32,'2019-08-26 17:17:44','Admin ha aggiunto una parola chiave alla pubblicazione : l\'amico ritrovato',3),(516,123,32,'2019-08-26 17:19:04','Admin ha inserito la pubblicazione titolata: le avventure di pinocchio',1),(517,123,32,'2019-08-26 17:19:05','Admin ha aggiunto l\'autore 43 alla pubblicazione : le avventure di pinocchio',3),(518,123,32,'2019-08-26 17:19:05','Admin ha aggiunto una parola chiave alla pubblicazione : le avventure di pinocchio',3),(519,123,32,'2019-08-26 17:19:05','Admin ha aggiunto una parola chiave alla pubblicazione : le avventure di pinocchio',3),(520,123,32,'2019-08-26 17:19:05','Admin ha aggiunto una parola chiave alla pubblicazione : le avventure di pinocchio',3),(521,124,32,'2019-08-26 17:20:00','Admin ha inserito la pubblicazione titolata: il bar sotto il mare',1),(522,124,32,'2019-08-26 17:20:01','Admin ha aggiunto l\'autore 4 alla pubblicazione : il bar sotto il mare',3),(523,125,35,'2019-08-26 17:22:29','arancio ha inserito la pubblicazione titolata: la compagnia dell\'anello',1),(524,125,35,'2019-08-26 17:22:29','arancio ha aggiunto l\'autore 44 alla pubblicazione : la compagnia dell\'anello',3),(525,125,35,'2019-08-26 17:22:30','arancio ha aggiunto una parola chiave alla pubblicazione : la compagnia dell\'anello',3),(526,125,35,'2019-08-26 17:22:30','arancio ha aggiunto una parola chiave alla pubblicazione : la compagnia dell\'anello',3),(527,125,35,'2019-08-26 17:22:30','arancio ha aggiunto una parola chiave alla pubblicazione : la compagnia dell\'anello',3),(528,125,35,'2019-08-26 17:22:30','arancio ha aggiunto una parola chiave alla pubblicazione : la compagnia dell\'anello',3),(529,126,35,'2019-08-26 17:23:36','arancio ha inserito la pubblicazione titolata: amleto',1),(530,126,35,'2019-08-26 17:23:36','arancio ha aggiunto l\'autore 45 alla pubblicazione : amleto',3),(531,126,35,'2019-08-26 17:23:37','arancio ha aggiunto una parola chiave alla pubblicazione : amleto',3),(532,126,35,'2019-08-26 17:23:37','arancio ha aggiunto una parola chiave alla pubblicazione : amleto',3),(533,127,35,'2019-08-26 17:25:15','arancio ha inserito la pubblicazione titolata: l\'importanza di essere onesto',1),(534,127,35,'2019-08-26 17:25:15','arancio ha aggiunto l\'autore 46 alla pubblicazione : l\'importanza di essere onesto',3),(535,127,35,'2019-08-26 17:25:16','arancio ha aggiunto una parola chiave alla pubblicazione : l\'importanza di essere onesto',3),(536,127,35,'2019-08-26 17:25:16','arancio ha aggiunto una parola chiave alla pubblicazione : l\'importanza di essere onesto',3),(537,127,35,'2019-08-26 17:25:16','arancio ha aggiunto una parola chiave alla pubblicazione : l\'importanza di essere onesto',3),(538,128,36,'2019-08-26 17:30:26','grigio ha inserito la pubblicazione titolata: il cavaliere inesistente',1),(539,128,36,'2019-08-26 17:30:27','grigio ha aggiunto una sorgente alla pubblicazione : il cavaliere inesistente',3),(540,128,36,'2019-08-26 17:30:27','grigio ha aggiunto l\'autore 3 alla pubblicazione : il cavaliere inesistente',3),(541,128,36,'2019-08-26 17:30:27','grigio ha aggiunto una parola chiave alla pubblicazione : il cavaliere inesistente',3),(542,129,36,'2019-08-26 17:31:36','grigio ha inserito la pubblicazione titolata: il sentiero dei nidi di ragno',1),(543,129,36,'2019-08-26 17:31:36','grigio ha aggiunto una sorgente alla pubblicazione : il sentiero dei nidi di ragno',3),(544,129,36,'2019-08-26 17:31:36','grigio ha aggiunto l\'autore 3 alla pubblicazione : il sentiero dei nidi di ragno',3),(545,129,36,'2019-08-26 17:31:37','grigio ha aggiunto una parola chiave alla pubblicazione : il sentiero dei nidi di ragno',3),(546,130,36,'2019-08-26 17:32:31','grigio ha inserito la pubblicazione titolata: racconti',1),(547,130,36,'2019-08-26 17:32:32','grigio ha aggiunto l\'autore 47 alla pubblicazione : racconti',3),(548,130,36,'2019-08-26 17:32:32','grigio ha aggiunto una parola chiave alla pubblicazione : racconti',3),(549,130,36,'2019-08-26 17:32:33','grigio ha aggiunto una parola chiave alla pubblicazione : racconti',3),(550,130,36,'2019-08-26 17:32:33','grigio ha aggiunto una parola chiave alla pubblicazione : racconti',3),(551,112,31,'2019-08-26 17:36:12','rosso ha messo like alla pubblicazione:  il diavolo in cattedra',7),(552,112,32,'2019-08-26 17:36:35','Admin ha messo like alla pubblicazione:  il diavolo in cattedra',7),(553,112,43,'2019-08-26 17:37:45','azzurro ha messo like alla pubblicazione:  il diavolo in cattedra',7),(554,112,33,'2019-08-26 17:37:45','verde ha messo like alla pubblicazione:  il diavolo in cattedra',7),(555,112,34,'2019-08-26 17:37:46','giallo ha messo like alla pubblicazione:  il diavolo in cattedra',7),(556,112,35,'2019-08-26 17:37:46','arancio ha messo like alla pubblicazione:  il diavolo in cattedra',7),(557,112,36,'2019-08-26 17:37:46','grigio ha messo like alla pubblicazione:  il diavolo in cattedra',7),(558,112,37,'2019-08-26 17:37:46',' bianchi ha messo like alla pubblicazione:  il diavolo in cattedra',7),(559,112,38,'2019-08-26 17:37:47','nero ha messo like alla pubblicazione:  il diavolo in cattedra',7),(560,112,39,'2019-08-26 17:37:47','blu ha messo like alla pubblicazione:  il diavolo in cattedra',7),(561,112,40,'2019-08-26 17:37:47','cyan ha messo like alla pubblicazione:  il diavolo in cattedra',7),(562,112,41,'2019-08-26 17:37:47','rosa ha messo like alla pubblicazione:  il diavolo in cattedra',7),(563,112,42,'2019-08-26 17:37:47','marrone ha messo like alla pubblicazione:  il diavolo in cattedra',7),(564,113,43,'2019-08-26 17:38:00','azzurro ha messo like alla pubblicazione:  corso di logica',7),(565,113,33,'2019-08-26 17:38:00','verde ha messo like alla pubblicazione:  corso di logica',7),(566,113,34,'2019-08-26 17:38:00','giallo ha messo like alla pubblicazione:  corso di logica',7),(567,113,35,'2019-08-26 17:38:01','arancio ha messo like alla pubblicazione:  corso di logica',7),(568,114,36,'2019-08-26 17:38:01','grigio ha messo like alla pubblicazione:  io non ho paura',7),(569,114,37,'2019-08-26 17:38:01',' bianchi ha messo like alla pubblicazione:  io non ho paura',7),(570,114,38,'2019-08-26 17:38:02','nero ha messo like alla pubblicazione:  io non ho paura',7),(571,114,39,'2019-08-26 17:38:03','blu ha messo like alla pubblicazione:  io non ho paura',7),(572,115,40,'2019-08-26 17:38:03','cyan ha messo like alla pubblicazione:  Dr.Jekyll and Mr.Hyde',7),(573,115,41,'2019-08-26 17:38:03','rosa ha messo like alla pubblicazione:  Dr.Jekyll and Mr.Hyde',7),(574,115,42,'2019-08-26 17:38:04','marrone ha messo like alla pubblicazione:  Dr.Jekyll and Mr.Hyde',7),(575,116,43,'2019-08-26 17:38:56','azzurro ha messo like alla pubblicazione:  il cacciatore di aquiloni',7),(576,116,33,'2019-08-26 17:38:56','verde ha messo like alla pubblicazione:  il cacciatore di aquiloni',7),(577,116,34,'2019-08-26 17:38:57','giallo ha messo like alla pubblicazione:  il cacciatore di aquiloni',7),(578,116,35,'2019-08-26 17:38:57','arancio ha messo like alla pubblicazione:  il cacciatore di aquiloni',7),(579,117,36,'2019-08-26 17:38:57','grigio ha messo like alla pubblicazione:  lo strano caso del cane ucciso a mezzanotte',7),(580,117,37,'2019-08-26 17:38:57',' bianchi ha messo like alla pubblicazione:  lo strano caso del cane ucciso a mezzanotte',7),(581,117,38,'2019-08-26 17:38:58','nero ha messo like alla pubblicazione:  lo strano caso del cane ucciso a mezzanotte',7),(582,117,39,'2019-08-26 17:38:58','blu ha messo like alla pubblicazione:  lo strano caso del cane ucciso a mezzanotte',7),(583,117,40,'2019-08-26 17:38:58','cyan ha messo like alla pubblicazione:  lo strano caso del cane ucciso a mezzanotte',7),(584,118,41,'2019-08-26 17:38:58','rosa ha messo like alla pubblicazione:  una barca nel bosco',7),(585,118,42,'2019-08-26 17:38:59','marrone ha messo like alla pubblicazione:  una barca nel bosco',7),(586,118,44,'2019-08-26 17:38:59','viola ha messo like alla pubblicazione:  una barca nel bosco',7),(587,118,45,'2019-08-26 17:38:59','ricky66 ha messo like alla pubblicazione:  una barca nel bosco',7),(588,118,46,'2019-08-26 17:38:59','dog1 ha messo like alla pubblicazione:  una barca nel bosco',7),(589,118,47,'2019-08-26 17:39:00','francesco ha messo like alla pubblicazione:  una barca nel bosco',7),(590,118,48,'2019-08-26 17:39:00','kiasmo ha messo like alla pubblicazione:  una barca nel bosco',7),(591,118,49,'2019-08-26 17:39:00','deriva ha messo like alla pubblicazione:  una barca nel bosco',7),(592,118,50,'2019-08-26 17:39:00','specchio ha messo like alla pubblicazione:  una barca nel bosco',7),(593,119,43,'2019-08-26 17:39:29','azzurro ha messo like alla pubblicazione:  queste oscure materie',7),(594,119,33,'2019-08-26 17:39:29','verde ha messo like alla pubblicazione:  queste oscure materie',7),(595,119,34,'2019-08-26 17:39:29','giallo ha messo like alla pubblicazione:  queste oscure materie',7),(596,119,35,'2019-08-26 17:39:29','arancio ha messo like alla pubblicazione:  queste oscure materie',7),(597,120,36,'2019-08-26 17:39:30','grigio ha messo like alla pubblicazione:  fontamara',7),(598,120,37,'2019-08-26 17:39:30',' bianchi ha messo like alla pubblicazione:  fontamara',7),(599,120,38,'2019-08-26 17:39:30','nero ha messo like alla pubblicazione:  fontamara',7),(600,120,39,'2019-08-26 17:39:31','blu ha messo like alla pubblicazione:  fontamara',7),(601,120,40,'2019-08-26 17:39:31','cyan ha messo like alla pubblicazione:  fontamara',7),(602,121,41,'2019-08-26 17:39:31','rosa ha messo like alla pubblicazione:  bar sport',7),(603,121,42,'2019-08-26 17:39:31','marrone ha messo like alla pubblicazione:  bar sport',7),(604,121,44,'2019-08-26 17:39:32','viola ha messo like alla pubblicazione:  bar sport',7),(605,121,45,'2019-08-26 17:39:32','ricky66 ha messo like alla pubblicazione:  bar sport',7),(606,121,46,'2019-08-26 17:39:32','dog1 ha messo like alla pubblicazione:  bar sport',7),(607,121,47,'2019-08-26 17:39:32','francesco ha messo like alla pubblicazione:  bar sport',7),(608,122,48,'2019-08-26 17:39:33','kiasmo ha messo like alla pubblicazione:  l\'amico ritrovato',7),(609,122,49,'2019-08-26 17:39:33','deriva ha messo like alla pubblicazione:  l\'amico ritrovato',7),(610,122,50,'2019-08-26 17:39:33','specchio ha messo like alla pubblicazione:  l\'amico ritrovato',7),(611,123,43,'2019-08-26 17:39:54','azzurro ha messo like alla pubblicazione:  le avventure di pinocchio',7),(612,123,33,'2019-08-26 17:39:55','verde ha messo like alla pubblicazione:  le avventure di pinocchio',7),(613,124,34,'2019-08-26 17:39:55','giallo ha messo like alla pubblicazione:  il bar sotto il mare',7),(614,124,35,'2019-08-26 17:39:55','arancio ha messo like alla pubblicazione:  il bar sotto il mare',7),(615,125,36,'2019-08-26 17:39:55','grigio ha messo like alla pubblicazione:  la compagnia dell\'anello',7),(616,125,37,'2019-08-26 17:39:56',' bianchi ha messo like alla pubblicazione:  la compagnia dell\'anello',7),(617,125,38,'2019-08-26 17:39:56','nero ha messo like alla pubblicazione:  la compagnia dell\'anello',7),(618,125,39,'2019-08-26 17:39:56','blu ha messo like alla pubblicazione:  la compagnia dell\'anello',7),(619,126,40,'2019-08-26 17:39:56','cyan ha messo like alla pubblicazione:  amleto',7),(620,126,41,'2019-08-26 17:39:57','rosa ha messo like alla pubblicazione:  amleto',7),(621,126,42,'2019-08-26 17:39:57','marrone ha messo like alla pubblicazione:  amleto',7),(622,126,44,'2019-08-26 17:39:57','viola ha messo like alla pubblicazione:  amleto',7),(623,127,45,'2019-08-26 17:39:57','ricky66 ha messo like alla pubblicazione:  l\'importanza di essere onesto',7),(624,127,46,'2019-08-26 17:39:57','dog1 ha messo like alla pubblicazione:  l\'importanza di essere onesto',7),(625,127,47,'2019-08-26 17:39:58','francesco ha messo like alla pubblicazione:  l\'importanza di essere onesto',7),(626,127,48,'2019-08-26 17:39:58','kiasmo ha messo like alla pubblicazione:  l\'importanza di essere onesto',7),(627,128,49,'2019-08-26 17:39:59','deriva ha messo like alla pubblicazione:  il cavaliere inesistente',7),(628,128,50,'2019-08-26 17:39:59','specchio ha messo like alla pubblicazione:  il cavaliere inesistente',7),(629,129,43,'2019-08-26 17:40:54','azzurro ha messo like alla pubblicazione:  il sentiero dei nidi di ragno',7),(630,129,33,'2019-08-26 17:40:54','verde ha messo like alla pubblicazione:  il sentiero dei nidi di ragno',7),(631,129,34,'2019-08-26 17:40:54','giallo ha messo like alla pubblicazione:  il sentiero dei nidi di ragno',7),(632,129,35,'2019-08-26 17:40:55','arancio ha messo like alla pubblicazione:  il sentiero dei nidi di ragno',7),(633,130,36,'2019-08-26 17:40:55','grigio ha messo like alla pubblicazione:  racconti',7),(634,130,37,'2019-08-26 17:40:55',' bianchi ha messo like alla pubblicazione:  racconti',7),(635,130,38,'2019-08-26 17:40:56','nero ha messo like alla pubblicazione:  racconti',7),(636,130,39,'2019-08-26 17:40:56','blu ha messo like alla pubblicazione:  racconti',7),(637,130,40,'2019-08-26 17:40:56','cyan ha messo like alla pubblicazione:  racconti',7),(638,130,41,'2019-08-26 17:40:56','rosa ha messo like alla pubblicazione:  racconti',7),(639,130,42,'2019-08-26 17:40:57','marrone ha messo like alla pubblicazione:  racconti',7),(640,130,44,'2019-08-26 17:40:57','viola ha messo like alla pubblicazione:  racconti',7),(641,127,51,'2019-08-26 17:40:57','navy ha messo like alla pubblicazione:  l\'importanza di essere onesto',7),(642,127,52,'2019-08-26 17:40:57','piombo ha messo like alla pubblicazione:  l\'importanza di essere onesto',7),(643,127,53,'2019-08-26 17:40:58','oro ha messo like alla pubblicazione:  l\'importanza di essere onesto',7),(644,127,54,'2019-08-26 17:40:58','rame ha messo like alla pubblicazione:  l\'importanza di essere onesto',7),(645,128,55,'2019-08-26 17:40:58','argento ha messo like alla pubblicazione:  il cavaliere inesistente',7),(646,128,56,'2019-08-26 17:40:59','bronzo ha messo like alla pubblicazione:  il cavaliere inesistente',7),(647,112,32,'2019-08-26 18:14:20','Admin ha inserito una recensione alla pubblicazione : il diavolo in cattedra',5),(648,113,32,'2019-08-26 18:16:57','Admin ha inserito una recensione alla pubblicazione : corso di logica',5),(649,112,33,'2019-08-26 18:16:57','verde ha inserito una recensione alla pubblicazione : il diavolo in cattedra',5),(650,112,34,'2019-08-26 18:16:57','giallo ha inserito una recensione alla pubblicazione : il diavolo in cattedra',5),(651,113,35,'2019-08-26 18:16:58','arancio ha inserito una recensione alla pubblicazione : corso di logica',5),(652,114,36,'2019-08-26 18:16:58','grigio ha inserito una recensione alla pubblicazione : io non ho paura',5),(653,115,37,'2019-08-26 18:16:58',' bianchi ha inserito una recensione alla pubblicazione : Dr.Jekyll and Mr.Hyde',5),(654,116,38,'2019-08-26 18:16:59','nero ha inserito una recensione alla pubblicazione : il cacciatore di aquiloni',5),(655,116,39,'2019-08-26 18:16:59','blu ha inserito una recensione alla pubblicazione : il cacciatore di aquiloni',5),(656,116,40,'2019-08-26 18:17:00','cyan ha inserito una recensione alla pubblicazione : il cacciatore di aquiloni',5),(657,117,41,'2019-08-26 18:17:00','rosa ha inserito una recensione alla pubblicazione : lo strano caso del cane ucciso a mezzanotte',5),(658,117,42,'2019-08-26 18:17:00','marrone ha inserito una recensione alla pubblicazione : lo strano caso del cane ucciso a mezzanotte',5),(659,117,43,'2019-08-26 18:17:01','azzurro ha inserito una recensione alla pubblicazione : lo strano caso del cane ucciso a mezzanotte',5),(660,117,44,'2019-08-26 18:17:01','viola ha inserito una recensione alla pubblicazione : lo strano caso del cane ucciso a mezzanotte',5),(661,118,45,'2019-08-26 18:17:02','ricky66 ha inserito una recensione alla pubblicazione : una barca nel bosco',5),(662,118,46,'2019-08-26 18:17:02','dog1 ha inserito una recensione alla pubblicazione : una barca nel bosco',5),(663,118,47,'2019-08-26 18:17:02','francesco ha inserito una recensione alla pubblicazione : una barca nel bosco',5),(664,118,48,'2019-08-26 18:17:03','kiasmo ha inserito una recensione alla pubblicazione : una barca nel bosco',5),(665,119,49,'2019-08-26 18:17:03','deriva ha inserito una recensione alla pubblicazione : queste oscure materie',5),(666,119,50,'2019-08-26 18:17:04','specchio ha inserito una recensione alla pubblicazione : queste oscure materie',5),(667,119,51,'2019-08-26 18:17:04','navy ha inserito una recensione alla pubblicazione : queste oscure materie',5),(668,119,52,'2019-08-26 18:17:05','piombo ha inserito una recensione alla pubblicazione : queste oscure materie',5),(669,120,53,'2019-08-26 18:17:06','oro ha inserito una recensione alla pubblicazione : fontamara',5),(670,120,54,'2019-08-26 18:17:07','rame ha inserito una recensione alla pubblicazione : fontamara',5),(671,120,55,'2019-08-26 18:17:07','argento ha inserito una recensione alla pubblicazione : fontamara',5),(672,120,56,'2019-08-26 18:17:07','bronzo ha inserito una recensione alla pubblicazione : fontamara',5),(673,121,32,'2019-08-26 18:18:05','Admin ha inserito una recensione alla pubblicazione : bar sport',5),(674,122,33,'2019-08-26 18:18:05','verde ha inserito una recensione alla pubblicazione : l\'amico ritrovato',5),(675,123,34,'2019-08-26 18:18:05','giallo ha inserito una recensione alla pubblicazione : le avventure di pinocchio',5),(676,123,35,'2019-08-26 18:18:05','arancio ha inserito una recensione alla pubblicazione : le avventure di pinocchio',5),(677,124,36,'2019-08-26 18:18:05','grigio ha inserito una recensione alla pubblicazione : il bar sotto il mare',5),(678,125,37,'2019-08-26 18:18:06',' bianchi ha inserito una recensione alla pubblicazione : la compagnia dell\'anello',5),(679,126,38,'2019-08-26 18:18:06','nero ha inserito una recensione alla pubblicazione : amleto',5),(680,126,39,'2019-08-26 18:18:06','blu ha inserito una recensione alla pubblicazione : amleto',5),(681,126,40,'2019-08-26 18:18:06','cyan ha inserito una recensione alla pubblicazione : amleto',5),(682,127,41,'2019-08-26 18:18:06','rosa ha inserito una recensione alla pubblicazione : l\'importanza di essere onesto',5),(683,127,42,'2019-08-26 18:18:07','marrone ha inserito una recensione alla pubblicazione : l\'importanza di essere onesto',5),(684,127,43,'2019-08-26 18:18:07','azzurro ha inserito una recensione alla pubblicazione : l\'importanza di essere onesto',5),(685,127,44,'2019-08-26 18:18:07','viola ha inserito una recensione alla pubblicazione : l\'importanza di essere onesto',5),(686,128,45,'2019-08-26 18:18:07','ricky66 ha inserito una recensione alla pubblicazione : il cavaliere inesistente',5),(687,128,46,'2019-08-26 18:18:07','dog1 ha inserito una recensione alla pubblicazione : il cavaliere inesistente',5),(688,128,47,'2019-08-26 18:18:08','francesco ha inserito una recensione alla pubblicazione : il cavaliere inesistente',5),(689,128,48,'2019-08-26 18:18:08','kiasmo ha inserito una recensione alla pubblicazione : il cavaliere inesistente',5),(690,129,49,'2019-08-26 18:18:08','deriva ha inserito una recensione alla pubblicazione : il sentiero dei nidi di ragno',5),(691,129,50,'2019-08-26 18:18:08','specchio ha inserito una recensione alla pubblicazione : il sentiero dei nidi di ragno',5),(692,129,51,'2019-08-26 18:18:08','navy ha inserito una recensione alla pubblicazione : il sentiero dei nidi di ragno',5),(693,129,52,'2019-08-26 18:18:09','piombo ha inserito una recensione alla pubblicazione : il sentiero dei nidi di ragno',5),(694,130,53,'2019-08-26 18:18:09','oro ha inserito una recensione alla pubblicazione : racconti',5),(695,130,54,'2019-08-26 18:18:09','rame ha inserito una recensione alla pubblicazione : racconti',5),(696,130,55,'2019-08-26 18:18:10','argento ha inserito una recensione alla pubblicazione : racconti',5),(697,130,56,'2019-08-26 18:18:10','bronzo ha inserito una recensione alla pubblicazione : racconti',5),(698,124,58,'2019-08-26 18:29:15','diamante ha inserito una recensione alla pubblicazione : il bar sotto il mare',5),(699,124,58,'2019-08-26 18:29:18','diamante ha messo like alla pubblicazione:  il bar sotto il mare',7),(700,112,32,'2019-08-26 18:29:56','è stata approvata la recensione di: Admin del libro: il diavolo in cattedra',4),(701,112,32,'2019-08-26 18:30:02','è stata approvata la recensione di: verde del libro: il diavolo in cattedra',4),(702,112,32,'2019-08-26 18:30:08','è stata approvata la recensione di: giallo del libro: il diavolo in cattedra',4),(703,116,32,'2019-08-26 18:30:13','è stata approvata la recensione di: nero del libro: il cacciatore di aquiloni',4),(704,117,32,'2019-08-26 18:30:18','è stata approvata la recensione di: viola del libro: lo strano caso del cane ucciso a mezzanotte',4),(705,118,32,'2019-08-26 18:30:22','è stata approvata la recensione di: kiasmo del libro: una barca nel bosco',4),(706,117,32,'2019-08-26 18:30:25','è stata approvata la recensione di: rosa del libro: lo strano caso del cane ucciso a mezzanotte',4),(707,116,32,'2019-08-26 18:30:27','è stata approvata la recensione di: cyan del libro: il cacciatore di aquiloni',4),(708,115,32,'2019-08-26 18:30:30','è stata approvata la recensione di:  bianchi del libro: Dr.Jekyll and Mr.Hyde',4),(709,116,32,'2019-08-26 18:30:33','è stata approvata la recensione di: blu del libro: il cacciatore di aquiloni',4),(710,120,32,'2019-08-26 18:30:35','è stata approvata la recensione di: argento del libro: fontamara',4),(711,117,32,'2019-08-26 18:30:38','è stata approvata la recensione di: azzurro del libro: lo strano caso del cane ucciso a mezzanotte',4),(712,119,32,'2019-08-26 18:30:41','è stata approvata la recensione di: specchio del libro: queste oscure materie',4),(713,124,32,'2019-08-26 18:30:44','è stata approvata la recensione di: diamante del libro: il bar sotto il mare',4),(714,118,32,'2019-08-26 18:30:46','è stata approvata la recensione di: francesco del libro: una barca nel bosco',4),(715,118,32,'2019-08-26 18:31:11','è stata approvata la recensione di: dog1 del libro: una barca nel bosco',4),(716,118,32,'2019-08-26 18:31:13','è stata approvata la recensione di: ricky66 del libro: una barca nel bosco',4),(717,120,32,'2019-08-26 18:31:16','è stata approvata la recensione di: oro del libro: fontamara',4),(718,119,32,'2019-08-26 18:31:20','è stata approvata la recensione di: piombo del libro: queste oscure materie',4),(719,124,58,'2019-08-26 18:31:43','diamante ha eliminato la propria recensione per la pubblicazione:  il bar sotto il mare',6),(720,113,58,'2019-08-26 18:31:50','diamante ha inserito una recensione alla pubblicazione : corso di logica',5),(721,113,58,'2019-08-26 18:31:52','diamante ha messo like alla pubblicazione:  corso di logica',7),(722,124,58,'2019-08-26 18:31:56','diamante ha inserito una recensione alla pubblicazione : il bar sotto il mare',5),(723,124,58,'2019-08-26 18:31:57','diamante ha rimosso il like alla pubblicazione : il bar sotto il mare',8),(724,124,58,'2019-08-26 18:31:58','diamante ha messo like alla pubblicazione:  il bar sotto il mare',7),(725,124,32,'2019-08-26 21:40:32','Admin ha messo like alla pubblicazione:  il bar sotto il mare',7),(726,124,32,'2019-08-26 21:40:42','Admin ha inserito una recensione alla pubblicazione : il bar sotto il mare',5),(727,120,32,'2019-08-26 21:41:08','è stata approvata la recensione di: rame del libro: fontamara',4),(728,113,32,'2019-08-26 21:42:00','Admin ha messo like alla pubblicazione:  corso di logica',7),(729,119,32,'2019-08-26 21:42:16','è stata approvata la recensione di: navy del libro: queste oscure materie',4),(730,124,32,'2019-08-26 21:42:33','è stata approvata la recensione di: Admin del libro: il bar sotto il mare',4),(731,123,32,'2019-08-26 21:42:38','è stata approvata la recensione di: arancio del libro: le avventure di pinocchio',4),(732,126,32,'2019-08-26 21:47:11','Admin ha inserito una recensione alla pubblicazione : amleto',5),(733,126,32,'2019-08-26 21:47:13','Admin ha messo like alla pubblicazione:  amleto',7),(734,130,32,'2019-08-26 21:49:27','Admin ha inserito una recensione alla pubblicazione : racconti',5),(735,119,32,'2019-08-26 21:52:51','Admin ha inserito una recensione alla pubblicazione : queste oscure materie',5),(736,119,32,'2019-08-26 21:52:52','Admin ha messo like alla pubblicazione:  queste oscure materie',7),(737,118,32,'2019-08-26 22:12:37','Admin ha inserito una recensione alla pubblicazione : una barca nel bosco',5),(738,118,32,'2019-08-26 22:12:38','è stata approvata la recensione di: Admin del libro: una barca nel bosco',4),(739,117,32,'2019-08-26 22:13:20','Admin ha inserito una recensione alla pubblicazione : lo strano caso del cane ucciso a mezzanotte',5),(740,117,32,'2019-08-26 22:13:21','è stata approvata la recensione di: Admin del libro: lo strano caso del cane ucciso a mezzanotte',4);
/*!40000 ALTER TABLE `storia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tag` (
  `IDPubblicazione` int(10) unsigned NOT NULL,
  `IDParolaChiave` int(10) unsigned NOT NULL,
  PRIMARY KEY (`IDPubblicazione`,`IDParolaChiave`),
  KEY `fk_Pubblicazione_has_Parole Chiave_Parole Chiave1_idx` (`IDParolaChiave`),
  KEY `fk_Pubblicazione_has_Parole Chiave_Pubblicazione1_idx` (`IDPubblicazione`),
  CONSTRAINT `fk_Pubblicazione_has_Parole Chiave_Parole Chiave1` FOREIGN KEY (`IDParolaChiave`) REFERENCES `parolachiave` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Pubblicazione_has_Parole Chiave_Pubblicazione1` FOREIGN KEY (`IDPubblicazione`) REFERENCES `pubblicazione` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (114,6),(116,8),(115,26),(119,26),(112,31),(113,31),(112,32),(113,32),(112,33),(112,34),(113,34),(114,35),(120,35),(121,35),(123,35),(125,35),(128,35),(130,35),(115,36),(119,36),(115,37),(130,37),(116,38),(122,38),(126,38),(116,39),(116,40),(117,40),(117,41),(117,42),(118,42),(118,43),(118,44),(119,45),(119,46),(120,47),(121,47),(120,48),(120,49),(121,50),(121,51),(127,51),(122,52),(122,53),(123,54),(123,55),(125,56),(125,57),(125,58),(126,59),(127,59),(127,60),(129,61),(130,62);
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS `utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `utente` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Nickname` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `Ruolo` tinyint(3) unsigned NOT NULL DEFAULT '1',
  `UtenteEliminato` bit(1) NOT NULL DEFAULT b'0',
  `DataUltimaRecensione` date DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Email_UNIQUE` (`Nickname`),
  KEY `indextipo` (`Ruolo`) /*!80000 INVISIBLE */,
  CONSTRAINT `fk_Ruolo` FOREIGN KEY (`Ruolo`) REFERENCES `ruolo` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES (31,'rosso','05f503102d5dc2cbf0df5aad0ecf3c23',1,_binary '\0',NULL),(32,'Admin','f6fdffe48c908deb0f4c3bd36c032e72',5,_binary '\0','2019-08-27'),(33,'verde','c46d488c6ae1d9c66a7c28f0296256fc',2,_binary '\0','2019-08-26'),(34,'giallo','fe3c13e931b8594b0bd052ba66935fe0',2,_binary '\0','2019-08-26'),(35,'arancio','a8b8ccfd1441c74fdcb2e0a3a772c87c',3,_binary '\0','2019-08-26'),(36,'grigio','0cfdb977ee461ac3be02c2ffafb15782',3,_binary '\0','2019-08-26'),(37,' bianchi','488a2bf21038e0684d3391f05f459817',2,_binary '\0','2019-08-26'),(38,'nero','b916459b359f9f00ff521049126cfc75',2,_binary '\0','2019-08-26'),(39,'blu','c5da05ffd8e3739a988a8b7f1a74a4fd',4,_binary '\0','2019-08-26'),(40,'cyan','b2becaa85d36d2aa97105f144dce705a',2,_binary '\0','2019-08-26'),(41,'rosa','e6ae4f9b46303106db282d1df173065f',2,_binary '\0','2019-08-26'),(42,'marrone','c0521e6975b7da3e6daff50c3cd67ea8',2,_binary '\0','2019-08-26'),(43,'azzurro','b240cbe294d447be5cb03dc4eb2b3a93',4,_binary '\0','2019-08-26'),(44,'viola','1068b8a92265037027c3ca95027f45f1',2,_binary '\0','2019-08-26'),(45,'ricky66','f2df47e7dbdab4727e8310f345dd2ba5',2,_binary '\0','2019-08-26'),(46,'dog1','44ebbc2f18a4f18612c4e1ab88962de9',2,_binary '\0','2019-08-26'),(47,'francesco','0581938f0767a65b373cea80e905c25f',2,_binary '\0','2019-08-26'),(48,'kiasmo','5c6d282a24cb9b851c6dac595495dc9f',2,_binary '\0','2019-08-26'),(49,'deriva','e4f0871fa2cc02e20ea3a06f850320d2',2,_binary '\0','2019-08-26'),(50,'specchio','64f902a814419a9775ff2625c6799bc2',2,_binary '\0','2019-08-26'),(51,'navy','a846df121c4b1e7a22815d1bc8e984ec',2,_binary '\0','2019-08-26'),(52,'piombo','7e6fb3cf6c72a02338ebcf95dc8597b6',2,_binary '\0','2019-08-26'),(53,'oro','0c4a5c79150668eebbfef98d16b5408d',2,_binary '\0','2019-08-26'),(54,'rame','4195b3950c09ed4774af0c0e04235e94',2,_binary '\0','2019-08-26'),(55,'argento','2bb2d62ec1e47e6a13a3c8ee98123ab4',4,_binary '\0','2019-08-26'),(56,'bronzo','cc2dd4feea777e2cceca27a9c9b81d3a',4,_binary '\0','2019-08-26'),(57,'legno','2796b7c77483bfc210ca2727d31ca09a',1,_binary '\0',NULL),(58,'diamante','b28307049aa6c6b722c22bf282b29c96',2,_binary '\0','2019-08-26'),(59,'quarzo','3b2e5d904a97e7c0bcbacd208a08a08d',1,_binary '\0',NULL),(60,'dominic','b7da9cb5477f3909b19e1ad5164d194f',1,_binary '\0',NULL),(61,'ferro','ddf158c534a5f26127cd787abbcc8aa5',1,_binary '\0',NULL),(62,'acciaio','adb66edadf6621f09aec748107f8c45f',1,_binary '\0',NULL),(63,'rubino','79bd519f7ac90f9def01fc4333c94359',1,_binary '\0',NULL),(64,'zaffiro','b5c2c3d42c2e9a90b971fee4730170c2',1,_binary '\0',NULL),(65,'smeraldo','6a48c4d1da85e66f1dcf6f77a713ed28',1,_binary '\0',NULL),(66,'topazio','66a5cff76cf2da2f2365a1acdea9cb9b',1,_binary '\0',NULL),(67,'lunedi','ae7be0a9e04cc49d717f4d4944d224fe',1,_binary '\0',NULL),(68,'martedi','be63d0581c53d6688c901a1ddbde71ad',1,_binary '\0',NULL),(69,'mercoledi','06b7e90b8d3bd344d682b982a607922b',1,_binary '\0',NULL),(70,'giovedi','c19d2024a5d23720f99702ebb6c483d2',1,_binary '\0',NULL),(71,'venerdi','1ea1b393ab8205b7e7d2e82d41b76bfc',1,_binary '\0',NULL),(72,'sabato','19b405fc2c4450d18dbc5c086c4629f8',1,_binary '\0',NULL);
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'progettodb'
--
/*!50106 SET @save_time_zone= @@TIME_ZONE */ ;
/*!50106 DROP EVENT IF EXISTS `PassaggioAttivoPassivo` */;
DELIMITER ;;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;;
/*!50003 SET character_set_client  = utf8mb4 */ ;;
/*!50003 SET character_set_results = utf8mb4 */ ;;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;;
/*!50003 SET @saved_time_zone      = @@time_zone */ ;;
/*!50003 SET time_zone             = '+02:00' */ ;;
/*!50106 CREATE*/ /*!50117 DEFINER=`root`@`localhost`*/ /*!50106 EVENT `PassaggioAttivoPassivo` ON SCHEDULE EVERY 1 WEEK STARTS '2019-07-18 03:00:00' ON COMPLETION NOT PRESERVE ENABLE DO UPDATE utente SET Ruolo = 1 WHERE Ruolo = 2 AND datediff(CURDATE(), DataUltimaRecensione ) > 60 */ ;;
/*!50003 SET time_zone             = @saved_time_zone */ ;;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;;
/*!50003 SET character_set_client  = @saved_cs_client */ ;;
/*!50003 SET character_set_results = @saved_cs_results */ ;;
/*!50003 SET collation_connection  = @saved_col_connection */ ;;
DELIMITER ;
/*!50106 SET TIME_ZONE= @save_time_zone */ ;

--
-- Dumping routines for database 'progettodb'
--
/*!50003 DROP PROCEDURE IF EXISTS `ApprovazioneRecensione` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ApprovazioneRecensione`( IDAppr INT, IDPubb INT, IDUtnt INT)
BEGIN 
DECLARE tit VARCHAR(100);
DECLARE nick VARCHAR(45);
SELECT NickName into nick FROM utente WHERE ID = IDUtnt;
SELECT Titolo into tit FROM pubblicazione WHERE ID = idPubb;
	
IF (  ( SELECT FlagApprovazione FROM Recensione r WHERE  r.IDPubblicazione = IDPubb AND r.IDUtente = IDUtnt ) = 0 ) THEN
UPDATE Recensione r SET r.FlagApprovazione = 1 WHERE r.IDPubblicazione = IDPubb AND r.IDUtente = IDUtnt;
UPDATE Recensione r SET r.ApprovatoDa = IDAppr WHERE r.IDPubblicazione = IDPubb       AND r.IDUtente = IDUtnt;

INSERT INTO Storia (IDPubblicazione, IDUtente, `Timestamp`, Descrizione, Tipo) 	VALUES (IDPubb, IDAppr, now(), concat( "è stata approvata la recensione di: ", nick, " del libro: ", tit ), 4 );

	UPDATE Pubblicazione P SET P.NumRec = P.NumRec + 1 WHERE P.ID = IDPubb;
END IF;	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delAutore` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delAutore`( idPubb INT, ida INT, idUtente INT )
BEGIN
	DECLARE nick VARCHAR(45);
	DECLARE tit VARCHAR(100);
    DECLARE stamp DATETIME;
    SET stamp = NOW();
	SELECT NickName into nick FROM utente WHERE ID = idUtente;
	SELECT Titolo into tit FROM pubblicazione WHERE ID = idPubb;

	DELETE FROM scritto WHERE scritto.IDPubblicazione = idPubb AND scritto.IDAutore = ida;
	 
	UPDATE pubblicazione p SET p.DataUltimaModifica = DATE(stamp) WHERE p.ID = idPubb;

	INSERT INTO storia ( `IDPubblicazione`, `IDUtente`, `Timestamp`, `Descrizione`, `Tipo` ) VALUES
	( idPubb, idUtente, stamp, CONCAT( nick, " ha rimosso un autore alla pubblicazione : ", tit), 3);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delCascadeAutore` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delCascadeAutore`(IN idAut INT, IN idUtente INT)
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE v_idPubb INT;
    DECLARE v_idAutore INT;
    DECLARE cursore CURSOR FOR (SELECT * FROM Scritto s WHERE idAut = s.IDAutore);
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    OPEN cursore;
    
    ciclo: LOOP
		FETCH cursore INTO v_idPubb, v_idAutore;
			IF(done = TRUE) THEN 
				LEAVE ciclo;
			END IF;
            
			-- eliminazione degli autori e scrittura su storia
			CALL delAutore(v_idPubb, idAut, idUtente);
	END LOOP;
    CLOSE cursore;    
    DELETE FROM Autore WHERE ID = idAut;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delCascadeTag` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delCascadeTag`(IN idPC INT, IN idUtente INT)
BEGIN		
    DECLARE done INT DEFAULT FALSE;
    DECLARE v_idPubb INT;
    DECLARE v_idPC INT;
    DECLARE cursore CURSOR FOR (SELECT * FROM Tag t WHERE idPC = t.IDParolaChiave);
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    OPEN cursore;
    
    ciclo: LOOP
		FETCH cursore INTO v_idPubb, v_idPC;
			IF(done = TRUE) THEN 
				LEAVE ciclo;
			END IF;
            
			-- eliminazione degli autori e scrittura su storia
			CALL delParolaChiave(v_idPubb, idPC, idUtente);
	END LOOP;
    CLOSE cursore;
    
    DELETE FROM ParolaChiave WHERE ID = idPC;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delLike` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delLike`( idUtnt INT , idPubb INT )
BEGIN 
	
DECLARE tit VARCHAR(100);
DECLARE nick VARCHAR(45);

SELECT NickName into nick FROM utente WHERE ID = idUtnt;
SELECT Titolo into tit FROM pubblicazione WHERE ID = idPubb;

DELETE FROM `Like` WHERE `Like`.IDUtente = idUtnt AND `Like`.IDPubblicazione = idPubb;

	INSERT INTO storia ( `IDPubblicazione`, `IDUtente`, `Timestamp`, `Descrizione`, `Tipo` ) VALUES
	( idPubb, idUtnt, now(), CONCAT( nick, " ha rimosso il like alla pubblicazione : ", tit), 8);

UPDATE Pubblicazione P SET P.NumLike = P.NumLike - 1 WHERE P.ID = idPubb;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delParolaChiave` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delParolaChiave`( idPubb INT, idpc INT, idUtente INT )
BEGIN
	DECLARE nick VARCHAR(45);
	DECLARE tit VARCHAR(100);
DECLARE stamp DATETIME;
SET stamp = NOW();

	SELECT NickName into nick FROM utente WHERE ID = idUtente;
	SELECT Titolo into tit FROM pubblicazione WHERE ID = idPubb;
	DELETE FROM tag WHERE tag.IDPubblicazione = idPubb AND tag.IDParolaChiave = idpc;
	UPDATE pubblicazione p SET p.DataUltimaModifica = DATE(stamp) WHERE p.ID = idPubb;
	INSERT INTO storia ( `IDPubblicazione`, `IDUtente`, `Timestamp`, `Descrizione`, `Tipo` ) VALUES
	( idPubb, idUtente, stamp, CONCAT( nick, " ha rimosso una parola chiave alla pubblicazione : ", tit), 3);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delPubb` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delPubb`( idPubb INT, idUtente INT )
BEGIN
DECLARE nick VARCHAR(45);
DECLARE tit VARCHAR(100);
DECLARE idIns INT;

SELECT NickName into nick FROM utente WHERE ID = idUtente;
SELECT Titolo into tit FROM pubblicazione WHERE ID = idPubb;
SELECT IDUtente into idIns FROM storia WHERE Tipo = 1 AND IDPubblicazione = idPubb;

UPDATE datiModeratore D SET D.NumPubb = D.NumPubb - 1 WHERE D.IDUtente = idIns;

DELETE FROM pubblicazione WHERE ID = idPubb;

INSERT INTO storia ( `IDPubblicazione`, `IDUtente`, `Timestamp`, `Descrizione`, `Tipo` ) VALUES
( NULL, idUtente, NOW(), CONCAT( nick, " ha eliminato la pubblicazione titolata : ", tit), 2);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delRecensione` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delRecensione`( idUtnt INT, idPubb INT, idElimin INT )
BEGIN 
DECLARE tit VARCHAR(100);
DECLARE nick VARCHAR(45);
DECLARE nickDel VARCHAR(45);
DECLARE approvato BIT;
SELECT NickName into nick FROM utente WHERE ID = idUtnt;
SELECT Titolo into tit FROM pubblicazione WHERE ID = idPubb;
SELECT NickName into nickDel FROM utente WHERE ID = idElimin;
SELECT FlagApprovazione into approvato FROM recensione WHERE IDPubblicazione = idPubb AND IDUtente = idUtnt;

DELETE FROM recensione WHERE IDPubblicazione = idPubb AND IDUtente = idUtnt;

IF ( approvato = 1 ) THEN
UPDATE pubblicazione SET NumRec = NumRec - 1 WHERE ID = idPubb;
END IF;
IF ( idUtnt = idElimin ) THEN
INSERT INTO Storia (IDPubblicazione, IDUtente, `Timestamp`, Descrizione, Tipo) VALUES 
(idPubb, idElimin, now(), CONCAT( nick, " ha eliminato la propria recensione per la pubblicazione:  ",tit ),6 );
ELSE
INSERT INTO Storia (IDPubblicazione, IDUtente, `Timestamp`, Descrizione, Tipo) VALUES 
(idPubb, idElimin, now(), CONCAT( nickDel, " ha eliminato la recensione di", nick, "per la pubblicazione:  ",tit ),6 );
END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delRistampa` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delRistampa`( idRistampa INT, idUtente INT )
BEGIN
	DECLARE nick VARCHAR(45);
	DECLARE tit VARCHAR(100);
    DECLARE stamp DATETIME;
    DECLARE idPubb INT;
    
    SET stamp = NOW();
    SELECT IDPubblicazione into idPubb FROM ristampa WHERE ID = idRistampa;
	SELECT NickName into nick FROM utente WHERE ID = idUtente;
	SELECT Titolo into tit FROM pubblicazione WHERE ID = idPubb;

	DELETE FROM ristampa WHERE ID = idRistampa;
	 
	UPDATE pubblicazione p SET p.DataUltimaModifica = DATE(stamp) WHERE p.ID = idPubb;

	INSERT INTO storia ( `IDPubblicazione`, `IDUtente`, `Timestamp`, `Descrizione`, `Tipo` ) VALUES
	( idPubb, idUtente, stamp, CONCAT( nick, " ha rimosso una ristampa della pubblicazione : ", tit), 3);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delSorgente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delSorgente`( idSorgente INT, idUtente INT )
BEGIN
	DECLARE nick VARCHAR(45);
	DECLARE tit VARCHAR(100);
	DECLARE stamp TIMESTAMP;
    DECLARE idPubb INT;
    
	SET stamp= NOW();
     SELECT IDPubblicazione into idPubb FROM sorgente WHERE ID = idSorgente;
	SELECT NickName into nick FROM utente WHERE ID = idUtente;
	SELECT Titolo into tit FROM pubblicazione WHERE ID = idPubb;

	DELETE FROM sorgente WHERE ID = idSorgente;
	 
	UPDATE pubblicazione p SET p.DataUltimaModifica = DATE(stamp) WHERE p.ID = idPubb;

	INSERT INTO storia ( `IDPubblicazione`, `IDUtente`, `Timestamp`, `Descrizione`, `Tipo` ) VALUES
	( idPubb, idUtente, stamp, CONCAT( nick, " ha rimosso una sorgente della pubblicazione : ", tit), 3);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delUtente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delUtente`( idUtnt INT )
BEGIN
UPDATE utente SET UtenteEliminato = 1 WHERE ID = idUtnt;
UPDATE utente SET Ruolo = 1 WHERE ID = idUtnt;
DELETE FROM datiUtente WHERE IDUtente = idUtnt;
DELETE FROM datiModeratore WHERE IDUtente = idUtnt;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `inserimentoAutore` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `inserimentoAutore`( idPubb INT, ida INT, idUtente INT )
BEGIN
	DECLARE nick VARCHAR(45);
	DECLARE tit VARCHAR(100);
	DECLARE stamp DATETIME;
    	SET stamp = NOW();

	SELECT NickName into nick FROM utente WHERE ID = idUtente;
	SELECT Titolo into tit FROM pubblicazione WHERE ID = idPubb;
	INSERT INTO scritto(IDPubblicazione,IDAutore) VALUES ( idPubb, ida );
	UPDATE pubblicazione p SET p.DataUltimaModifica = DATE(stamp) WHERE p.ID = idPubb;
	INSERT INTO storia ( `IDPubblicazione`, `IDUtente`, `Timestamp`, `Descrizione`, `Tipo` ) VALUES
	( idPubb, idUtente, stamp, CONCAT( nick, " ha aggiunto l'autore ", ida, " alla pubblicazione : ", tit), 3);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `inserimentoLike` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `inserimentoLike`( idUtente INT , idPubb INT )
BEGIN 
	
DECLARE ora TIMESTAMP;
DECLARE tit VARCHAR(100);
DECLARE nick VARCHAR(45);

SET ora = now();
SELECT NickName into nick FROM utente WHERE ID = idUtente;
SELECT Titolo into tit FROM pubblicazione WHERE ID = idPubb;

INSERT INTO `Like` VALUES (idPubb, idUtente, ora);

INSERT INTO Storia (IDPubblicazione, IDUtente, Timestamp, Descrizione, Tipo) VALUES 
(idPubb, idUtente, ora, 
CONCAT( nick, " ha messo like alla pubblicazione:  ",tit ),7 );

UPDATE Pubblicazione P SET P.NumLike = P.NumLike + 1 WHERE P.ID = idPubb;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `inserimentoParolaChiave` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `inserimentoParolaChiave`( idPubb INT, idpc INT, idUtente INT )
BEGIN
	DECLARE nick VARCHAR(45);
	DECLARE tit VARCHAR(100);
    DECLARE stamp DATETIME;
    SET stamp = NOW();

	SELECT NickName into nick FROM utente WHERE ID = idUtente;
	SELECT Titolo into tit FROM pubblicazione WHERE ID = idPubb;

	INSERT INTO tag(IDPubblicazione, IDParolaChiave) VALUES ( idPubb, idpc );
	 
	UPDATE pubblicazione p SET p.DataUltimaModifica = DATE(stamp) WHERE p.ID = idPubb;

	INSERT INTO storia ( `IDPubblicazione`, `IDUtente`, `Timestamp`, `Descrizione`, `Tipo` ) VALUES
	( idPubb, idUtente, stamp, CONCAT( nick, " ha aggiunto una parola chiave alla pubblicazione : ", tit), 3);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `inserimentoPubb` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `inserimentoPubb`(IN idUtnt INT, IN ISBNPubb  CHAR(13), IN titl VARCHAR(45), IN editr VARCHAR(45), 
IN dataPubb DATE, IN nPg INT, IN ling VARCHAR(45) , IN descrzn TEXT, IN indc TEXT, OUT idPubb INT )
BEGIN
    DECLARE nick VARCHAR(45);
    DECLARE stamp DATETIME;
    SET stamp = NOW();
    
    SELECT Nickname FROM utente WHERE ID = idUtnt INTO nick;

    UPDATE datimoderatore SET NumPubb = NumPubb + 1 WHERE IDUtente = idUtnt  ;
    
    INSERT INTO pubblicazione ( `ISBN`,`Titolo`,`Editore`,`DataUltimaModifica`,`DataPubblicazione`)
    VALUES (ISBNPubb,titl,editr,DATE(stamp),dataPubb);
    SET idPubb = LAST_INSERT_ID();
    INSERT INTO metadati ( `IDPubblicazione`,`Npag`,`Lingua`,`Descrizione`,`Indice`)
    VALUES (idPubb,nPg,ling,descrzn,indc);
    
    INSERT INTO storia ( `IDPubblicazione`,`IDUtente`,`Timestamp`,`Descrizione`,`Tipo` )
    VALUES ( idPubb,idUtnt,stamp, concat( nick, " ha inserito la pubblicazione titolata: ", titl), 1);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `inserimentoRecensione` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `inserimentoRecensione`( idUtente INT, idPubb INT, Des TEXT)
BEGIN 	
DECLARE dataoggi DATE;
DECLARE tit VARCHAR(100);
DECLARE nick VARCHAR(45);
DECLARE stamp DATETIME;

SET stamp = NOW();
SELECT NickName INTO nick FROM utente WHERE ID = idUtente;
SELECT Titolo INTO tit FROM pubblicazione WHERE ID = idPubb;

INSERT INTO Recensione ( `IDPubblicazione`, `IDUtente`, `Descrizione`, `Timestamp` ) VALUES (IDPubb, IDUtente, Des, stamp);

INSERT INTO storia ( `IDPubblicazione`, `IDUtente`, `Timestamp`, `Descrizione`, `Tipo` ) VALUES
( idPubb, idUtente, stamp, CONCAT( nick, " ha inserito una recensione alla pubblicazione : ", tit), 5);

UPDATE utente SET utente.DataUltimaRecensione = DATE(stamp) WHERE ID = idUtente;
IF (  ( SELECT Ruolo FROM utente u WHERE  u.id = idUtente ) = 1 ) THEN
UPDATE utente SET Ruolo = 2 WHERE ID = idUtente;
END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `inserimentoRistampa` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `inserimentoRistampa`( idPubb INT, dtRis DATE, num TINYINT, idUtente INT )
BEGIN
	DECLARE nick VARCHAR(45);
	DECLARE tit VARCHAR(100);
    DECLARE stamp DATETIME;
    SET stamp = NOW();
	SELECT NickName into nick FROM utente WHERE ID = idUtente;
	SELECT Titolo into tit FROM pubblicazione WHERE ID = idPubb;
	INSERT INTO ristampa(IDPubblicazione, DataRistampa, Numero) VALUES ( IDPubb, dtRis, num );
	UPDATE pubblicazione p SET p.DataUltimaModifica = DATE(stamp) WHERE p.ID = idPubb;
	INSERT INTO storia ( `IDPubblicazione`, `IDUtente`, `Timestamp`, `Descrizione`, `Tipo` ) VALUES
	( idPubb, idUtente, stamp, CONCAT( nick, " ha aggiunto una ristampa alla pubblicazione : ", tit), 3);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `inserimentoSorgente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `inserimentoSorgente`( idPubb INT, ur VARCHAR(2083), tp VARCHAR(45), frmt VARCHAR(45), des TEXT, idUtente INT )
BEGIN
	DECLARE nick VARCHAR(45);
	DECLARE tit VARCHAR(100);
	DECLARE stamp DATETIME;
	SET stamp = NOW();
	SELECT NickName into nick FROM utente WHERE ID = idUtente;
	SELECT Titolo into tit FROM pubblicazione WHERE ID = idPubb;

	INSERT INTO sorgente( IDPubblicazione, URI, Tipo, Formato, Descrizione ) VALUES ( IDPubb, ur, tp, frmt, des );
	 
	UPDATE pubblicazione p SET p.DataUltimaModifica = DATE(stamp) WHERE p.ID = idPubb;

	INSERT INTO storia ( `IDPubblicazione`, `IDUtente`, `Timestamp`, `Descrizione`, `Tipo` ) VALUES
	( idPubb, idUtente, stamp, CONCAT( nick, " ha aggiunto una sorgente alla pubblicazione : ", tit), 3);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `inserimentoUtente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `inserimentoUtente`(nick VARCHAR(45), email VARCHAR(254), pass VARCHAR(45), nome VARCHAR(45), cogn VARCHAR(45), dNascita DATE, lNascita VARCHAR(100), residenza VARCHAR(200) )
BEGIN

INSERT INTO utente ( `Nickname`, `Password` ) VALUES ( nick, pass );
INSERT INTO datiutente( `IDUtente`, `Nome`, `Cognome`, `DataNascita`, `LuogoNascita`, `Residenza`,`Email`)
VALUES ( LAST_INSERT_ID(), nome, cogn, dNascita, lNascita, residenza,email );

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `modificaDataPubb` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `modificaDataPubb`( idPubb INT, dt DATE, idUtente INT )
BEGIN
	DECLARE nick VARCHAR(45);
	DECLARE tit VARCHAR(100);
	DECLARE stamp DATETIME;
    	SET stamp = NOW();

	SELECT NickName into nick FROM utente WHERE ID = idUtente;
	SELECT Titolo into tit FROM pubblicazione WHERE ID = idPubb;

	UPDATE pubblicazione p SET p.DataPubblicazione = dt WHERE p.ID = idPubb;
	UPDATE pubblicazione p SET p.DataUltimaModifica = DATE(stamp) WHERE p.ID = idPubb;

	INSERT INTO storia ( `IDPubblicazione`, `IDUtente`, `Timestamp`, `Descrizione`, `Tipo` ) VALUES
	( idPubb, idUtente, stamp, CONCAT( nick, " ha cambiato la data di pubblicazione di  : ", tit, " in ", dt) , 3 );
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `modificaDescrizione` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `modificaDescrizione`( idPubb INT, des TEXT, idUtente INT )
BEGIN
	DECLARE nick VARCHAR(45);
	DECLARE tit VARCHAR(100);
	DECLARE stamp DATETIME;
    	SET stamp = NOW();
    
	SELECT NickName into nick FROM utente WHERE ID = idUtente;
	SELECT Titolo into tit FROM pubblicazione WHERE ID = idPubb;
	UPDATE metadati m SET m.Descrizione = des WHERE m.IDPubblicazione = idPubb;
	UPDATE pubblicazione p SET p.DataUltimaModifica = DATE(stamp) WHERE p.ID = idPubb;

	INSERT INTO storia ( `IDPubblicazione`, `IDUtente`, `Timestamp`, `Descrizione`, `Tipo` ) VALUES
	( idPubb, idUtente, stamp, CONCAT( nick, " ha cambiato la descrizione della pubblicazione : ", tit, " in ", des), 3);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `modificaEditore` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `modificaEditore`( idPubb INT, Edit VARCHAR(45), idUtente INT )
BEGIN
	DECLARE nick VARCHAR(45);
	DECLARE tit VARCHAR(100);
    DECLARE stamp DATETIME;
    SET stamp = NOW();

	SELECT NickName into nick FROM utente WHERE ID = idUtente;
	SELECT Titolo into tit FROM pubblicazione WHERE ID = idPubb;

	UPDATE pubblicazione p SET p.Editore = Edit WHERE p.ID = idPubb;
	UPDATE pubblicazione p SET p.DataUltimaModifica = DATE(stamp) WHERE p.ID = idPubb;

	INSERT INTO storia ( `IDPubblicazione`, `IDUtente`, `Timestamp`, `Descrizione`, `Tipo` ) VALUES
	( idPubb, idUtente, stamp, CONCAT( nick, " ha cambiato l’editore della pubblicazione : ", tit, " in ", Edit) , 3 );
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `modificaIndice` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `modificaIndice`( idPubb INT, ind TEXT, idUtente INT )
BEGIN
	DECLARE nick VARCHAR(45);
	DECLARE tit VARCHAR(100);
    	DECLARE stamp DATETIME;
    	SET stamp = NOW();

	SELECT NickName into nick FROM utente WHERE ID = idUtente;
	SELECT Titolo into tit FROM pubblicazione WHERE ID = idPubb;

	UPDATE metadati m SET m.Indice = ind WHERE m.IDPubblicazione = idPubb;
	UPDATE pubblicazione p SET p.DataUltimaModifica = DATE(stamp) WHERE p.ID = idPubb;

	INSERT INTO storia ( `IDPubblicazione`, `IDUtente`, `Timestamp`, `Descrizione`, `Tipo` ) VALUES
	( idPubb, idUtente, stamp, CONCAT( nick, " ha cambiato l’indice della pubblicazione : ", tit, " in ", ind), 3);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `modificaISBN` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `modificaISBN`( idPubb INT, codiceisbn CHAR(13), idUtente INT )
BEGIN
	DECLARE nick VARCHAR(45);
	DECLARE tit VARCHAR(100);

	SELECT NickName into nick FROM utente WHERE ID = idUtente;
	SELECT Titolo into tit FROM pubblicazione WHERE ID = idPubb;

	UPDATE pubblicazione p SET p.ISBN = codiceisbn WHERE p.ID = idPubb;
	UPDATE pubblicazione p SET p.DataUltimaModifica = CURDATE() WHERE p.ID = idPubb;

	INSERT INTO storia ( `IDPubblicazione`, `IDUtente`, `Timestamp`, `Descrizione`, `Tipo` ) VALUES
	( idPubb, idUtente, NOW(), CONCAT( nick, " ha cambiato il codice ISBN della pubblicazione : ", tit, " in ", codiceisbn) , 3 );
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `modificaLingua` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `modificaLingua`( idPubb INT, lin VARCHAR(45), idUtente INT )
BEGIN
	DECLARE nick VARCHAR(45);
	DECLARE tit VARCHAR(100);
    	DECLARE stamp DATETIME;
    	SET stamp = NOW();

	SELECT NickName into nick FROM utente WHERE ID = idUtente;
	SELECT Titolo into tit FROM pubblicazione WHERE ID = idPubb;

	UPDATE metadati m SET m.Lingua = lin WHERE m.IDPubblicazione = idPubb;
	UPDATE pubblicazione p SET p.DataUltimaModifica = DATE(stamp) WHERE p.ID = idPubb;
	INSERT INTO storia ( `IDPubblicazione`, `IDUtente`, `Timestamp`, `Descrizione`, `Tipo` ) VALUES
	( idPubb, idUtente, stamp, CONCAT( nick, " ha cambiato la lingua della pubblicazione : ", tit, " in ", lin), 3);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `modificaNPag` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `modificaNPag`( idPubb INT, np SMALLINT, idUtente INT )
BEGIN
	DECLARE nick VARCHAR(45);
	DECLARE tit VARCHAR(100);
    DECLARE stamp DATETIME;
    SET stamp = NOW();

	SELECT NickName into nick FROM utente WHERE ID = idUtente;
	SELECT Titolo into tit FROM pubblicazione WHERE ID = idPubb;

	UPDATE metadati m SET m.NPag = np WHERE m.IDPubblicazione = idPubb;
	UPDATE pubblicazione p SET p.DataUltimaModifica = DATE(stamp) WHERE p.ID = idPubb;

	INSERT INTO storia ( `IDPubblicazione`, `IDUtente`, `Timestamp`, `Descrizione`, `Tipo` ) VALUES
	( idPubb, idUtente, stamp, CONCAT( nick, " ha cambiato il numero di pagine della pubblicazione : ", tit, " in ", np), 3);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `modificaTitolo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `modificaTitolo`( idPubb INT, tit VARCHAR(100), idUtente INT )
BEGIN
	DECLARE stamp DATETIME;
	DECLARE nick VARCHAR(45);
    SET stamp = NOW();
	SELECT NickName into nick FROM utente WHERE ID = idUtente;

	UPDATE pubblicazione p SET p.Titolo = tit WHERE p.ID = idPubb;
	UPDATE pubblicazione p SET p.DataUltimaModifica = DATE(stamp) WHERE p.ID = idPubb;

	INSERT INTO storia ( `IDPubblicazione`, `IDUtente`, `Timestamp`, `Descrizione`, `Tipo` ) VALUES
	( idPubb, idUtente, stamp, CONCAT( nick, " ha cambiato il titolo della pubblicazione in : ", tit), 3);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `promAmministratore` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `promAmministratore`( idProm INT,  idUtnt INT )
BEGIN

UPDATE utente u SET u.Ruolo = 4 WHERE u.ID = idUtnt;
UPDATE datimoderatore SET DataPromozione = now() WHERE IDUtente = idUtnt;
UPDATE datimoderatore SET Promotore = idProm WHERE IDUtente = idUtnt;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `promModer` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `promModer`( idProm INT,  idUtente INT )
BEGIN

UPDATE utente u SET u.Ruolo = 3 WHERE u.ID = idUtente;
INSERT INTO datimoderatore( IDUtente, NumPubb, DataPromozione, Promotore) 
VALUES ( IDUtente, 0, CURDATE(), idProm );

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ricercaAutori` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ricercaAutori`( nm VARCHAR(45), cgnm VARCHAR(45), pagina INT )
BEGIN 
	IF ( nm IS NULL  ) THEN
		SELECT p.* 
        FROM pubblicazione p
		INNER JOIN scritto s ON s.IDPubblicazione = p.ID
		INNER JOIN autore a ON s.IDAutore = a.ID
		WHERE a.cognome = cgnm
        LIMIT pagina, 10;
	ELSEIF( cgnm IS NULL ) THEN
		SELECT p.* 
        FROM pubblicazione p
		INNER JOIN scritto s ON s.IDPubblicazione = p.ID
		INNER JOIN autore a ON s.IDAutore = a.ID
		WHERE a.nome = nm
        LIMIT pagina, 10;
	ELSE
		SELECT p.* 
        FROM pubblicazione p
		INNER JOIN scritto s ON s.IDPubblicazione = p.ID
		INNER JOIN autore a ON s.IDAutore = a.ID
		WHERE a.nome = nm AND a.cognome = cgnm
        LIMIT pagina ,10;
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-28 10:13:52
