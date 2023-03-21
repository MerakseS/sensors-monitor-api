package com.innowisegroup.sensorsmonitorapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequestDto {

    @NotBlank(message = "Login is required.")
    private String login;

    @NotBlank(message = "Name is required.")
    private String password;
}
