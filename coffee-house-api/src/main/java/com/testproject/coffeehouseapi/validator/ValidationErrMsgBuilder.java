package com.testproject.coffeehouseapi.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Component
public class ValidationErrMsgBuilder {

    public static String buildFieldErrMsg(BindingResult bindingResult) {
        StringBuilder errMsg = new StringBuilder();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError e : fieldErrors) {
            errMsg.append(e.getField())
                    .append(" - ").append(e.getDefaultMessage())
                    .append("; ");
        }
        return errMsg.toString().trim();
    }
}
