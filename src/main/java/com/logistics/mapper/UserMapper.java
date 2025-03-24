package com.logistics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.logistics.domain.entity.User;
import com.logistics.domain.vo.OrderVo;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    @Select("select order_number,user_id,total_amount,status,payment_method,payment_time,create_time,update_time,remark,product_id,product_name,price,quantity,total_price from orders left join order_items oi on orders.id = oi.order_id where create_time >= #{startLocalDateTime}")
    @ResultMap("orderResultMap")
    List<OrderVo> getOrderInfo(LocalDateTime startLocalDateTime);
}
