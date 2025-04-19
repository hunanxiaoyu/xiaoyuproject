package com.logistics.controller;

import cn.hutool.core.lang.Console;
import com.logistics.domain.vo.WarehouseStockVo;
import com.logistics.service.WareHouseService;
import com.logistics.utils.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseResult<List<WarehouseStockVo>> getWareHouseStock(){
      List<WarehouseStockVo> warehouseStockVos= wareHouseService.WarehouseStock();
      return ResponseResult.ok(warehouseStockVos);
  }
}
