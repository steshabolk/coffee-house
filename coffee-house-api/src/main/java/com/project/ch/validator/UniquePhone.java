package com.project.ch.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = UniquePhoneValidator.class)
public @interface UniquePhone {
    String message() default "{api.validation.phoneNotUnique.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

