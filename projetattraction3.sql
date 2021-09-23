-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : jeu. 23 sep. 2021 à 14:31
-- Version du serveur :  5.7.31
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `projetattraction3`
--
CREATE DATABASE IF NOT EXISTS `projetattraction3` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `projetattraction3`;

-- --------------------------------------------------------

--
-- Structure de la table `achat`
--

DROP TABLE IF EXISTS `achat`;
CREATE TABLE IF NOT EXISTS `achat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_element` varchar(40) DEFAULT NULL,
  `id_element` int(11) DEFAULT NULL,
  `id_parc` int(11) DEFAULT NULL,
  `nb_same_element` int(11) DEFAULT NULL,
  `niveau_amelioration` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5n1t2pq2311cwcv5bi9khx6le` (`id_parc`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `achat`
--

INSERT INTO `achat` (`id`, `type_element`, `id_element`, `id_parc`, `nb_same_element`, `niveau_amelioration`) VALUES
(4, 'attraction', 17, 3, 0, 6),
(7, 'attraction', 15, 5, 0, 0),
(9, 'attraction', 18, 5, 0, 0),
(10, 'attraction', 16, 5, 0, 0),
(12, 'restaurant', 11, 5, 0, 0),
(13, 'attraction', 17, 5, 0, 0),
(14, 'commodite', 1, 5, 2, 0),
(15, 'commodite', 2, 5, 3, 0),
(16, 'restaurant', 13, 5, 0, 0),
(22, 'attraction', 16, 6, 0, 0),
(23, 'restaurant', 12, 7, 0, 0),
(24, 'attraction', 17, 7, 0, 0),
(25, 'commodite', 1, 7, 1, 0),
(26, 'commodite', 2, 7, 1, 0),
(27, 'restaurant', 14, 5, 0, 0),
(28, 'employe', 7, 5, 1, 0),
(46, 'boutique', 4, 11, 0, 0),
(47, 'attraction', 15, 11, 0, 0),
(48, 'attraction', 17, 11, 0, 0),
(49, 'attraction', 16, 11, 0, 0),
(50, 'attraction', 18, 11, 0, 0),
(51, 'restaurant', 11, 11, 0, 0),
(52, 'boutique', 5, 11, 0, 0),
(53, 'restaurant', 12, 11, 0, 0),
(54, 'restaurant', 13, 11, 0, 0),
(55, 'restaurant', 14, 11, 0, 0),
(56, 'commodite', 1, 11, 3, 0);

-- --------------------------------------------------------

--
-- Structure de la table `attraction`
--

DROP TABLE IF EXISTS `attraction`;
CREATE TABLE IF NOT EXISTS `attraction` (
  `id` int(11) NOT NULL,
  `affluence_max` int(11) DEFAULT NULL,
  `nb_amelioration` int(11) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prix_acquisition` double DEFAULT NULL,
  `prix_fonctionnement` double DEFAULT NULL,
  `taille` double NOT NULL,
  `taux_incident` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `attraction`
--

INSERT INTO `attraction` (`id`, `affluence_max`, `nb_amelioration`, `nom`, `prix_acquisition`, `prix_fonctionnement`, `taille`, `taux_incident`) VALUES
(15, 500, 10, 'YoloCoaster', 500000, 5000, 500, 40),
(16, 450, 5, 'Ascenscoon', 400000, 1000, 200, 20),
(17, 400, 4, 'Auto-tamponoon', 175000, 750, 350, 20),
(18, 425, 7, 'Yol-horreur', 300000, 800, 300, 20);

-- --------------------------------------------------------

--
-- Structure de la table `boutique`
--

