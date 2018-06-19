-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 19, 2018 at 01:19 PM
-- Server version: 10.1.30-MariaDB
-- PHP Version: 5.6.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bricolage`
--

-- --------------------------------------------------------

--
-- Table structure for table `carburant`
--

CREATE TABLE `carburant` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `carburant`
--

INSERT INTO `carburant` (`ID`, `NOM`) VALUES
(1, 'Diesel'),
(2, 'Essence');

-- --------------------------------------------------------

--
-- Table structure for table `categorie`
--

CREATE TABLE `categorie` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `categorie`
--

INSERT INTO `categorie` (`ID`, `NOM`) VALUES
(1, 'Service Nettoyage'),
(2, 'Demenagement & Stock'),
(3, 'Travaux de maison'),
(4, 'Gardiennage & Deratisation'),
(5, 'Evenmentiel'),
(6, 'Babysitters'),
(7, 'Photographie'),
(8, 'Voitures'),
(9, 'Formation');

-- --------------------------------------------------------

--
-- Table structure for table `childdemandebabysitting`
--

CREATE TABLE `childdemandebabysitting` (
  `ID` bigint(20) NOT NULL,
  `AGE` int(11) DEFAULT NULL,
  `DEMANDEBABYSITTING_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `EMAIL` varchar(255) NOT NULL,
  `ADRESSECOMPLEMENT` varchar(255) DEFAULT NULL,
  `BLOCKED` tinyint(1) DEFAULT '0',
  `NOM` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `PHONE` varchar(255) DEFAULT NULL,
  `PRENOM` varchar(255) DEFAULT NULL,
  `TOKEN` varchar(255) DEFAULT NULL,
  `SECTEUR_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`EMAIL`, `ADRESSECOMPLEMENT`, `BLOCKED`, `NOM`, `PASSWORD`, `PHONE`, `PRENOM`, `TOKEN`, `SECTEUR_ID`) VALUES
('anas.the.creator@gmail.com', 'Saada 1 No 570', 0, 'Benchebani', 'walo', '0630247385', 'Mohamed Anas', NULL, 3),
('htakouit@gmail.com', 'Hay Chabab No 112', 0, 'Takouit', 'htakouit@gmail.com', '0677352220', 'hamza', NULL, 5),
('newCustomer@gmail.com', 'no 12 hay salama', 0, 'Customer', 'newCustomer@gmail.com', '0679461382', 'new', NULL, 5);

-- --------------------------------------------------------

--
-- Table structure for table `couleur`
--

CREATE TABLE `couleur` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `couleur`
--

INSERT INTO `couleur` (`ID`, `NOM`) VALUES
(1, 'Noir'),
(2, 'Blanc'),
(3, 'Rouge'),
(4, 'Gris'),
(5, 'Bleu');

-- --------------------------------------------------------

--
-- Table structure for table `cuisinedemandeevent`
--

CREATE TABLE `cuisinedemandeevent` (
  `ID` bigint(20) NOT NULL,
  `CUISINE_ID` bigint(20) DEFAULT NULL,
  `DEMANDEEVENT_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cuisinedemandeevent`
--

INSERT INTO `cuisinedemandeevent` (`ID`, `CUISINE_ID`, `DEMANDEEVENT_ID`) VALUES
(1, 2, 1),
(2, 3, 1),
(3, 2, 2),
(4, 3, 2),
(5, 2, 3),
(6, 3, 3);

-- --------------------------------------------------------

--
-- Table structure for table `cuisinetype`
--

CREATE TABLE `cuisinetype` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cuisinetype`
--

INSERT INTO `cuisinetype` (`ID`, `NOM`) VALUES
(1, 'Arabe'),
(2, 'Chinoix'),
(3, 'Mexican'),
(4, 'Other');

-- --------------------------------------------------------

--
-- Table structure for table `day`
--

CREATE TABLE `day` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `day`
--

INSERT INTO `day` (`ID`, `NOM`) VALUES
(1, 'Lundi'),
(2, 'Mardi'),
(3, 'Mercredi'),
(4, 'Jeudi'),
(5, 'Vendredi'),
(6, 'Samedi'),
(7, 'Dimanche');

-- --------------------------------------------------------

--
-- Table structure for table `demandebabysitting`
--

