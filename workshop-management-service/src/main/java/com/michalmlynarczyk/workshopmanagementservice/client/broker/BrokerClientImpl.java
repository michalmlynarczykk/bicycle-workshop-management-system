package com.michalmlynarczyk.workshopmanagementservice.client.broker;

import com.michalmlynarczyk.common.model.dto.broker.BrokerMessageWrapper;
import com.michalmlynarczyk.common.model.dto.broker.workshop.WorkshopCreatedEvent;
import com.michalmlynarczyk.common.util.Constant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class BrokerClientImpl implements BrokerClient {

    private final RabbitTemplate rabbitTemplate;


    @Override
    public void notifyAboutWorkshopCreated(final WorkshopCreatedEvent payload) {
        log.trace("notifyAboutWorkshopCreated() - enter - payload = {}", payload);
        final BrokerMessageWrapper<WorkshopCreatedEvent> brokerMessage = new BrokerMessageWrapper<>(UUID.randomUUID(), payload);

        final String exchange = Constant.WORKSHOP_SERVICE_BROKER_EXCHANGE;
        final String routingKey = Constant.WORKSHOP_CREATED_ROUTING_KEY;

        rabbitTemplate.convertAndSend(exchange, routingKey, brokerMessage);

        log.debug("notifyAboutWorkshopCreated() - brokerMessage = {} was sent to exchange = {} - routingKey = {}",
                brokerMessage, exchange, routingKey);
    }

}
