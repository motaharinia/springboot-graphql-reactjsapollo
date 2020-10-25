-- to execute dml like insert into table

-- MERGE INTO adminuser u USING (SELECT username FROM adminuser WHERE username ='superadmin@micser.com') e ON (u.username = e.username) WHEN NOT MATCHED THEN INSERT (u.username, u.password, u.firstname,u.lastname) VALUES ('superadmin@micser.com', '123456789', 'superFirstName','superLastName')



-- INSERT INTO adminuser (username, password, firstname,lastname) VALUES ('superadmin@micser.com', '123456789', 'superFirstName','superLastName');

-- INSERT INTO oauth_client_details
-- 	(client_id, client_secret, scope, authorized_grant_types,
-- 	web_server_redirect_uri, authorities, access_token_validity,
-- 	refresh_token_validity, additional_information, autoapprove)
-- VALUES
-- 	('web-client', '{bcrypt}$2a$10$gPhlXZfms0EpNHX0.HHptOhoFD1AoxSr/yUIdTqA8vtjeP4zi0DDu', 'READ,WRITE, TRUST',
-- 	'authorization_code,password,refresh_token,implicit', null, 'ROLE_CLIENT, ROLE_TRUSTED_CLIENT', 60*60*1, 24*60*60*1, null, 'true');

-- INSERT INTO MY_TABLE (f1) VALUES ('superadmin@micser.com');
