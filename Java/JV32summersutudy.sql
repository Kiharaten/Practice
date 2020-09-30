-- phpMyAdmin SQL Dump
-- version 4.9.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Sep 02, 2020 at 06:44 AM
-- Server version: 5.7.26
-- PHP Version: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `practice09`
--

-- --------------------------------------------------------

--
-- Table structure for table `actor`
--

CREATE TABLE `actor` (
  `id` int(255) NOT NULL,
  `name` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `actor`
--

INSERT INTO `actor` (`id`, `name`) VALUES
(0, '木原'),
(1, '神農'),
(2, '中村'),
(3, '佐藤'),
(4, 'TEST'),
(5, '工藤'),
(6, '七竈'),
(7, '中居'),
(8, '岡本'),
(9, '中西'),
(10, '山口'),
(11, '岡崎'),
(12, 'スティーブ'),
(13, 'yamayu'),
(14, '斎藤工');

-- --------------------------------------------------------

--
-- Table structure for table `disk`
--

CREATE TABLE `disk` (
  `id` int(255) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `actor` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `disk`
--

INSERT INTO `disk` (`id`, `name`, `genre`, `actor`) VALUES
(1, 'STAR WARS', 'SF', '木原,神農'),
(2, 'AKIRA', 'SF', '中村,佐藤'),
(3, 'HARRY POTTER', 'FANTASY', '木原,中村'),
(4, 'ALICE IN WONDERLAND', 'FANTASY', '木原,佐藤'),
(5, 'PROMARE', 'ANIMATION', '神農,中村'),
(6, 'SUMMER WARS', 'ANIMATION,ROMANCE', '神農,佐藤'),
(7, 'となりのトトロ', 'ANIMATION', '木原,神農'),
(8, '千と千尋の神隠し', 'ANIMATION', '神農'),
(9, 'あたしンち', 'FANTASY', '岡本'),
(10, '銀魂2', 'アクション', '斎藤工'),
(11, 'ターミネーター', 'ロボット', '斎藤工'),
(12, 'コナン', 'アクション', '斎藤工');

-- --------------------------------------------------------

--
-- Table structure for table `friend`
--

CREATE TABLE `friend` (
  `id` int(255) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `mail` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `friend`
--

INSERT INTO `friend` (`id`, `name`, `mail`) VALUES
(1, 'KIHARA', 'kihara@hoge.com'),
(2, 'TANAKA', 'tanaka@hoge.com'),
(3, 'NAKAYAMA', 'nakayama@hoge.com'),
(4, 'NAKAMURA', 'nakamura@hoge.com'),
(5, 'HUKUDA', 'hukuda@hoge.com'),
(6, 'NANAKAMADO', 'nanakamado@hoge.com'),
(7, '大島達郎', 'ohshima@hoge.com'),
(8, '山田孝之', 'yamadatakayuki@hoge.com'),
(9, 'ジョン小林', 'jonkoba@hoge.com'),
(10, 'やまちゅう', 'yamatyu@hoge.com');

-- --------------------------------------------------------

--
-- Table structure for table `genre`
--

CREATE TABLE `genre` (
  `id` int(255) NOT NULL,
  `name` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `genre`
--

INSERT INTO `genre` (`id`, `name`) VALUES
(0, 'SF'),
(1, 'FANTASY'),
(2, 'ANIMATION'),
(3, 'ROMANCE'),
(4, 'アクション'),
(5, '化学'),
(6, 'ドキュメンタリー'),
(7, 'スリルアクション'),
(8, 'ロボット');

-- --------------------------------------------------------

--
-- Table structure for table `rental`
--

CREATE TABLE `rental` (
  `id` int(255) NOT NULL,
  `number` int(255) NOT NULL,
  `date` datetime DEFAULT NULL,
  `disk_id` int(255) NOT NULL,
  `friend_id` int(255) NOT NULL,
  `status_id` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `rental`
--

INSERT INTO `rental` (`id`, `number`, `date`, `disk_id`, `friend_id`, `status_id`) VALUES
(1, 1, '2020-01-01 00:00:00', 1, 1, 0),
(2, 1, '2020-01-01 00:00:00', 2, 1, 0),
(3, 1, '2020-01-01 00:00:00', 3, 1, 0),
(4, 1, '2020-01-01 00:00:00', 4, 1, 0),
(5, 1, '2020-01-01 00:00:00', 5, 1, 0),
(6, 2, '2020-09-01 11:58:30', 1, 1, 0),
(7, 2, '2020-09-01 11:58:30', 2, 1, 0),
(8, 3, '2020-09-01 12:19:30', 3, 3, 0),
(9, 4, '2020-09-01 12:19:40', 4, 1, 1),
(10, 4, '2020-09-01 12:19:40', 5, 1, 1),
(11, 5, '2020-09-01 12:20:01', 1, 7, 1),
(12, 6, '2020-09-01 12:56:50', 2, 6, 1),
(13, 7, '2020-09-01 15:51:30', 11, 9, 0),
(14, 8, '2020-09-01 15:59:00', 12, 10, 0),
(15, 8, '2020-09-01 15:59:00', 9, 10, 0),
(16, 8, '2020-09-01 15:59:00', 7, 10, 0),
(17, 9, '2020-09-01 16:09:01', 12, 9, 1),
(18, 9, '2020-09-01 16:09:01', 7, 9, 1);

-- --------------------------------------------------------

--
-- Table structure for table `status`
--

CREATE TABLE `status` (
  `id` int(1) NOT NULL,
  `name` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `status`
--

INSERT INTO `status` (`id`, `name`) VALUES
(0, '返却済'),
(1, '貸出中');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `disk`
--
ALTER TABLE `disk`
  ADD KEY `id_index` (`id`);

--
-- Indexes for table `friend`
--
ALTER TABLE `friend`
  ADD KEY `id_index` (`id`);

--
-- Indexes for table `rental`
--
ALTER TABLE `rental`
  ADD KEY `disk_id` (`disk_id`),
  ADD KEY `friend_id` (`friend_id`),
  ADD KEY `status_id` (`status_id`);

--
-- Indexes for table `status`
--
ALTER TABLE `status`
  ADD KEY `id_index` (`id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `rental`
--
ALTER TABLE `rental`
  ADD CONSTRAINT `rental_ibfk_1` FOREIGN KEY (`disk_id`) REFERENCES `disk` (`id`),
  ADD CONSTRAINT `rental_ibfk_2` FOREIGN KEY (`friend_id`) REFERENCES `friend` (`id`),
  ADD CONSTRAINT `rental_ibfk_3` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`);