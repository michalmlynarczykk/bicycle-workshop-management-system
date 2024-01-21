package com.michalmlynarczyk.workshopmanagementservice.exception.handler;

import com.michalmlynarczyk.workshopmanagementservice.controller.WorkshopControllerImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(assignableTypes = WorkshopControllerImpl.class)
public class WorkshopControllerHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(Exception.class)
    void handleException(final Exception exception) {
        log.error("handleException() - exception", exception);
    }
}
