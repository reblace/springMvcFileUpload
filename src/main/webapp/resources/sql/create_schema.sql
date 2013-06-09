-- User account and authority information
CREATE TABLE users (
	username VARCHAR(64) NOT NULL,
	password VARCHAR(100) NOT NULL,
	enabled BOOLEAN NOT NULL,
	CONSTRAINT pk_users PRIMARY KEY(username)
);

CREATE TABLE authorities (
	username VARCHAR(64) NOT NULL,
	authority VARCHAR(50) NOT NULL,
	CONSTRAINT ix_auth_username UNIQUE (username, authority),
	CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users (username)
);

-- Tracking persistent logins
CREATE TABLE persistent_logins (
	username VARCHAR(64) NOT NULL,
	series VARCHAR(64) NOT NULL,
	token VARCHAR(64) NOT NULL,
	last_used TIMESTAMP NOT NULL,
	CONSTRAINT pk_persistent_logins PRIMARY KEY(series)
);