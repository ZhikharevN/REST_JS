package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
public class RoleRepositoryImp implements RoleRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Role> findAll() {
        return em.createQuery("SELECT role FROM Role role").getResultList();
    }


    @Override
    @Transactional
    public void saveAll(Set<Role> roles) {
        roles.stream().forEach(role -> em.persist(role));
    }

    @Override
    public Role findById(int id) {
        return em.find(Role.class, id);
    }
}
