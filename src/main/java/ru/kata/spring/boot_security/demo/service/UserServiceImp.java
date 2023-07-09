package ru.kata.spring.boot_security.demo.service;


import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class UserServiceImp implements UserService {

    private final UserDao userDao;


    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }


    public List<User> show() {
        return userDao.show();
    }

    @Transactional
    public void delete(int id) {
        userDao.delete(id);
    }

    @Transactional
    public void update(int id, User user) {
        userDao.update(id, user);
    }

    @Transactional
    public void create(User user) {
        userDao.create(user);
    }

    public User getUser(int id) {
        return userDao.getUser(id);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Transactional
    @Override
    public void saveAll(List<User> users) {
        userDao.saveAll(users);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }


}
