package com.innowisegroup.sensorsmonitorapi.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.Mockito;

import com.innowisegroup.sensorsmonitorapi.entity.User;
import com.innowisegroup.sensorsmonitorapi.exception.impl.BadCredentialsException;
import com.innowisegroup.sensorsmonitorapi.repository.UserRepository;
import com.innowisegroup.sensorsmonitorapi.service.impl.SecurityServiceImpl;

import jakarta.persistence.EntityNotFoundException;

class SecurityServiceTest {

    private UserRepository userRepository;
    private SecurityService securityService;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        securityService = new SecurityServiceImpl(userRepository);
    }

    @Test
    void authenticate() {
        String login = "user";
        String password = "password";

        User user = new User();
        user.setLogin(login);
        user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));

        Mockito.when(userRepository.findByLogin(login))
            .thenReturn(user);

        User authenticatedUser = securityService.authenticate(login, password);

        Assertions.assertEquals(login, authenticatedUser.getLogin());
    }

    @Test
    void authenticateUnknownUser() {
        String login = "unknown";
        String password = "password";

        Mockito.when(userRepository.findByLogin(login))
            .thenThrow(EntityNotFoundException.class);

        Assertions.assertThrows(BadCredentialsException.class,
            () -> securityService.authenticate(login, password));
    }

    @Test
    void authenticateIncorrectPassword() {
        String login = "user";
        String password = "incorrectPassword";

        User user = new User();
        user.setLogin(login);
        user.setPassword(BCrypt.hashpw("password", BCrypt.gensalt()));

        Mockito.when(userRepository.findByLogin(login))
            .thenReturn(user);

        Assertions.assertThrows(BadCredentialsException.class,
            () -> securityService.authenticate(login, password));
    }
}