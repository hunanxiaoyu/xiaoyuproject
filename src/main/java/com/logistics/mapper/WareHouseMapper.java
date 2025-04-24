package com.logistics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.logistics.domain.dto.InventoryDto;
import com.logistics.domain.entity.WarehouseStock;
import com.logistics.domain.vo.WarehouseLocationVo;
import com.logistics.domain.vo.WarehouseLogVo;
import com.logistics.domain.vo.WarehouseStockVo;
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
    @Insert("insert into warehouse_log (change_type, change_quantity, operation_time, operator, remark, warehouse_stock_id,storage_location) values (#{type},#{quantity},#{operateTime},#{operator},#{remark},#{warehouseStockId},#{storageLocation})")
    void insertWarehouseStockLog(Integer warehouseStockId, Integer type, Integer quantity, String remark, String operator, LocalDateTime operateTime,String storageLocation);
    @Select("select change_type,warehouse_stock.product_name,change_quantity,operation_time,operator,remark from warehouse_log join warehouse_stock on warehouse_log.warehouse_stock_id = warehouse_stock.id")
    List<WarehouseLogVo> getStockChangeRecord();
    List<WarehouseLocationVo> getWarehouseLocation(Integer id);
    @Update("update warehouse_stock set stock_quantity = stock_quantity - #{quantity} where id = #{goodsId}")
    void warehouseReduceCount(Integer goodsId, Integer quantity);
    @Select("select id,product_name,sort,stock_quantity,status from warehouse_stock where id = #{goodsId} and warehouse_location = #{toWarehouse}")
    WarehouseStockVo warehouseByIdAndwarehouseLocation(Integer goodsId, String toWarehouse);
    @Update("update warehouse_stock set stock_quantity = stock_quantity + #{quantity} where id = #{goodsId}")
    void warehouseAddCount(Integer goodsId, Integer quantity);

}
