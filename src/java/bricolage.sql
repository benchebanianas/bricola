-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mar 10 Avril 2018 à 19:37
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
(8, 'Voitures');

--
-- Contenu de la table `client`
--

INSERT INTO `client` (`EMAIL`, `ADRESSECOMPLEMENT`, `BLOCKED`, `NOM`, `PASSWORD`, `PHONE`, `PRENOM`, `SECTEUR_ID`) VALUES
('anas.the.creator@gmail.com', 'Saada 1 No 570', 0, 'Benchebani', 'walo', '0630247385', 'Mohamed Anas', 3),
('newCustomer@gmail.com', 'no 12 hay salama', 0, 'Customer', 'newCustomer@gmail.com', '0679461382', 'new', 5);

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

--
-- Contenu de la table `demandeservice`
--

INSERT INTO `demandeservice` (`ID`, `DATECONFIRMATION`, `DATEDERNIERMODIF`, `DATESUPPRESSION`, `DATEDEMANDE`, `DETAIL`, `PRIXHT`, `PRIXTTC`, `CLIENT_EMAIL`, `MANAGERCONFIRMATION_ID`, `PLANNING_ID`, `SECTEUR_ID`, `SERVICE_ID`, `SERVICEPRICING_ID`, `WORKER_EMAIL`, `WORKERTYPE_ID`, `TYPEDEMANDE_ID`) VALUES
(1, NULL, NULL, NULL, '2018-03-23', 'please enter quietly because the kids are still sleeping ', '300', '300', 'anas.the.creator@gmail.com', NULL, 1, 2, 1, 1, 'coitcleaners@gmail.com', 1, NULL),
(2, NULL, NULL, NULL, '2018-03-23', 'none', '50', '50', 'anas.the.creator@gmail.com', NULL, 2, 2, 1, 1, 'merrymaids@gmail.com', 2, NULL),
(3, NULL, NULL, NULL, '2018-03-23', 'i left food in the kitchen if you would like to eat', '400', '400', 'anas.the.creator@gmail.com', NULL, 3, 2, 1, 1, 'cleanharbors@gmail.com', 2, NULL),
(4, NULL, NULL, NULL, '2018-03-23', 'if you cloud please check to close the door before you leave thank you', '400', '400', 'newCustomer@gmail.com', NULL, 4, 5, 1, 1, 'cleanharbors@gmail.com', 2, NULL),
(5, NULL, NULL, NULL, '2018-03-31', 'this is just a test', '600', '600', 'anas.the.creator@gmail.com', NULL, 5, 2, 1, 1, 'coitcleaners@gmail.com', 1, NULL),
(6, NULL, NULL, NULL, '2018-03-31', 'test for cleaning', '50', '50', 'anas.the.creator@gmail.com', NULL, 6, 2, 1, 1, 'merrymaids@gmail.com', 2, NULL),
(7, NULL, NULL, NULL, '2018-03-31', 'none', '200', '200', 'anas.the.creator@gmail.com', NULL, 7, 2, 1, 1, 'cleanharbors@gmail.com', 2, NULL),
(8, NULL, NULL, NULL, '2018-03-31', 'please enter the house quietly ! ', '1000', '1000', 'anas.the.creator@gmail.com', NULL, 8, 3, 1, 1, 'merrymaids@gmail.com', 2, NULL);

--
-- Structure de la table `demandeserviceconfirmationdetail`
--

INSERT INTO `device` (`ID`, `BLOCKED`, `DATECONNECTION`, `IP`, `NAVIGATEUR`, `OS`, `MANAGER_ID`, `WORKER_EMAIL`, `DEVICECATEGORIE`) VALUES
(1, 0, '2018-04-10', '172.31.20.65', 'Firefox', 'Windows', 'manager', NULL, 'Personal computer');


--
-- Contenu de la table `manager`
--

INSERT INTO `manager` (`ID`, `PASSWORD`, `BLOCKED`) VALUES
('manager', 'manager', 0);


--
-- Contenu de la table `menuformulaire`
--

INSERT INTO `menuformulaire` (`ID`, `COMPANYTAB`, `DETAILSTAB`, `IMAGENAME`, `INFOTAB`, `PAYEMENTTAB`, `SUMMARYTAB`, `TYPEDEMANDE_ID`) VALUES
(1, 1, 1, 'nettoyageMaison', 1, 1, 1, 'nettoyageMaison');


--
-- Contenu de la table `pays`
--

INSERT INTO `pays` (`ID`, `NAME`, `TVA`) VALUES
(1, 'Morocco', NULL),
(2, 'Spain', NULL);

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
(8, NULL, NULL, '2018-03-31', 5);

--
-- Contenu de la table `planningitem`
--

