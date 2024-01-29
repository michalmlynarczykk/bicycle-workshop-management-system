package com.michalmlynarczyk.orderservice.exception;

import java.text.MessageFormat;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(final String message, final Object... args) {
        super(MessageFormat.format(message, args));
    }
}