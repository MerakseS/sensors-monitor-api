package com.innowisegroup.sensorsmonitorapi.repository.impl;

import java.util.List;

import com.innowisegroup.sensorsmonitorapi.entity.Unit;
import com.innowisegroup.sensorsmonitorapi.repository.Repository;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;

@Stateless
public class UnitRepository implements Repository<Unit> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Unit create(Unit unit) {
        entityManager.persist(unit);
        return unit;
    }

    @Override
    public List<Unit> findAll() {
        return entityManager
            .createNamedQuery("Units.findAll", Unit.class)
            .getResultList();
    }

    @Override
    public Unit findById(long id) {
        Unit unit = entityManager.find(Unit.class, id);
        if (unit == null) {
            throw new EntityNotFoundException(String.format(
                "Can't find unit with id %d", id));
        }

        return unit;
    }

    @Override
    public Unit update(Unit unit) {
        entityManager.merge(unit);
        return unit;
    }

    @Override
    public void delete(Unit unit) {
        entityManager.remove(unit);
    }
}
