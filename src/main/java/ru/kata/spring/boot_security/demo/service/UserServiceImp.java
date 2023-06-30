package ru.kata.spring.boot_security.demo.service;


import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    private final UserDao userDao;
    private final RoleService roleService;

    public UserServiceImp(UserDao userDao, RoleService roleService) {
        this.userDao = userDao;
        this.roleService = roleService;
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
        List<Integer> listRole = new ArrayList<>();
        user.getRoles().forEach(role -> listRole.add(Integer.parseInt(role.getName())));
        user.setRoles(Collections.emptySet());
        user.setRoles(listRole.stream().map(id -> roleService.findById(id)).collect(Collectors.toSet()));
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
}
