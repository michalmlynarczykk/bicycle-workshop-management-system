package com.michalmlynarczyk.workshopmanagementservice.client.broker;

import com.michalmlynarczyk.common.model.dto.broker.workshop.WorkshopCreatedEvent;
import com.michalmlynarczyk.common.model.dto.broker.workshop.WorkshopJoinApplicationApprovedEvent;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@Validated
public interface BrokerClient {

    void notifyAboutWorkshopCreated(@Valid final WorkshopCreatedEvent payload);

    void notifyAboutWorkshopJoinApplicationApproved(@Valid final WorkshopJoinApplicationApprovedEvent payload);
}
