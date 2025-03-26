package com.logistics.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
@Data
@TableName("orderItems")
public class OrderItem  implements Serializable {
    private BigInteger productId;
    private String productName;
    private BigDecimal price;
    private BigInteger quantity;
    private BigDecimal totalPrice;
}
