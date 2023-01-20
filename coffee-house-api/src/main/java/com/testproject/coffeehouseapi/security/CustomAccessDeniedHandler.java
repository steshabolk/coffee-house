package com.testproject.coffeehouseapi.security;

import com.testproject.coffeehouseapi.exception.ExceptionMessage;
import com.testproject.coffeehouseapi.exception.MessageConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.info("Authorization Error. Access is denied. No access permissions to the resource.");
        HttpResponseWriter.writeResponse(response,
                ExceptionMessage.setMessage(MessageConstant.ACCESS_DENIED),
                HttpServletResponse.SC_FORBIDDEN);
    }
}
