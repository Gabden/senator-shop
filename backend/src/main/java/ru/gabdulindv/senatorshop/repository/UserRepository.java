package ru.gabdulindv.senatorshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gabdulindv.senatorshop.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);

    Optional<User> findUserByUserDetailsDescription_PhoneContains(String phone);

    Optional<User> findUserByUsernameContains(String username);

    Page<User> findUsersByUserDetailsDescription_FIOfirstContainsOrUserDetailsDescription_FIOlastContainsOrUserDetailsDescription_FIOmiddleContains(String first, String second, String last, Pageable pageable);
}