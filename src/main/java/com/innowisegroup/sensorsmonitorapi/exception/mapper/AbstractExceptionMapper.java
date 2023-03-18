package com.innowisegroup.sensorsmonitorapi.exception.mapper;

import com.innowisegroup.sensorsmonitorapi.exception.ApiError;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public abstract class AbstractExceptionMapper<E extends Throwable> implements ExceptionMapper<E> {

    protected Response buildResponse(ApiError apiError) {
        return Response
            .status(apiError.getStatus())
            .entity(apiError)
            .type(MediaType.APPLICATION_JSON_TYPE)
            .build();
    }
}
