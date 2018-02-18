-- change database !
use umuc;

-- CREATE ADMIN USER!

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
VALUES ('Jonh', 'Administrator', 'jadmin@umuc.edu', 'admin', '12345678');

-- below statement will change privileges for user with username 'admin'
-- the user with username 'admin' will have 'admin' role in the application
UPDATE roles, profiles
SET role='admin'
WHERE roles.id=(SELECT id FROM profiles WHERE username='admin');

-- you can manually verify roles for all users:
SELECT profiles.id, profiles.firstname, profiles.lastname, profiles.username, roles.role 
FROM profiles
INNER JOIN roles ON profiles.id=roles.id;

-- change password for a user based on username
UPDATE profiles SET password='87654321'
WHERE username='admin';