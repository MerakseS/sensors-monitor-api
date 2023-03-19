package com.innowisegroup.sensorsmonitorapi.exception;

import java.time.LocalDateTime;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.json.bind.annotation.JsonbNillable;
import jakarta.ws.rs.core.Response;
import lombok.Data;

@Data
@JsonbNillable(value = false)
public class ApiError {

    private Response.Status status;

    @JsonbDateFormat(value = "dd-MM-yyyy hh:mm:ss")
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
