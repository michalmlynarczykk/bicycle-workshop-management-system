package com.michalmlynarczyk.authenticationservice.exception;

import java.text.MessageFormat;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(final String message, final Object... args) {
        super(MessageFormat.format(message, args));
    }
}
