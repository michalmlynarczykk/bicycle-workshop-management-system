package com.michalmlynarczyk.workshopmanagementservice.exception.handler;

import com.michalmlynarczyk.workshopmanagementservice.controller.WorkshopJoinControllerImpl;
import com.michalmlynarczyk.workshopmanagementservice.exception.WorkshopJoinApplicationAlreadyExistsException;
import com.michalmlynarczyk.workshopmanagementservice.exception.WorkshopJoinApplicationNotFoundException;
import com.michalmlynarczyk.workshopmanagementservice.exception.WorkshopNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Order(1)
@RestControllerAdvice(assignableTypes = WorkshopJoinControllerImpl.class)
public class WorkshopJoinControllerHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {
            WorkshopNotFoundException.class,
            WorkshopJoinApplicationNotFoundException.class
    })
    void handleNotFoundException(final Exception exception) {
        log.debug("handleNotFoundException() - exception", exception);
    }


    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(value = {
            WorkshopJoinApplicationAlreadyExistsException.class,
            IllegalStateException.class
    })
    void handleConflict(final Exception exception) {
        log.debug("handleConflict() - exception", exception);
    }
}
