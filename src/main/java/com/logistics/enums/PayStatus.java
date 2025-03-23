package com.logistics.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum PayStatus {
    // 订单状态: 0-待支付, 1-已支付, 2-已发货, 3-已完成, 4-已取消
    Waiting("待支付"),
    Paying("已支付"),
    Dispatch("已发货"),
    Completed("已完成"),
    Cancelled("已取消");
    @EnumValue
    @JsonValue
    private final String status;
    PayStatus(String status) {
      this.status = status;
    }

}
