package com.shekhar.confsays.service;

import com.shekhar.confsays.domain.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

@Stateless
public class UserService {

    @PersistenceContext
    private EntityManager em;


    public User find(Long id) {
        return em.find(User.class, id);
    }

    public User find(String email, String password) {
        List<User> found = em.createNamedQuery("User.find", User.class)
                .setParameter("email", email)
                .setParameter("password", password).getResultList();
        return found.isEmpty() ? null : found.get(0);
    }

    public User findByEmail(String email) {
        List<User> users = em.createNamedQuery("User.findByEmail", User.class)
                .setParameter("email", email)
                .getResultList();
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }

    @Produces
    @Named("users")
    public List<User> list() {
        return em.createNamedQuery("User.list", User.class).getResultList();
    }

    public Long create(User user) {
        em.persist(user);
        return user.getId();
    }

    public void update(User user) {
        em.merge(user);
    }

    public void delete(User user) {
        em.remove(em.contains(user) ? user : em.merge(user));
    }

}