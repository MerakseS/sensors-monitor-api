package com.innowisegroup.sensorsmonitorapi.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.innowisegroup.sensorsmonitorapi.entity.Sensor;
import com.innowisegroup.sensorsmonitorapi.entity.Sensor.SensorBuilder;
import com.innowisegroup.sensorsmonitorapi.entity.Type;
import com.innowisegroup.sensorsmonitorapi.entity.Unit;
import com.innowisegroup.sensorsmonitorapi.repository.Repository;
import com.innowisegroup.sensorsmonitorapi.service.impl.SensorServiceImpl;

import jakarta.persistence.EntityNotFoundException;

class SensorServiceTest {

    private SensorBuilder defaultSensorBuilder;
    private Repository<Sensor> sensorRepository;
    private SensorService sensorService;

    @BeforeEach
    void setUp() {
        defaultSensorBuilder = getDefaultSensorBuilder();
        sensorRepository = (Repository<Sensor>) Mockito.mock(Repository.class);
        sensorService = new SensorServiceImpl(sensorRepository);
    }

    @Test
    void create() {
        Sensor sensor = defaultSensorBuilder.id(null).build();

        Sensor createdSensor = defaultSensorBuilder.id(1L).build();
        Mockito.when(sensorRepository.create(sensor))
            .thenReturn(createdSensor);

        Sensor actualSensor = sensorService.create(sensor);

        Mockito.verify(sensorRepository).create(sensor);
        Sensor expectedSensor = defaultSensorBuilder.id(1L).build();
        Assertions.assertEquals(expectedSensor, actualSensor);
    }

    @Test
    void getAll() {
        List<Sensor> sensorList = sensorService.getAll();

        Mockito.verify(sensorRepository).findAll();
        Assertions.assertNotNull(sensorList);
    }

    @Test
    void getById() {
        long id = 1L;
        Mockito.when(sensorRepository.findById(id))
            .thenReturn(defaultSensorBuilder.build());

        Sensor sensor = sensorService.get(id);

        Assertions.assertEquals(id, sensor.getId());
    }

    @Test
    void getByUnknownId() {
        long id = -1L;
        Mockito.when(sensorRepository.findById(id))
            .thenThrow(new EntityNotFoundException());

        Assertions.assertThrows(EntityNotFoundException.class,
            () -> sensorService.get(id));
    }

    @Test
    void update() {
        long id = 1L;
        Sensor newSensor = defaultSensorBuilder.id(null)
            .name("New sensor")
            .build();

        Sensor oldSensor = defaultSensorBuilder.id(id)
            .name("Old sensor")
            .build();
        Mockito.when(sensorRepository.findById(oldSensor.getId()))
            .thenReturn(oldSensor);

        Sensor updatedSensor = defaultSensorBuilder.id(id)
            .name("New Sensor")
            .build();
        Mockito.when(sensorRepository.update(newSensor))
            .thenReturn(updatedSensor);

        Sensor actualSensor = sensorService.update(id, newSensor);

        Mockito.verify(sensorRepository).findById(id);
        Mockito.verify(sensorRepository).update(newSensor);
        Assertions.assertEquals(updatedSensor, actualSensor);
    }

    @Test
    void updateUnknownSensor() {
        long id = -1L;
        Sensor sensor = defaultSensorBuilder.build();
        Mockito.when(sensorRepository.findById(id))
            .thenThrow(new EntityNotFoundException());

        Mockito.verify(sensorRepository, Mockito.never()).update(sensor);
        Assertions.assertThrows(EntityNotFoundException.class,
            () -> sensorService.update(id, sensor));
    }

    @Test
    void delete() {
        long id = 1L;
        Sensor sensor = defaultSensorBuilder.build();
        Mockito.when(sensorRepository.findById(id))
            .thenReturn(sensor);

        sensorService.delete(id);

        Mockito.verify(sensorRepository).delete(sensor);
    }

    @Test
    void deleteUnknownSensor() {
        long id = -1L;
        Mockito.when(sensorRepository.findById(id))
            .thenThrow(new EntityNotFoundException());

        Assertions.assertThrows(EntityNotFoundException.class,
            () -> sensorService.delete(id));
    }

    private static SensorBuilder getDefaultSensorBuilder() {
        Type type = new Type();
        type.setId(1L);
        type.setName("Pressure");

        Unit unit = new Unit();
        unit.setId(1L);
        unit.setName("bar");

        return Sensor.builder()
            .id(1L)
            .name("Sensor 1")
            .model("PC33-56")
            .type(type)
            .rangeFrom(0)
            .rangeTo(16)
            .unit(unit)
            .location("Room 1")
            .description("Description");
    }
}