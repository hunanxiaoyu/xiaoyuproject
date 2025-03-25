package com.logistics.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.logistics.enums.ShippingStatus;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@TableName("order_shipping")
public class Order_shopping {
    private BigInteger id;
    private BigInteger orderId;
    private String trackingNumber;
    private String shoppingCompany;
    private ShippingStatus shippingStatus;
    private LocalDateTime shippingTime;
    private String deliveryTime;
}
