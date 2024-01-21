package com.michalmlynarczyk.common.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Constant {

    public static final String WORKSHOP_SERVICE_BROKER_EXCHANGE = "workshop";

    public static final String WORKSHOP_CREATED_ROUTING_KEY = "workshop.created";
}
