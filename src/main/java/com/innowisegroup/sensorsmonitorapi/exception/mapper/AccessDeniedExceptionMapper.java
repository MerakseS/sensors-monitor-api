package com.innowisegroup.sensorsmonitorapi.exception.mapper;

import com.innowisegroup.sensorsmonitorapi.exception.ApiError;
import com.innowisegroup.sensorsmonitorapi.exception.impl.AccessDeniedException;

import jakarta.ws.rs.core.Response;

public class AccessDeniedExceptionMapper extends AbstractExceptionMapper<AccessDeniedException> {

    @Override
    public Response toResponse(AccessDeniedException exception) {
        ApiError apiError = new ApiError(
            Response.Status.FORBIDDEN,
            "Forbidden",
            exception);

        return buildResponse(apiError);
    }
}
