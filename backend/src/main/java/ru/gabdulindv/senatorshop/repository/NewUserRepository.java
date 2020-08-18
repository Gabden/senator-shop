package ru.gabdulindv.senatorshop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.gabdulindv.senatorshop.model.User;

import java.util.Optional;

public interface NewUserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
}
