package ru.ryazan.senatorshop.core.utils;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UtilService {

    public boolean hasAnyRole(HttpServletRequest req, String... roles) {
        for (final String role : roles) {
            if (req.isUserInRole(role)) {
                return true;
            }
        }
        return false;
    }

}