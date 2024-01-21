package com.michalmlynarczyk.authenticationservice.exception;

import java.text.MessageFormat;

public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException(final String message, final Object... args) {
        super(MessageFormat.format(message, args));
    }
}
