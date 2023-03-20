package com.innowisegroup.sensorsmonitorapi.exception.mapper;

import javax.validation.ConstraintViolationException;

import com.innowisegroup.sensorsmonitorapi.exception.ApiError;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationExceptionMapper extends
    AbstractExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        ApiError apiError = new ApiError(Response.Status.BAD_REQUEST);
        apiError.setMessage("Validation error");
        apiError.addValidationErrors(exception.getConstraintViolations());

        return buildResponse(apiError);
    }
}
