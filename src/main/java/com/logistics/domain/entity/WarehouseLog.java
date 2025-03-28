package com.logistics.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("warehouse_log")
public class WarehouseLog {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer productId;
    private String changeType; // "IN" 或 "OUT"
    private Integer changeQuantity;
    private Integer remainingStock;
    private LocalDateTime operationTime;
    private String operator;
}