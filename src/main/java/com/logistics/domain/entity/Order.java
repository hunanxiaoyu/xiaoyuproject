package com.logistics.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.logistics.enums.PaymentMethod;
import com.logistics.enums.PayStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@TableName("orders")
public class Order {
    private BigInteger id;
    private String orderNumber;
    private String userId;
    private BigDecimal totalAmount;
    private PayStatus status;
    private PaymentMethod paymentMethod;
    private LocalDateTime paymentTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String remark;
}
