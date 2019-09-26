INSERT INTO CITY (id, name)
VALUES
       (1,'Москва'),
       (2,'Тула'),
       (3,'Тюмень');

INSERT INTO ROLE (id, name)
VALUES
       (1,'Admin'),
       (2,'Holder'),
       (3,'Member');

INSERT INTO USER (id, email, username, password)
VALUES
       (1,'admin@gmail.com','sa','sa'),
       (2,'holder@gmail.com','holder','holder'),
       (3,'member@gmail.com','member','member');

INSERT INTO USER_ROLE (user_id, role_id)
VALUES
       (1,1),
       (2,2),
       (3,3);