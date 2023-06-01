BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role,tier) VALUES ('user1','user1','ROLE_USER',"STANDARD");
INSERT INTO users (username,password_hash,role,tier) VALUES ('user2','user2','ROLE_USER',"STANDARD");
INSERT INTO users (username,password_hash,role,tier) VALUES ('user3','user3','ROLE_USER','STANDARD');

COMMIT TRANSACTION;
