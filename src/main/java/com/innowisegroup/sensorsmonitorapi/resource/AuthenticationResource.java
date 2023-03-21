package com.innowisegroup.sensorsmonitorapi.resource;

import com.innowisegroup.sensorsmonitorapi.dto.AuthRequestDto;
import com.innowisegroup.sensorsmonitorapi.dto.AuthResponseDto;
import com.innowisegroup.sensorsmonitorapi.entity.User;
import com.innowisegroup.sensorsmonitorapi.service.AuthenticationService;

import jakarta.ejb.EJB;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/authenticate")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticationResource {

    @EJB
    private AuthenticationService authenticationService;

    @POST
    public Response authenticate(@Valid AuthRequestDto authRequestDto) {
        User user = authenticationService.authenticate(
            authRequestDto.getLogin(), authRequestDto.getPassword());

        String token = authenticationService.generateToken(user);

        return Response.ok(new AuthResponseDto(token)).build();
    }
}
