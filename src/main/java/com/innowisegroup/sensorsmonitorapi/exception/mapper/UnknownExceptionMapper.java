package com.innowisegroup.sensorsmonitorapi.exception.mapper;

import com.innowisegroup.sensorsmonitorapi.exception.ApiError;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

@Provider
@Slf4j
public class UnknownExceptionMapper extends AbstractExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        log.error("Unexpected error", exception);
        ApiError apiError = new ApiError(Response.Status.INTERNAL_SERVER_ERROR, exception);
        return buildResponse(apiError);
    }
}
