package ru.pflb.eventmanager.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ru.pflb.eventmanager.entity.Role;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RoleRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void findByIdTest() {
        // given
        Role holder = new Role();
        holder.setName("Holder");
        entityManager.persist(holder);
        entityManager.flush();

        // when
        Role found = roleRepository.findById(holder.getId()).orElse(null);

        // then
        assertThat(found.getName())
                .isEqualTo(holder.getName());
    }

    @Test
    public void saveTest() {
        Role holder = new Role();
        holder.setName("Holder");
        roleRepository.save(holder);
        Assert.assertNotNull(roleRepository.findById(holder.getId()));
    }
}
