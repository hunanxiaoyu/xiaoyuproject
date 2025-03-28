package com.logistics.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.logistics.domain.entity.WarehouseStock;
import com.logistics.mapper.WareHouseMapper;
import com.logistics.service.WareHouseService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WareHouseServiceImpl extends ServiceImpl<WareHouseMapper, WarehouseStock> implements WareHouseService {

    @Override
    public Map<String,Integer> getAllWareHouseStock() {
        List<WarehouseStock> list= this.list();
        Map<String,Integer> map=new HashMap<>();
        list.forEach(warehouseStock-> map.put(warehouseStock.getProductName(),warehouseStock.getStockQuantity()));
        return map;
    }
}
