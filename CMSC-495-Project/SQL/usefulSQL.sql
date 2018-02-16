-- use umuc! if you connect manually
use umuc;

-- during login attempt, retrieve by given username
-- user's information required for authentication:
-- id, username, password, failed_login_attempts
-- last_failed_login and role
SELECT profiles.id, profiles.username, profiles.password, 
profiles.failed_login_attempts, profiles.last_failed_login,
roles.role
FROM 
profiles
INNER JOIN roles ON profiles.id=roles.id
WHERE profiles.username='admin';

-- before inserting new user, check:
-- how many rows in the profiles table
-- are there with given username or email
SELECT COUNT(*) FROM profiles
WHERE username='x' 
OR email='x@umuc.edu';

-- create new user account
INSERT INTO profiles (firstname, lastname, email, 
	company, department, title, work_address, 
	work_city, work_state, work_zip, phone, 
	username, password)
VALUES ('a', 'b', 'ab@umuc.edu', '','','','',
	'','MD','','','ab','11111111');

-- search user by last name
SELECT id, lastname, firstname, email, department 
FROM profiles 
WHERE lastname='Doe';

-- search user by department
SELECT id, lastname, firstname, email, department 
FROM profiles 
WHERE department='IT';

-- list all users
SELECT id, lastname, firstname, email, department 
FROM profiles;

-- access profile by id
SELECT id, firstname, lastname, email, company, 
	department, title, work_address, work_city, 
	work_state, work_zip, phone, username, password 
FROM profiles 
WHERE id=1;

-- delete user by id
DELETE FROM profiles
WHERE id=2;

-- update user by id
UPDATE profiles
SET firstname='New Firstname', lastname='New Lastname',
email='newemail@umuc.edu', company='New Company',
department='New Department', title='Better Job Title',
work_address='New Street 111', work_city='Different City',
work_state='NS', work_zip='22222', phone='2222223333',
username='nusername', password='qqqqqqqq' 
WHERE id=4;
