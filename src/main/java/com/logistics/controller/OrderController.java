package com.logistics.controller;

import cn.hutool.core.lang.Console;
import com.logistics.domain.Page.OrderPage;
import com.logistics.domain.dto.OrderDto;
import com.logistics.domain.entity.Order;
import com.logistics.domain.entity.OrderItem;
import com.logistics.domain.vo.OrderVo;
import com.logistics.service.OrderService;
import com.logistics.utils.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public ResponseResult<OrderPage> getOrderInfo(String orderNumber,String username,String status,@RequestParam(defaultValue = "1" ) Integer page,@RequestParam(defaultValue = "10") Integer pageSize){
        Console.log(orderNumber,username,status);
        OrderPage orderInfo= orderService.getOrderInfo(orderNumber, username, status, page, pageSize);
        return ResponseResult.ok(orderInfo);
    }
    @GetMapping("/getOrderDetail/{id}")
    public ResponseResult<OrderItem> getOrderDetailById(@PathVariable Long id){
        OrderItem orderDetailById= orderService.getOrderDetailById(id);
        return ResponseResult.ok(orderDetailById);
    }
    @GetMapping("/page")
    public ResponseResult<OrderPage> getOrderPage(@RequestParam(defaultValue = "1" ) Integer page,@RequestParam(defaultValue = "10") Integer pageSize){
      Console.log(page,pageSize);
        OrderPage orderPage = orderService.getOrderPage(page, pageSize);
        return ResponseResult.ok(orderPage);
    }
    @PostMapping("/addOrder")
    public ResponseResult<Void> CreateOrder(OrderDto orderDto){
      orderService.CreateOrder(orderDto);
      return ResponseResult.ok();
    }
    @PutMapping("/updateOrderStatusCancel")
    public ResponseResult<Void> updateOrderStatusCancel(Integer orderId){
      Console.log(orderId);
        orderService.updateOrderStatusCancel(orderId);
        return ResponseResult.ok();
    }

    @PutMapping("/updateOrderStatusDelivery")
    public ResponseResult<Void> updateOrderStatusDelivery(Integer orderId){
        orderService.updateOrderStatusDelivery(orderId);
        return ResponseResult.ok();
    }
}
