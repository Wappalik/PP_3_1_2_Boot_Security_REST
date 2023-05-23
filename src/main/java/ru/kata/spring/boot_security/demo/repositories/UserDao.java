package ru.kata.spring.boot_security.demo.repositories;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers ();
    void saveUser (User user);
    User getUserById(int id);
    public void deleteEmployee (int id);
    User getUserByName(String username);
}
