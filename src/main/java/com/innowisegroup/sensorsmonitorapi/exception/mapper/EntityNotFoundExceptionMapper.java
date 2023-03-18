package com.innowisegroup.sensorsmonitorapi.exception.mapper;

import com.innowisegroup.sensorsmonitorapi.exception.ApiError;

import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
public class EntityNotFoundExceptionMapper extends AbstractExceptionMapper<EntityNotFoundException> {

    @Override
    public Response toResponse(EntityNotFoundException exception) {
        ApiError apiError = new ApiError(
            Response.Status.NOT_FOUND,
            "Entity not found",
            exception);

        return buildResponse(apiError);
    }
}
