package com.logistics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.logistics.domain.dto.InventoryDto;
import com.logistics.domain.entity.WarehouseStock;
import com.logistics.domain.vo.*;
import com.logistics.enums.TransRecordStatus;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

public interface WareHouseMapper extends BaseMapper<WarehouseStock> {
    List<WarehouseStockVo> getWarehouseStockByProductNameAndStatus(String productName, String status);
    void updateInventory(InventoryDto inventoryDto);
    @Update("update warehouse_stock set stock_quantity = stock_quantity+#{ quantity} where id = #{id} ")
    void updateInInventory(Integer id, Integer quantity);
    @Update("update warehouse_stock set stock_quantity = stock_quantity-#{ quantity} where id = #{id} ")
    void updateOutInventory(Integer id, Integer quantity);
    @Insert("insert into warehouse_log (id, change_type, change_quantity, operation_time, operator, remark, warehouse_stock_id,storage_location) values (#{id},#{type},#{quantity},#{operateTime},#{operator},#{remark},#{warehouseStockId},#{storageLocation})")
    void insertWarehouseStockLog(Long id, Integer warehouseStockId, Integer type, Integer quantity, String remark, String operator, LocalDateTime operateTime,String storageLocation);
    @Select("select change_type,warehouse_stock.product_name,change_quantity,operation_time,operator,remark from warehouse_log join warehouse_stock on warehouse_log.warehouse_stock_id = warehouse_stock.id")
    List<WarehouseLogVo> getStockChangeRecord();
    List<WarehouseLocationVo> getWarehouseLocation();
    @Update("update warehouse_stock set stock_quantity = stock_quantity - #{quantity} where id = #{goodsId}")
    void warehouseReduceCount(Integer goodsId, Integer quantity);
    @Select("select warehouse_stock.id as warehouseStockId, wl.id as warehouseLogId, warehouse_location,storage_location from warehouse_stock join logistics.warehouse_log wl on warehouse_stock.id = wl.warehouse_stock_id where warehouse_stock.id = #{id}")
    List<WarehouseStockAndStorageLocationVO> getWarehouseLocationById(Long id);
    List<WarehouseStockTransferRecordVo> getWarehouseStockTransferRecord(Long id, String status);
}
