DROP DATABASE IF EXISTS `bookshop`;

CREATE DATABASE `bookshop`
/*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */
/*!80016 DEFAULT ENCRYPTION='N' */;

USE `bookshop`;

CREATE TABLE authors (
book_id INTEGER NOT NULL,
id INTEGER NOT NULL,
firstName VARCHAR(50) NOT NULL,
lastName VARCHAR(50) NOT NULL,
birthdate DATE NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (book_id) REFERENCES books_authors(book_id)
);

CREATE TABLE books (
id INTEGER NOT NULL,
title VARCHAR(60) NOT NULL,
price INTEGER  NOT NULL,
/*author_id VARCHAR(50) NOT NULL,*/
totalPages INTEGER  NOT NULL,
language VARCHAR(20) NOT NULL,
genres INTEGER NOT NULL,
PRIMARY KEY(id),
FOREIGN KEY (author_id) REFERENCES books_authors(author_id)
);

CREATE TABLE genres(
id INTEGER NOT NULL,
genre enum('THRILLER',
    'ADVENTURE',
    'ROMANCE',
    'COMEDY',
    'NON_FICTION',
    'MYSTERY',
    'FANTASY',
    'HISTORICAL',
    'SCI_FI'
),
PRIMARY KEY (id));

/*JOIN TABLES*/

CREATE TABLE books_genres(
book_id INTEGER NOT NULL,
genre_id INTEGER NOT NULL,
FOREIGN KEY (book_id) REFERENCES genre(id),
FOREIGN KEY (book_id) REFERENCES books(id));

CREATE TABLE books_authors(
book_id INTEGER NOT NULL,
author_id INTEGER NOT NULL,
FOREIGN KEY (book_id) REFERENCES books(id),
FOREIGN KEY (author_id) REFERENCES authors(id));

INSERT INTO `authors` (`book_id`, `author_id`, `firstName`, `lastName`, `birthdate`) VALUES (1,'Wall Street','1A','9999','Somewhere', 'Some Country');

INSERT INTO books(`id`, `title`,`price`,`author_id`,`totalPages`,`language`,`genres`) VALUES (1,'Neverwhere',20,'Neil Gaiman',433,'English','ADVENTURE');
INSERT INTO books(`id`, `title`,`price`,`author_id`,`totalPages`,`language`,`genres`) VALUES (2,'Equal Rites',20,'Terry Pratchett',264,'English','FANTASY, COMEDY');
INSERT INTO books(`id`, `title`,`price`,`author_id`,`totalPages`,`language`,`genres`) VALUES (3,'A Chromatic Approach to Jazz Harmony and Melody',40,'David Liebman',244,'English','NON_FICTION');
INSERT INTO books(`id`, `title`,`price`,`author_id`,`totalPages`,`language`,`genres`) VALUES (4,'Letters to a Young Poet',15,'Rainer Maria Rilke',80,'English','NON_FICTION');
INSERT INTO books(`id`, `title`,`price`,`author_id`,`totalPages`,`language`,`genres`) VALUES (5,'One Hundred Years of Solitude',18,'Gabriel Garcia Marquez',417,'English','ADVENTURE');
INSERT INTO books(`id`, `title`,`price`,`author_id`,`totalPages`,`language`,`genres`) VALUES (6,'Killing Commendatore',55,'Haruki Murakami',704,'English','MYSTERY');
INSERT INTO books(`id`, `title`,`price`,`author_id`,`totalPages`,`language`,`genres`) VALUES (7,'Neverwhere',20,'Neil Gaiman',429,'Nederlands','ADVENTURE');
INSERT INTO books(`id`, `title`,`price`,`author_id`,`totalPages`,`language`,`genres`) VALUES (8,'Art of War',20,'Sun Tzu',273,'English','HISTORICAL, NON_FICTION');
INSERT INTO books(`id`, `title`,`price`,`author_id`,`totalPages`,`language`,`genres`) VALUES (9,'The Alexandria Quaret',50,'Lawrence Durrell',884,'English','MYSTERY');
INSERT INTO books(`id`, `title`,`price`,`author_id`,`totalPages`,`language`,`genres`) VALUES (10,'Modern Jazz Voicings',35,'Ted Pease',156,'English','NON_FICTION');
INSERT INTO books(`id`, `title`,`price`,`author_id`,`totalPages`,`language`,`genres`) VALUES (11,'Harmony For a New Millenium',30,'Randy Sandke',90,'English','NON_FICTION');
INSERT INTO books(`id`, `title`,`price`,`author_id`,`totalPages`,`language`,`genres`) VALUES (12,'Naked Lunch',18,'William S. Burroughs',289,'English','THRILLER');
INSERT INTO books(`id`, `title`,`price`,`author_id`,`totalPages`,`language`,`genres`) VALUES (13,'House of Leaves',50,'Mark Z. Danielewski',705,'English','THRILLER');
INSERT INTO books(`id`, `title`,`price`,`author_id`,`totalPages`,`language`,`genres`) VALUES (14,'The Odyssey',15,'Homer',541,'English','HISTORICAL');
INSERT INTO books(`id`, `title`,`price`,`author_id`,`totalPages`,`language`,`genres`) VALUES (15,'Foundation',15,'Isaac Asimov',255,'English','SCI_FI');
INSERT INTO books(`id`, `title`,`price`,`author_id`,`totalPages`,`language`,`genres`) VALUES (16,'Lost Stars',25,'Claudia Gray',551,'English','ROMANCE, SCI_FI');

