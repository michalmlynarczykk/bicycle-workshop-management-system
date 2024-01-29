package com.michalmlynarczyk.workshopmanagementservice.client.broker;

import com.michalmlynarczyk.common.model.dto.broker.BrokerMessageWrapper;
import com.michalmlynarczyk.common.model.dto.broker.workshop.WorkshopCreatedEvent;
import com.michalmlynarczyk.common.model.dto.broker.workshop.WorkshopJoinApplicationApprovedEvent;
import com.michalmlynarczyk.common.util.Constant;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Component
@RequiredArgsConstructor
public class BrokerClientImpl implements BrokerClient {

    private final RabbitTemplate rabbitTemplate;


    @Override
    public void notifyAboutWorkshopCreated(@Valid final WorkshopCreatedEvent payload) {
        log.debug("notifyAboutWorkshopCreated() - enter - payload = {}", payload);

        final String exchange = Constant.WORKSHOP_SERVICE_BROKER_EXCHANGE;
        final String routingKey = Constant.WORKSHOP_CREATED_ROUTING_KEY;

        final BrokerMessageWrapper<?> brokerMessage = BrokerMessageWrapper.of(payload);
        send(exchange, routingKey, brokerMessage);
    }


    @Override
    public void notifyAboutWorkshopJoinApplicationApproved(@Valid final WorkshopJoinApplicationApprovedEvent payload) {
        log.trace("notifyAboutWorkshopJoinApplicationApproved() - enter - payload = {}", payload);
        final String exchange = Constant.WORKSHOP_SERVICE_BROKER_EXCHANGE;
        final String routingKey = Constant.WORKSHOP_JOIN_APPLICATION_APPROVED_ROUTING_KEY;

        final BrokerMessageWrapper<?> brokerMessage = BrokerMessageWrapper.of(payload);
        send(exchange, routingKey, brokerMessage);
    }


    private void send(final String exchange, final String routingKey, final BrokerMessageWrapper<?> brokerMessage) {
        rabbitTemplate.convertAndSend(exchange, routingKey, brokerMessage);
        log.debug("send() - brokerMessage = {} was sent to exchange = {} - routingKey = {}",
                brokerMessage, exchange, routingKey);
    }

}
