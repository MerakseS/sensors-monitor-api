package com.innowisegroup.sensorsmonitorapi.repository.impl;

import java.util.List;

import com.innowisegroup.sensorsmonitorapi.entity.User;
import com.innowisegroup.sensorsmonitorapi.repository.UserRepository;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;

@Stateless
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User create(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return entityManager
            .createNamedQuery("Users.findAll", User.class)
            .getResultList();
    }

    @Override
    public User findById(long id) {
        User user = entityManager.find(User.class, id);
        if (user == null) {
            throw new EntityNotFoundException(String.format("User with id %d doesn't exists", id));
        }

        return user;
    }

    @Override
    public User findByLogin(String login) {
        return entityManager
            .createNamedQuery("Users.findByLogin", User.class)
            .setParameter("login", login)
            .getSingleResult();
    }

    @Override
    public User update(User user) {
        entityManager.merge(user);
        return user;
    }

    @Override
    public void delete(User user) {
        entityManager.remove(user);
    }
}
