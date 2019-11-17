package ru.pflb.eventmanager.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.pflb.eventmanager.dto.RoleDto;
import ru.pflb.eventmanager.entity.Role;
import ru.pflb.eventmanager.mapper.impl.RoleMapper;
import ru.pflb.eventmanager.repository.RoleRepository;
import ru.pflb.eventmanager.service.impl.RoleServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class RoleServiceImplIntegrationTest {

    @TestConfiguration
    static class RoleServiceImplTestContextConfiguration {

        private RoleRepository roleRepository;
        private RoleMapper mapper;

        @Bean
        public RoleService roleService() {
            return  new RoleServiceImpl(roleRepository, mapper);
        }
    }

    @Autowired
    private RoleService roleService;

    @MockBean
    private RoleRepository roleRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createTest() {
        RoleDto alex = new RoleDto();
        alex.setName("Alex");
        RoleDto result = null;
        try {
            result = roleService.create(alex);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        if (result != null) {
            assertThat(result.getName()).isEqualTo("Alex");
        }
    }
}
