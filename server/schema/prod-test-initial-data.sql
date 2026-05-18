use crag_database_prod_test_env;

insert into location (Country, Region, description) values
	('United States', 'Wisconsin', 'A heavily forested landscape nesled in the glacial valley which gave way to the unique types of rock formations and compositions such as baraboo quartzite which is only found in the near the small town of baraboo'),
	('United States', 'Minnesota', 'Despite not being well know for its climbing, Minnesota has some of the greatest diversity of rock types in the country. Minnesota also has deep roots in climbings history being home to one of, if not the, first commercial climbing gym in the country and many big names accross of parts of the climbing industry hailing from this land of 10,000 lakes');


insert into user(email , password , role) values
	('a@a.com', 'a' , 'USER'),
	('b@b.com', 'b' , 'USER');

insert ignore into user(email , password , role) values
	('b@b.com', 'b' , 'USER')

insert ignore into user(email , password , role) values
	('c@c.com', 'c' , 'USER')


select * from user;

delete from user u where u.id = 6;





























