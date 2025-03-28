package com.logistics.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.logistics.domain.entity.Order;
import com.logistics.domain.entity.OrderItem;
import com.logistics.domain.vo.OrderVo;
import com.logistics.mapper.OrderMapper;
import com.logistics.service.OrderService;
import com.logistics.utils.ResponseResult;
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
    public List<OrderVo> getOrderInfo(String orderNumber,String username,String status) {
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
        return baseMapper.getOrderInfo(orderNumber,username,orderStatus);
    }

    @Override
    public OrderItem getOrderDetailById(Long id) {
        return baseMapper.getOrderDetail(id);
    }
}
