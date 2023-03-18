package com.innowisegroup.sensorsmonitorapi.repository.impl;

import com.innowisegroup.sensorsmonitorapi.entity.Unit;
import com.innowisegroup.sensorsmonitorapi.repository.UnitRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;

public class UnitRepositoryImpl implements UnitRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Unit findById(long id) {
        Unit unit = entityManager.find(Unit.class, id);
        if (unit == null) {
            throw new EntityNotFoundException(String.format(
                "Can't find unit with id %d", id));
        }

        return unit;
    }
}
