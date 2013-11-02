DROP TABLE IF EXISTS users, book, bookLog, genres, bookGenres;

CREATE TYPE ROLES AS ENUM('USER', 'WORKER');

CREATE TABLE IF NOT EXISTS users (
    id serial PRIMARY KEY, 
    firstname VARCHAR(80),
    lastname VARCHAR(80),
    password character varying(80),
    userRole ROLES
);

CREATE TABLE IF NOT EXISTS book (
    id serial PRIMARY KEY, 
    isbn VARCHAR(40),
    title VARCHAR(255),
    author VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS bookLog (
	id serial PRIMARY KEY,
	userId INT references users(id),
	bookId INT references book(id),
	dateBegin DATE,
	dateEnd DATE
);

CREATE TABLE IF NOT EXISTS genres (
	id serial PRIMARY KEY,
	name VARCHAR(80)
);

CREATE TABLE IF NOT EXISTS bookGenres (
	bookId INT NOT NULL,
	genreId INT NOT NULL,
	PRIMARY KEY(bookId, genreId)
);