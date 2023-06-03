package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRestController {
    @Autowired
    UserService userService;
    @Autowired
    RoleRepository roleRepository;


    @GetMapping("/users")
    public ResponseEntity<List<User>> listResponseEntity() {
        List <User> userList = userService.getAllUsers();
        return ResponseEntity.ok(userList);
    }

    @GetMapping ("/users/{id}")
    public User getUserById (@PathVariable ("id") int id) {
        User user = userService.getUserById(id);
        return user;
    }


    @GetMapping ("/roles")
    public ResponseEntity<Collection<Role>> getAllRoles () {
        return ResponseEntity.ok(roleRepository.findAll());
    }

    @PostMapping("/users")
    public ResponseEntity<HttpStatus> saveUser(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @PutMapping("/users")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") int id) {
        userService.deleteEmployee(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
