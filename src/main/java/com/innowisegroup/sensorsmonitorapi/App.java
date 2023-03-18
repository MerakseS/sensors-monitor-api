package com.innowisegroup.sensorsmonitorapi;

import java.util.HashSet;
import java.util.Set;

import com.innowisegroup.sensorsmonitorapi.resource.SensorResource;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
public class App extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(SensorResource.class);
        return resources;
    }
}