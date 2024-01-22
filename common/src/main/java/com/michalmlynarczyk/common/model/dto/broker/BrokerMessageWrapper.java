package com.michalmlynarczyk.common.model.dto.broker;

import java.util.UUID;

public record BrokerMessageWrapper<T>(
        UUID messageId,
        T payload) {

    public static <T> BrokerMessageWrapper of(T payload) {
        return new BrokerMessageWrapper<>(UUID.randomUUID(), payload);
    }
}
