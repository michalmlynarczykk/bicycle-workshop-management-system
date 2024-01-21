package com.michalmlynarczyk.authenticationservice.exception.handler;

import com.michalmlynarczyk.authenticationservice.controller.AuthenticationControllerImpl;
import com.michalmlynarczyk.authenticationservice.exception.RoleNotFoundException;
import com.michalmlynarczyk.authenticationservice.exception.UserAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Order(1)
@RestControllerAdvice(assignableTypes = AuthenticationControllerImpl.class)
public class AuthenticationControllerHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserAlreadyExistsException.class)
    void handleUserAlreadyExistsException(final UserAlreadyExistsException exception) {
        log.debug("handleUserAlreadyExistsException() - exception", exception);
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RoleNotFoundException.class)
    void handleRoleNotFoundException(final RoleNotFoundException exception) {
        log.error("handleRoleNotFoundException() - missing configuration - exception ", exception);
    }
}
