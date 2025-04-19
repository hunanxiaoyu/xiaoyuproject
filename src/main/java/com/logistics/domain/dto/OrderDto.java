package com.logistics.domain.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDto {
    private String orderNumber;
    private String username;
    private BigDecimal totalAmount;
    private String remark;
}
