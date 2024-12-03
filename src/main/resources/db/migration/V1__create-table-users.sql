CREATE SEQUENCE users_id_seq;

CREATE TABLE users (
    id INT PRIMARY KEY DEFAULT nextval('users_id_seq'),
    username TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    role TEXT NOT NULL,
    presence BIT NOT NULL
);

SELECT setval('users_id_seq', COALESCE((SELECT MAX(id) FROM users), 1));