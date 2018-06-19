-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 19, 2018 at 01:20 PM
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

--
-- Dumping data for table `carburant`
--

INSERT INTO `carburant` (`ID`, `NOM`) VALUES
(1, 'Diesel'),
(2, 'Essence');

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

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`EMAIL`, `ADRESSECOMPLEMENT`, `BLOCKED`, `NOM`, `PASSWORD`, `PHONE`, `PRENOM`, `TOKEN`, `SECTEUR_ID`) VALUES
('anas.the.creator@gmail.com', 'Saada 1 No 570', 0, 'Benchebani', 'walo', '0630247385', 'Mohamed Anas', NULL, 3),
('htakouit@gmail.com', 'Hay Chabab No 112', 0, 'Takouit', 'htakouit@gmail.com', '0677352220', 'hamza', NULL, 5),
('newCustomer@gmail.com', 'no 12 hay salama', 0, 'Customer', 'newCustomer@gmail.com', '0679461382', 'new', NULL, 5);

--
-- Dumping data for table `couleur`
--

INSERT INTO `couleur` (`ID`, `NOM`) VALUES
(1, 'Noir'),
(2, 'Blanc'),
(3, 'Rouge'),
(4, 'Gris'),
(5, 'Bleu');

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

--
-- Dumping data for table `cuisinetype`
--

INSERT INTO `cuisinetype` (`ID`, `NOM`) VALUES
(1, 'Arabe'),
(2, 'Chinoix'),
(3, 'Mexican'),
(4, 'Other');

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

--
-- Dumping data for table `demandeevent`
--

INSERT INTO `demandeevent` (`ID`, `NBRINVITES`, `DEMANDESERVICE_ID`, `EVENTBUDGET_ID`, `EVENTTYPE_ID`) VALUES
(1, 0, 22, 1, 1),
(2, 2, 24, 2, 5),
(3, 60, 26, 2, 5);

--
-- Dumping data for table `demandeformationpersonnel`
--

INSERT INTO `demandeformationpersonnel` (`ID`, `ADOMICILE`, `NBRPERSONNE`, `FILIERE_ID`, `MATIERE_ID`, `DEMANDESERVICE_ID`) VALUES
(1, 1, 5, NULL, NULL, 9),
(2, 0, 5, 7, 1, 41),
(3, 0, 5, 1, 4, 42);

--
-- Dumping data for table `demandehandyman`
--

INSERT INTO `demandehandyman` (`ID`, `NBRHANDYMAN`, `NBRHEURES`, `DEMANDESERVICE_ID`, `SERVICE_ID`) VALUES
(1, '2', '2', 27, 8),
(2, '1', '1', 38, 8);

--
-- Dumping data for table `demandepestcontrol`
--

INSERT INTO `demandepestcontrol` (`ID`, `DEMANDESERVICE_ID`, `TYPEOFPESTCONTROL_ID`) VALUES
(1, 25, 1);

--
-- Dumping data for table `demandephotographie`
--

INSERT INTO `demandephotographie` (`ID`, `VIDEOGRAPHIE`, `DEMANDESERVICE_ID`, `TYPEPHOTOGRAPHIE_ID`) VALUES
(1, 1, 10, 2);

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

--
-- Dumping data for table `demandevoiture`
--

INSERT INTO `demandevoiture` (`ID`, `DEMANDESERVICE_ID`) VALUES
(1, 36),
(2, 37),
(3, 40),
(4, 44);

--
-- Dumping data for table `demandevoitureitem`
--

INSERT INTO `demandevoitureitem` (`ID`, `DATEDEBUT`, `DATEFIN`, `PRIX`, `QUANTITE`, `CARBURANT_ID`, `DEMANDEVOITURE_ID`, `MODELE_ID`, `WORKER_EMAIL`) VALUES
(1, '2018-06-09', '2018-06-14', NULL, 2, 1, 4, 3, 'MultiServices@gmail.com'),
(2, '2018-06-20', '2018-06-26', NULL, 1, 2, 4, 1, 'MultiServices@gmail.com');

--
-- Dumping data for table `device`
--

INSERT INTO `device` (`ID`, `BLOCKED`, `DATECONNECTION`, `DEVICECATEGORIE`, `IP`, `NAVIGATEUR`, `OS`, `MANAGER_LOGIN`, `WORKER_EMAIL`) VALUES
(1, 0, '2018-04-10', 'Personal computer', '172.31.20.65', 'Firefox', 'Windows', 'manager', NULL),
(2, 0, '2018-04-23', 'Personal computer', '192.168.67.2', 'Firefox', 'Windows 8.1', 'ysn', NULL);

--
-- Dumping data for table `eventbudget`
--

INSERT INTO `eventbudget` (`ID`, `BUDGET`) VALUES
(1, '100dh par personne'),
(2, '150 dh par personne');

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

--
-- Dumping data for table `faq`
--

INSERT INTO `faq` (`ID`, `CORPS`, `TITRE`, `SERVICE_ID`) VALUES
(1, 'ceci est la reponse de la question numero 1 du service nettoyage', 'question numero 1 NM', 1),
(2, 'ceci est la reponse de la question numero 2 du service nettoyage', 'question numero 2 NM', 1),
(3, 'ceci est la reponse de la question numero 1 du service peinture', 'question numero 1 Peinture', 8),
(4, 'ceci est la reponse de la question numero 2 du service peinture', 'question numero 2 Peinture', 8);

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

