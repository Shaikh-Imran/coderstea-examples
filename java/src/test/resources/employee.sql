DROP ALL OBJECTS;
CREATE SCHEMA IF NOT EXISTS db;

CREATE TABLE employee (
    id int,
    name varchar(250)
);

INSERT INTO employee (id, name) VALUES (1, 'Imran');
INSERT INTO employee (id, name) VALUES (2, 'Mahesh');