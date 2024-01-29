package com.michalmlynarczyk.workshopmanagementservice.exception;

import java.text.MessageFormat;

public class WorkshopJoinApplicationAlreadyExistsException extends RuntimeException {

    public WorkshopJoinApplicationAlreadyExistsException(final String message, final Object... args) {
        super(MessageFormat.format(message, args));
    }
}
