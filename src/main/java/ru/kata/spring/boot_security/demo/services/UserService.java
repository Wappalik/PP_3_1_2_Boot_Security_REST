package ru.kata.spring.boot_security.demo.services;


import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    public List<User> getAllUsers ();

    void saveUser (User user);
    User getUserById(int id);
    public void deleteEmployee (int id);
    void updateUser (User user);
    public User findByUsername(String username);
}
