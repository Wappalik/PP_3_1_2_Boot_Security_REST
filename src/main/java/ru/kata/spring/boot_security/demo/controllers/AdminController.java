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
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserServiceImpl service;
    @Autowired
    private RoleRepository roleRepository;


//    @PatchMapping
//    public String updateInfo() {
//
//    }
    @GetMapping
    public String allUsers(Principal principal, Model model) {
        //страничка юзера
        String username = principal.getName();
        User user = service.findByUsername(username);
        model.addAttribute("thisUser", user);

        //таблица всех юзеров
        List<User> userList = service.getAllUsers();
        model.addAttribute("users", userList);

        //для ролей
        Collection <Role> list = new ArrayList<>();
        list.add(roleRepository.getById(1L));
        list.add(roleRepository.getById(2L));
        model.addAttribute("rolesList", list);

        //добавление нового юзера
        User user1 = new User ();
        model.addAttribute("newUser", user1);


//        //обновление юзера
//        User user2 = service.getUserById(id);
//        model.addAttribute("user2", user2);
        return "all-users5";
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
    public String saveUser(@ModelAttribute("newUser") User user) {
            service.saveUser(user);
            return "redirect:/admin";
    }

    @PostMapping("/updateInfo")
    public String updateUser(@ModelAttribute("oneUser") User user) {
        service.saveUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable ("id") int id) {
        service.deleteEmployee(id);
        return "redirect:/admin";
    }
}


