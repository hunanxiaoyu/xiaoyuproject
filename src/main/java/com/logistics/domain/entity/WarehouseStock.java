package com.logistics.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.logistics.enums.WarehouseStockStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("warehouse_stock")
public class WarehouseStock {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer productId;
    private String productName;
    private Integer stockQuantity;
    private Integer minStock;
    private Integer maxStock;
    private String warehouseLocation;
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime lastUpdate;
    private String sort;
    private WarehouseStockStatus status;
}
