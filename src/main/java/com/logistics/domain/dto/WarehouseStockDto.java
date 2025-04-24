package com.logistics.domain.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class WarehouseStockDto {
    private Integer goodsId;
    private String fromWarehouse;
    private String toWarehouse;
    private String reason;
    private Integer quantity;
}
