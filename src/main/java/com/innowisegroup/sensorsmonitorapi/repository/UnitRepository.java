package com.innowisegroup.sensorsmonitorapi.repository;

import com.innowisegroup.sensorsmonitorapi.entity.Unit;

public interface UnitRepository {

    Unit findById(long id);
}
