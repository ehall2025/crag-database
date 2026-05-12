DROP IF database EXISTS 'crag-database'
CREATE database 'crag-database'
use database 'crag-database';

CREATE TABLE User (
	`id` int AUTO_INCREMENT NOT NULL UNIQUE,
	`displayName` text NOT NULL,
	`email` text NOT NULL,
	`password` text NOT NULL,
	`role` text NOT NULL,
	`location_id` int NOT NULL,
	`list_id` int NOT NULL,
	PRIMARY KEY (`id`)
);
CREATE TABLE Climb (
	`id` int AUTO_INCREMENT NOT NULL UNIQUE,
	`name` int NOT NULL,
	`area_id` int NOT NULL,
	`difficulty` int NOT NULL,
	`danger_level_id` int,
	`quality` int,
	`is_classic` int NOT NULL,
	`height` int,
	`discipline_id` int NOT NULL,
	`fa_name` text NOT NULL,
	`fa_date` date NOT NULL,
	`description` int NOT NULL,
	`start_position` int NOT NULL,
	`protection` int NOT NULL,
	`directions` int NOT NULL,
	PRIMARY KEY (`id`)
);
CREATE TABLE Discipline (
	`id` int AUTO_INCREMENT NOT NULL UNIQUE,
	`name` int NOT NULL,
	PRIMARY KEY (`id`)
);
CREATE TABLE Area (
	`id` int AUTO_INCREMENT NOT NULL UNIQUE,
	`name` int NOT NULL,
	`super_area_id` int NOT NULL,
	`crag_id` int NOT NULL,
	`description` text NOT NULL,
	PRIMARY KEY (`id`)
);
CREATE TABLE Comment (
	`id` int AUTO_INCREMENT NOT NULL UNIQUE,
	`content` int NOT NULL,
	`user_id` int NOT NULL,
	`climb_id` int NOT NULL,
	`parent_comment_id` int NOT NULL,
	PRIMARY KEY (`id`)
);
CREATE TABLE Crag (
	`id` int AUTO_INCREMENT NOT NULL UNIQUE,
	`name` int NOT NULL,
	`location_id` int NOT NULL,
	`desciption` int NOT NULL,
	PRIMARY KEY (`id`)
);
CREATE TABLE Location (
	`id` int AUTO_INCREMENT NOT NULL UNIQUE,
	`Country` int NOT NULL,
	`Region` int NOT NULL,
	`description` int NOT NULL,
	PRIMARY KEY (`id`)
);
CREATE TABLE List (
	`id` int AUTO_INCREMENT NOT NULL UNIQUE,
	`name` int NOT NULL,
	PRIMARY KEY (`id`)
);
CREATE TABLE List_Climb (
	`list_id` int NOT NULL,
	`climb_id` int NOT NULL
);
CREATE TABLE Danger_level (
	`id` int AUTO_INCREMENT NOT NULL UNIQUE,
	`name` int NOT NULL,
	PRIMARY KEY (`id`)
);


ALTER TABLE User ADD CONSTRAINT `User_fk5` FOREIGN KEY (`location_id`) REFERENCES Location(`id`);
ALTER TABLE User ADD CONSTRAINT `User_fk6` FOREIGN KEY (`list_id`) REFERENCES List(`id`);

ALTER TABLE Climb ADD CONSTRAINT `Climb_fk2` FOREIGN KEY (`area_id`) REFERENCES Area(`id`);
ALTER TABLE Climb ADD CONSTRAINT `Climb_fk4` FOREIGN KEY (`danger_level_id`) REFERENCES Danger_level(`id`);
ALTER TABLE Climb ADD CONSTRAINT `Climb_fk8` FOREIGN KEY (`discipline_id`) REFERENCES Discipline(`id`);

ALTER TABLE Area ADD CONSTRAINT `Area_fk2` FOREIGN KEY (`super_area_id`) REFERENCES Area(`id`);
ALTER TABLE Area ADD CONSTRAINT `Area_fk3` FOREIGN KEY (`crag_id`) REFERENCES Crag(`id`);

ALTER TABLE Comment ADD CONSTRAINT `comment_fk2` FOREIGN KEY (`user_id`) REFERENCES User(`id`);
ALTER TABLE Comment ADD CONSTRAINT `comment_fk3` FOREIGN KEY (`climb_id`) REFERENCES Climb(`id`);
ALTER TABLE Comment ADD CONSTRAINT `comment_fk4` FOREIGN KEY (`parent_comment_id`) REFERENCES Comment(`id`);

ALTER TABLE Crag ADD CONSTRAINT `Crag_fk2` FOREIGN KEY (`location_id`) REFERENCES Location(`id`);

ALTER TABLE List_Climb ADD CONSTRAINT `List_Climb_fk0` FOREIGN KEY (`list_id`) REFERENCES List(`id`);
ALTER TABLE List_Climb ADD CONSTRAINT `List_Climb_fk1` FOREIGN KEY (`climb_id`) REFERENCES Climb(`id`);






















