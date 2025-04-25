package com.logistics.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.logistics.enums.TransRecordStatus;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class WarehouseStockTransferRecordVo {
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private LocalDateTime operationTime;
    private String productName;
    private String warehouseLocation;
    private String storageLocation;
    private Integer changeQuantity;
    private TransRecordStatus status;
    private String operator;
    private String remark;
}
