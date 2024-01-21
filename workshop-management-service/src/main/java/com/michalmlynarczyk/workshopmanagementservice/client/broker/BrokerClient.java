package com.michalmlynarczyk.workshopmanagementservice.client.broker;

import com.michalmlynarczyk.common.model.dto.broker.workshop.WorkshopCreatedEvent;

public interface BrokerClient {

    void notifyAboutWorkshopCreated(final WorkshopCreatedEvent payload);
}
