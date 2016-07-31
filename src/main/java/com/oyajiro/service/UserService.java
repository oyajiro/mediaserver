package com.oyajiro.service;

import com.oyajiro.entity.User;
import com.oyajiro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by user on 31.07.16.
 */
@Service
public class UserService {

    private UserRepository userRepository;
    private final String DEBUG_LOGIN = "debug";

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getCurrentUser() {
        String login = getCurrentUserLogin();
        User current = userRepository.findByLogin(login);
        if (current == null) {
            current = User.createFromLogin(login);
            userRepository.save(current);
        }
        return current;
    }

    //TODO SSO integration
    private String getCurrentUserLogin() {
        return DEBUG_LOGIN;
    }
}
