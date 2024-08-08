package com.tally.domainevent.order.application;

import com.tally.domainevent.order.domain.Order;
import com.tally.domainevent.order.enums.OrderStatus;
import com.tally.domainevent.order.persistence.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public void placeOrder() {
        log.info("1 ====== 주문을 생성합니다.");
        final Order order = Order.builder()
                .userId(1L)
                .build();
        orderRepository.save(order);
    }

    @Transactional
    public void payOrder(final UUID orderKey, final Long userId, final OrderStatus status) {
        log.info("2 ====== 결제를 시작합니다. 주문 키: {}, 주문자 아이디: {}, 주문 상태: {}", orderKey, userId, status.getKr());
        final Order order = orderRepository.findByOrderKey(orderKey).orElseThrow(IllegalArgumentException::new);
        order.confirmed();
        orderRepository.save(order);
        log.info("3 ====== 결제가 완료되었습니다.");
    }

    @Transactional
    public void completeOrder(final Order order) {
        log.info("4 ====== 주문이 완료되었습니다. 주문 상태: {}", order.getStatus().getKr());

        log.info("5 ====== 이메일 전송");
        log.info("5 ====== 알림톡 전송");
    }
}
