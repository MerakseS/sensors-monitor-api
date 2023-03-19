package com.innowisegroup.sensorsmonitorapi.service;

import java.util.List;

import com.innowisegroup.sensorsmonitorapi.entity.Sensor;

import jakarta.ejb.Local;

@Local
public interface SensorService {

    Sensor create(Sensor sensor);

    List<Sensor> getAll();

    Sensor get(long id);

    Sensor update(long id, Sensor sensor);

    void delete(long id);
}
