package com.logistics.domain.vo;

import com.logistics.domain.entity.orderItem;
import com.logistics.enums.PayStatus;
import com.logistics.enums.PaymentMethod;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderVo {
    private String orderNumber;
    private String userId;
    private BigDecimal totalPrice;
    private PayStatus payStatus;
    private BigDecimal totalAmount;
    private PaymentMethod paymentMethod;
    private LocalDateTime paymentTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String remark;
    private List<orderItem> orderItems;
}
