package com.project.ch.validator;

import com.project.ch.dto.request.RegisterRequest;
import com.project.ch.repository.UserRepository;
import com.project.ch.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

class UniquePhoneValidator implements ConstraintValidator<UniquePhone, RegisterRequest> {

    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    UniquePhoneValidator(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public boolean isValid(RegisterRequest value, ConstraintValidatorContext context) {
        if (value == null) return true;
        Boolean isExists = userRepository.existsByPhone(userService.parsePhone(value.getPhone()));
        if (isExists) {
            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("phone")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
