-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 14 Sty 2023, 20:13
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
-- Baza danych: `pizzeria`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `admin`
--

CREATE TABLE `admin` (
  `email` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Zrzut danych tabeli `admin`
--

INSERT INTO `admin` (`email`, `password`) VALUES
('admin', 'admin');

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
  `id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `contact` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `customer`
--

INSERT INTO `customer` (`id`, `email`, `contact`, `name`, `password`, `address`) VALUES
(1, 'zxc@gmail.com', 123123123, 'zxc', 'Zxcvbnm8*', 'Sosnowiec, nowa 14/5'),
(3, 'qwe@gmail.com', 883001015, 'qwe', 'Qwertyui8*', 'Sosnowiec, paryska 35');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `date` date NOT NULL,
  `productid` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `quantity` int(11) NOT NULL,
  `totalcost` float NOT NULL,
  `customer_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `orders`
--

INSERT INTO `orders` (`id`, `date`, `productid`, `name`, `quantity`, `totalcost`, `customer_id`) VALUES
(129787, '2023-01-14', 28604, 'SALAME DOLCE', 1, 28, 1),
(267869, '2023-01-14', 77801, 'GRECO', 1, 30, 3),
(306259, '2023-01-14', 76528, 'MARGHERITA', 2, 44, 1),
(379754, '2023-01-14', 89862, 'MARINARA', 2, 44, 1),
(391705, '2023-01-14', 79261, 'CAPRICCIOSA', 1, 32, 1),
(457533, '2023-01-14', 17497, 'NAPOLETANA', 1, 24, 1),
(490587, '2023-01-14', 28604, 'SALAME DOLCE', 2, 56, 3),
(556499, '2023-01-14', 90459, 'MESSICANA', 1, 32, 1),
(822993, '2023-01-14', 77801, 'GRECO', 3, 90, 1);

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
(17497, 'pizza', '(SOS POMIDOROWY, MOZZARELLA, ANCHOIS, KAPARY, OREGANO, BAZYLIA, OLIVA)', 'NAPOLETANA', 24),
(28604, 'pizza', '(SOS POMIDOROWY, MOZZARELLA, SALAMI)', 'SALAME DOLCE', 28),
(60265, 'pizza', '(SOS POMIDOROWY, MOZZARELLA, KURCZAK, ANANAS, KUKURYDZA)', 'HAWAJSKA', 29),
(76528, 'pizza', '(SOS POMIDOROWY, MOZZARELLA, BAZYLIA, OLIVA)', 'MARGHERITA', 22),
(77801, 'pizza', '(SOS POMIDOROWY, OREGANO, OLIWKI, SUSZONE POMIDORY, CEBULKA KARMELIZOWANA, RUCOLA, SER KOZI)', 'GRECO', 30),
(79261, 'pizza', '(SOS POMIDOROWY, MOZZARELLA, PIECZARKI, KARCZOCHY, OLIWKI, PROSCIUTTO COTTO, BAZYLIA, OLIVA)', 'CAPRICCIOSA', 32),
(82833, 'pizza', '(SOS POMIDOROWY, MOZZARELLA, KIEŁBASKA WIEPRZOWA, PIECZARKI, CEBULA CZERWONA, PANCETTA, OLIVA)', 'SALAMELLA', 29),
(89862, 'pizza', '(SOS POMIDOROWY, OREGANO, CZOSNEK, BAZYLIA, OLIVA)', 'MARINARA', 23),
(90459, 'pizza', '(SOS POMIDOROWY, MOZZARELLA, KIEŁBASKA WIEPRZOWA, SPIANATA PICCANTE NDUJA, PAPRYKA, FASOLKA CZERWONA, OLIVA)', 'MESSICANA', 32);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`email`);

--
-- Indeksy dla tabeli `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `customer_id` (`customer_id`) USING BTREE;

--
-- Indeksy dla tabeli `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT dla zrzuconych tabel
--

--
-- AUTO_INCREMENT dla tabeli `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT dla tabeli `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=822994;

--
-- AUTO_INCREMENT dla tabeli `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=90460;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
