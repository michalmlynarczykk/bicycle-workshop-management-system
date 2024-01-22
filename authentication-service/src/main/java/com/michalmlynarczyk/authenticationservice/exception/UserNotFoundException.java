package com.michalmlynarczyk.authenticationservice.exception;

import java.text.MessageFormat;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(final String message, final Object... args) {
        super(MessageFormat.format(message, args));
    }
}
