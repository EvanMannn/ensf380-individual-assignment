/*
 Each time this file is executed, it will reset the database to the original state defined below.
 You can import this directly in your database by (a) manually entering the first three lines of
 commands form this file, (b) removing the first three lines of commands from this file, and
 (c) \i 'path_to_file\project.sql' (with appropriate use of \ or / based on OS).
 
 During grading, TAs will assume that these two tables exist, but will enter different values.
 Thus you cannot assume that any of the values provided here exist, but you can assume the tables
 exist.
 
 You may optionally create additional tables in the ensf380project database with demonstration 
 data, provided that you provide the information in a valid SQL file which TAs can import and
 clearly include this information in your instructions.
 */
DROP DATABASE IF EXISTS ensf380project;
CREATE DATABASE ensf380project;
\c ensf380project 

CREATE TABLE INQUIRER (
    id SERIAL PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    phoneNumber VARCHAR(20) NOT NULL,
    info VARCHAR(25) NOT NULL
);
INSERT INTO INQUIRER (firstName, lastName, phoneNumber, info)
VALUES ('Dominik', 'Pflug', '123-456-9831', 'placeholder info'),
    ('Yaa', 'Odei', '123-456-8913', 'placeholder info'),
    ('Cecilia', 'Cobos', '123-456-7891', 'placeholder info'),
    ('Hongjoo', 'Park', '123-456-8912', 'placeholder info'),
    ('Adien', 'Smith', '123-476-2387', 'placeholder info'),
    ('Sam', 'Doe', '353-456-2363', 'placeholder info');
CREATE TABLE INQUIRY_LOG (
    id SERIAL PRIMARY KEY,
    inquirer int NOT NULL,
    callDate DATE NOT NULL,
    details VARCHAR(500) NOT NULL,
    foreign key (inquirer) references INQUIRER(id) ON UPDATE CASCADE
);
INSERT INTO INQUIRY_LOG (inquirer, callDate, details)
VALUES (1, '2024-02-28', 'Theresa Pflug'),
    (2, '2024-02-28', 'Offer to assist as volunteer'),
    (3, '2024-03-01', 'Valesk Souza'),
    (1, '2024-03-01', 'Theresa Pflug'),
    (1, '2024-03-02', 'Theresa Pflug'),
    (4, '2024-03-02', 'Yoyo Jefferson and Roisin Fitzgerald'),
    (5, '2024-03-02', 'Henk Wouters'),
    (3, '2024-03-03', 'Melinda'),
    (6, '2024-03-04', 'Julius');

DROP USER IF EXISTS oop;
CREATE USER oop WITH PASSWORD 'ucalgary';
GRANT ALL PRIVILEGES ON TABLE INQUIRER, INQUIRY_LOG TO oop;
GRANT USAGE, SELECT ON SEQUENCE inquirer_id_seq TO oop;
GRANT USAGE, SELECT ON SEQUENCE inquiry_log_id_seq TO oop;
