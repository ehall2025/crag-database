use crag_database_prod_test_env;

insert into user (email, password, role) values
	('jim_merli@rockmaster.com', '$2a$12$01igN71/i6pFe7rTDSSGaulCR36jWBrOeAqt7dlFBluw2e5hYugwq', 'ROLE_ADMIN'),
	('tommy@4fingers.com', '$2a$12$S2Uu.lenW46InFyq6.dJ2ubt1Xsdwe8iVfXSCzRkA1V6tZCccU6.G', 'ROLE_USER'),
	('user2@user.com', '$2a$12$S2Uu.lenW46InFyq6.dJ2ubt1Xsdwe8iVfXSCzRkA1V6tZCccU6.G', 'ROLE_USER');

insert into location (Country, Region, description) values
	('United States', 'Wisconsin', 'A heavily forested landscape nesled in the glacial valley which gave way to the unique types of rock formations and compositions such as baraboo quartzite which is only found in the near the small town of baraboo'),
	('United States', 'Minnesota', 'Despite not being well know for its climbing, Minnesota has some of the greatest diversity of rock types in the country. Minnesota also has deep roots in climbings history being home to one of, if not the, first commercial climbing gym in the country and many big names accross of parts of the climbing industry hailing from this land of 10,000 lakes'),
	('United States', 'Colorado', ''),
	('United States', 'Texas', '');

insert into crag (name, location_id , description) values
	('Devil\'s Lake', 1 , 'lots of talus which leads to lots of compression sytle blocks'),
	('Governer Dodge State Park', 1, ''),
	('Dresser', 1, ''),
	('Taylor\'s Falls', 2 , 'go to area for climbers in the Twin Cities in Minnesota. Not a lot not very high quality but its close with good enough density of climbs');

insert into area(name , super_area_id , crag_id , description) values
	('East Bluff', null , 1 , 'east bluff of the lake. Access through north or south shore entraces, south can be handy on busy days with lots of swimmers as they will all be on the north shore.'),
	('East Talus North', 1, null, 'Large Talus field on the North end of the lake. Access through furthest parking lot on the North shore.'),
	('Monolith Area', 1, null, 'Small hideaway nested at the top of the Balanced Rock Trail.'),
	('CCC trail', 1, null, 'Fun hike up the talus with lots of classics just off the trail. Must sees are The Flatiron, Railgun, and Fat Pants.'),
	('West Bluff', null , 1 , 'west bluff of the lake'),
	('Cave boulder' , null , 2 , 'most popular spot at Taylor\' Falls, very polished after years of mn climbers cutting their teeth on these classics');

insert into route(name , area_id , description , start_position) values 
	('Dagger of the lake' , 2 , 'super classic and one of my personal projects' , 'start compressing on a low undercling and slopey pod'),
	('Super Slab' , 5 , '25\' beauty of a climb' , 'start standing on holds in the middle of the face'),
	('Alpine Club', 5, '', ''),
	('Moonlit Water', 2, '', ''),
	('Sunny and 60s', 2, '', '');

insert into route_staging(name , area_id , description , start_position) values 
	('Panic Room', 1, 'Traverse into Bunker Arete and FLY!', 'Start on the large pedestal in bottom left of the boulder.'),
	('Ghostly Grips', 2, 'Quite dabby but fun slopers!', 'start on good ledges in the pit');

insert into list (name , user_id) values
	('todo' , 1),
	('ticks' , 1),
	('todo' , 2),
	('ticks' , 2),
	('todo' , 3),
	('ticks' , 3);

insert into list_route (list_id , route_id) values
	(3, 3),
	(4, 2),
	(4, 4),
	(4, 5);



























