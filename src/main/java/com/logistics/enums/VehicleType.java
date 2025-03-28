package com.logistics.enums;

import lombok.Getter;

@Getter
public enum VehicleType {
    CAR("轿车"),
    SUV("SUV"),
    TRUCK("卡车"),
    MOTORCYCLE("摩托车"),
    ELECTRONEGATIVITIES("电动车"),
    BUS("客车");
    private final String type;
    VehicleType(String type) {
        this.type = type;
    }

}
