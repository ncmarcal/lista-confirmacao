CREATE TABLE users (
    id INT PRIMARY KEY NOT NULL,
    username TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    role TEXT NOT NULL
);