package com.logistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.logistics.domain.Page.OrderPage;
import com.logistics.domain.dto.OrderDto;
import com.logistics.domain.entity.Order;
import com.logistics.domain.entity.OrderItem;
import com.logistics.domain.vo.OrderVo;
import com.logistics.utils.ResponseResult;

import java.util.List;

public interface OrderService extends IService<Order> {
    Integer getOrderCompleteRate();
    ResponseResult<List<OrderVo>> getOrderAllInfo();

    OrderPage getOrderInfo(String orderNumber,String username,String status,Integer page,Integer pageSize);

    OrderItem getOrderDetailById(Long id);

    OrderPage getOrderPage(Integer page, Integer pageSize);

    void CreateOrder(OrderDto orderDto);

    void updateOrderStatusCancel(Integer orderId);

    void updateOrderStatusDelivery(Integer orderId);
}
