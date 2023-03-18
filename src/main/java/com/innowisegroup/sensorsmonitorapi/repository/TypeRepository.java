package com.innowisegroup.sensorsmonitorapi.repository;

import com.innowisegroup.sensorsmonitorapi.entity.Type;

public interface TypeRepository {

    Type findById(long id);
}
