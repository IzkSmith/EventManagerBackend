package ru.pflb.eventmanager.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import ru.pflb.eventmanager.dto.EventDto;
import ru.pflb.eventmanager.entity.Event;
import ru.pflb.eventmanager.entity.User;
import ru.pflb.eventmanager.mapper.impl.EventMapper;
import ru.pflb.eventmanager.mapper.impl.UserMapper;
import ru.pflb.eventmanager.repository.EventRepository;
import ru.pflb.eventmanager.repository.UserRepository;
import ru.pflb.eventmanager.service.impl.EventServiceImpl;
import ru.pflb.eventmanager.service.impl.UserServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class EventServiceImplIntegrationTest {

    @TestConfiguration
    static class EventServiceImplTestContextConfiguration {

        private EventRepository eventRepository;
        private EventMapper mapper;

        @Bean
        public EventService eventService() {
            return  new EventServiceImpl(eventRepository, mapper);
        }
    }

    @Autowired
    private EventService eventService;

    @MockBean
    private EventRepository eventRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void registerTest() {
        EventDto alex = new EventDto();
    }
}
