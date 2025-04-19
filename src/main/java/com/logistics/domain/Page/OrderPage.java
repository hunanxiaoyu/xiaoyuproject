package com.logistics.domain.Page;

import com.logistics.domain.vo.OrderVo;
import lombok.Data;

import java.util.List;

@Data
public class OrderPage {
    private Long total;
    private Long currentPage;
    private Integer pageSize;
    private List<OrderVo> orderList;
}
