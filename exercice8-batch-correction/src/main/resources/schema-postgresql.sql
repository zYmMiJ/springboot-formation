-- creation des tables

create table if not exists student (
	id Bigserial primary key,
	first_name varchar(25),
	last_name varchar(25),
	birth_date date,
	score int
);