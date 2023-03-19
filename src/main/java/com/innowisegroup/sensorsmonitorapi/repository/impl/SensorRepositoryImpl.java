package com.innowisegroup.sensorsmonitorapi.repository.impl;

import java.util.List;

import com.innowisegroup.sensorsmonitorapi.entity.Sensor;
import com.innowisegroup.sensorsmonitorapi.repository.SensorRepository;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
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

    @Override
    public List<Sensor> findAll() {
        return entityManager
            .createNamedQuery("Sensors.findAll", Sensor.class)
            .getResultList();
    }

    @Override
    public Sensor findById(long id) {
        Sensor sensor = entityManager.find(Sensor.class, id);
        if (sensor == null) {
            throw new EntityNotFoundException(String.format("Sensor with id %d doesn't exists", id));
        }

        return sensor;
    }

    @Override
    public Sensor update(Sensor sensor) {
        entityManager.merge(sensor);
        return sensor;
    }
}
