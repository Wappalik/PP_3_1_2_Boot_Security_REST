package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserServiceImpl service;
    @Autowired
    private RoleRepository roleRepository;


    @GetMapping
    public String sayHello(Model model) {
        List<User> userList = service.getAllUsers();
        model.addAttribute("users", userList);
        return "all-users";
    }

    @GetMapping("/addNewEmployee")
    public String addNewUser(Model model) {
        User user = new User();
        Collection <Role> list = new ArrayList<>();
        list.add(roleRepository.getById(1L));
        list.add(roleRepository.getById(2L));
        model.addAttribute("user", user);
        model.addAttribute("rolesList", list);
        return "user-info";
    }

    @PostMapping("/saveUser")
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user-info";
        } else {
            service.saveUser(user);
            return "redirect:/admin";
        }
    }

    @PatchMapping("/updateInfo")
    public String updateUser(@RequestParam("userId") int id, Model model) {

        User user = service.getUserById(id);
        Collection <Role> list = new ArrayList<>();
        list.add(roleRepository.getById(1L));
        list.add(roleRepository.getById(2L));
        model.addAttribute("user", user);
        model.addAttribute("rolesList", list);
        return "update-user";
    }

    @DeleteMapping("/deleteInfo")
    public String deleteUser(@RequestParam("userId") int id) {
        service.deleteEmployee(id);
        return "redirect:/admin";
    }
}


