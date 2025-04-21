package com.logistics.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.logistics.domain.dto.InventoryDto;
import com.logistics.domain.entity.WarehouseStock;
import com.logistics.domain.vo.WarehouseStockVo;
import com.logistics.enums.WarehouseStockStatus;
import com.logistics.mapper.WareHouseMapper;
import com.logistics.service.WareHouseService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
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
    public List<WarehouseStockVo> WarehouseStock(String productName,String status) {
        return baseMapper.getWarehouseStockByProductNameAndStatus(productName, status);
    }

    @Override
    public void updateInventory(InventoryDto inventoryDto) {
        baseMapper.updateInventory(inventoryDto);
    }

    @Override
    public void updateInOutInventory(BigInteger id, String type, Integer quantity, String remark) {
        // 判断是出库还是入库
        if(type.equals("in"))
        // 分别执行sql
        {
            baseMapper.updateInInventory(id,quantity);
        }
        if(type.equals("out")){
            baseMapper.updateOutInventory(id,quantity);
        }
        // 新增库存日志
        // 查询出现在的库存
        WarehouseStock warehouseStock= this.getById(id);
        Integer stockQuantity= warehouseStock.getStockQuantity();
//        baseMapper.insertWarehouseStockLog()
    }

}
