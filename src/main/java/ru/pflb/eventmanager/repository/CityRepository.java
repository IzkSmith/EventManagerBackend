package ru.pflb.eventmanager.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pflb.eventmanager.entity.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}