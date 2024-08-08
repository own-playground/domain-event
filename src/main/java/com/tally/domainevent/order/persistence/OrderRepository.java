package com.tally.domainevent.order.persistence;

import com.tally.domainevent.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByOrderKey(UUID orderKey);

}
