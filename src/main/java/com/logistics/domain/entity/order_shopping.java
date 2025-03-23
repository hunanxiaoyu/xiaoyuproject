package com.logistics.domain.entity;

import com.logistics.enums.ShippingStatus;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
public class order_shopping {
    private BigInteger id;
    private BigInteger orderId;
    private String trackingNumber;
    private String shoppingCompany;
    private ShippingStatus shippingStatus;
    private LocalDateTime shippingTime;
    private String deliveryTime;
}
