package ru.kata.spring.boot_security.demo.dao;


import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;



@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager em;


    public List<User> show() {
        return em.createQuery("SELECT user FROM User user").getResultList();
    }


    public void delete(int id) {
        User user = em.find(User.class, id);
        if (user == null) {
            throw new NullPointerException();
        }
        em.remove(user);
    }


    public void update(int id, User updatedUser) {
        em.merge(updatedUser);
    }

    public User getUser(int id) {
        return em.find(User.class, id);
    }


    public void create(User user) {
        em.persist(user);
    }

    public User findByUsername(String username) {
        return (User) em.createQuery("Select user from User user left join fetch user.roles where user.username=:username")
                .setParameter("username", username).getSingleResult();
    }


    public void saveAll(List<User> users) {
        users.stream().forEach(user -> em.persist(user));
    }

}
