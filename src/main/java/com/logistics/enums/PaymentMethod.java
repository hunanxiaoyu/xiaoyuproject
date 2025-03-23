package com.logistics.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.Getter;

@Getter
public enum PaymentMethod {
   //支付方式: 1-支付宝, 2-微信, 3-银行卡
    ZhiFuBiao(1),
    weiCat(2),
    card(3);
    @EnumValue
    @JsonValue
    private final  Integer method;
    PaymentMethod(Integer method) {
        this.method = method;
    }
}
