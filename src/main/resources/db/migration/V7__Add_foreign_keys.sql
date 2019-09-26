alter table EVENT
    add constraint EVENT_CITY_FK foreign key (city_id) references city;
alter table EVENT_USERS
    add constraint EVENT_USERS_USER_FK foreign key (users_id) references user;
alter table EVENT_USERS
    add constraint EVENT_USERS_EVENT_FK foreign key (events_id) references event;
alter table ROLE_USERS
    add constraint ROLE_USERS_USER_FK foreign key (users_id) references user;
alter table ROLE_USERS
    add constraint ROLE_USERS_ROLE_FK foreign key (roles_id) references role;