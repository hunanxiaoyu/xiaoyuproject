package com.logistics.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.logistics.domain.entity.WarehouseStock;
import com.logistics.domain.vo.WarehouseStockVo;
import com.logistics.mapper.WareHouseMapper;
import com.logistics.service.WareHouseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public List<WarehouseStockVo> WarehouseStock() {
        List<WarehouseStock> warehouseStockList= this.list();
        List<WarehouseStockVo> warehouseStockVos = new ArrayList<>();
        warehouseStockList.forEach(( warehouseStock -> {
            WarehouseStockVo warehouseStockVo= BeanUtil.copyProperties(warehouseStock, WarehouseStockVo.class);
            warehouseStockVos.add(warehouseStockVo);
        }));
        return warehouseStockVos;
    }
}
