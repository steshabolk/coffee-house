package com.testproject.coffeehouseapi.validator;

import com.testproject.coffeehouseapi.exception.ExceptionMessage;
import com.testproject.coffeehouseapi.exception.MessageConstant;
import com.testproject.coffeehouseapi.model.User;
import com.testproject.coffeehouseapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
@Component
public class UserValidator implements Validator {

    private final UserService userService;

    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(User.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if (userService.findByPhone(user.getPhone()).isPresent()) {
            log.info("Fail while register new user. Phone number {} already in use", user.getPhone());

            errors.rejectValue("phone", "", ExceptionMessage.setMessage(MessageConstant.PHONE_ALREADY_USED, user.getPhone()));
        }
    }
}
