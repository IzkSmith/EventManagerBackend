INSERT INTO CITY (id, name)
VALUES
       (1,'Москва'),
       (2,'Тула'),
       (3,'Тюмень');

INSERT INTO ROLE (id, name)
VALUES
       (1,'ROLE_ADMIN'),
       (2,'ROLE_HOLDER'),
       (3,'ROLE_USER');

INSERT INTO USER (id, email, username, password, status)
VALUES
       (1,'admin@gmail.com','sa','$2a$04$Bc9WlhAgh8EPCkEKGaqlBOC2aMk3ed9jU7SEH3V0jBuoXStHocOlO', 'ACTIVE'),
       (2,'holder@gmail.com','holder','$2a$04$LTXfUgW6rNemxAM17oMa0Oq6O8JjIHNaFtWImsWFFQq1F2N8fW/5e', 'ACTIVE'),
       (3,'member@gmail.com','member','$2a$04$AT8xvAZHdn9N8kZ7kh435OldAi8vQqoM4Lrer8q3XBKLkhVw5hfhG', 'ACTIVE');

INSERT INTO USER_ROLE (user_id, role_id)
VALUES
       (1,1),
       (2,2),
       (3,3);

INSERT INTO EVENT (id, date,description,max_members, name, city_id)
VALUES
(1, '2012-09-17 18:47:52.69', 'We will be glad to see you on our amazing event!', 1000, 'Christmas celebrating event', 1),
(2,'2017-09-17 18:47:52.69' , 'There will be a concert.', 500, 'Concert', 2),
(3, '2015-09-17 18:47:52.69', 'Everyone invited to join us in the upcoming event!', 2000, 'Family day in the park',3);

INSERT INTO USER_EVENT (user_id, event_id)
VALUES
(1,1),
(2,2),
(3,3);

