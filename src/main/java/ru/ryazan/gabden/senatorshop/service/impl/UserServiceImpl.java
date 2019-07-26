package ru.ryazan.gabden.senatorshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ryazan.gabden.senatorshop.domain.User;
import ru.ryazan.gabden.senatorshop.repository.UserRepository;
import ru.ryazan.gabden.senatorshop.service.UserService;

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
    public void save(User user) {
        userRepository.save(user);
    }
}
