create table department (
	department_id int primary key,
	description varchar(50),
	name varchar(25)
);
create table category (
	category_id int primary key,
	department_id int,
	description varchar(50),
	name varchar(25),
	CONSTRAINT fkdepartment FOREIGN KEY(department_id) REFERENCES department(department_id)
);
