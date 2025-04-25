package com.logistics.controller;

import cn.hutool.core.lang.Console;
import com.logistics.domain.dto.InventoryDto;
import com.logistics.domain.dto.WarehouseStockDto;
import com.logistics.domain.vo.*;
import com.logistics.service.WareHouseService;
import com.logistics.utils.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wareHouse")
@RequiredArgsConstructor
public class WareHouseController {
  private final WareHouseService wareHouseService;
  @GetMapping
  public ResponseResult<Map<String,Integer>> getAllWareHouseStock(){
      Map<String, Integer> allWareHouseStock= wareHouseService.getAllWareHouseStock();
      Console.log(allWareHouseStock);
      return ResponseResult.ok(allWareHouseStock);
  }
  @GetMapping("/getWareHouseStock/List")
    public ResponseResult<List<WarehouseStockVo>> getWareHouseStock(String productName,String status){
      List<WarehouseStockVo> warehouseStockVos= wareHouseService.WarehouseStock(productName, status);
      return ResponseResult.ok(warehouseStockVos);
  }
  @PutMapping("/adjusting/inventory")
    public ResponseResult<Void>  updateInventory(@RequestBody InventoryDto inventoryDto) {
      wareHouseService.updateInventory(inventoryDto);
      return ResponseResult.ok();
  }
  @PostMapping("/InOutInventoty")
  public ResponseResult<Void>  updateInOutInventory( Integer id, String type, Integer quantity, String remark) {
       wareHouseService.updateInOutInventory(id,type,quantity,remark);
       return ResponseResult.ok();
  }
  @GetMapping("/getStockChangeRecord")
    public ResponseResult<List<WarehouseLogVo>> getStockChangeRecord(){
    List<WarehouseLogVo> stockChangeRecord= wareHouseService.getStockChangeRecord();
    return ResponseResult.ok(stockChangeRecord);
  }
  @GetMapping("/getWarehouseLocation")
  public  ResponseResult<List<WarehouseLocationVo>> getWarehouseLocation(){
    List<WarehouseLocationVo> warehouseLocation= wareHouseService.getWarehouseLocation();
    return ResponseResult.ok(warehouseLocation);
  }
  @PostMapping("/stockSchedule")
    public ResponseResult<Void>  stockSchedule(@RequestBody WarehouseStockDto warehouseStockDto) {
       wareHouseService.stockSchedule(warehouseStockDto);
       return ResponseResult.ok();
  }
  @GetMapping("/getWarehouseLocationById")
  public ResponseResult<List<WarehouseStockAndStorageLocationVO>> getWarehouseLocationById(Long id){
    List<WarehouseStockAndStorageLocationVO> warehouseLocationById= wareHouseService.getWarehouseLocationById(id);
    return ResponseResult.ok(warehouseLocationById);
  }
  @GetMapping("/getTransferRecord")
  public ResponseResult<List<WarehouseStockTransferRecordVo>> getTransferRecord(Long id,String status){
    List<WarehouseStockTransferRecordVo> transferRecord= wareHouseService.getWarehouseStockTransferRecord(id,status);
    return ResponseResult.ok(transferRecord);
  }
}
