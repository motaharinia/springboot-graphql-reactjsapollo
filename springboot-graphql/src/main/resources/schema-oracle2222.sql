-- to execute ddl like create tables
-- SELECT * FROM dba_tables where table_name = 'table_name';
-- DECLARE
--    CNT INT;
-- BEGIN
--    SELECT COUNT(*) INTO CNT
--      FROM USER_TABLES
--      WHERE TABLE_NAME = 'MY_TABLE' ^;
--    IF CNT < 1 THEN
--       EXECUTE IMMEDIATE 'CREATE TABLE MY_TABLE (f1 VARCHAR(256), f2 BLOB)';
--    END IF ^;
-- END ^;

-- CREATE PROCEDURE Alter_Table()
-- BEGIN
--  IF NOT EXISTS( SELECT NULL
--             FROM INFORMATION_SCHEMA.COLUMNS
--            WHERE table_name = 'test_table'
--              AND table_schema = 'test'
--              AND column_name = 'cc_test_id')  THEN
--
--   alter table test_table add cc_test_id VARCHAR(128) NOT NULL;
--
-- END IF;
-- END ^;

-- call Alter_Table ^;

-- create table oauth_client_details (
--   client_id VARCHAR(256) PRIMARY KEY,
--   resource_ids VARCHAR(256),
--   client_secret VARCHAR(256),
--   scope VARCHAR(256),
--   authorized_grant_types VARCHAR(256),
--   web_server_redirect_uri VARCHAR(256),
--   authorities VARCHAR(256),
--   access_token_validity INTEGER,
--   refresh_token_validity INTEGER,
--   additional_information VARCHAR(4000),
--   autoapprove VARCHAR(256)
-- );
--
-- create table oauth_client_token (
--   token_id VARCHAR(256),
--   token BLOB,
--   authentication_id VARCHAR(256) PRIMARY KEY,
--   user_name VARCHAR(256),
--   client_id VARCHAR(256)
-- );
--
-- create table oauth_access_token (
--   token_id VARCHAR(256),
--   token BLOB,
--   authentication_id VARCHAR(256) PRIMARY KEY,
--   user_name VARCHAR(256),
--   client_id VARCHAR(256),
--   authentication BLOB,
--   refresh_token VARCHAR(256)
-- );
--
-- create table oauth_refresh_token (
--   token_id VARCHAR(256),
--   token BLOB,
--   authentication BLOB
-- );
--
-- create table oauth_code (
--   code VARCHAR(256), authentication BLOB
-- );
--
-- create table oauth_approvals (
-- 	userId VARCHAR(256),
-- 	clientId VARCHAR(256),
-- 	scope VARCHAR(256),
-- 	status VARCHAR(10),
-- 	expiresAt TIMESTAMP,
-- 	lastModifiedAt TIMESTAMP
-- );
--
-- create table ClientDetails (
--   appId VARCHAR(256) PRIMARY KEY,
--   resourceIds VARCHAR(256),
--   appSecret VARCHAR(256),
--   scope VARCHAR(256),
--   grantTypes VARCHAR(256),
--   redirectUrl VARCHAR(256),
--   authorities VARCHAR(256),
--   access_token_validity INTEGER,
--   refresh_token_validity INTEGER,
--   additionalInformation VARCHAR(4000),
--   autoApproveScopes VARCHAR(256)
-- );
