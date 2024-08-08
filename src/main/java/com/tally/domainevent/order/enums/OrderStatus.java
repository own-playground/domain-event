package com.tally.domainevent.order.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {

    READY("READY", "주문 최초 생성"),
    CONFIRM("CONFIRM", "결제 완료"),
    REFUND("REFUND", "환불 완료"),
    CANCELED("CANCELED", "취소된 결제"),
    FAILED("FAILED", "결제 실패");

    private final String value;
    private final String kr;

}
