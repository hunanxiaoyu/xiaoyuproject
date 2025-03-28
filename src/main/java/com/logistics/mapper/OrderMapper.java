package com.logistics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.logistics.domain.entity.Order;
import com.logistics.domain.entity.OrderItem;
import com.logistics.domain.vo.OrderVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderMapper extends BaseMapper<Order> {
    @Select("select (select count(*) from orders where status =3)/ (select count(*) from orders)")
    double getOrderCompleteRate();
    @Select("select orders.id,order_number,username,total_amount,status,payment_method,payment_time,create_time,update_time,remark,product_id,product_name,price,quantity,total_price from orders\n" +
            "    left join order_items oi on orders.id = oi.order_id\n" +
            "    join userInfo on orders.user_id = userInfo.id\n" +
            "      where create_time >= #{startLocalDateTime} order by create_time desc ;")
    @ResultMap("orderResultMap")
    List<OrderVo> getOrderAllInfo(LocalDateTime startLocalDateTime);
    @Select("<script>"+
            "select o.id,order_number,username,total_amount,status,payment_method,payment_time,create_time,update_time,remark from userInfo join logistics.orders o on userInfo.id = o.user_id"+
            " where 1=1" +
            "<if test = 'orderNumber != null'> and order_number = #{orderNumber} </if>" +
            "<if test = 'username != null'> and username = #{username} </if>"+
            "<if test = 'orderStatus != null'> and status = #{orderStatus} </if>"+
            "</script>"
    )
    List<OrderVo> getOrderInfo(@Param("orderNumber") String orderNumber,@Param("username") String username, @Param("orderStatus") Integer orderStatus);
    @Select("select product_id,product_name,price,quantity,total_price from order_items where id = #{id}")
    OrderItem getOrderDetail(Long id);
}
