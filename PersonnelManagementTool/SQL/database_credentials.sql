-- application's database: umuc
-- application's database user: umuc
-- password for umuc user: myUmucPr0j3ct

-- to grant umuc user who has myUmucPr0j3ct password, to connect from everywhere(%),
-- login to the database as root user and paste the following:
GRANT ALL ON *.* to umuc@'%' IDENTIFIED BY 'myUmucPr0j3ct'; 

-- log in as new database user (umuc)
-- and create database dedicated to the "Personnel Management" application
CREATE DATABASE umuc;