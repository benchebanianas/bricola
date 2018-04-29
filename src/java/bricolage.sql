-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Dim 29 Avril 2018 à 21:59
-- Version du serveur :  10.1.16-MariaDB
-- Version de PHP :  5.6.24

SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `bricolage`
--

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE `categorie` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `categorie`
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
-- Structure de la table `childdemandebabysitting`
--

CREATE TABLE `childdemandebabysitting` (
  `ID` bigint(20) NOT NULL,
  `AGE` int(11) DEFAULT NULL,
  `DEMANDEBABYSITTING_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `client`
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `client`
--

INSERT INTO `client` (`EMAIL`, `ADRESSECOMPLEMENT`, `BLOCKED`, `NOM`, `PASSWORD`, `PHONE`, `PRENOM`, `TOKEN`, `SECTEUR_ID`) VALUES
('anas.the.creator@gmail.com', 'Saada 1 No 570', 0, 'Benchebani', 'walo', '0630247385', 'Mohamed Anas', NULL, 3),
('htakouit@gmail.com', '06', 0, 'Takouit', 'htakouit@gmail.com', '0677352220', 'hamza', NULL, 5),
('newCustomer@gmail.com', 'no 12 hay salama', 0, 'Customer', 'newCustomer@gmail.com', '0679461382', 'new', NULL, 5);

-- --------------------------------------------------------

--
-- Structure de la table `cuisinedemandeevent`
--

CREATE TABLE `cuisinedemandeevent` (
  `ID` bigint(20) NOT NULL,
  `CUISINE_ID` bigint(20) DEFAULT NULL,
  `DEMANDEEVENT_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `cuisinedemandeevent`
--

INSERT INTO `cuisinedemandeevent` (`ID`, `CUISINE_ID`, `DEMANDEEVENT_ID`) VALUES
(1, 2, 1),
(2, 3, 1),
(3, 2, 2),
(4, 3, 2);

-- --------------------------------------------------------

--
-- Structure de la table `cuisinetype`
--

CREATE TABLE `cuisinetype` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `cuisinetype`
--

INSERT INTO `cuisinetype` (`ID`, `NOM`) VALUES
(1, 'Arabe'),
(2, 'Chinoix'),
(3, 'Mexican'),
(4, 'Other');

-- --------------------------------------------------------

--
-- Structure de la table `day`
--

CREATE TABLE `day` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `day`
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
-- Structure de la table `demandebabysitting`
--

CREATE TABLE `demandebabysitting` (
  `ID` bigint(20) NOT NULL,
  `DETAIL` varchar(255) DEFAULT NULL,
  `FULLTIME` tinyint(1) DEFAULT '0',
  `NBRHEURES` decimal(38,0) DEFAULT NULL,
  `DEMANDESERVICE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `demandecleaning`
--

CREATE TABLE `demandecleaning` (
  `ID` bigint(20) NOT NULL,
  `BRINGEQUIPEMENT` tinyint(1) DEFAULT '0',
  `NBRCLEANER` decimal(38,0) DEFAULT NULL,
  `NBRHEURES` decimal(38,0) DEFAULT NULL,
  `DEMANDESERVICE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `demandecleaning`
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
-- Structure de la table `demandeevent`
--

CREATE TABLE `demandeevent` (
  `ID` bigint(20) NOT NULL,
  `NBRINVITES` int(11) DEFAULT NULL,
  `DEMANDESERVICE_ID` bigint(20) DEFAULT NULL,
  `EVENTBUDGET_ID` bigint(20) DEFAULT NULL,
  `EVENTTYPE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `demandeevent`
--

INSERT INTO `demandeevent` (`ID`, `NBRINVITES`, `DEMANDESERVICE_ID`, `EVENTBUDGET_ID`, `EVENTTYPE_ID`) VALUES
(1, 0, 22, 1, 1),
(2, 2, 24, 2, 5);

-- --------------------------------------------------------

--
-- Structure de la table `demandeformationpersonnel`
--

CREATE TABLE `demandeformationpersonnel` (
  `ID` bigint(20) NOT NULL,
  `ADOMICILE` tinyint(1) DEFAULT '0',
  `NBRPERSONNE` int(11) DEFAULT NULL,
  `MATIERE_ID` bigint(20) DEFAULT NULL,
  `DEMANDESERVICE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `demandeformationpersonnel`
--

INSERT INTO `demandeformationpersonnel` (`ID`, `ADOMICILE`, `NBRPERSONNE`, `MATIERE_ID`, `DEMANDESERVICE_ID`) VALUES
(1, 1, 5, NULL, 9);

-- --------------------------------------------------------

--
-- Structure de la table `demandegardening`
--

CREATE TABLE `demandegardening` (
  `ID` bigint(20) NOT NULL,
  `DEMANDESERVICE_ID` bigint(20) DEFAULT NULL,
  `GARDENINGTYPE_ID` bigint(20) DEFAULT NULL,
  `HOME_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `demandehandyman`
--

CREATE TABLE `demandehandyman` (
  `ID` bigint(20) NOT NULL,
  `DEMANDESERVICE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `demandemoving`
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `demandepainting`
--

CREATE TABLE `demandepainting` (
  `ID` bigint(20) NOT NULL,
  `DETAIL` varchar(255) DEFAULT NULL,
  `DEMANDESERVICE_ID` bigint(20) DEFAULT NULL,
  `PAINTING_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `demandepestcontrol`
--

CREATE TABLE `demandepestcontrol` (
  `ID` bigint(20) NOT NULL,
  `DEMANDESERVICE_ID` bigint(20) DEFAULT NULL,
  `TYPEOFPESTCONTROL_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `demandephotographie`
--

CREATE TABLE `demandephotographie` (
  `ID` bigint(20) NOT NULL,
  `VIDEOGRAPHIE` tinyint(1) DEFAULT '0',
  `DEMANDESERVICE_ID` bigint(20) DEFAULT NULL,
  `TYPEPHOTOGRAPHIE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `demandephotographie`
--

INSERT INTO `demandephotographie` (`ID`, `VIDEOGRAPHIE`, `DEMANDESERVICE_ID`, `TYPEPHOTOGRAPHIE_ID`) VALUES
(1, 1, 10, 2);

-- --------------------------------------------------------

--
-- Structure de la table `demandeservice`
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
  `MANAGERCONFIRMATION_ID` varchar(255) DEFAULT NULL,
  `PLANNING_ID` bigint(20) DEFAULT NULL,
  `SECTEUR_ID` bigint(20) DEFAULT NULL,
  `SERVICE_ID` bigint(20) DEFAULT NULL,
  `SERVICEPRICING_ID` bigint(20) DEFAULT NULL,
  `WORKER_EMAIL` varchar(255) DEFAULT NULL,
  `WORKERTYPE_ID` bigint(20) DEFAULT NULL,
  `TYPEDEMANDE_ID` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `demandeservice`
--

INSERT INTO `demandeservice` (`ID`, `DATECONFIRMATION`, `DATEDERNIERMODIF`, `DATESUPPRESSION`, `DATEDEMANDE`, `DETAIL`, `PRIXHT`, `PRIXTTC`, `CLIENT_EMAIL`, `MANAGERCONFIRMATION_ID`, `PLANNING_ID`, `SECTEUR_ID`, `SERVICE_ID`, `SERVICEPRICING_ID`, `WORKER_EMAIL`, `WORKERTYPE_ID`, `TYPEDEMANDE_ID`) VALUES
(1, NULL, NULL, '2018-04-23', '2018-03-23', 'please enter quietly because the kids are still sleeping ', '300', '300', 'anas.the.creator@gmail.com', 'ysn', 1, 2, 1, 1, 'coitcleaners@gmail.com', 1, 'DemandeCleaning'),
(2, NULL, NULL, '2018-04-16', '2018-03-23', 'none', '50', '50', 'anas.the.creator@gmail.com', 'ysn', 2, 2, 1, 1, 'merrymaids@gmail.com', 2, 'DemandeCleaning'),
(3, NULL, NULL, NULL, '2018-03-23', 'i left food in the kitchen if you would like to eat', '400', '400', 'anas.the.creator@gmail.com', NULL, 3, 2, 1, 1, 'cleanharbors@gmail.com', 2, 'DemandeCleaning'),
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
(24, NULL, NULL, NULL, '2018-04-29', 'My birthday', '150', '150', 'htakouit@gmail.com', NULL, 23, 5, 17, 7, 'MultiServices@gmail.com', 2, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `demandeserviceconfirmationdetail`
--

CREATE TABLE `demandeserviceconfirmationdetail` (
  `ID` bigint(20) NOT NULL,
  `DATEACTION` date DEFAULT NULL,
  `DEMANDESERVICE_ID` bigint(20) DEFAULT NULL,
  `MANAGER_ID` varchar(255) DEFAULT NULL,
  `TYPEACTION_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `demandeserviceconfirmationdetail`
--

INSERT INTO `demandeserviceconfirmationdetail` (`ID`, `DATEACTION`, `DEMANDESERVICE_ID`, `MANAGER_ID`, `TYPEACTION_ID`) VALUES
(1, '2018-04-16', 1, 'ysn', NULL),
(2, '2018-04-16', 2, 'ysn', NULL),
(3, '2018-04-16', 4, 'ysn', NULL),
(4, '2018-04-23', 1, 'ysn', 1),
(5, '2018-04-23', 1, 'ysn', 2);

-- --------------------------------------------------------

--
-- Structure de la table `demandevoiture`
--

CREATE TABLE `demandevoiture` (
  `ID` bigint(20) NOT NULL,
  `DEMANDESERVICE_ID` bigint(20) DEFAULT NULL,
  `VOITURE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `device`
--

CREATE TABLE `device` (
  `ID` bigint(20) NOT NULL,
  `BLOCKED` tinyint(1) DEFAULT '0',
  `DATECONNECTION` date DEFAULT NULL,
  `DEVICECATEGORIE` varchar(255) DEFAULT NULL,
  `IP` varchar(255) DEFAULT NULL,
  `NAVIGATEUR` varchar(255) DEFAULT NULL,
  `OS` varchar(255) DEFAULT NULL,
  `MANAGER_ID` varchar(255) DEFAULT NULL,
  `WORKER_EMAIL` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `device`
--

INSERT INTO `device` (`ID`, `BLOCKED`, `DATECONNECTION`, `DEVICECATEGORIE`, `IP`, `NAVIGATEUR`, `OS`, `MANAGER_ID`, `WORKER_EMAIL`) VALUES
(1, 0, '2018-04-10', 'Personal computer', '172.31.20.65', 'Firefox', 'Windows', 'manager', NULL),
(2, 0, '2018-04-23', 'Personal computer', '192.168.67.2', 'Firefox', 'Windows 8.1', 'ysn', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `eventbudget`
--

CREATE TABLE `eventbudget` (
  `ID` bigint(20) NOT NULL,
  `BUDGET` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `eventbudget`
--

INSERT INTO `eventbudget` (`ID`, `BUDGET`) VALUES
(1, '100dh par personne'),
(2, '150 dh par personne');

-- --------------------------------------------------------

--
-- Structure de la table `eventtype`
--

CREATE TABLE `eventtype` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `eventtype`
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
-- Structure de la table `faq`
--

CREATE TABLE `faq` (
  `ID` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `filiere`
--

CREATE TABLE `filiere` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `NIVEAUSCOLAIRE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `filiere`
--

INSERT INTO `filiere` (`ID`, `NOM`, `NIVEAUSCOLAIRE_ID`) VALUES
(1, 'mipc', 1),
(2, 'mip', 1),
(3, 'bcg', 1),
(4, 'mip', 2),
(5, 'mipc', 2),
(6, 'bcg', 2),
(7, 'sir', 3),
(8, 'pcm', 3),
(9, 'ieea', 3),
(10, 'pm', 3);

-- --------------------------------------------------------

--
-- Structure de la table `formateurjob`
--

CREATE TABLE `formateurjob` (
  `ID` bigint(20) NOT NULL,
  `FPS_ID` bigint(20) DEFAULT NULL,
  `WORKER_EMAIL` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `formationprodemande`
--

CREATE TABLE `formationprodemande` (
  `ID` bigint(20) NOT NULL,
  `NBRHEURES` int(11) DEFAULT NULL,
  `NBRPERSONNE` int(11) DEFAULT NULL,
  `DEMANDESERVICE_ID` bigint(20) DEFAULT NULL,
  `FORMATEUR_EMAIL` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `fprosubtype`
--

CREATE TABLE `fprosubtype` (
  `ID` bigint(20) NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `FP_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `fprotype`
--

CREATE TABLE `fprotype` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `gardeningtype`
--

CREATE TABLE `gardeningtype` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `home`
--

CREATE TABLE `home` (
  `ID` bigint(20) NOT NULL,
  `NOMBREPIECES` int(11) DEFAULT NULL,
  `HOMETYPE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `hometype`
--

CREATE TABLE `hometype` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `manager`
--

CREATE TABLE `manager` (
  `ID` varchar(255) NOT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `BLOCKED` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `manager`
--

INSERT INTO `manager` (`ID`, `PASSWORD`, `BLOCKED`) VALUES
('manager', 'manager', 0),
('ysn', '123', 0);

-- --------------------------------------------------------

--
-- Structure de la table `matiere`
--

CREATE TABLE `matiere` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `FILIERE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `matiere`
--

INSERT INTO `matiere` (`ID`, `NOM`, `FILIERE_ID`) VALUES
(1, 'base de donnee', 7),
(2, 'java', 7),
(3, 'uml', 7),
(4, 'reseaux', 7),
(5, 'analyse 1', 1),
(6, 'algebre 1', 1),
(7, 'mecanique du point', 1);

-- --------------------------------------------------------

--
-- Structure de la table `menuformulaire`
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `menuformulaire`
--

INSERT INTO `menuformulaire` (`ID`, `COMPANYTAB`, `DETAILSTAB`, `IMAGENAME`, `INFOTAB`, `PAYEMENTTAB`, `SUMMARYTAB`, `SERVICE_ID`) VALUES
(1, 1, 1, 'nettoyageMaison', 1, 1, 1, 1),
(2, 1, 1, 'photographie', 1, 1, 1, 19),
(3, 1, 1, 'LocationVoiture', 1, 1, 1, 21),
(4, 1, 1, 'formationpersonnel', 1, 1, 1, 22),
(5, 1, 1, 'nan', 1, 1, 1, 17),
(6, 1, 1, 'deratisation', 0, 1, 1, 14),
(7, 1, 1, 'demenagement', 1, 1, 1, 5);

-- --------------------------------------------------------

--
-- Structure de la table `niveauscolaire`
--

CREATE TABLE `niveauscolaire` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `niveauscolaire`
--

INSERT INTO `niveauscolaire` (`ID`, `NOM`) VALUES
(1, '1 ere annee College'),
(2, '2 eme annee College'),
(3, '3 eme annee College'),
(4, '1 ere annee Lycee'),
(5, '1 ere Bac'),
(6, '2 eme Bac'),
(7, '1 ere annee Universitaire'),
(8, '2 ere annee Universitaire'),
(9, 'Licence'),
(10, '1 ere annee Master'),
(11, '2 eme annee Master');

-- --------------------------------------------------------

--
-- Structure de la table `packaging`
--

CREATE TABLE `packaging` (
  `ID` bigint(20) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `SERVICE_ID` bigint(20) DEFAULT NULL,
  `SERVICEPRICING_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `packaging`
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
-- Structure de la table `paintingtype`
--

CREATE TABLE `paintingtype` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `pays`
--

CREATE TABLE `pays` (
  `ID` bigint(20) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `TVA` decimal(38,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `pays`
--

INSERT INTO `pays` (`ID`, `NAME`, `TVA`) VALUES
(1, 'Morocco', '20'),
(2, 'Spain', '30');

-- --------------------------------------------------------

--
-- Structure de la table `pestcontroltype`
--

CREATE TABLE `pestcontroltype` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `pestcontroltype`
--

INSERT INTO `pestcontroltype` (`ID`, `NOM`) VALUES
(1, 'cockroaches'),
(2, 'bed bugs'),
(3, 'ants'),
(4, 'general');

-- --------------------------------------------------------

--
-- Structure de la table `photographietype`
--

CREATE TABLE `photographietype` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `photographietype`
--

INSERT INTO `photographietype` (`ID`, `NOM`) VALUES
(1, 'Marriage'),
(2, 'Shooting');

-- --------------------------------------------------------

--
-- Structure de la table `planning`
--

CREATE TABLE `planning` (
  `ID` bigint(20) NOT NULL,
  `DATEDEBUT` date DEFAULT NULL,
  `DATEFIN` date DEFAULT NULL,
  `DATEONCE` date DEFAULT NULL,
  `TIMING_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `planning`
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
(23, NULL, NULL, '2018-05-02', 2);

-- --------------------------------------------------------

--
-- Structure de la table `planningitem`
--

CREATE TABLE `planningitem` (
  `ID` bigint(20) NOT NULL,
  `NUMEROJOUR` int(11) DEFAULT NULL,
  `REPETITION` int(11) DEFAULT NULL,
  `DAY_ID` bigint(20) DEFAULT NULL,
  `PLANNING_ID` bigint(20) DEFAULT NULL,
  `TIMING_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `planningitem`
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
-- Structure de la table `profjob`
--

CREATE TABLE `profjob` (
  `ID` bigint(20) NOT NULL,
  `MATIERE_ID` bigint(20) DEFAULT NULL,
  `WORKER_EMAIL` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `review`
--

CREATE TABLE `review` (
  `ID` bigint(20) NOT NULL,
  `DATEREVIEW` date DEFAULT NULL,
  `STARS` int(11) DEFAULT NULL,
  `TEXT` varchar(255) DEFAULT NULL,
  `WORKER_EMAIL` varchar(255) DEFAULT NULL,
  `CLIENT_EMAIL` varchar(255) DEFAULT NULL,
  `SERVICE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `secteur`
--

CREATE TABLE `secteur` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `VILLE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `secteur`
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
-- Structure de la table `sequence`
--

CREATE TABLE `sequence` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `sequence`
--

INSERT INTO `sequence` (`SEQ_NAME`, `SEQ_COUNT`) VALUES
('SEQ_GEN', '0');

-- --------------------------------------------------------

--
-- Structure de la table `service`
--

CREATE TABLE `service` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `CATEGORIE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `service`
--

INSERT INTO `service` (`ID`, `NOM`, `CATEGORIE_ID`) VALUES
(1, 'nettoyageMaison', 1),
(2, 'Nettoyage Complet', 1),
(3, 'Nettoyage Piscine', 1),
(4, 'Nettoyage Divers', 1),
(5, 'demenagement', 2),
(6, 'Demenagement international', 2),
(7, 'Stockage', 2),
(8, 'Peinture', 3),
(9, 'Handyman', 3),
(10, 'Electricite', 3),
(11, 'Plomberie', 3),
(12, 'Menuiserie', 3),
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
-- Structure de la table `servicepricing`
--

CREATE TABLE `servicepricing` (
  `ID` bigint(20) NOT NULL,
  `DATEAPPLICATION` date DEFAULT NULL,
  `PRIX` decimal(38,0) DEFAULT NULL,
  `SERVICE_ID` bigint(20) DEFAULT NULL,
  `UNITE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `servicepricing`
--

INSERT INTO `servicepricing` (`ID`, `DATEAPPLICATION`, `PRIX`, `SERVICE_ID`, `UNITE_ID`) VALUES
(1, '2015-03-03', '50', 1, 1),
(2, '2018-03-01', '179', 14, 2),
(3, '2018-03-01', '300', 14, 3),
(4, '2018-03-01', '200', 14, 5),
(5, '2018-03-01', '370', 14, 4),
(6, '2018-04-29', '500', 19, 6),
(7, '2018-04-29', '150', 17, 7);

-- --------------------------------------------------------

--
-- Structure de la table `serviceville`
--

CREATE TABLE `serviceville` (
  `ID` bigint(20) NOT NULL,
  `SERVICE_ID` bigint(20) DEFAULT NULL,
  `VILLE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `serviceville`
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
-- Structure de la table `supplementdemandeevent`
--

CREATE TABLE `supplementdemandeevent` (
  `ID` bigint(20) NOT NULL,
  `DEMANDEEVENT_ID` bigint(20) DEFAULT NULL,
  `SUPPLEMENTEVENT_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `supplementdemandeevent`
--

INSERT INTO `supplementdemandeevent` (`ID`, `DEMANDEEVENT_ID`, `SUPPLEMENTEVENT_ID`) VALUES
(1, 1, 2),
(2, 2, 1),
(3, 2, 2);

-- --------------------------------------------------------

--
-- Structure de la table `supplementevent`
--

CREATE TABLE `supplementevent` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `supplementevent`
--

INSERT INTO `supplementevent` (`ID`, `NOM`) VALUES
(1, 'Serveurs'),
(2, 'Tables et fournitures'),
(3, 'Decoration');

-- --------------------------------------------------------

--
-- Structure de la table `timing`
--

CREATE TABLE `timing` (
  `ID` bigint(20) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `VALEUR` decimal(38,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `timing`
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
-- Structure de la table `typeaction`
--

CREATE TABLE `typeaction` (
  `ID` bigint(20) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `typeaction`
--

INSERT INTO `typeaction` (`ID`, `NAME`) VALUES
(1, 'Confirmation'),
(2, 'Suppression');

-- --------------------------------------------------------

--
-- Structure de la table `typedemande`
--

CREATE TABLE `typedemande` (
  `ID` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `typedemande`
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
-- Structure de la table `unite`
--

CREATE TABLE `unite` (
  `ID` bigint(20) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `unite`
--

INSERT INTO `unite` (`ID`, `NAME`) VALUES
(1, 'CleanerPerHour'),
(2, 'Studio'),
(3, '1 BR'),
(4, '2 BR'),
(5, '3 BR'),
(6, 'PhotographiePerHour'),
(7, 'EventPerHour');

-- --------------------------------------------------------

--
-- Structure de la table `ville`
--

CREATE TABLE `ville` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `PAYS_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `ville`
--

INSERT INTO `ville` (`ID`, `NOM`, `PAYS_ID`) VALUES
(1, 'Marrakesh', 1),
(2, 'Casablanca', 1),
(3, 'Madrid', 2),
(4, 'Barcelona', 2);

-- --------------------------------------------------------

--
-- Structure de la table `voiture`
--

CREATE TABLE `voiture` (
  `ID` bigint(20) NOT NULL,
  `CARBURANT` varchar(255) DEFAULT NULL,
  `COULEUR` varchar(255) DEFAULT NULL,
  `KILOMETRAGE` varchar(255) DEFAULT NULL,
  `MATRICULE` varchar(255) DEFAULT NULL,
  `WORKER_EMAIL` varchar(255) DEFAULT NULL,
  `MODELE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `voitureimage`
--

CREATE TABLE `voitureimage` (
  `ID` bigint(20) NOT NULL,
  `IMAGE` varchar(255) DEFAULT NULL,
  `VOITURE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `voituremarque`
--

CREATE TABLE `voituremarque` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `voituremodele`
--

CREATE TABLE `voituremodele` (
  `ID` bigint(20) NOT NULL,
  `ANNEE` varchar(255) DEFAULT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `MARQUE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `worker`
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `worker`
--

INSERT INTO `worker` (`EMAIL`, `ACCEPTED`, `BLOCKED`, `DESCRIPTION`, `NOM`, `NOMBREEMPLOYE`, `PASSWORD`, `PHONE`, `SITEWEB`, `WORKERTYPE_ID`) VALUES
('cleanharbors@gmail.com', 1, 0, 'you probably heard of us, the leading company for cleaning services in over 26 countries', 'Clean Harbors', 32, 'cleanharbors@gmail.com', '0679120435', 'www.cleanharbors.com', 2),
('coitcleaners@gmail.com', 0, 0, 'An inspiring individual with many years of experiences in cleaning', 'Coit Cleaners ', 5, 'coitcleaners@gmail.com', '0613467982', 'www.coitcleaners.com', 1),
('merrymaids@gmail.com', 0, 0, 'we can guarantee that our services are the best in the cleaning industry', 'Merry Maids', 54, 'merrymaids@gmail.com', '0687125903', 'www.merrymaids.com', 2),
('MultiServices@gmail.com', 1, 0, 'We do everything for the sake of testing ', 'Multi Services', 100, 'MultiServices@gmail.com', '0677352220', 'MultiServices.com', 2),
('taskrabbit@gmail.com', 1, 0, 'we hire specialist from all around the globe so that you can have the best services', 'TaskRabbit ', 78, 'taskrabbit@gmail.com', '0612064367', 'www.taskrabbit.com', 2);

-- --------------------------------------------------------

--
-- Structure de la table `workerjob`
--

CREATE TABLE `workerjob` (
  `ID` bigint(20) NOT NULL,
  `SECTEUR_ID` bigint(20) DEFAULT NULL,
  `SERVICE_ID` bigint(20) DEFAULT NULL,
  `WORKER_EMAIL` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `workerjob`
--

INSERT INTO `workerjob` (`ID`, `SECTEUR_ID`, `SERVICE_ID`, `WORKER_EMAIL`) VALUES
(1, 1, 1, 'cleanharbors@gmail.com'),
(2, 2, 1, 'merrymaids@gmail.com'),
(3, 4, 4, 'taskrabbit@gmail.com'),
(4, 5, 1, 'coitcleaners@gmail.com'),
(5, 4, 19, 'MultiServices@gmail.com'),
(6, 1, 17, 'MultiServices@gmail.com');

-- --------------------------------------------------------

--
-- Structure de la table `workertype`
--

CREATE TABLE `workertype` (
  `ID` bigint(20) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `workertype`
--

INSERT INTO `workertype` (`ID`, `NAME`) VALUES
(1, 'Personne Physique'),
(2, 'Personne Morale');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `childdemandebabysitting`
--
ALTER TABLE `childdemandebabysitting`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_CHILDDEMANDEBABYSITTING_DEMANDEBABYSITTING_ID` (`DEMANDEBABYSITTING_ID`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`EMAIL`),
  ADD KEY `FK_CLIENT_SECTEUR_ID` (`SECTEUR_ID`);

--
-- Index pour la table `cuisinedemandeevent`
--
ALTER TABLE `cuisinedemandeevent`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_CUISINEDEMANDEEVENT_DEMANDEEVENT_ID` (`DEMANDEEVENT_ID`),
  ADD KEY `FK_CUISINEDEMANDEEVENT_CUISINE_ID` (`CUISINE_ID`);

--
-- Index pour la table `cuisinetype`
--
ALTER TABLE `cuisinetype`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `day`
--
ALTER TABLE `day`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `demandebabysitting`
--
ALTER TABLE `demandebabysitting`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_DEMANDEBABYSITTING_DEMANDESERVICE_ID` (`DEMANDESERVICE_ID`);

--
-- Index pour la table `demandecleaning`
--
ALTER TABLE `demandecleaning`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_DEMANDECLEANING_DEMANDESERVICE_ID` (`DEMANDESERVICE_ID`);

--
-- Index pour la table `demandeevent`
--
ALTER TABLE `demandeevent`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_DEMANDEEVENT_DEMANDESERVICE_ID` (`DEMANDESERVICE_ID`),
  ADD KEY `FK_DEMANDEEVENT_EVENTBUDGET_ID` (`EVENTBUDGET_ID`),
  ADD KEY `FK_DEMANDEEVENT_EVENTTYPE_ID` (`EVENTTYPE_ID`);

--
-- Index pour la table `demandeformationpersonnel`
--
ALTER TABLE `demandeformationpersonnel`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_DEMANDEFORMATIONPERSONNEL_DEMANDESERVICE_ID` (`DEMANDESERVICE_ID`),
  ADD KEY `FK_DEMANDEFORMATIONPERSONNEL_MATIERE_ID` (`MATIERE_ID`);

--
-- Index pour la table `demandegardening`
--
ALTER TABLE `demandegardening`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_DEMANDEGARDENING_GARDENINGTYPE_ID` (`GARDENINGTYPE_ID`),
  ADD KEY `FK_DEMANDEGARDENING_DEMANDESERVICE_ID` (`DEMANDESERVICE_ID`),
  ADD KEY `FK_DEMANDEGARDENING_HOME_ID` (`HOME_ID`);

--
-- Index pour la table `demandehandyman`
--
ALTER TABLE `demandehandyman`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_DEMANDEHANDYMAN_DEMANDESERVICE_ID` (`DEMANDESERVICE_ID`);

--
-- Index pour la table `demandemoving`
--
ALTER TABLE `demandemoving`
  ADD PRIMARY KEY (`DEMANDESERVICE_ID`),
  ADD KEY `FK_DEMANDEMOVING_VILLEDEPART_ID` (`VILLEDEPART_ID`),
  ADD KEY `FK_DEMANDEMOVING_VILLEARRIVE_ID` (`VILLEARRIVE_ID`);

--
-- Index pour la table `demandepainting`
--
ALTER TABLE `demandepainting`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_DEMANDEPAINTING_PAINTING_ID` (`PAINTING_ID`),
  ADD KEY `FK_DEMANDEPAINTING_DEMANDESERVICE_ID` (`DEMANDESERVICE_ID`);

--
-- Index pour la table `demandepestcontrol`
--
ALTER TABLE `demandepestcontrol`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_DEMANDEPESTCONTROL_DEMANDESERVICE_ID` (`DEMANDESERVICE_ID`),
  ADD KEY `FK_DEMANDEPESTCONTROL_TYPEOFPESTCONTROL_ID` (`TYPEOFPESTCONTROL_ID`);

--
-- Index pour la table `demandephotographie`
--
ALTER TABLE `demandephotographie`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_DEMANDEPHOTOGRAPHIE_TYPEPHOTOGRAPHIE_ID` (`TYPEPHOTOGRAPHIE_ID`),
  ADD KEY `FK_DEMANDEPHOTOGRAPHIE_DEMANDESERVICE_ID` (`DEMANDESERVICE_ID`);

--
-- Index pour la table `demandeservice`
--
ALTER TABLE `demandeservice`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_DEMANDESERVICE_TYPEDEMANDE_ID` (`TYPEDEMANDE_ID`),
  ADD KEY `FK_DEMANDESERVICE_MANAGERCONFIRMATION_ID` (`MANAGERCONFIRMATION_ID`),
  ADD KEY `FK_DEMANDESERVICE_PLANNING_ID` (`PLANNING_ID`),
  ADD KEY `FK_DEMANDESERVICE_SERVICE_ID` (`SERVICE_ID`),
  ADD KEY `FK_DEMANDESERVICE_WORKER_EMAIL` (`WORKER_EMAIL`),
  ADD KEY `FK_DEMANDESERVICE_CLIENT_EMAIL` (`CLIENT_EMAIL`),
  ADD KEY `FK_DEMANDESERVICE_SECTEUR_ID` (`SECTEUR_ID`),
  ADD KEY `FK_DEMANDESERVICE_WORKERTYPE_ID` (`WORKERTYPE_ID`),
  ADD KEY `FK_DEMANDESERVICE_SERVICEPRICING_ID` (`SERVICEPRICING_ID`);

--
-- Index pour la table `demandeserviceconfirmationdetail`
--
ALTER TABLE `demandeserviceconfirmationdetail`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_DEMANDESERVICECONFIRMATIONDETAIL_TYPEACTION_ID` (`TYPEACTION_ID`),
  ADD KEY `DEMANDESERVICECONFIRMATIONDETAIL_DEMANDESERVICE_ID` (`DEMANDESERVICE_ID`),
  ADD KEY `FK_DEMANDESERVICECONFIRMATIONDETAIL_MANAGER_ID` (`MANAGER_ID`);

--
-- Index pour la table `demandevoiture`
--
ALTER TABLE `demandevoiture`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_DEMANDEVOITURE_VOITURE_ID` (`VOITURE_ID`),
  ADD KEY `FK_DEMANDEVOITURE_DEMANDESERVICE_ID` (`DEMANDESERVICE_ID`);

--
-- Index pour la table `device`
--
ALTER TABLE `device`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_DEVICE_MANAGER_ID` (`MANAGER_ID`),
  ADD KEY `FK_DEVICE_WORKER_EMAIL` (`WORKER_EMAIL`);

--
-- Index pour la table `eventbudget`
--
ALTER TABLE `eventbudget`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `eventtype`
--
ALTER TABLE `eventtype`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `faq`
--
ALTER TABLE `faq`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `filiere`
--
ALTER TABLE `filiere`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_FILIERE_NIVEAUSCOLAIRE_ID` (`NIVEAUSCOLAIRE_ID`);

--
-- Index pour la table `formateurjob`
--
ALTER TABLE `formateurjob`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_FORMATEURJOB_WORKER_EMAIL` (`WORKER_EMAIL`),
  ADD KEY `FK_FORMATEURJOB_FPS_ID` (`FPS_ID`);

--
-- Index pour la table `formationprodemande`
--
ALTER TABLE `formationprodemande`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_FORMATIONPRODEMANDE_FORMATEUR_EMAIL` (`FORMATEUR_EMAIL`),
  ADD KEY `FK_FORMATIONPRODEMANDE_DEMANDESERVICE_ID` (`DEMANDESERVICE_ID`);

--
-- Index pour la table `fprosubtype`
--
ALTER TABLE `fprosubtype`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_FPROSUBTYPE_FP_ID` (`FP_ID`);

--
-- Index pour la table `fprotype`
--
ALTER TABLE `fprotype`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `gardeningtype`
--
ALTER TABLE `gardeningtype`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `home`
--
ALTER TABLE `home`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_HOME_HOMETYPE_ID` (`HOMETYPE_ID`);

--
-- Index pour la table `hometype`
--
ALTER TABLE `hometype`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `manager`
--
ALTER TABLE `manager`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `matiere`
--
ALTER TABLE `matiere`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_MATIERE_FILIERE_ID` (`FILIERE_ID`);

--
-- Index pour la table `menuformulaire`
--
ALTER TABLE `menuformulaire`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_MENUFORMULAIRE_SERVICE_ID` (`SERVICE_ID`);

--
-- Index pour la table `niveauscolaire`
--
ALTER TABLE `niveauscolaire`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `packaging`
--
ALTER TABLE `packaging`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_PACKAGING_SERVICE_ID` (`SERVICE_ID`),
  ADD KEY `FK_PACKAGING_SERVICEPRICING_ID` (`SERVICEPRICING_ID`);

--
-- Index pour la table `paintingtype`
--
ALTER TABLE `paintingtype`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `pays`
--
ALTER TABLE `pays`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `pestcontroltype`
--
ALTER TABLE `pestcontroltype`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `photographietype`
--
ALTER TABLE `photographietype`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `planning`
--
ALTER TABLE `planning`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_PLANNING_TIMING_ID` (`TIMING_ID`);

--
-- Index pour la table `planningitem`
--
ALTER TABLE `planningitem`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_PLANNINGITEM_TIMING_ID` (`TIMING_ID`),
  ADD KEY `FK_PLANNINGITEM_PLANNING_ID` (`PLANNING_ID`),
  ADD KEY `FK_PLANNINGITEM_DAY_ID` (`DAY_ID`);

--
-- Index pour la table `profjob`
--
ALTER TABLE `profjob`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_PROFJOB_MATIERE_ID` (`MATIERE_ID`),
  ADD KEY `FK_PROFJOB_WORKER_EMAIL` (`WORKER_EMAIL`);

--
-- Index pour la table `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_REVIEW_SERVICE_ID` (`SERVICE_ID`),
  ADD KEY `FK_REVIEW_WORKER_EMAIL` (`WORKER_EMAIL`),
  ADD KEY `FK_REVIEW_CLIENT_EMAIL` (`CLIENT_EMAIL`);

--
-- Index pour la table `secteur`
--
ALTER TABLE `secteur`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_SECTEUR_VILLE_ID` (`VILLE_ID`);

--
-- Index pour la table `sequence`
--
ALTER TABLE `sequence`
  ADD PRIMARY KEY (`SEQ_NAME`);

--
-- Index pour la table `service`
--
ALTER TABLE `service`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_SERVICE_CATEGORIE_ID` (`CATEGORIE_ID`);

--
-- Index pour la table `servicepricing`
--
ALTER TABLE `servicepricing`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_SERVICEPRICING_UNITE_ID` (`UNITE_ID`),
  ADD KEY `FK_SERVICEPRICING_SERVICE_ID` (`SERVICE_ID`);

--
-- Index pour la table `serviceville`
--
ALTER TABLE `serviceville`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_SERVICEVILLE_SERVICE_ID` (`SERVICE_ID`),
  ADD KEY `FK_SERVICEVILLE_VILLE_ID` (`VILLE_ID`);

--
-- Index pour la table `supplementdemandeevent`
--
ALTER TABLE `supplementdemandeevent`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_SUPPLEMENTDEMANDEEVENT_DEMANDEEVENT_ID` (`DEMANDEEVENT_ID`),
  ADD KEY `FK_SUPPLEMENTDEMANDEEVENT_SUPPLEMENTEVENT_ID` (`SUPPLEMENTEVENT_ID`);

--
-- Index pour la table `supplementevent`
--
ALTER TABLE `supplementevent`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `timing`
--
ALTER TABLE `timing`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `typeaction`
--
ALTER TABLE `typeaction`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `typedemande`
--
ALTER TABLE `typedemande`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `unite`
--
ALTER TABLE `unite`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `ville`
--
ALTER TABLE `ville`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_VILLE_PAYS_ID` (`PAYS_ID`);

--
-- Index pour la table `voiture`
--
ALTER TABLE `voiture`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_VOITURE_MODELE_ID` (`MODELE_ID`),
  ADD KEY `FK_VOITURE_WORKER_EMAIL` (`WORKER_EMAIL`);

--
-- Index pour la table `voitureimage`
--
ALTER TABLE `voitureimage`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_VOITUREIMAGE_VOITURE_ID` (`VOITURE_ID`);

--
-- Index pour la table `voituremarque`
--
ALTER TABLE `voituremarque`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `voituremodele`
--
ALTER TABLE `voituremodele`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_VOITUREMODELE_MARQUE_ID` (`MARQUE_ID`);

--
-- Index pour la table `worker`
--
ALTER TABLE `worker`
  ADD PRIMARY KEY (`EMAIL`),
  ADD KEY `FK_WORKER_WORKERTYPE_ID` (`WORKERTYPE_ID`);

--
-- Index pour la table `workerjob`
--
ALTER TABLE `workerjob`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_WORKERJOB_SERVICE_ID` (`SERVICE_ID`),
  ADD KEY `FK_WORKERJOB_SECTEUR_ID` (`SECTEUR_ID`),
  ADD KEY `FK_WORKERJOB_WORKER_EMAIL` (`WORKER_EMAIL`);

--
-- Index pour la table `workertype`
--
ALTER TABLE `workertype`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `eventbudget`
--
ALTER TABLE `eventbudget`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `childdemandebabysitting`
--
ALTER TABLE `childdemandebabysitting`
  ADD CONSTRAINT `FK_CHILDDEMANDEBABYSITTING_DEMANDEBABYSITTING_ID` FOREIGN KEY (`DEMANDEBABYSITTING_ID`) REFERENCES `demandebabysitting` (`ID`);

--
-- Contraintes pour la table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `FK_CLIENT_SECTEUR_ID` FOREIGN KEY (`SECTEUR_ID`) REFERENCES `secteur` (`ID`);

--
-- Contraintes pour la table `cuisinedemandeevent`
--
ALTER TABLE `cuisinedemandeevent`
  ADD CONSTRAINT `FK_CUISINEDEMANDEEVENT_CUISINE_ID` FOREIGN KEY (`CUISINE_ID`) REFERENCES `cuisinetype` (`ID`),
  ADD CONSTRAINT `FK_CUISINEDEMANDEEVENT_DEMANDEEVENT_ID` FOREIGN KEY (`DEMANDEEVENT_ID`) REFERENCES `demandeevent` (`ID`);

--
-- Contraintes pour la table `demandebabysitting`
--
ALTER TABLE `demandebabysitting`
  ADD CONSTRAINT `FK_DEMANDEBABYSITTING_DEMANDESERVICE_ID` FOREIGN KEY (`DEMANDESERVICE_ID`) REFERENCES `demandeservice` (`ID`);

--
-- Contraintes pour la table `demandecleaning`
--
ALTER TABLE `demandecleaning`
  ADD CONSTRAINT `FK_DEMANDECLEANING_DEMANDESERVICE_ID` FOREIGN KEY (`DEMANDESERVICE_ID`) REFERENCES `demandeservice` (`ID`);

--
-- Contraintes pour la table `demandeevent`
--
ALTER TABLE `demandeevent`
  ADD CONSTRAINT `FK_DEMANDEEVENT_DEMANDESERVICE_ID` FOREIGN KEY (`DEMANDESERVICE_ID`) REFERENCES `demandeservice` (`ID`),
  ADD CONSTRAINT `FK_DEMANDEEVENT_EVENTBUDGET_ID` FOREIGN KEY (`EVENTBUDGET_ID`) REFERENCES `eventbudget` (`ID`),
  ADD CONSTRAINT `FK_DEMANDEEVENT_EVENTTYPE_ID` FOREIGN KEY (`EVENTTYPE_ID`) REFERENCES `eventtype` (`ID`);

--
-- Contraintes pour la table `demandeformationpersonnel`
--
ALTER TABLE `demandeformationpersonnel`
  ADD CONSTRAINT `FK_DEMANDEFORMATIONPERSONNEL_DEMANDESERVICE_ID` FOREIGN KEY (`DEMANDESERVICE_ID`) REFERENCES `demandeservice` (`ID`),
  ADD CONSTRAINT `FK_DEMANDEFORMATIONPERSONNEL_MATIERE_ID` FOREIGN KEY (`MATIERE_ID`) REFERENCES `matiere` (`ID`);

--
-- Contraintes pour la table `demandegardening`
--
ALTER TABLE `demandegardening`
  ADD CONSTRAINT `FK_DEMANDEGARDENING_DEMANDESERVICE_ID` FOREIGN KEY (`DEMANDESERVICE_ID`) REFERENCES `demandeservice` (`ID`),
  ADD CONSTRAINT `FK_DEMANDEGARDENING_GARDENINGTYPE_ID` FOREIGN KEY (`GARDENINGTYPE_ID`) REFERENCES `gardeningtype` (`ID`),
  ADD CONSTRAINT `FK_DEMANDEGARDENING_HOME_ID` FOREIGN KEY (`HOME_ID`) REFERENCES `home` (`ID`);

--
-- Contraintes pour la table `demandehandyman`
--
ALTER TABLE `demandehandyman`
  ADD CONSTRAINT `FK_DEMANDEHANDYMAN_DEMANDESERVICE_ID` FOREIGN KEY (`DEMANDESERVICE_ID`) REFERENCES `demandeservice` (`ID`);

--
-- Contraintes pour la table `demandemoving`
--
ALTER TABLE `demandemoving`
  ADD CONSTRAINT `FK_DEMANDEMOVING_DEMANDESERVICE_ID` FOREIGN KEY (`DEMANDESERVICE_ID`) REFERENCES `demandeservice` (`ID`),
  ADD CONSTRAINT `FK_DEMANDEMOVING_VILLEARRIVE_ID` FOREIGN KEY (`VILLEARRIVE_ID`) REFERENCES `ville` (`ID`),
  ADD CONSTRAINT `FK_DEMANDEMOVING_VILLEDEPART_ID` FOREIGN KEY (`VILLEDEPART_ID`) REFERENCES `ville` (`ID`);

--
-- Contraintes pour la table `demandepainting`
--
ALTER TABLE `demandepainting`
  ADD CONSTRAINT `FK_DEMANDEPAINTING_DEMANDESERVICE_ID` FOREIGN KEY (`DEMANDESERVICE_ID`) REFERENCES `demandeservice` (`ID`),
  ADD CONSTRAINT `FK_DEMANDEPAINTING_PAINTING_ID` FOREIGN KEY (`PAINTING_ID`) REFERENCES `paintingtype` (`ID`);

--
-- Contraintes pour la table `demandepestcontrol`
--
ALTER TABLE `demandepestcontrol`
  ADD CONSTRAINT `FK_DEMANDEPESTCONTROL_DEMANDESERVICE_ID` FOREIGN KEY (`DEMANDESERVICE_ID`) REFERENCES `demandeservice` (`ID`),
  ADD CONSTRAINT `FK_DEMANDEPESTCONTROL_TYPEOFPESTCONTROL_ID` FOREIGN KEY (`TYPEOFPESTCONTROL_ID`) REFERENCES `pestcontroltype` (`ID`);

--
-- Contraintes pour la table `demandephotographie`
--
ALTER TABLE `demandephotographie`
  ADD CONSTRAINT `FK_DEMANDEPHOTOGRAPHIE_DEMANDESERVICE_ID` FOREIGN KEY (`DEMANDESERVICE_ID`) REFERENCES `demandeservice` (`ID`),
  ADD CONSTRAINT `FK_DEMANDEPHOTOGRAPHIE_TYPEPHOTOGRAPHIE_ID` FOREIGN KEY (`TYPEPHOTOGRAPHIE_ID`) REFERENCES `photographietype` (`ID`);

--
-- Contraintes pour la table `demandeservice`
--
ALTER TABLE `demandeservice`
  ADD CONSTRAINT `FK_DEMANDESERVICE_CLIENT_EMAIL` FOREIGN KEY (`CLIENT_EMAIL`) REFERENCES `client` (`EMAIL`),
  ADD CONSTRAINT `FK_DEMANDESERVICE_MANAGERCONFIRMATION_ID` FOREIGN KEY (`MANAGERCONFIRMATION_ID`) REFERENCES `manager` (`ID`),
  ADD CONSTRAINT `FK_DEMANDESERVICE_PLANNING_ID` FOREIGN KEY (`PLANNING_ID`) REFERENCES `planning` (`ID`),
  ADD CONSTRAINT `FK_DEMANDESERVICE_SECTEUR_ID` FOREIGN KEY (`SECTEUR_ID`) REFERENCES `secteur` (`ID`),
  ADD CONSTRAINT `FK_DEMANDESERVICE_SERVICEPRICING_ID` FOREIGN KEY (`SERVICEPRICING_ID`) REFERENCES `servicepricing` (`ID`),
  ADD CONSTRAINT `FK_DEMANDESERVICE_SERVICE_ID` FOREIGN KEY (`SERVICE_ID`) REFERENCES `service` (`ID`),
  ADD CONSTRAINT `FK_DEMANDESERVICE_TYPEDEMANDE_ID` FOREIGN KEY (`TYPEDEMANDE_ID`) REFERENCES `typedemande` (`ID`),
  ADD CONSTRAINT `FK_DEMANDESERVICE_WORKERTYPE_ID` FOREIGN KEY (`WORKERTYPE_ID`) REFERENCES `workertype` (`ID`),
  ADD CONSTRAINT `FK_DEMANDESERVICE_WORKER_EMAIL` FOREIGN KEY (`WORKER_EMAIL`) REFERENCES `worker` (`EMAIL`);

--
-- Contraintes pour la table `demandeserviceconfirmationdetail`
--
ALTER TABLE `demandeserviceconfirmationdetail`
  ADD CONSTRAINT `DEMANDESERVICECONFIRMATIONDETAIL_DEMANDESERVICE_ID` FOREIGN KEY (`DEMANDESERVICE_ID`) REFERENCES `demandeservice` (`ID`),
  ADD CONSTRAINT `FK_DEMANDESERVICECONFIRMATIONDETAIL_MANAGER_ID` FOREIGN KEY (`MANAGER_ID`) REFERENCES `manager` (`ID`),
  ADD CONSTRAINT `FK_DEMANDESERVICECONFIRMATIONDETAIL_TYPEACTION_ID` FOREIGN KEY (`TYPEACTION_ID`) REFERENCES `typeaction` (`ID`);

--
-- Contraintes pour la table `demandevoiture`
--
ALTER TABLE `demandevoiture`
  ADD CONSTRAINT `FK_DEMANDEVOITURE_DEMANDESERVICE_ID` FOREIGN KEY (`DEMANDESERVICE_ID`) REFERENCES `demandeservice` (`ID`),
  ADD CONSTRAINT `FK_DEMANDEVOITURE_VOITURE_ID` FOREIGN KEY (`VOITURE_ID`) REFERENCES `voiture` (`ID`);

--
-- Contraintes pour la table `device`
--
ALTER TABLE `device`
  ADD CONSTRAINT `FK_DEVICE_MANAGER_ID` FOREIGN KEY (`MANAGER_ID`) REFERENCES `manager` (`ID`),
  ADD CONSTRAINT `FK_DEVICE_WORKER_EMAIL` FOREIGN KEY (`WORKER_EMAIL`) REFERENCES `worker` (`EMAIL`);

--
-- Contraintes pour la table `filiere`
--
ALTER TABLE `filiere`
  ADD CONSTRAINT `FK_FILIERE_NIVEAUSCOLAIRE_ID` FOREIGN KEY (`NIVEAUSCOLAIRE_ID`) REFERENCES `niveauscolaire` (`ID`);

--
-- Contraintes pour la table `formateurjob`
--
ALTER TABLE `formateurjob`
  ADD CONSTRAINT `FK_FORMATEURJOB_FPS_ID` FOREIGN KEY (`FPS_ID`) REFERENCES `fprosubtype` (`ID`),
  ADD CONSTRAINT `FK_FORMATEURJOB_WORKER_EMAIL` FOREIGN KEY (`WORKER_EMAIL`) REFERENCES `worker` (`EMAIL`);

--
-- Contraintes pour la table `formationprodemande`
--
ALTER TABLE `formationprodemande`
  ADD CONSTRAINT `FK_FORMATIONPRODEMANDE_DEMANDESERVICE_ID` FOREIGN KEY (`DEMANDESERVICE_ID`) REFERENCES `demandeservice` (`ID`),
  ADD CONSTRAINT `FK_FORMATIONPRODEMANDE_FORMATEUR_EMAIL` FOREIGN KEY (`FORMATEUR_EMAIL`) REFERENCES `worker` (`EMAIL`);

--
-- Contraintes pour la table `fprosubtype`
--
ALTER TABLE `fprosubtype`
  ADD CONSTRAINT `FK_FPROSUBTYPE_FP_ID` FOREIGN KEY (`FP_ID`) REFERENCES `fprotype` (`ID`);

--
-- Contraintes pour la table `home`
--
ALTER TABLE `home`
  ADD CONSTRAINT `FK_HOME_HOMETYPE_ID` FOREIGN KEY (`HOMETYPE_ID`) REFERENCES `hometype` (`ID`);

--
-- Contraintes pour la table `matiere`
--
ALTER TABLE `matiere`
  ADD CONSTRAINT `FK_MATIERE_FILIERE_ID` FOREIGN KEY (`FILIERE_ID`) REFERENCES `filiere` (`ID`);

--
-- Contraintes pour la table `menuformulaire`
--
ALTER TABLE `menuformulaire`
  ADD CONSTRAINT `FK_MENUFORMULAIRE_SERVICE_ID` FOREIGN KEY (`SERVICE_ID`) REFERENCES `service` (`ID`);

--
-- Contraintes pour la table `packaging`
--
ALTER TABLE `packaging`
  ADD CONSTRAINT `FK_PACKAGING_SERVICEPRICING_ID` FOREIGN KEY (`SERVICEPRICING_ID`) REFERENCES `servicepricing` (`ID`),
  ADD CONSTRAINT `FK_PACKAGING_SERVICE_ID` FOREIGN KEY (`SERVICE_ID`) REFERENCES `service` (`ID`);

--
-- Contraintes pour la table `planning`
--
ALTER TABLE `planning`
  ADD CONSTRAINT `FK_PLANNING_TIMING_ID` FOREIGN KEY (`TIMING_ID`) REFERENCES `timing` (`ID`);

--
-- Contraintes pour la table `planningitem`
--
ALTER TABLE `planningitem`
  ADD CONSTRAINT `FK_PLANNINGITEM_DAY_ID` FOREIGN KEY (`DAY_ID`) REFERENCES `day` (`ID`),
  ADD CONSTRAINT `FK_PLANNINGITEM_PLANNING_ID` FOREIGN KEY (`PLANNING_ID`) REFERENCES `planning` (`ID`),
  ADD CONSTRAINT `FK_PLANNINGITEM_TIMING_ID` FOREIGN KEY (`TIMING_ID`) REFERENCES `timing` (`ID`);

--
-- Contraintes pour la table `profjob`
--
ALTER TABLE `profjob`
  ADD CONSTRAINT `FK_PROFJOB_MATIERE_ID` FOREIGN KEY (`MATIERE_ID`) REFERENCES `matiere` (`ID`),
  ADD CONSTRAINT `FK_PROFJOB_WORKER_EMAIL` FOREIGN KEY (`WORKER_EMAIL`) REFERENCES `worker` (`EMAIL`);

--
-- Contraintes pour la table `review`
--
ALTER TABLE `review`
  ADD CONSTRAINT `FK_REVIEW_CLIENT_EMAIL` FOREIGN KEY (`CLIENT_EMAIL`) REFERENCES `client` (`EMAIL`),
  ADD CONSTRAINT `FK_REVIEW_SERVICE_ID` FOREIGN KEY (`SERVICE_ID`) REFERENCES `service` (`ID`),
  ADD CONSTRAINT `FK_REVIEW_WORKER_EMAIL` FOREIGN KEY (`WORKER_EMAIL`) REFERENCES `worker` (`EMAIL`);

--
-- Contraintes pour la table `secteur`
--
ALTER TABLE `secteur`
  ADD CONSTRAINT `FK_SECTEUR_VILLE_ID` FOREIGN KEY (`VILLE_ID`) REFERENCES `ville` (`ID`);

--
-- Contraintes pour la table `service`
--
ALTER TABLE `service`
  ADD CONSTRAINT `FK_SERVICE_CATEGORIE_ID` FOREIGN KEY (`CATEGORIE_ID`) REFERENCES `categorie` (`ID`);

--
-- Contraintes pour la table `servicepricing`
--
ALTER TABLE `servicepricing`
  ADD CONSTRAINT `FK_SERVICEPRICING_SERVICE_ID` FOREIGN KEY (`SERVICE_ID`) REFERENCES `service` (`ID`),
  ADD CONSTRAINT `FK_SERVICEPRICING_UNITE_ID` FOREIGN KEY (`UNITE_ID`) REFERENCES `unite` (`ID`);

--
-- Contraintes pour la table `serviceville`
--
ALTER TABLE `serviceville`
  ADD CONSTRAINT `FK_SERVICEVILLE_SERVICE_ID` FOREIGN KEY (`SERVICE_ID`) REFERENCES `service` (`ID`),
  ADD CONSTRAINT `FK_SERVICEVILLE_VILLE_ID` FOREIGN KEY (`VILLE_ID`) REFERENCES `ville` (`ID`);

--
-- Contraintes pour la table `supplementdemandeevent`
--
ALTER TABLE `supplementdemandeevent`
  ADD CONSTRAINT `FK_SUPPLEMENTDEMANDEEVENT_DEMANDEEVENT_ID` FOREIGN KEY (`DEMANDEEVENT_ID`) REFERENCES `demandeevent` (`ID`),
  ADD CONSTRAINT `FK_SUPPLEMENTDEMANDEEVENT_SUPPLEMENTEVENT_ID` FOREIGN KEY (`SUPPLEMENTEVENT_ID`) REFERENCES `supplementevent` (`ID`);

--
-- Contraintes pour la table `ville`
--
ALTER TABLE `ville`
  ADD CONSTRAINT `FK_VILLE_PAYS_ID` FOREIGN KEY (`PAYS_ID`) REFERENCES `pays` (`ID`);

--
-- Contraintes pour la table `voiture`
--
ALTER TABLE `voiture`
  ADD CONSTRAINT `FK_VOITURE_MODELE_ID` FOREIGN KEY (`MODELE_ID`) REFERENCES `voituremodele` (`ID`),
  ADD CONSTRAINT `FK_VOITURE_WORKER_EMAIL` FOREIGN KEY (`WORKER_EMAIL`) REFERENCES `worker` (`EMAIL`);

--
-- Contraintes pour la table `voitureimage`
--
ALTER TABLE `voitureimage`
  ADD CONSTRAINT `FK_VOITUREIMAGE_VOITURE_ID` FOREIGN KEY (`VOITURE_ID`) REFERENCES `voiture` (`ID`);

--
-- Contraintes pour la table `voituremodele`
--
ALTER TABLE `voituremodele`
  ADD CONSTRAINT `FK_VOITUREMODELE_MARQUE_ID` FOREIGN KEY (`MARQUE_ID`) REFERENCES `voituremarque` (`ID`);

--
-- Contraintes pour la table `worker`
--
ALTER TABLE `worker`
  ADD CONSTRAINT `FK_WORKER_WORKERTYPE_ID` FOREIGN KEY (`WORKERTYPE_ID`) REFERENCES `workertype` (`ID`);

--
-- Contraintes pour la table `workerjob`
--
ALTER TABLE `workerjob`
  ADD CONSTRAINT `FK_WORKERJOB_SECTEUR_ID` FOREIGN KEY (`SECTEUR_ID`) REFERENCES `secteur` (`ID`),
  ADD CONSTRAINT `FK_WORKERJOB_SERVICE_ID` FOREIGN KEY (`SERVICE_ID`) REFERENCES `service` (`ID`),
  ADD CONSTRAINT `FK_WORKERJOB_WORKER_EMAIL` FOREIGN KEY (`WORKER_EMAIL`) REFERENCES `worker` (`EMAIL`);
SET FOREIGN_KEY_CHECKS=1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
