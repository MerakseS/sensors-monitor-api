package com.innowisegroup.sensorsmonitorapi.repository.impl;

import com.innowisegroup.sensorsmonitorapi.entity.Type;
import com.innowisegroup.sensorsmonitorapi.repository.TypeRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;

public class TypeRepositoryImpl implements TypeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Type findById(long id) {
        Type type = entityManager.find(Type.class, id);
        if (type == null) {
            throw new EntityNotFoundException(String.format(
                "Can't find type with id %d", id));
        }

        return type;
    }
}
