CREATE TABLE USER_EVENT
(
    event_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    created TIMESTAMP,
    status VARCHAR(255),
    updated TIMESTAMP
);