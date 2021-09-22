-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mer. 22 sep. 2021 à 18:39
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
(15, 500, 10, 'Yolo Coaster', 500000, 5000, 500, 40),
(16, 450, 5, 'Ascenscoon', 400000, 1000, 100, 20),
(17, 400, 4, 'Auto-tamponoon', 175000, 750, 150, 20),
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
  `revenu_jour_personne` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `boutique`
--

INSERT INTO `boutique` (`id`, `affluence_max`, `nb_amelioration`, `nom`, `prix_acquisition`, `prix_fonctionnement`, `taille`, `taux_incident`, `revenu_jour_personne`) VALUES
(4, 400, 6, 'Boutique PAPoon', 300000, 1500, 250, 10, 300),
(5, 400, 6, 'Boutique Dream', 300000, 1000, 250, 10, 200);

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
(1, 'Toilettes F', 100000, 75),
(2, 'Toilettes H', 100000, 75),
(3, 'Zone de Detente', 75000, 100);

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `compte`
--

INSERT INTO `compte` (`type_compte`, `id_compte`, `login`, `password`, `enable`) VALUES
('joueur', 1, 'caro', '$2a$10$Yz3ox6SovSK8.8OIgarzj.ZohT2Uq2NX53S3/RRsZifA1hXc8AR56', b'0'),
('admin', 2, 'user', '$2a$10$SmecC1UYvw7xGcpr0eoeru/eNPAvDvyeg6WwRx.OBzvIjNPQNxs86', b'0'),
('joueur', 3, 'lamer', '$2a$10$fTL6KPRi5VR26f5avXg4aeeUzbnImvDKsqemjyPKADJDdyXcXHscW', b'0');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `parc`
--

INSERT INTO `parc` (`id`, `argent`, `nbjour`, `nom`, `taille`, `typeDifficulte`, `id_joueur`, `type_difficulte`) VALUES
(3, 1043665, 3, 'monParc', 9130, NULL, 1, 'Facile');

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
  `revenu_jour_personne` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `restaurant`
--

INSERT INTO `restaurant` (`id`, `affluence_max`, `nb_amelioration`, `nom`, `prix_acquisition`, `prix_fonctionnement`, `taille`, `taux_incident`, `revenu_jour_personne`) VALUES
(11, 600, 15, 'Le Yololo', 450000, 1500, 250, 45, 300),
(12, 400, 12, 'Tacotycoon ', 300000, 1250, 200, 45, 200),
(13, 300, 8, 'YoloDouceur ', 150000, 1000, 100, 30, 150),
(14, 300, 4, 'Glacoon', 100000, 800, 70, 20, 90);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `achat`
--
ALTER TABLE `achat`
  ADD CONSTRAINT `FK5n1t2pq2311cwcv5bi9khx6le` FOREIGN KEY (`id_parc`) REFERENCES `parc` (`id`);

--
-- Contraintes pour la table `parc`
--
ALTER TABLE `parc`
  ADD CONSTRAINT `FK7fu8bhg95aomfolhochmq6ju4` FOREIGN KEY (`id_joueur`) REFERENCES `compte` (`id_compte`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
