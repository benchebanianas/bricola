-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mar 20 Mars 2018 à 18:39
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
(8, 'Voitures');

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
  `SECTEUR_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `client`
--

INSERT INTO `client` (`EMAIL`, `ADRESSECOMPLEMENT`, `BLOCKED`, `NOM`, `PASSWORD`, `PHONE`, `PRENOM`, `SECTEUR_ID`) VALUES
('anas.the.creator@gmail.com', 'Saada 1 No 570', 0, 'Benchebani', 'walo', '0630247385', 'Mohamed Anas', 2);

-- --------------------------------------------------------

--
-- Structure de la table `cuisinedemandeevent`
--

CREATE TABLE `cuisinedemandeevent` (
  `ID` bigint(20) NOT NULL,
  `CUISINE_ID` bigint(20) DEFAULT NULL,
  `DEMANDEEVENT_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `cuisinetype`
--

CREATE TABLE `cuisinetype` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `day`
--

CREATE TABLE `day` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `NBRCLEANER` int(11) DEFAULT NULL,
  `NBRHEURES` decimal(38,0) DEFAULT NULL,
  `DEMANDESERVICE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `demandeevent`
--

CREATE TABLE `demandeevent` (
  `ID` bigint(20) NOT NULL,
  `DETAIL` varchar(255) DEFAULT NULL,
  `NBRINVITES` int(11) DEFAULT NULL,
  `DEMANDESERVICE_ID` bigint(20) DEFAULT NULL,
  `EVENT_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `DETAIL` varchar(255) DEFAULT NULL,
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
  `WORKER_ID` bigint(20) DEFAULT NULL,
  `TYPEDEMANDE_ID` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `IP` varchar(255) DEFAULT NULL,
  `NAVIGATEUR` varchar(255) DEFAULT NULL,
  `OS` varchar(255) DEFAULT NULL,
  `MANAGER_ID` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `eventtype`
--

CREATE TABLE `eventtype` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `faq`
--

CREATE TABLE `faq` (
  `ID` bigint(20) NOT NULL
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
(1, 'Morocco', NULL),
(2, 'Spain', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `pestcontroltype`
--

CREATE TABLE `pestcontroltype` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `photographietype`
--

CREATE TABLE `photographietype` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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

-- --------------------------------------------------------

--
-- Structure de la table `review`
--

CREATE TABLE `review` (
  `ID` bigint(20) NOT NULL,
  `DATEREVIEW` date DEFAULT NULL,
  `STARS` int(11) DEFAULT NULL,
  `TEXT` varchar(255) DEFAULT NULL,
  `WORKER_ID` bigint(20) DEFAULT NULL,
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
(1, 'Nettoyage Maison', 1),
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

-- --------------------------------------------------------

--
-- Structure de la table `supplementevent`
--

CREATE TABLE `supplementevent` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `timing`
--

CREATE TABLE `timing` (
  `ID` bigint(20) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `VALEUR` decimal(38,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `typeaction`
--

CREATE TABLE `typeaction` (
  `ID` bigint(20) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `typedemande`
--

CREATE TABLE `typedemande` (
  `ID` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `unite`
--

CREATE TABLE `unite` (
  `ID` bigint(20) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `WORKER_ID` bigint(20) DEFAULT NULL,
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
  `ID` bigint(20) NOT NULL,
  `BLOCKED` tinyint(1) DEFAULT '0',
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `NOMBREEMPLOYE` int(11) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `PHONE` varchar(255) DEFAULT NULL,
  `SITEWEB` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `workerjob`
--

CREATE TABLE `workerjob` (
  `ID` bigint(20) NOT NULL,
  `SECTEUR_ID` bigint(20) DEFAULT NULL,
  `SERVICE_ID` bigint(20) DEFAULT NULL,
  `WORKER_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  ADD KEY `FK_DEMANDEEVENT_EVENT_ID` (`EVENT_ID`),
  ADD KEY `FK_DEMANDEEVENT_DEMANDESERVICE_ID` (`DEMANDESERVICE_ID`);

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
  ADD KEY `FK_DEMANDESERVICE_WORKER_ID` (`WORKER_ID`),
  ADD KEY `FK_DEMANDESERVICE_CLIENT_EMAIL` (`CLIENT_EMAIL`),
  ADD KEY `FK_DEMANDESERVICE_SECTEUR_ID` (`SECTEUR_ID`),
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
  ADD KEY `FK_DEVICE_MANAGER_ID` (`MANAGER_ID`);

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
-- Index pour la table `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_REVIEW_SERVICE_ID` (`SERVICE_ID`),
  ADD KEY `FK_REVIEW_WORKER_ID` (`WORKER_ID`),
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
  ADD KEY `FK_VOITURE_WORKER_ID` (`WORKER_ID`),
  ADD KEY `FK_VOITURE_MODELE_ID` (`MODELE_ID`);

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
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `workerjob`
--
ALTER TABLE `workerjob`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_WORKERJOB_SERVICE_ID` (`SERVICE_ID`),
  ADD KEY `FK_WORKERJOB_WORKER_ID` (`WORKER_ID`),
  ADD KEY `FK_WORKERJOB_SECTEUR_ID` (`SECTEUR_ID`);

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
  ADD CONSTRAINT `FK_DEMANDEEVENT_EVENT_ID` FOREIGN KEY (`EVENT_ID`) REFERENCES `eventtype` (`ID`);

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
  ADD CONSTRAINT `FK_DEMANDESERVICE_WORKER_ID` FOREIGN KEY (`WORKER_ID`) REFERENCES `worker` (`ID`);

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
  ADD CONSTRAINT `FK_DEVICE_MANAGER_ID` FOREIGN KEY (`MANAGER_ID`) REFERENCES `manager` (`ID`);

--
-- Contraintes pour la table `home`
--
ALTER TABLE `home`
  ADD CONSTRAINT `FK_HOME_HOMETYPE_ID` FOREIGN KEY (`HOMETYPE_ID`) REFERENCES `hometype` (`ID`);

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
-- Contraintes pour la table `review`
--
ALTER TABLE `review`
  ADD CONSTRAINT `FK_REVIEW_CLIENT_EMAIL` FOREIGN KEY (`CLIENT_EMAIL`) REFERENCES `client` (`EMAIL`),
  ADD CONSTRAINT `FK_REVIEW_SERVICE_ID` FOREIGN KEY (`SERVICE_ID`) REFERENCES `service` (`ID`),
  ADD CONSTRAINT `FK_REVIEW_WORKER_ID` FOREIGN KEY (`WORKER_ID`) REFERENCES `worker` (`ID`);

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
  ADD CONSTRAINT `FK_VOITURE_WORKER_ID` FOREIGN KEY (`WORKER_ID`) REFERENCES `worker` (`ID`);

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
-- Contraintes pour la table `workerjob`
--
ALTER TABLE `workerjob`
  ADD CONSTRAINT `FK_WORKERJOB_SECTEUR_ID` FOREIGN KEY (`SECTEUR_ID`) REFERENCES `secteur` (`ID`),
  ADD CONSTRAINT `FK_WORKERJOB_SERVICE_ID` FOREIGN KEY (`SERVICE_ID`) REFERENCES `service` (`ID`),
  ADD CONSTRAINT `FK_WORKERJOB_WORKER_ID` FOREIGN KEY (`WORKER_ID`) REFERENCES `worker` (`ID`);
SET FOREIGN_KEY_CHECKS=1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
