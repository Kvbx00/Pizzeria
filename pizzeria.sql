-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 22 Sty 2023, 12:29
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
-- Struktura tabeli dla tabeli `orderdetails`
--

CREATE TABLE `orderdetails` (
  `id` int(11) NOT NULL,
  `productid` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `quantity` int(11) NOT NULL,
  `totalcost` float NOT NULL,
  `order_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `orderdetails`
--

INSERT INTO `orderdetails` (`id`, `productid`, `name`, `quantity`, `totalcost`, `order_id`) VALUES
(21, 17497, 'NAPOLETANA', 2, 48, 439619),
(22, 60265, 'HAWAJSKA', 1, 29, 439619),
(23, 17497, 'NAPOLETANA', 1, 24, 541785),
(24, 76528, 'MARGHERITA', 2, 44, 541785),
(25, 79261, 'CAPRICCIOSA', 3, 96, 541785),
(27, 79261, 'CAPRICCIOSA', 1, 32, 407508),
(28, 76528, 'MARGHERITA', 3, 66, 407508),
(29, 77801, 'GRECO', 2, 60, 101993),
(30, 82833, 'SALAMELLA', 1, 29, 101993),
(31, 17497, 'NAPOLETANA', 1, 24, 669382),
(32, 28604, 'SALAME DOLCE', 1, 28, 669382),
(33, 90459, 'MESSICANA', 2, 64, 543320),
(34, 89862, 'MARINARA', 1, 23, 543320),
(35, 17497, 'NAPOLETANA', 2, 48, 516712),
(36, 79261, 'CAPRICCIOSA', 3, 96, 516712),
(37, 76528, 'MARGHERITA', 3, 66, 516712);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `date` date NOT NULL,
  `price` float NOT NULL,
  `customer_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `orders`
--

INSERT INTO `orders` (`id`, `date`, `price`, `customer_id`) VALUES
(101993, '2023-01-22', 89, 3),
(407508, '2023-01-22', 98, 1),
(439619, '2023-01-22', 77, 1),
(516712, '2023-01-22', 210, 3),
(541785, '2023-01-22', 164, 1),
(543320, '2023-01-22', 87, 3),
(669382, '2023-01-22', 52, 3);

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
-- Indeksy dla tabeli `orderdetails`
--
ALTER TABLE `orderdetails`
  ADD PRIMARY KEY (`id`),
  ADD KEY `order_id` (`order_id`);

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
-- AUTO_INCREMENT dla tabeli `orderdetails`
--
ALTER TABLE `orderdetails`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT dla tabeli `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=992834;

--
-- AUTO_INCREMENT dla tabeli `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=90460;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `orderdetails`
--
ALTER TABLE `orderdetails`
  ADD CONSTRAINT `orderdetails_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

--
-- Ograniczenia dla tabeli `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
