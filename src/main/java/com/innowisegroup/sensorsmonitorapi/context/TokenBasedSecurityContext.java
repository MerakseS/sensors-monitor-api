package com.innowisegroup.sensorsmonitorapi.context;

import java.security.Principal;
import java.util.List;

import jakarta.ws.rs.core.SecurityContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TokenBasedSecurityContext implements SecurityContext {

    private static final String AUTHENTICATION_SCHEME = "Bearer";

    private final String username;
    private final List<String> roles;
    private final boolean isSecured;

    @Override
    public Principal getUserPrincipal() {
        return () -> username;
    }

    @Override
    public boolean isUserInRole(String role) {
        return roles.contains(role);
    }

    @Override
    public boolean isSecure() {
        return isSecured;
    }

    @Override
    public String getAuthenticationScheme() {
        return AUTHENTICATION_SCHEME;
    }
}
