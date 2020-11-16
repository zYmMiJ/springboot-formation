-- Script SQL du cours SpringBoot JPA
-- 1- creation des 5 tables
create table event (
	event_id serial primary key,
	title varchar(20),
	description varchar(50),
	begin_date date,
	all_day boolean
);

create table address (
	address_id serial primary key,
	name varchar(20),
	street varchar(20),
	comments varchar(50),
	zip_code varchar(5),
	city varchar(20)
);
-- 2- creation de la clef etrangere 
alter table event add column address serial;
alter table event add foreign key (address) references address(address_id);

create table users (
	users_id serial primary key,
	login varchar(25),
	pass varchar(25),
	email varchar(50)
);

alter table event add column users serial;
alter table event add foreign key (users) references users(users_id);

create table item (
	item_id serial primary key,
	name varchar(20),
	needed_quantity integer,
	current_quantity integer
);

alter table item add column event serial;
alter table item add foreign key (event) references event(event_id);

create table guest (
	guest_id serial primary key,
	name varchar(20),
	email varchar(50)
);

-- 3- creation de la table charniere
create table guest_event (
	fk_event serial,
	fk_guest serial,
	foreign key (fk_event) references event(event_id),
	foreign key (fk_guest) references guest(guest_id),
	primary key (fk_event, fk_guest)
);
-- 4- placer les contraintes
alter table item add constraint needed_quantity check(needed_quantity >= 0);
alter table item add constraint current_quantity CHECK(current_quantity <= needed_quantity);
alter table event add constraint begin_date check(begin_date > current_date);

-- 5- inserer des donnees dans les tables
alter table address alter column street type varchar(50);
insert into address (name, street, comments, zip_code, city) values ('Pyramides', 'avenue du General De Gaulle', 'centre de formation', '94010', 'Creteil');
insert into users (login, pass, email) values ('deblouze', 'agathe', 'ad@formation.fr');
insert into event (title, description, begin_date, all_day, address, users) values ('cours BD', 'le cours le plus important en persistance', '2021-10-30',
																				   false,
																				   (select address_id from address where name = 'Pyramides' limit 1),
																				   (select users_id from users where login = 'deblouze')
																					);
insert into guest (name, email) values ('dupuis', 'samuel.dupuis@formation.fr'), ('linais', 'stephane.linais@formation.fr'), ('nejjari', 'hamid.nejjari@formation.fr');
select * from guest;
insert into guest_event (fk_event, fk_guest) values (
		(select event_id from event where title = 'cours BD'),
		(select guest_id from guest where name = 'dupuis')
	), (
		(select event_id from event where title = 'cours BD'),
		(select guest_id from guest where name = 'linais')
	), (
		(select event_id from event where title = 'cours BD'),
		(select guest_id from guest where name = 'nejjari')
	);
