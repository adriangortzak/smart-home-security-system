-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 21, 2016 at 11:10 AM
-- Server version: 5.5.49-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `SHSS`
--

-- --------------------------------------------------------

--
-- Table structure for table `alarmStatus`
--

CREATE TABLE IF NOT EXISTS `alarmStatus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activeAlarm` tinyint(1) NOT NULL,
  `lastChange` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `alarmStatus`
--

INSERT INTO `alarmStatus` (`id`, `activeAlarm`, `lastChange`) VALUES
(1, 0, '2016-04-18 19:07:49');

-- --------------------------------------------------------

--
-- Table structure for table `groups`
--

CREATE TABLE IF NOT EXISTS `groups` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `admin` tinyint(1) NOT NULL,
  `history` tinyint(1) NOT NULL,
  `control` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `groups`
--

INSERT INTO `groups` (`id`, `name`, `admin`, `history`, `control`) VALUES
(0, 'observer', 0, 0, 0),
(1, 'admin', 1, 1, 1),
(2, 'users', 0, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `history`
--

CREATE TABLE IF NOT EXISTS `history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user` varchar(32) NOT NULL,
  `message` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `migrations`
--

CREATE TABLE IF NOT EXISTS `migrations` (
  `migration` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `batch` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `migrations`
--

INSERT INTO `migrations` (`migration`, `batch`) VALUES
('2014_10_12_000000_create_users_table', 1),
('2014_10_12_100000_create_password_resets_table', 1);

-- --------------------------------------------------------

--
-- Table structure for table `notifications`
--

CREATE TABLE IF NOT EXISTS `notifications` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` int(11) NOT NULL,
  `type` varchar(32) NOT NULL,
  `token` varchar(100) NOT NULL,
  `active` tinyint(1) NOT NULL,
  `name` varchar(32) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

-- --------------------------------------------------------

--
-- Table structure for table `password_resets`
--

CREATE TABLE IF NOT EXISTS `password_resets` (
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `token` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  KEY `password_resets_email_index` (`email`),
  KEY `password_resets_token_index` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `serverSettings`
--

CREATE TABLE IF NOT EXISTS `serverSettings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Setting` varchar(32) NOT NULL,
  `Value` varchar(128) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Setting` (`Setting`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `serverSettings`
--

INSERT INTO `serverSettings` (`id`, `Setting`, `Value`, `created_at`, `updated_at`) VALUES
(1, 'pendingTime', '10', NULL, '2016-05-13 20:45:15'),
(2, 'notificationInterval', '120', NULL, '2016-05-12 08:46:04'),
(3, 'city', NULL, NULL, '2016-05-12 10:38:33'),
(4, 'countryCode', NULL, NULL, '2016-05-12 10:38:34'),
(5, 'openWeatherMapAPI', NULL, NULL, '2016-05-12 10:38:34'),
(6, 'email_username', NULL, NULL, NULL),
(7, 'email_password', NULL, NULL, NULL),
(8, 'threadPool', '4', NULL, '2016-05-12 10:41:57');

-- --------------------------------------------------------

--
-- Table structure for table `triggers`
--

CREATE TABLE IF NOT EXISTS `triggers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sensor` int(11) NOT NULL,
  `active` tinyint(1) NOT NULL,
  `type` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `group` int(11) NOT NULL DEFAULT '0',
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `remember_token` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `profilePic` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `users_email_unique` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=7 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `group`, `name`, `email`, `password`, `remember_token`, `created_at`, `updated_at`, `profilePic`) VALUES
(6, 1, 'root', 'root@example.com', '$2y$10$qK2sxLQmRyaIJcvVnNp5ceB1DAa8.CQ9CERRGBs6NJpKfg47t12Xm', 'zTAyIy1xFDCPksPjfgYkPGuTAjzzl8QO2rOA7bmGNIlNefS1wuaNukNWXGZX', '2016-05-21 09:51:41', '2016-05-21 09:52:23', NULL);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
