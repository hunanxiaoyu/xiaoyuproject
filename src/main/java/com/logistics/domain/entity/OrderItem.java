package com.logistics.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
@Data
@TableName("order_items")
public class OrderItem {
    private BigInteger id;
    private BigInteger orderId;
    private BigInteger productId;
    private String productName;
    private BigDecimal price;
    private BigInteger quantity;
    private BigInteger totalPrice;
}
