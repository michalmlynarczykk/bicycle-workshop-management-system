package com.michalmlynarczyk.workshopmanagementservice.exception.handler;

import com.michalmlynarczyk.workshopmanagementservice.controller.WorkshopControllerImpl;
import com.michalmlynarczyk.workshopmanagementservice.exception.WorkshopAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Order(1)
@RestControllerAdvice(assignableTypes = WorkshopControllerImpl.class)
public class WorkshopControllerHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(WorkshopAlreadyExistsException.class)
    void handleWorkshopAlreadyExistsException(final WorkshopAlreadyExistsException exception) {
        log.error("handleWorkshopAlreadyExistsException() - exception", exception);
    }
}
