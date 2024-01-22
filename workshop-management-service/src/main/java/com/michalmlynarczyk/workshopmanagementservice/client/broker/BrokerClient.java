package com.michalmlynarczyk.workshopmanagementservice.client.broker;

import com.michalmlynarczyk.common.model.dto.broker.workshop.WorkshopCreatedEvent;
import com.michalmlynarczyk.common.model.dto.broker.workshop.WorkshopJoinApplicationApprovedEvent;

public interface BrokerClient {

    void notifyAboutWorkshopCreated(final WorkshopCreatedEvent payload);

    void notifyAboutWorkshopJoinApplicationApproved(final WorkshopJoinApplicationApprovedEvent payload);
}
