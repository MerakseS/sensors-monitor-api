package com.innowisegroup.sensorsmonitorapi.repository;

import java.util.List;

import com.innowisegroup.sensorsmonitorapi.entity.Sensor;

public interface SensorRepository {

    Sensor create(Sensor sensor);

    List<Sensor> findAll();

    Sensor findById(long id);

    Sensor update(Sensor sensor);
}
