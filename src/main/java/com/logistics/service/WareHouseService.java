package com.logistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.logistics.domain.entity.WarehouseStock;

import java.util.Map;

public interface WareHouseService extends IService<WarehouseStock> {
    Map<String,Integer> getAllWareHouseStock();
}
