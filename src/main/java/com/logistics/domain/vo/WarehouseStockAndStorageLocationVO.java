package com.logistics.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

@Data
public class WarehouseStockAndStorageLocationVO {
    private Long  warehouseStockId;
    private String warehouseLocation;
    private Long warehouseLogId;
    private String storageLocation;
}
