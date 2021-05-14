package ru.gabdulindv.senatorshop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.gabdulindv.senatorshop.model.UserDetailsDescription;

public interface UserDescriptionRepo extends JpaRepository<UserDetailsDescription, Long> {
}