--
-- Dumping data for table `manager`
--

INSERT INTO `manager` (`LOGIN`, `PASSWORD`, `BLOCKED`, `NOM`, `PHONE`, `PRENOM`) VALUES
('benchebani', 'anas18', 0, 'benchebani', '0630247385', 'mohamed anas'),
('manager', 'manager', 0, '', '', ''),
('ysn', '123', 0, 'Yassin', '0646925713', 'Mahjoubi');

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

--
-- Dumping data for table `pays`
--

INSERT INTO `pays` (`ID`, `NAME`, `TVA`) VALUES
(1, 'Morocco', '20'),
(2, 'Spain', '30');

--
-- Dumping data for table `pestcontroltype`
--

INSERT INTO `pestcontroltype` (`ID`, `NOM`) VALUES
(1, 'cockroaches'),
(2, 'bed bugs'),
(3, 'ants'),
(4, 'general');

--
-- Dumping data for table `photographietype`
--

INSERT INTO `photographietype` (`ID`, `NOM`) VALUES
(1, 'Marriage'),
(2, 'Shooting');

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

--
-- Dumping data for table `review`
--

INSERT INTO `review` (`ID`, `CORPS`, `DATEREVIEW`, `STARS`, `SUJET`, `WORKER_EMAIL`, `CLIENT_EMAIL`, `DEMANDESERVICE_ID`) VALUES
(1, 'c\'est la premiere fois que j\'etulise la platforme, est c\'est vraiment tres facile. le service offert par l\'entreprise est aussi genial', '2018-05-16', 4, 'Service magnifique', 'cleanharbors@gmail.com', 'anas.the.creator@gmail.com', 1),
(2, 'service tres rapide est professionnelle c\'est l\'un des mailleurs experience', '2018-05-23', 5, 'Service profesionnelle', 'cleanharbors@gmail.com', 'htakouit@gmail.com', 3),
(4, 'test commantaire ', '2018-05-30', 5, 'bon service', 'MultiServices@gmail.com', 'htakouit@gmail.com', 10),
(5, 'j\'ai bien apreciet le service ', '2018-06-03', 4, 'etat extraordinaire', 'MultiServices@gmail.com', 'anas.the.creator@gmail.com', 37);

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

--
-- Dumping data for table `sequence`
--

INSERT INTO `sequence` (`SEQ_NAME`, `SEQ_COUNT`) VALUES
('SEQ_GEN', '0');

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

--
-- Dumping data for table `supplementdemandeevent`
--

INSERT INTO `supplementdemandeevent` (`ID`, `DEMANDEEVENT_ID`, `SUPPLEMENTEVENT_ID`) VALUES
(1, 1, 2),
(2, 2, 1),
(3, 2, 2),
(4, 3, 2),
(5, 3, 3);

--
-- Dumping data for table `supplementevent`
--

INSERT INTO `supplementevent` (`ID`, `NOM`) VALUES
(1, 'Serveurs'),
(2, 'Tables et fournitures'),
(3, 'Decoration');

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

--
-- Dumping data for table `typeaction`
--

INSERT INTO `typeaction` (`ID`, `NAME`) VALUES
(1, 'Confirmation'),
(2, 'Suppression');

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

--
-- Dumping data for table `ville`
--

INSERT INTO `ville` (`ID`, `NOM`, `PAYS_ID`) VALUES
(1, 'Marrakesh', 1),
(2, 'Casablanca', 1),
(3, 'Madrid', 2),
(4, 'Barcelona', 2);

--
-- Dumping data for table `voiture`
--

INSERT INTO `voiture` (`ID`, `QUANTITE`, `CARBURANT_ID`, `WORKER_EMAIL`, `MODELE_ID`) VALUES
(1, 2, 1, 'MultiServices@gmail.com', 1),
(2, 4, 2, 'MultiServices@gmail.com', 2),
(3, 1, 1, 'MultiServices@gmail.com', 3),
(4, 1, 2, 'MultiServices@gmail.com', 3),
(5, 2, 2, 'top.sarl@gmail.com', 5);

--
-- Dumping data for table `voiturecarburantcouleur`
--

INSERT INTO `voiturecarburantcouleur` (`ID`, `CARBURANT_ID`, `COULEUR_ID`, `VOITURE_ID`) VALUES
(1, 1, 1, 1),
(2, 2, 2, 2),
(3, 1, 4, 3),
(4, 2, 3, 4),
(5, 2, 5, 5);

--
-- Dumping data for table `voituremarque`
--

INSERT INTO `voituremarque` (`ID`, `NOM`) VALUES
(1, 'Renault'),
(2, 'Ford');

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

--
-- Dumping data for table `voiturepricing`
--

INSERT INTO `voiturepricing` (`ID`, `DATEAPPLICATION`, `PRIX`, `UNITE_ID`, `VOITURE_ID`) VALUES
(1, '2018-06-01', '300', 9, 1),
(2, '2018-06-01', '250', 9, 2),
(3, '2018-06-01', '275', 9, 3),
(4, '2018-06-01', '387', 9, 4),
(5, '2018-06-01', '420', 9, 5);

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

--
-- Dumping data for table `workertype`
--

INSERT INTO `workertype` (`ID`, `NAME`) VALUES
(0, 'Choisis Pour Moi'),
(1, 'Personne Physique'),
(2, 'Personne Morale');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
