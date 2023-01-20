package com.testproject.coffeehouseapi.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.testproject.coffeehouseapi.exception.ExceptionMessage;
import com.testproject.coffeehouseapi.exception.MessageConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtRequestAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    public JwtRequestAuthenticationFilter(JwtUtil jwtUtil, UserDetailsServiceImpl userDetailsServiceImpl) {
        this.jwtUtil = jwtUtil;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(jwtUtil.getHeader());
        if (authHeader != null && authHeader.startsWith(jwtUtil.getTokenPrefix())) {
            String jwt = authHeader.replace(jwtUtil.getTokenPrefix(), "");
            String phone = "";
            try {
                phone = jwtUtil.validateToken(jwt);
                UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(phone);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        userDetails.getPassword(),
                        userDetails.getAuthorities());
                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (JWTVerificationException e) {
                log.info("Invalid JWT Token in Authorization Header : {}", jwt);
                HttpResponseWriter.writeResponse(response,
                        ExceptionMessage.setMessage(MessageConstant.INVALID_JWT_TOKEN),
                        HttpServletResponse.SC_UNAUTHORIZED);
            } catch (UsernameNotFoundException e) {
                log.info("User with phone number {} was not found", phone);
                HttpResponseWriter.writeResponse(response,
                        e.getMessage(),
                        HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
        filterChain.doFilter(request, response);
    }
}