CREATE TABLE `demandebabysitting` (
  `ID` bigint(20) NOT NULL,
  `DETAIL` varchar(255) DEFAULT NULL,
  `FULLTIME` tinyint(1) DEFAULT '0',
  `NBRHEURES` decimal(38,0) DEFAULT NULL,
  `DEMANDESERVICE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `demandecleaning`
--

CREATE TABLE `demandecleaning` (
  `ID` bigint(20) NOT NULL,
  `BRINGEQUIPEMENT` tinyint(1) DEFAULT '0',
  `NBRCLEANER` decimal(38,0) DEFAULT NULL,
  `NBRHEURES` decimal(38,0) DEFAULT NULL,
  `DEMANDESERVICE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `demandecleaning`
--

INSERT INTO `demandecleaning` (`ID`, `BRINGEQUIPEMENT`, `NBRCLEANER`, `NBRHEURES`, `DEMANDESERVICE_ID`) VALUES
(1, 0, '3', '2', 1),
(2, 1, '1', '1', 2),
(3, 0, '4', '2', 3),
(4, 0, '2', '4', 4),
(5, 0, '3', '4', 5),
(6, 0, '1', '1', 6),
(7, 0, '2', '2', 7),
(8, 1, '4', '5', 8);

-- --------------------------------------------------------

--
-- Table structure for table `demandeevent`
--

CREATE TABLE `demandeevent` (
  `ID` bigint(20) NOT NULL,
  `NBRINVITES` int(11) DEFAULT NULL,
  `DEMANDESERVICE_ID` bigint(20) DEFAULT NULL,
  `EVENTBUDGET_ID` bigint(20) DEFAULT NULL,
  `EVENTTYPE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `demandeevent`
--

INSERT INTO `demandeevent` (`ID`, `NBRINVITES`, `DEMANDESERVICE_ID`, `EVENTBUDGET_ID`, `EVENTTYPE_ID`) VALUES
(1, 0, 22, 1, 1),
(2, 2, 24, 2, 5),
(3, 60, 26, 2, 5);

-- --------------------------------------------------------

--
-- Table structure for table `demandeformationpersonnel`
--

CREATE TABLE `demandeformationpersonnel` (
  `ID` bigint(20) NOT NULL,
  `ADOMICILE` tinyint(1) DEFAULT '0',
  `NBRPERSONNE` int(11) DEFAULT NULL,
  `FILIERE_ID` bigint(20) DEFAULT NULL,
  `MATIERE_ID` bigint(20) DEFAULT NULL,
  `DEMANDESERVICE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `demandeformationpersonnel`
--

INSERT INTO `demandeformationpersonnel` (`ID`, `ADOMICILE`, `NBRPERSONNE`, `FILIERE_ID`, `MATIERE_ID`, `DEMANDESERVICE_ID`) VALUES
(1, 1, 5, NULL, NULL, 9),
(2, 0, 5, 7, 1, 41),
(3, 0, 5, 1, 4, 42);

-- --------------------------------------------------------

--
-- Table structure for table `demandeformationpro`
--

CREATE TABLE `demandeformationpro` (
  `ID` bigint(20) NOT NULL,
  `NBRHEURES` int(11) DEFAULT NULL,
  `NBRPERSONNE` int(11) DEFAULT NULL,
  `DEMANDESERVICE_ID` bigint(20) DEFAULT NULL,
  `FORMATEUR_EMAIL` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `demandegardening`
--

CREATE TABLE `demandegardening` (
  `ID` bigint(20) NOT NULL,
  `DEMANDESERVICE_ID` bigint(20) DEFAULT NULL,
  `GARDENINGTYPE_ID` bigint(20) DEFAULT NULL,
  `HOME_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `demandehandyman`
--

CREATE TABLE `demandehandyman` (
  `ID` bigint(20) NOT NULL,
  `NBRHANDYMAN` decimal(38,0) DEFAULT NULL,
  `NBRHEURES` decimal(38,0) DEFAULT NULL,
  `DEMANDESERVICE_ID` bigint(20) DEFAULT NULL,
  `SERVICE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `demandehandyman`
--

INSERT INTO `demandehandyman` (`ID`, `NBRHANDYMAN`, `NBRHEURES`, `DEMANDESERVICE_ID`, `SERVICE_ID`) VALUES
(1, '2', '2', 27, 8),
(2, '1', '1', 38, 8);

-- --------------------------------------------------------

--
-- Table structure for table `demandemoving`
--

CREATE TABLE `demandemoving` (
  `ADRESSEARRIVE` varchar(255) DEFAULT NULL,
  `ADRESSEDEPART` varchar(255) DEFAULT NULL,
  `HANDYMAN` tinyint(1) DEFAULT '0',
  `ID` bigint(20) DEFAULT NULL,
  `STORAGE` tinyint(1) DEFAULT '0',
  `DEMANDESERVICE_ID` bigint(20) NOT NULL,
  `VILLEARRIVE_ID` bigint(20) DEFAULT NULL,
  `VILLEDEPART_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `demandepainting`
--

CREATE TABLE `demandepainting` (
  `ID` bigint(20) NOT NULL,
  `DETAIL` varchar(255) DEFAULT NULL,
  `DEMANDESERVICE_ID` bigint(20) DEFAULT NULL,
  `PAINTING_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `demandepestcontrol`
--

CREATE TABLE `demandepestcontrol` (
  `ID` bigint(20) NOT NULL,
  `DEMANDESERVICE_ID` bigint(20) DEFAULT NULL,
  `TYPEOFPESTCONTROL_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `demandepestcontrol`
--

INSERT INTO `demandepestcontrol` (`ID`, `DEMANDESERVICE_ID`, `TYPEOFPESTCONTROL_ID`) VALUES
(1, 25, 1);

-- --------------------------------------------------------

--
-- Table structure for table `demandephotographie`
--

CREATE TABLE `demandephotographie` (
  `ID` bigint(20) NOT NULL,
  `VIDEOGRAPHIE` tinyint(1) DEFAULT '0',
  `DEMANDESERVICE_ID` bigint(20) DEFAULT NULL,
  `TYPEPHOTOGRAPHIE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `demandephotographie`
--

INSERT INTO `demandephotographie` (`ID`, `VIDEOGRAPHIE`, `DEMANDESERVICE_ID`, `TYPEPHOTOGRAPHIE_ID`) VALUES
(1, 1, 10, 2);

-- --------------------------------------------------------

--
-- Table structure for table `demandeservice`
--

CREATE TABLE `demandeservice` (
  `ID` bigint(20) NOT NULL,
  `DATECONFIRMATION` date DEFAULT NULL,
  `DATEDERNIERMODIF` date DEFAULT NULL,
  `DATESUPPRESSION` date DEFAULT NULL,
  `DATEDEMANDE` date DEFAULT NULL,
  `DETAIL` varchar(255) DEFAULT NULL,
  `PRIXHT` decimal(38,0) DEFAULT NULL,
  `PRIXTTC` decimal(38,0) DEFAULT NULL,
  `CLIENT_EMAIL` varchar(255) DEFAULT NULL,
  `MANAGERCONFIRMATION_LOGIN` varchar(255) DEFAULT NULL,
  `PLANNING_ID` bigint(20) DEFAULT NULL,
  `SECTEUR_ID` bigint(20) DEFAULT NULL,
  `SERVICE_ID` bigint(20) DEFAULT NULL,
  `SERVICEPRICING_ID` bigint(20) DEFAULT NULL,
  `WORKER_EMAIL` varchar(255) DEFAULT NULL,
  `WORKERTYPE_ID` bigint(20) DEFAULT NULL,
  `TYPEDEMANDE_ID` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `demandeservice`
--

INSERT INTO `demandeservice` (`ID`, `DATECONFIRMATION`, `DATEDERNIERMODIF`, `DATESUPPRESSION`, `DATEDEMANDE`, `DETAIL`, `PRIXHT`, `PRIXTTC`, `CLIENT_EMAIL`, `MANAGERCONFIRMATION_LOGIN`, `PLANNING_ID`, `SECTEUR_ID`, `SERVICE_ID`, `SERVICEPRICING_ID`, `WORKER_EMAIL`, `WORKERTYPE_ID`, `TYPEDEMANDE_ID`) VALUES
(1, '2018-05-28', NULL, '2018-04-23', '2018-03-23', 'please enter quietly because the kids are still sleeping ', '300', '300', 'anas.the.creator@gmail.com', 'benchebani', 1, 2, 1, 1, 'coitcleaners@gmail.com', 1, 'DemandeCleaning'),
(2, '2018-05-30', NULL, '2018-04-16', '2018-03-23', 'none', '50', '50', 'anas.the.creator@gmail.com', 'benchebani', 2, 2, 1, 1, 'merrymaids@gmail.com', 2, 'DemandeCleaning'),
(3, NULL, NULL, NULL, '2018-03-23', 'i left food in the kitchen if you would like to eat', '400', '400', 'htakouit@gmail.com', NULL, 3, 2, 1, 1, 'cleanharbors@gmail.com', 2, 'DemandeCleaning'),
(4, NULL, NULL, '2018-04-16', '2018-03-23', 'if you cloud please check to close the door before you leave thank you', '400', '400', 'newCustomer@gmail.com', 'ysn', 4, 5, 1, 1, 'cleanharbors@gmail.com', 2, 'DemandeCleaning'),
(5, NULL, NULL, NULL, '2018-03-31', 'this is just a test', '600', '600', 'anas.the.creator@gmail.com', NULL, 5, 2, 1, 1, 'coitcleaners@gmail.com', 1, 'DemandeCleaning'),
(6, NULL, NULL, NULL, '2018-03-31', 'test for cleaning', '50', '50', 'anas.the.creator@gmail.com', NULL, 6, 2, 1, 1, 'merrymaids@gmail.com', 2, 'DemandeCleaning'),
(7, NULL, NULL, NULL, '2018-03-31', 'none', '200', '200', 'anas.the.creator@gmail.com', NULL, 7, 2, 1, 1, 'cleanharbors@gmail.com', 2, 'DemandeCleaning'),
(8, NULL, NULL, NULL, '2018-03-31', 'please enter the house quietly ! ', '1000', '1000', 'anas.the.creator@gmail.com', NULL, 8, 3, 1, 1, 'merrymaids@gmail.com', 2, 'DemandeCleaning'),
(9, NULL, NULL, NULL, '2018-04-16', 'rah swaret te7t l7be9', '44', '66', 'newCustomer@gmail.com', NULL, 2, 2, 3, NULL, 'cleanharbors@gmail.com', 1, 'DemandeFormationPersonnel'),
(10, NULL, NULL, NULL, '2018-04-29', 'for a birthday', '500', '500', 'htakouit@gmail.com', NULL, 9, 5, 19, 6, 'MultiServices@gmail.com', 2, NULL),
(11, NULL, NULL, NULL, '2018-04-29', 'my birthday', NULL, NULL, 'htakouit@gmail.com', NULL, 10, 5, 17, 7, 'MultiServices@gmail.com', 2, NULL),
(12, NULL, NULL, NULL, '2018-04-29', 'birthday', NULL, NULL, 'htakouit@gmail.com', NULL, 11, 5, 17, 7, 'MultiServices@gmail.com', 2, NULL),
(13, NULL, NULL, NULL, '2018-04-29', '', NULL, NULL, 'htakouit@gmail.com', NULL, 12, 5, 17, 7, 'MultiServices@gmail.com', 2, NULL),
(14, NULL, NULL, NULL, '2018-04-29', '', NULL, NULL, 'htakouit@gmail.com', NULL, 13, 5, 17, 7, 'MultiServices@gmail.com', 2, NULL),
(15, NULL, NULL, NULL, '2018-04-29', '', NULL, NULL, 'htakouit@gmail.com', NULL, 14, 5, 17, 7, 'MultiServices@gmail.com', 2, NULL),
(16, NULL, NULL, NULL, '2018-04-29', '', NULL, NULL, 'htakouit@gmail.com', NULL, 15, 5, 17, 7, 'MultiServices@gmail.com', 2, NULL),
(17, NULL, NULL, NULL, '2018-04-29', 'My birthday', NULL, NULL, 'htakouit@gmail.com', NULL, 16, 5, 17, 7, 'MultiServices@gmail.com', 2, NULL),
(18, NULL, NULL, NULL, '2018-04-29', '', NULL, NULL, 'htakouit@gmail.com', NULL, 17, 5, 17, 7, 'MultiServices@gmail.com', 2, NULL),
(19, NULL, NULL, NULL, '2018-04-29', '', NULL, NULL, 'htakouit@gmail.com', NULL, 18, 5, 17, 7, 'MultiServices@gmail.com', 2, NULL),
(20, NULL, NULL, NULL, '2018-04-29', '', NULL, NULL, 'htakouit@gmail.com', NULL, 19, 5, 17, 7, 'MultiServices@gmail.com', 2, NULL),
(21, NULL, NULL, NULL, '2018-04-29', '', NULL, NULL, 'htakouit@gmail.com', NULL, 20, 5, 17, 7, 'MultiServices@gmail.com', 2, NULL),
(22, NULL, NULL, NULL, '2018-04-29', '', '150', '150', 'htakouit@gmail.com', NULL, 21, 5, 17, 7, 'MultiServices@gmail.com', 2, NULL),
(23, NULL, NULL, NULL, '2018-04-29', 'My birthday', NULL, NULL, 'htakouit@gmail.com', NULL, 22, 5, 17, 7, 'MultiServices@gmail.com', 2, NULL),
(24, NULL, NULL, NULL, '2018-04-29', 'My birthday', '150', '150', 'htakouit@gmail.com', NULL, 23, 5, 17, 7, 'MultiServices@gmail.com', 2, NULL),
(25, NULL, NULL, NULL, '2018-04-30', 'nothing', '179', '179', 'htakouit@gmail.com', NULL, 24, 5, 14, 2, 'MultiServices@gmail.com', 2, NULL),
(26, NULL, NULL, NULL, '2018-04-30', 'anniversaire', '150', '150', 'htakouit@gmail.com', NULL, 25, 5, 17, 7, 'MultiServices@gmail.com', 2, NULL),
(27, NULL, NULL, NULL, '2018-05-31', 'Rien de speciale', '880', '880', 'anas.the.creator@gmail.com', NULL, 26, 3, 8, 8, 'MultiServices@gmail.com', 2, NULL),
(28, NULL, NULL, NULL, '2018-06-03', 'test location', NULL, NULL, 'anas.the.creator@gmail.com', NULL, 27, 3, 21, NULL, 'MultiServices@gmail.com', 2, NULL),
(29, NULL, NULL, NULL, '2018-06-03', 'nothiiiiiiiiiing', NULL, NULL, 'anas.the.creator@gmail.com', NULL, 28, 3, 21, NULL, 'MultiServices@gmail.com', 2, NULL),
(30, NULL, NULL, NULL, '2018-06-03', 'just a test', NULL, NULL, 'anas.the.creator@gmail.com', NULL, 29, 3, 21, NULL, 'MultiServices@gmail.com', 2, NULL),
(31, NULL, NULL, NULL, '2018-06-03', 'this is just a test', NULL, NULL, 'anas.the.creator@gmail.com', NULL, 30, 3, 21, NULL, 'MultiServices@gmail.com', 2, NULL),
(32, NULL, NULL, NULL, '2018-06-03', 'this is just a test', NULL, NULL, 'anas.the.creator@gmail.com', NULL, 31, 3, 21, NULL, 'MultiServices@gmail.com', 2, NULL),
(33, NULL, NULL, NULL, '2018-06-03', 'this is just a test', NULL, NULL, 'anas.the.creator@gmail.com', NULL, 32, 3, 21, NULL, 'MultiServices@gmail.com', 2, NULL),
(34, NULL, NULL, NULL, '2018-06-03', 'test test test', NULL, NULL, 'anas.the.creator@gmail.com', NULL, 33, 3, 21, NULL, 'MultiServices@gmail.com', 2, NULL),
(35, NULL, NULL, NULL, '2018-06-03', 'testtttt', NULL, NULL, 'anas.the.creator@gmail.com', NULL, 34, 3, 21, NULL, 'MultiServices@gmail.com', 2, NULL),
(36, NULL, NULL, NULL, '2018-06-03', 'this is just a test', '1925', '1925', 'anas.the.creator@gmail.com', NULL, 35, 3, 21, NULL, 'MultiServices@gmail.com', 2, NULL),
(37, NULL, NULL, NULL, '2018-06-03', 'please give me the best car you have', '600', '600', 'anas.the.creator@gmail.com', NULL, 36, 3, 21, NULL, 'MultiServices@gmail.com', 2, NULL),
(38, NULL, NULL, NULL, '2018-06-04', 'nooone', '220', '220', 'anas.the.creator@gmail.com', NULL, 37, 3, 8, 8, 'MultiServices@gmail.com', 0, NULL),
(39, NULL, NULL, NULL, '2018-06-04', NULL, NULL, NULL, 'anas.the.creator@gmail.com', NULL, 38, 3, 21, NULL, NULL, NULL, NULL),
(40, NULL, NULL, NULL, '2018-06-04', NULL, '1161', '1161', 'anas.the.creator@gmail.com', NULL, 39, 3, 21, NULL, 'MultiServices@gmail.com', NULL, NULL),
(41, NULL, NULL, NULL, '2018-06-05', 'un prof bien', '750', '750', 'anas.the.creator@gmail.com', NULL, 40, 3, 22, 12, 'MultiServices@gmail.com', NULL, NULL),
(42, '2018-06-06', NULL, NULL, '2018-06-06', 'un bon teacher', '750', '750', 'anas.the.creator@gmail.com', 'benchebani', 41, 3, 22, 12, 'MultiServices@gmail.com', NULL, NULL),
(43, NULL, NULL, NULL, '2018-06-09', NULL, NULL, NULL, 'anas.the.creator@gmail.com', NULL, 42, 3, 21, NULL, NULL, NULL, NULL),
(44, '2018-06-10', NULL, NULL, '2018-06-09', NULL, NULL, NULL, 'anas.the.creator@gmail.com', 'benchebani', 43, 3, 21, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `demandeserviceconfirmationdetail`
--

CREATE TABLE `demandeserviceconfirmationdetail` (
  `ID` bigint(20) NOT NULL,
  `DATEACTION` date DEFAULT NULL,
  `DEMANDESERVICE_ID` bigint(20) DEFAULT NULL,
  `MANAGER_LOGIN` varchar(255) DEFAULT NULL,
  `TYPEACTION_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `demandevoiture`
--

CREATE TABLE `demandevoiture` (
  `ID` bigint(20) NOT NULL,
  `DEMANDESERVICE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `demandevoiture`
--

INSERT INTO `demandevoiture` (`ID`, `DEMANDESERVICE_ID`) VALUES
(1, 36),
(2, 37),
(3, 40),
(4, 44);

-- --------------------------------------------------------

--
-- Table structure for table `demandevoitureitem`
--

CREATE TABLE `demandevoitureitem` (
  `ID` bigint(20) NOT NULL,
  `DATEDEBUT` date DEFAULT NULL,
  `DATEFIN` date DEFAULT NULL,
  `PRIX` decimal(38,0) DEFAULT NULL,
  `QUANTITE` int(11) DEFAULT NULL,
  `CARBURANT_ID` bigint(20) DEFAULT NULL,
  `DEMANDEVOITURE_ID` bigint(20) DEFAULT NULL,
  `MODELE_ID` bigint(20) DEFAULT NULL,
  `WORKER_EMAIL` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `demandevoitureitem`
--

INSERT INTO `demandevoitureitem` (`ID`, `DATEDEBUT`, `DATEFIN`, `PRIX`, `QUANTITE`, `CARBURANT_ID`, `DEMANDEVOITURE_ID`, `MODELE_ID`, `WORKER_EMAIL`) VALUES
(1, '2018-06-09', '2018-06-14', NULL, 2, 1, 4, 3, 'MultiServices@gmail.com'),
(2, '2018-06-20', '2018-06-26', NULL, 1, 2, 4, 1, 'MultiServices@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `device`
--

CREATE TABLE `device` (
  `ID` bigint(20) NOT NULL,
  `BLOCKED` tinyint(1) DEFAULT '0',
  `DATECONNECTION` date DEFAULT NULL,
  `DEVICECATEGORIE` varchar(255) DEFAULT NULL,
  `IP` varchar(255) DEFAULT NULL,
  `NAVIGATEUR` varchar(255) DEFAULT NULL,
  `OS` varchar(255) DEFAULT NULL,
  `MANAGER_LOGIN` varchar(255) DEFAULT NULL,
  `WORKER_EMAIL` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `device`
--

INSERT INTO `device` (`ID`, `BLOCKED`, `DATECONNECTION`, `DEVICECATEGORIE`, `IP`, `NAVIGATEUR`, `OS`, `MANAGER_LOGIN`, `WORKER_EMAIL`) VALUES
(1, 0, '2018-04-10', 'Personal computer', '172.31.20.65', 'Firefox', 'Windows', 'manager', NULL),
(2, 0, '2018-04-23', 'Personal computer', '192.168.67.2', 'Firefox', 'Windows 8.1', 'ysn', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `eventbudget`
--

CREATE TABLE `eventbudget` (
  `ID` bigint(20) NOT NULL,
  `BUDGET` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `eventbudget`
--

INSERT INTO `eventbudget` (`ID`, `BUDGET`) VALUES
(1, '100dh par personne'),
(2, '150 dh par personne');

-- --------------------------------------------------------

--
-- Table structure for table `eventtype`
--

CREATE TABLE `eventtype` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `eventtype`
--

INSERT INTO `eventtype` (`ID`, `NOM`) VALUES
(1, 'Marriage'),
(2, 'Reception'),
(3, 'Reunion'),
(4, 'Soutenance'),
(5, 'Anniversaire'),
(6, 'other');

-- --------------------------------------------------------

--
-- Table structure for table `faq`
--

CREATE TABLE `faq` (
  `ID` bigint(20) NOT NULL,
  `CORPS` varchar(255) DEFAULT NULL,
  `TITRE` varchar(255) DEFAULT NULL,
  `SERVICE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `faq`
--

INSERT INTO `faq` (`ID`, `CORPS`, `TITRE`, `SERVICE_ID`) VALUES
(1, 'ceci est la reponse de la question numero 1 du service nettoyage', 'question numero 1 NM', 1),
(2, 'ceci est la reponse de la question numero 2 du service nettoyage', 'question numero 2 NM', 1),
(3, 'ceci est la reponse de la question numero 1 du service peinture', 'question numero 1 Peinture', 8),
(4, 'ceci est la reponse de la question numero 2 du service peinture', 'question numero 2 Peinture', 8);

-- --------------------------------------------------------

--
-- Table structure for table `filiere`
--

CREATE TABLE `filiere` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `NIVEAUSCOLAIRE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `filiere`
--

INSERT INTO `filiere` (`ID`, `NOM`, `NIVEAUSCOLAIRE_ID`) VALUES
(1, 'MIPC', 7),
(2, 'MIP', 7),
(3, 'BCG', 7),
(4, 'MIPC', 8),
(5, 'MIP', 8),
(6, 'BCG', 8),
(7, 'PC', 6),
(8, 'SVT', 6),
(9, 'Sc Math A', 6),
(10, 'Sc Math B', 6),
(11, 'Sc Exp', 5),
(12, 'Sc Math', 5),
(13, 'Sciences', 4);

-- --------------------------------------------------------

--
-- Table structure for table `formateurjob`
--

CREATE TABLE `formateurjob` (
  `ID` bigint(20) NOT NULL,
  `FORMATIONPROSUBTYPE_ID` bigint(20) DEFAULT NULL,
  `WORKER_EMAIL` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `formationprosubtype`
--

CREATE TABLE `formationprosubtype` (
  `ID` bigint(20) NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `FORMATIONPROTYPE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `formationprotype`
--

CREATE TABLE `formationprotype` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `gardeningtype`
--

CREATE TABLE `gardeningtype` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `handymantype`
--

CREATE TABLE `handymantype` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `handymantype`
--

INSERT INTO `handymantype` (`ID`, `NOM`) VALUES
(1, 'electricite'),
(2, 'fourniture'),
(3, 'nettoyageClim'),
(4, 'rideaux'),
(5, 'plomberie'),
(6, 'AC Installation'),
(7, 'AC Repair');

-- --------------------------------------------------------

--
-- Table structure for table `home`
--

CREATE TABLE `home` (
  `ID` bigint(20) NOT NULL,
  `NOMBREPIECES` int(11) DEFAULT NULL,
  `HOMETYPE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hometype`
--

CREATE TABLE `hometype` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `manager`
--

CREATE TABLE `manager` (
  `LOGIN` varchar(255) NOT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `BLOCKED` tinyint(1) DEFAULT '0',
  `NOM` varchar(255) DEFAULT NULL,
  `PHONE` varchar(255) DEFAULT NULL,
  `PRENOM` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `manager`
--

INSERT INTO `manager` (`LOGIN`, `PASSWORD`, `BLOCKED`, `NOM`, `PHONE`, `PRENOM`) VALUES
('benchebani', 'anas18', 0, 'benchebani', '0630247385', 'mohamed anas'),
('manager', 'manager', 0, '', '', ''),
('ysn', '123', 0, 'Yassin', '0646925713', 'Mahjoubi');

-- --------------------------------------------------------

--
-- Table structure for table `matiere`
--

CREATE TABLE `matiere` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `FILIERE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `matiere`
--

INSERT INTO `matiere` (`ID`, `NOM`, `FILIERE_ID`) VALUES
(1, 'Math', 7),
(2, 'PC', 7),
(3, 'SVT', 7),
(4, 'Analyse', 1),
(5, 'Algebre', 1),
(6, 'Mecanique', 1),
(7, 'Chimie', 1);

-- --------------------------------------------------------

--
-- Table structure for table `menuformulaire`
--

CREATE TABLE `menuformulaire` (
  `ID` bigint(20) NOT NULL,
  `COMPANYTAB` tinyint(1) DEFAULT '0',
  `DETAILSTAB` tinyint(1) DEFAULT '0',
  `IMAGENAME` varchar(255) DEFAULT NULL,
  `INFOTAB` tinyint(1) DEFAULT '0',
  `PAYEMENTTAB` tinyint(1) DEFAULT '0',
  `SUMMARYTAB` tinyint(1) DEFAULT '0',
  `SERVICE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `menuformulaire`
--

INSERT INTO `menuformulaire` (`ID`, `COMPANYTAB`, `DETAILSTAB`, `IMAGENAME`, `INFOTAB`, `PAYEMENTTAB`, `SUMMARYTAB`, `SERVICE_ID`) VALUES
(1, 1, 1, 'nettoyageMaison', 1, 1, 1, 1),
(2, 1, 1, 'photographie', 1, 1, 1, 19),
(3, 0, 1, 'LocationVoiture', 1, 1, 0, 21),
(4, 1, 1, 'formationpersonnel', 1, 1, 1, 22),
(5, 1, 1, 'nan', 1, 1, 1, 17),
(6, 1, 1, 'deratisation', 0, 1, 1, 14),
(7, 1, 1, 'demenagement', 1, 1, 1, 5),
(8, 1, 1, 'peinture', 1, 1, 1, 8),
(9, 1, 1, 'plomberie', 1, 1, 1, 11),
(10, 1, 1, 'electricite', 1, 1, 1, 10),
(11, 1, 1, 'menuiserie', 1, 1, 1, 12);

-- --------------------------------------------------------

--
-- Table structure for table `niveauscolaire`
--

CREATE TABLE `niveauscolaire` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `niveauscolaire`
--

INSERT INTO `niveauscolaire` (`ID`, `NOM`) VALUES
(1, '1ère année College'),
(2, '2ème année College'),
(3, '3ème année College'),
(4, 'Tronc Commun'),
(5, '1ère année Bac'),
(6, '2ème année Bac'),
(7, '1ère année Universitaire'),
(8, '2ème année Universitaire');

-- --------------------------------------------------------

--
-- Table structure for table `packaging`
--

CREATE TABLE `packaging` (
  `ID` bigint(20) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `SERVICE_ID` bigint(20) DEFAULT NULL,
  `SERVICEPRICING_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `packaging`
--

INSERT INTO `packaging` (`ID`, `NAME`, `SERVICE_ID`, `SERVICEPRICING_ID`) VALUES
(1, 'cockroaches ', 14, 2),
(2, 'cockroaches ', 14, 3),
(3, 'cockroaches ', 14, 4),
(4, 'cockroaches ', 14, 4),
(5, 'bed bugs', 14, 2),
(6, 'bed bugs', 14, 3),
(7, 'bed bugs', 14, 3),
(8, 'bed bugs', 14, 5),
(9, 'ants ', 14, 2),
(10, 'ants ', 14, 3),
(11, 'ants ', 14, 4),
(12, 'ants ', 14, 5);

-- --------------------------------------------------------

--
-- Table structure for table `paintingtype`
--

CREATE TABLE `paintingtype` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `pays`
--

CREATE TABLE `pays` (
  `ID` bigint(20) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `TVA` decimal(38,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `pays`
--

INSERT INTO `pays` (`ID`, `NAME`, `TVA`) VALUES
(1, 'Morocco', '20'),
(2, 'Spain', '30');

-- --------------------------------------------------------

--
-- Table structure for table `pestcontroltype`
--

CREATE TABLE `pestcontroltype` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `pestcontroltype`
--

INSERT INTO `pestcontroltype` (`ID`, `NOM`) VALUES
(1, 'cockroaches'),
(2, 'bed bugs'),
(3, 'ants'),
(4, 'general');

-- --------------------------------------------------------

--
-- Table structure for table `photographietype`
--

CREATE TABLE `photographietype` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `photographietype`
--

INSERT INTO `photographietype` (`ID`, `NOM`) VALUES
(1, 'Marriage'),
(2, 'Shooting');

-- --------------------------------------------------------

--
-- Table structure for table `planning`
--

CREATE TABLE `planning` (
  `ID` bigint(20) NOT NULL,
  `DATEDEBUT` date DEFAULT NULL,
  `DATEFIN` date DEFAULT NULL,
  `DATEONCE` date DEFAULT NULL,
  `TIMING_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `planning`
--

INSERT INTO `planning` (`ID`, `DATEDEBUT`, `DATEFIN`, `DATEONCE`, `TIMING_ID`) VALUES
(1, '2018-04-18', '2018-08-31', NULL, NULL),
(2, NULL, NULL, '2018-05-24', 1),
(3, '2018-07-25', '2018-11-29', NULL, NULL),
(4, '2018-03-23', '2019-02-20', NULL, NULL),
(5, NULL, NULL, '2018-03-31', 13),
(6, '2018-03-08', '2018-09-21', NULL, NULL),
(7, '2018-03-14', '2018-03-24', NULL, NULL),
(8, NULL, NULL, '2018-03-31', 5),
(9, NULL, NULL, '2018-05-01', 5),
(10, NULL, NULL, '2018-05-02', 1),
(11, NULL, NULL, '2018-05-01', 1),
(12, NULL, NULL, '2018-04-11', 4),
(13, NULL, NULL, NULL, NULL),
(14, NULL, NULL, NULL, NULL),
(15, NULL, NULL, NULL, NULL),
(16, NULL, NULL, '2018-05-02', 1),
(17, NULL, NULL, NULL, NULL),
(18, NULL, NULL, '2018-05-16', 1),
(19, NULL, NULL, '2018-04-30', 3),
(20, NULL, NULL, NULL, NULL),
(21, NULL, NULL, NULL, NULL),
(22, NULL, NULL, '2018-05-02', NULL),
(23, NULL, NULL, '2018-05-02', 2),
(24, NULL, NULL, NULL, NULL),
(25, NULL, NULL, '2018-05-02', 19),
(26, NULL, NULL, '2018-05-31', 15),
(27, '2018-06-03', '2018-06-10', NULL, NULL),
(28, '2018-06-03', '2018-06-10', NULL, NULL),
(29, '2018-06-03', '2018-06-21', NULL, NULL),
(30, '2018-06-14', '2018-06-20', NULL, NULL),
(31, '2018-06-16', '2018-06-20', NULL, NULL),
(32, '2018-06-16', '2018-06-20', NULL, NULL),
(33, '2018-06-15', '2018-06-20', NULL, NULL),
(34, '2018-06-16', '2018-06-23', NULL, NULL),
(35, '2018-06-03', '2018-06-10', NULL, NULL),
(36, '2018-06-03', '2018-06-05', NULL, NULL),
(37, NULL, NULL, '2018-06-07', 2),
(38, NULL, NULL, NULL, NULL),
(39, '2018-06-14', '2018-06-18', NULL, NULL),
(40, NULL, NULL, NULL, NULL),
(41, NULL, NULL, '2018-06-22', 3),
(42, NULL, NULL, NULL, NULL),
(43, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `planningitem`
--

CREATE TABLE `planningitem` (
  `ID` bigint(20) NOT NULL,
  `NUMEROJOUR` int(11) DEFAULT NULL,
  `REPETITION` int(11) DEFAULT NULL,
  `DAY_ID` bigint(20) DEFAULT NULL,
  `PLANNING_ID` bigint(20) DEFAULT NULL,
  `TIMING_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `planningitem`
--

INSERT INTO `planningitem` (`ID`, `NUMEROJOUR`, `REPETITION`, `DAY_ID`, `PLANNING_ID`, `TIMING_ID`) VALUES
(1, 0, 1, 1, 6, 1),
(2, 0, 1, 3, 1, 5),
(3, 3, 2, NULL, 1, 9),
(4, 1, 2, NULL, 3, 15),
(5, 0, 1, 5, 3, 13),
(51, 7, 2, 1, 4, 1),
(52, 0, 1, 2, 4, 5),
(101, 0, 1, 1, 6, 1),
(151, 5, 2, NULL, 7, 1),
(152, 0, 1, 5, 7, 9);

-- --------------------------------------------------------

--
-- Table structure for table `profjob`
--

CREATE TABLE `profjob` (
  `ID` bigint(20) NOT NULL,
  `MATIERE_ID` bigint(20) DEFAULT NULL,
  `WORKER_EMAIL` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `review`
--

CREATE TABLE `review` (
  `ID` bigint(20) NOT NULL,
  `CORPS` varchar(255) DEFAULT NULL,
  `DATEREVIEW` date DEFAULT NULL,
  `STARS` int(11) DEFAULT NULL,
  `SUJET` varchar(255) DEFAULT NULL,
  `WORKER_EMAIL` varchar(255) DEFAULT NULL,
  `CLIENT_EMAIL` varchar(255) DEFAULT NULL,
  `DEMANDESERVICE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `review`
--

INSERT INTO `review` (`ID`, `CORPS`, `DATEREVIEW`, `STARS`, `SUJET`, `WORKER_EMAIL`, `CLIENT_EMAIL`, `DEMANDESERVICE_ID`) VALUES
(1, 'c\'est la premiere fois que j\'etulise la platforme, est c\'est vraiment tres facile. le service offert par l\'entreprise est aussi genial', '2018-05-16', 4, 'Service magnifique', 'cleanharbors@gmail.com', 'anas.the.creator@gmail.com', 1),
(2, 'service tres rapide est professionnelle c\'est l\'un des mailleurs experience', '2018-05-23', 5, 'Service profesionnelle', 'cleanharbors@gmail.com', 'htakouit@gmail.com', 3),
(4, 'test commantaire ', '2018-05-30', 5, 'bon service', 'MultiServices@gmail.com', 'htakouit@gmail.com', 10),
(5, 'j\'ai bien apreciet le service ', '2018-06-03', 4, 'etat extraordinaire', 'MultiServices@gmail.com', 'anas.the.creator@gmail.com', 37);

-- --------------------------------------------------------

--
-- Table structure for table `secteur`
--

CREATE TABLE `secteur` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `VILLE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `secteur`
--

INSERT INTO `secteur` (`ID`, `NOM`, `VILLE_ID`) VALUES
(1, 'Gueliz', 1),
(2, 'Daoudiate', 1),
(3, 'Massira', 1),
(4, 'Barnoussi', 2),
(5, 'Hay Hassani', 2),
(6, 'Mediouna', 2),
(7, 'Alonso Martínez', 3),
(8, 'Las Cortes', 3),
(9, 'Sant Martí', 4),
(10, 'Eixample', 4);

-- --------------------------------------------------------

--
-- Table structure for table `sequence`
--

CREATE TABLE `sequence` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sequence`
--

INSERT INTO `sequence` (`SEQ_NAME`, `SEQ_COUNT`) VALUES
('SEQ_GEN', '0');

-- --------------------------------------------------------

--
-- Table structure for table `service`
--

CREATE TABLE `service` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `CATEGORIE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `service`
--

INSERT INTO `service` (`ID`, `NOM`, `CATEGORIE_ID`) VALUES
(1, 'nettoyageMaison', 1),
(2, 'Nettoyage Complet', 1),
(3, 'Nettoyage Piscine', 1),
(4, 'Nettoyage Divers', 1),
(5, 'demenagement', 2),
(6, 'Demenagement international', 2),
(7, 'Stockage', 2),
(8, 'peinture', 3),
(9, 'Handyman', 3),
(10, 'electricite', 3),
(11, 'plomberie', 3),
(12, 'menuiserie', 3),
(13, 'Climatisation', 3),
(14, 'deratisation', 4),
(15, 'Gardiennage', 4),
(16, 'Restauration', 5),
(17, 'traiteur', 5),
(18, 'Babysitters', 6),
(19, 'photographie', 7),
(20, 'Videographie', 7),
(21, 'locationvoiture', 8),
(22, 'formationpersonnel', 9);

-- --------------------------------------------------------

--
-- Table structure for table `servicepricing`
--

CREATE TABLE `servicepricing` (
  `ID` bigint(20) NOT NULL,
  `DATEAPPLICATION` date DEFAULT NULL,
  `PRIX` decimal(38,0) DEFAULT NULL,
  `SERVICE_ID` bigint(20) DEFAULT NULL,
  `UNITE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `servicepricing`
--

INSERT INTO `servicepricing` (`ID`, `DATEAPPLICATION`, `PRIX`, `SERVICE_ID`, `UNITE_ID`) VALUES
(1, '2015-03-03', '50', 1, 1),
(2, '2018-03-01', '179', 14, 2),
(3, '2018-03-01', '300', 14, 3),
(4, '2018-03-01', '200', 14, 5),
(5, '2018-03-01', '370', 14, 4),
(6, '2018-04-29', '500', 19, 6),
(7, '2018-04-29', '150', 17, 7),
(8, '2017-05-23', '220', 8, 8),
(9, '2017-05-23', '120', 10, 8),
(10, '2017-06-18', '150', 11, 8),
(11, '2017-06-18', '120', 12, 8),
(12, '2016-06-13', '150', 22, 10);

-- --------------------------------------------------------

--
-- Table structure for table `serviceville`
--

CREATE TABLE `serviceville` (
  `ID` bigint(20) NOT NULL,
  `SERVICE_ID` bigint(20) DEFAULT NULL,
  `VILLE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `serviceville`
--

INSERT INTO `serviceville` (`ID`, `SERVICE_ID`, `VILLE_ID`) VALUES
(1, 1, 1),
(2, 2, 1),
(3, 5, 1),
(4, 7, 1),
(5, 8, 1),
(6, 9, 1),
(7, 13, 1),
(8, 16, 1),
(9, 5, 2),
(10, 12, 2),
(11, 19, 2),
(12, 10, 2),
(13, 17, 2),
(14, 18, 2),
(15, 16, 2),
(17, 6, 3),
(18, 5, 3),
(19, 6, 4),
(20, 5, 4);

-- --------------------------------------------------------

--
-- Table structure for table `supplementdemandeevent`
--

CREATE TABLE `supplementdemandeevent` (
  `ID` bigint(20) NOT NULL,
  `DEMANDEEVENT_ID` bigint(20) DEFAULT NULL,
  `SUPPLEMENTEVENT_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `supplementdemandeevent`
--

INSERT INTO `supplementdemandeevent` (`ID`, `DEMANDEEVENT_ID`, `SUPPLEMENTEVENT_ID`) VALUES
(1, 1, 2),
(2, 2, 1),
(3, 2, 2),
(4, 3, 2),
(5, 3, 3);

-- --------------------------------------------------------

--
-- Table structure for table `supplementevent`
--

CREATE TABLE `supplementevent` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `supplementevent`
--

INSERT INTO `supplementevent` (`ID`, `NOM`) VALUES
(1, 'Serveurs'),
(2, 'Tables et fournitures'),
(3, 'Decoration');

-- --------------------------------------------------------

--
-- Table structure for table `timing`
--

CREATE TABLE `timing` (
  `ID` bigint(20) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `VALEUR` decimal(38,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `timing`
--

INSERT INTO `timing` (`ID`, `NAME`, `VALEUR`) VALUES
(1, '08:00', '8'),
(2, '08:30', '8'),
(3, '09:00', '9'),
(4, '09:30', '9'),
(5, '10:00', '10'),
(6, '10:30', '10'),
(7, '11:00', '11'),
(8, '11:30', '11'),
(9, '12:00', '12'),
(10, '12:30', '12'),
(11, '13:00', '13'),
(12, '13:30', '13'),
(13, '14:00', '14'),
(14, '14:30', '14'),
(15, '15:00', '15'),
(16, '15:30', '15'),
(17, '16:00', '16'),
(18, '16:30', '16'),
(19, '17:00', '17');

-- --------------------------------------------------------

--
-- Table structure for table `typeaction`
--

CREATE TABLE `typeaction` (
  `ID` bigint(20) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `typeaction`
--

INSERT INTO `typeaction` (`ID`, `NAME`) VALUES
(1, 'Confirmation'),
(2, 'Suppression');

-- --------------------------------------------------------

--
-- Table structure for table `typedemande`
--

CREATE TABLE `typedemande` (
  `ID` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `typedemande`
--

INSERT INTO `typedemande` (`ID`) VALUES
('DemandeBabySitting'),
('DemandeCleaning'),
('DemandeEvent'),
('DemandeFormationPersonnel'),
('DemandeGardening'),
('DemandeHandyMan'),
('DemandeMoving'),
('DemandePainting'),
('DemandePestControl'),
('DemandePhotographie'),
('DemandeVoiture');

-- --------------------------------------------------------

--
-- Table structure for table `unite`
--

CREATE TABLE `unite` (
  `ID` bigint(20) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `unite`
--

INSERT INTO `unite` (`ID`, `NAME`) VALUES
(1, 'CleanerPerHour'),
(2, 'Studio'),
(3, '1 BR'),
(4, '2 BR'),
(5, '3 BR'),
(6, 'PhotographiePerHour'),
(7, 'EventPerHour'),
(8, 'HandyManPerHour'),
(9, 'VoitureParJour'),
(10, 'FormationParPersonne');

-- --------------------------------------------------------

--
-- Table structure for table `ville`
--

CREATE TABLE `ville` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `PAYS_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ville`
--

INSERT INTO `ville` (`ID`, `NOM`, `PAYS_ID`) VALUES
(1, 'Marrakesh', 1),
(2, 'Casablanca', 1),
(3, 'Madrid', 2),
(4, 'Barcelona', 2);

-- --------------------------------------------------------

--
-- Table structure for table `voiture`
--

CREATE TABLE `voiture` (
  `ID` bigint(20) NOT NULL,
  `QUANTITE` int(11) DEFAULT NULL,
  `CARBURANT_ID` bigint(20) DEFAULT NULL,
  `WORKER_EMAIL` varchar(255) DEFAULT NULL,
  `MODELE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `voiture`
--

INSERT INTO `voiture` (`ID`, `QUANTITE`, `CARBURANT_ID`, `WORKER_EMAIL`, `MODELE_ID`) VALUES
(1, 2, 1, 'MultiServices@gmail.com', 1),
(2, 4, 2, 'MultiServices@gmail.com', 2),
(3, 1, 1, 'MultiServices@gmail.com', 3),
(4, 1, 2, 'MultiServices@gmail.com', 3),
(5, 2, 2, 'top.sarl@gmail.com', 5);

-- --------------------------------------------------------

--
-- Table structure for table `voiturecarburantcouleur`
--

CREATE TABLE `voiturecarburantcouleur` (
  `ID` bigint(20) NOT NULL,
  `CARBURANT_ID` bigint(20) DEFAULT NULL,
  `COULEUR_ID` bigint(20) DEFAULT NULL,
  `VOITURE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `voiturecarburantcouleur`
--

INSERT INTO `voiturecarburantcouleur` (`ID`, `CARBURANT_ID`, `COULEUR_ID`, `VOITURE_ID`) VALUES
(1, 1, 1, 1),
(2, 2, 2, 2),
(3, 1, 4, 3),
(4, 2, 3, 4),
(5, 2, 5, 5);

-- --------------------------------------------------------

--
-- Table structure for table `voitureimage`
--

CREATE TABLE `voitureimage` (
  `ID` bigint(20) NOT NULL,
  `IMAGE` varchar(255) DEFAULT NULL,
  `VOITURE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `voituremarque`
--

CREATE TABLE `voituremarque` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `voituremarque`
--

INSERT INTO `voituremarque` (`ID`, `NOM`) VALUES
(1, 'Renault'),
(2, 'Ford');

-- --------------------------------------------------------

--
-- Table structure for table `voituremodele`
--

CREATE TABLE `voituremodele` (
  `ID` bigint(20) NOT NULL,
  `ANNEE` varchar(255) DEFAULT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `MARQUE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `voituremodele`
--

INSERT INTO `voituremodele` (`ID`, `ANNEE`, `NOM`, `MARQUE_ID`) VALUES
(1, '2016', 'Clio', 1),
(2, '2014', 'Fluence', 1),
(3, '2015', 'Mégane', 1),
(4, '2016', 'Fiesta', 2),
(5, '2017', 'Focus', 2),
(6, '2014', 'Kuga', 2);

-- --------------------------------------------------------

--
-- Table structure for table `voiturepricing`
--

CREATE TABLE `voiturepricing` (
  `ID` bigint(20) NOT NULL,
  `DATEAPPLICATION` date DEFAULT NULL,
  `PRIX` decimal(38,0) DEFAULT NULL,
  `UNITE_ID` bigint(20) DEFAULT NULL,
  `VOITURE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `voiturepricing`
--

INSERT INTO `voiturepricing` (`ID`, `DATEAPPLICATION`, `PRIX`, `UNITE_ID`, `VOITURE_ID`) VALUES
(1, '2018-06-01', '300', 9, 1),
(2, '2018-06-01', '250', 9, 2),
(3, '2018-06-01', '275', 9, 3),
(4, '2018-06-01', '387', 9, 4),
(5, '2018-06-01', '420', 9, 5);

-- --------------------------------------------------------

--
-- Table structure for table `worker`
--

CREATE TABLE `worker` (
  `EMAIL` varchar(255) NOT NULL,
  `ACCEPTED` tinyint(1) DEFAULT '0',
  `BLOCKED` tinyint(1) DEFAULT '0',
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `NOMBREEMPLOYE` int(11) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `PHONE` varchar(255) DEFAULT NULL,
  `SITEWEB` varchar(255) DEFAULT NULL,
  `WORKERTYPE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `worker`
--

INSERT INTO `worker` (`EMAIL`, `ACCEPTED`, `BLOCKED`, `DESCRIPTION`, `NOM`, `NOMBREEMPLOYE`, `PASSWORD`, `PHONE`, `SITEWEB`, `WORKERTYPE_ID`) VALUES
('cleanharbors@gmail.com', 1, 0, 'you probably heard of us, the leading company for cleaning services in over 26 countries', 'Clean Harbors', 32, 'cleanharbors@gmail.com', '0679120435', 'www.cleanharbors.com', 2),
('coitcleaners@gmail.com', 0, 0, 'An inspiring individual with many years of experiences in cleaning', 'Coit Cleaners ', 5, 'coitcleaners@gmail.com', '0613467982', 'www.coitcleaners.com', 1),
('merrymaids@gmail.com', 0, 0, 'we can guarantee that our services are the best in the cleaning industry', 'Merry Maids', 54, 'merrymaids@gmail.com', '0687125903', 'www.merrymaids.com', 2),
('MultiServices@gmail.com', 1, 0, 'We do everything for the sake of testing ', 'Multi Services', 100, 'MultiServices@gmail.com', '0677352220', 'MultiServices.com', 2),
('mycompany@gmail.com', 1, 0, 'mailleurs services', 'myCompany', 50, 'mycompany@gmail.com', '0612457982', 'www.mycompany.com', 1),
('taskrabbit@gmail.com', 1, 0, 'we hire specialist from all around the globe so that you can have the best services', 'TaskRabbit ', 78, 'taskrabbit@gmail.com', '0612064367', 'www.taskrabbit.com', 2),
('top.sarl@gmail.com', 1, 0, 'nos services sont les mailleurs dans le marche', 'Top S.AR.L', 24, 'top.sarl@gmail.com', '0536798152', 'www.topsarl.com', 2);

-- --------------------------------------------------------

--
-- Table structure for table `workerjob`
--

CREATE TABLE `workerjob` (
  `ID` bigint(20) NOT NULL,
  `SECTEUR_ID` bigint(20) DEFAULT NULL,
  `SERVICE_ID` bigint(20) DEFAULT NULL,
  `WORKER_EMAIL` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `workerjob`
--

INSERT INTO `workerjob` (`ID`, `SECTEUR_ID`, `SERVICE_ID`, `WORKER_EMAIL`) VALUES
(1, 1, 1, 'cleanharbors@gmail.com'),
(2, 2, 1, 'merrymaids@gmail.com'),
(3, 4, 4, 'taskrabbit@gmail.com'),
(4, 5, 1, 'coitcleaners@gmail.com'),
(5, 4, 19, 'MultiServices@gmail.com'),
(6, 1, 17, 'MultiServices@gmail.com'),
(7, 1, 14, 'MultiServices@gmail.com'),
(8, 3, 8, 'MultiServices@gmail.com'),
(9, 1, 10, 'MultiServices@gmail.com'),
(10, 2, 21, 'MultiServices@gmail.com'),
(11, 1, 22, 'MultiServices@gmail.com'),
(12, 1, 1, 'top.sarl@gmail.com'),
(13, 2, 21, 'top.sarl@gmail.com'),
(14, 3, 10, 'top.sarl@gmail.com'),
(15, 3, 1, 'mycompany@gmail.com'),
(16, 2, 10, 'mycompany@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `workertype`
--

CREATE TABLE `workertype` (
  `ID` bigint(20) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `workertype`
--

INSERT INTO `workertype` (`ID`, `NAME`) VALUES
(0, 'Choisis Pour Moi'),
(1, 'Personne Physique'),
(2, 'Personne Morale');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `carburant`
--
ALTER TABLE `carburant`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `childdemandebabysitting`
--
ALTER TABLE `childdemandebabysitting`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_CHILDDEMANDEBABYSITTING_DEMANDEBABYSITTING_ID` (`DEMANDEBABYSITTING_ID`);

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`EMAIL`),
  ADD KEY `FK_CLIENT_SECTEUR_ID` (`SECTEUR_ID`);

--
-- Indexes for table `couleur`
--
ALTER TABLE `couleur`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `cuisinedemandeevent`
--
ALTER TABLE `cuisinedemandeevent`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_CUISINEDEMANDEEVENT_DEMANDEEVENT_ID` (`DEMANDEEVENT_ID`),
  ADD KEY `FK_CUISINEDEMANDEEVENT_CUISINE_ID` (`CUISINE_ID`);

--
-- Indexes for table `cuisinetype`
--
ALTER TABLE `cuisinetype`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `day`
--
ALTER TABLE `day`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `demandebabysitting`
--
ALTER TABLE `demandebabysitting`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_DEMANDEBABYSITTING_DEMANDESERVICE_ID` (`DEMANDESERVICE_ID`);

--
-- Indexes for table `demandecleaning`
--
ALTER TABLE `demandecleaning`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_DEMANDECLEANING_DEMANDESERVICE_ID` (`DEMANDESERVICE_ID`);

--
-- Indexes for table `demandeevent`
--
ALTER TABLE `demandeevent`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_DEMANDEEVENT_DEMANDESERVICE_ID` (`DEMANDESERVICE_ID`),
  ADD KEY `FK_DEMANDEEVENT_EVENTBUDGET_ID` (`EVENTBUDGET_ID`),
  ADD KEY `FK_DEMANDEEVENT_EVENTTYPE_ID` (`EVENTTYPE_ID`);

--
-- Indexes for table `demandeformationpersonnel`
--
ALTER TABLE `demandeformationpersonnel`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_DEMANDEFORMATIONPERSONNEL_DEMANDESERVICE_ID` (`DEMANDESERVICE_ID`),
  ADD KEY `FK_DEMANDEFORMATIONPERSONNEL_FILIERE_ID` (`FILIERE_ID`),
  ADD KEY `FK_DEMANDEFORMATIONPERSONNEL_MATIERE_ID` (`MATIERE_ID`);

--
-- Indexes for table `demandeformationpro`
--
ALTER TABLE `demandeformationpro`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_DEMANDEFORMATIONPRO_DEMANDESERVICE_ID` (`DEMANDESERVICE_ID`),
  ADD KEY `FK_DEMANDEFORMATIONPRO_FORMATEUR_EMAIL` (`FORMATEUR_EMAIL`);

--
-- Indexes for table `demandegardening`
--
ALTER TABLE `demandegardening`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_DEMANDEGARDENING_GARDENINGTYPE_ID` (`GARDENINGTYPE_ID`),
  ADD KEY `FK_DEMANDEGARDENING_DEMANDESERVICE_ID` (`DEMANDESERVICE_ID`),
  ADD KEY `FK_DEMANDEGARDENING_HOME_ID` (`HOME_ID`);

--
-- Indexes for table `demandehandyman`
--
ALTER TABLE `demandehandyman`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_DEMANDEHANDYMAN_SERVICE_ID` (`SERVICE_ID`),
  ADD KEY `FK_DEMANDEHANDYMAN_DEMANDESERVICE_ID` (`DEMANDESERVICE_ID`);

--
-- Indexes for table `demandemoving`
--
ALTER TABLE `demandemoving`
  ADD PRIMARY KEY (`DEMANDESERVICE_ID`),
  ADD KEY `FK_DEMANDEMOVING_VILLEDEPART_ID` (`VILLEDEPART_ID`),
  ADD KEY `FK_DEMANDEMOVING_VILLEARRIVE_ID` (`VILLEARRIVE_ID`);

--
-- Indexes for table `demandepainting`
--
ALTER TABLE `demandepainting`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_DEMANDEPAINTING_PAINTING_ID` (`PAINTING_ID`),
  ADD KEY `FK_DEMANDEPAINTING_DEMANDESERVICE_ID` (`DEMANDESERVICE_ID`);

--
-- Indexes for table `demandepestcontrol`
--
ALTER TABLE `demandepestcontrol`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_DEMANDEPESTCONTROL_DEMANDESERVICE_ID` (`DEMANDESERVICE_ID`),
  ADD KEY `FK_DEMANDEPESTCONTROL_TYPEOFPESTCONTROL_ID` (`TYPEOFPESTCONTROL_ID`);

--
-- Indexes for table `demandephotographie`
--
ALTER TABLE `demandephotographie`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_DEMANDEPHOTOGRAPHIE_TYPEPHOTOGRAPHIE_ID` (`TYPEPHOTOGRAPHIE_ID`),
  ADD KEY `FK_DEMANDEPHOTOGRAPHIE_DEMANDESERVICE_ID` (`DEMANDESERVICE_ID`);

--
-- Indexes for table `demandeservice`
--
ALTER TABLE `demandeservice`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_DEMANDESERVICE_TYPEDEMANDE_ID` (`TYPEDEMANDE_ID`),
  ADD KEY `FK_DEMANDESERVICE_PLANNING_ID` (`PLANNING_ID`),
  ADD KEY `FK_DEMANDESERVICE_SERVICE_ID` (`SERVICE_ID`),
  ADD KEY `FK_DEMANDESERVICE_WORKER_EMAIL` (`WORKER_EMAIL`),
  ADD KEY `FK_DEMANDESERVICE_CLIENT_EMAIL` (`CLIENT_EMAIL`),
  ADD KEY `FK_DEMANDESERVICE_MANAGERCONFIRMATION_LOGIN` (`MANAGERCONFIRMATION_LOGIN`),
  ADD KEY `FK_DEMANDESERVICE_SECTEUR_ID` (`SECTEUR_ID`),
  ADD KEY `FK_DEMANDESERVICE_WORKERTYPE_ID` (`WORKERTYPE_ID`),
  ADD KEY `FK_DEMANDESERVICE_SERVICEPRICING_ID` (`SERVICEPRICING_ID`);

--
-- Indexes for table `demandeserviceconfirmationdetail`
--
ALTER TABLE `demandeserviceconfirmationdetail`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_DEMANDESERVICECONFIRMATIONDETAIL_MANAGER_LOGIN` (`MANAGER_LOGIN`),
  ADD KEY `FK_DEMANDESERVICECONFIRMATIONDETAIL_TYPEACTION_ID` (`TYPEACTION_ID`),
  ADD KEY `DEMANDESERVICECONFIRMATIONDETAIL_DEMANDESERVICE_ID` (`DEMANDESERVICE_ID`);

--
-- Indexes for table `demandevoiture`
--
ALTER TABLE `demandevoiture`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_DEMANDEVOITURE_DEMANDESERVICE_ID` (`DEMANDESERVICE_ID`);

--
-- Indexes for table `demandevoitureitem`
--
ALTER TABLE `demandevoitureitem`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_DEMANDEVOITUREITEM_DEMANDEVOITURE_ID` (`DEMANDEVOITURE_ID`),
  ADD KEY `FK_DEMANDEVOITUREITEM_WORKER_EMAIL` (`WORKER_EMAIL`),
  ADD KEY `FK_DEMANDEVOITUREITEM_CARBURANT_ID` (`CARBURANT_ID`),
  ADD KEY `FK_DEMANDEVOITUREITEM_MODELE_ID` (`MODELE_ID`);

--
-- Indexes for table `device`
--
ALTER TABLE `device`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_DEVICE_MANAGER_LOGIN` (`MANAGER_LOGIN`),
  ADD KEY `FK_DEVICE_WORKER_EMAIL` (`WORKER_EMAIL`);

--
-- Indexes for table `eventbudget`
--
ALTER TABLE `eventbudget`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `eventtype`
--
ALTER TABLE `eventtype`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `faq`
--
ALTER TABLE `faq`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_FAQ_SERVICE_ID` (`SERVICE_ID`);

--
-- Indexes for table `filiere`
--
ALTER TABLE `filiere`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_FILIERE_NIVEAUSCOLAIRE_ID` (`NIVEAUSCOLAIRE_ID`);

--
-- Indexes for table `formateurjob`
--
ALTER TABLE `formateurjob`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_FORMATEURJOB_WORKER_EMAIL` (`WORKER_EMAIL`),
  ADD KEY `FK_FORMATEURJOB_FORMATIONPROSUBTYPE_ID` (`FORMATIONPROSUBTYPE_ID`);

--
-- Indexes for table `formationprosubtype`
--
ALTER TABLE `formationprosubtype`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_FORMATIONPROSUBTYPE_FORMATIONPROTYPE_ID` (`FORMATIONPROTYPE_ID`);

--
-- Indexes for table `formationprotype`
--
ALTER TABLE `formationprotype`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `gardeningtype`
--
ALTER TABLE `gardeningtype`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `handymantype`
--
ALTER TABLE `handymantype`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `home`
--
ALTER TABLE `home`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_HOME_HOMETYPE_ID` (`HOMETYPE_ID`);

--
-- Indexes for table `hometype`
--
ALTER TABLE `hometype`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `manager`
--
ALTER TABLE `manager`
  ADD PRIMARY KEY (`LOGIN`);

--
-- Indexes for table `matiere`
--
ALTER TABLE `matiere`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_MATIERE_FILIERE_ID` (`FILIERE_ID`);

--
-- Indexes for table `menuformulaire`
--
ALTER TABLE `menuformulaire`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_MENUFORMULAIRE_SERVICE_ID` (`SERVICE_ID`);

--
-- Indexes for table `niveauscolaire`
--
ALTER TABLE `niveauscolaire`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `packaging`
--
ALTER TABLE `packaging`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_PACKAGING_SERVICE_ID` (`SERVICE_ID`),
  ADD KEY `FK_PACKAGING_SERVICEPRICING_ID` (`SERVICEPRICING_ID`);

--
-- Indexes for table `paintingtype`
--
ALTER TABLE `paintingtype`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `pays`
--
ALTER TABLE `pays`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `pestcontroltype`
--
ALTER TABLE `pestcontroltype`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `photographietype`
--
ALTER TABLE `photographietype`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `planning`
--
ALTER TABLE `planning`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_PLANNING_TIMING_ID` (`TIMING_ID`);

--
-- Indexes for table `planningitem`
--
ALTER TABLE `planningitem`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_PLANNINGITEM_TIMING_ID` (`TIMING_ID`),
  ADD KEY `FK_PLANNINGITEM_PLANNING_ID` (`PLANNING_ID`),
  ADD KEY `FK_PLANNINGITEM_DAY_ID` (`DAY_ID`);

--
-- Indexes for table `profjob`
--
ALTER TABLE `profjob`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_PROFJOB_MATIERE_ID` (`MATIERE_ID`),
  ADD KEY `FK_PROFJOB_WORKER_EMAIL` (`WORKER_EMAIL`);

--
-- Indexes for table `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_REVIEW_WORKER_EMAIL` (`WORKER_EMAIL`),
  ADD KEY `FK_REVIEW_DEMANDESERVICE_ID` (`DEMANDESERVICE_ID`),
  ADD KEY `FK_REVIEW_CLIENT_EMAIL` (`CLIENT_EMAIL`);

--
-- Indexes for table `secteur`
--
ALTER TABLE `secteur`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_SECTEUR_VILLE_ID` (`VILLE_ID`);

--
-- Indexes for table `sequence`
--
ALTER TABLE `sequence`
  ADD PRIMARY KEY (`SEQ_NAME`);

--
-- Indexes for table `service`
--
ALTER TABLE `service`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_SERVICE_CATEGORIE_ID` (`CATEGORIE_ID`);

--
-- Indexes for table `servicepricing`
--
ALTER TABLE `servicepricing`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_SERVICEPRICING_UNITE_ID` (`UNITE_ID`),
  ADD KEY `FK_SERVICEPRICING_SERVICE_ID` (`SERVICE_ID`);

--
-- Indexes for table `serviceville`
--
ALTER TABLE `serviceville`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_SERVICEVILLE_SERVICE_ID` (`SERVICE_ID`),
  ADD KEY `FK_SERVICEVILLE_VILLE_ID` (`VILLE_ID`);

--
-- Indexes for table `supplementdemandeevent`
--
ALTER TABLE `supplementdemandeevent`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_SUPPLEMENTDEMANDEEVENT_DEMANDEEVENT_ID` (`DEMANDEEVENT_ID`),
  ADD KEY `FK_SUPPLEMENTDEMANDEEVENT_SUPPLEMENTEVENT_ID` (`SUPPLEMENTEVENT_ID`);

--
-- Indexes for table `supplementevent`
--
ALTER TABLE `supplementevent`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `timing`
--
ALTER TABLE `timing`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `typeaction`
--
ALTER TABLE `typeaction`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `typedemande`
--
ALTER TABLE `typedemande`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `unite`
--
ALTER TABLE `unite`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `ville`
--
ALTER TABLE `ville`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_VILLE_PAYS_ID` (`PAYS_ID`);

--
-- Indexes for table `voiture`
--
ALTER TABLE `voiture`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_VOITURE_CARBURANT_ID` (`CARBURANT_ID`),
  ADD KEY `FK_VOITURE_MODELE_ID` (`MODELE_ID`),
  ADD KEY `FK_VOITURE_WORKER_EMAIL` (`WORKER_EMAIL`);

--
-- Indexes for table `voiturecarburantcouleur`
--
ALTER TABLE `voiturecarburantcouleur`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_VOITURECARBURANTCOULEUR_COULEUR_ID` (`COULEUR_ID`),
  ADD KEY `FK_VOITURECARBURANTCOULEUR_VOITURE_ID` (`VOITURE_ID`),
  ADD KEY `FK_VOITURECARBURANTCOULEUR_CARBURANT_ID` (`CARBURANT_ID`);

--
-- Indexes for table `voitureimage`
--
ALTER TABLE `voitureimage`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_VOITUREIMAGE_VOITURE_ID` (`VOITURE_ID`);

--
-- Indexes for table `voituremarque`
--
ALTER TABLE `voituremarque`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `voituremodele`
--
ALTER TABLE `voituremodele`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_VOITUREMODELE_MARQUE_ID` (`MARQUE_ID`);

--
-- Indexes for table `voiturepricing`
--
ALTER TABLE `voiturepricing`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_VOITUREPRICING_VOITURE_ID` (`VOITURE_ID`),
  ADD KEY `FK_VOITUREPRICING_UNITE_ID` (`UNITE_ID`);

--
-- Indexes for table `worker`
--
ALTER TABLE `worker`
  ADD PRIMARY KEY (`EMAIL`),
  ADD KEY `FK_WORKER_WORKERTYPE_ID` (`WORKERTYPE_ID`);

--
-- Indexes for table `workerjob`
--
ALTER TABLE `workerjob`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_WORKERJOB_SERVICE_ID` (`SERVICE_ID`),
  ADD KEY `FK_WORKERJOB_SECTEUR_ID` (`SECTEUR_ID`),
  ADD KEY `FK_WORKERJOB_WORKER_EMAIL` (`WORKER_EMAIL`);

--
-- Indexes for table `workertype`
--
ALTER TABLE `workertype`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `eventbudget`
--
ALTER TABLE `eventbudget`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `childdemandebabysitting`
--
ALTER TABLE `childdemandebabysitting`
  ADD CONSTRAINT `FK_CHILDDEMANDEBABYSITTING_DEMANDEBABYSITTING_ID` FOREIGN KEY (`DEMANDEBABYSITTING_ID`) REFERENCES `demandebabysitting` (`ID`);

--
-- Constraints for table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `FK_CLIENT_SECTEUR_ID` FOREIGN KEY (`SECTEUR_ID`) REFERENCES `secteur` (`ID`);

--
-- Constraints for table `cuisinedemandeevent`
--
ALTER TABLE `cuisinedemandeevent`
  ADD CONSTRAINT `FK_CUISINEDEMANDEEVENT_CUISINE_ID` FOREIGN KEY (`CUISINE_ID`) REFERENCES `cuisinetype` (`ID`),
  ADD CONSTRAINT `FK_CUISINEDEMANDEEVENT_DEMANDEEVENT_ID` FOREIGN KEY (`DEMANDEEVENT_ID`) REFERENCES `demandeevent` (`ID`);

--
-- Constraints for table `demandebabysitting`
--
ALTER TABLE `demandebabysitting`
  ADD CONSTRAINT `FK_DEMANDEBABYSITTING_DEMANDESERVICE_ID` FOREIGN KEY (`DEMANDESERVICE_ID`) REFERENCES `demandeservice` (`ID`);

--
-- Constraints for table `demandecleaning`
--
ALTER TABLE `demandecleaning`
  ADD CONSTRAINT `FK_DEMANDECLEANING_DEMANDESERVICE_ID` FOREIGN KEY (`DEMANDESERVICE_ID`) REFERENCES `demandeservice` (`ID`);

--
-- Constraints for table `demandeevent`
--
ALTER TABLE `demandeevent`
  ADD CONSTRAINT `FK_DEMANDEEVENT_DEMANDESERVICE_ID` FOREIGN KEY (`DEMANDESERVICE_ID`) REFERENCES `demandeservice` (`ID`),
  ADD CONSTRAINT `FK_DEMANDEEVENT_EVENTBUDGET_ID` FOREIGN KEY (`EVENTBUDGET_ID`) REFERENCES `eventbudget` (`ID`),
  ADD CONSTRAINT `FK_DEMANDEEVENT_EVENTTYPE_ID` FOREIGN KEY (`EVENTTYPE_ID`) REFERENCES `eventtype` (`ID`);

--
-- Constraints for table `demandeformationpersonnel`
--
ALTER TABLE `demandeformationpersonnel`
  ADD CONSTRAINT `FK_DEMANDEFORMATIONPERSONNEL_DEMANDESERVICE_ID` FOREIGN KEY (`DEMANDESERVICE_ID`) REFERENCES `demandeservice` (`ID`),
  ADD CONSTRAINT `FK_DEMANDEFORMATIONPERSONNEL_FILIERE_ID` FOREIGN KEY (`FILIERE_ID`) REFERENCES `filiere` (`ID`),
  ADD CONSTRAINT `FK_DEMANDEFORMATIONPERSONNEL_MATIERE_ID` FOREIGN KEY (`MATIERE_ID`) REFERENCES `matiere` (`ID`);

--
-- Constraints for table `demandeformationpro`
--
ALTER TABLE `demandeformationpro`
  ADD CONSTRAINT `FK_DEMANDEFORMATIONPRO_DEMANDESERVICE_ID` FOREIGN KEY (`DEMANDESERVICE_ID`) REFERENCES `demandeservice` (`ID`),
  ADD CONSTRAINT `FK_DEMANDEFORMATIONPRO_FORMATEUR_EMAIL` FOREIGN KEY (`FORMATEUR_EMAIL`) REFERENCES `worker` (`EMAIL`);

--
-- Constraints for table `demandegardening`
--
ALTER TABLE `demandegardening`
  ADD CONSTRAINT `FK_DEMANDEGARDENING_DEMANDESERVICE_ID` FOREIGN KEY (`DEMANDESERVICE_ID`) REFERENCES `demandeservice` (`ID`),
  ADD CONSTRAINT `FK_DEMANDEGARDENING_GARDENINGTYPE_ID` FOREIGN KEY (`GARDENINGTYPE_ID`) REFERENCES `gardeningtype` (`ID`),
  ADD CONSTRAINT `FK_DEMANDEGARDENING_HOME_ID` FOREIGN KEY (`HOME_ID`) REFERENCES `home` (`ID`);

--
-- Constraints for table `demandehandyman`
--
ALTER TABLE `demandehandyman`
  ADD CONSTRAINT `FK_DEMANDEHANDYMAN_DEMANDESERVICE_ID` FOREIGN KEY (`DEMANDESERVICE_ID`) REFERENCES `demandeservice` (`ID`),
  ADD CONSTRAINT `FK_DEMANDEHANDYMAN_SERVICE_ID` FOREIGN KEY (`SERVICE_ID`) REFERENCES `service` (`ID`);

--
-- Constraints for table `demandemoving`
--
ALTER TABLE `demandemoving`
  ADD CONSTRAINT `FK_DEMANDEMOVING_DEMANDESERVICE_ID` FOREIGN KEY (`DEMANDESERVICE_ID`) REFERENCES `demandeservice` (`ID`),
  ADD CONSTRAINT `FK_DEMANDEMOVING_VILLEARRIVE_ID` FOREIGN KEY (`VILLEARRIVE_ID`) REFERENCES `ville` (`ID`),
  ADD CONSTRAINT `FK_DEMANDEMOVING_VILLEDEPART_ID` FOREIGN KEY (`VILLEDEPART_ID`) REFERENCES `ville` (`ID`);

--
-- Constraints for table `demandepainting`
--
ALTER TABLE `demandepainting`
  ADD CONSTRAINT `FK_DEMANDEPAINTING_DEMANDESERVICE_ID` FOREIGN KEY (`DEMANDESERVICE_ID`) REFERENCES `demandeservice` (`ID`),
  ADD CONSTRAINT `FK_DEMANDEPAINTING_PAINTING_ID` FOREIGN KEY (`PAINTING_ID`) REFERENCES `paintingtype` (`ID`);

--
-- Constraints for table `demandepestcontrol`
--
ALTER TABLE `demandepestcontrol`
  ADD CONSTRAINT `FK_DEMANDEPESTCONTROL_DEMANDESERVICE_ID` FOREIGN KEY (`DEMANDESERVICE_ID`) REFERENCES `demandeservice` (`ID`),
  ADD CONSTRAINT `FK_DEMANDEPESTCONTROL_TYPEOFPESTCONTROL_ID` FOREIGN KEY (`TYPEOFPESTCONTROL_ID`) REFERENCES `pestcontroltype` (`ID`);

--
-- Constraints for table `demandephotographie`
--
ALTER TABLE `demandephotographie`
  ADD CONSTRAINT `FK_DEMANDEPHOTOGRAPHIE_DEMANDESERVICE_ID` FOREIGN KEY (`DEMANDESERVICE_ID`) REFERENCES `demandeservice` (`ID`),
  ADD CONSTRAINT `FK_DEMANDEPHOTOGRAPHIE_TYPEPHOTOGRAPHIE_ID` FOREIGN KEY (`TYPEPHOTOGRAPHIE_ID`) REFERENCES `photographietype` (`ID`);

--
-- Constraints for table `demandeservice`
--
ALTER TABLE `demandeservice`
  ADD CONSTRAINT `FK_DEMANDESERVICE_CLIENT_EMAIL` FOREIGN KEY (`CLIENT_EMAIL`) REFERENCES `client` (`EMAIL`),
  ADD CONSTRAINT `FK_DEMANDESERVICE_MANAGERCONFIRMATION_LOGIN` FOREIGN KEY (`MANAGERCONFIRMATION_LOGIN`) REFERENCES `manager` (`LOGIN`),
  ADD CONSTRAINT `FK_DEMANDESERVICE_PLANNING_ID` FOREIGN KEY (`PLANNING_ID`) REFERENCES `planning` (`ID`),
  ADD CONSTRAINT `FK_DEMANDESERVICE_SECTEUR_ID` FOREIGN KEY (`SECTEUR_ID`) REFERENCES `secteur` (`ID`),
  ADD CONSTRAINT `FK_DEMANDESERVICE_SERVICEPRICING_ID` FOREIGN KEY (`SERVICEPRICING_ID`) REFERENCES `servicepricing` (`ID`),
  ADD CONSTRAINT `FK_DEMANDESERVICE_SERVICE_ID` FOREIGN KEY (`SERVICE_ID`) REFERENCES `service` (`ID`),
  ADD CONSTRAINT `FK_DEMANDESERVICE_TYPEDEMANDE_ID` FOREIGN KEY (`TYPEDEMANDE_ID`) REFERENCES `typedemande` (`ID`),
  ADD CONSTRAINT `FK_DEMANDESERVICE_WORKERTYPE_ID` FOREIGN KEY (`WORKERTYPE_ID`) REFERENCES `workertype` (`ID`),
  ADD CONSTRAINT `FK_DEMANDESERVICE_WORKER_EMAIL` FOREIGN KEY (`WORKER_EMAIL`) REFERENCES `worker` (`EMAIL`);

--
-- Constraints for table `demandeserviceconfirmationdetail`
--
ALTER TABLE `demandeserviceconfirmationdetail`
  ADD CONSTRAINT `DEMANDESERVICECONFIRMATIONDETAIL_DEMANDESERVICE_ID` FOREIGN KEY (`DEMANDESERVICE_ID`) REFERENCES `demandeservice` (`ID`),
  ADD CONSTRAINT `FK_DEMANDESERVICECONFIRMATIONDETAIL_MANAGER_LOGIN` FOREIGN KEY (`MANAGER_LOGIN`) REFERENCES `manager` (`LOGIN`),
  ADD CONSTRAINT `FK_DEMANDESERVICECONFIRMATIONDETAIL_TYPEACTION_ID` FOREIGN KEY (`TYPEACTION_ID`) REFERENCES `typeaction` (`ID`);

--
-- Constraints for table `demandevoiture`
--
ALTER TABLE `demandevoiture`
  ADD CONSTRAINT `FK_DEMANDEVOITURE_DEMANDESERVICE_ID` FOREIGN KEY (`DEMANDESERVICE_ID`) REFERENCES `demandeservice` (`ID`);

--
-- Constraints for table `demandevoitureitem`
--
ALTER TABLE `demandevoitureitem`
  ADD CONSTRAINT `FK_DEMANDEVOITUREITEM_CARBURANT_ID` FOREIGN KEY (`CARBURANT_ID`) REFERENCES `carburant` (`ID`),
  ADD CONSTRAINT `FK_DEMANDEVOITUREITEM_DEMANDEVOITURE_ID` FOREIGN KEY (`DEMANDEVOITURE_ID`) REFERENCES `demandevoiture` (`ID`),
  ADD CONSTRAINT `FK_DEMANDEVOITUREITEM_MODELE_ID` FOREIGN KEY (`MODELE_ID`) REFERENCES `voituremodele` (`ID`),
  ADD CONSTRAINT `FK_DEMANDEVOITUREITEM_WORKER_EMAIL` FOREIGN KEY (`WORKER_EMAIL`) REFERENCES `worker` (`EMAIL`);

--
-- Constraints for table `device`
--
ALTER TABLE `device`
  ADD CONSTRAINT `FK_DEVICE_MANAGER_LOGIN` FOREIGN KEY (`MANAGER_LOGIN`) REFERENCES `manager` (`LOGIN`),
  ADD CONSTRAINT `FK_DEVICE_WORKER_EMAIL` FOREIGN KEY (`WORKER_EMAIL`) REFERENCES `worker` (`EMAIL`);

--
-- Constraints for table `faq`
--
ALTER TABLE `faq`
  ADD CONSTRAINT `FK_FAQ_SERVICE_ID` FOREIGN KEY (`SERVICE_ID`) REFERENCES `service` (`ID`);

--
-- Constraints for table `filiere`
--
ALTER TABLE `filiere`
  ADD CONSTRAINT `FK_FILIERE_NIVEAUSCOLAIRE_ID` FOREIGN KEY (`NIVEAUSCOLAIRE_ID`) REFERENCES `niveauscolaire` (`ID`);

--
-- Constraints for table `formateurjob`
--
ALTER TABLE `formateurjob`
  ADD CONSTRAINT `FK_FORMATEURJOB_FORMATIONPROSUBTYPE_ID` FOREIGN KEY (`FORMATIONPROSUBTYPE_ID`) REFERENCES `formationprosubtype` (`ID`),
  ADD CONSTRAINT `FK_FORMATEURJOB_WORKER_EMAIL` FOREIGN KEY (`WORKER_EMAIL`) REFERENCES `worker` (`EMAIL`);

--
-- Constraints for table `formationprosubtype`
--
ALTER TABLE `formationprosubtype`
  ADD CONSTRAINT `FK_FORMATIONPROSUBTYPE_FORMATIONPROTYPE_ID` FOREIGN KEY (`FORMATIONPROTYPE_ID`) REFERENCES `formationprotype` (`ID`);

--
-- Constraints for table `home`
--
ALTER TABLE `home`
  ADD CONSTRAINT `FK_HOME_HOMETYPE_ID` FOREIGN KEY (`HOMETYPE_ID`) REFERENCES `hometype` (`ID`);

--
-- Constraints for table `matiere`
--
ALTER TABLE `matiere`
  ADD CONSTRAINT `FK_MATIERE_FILIERE_ID` FOREIGN KEY (`FILIERE_ID`) REFERENCES `filiere` (`ID`);

--
-- Constraints for table `menuformulaire`
--
ALTER TABLE `menuformulaire`
  ADD CONSTRAINT `FK_MENUFORMULAIRE_SERVICE_ID` FOREIGN KEY (`SERVICE_ID`) REFERENCES `service` (`ID`);

--
-- Constraints for table `packaging`
--
ALTER TABLE `packaging`
  ADD CONSTRAINT `FK_PACKAGING_SERVICEPRICING_ID` FOREIGN KEY (`SERVICEPRICING_ID`) REFERENCES `servicepricing` (`ID`),
  ADD CONSTRAINT `FK_PACKAGING_SERVICE_ID` FOREIGN KEY (`SERVICE_ID`) REFERENCES `service` (`ID`);

--
-- Constraints for table `planning`
--
ALTER TABLE `planning`
  ADD CONSTRAINT `FK_PLANNING_TIMING_ID` FOREIGN KEY (`TIMING_ID`) REFERENCES `timing` (`ID`);

--
-- Constraints for table `planningitem`
--
ALTER TABLE `planningitem`
  ADD CONSTRAINT `FK_PLANNINGITEM_DAY_ID` FOREIGN KEY (`DAY_ID`) REFERENCES `day` (`ID`),
  ADD CONSTRAINT `FK_PLANNINGITEM_PLANNING_ID` FOREIGN KEY (`PLANNING_ID`) REFERENCES `planning` (`ID`),
  ADD CONSTRAINT `FK_PLANNINGITEM_TIMING_ID` FOREIGN KEY (`TIMING_ID`) REFERENCES `timing` (`ID`);

--
-- Constraints for table `profjob`
--
ALTER TABLE `profjob`
  ADD CONSTRAINT `FK_PROFJOB_MATIERE_ID` FOREIGN KEY (`MATIERE_ID`) REFERENCES `matiere` (`ID`),
  ADD CONSTRAINT `FK_PROFJOB_WORKER_EMAIL` FOREIGN KEY (`WORKER_EMAIL`) REFERENCES `worker` (`EMAIL`);

--
-- Constraints for table `review`
--
ALTER TABLE `review`
  ADD CONSTRAINT `FK_REVIEW_CLIENT_EMAIL` FOREIGN KEY (`CLIENT_EMAIL`) REFERENCES `client` (`EMAIL`),
  ADD CONSTRAINT `FK_REVIEW_DEMANDESERVICE_ID` FOREIGN KEY (`DEMANDESERVICE_ID`) REFERENCES `demandeservice` (`ID`),
  ADD CONSTRAINT `FK_REVIEW_WORKER_EMAIL` FOREIGN KEY (`WORKER_EMAIL`) REFERENCES `worker` (`EMAIL`);

--
-- Constraints for table `secteur`
--
ALTER TABLE `secteur`
  ADD CONSTRAINT `FK_SECTEUR_VILLE_ID` FOREIGN KEY (`VILLE_ID`) REFERENCES `ville` (`ID`);

--
-- Constraints for table `service`
--
ALTER TABLE `service`
  ADD CONSTRAINT `FK_SERVICE_CATEGORIE_ID` FOREIGN KEY (`CATEGORIE_ID`) REFERENCES `categorie` (`ID`);

--
-- Constraints for table `servicepricing`
--
ALTER TABLE `servicepricing`
  ADD CONSTRAINT `FK_SERVICEPRICING_SERVICE_ID` FOREIGN KEY (`SERVICE_ID`) REFERENCES `service` (`ID`),
  ADD CONSTRAINT `FK_SERVICEPRICING_UNITE_ID` FOREIGN KEY (`UNITE_ID`) REFERENCES `unite` (`ID`);

--
-- Constraints for table `serviceville`
--
ALTER TABLE `serviceville`
  ADD CONSTRAINT `FK_SERVICEVILLE_SERVICE_ID` FOREIGN KEY (`SERVICE_ID`) REFERENCES `service` (`ID`),
  ADD CONSTRAINT `FK_SERVICEVILLE_VILLE_ID` FOREIGN KEY (`VILLE_ID`) REFERENCES `ville` (`ID`);

--
-- Constraints for table `supplementdemandeevent`
--
ALTER TABLE `supplementdemandeevent`
  ADD CONSTRAINT `FK_SUPPLEMENTDEMANDEEVENT_DEMANDEEVENT_ID` FOREIGN KEY (`DEMANDEEVENT_ID`) REFERENCES `demandeevent` (`ID`),
  ADD CONSTRAINT `FK_SUPPLEMENTDEMANDEEVENT_SUPPLEMENTEVENT_ID` FOREIGN KEY (`SUPPLEMENTEVENT_ID`) REFERENCES `supplementevent` (`ID`);

--
-- Constraints for table `ville`
--
ALTER TABLE `ville`
  ADD CONSTRAINT `FK_VILLE_PAYS_ID` FOREIGN KEY (`PAYS_ID`) REFERENCES `pays` (`ID`);

--
-- Constraints for table `voiture`
--
ALTER TABLE `voiture`
  ADD CONSTRAINT `FK_VOITURE_CARBURANT_ID` FOREIGN KEY (`CARBURANT_ID`) REFERENCES `carburant` (`ID`),
  ADD CONSTRAINT `FK_VOITURE_MODELE_ID` FOREIGN KEY (`MODELE_ID`) REFERENCES `voituremodele` (`ID`),
  ADD CONSTRAINT `FK_VOITURE_WORKER_EMAIL` FOREIGN KEY (`WORKER_EMAIL`) REFERENCES `worker` (`EMAIL`);

--
-- Constraints for table `voiturecarburantcouleur`
--
ALTER TABLE `voiturecarburantcouleur`
  ADD CONSTRAINT `FK_VOITURECARBURANTCOULEUR_CARBURANT_ID` FOREIGN KEY (`CARBURANT_ID`) REFERENCES `carburant` (`ID`),
  ADD CONSTRAINT `FK_VOITURECARBURANTCOULEUR_COULEUR_ID` FOREIGN KEY (`COULEUR_ID`) REFERENCES `couleur` (`ID`),
  ADD CONSTRAINT `FK_VOITURECARBURANTCOULEUR_VOITURE_ID` FOREIGN KEY (`VOITURE_ID`) REFERENCES `voiture` (`ID`);

--
-- Constraints for table `voitureimage`
--
ALTER TABLE `voitureimage`
  ADD CONSTRAINT `FK_VOITUREIMAGE_VOITURE_ID` FOREIGN KEY (`VOITURE_ID`) REFERENCES `voiture` (`ID`);

--
-- Constraints for table `voituremodele`
--
ALTER TABLE `voituremodele`
  ADD CONSTRAINT `FK_VOITUREMODELE_MARQUE_ID` FOREIGN KEY (`MARQUE_ID`) REFERENCES `voituremarque` (`ID`);

--
-- Constraints for table `voiturepricing`
--
ALTER TABLE `voiturepricing`
  ADD CONSTRAINT `FK_VOITUREPRICING_UNITE_ID` FOREIGN KEY (`UNITE_ID`) REFERENCES `unite` (`ID`),
  ADD CONSTRAINT `FK_VOITUREPRICING_VOITURE_ID` FOREIGN KEY (`VOITURE_ID`) REFERENCES `voiture` (`ID`);

--
-- Constraints for table `worker`
--
ALTER TABLE `worker`
  ADD CONSTRAINT `FK_WORKER_WORKERTYPE_ID` FOREIGN KEY (`WORKERTYPE_ID`) REFERENCES `workertype` (`ID`);

--
-- Constraints for table `workerjob`
--
ALTER TABLE `workerjob`
  ADD CONSTRAINT `FK_WORKERJOB_SECTEUR_ID` FOREIGN KEY (`SECTEUR_ID`) REFERENCES `secteur` (`ID`),
  ADD CONSTRAINT `FK_WORKERJOB_SERVICE_ID` FOREIGN KEY (`SERVICE_ID`) REFERENCES `service` (`ID`),
  ADD CONSTRAINT `FK_WORKERJOB_WORKER_EMAIL` FOREIGN KEY (`WORKER_EMAIL`) REFERENCES `worker` (`EMAIL`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
