package ru.architecture.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.architecture.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByName(String username);
}
