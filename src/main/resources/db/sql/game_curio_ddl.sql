create table game
(
	id           int primary key auto_increment,
	title        varchar(100) not null,
	release_date date,
	description  varchar(1000),
	price        float
);

create table developer
(
	id   int primary key auto_increment,
	name varchar(100) not null
);

create table publisher
(
	id   int primary key auto_increment,
	name varchar(100) not null
);

create table dlc
(
	id      int primary key auto_increment,
	name    varchar(100) not null,
	game_id int not null,
	price   float,

	constraint fk_dlc_game foreign key (game_id) references game (id)
);

create table game_dev_ref
(
	game_id      int,
	developer_id int,

	constraint fk_ref_game_dev_game foreign key (game_id) references game (id),
	constraint fk_ref_game_dev_dev foreign key (developer_id) references developer (id)
);

create table game_pub_ref
(
	game_id      int,
	publisher_id int,

	constraint fk_ref_game_pub_game foreign key (game_id) references game (id),
	constraint fk_ref_game_pub_pub foreign key (publisher_id) references publisher (id)
);
