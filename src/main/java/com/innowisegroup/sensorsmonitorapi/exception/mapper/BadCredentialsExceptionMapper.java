package com.innowisegroup.sensorsmonitorapi.exception.mapper;

import com.innowisegroup.sensorsmonitorapi.exception.ApiError;
import com.innowisegroup.sensorsmonitorapi.exception.impl.BadCredentialsException;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
public class BadCredentialsExceptionMapper extends
    AbstractExceptionMapper<BadCredentialsException> {

    @Override
    public Response toResponse(BadCredentialsException exception) {
        ApiError apiError = new ApiError(
            Response.Status.UNAUTHORIZED,
            "Incorrect login or password",
            exception);

        return buildResponse(apiError);
    }
}
