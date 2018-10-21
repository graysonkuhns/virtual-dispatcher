# Virtual Dispacher Database Schema
# Last Edit: 10/19/2018 7:54 PM

#######################################################################
## Create database schema
#######################################################################

DROP DATABASE IF EXISTS vd_db;
CREATE DATABASE vd_db;
USE vd_db;

# Create the service account and grant it admin permissions on the database
CREATE OR REPLACE USER `dispatcher`@`%` IDENTIFIED BY 'dispatcher';
GRANT ALL PRIVILEGES ON vd_db.* TO `dispatcher`@`%` WITH GRANT OPTION;

CREATE TABLE pilot (
	id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
	f_name VARCHAR(30) NOT NULL,
	l_name VARCHAR(30) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE aircraft (
	id SMALLINT NOT NULL AUTO_INCREMENT,
	operational BOOLEAN NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE zones (
	id TINYINT UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (id)
);

CREATE TABLE availability (
	pilot_id SMALLINT UNSIGNED NOT NULL UNIQUE,
	created TIMESTAMP NOT NULL,
	PRIMARY KEY (pilot_id)
);

CREATE TABLE flights (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	completed BOOLEAN NOT NULL,
	started BOOLEAN NOT NULL,
	pilot_id SMALLINT UNSIGNED NOT NULL,
	aircraft_id SMALLINT NOT NULL,
	zone_id TINYINT UNSIGNED NOT NULL,
	PRIMARY KEY (id)
);

# ADD FOREIGN KEY CONSTRAINTS

ALTER TABLE availability
	ADD FOREIGN KEY (pilot_id)
	REFERENCES pilot (id)
	ON DELETE CASCADE;

ALTER TABLE flights
	ADD FOREIGN KEY (aircraft_id)
	REFERENCES aircraft (id)
	ON DELETE NO ACTION;

#######################################################################
## Populate database
#######################################################################

INSERT INTO pilot (f_name, l_name)
	VALUES
	  ("Buck", "Sommerkamp"),
	  ("Brian", "Faros"),
	  ("Vaani", "Ranganathan"),
	  ("Marge", "Sendze"),
	  ("Alex", "LeBlanc"),
	  ("Bill", "Davis"),
	  ("Bryan", "Danaher"),
	  ("Devan", "Barger"),
	  ("Gina", "Donnelly"),
	  ("Omar", "Alzubbi"),
	  ("Tyler", "Robinson"),
	  ("Denise", "Bruce"),
	  ("Mitchell", "Jurich"),
	  ("Jerome", "Tujague"),
	  ("Grayson", "Kuhns"),
	  ("Jon", "Bockhorst"),
	  ("Morgan", "Benton"),
	  ("Connor", "Marchand"),
	  ("Belinda", "Copus"),
	  ("Neal", "Patterson"),
	  ("Eric", "Benton"),
	  ("Cami", "Kuhns");
		
INSERT INTO aircraft (operational)
	VALUES
		(true),
		(false),
		(true),
		(true),
		(true),
		(true),
		(true),
		(true);
	
INSERT INTO zones
	VALUES
		(),
		(),
		(),
		(),
		(),
		();