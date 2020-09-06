package ru.architecture.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.architecture.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
