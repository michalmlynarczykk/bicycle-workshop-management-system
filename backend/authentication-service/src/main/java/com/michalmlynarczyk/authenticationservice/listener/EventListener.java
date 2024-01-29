package com.michalmlynarczyk.authenticationservice.listener;

import com.michalmlynarczyk.common.model.dto.broker.BrokerMessageWrapper;
import com.michalmlynarczyk.common.model.dto.broker.workshop.WorkshopCreatedEvent;
import com.michalmlynarczyk.common.model.dto.broker.workshop.WorkshopJoinApplicationApprovedEvent;
import jakarta.validation.Valid;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.validation.annotation.Validated;

@Validated
public interface EventListener {

    void receiveWorkshopCreatedEvent(@Payload @Valid final BrokerMessageWrapper<WorkshopCreatedEvent> message);

    void receiveWorkshopJoinApplicationApprovedEvent(@Payload @Valid final BrokerMessageWrapper<WorkshopJoinApplicationApprovedEvent> message);
}
