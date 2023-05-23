package ru.kata.spring.boot_security.demo.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserDao;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        user.setRoles(Collections.singleton(roleRepository.getById(1L)));
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userDao.saveUser(user);
    }

    @Override
    @Transactional
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    @Transactional
    public void deleteEmployee(int id) {
        userDao.deleteEmployee(id);
    }

    @Override
    public User getUserByName(String username) {
        return userDao.getUserByName(username);
    }

//    @Query("Select u from User u left join fetch u.roles where u.username=:username")
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = findByUsername(username);
        if (user == null) {
            throw  new UsernameNotFoundException(String.format("User '%s' not found",username));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),
                true,true,true,true,mapRolesAuthorities(user.getRoles()));
    }

    private Collection <? extends GrantedAuthority>mapRolesAuthorities (Collection<Role> roles) {

        return roles.stream().map(a-> new SimpleGrantedAuthority(a.getName())).collect(Collectors.toList());
    }
}
