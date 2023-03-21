package com.innowisegroup.sensorsmonitorapi.filter;

import java.util.Arrays;
import java.util.List;

import com.innowisegroup.sensorsmonitorapi.context.TokenBasedSecurityContext;
import com.innowisegroup.sensorsmonitorapi.service.SecurityService;

import jakarta.annotation.Priority;
import jakarta.ejb.EJB;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    private static final String AUTHENTICATION_SCHEME = "Bearer ";
    private static final String REALM = "example";

    @EJB
    private SecurityService securityService;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        if (securityService.isUriAllowed(requestContext.getUriInfo())) {
            return;
        }

        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (!isTokenBasedAuthentication(authorizationHeader)) {
            abortWithUnauthorized(requestContext);
            return;
        }

        String token = authorizationHeader.substring(AUTHENTICATION_SCHEME.length());

        try {
            boolean isSecure = requestContext.getSecurityContext().isSecure();
            String username = securityService.extractUsername(token);
            String commaSeparatedAuthorities = securityService.extractAuthorities(token);
            List<String> authorities = Arrays.asList(commaSeparatedAuthorities.split(","));

            SecurityContext securityContext = new TokenBasedSecurityContext(username, authorities, isSecure);
            requestContext.setSecurityContext(securityContext);
        }
        catch (Exception e) {
            abortWithUnauthorized(requestContext);
        }
    }

    private boolean isTokenBasedAuthentication(String authorizationHeader) {
        return authorizationHeader != null &&
            authorizationHeader.startsWith(AUTHENTICATION_SCHEME);
    }

    private void abortWithUnauthorized(ContainerRequestContext requestContext) {
        String wwwAuthenticateHeader = String.format("%srealm=\"%s\"",
            AUTHENTICATION_SCHEME, REALM);

        requestContext.abortWith(Response
            .status(Response.Status.UNAUTHORIZED)
            .header(HttpHeaders.WWW_AUTHENTICATE, wwwAuthenticateHeader)
            .build());
    }
}
