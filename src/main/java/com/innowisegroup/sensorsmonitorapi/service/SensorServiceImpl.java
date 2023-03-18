package com.innowisegroup.sensorsmonitorapi.service;

import com.innowisegroup.sensorsmonitorapi.entity.Sensor;
import com.innowisegroup.sensorsmonitorapi.repository.SensorRepository;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Stateless
public class SensorServiceImpl implements SensorService {

    @EJB
    private SensorRepository sensorRepository;

    @Override
    @Transactional
    public Sensor create(Sensor sensor) {
        sensor = sensorRepository.create(sensor);
        log.info("Successfully created sensor with id {}", sensor.getId());
        return sensor;
    }
}
