package com.tally.domainevent.order.domain;

import com.tally.domainevent.order.domain.event.OrderCompletedEvent;
import com.tally.domainevent.order.domain.event.OrderCreatedEvent;
import com.tally.domainevent.order.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "orders")
@EntityListeners(AuditingEntityListener.class)
public class Order extends AbstractAggregateRoot<Order> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(unique = true, nullable = false)
    @Builder.Default
    private UUID orderKey = UUID.randomUUID();

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private OrderStatus status;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    protected Order() {}

    @Builder
    public Order(final Long userId) {
        this.userId = userId;
        this.status = OrderStatus.READY;
        registerEvent(new OrderCreatedEvent(this));
    }

    public void confirmed() {
        this.status = OrderStatus.CONFIRM;
        registerEvent(new OrderCompletedEvent(this));
    }

    public Long getUserId() {
        return userId;
    }

    public UUID getOrderKey() {
        return orderKey;
    }

    public OrderStatus getStatus() {
        return status;
    }
}
