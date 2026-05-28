DROP DATABASE IF EXISTS crag_database_test;
CREATE DATABASE crag_database_test;
USE crag_database_test;

CREATE TABLE User (
	id int AUTO_INCREMENT primary key,
	displayName text,
	email varchar(255) unique not NULL,
	password text NOT NULL,
	role text NOT NULL,
	location_id int
);

CREATE TABLE Route (
	id int AUTO_INCREMENT PRIMARY KEY,
	name text NOT NULL,
	area_id int NOT NULL,
	difficulty int,
	danger_level int,
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

CREATE TABLE Route_Staging (
	id int AUTO_INCREMENT PRIMARY KEY,
	name text NOT NULL,
	area_id int NOT NULL,
	description text NOT NULL,
	start_position text NOT NULL
);

create table Route_Summary (
	id int auto_increment primary key,
	route_id int not null,
	user_id int not null,
	difficulty int,
	quality int,
	danger_level int
);

CREATE TABLE Discipline (
	id int AUTO_INCREMENT PRIMARY KEY,
	name text NOT NULL
);

CREATE TABLE Area (
	id int AUTO_INCREMENT PRIMARY KEY,
	name text NOT NULL,
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
	description text
);

CREATE TABLE Location (
	id int AUTO_INCREMENT PRIMARY KEY,
	Country text NOT NULL,
	Region text,
	description text
);

CREATE TABLE List (
	id int AUTO_INCREMENT PRIMARY KEY,
	name text NOT NULL,
	user_id int NOT NULL
);

CREATE TABLE List_Route (
	list_id int NOT NULL,
	route_id int NOT NULL
);

ALTER TABLE User ADD CONSTRAINT User_fk5 FOREIGN KEY (location_id) REFERENCES Location(id);

ALTER TABLE Route ADD CONSTRAINT Route_fk2 FOREIGN KEY (area_id) REFERENCES Area(id);
ALTER TABLE Route ADD CONSTRAINT Route_fk8 FOREIGN KEY (discipline_id) REFERENCES Discipline(id);

ALTER TABLE Route_Summary ADD CONSTRAINT route_summary_fk2 FOREIGN KEY (user_id) REFERENCES User(id);
ALTER TABLE Route_Summary ADD CONSTRAINT route_summary_fk3 FOREIGN KEY (route_id) REFERENCES Route(id);

ALTER TABLE Area ADD CONSTRAINT Area_fk2 FOREIGN KEY (super_area_id) REFERENCES Area(id);
ALTER TABLE Area ADD CONSTRAINT Area_fk3 FOREIGN KEY (crag_id) REFERENCES Crag(id);

ALTER TABLE Comment ADD CONSTRAINT comment_fk2 FOREIGN KEY (user_id) REFERENCES User(id);
ALTER TABLE Comment ADD CONSTRAINT comment_fk3 FOREIGN KEY (route_id) REFERENCES Route(id);
ALTER TABLE Comment ADD CONSTRAINT comment_fk4 FOREIGN KEY (parent_comment_id) REFERENCES Comment(id);

ALTER TABLE Crag ADD CONSTRAINT Crag_fk2 FOREIGN KEY (location_id) REFERENCES Location(id);

ALTER TABLE List ADD CONSTRAINT List_fk1 FOREIGN KEY (user_id) REFERENCES User(id);

ALTER TABLE List_Route ADD CONSTRAINT List_Route_fk0 FOREIGN KEY (list_id) REFERENCES List(id);
ALTER TABLE List_Route ADD CONSTRAINT List_Route_fk1 FOREIGN KEY (route_id) REFERENCES Route(id);

-- -- -- -- -- --
--  Test data  --
-- -- -- -- -- --

delimiter //
create procedure set_known_good_state()
begin
	delete from list_route;
    delete from list;
    delete from user;
    alter table user auto_increment = 1;

    delete from location;
    alter table location auto_increment = 1;

    insert into location (Country, Region, description) values
	('United States', 'Wisconsin', 'A heavily forested landscape nesled in the glacial valley which gave way to the unique types of rock formations and compositions such as baraboo quartzite which is only found in the near the small town of baraboo'),
	('United States', 'Minnesota', 'Despite not being well know for its climbing, Minnesota has some of the greatest diversity of rock types in the country. Minnesota also has deep roots in climbings history being home to one of, if not the, first commercial climbing gym in the country and many big names accross of parts of the climbing industry hailing from this land of 10,000 lakes');

    insert into user (email, password, role) values
    ('jim_merli@rockmaster.com', '$2a$12$01igN71/i6pFe7rTDSSGaulCR36jWBrOeAqt7dlFBluw2e5hYugwq', 'ROLE_ADMIN'),
    ('tommy@4fingers.com', '$2a$12$S2Uu.lenW46InFyq6.dJ2ubt1Xsdwe8iVfXSCzRkA1V6tZCccU6.G', 'ROLE_USER'),
    ('user2@user.com', '$2a$12$S2Uu.lenW46InFyq6.dJ2ubt1Xsdwe8iVfXSCzRkA1V6tZCccU6.G', 'ROLE_USER');

    insert into list (name, user_id) values
    ('todo', 1), ('ticks', 1),
    ('todo', 2), ('ticks', 2),
    ('todo', 3), ('ticks', 3);

    insert into location (Country, Region, description) values
        ('United States', 'Wisconsin', ''),
        ('United States', 'Minnesota', '');

    insert into crag (name, location_id , description) values
        ('Devil\'s Lake', 1 , ''),
        ('Taylor\'s Falls', 2 , '');

    insert into area(name , super_area_id , crag_id , description) values
        ('East Bluff', null , 1 , ''),
        ('East Talus North', 1, null, ''),
        ('Cave boulder' , null , 2 , '');

    insert into route(name , area_id , description , start_position) values
        ('Dagger of the lake' , 2 , '' , ''),
        ('Oxygen  Cocktail', 4, '', '');

    insert into route_staging(name , area_id , description , start_position) values
        ('Panic Room', 2, '', ''),
        ('Ghostly Grips', 2, '', '');

end //
delimiter ;

























