package ru.ryazan.senatorshop.core.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.ryazan.senatorshop.core.domain.User;


public interface UserRepository extends JpaRepository<User, Long> {
}
