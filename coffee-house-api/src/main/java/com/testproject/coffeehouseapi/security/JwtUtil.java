package com.testproject.coffeehouseapi.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Getter
@Component
public class JwtUtil {

    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expiration}")
    private long expiration;

    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String HEADER = "Authorization";

    public void setAuthHeader(HttpServletResponse response, String phone, String name, String role) {
        String token = generateToken(phone, name, role);
        response.setHeader(HEADER, TOKEN_PREFIX + token);
    }

    public String generateToken(String phone, String name, String role) {
        Date expirationDate = Date.from(Instant.now().plus(expiration, ChronoUnit.MINUTES));
        return JWT.create()
                .withSubject(phone)
                .withClaim("name", name)
                .withClaim("role", role)
                .withIssuedAt(new Date())
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC256(secret));
    }

    public String validateToken(String token) throws JWTVerificationException {
        DecodedJWT jwt = JWT.require(Algorithm.HMAC256(secret))
                .build()
                .verify(token);
        return jwt.getSubject();
    }

    public String getTokenPrefix() {
        return TOKEN_PREFIX;
    }

    public String getHeader() {
        return HEADER;
    }
}
