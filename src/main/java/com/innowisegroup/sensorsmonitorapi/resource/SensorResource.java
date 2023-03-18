package com.innowisegroup.sensorsmonitorapi.resource;

import java.util.List;

import com.innowisegroup.sensorsmonitorapi.dto.SensorRequestDto;
import com.innowisegroup.sensorsmonitorapi.dto.SensorResponseDto;
import com.innowisegroup.sensorsmonitorapi.entity.Sensor;
import com.innowisegroup.sensorsmonitorapi.mapper.SensorMapper;
import com.innowisegroup.sensorsmonitorapi.service.SensorService;

import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/sensors")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SensorResource {

    @Inject
    private SensorMapper sensorMapper;

    @EJB
    private SensorService sensorService;

    @POST
    public Response create(@Valid SensorRequestDto sensorRequestDto) {
        Sensor sensor = sensorMapper.mapRequestDtoToSensor(sensorRequestDto);
        sensor = sensorService.create(sensor);
        SensorResponseDto sensorResponseDto = sensorMapper.mapSensorToResponseDto(sensor);

        return Response
            .status(Response.Status.CREATED)
            .entity(sensorResponseDto)
            .build();
    }

    @GET
    public Response get() {
        List<Sensor> sensorList = sensorService.getAll();
        List<SensorResponseDto> sensorResponseDtoList = sensorMapper
            .mapSensorListToResponseDtoList(sensorList);

        return Response.ok(sensorResponseDtoList).build();
    }

    @GET
    @Path("{id}")
    public Response get(@PathParam("id") long id) {
        Sensor sensor = sensorService.get(id);
        SensorResponseDto sensorResponseDto = sensorMapper.mapSensorToResponseDto(sensor);
        return Response.ok(sensorResponseDto).build();
    }
}
