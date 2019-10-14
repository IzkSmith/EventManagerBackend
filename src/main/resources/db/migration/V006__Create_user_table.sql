CREATE TABLE USER
(
    id BIGINT generated by default as identity,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    username VARCHAR(255) UNIQUE NOT NULL,
    created TIMESTAMP,
    status VARCHAR(255),
    updated TIMESTAMP,
    primary key (id)
);