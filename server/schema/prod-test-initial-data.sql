use crag_database_prod_test_env;

insert into location (Country, Region, description) values
	('United States', 'Wisconsin', 'A heavily forested landscape nesled in the glacial valley which gave way to the unique types of rock formations and compositions such as baraboo quartzite which is only found in the near the small town of baraboo'),
	('United States', 'Minnesota', 'Despite not being well know for its climbing, Minnesota has some of the greatest diversity of rock types in the country. Minnesota also has deep roots in climbings history being home to one of, if not the, first commercial climbing gym in the country and many big names accross of parts of the climbing industry hailing from this land of 10,000 lakes');


insert into crag (name, location_id , description) values
	('Devil\'s Lake', 1 , 'lots of talus which leads to lots of compression sytle blocks'),
	('Taylor\'s Falls', 2 , 'go to area for climbers in the Twin Cities in Minnesota. Not a lot not very high quality but its close with good enough density of climbs');

insert into area(name , super_area_id , crag_id , description) values
	('East Bluff', null , 1 , 'east bluff of the lake. Access through north or south shore entraces, south can be handy on busy days with lots of swimmers as they will all be on the north shore.'),
	('West Bluff', null , 1 , 'west bluff of the lake'),
	('Cave boulder' , null , 2 , 'most popular sport at TFalls, very polished after years of mn climbers cutting their teeth on these classics');

insert into route(name , area_id , description , start_position) values 
	('Dagger of the lake' , 1 , 'super classic and one of my personal projects' , 'start compressing on a low undercling and slopey pod');





























