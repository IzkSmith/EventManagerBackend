package ru.pflb.eventmanager.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pflb.eventmanager.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
