package ru.pflb.eventmanager.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ru.pflb.eventmanager.entity.City;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CityRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CityRepository cityRepository;

    @Test
    public void findByIdTest() {
        // given
        City moscow = new City();
        moscow.setName("Moscow");
        entityManager.persist(moscow);
        entityManager.flush();

        // when
        City found = cityRepository.findById(moscow.getId()).orElse(null);

        // then
        assertThat(found.getName())
                .isEqualTo(moscow.getName());
    }

    @Test
    public void saveTest() {
        City moscow = new City();
        moscow.setName("Moscow");
        cityRepository.save(moscow);
        Assert.assertNotNull(cityRepository.findById(moscow.getId()));
    }
}
