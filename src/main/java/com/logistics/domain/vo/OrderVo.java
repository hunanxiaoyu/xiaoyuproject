package com.logistics.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.logistics.domain.entity.OrderItem;
import com.logistics.enums.PayStatus;
import com.logistics.enums.PaymentMethod;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderVo {
    private Integer id;
    private String orderNumber;
    private String username;
    private PayStatus status;
    private BigDecimal totalAmount;
    private PaymentMethod paymentMethod;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime paymentTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    private String remark;
    private List<OrderItem> OrderItems;
}
