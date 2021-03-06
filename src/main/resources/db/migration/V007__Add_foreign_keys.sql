ALTER TABLE EVENT
    ADD CONSTRAINT EVENT_CITY_FK FOREIGN KEY (city_id) REFERENCES CITY;
ALTER TABLE USER_EVENT
    ADD CONSTRAINT USER_EVENT_USER_FK FOREIGN KEY (user_id) REFERENCES USER;
ALTER TABLE USER_EVENT
    ADD CONSTRAINT USER_EVENT_EVENT_FK FOREIGN KEY (event_id) REFERENCES EVENT;
ALTER TABLE USER_ROLE
    ADD CONSTRAINT USER_ROLE_USER_FK FOREIGN KEY (user_id) REFERENCES USER;
ALTER TABLE USER_ROLE
    ADD CONSTRAINT USER_ROLE_ROLE_FK FOREIGN KEY (role_id) REFERENCES ROLE;