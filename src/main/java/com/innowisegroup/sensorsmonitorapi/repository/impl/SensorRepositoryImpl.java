package com.innowisegroup.sensorsmonitorapi.repository.impl;

import com.innowisegroup.sensorsmonitorapi.entity.Sensor;
import com.innowisegroup.sensorsmonitorapi.repository.SensorRepository;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class SensorRepositoryImpl implements SensorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Sensor create(Sensor sensor) {
        entityManager.persist(sensor);
        return sensor;
    }
}
