package ru.pflb.eventmanager.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pflb.eventmanager.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
