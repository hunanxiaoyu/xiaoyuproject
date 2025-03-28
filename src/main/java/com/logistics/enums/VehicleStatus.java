package com.logistics.enums;

import lombok.Getter;

import javax.swing.*;
@Getter
public enum VehicleStatus {
    NORMAL("运行中"),
    REPAIR("维修中"),
    SCRAPPED("闲置");
    private final String status;
    VehicleStatus(String status) {
        this.status = status;
    }
}
