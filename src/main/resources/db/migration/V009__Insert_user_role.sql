INSERT INTO ROLE (id, name)
VALUES
       (1,'ROLE_ADMIN'),
       (2,'ROLE_HOLDER'),
       (3,'ROLE_USER');

INSERT INTO USER (id, first_name, last_name, email, username, password, status)
VALUES
       (1, 'Isaac', 'Smith', 'admin@gmail.com','sa',
        '$2a$04$Bc9WlhAgh8EPCkEKGaqlBOC2aMk3ed9jU7SEH3V0jBuoXStHocOlO', 'ACTIVE'),
       (2, 'Ed', 'Chase','holder1@gmail.com','holder1',
        '$2a$04$iEd/yDVhkUzDwvoj0DHIKOI0hmmWx1N/OM274vRsQPjfMQrPK2mO6', 'ACTIVE'),
       (3, 'Jennifer', 'Davis','holder2@gmail.com','holder2',
        '$2a$04$iEd/yDVhkUzDwvoj0DHIKOI0hmmWx1N/OM274vRsQPjfMQrPK2mO6', 'ACTIVE'),
       (4, 'Johnny', 'Lollobrigida','holder3@gmail.com','holder3',
        '$2a$04$iEd/yDVhkUzDwvoj0DHIKOI0hmmWx1N/OM274vRsQPjfMQrPK2mO6', 'ACTIVE'),
       (5, 'Bette', 'Nicholson','member1@gmail.com','member1',
        '$2a$04$iEd/yDVhkUzDwvoj0DHIKOI0hmmWx1N/OM274vRsQPjfMQrPK2mO6', 'ACTIVE'),
       (6, 'Nick', 'Wahlberg','member2@gmail.com','member2',
        '$2a$04$iEd/yDVhkUzDwvoj0DHIKOI0hmmWx1N/OM274vRsQPjfMQrPK2mO6', 'ACTIVE'),
       (7, 'Penelope', 'Guiness','member3@gmail.com','member3',
        '$2a$04$iEd/yDVhkUzDwvoj0DHIKOI0hmmWx1N/OM274vRsQPjfMQrPK2mO6', 'ACTIVE');
INSERT INTO USER_ROLE (user_id, role_id)
VALUES
       (1,1),
       (2,2),
       (3,2),
       (4,2),
       (5,3),
       (6,3),
       (7,3)



