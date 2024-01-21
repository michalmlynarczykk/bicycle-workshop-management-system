package com.michalmlynarczyk.workshopmanagementservice.exception;

import java.text.MessageFormat;

public class WorkshopNotFoundException extends RuntimeException {

    public WorkshopNotFoundException(final String message, final Object... args) {
        super(MessageFormat.format(message, args));
    }
}
