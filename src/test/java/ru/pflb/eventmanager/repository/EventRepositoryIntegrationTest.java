package ru.pflb.eventmanager.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ru.pflb.eventmanager.entity.Event;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EventRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EventRepository eventRepository;

    @Test
    public void findByIdTest() {
        // given
        Event moscow = new Event();
        moscow.setName("Moscow");
        entityManager.persist(moscow);
        entityManager.flush();

        // when
        Event found = eventRepository.findById(moscow.getId()).orElse(null);

        // then
        assertThat(found.getName())
                .isEqualTo(moscow.getName());
    }

    @Test
    public void saveTest() {
        Event moscow = new Event();
        moscow.setName("Moscow");
        eventRepository.save(moscow);
        Assert.assertNotNull(eventRepository.findById(moscow.getId()));
    }
}
