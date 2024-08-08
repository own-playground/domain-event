package com.tally.domainevent.order.domain.event;

import com.tally.domainevent.order.domain.Order;
import org.springframework.util.Assert;

public class OrderCompletedEvent {

    private final Order order;

    public OrderCompletedEvent(final Order order) {
        Assert.notNull(order, "주문은 필수입니다.");
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
}
