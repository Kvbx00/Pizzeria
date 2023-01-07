-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 07 Sty 2023, 09:34
-- Wersja serwera: 10.4.27-MariaDB
-- Wersja PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `food`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `admin`
--

CREATE TABLE `admin` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Zrzut danych tabeli `admin`
--

INSERT INTO `admin` (`username`, `password`) VALUES
('admin', 'zxc');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `cart`
--

CREATE TABLE `cart` (
  `id` int(11) NOT NULL,
  `price` float NOT NULL,
  `product_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `customer`
--

CREATE TABLE `customer` (
  `email` varchar(255) NOT NULL,
  `contact` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Zrzut danych tabeli `customer`
--

INSERT INTO `customer` (`email`, `contact`, `name`, `password`) VALUES
('zxc@gmail.com', 123123123, 'zxc', 'zxc');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `category` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Zrzut danych tabeli `product`
--

INSERT INTO `product` (`id`, `category`, `description`, `name`, `price`) VALUES
(91343, 'Pizza', 'Sos pomidorowy, ser mozzarella', 'Margherita', 25);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `purchase`
--

CREATE TABLE `purchase` (
  `id` int(11) NOT NULL,
  `dop` date DEFAULT NULL,
  `productid` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `quantity` int(11) NOT NULL,
  `totalcost` float NOT NULL,
  `customer_email` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Zrzut danych tabeli `purchase`
--

INSERT INTO `purchase` (`id`, `dop`, `productid`, `name`, `quantity`, `totalcost`, `customer_email`) VALUES
(147794, '2023-01-05', 91343, 'Margherita', 2, 50, 'zxc@gmail.com'),
(520027, '2023-01-05', 91343, 'Margherita', 1, 25, 'zxc@gmail.com');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`username`);

--
-- Indeksy dla tabeli `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`email`);

--
-- Indeksy dla tabeli `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `purchase`
--
ALTER TABLE `purchase`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKo7gdhk6inpwo1ktafs0q3wy2a` (`customer_email`);

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `purchase`
--
ALTER TABLE `purchase`
  ADD CONSTRAINT `FKo7gdhk6inpwo1ktafs0q3wy2a` FOREIGN KEY (`customer_email`) REFERENCES `customer` (`email`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
