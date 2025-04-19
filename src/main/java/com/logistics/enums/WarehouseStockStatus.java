package com.logistics.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum WarehouseStockStatus {
    NORMAL("正常"),
    LOW("低库存"),
    OUT_OF_STOCK("缺货");
    @EnumValue
    @JsonValue
    private final String status;
    WarehouseStockStatus(String status) {
        this.status = status;
    }

}
