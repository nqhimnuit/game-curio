insert into developer (name)
values ('Rocksteady Studios'),
	   ('Feral Interactive (Mac)'),
	   ('Frozenbyte'),
	   ('Image & Form Games');

insert into publisher (name)
values ('Warner Bros. Interactive Entertainment'),
	   ('Feral Interactive (Mac)'),
	   ('Frozenbyte'),
	   ('Image & Form Games');

insert into game (title, release_date, description, price)
values ('Batman: Arkham Asylum Game of the Year Edition', date('2010-03-27'),
		concat('Experience what it’s like to be Batman and face off against Gotham''s greatest',
			   'villians. Explore every inch of Arkham Asylum and roam freely on the infamous island.'),
		188000),
	   ('Batman: Arkham City - Game of the Year Edition', date('2012-09-08'),
		'Get Batman: Arkham City and all DLC for one low price with the release of the GOTY Edition!',
		188000),
	   ('Batman™: Arkham Knight', date('2015-06-25'),
		concat('Batman™: Arkham Knight brings the award-winning Arkham trilogy from Rocksteady',
			   ' Studios to its epic conclusion. Developed exclusively for New-Gen platforms,',
			   ' Batman: Arkham Knight introduces Rocksteady''s uniquely designed version',
			   ' of the Batmobile.'),
		188000),
	   ('Trine Enchanted Edition', date('2009-07-02'),
		concat('Three Heroes make their way through dangers untold in a fairytale world of great',
			   'castles and strange machinery, featuring physics-based puzzles,',
			   'beautiful sights and online co-op.'), 165000),
	   ('SteamWorld Heist', date('2016-06-07'),
		concat('Command a steam-driven pirate crew in a series of epic tactical shootouts in',
			   ' SteamWorld Heist. This is turn-based strategy with a twist: You manually aim the',
			   ' guns of your robots, allowing for insane skill shots and bullet-bouncing action!'),
		165000);

insert into dlc (name, game_id, price)
values ('Batman™: Arkham Knight Season Pass', 3, 188000),
	   ('Batman™: Arkham Knight - A Matter of Family', 3, 90000),
	   ('Batman™: Arkham Knight - Catwoman''s Revenge', 3, 30000),
	   ('SteamWorld Heist: The Outsider', 5, 70000),
	   ('Music from SteamWorld Heist - Steam Powered Giraffe', 5, 120000),
	   ('Hatbox: Three 4 Free (SteamWorld Heist)', 5, 0),
	   ('Hatbox: Hatful Eight + 2 (SteamWorld Heist)', 5, 43000);

insert into game_dev_ref (game_id, developer_id)
values (1, 1),
	   (2, 1),
	   (2, 2),
	   (3, 1),
	   (4, 3),
	   (5, 4);

insert into game_pub_ref (game_id, publisher_id)
values (1, 1),
	   (2, 1),
	   (2, 2),
	   (3, 1),
	   (4, 3),
	   (5, 4);
