package com.michalmlynarczyk.workshopmanagementservice.exception;

import java.text.MessageFormat;

public class WorkshopAlreadyExistsException extends RuntimeException {

    public WorkshopAlreadyExistsException(final String message, final Object... args) {
        super(MessageFormat.format(message, args));
    }
}
