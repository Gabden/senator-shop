package ru.ryazan.senatorshop.core.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ryazan.senatorshop.core.domain.User;
import ru.ryazan.senatorshop.core.service.UserService;
@Service
public class UserPrincipalDetailsService implements UserDetailsService {
    private UserService userService;

    public UserPrincipalDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.findUserByUsername(s);
        UserPrincipal userPrincipal = new UserPrincipal(user);
        return userPrincipal;
    }
}
