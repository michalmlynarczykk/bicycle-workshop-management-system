package com.michalmlynarczyk.orderservice.exception.handler;

import com.michalmlynarczyk.orderservice.controller.OrderControllerImpl;
import com.michalmlynarczyk.orderservice.exception.OrderNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Order(1)
@RestControllerAdvice(assignableTypes = OrderControllerImpl.class)
public class OrderControllerHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(OrderNotFoundException.class)
    void handleOrderNotFoundException(final OrderNotFoundException exception) {
        log.debug("handleOrderNotFoundException() - exception", exception);
    }
}
