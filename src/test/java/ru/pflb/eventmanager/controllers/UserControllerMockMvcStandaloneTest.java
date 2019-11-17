package ru.pflb.eventmanager.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.pflb.eventmanager.controller.Filter.UserFilter;
import ru.pflb.eventmanager.controller.UserController;
import ru.pflb.eventmanager.entity.User;
import ru.pflb.eventmanager.repository.UserRepository;
import ru.pflb.eventmanager.service.UserService;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerMockMvcStandaloneTest {

    private MockMvc mvc;

    @Mock
    private UserRepository repository;

    @Mock
    private UserService service;

    @InjectMocks
    private UserController userController;

    private JacksonTester<User> jsonUser;

    @Before
    public void setup() {
        // We would need this line if we would not use MockitoJUnitRunner
        // MockitoAnnotations.initMocks(this);
        // Initializes the JacksonTester
        JacksonTester.initFields(this, new ObjectMapper());
        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(userController)
                .addFilters(new UserFilter())
                .build();
    }

    @Test
    public void canRetrieveByIdWhenExists() throws Exception {
        User alex = new User();
        alex.setId(2L);
        alex.setFirstName("Alex");
        alex.setLastName("Simpson");
        alex.setUsername("Alex");
        // given
        given(repository.findById(2L))
                .willReturn(Optional.of(alex));

        // when
        MockHttpServletResponse response = mvc.perform(
                get("/api/v1/user/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonUser.write(alex).getJson()
        );
    }

    @Test
    public void canRegisterANewUser() throws Exception {
        User alex = new User();
        alex.setFirstName("Alex");
        alex.setLastName("Simpson");
        alex.setUsername("Alexandro");
        alex.setPassword("123");
        alex.setEmail("test@gmail.com");
        // when
        MockHttpServletResponse response = mvc.perform(
                post("/api/v1/user/register").contentType(MediaType.APPLICATION_JSON).content(
                        jsonUser.write(alex).getJson()
                )).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
