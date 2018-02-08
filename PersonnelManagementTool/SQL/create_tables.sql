-- change database !
use umuc;

-- drop tables if they exist
DROP TABLE roles;
DROP TABLE profiles;

-- create table with client's profile
CREATE TABLE profiles (
id INT NOT NULL AUTO_INCREMENT,
firstname VARCHAR(30) NOT NULL,
lastname VARCHAR(30) NOT NULL,
email VARCHAR(30) UNIQUE NOT NULL,
company VARCHAR(30),
department VARCHAR(30),
title VARCHAR(30),
work_address VARCHAR(50),
work_city VARCHAR(30),
work_state VARCHAR(2),
work_zip VARCHAR(5),
phone VARCHAR(15),
username VARCHAR(30) UNIQUE NOT NULL,
password VARCHAR(30) NOT NULL,
failed_login_attempts INT NOT NULL DEFAULT 0,
last_failed_login DATETIME,
PRIMARY KEY (id)
);

-- create table with roles
CREATE TABLE roles (
id INT NOT NULL,
role VARCHAR(10) NOT NULL DEFAULT 'user',
FOREIGN KEY (id)
REFERENCES profiles (id) ON DELETE CASCADE
);

-- create a trigger, so that after each insert to profiles table,
-- new row in roles table will be created 
-- (sequence id will match primary key in profiles table)
CREATE TRIGGER profile_ai AFTER INSERT ON profiles
FOR EACH ROW INSERT INTO roles (id) VALUES (NEW.id);

