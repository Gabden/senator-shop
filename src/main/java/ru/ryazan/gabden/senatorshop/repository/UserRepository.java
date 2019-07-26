package ru.ryazan.gabden.senatorshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ryazan.gabden.senatorshop.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
