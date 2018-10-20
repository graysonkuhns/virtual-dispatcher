# Virtual Dispacher Database Schema
# Last Edit: 10/19/2018 7:54 PM

DROP DATABASE IF EXISTS vd_db;
CREATE DATABASE vd_db;
USE vd_db;

CREATE TABLE pilot (
	pilot_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
	f_name VARCHAR(30) NOT NULL,
	l_name VARCHAR(30) NOT NULL,
	PRIMARY KEY (pilot_id)
);

CREATE TABLE aircraft (
	aircraft_id SMALLINT NOT NULL,
	maintenence BOOL NOT NULL,
	PRIMARY KEY (aircraft_id)
);

CREATE TABLE zones (
	zone_id TINYINT UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (zone_id)
);

CREATE TABLE avaliability (
	pilot_id SMALLINT UNSIGNED NOT NULL UNIQUE,
	time_avaliable TIME NOT NULL,
	PRIMARY KEY (pilot_id)
)

CREATE TABLE flights (
	flight_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	completed BOOL NOT NULL,
	aircraft_id SMALLINT NOT NULL,
	PRIMARY KEY (flight_id)
);

# ADD FOREIGN KEY CONSTRAINTS

ALTER TABLE avaliability
	ADD FOREIGN KEY (pilot_id)
	REFERENCES pilot (pilot_id)
	ON DELETE CASCADE;

ALTER TABLE flights
	ADD FOREIGN KEY (aircraft_id),
	REFERENCES aircraft (aircraft_id),
	ON DELETE NO ACTION;