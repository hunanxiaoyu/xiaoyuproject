package com.logistics.controller;

import cn.hutool.core.lang.Console;
import com.logistics.domain.entity.Order;
import com.logistics.domain.entity.OrderItem;
import com.logistics.domain.vo.OrderVo;
import com.logistics.service.OrderService;
import com.logistics.utils.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
  private final OrderService orderService;
  @GetMapping("getRate")
  public ResponseResult<Integer> getOrderCompleteRate(){
      Integer orderCompleteRate= orderService.getOrderCompleteRate();
      return ResponseResult.ok(orderCompleteRate);
  }
    @GetMapping("/getAllorderInfo")
    public ResponseResult<List<OrderVo>> getOrderALlInfo(){
        return orderService.getOrderAllInfo();
    }
    @GetMapping("/getOrderInfo")
    public ResponseResult<List<OrderVo>> getOrderInfo(String orderNumber,String username,String status){
        Console.log(orderNumber,username,status);
        List<OrderVo> orderInfo= orderService.getOrderInfo(orderNumber,username,status);
        return ResponseResult.ok(orderInfo);
    }
    @GetMapping("/getOrderDetail/{id}")
    public ResponseResult<OrderItem> getOrderDetailById(@PathVariable Long id){
        OrderItem orderDetailById= orderService.getOrderDetailById(id);
        return ResponseResult.ok(orderDetailById);
    }
}
