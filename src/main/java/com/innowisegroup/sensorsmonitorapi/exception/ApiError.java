package com.innowisegroup.sensorsmonitorapi.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.ws.rs.core.Response;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {

    private Response.Status status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    private String message;

    private String debugMessage;

    public ApiError() {
        timestamp = LocalDateTime.now();
    }

    public ApiError(Response.Status status) {
        this();
        this.status = status;
    }

    public ApiError(Response.Status status, Throwable throwable) {
        this(status);
        this.message = "Unexpected error";
        this.debugMessage = throwable.getMessage();
    }

    public ApiError(Response.Status status, String message, Throwable throwable) {
        this(status);
        this.message = message;
        this.debugMessage = throwable.getMessage();
    }
}
