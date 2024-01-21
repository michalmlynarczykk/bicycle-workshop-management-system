package com.michalmlynarczyk.workshopmanagementservice.exception;

import java.text.MessageFormat;

public class WorkshopJoinRequestAlreadyExistsException extends RuntimeException {

    public WorkshopJoinRequestAlreadyExistsException(final String message, final Object... args) {
        super(MessageFormat.format(message, args));
    }
}
