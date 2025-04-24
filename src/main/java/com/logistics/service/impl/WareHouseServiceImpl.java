package com.logistics.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.logistics.domain.dto.InventoryDto;
import com.logistics.domain.dto.WarehouseStockDto;
import com.logistics.domain.entity.WarehouseStock;
import com.logistics.domain.vo.WarehouseLocationVo;
import com.logistics.domain.vo.WarehouseLogVo;
import com.logistics.domain.vo.WarehouseStockVo;
import com.logistics.enums.WarehouseStockStatus;
import com.logistics.mapper.WareHouseMapper;
import com.logistics.service.WareHouseService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WareHouseServiceImpl extends ServiceImpl<WareHouseMapper, WarehouseStock> implements WareHouseService {

    @Override
    public Map<String, Integer> getAllWareHouseStock() {
        List<WarehouseStock> list = this.list();
        Map<String, Integer> map = new HashMap<>();
        list.forEach(warehouseStock -> map.put(warehouseStock.getProductName(), warehouseStock.getStockQuantity()));
        return map;
    }

    @Override
    public List<WarehouseStockVo> WarehouseStock(String productName, String status) {
        return baseMapper.getWarehouseStockByProductNameAndStatus(productName, status);
    }

    @Override
    public void updateInventory(InventoryDto inventoryDto) {
        baseMapper.updateInventory(inventoryDto);
    }

    @Override
    public void updateInOutInventory(Integer warehouseStockId, String type, Integer quantity, String remark) {
        Integer inAndOut = null;
        // 判断是出库还是入库
        if (type.equals("in"))
        // 分别执行sql
        {
            baseMapper.updateInInventory(warehouseStockId, quantity);
            // 设置type
            inAndOut = 1;
        }
        if (type.equals("out")) {
            baseMapper.updateOutInventory(warehouseStockId, quantity);
            // 设置type
            inAndOut = 0;
        }
        // 新增库存日志
        // 查询出现在的库存
        WarehouseStock warehouseStock = this.getById(warehouseStockId);
        Integer stockQuantity = warehouseStock.getStockQuantity();
        LocalDateTime localDateTime = LocalDateTime.now();
        // TODO 当前登录用户先写死
        String username = "xiaoyu";


        baseMapper.insertWarehouseStockLog(warehouseStockId, inAndOut, quantity, remark, username, localDateTime,null);
    }

    @Override
    public List<WarehouseLogVo> getStockChangeRecord() {
        return baseMapper.getStockChangeRecord();
    }

    @Override
    public List<WarehouseLocationVo> getWarehouseLocation(Integer id) {
        return baseMapper.getWarehouseLocation(id);
    }

    @Override
    public void stockSchedule(WarehouseStockDto warehouseStockDto) {
        if(warehouseStockDto.getFromWarehouse().equals(warehouseStockDto.getToWarehouse())){
            throw new RuntimeException("调出仓库和调入仓库不能是同一个");
        }
        // 调出库存减少数量
        baseMapper.warehouseReduceCount(warehouseStockDto.getGoodsId(),warehouseStockDto.getQuantity());
        // 调入库存新增数量
        //  查看这个仓库是否有这个库存
        WarehouseStockVo warehouseStockVo= baseMapper.warehouseByIdAndwarehouseLocation(warehouseStockDto.getGoodsId(), warehouseStockDto.getToWarehouse());

        //有的话就直接新增数量就行
     if (warehouseStockVo != null) {
         baseMapper.warehouseAddCount(warehouseStockDto.getGoodsId(),warehouseStockDto.getQuantity());
     }
        //没有就新增一条库存
        WarehouseStock stock= this.getById(warehouseStockDto.getGoodsId());
         stock.setWarehouseLocation(warehouseStockDto.getToWarehouse());
         stock.setStockQuantity(warehouseStockDto.getQuantity());
         stock.setLastUpdate(LocalDateTime.now());
         stock.setId(null);
         this.save(stock);
        // 生成一条日志
        // TODO 当前登录用户先写死
        String username = "xiaoyu";
        LocalDateTime localDateTime = LocalDateTime.now();
        // 新增跟改库存记录
        baseMapper.insertWarehouseStockLog(stock.getId(),3,warehouseStockDto.getQuantity(),warehouseStockDto.getReason(),username,localDateTime,warehouseStockDto.getToWarehouse());

   }
}
