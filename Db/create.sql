DROP TABLE IF EXISTS users, book, bookLog, genres, bookGenres, author, authorBooks, example CASCADE;
DROP TYPE IF EXISTS ROLES;

CREATE TYPE ROLES AS ENUM('User', 'Worker');

-- TABLES

CREATE TABLE IF NOT EXISTS author (
	id 			SERIAL PRIMARY KEY,
	firstname 	VARCHAR(80) CHECK (CHAR_LENGTH(firstname) > 1),
	lastname 	VARCHAR(80) CHECK (CHAR_LENGTH(lastname) > 1)
);

CREATE TABLE IF NOT EXISTS users (
    id 			SERIAL PRIMARY KEY, 
    firstname 	VARCHAR(80),
    lastname 	VARCHAR(80),
    userRole 	ROLES NOT NULL DEFAULT 'User'
);

CREATE TABLE IF NOT EXISTS book (
    isbn 		CHAR(40) PRIMARY KEY,
    title 		VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS authorBooks (
	authorId	INT REFERENCES author(id),
	isbn 		CHAR(80) REFERENCES book(isbn)
);

CREATE TABLE IF NOT EXISTS example (
	id 			SERIAL PRIMARY KEY,
	isbn 		CHAR(80) CHECK (CHAR_LENGTH(isbn) >= 13)
);

CREATE TABLE IF NOT EXISTS bookLog (
	id 			SERIAL PRIMARY KEY,
	userId 		INT REFERENCES users(id),
	exampleId	INT REFERENCES example(id),
	dateFrom 	DATE NOT NULL DEFAULT CURRENT_DATE,
	dateDue 	DATE NOT NULL CHECK (dateFrom <= dateDue ),
	dateReturn	DATE
);

CREATE TABLE IF NOT EXISTS genres (
	id 			SERIAL PRIMARY KEY,
	label 		VARCHAR(80),
	description VARCHAR(500)
);

CREATE TABLE IF NOT EXISTS bookGenres (
	isbn 		CHAR(80) NOT NULL REFERENCES book(isbn),
	genreId 	INT NOT NULL REFERENCES genres(id),
	PRIMARY KEY(isbn, genreId)
);

-- VALUES

INSERT INTO author (firstname, lastname) VALUES
	('Vincas', 'Mykolaitis - Putinas'),
	('Justinas', 'Marcinkevičius'),
	('Salomėja', 'Nėris');

INSERT INTO users (firstname, lastname, userRole) VALUES
	('Jokubas', 'Ramanauskas', 'Worker'),
	('Vartotojas', 'Pavarde', DEFAULT);

INSERT INTO book (isbn, title) VALUES
	('123456789-12345', 'Altorių Šešėly'),
	('654987877-45466', 'Mažvydas'),
	('789451545-55456', 'Poema Stalinui');

INSERT INTO authorBooks (authorId, isbn) VALUES
	(1, '123456789-12345'),
	(2, '654987877-45466'),
	(3, '789451545-55456');

INSERT INTO example (isbn) VALUES
	('123456789-12345'),
	('123456789-12345'),
	('123456789-12345'),
	('123456789-12345'),

	('654987877-45466'),
	('654987877-45466'),
	('654987877-45466'),

	('789451545-55456');

INSERT INTO genres (label, description) VALUES
	('Romanas', 'Ilgas grožinis kurinys'),
	('Poema', 'Eiliuotas kurinys'),
	('Drama', 'Dramos rūšies žanras'),
	('Pshicologija', 'Kūrinys turi psichologinių vingių');

INSERT INTO bookGenres (isbn, genreId) VALUES
	('123456789-12345', 1),
	('123456789-12345', 4),

	('654987877-45466', 3),

	('789451545-55456', 2);

INSERT INTO bookLog (userId, exampleId, dateFrom, dateDue, dateReturn) VALUES
	(1, 2, DEFAULT, '2013-12-25', NULL),
	(1, 8, '2013-12-01', '2013-12-25', NULL),
	(2, 5, '2013-11-29', '2013-12-02', '2013-12-02'),
	(2, 6, '2013-11-29', '2013-12-02', '2013-12-02');

-- VIEWS

DROP VIEW IF EXISTS userBooks;

CREATE VIEW userBooks AS
	SELECT 	users.id,
			users.firstname, 
			users.lastname,
			users.userRole AS "role",
			book.isbn,
			book.title,
			CONCAT(author.firstname, ' ', author.lastname) AS autorius,
			bookLog.dateFrom,
			bookLog.dateDue,
			bookLog.dateReturn
	FROM bookLog
	LEFT JOIN users
		ON users.id = bookLog.userId
	LEFT JOIN example AS e
		ON e.id = bookLog.exampleId
	LEFT JOIN book
		ON book.isbn = e.isbn
	LEFT JOIN authorBooks AS ab
		ON ab.isbn = book.isbn
	LEFT JOIN author
		ON ab.authorId = author.id
;

DROP VIEW IF EXISTS popularBooks;

CREATE VIEW popularBooks AS
	SELECT 	book.isbn,
			book.title,
			count(book.isbn) AS "Paimta kartu"
	FROM bookLog
	LEFT JOIN example AS e
		ON e.id = bookLog.exampleId
	LEFT JOIN book
		ON book.isbn = e.isbn
	GROUP BY book.isbn, book.title
	ORDER BY "Paimta kartu" DESC
;

-- INDEXES

DROP INDEX IF EXISTS uniqueGenre, authorFullname, bookTitle;

CREATE UNIQUE INDEX uniqueGenre
	ON genres(label);
CREATE INDEX authorFullname
	ON author(firstname, lastname);
CREATE INDEX bookTitle
	ON book(title);

-- PROCEDURES

CREATE OR REPLACE FUNCTION maxBooksTook()
	RETURNS TRIGGER AS '
		BEGIN			
			IF 5 > (	SELECT COUNT(isbn)
						FROM userBooks
						WHERE NEW.userId = userBooks.id
							AND userBooks.role = ''User''
							AND userBooks.dateReturn IS NULL) THEN
				RAISE EXCEPTION ''User has taken too much books. No more than 5 is allowed.'';
			END IF;
	
			RETURN NEW;
		END;
	'
	LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION allowOnlyOneExampleToTake()
	RETURNS TRIGGER AS '
		BEGIN
			IF EXISTS ( SELECT *
						FROM userBooks
						LEFT JOIN example AS e
							ON NEW.exampleId = e.id
						WHERE NEW.userId = userBooks.id
							AND userBooks.dateReturn IS NULL
							AND e.isbn = userBooks.isbn
							AND userBooks.role = ''User'') THEN
				RAISE EXCEPTION ''User can take only one example of book'';
			END IF;

			RETURN NEW;
		END;
	'
	LANGUAGE plpgsql;

-- TRIGGERS

DROP TRIGGER IF EXISTS maxBooksToTook ON bookLog;
DROP TRIGGER IF EXISTS onlyOneExampleIsAllowed ON bookLog;

CREATE TRIGGER maxBooksToTook
	BEFORE INSERT ON bookLog
	FOR EACH ROW
	EXECUTE PROCEDURE maxBooksTook();

CREATE TRIGGER onlyOneExampleIsAllowed
	BEFORE INSERT ON bookLog
	FOR EACH ROW
	EXECUTE PROCEDURE allowOnlyOneExampleToTake();