package com.tally.domainevent.order.application.handler;

import com.tally.domainevent.order.application.OrderService;
import com.tally.domainevent.order.domain.event.OrderCompletedEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class CompleteOrderWithOrderCompletedEventHandler {

    private final OrderService orderService;

    public CompleteOrderWithOrderCompletedEventHandler(final OrderService orderService) {
        this.orderService = orderService;
    }

    @Async
    @TransactionalEventListener(
            classes = OrderCompletedEvent.class,
            phase = TransactionPhase.AFTER_COMMIT
    )
    public void handle(final OrderCompletedEvent event) {
        orderService.completeOrder(event.getOrder());
    }
}
