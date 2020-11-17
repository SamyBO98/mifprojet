DROP TABLE IF EXISTS Location, User_, Event, Category, Creator, Contributor, Interest, SortedEvent,  LocalizedEvent, LocalizedUser;

CREATE TABLE Location(
	coordinates varchar (50) PRIMARY KEY,
	roadName varchar (20) NOT NULL,
	complement varchar (20),
	postalCode integer UNIQUE NOT NULL,
	cityName varchar (15) UNIQUE NOT NULL,
	countryName varchar (10) UNIQUE NOT NULL
);

CREATE TABLE User_(
	email varchar (20) PRIMARY KEY,
	UserName varchar (20) NOT NULL,
	UserFirstName varchar (20) NOT NULL,
	coordinates varchar (20) references Location (coordinates)
);

CREATE TABLE Event (
	eventID SERIAL PRIMARY KEY,
	EvnetName varchar (20) NOT NULL,
	content varchar (100),
	email varchar (20) references User_(email),
	coordinates varchar (20) references Location (coordinates)
);

CREATE TABLE Category (
	categoryName varchar (20) PRIMARY KEY
);

CREATE TABLE Creator (
	email varchar (20) references User_(email),
	eventID SERIAL references Event(eventID),
	PRIMARY KEY (email, eventID)
);

CREATE TABLE Contributor(
	email varchar (20) references User_ (email),
	eventID SERIAL references Event (eventID),
	PRIMARY KEY (email, eventID)
);

CREATE TABLE Interest (
	email varchar (20) references User_ (email),
	categoryName varchar (20) references Category (categoryName),
	PRIMARY KEY (email, categoryName)
);

CREATE TABLE SortedEvent (
	eventID SERIAL references Event(eventID),
	categoryName varchar (20) references Category (categoryName),
	PRIMARY KEY (eventID, categoryName)
);

