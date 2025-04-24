package com.logistics.domain.dto;

import com.logistics.enums.WarehouseStockStatus;
import lombok.Data;

import java.math.BigInteger;
@Data
public class InventoryDto {
    private BigInteger id;
    private Integer stockQuantity;
    private String status;
}
