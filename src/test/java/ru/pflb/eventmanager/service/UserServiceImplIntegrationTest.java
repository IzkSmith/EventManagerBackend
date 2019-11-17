package ru.pflb.eventmanager.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import ru.pflb.eventmanager.dto.UserDto;
import ru.pflb.eventmanager.entity.User;
import ru.pflb.eventmanager.mapper.impl.UserMapper;
import ru.pflb.eventmanager.repository.UserRepository;
import ru.pflb.eventmanager.service.impl.UserServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class UserServiceImplIntegrationTest {

    @TestConfiguration
    static class UserServiceImplTestContextConfiguration {

        private UserRepository userRepository;
        private UserMapper mapper;
        private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        @Bean
        public UserService userService() {
            return  new UserServiceImpl(userRepository, mapper, passwordEncoder );
        }
    }

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Before
    public void setUp() {
        User alex = new User();
        alex.setUsername("Alex");

        Mockito.when(userRepository.findByUsername(alex.getUsername())).thenReturn(alex);//фейковый репозиторий
    }

    @Test
    public void whenValidName_thenUserShouldBeFound() {
        String name = "Alex";
        User found = userService.findByUsername(name);

        assertThat(found.getUsername()).isEqualTo(name);
    }

    @Test
    public void registerTest() {
        User alex = new User();
        alex.setPassword("123");
        alex.setUsername("Alex");
        alex.setFirstName("Alex");
        alex.setLastName("Smith");
        User result = userService.register(alex);

        assertThat(result.getUsername()).isEqualTo("Alex");
    }

    @Test
    public void getTest() {
        User alex = new User();
        alex.setId(1L);
        alex.setUsername("Alex");

        Mockito.when(userRepository.findById(alex.getId())).thenReturn(Optional.of(alex));
        assertThat(userService.get(alex.getId())).isEqualTo(alex);
    }
}
