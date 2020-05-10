package ru.gabdulindv.senatorshop.model;

import lombok.Data;
import java.util.List;

@Data
public class UserPayload {
    private String username;
    private String token;
    private List<String> rolesList;

    public UserPayload(String username, String token, List<String> rolesList) {
        this.username = username;
        this.token = token;
        this.rolesList = rolesList;
    }

    @Override
    public String toString() {
        return "{" +
                "username:'" + username + '\'' +
                ", token:'" + token + '\'' +
                ", rolesList:" + rolesList +
                '}';
    }
}
