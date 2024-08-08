package com.tally.domainevent.order.application.handler;

import com.tally.domainevent.order.application.OrderService;
import com.tally.domainevent.order.domain.event.OrderCreatedEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class CreateOrderWithOrderCreatedEventHandler {

    private final OrderService orderService;

    public CreateOrderWithOrderCreatedEventHandler(final OrderService orderService) {
        this.orderService = orderService;
    }

    @Async
    @TransactionalEventListener(
            classes = OrderCreatedEvent.class,
            phase = TransactionPhase.AFTER_COMMIT
    )
    public void handle(final OrderCreatedEvent event) {
        orderService.payOrder(event.getOrderKey(), event.getUserId(), event.getStatus());
    }
}
