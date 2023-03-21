package com.innowisegroup.sensorsmonitorapi.filter;

import java.lang.reflect.Method;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

import com.innowisegroup.sensorsmonitorapi.exception.impl.AccessDeniedException;
import com.innowisegroup.sensorsmonitorapi.service.SecurityService;

import jakarta.annotation.Priority;
import jakarta.ejb.EJB;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    @EJB
    private SecurityService securityService;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        if (securityService.isUriAllowed(requestContext.getUriInfo())) {
            return;
        }

        Method method = resourceInfo.getResourceMethod();

        if (method.isAnnotationPresent(DenyAll.class)) {
            refuseRequest();
        }

        RolesAllowed rolesAllowed = method.getAnnotation(RolesAllowed.class);
        if (rolesAllowed != null) {
            performAuthorization(rolesAllowed.value(), requestContext);
            return;
        }

        if (method.isAnnotationPresent(PermitAll.class)) {
            return;
        }

        Class<?> resourceClass = resourceInfo.getResourceClass();

        rolesAllowed = resourceClass.getAnnotation(RolesAllowed.class);
        if (rolesAllowed != null) {
            performAuthorization(rolesAllowed.value(), requestContext);
            return;
        }

        if (resourceClass.isAnnotationPresent(PermitAll.class)) {
            return;
        }

        if (isNotAuthenticated(requestContext)) {
            refuseRequest();
        }
    }

    private void performAuthorization(String[] rolesAllowed,
        ContainerRequestContext requestContext) {

        if (rolesAllowed.length > 0 && isNotAuthenticated(requestContext)) {
            refuseRequest();
        }

        for (String roleAllowed : rolesAllowed) {
            if (requestContext.getSecurityContext().isUserInRole(roleAllowed)) {
                return;
            }
        }

        refuseRequest();
    }

    private boolean isNotAuthenticated(ContainerRequestContext requestContext) {
        return requestContext.getSecurityContext().getUserPrincipal() == null;
    }

    private static void refuseRequest() throws AccessDeniedException {
        throw new AccessDeniedException("You don't have permissions to perform this action.");
    }
}
