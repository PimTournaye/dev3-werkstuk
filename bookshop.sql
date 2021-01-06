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
FOREIGN KEY (book_id) REFERENCES books(id)
);

CREATE TABLE books (
id INTEGER NOT NULL,
title VARCHAR(60) NOT NULL,
price INTEGER  NOT NULL,
author_id VARCHAR(50) NOT NULL,
totalPages INTEGER  NOT NULL,
lang VARCHAR(20) NOT NULL,
genres ENUM('THRILLER',
           'ADVENTURE',
           'ROMANCE',
           'COMEDY',
           'NON_FICTION',
           'MYSTERY',
           'FANTASY',
           'HISTORICAL',
           'SCI_FI') NOT NULL,
PRIMARY KEY(id),
FOREIGN KEY (author_id) REFERENCES authors(id)
/*FOREIGN KEY (author_id) REFERENCES books_authors(author_id)*/
);



INSERT INTO `authors` (`book_id`, `id`, `firstName`, `lastName`, `birthdate`) VALUES (1, 1, 'Neil','Gaiman',1995-11-3);
INSERT INTO `authors` (`book_id`, `id`, `firstName`, `lastName`, `birthdate`) VALUES  (2,2, 'Terry', "Pratchett", 1970-3-25);
INSERT INTO `authors` (`book_id`, `id`, `firstName`, `lastName`, `birthdate`) VALUES (3,3,"David", "Liebman", 1946-6-7);

INSERT INTO books(`id`, `title`,`price`,`author_id`,`totalPages`,`lang`,`genres`) VALUES (1,'Neverwhere',20 ,1 ,433,'English','ADVENTURE');
INSERT INTO books(`id`, `title`,`price`,`author_id`,`totalPages`,`lang`,`genres`) VALUES (2,'Equal Rites',20 ,2 ,264,'English','COMEDY');
INSERT INTO books(`id`, `title`,`price`,`author_id`,`totalPages`,`lang`,`genres`) VALUES (3,'A Chromatic Approach to Jazz Harmony and Melody',40 ,3 ,244,'English','NON_FICTION');

INSERT INTO books(`id`, `title`,`price`,`author_id`,`totalPages`,`lang`,`genres`) VALUES (4,'Neverwhere',20,1,433,'English','ADVENTURE');

