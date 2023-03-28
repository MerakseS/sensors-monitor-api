package com.innowisegroup.sensorsmonitorapi.repository.impl;

import java.util.List;

import com.innowisegroup.sensorsmonitorapi.entity.Type;
import com.innowisegroup.sensorsmonitorapi.repository.Repository;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;

@Stateless
public class TypeRepositoryImpl implements Repository<Type> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Type create(Type type) {
        entityManager.persist(type);
        return type;
    }

    @Override
    public List<Type> findAll() {
        return entityManager
            .createNamedQuery("Types.findAll", Type.class)
            .getResultList();
    }

    @Override
    public Type findById(long id) {
        Type type = entityManager.find(Type.class, id);
        if (type == null) {
            throw new EntityNotFoundException(String.format(
                "Can't find type with id %d", id));
        }

        return type;
    }

    @Override
    public Type update(Type type) {
        entityManager.merge(type);
        return type;
    }

    @Override
    public void delete(Type type) {
        entityManager.remove(type);
    }
}
