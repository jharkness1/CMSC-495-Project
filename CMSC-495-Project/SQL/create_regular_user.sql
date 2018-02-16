-- change database !
use umuc;

-- CREATE REGULAR USER!

-- below statement will insert into profiles table new user:
-- required fields are: 
-- firstname
-- lastname
-- email (unique!)
-- username (unique!)
-- password (at least 8 characters long!)
-- below statement will insert a row in profiles table
-- and a row in roles table with the same id and role of regular user: 'user'!
INSERT INTO profiles (firstname, lastname, email, username, password)
VALUES ('Jane', 'Doe', 'jdoe@umuc.edu', 'jdoe', '12345678');

-- you can manually verify roles for all users:
SELECT profiles.id, profiles.firstname, profiles.lastname, profiles.username, roles.role 
FROM profiles
INNER JOIN roles ON profiles.id=roles.id;