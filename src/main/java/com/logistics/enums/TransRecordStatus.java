package com.logistics.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum TransRecordStatus {
    pending("待调拨"),
    completed("已完成"),
    error("异常");
    @EnumValue
    private final String status;
    TransRecordStatus(String status) {
        this.status = status;
    }
}
