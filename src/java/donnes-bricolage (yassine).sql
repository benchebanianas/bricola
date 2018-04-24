-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Sam 14 Avril 2018 à 18:21
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Contenu de la table `demandebabysitting`
--

INSERT INTO `demandebabysitting` (`ID`, `DETAIL`, `FULLTIME`, `NBRHEURES`, `DEMANDESERVICE_ID`) VALUES
(100, 'walo walo walo walo', 1, '34', 1);

--
-- Contenu de la table `demandecleaning`
--

INSERT INTO `demandecleaning` (`ID`, `BRINGEQUIPEMENT`, `NBRCLEANER`, `NBRHEURES`, `DEMANDESERVICE_ID`) VALUES
(100, 0, '1', '3', 2);

--
-- Contenu de la table `demandephotographie`
--

INSERT INTO `demandephotographie` (`ID`, `VIDEOGRAPHIE`, `DEMANDESERVICE_ID`, `TYPEPHOTOGRAPHIE_ID`) VALUES
(100, 1, 3, 1);

--
-- Contenu de la table `demandeserviceconfirmationdetail`
--

INSERT INTO `demandeserviceconfirmationdetail` (`ID`, `DATEACTION`, `DEMANDESERVICE_ID`, `MANAGER_ID`, `TYPEACTION_ID`) VALUES
(10, '2018-04-13', 1, 'ysn', 2),
(11, '2018-04-13', 2, 'ysn', 1);

--
-- Contenu de la table `device`
--

INSERT INTO `device` (`ID`, `BLOCKED`, `DATECONNECTION`, `DEVICECATEGORIE`, `IP`, `NAVIGATEUR`, `OS`, `MANAGER_ID`, `WORKER_EMAIL`) VALUES
(1, 0, '2018-04-13', NULL, '192.168.67.2', 'Firefox', 'Windows 8.1', 'ysn', NULL);

--
-- Contenu de la table `manager`
--

INSERT INTO `manager` (`ID`, `PASSWORD`, `BLOCKED`) VALUES
('ysn', 'ysn', 0);


SET FOREIGN_KEY_CHECKS=1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
