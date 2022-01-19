-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 19 Jan 2022 pada 17.31
-- Versi server: 10.4.22-MariaDB
-- Versi PHP: 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbbukuid`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_buku`
--

CREATE TABLE `tbl_buku` (
  `id` int(11) NOT NULL,
  `judul` varchar(50) NOT NULL,
  `penulis` text NOT NULL,
  `harga` varchar(20) NOT NULL,
  `detail` varchar(360) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tbl_buku`
--

INSERT INTO `tbl_buku` (`id`, `judul`, `penulis`, `harga`, `detail`) VALUES
(2, 'Seni Perang', 'Sun Zi', '65.000', '\"The Art Of War\" atau \"Seni Perang Sun Zi\" adalah sebuah buku filsafat militer yang diperkirakan ditulis pada abad ke-6 SM oleh Sun Zi. Terdiri dari 13 bab di mana setiap bagian membahas strategi dan berbagai metode perang.'),
(3, 'Madilog', 'Tan Malaka', '125.000', 'Madilog oleh Iljas Hussein, pertama kali diterbitkan pada tahun 1943, edisi pertama resmi tahun 1951, adalah magnum opus dari Tan Malaka, pahlawan nasional Indonesia dan merupakan karya paling berpengaruh dalam sejarah filsafat Indonesia modern.'),
(4, 'Aku Bersaksi Tiada Perempuan Selain Engkau', 'Nizar Qabbani', '68.000', 'Dalam karya ini, kepiawaian Musyfiqur Rahman benar-benar tampak dengan kuat. Santri Annuqayah ini bukan sekadar mampu menerjemahkan secara akurat karya Nizar Qabbani, tapi sekaligus juga bisa mengusung suasana batin sang penyair ke dalam alam Indonesia.'),
(5, 'Rich Dad Poor Dad', 'Robert T. Kiyosaki', '75.000', 'Rich Dad Poor Dad adalah buku tahun 1997 karya Robert Kiyosaki dan Sharon Lechter. Rich Dad, Poor Dad adalah buku yang membahas masalah finansial yang dihadapi banyak orang dikarenakan ajaran keliru orang tua mereka mengenai keuangan, yang juga dialaminya semasa kecil dan remaja.'),
(6, 'Mati Tidak Harus Sakit, Jal Tidak Harus Tua', 'Riska Wati Harfin', '75.000', 'Napas adalah bagian dari waktu yang berlalu pergi dan tak kembali. Setiap detik dan menitnya, waktu menjadi saksi terhadap setiap ibadah serta amalan yang kita kerjakan. Besar ataupun kecil, segalanya akan memperoleh balasan dari-Nya. ');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `tbl_buku`
--
ALTER TABLE `tbl_buku`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `tbl_buku`
--
ALTER TABLE `tbl_buku`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
