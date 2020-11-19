CREATE TABLE IF NOT EXISTS student (
    id BigSerial primary key,
    first_name varchar(25),
    last_name varchar(25),
    score int
);