package com.logistics.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.logistics.domain.dto.InventoryDto;
import com.logistics.domain.dto.WarehouseStockDto;
import com.logistics.domain.entity.WarehouseStock;
import com.logistics.domain.vo.*;
import com.logistics.mapper.WareHouseMapper;
import com.logistics.service.WareHouseService;
import org.springframework.stereotype.Service;
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
        Long id = IdWorker.getId();
        baseMapper.insertWarehouseStockLog(id,warehouseStockId, inAndOut, quantity, remark, username, localDateTime,null);
    }

    @Override
    public List<WarehouseLogVo> getStockChangeRecord() {
        return baseMapper.getStockChangeRecord();
    }

    @Override
    public List<WarehouseLocationVo> getWarehouseLocation() {
        return baseMapper.getWarehouseLocation();
    }

    @Override
    public void stockSchedule(WarehouseStockDto warehouseStockDto) {
        if(warehouseStockDto.getFromWarehouse().equals(warehouseStockDto.getToWarehouse())){
            throw new RuntimeException("调出仓库和调入仓库不能是同一个");
        }
        // 调出库存减少数量
        baseMapper.warehouseReduceCount(warehouseStockDto.getGoodsId(),warehouseStockDto.getQuantity());

        // 生成库存调动
        // TODO 当前登录用户先写死
        String username = "xiaoyu";
        LocalDateTime localDateTime = LocalDateTime.now();
        // 新增跟改库存记录
        Long id = IdWorker.getId();
        baseMapper.insertWarehouseStockLog(id,warehouseStockDto.getGoodsId(), 3,warehouseStockDto.getQuantity(),warehouseStockDto.getReason(),username,localDateTime,warehouseStockDto.getToWarehouse());

   }

    @Override
    public List<WarehouseStockAndStorageLocationVO> getWarehouseLocationById(Long id) {
        return baseMapper.getWarehouseLocationById(id);
    }

    @Override
    public List<WarehouseStockTransferRecordVo> getWarehouseStockTransferRecord(Long id, String status) {

        return baseMapper.getWarehouseStockTransferRecord(id,status);
    }
}
