package com.logistics.domain.vo;

import com.logistics.domain.entity.WarehouseStock;
import com.logistics.enums.WarehouseStockStatus;
import lombok.Data;

import java.math.BigInteger;
@Data
public class WarehouseStockVo {
    private BigInteger id;
    private String productName;
    private String sort;
    private BigInteger stockQuantity;
    private WarehouseStockStatus status;
}
