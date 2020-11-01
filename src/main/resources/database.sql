DROP TABLE Event;
DROP TABLE Account;

-- Create User
CREATE TABLE Account(
    emailUser VARCHAR(255) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL
);

-- Create Event
CREATE TABLE Event(
    idEvent INT,
    owner VARCHAR(255) REFERENCES Account(emailUser),
    nameEvent VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    PRIMARY KEY (idEvent, owner)
);
