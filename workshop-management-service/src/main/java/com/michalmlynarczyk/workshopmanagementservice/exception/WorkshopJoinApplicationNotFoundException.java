package com.michalmlynarczyk.workshopmanagementservice.exception;

import java.text.MessageFormat;

public class WorkshopJoinApplicationNotFoundException extends RuntimeException {

    public WorkshopJoinApplicationNotFoundException(final String message, final Object... args) {
        super(MessageFormat.format(message, args));
    }
}
