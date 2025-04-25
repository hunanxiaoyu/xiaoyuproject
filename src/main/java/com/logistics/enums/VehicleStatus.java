package com.logistics.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import javax.swing.*;
@Getter
public enum VehicleStatus {
    NORMAL("行驶中"),
    REPAIR("停车"),
    SCRAPPED("维修中");
    @EnumValue
    @JsonValue
    private final String status;
    VehicleStatus(String status) {
        this.status = status;
    }
}
