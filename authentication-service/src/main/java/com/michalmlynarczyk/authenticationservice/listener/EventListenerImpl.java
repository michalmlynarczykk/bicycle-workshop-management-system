package com.michalmlynarczyk.authenticationservice.listener;

import com.michalmlynarczyk.authenticationservice.service.UserService;
import com.michalmlynarczyk.common.model.dto.broker.BrokerMessageWrapper;
import com.michalmlynarczyk.common.model.dto.broker.workshop.WorkshopCreatedEvent;
import com.michalmlynarczyk.common.util.Constant;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Component
@Validated
@RequiredArgsConstructor
public class EventListenerImpl implements EventListener {

    private final UserService userService;


    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(name = Constant.WORKSHOP_SERVICE_BROKER_EXCHANGE, type = ExchangeTypes.TOPIC),
            value = @Queue(name = Constant.WORKSHOP_CREATED_QUEUE, durable = "true"),
            key = Constant.WORKSHOP_CREATED_ROUTING_KEY)
    )
    @Override
    public void receiveWorkshopCreatedEvent(@Payload @Valid final BrokerMessageWrapper<WorkshopCreatedEvent> message) {
        log.info("receiveWorkshopCreatedEvent() - enter - payload = {}", message);
        final WorkshopCreatedEvent workshopCreatedEvent = message.payload();
        try {
            userService.assignWorkshopAndUpgradeRole(workshopCreatedEvent.ownerId(), workshopCreatedEvent.workshopId());
        } catch (final Exception e) {
            log.error("receiveWorkshopCreatedEvent() - error", e);
        }
        log.info("receiveWorkshopCreatedEvent() - exit");
    }
}
