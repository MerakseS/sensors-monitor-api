package com.innowisegroup.sensorsmonitorapi.service;

import com.innowisegroup.sensorsmonitorapi.entity.User;

import jakarta.ws.rs.core.UriInfo;

public interface SecurityService {

    User authenticate(String login, String password);

    String generateToken(User user);

    String extractUsername(String token);

    String extractAuthorities(String token);

    boolean isUriAllowed(UriInfo uriInfo);
}
