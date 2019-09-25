package ru.ryazan.senatorshop.core.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import ru.ryazan.senatorshop.core.domain.User;
import ru.ryazan.senatorshop.core.repository.UserRepository;
import ru.ryazan.senatorshop.core.service.UserService;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findUserById (Long id){
        return userRepository.findById(id);
    }
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
