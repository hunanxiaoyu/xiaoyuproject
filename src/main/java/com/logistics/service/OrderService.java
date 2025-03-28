package com.logistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.logistics.domain.entity.Order;
import com.logistics.domain.entity.OrderItem;
import com.logistics.domain.vo.OrderVo;
import com.logistics.utils.ResponseResult;

import java.util.List;

public interface OrderService extends IService<Order> {
    Integer getOrderCompleteRate();
    ResponseResult<List<OrderVo>> getOrderAllInfo();

    List<OrderVo> getOrderInfo(String orderNumber,String username,String status);

    OrderItem getOrderDetailById(Long id);
}
