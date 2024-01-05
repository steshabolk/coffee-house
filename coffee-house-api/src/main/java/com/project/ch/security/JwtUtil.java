package com.project.ch.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.project.ch.entity.User;
import com.project.ch.exception.ApiError;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Getter
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.ttl}")
    private long ttl;

    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String AUTH_HEADER = "Authorization";

    public void setAuthHeader(HttpServletResponse response, User user) {
        String token = generateToken(user.getId(), user.getPhone(), user.getName(), user.getRole().name());
        response.setHeader(AUTH_HEADER, TOKEN_PREFIX + token);
    }

    public String generateToken(Long id, String phone, String name, String role) {
        return JWT.create()
                .withSubject(String.valueOf(id))
                .withClaim("phone", phone)
                .withClaim("name", name)
                .withClaim("role", role)
                .withExpiresAt(Date.from(Instant.now().plus(ttl, ChronoUnit.MINUTES)))
                .sign(Algorithm.HMAC256(secret));
    }

    public DecodedJWT validateToken(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(secret))
                    .build()
                    .verify(token);
        } catch (TokenExpiredException ex) {
            throw ApiError.TOKEN_EXPIRED.toException();
        } catch (Exception ex) {
            throw ApiError.INVALID_TOKEN.toException();
        }
    }

    public String getPhoneFromJWT(DecodedJWT jwt) {
        String phone = jwt.getClaim("phone").asString();
        if (phone == null) throw ApiError.INVALID_TOKEN.toException();
        return phone;
    }

    public String getTokenPrefix() {
        return TOKEN_PREFIX;
    }

    public String getAuthHeader() {
        return AUTH_HEADER;
    }
}
