package com.project.ch.service;

import com.project.ch.dto.request.LoginRequest;
import com.project.ch.dto.request.RegisterRequest;
import com.project.ch.entity.Role;
import com.project.ch.entity.User;
import com.project.ch.exception.ApiError;
import com.project.ch.repository.UserRepository;
import com.project.ch.security.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public User getByPhone(String phone) {
        return userRepository.findByPhone(phone).orElseThrow(() -> {
            throw ApiError.USER_NOT_FOUND.toException(phone);
        });
    }

    @Transactional
    public void register(RegisterRequest registerRequest) {
        User user = User.builder()
                .name(registerRequest.getName())
                .phone(parsePhone(registerRequest.getPhone()))
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.ROLE_USER)
                .build();
        User saved = userRepository.save(user);
        log.debug("save new User{id={}}", saved.getId());
    }

    public User login(LoginRequest loginRequest) {
        User user = getByPhone(loginRequest.getPhone());
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw ApiError.WRONG_PASSWORD.toException();
        }
        log.debug("successful authentication of the user{id={}}", user.getId());
        return user;
    }

    @Transactional
    public User changeName(String name) {
        User user = getUserFromAuthentication();
        user.setName(name);
        log.debug("user{id={}} changed name", user.getId());
        return userRepository.save(user);
    }

    @Transactional
    public void changePassword(String password) {
        User user = getUserFromAuthentication();
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        log.debug("user{id={}} changed password", user.getId());
    }

    public String parsePhone(String phone) {
        return "7" + phone.substring(phone.length() - 10);
    }

    public User getUserFromAuthentication() {
        UserDetailsImpl authentication = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return authentication.getUser();
    }
}
