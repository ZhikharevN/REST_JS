package ru.kata.spring.boot_security.demo.dao;


import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;


    public List<User> show() {
        return entityManager.createQuery("SELECT user FROM User user").getResultList();
    }


    public void delete(int id) {
        User user = entityManager.find(User.class, id);
        if (user == null) {
            throw new NullPointerException();
        }
        entityManager.remove(user);
    }


    public void update(int id, User updatedUser) {
        entityManager.merge(updatedUser);
    }

    public User getUser(int id) {
        return entityManager.find(User.class, id);
    }


    public void create(User user) {
        entityManager.persist(user);
    }

    public User findByUsername(String username) {
        return entityManager.createQuery("Select user from User user left join fetch user.roles where user.username=:username", User.class)
                .setParameter("username", username).getSingleResult();
    }
    public User findByEmail(String email) {
        return entityManager.createQuery("Select user from User user left join fetch user.roles where user.email=:email", User.class)
                .setParameter("email", email).getSingleResult();
    }

    public void saveAll(List<User> users) {
        users.stream().forEach(user -> entityManager.persist(user));
    }

}
