package com.innowisegroup.sensorsmonitorapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.innowisegroup.sensorsmonitorapi.dto.SensorRequestDto;
import com.innowisegroup.sensorsmonitorapi.dto.SensorResponseDto;
import com.innowisegroup.sensorsmonitorapi.entity.Sensor;
import com.innowisegroup.sensorsmonitorapi.entity.Type;
import com.innowisegroup.sensorsmonitorapi.entity.Unit;
import com.innowisegroup.sensorsmonitorapi.repository.TypeRepository;
import com.innowisegroup.sensorsmonitorapi.repository.UnitRepository;

import jakarta.inject.Inject;

@Mapper(componentModel = "jakarta")
public abstract class SensorMapper {

    @Inject
    protected TypeRepository typeRepository;

    @Inject
    protected UnitRepository unitRepository;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "type", source = "typeId")
    @Mapping(target = "unit", source = "unitId")
    public abstract Sensor mapRequestDtoToSensor(SensorRequestDto requestDto);

    @Mapping(target = "type", source = "type.name")
    @Mapping(target = "unit", source = "unit.name")
    public abstract SensorResponseDto mapSensorToResponseDto(Sensor sensor);

    protected Type getTypeById(long typeId) {
        return typeRepository.findById(typeId);
    }

    protected Unit getUnitById(long unitId) {
        return unitRepository.findById(unitId);
    }
}
