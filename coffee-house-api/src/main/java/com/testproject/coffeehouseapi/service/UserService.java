package com.testproject.coffeehouseapi.service;

import com.testproject.coffeehouseapi.model.Role;
import com.testproject.coffeehouseapi.model.User;
import com.testproject.coffeehouseapi.repository.UserRepository;
import com.testproject.coffeehouseapi.security.JwtUtil;
import com.testproject.coffeehouseapi.util.ResponseHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final ResponseHelper responseHelper;

    @Autowired
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder, JwtUtil jwtUtil, ResponseHelper responseHelper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.responseHelper = responseHelper;
    }

    public Optional<User> findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    @Transactional
    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_USER);
        userRepository.save(user);
        log.info("New user has been registered at {}. Name : {}, Phone number : {}",
                responseHelper.currentDateTime(), user.getName(), user.getPhone());
    }

    @Transactional
    public void changeName(User user, String name) {
        String oldName = user.getName();
        user.setName(name);
        log.info("User {} changed name from '{}' to '{}' at {}",
                user.getPhone(), oldName, user.getName(), responseHelper.currentDateTime());
    }

    @Transactional
    public void changePassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.saveAndFlush(user);
        log.info("User {} changed password at {}", user.getPhone(), responseHelper.currentDateTime());
    }

    public HttpHeaders getAuthToken(User user) {
        String token = jwtUtil.generateToken(user.getPhone(), user.getName(), user.getRole().name());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(jwtUtil.getHeader(), jwtUtil.getTokenPrefix() + token);
        return responseHeaders;
    }
}
