package com.logistics.domain.entity;

import cn.hutool.core.date.DateTime;
import com.logistics.enums.VehicleStatus;
import com.logistics.enums.VehicleType;
import lombok.Data;

import java.math.BigInteger;

@Data
public class Vehicle {


    private Integer id;  // 车辆ID

    private String licensePlate;  // 车牌号

    private String brand;  // 品牌

    private String model;  // 型号

    private String color;  // 颜色

    private VehicleType vehicleType;  // 车辆类型

    private BigInteger ownerId;  // 车主，外键关联车主表


    private DateTime purchaseDate;  // 购入时间

    private Integer mileage;  // 行驶里程

    private VehicleStatus status;  // 车辆状态

    private DateTime lastServiceDate;  // 最近保养日期

    private DateTime createdAt;  // 创建时间

    private DateTime updatedAt;  //  更新时间
}