-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Erstellungszeit: 27. Okt 2026 um 11:54
-- Server-Version: 10.4.28-MariaDB
-- PHP-Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `ghostnet_db`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `GhostNet`
--

CREATE TABLE `GhostNet` (
  `id` bigint(20) NOT NULL,
  `estimatedSize` double NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `bergendePerson_id` bigint(20) DEFAULT NULL,
  `meldendePerson_id` bigint(20) DEFAULT NULL,
  `verschollenMelder_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `GhostNet`
--

INSERT INTO `GhostNet` (`id`, `estimatedSize`, `latitude`, `longitude`, `status`, `bergendePerson_id`, `meldendePerson_id`, `verschollenMelder_id`) VALUES
(1, 8, 30, 60.123456, 'GEBORGEN', 2, NULL, NULL),
(2, 18, 54.454545, 89.906, 'VERSCHOLLEN', NULL, 1, 3),
(3, 30, 52.516275, 13.3777, 'BERGUNG_BEVORSTEHEND', 4, 4, NULL),
(4, 120, 88.12345, 8.12345, 'GEMELDET', NULL, 5, NULL);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Person`
--

CREATE TABLE `Person` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `telefonnummer` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `Person`
--

INSERT INTO `Person` (`id`, `name`, `telefonnummer`) VALUES
(1, 'Albert Einstein', '0176123456'),
(2, 'Isaac Newton', '0800123456'),
(3, 'Ada Lovelace', '0152887766'),
(4, 'Thomas Edison', '0177765432'),
(5, 'Alan Turing', '0900555555');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `GhostNet`
--
ALTER TABLE `GhostNet`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKmhrlil2nw31atv6mh05m7o302` (`bergendePerson_id`),
  ADD KEY `FKioof3qc5m6f7j509vn7f79cpc` (`meldendePerson_id`),
  ADD KEY `FKsgugyxoe8wb5dvqm9ldjq1v30` (`verschollenMelder_id`);

--
-- Indizes für die Tabelle `Person`
--
ALTER TABLE `Person`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `GhostNet`
--
ALTER TABLE `GhostNet`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT für Tabelle `Person`
--
ALTER TABLE `Person`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `GhostNet`
--
ALTER TABLE `GhostNet`
  ADD CONSTRAINT `FKioof3qc5m6f7j509vn7f79cpc` FOREIGN KEY (`meldendePerson_id`) REFERENCES `Person` (`id`),
  ADD CONSTRAINT `FKmhrlil2nw31atv6mh05m7o302` FOREIGN KEY (`bergendePerson_id`) REFERENCES `Person` (`id`),
  ADD CONSTRAINT `FKsgugyxoe8wb5dvqm9ldjq1v30` FOREIGN KEY (`verschollenMelder_id`) REFERENCES `Person` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
