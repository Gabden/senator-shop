package ru.ryazan.gabden.senatorshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ryazan.gabden.senatorshop.domain.User;
import ru.ryazan.gabden.senatorshop.service.UserService;

import javax.annotation.PostConstruct;

@Component
public class Bootstrap {

    private UserService userService;

    public Bootstrap(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void init (){
        User newUser = new User("Denis", "densi@mail.ru","12345");
        userService.save(newUser);

        User newUser2 = new User("Oleg", "Oleg@mail.ru","12345");
        userService.save(newUser2);

        User newUser3 = new User("Anton", "Anton@mail.ru","12345");
        userService.save(newUser3);
    }
}