INSERT INTO `planningitem` (`ID`, `NUMEROJOUR`, `REPETITION`, `DAY_ID`, `PLANNING_ID`, `TIMING_ID`) VALUES
(1, 0, 1, 1, 6, 1),
(2, 0, 1, 3, 1, 5),
(3, 3, 2, NULL, 1, 9),
(4, 1, 2, NULL, 3, 15),
(5, 0, 1, 5, 3, 13),
(51, 7, 2, NULL, 4, 1),
(52, 0, 1, 2, 4, 5),
(101, 0, 1, 1, 6, 1),
(151, 5, 2, NULL, 7, 1),
(152, 0, 1, 5, 7, 9);


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

--
-- Contenu de la table `service`
--

INSERT INTO `service` (`ID`, `NOM`, `CATEGORIE_ID`) VALUES
(1, 'nettoyageMaison', 1),
(2, 'Nettoyage Complet', 1),
(3, 'Nettoyage Piscine', 1),
(4, 'Nettoyage Divers', 1),
(5, 'Demenagement national', 2),
(6, 'Demenagement international', 2),
(7, 'Stockage', 2),
(8, 'Peinture', 3),
(9, 'Handyman', 3),
(10, 'Electricite', 3),
(11, 'Plomberie', 3),
(12, 'Menuiserie', 3),
(13, 'Climatisation', 3),
(14, 'Deratisation complete', 4),
(15, 'Gardiennage', 4),
(16, 'Restauration', 5),
(17, 'Traiteur', 5),
(18, 'Babysitters', 6),
(19, 'Photographie', 7),
(20, 'Videographie', 7),
(21, 'Location Voiture', 8);


--
-- Contenu de la table `servicepricing`
--

INSERT INTO `servicepricing` (`ID`, `DATEAPPLICATION`, `PRIX`, `SERVICE_ID`, `UNITE_ID`) VALUES
(1, '2015-03-03', '50', 1, 1);

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


--
-- Structure de la table `supplementdemandeevent`
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
-- Contenu de la table `typedemande`
--

INSERT INTO `typedemande` (`ID`) VALUES
('climatisation'),
('demenagement'),
('deratisation'),
('locationVoiture'),
('nettoyageMaison'),
('peinture'),
('photographie'),
('traiteur');


--
-- Contenu de la table `unite`
--

INSERT INTO `unite` (`ID`, `NAME`) VALUES
(1, 'CleanerPerHour');


--
-- Contenu de la table `ville`
--

INSERT INTO `ville` (`ID`, `NOM`, `PAYS_ID`) VALUES
(1, 'Marrakesh', 1),
(2, 'Casablanca', 1),
(3, 'Madrid', 2),
(4, 'Barcelona', 2);

--
-- Contenu de la table `worker`
--

INSERT INTO `worker` (`EMAIL`, `BLOCKED`, `DESCRIPTION`, `NOM`, `NOMBREEMPLOYE`, `PASSWORD`, `PHONE`, `SITEWEB`, `WORKERTYPE_ID`) VALUES
('cleanharbors@gmail.com', 0, 'you probably heard of us, the leading company for cleaning services in over 26 countries', 'Clean Harbors', 32, 'cleanharbors@gmail.com', '0679120435', 'www.cleanharbors.com', 2),
('coitcleaners@gmail.com', 0, 'An inspiring individual with many years of experiences in cleaning', 'Coit Cleaners ', 5, 'coitcleaners@gmail.com', '0613467982', 'www.coitcleaners.com', 1),
('merrymaids@gmail.com', 0, 'we can guarantee that our services are the best in the cleaning industry', 'Merry Maids', 54, 'merrymaids@gmail.com', '0687125903', 'www.merrymaids.com', 2),
('taskrabbit@gmail.com', 0, 'we hire specialist from all around the globe so that you can have the best services', 'TaskRabbit ', 78, 'taskrabbit@gmail.com', '0612064367', 'www.taskrabbit.com', 2);


--
-- Contenu de la table `workerjob`
--

INSERT INTO `workerjob` (`ID`, `SECTEUR_ID`, `SERVICE_ID`, `WORKER_EMAIL`) VALUES
(1, 1, 1, 'cleanharbors@gmail.com'),
(2, 2, 1, 'merrymaids@gmail.com'),
(3, 4, 4, 'taskrabbit@gmail.com'),
(4, 5, 1, 'coitcleaners@gmail.com');

--
-- Contenu de la table `workertype`
--

INSERT INTO `workertype` (`ID`, `NAME`) VALUES
(1, 'Personne Physique'),
(2, 'Personne Morale');


SET FOREIGN_KEY_CHECKS=1;
commit;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
