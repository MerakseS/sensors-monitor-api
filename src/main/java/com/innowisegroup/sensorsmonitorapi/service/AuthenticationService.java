package com.innowisegroup.sensorsmonitorapi.service;

import com.innowisegroup.sensorsmonitorapi.entity.User;

public interface AuthenticationService {

    User authenticate(String login, String password);

    String generateToken(User user);

    String extractUsername(String token);

    String extractAuthorities(String token);
}
