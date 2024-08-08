package com.tally.domainevent.order.domain.event;

import com.tally.domainevent.order.domain.Order;
import com.tally.domainevent.order.enums.OrderStatus;
import org.springframework.util.Assert;

import java.util.UUID;

public class OrderCreatedEvent {

    private final UUID orderKey;
    private final Long userId;
    private final OrderStatus status;

    public OrderCreatedEvent(final Order order) {
        Assert.notNull(order.getOrderKey(), "주문 키는 필수입니다.");
        Assert.notNull(order.getUserId(), "주문자 아이디는 필수입니다.");
        Assert.notNull(order.getStatus(), "주문 상태는 필수입니다.");
        this.orderKey = order.getOrderKey();
        this.userId = order.getUserId();
        this.status = order.getStatus();
    }

    public UUID getOrderKey() {
        return orderKey;
    }

    public Long getUserId() {
        return userId;
    }

    public OrderStatus getStatus() {
        return status;
    }
}
