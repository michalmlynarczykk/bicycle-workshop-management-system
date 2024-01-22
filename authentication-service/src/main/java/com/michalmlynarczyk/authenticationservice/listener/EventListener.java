package com.michalmlynarczyk.authenticationservice.listener;

import com.michalmlynarczyk.common.model.dto.broker.BrokerMessageWrapper;
import com.michalmlynarczyk.common.model.dto.broker.workshop.WorkshopCreatedEvent;

public interface EventListener {

    void receiveWorkshopCreatedEvent(BrokerMessageWrapper<WorkshopCreatedEvent> message);
}
