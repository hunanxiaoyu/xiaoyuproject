package com.logistics.enums;

import lombok.Getter;

@Getter
public enum ShippingStatus {
    // 物流状态: 0-待发货, 1-已发货, 2-运输中, 3-已签收
    waiting(0),
    Dispatch(1),
    delivery(2),
    receive(3);
    private final Integer shippingStatus;
    ShippingStatus (Integer shippingStatus) {
        this.shippingStatus = shippingStatus;
    }
}
