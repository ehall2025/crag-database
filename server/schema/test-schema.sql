DROP DATABASE IF EXISTS crag_database_test;
CREATE DATABASE crag_database_test;
USE crag_database_test;

CREATE TABLE User (
	id int AUTO_INCREMENT primary key,
	displayName text,
	email text NOT NULL,
	password text NOT NULL,
	role text NOT NULL,
	location_id int,
	list_id int NOT NULL
);

CREATE TABLE Route (
	id int AUTO_INCREMENT PRIMARY KEY,
	name int NOT NULL,
	area_id int NOT NULL,
	difficulty double,
	danger_level_id int,
	quality int,
	is_classic int NOT null default false,
	height int,
	discipline_id int,
	fa_name text,
	fa_date date,
	description text NOT NULL,
	start_position text NOT NULL,
	protection text,
	directions text
);

CREATE TABLE Discipline (
	id int AUTO_INCREMENT PRIMARY KEY,
	name text NOT NULL
);

CREATE TABLE Area (
	id int AUTO_INCREMENT PRIMARY KEY,
	name int NOT NULL,
	super_area_id int,
	crag_id int,
	description text NOT NULL
);

CREATE TABLE Comment (
	id int AUTO_INCREMENT PRIMARY KEY,
	content text NOT NULL,
	user_id int NOT NULL,
	route_id int NOT NULL,
	parent_comment_id int NOT NULL
);

CREATE TABLE Crag (
	id int AUTO_INCREMENT PRIMARY KEY,
	name text NOT NULL,
	location_id int NOT NULL,
	desciption text
);

CREATE TABLE Location (
	id int AUTO_INCREMENT PRIMARY KEY,
	Country text NOT NULL,
	Region text,
	description text
);

CREATE TABLE List (
	id int AUTO_INCREMENT PRIMARY KEY,
	name text NOT NULL
);

CREATE TABLE List_Route (
	list_id int NOT NULL,
	route_id int NOT NULL
);

CREATE TABLE Danger_level (
	id int AUTO_INCREMENT PRIMARY KEY,
	name text NOT NULL
);


ALTER TABLE User ADD CONSTRAINT User_fk5 FOREIGN KEY (location_id) REFERENCES Location(id);
ALTER TABLE User ADD CONSTRAINT User_fk6 FOREIGN KEY (list_id) REFERENCES List(id);

ALTER TABLE Route ADD CONSTRAINT Route_fk2 FOREIGN KEY (area_id) REFERENCES Area(id);
ALTER TABLE Route ADD CONSTRAINT Route_fk4 FOREIGN KEY (danger_level_id) REFERENCES Danger_level(id);
ALTER TABLE Route ADD CONSTRAINT Route_fk8 FOREIGN KEY (discipline_id) REFERENCES Discipline(id);

ALTER TABLE Area ADD CONSTRAINT Area_fk2 FOREIGN KEY (super_area_id) REFERENCES Area(id);
ALTER TABLE Area ADD CONSTRAINT Area_fk3 FOREIGN KEY (crag_id) REFERENCES Crag(id);

ALTER TABLE Comment ADD CONSTRAINT comment_fk2 FOREIGN KEY (user_id) REFERENCES User(id);
ALTER TABLE Comment ADD CONSTRAINT comment_fk3 FOREIGN KEY (route_id) REFERENCES Route(id);
ALTER TABLE Comment ADD CONSTRAINT comment_fk4 FOREIGN KEY (parent_comment_id) REFERENCES Comment(id);

ALTER TABLE Crag ADD CONSTRAINT Crag_fk2 FOREIGN KEY (location_id) REFERENCES Location(id);

ALTER TABLE List_Route ADD CONSTRAINT List_Route_fk0 FOREIGN KEY (list_id) REFERENCES List(id);
ALTER TABLE List_Route ADD CONSTRAINT List_Route_fk1 FOREIGN KEY (route_id) REFERENCES Route(id);






















