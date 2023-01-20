package com.testproject.coffeehouseapi.interceptor;

import com.testproject.coffeehouseapi.exception.RequestStatusException;
import com.testproject.coffeehouseapi.model.User;
import com.testproject.coffeehouseapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Slf4j
@Component
public class CheckAuthInterceptor implements HandlerInterceptor {

    private final UserService userService;

    public CheckAuthInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String phone = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> optionalUser = userService.findByPhone(phone);
        if (optionalUser.isPresent()) {
            request.setAttribute("user", optionalUser.get());
            return true;
        } else {
            throw new RequestStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}
