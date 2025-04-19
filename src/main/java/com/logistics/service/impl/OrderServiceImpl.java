package com.logistics.service.impl;

import cn.hutool.core.lang.Console;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.logistics.domain.Page.OrderPage;
import com.logistics.domain.dto.OrderDto;
import com.logistics.domain.entity.Order;
import com.logistics.domain.entity.OrderItem;
import com.logistics.domain.vo.OrderVo;
import com.logistics.mapper.OrderMapper;
import com.logistics.service.OrderService;
import com.logistics.utils.ResponseResult;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Override
    public Integer getOrderCompleteRate() {
        double orderCompleteRate= baseMapper.getOrderCompleteRate();
              orderCompleteRate*=100.0;
        return (int) orderCompleteRate;
    }
    @Override
    public ResponseResult<List<OrderVo>> getOrderAllInfo() {
        LocalDate now= LocalDate.now();
        LocalDate startLocalDate= now.with(DayOfWeek.MONDAY).minusDays(7);
        LocalDateTime startLocalDateTime= startLocalDate.atStartOfDay();

        // 获取订单信息数量
        List<OrderVo> orderInfo= baseMapper.getOrderAllInfo(startLocalDateTime);
        return ResponseResult.ok(orderInfo);
    }

    @Override
    public OrderPage getOrderInfo(String orderNumber,String username,String status,Integer page,Integer pageSize) {
      Integer orderStatus = null;
      if(status!=null) {
          switch (status) {
              case "pending_payment":
                  orderStatus = 0;
                  break;
              case "paid":
                  orderStatus = 1;
                  break;
              case "shipped":
                  orderStatus = 2;
                  break;
              case "completed":
                  orderStatus = 3;
                  break;
              case "cancelled":
                  orderStatus = 4;
                  break;
              default:
                  orderStatus = 5;
          }
      }
      // 进行分页
        Page<Order> page1= this.page(new Page<>(page, pageSize));
        IPage<OrderVo> orderInfo= baseMapper.getOrderInfo(page1, orderNumber, username, orderStatus);
        OrderPage orderPage1= new OrderPage();
        orderPage1.setCurrentPage(orderInfo.getCurrent());
        orderPage1.setPageSize(pageSize);
        orderPage1.setOrderList(orderInfo.getRecords());
        orderPage1.setTotal(orderInfo.getTotal());
        return orderPage1;
    }

    @Override
    public OrderItem getOrderDetailById(Long id) {
        return baseMapper.getOrderDetail(id);
    }

    @Override
    public OrderPage getOrderPage(Integer page, Integer pageSize) {
        Page<Order> page1= this.page(new Page<>(page, pageSize));
        IPage<OrderVo> orderPage= baseMapper.getOrderPage(page1);
        OrderPage orderPage1= new OrderPage();
        orderPage1.setCurrentPage(orderPage.getCurrent());
        orderPage1.setPageSize(pageSize);
        orderPage1.setOrderList(orderPage.getRecords());
        orderPage1.setTotal(orderPage.getTotal());
        return orderPage1;
    }

    @Override
    public void CreateOrder(OrderDto orderDto) {
        String userId= baseMapper.selectUserIdByUserName(orderDto.getUsername());
        Order order = new Order();
        BeanUtils.copyProperties(orderDto, order);
        order.setUserId(userId);
        Console.log(order);
        this.save(order);
    }

    @Override
    public void updateOrderStatusCancel(Integer orderId) {
        baseMapper.updateOrderStatusCancel(orderId);
    }

    @Override
    public void updateOrderStatusDelivery(Integer orderId) {
        baseMapper.updateOrderStatusDelivery(orderId);
    }
}
