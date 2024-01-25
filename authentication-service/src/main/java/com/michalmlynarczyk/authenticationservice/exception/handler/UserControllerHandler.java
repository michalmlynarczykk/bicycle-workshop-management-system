package com.michalmlynarczyk.authenticationservice.exception.handler;

import com.michalmlynarczyk.authenticationservice.controller.UserControllerImpl;
import com.michalmlynarczyk.authenticationservice.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Order(1)
@RestControllerAdvice(assignableTypes = UserControllerImpl.class)
public class UserControllerHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    void handleUserNotFoundException(final UserNotFoundException exception) {
        log.debug("handleUserNotFoundException() - exception", exception);
    }

}
