package ru.kata.spring.boot_security.demo.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column (name = "name")
    @NotBlank(message = "should not be empty")
    @Size(min = 2, message = "min 2 symbols")
    @Pattern(regexp = "[^\\d]+", message = "should not be a number")
    @Pattern(regexp = "\\p{L}+", message = "should not be symbol")
    private String name;

    @Column (name = "department")
    @NotBlank(message = "should not be empty")
    private String department;

    @Column (name = "age")
    @Min(value = 18, message = "min is 18")
    @Max(value = 60, message = "max is 60")
    private long age;

    @Column (name = "salary")
    @Min (value = 500, message = "min salary is begin from 500")
    private long salary;

    @Column (name = "username")
    private String username;

    @Column (name = "password")
    private String password;

    @ManyToMany
    @JoinTable (name = "users_roles",
    joinColumns = @JoinColumn (name = "user_id"),
    inverseJoinColumns = @JoinColumn (name = "roles_id"))
    private Collection <Role> roles;


    public User() {
    }

    public User(String name, String department, long age, long salary, String username, String password) {
        this.name = name;
        this.department = department;
        this.age = age;
        this.salary = salary;
        this.username = username;
        this.password = password;

    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && age == user.age && salary == user.salary && Objects.equals(name, user.name) && Objects.equals(department, user.department) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, department, age, salary, username, password, roles);
    }
}