DROP TABLE IF EXISTS `boutique`;
CREATE TABLE IF NOT EXISTS `boutique` (
  `id` int(11) NOT NULL,
  `affluence_max` int(11) DEFAULT NULL,
  `nb_amelioration` int(11) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prix_acquisition` double DEFAULT NULL,
  `prix_fonctionnement` double DEFAULT NULL,
  `taille` double NOT NULL,
  `taux_incident` double DEFAULT NULL,
  `revenuJourPersonne` double NOT NULL,
  `revenu_jour_personne` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `boutique`
--

INSERT INTO `boutique` (`id`, `affluence_max`, `nb_amelioration`, `nom`, `prix_acquisition`, `prix_fonctionnement`, `taille`, `taux_incident`, `revenuJourPersonne`, `revenu_jour_personne`) VALUES
(4, 400, 6, 'Boutique PAPoon', 300000, 1500, 250, 10, 300, 3000),
(5, 400, 6, 'Boutique Dream', 300000, 1000, 250, 10, 200, 1500);

-- --------------------------------------------------------

--
-- Structure de la table `commodite`
--

DROP TABLE IF EXISTS `commodite`;
CREATE TABLE IF NOT EXISTS `commodite` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prix_acquisition` double DEFAULT NULL,
  `taille` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `commodite`
--

INSERT INTO `commodite` (`id`, `nom`, `prix_acquisition`, `taille`) VALUES
(1, 'ToilettesFemmes', 100000, 100),
(2, 'ToilettesHommes', 100000, 100),
(3, 'ZoneDeDetente', 75000, 100);

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

DROP TABLE IF EXISTS `compte`;
CREATE TABLE IF NOT EXISTS `compte` (
  `type_compte` enum('admin','joueur') NOT NULL,
  `id_compte` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `enable` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id_compte`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `compte`
--

INSERT INTO `compte` (`type_compte`, `id_compte`, `login`, `password`, `enable`) VALUES
('joueur', 1, 'caro', '$2a$10$Yz3ox6SovSK8.8OIgarzj.ZohT2Uq2NX53S3/RRsZifA1hXc8AR56', b'0'),
('admin', 2, 'user', '$2a$10$SmecC1UYvw7xGcpr0eoeru/eNPAvDvyeg6WwRx.OBzvIjNPQNxs86', b'0'),
('joueur', 3, 'lamer', '$2a$10$fTL6KPRi5VR26f5avXg4aeeUzbnImvDKsqemjyPKADJDdyXcXHscW', b'0'),
('joueur', 4, 'bill', '$2a$10$gBy4.nBCH.g0F5J68n/62uhnH6M6TaVtDvgfF0xMwwY4M//6OppAi', b'0'),
('joueur', 5, 'bernard', '$2a$10$WGdSBKmjrvwxsri7O6jCK.C9UKISXeEOwwobnMi3wJOjvtWBQLyuW', b'0');

-- --------------------------------------------------------

--
-- Structure de la table `employe`
--

DROP TABLE IF EXISTS `employe`;
CREATE TABLE IF NOT EXISTS `employe` (
  `id` int(11) NOT NULL,
  `metier` varchar(255) DEFAULT NULL,
  `salaire` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `employe`
--

INSERT INTO `employe` (`id`, `metier`, `salaire`) VALUES
(6, 'Jardinier', 45),
(7, 'Agent dentretien', 40),
(8, 'Securite', 90),
(9, 'Personnel dattractions', 80),
(10, 'Mascotte', 35);

-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
CREATE TABLE IF NOT EXISTS `hibernate_sequences` (
  `sequence_name` varchar(255) NOT NULL,
  `next_val` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`sequence_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `hibernate_sequences`
--

INSERT INTO `hibernate_sequences` (`sequence_name`, `next_val`) VALUES
('default', 18);

-- --------------------------------------------------------

--
-- Structure de la table `parc`
--

DROP TABLE IF EXISTS `parc`;
CREATE TABLE IF NOT EXISTS `parc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `argent` double NOT NULL,
  `nbjour` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `taille` double NOT NULL,
  `typeDifficulte` varchar(255) DEFAULT NULL,
  `id_joueur` int(11) DEFAULT NULL,
  `type_difficulte` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7fu8bhg95aomfolhochmq6ju4` (`id_joueur`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `parc`
--

INSERT INTO `parc` (`id`, `argent`, `nbjour`, `nom`, `taille`, `typeDifficulte`, `id_joueur`, `type_difficulte`) VALUES
(2, 750000, 0, 'Test2', 8000, NULL, 1, 'Moyen'),
(3, 1000000, 0, 'monParc', 10000, NULL, 1, 'Facile'),
(5, 1134930, 77, 'ParcOLand', 8880, NULL, 4, 'Facile'),
(6, 1140748.75, 128, 'rbddrdb', 7800, NULL, 4, 'Moyen'),
(7, 57295400, 104, 'turfu', 4250, NULL, 5, 'Difficile'),
(8, 500000, 0, 'dur', 5000, NULL, 4, 'Difficile'),
(11, 11605060, 30, 'de merde!', 5430, NULL, 4, 'Moyen');

-- --------------------------------------------------------

--
-- Structure de la table `restaurant`
--

DROP TABLE IF EXISTS `restaurant`;
CREATE TABLE IF NOT EXISTS `restaurant` (
  `id` int(11) NOT NULL,
  `affluence_max` int(11) DEFAULT NULL,
  `nb_amelioration` int(11) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prix_acquisition` double DEFAULT NULL,
  `prix_fonctionnement` double DEFAULT NULL,
  `taille` double NOT NULL,
  `taux_incident` double DEFAULT NULL,
  `revenuJourPersonne` double NOT NULL,
  `revenu_jour_personne` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `restaurant`
--

INSERT INTO `restaurant` (`id`, `affluence_max`, `nb_amelioration`, `nom`, `prix_acquisition`, `prix_fonctionnement`, `taille`, `taux_incident`, `revenuJourPersonne`, `revenu_jour_personne`) VALUES
(11, 600, 15, 'Le Yololo', 450000, 1500, 250, 45, 300, 1500),
(12, 400, 12, 'Tacotycoon ', 300000, 1250, 200, 45, 200, 1950),
(13, 300, 8, 'YoloDouceur', 150000, 1000, 150, 30, 150, 1300),
(14, 300, 4, 'Glacoon', 100000, 800, 70, 20, 90, 1200);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `parc`
--
ALTER TABLE `parc`
  ADD CONSTRAINT `FK7fu8bhg95aomfolhochmq6ju4` FOREIGN KEY (`id_joueur`) REFERENCES `compte` (`id_compte`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
