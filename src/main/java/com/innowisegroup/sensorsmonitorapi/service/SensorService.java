package com.innowisegroup.sensorsmonitorapi.service;

import java.util.List;

import com.innowisegroup.sensorsmonitorapi.entity.Sensor;

import jakarta.ejb.Local;

@Local
public interface SensorService {

    Sensor create(Sensor sensor);

    List<Sensor> getAll();
}
