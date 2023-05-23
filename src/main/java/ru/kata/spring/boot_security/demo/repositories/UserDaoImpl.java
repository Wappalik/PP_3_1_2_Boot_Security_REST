package ru.kata.spring.boot_security.demo.repositories;


import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {


    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List <User> getAllUsers () {
//        User user1 = new User("Malik", "IT",25,1000);
//        User user2 = new User("Erik", "HR",31,1500);
//        User user3 = new User("Olga", "Sales",28,1600);
//        User user4 = new User("Kostya", "Security",36,2000);
//        entityManager.persist(user1);
//        entityManager.persist(user2);
//        entityManager.persist(user3);
//        entityManager.persist(user4);
        TypedQuery<User> userQuery = entityManager.createQuery("from User", User.class);
        return userQuery.getResultList();

    }

    @Override
    public void saveUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getUserById(int id) {
        User user = entityManager.find(User.class,id);
        return user;
    }

    @Override
    public void deleteEmployee(int id) {
   TypedQuery <User>query = (TypedQuery<User>) entityManager.createQuery("delete from User where id=:userId");
    query.setParameter("userId",id).executeUpdate();
    }

    @Override
    @Query ("Select u from User u left join fetch u.roles where u.username=:username")
    public User getUserByName(String username) {
    TypedQuery <User> query = entityManager.createQuery("select  from User where username=: name", User.class);
     query.setParameter("name",username);
     return query.getSingleResult();
    }

}
