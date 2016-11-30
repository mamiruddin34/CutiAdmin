-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 30, 2016 at 04:11 PM
-- Server version: 10.1.16-MariaDB
-- PHP Version: 7.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `admincuti`
--

-- --------------------------------------------------------

--
-- Table structure for table `cutiumum`
--

CREATE TABLE `cutiumum` (
  `CutiUmumID` int(11) NOT NULL,
  `tarikhCuti` date NOT NULL,
  `kampus` varchar(30) NOT NULL,
  `keterangan` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `daftarcuti`
--

CREATE TABLE `daftarcuti` (
  `cutiUmumID` int(11) NOT NULL,
  `tarikhCuti` date NOT NULL,
  `kampus` int(2) NOT NULL,
  `keterangan` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `daftarcuti`
--

INSERT INTO `daftarcuti` (`cutiUmumID`, `tarikhCuti`, `kampus`, `keterangan`) VALUES
(17, '2016-12-25', 3, 'xmas'),
(18, '2017-01-01', 2, 'New Year');

-- --------------------------------------------------------

--
-- Table structure for table `mohoncutiumum`
--

CREATE TABLE `mohoncutiumum` (
  `id` int(11) NOT NULL,
  `tarikhMula` date NOT NULL,
  `tarikhTamat` date NOT NULL,
  `tarikhMohon` date NOT NULL,
  `bilanganCuti` int(2) DEFAULT NULL,
  `catatan` varchar(50) NOT NULL,
  `alamatCuti` varchar(50) NOT NULL,
  `id_sokonglulus` int(11) NOT NULL,
  `status` int(2) NOT NULL DEFAULT '0' COMMENT '0 == BARU | 1 == DISOKONG | 2 == DILULUS | 3 == TIDAK SOKONG | 4 == TIDAK LULUS',
  `sebabTidakSokong` varchar(50) DEFAULT NULL,
  `sebabTidakLulus` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mohoncutiumum`
--

INSERT INTO `mohoncutiumum` (`id`, `tarikhMula`, `tarikhTamat`, `tarikhMohon`, `bilanganCuti`, `catatan`, `alamatCuti`, `id_sokonglulus`, `status`, `sebabTidakSokong`, `sebabTidakLulus`) VALUES
(10, '2016-11-30', '2016-11-03', '2016-11-27', 3, 'Cuti', 'Rumah', 1, 0, NULL, NULL),
(101, '2016-12-01', '2016-11-08', '2016-11-29', 7, 'Cuti Penat Kerja', 'Desaru', 2, 4, NULL, 'Mengarut'),
(102, '2016-11-09', '2016-11-10', '2016-11-08', 1, 'tyutyu', 'tyutyu', 7, 0, NULL, NULL),
(103, '2016-11-09', '2016-11-10', '2016-11-08', 1, 'tyutyu', 'tyutyu', 7, 2, NULL, NULL),
(104, '2016-11-09', '2016-11-10', '2016-11-08', 1, 'tyutyu', 'tyutyu', 98, 3, NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cutiumum`
--
ALTER TABLE `cutiumum`
  ADD PRIMARY KEY (`CutiUmumID`);

--
-- Indexes for table `daftarcuti`
--
ALTER TABLE `daftarcuti`
  ADD PRIMARY KEY (`cutiUmumID`);

--
-- Indexes for table `mohoncutiumum`
--
ALTER TABLE `mohoncutiumum`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cutiumum`
--
ALTER TABLE `cutiumum`
  MODIFY `CutiUmumID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `daftarcuti`
--
ALTER TABLE `daftarcuti`
  MODIFY `cutiUmumID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT for table `mohoncutiumum`
--
ALTER TABLE `mohoncutiumum`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=105;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
