package com.michalmlynarczyk.workshopmanagementservice.exception.handler;

import com.michalmlynarczyk.workshopmanagementservice.controller.WorkshopJoinControllerImpl;
import com.michalmlynarczyk.workshopmanagementservice.exception.WorkshopJoinRequestAlreadyExistsException;
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
    @ExceptionHandler(WorkshopNotFoundException.class)
    void handleWorkshopNotFoundException(final WorkshopNotFoundException exception) {
        log.debug("handleWorkshopNotFoundException() - exception", exception);
    }


    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(WorkshopJoinRequestAlreadyExistsException.class)
    void handleWorkshopJoinRequestAlreadyExistsException(final WorkshopJoinRequestAlreadyExistsException exception) {
        log.debug("handleWorkshopJoinRequestAlreadyExistsException() - exception", exception);
    }
}
