package com.innowisegroup.sensorsmonitorapi.service.impl;

import java.util.Date;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import com.innowisegroup.sensorsmonitorapi.entity.User;
import com.innowisegroup.sensorsmonitorapi.exception.impl.BadCredentialsException;
import com.innowisegroup.sensorsmonitorapi.repository.UserRepository;
import com.innowisegroup.sensorsmonitorapi.service.SecurityService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.UriInfo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Stateless
@NoArgsConstructor
@AllArgsConstructor(onConstructor_ = @Inject)
public class SecurityServiceImpl implements SecurityService {

    private static final String AUTHORITIES_KEY = "authorities";
    private static final String SECRET_KEY = "secret_key";
    private static final long SESSION_TIME = 120000000;

    private static final List<String> allowedUriList = List.of("authenticate");

    private UserRepository userRepository;

    @Override
    public User authenticate(String login, String password) {
        try {
            User user = userRepository.findByLogin(login);
            if (!BCrypt.checkpw(password, user.getPassword())) {
                throw new BadCredentialsException("Incorrect password");
            }

            return user;
        }
        catch (Exception e) {
            throw new BadCredentialsException(e);
        }
    }

    @Override
    public boolean isUriAllowed(UriInfo uriInfo) {
        for (String allowedUri : allowedUriList) {
            if (uriInfo.getPath().equals(allowedUri)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String generateToken(User user) {
        Claims claims = createClaims(user);
        return createToken(user, claims);
    }

    @Override
    public String extractUsername(String token) {
        Claims claims = parseClaims(token);
        return claims.getSubject();
    }

    @Override
    public String extractAuthorities(String token) {
        Claims claims = parseClaims(token);
        return (String) claims.get(AUTHORITIES_KEY);
    }

    private String createToken(User user, Claims claims) {
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(user.getLogin())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + SESSION_TIME))
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
            .compact();
    }

    private Claims createClaims(User user) {
        Claims claims = new DefaultClaims();
        claims.put(AUTHORITIES_KEY, user.getRole());

        return claims;
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
            .setSigningKey(SECRET_KEY)
            .parseClaimsJws(token)
            .getBody();
    }
}
