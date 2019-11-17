package ru.pflb.eventmanager.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ru.pflb.eventmanager.entity.User;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindByName_thenReturnUser() {
        // given
        User alex = new User();
        alex.setUsername("Alex");
        alex.setPassword("123");
        entityManager.persist(alex);
        entityManager.flush();

        // when
        User found = userRepository.findByUsername(alex.getUsername());

        // then
        assertThat(found.getUsername())
                .isEqualTo(alex.getUsername());
    }

    @Test
    public void whenFindById_thenReturnUser() {
        // given
        User alex = new User();
        alex.setUsername("Alex");
        alex.setPassword("123");
        entityManager.persist(alex);
        entityManager.flush();

        // when
        User found = userRepository.findById(alex.getId()).orElse(null);

        // then
        assertThat(found.getUsername())
                .isEqualTo(alex.getUsername());
    }

    @Test
    public void saveTest() {
        User alex = new User();
        alex.setUsername("Alex");
        userRepository.save(alex);
        Assert.assertNotNull(userRepository.findByUsername("Alex"));
    }
}
