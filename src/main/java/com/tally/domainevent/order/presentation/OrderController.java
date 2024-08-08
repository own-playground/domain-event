package com.tally.domainevent.order.presentation;

import com.tally.domainevent.order.application.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/v1/orders")
    public ResponseEntity<Object> createOrder() {
        orderService.placeOrder();

        return ResponseEntity.ok().build();
    }
}
