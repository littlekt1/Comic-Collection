BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS collections;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	tier varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE collections (
    collection_id SERIAL,
    collection_name varchar(50) NOT NULL,
    comics_in_collection INT[] NOT NULL,
    owner_id INT NOT NULL,
    visible BOOLEAN NOT NULL,
    CONSTRAINT PK_collection PRIMARY KEY (collection_id),
    CONSTRAINT FK_user_id FOREIGN KEY (owner_id) REFERENCES users(user_id)
);

COMMIT TRANSACTION;
