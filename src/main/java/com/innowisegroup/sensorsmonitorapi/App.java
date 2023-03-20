package com.innowisegroup.sensorsmonitorapi;

import java.util.Set;

import com.innowisegroup.sensorsmonitorapi.exception.mapper.ConstraintViolationExceptionMapper;
import com.innowisegroup.sensorsmonitorapi.exception.mapper.EntityNotFoundExceptionMapper;
import com.innowisegroup.sensorsmonitorapi.exception.mapper.UnknownExceptionMapper;
import com.innowisegroup.sensorsmonitorapi.resource.SensorResource;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
public class App extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        return Set.of(
            SensorResource.class,
            EntityNotFoundExceptionMapper.class,
            ConstraintViolationExceptionMapper.class,
            UnknownExceptionMapper.class
        );
    }
}