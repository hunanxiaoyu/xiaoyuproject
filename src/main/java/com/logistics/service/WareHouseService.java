package com.logistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.logistics.domain.entity.WarehouseStock;
import com.logistics.domain.vo.WarehouseStockVo;

import java.util.List;
import java.util.Map;

public interface WareHouseService extends IService<WarehouseStock> {
    Map<String,Integer> getAllWareHouseStock();

    List<WarehouseStockVo> WarehouseStock();
}
