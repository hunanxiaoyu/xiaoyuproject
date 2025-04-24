package com.logistics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.logistics.domain.dto.InventoryDto;
import com.logistics.domain.entity.WarehouseStock;
import com.logistics.domain.vo.WarehouseStockVo;
import org.apache.ibatis.annotations.Update;

import java.math.BigInteger;
import java.util.List;

public interface WareHouseMapper extends BaseMapper<WarehouseStock> {
    List<WarehouseStockVo> getWarehouseStockByProductNameAndStatus(String productName, String status);
    void updateInventory(InventoryDto inventoryDto);
    @Update("update warehouse_stock set stock_quantity = stock_quantity+#{ quantity} where id = #{id} ")
    void updateInInventory(BigInteger id, Integer quantity);
    @Update("update warehouse_stock set stock_quantity = stock_quantity-#{ quantity} where id = #{id} ")
    void updateOutInventory(BigInteger id, Integer quantity);
}
