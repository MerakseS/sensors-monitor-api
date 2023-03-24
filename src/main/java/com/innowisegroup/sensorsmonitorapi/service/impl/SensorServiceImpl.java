package com.innowisegroup.sensorsmonitorapi.service.impl;

import java.util.List;

import com.innowisegroup.sensorsmonitorapi.entity.Sensor;
import com.innowisegroup.sensorsmonitorapi.repository.Repository;
import com.innowisegroup.sensorsmonitorapi.service.SensorService;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Stateless
@NoArgsConstructor
@AllArgsConstructor(onConstructor_ = @Inject)
public class SensorServiceImpl implements SensorService {

    private Repository<Sensor> sensorRepository;

    @Override
    @Transactional
    public Sensor create(Sensor sensor) {
        sensor = sensorRepository.create(sensor);
        log.info("Successfully created sensor with id {}", sensor.getId());
        return sensor;
    }

    @Override
    public List<Sensor> getAll() {
        List<Sensor> sensorList = sensorRepository.findAll();
        log.info("Successfully found all sensors");
        return sensorList;
    }

    @Override
    public Sensor get(long id) {
        Sensor sensor = sensorRepository.findById(id);
        log.info("Successfully found sensor with id {}", sensor.getId());
        return sensor;
    }

    @Override
    @Transactional
    public Sensor update(long id, Sensor sensor) {
        sensorRepository.findById(id);

        sensor.setId(id);
        sensor = sensorRepository.update(sensor);
        log.info("Successfully updated sensor with id {}", sensor.getId());

        return sensor;
    }

    @Override
    @Transactional
    public void delete(long id) {
        Sensor sensor = sensorRepository.findById(id);
        sensorRepository.delete(sensor);
        log.info("Successfully deleted sensor with id {}", id);
    }
}
