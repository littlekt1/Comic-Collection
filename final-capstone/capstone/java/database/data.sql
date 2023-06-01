BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role,tier) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'STANDARD');
INSERT INTO users (username,password_hash,role,tier) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN', 'PREMIUM');

INSERT INTO collections (collection_name,comics_in_collection,owner_id,visible) VALUES ('test1', ARRAY[0,1,2,3], 1, true);
INSERT INTO collections (collection_name,comics_in_collection,owner_id,visible) VALUES ('test2', ARRAY[0,2,3,4], 0, false);


COMMIT TRANSACTION;
