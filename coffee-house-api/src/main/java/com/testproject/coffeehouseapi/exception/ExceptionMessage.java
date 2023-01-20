package com.testproject.coffeehouseapi.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ExceptionMessage {

    private static MessageSource messageSource;

    @Autowired
    public ExceptionMessage(MessageSource messageSource) {
        ExceptionMessage.messageSource = messageSource;
    }

    public static String setMessage(String code) {
        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }

    public static String setMessage(String code, String... args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
