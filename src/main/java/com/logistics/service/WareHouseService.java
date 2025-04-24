package com.logistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.logistics.domain.dto.InventoryDto;
import com.logistics.domain.entity.WarehouseStock;
import com.logistics.domain.vo.WarehouseStockVo;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface WareHouseService extends IService<WarehouseStock> {
    Map<String,Integer> getAllWareHouseStock();

    List<WarehouseStockVo> WarehouseStock(String productName,String status);

    void updateInventory(InventoryDto inventtoryDto);


    void updateInOutInventory(BigInteger id, String type, Integer quantity, String remark);
}
