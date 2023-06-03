package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRestController {
    @RestController
    @RequestMapping("/api")
    public class RestApiController {

        private final UserService userService;
        private final RoleRepository roleRepository;


        @Autowired
        public RestApiController(UserService userService, RoleRepository roleRepository) {
            this.userService = userService;
            this.roleRepository = roleRepository;
        }

        @GetMapping("/users")
        public ResponseEntity<List<User>> getAllUsers() {
            List<User> list = userService.getAllUsers();
            return ResponseEntity.ok(list);
        }

        @GetMapping("/users/{id}")
        public User getUserById(@PathVariable("id") int id) {
            return userService.getUserById(id);
        }

        @GetMapping("/roles")
        public ResponseEntity<List<Role>> getAllRoles() {
            List<Role> roleList = roleRepository.findAll();
            return ResponseEntity.ok(roleList);
        }


        @PostMapping("/users")
        public ResponseEntity<HttpStatus> saveNewUser(@RequestBody User user) {
            userService.saveUser(user);
            return ResponseEntity.ok(HttpStatus.OK);
        }

        @PutMapping("/users")
        public ResponseEntity<HttpStatus> updateUser(@RequestBody User user) {
            userService.updateUser(user);
            return ResponseEntity.ok(HttpStatus.OK);
        }

        @DeleteMapping("/users/{id}")
        public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") int id) {
            userService.deleteEmployee(id);
            return ResponseEntity.ok(HttpStatus.OK);
        }
    }
}
