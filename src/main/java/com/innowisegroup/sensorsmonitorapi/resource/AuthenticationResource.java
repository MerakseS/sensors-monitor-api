package com.innowisegroup.sensorsmonitorapi.resource;

import com.innowisegroup.sensorsmonitorapi.dto.AuthRequestDto;
import com.innowisegroup.sensorsmonitorapi.dto.AuthResponseDto;
import com.innowisegroup.sensorsmonitorapi.entity.User;
import com.innowisegroup.sensorsmonitorapi.service.SecurityService;

import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Path("/authenticate")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@NoArgsConstructor
@AllArgsConstructor(onConstructor_ = @Inject)
public class AuthenticationResource {

    private SecurityService securityService;

    @POST
    @PermitAll
    public Response authenticate(@Valid AuthRequestDto authRequestDto) {
        User user = securityService.authenticate(
            authRequestDto.getLogin(), authRequestDto.getPassword());

        String token = securityService.generateToken(user);

        return Response.ok(new AuthResponseDto(token)).build();
    }
}
