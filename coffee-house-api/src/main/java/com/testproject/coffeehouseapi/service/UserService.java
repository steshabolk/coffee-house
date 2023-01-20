package com.testproject.coffeehouseapi.service;

import com.testproject.coffeehouseapi.model.*;
import com.testproject.coffeehouseapi.repository.OrderDetailsRepository;
import com.testproject.coffeehouseapi.repository.OrderRepository;
import com.testproject.coffeehouseapi.repository.UserRepository;
import com.testproject.coffeehouseapi.security.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public Optional<User> findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    @Transactional
    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_USER);
        userRepository.save(user);
        log.info("New user has been registered. Name : {}, Phone number : {}",
                user.getName(), user.getPhone());
    }

    @Transactional
    public void changeName(User user, String name) {
        String oldName = user.getName();
        user.setName(name);
        log.info("User {} changed name from '{}' to '{}'",
                user.getPhone(), oldName, user.getName());
    }

    @Transactional
    public void changePassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        log.info("User {} changed password", user.getPhone());
    }

    public HttpHeaders getAuthToken(User user) {
        String token = jwtUtil.generateToken(user.getPhone(), user.getName(), user.getRole().name());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(jwtUtil.getHeader(), jwtUtil.getTokenPrefix() + token);
        return responseHeaders;
    }
}
