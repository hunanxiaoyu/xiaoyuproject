package com.logistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.logistics.domain.dto.InventoryDto;
import com.logistics.domain.dto.WarehouseStockDto;
import com.logistics.domain.entity.WarehouseStock;
import com.logistics.domain.vo.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface WareHouseService extends IService<WarehouseStock> {
    Map<String,Integer> getAllWareHouseStock();

    List<WarehouseStockVo> WarehouseStock(String productName,String status);

    void updateInventory(InventoryDto inventtoryDto);


    void updateInOutInventory(Integer id, String type, Integer quantity, String remark);

    List<WarehouseLogVo> getStockChangeRecord();

    List<WarehouseLocationVo> getWarehouseLocation();

    void stockSchedule(WarehouseStockDto warehouseStockDto);

    List<WarehouseStockAndStorageLocationVO> getWarehouseLocationById(Long id);

    List<WarehouseStockTransferRecordVo> getWarehouseStockTransferRecord(Long id,String status);
}
