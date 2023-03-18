package com.innowisegroup.sensorsmonitorapi.service;

import com.innowisegroup.sensorsmonitorapi.entity.Sensor;

import jakarta.ejb.Local;

@Local
public interface SensorService {

    Sensor create(Sensor sensor);
}
