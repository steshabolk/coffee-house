package com.testproject.coffeehouseapi.service;

import com.testproject.coffeehouseapi.exception.ExceptionMessage;
import com.testproject.coffeehouseapi.exception.MessageConstant;
import com.testproject.coffeehouseapi.exception.RequestException;
import com.testproject.coffeehouseapi.model.Role;
import com.testproject.coffeehouseapi.model.User;
import com.testproject.coffeehouseapi.repository.UserRepository;
import com.testproject.coffeehouseapi.util.ResponseHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private final ResponseHelper responseHelper;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, ResponseHelper responseHelper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.responseHelper = responseHelper;
    }

    public Optional<User> findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    public User getUserByPhone(String phone) {
        return userRepository.findByPhone(phone).orElseThrow(() -> {
            log.info("Error when finding an user with phone {}", phone);
            throw new RequestException(ExceptionMessage.setMessage(MessageConstant.PHONE_NOT_FOUND,
                    phone), HttpStatus.BAD_REQUEST);
        });
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
        userRepository.saveAndFlush(user);

        log.info("User {} changed name from '{}' to '{}' at {}",
                user.getPhone(), oldName, user.getName(), responseHelper.currentDateTime());
    }

    @Transactional
    public void changePassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.saveAndFlush(user);

        log.info("User {} changed password at {}", user.getPhone(), responseHelper.currentDateTime());
    }
}
