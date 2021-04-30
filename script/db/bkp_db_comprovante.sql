-- MariaDB dump 10.18  Distrib 10.4.17-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: db_comprovante
-- ------------------------------------------------------
-- Server version	10.4.17-MariaDB-1:10.4.17+maria~bionic

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `comprovante`
--

DROP TABLE IF EXISTS `comprovante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comprovante` (
  `comprovante_id` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(50) NOT NULL,
  `descricao` varchar(150) DEFAULT NULL,
  `tipo` varchar(20) NOT NULL,
  `versao` varchar(15) NOT NULL,
  PRIMARY KEY (`comprovante_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comprovante`
--

LOCK TABLES `comprovante` WRITE;
/*!40000 ALTER TABLE `comprovante` DISABLE KEYS */;
INSERT INTO `comprovante` VALUES (1,'RFB V2','Comprovante do RFB','RFB','2'),(2,'RFB V3','Comprovante RFB V3','RFB','3'),(3,'SEFAZ IPVA SP','Comprovante IPVA SP','IPVA-SP','1');
/*!40000 ALTER TABLE `comprovante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalhe_grupo`
--

DROP TABLE IF EXISTS `detalhe_grupo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detalhe_grupo` (
  `detalhe_grupo_id` int(11) NOT NULL AUTO_INCREMENT,
  `grupo_id` int(11) NOT NULL,
  `visibilidade` tinyint(1) NOT NULL,
  `ordenacao` int(11) NOT NULL,
  PRIMARY KEY (`detalhe_grupo_id`),
  KEY `fk_grupodetalhe_grupo` (`grupo_id`),
  CONSTRAINT `fk_grupodetalhe_grupo` FOREIGN KEY (`grupo_id`) REFERENCES `grupo` (`grupo_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalhe_grupo`
--

LOCK TABLES `detalhe_grupo` WRITE;
/*!40000 ALTER TABLE `detalhe_grupo` DISABLE KEYS */;
INSERT INTO `detalhe_grupo` VALUES (2,1,1,0),(4,1,1,1),(5,2,1,0),(6,2,1,1),(7,4,1,0),(8,4,1,1),(9,5,1,0),(10,6,1,0),(11,8,1,0);
/*!40000 ALTER TABLE `detalhe_grupo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalhe_grupo_conteudo`
--

DROP TABLE IF EXISTS `detalhe_grupo_conteudo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detalhe_grupo_conteudo` (
  `detalhe_grupo_conteudo_id` int(11) NOT NULL AUTO_INCREMENT,
  `detalhe_grupo_id` int(11) NOT NULL,
  `dominio_atributo_detalhe_grupo_id` int(11) NOT NULL,
  `conteudo` text NOT NULL,
  PRIMARY KEY (`detalhe_grupo_conteudo_id`),
  KEY `fk_detalhegrupoconteudo_detalhegrupo` (`detalhe_grupo_id`),
  KEY `fk_detalhegrupoconteudo_dominioatributodetalhegrupo` (`dominio_atributo_detalhe_grupo_id`),
  CONSTRAINT `fk_detalhegrupoconteudo_detalhegrupo` FOREIGN KEY (`detalhe_grupo_id`) REFERENCES `detalhe_grupo` (`detalhe_grupo_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_detalhegrupoconteudo_dominioatributodetalhegrupo` FOREIGN KEY (`dominio_atributo_detalhe_grupo_id`) REFERENCES `dominio_atributo_detalhe_grupo` (`dominio_atributo_detalhe_grupo_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalhe_grupo_conteudo`
--

LOCK TABLES `detalhe_grupo_conteudo` WRITE;
/*!40000 ALTER TABLE `detalhe_grupo_conteudo` DISABLE KEYS */;
INSERT INTO `detalhe_grupo_conteudo` VALUES (1,2,3,'aqui está o valor'),(2,2,4,'$.detalhes[?(@.tituloAtributo == \'valor do pagamento\')].valorAtributo'),(3,4,3,'nome atributo fixo'),(4,4,4,'valor atributo fixo'),(5,5,3,'Esse é meu código de barras'),(6,5,4,'$.detalhes[?(@.tituloAtributo == \'codigo de barras\')].valorAtributo'),(7,6,3,'aqui está o valor'),(8,6,4,'$.detalhes[?(@.tituloAtributo == \'valor do pagamento\')].valorAtributo'),(9,7,5,'Esse comprovante eh muito importante para mim...'),(10,8,5,'$.detalhes[?(@.tituloAtributo == \'mensagem_t3\')].valorAtributo'),(11,9,3,'codigo de barras'),(12,9,4,'$.detalhes[?(@.tituloAtributo == \'codigo de barras\')].valorAtributo'),(13,10,3,'codigo de barras'),(14,10,4,'$.detalhes[?(@.tituloAtributo == \'codigo de barras\')].valorAtributo'),(15,11,6,'top1'),(16,11,7,'top2'),(17,11,8,'dado 1'),(18,11,9,'dado 2');
/*!40000 ALTER TABLE `detalhe_grupo_conteudo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dominio_atributo_detalhe_grupo`
--

DROP TABLE IF EXISTS `dominio_atributo_detalhe_grupo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dominio_atributo_detalhe_grupo` (
  `dominio_atributo_detalhe_grupo_id` int(11) NOT NULL AUTO_INCREMENT,
  `nome_atributo` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`dominio_atributo_detalhe_grupo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dominio_atributo_detalhe_grupo`
--

LOCK TABLES `dominio_atributo_detalhe_grupo` WRITE;
/*!40000 ALTER TABLE `dominio_atributo_detalhe_grupo` DISABLE KEYS */;
INSERT INTO `dominio_atributo_detalhe_grupo` VALUES (3,'tituloAtributo'),(4,'valorAtributo'),(5,'texto'),(6,'cabecalho1'),(7,'cabecalho2'),(8,'coluna1'),(9,'coluna2');
/*!40000 ALTER TABLE `dominio_atributo_detalhe_grupo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupo`
--

DROP TABLE IF EXISTS `grupo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grupo` (
  `grupo_id` int(11) NOT NULL AUTO_INCREMENT,
  `comprovante_id` int(11) NOT NULL,
  `titulo` varchar(50) DEFAULT NULL,
  `tipo` varchar(20) NOT NULL,
  `ordenacao` int(11) NOT NULL,
  `visibilidade` tinyint(1) NOT NULL,
  PRIMARY KEY (`grupo_id`),
  KEY `fk_grupo_comprovante` (`comprovante_id`),
  CONSTRAINT `fk_grupo_comprovante` FOREIGN KEY (`comprovante_id`) REFERENCES `comprovante` (`comprovante_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo`
--

LOCK TABLES `grupo` WRITE;
/*!40000 ALTER TABLE `grupo` DISABLE KEYS */;
INSERT INTO `grupo` VALUES (1,1,'RFB','bloco',0,1),(2,1,'Dados do Pagamento','bloco',1,1),(3,1,'grupo linha orizontal','linha_horizontal',2,1),(4,1,'Minhas Mensagenss','texto',3,1),(5,2,'Dados do Pagamento','bloco',0,1),(6,3,'Dados do Pagamento','bloco',0,1),(7,2,NULL,'linha_horizontal',0,1),(8,1,'Minha Tabela','tabela',4,1);
/*!40000 ALTER TABLE `grupo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-30  2:21:19
