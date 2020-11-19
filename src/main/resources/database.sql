CREATE TABLE IF NOT EXISTS Location(
	coordinates varchar(50) PRIMARY KEY,
	roadName varchar(25) NOT NULL,
	complement varchar(25),
	postalCode integer UNIQUE NOT NULL,
	cityName varchar(25) UNIQUE NOT NULL,
	countryName varchar(15) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS Account(
    email varchar(40) PRIMARY KEY,
    name varchar(25) NOT NULL,
    firstName varchar(25) NOT NULL,
    password varchar NOT NULL,
    salt VARCHAR NOT NULL,
    coordinates varchar(50) references Location(coordinates)
);

CREATE TABLE IF NOT EXISTS Event (
	eventID SERIAL PRIMARY KEY,
	EvnetName varchar(20) NOT NULL,
	content varchar(255),
	email varchar(50) references Account(email),
	coordinates varchar(50) references Location(coordinates)
);

CREATE TABLE IF NOT EXISTS Category (
	categoryName varchar(25) PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS Creator (
	email varchar(40) references Account(email),
	eventID SERIAL references Event(eventID),
	PRIMARY KEY(email, eventID)
);

CREATE TABLE IF NOT EXISTS Contributor(
	email varchar(40) references Account(email),
	eventID SERIAL references Event(eventID),
	PRIMARY KEY(email, eventID)
);

CREATE TABLE IF NOT EXISTS Interest (
	email varchar(40) references Account(email),
	categoryName varchar(25) references Category(categoryName),
	PRIMARY KEY(email, categoryName)
);

CREATE TABLE IF NOT EXISTS SortedEvent (
	eventID SERIAL references Event(eventID),
	categoryName varchar(25) references Category(categoryName),
	PRIMARY KEY(eventID, categoryName)
);
