CREATE TABLE ROLE
(
    id BIGINT generated by default as identity,
    name VARCHAR(255) UNIQUE NOT NULL,
    primary key (id)
);